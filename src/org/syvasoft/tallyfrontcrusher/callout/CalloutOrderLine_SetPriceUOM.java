package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.Callout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;

public class CalloutOrderLine_SetPriceUOM implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		int AD_Org_ID = CalloutUtil.getIntValue(mTab, TF_MOrderLine.COLUMNNAME_AD_Org_ID);
		int M_Product_ID = CalloutUtil.getIntValue(mTab, TF_MOrderLine.COLUMNNAME_M_Product_ID);
		int C_BPartner_ID = Env.getContextAsInt(ctx, WindowNo, "C_BPartner_ID");
		int C_UOM_ID = CalloutUtil.getIntValue(mTab, TF_MOrderLine.COLUMNNAME_C_UOM_ID);
		boolean isSOTrx = Env.getContext(ctx, WindowNo, "IsSOTrx").equals("Y");
		Timestamp dateAcct = (Timestamp) Env.getContextAsDate(ctx, WindowNo, "DateAcct");
		
		BigDecimal price = MPriceListUOM.getPrice(ctx, AD_Org_ID, M_Product_ID, C_UOM_ID, C_BPartner_ID, isSOTrx, dateAcct);
		
		mTab.setValue(TF_MOrderLine.COLUMNNAME_PriceEntered, price);
		mTab.setValue(TF_MOrderLine.COLUMNNAME_PriceActual, price);
		mTab.setValue(TF_MOrderLine.COLUMNNAME_PriceList, price);
						
		
		return null;
	}

}
