package org.syvasoft.tallyfrontcrusher.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MMachinery;
import org.syvasoft.tallyfrontcrusher.model.MTripSheet;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class CalloutTripSheet_SetActivity implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		int PM_Machinery_ID = CalloutUtil.getIntValue(mTab, MTripSheet.COLUMNNAME_PM_Machinery_ID);
		int Jobwork_ID = CalloutUtil.getIntValue(mTab, MTripSheet.COLUMNNAME_JobWork_Product_ID);
		
		int C_Activity_ID = 0;
		MMachinery m = new MMachinery(ctx, PM_Machinery_ID, null);
		C_Activity_ID = m.getC_Activity_ID();
		
		if(Jobwork_ID > 0) {
			TF_MProduct p = new TF_MProduct(ctx, Jobwork_ID, null);
			if(p.getC_Activity_ID() > 0)
				C_Activity_ID= p.getC_Activity_ID();
		}
		
		mTab.setValue(MTripSheet.COLUMNNAME_C_Activity_ID, C_Activity_ID);
		
		return null;
	}

}
