package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
		BigDecimal RentAmount = CalloutUtil.getBDValue(mTab, MWeighmentEntry.COLUMNNAME_Rent_Amt);
		
		BigDecimal GstAmt = BigDecimal.ZERO;
		if(mTab.getValue(MWeighmentEntry.COLUMNNAME_GSTAmount) != null)
				GstAmt = (BigDecimal) mTab.getValue(MWeighmentEntry.COLUMNNAME_GSTAmount);
		
		BigDecimal driverTips = (BigDecimal) mTab.getValue(MWeighmentEntry.COLUMNNAME_DriverTips);		
		Boolean ApplyTax = mTab.getValueAsBoolean(MWeighmentEntry.COLUMNNAME_IsPermitSales);
		if(ApplyTax)
			GstAmt = RentAmount.add(Amount).multiply(new BigDecimal(0.05)).setScale(2, RoundingMode.HALF_EVEN);
		else
			GstAmt = BigDecimal.ZERO;
		BigDecimal TotalAmt = Amount.add(GstAmt).subtract(driverTips);
		mTab.setValue(MWeighmentEntry.COLUMNNAME_GSTAmount, GstAmt);		
		mTab.setValue(MWeighmentEntry.COLUMNNAME_Amount, Amount);
		mTab.setValue(MWeighmentEntry.COLUMNNAME_TotalAmt, TotalAmt);
		return null;
	}

}
