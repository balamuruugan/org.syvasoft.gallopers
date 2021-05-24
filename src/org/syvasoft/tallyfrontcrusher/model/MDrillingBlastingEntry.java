package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MClient;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MPriceList;
import org.compiere.model.MProductCategory;
import org.compiere.model.MProduction;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.Env;

public class MDrillingBlastingEntry extends X_TF_DrillingBlasting {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6670510872985071699L;

	public MDrillingBlastingEntry(Properties ctx, int TF_DrillingBlasting_ID, String trxName) {
		super(ctx, TF_DrillingBlasting_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDrillingBlastingEntry(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public void processIt(String docAction) {
		if(docAction.equals(DocAction.ACTION_Complete)) {
			setProcessed(true);			
			setDocStatus(DOCSTATUS_Completed);
			createSubcontractInvoice();
			createFuelIssue();
		}
	}
	
	public void reverseIt() {
		setProcessed(false);
		setDocStatus(DOCSTATUS_Drafted);
		if(getC_Invoice_ID()>0) {
			TF_MInvoice inv = new TF_MInvoice(getCtx(), getC_Invoice_ID(), get_TrxName());
			if(inv.getDocStatus().equals(DOCSTATUS_Completed))
				inv.reverseCorrectIt();
			inv.saveEx();			
			setC_Invoice_ID(0);						
		}

		String whereClause="TF_DrillingBlasting_ID=?";
		List<MBlastingEntry> blEntries=new Query(getCtx(),MBlastingEntry.Table_Name,whereClause,get_TrxName())
				.setClient_ID()
				.setParameters(getTF_DrillingBlasting_ID())
				.list();
		for(MBlastingEntry blEntry : blEntries) {
			MFuelIssue fIssue=new MFuelIssue(getCtx(), blEntry.getTF_Fuel_Issue_ID(), get_TrxName());
			fIssue.reverseIt();
		}		
		
	}
	
	public void createSubcontractInvoice() {
		
		if(getC_BPartner_ID()>0) {
			TF_MBPartner bp = new TF_MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
			
			String whereClause="TF_DrillingBlasting_ID=?";
			MDrillingEntry drilEntry=new Query(getCtx(),MDrillingEntry.Table_Name,whereClause,get_TrxName())
					.setClient_ID()
					.setParameters(getTF_DrillingBlasting_ID())
					.first();
			
			//Purchase Invoice Header
			MGLPostingConfig config = MGLPostingConfig.getMGLPostingConfig(getCtx());
			TF_MInvoice invoice = new TF_MInvoice(getCtx(), 0, get_TrxName());
			invoice.setClientOrg(getAD_Client_ID(), getAD_Org_ID());
			invoice.setC_DocTypeTarget_ID(config.getTransporterInvoiceDocType_ID());	// AP Invoice		
			invoice.setDateInvoiced(getDateAcct());
			invoice.setDateAcct(getDateAcct());
			//
			invoice.setSalesRep_ID(Env.getAD_User_ID(getCtx()));
			//
			
			invoice.setBPartner(bp);
			invoice.setIsSOTrx(false);		
			
			
			//String desc = getFeet().doubleValue() + " Feet X "  + getHoles().doubleValue() + " Holes" ;		
			String desc ="";
			invoice.setDescription(desc);
			
			//Price List
			int m_M_PriceList_ID = Env.getContextAsInt(getCtx(), "#M_PriceList_ID");
			if(bp.getPO_PriceList_ID() > 0)
				m_M_PriceList_ID = bp.getPO_PriceList_ID();
			if(m_M_PriceList_ID == 0) {
				MPriceList pl = new Query(getCtx(), MPriceList.Table_Name, "IsDefault='Y' AND IsActive='Y'", get_TrxName())
						.setClient_ID().first();
				if(pl != null)
					m_M_PriceList_ID = pl.getM_PriceList_ID();
			}
			invoice.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
			if(invoice.getC_Currency_ID() == 0)
				invoice.setC_Currency_ID(MClient.get(Env.getCtx()).getC_Currency_ID());
			
					
			invoice.saveEx();
			//End Invoice Header
			
			//Invoice Line - Vehicle Rental Charge
			MInvoiceLine invLine = new MInvoiceLine(invoice);
			invLine.setM_Product_ID(drilEntry.getM_Product_ID(), true);				
			
			invLine.setQty(drilEntry.getTotalFeet());
			invLine.setDescription(drilEntry.getDescription());
			
			invLine.setPriceActual(drilEntry.getFeetRate());
			invLine.setPriceList(drilEntry.getFeetRate());
			invLine.setPriceLimit(drilEntry.getFeetRate());
			invLine.setPriceEntered(drilEntry.getFeetRate());
			
			invLine.setC_Tax_ID(1000000);
			invLine.saveEx();
			
			//Invoice DocAction
			if (!invoice.processIt(DocAction.ACTION_Complete))
				throw new AdempiereException("Failed when processing document - " + invoice.getProcessMsg());
			invoice.saveEx();
			
			setC_Invoice_ID(invoice.getC_Invoice_ID());		
		}
	}
	
	public void createFuelIssue() {
		
		String whereClause="TF_DrillingBlasting_ID=?";
		List<MBlastingEntry> blEntries=new Query(getCtx(),MBlastingEntry.Table_Name,whereClause,get_TrxName())
				.setClient_ID()
				.setParameters(getTF_DrillingBlasting_ID())
				.list();
		
		for(MBlastingEntry blEntry : blEntries) {
			MFuelIssue fIssue=new MFuelIssue(getCtx(), 0, get_TrxName());
			fIssue.setAD_Org_ID(getAD_Org_ID());
			fIssue.setDateAcct(getDateAcct());
			if(getC_BPartner_ID()>0) {
				fIssue.setIssueType("P");
				fIssue.setC_BPartner_ID(getC_BPartner_ID());
			}
			else {
				fIssue.setIssueType("E");
			}
			
			TF_MProduct prod=new TF_MProduct(getCtx(), blEntry.getM_Product_ID(), get_TrxName());
			TF_MProductCategory prodcategory=new TF_MProductCategory(getCtx(), prod.getM_Product_Category_ID(), get_TrxName());
			
			fIssue.setM_Product_ID(blEntry.getM_Product_ID());
			fIssue.setC_ElementValue_ID(prodcategory.getSpareExpensesAcct_ID());
			fIssue.setM_Locator_ID(prod.getM_Locator_ID());
			fIssue.setQty(blEntry.getQty());
			fIssue.setQtyAvailable(blEntry.getQtyAvailable());
			fIssue.setRate(blEntry.getPrice());
			fIssue.setAmt(blEntry.getQty().multiply(blEntry.getPrice()));
			fIssue.saveEx();
			fIssue.processIt(DocAction.ACTION_Complete);
			
			blEntry.setTF_Fuel_Issue_ID(fIssue.getTF_Fuel_Issue_ID());
			blEntry.saveEx();
		}
		
	}
}
