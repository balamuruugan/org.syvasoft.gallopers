package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.rmi.NoSuchObjectException;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MJournal;
import org.compiere.model.MJournalLine;
import org.compiere.model.MPeriod;
import org.compiere.model.Query;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.Env;

public class MEmployeeSalaryDet extends X_TF_EmployeeSalary_Det {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1657054354220279252L;

	public MEmployeeSalaryDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MEmployeeSalaryDet(Properties ctx, int TF_Employee_Salary_ID,
			String trxName) {
		super(ctx, TF_Employee_Salary_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		//
		if(newRecord){
			String whereClause = "TF_EmployeeSalary_ID = ? AND C_BPartner_ID = ?";
			List<MEmployeeSalaryDet> empSalaryDet =  new Query(getCtx(),MEmployeeSalaryDet.Table_Name, whereClause, get_TrxName()).setClient_ID()
					.setParameters(getTF_EmployeeSalary_ID(), getC_BPartner_ID()).list();
			
			if(empSalaryDet.size() > 0) {
				throw new AdempiereUserError("Employee already exists for the selected month!");
			}
		}
		else {
			String whereClause = "TF_EmployeeSalary_ID = ? AND C_BPartner_ID = ? AND TF_EmployeeSalary_Det_ID <> ?";
			List<MEmployeeSalaryDet> empSalaryDet =  new Query(getCtx(),MEmployeeSalaryDet.Table_Name, whereClause, get_TrxName()).setClient_ID()
					.setParameters(getTF_EmployeeSalary_ID(), getC_BPartner_ID(), getTF_EmployeeSalary_Det_ID()).list();
			
			if(empSalaryDet.size() > 0) {
				throw new AdempiereUserError("Employee already exists for the selected month!");
			}
		}
		return super.beforeSave(newRecord);
	}
	
	public void processIt(String DocAction) {
		
	}
	public void reverseIt() {
		
	}
	
	public static void createEmployeeSalaryDetail(Properties ctx, String trxName, MEmployeeSalary EmployeeSalary, TF_MBPartner BPartner,int SNo) {		
		
		String wherecondition = "TF_EmployeeSalary.TF_EmployeeSalary_id IN (SELECT d.TF_EmployeeSalary_id FROM TF_EmployeeSalary_Det d " + 
				"WHERE d.TF_EmployeeSalary_id = TF_EmployeeSalary.TF_EmployeeSalary_id AND d.C_BPartner_ID = ?) AND DateAcct < ?";
		
		MEmployeeSalary prevEmployeeSalary =  new Query(ctx,MEmployeeSalary.Table_Name, wherecondition, trxName).setClient_ID()
				.setParameters(BPartner.getC_BPartner_ID(), EmployeeSalary.getDateAcct()).setOrderBy(MEmployeeSalary.COLUMNNAME_DateAcct + " desc").first();
		
		wherecondition = "TF_EmployeeSalary_id = ? AND C_BPartner_ID = ?";
		MEmployeeSalaryDet prevEmployeeSalaryDet = null;
		
		if(prevEmployeeSalary != null)
			prevEmployeeSalaryDet = new Query(ctx,MEmployeeSalaryDet.Table_Name, wherecondition, trxName).setClient_ID()
				.setParameters(prevEmployeeSalary.getTF_EmployeeSalary_ID(), BPartner.getC_BPartner_ID()).first();
		
		wherecondition = "TF_EmployeeSalary_Det.TF_EmployeeSalary_id IN (SELECT h.TF_EmployeeSalary_id FROM TF_EmployeeSalary h " + 
				"WHERE h.TF_EmployeeSalary_id = TF_EmployeeSalary_Det.TF_EmployeeSalary_id AND h.DateAcct < ?) and C_BPartner_ID = ?";
		
		Query query = new Query(ctx,MEmployeeSalaryDet.Table_Name, wherecondition, trxName).setClient_ID()
				.setParameters(EmployeeSalary.getDateAcct(), BPartner.getC_BPartner_ID());
		
		BigDecimal deductedAmt = (BigDecimal) query.aggregate(COLUMNNAME_DeductAdvance, Query.AGGREGATE_SUM);
		
		wherecondition = "C_BPartner_ID = ? AND DateAcct < ? AND DocStatus IN ('CO','CL')";
		
		Query querySalAdvance = new Query(ctx, MEmployeeSalaryAdvance.Table_Name, wherecondition, trxName).setClient_ID()
				.setParameters(BPartner.getC_BPartner_ID(), EmployeeSalary.getDateAcct());
		
		BigDecimal salaryAdvance = (BigDecimal) querySalAdvance.aggregate(MEmployeeSalaryAdvance.COLUMNNAME_Advance_Amt, Query.AGGREGATE_SUM);
		
		BigDecimal salaryAdvRemaining = BigDecimal.ZERO;
		
		if(salaryAdvance.intValue() > 0)
			salaryAdvRemaining = salaryAdvance.subtract(deductedAmt);
		
		/*String whereClause = "C_Period_ID = ?";
		
		
		MPeriod period = new Query(ctx, MPeriod.Table_Name, whereClause, trxName).setClient_ID()
				.setParameters(EmployeeSalary.getC_Period_ID()).first();
		
		whereClause = "C_Year_ID = ? AND PeriodNo < ?";
		
		MPeriod prevperiod = new Query(ctx,MPeriod.Table_Name, whereClause, trxName).setClient_ID()
				.setParameters(period.getC_Year_ID(),period.getPeriodNo()).setOrderBy(MPeriod.COLUMNNAME_PeriodNo + " desc").first();		
		
		whereClause = "C_Period_ID = ? AND EmployeeType = ?";
		MEmployeeSalary prevEmployeeSalary =  new Query(ctx,MEmployeeSalary.Table_Name, whereClause, trxName).setClient_ID()
				.setParameters(prevperiod.getC_Period_ID(), EmployeeSalary.getEmployeeType()).first();
		*/
		
		//String whereClause = "TF_EmployeeSalary_ID = ? AND C_BPartner_ID = ?";
		
		//MEmployeeSalaryDet prevEmployeeSalaryDet = null;
		
		if(prevEmployeeSalaryDet != null)
			prevEmployeeSalaryDet =  query.first();
		
		
		MEmployeeSalaryDet employeesalaryDet = new MEmployeeSalaryDet(ctx, 0, trxName);
		
		employeesalaryDet.setClientOrg(EmployeeSalary.getAD_Client_ID(), EmployeeSalary.getAD_Org_ID());
		employeesalaryDet.setSNo(SNo);
		employeesalaryDet.setTF_EmployeeSalary_ID(EmployeeSalary.getTF_EmployeeSalary_ID());
		employeesalaryDet.setC_BPartner_ID(BPartner.getC_BPartner_ID());
		employeesalaryDet.setDesignation(BPartner.getDesignation());
		employeesalaryDet.setSalary(BPartner.getMonthlySalary());
		employeesalaryDet.setNoOfDays(EmployeeSalary.getStd_Days());
		employeesalaryDet.setSalaryDue(BPartner.getMonthlySalary());
		employeesalaryDet.setAdvancePaid(salaryAdvRemaining);
		
		if(prevEmployeeSalaryDet != null) {
			employeesalaryDet.setUnpaidSalary(prevEmployeeSalaryDet.getSalaryWithheld());
			employeesalaryDet.setNetSalary(BPartner.getMonthlySalary().add(prevEmployeeSalaryDet.getSalaryWithheld()).setScale(0,RoundingMode.HALF_UP));
		}
		else {
			employeesalaryDet.setUnpaidSalary(BigDecimal.ZERO);
			employeesalaryDet.setNetSalary(BPartner.getMonthlySalary().setScale(0,RoundingMode.HALF_UP));
		}
		
		employeesalaryDet.saveEx();
	}
	
	public static void calculateSalary(Properties ctx, String trxName, MEmployeeSalary EmployeeSalary, MEmployeeSalaryDet EmployeeSalaryDet) {
		BigDecimal stdDays = EmployeeSalary.getStd_Days();
		BigDecimal salary = EmployeeSalaryDet.getSalary();
		BigDecimal presentDays = EmployeeSalaryDet.getNoOfDays();
		BigDecimal deductAdvance = EmployeeSalaryDet.getDeductAdvance();
		BigDecimal messAdvance = EmployeeSalaryDet.getMessAdvance();		
		BigDecimal salaryWithheld = EmployeeSalaryDet.getSalaryWithheld();
		BigDecimal unpaidSalary = EmployeeSalaryDet.getUnpaidSalary(); 
		
		
		salary = (salary == null) ? BigDecimal.ZERO : salary;
		presentDays = (presentDays == null) ? BigDecimal.ZERO : presentDays;
		deductAdvance = (deductAdvance == null) ? BigDecimal.ZERO : deductAdvance;
		messAdvance = (messAdvance == null) ? BigDecimal.ZERO : messAdvance;
		salaryWithheld = (salaryWithheld == null) ? BigDecimal.ZERO : salaryWithheld;
		unpaidSalary = (unpaidSalary == null) ? BigDecimal.ZERO : unpaidSalary;
		
		BigDecimal salaryDue = BigDecimal.ZERO;
		BigDecimal netSalary = BigDecimal.ZERO;
		
		if(stdDays == BigDecimal.ZERO)
			salaryDue = BigDecimal.ZERO;
		else
			salaryDue = salary.multiply(presentDays).divide(stdDays, 2, RoundingMode.HALF_EVEN).setScale(2, RoundingMode.HALF_EVEN);		
		EmployeeSalaryDet.setSalaryDue(salaryDue);
		
		netSalary = salaryDue.add(unpaidSalary).subtract(deductAdvance).subtract(messAdvance).subtract(salaryWithheld).setScale(0,RoundingMode.HALF_UP);
		EmployeeSalaryDet.setNetSalary(netSalary);
		EmployeeSalaryDet.saveEx();
	}
}
