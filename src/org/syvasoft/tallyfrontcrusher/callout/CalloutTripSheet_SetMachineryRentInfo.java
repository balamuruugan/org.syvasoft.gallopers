package org.syvasoft.tallyfrontcrusher.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MMachinery;
import org.syvasoft.tallyfrontcrusher.model.MTripSheet;

public class CalloutTripSheet_SetMachineryRentInfo implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		//get Rent UOM from Machinery Master or from Machinery Rent Config
		//for time being, just set same UOM as selected.
		int PM_Machinery_ID = CalloutUtil.getIntValue(mTab, MTripSheet.COLUMNNAME_PM_Machinery_ID);
		MMachinery m = new MMachinery(ctx, PM_Machinery_ID, null);
		mTab.setValue(MTripSheet.COLUMNNAME_Rent_UOM_ID, m.getPM_MachineryType().getC_UOM_ID());
		//mTab.setValue(MTripSheet.COLUMNNAME_C_UOM_ID, m.getPM_MachineryType().getC_UOM_ID());
		return null;
	}

}
