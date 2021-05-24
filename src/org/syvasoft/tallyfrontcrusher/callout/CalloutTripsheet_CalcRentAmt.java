package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MTripSheet;

public class CalloutTripsheet_CalcRentAmt implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		BigDecimal runningMeter = CalloutUtil.getBDValue(mTab, MTripSheet.COLUMNNAME_Running_Meter);
		BigDecimal rate = CalloutUtil.getBDValue(mTab, MTripSheet.COLUMNNAME_Rate);
		BigDecimal Rent = runningMeter.multiply(rate);
		mTab.setValue(MTripSheet.COLUMNNAME_Rent_Amt, Rent);
		return null;
	}

}
