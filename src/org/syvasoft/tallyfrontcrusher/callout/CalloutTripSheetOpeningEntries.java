package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.event.IEventTopics;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MSysConfig;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MTripSheet;

public class CalloutTripSheetOpeningEntries implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {		
		if(value == null) {
			mTab.setValue(MTripSheet.COLUMNNAME_Opening_Meter, BigDecimal.ZERO);
			mTab.setValue(MTripSheet.COLUMNNAME_Opening_Fuel, BigDecimal.ZERO);
			return null;
		}
			
		Timestamp dateReport = (Timestamp) mTab.getValue(MTripSheet.COLUMNNAME_DateReport);
		int vehicle_ID = (int) mTab.getValue(MTripSheet.COLUMNNAME_Vehicle_ID);
				
			
		
		//Get Opening Meter				
		BigDecimal openingMeter = MTripSheet.getOpeningMeter(vehicle_ID, dateReport);		
		mTab.setValue(MTripSheet.COLUMNNAME_Opening_Meter, openingMeter);
		
		/*
		String dieselIssue = MSysConfig.getValue("TF_DIESEL_ISSUE_FROM_TRIPSHEET", "N");	
		//Get Opening Fuel				
		BigDecimal openingFuel = MTripSheet.getOpeningFuel(vehicle_ID, dateReport);		
		//mTab.setValue(MTripSheet.COLUMNNAME_Opening_Fuel, openingFuel);
		
		//Set Received Fuel
		if(!dieselIssue.equals("Y")) {
			BigDecimal receivedFuel = MTripSheet.getReceivedFuel(vehicle_ID, dateReport);
			//mTab.setValue(MTripSheet.COLUMNNAME_Received_Fuel, receivedFuel);
		}
		*/
		return null;
	}

}
