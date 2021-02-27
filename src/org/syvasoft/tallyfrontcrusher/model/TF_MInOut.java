package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MPriceList;
import org.compiere.model.MProduct;
import org.compiere.model.MRMA;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTable;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.Env;
import org.compiere.util.Msg;


public class TF_MInOut extends MInOut {

	/**
	 * 
	 */
	private static final long serialVersionUID = -662134145363709054L;

	public TF_MInOut(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public TF_MInOut(Properties ctx, int M_InOut_ID, String trxName) {
		super(ctx, M_InOut_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	

	public TF_MInOut (MOrder order, int C_DocTypeShipment_ID, Timestamp movementDate)
	{
		super(order, C_DocTypeShipment_ID, movementDate);
	}
	
	/** Column name TF_WeighmentEntry_ID */
    public static final String COLUMNNAME_TF_WeighmentEntry_ID = "TF_WeighmentEntry_ID";
    
	/** Set TF_WeighmentEntry_ID.
		@param TF_WeighmentEntry_ID 
		Weighment Entry
	*/
	public void setTF_WeighmentEntry_ID (int TF_WeighmentEntry_ID)
	{
		if (TF_WeighmentEntry_ID < 1) 
			set_Value (COLUMNNAME_TF_WeighmentEntry_ID, null);
		else 
			set_Value (COLUMNNAME_TF_WeighmentEntry_ID, Integer.valueOf(TF_WeighmentEntry_ID));
	}

	/** Get TF_WeighmentEntry_ID.
		@return TF_WeighmentEntry_ID
	  */
	public int getTF_WeighmentEntry_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_WeighmentEntry_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	boolean createConsolidatedTransportInvoice = MSysConfig.getBooleanValue("CONSOLIDATED_TRANSPORT_INVOICE_ENABLED", true , getAD_Client_ID(), getAD_Org_ID());
	
	   /** Column name TF_Crusher_Production_ID */
    public static final String COLUMNNAME_TF_Crusher_Production_ID = "TF_Crusher_Production_ID";

	/** Set Crusher Production.
	@param TF_Crusher_Production_ID Crusher Production	  */
	public void setTF_Crusher_Production_ID (int TF_Crusher_Production_ID)
	{
		if (TF_Crusher_Production_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_Crusher_Production_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_Crusher_Production_ID, Integer.valueOf(TF_Crusher_Production_ID));
	}
	
	/** Get Crusher Production.
		@return Crusher Production	  */
	public int getTF_Crusher_Production_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_Crusher_Production_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name C_BPartnerDebit_ID */
    public static final String COLUMNNAME_C_BPartnerDebit_ID = "C_BPartnerDebit_ID";
    
	public org.compiere.model.I_C_BPartner getC_BPartnerDebit() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartnerDebit_ID(), get_TrxName());	}

	/** Set Debit To.
		@param C_BPartnerDebit_ID Debit To	  */
	public void setC_BPartnerDebit_ID (int C_BPartnerDebit_ID)
	{
		if (C_BPartnerDebit_ID < 1) 
			set_Value (COLUMNNAME_C_BPartnerDebit_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartnerDebit_ID, Integer.valueOf(C_BPartnerDebit_ID));
	}

	/** Get Debit To.
		@return Debit To	  */
	public int getC_BPartnerDebit_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartnerDebit_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Column name C_InvoiceDebitNote_ID */
    public static final String COLUMNNAME_C_InvoiceDebitNote_ID = "C_InvoiceDebitNote_ID";

	public org.compiere.model.I_C_Invoice getC_InvoiceDebitNote() throws RuntimeException
    {
		return (org.compiere.model.I_C_Invoice)MTable.get(getCtx(), org.compiere.model.I_C_Invoice.Table_Name)
			.getPO(getC_InvoiceDebitNote_ID(), get_TrxName());	}

	/** Set Debit Note.
		@param C_InvoiceDebitNote_ID Debit Note	  */
	public void setC_InvoiceDebitNote_ID (int C_InvoiceDebitNote_ID)
	{
		if (C_InvoiceDebitNote_ID < 1) 
			set_Value (COLUMNNAME_C_InvoiceDebitNote_ID, null);
		else 
			set_Value (COLUMNNAME_C_InvoiceDebitNote_ID, Integer.valueOf(C_InvoiceDebitNote_ID));
	}

	/** Get Debit Note.
		@return Debit Note	  */
	public int getC_InvoiceDebitNote_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_InvoiceDebitNote_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	int ExplosivesVendor_ID = MSysConfig.getIntValue("EXPLOSIVES_VENDOR_ID", 1000662);
	int QuarrySubcontractor_ID = MSysConfig.getIntValue("QUARRY_SUBCONTRACTOR_ID", 1000580);
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(!isSOTrx() && getC_BPartner_ID() == ExplosivesVendor_ID) {
			setC_BPartnerDebit_ID(QuarrySubcontractor_ID);
		}
		return super.beforeSave(newRecord);
	}

