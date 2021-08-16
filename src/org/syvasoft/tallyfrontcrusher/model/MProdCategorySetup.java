package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MLocator;
import org.compiere.model.Query;

public class MProdCategorySetup extends X_TF_ProdCategorySetup {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3917225098856854170L;

	public MProdCategorySetup(Properties ctx, int TF_ProdCategorySetup_ID, String trxName) {
		super(ctx, TF_ProdCategorySetup_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MProdCategorySetup(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static MLocator getM_Locator(Properties ctx, int AD_Org_ID, int M_Product_ID) {
		TF_MProduct p = new TF_MProduct(ctx, M_Product_ID, null);
		int M_Product_Category_ID = p.getM_Product_Category_ID();
		String whereClause = "AD_Org_ID = ? AND M_Product_Category_ID = ?";
		MProdCategorySetup catSetup = new Query(ctx, Table_Name, whereClause, null)
				.setClient_ID()
				.setParameters(AD_Org_ID, M_Product_Category_ID)
				.setOrderBy("IsDefault DESC")
				.first();
		if(catSetup != null) {
			MLocator l = (MLocator) catSetup.getM_Locator();
			return l;
		}
		else
			return null;
	}
	
}
