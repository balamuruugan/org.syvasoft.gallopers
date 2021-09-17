package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MSysConfig;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;

public class CalloutWeighmentEntry_CalcAmount implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		BigDecimal qty = (BigDecimal) mTab.getValue(MWeighmentEntry.COLUMNNAME_NetWeightUnit);
		BigDecimal price = (BigDecimal) mTab.getValue(MWeighmentEntry.COLUMNNAME_Price);
		BigDecimal passqty = (BigDecimal) mTab.getValue(MWeighmentEntry.COLUMNNAME_PermitIssuedQty);
		BigDecimal passprice = (BigDecimal) mTab.getValue(MWeighmentEntry.COLUMNNAME_PassPricePerUnit);
		BigDecimal freightprice  = (BigDecimal) mTab.getValue(MWeighmentEntry.COLUMNNAME_FreightPrice);
		
		int passID = 0;
		int freight_uom_id = 0; 
		
		if(mTab.getValue(MWeighmentEntry.COLUMNNAME_FreightRule_ID) != null) {
			freight_uom_id = (int) mTab.getValue(MWeighmentEntry.COLUMNNAME_FreightRule_ID);
		}
		
		if(mTab.getValue(MWeighmentEntry.COLUMNNAME_M_Product_Pass_ID) != null) {
			passID  = (int) mTab.getValue(MWeighmentEntry.COLUMNNAME_M_Product_Pass_ID);
		}
		
		BigDecimal Amount = qty.multiply(price);
		BigDecimal PassAmount = BigDecimal.ZERO;
		
		if(passqty != null && passprice != null) {
			PassAmount = passqty.multiply(passprice);
		}
		
		BigDecimal GrandTotalAmt = BigDecimal.ZERO;
		BigDecimal RentAmount = BigDecimal.ZERO;// CalloutUtil.getBDValue(mTab, MWeighmentEntry.COLUMNNAME_Rent_Amt);
		
		if(passID > 0) {
			PassAmount = passqty.multiply(passprice);
		}
		
		if(freight_uom_id > 0) {
			int KM_UOM_ID = MSysConfig.getIntValue("KM_UOM", 1000071, Env.getAD_Client_ID(ctx));
			int MT_KM_UOM_ID = MSysConfig.getIntValue("MT_KM_UOM", 1000093, Env.getAD_Client_ID(ctx));
			int MT_UOM_ID = MSysConfig.getIntValue("MT_KM_UOM", 1000069, Env.getAD_Client_ID(ctx));
			int LOAD_UOM_ID = MSysConfig.getIntValue("MT_KM_UOM", 1000072, Env.getAD_Client_ID(ctx));
			
			if(freight_uom_id == LOAD_UOM_ID) {
				RentAmount = (BigDecimal) freightprice;
			}	
			else if(freight_uom_id == MT_UOM_ID) {
				RentAmount = (BigDecimal) freightprice.multiply(qty);
			}
		}
		
		BigDecimal GstAmt = BigDecimal.ZERO;		
		
		BigDecimal TCSAmt = BigDecimal.ZERO;
		
		BigDecimal driverTips = BigDecimal.ZERO;		
		if(driverTips != null) {
			driverTips	= (BigDecimal) mTab.getValue(MWeighmentEntry.COLUMNNAME_DriverTips);
		}
		
		BigDecimal discountAmt = BigDecimal.ZERO;
		if(discountAmt != null) {
			discountAmt = (BigDecimal) mTab.getValue(MWeighmentEntry.COLUMNNAME_DiscountAmount);
		}
		
		Boolean ApplyTax = mTab.getValueAsBoolean(MWeighmentEntry.COLUMNNAME_IsPermitSales);
		Boolean ApplyTCS = mTab.getValueAsBoolean(MWeighmentEntry.COLUMNNAME_ApplyTCS);
		//if(ApplyTax)
		GstAmt = (PassAmount.add(Amount)).multiply(new BigDecimal(0.05)).setScale(2, RoundingMode.HALF_EVEN);
		/*else
			GstAmt = BigDecimal.ZERO;
		*/
		BigDecimal TotalAmount = Amount.add(GstAmt).add(RentAmount).add(PassAmount);
		
		if(ApplyTCS) {
			TCSAmt = TotalAmount.multiply(new BigDecimal(0.001)).setScale(2, RoundingMode.HALF_EVEN);
		}
			
		GrandTotalAmt = TotalAmount.add(TCSAmt).subtract(driverTips).subtract(discountAmt);
		
		mTab.setValue(MWeighmentEntry.COLUMNNAME_Rent_Amt, RentAmount);
		mTab.setValue(MWeighmentEntry.COLUMNNAME_GSTAmount, GstAmt);		
		mTab.setValue(MWeighmentEntry.COLUMNNAME_Amount, Amount);
		mTab.setValue(MWeighmentEntry.COLUMNNAME_PermitPassAmount, PassAmount);
		mTab.setValue(MWeighmentEntry.COLUMNNAME_TotalAmt, GrandTotalAmt);
		return null;
	}

}