	public boolean materialReceipt = true;
	
	@Override
	public String completeIt() {
		if(getTF_WeighmentEntry_ID() > 0) {			
			
			MWeighmentEntry we = new MWeighmentEntry(getCtx(), getTF_WeighmentEntry_ID(), get_TrxName());
			
			if(we.getWeighmentEntryType().equals(MWeighmentEntry.WEIGHMENTENTRYTYPE_Sales) && isSOTrx())
				we.shipped();
			else if(!we.getWeighmentEntryType().equals(MWeighmentEntry.WEIGHMENTENTRYTYPE_Sales) && !isSOTrx() )
				we.shipped();			
			
			we.saveEx();
			
			if(createConsolidatedTransportInvoice)
				createTransportMaterialReceipt();
			createMaterialMovement(we);
			
			m_processMsg = postCrusherProduction(we);
			
		}
		createDebitNote();
		// TODO Auto-generated method stub
		return super.completeIt();
	}
	
	@Override
	public boolean reverseCorrectIt() {
		if(getTF_WeighmentEntry_ID() >0) {			
			MWeighmentEntry we = new MWeighmentEntry(getCtx(), getTF_WeighmentEntry_ID(), get_TrxName());
			if(we.getWeighmentEntryType().equals(MWeighmentEntry.WEIGHMENTENTRYTYPE_Sales) && isSOTrx())
				we.reverseShipped();
			else if(!we.getWeighmentEntryType().equals(MWeighmentEntry.WEIGHMENTENTRYTYPE_Sales) && !isSOTrx() )
				we.reverseShipped();				
			we.saveEx();
			
			reverseTransportMaterialReceipt();
		}
		
		MSubcontractMaterialMovement.deleteWeighmentMovement(getTF_WeighmentEntry_ID(), get_TrxName());
		MBoulderMovement.deleteBoulderMovement(getTF_WeighmentEntry_ID(), get_TrxName());
		
		if(getTF_Crusher_Production_ID() > 0) {
			MCrusherProduction crProd = new MCrusherProduction(getCtx(), getTF_Crusher_Production_ID(), get_TrxName());
			crProd.reverseIt();
			crProd.saveEx();
			setTF_Crusher_Production_ID(0);
		}		
		reverseDebitNote();
		
		return super.reverseCorrectIt();
	}
	
