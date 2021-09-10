package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MProduct;
import org.compiere.model.MSysConfig;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MRentedVehicle extends X_TF_RentedVehicle {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2641346720054190146L;

	public MRentedVehicle(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MRentedVehicle(Properties ctx, int TF_RentedVehicle_ID, String trxName) {
		super(ctx, TF_RentedVehicle_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		// TODO Auto-generated method stub

		if(!newRecord && isTransporter() && is_ValueChanged(COLUMNNAME_TareWeight)) {
			if(get_ValueOld(COLUMNNAME_TareWeight)!=null) {
				BigDecimal TareWeight=(BigDecimal)get_ValueOld(COLUMNNAME_TareWeight);
				setOldTareweight(TareWeight);
			}
		}
		
		if(newRecord && getC_BPartner_ID() > 0) {
			TF_MBPartner bp = new TF_MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
			setIsTransporter(bp.isVendor());	
			setIsOwnVehicle(false);
		}
		if(!newRecord && isTransporter()) {
			TF_MBPartner bp = new TF_MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
			if(!bp.isVendor())
				throw new AdempiereException("It is not a transporter vehicle!");
		}
		
		boolean ok = super.beforeSave(newRecord);
		return ok;
	}
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {		
		boolean ok = super.afterSave(newRecord, success);
		int M_Product_ID = getM_Product_ID();		
		MProduct prod = new MProduct(getCtx(), M_Product_ID, get_TrxName());
		TF_MOrg org = new TF_MOrg(getCtx(), getAD_Org_ID(), get_TrxName());
		prod.setAD_Org_ID(getAD_Org_ID());		
		prod.setValue(org.getValue() + "_" + getVehicleNo());
		prod.setName(getVehicleNo());
		prod.setC_UOM_ID(getC_UOM_ID());
		prod.setM_Product_Category_ID(getM_Product_Category_ID());
		prod.setProductType(MProduct.PRODUCTTYPE_Resource);
		prod.setIsPurchased(true);
		prod.setIsSold(true);
		prod.set_ValueOfColumn("IsRented", false);
		if(!isTransporter() && !isOwnVehicle())
			prod.setDescription("Customer Vehicle from " + getC_BPartner().getName());
		else if(isOwnVehicle()) 
			prod.setDescription("Own Vehicle/Machinery");			
		else if(isTransporter()) {
			prod.setDescription("Rented from " + getC_BPartner().getName());
			prod.set_ValueOfColumn("IsRented", true);
		}
		prod.setIsActive(isActive());
		
		prod.setC_TaxCategory_ID(1000000);
		//prod.setC_TaxCategory_ID(Env.getContextAsInt(getCtx(), "#C_TaxCategory_ID"));
		
		prod.saveEx();
		if(getM_Product_ID() == 0) {
			DB.executeUpdate("UPDATE TF_RentedVehicle SET M_Product_ID = " + prod.getM_Product_ID() +
					" WHERE TF_RentedVehicle_ID = " + getTF_RentedVehicle_ID(), get_TrxName());			
		}
		
		//Add all the destinations to the Rent Configuration with the default rate.
		if(newRecord && isRequireRentConfig()) {
			List<MDestination> destinations = new Query(getCtx(), MDestination.Table_Name, "AD_Org_ID IN (0,?)", get_TrxName())
					.setOnlyActiveRecords(true).setParameters(getAD_Org_ID()).setOrderBy("Name").list();
			for(MDestination dest : destinations) {
				MVehicleRentConfig rentConfig = new MVehicleRentConfig(getCtx(), 0, get_TrxName());
				rentConfig.setAD_Org_ID(getAD_Org_ID());
				rentConfig.setM_Product_ID(prod.getM_Product_ID());
				rentConfig.setTF_Destination_ID(dest.getTF_Destination_ID());
				rentConfig.setRate(dest.getRate());
				rentConfig.setIsActive(true);
				rentConfig.saveEx();
			}
			
		}
		
		String IsMachineMaint=MSysConfig.getValue("MACHINERY_MAINT", "Y");
		if(IsMachineMaint.equals("Y") && isOwnVehicle() && getPM_MachineryType_ID() > 0) {			
			int PM_Machinery_ID = MMachinery.getPM_Machinery_ID(getCtx(), prod.getM_Product_ID(), get_TrxName());
			MMachinery machine=new MMachinery(getCtx(), PM_Machinery_ID, get_TrxName());
			machine.setAD_Org_ID(getAD_Org_ID());
			machine.setMachineryNo(getVehicleNo());
			machine.setPM_MachineryType_ID(getPM_MachineryType_ID());
			machine.setTF_VehicleType_ID(getTF_VehicleType_ID());
			machine.setTF_RentedVehicle_ID(getTF_RentedVehicle_ID());
			machine.setM_Product_ID(prod.getM_Product_ID());
			machine.saveEx();
		}
		
		return ok;
	}

	@Override
	protected boolean afterDelete(boolean success) {		
		boolean ok = super.afterDelete(success);
		MProduct prod = MProduct.get(getCtx(), getM_Product_ID());
		prod.deleteEx(true, get_TrxName());		
		return ok;
	}
	
	
	public static MRentedVehicle get(Properties ctx, int M_Product_ID, String trxName) {
		String whereClause = "M_Product_ID = ?";
		MRentedVehicle rv = new Query(ctx, MRentedVehicle.Table_Name, whereClause, trxName)
				.setClient_ID()
				.setParameters(M_Product_ID)
				.first();
		return rv;
	}

}
