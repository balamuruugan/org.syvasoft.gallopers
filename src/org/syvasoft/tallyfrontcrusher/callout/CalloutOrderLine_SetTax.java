package org.syvasoft.tallyfrontcrusher.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MLocator;
import org.compiere.model.MSysConfig;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class CalloutOrderLine_SetTax implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		
		int M_Product_ID = CalloutUtil.getIntValue(mTab, TF_MOrderLine.COLUMNNAME_M_Product_ID);
		//boolean isTaxIncluded = (boolean)mTab.getValue(TF_MOrderLine.COLUMNNAME_IsTaxIncluded);
		int bPartner_ID = 0;
		
		if(mTab.getValue(TF_MOrder.COLUMNNAME_C_BPartner_ID) != null)
			bPartner_ID	= (int) mTab.getValue(TF_MOrder.COLUMNNAME_C_BPartner_ID);
		
		TF_MBPartner bp = new TF_MBPartner(ctx, bPartner_ID, null);
		TF_MProduct product = new TF_MProduct(ctx, M_Product_ID, null);
		mTab.setValue(TF_MOrderLine.COLUMNNAME_C_Tax_ID, product.getTax_ID(true,bp.isApplyTCS(),bp.isInterState()));
		
		int M_Locator_ID = CalloutUtil.getIntValue(mTab, TF_MOrderLine.COLUMNNAME_M_Locator_ID);
		int M_Product_Category_ID = 0;
		int M_Warehouse_ID  = 0;
		int AD_Org_ID = CalloutUtil.getIntValue(mTab, TF_MOrderLine.COLUMNNAME_AD_Org_ID);
		if(M_Locator_ID == 0) {
			MLocator loc = TF_MProduct.getLocator(ctx, AD_Org_ID, M_Product_ID);
			M_Product_Category_ID = product.getM_Product_Category_ID();
			M_Warehouse_ID = loc.getM_Warehouse_ID();
			M_Locator_ID = loc.getM_Locator_ID();
			
			mTab.setValue("M_WarehouseNew_ID", M_Warehouse_ID);	
			mTab.setValue(TF_MProduct.COLUMNNAME_M_Product_Category_ID, M_Product_Category_ID);
			mTab.setValue(TF_MOrderLine.COLUMNNAME_M_Product_ID, M_Product_ID);
			mTab.setValue(MLocator.COLUMNNAME_M_Locator_ID, M_Locator_ID);			
		}
		
		
		
		return null;
	}
	
	

}
