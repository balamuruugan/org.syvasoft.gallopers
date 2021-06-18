package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MTripSheetAddionalMeter;

public class CalloutTripSheetAM_CalcAmount implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		BigDecimal rate = CalloutUtil.getBDValue(mTab, MTripSheetAddionalMeter.COLUMNNAME_Rate);
		BigDecimal runningMeter = CalloutUtil.getBDValue(mTab, MTripSheetAddionalMeter.COLUMNNAME_Running_Meter);
		BigDecimal amount = rate.multiply(runningMeter);
		mTab.setValue(MTripSheetAddionalMeter.COLUMNNAME_Amount, amount);
		return null;
	}

}
