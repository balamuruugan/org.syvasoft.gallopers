package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MTripSheetProduct extends X_TF_TripSheetProduct {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5205753000759138132L;

	public MTripSheetProduct(Properties ctx, int TF_TripSheetProduct_ID, String trxName) {
		super(ctx, TF_TripSheetProduct_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MTripSheetProduct(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if(getTF_TripSheet_ID() > 0) {
			MTripSheet ts = (MTripSheet) getTF_TripSheet();
			ts.updateIncentiveQty();
			ts.saveEx();
		}
		return super.afterSave(newRecord, success);
	}


}
