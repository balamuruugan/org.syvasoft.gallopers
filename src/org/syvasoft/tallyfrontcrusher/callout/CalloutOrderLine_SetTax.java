package org.syvasoft.tallyfrontcrusher.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MSysConfig;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class CalloutOrderLine_SetTax implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		
		int M_Product_ID = CalloutUtil.getIntValue(mTab, TF_MOrderLine.COLUMNNAME_M_Product_ID);
		//boolean isTaxIncluded = (boolean)mTab.getValue(TF_MOrderLine.COLUMNNAME_IsTaxIncluded);
		
		TF_MProduct product = new TF_MProduct(ctx, M_Product_ID, null);
		mTab.setValue(TF_MOrderLine.COLUMNNAME_C_Tax_ID, product.getTax_ID(true));
		 
		return null;
	}
	
	

}
