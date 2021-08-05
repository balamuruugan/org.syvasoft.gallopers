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
import org.syvasoft.tallyfrontcrusher.model.MVehicleRentConfig;
import org.syvasoft.tallyfrontcrusher.model.MVehicleType;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;

public class CalloutOrder_WeighmentEntry implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		boolean isSOTrx = Env.getContext(ctx, WindowNo, "IsSOTrx").equals("Y");
		
		if(mTab.getValue(TF_MOrder.COLUMNNAME_TF_WeighmentEntry_ID) != null) {
			int weighment_id = (int) mTab.getValue(TF_MOrder.COLUMNNAME_TF_WeighmentEntry_ID);
			MWeighmentEntry weighment = new MWeighmentEntry(ctx, weighment_id, null);
			mTab.setValue(TF_MOrder.COLUMNNAME_DateOrdered, weighment.getGrossWeightTime());
			mTab.setValue(TF_MOrder.COLUMNNAME_DateAcct, weighment.getGrossWeightTime());
			int bPartner_id = weighment.getC_BPartner_ID();
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
			
			int uom_id = weighment.getM_Product().getC_UOM_ID();
			if(weighment.getC_UOM_ID() > 0) {
				uom_id = weighment.getC_UOM_ID();
			}
			int tonnage_uom_id = MSysConfig.getIntValue("TONNAGE_UOM", 1000069, Env.getAD_Client_ID(ctx));
			
			
			if(weighment.getM_Warehouse_ID() > 0)
				mTab.setValue(TF_MOrder.COLUMNNAME_M_Warehouse_ID, weighment.getM_Warehouse_ID());
			
			mTab.setValue(TF_MOrder.COLUMNNAME_Item1_ID, weighment.getM_Product_ID());
			mTab.setValue(TF_MOrder.COLUMNNAME_Item1_Tax_ID, weighment.getC_Tax_ID()); //Env.getContext(ctx, "#C_Tax_ID"));
			mTab.setValue(TF_MOrder.COLUMNNAME_Item1_UOM_ID, uom_id);
			
			BigDecimal qty = weighment.getNetWeight();
			
			if(uom_id == tonnage_uom_id) {
				qty = qty.divide(new BigDecimal(1000));
			}			
			
			if(weighment.getNetWeightUnit() != null && weighment.getNetWeightUnit().doubleValue() > 0) {
				qty = weighment.getNetWeightUnit();
			}
				
			
			MProductPricing pp = TF_MOrder.getProductPricing(weighment.getM_Product_ID(), priceList_id, bPartner_id,
					qty, weighment.getGrossWeightTime(), isSOTrx);
			BigDecimal price = pp.getPriceStd();
			
			price = MPriceListUOM.getPrice(ctx, weighment.getM_Product_ID(), uom_id,
					weighment.getC_BPartner_ID(),weighment.getTF_Destination_ID(), isSOTrx, weighment.getGrossWeightTime());
			
			if(weighment.getPrice() != null && weighment.getPrice().doubleValue() > 0)
				price = weighment.getPrice();
			
			if(isSOTrx)
				mTab.setValue(TF_MOrder.COLUMNNAME_C_DocTypeTarget_ID, weighment.getC_DocType_ID());
			
			if(isSOTrx && weighment.getPassQtyIssued().doubleValue() != 0) {				
				mTab.setValue(TF_MOrder.COLUMNNAME_Item2_ID, weighment.getM_Product2_ID());
				mTab.setValue(TF_MOrder.COLUMNNAME_Item2_Qty, weighment.getPassQtyIssued());
				mTab.setValue(TF_MOrder.COLUMNNAME_Item2_Price, weighment.getPassPricePerUnit());
				mTab.setValue(TF_MOrder.COLUMNNAME_Item2_Amt, weighment.getPermitPassAmount());
			}
			
			mTab.setValue(TF_MOrder.COLUMNNAME_Item1_Qty, qty);			
			mTab.setValue(TF_MOrder.COLUMNNAME_Item1_Price, price);
			mTab.setValue(TF_MOrder.COLUMNNAME_Item1_UnitPrice, price);
			mTab.setValue(TF_MOrder.COLUMNNAME_Item1_Amt, price.multiply(qty));
			
			
			
			MRentedVehicle rv;
			if(weighment.getTF_RentedVehicle_ID() > 0) {
				
				rv = new MRentedVehicle(ctx,weighment.getTF_RentedVehicle_ID(),null);
				if(rv.isTransporter() || rv.isOwnVehicle())
					mTab.setValue(TF_MOrder.COLUMNNAME_TF_RentedVehicle_ID, weighment.getTF_RentedVehicle_ID());
				else {
					mTab.setValue(TF_MOrder.COLUMNNAME_VehicleNo, weighment.getVehicleNo());
					mTab.setValue(TF_MOrder.COLUMNNAME_TF_RentedVehicle_ID, null);
				}
					
			}
			else
				mTab.setValue(TF_MOrder.COLUMNNAME_TF_RentedVehicle_ID, null);
			
			
			if(weighment.getTF_Destination_ID() > 0) {
				mTab.setValue(TF_MOrder.COLUMNNAME_TF_Destination_ID, weighment.getTF_Destination_ID());
				BigDecimal Distance=(BigDecimal) mTab.getValue(TF_MOrder.COLUMNNAME_Distance);
				/*int TF_RentedVehicle_ID=weighment.getTF_RentedVehicle_ID();
				if(TF_RentedVehicle_ID>0) {
				    BigDecimal lumpsumrent=MLumpSumRentConfig.getLumpSumRent(weighment.getCtx(), Env.getAD_Client_ID(weighment.getCtx()), weighment.getTF_Destination_ID(), weighment.getTF_VehicleType_ID(),Distance, weighment.get_TrxName());
				    mTab.setValue(TF_MOrder.COLUMNNAME_Rent_Amt, lumpsumrent);
				}*/
			}
			
			
			if(weighment.getDescription() != null)
				mTab.setValue(TF_MOrder.COLUMNNAME_Description, weighment.getDescription());
			else
				mTab.setValue(TF_MOrder.COLUMNNAME_Description, null);
			
			if(weighment.getPaymentRule() != null) {
				mTab.setValue(TF_MOrder.COLUMNNAME_PaymentRule, weighment.getPaymentRule());
				boolean isRoyaltyPassInclusive = !weighment.getPaymentRule().equals(MWeighmentEntry.PAYMENTRULE_Cash);
				mTab.setValue(TF_MOrder.COLUMNNAME_IsRoyaltyPassInclusive, false);
				mTab.setValue(TF_MOrder.COLUMNNAME_IsRoyaltyPassBreakup, true);
					
			}
			
			mTab.setValue(TF_MOrder.COLUMNNAME_OnAccount, weighment.isGST());
			
			mTab.setValue(TF_MOrder.COLUMNNAME_VehicleNo, weighment.getVehicleNo());
			
			String description = "";
			if(weighment.getPartyName() != null) { 
				mTab.setValue(TF_MOrder.COLUMNNAME_PartyName, weighment.getPartyName());
				description = "Party Name : " + weighment.getPartyName();
			}
			if(weighment.getPhone() != null) {
				mTab.setValue(TF_MOrder.COLUMNNAME_Phone, weighment.getPhone());
				description = description + ", Phone: " + weighment.getPhone();
			}
			
			mTab.setValue(TF_MOrder.COLUMNNAME_Description, description);
	
			
			String orgType = (String) mTab.getValue(TF_MOrder.COLUMNNAME_OrgType);
			if(orgType != null && orgType.equals(TF_MOrder.ORGTYPE_SandBlockWeighbridge)) {
				mTab.setValue(TF_MOrder.COLUMNNAME_Item1_IsPermitSales, weighment.isHasBalance());
				mTab.setValue(TF_MOrder.COLUMNNAME_Item1_PermitIssued, weighment.getPermitIssuedQty());
				mTab.setValue(TF_MOrder.COLUMNNAME_Item1_TotalLoad, BigDecimal.ONE);
				mTab.setValue(TF_MOrder.COLUMNNAME_MDPNo, weighment.getMDPNo());
				
				if(weighment.getPrice() != null)
					mTab.setValue(TF_MOrder.COLUMNNAME_Item1_Price, weighment.getPrice());				
				
				if(weighment.isHasBalance())
					mTab.setValue(TF_MOrder.COLUMNNAME_Item1_SandType, TF_MOrder.ITEM1_SANDTYPE_PermitSand);
				else
					mTab.setValue(TF_MOrder.COLUMNNAME_Item1_SandType, TF_MOrder.ITEM1_SANDTYPE_WithoutPermit);
					
				mTab.setValue(TF_MOrder.COLUMNNAME_Item1_BucketQty, null);
			}
			//TODO: need to add customer name from weighment entry into description
			//This will be required only for sand blocks.
			
					
			if(weighment.getTF_VehicleType_ID() > 0)
			{
				mTab.setValue(TF_MOrder.COLUMNNAME_Item1_VehicleType_ID, weighment.getTF_VehicleType_ID());
			
				
				// * this code is already in CalloutOrder_VehicleType hence it is commented here.
				if(weighment.getVehicleNo()!="" && isSOTrx)
				{
					BigDecimal betaAmt= MDriverBetaConfig.getDriverBetaAmount(ctx, Env.getAD_Org_ID(ctx),weighment.getTF_VehicleType_ID(), null);
					mTab.setValue(TF_MOrder.COLUMNNAME_DriverTips, betaAmt);
				}
				else
				{
					mTab.setValue(TF_MOrder.COLUMNNAME_DriverTips, BigDecimal.ZERO);
				}
				
			}
			
			int BoulderID = MSysConfig.getIntValue("BOULDER_ID", 1000233, weighment.getAD_Client_ID(), weighment.getAD_Org_ID());

			if(!isSOTrx && weighment.getM_Product_ID()==BoulderID) {
				mTab.setValue(TF_MOrder.COLUMNNAME_TF_Send_To, weighment.getTF_Send_To());
				mTab.setValue(TF_MOrder.COLUMNNAME_TF_BlueMetal_Type, weighment.getTF_BlueMetal_Type());
				mTab.setValue(TF_MOrder.COLUMNNAME_TF_ProductionPlant_ID, weighment.getTF_ProductionPlant_ID());
			}
			
			if(weighment.getDiscountAmount().doubleValue()>0 && weighment.getPaymentRule().equals(MWeighmentEntry.PAYMENTRULE_Cash)) {
				mTab.setValue(TF_MOrder.COLUMNNAME_SalesDiscountAmt, weighment.getDiscountAmount());
			}
			
			if(weighment.getDriverTips().doubleValue()>0) {
				mTab.setValue(TF_MOrder.COLUMNNAME_DriverTips, weighment.getDriverTips());
			}
		}
		return null;
	}

}
