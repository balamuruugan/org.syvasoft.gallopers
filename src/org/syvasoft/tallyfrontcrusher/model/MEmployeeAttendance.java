package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MEmployeeAttendance extends X_TF_EmployeeAttendance {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5912416642834450467L;

	public MEmployeeAttendance(Properties ctx, int TF_EmployeeAttendance_ID, String trxName) {
		super(ctx, TF_EmployeeAttendance_ID, trxName);
		
	}

	public MEmployeeAttendance(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
