package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MClient;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MPriceList;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.Env;

public class MDrillingEntry extends X_TF_DrillingEntry {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3405548409534643062L;

	public MDrillingEntry(Properties ctx, int TF_DrillingEntry_ID, String trxName) {
		super(ctx, TF_DrillingEntry_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDrillingEntry(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if(getTF_TripSheet_ID() > 0) {
			MTripSheet ts = new MTripSheet(getCtx(), getTF_TripSheet_ID(), get_TrxName());
			ts.updateRentQty();
			ts.saveEx();
		}
		return super.afterSave(newRecord, success);
	}
	
	public void processIt(String docAction) {
		if(docAction.equals(DocAction.ACTION_Complete)) {
			setProcessed(true);
			setProcessing(true);
			setDocStatus(DOCSTATUS_Completed);
			createSubcontractInvoice();
		}
	}
	
	public void reverseIt() {
		setProcessed(false);
		setProcessing(false);		
		setDocStatus(DOCSTATUS_Drafted);
		if(getC_Invoice_ID()>0) {
			TF_MInvoice inv = new TF_MInvoice(getCtx(), getC_Invoice_ID(), get_TrxName());
			if(inv.getDocStatus().equals(DOCSTATUS_Completed))
				inv.reverseCorrectIt();
			inv.saveEx();			
			setC_Invoice_ID(0);						
		}
	}
	
	public void createSubcontractInvoice() {
		
		TF_MBPartner bp = new TF_MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
		
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
		
		
		String desc = getFeet().doubleValue() + " Feet X "  + getHoles().doubleValue() + " Holes" ;		
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
		invLine.setM_Product_ID(getM_Product_ID(), true);				
		
		invLine.setQty(getTotalFeet());
		invLine.setDescription(getDescription());
		
		invLine.setPriceActual(getFeetRate());
		invLine.setPriceList(getFeetRate());
		invLine.setPriceLimit(getFeetRate());
		invLine.setPriceEntered(getFeetRate());
		
		invLine.setC_Tax_ID(1000000);
		invLine.saveEx();
		
		//Invoice DocAction
		if (!invoice.processIt(DocAction.ACTION_Complete))
			throw new AdempiereException("Failed when processing document - " + invoice.getProcessMsg());
		invoice.saveEx();
		
		setC_Invoice_ID(invoice.getC_Invoice_ID());		
		
	}
	
}

