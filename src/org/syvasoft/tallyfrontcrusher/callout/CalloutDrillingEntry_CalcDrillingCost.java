package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MDrillingEntry;
import org.syvasoft.tallyfrontcrusher.model.MMachineryRentConfig;
import org.syvasoft.tallyfrontcrusher.model.MTripSheet;

public class CalloutDrillingEntry_CalcDrillingCost implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		BigDecimal holes = BigDecimal.ZERO;
		BigDecimal feet = BigDecimal.ZERO;
		BigDecimal rate = BigDecimal.ZERO;
		int TF_TripSheet_ID = CalloutUtil.getIntValue(mTab, MDrillingEntry.COLUMNNAME_TF_TripSheet_ID);
		if(TF_TripSheet_ID > 0) {
			MTripSheet ts = new MTripSheet(ctx, TF_TripSheet_ID, null);
			int PM_Machinery_ID = ts.getPM_Machinery_ID();
			int Jobwork_ID = CalloutUtil.getIntValue(mTab, MDrillingEntry.COLUMNNAME_M_Product_ID);
			int UOM_ID = CalloutUtil.getIntValue(mTab, MDrillingEntry.COLUMNNAME_C_UOM_ID);
			BigDecimal feetRate = MMachineryRentConfig.getRent(ctx, PM_Machinery_ID, Jobwork_ID, UOM_ID);
			if(feetRate != null) {
				mTab.setValue(MDrillingEntry.COLUMNNAME_FeetRate, feetRate);
			}
				
		}
		holes = (BigDecimal) mTab.getValue(MDrillingEntry.COLUMNNAME_Holes);
		feet = (BigDecimal) mTab.getValue(MDrillingEntry.COLUMNNAME_Feet);
		rate = (BigDecimal) mTab.getValue(MDrillingEntry.COLUMNNAME_FeetRate);
		mTab.setValue(MDrillingEntry.COLUMNNAME_TotalFeet, holes.multiply(feet));
		mTab.setValue(MDrillingEntry.COLUMNNAME_DrillingCost, holes.multiply(feet).multiply(rate));
		return null;
	}

}
