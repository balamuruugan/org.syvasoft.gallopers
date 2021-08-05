package org.syvasoft.tallyfrontcrusher.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MFuelIssue;

public class CalloutFuelIssue_IssueType implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		mTab.setValue(MFuelIssue.COLUMNNAME_Vehicle_ID, null);
		mTab.setValue(MFuelIssue.COLUMNNAME_C_Project_ID, null);
		mTab.setValue(MFuelIssue.COLUMNNAME_M_Product_ID, null);
		mTab.setValue(MFuelIssue.COLUMNNAME_C_ElementValue_ID, null);	
		mTab.setValue(MFuelIssue.COLUMNNAME_C_BPartner_ID, null);
		return null;
	}	
}
