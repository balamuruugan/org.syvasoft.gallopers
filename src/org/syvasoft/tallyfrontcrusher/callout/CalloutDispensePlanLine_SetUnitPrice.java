package org.syvasoft.tallyfrontcrusher.callout;


import java.util.Properties;
import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlanLine;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;


public class CalloutDispensePlanLine_SetUnitPrice implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		
		if(CalloutUtil.getIntValue(mTab, MDispensePlanLine.COLUMNNAME_C_OrderLine_ID) == 0) {
			int TF_Destination_ID = CalloutUtil.getIntValue(mTab, MDispensePlanLine.COLUMNNAME_TF_Destination_ID);
			int C_UOM_ID = CalloutUtil.getIntValue(mTab, MDispensePlanLine.COLUMNNAME_C_UOM_ID);
			int M_Product_ID = CalloutUtil.getIntValue(mTab, MDispensePlanLine.COLUMNNAME_M_Product_ID);
			
			if(M_Product_ID > 0 && C_UOM_ID > 0) {
				MPriceListUOM priceUOM = MPriceListUOM.getPriceListUOM(ctx, M_Product_ID, C_UOM_ID, 
						CalloutUtil.getIntValue(mTab, MDispensePlanLine.COLUMNNAME_C_BPartner_ID), TF_Destination_ID, true, 
						CalloutUtil.getTimestamp(mTab, MDispensePlanLine.COLUMNNAME_DateOrdered));
				if(priceUOM != null) {
					mTab.setValue(MDispensePlanLine.COLUMNNAME_UnitPrice, priceUOM.getPrice(true));
					mTab.setValue(MDispensePlanLine.COLUMNNAME_IsTaxIncluded, priceUOM.isTaxIncluded());
					mTab.setValue(MDispensePlanLine.COLUMNNAME_IsRoyaltyPassInclusive, priceUOM.isRoyaltyPassInclusive());
					mTab.setValue(MDispensePlanLine.COLUMNNAME_IsRentInclusive, priceUOM.isRentInclusive());
				}
			}
		}
		
		return null;
	}
	
	

}
