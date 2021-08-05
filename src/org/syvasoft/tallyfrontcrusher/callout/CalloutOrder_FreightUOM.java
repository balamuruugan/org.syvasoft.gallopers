package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MSysConfig;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;

public class CalloutOrder_FreightUOM implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		boolean isSOTrx = Env.getContext(ctx, WindowNo, "IsSOTrx").equals("Y");
		
		if(isSOTrx) {
			boolean isRentInclusive = mTab.getValueAsBoolean(TF_MOrderLine.COLUMNNAME_IsRentInclusive);
			
			if(isRentInclusive) {
				mTab.setValue(TF_MOrderLine.COLUMNNAME_FreightUOM_ID, 0);
			}
		}
		return null;
	}

}
