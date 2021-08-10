package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MProduct;
import org.compiere.model.MProductCategory;
import org.compiere.model.MProductPricing;
import org.compiere.model.M_Registration;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.MRentedVehicle;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class CalloutOrder_PriceIncludesTax implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {		
		if(value != null){
			boolean isSOTrx = Env.getContext(ctx, WindowNo, "IsSOTrx").equals("Y");
			if(isSOTrx) {
				int bPartner_ID = 0;
				int product_ID = 0;
				int vehicle_ID = 0;
				boolean isTaxIncluded = false;
				if(mTab.getValue(TF_MOrder.COLUMNNAME_C_BPartner_ID) != null)
					bPartner_ID	= (int) mTab.getValue(TF_MOrder.COLUMNNAME_C_BPartner_ID);
				
				if(mTab.getValue(TF_MOrder.COLUMNNAME_Item1_ID) != null)
					product_ID = (int) mTab.getValue(TF_MOrder.COLUMNNAME_Item1_ID);
				
				if(mTab.getValue(TF_MOrder.COLUMNNAME_IsTaxIncluded1) != null)
					isTaxIncluded = (boolean) mTab.getValue(TF_MOrder.COLUMNNAME_IsTaxIncluded1);
				
				if(mTab.getValue(TF_MOrder.COLUMNNAME_TF_RentedVehicle_ID) != null)
					vehicle_ID = (int) mTab.getValue(TF_MOrder.COLUMNNAME_TF_RentedVehicle_ID);
				
				TF_MBPartner bp = new TF_MBPartner(ctx, bPartner_ID, null);
				TF_MProduct product = new TF_MProduct(ctx, product_ID, null);
				
				int tax_ID = product.getTax_ID(isTaxIncluded, bp.isInterState());
				
				if(mField.getColumnName().equals(TF_MOrder.COLUMNNAME_C_BPartner_ID) ||
						mField.getColumnName().equals(TF_MOrder.COLUMNNAME_Item1_ID) || 
						mField.getColumnName().equals(TF_MOrder.COLUMNNAME_IsTaxIncluded1)) {
						mTab.setValue(TF_MOrder.COLUMNNAME_Item1_Tax_ID, tax_ID);
				}
				
				if(vehicle_ID > 0) {
					MRentedVehicle rentedVehicle = new MRentedVehicle(ctx, vehicle_ID, null);
					
					if(rentedVehicle != null){
						TF_MProduct vehicle = new TF_MProduct(ctx, rentedVehicle.getM_Product_ID(), null);
						int vehicle_tax_ID = vehicle.getTax_ID(isTaxIncluded, bp.isInterState());
						
						mTab.setValue(TF_MOrder.COLUMNNAME_Rent_Tax_ID, vehicle_tax_ID);
					}
				}
			}
		}
		return null;
	}
}
