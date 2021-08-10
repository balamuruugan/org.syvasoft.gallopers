package org.syvasoft.tallyfrontcrusher.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MSysConfig;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlanLine;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class CalloutDispensePlanLine_SetUOMTax implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		
		if(CalloutUtil.getIntValue(mTab, MDispensePlanLine.COLUMNNAME_C_OrderLine_ID) == 0) {
			int M_Product_ID = CalloutUtil.getIntValue(mTab, MDispensePlanLine.COLUMNNAME_M_Product_ID);
			//boolean isTaxIncluded = (boolean)mTab.getValue(TF_MOrderLine.COLUMNNAME_IsTaxIncluded);
			int bPartner_ID = 0;
			
			if(mTab.getValue(TF_MOrder.COLUMNNAME_C_BPartner_ID) != null)
				bPartner_ID	= (int) mTab.getValue(TF_MOrder.COLUMNNAME_C_BPartner_ID);
			
			TF_MBPartner bp = new TF_MBPartner(ctx, bPartner_ID, null);
			TF_MProduct product = new TF_MProduct(ctx, M_Product_ID, null);
			
			if(product != null) {
				mTab.setValue(MDispensePlanLine.COLUMNNAME_C_Tax_ID, product.getTax_ID(true, bp.isInterState()));
				mTab.setValue(MDispensePlanLine.COLUMNNAME_C_UOM_ID, product.getC_UOM_ID());
			}
		}
		return null;
	}
	
	

}
