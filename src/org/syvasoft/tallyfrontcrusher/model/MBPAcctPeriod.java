package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.MSysConfig;
import org.compiere.model.Query;
import org.compiere.util.Env;

public class MBPAcctPeriod extends X_TF_BPAcctPeriod {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6974934404477091829L;

	public MBPAcctPeriod(Properties ctx, int TF_BPAcctPeriod_ID, String trxName) {
		super(ctx, TF_BPAcctPeriod_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MBPAcctPeriod(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static boolean isOpen(Properties ctx,int AD_Org_ID, int C_BPartner_ID, Timestamp dateAcct) {
		
		boolean acctPeriodLock = MSysConfig.getBooleanValue("ACCOUNTING_PERIOD_LOCK", true, Env.getAD_Client_ID(ctx), AD_Org_ID);
		
		if(!acctPeriodLock)
			return true;
		
		String whereClause = "AD_Org_ID = ? AND C_BPartner_ID = ? AND  "
				+ " DateTo >= TRUNC(?::Timestamp) AND IsActive='Y'";
		MBPAcctPeriod period = new Query(ctx, Table_Name, whereClause, null)
				.setClient_ID()
				.setParameters(AD_Org_ID, C_BPartner_ID, dateAcct)
				.first();
		
		if(period != null)
			return false;
		
		return true;
	}
}
