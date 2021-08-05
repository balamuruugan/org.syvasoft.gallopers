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
import org.syvasoft.tallyfrontcrusher.model.TF_MRequisition;
import org.syvasoft.tallyfrontcrusher.model.TF_MRequisitionLine;
import org.syvasoft.tallyfrontcrusher.process.GenerateTaxInvoiceLines;

public class CalloutRequisition_SetPriceUOM implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		boolean isSOTrx = Env.getContext(ctx, WindowNo, "IsSOTrx").equals("Y");
		int requisitionId = (int) mTab.getValue(TF_MRequisitionLine.COLUMNNAME_M_Requisition_ID);
		
		TF_MRequisition requistion = new TF_MRequisition(ctx, requisitionId, null);
		
		if(value != null
				&& mTab.getValue(TF_MRequisitionLine.COLUMNNAME_C_UOM_ID) != null) {
			
			int bPartner_ID = 0;
			
			if(mTab.getValue(TF_MRequisitionLine.COLUMNNAME_C_BPartner_ID) != null)
				bPartner_ID =  (int) mTab.getValue(TF_MRequisitionLine.COLUMNNAME_C_BPartner_ID);
			
			int product_ID = (int) mTab.getValue(TF_MRequisitionLine.COLUMNNAME_M_Product_ID);			
						
			int C_UOM_ID = (int) mTab.getValue(TF_MRequisitionLine.COLUMNNAME_C_UOM_ID);
			
			BigDecimal qty = (BigDecimal) mTab.getValue(TF_MRequisitionLine.COLUMNNAME_Qty);
			
			MPriceListUOM priceUOM = MPriceListUOM.getPriceListUOM(ctx, product_ID, C_UOM_ID, bPartner_ID,0, isSOTrx, requistion.getDateRequired());
			
			if(priceUOM != null){
				BigDecimal price = priceUOM.getPrice();
				if(price == null)
					price = BigDecimal.ZERO;
				
				mTab.setValue(TF_MRequisitionLine.COLUMNNAME_PriceActual, price);
				
				if(qty != null)
					mTab.setValue(TF_MRequisitionLine.COLUMNNAME_LineNetAmt, price.multiply(qty).divide(BigDecimal.ONE, 2, RoundingMode.HALF_UP));
			}
			else{
				mTab.setValue(TF_MRequisitionLine.COLUMNNAME_PriceActual, BigDecimal.ZERO);
				mTab.setValue(TF_MRequisitionLine.COLUMNNAME_LineNetAmt, BigDecimal.ZERO);
			}
		}
		return null;
	}

	
}
