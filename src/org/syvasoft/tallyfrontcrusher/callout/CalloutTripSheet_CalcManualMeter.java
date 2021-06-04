package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MTripSheet;

public class CalloutTripSheet_CalcManualMeter implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		BigDecimal loads = CalloutUtil.getBDValue(mTab, MTripSheet.COLUMNNAME_NoOfLoad);
		BigDecimal MTperLoad = CalloutUtil.getBDValue(mTab, MTripSheet.COLUMNNAME_TonnagePerLoad);
		BigDecimal totalMT = loads.multiply(MTperLoad);
		BigDecimal RunningMeter = CalloutUtil.getBDValue(mTab, MTripSheet.COLUMNNAME_Running_Meter);
		BigDecimal GrandTotalMT = totalMT.add(RunningMeter);
		
		mTab.setValue(MTripSheet.COLUMNNAME_TotalMT, totalMT);
		mTab.setValue(MTripSheet.COLUMNNAME_TotalMTExtended, GrandTotalMT);
		return null;
	}

}
