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
import org.compiere.model.MSysConfig;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

public class MSubcontractMaterialMovement extends X_TF_RMSubcon_Movement {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2861377545557568568L;

	public MSubcontractMaterialMovement(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MSubcontractMaterialMovement(Properties ctx, int TF_RMSubcon_Movement_ID, String trxName) {
		super(ctx, TF_RMSubcon_Movement_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(newRecord && getTF_WeighmentEntry_ID() > 0) {
			setWarehouse();
		}
		return super.beforeSave(newRecord);
	}
	
	public void setWarehouse() {
		MWeighmentEntry we = new MWeighmentEntry(getCtx(), getTF_WeighmentEntry_ID(), get_TrxName());
		if(we.getTF_ProductionPlant_ID() > 0 ) {
			MProductionPlant pp = new MProductionPlant(getCtx(), we.getTF_ProductionPlant_ID(), get_TrxName());
			setM_Warehouse_ID(pp.getM_Warehouse_ID());
		}
		else {
			setM_Warehouse_ID(we.getM_Warehouse_ID());
		}
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		createCrusherProductionServiceReceipt();
		return super.afterSave(newRecord, success);
	}
	
	public void createCrusherProductionServiceReceipt() {		
		if(getQty_Receipt() == null || getQty_Receipt().doubleValue() == 0)
			return;
		
		TF_MProject proj = TF_MProject.getCrusherProductionSubcontractByWarehouse(getM_Warehouse_ID());
		if(proj == null)
			return;
		
		int invoiceItem_id = 0;
		int priceItem_id = 0;
		String priceItemName = null;
		

		invoiceItem_id = proj.getJobWork_Product_ID();
		
		priceItem_id = proj.getJobWork_Product_ID();
		priceItemName = proj.getJobWork_Product().getName();
		
		
		//Subcontract Purchase Price		
		BigDecimal purchasePrice = MJobworkProductPrice.getPrice(getCtx(), getC_Project_ID(), priceItem_id, getMovementDate()) ;
		if(purchasePrice == null) 
			throw new AdempiereException("Please setup Contract Price for " + priceItemName + "!");
		
		TF_MBPartner bp = new TF_MBPartner(getCtx(), proj.getC_BPartner_ID(), get_TrxName());
		
		int ServiceReceiptId = MSysConfig.getIntValue("SERVICE_RECEIPT_ID", 1000068, Env.getAD_Client_ID(Env.getCtx()));
		
		MDocType dt = new MDocType(getCtx(), ServiceReceiptId, get_TrxName());
		
		//Service Receipt Header		
		TF_MInOut inout = new TF_MInOut(getCtx(), 0, get_TrxName());
		inout.materialReceipt = false;
		inout.setTF_WeighmentEntry_ID(getTF_WeighmentEntry_ID());		
		inout.setIsSOTrx(false);
		inout.setC_DocType_ID(ServiceReceiptId);
		inout.setMovementType(MInOut.MOVEMENTTYPE_VendorReceipts);		
		inout.setDateAcct(getMovementDate());
		inout.setMovementDate(getMovementDate());		
		inout.setC_BPartner_ID(bp.getC_BPartner_ID());
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
		ioLine.setM_Product_ID(invoiceItem_id);
		ioLine.setM_Locator_ID(wh.getDefaultLocator().get_ID());
		
		BigDecimal qty = getQty_Receipt();		 
		
		ioLine.setQty(qty);
		ioLine.setC_UOM_ID(proj.getC_UOM_ID());
		ioLine.set_ValueOfColumn("Price", purchasePrice);	
		ioLine.set_ValueOfColumn("DocStatus", "CO");				
		ioLine.saveEx(get_TrxName());
		
		//Material Receipt DocAction
		if (!inout.processIt(DocAction.ACTION_Complete))
			throw new AdempiereException("Failed when processing document - " + inout.getProcessMsg());
		
		inout.saveEx();
		
	}
	
	public void reverseCrusherProductionServiceReceipt() {
		
	}
	
	public static void createRawmaterialMovementsFromWeighment(String trxName) {
		String whereClause = "C_Project_ID IS NOT NULL AND Status='CO' AND Processed='N' AND TareWeightTime IS NOT NULL AND GrossWeightTime IS NOT NULL";
		List<MWeighmentEntry> wEntries = new Query(Env.getCtx(), MWeighmentEntry.Table_Name, whereClause, trxName)
				.list();
		for(MWeighmentEntry entry : wEntries) {
			MSubcontractMaterialMovement mov = new MSubcontractMaterialMovement(Env.getCtx(), 0, trxName);
			mov.setAD_Org_ID(entry.getAD_Org_ID());
			mov.setC_Project_ID(entry.getC_Project_ID());
			mov.setC_BPartner_ID(entry.getC_BPartner_ID());
			mov.setM_Product_ID(entry.getM_Product_ID());
			mov.setTF_WeighmentEntry_ID(entry.getTF_WeighmentEntry_ID());
			mov.setMovementDate(entry.getGrossWeightTime());
			mov.setQty_Receipt(new BigDecimal(entry.getNetWeight().doubleValue()/1000));
			mov.setProcessed(true);
			mov.saveEx();
			entry.setStatus(MWeighmentEntry.STATUS_Billed);
			entry.setProcessed(true);
			entry.saveEx();			
		}
	}

	public static int createRawmaterialMovement(String trxName, Timestamp movementDate, int AD_Org_ID, int C_Project_ID, int C_BPartner_ID, 
			int M_Product_ID, int TF_WeighmentEntry_ID, int C_Order_ID,  BigDecimal QtyReceipt) {
		MSubcontractMaterialMovement mov = new MSubcontractMaterialMovement(Env.getCtx(), 0, trxName);
		mov.setAD_Org_ID(AD_Org_ID);
		mov.setC_Project_ID(C_Project_ID);
		mov.setC_BPartner_ID(C_BPartner_ID);
		mov.setM_Product_ID(M_Product_ID);
		mov.setTF_WeighmentEntry_ID(TF_WeighmentEntry_ID);
		mov.setC_Order_ID(C_Order_ID);
		mov.setMovementDate(movementDate);
		mov.setQty_Receipt(QtyReceipt);
		mov.setProcessed(true);
		mov.saveEx();
		return mov.getTF_RMSubcon_Movement_ID();
	}
	
	public static int createRawmaterialMovement(String trxName, Timestamp movementDate, int AD_Org_ID, int C_Project_ID, int C_BPartner_ID, 
			int M_Product_ID, int TF_WeighmentEntry_ID,  BigDecimal QtyReceipt) {
		MSubcontractMaterialMovement mov = new MSubcontractMaterialMovement(Env.getCtx(), 0, trxName);
		mov.setAD_Org_ID(AD_Org_ID);
		mov.setC_Project_ID(C_Project_ID);
		mov.setC_BPartner_ID(C_BPartner_ID);
		mov.setM_Product_ID(M_Product_ID);
		mov.setTF_WeighmentEntry_ID(TF_WeighmentEntry_ID);
		mov.setMovementDate(movementDate);
		mov.setQty_Receipt(QtyReceipt);
		mov.setProcessed(true);
		mov.saveEx();
		return mov.getTF_RMSubcon_Movement_ID();
	}
	
	public static void createMaterialMovement(String trxName, Timestamp dateAcct,int AD_Org_ID, int C_Project_ID,  int C_Invoice_ID, 
			int C_BPartner_ID, int M_Product_ID, BigDecimal QtyPayment, int TF_WeighmentEntry_ID) {
		MJobworkProductPrice pp = new Query(Env.getCtx(), MJobworkProductPrice.Table_Name, 
				"C_Project_ID = ? AND M_Product_ID = ? AND IsActive='Y'", null)
				.setParameters(C_Project_ID, M_Product_ID)
				.first();
		if(pp == null) //do not create material movement
			return;  //Items other than subcontracted production items
		
		MSubcontractMaterialMovement mov = new MSubcontractMaterialMovement(Env.getCtx(), 0, trxName);
		mov.setAD_Org_ID(AD_Org_ID);
		mov.setMovementDate(dateAcct);
		mov.setC_Project_ID(C_Project_ID);
		mov.setC_Invoice_ID(C_Invoice_ID);
		mov.setC_BPartner_ID(C_BPartner_ID);
		mov.setM_Product_ID(M_Product_ID);
		mov.setQty_Payment(QtyPayment);
		mov.setTF_WeighmentEntry_ID(TF_WeighmentEntry_ID);
		mov.setProcessed(true);
		mov.saveEx();
	}
	
	public static void createMaterialMovement(String trxName, Timestamp dateAcct,int AD_Org_ID, int C_Order_ID, 
			int C_BPartner_ID, int M_Product_ID, BigDecimal QtyPayment, int TF_WeighmentEntry_ID) {		
						
		MSubcontractMaterialMovement mov = new MSubcontractMaterialMovement(Env.getCtx(), 0, trxName);
		mov.setAD_Org_ID(AD_Org_ID);
		mov.setMovementDate(dateAcct);
		mov.setC_BPartner_ID(C_BPartner_ID);
		mov.setC_Order_ID(C_Order_ID);
		mov.setM_Product_ID(M_Product_ID);
		mov.setQty_Payment(QtyPayment);
		mov.setTF_WeighmentEntry_ID(TF_WeighmentEntry_ID);
		mov.setProcessed(true);
		mov.saveEx();
	}
	
	public static void deleteWeighmentMovement(int TF_WeighmentEntry_ID, String trxName) {
		String sql = "DELETE FROM TF_RMSubcon_Movement WHERE TF_WeighmentEntry_ID = " + TF_WeighmentEntry_ID;
		DB.executeUpdate(sql, trxName);
	}
	
	public static void deleteInvoiceMovement(int C_Invoice_ID, String trxName) {
		String sql = "DELETE FROM TF_RMSubcon_Movement WHERE C_Invoice_ID = " + C_Invoice_ID;
		DB.executeUpdate(sql, trxName);
	}
	
	public static void deleteSalesEntryMovement(int C_Order_ID, String trxName) {
		String sql = "DELETE FROM TF_RMSubcon_Movement WHERE C_Order_ID = " + C_Order_ID;
		DB.executeUpdate(sql, trxName);
	}
}
