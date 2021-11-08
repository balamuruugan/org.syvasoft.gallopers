package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.TimeUtil;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeAttendance;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalaryOld;


public class CalloutEmployeeSalary_Attendance implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		
		Timestamp dateFrom = CalloutUtil.getTimestamp(mTab, MEmployeeSalaryOld.COLUMNNAME_DateFrom);
		Timestamp dateTo = CalloutUtil.getTimestamp(mTab, MEmployeeSalaryOld.COLUMNNAME_DateTo);
		int workingDays = 0;
		if(dateFrom != null && dateTo != null)
			workingDays =	TimeUtil.getDaysBetween(dateFrom, dateTo) + 1;
		
		boolean isBiometricAttendance = mTab.getValueAsBoolean(MEmployeeSalaryOld.COLUMNNAME_IsBiometricAttendance);
		
		if(isBiometricAttendance) {
			int AD_Org_ID = CalloutUtil.getIntValue(mTab, MEmployeeSalaryOld.COLUMNNAME_AD_Org_ID);
			int C_BPartner_ID = CalloutUtil.getIntValue(mTab, MEmployeeSalaryOld.COLUMNNAME_C_BPartner_ID);
			BigDecimal presentDays = MEmployeeAttendance.getPresentDays(ctx, AD_Org_ID, C_BPartner_ID, dateFrom, dateTo); 
			mTab.setValue(MEmployeeSalaryOld.COLUMNNAME_Present_Days, presentDays);
		}
		
		mTab.setValue(MEmployeeSalaryOld.COLUMNNAME_Std_Days, new BigDecimal(workingDays));
		
		return null;
	}

}
