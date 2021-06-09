package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MMachinery;
import org.syvasoft.tallyfrontcrusher.model.MMachineryRentConfig;
import org.syvasoft.tallyfrontcrusher.model.MTripSheet;

public class CalloutTripSheet_SetMachineryRentInfo implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		//get Rent UOM from Machinery Master or from Machinery Rent Config
		//for time being, just set same UOM as selected.
		int PM_Machinery_ID = CalloutUtil.getIntValue(mTab, MTripSheet.COLUMNNAME_PM_Machinery_ID);
		int Jobwork_Product_ID = CalloutUtil.getIntValue(mTab, MTripSheet.COLUMNNAME_JobWork_Product_ID);
		
		MMachinery m = new MMachinery(ctx, PM_Machinery_ID, null);
		int rentUOM_ID = m.getPM_MachineryType().getC_UOM_ID();
		
		
		
		if(mField.getColumnName().equals(MTripSheet.COLUMNNAME_Rent_UOM_ID))
			rentUOM_ID = CalloutUtil.getIntValue(mTab, MTripSheet.COLUMNNAME_Rent_UOM_ID);
		else
			mTab.setValue(MTripSheet.COLUMNNAME_Rent_UOM_ID, rentUOM_ID);
		
		BigDecimal unitRent = MMachineryRentConfig.getRent(ctx, PM_Machinery_ID, Jobwork_Product_ID, rentUOM_ID);		
		mTab.setValue(MTripSheet.COLUMNNAME_Rate, unitRent);
		
		return null;
	}

}
