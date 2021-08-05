package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;

public class MPrintDocSetup extends X_TF_PrintDocSetup {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8086375269293054622L;

	public MPrintDocSetup(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MPrintDocSetup(Properties ctx, int TF_Destination_ID, String trxName) {
		super(ctx, TF_Destination_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
}
