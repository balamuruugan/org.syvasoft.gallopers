package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MEmpSalaryConfig;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalary;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalaryOld;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalaryAdvance;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalaryDet;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;

public class CalloutEmployeeSalary_NetSalary implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,GridField mField, Object value, Object oldValue) {
		BigDecimal stdDays = BigDecimal.ZERO;
		BigDecimal salary = (BigDecimal)mTab.getValue(MEmployeeSalaryDet.COLUMNNAME_Salary);
		BigDecimal presentDays = (BigDecimal)mTab.getValue(MEmployeeSalaryDet.COLUMNNAME_NoOfDays);
		BigDecimal deductAdvance = (BigDecimal)mTab.getValue(MEmployeeSalaryDet.COLUMNNAME_DeductAdvance);
		BigDecimal messAdvance = (BigDecimal)mTab.getValue(MEmployeeSalaryDet.COLUMNNAME_MessAdvance);		
		
		MEmployeeSalary employeeSalary = new MEmployeeSalary(ctx, (int)mTab.getValue(MEmployeeSalaryDet.COLUMNNAME_TF_EmployeeSalary_ID), null);
		
		
		stdDays = (employeeSalary.getStd_Days() == null) ? BigDecimal.ZERO : employeeSalary.getStd_Days();
		salary = (salary == null) ? BigDecimal.ZERO : salary;
		presentDays = (presentDays == null) ? BigDecimal.ZERO : presentDays;
		deductAdvance = (deductAdvance == null) ? BigDecimal.ZERO : deductAdvance;
		messAdvance = (messAdvance == null) ? BigDecimal.ZERO : messAdvance;
		
		BigDecimal salaryDue = BigDecimal.ZERO;
		BigDecimal netSalary = BigDecimal.ZERO;
		
		salaryDue = (BigDecimal)mTab.getValue(MEmployeeSalaryDet.COLUMNNAME_SalaryDue);
		
		netSalary = salaryDue.subtract(deductAdvance).subtract(messAdvance);
		
		mTab.setValue(MEmployeeSalaryDet.COLUMNNAME_NetSalary, netSalary);
		return null;
	}

	
}
