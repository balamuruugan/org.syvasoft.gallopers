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
import org.compiere.model.MOrder;
import org.compiere.model.MRMA;
import org.compiere.model.MSysConfig;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
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
			
			m_processMsg = postCrusherProduction(we);
			
		}
		
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
		
		if(getTF_Crusher_Production_ID() > 0) {
			MCrusherProduction crProd = new MCrusherProduction(getCtx(), getTF_Crusher_Production_ID(), get_TrxName());
			crProd.reverseIt();
			crProd.saveEx();
			setTF_Crusher_Production_ID(0);
		}		
		
		// TODO Auto-generated method stub
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
		inout.setTF_WeighmentEntry_ID(getTF_WeighmentEntry_ID());		
		
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
		
		ioLine.setQty(qty);
		ioLine.setC_UOM_ID(Rent_UOM_ID);
		ioLine.set_ValueOfColumn("Price", price);
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
		
		//Don't Create Material Receipt for the same Transporter
		//It stops the recursive loop
		if(rv.getC_BPartner_ID() == getC_BPartner_ID())
			return;
		
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
	
	
	public String postCrusherProduction(MWeighmentEntry wEntry) {				
		String aggregateStockApproach = MSysConfig.getValue("AGGREGATE_STOCK_APPROACH","B", getAD_Client_ID(), getAD_Org_ID());  
		if(aggregateStockApproach.equals("B") && isSOTrx()) {
			
				MCrusherProduction cProd = new MCrusherProduction(getCtx(), 0, get_TrxName());
				MCrusherProductionConfig pConfig = MCrusherProductionConfig.getMCrusherProductionConfig(getCtx(), getAD_Org_ID(), "SO", wEntry.getM_Product_ID());
				if(pConfig == null)
					return null;
				cProd.setAD_Org_ID(getAD_Org_ID());					
				cProd.setTF_BlueMetal_Type("SO");
				cProd.setTF_WeighmentEntry_ID(getTF_WeighmentEntry_ID());
				cProd.setMovementDate(getMovementDate());
				cProd.setC_UOM_ID(wEntry.getC_UOM_ID());		
				cProd.setM_Warehouse_ID(getM_Warehouse_ID());
				MWarehouse wh = MWarehouse.get(getCtx(), getM_Warehouse_ID());
				cProd.setM_Locator_ID(wh.getDefaultLocator().get_ID());
				cProd.setRM_Product_ID(wEntry.getM_Product_ID());
				cProd.setQtyUsed(wEntry.getNetWeightUnit());				
				cProd.setDocStatus(DOCSTATUS_Drafted);
				cProd.setDocAction(DOCACTION_Prepare);
				cProd.saveEx();
				
				//Update Crusher Production Reference to Material Receipt
				setTF_Crusher_Production_ID(cProd.getTF_Crusher_Production_ID());
				
				cProd.createProductionBySales();
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

}
