package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MMachineryRentConfig;
import org.syvasoft.tallyfrontcrusher.model.MTripSheetAddionalMeter;

public class CalloutTripSheetAM_SetRate implements IColumnCallout {
	
	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {		
		int PM_Machinery_ID = CalloutUtil.getIntValue(mTab, MTripSheetAddionalMeter.COLUMNNAME_PM_Machinery_ID);
		int C_UOM_ID = CalloutUtil.getIntValue(mTab, MTripSheetAddionalMeter.COLUMNNAME_C_UOM_ID);
		
		MMachineryRentConfig m = MMachineryRentConfig.getExpense(ctx, PM_Machinery_ID, 0, C_UOM_ID);
		BigDecimal unitExpense = BigDecimal.ZERO;
		String description = null;
		if(m != null) {
			description = m.getDescription();
			unitExpense = m.getUnitExpense();
		}
		
		mTab.setValue(MTripSheetAddionalMeter.COLUMNNAME_Rate, unitExpense);
		mTab.setValue(MTripSheetAddionalMeter.COLUMNNAME_Rate, description);
		return null;
	}

}
