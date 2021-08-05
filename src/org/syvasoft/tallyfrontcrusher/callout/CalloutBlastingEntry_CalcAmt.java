package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MBlastingEntry;

public class CalloutBlastingEntry_CalcAmt implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		BigDecimal qty = CalloutUtil.getBDValue(mTab, MBlastingEntry.COLUMNNAME_Qty);
		BigDecimal price = CalloutUtil.getBDValue(mTab, MBlastingEntry.COLUMNNAME_Price);
		
		return null;
	}

}
