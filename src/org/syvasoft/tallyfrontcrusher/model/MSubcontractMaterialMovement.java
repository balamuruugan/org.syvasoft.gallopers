package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;
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
