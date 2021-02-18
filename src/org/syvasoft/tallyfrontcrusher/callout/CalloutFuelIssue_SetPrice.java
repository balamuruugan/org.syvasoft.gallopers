package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MSysConfig;
import org.syvasoft.tallyfrontcrusher.model.MFuelIssue;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;
import org.syvasoft.tallyfrontcrusher.model.TF_MProductCategory;

public class CalloutFuelIssue_SetPrice implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		String issueType = (String) mTab.getValue(MFuelIssue.COLUMNNAME_IssueType);
		if(issueType.equals(MFuelIssue.ISSUETYPE_Payment)) {
			mTab.setValue(MFuelIssue.COLUMNNAME_Rate, BigDecimal.ZERO);
			mTab.setValue(MFuelIssue.COLUMNNAME_Account_ID, null);
			return null;
		}
		
		int AD_Org_ID = (int) mTab.getValue(MFuelIssue.COLUMNNAME_AD_Org_ID);
		int M_Product_ID = 0;
		if(mTab.getValue(MFuelIssue.COLUMNNAME_M_Product_ID) != null)
			M_Product_ID = (int) mTab.getValue(MFuelIssue.COLUMNNAME_M_Product_ID);
		else
			return null;
		BigDecimal currentCost = TF_MProduct.getCurrentCost(AD_Org_ID, M_Product_ID);
		if(MSysConfig.getBooleanValue("SET_MATERIAL_ISSUE_PRICE", false))
			mTab.setValue(MFuelIssue.COLUMNNAME_Rate, currentCost);
		
		int Product_Category_ID = 0;
		TF_MProduct prod=new TF_MProduct(ctx, M_Product_ID, null);
		Product_Category_ID=prod.getM_Product_Category_ID();
		TF_MProductCategory prodc=new TF_MProductCategory(ctx, Product_Category_ID, null);
		if(prodc!=null) {
			mTab.setValue(MFuelIssue.COLUMNNAME_Account_ID, 
					prodc.getSpareExpensesAcct_ID() > 0 ? prodc.getSpareExpensesAcct_ID() : null);
		}
				
		Timestamp dateAcct = (Timestamp) mTab.getValue(MFuelIssue.COLUMNNAME_DateAcct);
		int Vehicle_ID = 0;
		if(mTab.getValue(MFuelIssue.COLUMNNAME_Vehicle_ID) != null)
			Vehicle_ID = (int) mTab.getValue(MFuelIssue.COLUMNNAME_Vehicle_ID);
		
		//Set Previous Issue Meter		
		if(prod.IsIssuedMeterRequired()) {
			BigDecimal prevIssuedMeter = MFuelIssue.getPreviousMeter(ctx, AD_Org_ID, dateAcct, Vehicle_ID, M_Product_ID);
			mTab.setValue(MFuelIssue.COLUMNNAME_PrevIssueMeter, prevIssuedMeter);
		}
		
		return null;
	}

}
