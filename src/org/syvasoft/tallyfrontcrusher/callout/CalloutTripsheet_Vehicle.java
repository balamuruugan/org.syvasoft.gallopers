package org.syvasoft.tallyfrontcrusher.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MFuelIssue;
import org.syvasoft.tallyfrontcrusher.model.MJobworkAssignedVehicle;
import org.syvasoft.tallyfrontcrusher.model.MMachinery;
import org.syvasoft.tallyfrontcrusher.model.MTripSheet;

public class CalloutTripsheet_Vehicle implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		int Vehicle_ID = 0;
		int User1_ID = 0;
		int PM_Machinery_ID = 0;
		if(mTab.getValue(MTripSheet.COLUMNNAME_PM_Machinery_ID) != null) 
			PM_Machinery_ID = (int) mTab.getValue(MFuelIssue.COLUMNNAME_PM_Machinery_ID);
		
		if(PM_Machinery_ID > 0) {
			MMachinery m = new MMachinery(ctx, PM_Machinery_ID, null);
			Vehicle_ID = m.getM_Product_ID();
			User1_ID = m.getUser1_ID();
		}
		mTab.setValue(MFuelIssue.COLUMNNAME_Vehicle_ID, Vehicle_ID > 0 ? Vehicle_ID : null);
		
		int AD_Org_ID = (int) mTab.getValue(MFuelIssue.COLUMNNAME_AD_Org_ID);
		
		MJobworkAssignedVehicle jwVehicle = MJobworkAssignedVehicle.getJobwork(AD_Org_ID, Vehicle_ID);		
		if(jwVehicle != null) {
			mTab.setValue(MFuelIssue.COLUMNNAME_C_Project_ID, jwVehicle.getC_Project_ID());
		}
		else {
			mTab.setValue(MFuelIssue.COLUMNNAME_C_Project_ID, null);
		}

		mTab.setValue(MTripSheet.COLUMNNAME_User1_ID, User1_ID > 0 ? User1_ID : null);
		
		return null;
	}

}
