package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MInOutLine;
import org.compiere.model.MSysConfig;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.TF_MInOut;
import org.syvasoft.tallyfrontcrusher.model.TF_MInOutLine;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.MDestination;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlanLine;
import org.syvasoft.tallyfrontcrusher.model.MLumpSumRentConfig;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class CalloutInOutLine_SetVehicleRentConfig implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		
		if(mTab.getValue(TF_MInOutLine.COLUMNNAME_TF_Destination_ID) != null && 
				mTab.getValue(TF_MInOutLine.COLUMNNAME_C_UOM_ID) != null) {
			int TF_Destination_ID = (int)mTab.getValue(TF_MInOutLine.COLUMNNAME_TF_Destination_ID);
			int C_UOM_ID = (int)mTab.getValue(TF_MInOutLine.COLUMNNAME_C_UOM_ID);
			int M_InOut_ID = (int)mTab.getValue(TF_MInOutLine.COLUMNNAME_M_InOut_ID);		
			
			TF_MInOut minout = new TF_MInOut(Env.getCtx(), M_InOut_ID, null);
			MWeighmentEntry we = new MWeighmentEntry(Env.getCtx(), minout.getTF_WeighmentEntry_ID(), null);
			MDestination dest = new MDestination(Env.getCtx(), TF_Destination_ID, null);
			
			int Load_UOM_ID = MSysConfig.getIntValue("LOAD_UOM", 1000072, minout.getAD_Client_ID());
			int KM_UOM_ID = MSysConfig.getIntValue("KM_UOM", 1000071, minout.getAD_Client_ID());
			int MT_KM_UOM_ID = MSysConfig.getIntValue("MT_KM_UOM", 1000071, minout.getAD_Client_ID());
			int TF_LumpSumRentConfig_ID = 0;
			
			BigDecimal distance = dest.getDistance();
			
			BigDecimal qty = BigDecimal.ZERO;
			BigDecimal price = BigDecimal.ZERO;
			BigDecimal RateMTKM = BigDecimal.ZERO;
			
			if(we != null) {
				MLumpSumRentConfig lumpsumConfig = MLumpSumRentConfig.getFreightPrice(Env.getCtx(), we.getAD_Org_ID(), minout.getC_BPartner_ID(), 
						we.getC_BPartner_ID(), we.getM_Product_ID(), TF_Destination_ID, we.getTF_VehicleType_ID(), dest.getDistance(), C_UOM_ID, null);		
				
				
				if(lumpsumConfig != null) {
					//ioLine.set_ValueOfColumn("FreightRule", we.getFreightRule());
					price = lumpsumConfig.getFreightPrice();
					
					if(we.getFreightRule_ID() == Load_UOM_ID)
					{
						qty = BigDecimal.ONE;							
					}
					else if(we.getFreightRule_ID() == KM_UOM_ID)
					{
						qty = dest.getDistance();							
					}
					else if(we.getFreightRule_ID() == MT_KM_UOM_ID)
					{
						qty = we.getMT();							
						RateMTKM =  price;
					}
					else
					{
						qty = we.getNetWeightUnit();							
					}
					
					TF_LumpSumRentConfig_ID = lumpsumConfig.getTF_LumpSumRent_Config_ID();
				}
				else {
					qty = BigDecimal.ONE;			
					price = BigDecimal.ZERO;
				}
					
				mTab.setValue(TF_MInOutLine.COLUMNNAME_Distance, distance);
				mTab.setValue(TF_MInOutLine.COLUMNNAME_Price, price);
				mTab.setValue(TF_MInOutLine.COLUMNNAME_QtyEntered, qty);
				mTab.setValue(TF_MInOutLine.COLUMNNAME_TF_LumpSumRent_Config_ID, TF_LumpSumRentConfig_ID);
			}
		}
		return null;
	}
	
	

}
