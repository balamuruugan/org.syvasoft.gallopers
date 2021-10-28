package org.syvasoft.tallyfrontcrusher.process;

import java.sql.Timestamp;
import java.util.List;

import org.compiere.model.Query;
import org.compiere.process.SvrProcess;
import org.compiere.util.TimeUtil;
import org.syvasoft.tallyfrontcrusher.model.MBPAcctPeriod;
import org.syvasoft.tallyfrontcrusher.model.MCashAcctPeriod;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;
import org.syvasoft.tallyfrontcrusher.model.TF_MBankAccount;

public class AutoCloseCashAccountingPeriod extends SvrProcess {

	@Override
	protected void prepare() {
		

	}

	@Override
	protected String doIt() throws Exception {
		Timestamp today = new Timestamp(TimeUtil.getToday().getTimeInMillis());
		Timestamp date = TimeUtil.getPreviousDay(today);
		
		String whereClause = "C_BankAccount_ID NOT IN (SELECT C_BankAccount_ID FROM "
				+ "TF_AcctPeriod WHERE DateTo = Trunc(?::timestamp))";
		List<TF_MBankAccount> list = new Query(getCtx(), TF_MBankAccount.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(date)
				.setOnlyActiveRecords(true)
				.list();
		
		
		int i = 0;
		for(TF_MBankAccount b : list) {
			whereClause = "AD_Org_ID = ? AND C_BankAccount_ID = ?";
			
			MCashAcctPeriod p = new Query(getCtx(), MCashAcctPeriod.Table_Name, whereClause, get_TrxName())
					.setClient_ID()
					.setParameters(b.getAD_Org_ID(), b.getC_BankAccount_ID())
					.setOrderBy("DateTo DESC")
					.first();			
			
			if(p == null) {
				p = new MCashAcctPeriod(getCtx(), 0, get_TrxName());
				p.setAD_Org_ID(b.getAD_Org_ID());
				p.setC_BankAccount_ID(b.getC_BankAccount_ID());
				p.setDateFrom(date);				
			}
			
			p.setDateTo(date);
			p.saveEx();
			i++;
		}
		
		whereClause = "(IsCustomer='Y' OR IsVendor='Y') AND C_BPartner_ID NOT IN (SELECT C_BPartner_ID FROM "
				+ "TF_BPAcctPeriod WHERE DateTo = Trunc(?::timestamp))";
		List<TF_MBPartner> bpList = new Query(getCtx(), TF_MBPartner.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(date)
				.list();
		
		for(TF_MBPartner bp : bpList) {
			if(bp.getAD_Org_ID() > 0)
				createBPLockPeriod(bp.getAD_Org_ID(), bp.getC_BPartner_ID(), date);
			else {
				createBPLockPeriod(1000003, bp.getC_BPartner_ID(), date);
				createBPLockPeriod(1000000, bp.getC_BPartner_ID(), date);
			}
			i++;
		}
		
		return i + " records are created successfully!";
	}
	
	private void createBPLockPeriod(int AD_Org_ID, int C_BPartner_ID, Timestamp date) {
		String whereClause = "AD_Org_ID = ? AND C_BPartner_ID = ? ";
		MBPAcctPeriod p = new Query(getCtx(), MBPAcctPeriod.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(AD_Org_ID, C_BPartner_ID)
				.setOrderBy("DateTo DESC")
				.first();
		if(p == null) {
			p = new MBPAcctPeriod(getCtx(), 0, get_TrxName());
			p.setAD_Org_ID(AD_Org_ID);
			p.setC_BPartner_ID(C_BPartner_ID);
			p.setDateFrom(date);
			p.setDateTo(date);	
		}
		else
			p.setDateTo(date);
		
		p.saveEx();
	}

}
