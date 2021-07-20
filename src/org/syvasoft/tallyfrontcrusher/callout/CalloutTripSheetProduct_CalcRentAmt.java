package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MMachineryRentConfig;
import org.syvasoft.tallyfrontcrusher.model.MTripSheet;
import org.syvasoft.tallyfrontcrusher.model.MTripSheetProduct;

public class CalloutTripSheetProduct_CalcRentAmt implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		int TF_TripSheet_ID = CalloutUtil.getIntValue(mTab, MTripSheetProduct.COLUMNNAME_TF_TripSheet_ID);
		int M_Product_ID = CalloutUtil.getIntValue(mTab, MTripSheetProduct.COLUMNNAME_M_Product_ID);
		MTripSheet ts = new MTripSheet(ctx, TF_TripSheet_ID, null);
		int PM_Machinery_ID = ts.getPM_Machinery_ID();
		int C_UOM_ID = ts.getRent_UOM_ID();
		BigDecimal TotalMT = CalloutUtil.getBDValue(mTab, MTripSheetProduct.COLUMNNAME_TotalMT);
		
		MMachineryRentConfig rconfig = MMachineryRentConfig.getExpense(ctx, PM_Machinery_ID, M_Product_ID, C_UOM_ID);
		BigDecimal Rate = CalloutUtil.getBDValue(mTab, MTripSheetProduct.COLUMNNAME_RateMT);
		
		if(rconfig != null && mField.getColumnName().equals(MTripSheetProduct.COLUMNNAME_M_Product_ID))  {
			Rate = rconfig.getUnitRent();
			mTab.setValue(MTripSheetProduct.COLUMNNAME_RateMT, Rate);
			mTab.setValue(MTripSheetProduct.COLUMNNAME_Description, rconfig.getDescription());
		}
		
		BigDecimal rentAmount = TotalMT.multiply(Rate); 
		
		mTab.setValue(MTripSheetProduct.COLUMNNAME_Rent_Amt, rentAmount);
		return null;
	}

}
