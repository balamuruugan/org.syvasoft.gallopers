package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MProduct;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;

import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;

public class CalloutOrderQuickEntry_SetPriceUOM implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		boolean isSOTrx = Env.getContext(ctx, WindowNo, "IsSOTrx").equals("Y");
		Timestamp dateAcct = (Timestamp) mTab.getValue(TF_MOrder.COLUMNNAME_DateAcct);
		int TF_Destination_ID = CalloutUtil.getIntValue(mTab, TF_MOrder.COLUMNNAME_TF_Destination_ID);
		
		if(value != null
				&& mTab.getValue(TF_MOrder.COLUMNNAME_C_BPartner_ID) != null
				&& mTab.getValue(TF_MOrder.COLUMNNAME_Item1_UOM_ID) != null
				&& mTab.getValue(TF_MOrder.COLUMNNAME_DateAcct) != null
				&& mTab.getValue(TF_MOrder.COLUMNNAME_M_PriceList_ID) != null) {
			int bPartner_ID = (int) mTab.getValue(TF_MOrder.COLUMNNAME_C_BPartner_ID);			
			int product_ID = (int) mTab.getValue(TF_MOrder.COLUMNNAME_Item1_ID);			
						
			int C_UOM_ID = (int) mTab.getValue(TF_MOrder.COLUMNNAME_Item1_UOM_ID);
			
			BigDecimal qty = (BigDecimal) mTab.getValue(TF_MOrder.COLUMNNAME_Item1_Qty);
			
			boolean isRentInclusive = false;
			boolean isTaxIncluded = false;
			
			
			MPriceListUOM priceUOM = MPriceListUOM.getPriceListUOM(ctx, product_ID, C_UOM_ID, bPartner_ID,TF_Destination_ID, isSOTrx, dateAcct);
			
			if(priceUOM != null){
				BigDecimal price = priceUOM.getPrice();
				if(price == null)
					price = BigDecimal.ZERO;
				mTab.setValue(TF_MOrder.COLUMNNAME_Item1_Price, price);
				mTab.setValue(TF_MOrder.COLUMNNAME_Item1_UnitPrice, price);
				if(qty != null)
					mTab.setValue(TF_MOrder.COLUMNNAME_Item1_Amt, price.multiply(qty).divide(BigDecimal.ONE, 2, RoundingMode.HALF_UP));
				
				isRentInclusive = priceUOM.isRentInclusive();
				isTaxIncluded = priceUOM.isTaxIncluded();
				
				//mTab.setValue(TF_MOrder.COLUMNNAME_IsTaxIncluded1, isTaxIncluded);
				//For Namakkal bluemetals, default amount includes tax, but tax amount will be known after generating sales tax invoice
				mTab.setValue(TF_MOrder.COLUMNNAME_IsTaxIncluded1, false);
								
				if(!isSOTrx) {
					mTab.setValue(TF_MOrder.COLUMNNAME_CreateTransporterInvoice, !isRentInclusive);
					mTab.setValue(TF_MOrder.COLUMNNAME_IsRentInclusive, false);
				}
				else {
					mTab.setValue(TF_MOrder.COLUMNNAME_IsRentInclusive, isRentInclusive);
				}
			}
			else{
				mTab.setValue(TF_MOrder.COLUMNNAME_Item1_Price, BigDecimal.ZERO);
				mTab.setValue(TF_MOrder.COLUMNNAME_Item1_UnitPrice, BigDecimal.ZERO);
				mTab.setValue(TF_MOrder.COLUMNNAME_Item1_Amt, BigDecimal.ZERO);
				
				mTab.setValue(TF_MOrder.COLUMNNAME_IsRentInclusive, false);
				mTab.setValue(TF_MOrder.COLUMNNAME_IsTaxIncluded1, false);
			}
		}
		
		if(value != null
				&& mTab.getValue(TF_MOrder.COLUMNNAME_C_BPartner_ID) != null
				&& mTab.getValue(TF_MOrder.COLUMNNAME_Item2_UOM_ID) != null
				&& mTab.getValue(TF_MOrder.COLUMNNAME_DateAcct) != null
				&& mTab.getValue(TF_MOrder.COLUMNNAME_M_PriceList_ID) != null) {
			int bPartner_ID = (int) mTab.getValue(TF_MOrder.COLUMNNAME_C_BPartner_ID);			
			int product_ID = (int) mTab.getValue(TF_MOrder.COLUMNNAME_Item2_ID);
					
			BigDecimal qty = (BigDecimal) mTab.getValue(TF_MOrder.COLUMNNAME_Item2_Qty);
			int C_UOM_ID = (int) mTab.getValue(TF_MOrder.COLUMNNAME_Item2_UOM_ID);
			MPriceListUOM priceUOM = MPriceListUOM.getPriceListUOM(ctx, product_ID, C_UOM_ID, bPartner_ID, TF_Destination_ID, isSOTrx, dateAcct);
			
			if(priceUOM != null){
				BigDecimal price = priceUOM.getPrice();
				if(price == null)
					price = BigDecimal.ZERO;
				mTab.setValue(TF_MOrder.COLUMNNAME_Item2_Price, price);
				mTab.setValue(TF_MOrder.COLUMNNAME_Item2_Amt, price.multiply(qty).divide(BigDecimal.ONE, 2, RoundingMode.HALF_EVEN));		
				
				MProduct prod = MProduct.get(ctx, product_ID);
				mTab.setValue(TF_MOrder.COLUMNNAME_Item2_UOM_ID, prod.getC_UOM_ID());
				int defaultTaxID = Env.getContextAsInt(ctx, "#C_Tax_ID");
				mTab.setValue(TF_MOrder.COLUMNNAME_Item2_Tax_ID, defaultTaxID);
			}
			else{
				mTab.setValue(TF_MOrder.COLUMNNAME_Item2_Price, BigDecimal.ZERO);
				mTab.setValue(TF_MOrder.COLUMNNAME_Item2_Amt, BigDecimal.ZERO);
			}
		}
		return null;
	}

	
}
