package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;

public class MTripSheetAddionalMeter extends X_TF_TripSheet_AM {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1682098138635452964L;

	public MTripSheetAddionalMeter(Properties ctx, int TF_TripSheet_AM_ID, String trxName) {
		super(ctx, TF_TripSheet_AM_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MTripSheetAddionalMeter(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	
	public void processIt() {
		setProcessed(true);

		postMachineryExpenses();
		postMeterLog();
	}
	
	public void reverseIt() {
		setProcessed(false);
	}
	
	private void postMachineryExpenses() {
		if(getAmount() == null && getAmount().doubleValue() == 0)
			return;
		
		MMachineryType mt = (MMachineryType) getPM_Machinery().getPM_MachineryType();
		if(mt.getC_ElementValueExpenses_ID() ==0)
			throw new AdempiereException("Please specify Machinery Expense Account in the Machinery Type: " + mt.getName());
				
		MMachineryStatement st = new MMachineryStatement(getCtx(), 0, get_TrxName());
		st = new MMachineryStatement(getCtx(), 0, get_TrxName());
		st.setAD_Org_ID(getAD_Org_ID());
		st.setDateAcct(getDateReport());
		st.setPM_Machinery_ID(getPM_Machinery_ID());
		st.setC_ElementValue_ID(mt.getC_ElementValueExpenses_ID());
		st.setDescription(getDescription());
		BigDecimal amount = getAmount();
		st.setExpense(amount);
		st.setTF_TripSheet_ID(getTF_TripSheet_ID());		
		st.saveEx();
	}
	
	private void postMeterLog() {
		//Create Meter log if the opening meter and closing meter entered
		if(getClosing_Meter().doubleValue() > 0 ) {
			MMeterLog mLog = new MMeterLog(getCtx(), 0, get_TrxName());
			mLog.setAD_Org_ID(getAD_Org_ID());
			mLog.setDateReport(getDateReport());
			mLog.setShift(getShift());
			mLog.setPM_Machinery_ID(getPM_Machinery_ID());
			mLog.setOpening_Meter(getOpening_Meter());
			mLog.setClosing_Meter(getClosing_Meter());
			mLog.setRunning_Meter(getRunning_Meter());
			mLog.setTF_TripSheet_ID(getTF_TripSheet_ID());
			mLog.setC_UOM_ID(getC_UOM_ID());
			mLog.setProcessed(true);
			mLog.saveEx();
		}
	}
	
}
