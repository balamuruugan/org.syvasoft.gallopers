package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MInOut;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MPriceList;
import org.compiere.model.MSysConfig;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.Env;

public class MCrusherKatingEntry extends X_TF_CrusherKatingEntry {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6641225223046443500L;

	public MCrusherKatingEntry(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCrusherKatingEntry(Properties ctx, int TF_CrusherKatingEntry_ID, String trxName) {
		super(ctx, TF_CrusherKatingEntry_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public void processIt(String docAction) {
		if(DocAction.ACTION_Prepare.equals(docAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(DocAction.ACTION_Complete.equals(docAction)) {
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
			
			MWeighmentEntry wEntry = (MWeighmentEntry) getTF_WeighmentEntry();
			if(wEntry != null) {
				wEntry.shipped();
				wEntry.close();				
				wEntry.saveEx();
			}
			
			int BoulderID = MSysConfig.getIntValue("BOULDER_ID", 1000233, getAD_Client_ID(), getAD_Org_ID());
			if(wEntry.getTF_Send_To().equals("P") && BoulderID == getM_Product_ID()) {
				MBoulderMovement.createBoulderIssue(get_TrxName(), getDateAcct(), getAD_Org_ID(), getM_Product_ID(), getTonnage(), getTF_WeighmentEntry_ID(), getM_Warehouse_ID());
				MSubcontractMaterialMovement.createRawmaterialMovement(get_TrxName(), getDateAcct(), getAD_Org_ID(), 0, 0, getM_Product_ID(), getTF_WeighmentEntry_ID(), getTonnage());
				
				if(!MSysConfig.getValue("AGGREGATE_STOCK_APPROACH","B", getAD_Client_ID(), getAD_Org_ID()).equals("B")) {
					postCrusherProduction();	
				}
			}
						
			
			//Transporter Invoice has to be created according to client requirement.
			//Material Receipt should be created for the consolidate invoice scenario.
			MRentedVehicle vehicle = new MRentedVehicle(getCtx(), getTF_RentedVehicle_ID(), get_TrxName());
			if(!vehicle.isTransporter() || getTF_RentedVehicle_ID() > 0) {
				return;
			}
			
			//Transporter Invoice			
			MBPartner bp = new MBPartner(getCtx(), vehicle.getC_BPartner_ID(), get_TrxName());			
			//Invoice Header
			TF_MInvoice invoice = new TF_MInvoice(getCtx(), 0, get_TrxName());
			invoice.setClientOrg(getAD_Client_ID(), getAD_Org_ID());			
			invoice.setC_DocTypeTarget_ID(MGLPostingConfig.getMGLPostingConfig(getCtx()).getTransporterInvoiceDocType_ID());
			
			invoice.setDateInvoiced(getDateAcct());
			invoice.setDateAcct(getDateAcct());
			//
			invoice.setSalesRep_ID(Env.getAD_User_ID(getCtx()));
			//
			invoice.setBPartner(bp);
			invoice.setIsSOTrx(false);		
			invoice.setDescription(getDocumentNo());
			if(getDescription() != null && getDescription().length() > 0)
				invoice.addDescription(getDescription());
			
			//Price List
			int m_M_PriceList_ID = Env.getContextAsInt(getCtx(), "#M_PriceList_ID");
			if(bp.getPO_PriceList_ID() > 0)
				m_M_PriceList_ID = bp.getPO_PriceList_ID();
			
			if(m_M_PriceList_ID == 0) {
				m_M_PriceList_ID = MPriceList.getDefault(getCtx(), false).getM_PriceList_ID();
			}
				
			
			invoice.setM_PriceList_ID(m_M_PriceList_ID);
			invoice.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
			
			//Financial Dimension - Profit Center
			invoice.setC_Project_ID(getC_Project_ID());
			
			invoice.saveEx();
			//End Invoice Header
			
			//Invoice Line - Vehicle Rental Charge
			MInvoiceLine invLine = new MInvoiceLine(invoice);
			invLine.setM_Product_ID(vehicle.getM_Product_ID(), true);
			invLine.setDescription("Transportation Charge for Kating");
			
			int load_uom_id = MSysConfig.getIntValue("LOAD_UOM", 1000072, getAD_Client_ID());
			int tonnage_uom_Id = MSysConfig.getIntValue("TONNAGE_UOM", 1000069, getAD_Client_ID());
			
			int uom_id = tonnage_uom_Id;
			BigDecimal qty = getTonnage(); 
			if(KATINGENTRYTYPE_Load.equals(getKatingEntryType())) {
				uom_id = load_uom_id;
				qty = getTotalLoad();
			}
			
			invLine.setC_UOM_ID(uom_id);
			invLine.setQty(qty);			
			invLine.setPriceActual(getTransport_Price());
			invLine.setPriceList(getTransport_Price());
			invLine.setPriceLimit(getTransport_Price());
			invLine.setPriceEntered(getTransport_Price());			
			invLine.saveEx();
			
						
			//DocAction
			if (!invoice.processIt(docAction))
				throw new AdempiereException("Failed when processing document - " + invoice.getProcessMsg());
			invoice.saveEx();
			//End DocAction
			
			setTransporterInvoice_ID(invoice.getC_Invoice_ID());
			
			
			//--------------------------------------
			
			// Loader invoice
			if(getLoaderVehicle_ID() > 0) {
				vehicle = new MRentedVehicle(getCtx(), getLoaderVehicle_ID(), get_TrxName());
				bp = new MBPartner(getCtx(), vehicle.getC_BPartner_ID(), get_TrxName());			
				//Invoice Header
				invoice = new TF_MInvoice(getCtx(), 0, get_TrxName());
				invoice.setClientOrg(getAD_Client_ID(), getAD_Org_ID());			
				invoice.setC_DocTypeTarget_ID(MGLPostingConfig.getMGLPostingConfig(getCtx()).getTransporterInvoiceDocType_ID());
				
				invoice.setDateInvoiced(getDateAcct());
				invoice.setDateAcct(getDateAcct());
				//
				invoice.setSalesRep_ID(Env.getAD_User_ID(getCtx()));
				//
				invoice.setBPartner(bp);
				invoice.setIsSOTrx(false);		
				invoice.setDescription(getDocumentNo());
				if(getDescription() != null && getDescription().length() > 0)
					invoice.addDescription(getDescription());
				
				//Price List
				 m_M_PriceList_ID = Env.getContextAsInt(getCtx(), "#M_PriceList_ID");
				if(bp.getPO_PriceList_ID() > 0)
					m_M_PriceList_ID = bp.getPO_PriceList_ID();
				
				if(m_M_PriceList_ID == 0) {
					m_M_PriceList_ID = MPriceList.getDefault(getCtx(), false).getM_PriceList_ID();
				}
				
				invoice.setM_PriceList_ID(m_M_PriceList_ID);
				invoice.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
				
				//Financial Dimension - Profit Center
				invoice.setC_Project_ID(getC_Project_ID());
				
				invoice.saveEx();
				//End Invoice Header
				
				//Invoice Line - Vehicle Rental Charge
				invLine = new MInvoiceLine(invoice);
				invLine.setM_Product_ID(vehicle.getM_Product_ID(), true);
				invLine.setDescription("Loading Charge for Kating");
				invLine.setC_UOM_ID(uom_id);
				invLine.setQty(qty);			
				invLine.setPriceActual(getLoading_Price());
				invLine.setPriceList(getLoading_Price());
				invLine.setPriceLimit(getLoading_Price());
				invLine.setPriceEntered(getLoading_Price());			
				invLine.saveEx();
				
							
				//DocAction
				if (!invoice.processIt(docAction))
					throw new AdempiereException("Failed when processing document - " + invoice.getProcessMsg());
				invoice.saveEx();
				//End DocAction
				
				setLoaderInvoice_ID(invoice.getC_Invoice_ID());
			}
			
		}
	}
	
	public void reverseIt() {
		if(getTransporterInvoice_ID() > 0 ) {
			TF_MInvoice inv = new TF_MInvoice(getCtx(), getTransporterInvoice_ID(), get_TrxName());
			if(inv.getDocStatus().equals(DocAction.ACTION_Complete))
				inv.reverseCorrectIt();
			inv.saveEx();
			setTransporterInvoice_ID(0);
		}
		if(getLoaderInvoice_ID() > 0 ) {
			TF_MInvoice inv = new TF_MInvoice(getCtx(), getLoaderInvoice_ID(), get_TrxName());
			if(inv.getDocStatus().equals(DocAction.ACTION_Complete))
				inv.reverseCorrectIt();
			inv.saveEx();
			setLoaderInvoice_ID(0);
		}
		setDocStatus(DOCSTATUS_InProgress);
		setProcessed(false);
		MWeighmentEntry wEntry = (MWeighmentEntry) getTF_WeighmentEntry();
		if(wEntry != null) {
			wEntry.reverseShipped();
			wEntry.reverse();
			wEntry.saveEx();
		}
		MSubcontractMaterialMovement.deleteWeighmentMovement(getTF_WeighmentEntry_ID(), get_TrxName());
		MBoulderMovement.deleteBoulderMovement(getTF_WeighmentEntry_ID(), get_TrxName());
		reverseCrusherProduction();
		reverseServiceReceipts();
		
	}
	
	public void reverseServiceReceipts() {
		
		String whereClause = "TF_WeighmentEntry_ID = ? AND MovementType = ? AND DocStatus = 'CO'";
		List<TF_MInOut> list = new Query(getCtx(), TF_MInOut.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getTF_WeighmentEntry_ID(), MInOut.MOVEMENTTYPE_VendorReceipts)
				.list();
		for(TF_MInOut io : list) {
			if(io.getDocStatus().equals(DOCSTATUS_Completed))
				io.reverseCorrectIt();
			io.saveEx();
		}
		
	}
	public void postCrusherProduction() {
		String m_processMsg = null;
		//Create Crusher Production
		TF_MProduct prod=new TF_MProduct(getCtx(), getM_Product_ID(), get_TrxName());
		MCrusherProduction cProd = new MCrusherProduction(getCtx(), 0, get_TrxName());
		cProd.setAD_Org_ID(getAD_Org_ID());
		cProd.setTF_ProductionPlant_ID(getTF_ProductionPlant_ID());		
		cProd.setTF_BlueMetal_Type(getTF_BlueMetal_Type());
		cProd.setTF_WeighmentEntry_ID(getTF_WeighmentEntry_ID());
		cProd.setMovementDate(getCreated());
		cProd.setC_UOM_ID(prod.getC_UOM_ID());		
		cProd.setM_Warehouse_ID(getM_Warehouse_ID());
		MWarehouse wh = MWarehouse.get(getCtx(),getM_Warehouse_ID());
		cProd.setM_Locator_ID(wh.getDefaultLocator().get_ID());
		cProd.setRM_Product_ID(getM_Product_ID());
		cProd.setQtyUsed(getTonnage());
		//cProd.setDescription("Created from Boulder Receipt : " + getDocumentNo());
		cProd.setDescription("Created from Boulder Receipt : ");
		cProd.setDocStatus(MBoulderReceipt.DOCSTATUS_Drafted);
		cProd.setDocAction(MBoulderReceipt.DOCACTION_Prepare);
		cProd.saveEx();
		
		//Update Crusher Production Reference to Boulder Receipt
		setTF_Crusher_Production_ID(cProd.getTF_Crusher_Production_ID());
		
		cProd.createProduction(true);
		cProd.saveEx();		
		//End Create
		
		//Post Crusher Production
		m_processMsg = cProd.processIt(MBoulderReceipt.DOCACTION_Complete);
		if(m_processMsg == null)			
			cProd.saveEx();
		
		//return m_processMsg;
	}
	
	public void reverseCrusherProduction() {
		if(getTF_Crusher_Production_ID() > 0) {
			MCrusherProduction crProd = new MCrusherProduction(getCtx(), getTF_Crusher_Production_ID(), get_TrxName());
			crProd.reverseIt();
			crProd.saveEx();
			setTF_Crusher_Production_ID(0);
		}
	}
	
}
