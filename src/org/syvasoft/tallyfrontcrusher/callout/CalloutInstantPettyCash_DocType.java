package org.syvasoft.tallyfrontcrusher.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MDocType;
import org.syvasoft.tallyfrontcrusher.model.MInstantPettyCash;

public class CalloutInstantPettyCash_DocType implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		int C_DocType_ID = CalloutUtil.getIntValue(mTab, MInstantPettyCash.COLUMNNAME_C_DocType_ID);
		MDocType dt = new MDocType(ctx, C_DocType_ID, null);
		mTab.setValue(MInstantPettyCash.COLUMNNAME_IsReceipt, dt.isSOTrx()  ? "Y" : "N");
		return null;
	}

}