	public void createTransportMaterialReceipt() {
		
		
		MWeighmentEntry we = new MWeighmentEntry(getCtx(), getTF_WeighmentEntry_ID(), get_TrxName());
				
		if(we.getTF_RentedVehicle_ID() == 0)
			return;
		
		MRentedVehicle rv = new MRentedVehicle(getCtx(), we.getTF_RentedVehicle_ID(), get_TrxName());
		if(rv.isOwnVehicle() || !rv.isTransporter())
			return;
		
		//Don't Create Material Receipt for the same Transporter
		//It stops the recursive loop
		if(rv.getC_BPartner_ID() == getC_BPartner_ID())
			return;
		
		MDestination dest = new MDestination(getCtx(), we.getTF_Destination_ID(), get_TrxName());
		TF_MBPartner bp = new TF_MBPartner(getCtx(), rv.getC_BPartner_ID(), get_TrxName());
		
		TF_MInOut inout = new TF_MInOut(getCtx(), 0, get_TrxName());
		inout.materialReceipt = false;
		inout.setTF_WeighmentEntry_ID(getTF_WeighmentEntry_ID());		
		inout.setIsSOTrx(false);
		inout.setC_DocType_ID(MGLPostingConfig.getMGLPostingConfig(getCtx()).getMaterialReceipt_DocType_ID());
		inout.setMovementType(MInOut.MOVEMENTTYPE_VendorReceipts);		
		inout.setDateAcct(getDateAcct());		
		inout.setC_BPartner_ID(rv.getC_BPartner_ID());
		inout.setC_BPartner_Location_ID(bp.getPrimaryC_BPartner_Location_ID());
		inout.setAD_User_ID(bp.getAD_User_ID());
		inout.setM_Warehouse_ID(getM_Warehouse_ID());
		inout.setPriorityRule(TF_MInOut.PRIORITYRULE_Medium);
		inout.setFreightCostRule(TF_MInOut.FREIGHTCOSTRULE_FreightIncluded);
		inout.saveEx(get_TrxName());
		
		//Material Receipt Line
		MInOutLine ioLine = new MInOutLine(inout);
		MWarehouse wh = (MWarehouse) getM_Warehouse();
		
		ioLine.setLine(10);
		ioLine.setM_Product_ID(rv.getM_Product_ID());
		ioLine.setM_Locator_ID(wh.getDefaultLocator().get_ID());
		
		
		int Rent_UOM_ID = 0;
		BigDecimal qty = BigDecimal.ZERO;
		BigDecimal price = BigDecimal.ZERO;		
					
		//Get Vehicle Rent from Shipment Line
		String whereClause = "M_InOut_ID = ? AND M_Product_ID = ? ";
		TF_MInOutLine srcLine = new Query(getCtx(), TF_MInOutLine.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getM_InOut_ID(), rv.getM_Product_ID())
				.first();
		
		//This has to be customized once again according to customer requirements.
		if(srcLine != null) {
			Rent_UOM_ID = srcLine.getC_UOM_ID();
			qty = srcLine.getQtyEntered();
			Object srcPrice = srcLine.get_Value("Price");
			if(srcPrice != null)
				price = (BigDecimal) srcPrice; 
		}
		else {
			Rent_UOM_ID = we.getMT_UOM_ID();
			qty = we.getMT();
		}
		
		ioLine.setQty(qty);
		ioLine.setC_UOM_ID(Rent_UOM_ID);
		ioLine.set_ValueOfColumn("Price", price);
		if(we.getTF_Destination_ID() > 0)
			ioLine.setDescription("Destination : " + dest.getName());		
		ioLine.saveEx(get_TrxName());
		
		//Material Receipt DocAction
		if (!inout.processIt(DocAction.ACTION_Complete))
			throw new AdempiereException("Failed when processing document - " + inout.getProcessMsg());
		
		inout.saveEx();
	}
	
	public void reverseTransportMaterialReceipt() {
		MWeighmentEntry we = new MWeighmentEntry(getCtx(), getTF_WeighmentEntry_ID(), get_TrxName());
		
		if(we.getTF_RentedVehicle_ID() == 0)
			return;
		
		MRentedVehicle rv = new MRentedVehicle(getCtx(), we.getTF_RentedVehicle_ID(), get_TrxName());
		if(rv.isOwnVehicle() || !rv.isTransporter())
			return;
		
		//Don't reverse Material Receipt for other than Transporters
		//It stops the recursive loop
		if(rv.getC_BPartner_ID() == getC_BPartner_ID())
			return;
		
		String whereClause = "TF_WeighmentEntry_ID = ? AND MovementType = ? AND DocStatus = 'CO' AND M_InOut_ID != ? ";
		List<TF_MInOut> list = new Query(getCtx(), TF_MInOut.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getTF_WeighmentEntry_ID(), MInOut.MOVEMENTTYPE_VendorReceipts, getM_InOut_ID())
				.list();
		for(TF_MInOut io : list) {
			if(io.getDocStatus().equals(DOCSTATUS_Completed))
				io.reverseCorrectIt();
			io.saveEx();
		}
	}
	
