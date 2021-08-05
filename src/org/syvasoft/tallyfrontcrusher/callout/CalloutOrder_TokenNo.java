package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MPriceList;
import org.compiere.model.MProductPricing;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTab;
import org.compiere.model.MUser;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MDriverBetaConfig;
import org.syvasoft.tallyfrontcrusher.model.MLumpSumRentConfig;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.MRentedVehicle;
import org.syvasoft.tallyfrontcrusher.model.MToken;
import org.syvasoft.tallyfrontcrusher.model.MVehicleRentConfig;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;

public class CalloutOrder_TokenNo implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		boolean isSOTrx = Env.getContext(ctx, WindowNo, "IsSOTrx").equals("Y");
		
		if(mTab.getValue(TF_MOrder.COLUMNNAME_TF_Token_ID) != null) {
			int token_id = (int) mTab.getValue(TF_MOrder.COLUMNNAME_TF_Token_ID);
			MToken token = new MToken(ctx, token_id , null);
			mTab.setValue(TF_MOrder.COLUMNNAME_DateOrdered, token.getDateAcct());
			mTab.setValue(TF_MOrder.COLUMNNAME_PartyName, token.getPartyName());
			
			int bPartner_id = token.getC_BPartner_ID();
			mTab.setValue(TF_MOrder.COLUMNNAME_C_BPartner_ID, bPartner_id);
			mTab.setValue(TF_MOrder.COLUMNNAME_Bill_BPartner_ID, bPartner_id);
			MBPartnerLocation[] loc = MBPartnerLocation.getForBPartner(ctx, bPartner_id, null);
			MUser[] users = MUser.getOfBPartner(ctx, bPartner_id,null);
			if(users.length > 0) {
				mTab.setValue(TF_MOrder.COLUMNNAME_AD_User_ID, users[0].getAD_User_ID());
				mTab.setValue(TF_MOrder.COLUMNNAME_Bill_User_ID, users[0].getAD_User_ID());
			}
			if(loc.length > 0) {
				mTab.setValue(TF_MOrder.COLUMNNAME_C_BPartner_Location_ID, loc[0].getC_BPartner_Location_ID());
				mTab.setValue(TF_MOrder.COLUMNNAME_Bill_Location_ID, loc[0].getC_BPartner_Location_ID());
			}
			MBPartner bp = new MBPartner(ctx, bPartner_id, null);
			int priceList_id = Env.getContextAsInt(ctx, "#M_PriceList_ID");
			if(bp.getPO_PriceList_ID() > 0 && !isSOTrx) {
				priceList_id = bp.getPO_PriceList_ID();
			}
			else if( bp.getM_PriceList_ID() > 0 && isSOTrx) {
				priceList_id = bp.getM_PriceList_ID();
			}
			
			mTab.setValue(TF_MOrder.COLUMNNAME_M_PriceList_ID, priceList_id);
			MPriceList pl = new MPriceList(ctx, priceList_id, null);
			mTab.setValue(TF_MOrder.COLUMNNAME_C_Currency_ID, pl.getC_Currency_ID());

			int uom_id = token.getM_Product().getC_UOM_ID();
			if(token.getC_UOM_ID() > 0) {
				uom_id = token.getC_UOM_ID();
			}
			int tonnage_uom_id = MSysConfig.getIntValue("TONNAGE_UOM", 1000069, Env.getAD_Client_ID(ctx));
			
			mTab.setValue(TF_MOrder.COLUMNNAME_Item1_ID, token.getM_Product_ID());
			mTab.setValue(TF_MOrder.COLUMNNAME_Item1_Tax_ID, Env.getContext(ctx, "#C_Tax_ID"));
			mTab.setValue(TF_MOrder.COLUMNNAME_Item1_UOM_ID, uom_id);
			
			BigDecimal qty = token.getNetWeightUnit();
			
			if(uom_id == tonnage_uom_id) {
				qty = qty.divide(new BigDecimal(1000));
			}			
			
			if(token.getNetWeightUnit() != null && token.getNetWeightUnit().doubleValue() > 0) {
				qty = token.getNetWeightUnit();
			}				
			
			MProductPricing pp = TF_MOrder.getProductPricing(token.getM_Product_ID(), priceList_id, bPartner_id,
					qty, token.getDateAcct(), isSOTrx);
			BigDecimal price = pp.getPriceStd();
			
			price = MPriceListUOM.getPrice(ctx, token.getM_Product_ID(), uom_id,
					token.getC_BPartner_ID(), token.getTF_Destination_ID(), isSOTrx, token.getDateAcct());
			
			if(pp.getPriceList() != null && pp.getPriceList().doubleValue() > 0)
				price = pp.getPriceList();
			
			mTab.setValue(TF_MOrder.COLUMNNAME_Item1_Qty, qty);
			mTab.setValue(TF_MOrder.COLUMNNAME_TF_Destination_ID, token.getTF_Destination_ID());
			MRentedVehicle rv;
			if(token.getTF_RentedVehicle_ID() > 0) {
				
				rv = new MRentedVehicle(ctx,token.getTF_RentedVehicle_ID(),null);
				mTab.setValue(TF_MOrder.COLUMNNAME_Item1_VehicleType_ID, rv.getTF_VehicleType_ID());
				if(rv.isTransporter() || rv.isOwnVehicle()) {					
					mTab.setValue(TF_MOrder.COLUMNNAME_TF_RentedVehicle_ID, token.getTF_RentedVehicle_ID());
					mTab.setValue(TF_MOrder.COLUMNNAME_VehicleNo, token.getTF_RentedVehicle().getVehicleNo());
				}
				else {					
					mTab.setValue(TF_MOrder.COLUMNNAME_VehicleNo, token.getTF_RentedVehicle().getVehicleNo());					
					mTab.setValue(TF_MOrder.COLUMNNAME_TF_RentedVehicle_ID, null);
				}
					
			}
			else {
				mTab.setValue(TF_MOrder.COLUMNNAME_TF_RentedVehicle_ID, null);
				mTab.setValue(TF_MOrder.COLUMNNAME_VehicleNo, token.getVehicleNo());
			}
			
			
		}
		return null;
	}

}
