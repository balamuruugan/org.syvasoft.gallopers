package org.syvasoft.tallyfrontcrusher.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.Query;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;

public class CalloutPriceList_BPartner implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		int C_BPartner_ID = 0;
		TF_MBPartner bp = null;
		boolean isRentInclusive = false;
		boolean isTaxIncluded = false;
		
		if(mTab.getValue(MPriceListUOM.COLUMNNAME_C_BPartner_ID) != null) {
			C_BPartner_ID = (int) mTab.getValue(MPriceListUOM.COLUMNNAME_C_BPartner_ID);
		}
		else {
			bp = new Query(ctx, TF_MBPartner.Table_Name, "IsPOSCashBP = 'Y'", null)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.first();			
		}
		
		if(C_BPartner_ID > 0) {
			bp = new TF_MBPartner(ctx, C_BPartner_ID, null);			
		}		
		
		isRentInclusive = bp.isRentInclusive();
		isTaxIncluded = bp.isTaxIncluded();
		mTab.setValue(MPriceListUOM.COLUMNNAME_IsRentInclusive, isRentInclusive);
		mTab.setValue(MPriceListUOM.COLUMNNAME_IsTaxIncluded, isTaxIncluded);
		
		return null;
	}

}
