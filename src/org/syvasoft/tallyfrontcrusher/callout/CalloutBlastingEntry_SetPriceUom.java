package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MBlastingEntry;
import org.syvasoft.tallyfrontcrusher.model.MFuelIssue;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class CalloutBlastingEntry_SetPriceUom implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		int AD_Org_ID = (int) mTab.getValue(MFuelIssue.COLUMNNAME_AD_Org_ID);
		int M_Product_ID = 0;

		if(mTab.getValue(MBlastingEntry.COLUMNNAME_M_Product_ID) != null)
			M_Product_ID = (int) mTab.getValue(MBlastingEntry.COLUMNNAME_M_Product_ID);
		else
			return null;
		
		TF_MProduct prod=new TF_MProduct(ctx, M_Product_ID, null);
		BigDecimal currentCost = TF_MProduct.getCurrentCost(AD_Org_ID, M_Product_ID);
		mTab.setValue(MBlastingEntry.COLUMNNAME_Price, currentCost);
		mTab.setValue(MBlastingEntry.COLUMNNAME_C_UOM_ID, prod.getC_UOM_ID());
		return null;
	}

}
