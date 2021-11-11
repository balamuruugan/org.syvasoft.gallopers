package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.process.DocAction;

public class MTripSheetSalary extends X_TF_TripSheet_Salary {

	/**
	 * 
	 */
	private static final long serialVersionUID = 923382587544621282L;

	public MTripSheetSalary(Properties ctx, int TF_TripSheet_Salary_ID, String trxName) {
		super(ctx, TF_TripSheet_Salary_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MTripSheetSalary(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		calculateIncentive();
		return super.beforeSave(newRecord);
	}
	
	private void calculateIncentive() {
		if(getUnitIncentive().doubleValue() > 0) {
			setIncentive(getUnitIncentive().multiply(getDrillingQty()));
		}
		else {
			setIncentive(getDayIncentive());
		}
	}
	
	public void processIt() {
		BigDecimal totalWage = getEarned_Wage().add(getIncentive());
		MTripSheet ts = (MTripSheet) getTF_TripSheet();
		if(totalWage.doubleValue() != 0 && getC_BPartner_ID() > 0){
			MEmployeeSalaryOld salary = new MEmployeeSalaryOld(getCtx(), 0, get_TrxName());
			
			salary.setAD_Org_ID(getAD_Org_ID());
			salary.setDateAcct(ts.getDateReport());
			salary.setPresent_Days(BigDecimal.ONE);
			salary.setC_BPartner_ID(getC_BPartner_ID());				
			salary.setSalary_Amt(getEarned_Wage());
			salary.setIncentive(getIncentive());
			salary.setDescription("TripSheet No: " + ts.getDocumentNo());
			salary.setDocStatus(MEmployeeSalaryOld.DOCSTATUS_Drafted);
			salary.setUser1_ID(ts.getUser1_ID());
			salary.setTF_TripSheet_ID(getTF_TripSheet_ID());
			salary.saveEx();
						
			
			salary.processIt(DocAction.ACTION_Complete);
			salary.saveEx();
			
			//operator salary expenses
			MGLPostingConfig glConfig = MGLPostingConfig.getMGLPostingConfig(getCtx());
			MMachineryStatement st = new MMachineryStatement(getCtx(), 0, get_TrxName());
			st = new MMachineryStatement(getCtx(), 0, get_TrxName());
			st.setAD_Org_ID(getAD_Org_ID());
			st.setDateAcct(ts.getDateReport());
			st.setPM_Machinery_ID(ts.getPM_Machinery_ID());
			st.setC_ElementValue_ID(glConfig.getSalariesExpenseAcct());
			st.setDescription(getC_BPartner().getName());
			st.setC_Activity_ID(ts.getC_Activity_ID());
			st.setExpense(totalWage);
			st.setUser1_ID(ts.getUser1_ID());
			st.setTF_TripSheet_ID(getTF_TripSheet_ID());		
			st.saveEx();
			
		}
	}
	
	public void reverseIt() {
		
	}
}