	public void createMaterialMovement(MWeighmentEntry wEntry) {		
		if(!materialReceipt)
			return;
		
		int BoulderID = MSysConfig.getIntValue("BOULDER_ID", 1000233, getAD_Client_ID(), getAD_Org_ID());
		if(!isSOTrx()) {
			if(BoulderID == wEntry.getM_Product_ID() && MWeighmentEntry.TF_SEND_TO_Production.equals(wEntry.getTF_Send_To())) {
				MSubcontractMaterialMovement.createRawmaterialMovement(get_TrxName(), getDateAcct(), getAD_Org_ID(),				
						0, 0, BoulderID, getTF_WeighmentEntry_ID(), 0, wEntry.getNetWeightUnit());
			}
			else if(BoulderID == wEntry.getM_Product_ID() && MWeighmentEntry.TF_SEND_TO_Stock.equals(wEntry.getTF_Send_To())) {
				MBoulderMovement.createBoulderReceipt(get_TrxName(), getDateAcct(), getAD_Org_ID(), BoulderID, wEntry.getNetWeightUnit(), getTF_WeighmentEntry_ID(), getM_Warehouse_ID());
			}
		}
		else {
			MProduct rm = MProduct.get(getCtx(), BoulderID);
			//Need to add support for CFT sales and the resepctive MT material movement.
			if(wEntry.getC_UOM_ID() == rm.getC_UOM_ID() && wEntry.getM_Product_ID() != BoulderID) {				
				TF_MProductCategory pc = new TF_MProductCategory(getCtx(), wEntry.getM_Product().getM_Product_Category_ID(), get_TrxName());
				if(pc.isTrackMaterialMovement())
					MSubcontractMaterialMovement.createMaterialMovement(get_TrxName(), getDateAcct(), getAD_Org_ID(), getC_Order_ID(), 
							getC_BPartner_ID(), wEntry.getM_Product_ID(), wEntry.getNetWeightUnit(), getTF_WeighmentEntry_ID());
			}
			else if(wEntry.getM_Product_ID() == BoulderID && getTF_WeighmentEntry_ID() > 0) {
				MBoulderMovement.createBoulderIssue(get_TrxName(), getDateAcct(), getAD_Org_ID(), wEntry.getM_Product_ID(),
						wEntry.getNetWeightUnit(), getTF_WeighmentEntry_ID(), getM_Warehouse_ID());
			}
		}
	}
	
	public String postCrusherProduction(MWeighmentEntry wEntry) {				
		String aggregateStockApproach = MSysConfig.getValue("AGGREGATE_STOCK_APPROACH","B", getAD_Client_ID(), getAD_Org_ID());
		int Boulder_ID = MSysConfig.getIntValue("BOULDER_ID",getAD_Client_ID(), getAD_Org_ID());
		if(aggregateStockApproach.equals("B") && isSOTrx() && Boulder_ID != wEntry.getM_Product_ID()) {
			
				MCrusherProduction cProd = new MCrusherProduction(getCtx(), 0, get_TrxName());
				MCrusherProductionConfig pConfig = MCrusherProductionConfig.getMCrusherProductionConfig(getCtx(), getAD_Org_ID(), "SO", wEntry.getM_Product_ID());
				if(pConfig == null)
					return null;
				cProd.setAD_Org_ID(getAD_Org_ID());					
				cProd.setTF_BlueMetal_Type("SO");
				cProd.setTF_ProductionPlant_ID(pConfig.getTF_ProductionPlant_ID());
				cProd.setTF_WeighmentEntry_ID(getTF_WeighmentEntry_ID());
				cProd.setMovementDate(getMovementDate());
				cProd.setC_UOM_ID(wEntry.getC_UOM_ID());		
				cProd.setM_Warehouse_ID(getM_Warehouse_ID());
				MWarehouse wh = MWarehouse.get(getCtx(), getM_Warehouse_ID());
				cProd.setM_Locator_ID(wh.getDefaultLocator().get_ID());
				cProd.setRM_Product_ID(Boulder_ID);
				cProd.setQtyUsed(wEntry.getNetWeightUnit());				
				cProd.setDocStatus(DOCSTATUS_Drafted);
				cProd.setDocAction(DOCACTION_Prepare);
				cProd.saveEx();
				
				//Update Crusher Production Reference to Material Receipt
				setTF_Crusher_Production_ID(cProd.getTF_Crusher_Production_ID());
				
				cProd.createProductionBySales(wEntry.getM_Product_ID());
				cProd.saveEx();		
				//End Create
				
				//Post Crusher Production
				m_processMsg = cProd.processIt(DOCACTION_Complete);
				if(m_processMsg == null)			
					cProd.saveEx();				
			
			}
		else if(!isSOTrx() && aggregateStockApproach.equals("O") && 
					MSysConfig.getIntValue("BOULDER_ID",getAD_Client_ID(), getAD_Org_ID()) == wEntry.getM_Product_ID())  {
					
				//Create Crusher Production
				MCrusherProduction cProd = new MCrusherProduction(getCtx(), 0, get_TrxName());
				cProd.setAD_Org_ID(getAD_Org_ID());
				cProd.setTF_ProductionPlant_ID(wEntry.getTF_ProductionPlant_ID());		
				cProd.setTF_BlueMetal_Type(wEntry.getTF_BlueMetal_Type());
				cProd.setTF_WeighmentEntry_ID(getTF_WeighmentEntry_ID());
				cProd.setMovementDate(getMovementDate());
				cProd.setC_UOM_ID(wEntry.getC_UOM_ID());		
				cProd.setM_Warehouse_ID(getM_Warehouse_ID());
				MWarehouse wh = MWarehouse.get(getCtx(), getM_Warehouse_ID());
				cProd.setM_Locator_ID(wh.getDefaultLocator().get_ID());
				cProd.setRM_Product_ID(wEntry.getM_Product_ID());
				cProd.setQtyUsed(wEntry.getNetWeightUnit());
				cProd.setDescription("Created from Material Receipt : " + getDocumentNo());
				cProd.setDocStatus(DOCSTATUS_Drafted);
				cProd.setDocAction(DOCACTION_Prepare);
				cProd.saveEx();
				
				//Update Crusher Production Reference to Material Receipt
				setTF_Crusher_Production_ID(cProd.getTF_Crusher_Production_ID());
				
				cProd.createProduction(true);
				cProd.saveEx();		
				//End Create
				
				//Post Crusher Production
				m_processMsg = cProd.processIt(DOCACTION_Complete);
				if(m_processMsg == null)			
					cProd.saveEx();
			}
		
		return m_processMsg;
	}	
	
