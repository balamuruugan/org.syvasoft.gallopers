package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalaryOld;

public class CalloutEmployeeSalary_CalcIncentiveAmt implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		BigDecimal stdwage = CalloutUtil.getBDValue(mTab, MEmployeeSalaryOld.COLUMNNAME_Std_Wage);		
		BigDecimal incentiveDays = CalloutUtil.getBDValue(mTab, MEmployeeSalaryOld.COLUMNNAME_IncentiveDays);		
		mTab.setValue(MEmployeeSalaryOld.COLUMNNAME_Incentive, stdwage.multiply(incentiveDays));
		return null;
	}

}
