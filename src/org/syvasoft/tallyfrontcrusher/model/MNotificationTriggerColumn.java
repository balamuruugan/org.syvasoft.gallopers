package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MNotificationTriggerColumn extends X_TF_SMSTriggerColumn {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2367121718257874928L;

	public MNotificationTriggerColumn(Properties ctx, int TF_SMSTriggerColumn_ID, String trxName) {
		super(ctx, TF_SMSTriggerColumn_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MNotificationTriggerColumn(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
