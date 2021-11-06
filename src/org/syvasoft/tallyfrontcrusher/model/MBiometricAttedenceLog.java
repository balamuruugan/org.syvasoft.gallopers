package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;

public class MBiometricAttedenceLog extends X_TF_BiometricAttendence {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3389888168309404354L;
	public MBiometricAttedenceLog(Properties ctx, int TF_BiometricAttendence_ID, String trxName) {
		super(ctx, TF_BiometricAttendence_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MBiometricAttedenceLog(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public void setShift() {
		String sql = "SELECT getTF_EmpShift_ID(?, ? :: timestamp without time zone, ?)";
		int shift_id = DB.getSQLValue(get_TrxName(), sql, getAD_Org_ID(), getAttendenceTime(), getInOutMode());
		setTF_EmpShift_ID(shift_id);
		
		MEmployeeShift es = new MEmployeeShift(getCtx(), getTF_EmpShift_ID(), get_TrxName());
		setDateAcct(es.getDateAcct(getAttendenceTime(), getInOutMode()));
		setProcessed(false);
	}
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(newRecord) {
			setShift();
		}
		return super.beforeSave(newRecord);
	}
	
}
