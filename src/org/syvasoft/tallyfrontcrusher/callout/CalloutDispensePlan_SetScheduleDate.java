package org.syvasoft.tallyfrontcrusher.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MSysConfig;
import org.compiere.model.Query;
import org.compiere.util.Env;
import java.util.List;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlan;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlanLine;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class CalloutDispensePlan_SetScheduleDate implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		
		String whereClause = "";
		String ID = mTab.get_ValueAsString(MDispensePlan.COLUMNNAME_TF_DispensePlan_ID);
		if(ID == null) {
			whereClause = " trunc(ScheduleDate) = '" + mTab.get_ValueAsString(MDispensePlan.COLUMNNAME_ScheduleDate) + "'";
		}
		else {
			whereClause = " trunc(ScheduleDate) = '" + mTab.get_ValueAsString(MDispensePlan.COLUMNNAME_ScheduleDate) + "' AND TF_DispensePlan_ID != '" + ID + "'";
		}
		
		List<MDispensePlan> dispensePlan = new Query(Env.getCtx(), MDispensePlan.Table_Name, whereClause, null).list();
		
		Env.setContext(Env.getCtx(), WindowNo, "#DispensePlanCount", dispensePlan.size());	
		mTab.setValue("Counter", Env.getContext(Env.getCtx(), WindowNo, "#DispensePlanCount"));
		
		//Env.setContext(Env.getCtx(), "#DPDate", mTab.get_ValueAsString(MDispensePlan.COLUMNNAME_ScheduleDate));	
		return null;
	}
	
	

}
