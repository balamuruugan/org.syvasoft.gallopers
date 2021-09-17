package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;

public class CalloutOrderLine_ListPrice implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		BigDecimal PriceList = CalloutUtil.getBDValue(mTab, TF_MOrderLine.COLUMNNAME_PriceList);
		mTab.setValue(TF_MOrderLine.COLUMNNAME_Discount, BigDecimal.ZERO);
		mTab.setValue(TF_MOrderLine.COLUMNNAME_PriceEntered, PriceList);
		
		return null;
	}

}
