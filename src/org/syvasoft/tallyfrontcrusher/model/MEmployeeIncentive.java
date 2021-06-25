package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.Query;

public class MEmployeeIncentive extends X_TF_IncentiveConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1254611722020548446L;

	public MEmployeeIncentive(Properties ctx, int TF_IncentiveConfig_ID, String trxName) {
		super(ctx, TF_IncentiveConfig_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MEmployeeIncentive(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	
	public static MEmployeeIncentive get(Properties ctx, int AD_Org_ID, int C_BPartner_ID, int C_UOM_ID) {
		String meterType = null;
		int MT_UOM_ID = 1000069;
		int Hour_UOM_ID = 101;
		if(C_UOM_ID == Hour_UOM_ID)
			meterType = MEmployeeIncentive.INCENTIVETYPE_Hour;
		else if(C_UOM_ID == MT_UOM_ID) 
			meterType = MEmployeeIncentive.INCENTIVETYPE_MT;
		else
			meterType = null;
		
		String whereClause = "AD_Org_ID IN (0,?) AND COALESCE(C_BPartner_ID,0) IN (0,?) AND IncentiveType=?";
		MEmployeeIncentive inc = new Query(ctx, Table_Name, whereClause, null)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID, C_BPartner_ID, meterType)
				.setOrderBy("COALESCE(C_BPartner_ID,0) DESC")
				.first();
		
		return inc;
	}
	
	public static MEmployeeIncentive get(Properties ctx, int AD_Org_ID, int C_BPartner_ID) {
		if(C_BPartner_ID == 0)
			return null;
		
		String whereClause = "AD_Org_ID IN (0,?) AND C_BPartner_ID = ?";
		MEmployeeIncentive inc = new Query(ctx, Table_Name, whereClause, null)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID, C_BPartner_ID)
				.setOrderBy("COALESCE(C_BPartner_ID,0) DESC")
				.first();
		
		return inc;
	}
	
}
