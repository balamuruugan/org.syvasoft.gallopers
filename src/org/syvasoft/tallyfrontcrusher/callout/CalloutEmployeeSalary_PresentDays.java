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

public class CalloutEmployeeSalary_PresentDays implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,GridField mField, Object value, Object oldValue) {
		BigDecimal stdDays = BigDecimal.ZERO;
		BigDecimal absentees = (BigDecimal)mTab.getValue(MEmployeeSalaryDet.COLUMNNAME_Absentees);
		
		MEmployeeSalary employeeSalary = new MEmployeeSalary(ctx, (int)mTab.getValue(MEmployeeSalaryDet.COLUMNNAME_TF_EmployeeSalary_ID), null);
		
		
		stdDays = (employeeSalary.getStd_Days() == null) ? BigDecimal.ZERO : employeeSalary.getStd_Days();
		absentees = (absentees == null) ? BigDecimal.ZERO : absentees;
		
		BigDecimal presentDays = stdDays.subtract(absentees);
		
		mTab.setValue(MEmployeeSalaryDet.COLUMNNAME_NoOfDays, presentDays);
		
		return null;
	}

	
}
