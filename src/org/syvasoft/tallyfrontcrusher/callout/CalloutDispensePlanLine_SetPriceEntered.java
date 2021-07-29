package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MTax;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlanLine;

public class CalloutDispensePlanLine_SetPriceEntered implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		//if(CalloutUtil.getIntValue(mTab, MDispensePlanLine.COLUMNNAME_C_OrderLine_ID) == 0) 
		{
			int C_Tax_ID = CalloutUtil.getIntValue(mTab, MDispensePlanLine.COLUMNNAME_C_Tax_ID);
			BigDecimal unitPrice = CalloutUtil.getBDValue(mTab, MDispensePlanLine.COLUMNNAME_UnitPrice);
			BigDecimal qty = CalloutUtil.getBDValue(mTab, MDispensePlanLine.COLUMNNAME_DispenseQty);
			
			if(unitPrice.doubleValue() == 0)
				return null;
			
			boolean priceIncludesTax = mTab.getValueAsBoolean(MDispensePlanLine.COLUMNNAME_IsTaxIncluded);
			
			MTax tax = new MTax(ctx, C_Tax_ID, null);
			BigDecimal taxRate = tax.getRate().divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN).add(BigDecimal.ONE);		
			BigDecimal priceWOTax = unitPrice.divide(taxRate, 2, RoundingMode.HALF_EVEN);
			
			
			
			if(priceIncludesTax)
				priceWOTax = unitPrice.divide(taxRate, 2, RoundingMode.HALF_EVEN);
			else
				priceWOTax = unitPrice;
			
			BigDecimal linenetAmt = unitPrice.multiply(qty);
			
			mTab.setValue(MDispensePlanLine.COLUMNNAME_PriceEntered, priceWOTax);
			mTab.setValue(MDispensePlanLine.COLUMNNAME_PriceActual, priceWOTax);
			mTab.setValue(MDispensePlanLine.COLUMNNAME_PriceList, priceWOTax);
			mTab.setValue(MDispensePlanLine.COLUMNNAME_PriceLimit, priceWOTax);
			mTab.setValue(MDispensePlanLine.COLUMNNAME_LineNetAmt, linenetAmt);
		}
		
		return null;
	}

}
