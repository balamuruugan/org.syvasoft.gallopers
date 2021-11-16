package org.syvasoft.tallyfrontcrusher.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MInstantPettyCash;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrg;

public class CalloutInstantPettyCash_Org implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		int AD_Org_ID = CalloutUtil.getIntValue(mTab, MInstantPettyCash.COLUMNNAME_AD_Org_ID);		
		TF_MOrg org = new TF_MOrg(ctx, AD_Org_ID, null);
		mTab.setValue(MInstantPettyCash.COLUMNNAME_C_ElementValue_ID, org.getInstantPettyCashAcct_ID() > 0 ? 
				org.getInstantPettyCashAcct_ID() : null);		
		return null;
	}

}