	private void createDebitNote() {
	
		if(getC_BPartnerDebit_ID() == 0 || getC_InvoiceDebitNote_ID() > 0)
			return;
		
		//Debit Note Header
		//if(getRate().doubleValue() > 0) {
		TF_MInvoice invoice = new TF_MInvoice(getCtx(), 0, get_TrxName());
		invoice.setClientOrg(getAD_Client_ID(), getAD_Org_ID());
		invoice.setC_DocTypeTarget_ID(MGLPostingConfig.getMGLPostingConfig(getCtx()).getDebitNote_DocType_ID());			
		invoice.setDateInvoiced(getDateAcct());
		invoice.setDateAcct(getDateAcct());
		//
		invoice.setSalesRep_ID(Env.getAD_User_ID(getCtx()));
		//
		TF_MBPartner bp = new TF_MBPartner(getCtx(), getC_BPartnerDebit_ID(), get_TrxName());
		invoice.setBPartner(bp);
		invoice.setIsSOTrx(false);		
		
		//String description = getDocumentNo();		
		invoice.setDescription("Material Receipt: " + getDocumentNo());
		if(getDescription() != null && getDescription().length() > 0)			 		
			invoice.addDescription(getDescription());
		
		//Price List
		int m_M_PriceList_ID = Env.getContextAsInt(getCtx(), "#M_PriceList_ID");
		if(bp.getPO_PriceList_ID() > 0)
			m_M_PriceList_ID = bp.getPO_PriceList_ID();			
		invoice.setM_PriceList_ID(m_M_PriceList_ID);
		invoice.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
		
		//Financial Dimension - Profit Center			
		invoice.setC_Project_ID(getC_Project_ID());
		
		invoice.saveEx();
		//End Invoice Header
		
		//Invoice Line - Vehicle Rental Charge
		for (MInOutLine ioLine : getLines()) {
			MInvoiceLine invLine = new MInvoiceLine(invoice);
		
			invLine.setM_Product_ID(ioLine.getM_Product_ID(), true);
			invLine.setQty(ioLine.getMovementQty());			
			BigDecimal price = MPriceListUOM.getPrice(getCtx(), invLine.getM_Product_ID(), invLine.getC_UOM_ID(), QuarrySubcontractor_ID, false, getDateAcct());
			
			invLine.setPriceActual(price);
			invLine.setPriceList(price);
			invLine.setPriceLimit(price);
			invLine.setPriceEntered(price);
			invLine.setC_Tax_ID(1000000);
			invLine.saveEx();
		}
		
		setC_InvoiceDebitNote_ID(invoice.getC_Invoice_ID());
				
	}
	
	private void reverseDebitNote() {		
		if(getC_InvoiceDebitNote_ID() > 0 ) {
			TF_MInvoice inv = new TF_MInvoice(getCtx(), getC_InvoiceDebitNote_ID(), get_TrxName());
			if(inv.getDocStatus().equals(DOCSTATUS_Completed))
				inv.reverseCorrectIt();
			else
				inv.voidIt();
			inv.saveEx();
		}
	}

}
