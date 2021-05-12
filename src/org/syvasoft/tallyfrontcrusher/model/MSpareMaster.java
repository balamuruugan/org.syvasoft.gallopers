package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;

public class MSpareMaster extends X_PM_Spare {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6541347037906356906L;

	public MSpareMaster(Properties ctx, int PM_Spare_ID, String trxName) {
		super(ctx, PM_Spare_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MSpareMaster(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		setLifeUsed(getLastMeter().subtract(getIssuedAt()));
		setRemainingLife(getSpareStdLife().subtract(getLifeUsed()));
		return super.beforeSave(newRecord);
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		return super.afterSave(newRecord, success);
	}
	
	public static void createSpareMaster(Properties ctx, String trxName, int TF_Fuel_Issue_ID, int machinery_ID, int sparelife_UOM_ID, int product_ID, Timestamp dateacct, BigDecimal qty) {
		
		String whereClause = "PM_Machinery_ID = ? AND SpareLife_UOM_ID = ? AND M_Product_ID = ? AND SpareStatus = ?";
		List<MSpareMaster> spareList = new Query(ctx, MSpareMaster.Table_Name, whereClause, trxName)
				.setClient_ID()
				.setParameters(machinery_ID, sparelife_UOM_ID, product_ID, MSpareMaster.SPARESTATUS_InUse).setOrderBy(COLUMNNAME_PM_Spare_ID).list();

		MMachinery mMachinery = new MMachinery(ctx, machinery_ID, trxName);
		
		whereClause = "PM_MachineryType_ID = ? AND M_Product_ID = ?";
		
		MSpareSetup spareSetup = new Query(ctx, MSpareSetup.Table_Name, whereClause, trxName)
				.setClient_ID()
				.setParameters(mMachinery.getPM_MachineryType_ID(), product_ID).first();
		
		if(spareSetup != null) {
			int requiredQty = spareSetup.getQtyRequired().intValue(); 
			int issuedQty = spareList.size();
			
			for (BigDecimal i = BigDecimal.ZERO; i.compareTo(qty) < 0; i = i.add(BigDecimal.ONE)) {
				MSpareMaster oldSpare = null;
				if(issuedQty + i.intValue() - requiredQty >= 0) {
					replaceSpare(ctx, trxName, spareList.get(issuedQty + i.intValue() - requiredQty), TF_Fuel_Issue_ID);
				}
				MSpareMaster spareMaster = createSpareMaster(ctx, trxName, TF_Fuel_Issue_ID, machinery_ID, product_ID, dateacct);
				spareMaster.saveEx();
			}
		}
		else {
			throw new AdempiereException("Invalid Spare issue for the selected Machinery!");
		}
	}
	
	public static void replaceSpare(Properties ctx, String trxName, MSpareMaster oldSpare, int TF_Fuel_Issue_ID) {
		oldSpare.setSpareStatus(SPARESTATUS_Replaced);
		oldSpare.setTF_FuelIssueReplace_ID(TF_Fuel_Issue_ID);
		
		String whereClause = "PM_Machinery_ID = ? AND C_UOM_ID = ?";
		
		MMeter mMeter = new Query(ctx, MMeter.Table_Name, whereClause, trxName)
				.setClient_ID()
				.setParameters(oldSpare.getPM_Machinery_ID(), oldSpare.getSpareLife_UOM_ID())
				.first();

		if(mMeter != null) {
			oldSpare.setLastMeter(mMeter.getCurrentMeter());
		}
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		oldSpare.setReplacedDate(timestamp);
		oldSpare.saveEx();
	}
	
	public static MSpareMaster createSpareMaster(Properties ctx, String trxName, int TF_Fuel_Issue_ID, int machinery_ID, int product_ID, Timestamp dateacct) {
			MSpareMaster spareMaster = new MSpareMaster(ctx, 0, trxName);
			
			MMachinery mMachinery = new MMachinery(ctx, machinery_ID, trxName);
			TF_MProduct product = new TF_MProduct(ctx,product_ID,trxName);
			
			spareMaster.setClientOrg(mMachinery.getAD_Client_ID(), mMachinery.getAD_Org_ID());
			spareMaster.setPM_Machinery_ID(machinery_ID);
			spareMaster.setM_Product_ID(product_ID);
			spareMaster.setMovementDate(dateacct);
			if(product != null) {
				spareMaster.setPM_SpareGroup_ID(product.getPM_SpareGroup_ID());
				spareMaster.setSpareLife_UOM_ID(product.getSpareLife_UOM_ID());
				spareMaster.setSpareStdLife(product.getSpareStdLife());
				spareMaster.setSpareLIfeGreenLimit(product.getSpareLIfeGreenLimit());
				spareMaster.setSpareLIfeYellowLimit(product.getSpareLIfeYellowLimit());
			}
			
			String whereClause = "PM_Machinery_ID = ? AND C_UOM_ID = ?";
			
			MMeter mMeter = new Query(ctx, MMeter.Table_Name, whereClause, trxName)
					.setClient_ID()
					.setParameters(machinery_ID, product.getSpareLife_UOM_ID())
					.first();

			if(mMeter != null) {
				spareMaster.setIssuedAt(mMeter.getCurrentMeter());
			}
			else {
				spareMaster.setIssuedAt(BigDecimal.ZERO);
			}
			
			spareMaster.setTF_Fuel_Issue_ID(TF_Fuel_Issue_ID);
			spareMaster.saveEx();
			
			return spareMaster;
	}
	
	public static void reverseSpareEntries(Properties ctx, String trxName, int TF_Fuel_Issue_ID) {
		String whereClause = "TF_Fuel_Issue_ID = ? AND SpareStatus = ?";
		
		List<MSpareMaster> currentspareList = new Query(ctx, MSpareMaster.Table_Name, whereClause, trxName)
				.setClient_ID()
				.setParameters(TF_Fuel_Issue_ID,MSpareMaster.SPARESTATUS_InUse).list();
		
		for(MSpareMaster spare : currentspareList) {
			spare.setSpareStatus(SPARESTATUS_Voided);
			spare.saveEx();
		}
		
		whereClause = "TF_FuelIssueReplace_ID = ? AND SpareStatus = ?";
		
		List<MSpareMaster> lastspareList = new Query(ctx, MSpareMaster.Table_Name, whereClause, trxName)
				.setClient_ID()
				.setParameters(TF_Fuel_Issue_ID,MSpareMaster.SPARESTATUS_Replaced).list();
		
		for(MSpareMaster spare : lastspareList) {
			spare.setSpareStatus(SPARESTATUS_InUse);
			spare.setTF_FuelIssueReplace_ID(0);
			spare.setReplacedDate(null);
			spare.saveEx();
		}
	}
	
	public static void updateSpareMeter(Properties ctx, String trxName, int machinery_ID, int sparelife_UOM_ID, BigDecimal lastMeter) {		
		String whereClause = "PM_Machinery_ID = ? AND SpareLife_UOM_ID = ? AND SpareStatus = ?";
		
		List<MSpareMaster> currentspareList = new Query(ctx, MSpareMaster.Table_Name, whereClause, trxName)
				.setClient_ID()
				.setParameters(machinery_ID, sparelife_UOM_ID, MSpareMaster.SPARESTATUS_InUse).list();
		
		for(MSpareMaster spare : currentspareList) {
			spare.setLastMeter(lastMeter);
			spare.saveEx();
		}
	}
}
