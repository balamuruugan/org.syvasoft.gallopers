package org.syvasoft.tallyfrontcrusher.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MFuelIssue;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class CalloutFuelIssue_Barcode implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {		
		String barcode = CalloutUtil.getString(mTab, TF_MOrderLine.COLUMNNAME_Barcode);
		int M_Product_ID = TF_MProduct.getM_Product_ID(ctx, barcode);
		int M_Product_Category_ID = 0;
		
		if(M_Product_ID > 0) {
			TF_MProduct prod = new TF_MProduct(ctx, M_Product_ID, null);
			M_Product_Category_ID = prod.getM_Product_Category_ID();
		}
		
		mTab.setValue(MFuelIssue.COLUMNNAME_M_Product_Category_ID, M_Product_Category_ID);
		mTab.setValue(MFuelIssue.COLUMNNAME_M_Product_ID, M_Product_ID);
		return null;
	}

}
