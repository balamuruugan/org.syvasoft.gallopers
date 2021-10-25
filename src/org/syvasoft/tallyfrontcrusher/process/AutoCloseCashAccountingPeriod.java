package org.syvasoft.tallyfrontcrusher.process;

import java.sql.Timestamp;
import java.util.List;

import org.compiere.model.Query;
import org.compiere.process.SvrProcess;
import org.compiere.util.TimeUtil;
import org.syvasoft.tallyfrontcrusher.model.MCashAcctPeriod;
import org.syvasoft.tallyfrontcrusher.model.TF_MBankAccount;

public class AutoCloseCashAccountingPeriod extends SvrProcess {

	@Override
	protected void prepare() {
		

	}

	@Override
	protected String doIt() throws Exception {
		String whereClause = "C_BankAccount_ID NOT IN (SELECT C_BankAccount_ID FROM "
				+ "TF_AcctPeriod WHERE DateTo < now() - 1)";
		List<TF_MBankAccount> list = new Query(getCtx(), TF_MBankAccount.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.list();
		
		Timestamp today = new Timestamp(TimeUtil.getToday().getTimeInMillis());
		Timestamp date = TimeUtil.getPreviousDay(today);
		int i = 0;
		for(TF_MBankAccount b : list) {
			MCashAcctPeriod p = new MCashAcctPeriod(getCtx(), 0, get_TrxName());
			p.setAD_Org_ID(b.getAD_Org_ID());
			p.setC_BankAccount_ID(b.getC_BankAccount_ID());
			p.setDateFrom(date);
			p.setDateTo(date);
			p.saveEx();
			i++;
		}
		
		return i + " records are created successfully!";
	}

}
