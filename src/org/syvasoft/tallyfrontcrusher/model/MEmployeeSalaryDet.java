package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
		MEmployeeSalaryDet employeesalaryDet = new MEmployeeSalaryDet(ctx, 0, trxName);
		
		employeesalaryDet.setClientOrg(EmployeeSalary.getAD_Client_ID(), EmployeeSalary.getAD_Org_ID());
		employeesalaryDet.setSNo(SNo);
		employeesalaryDet.setTF_EmployeeSalary_ID(EmployeeSalary.getTF_EmployeeSalary_ID());
		employeesalaryDet.setC_BPartner_ID(BPartner.getC_BPartner_ID());
		employeesalaryDet.setDesignation(BPartner.getDesignation());
		employeesalaryDet.setSalary(BPartner.getMonthlySalary());
		employeesalaryDet.setNoOfDays(EmployeeSalary.getStd_Days());
		employeesalaryDet.setSalaryDue(BPartner.getMonthlySalary());
		employeesalaryDet.setNetSalary(BPartner.getMonthlySalary());
		employeesalaryDet.saveEx();
	}
	
	public static void calculateSalary(Properties ctx, String trxName, MEmployeeSalary EmployeeSalary, MEmployeeSalaryDet EmployeeSalaryDet) {
		BigDecimal stdDays = EmployeeSalary.getStd_Days();
		BigDecimal salary = EmployeeSalaryDet.getSalary();
		BigDecimal presentDays = stdDays;
		BigDecimal deductAdvance = EmployeeSalaryDet.getDeductAdvance();
		BigDecimal messAdvance = EmployeeSalaryDet.getMessAdvance();		
		
		EmployeeSalaryDet.setNoOfDays(stdDays);
		
		salary = (salary == null) ? BigDecimal.ZERO : salary;
		presentDays = (presentDays == null) ? BigDecimal.ZERO : presentDays;
		deductAdvance = (deductAdvance == null) ? BigDecimal.ZERO : deductAdvance;
		messAdvance = (messAdvance == null) ? BigDecimal.ZERO : messAdvance;
		
		BigDecimal salaryDue = BigDecimal.ZERO;
		BigDecimal netSalary = BigDecimal.ZERO;
		
		if(stdDays == BigDecimal.ZERO)
			salaryDue = BigDecimal.ZERO;
		else
			salaryDue = salary.multiply(presentDays).divide(stdDays).setScale(2, RoundingMode.HALF_EVEN);		
		EmployeeSalaryDet.setSalaryDue(salaryDue);
		
		netSalary = salaryDue.subtract(deductAdvance).subtract(messAdvance);
		EmployeeSalaryDet.setNetSalary(netSalary);
		EmployeeSalaryDet.saveEx();
	}
}
