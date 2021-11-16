package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MInstantPettyCash extends X_TF_InstantPettyCash {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5832491475146926547L;

	public MInstantPettyCash(Properties ctx, int TF_InstantPettyCash_ID, String trxName) {
		super(ctx, TF_InstantPettyCash_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MInstantPettyCash(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
