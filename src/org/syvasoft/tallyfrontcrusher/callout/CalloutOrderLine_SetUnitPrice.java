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

public class CalloutOrderLine_SetUnitPrice implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		
		int C_Order_ID = (int) mTab.getValue(TF_MOrder.COLUMNNAME_C_Order_ID);
		TF_MOrder ord = new TF_MOrder(ctx, C_Order_ID, null);
		
		/*int C_DocType_ID = MSysConfig.getIntValue("STANDARD_ORDER", 1000032);
		
		if(C_DocType_ID != ord.getC_DocTypeTarget_ID())
			return null;*/
		
		int TF_Destination_ID = CalloutUtil.getIntValue(mTab, TF_MOrder.COLUMNNAME_TF_Destination_ID);
		int C_UOM_ID = CalloutUtil.getIntValue(mTab, TF_MOrderLine.COLUMNNAME_C_UOM_ID);
		int M_Product_ID = CalloutUtil.getIntValue(mTab, TF_MOrderLine.COLUMNNAME_M_Product_ID);
		
		MPriceListUOM priceUOM = MPriceListUOM.getPriceListUOM(ctx, M_Product_ID, C_UOM_ID, ord.getC_BPartner_ID(), TF_Destination_ID, true, ord.getDateAcct());
		if(priceUOM != null) {
			mTab.setValue(TF_MOrderLine.COLUMNNAME_UnitPrice, priceUOM.getPrice(true));
			mTab.setValue(TF_MOrderLine.COLUMNNAME_IsTaxIncluded, priceUOM.isTaxIncluded());
			mTab.setValue(TF_MOrderLine.COLUMNNAME_IsRoyaltyPassInclusive, priceUOM.isRoyaltyPassInclusive());
			mTab.setValue(TF_MOrderLine.COLUMNNAME_IsRentInclusive, priceUOM.isRentInclusive());
			
			
			/*TF_MProduct prod = new TF_MProduct(ctx, M_Product_ID, null);
			mTab.setValue(TF_MOrderLine.COLUMNNAME_C_Tax_ID, prod.getTax_ID(true));*/
		}
		
		return null;
	}
	
	

}
