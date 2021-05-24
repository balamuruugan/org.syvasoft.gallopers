package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MBlastingEntry extends X_TF_BlastingEntry {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1077189153705216866L;

	public MBlastingEntry(Properties ctx, int TF_BlastingEntry_ID, String trxName) {
		super(ctx, TF_BlastingEntry_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MBlastingEntry(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}


	
}
