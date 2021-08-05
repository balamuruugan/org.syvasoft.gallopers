package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MTransportOrderLine extends X_TF_TorderLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4475118726495122627L;

	public MTransportOrderLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MTransportOrderLine(Properties ctx, int TF_TorderLine_ID, String trxName) {
		super(ctx, TF_TorderLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}


}
