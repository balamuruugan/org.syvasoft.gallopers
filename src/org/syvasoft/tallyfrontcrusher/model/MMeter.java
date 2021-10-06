package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.Query;
import org.compiere.util.DB;

public class MMeter extends X_PM_Meter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5430807137929545510L;

	public MMeter(Properties ctx, int PM_Meter_ID, String trxName) {
		super(ctx, PM_Meter_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MMeter(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public static void updateCurrentMeter(Properties ctx, int PM_Machinery_ID, int meterType_ID, String trxName) {
				
		String sql = "SELECT MAX(Closing_Meter) FROM PM_Meter_Log WHERE PM_Machinery_ID = ? AND C_UOM_ID = ?";
		
		MMachinery m = new MMachinery(ctx, PM_Machinery_ID, trxName);
		if(m.isIoTLinked()) {
			sql = "SELECT MAX(Running_Meter) FROM PLC_Reading_Log WHERE PM_Machinery_ID = ? AND C_UOM_ID = ? ";
		}
		
		BigDecimal currentMeter = DB.getSQLValueBDEx(trxName, sql, PM_Machinery_ID, meterType_ID);
		if(currentMeter == null)
			currentMeter = BigDecimal.ZERO;
		
		
		String whereClause = "PM_Machinery_ID = ? AND C_UOM_ID = ?";
		MMeter mtr = new Query(ctx, Table_Name, whereClause, trxName)
				.setClient_ID()
				.setParameters(PM_Machinery_ID, meterType_ID)
				.first();
		if(mtr == null) {
			mtr = new MMeter(ctx, 0, trxName);
			mtr.setPM_Machinery_ID(PM_Machinery_ID);			
			mtr.setAD_Org_ID(mtr.getPM_Machinery().getAD_Org_ID());
			mtr.setC_UOM_ID(meterType_ID);
		}
		
		mtr.setCurrentMeter(currentMeter);
		mtr.saveEx();
	}

}
