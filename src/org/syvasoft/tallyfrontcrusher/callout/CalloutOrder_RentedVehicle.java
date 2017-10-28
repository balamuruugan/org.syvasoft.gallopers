package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MRentedVehicle;
import org.syvasoft.tallyfrontcrusher.model.MVehicleRentConfig;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;

public class CalloutOrder_RentedVehicle implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		BigDecimal rate = BigDecimal.ZERO;
		if(mTab.getValue(TF_MOrder.COLUMNNAME_TF_RentedVehicle_ID) != null && 
				mTab.getValue(TF_MOrder.COLUMNNAME_TF_Destination_ID) != null) {
			int vehicle_id = (int) mTab.getValue(TF_MOrder.COLUMNNAME_TF_RentedVehicle_ID);
			int destination_id = (int) mTab.getValue(TF_MOrder.COLUMNNAME_TF_Destination_ID);
			rate = MVehicleRentConfig.getRate(vehicle_id, destination_id);
		}
		mTab.setValue(TF_MOrder.COLUMNNAME_Rate, rate);
		return null;
	}

}