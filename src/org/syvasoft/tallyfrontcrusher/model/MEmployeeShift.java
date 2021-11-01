package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MEmployeeShift extends X_TF_EmpShift{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6237619174328064099L;

	public MEmployeeShift(Properties ctx, int TF_EmpShift_ID, String trxName) {
		super(ctx, TF_EmpShift_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MEmployeeShift(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	

}
