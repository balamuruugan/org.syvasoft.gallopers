package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MSysConfig;
import org.compiere.model.Query;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlan;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlanLine;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class CalloutDispensePlanLine_SetOrderInfo implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		
		int C_OrderLine_ID = CalloutUtil.getIntValue(mTab, MDispensePlanLine.COLUMNNAME_C_OrderLine_ID);
		//boolean isTaxIncluded = (boolean)mTab.getValue(TF_MOrderLine.COLUMNNAME_IsTaxIncluded);
		
		if(C_OrderLine_ID > 0) {
			TF_MOrderLine orderline = new TF_MOrderLine(ctx, C_OrderLine_ID, null);
			
			if(orderline != null) {				
				String sql = " C_OrderLine_ID = "+ orderline.getC_OrderLine_ID();
				
				MDispensePlanLine prevdispenseLine = new Query(ctx, MDispensePlanLine.Table_Name, sql, null).first();
				
				BigDecimal balanceQty = orderline.getQtyOrdered().subtract(orderline.getQtyDelivered());
				
				TF_MOrder order = new TF_MOrder(ctx, orderline.getC_Order_ID(), null);
				
				mTab.setValue(MDispensePlanLine.COLUMNNAME_Priority, MDispensePlanLine.PRIORITY_Normal);
				mTab.setValue(MDispensePlanLine.COLUMNNAME_Type, MDispensePlanLine.TYPE_Order);
				mTab.setValue(MDispensePlanLine.COLUMNNAME_C_OrderLine_ID, orderline.getC_OrderLine_ID());
				mTab.setValue(MDispensePlanLine.COLUMNNAME_PaymentRule, order.getPaymentRule());
				mTab.setValue(MDispensePlanLine.COLUMNNAME_C_BPartner_ID, order.getC_BPartner_ID());
				mTab.setValue(MDispensePlanLine.COLUMNNAME_IsPriceConfidential, order.get_ValueAsBoolean(MDispensePlanLine.COLUMNNAME_IsPriceConfidential));
				mTab.setValue(MDispensePlanLine.COLUMNNAME_DateOrdered, orderline.getDateOrdered());
				mTab.setValue(MDispensePlanLine.COLUMNNAME_TF_Destination_ID, orderline.getTF_Destination_ID());
				mTab.setValue(MDispensePlanLine.COLUMNNAME_Line, 10);
				mTab.setValue(MDispensePlanLine.COLUMNNAME_M_Product_ID, orderline.getM_Product_ID());
				mTab.setValue(MDispensePlanLine.COLUMNNAME_M_Warehouse_ID, orderline.getM_Warehouse_ID());
				mTab.setValue(MDispensePlanLine.COLUMNNAME_Description, orderline.getDescription());
				
				mTab.setValue(MDispensePlanLine.COLUMNNAME_ContactPerson, orderline.getContactPerson());
				mTab.setValue(MDispensePlanLine.COLUMNNAME_DeliveryContact, orderline.getDeliveryContact());
				
				mTab.setValue(MDispensePlanLine.COLUMNNAME_DispenseQty, 0);
				mTab.setValue(MDispensePlanLine.COLUMNNAME_C_UOM_ID, orderline.getC_UOM_ID());
				mTab.setValue(MDispensePlanLine.COLUMNNAME_BalanceDPQty, 0);
				mTab.setValue(MDispensePlanLine.COLUMNNAME_DeliveredDPQty, BigDecimal.ZERO);
				
				mTab.setValue(MDispensePlanLine.COLUMNNAME_QtyOrdered, orderline.getQtyOrdered());
				mTab.setValue(MDispensePlanLine.COLUMNNAME_QtyDelivered, orderline.getQtyDelivered());
				mTab.setValue(MDispensePlanLine.COLUMNNAME_BalanceQty, balanceQty);
				
				mTab.setValue(MDispensePlanLine.COLUMNNAME_C_Tax_ID, orderline.getC_Tax_ID());
				mTab.setValue(MDispensePlanLine.COLUMNNAME_IsTaxIncluded, orderline.isTaxIncluded());
				mTab.setValue(MDispensePlanLine.COLUMNNAME_IsRoyaltyPassInclusive, orderline.isRoyaltyPassInclusive());
				mTab.setValue(MDispensePlanLine.COLUMNNAME_IsRentInclusive, orderline.isRentInclusive());
				//mTab.setValue(MDispensePlanLine.COLUMNNAME_IsPriceConfidential, orderline.isPriceConfidential());
				mTab.setValue(MDispensePlanLine.COLUMNNAME_CustomerTransporter, orderline.isCustomerTransporter());
				mTab.setValue(MDispensePlanLine.COLUMNNAME_FreightUOM_ID, orderline.getFreightUOM_ID());
				mTab.setValue(MDispensePlanLine.COLUMNNAME_UnitPrice, orderline.getUnitPrice());
				mTab.setValue(MDispensePlanLine.COLUMNNAME_PriceEntered, orderline.getPriceEntered());
				mTab.setValue(MDispensePlanLine.COLUMNNAME_Discount, orderline.getDiscount());
				mTab.setValue(MDispensePlanLine.COLUMNNAME_FreightAmt, orderline.getFreightAmt());
				//mTab.setValue(MDispensePlanLine.COLUMNNAME_LineNetAmt, orderline.getLineNetAmt());
				
				if(prevdispenseLine != null) {
					mTab.setValue(MDispensePlanLine.COLUMNNAME_OriginDate, prevdispenseLine.getOriginDate());
				}
				else {
					mTab.setValue(MDispensePlanLine.COLUMNNAME_OriginDate, new Timestamp(System.currentTimeMillis()));
				}
			}
		}
		return null;
	}
	
	

}
