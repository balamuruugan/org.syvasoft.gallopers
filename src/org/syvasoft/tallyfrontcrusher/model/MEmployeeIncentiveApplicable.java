package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MEmployeeIncentiveApplicable extends X_TF_IncentiveConfig_Applicable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4159578354742223597L;

	public MEmployeeIncentiveApplicable(Properties ctx, int TF_IncentiveConfig_Exld_ID, String trxName) {
		super(ctx, TF_IncentiveConfig_Exld_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MEmployeeIncentiveApplicable(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
