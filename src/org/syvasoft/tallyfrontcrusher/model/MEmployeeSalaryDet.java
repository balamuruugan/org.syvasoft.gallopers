package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.rmi.NoSuchObjectException;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MJournal;
import org.compiere.model.MJournalLine;
import org.compiere.model.MPeriod;
import org.compiere.model.Query;
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
		
		return super.beforeSave(newRecord);
	}
	
	public void processIt(String DocAction) {
		
	}
	public void reverseIt() {
		
	}
	
	public static void createEmployeeSalaryDetail(Properties ctx, String trxName, MEmployeeSalary EmployeeSalary, TF_MBPartner BPartner,int SNo) {
		String whereClause = "C_Period_ID = ?";
		
		MPeriod period = new Query(ctx, MPeriod.Table_Name, whereClause, trxName).setClient_ID()
				.setParameters(EmployeeSalary.getC_Period_ID()).first();
		
		whereClause = "C_Year_ID = ? AND PeriodNo < ?";
		
		MPeriod prevperiod = new Query(ctx,MPeriod.Table_Name, whereClause, trxName).setClient_ID()
				.setParameters(period.getC_Year_ID(),period.getPeriodNo()).setOrderBy(MPeriod.COLUMNNAME_PeriodNo + " desc").first();		
		
		whereClause = "C_Period_ID = ? AND EmployeeType = ?";
		MEmployeeSalary prevEmployeeSalary =  new Query(ctx,MEmployeeSalary.Table_Name, whereClause, trxName).setClient_ID()
				.setParameters(prevperiod.getC_Period_ID(), EmployeeSalary.getEmployeeType()).first();
		
		whereClause = "TF_EmployeeSalary_ID = ? AND C_BPartner_ID = ?";
		
		MEmployeeSalaryDet prevEmployeeSalaryDet = null;
		
		if(prevEmployeeSalary != null)
			prevEmployeeSalaryDet =  new Query(ctx,MEmployeeSalaryDet.Table_Name, whereClause, trxName).setClient_ID()
				.setParameters(prevEmployeeSalary.getTF_EmployeeSalary_ID(), BPartner.getC_BPartner_ID()).first();
		
		MEmployeeSalaryDet employeesalaryDet = new MEmployeeSalaryDet(ctx, 0, trxName);
		
		employeesalaryDet.setClientOrg(EmployeeSalary.getAD_Client_ID(), EmployeeSalary.getAD_Org_ID());
		employeesalaryDet.setSNo(SNo);
		employeesalaryDet.setTF_EmployeeSalary_ID(EmployeeSalary.getTF_EmployeeSalary_ID());
		employeesalaryDet.setC_BPartner_ID(BPartner.getC_BPartner_ID());
		employeesalaryDet.setDesignation(BPartner.getDesignation());
		employeesalaryDet.setSalary(BPartner.getMonthlySalary());
		employeesalaryDet.setNoOfDays(EmployeeSalary.getStd_Days());
		employeesalaryDet.setSalaryDue(BPartner.getMonthlySalary());
		
		if(prevEmployeeSalaryDet != null) {
			employeesalaryDet.setUnpaidSalary(prevEmployeeSalaryDet.getSalaryWithheld());
			employeesalaryDet.setNetSalary(BPartner.getMonthlySalary().add(prevEmployeeSalaryDet.getSalaryWithheld()));
		}
		else {
			employeesalaryDet.setUnpaidSalary(BigDecimal.ZERO);
			employeesalaryDet.setNetSalary(BPartner.getMonthlySalary());
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
		
		netSalary = salaryDue.add(unpaidSalary).subtract(deductAdvance).subtract(messAdvance).subtract(salaryWithheld);
		EmployeeSalaryDet.setNetSalary(netSalary);
		EmployeeSalaryDet.saveEx();
	}
}
