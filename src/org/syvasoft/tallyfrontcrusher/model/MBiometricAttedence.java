package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MBiometricAttedence extends X_TF_BiometricAttendence {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3389888168309404354L;
	public MBiometricAttedence(Properties ctx, int TF_BiometricAttendence_ID, String trxName) {
		super(ctx, TF_BiometricAttendence_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MBiometricAttedence(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}



}
