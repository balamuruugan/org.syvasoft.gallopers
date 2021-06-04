package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.Callout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MTripSheet;

public class CalloutTripSheet_CalcIncentive implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		BigDecimal totalMTExtended = CalloutUtil.getBDValue(mTab, MTripSheet.COLUMNNAME_TotalMTExtended);
		BigDecimal RunningMeter = CalloutUtil.getBDValue(mTab, MTripSheet.COLUMNNAME_Running_Meter);		
		BigDecimal qty = totalMTExtended;
		
		if(!mTab.getValueAsBoolean(MTripSheet.COLUMNNAME_IsManual))
			qty = RunningMeter;
		
		BigDecimal unitIncentive = CalloutUtil.getBDValue(mTab, MTripSheet.COLUMNNAME_UnitIncentive);
		BigDecimal dayincentive = CalloutUtil.getBDValue(mTab, MTripSheet.COLUMNNAME_DayIncentive);
		BigDecimal eligibleUnit = CalloutUtil.getBDValue(mTab, MTripSheet.COLUMNNAME_EligibleUnit);
		
		boolean calcIncentive = qty.doubleValue() >= eligibleUnit.doubleValue();
		
		BigDecimal incentiveAmt = BigDecimal.ZERO;
		
		if(calcIncentive) {
			if(dayincentive.doubleValue() > 0)
				incentiveAmt = dayincentive;
			else
				incentiveAmt = unitIncentive.multiply(qty).setScale(2, RoundingMode.HALF_EVEN);
		}
		
		mTab.setValue(MTripSheet.COLUMNNAME_Incentive, incentiveAmt);
		
		return null;
	}

}
