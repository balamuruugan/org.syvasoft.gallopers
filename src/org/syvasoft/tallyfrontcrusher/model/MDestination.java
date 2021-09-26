package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;

public class MDestination extends X_TF_Destination {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8086375269293054622L;

	public MDestination(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDestination(Properties ctx, int TF_Destination_ID, String trxName) {
		super(ctx, TF_Destination_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	protected boolean beforeSave(boolean newRecord) {
		if(newRecord) {
			String where = " TRIM(UPPER(Name)) = '" + getName().toUpperCase().trim() + "' AND AD_Org_ID = " + getAD_Org_ID();
			
			MDestination dest = new Query(getCtx(), MDestination.Table_Name, where, get_TrxName()).first();
			
			if(dest != null) {
				throw new AdempiereUserError("Destination already exists");
			}
		}
		else {
			String where = " TRIM(UPPER(Name)) = '" + getName().toUpperCase().trim() + "' AND AD_Org_ID = " + getAD_Org_ID() + " AND TF_Destination_ID != " + getTF_Destination_ID();
			
			MDestination dest = new Query(getCtx(), MDestination.Table_Name, where, get_TrxName()).first();
			
			if(dest != null) {
				throw new AdempiereUserError("Destination already exists");
			}
		}
		return super.beforeSave(newRecord);
	}
	
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if(newRecord) {
			String whereClause = " AD_Org_ID  IN (0,?) AND RequireRentConfig = 'Y' ";
			List<MRentedVehicle> vehicles = new Query(getCtx(), MRentedVehicle.Table_Name, whereClause, get_TrxName())
					.setOnlyActiveRecords(true).setParameters(getAD_Org_ID()).list();
			for(MRentedVehicle vehicle : vehicles) {
				MVehicleRentConfig rentConfig = new MVehicleRentConfig(getCtx(), 0, get_TrxName());
				rentConfig.setAD_Org_ID(getAD_Org_ID());
				rentConfig.setM_Product_ID(vehicle.getM_Product_ID());
				rentConfig.setTF_Destination_ID(getTF_Destination_ID());
				rentConfig.setRate(getRate());
				rentConfig.setIsActive(true);
				rentConfig.saveEx();
			}
		}
		return super.afterSave(newRecord, success);
	}

	
}
