package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MLocator;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MFuelIssue;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class CalloutInventoryLine_Barcode implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		int M_Warehouse_ID = Env.getContextAsInt(ctx, WindowNo, "M_Warehouse_ID");
		String barcode = CalloutUtil.getString(mTab, "Barcode");
		int M_Product_ID = TF_MProduct.getM_Product_ID(ctx, barcode);
		int M_Product_Category_ID = 0;
		
		if(M_Product_ID > 0) {
			TF_MProduct prod = new TF_MProduct(ctx, M_Product_ID, null);
			M_Product_Category_ID = prod.getM_Product_Category_ID();
		}
		
		String whereClause = "M_Warehouse_ID=? AND M_Locator_ID IN (SELECT m_storeageonhand_v.M_Locator_ID FROM m_storeageonhand_v WHERE M_Product_ID = ? AND QtyOnHand > 0 )";
		MLocator loc = new Query(ctx, MLocator.Table_Name, whereClause, null)
				.setClient_ID()
				.setParameters(M_Warehouse_ID, M_Product_ID)
				.first();
		if(loc != null) {
			mTab.setValue(MFuelIssue.COLUMNNAME_M_Locator_ID, loc.get_ID());
			String sql = "SELECT QtyOnHand FROM m_storeageonhand_v WHERE M_Product_ID = ? AND M_Locator_ID = ? ";
			BigDecimal OnHandQty = DB.getSQLValueBD(null, sql, M_Product_ID, loc.get_ID());
			mTab.setValue(MInventoryLine.COLUMNNAME_QtyBook, OnHandQty);
		}
		
		mTab.setValue(MFuelIssue.COLUMNNAME_M_Product_Category_ID, M_Product_Category_ID > 0 ? M_Product_Category_ID : null);
		mTab.setValue(MFuelIssue.COLUMNNAME_M_Product_ID, M_Product_ID > 0 ? M_Product_ID : null);
		
		
		
		return null;
	}

}
