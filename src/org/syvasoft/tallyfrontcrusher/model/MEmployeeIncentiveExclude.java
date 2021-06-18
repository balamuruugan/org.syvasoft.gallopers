package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MEmployeeIncentiveExclude extends X_TF_IncentiveConfig_Exld {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4159578354742223597L;

	public MEmployeeIncentiveExclude(Properties ctx, int TF_IncentiveConfig_Exld_ID, String trxName) {
		super(ctx, TF_IncentiveConfig_Exld_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MEmployeeIncentiveExclude(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
