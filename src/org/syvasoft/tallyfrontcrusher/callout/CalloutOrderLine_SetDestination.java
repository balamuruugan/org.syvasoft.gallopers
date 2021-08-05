package org.syvasoft.tallyfrontcrusher.callout;

import java.util.Properties;
import org.compiere.model.Query;
import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MSysConfig;
import org.syvasoft.tallyfrontcrusher.model.MDestination;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class CalloutOrderLine_SetDestination implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		
		int C_Order_ID = CalloutUtil.getIntValue(mTab, TF_MOrderLine.COLUMNNAME_C_Order_ID);
		//boolean isTaxIncluded = (boolean)mTab.getValue(TF_MOrderLine.COLUMNNAME_IsTaxIncluded);
		
		TF_MOrder order = new TF_MOrder(ctx, C_Order_ID, null);
		
		TF_MBPartner bpartner = new TF_MBPartner(ctx, order.getC_BPartner_ID(), null);
		
		if(bpartner != null) {
			String where = " Name = '" + bpartner.getAddress4() + "'";
			
			MDestination destination = new Query(ctx, MDestination.Table_Name, where, null).first();
			
			if(destination != null) {
				mTab.setValue(TF_MOrderLine.COLUMNNAME_TF_Destination_ID, destination.getTF_Destination_ID());
			}
		}
		 
		return null;
	}
	
	

}
