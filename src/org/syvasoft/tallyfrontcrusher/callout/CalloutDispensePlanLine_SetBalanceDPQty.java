package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MSysConfig;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlanLine;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class CalloutDispensePlanLine_SetBalanceDPQty implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		
		BigDecimal planQty = (BigDecimal)mTab.getValue(MDispensePlanLine.COLUMNNAME_DispenseQty);
		BigDecimal delQty = (BigDecimal)mTab.getValue(MDispensePlanLine.COLUMNNAME_DeliveredDPQty);
		
		mTab.setValue(MDispensePlanLine.COLUMNNAME_BalanceDPQty, planQty.subtract(delQty));
		return null;
	}
	
	

}
