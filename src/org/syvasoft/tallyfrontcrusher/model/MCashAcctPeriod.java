package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.MSysConfig;
import org.compiere.model.Query;
import org.compiere.util.Env;

public class MCashAcctPeriod extends X_TF_AcctPeriod {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7085146109675813387L;

	public MCashAcctPeriod(Properties ctx, int TF_AcctPeriod_ID, String trxName) {
		super(ctx, TF_AcctPeriod_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCashAcctPeriod(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static boolean isOpen(Properties ctx,int AD_Org_ID, int C_BankAccount_ID, Timestamp dateAcct) {
		boolean acctPeriodLock = MSysConfig.getBooleanValue("ACCOUNTING_PERIOD_LOCK", true, Env.getAD_Client_ID(ctx), AD_Org_ID);
		
		if(!acctPeriodLock)
			return true;
		
		String whereClause = "AD_Org_ID = ? AND C_BankAccount_ID = ? AND  "
				+ " DateTo >= TRUNC(?::Timestamp) AND IsActive='Y'";
		MCashAcctPeriod period = new Query(ctx, Table_Name, whereClause, null)
				.setClient_ID()
				.setParameters(AD_Org_ID, C_BankAccount_ID, dateAcct)
				.first();
		
		if(period != null)
			return false;
		
		return true;
	}
}
