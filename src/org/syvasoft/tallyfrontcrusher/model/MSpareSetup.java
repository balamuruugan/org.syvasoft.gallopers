package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MSpareSetup extends X_PM_SpareSetup {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8285872841462603405L;

	public MSpareSetup(Properties ctx, int PM_SpareGroup_ID, String trxName) {
		super(ctx, PM_SpareGroup_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MSpareSetup(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
