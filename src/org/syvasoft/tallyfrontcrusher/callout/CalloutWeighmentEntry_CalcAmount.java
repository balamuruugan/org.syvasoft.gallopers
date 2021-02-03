package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;

public class CalloutWeighmentEntry_CalcAmount implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		BigDecimal qty = (BigDecimal) mTab.getValue(MWeighmentEntry.COLUMNNAME_NetWeightUnit);
		BigDecimal price = (BigDecimal) mTab.getValue(MWeighmentEntry.COLUMNNAME_Price);
		BigDecimal Amount = qty.multiply(price);
		BigDecimal GstAmt = (BigDecimal) mTab.getValue(MWeighmentEntry.COLUMNNAME_GSTAmount);
		BigDecimal driverTips = (BigDecimal) mTab.getValue(MWeighmentEntry.COLUMNNAME_DriverTips);
		BigDecimal TotalAmt = Amount.add(GstAmt).subtract(driverTips);
		
		mTab.setValue(MWeighmentEntry.COLUMNNAME_Amount, Amount);
		mTab.setValue(MWeighmentEntry.COLUMNNAME_TotalAmt, TotalAmt);
		return null;
	}

}
