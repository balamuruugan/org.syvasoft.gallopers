package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MTax;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;

public class CalloutOrderLine_SetPriceEntered implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		int C_Tax_ID = CalloutUtil.getIntValue(mTab, TF_MOrderLine.COLUMNNAME_C_Tax_ID);
		BigDecimal unitPrice = CalloutUtil.getBDValue(mTab, TF_MOrderLine.COLUMNNAME_UnitPrice);
		BigDecimal qty = CalloutUtil.getBDValue(mTab, TF_MOrderLine.COLUMNNAME_QtyEntered);
		
		if(unitPrice.doubleValue() == 0)
			return null;
		
		boolean priceIncludesTax = mTab.getValueAsBoolean(TF_MOrderLine.COLUMNNAME_IsTaxIncluded);
		
		MTax tax = new MTax(ctx, C_Tax_ID, null);
		BigDecimal taxRate = tax.getRate().divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN).add(BigDecimal.ONE);		
		BigDecimal priceWOTax = unitPrice.divide(taxRate, 2, RoundingMode.HALF_EVEN);
		
		if(priceIncludesTax)
			priceWOTax = unitPrice.divide(taxRate, 2, RoundingMode.HALF_EVEN);
		else
			priceWOTax = unitPrice;
		
		mTab.setValue(TF_MOrderLine.COLUMNNAME_PriceEntered, priceWOTax);
		mTab.setValue(TF_MOrderLine.COLUMNNAME_PriceActual, priceWOTax);
		mTab.setValue(TF_MOrderLine.COLUMNNAME_PriceList, priceWOTax);
		mTab.setValue(TF_MOrderLine.COLUMNNAME_PriceLimit, priceWOTax);		
		
		mTab.setValue(TF_MOrderLine.COLUMNNAME_LineNetAmt, priceWOTax.multiply(qty));
		return null;
	}

}
