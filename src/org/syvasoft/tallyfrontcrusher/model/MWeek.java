package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.Query;

public class MWeek extends X_C_Week {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7085146109675813387L;

	public MWeek(Properties ctx, int C_Week_ID, String trxName) {
		super(ctx, C_Week_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MWeek(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
}
