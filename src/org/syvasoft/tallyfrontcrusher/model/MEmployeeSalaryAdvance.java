package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.process.DocAction;
import org.compiere.util.Env;

public class MEmployeeSalaryAdvance extends X_TF_Employee_Salary_Advance {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5270551286490487235L;
	public MEmployeeSalaryAdvance(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	public MEmployeeSalaryAdvance(Properties ctx,
			int TF_Employee_Salary_Advance_ID, String trxName) {
		super(ctx, TF_Employee_Salary_Advance_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		//If the Employee is created from Quick Entry
		/*if(!getC_BPartner().isEmployee()) {
			MBPartner bp = new MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
			bp.setIsEmployee(true);
			bp.setIsCustomer(false);
			bp.setIsVendor(false);
			bp.saveEx();
		}*/
		return super.beforeSave(newRecord);
	}
	public void processIt(String docAction) {
		if(DocAction.ACTION_Prepare.equals(docAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(DocAction.ACTION_Complete.equals(docAction)) {
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
			
			
			/*MGLPostingConfig glConfig = MGLPostingConfig.getMGLPostingConfig(getCtx());
			
			//Create Salaries Advance Charge if it is not there already.
			//It should be in atomic transaction to get account settings of Charge for the current docaction transaction.
			TF_MCharge charge = TF_MCharge.createChargeFromAccount(getCtx(), glConfig.getSalariesAdvanceAcct_ID(), null);
			
			boolean isAdvancePaid = getAdvance_Amt().doubleValue() != 0;
			
			if(!isAdvancePaid)
				charge = TF_MCharge.createChargeFromAccount(getCtx(), glConfig.getLoan_ID(), null);
			
			//Posting Payment Document for Labour Wage Advance
			TF_MPayment payment = new TF_MPayment(getCtx(), 0, get_TrxName());
			payment.setDateAcct(getDateAcct());
			payment.setDateTrx(getDateAcct());
			payment.setDescription("Generated from Employee Monthly Salary Advance - " + getDocumentNo());
			payment.setCashType(TF_MPayment.CASHTYPE_EmployeePayment);
			payment.setC_DocType_ID(false);
			payment.setC_BPartner_ID(getC_BPartner_ID());
			payment.setC_Charge_ID(charge.getC_Charge_ID());
			payment.setUser1_ID(getC_ElementValue_ID()); // Profit Center
			payment.setC_BankAccount_ID(getC_BankAccount_ID());
			
			if(isAdvancePaid) {
				payment.setC_ElementValue_ID(glConfig.getSalariesAdvanceAcct_ID());
				payment.setPayAmt(getAdvance_Amt());
			}
			else {
				payment.setC_ElementValue_ID(glConfig.getLoan_ID());
				payment.setPayAmt(getLoanAmt());
			}
				
			
			payment.setC_Currency_ID(Env.getContextAsInt(getCtx(), "$C_Currency_ID"));
			payment.setDocStatus(DOCSTATUS_InProgress);
			payment.setTenderType(TF_MPayment.TENDERTYPE_Cash);
			payment.saveEx();
			payment.processIt(DocAction.ACTION_Complete);
			payment.saveEx();
			
			setC_Payment_ID(payment.get_ID());*/
			
		}
	}
	
	public void reverseIt() {
		/*if(getC_Payment_ID()>0) {
			TF_MPayment payment = new TF_MPayment(getCtx(), getC_Payment_ID(), get_TrxName());
			payment.reverseCorrectIt();
			payment.saveEx();
			
			setC_Payment_ID(0);
			setProcessed(false);
			setDocStatus(DOCSTATUS_Drafted);
		}*/
		setProcessed(false);
		setDocStatus(DOCSTATUS_Drafted);
	}
}
