package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeIncentive;
import org.syvasoft.tallyfrontcrusher.model.MTripSheet;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;

public class CalloutTripSheet_SetIncentiveRules implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		int C_BPartner_ID = CalloutUtil.getIntValue(mTab, MTripSheet.COLUMNNAME_C_BPartner_ID);
		int C_UOM_ID = CalloutUtil.getIntValue(mTab, MTripSheet.COLUMNNAME_C_UOM_ID);
		int AD_Org_ID = CalloutUtil.getIntValue(mTab, MTripSheet.COLUMNNAME_AD_Org_ID);
		MEmployeeIncentive inc = MEmployeeIncentive.get(ctx, AD_Org_ID, C_BPartner_ID, C_UOM_ID);
		TF_MBPartner bp = new TF_MBPartner(ctx, C_BPartner_ID, null);
		BigDecimal eligibleUnit = BigDecimal.ZERO;
		BigDecimal unitIncentive = BigDecimal.ZERO;
		BigDecimal dayIncentive = BigDecimal.ZERO;
		String incentiveType = null;
		
		if(inc != null) {
			eligibleUnit = inc.getEligibleUnit();
			incentiveType = inc.getIncentiveType();
			dayIncentive = inc.getDayIncentive();
			unitIncentive = inc.getUnitIncentive();
		}
		mTab.setValue(MTripSheet.COLUMNNAME_Earned_Wage, bp.getStd_Wage());
		mTab.setValue(MTripSheet.COLUMNNAME_IncentiveType, incentiveType);
		mTab.setValue(MTripSheet.COLUMNNAME_EligibleUnit, eligibleUnit);
		mTab.setValue(MTripSheet.COLUMNNAME_UnitIncentive, unitIncentive);
		mTab.setValue(MTripSheet.COLUMNNAME_DayIncentive, dayIncentive);
		return null;
	}
	
}
