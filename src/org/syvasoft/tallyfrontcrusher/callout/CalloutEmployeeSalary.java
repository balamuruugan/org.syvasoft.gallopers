package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MEmpSalaryConfig;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalaryOld;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalaryAdvance;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;

public class CalloutEmployeeSalary implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		BigDecimal stdDays = BigDecimal.ZERO;
		BigDecimal stdWage = BigDecimal.ZERO;
		BigDecimal presentDays = BigDecimal.ZERO;
		boolean isCalculated = mTab.getValueAsBoolean(MEmployeeSalaryOld.COLUMNNAME_IsCalculated);		
		
		if(mTab.getValue(MEmployeeSalaryOld.COLUMNNAME_Present_Days) != null)
			presentDays = (BigDecimal) mTab.getValue(MEmployeeSalaryOld.COLUMNNAME_Present_Days);
		
		if(value == null || mTab.getValue(MEmployeeSalaryOld.COLUMNNAME_DateAcct) == null || 
				mTab.getValue(MEmployeeSalaryOld.COLUMNNAME_C_BPartner_ID) == null) {
			mTab.setValue(MEmployeeSalaryOld.COLUMNNAME_Std_Days, stdDays);
			mTab.setValue(MEmployeeSalaryOld.COLUMNNAME_Std_Wage, stdWage);
			mTab.setValue(MEmployeeSalaryOld.COLUMNNAME_Salary_Amt, BigDecimal.ZERO);		
		}
		else {
			Timestamp dateAcct = null;
			dateAcct = (Timestamp) mTab.getValue(MEmployeeSalaryOld.COLUMNNAME_DateAcct);		
			int bpartner_ID = (int)  mTab.getValue(MEmployeeSalaryOld.COLUMNNAME_C_BPartner_ID);
			TF_MBPartner bp = new TF_MBPartner(ctx, bpartner_ID, null);
			stdDays = bp.getStd_Days();
			stdWage = bp.getStd_Wage();
			
			//MEmpSalaryConfig salaryConfig = MEmpSalaryConfig.getEmpSalaryConfig(ctx, bpartner_ID, dateAcct);
			
			//if(salaryConfig != null && isCalculated) {
			//	stdDays = salaryConfig.getStd_Days();
			//	stdWage = salaryConfig.getStd_Wage();
			//}
			
			BigDecimal earnedSalary = (BigDecimal) mTab.getValue(MEmployeeSalaryOld.COLUMNNAME_Salary_Amt);
			if(stdDays.doubleValue() !=0  && isCalculated)
				earnedSalary = stdWage.multiply(presentDays.divide(stdDays, 2, RoundingMode.HALF_UP));
			else if(isCalculated)
				earnedSalary = BigDecimal.ZERO;				
			
			mTab.setValue(MEmployeeSalaryOld.COLUMNNAME_Std_Days, stdDays);
			mTab.setValue(MEmployeeSalaryOld.COLUMNNAME_Std_Wage, stdWage);
			mTab.setValue(MEmployeeSalaryOld.COLUMNNAME_Salary_Amt, earnedSalary);
		}
		return null;
	}

	
}
