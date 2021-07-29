package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MLumpSumRentRentMargin extends X_TF_LumpSumRent_Customer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4143524382702201763L;

	public MLumpSumRentRentMargin(Properties ctx, int TF_LumpSumRent_Customer_ID, String trxName) {
		super(ctx, TF_LumpSumRent_Customer_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MLumpSumRentRentMargin(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
