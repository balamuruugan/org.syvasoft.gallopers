package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MInstantPettyCashLine extends X_TF_InstantPettyCashLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6713395736222335731L;

	public MInstantPettyCashLine(Properties ctx, int TF_InstantPettyCashLine_ID, String trxName) {
		super(ctx, TF_InstantPettyCashLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MInstantPettyCashLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
