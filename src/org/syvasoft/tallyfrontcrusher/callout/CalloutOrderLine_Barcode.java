package org.syvasoft.tallyfrontcrusher.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MLocator;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class CalloutOrderLine_Barcode implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		int AD_Org_ID = CalloutUtil.getIntValue(mTab, TF_MOrderLine.COLUMNNAME_AD_Org_ID);
		String barcode = CalloutUtil.getString(mTab, TF_MOrderLine.COLUMNNAME_Barcode);
		int M_Product_ID = TF_MProduct.getM_Product_ID(ctx, barcode);
		int M_Product_Category_ID = 0;
		int M_Warehouse_ID = 0;
		int M_Locator_ID = 0;
		int C_Tax_ID = 0;
		int C_BPartner_ID = Env.getContextAsInt(ctx, WindowNo, "C_BPartner_ID");
		TF_MBPartner bp = new TF_MBPartner(ctx, C_BPartner_ID, null);
		if(M_Product_ID > 0) {
			TF_MProduct prod = new TF_MProduct(ctx, M_Product_ID, null);
			MLocator loc = TF_MProduct.getLocator(ctx, AD_Org_ID, M_Product_ID);
			M_Product_Category_ID = prod.getM_Product_Category_ID();
			M_Warehouse_ID = loc.getM_Warehouse_ID();
			M_Locator_ID = loc.getM_Locator_ID();
			C_Tax_ID = prod.getTax_ID(true, bp.isApplyTCS(), bp.isInterState());
		}
		
		mTab.setValue("M_WarehouseNew_ID", M_Warehouse_ID);	
		mTab.setValue(TF_MProduct.COLUMNNAME_M_Product_Category_ID, M_Product_Category_ID);
		mTab.setValue(TF_MOrderLine.COLUMNNAME_M_Product_ID, M_Product_ID);
		mTab.setValue(MLocator.COLUMNNAME_M_Locator_ID, M_Locator_ID);
		mTab.setValue(TF_MOrderLine.COLUMNNAME_C_Tax_ID, C_Tax_ID);
		return null;
	}

}
