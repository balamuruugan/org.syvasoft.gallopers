package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.syvasoft.tallyfrontcrusher.model.MBlastingEntry;
import org.syvasoft.tallyfrontcrusher.model.MFuelIssue;

public class CalloutBlastingEntry_AvailableQty implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		if(mTab.getValue(MBlastingEntry.COLUMNNAME_M_Locator_ID)!=null && mTab.getValue(MBlastingEntry.COLUMNNAME_M_Product_ID)!=null) {
			int AD_Org_ID=(int)mTab.getValue(MBlastingEntry.COLUMNNAME_AD_Org_ID);
			int M_Locator_ID=(int)mTab.getValue(MBlastingEntry.COLUMNNAME_M_Locator_ID);
			int M_Product_ID=(int)mTab.getValue(MBlastingEntry.COLUMNNAME_M_Product_ID);
			String sql="SELECT SUM(QtyOnHand) FROM m_storeageonhand_v WHERE AD_Org_ID=? AND M_Locator_ID=? AND M_Product_ID=?";
			BigDecimal availQty = DB.getSQLValueBDEx(null, sql, AD_Org_ID, M_Locator_ID,M_Product_ID);
			mTab.setValue(MBlastingEntry.COLUMNNAME_QtyAvailable, availQty);
		}

		return null;
	}

}
