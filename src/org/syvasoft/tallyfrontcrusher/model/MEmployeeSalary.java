package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MJournal;
import org.compiere.model.MJournalLine;
import org.compiere.model.MPeriod;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.Env;
import org.compiere.model.Query;
import org.compiere.process.DocAction;

public class MEmployeeSalary extends X_TF_EmployeeSalary {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1657054354220279252L;

	public MEmployeeSalary(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MEmployeeSalary(Properties ctx, int TF_Employee_Salary_ID,
			String trxName) {
		super(ctx, TF_Employee_Salary_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		//
		if(newRecord){
			String whereClause = "C_Period_ID = ? AND EmployeeType = ?";
			List<MEmployeeSalary> empSalary =  new Query(getCtx(),MEmployeeSalary.Table_Name, whereClause, get_TrxName()).setClient_ID()
					.setParameters(getC_Period_ID(), getEmployeeType()).list();
			
			if(empSalary.size() > 0) {
				throw new AdempiereUserError("Record already exists!");
			}
		}
		else {
			String whereClause = "C_Period_ID = ? AND EmployeeType = ? AND TF_EmployeeSalary_ID <> ?";
			List<MEmployeeSalary> empSalary =  new Query(getCtx(),MEmployeeSalary.Table_Name, whereClause, get_TrxName()).setClient_ID()
					.setParameters(getC_Period_ID(), getEmployeeType(), getTF_EmployeeSalary_ID()).list();
			
			if(empSalary.size() > 0) {
				throw new AdempiereUserError("Record already exists!");
			}
		}
		return super.beforeSave(newRecord);
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		caluclateEmployeeSalary();
		return super.afterSave(newRecord, success);
	}
	public void processIt(String DocAction) {
		if(MBoulderReceipt.DOCACTION_Prepare.equals(DocAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(MBoulderReceipt.DOCACTION_Complete.equals(DocAction)) {
			updateprocessedEmployeeDetail(true);
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
			//createJournalEntries();
		}
	}
	public void reverseIt() {
		/*if(getGL_Journal_ID()>0) {
			MJournal j = new MJournal(getCtx(), getGL_Journal_ID(), get_TrxName());
			j.reverseCorrectIt();
			j.saveEx();
			
			setGL_Journal_ID(0);			
		}*/
		updateprocessedEmployeeDetail(false);
		setProcessed(false);
		setDocStatus(DOCSTATUS_Drafted);
	}
	
	public void createEmployeeList() {
		String whereClause = "IsActive = 'Y' AND IsEmployee = 'Y' AND EmployeeType = ?";
		List<TF_MBPartner> bpList = new Query(getCtx(), TF_MBPartner.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getEmployeeType()).setOrderBy(TF_MBPartner.COLUMNNAME_C_BPartner_ID).list();
		
		int sno = 1;
		for(TF_MBPartner bpartner : bpList) {
			MEmployeeSalaryDet.createEmployeeSalaryDetail(getCtx(), get_TrxName(), this, bpartner, sno);
			sno++;
		}
	}
	
	public void caluclateEmployeeSalary() {
		String whereClause = "TF_EmployeeSalary_ID = ?";
		List<MEmployeeSalaryDet> empSalaryList = new Query(getCtx(), MEmployeeSalaryDet.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getTF_EmployeeSalary_ID()).setOrderBy(MEmployeeSalaryDet.COLUMNNAME_TF_EmployeeSalary_Det_ID).list();
		
		
		for(MEmployeeSalaryDet empSalaryDet : empSalaryList) {
			MEmployeeSalaryDet.calculateSalary(getCtx(), get_TrxName(), this, empSalaryDet);
		}
	}
	
	public void updateprocessedEmployeeDetail(boolean isprocessed) {
		String whereClause = "TF_EmployeeSalary_ID = ?";
		List<MEmployeeSalaryDet> empSalaryList = new Query(getCtx(), MEmployeeSalaryDet.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getTF_EmployeeSalary_ID()).setOrderBy(MEmployeeSalaryDet.COLUMNNAME_TF_EmployeeSalary_Det_ID).list();
		
		
		for(MEmployeeSalaryDet empSalaryDet : empSalaryList) {
			empSalaryDet.setProcessed(isprocessed);
			empSalaryDet.saveEx();
		}
	}
	
	public void createJournalEntries() {
		String whereClause = "TF_EmployeeSalary_ID = ?";
		List<MEmployeeSalaryDet> empSalaryList = new Query(getCtx(), MEmployeeSalaryDet.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getTF_EmployeeSalary_ID()).setOrderBy(MEmployeeSalaryDet.COLUMNNAME_TF_EmployeeSalary_Det_ID).list();
		
		
		for(MEmployeeSalaryDet empSalaryDet : empSalaryList) {
			MJournal j = new MJournal(getCtx(), 0, get_TrxName());
			j.setAD_Org_ID(getAD_Org_ID());
			String description = getDocumentNo();
			if(empSalaryDet.getDescription() != null)
				description = empSalaryDet.getDescription() + " | " + description;
				
			j.setDescription(description);
			j.setC_AcctSchema_ID(Env.getContextAsInt(getCtx(), "$C_AcctSchema_ID"));
			j.setC_Currency_ID(Env.getContextAsInt(getCtx(), "$C_Currency_ID"));
			j.setPostingType(MJournal.POSTINGTYPE_Actual);
			j.setC_DocType_ID(1000000);
			j.setDateDoc(getDateAcct());
			j.setDateAcct(getDateAcct());
			j.setDocStatus(DOCSTATUS_Drafted);
			MPeriod period = MPeriod.get(getCtx(), getDateAcct());
			j.setC_Period_ID(period.getC_Period_ID());
			j.setGL_Category_ID(1000000);
			j.setC_ConversionType_ID(114);
			j.saveEx();
			
			int line = 0;
			MJournalLine jl = null;
			//Salaries Expense Dr
			if(empSalaryDet.getNetSalary().doubleValue() != 0) {
				jl = new MJournalLine(j);
				line = line + 10;
				jl.setLine(line);			
				jl.setAccount_ID(MGLPostingConfig.getMGLPostingConfig(getCtx()).getSalariesExpenseAcct());
				jl.setC_BPartner_ID(empSalaryDet.getC_BPartner_ID());
				//jl.setC_Project_ID(getC_Project_ID());
				//jl.setUser1_ID(getC_ElementValue_ID()); // Quarry Profit Center
				jl.setAmtSourceDr(empSalaryDet.getNetSalary());
				jl.setAmtAcctDr(empSalaryDet.getNetSalary());
				jl.setIsGenerated(true);
				jl.saveEx();
			}
			
			//Incentive Expense Dr
			/*if(getIncentive().doubleValue() != 0) {
				jl = new MJournalLine(j);
				line = line + 10;
				jl.setLine(line);			
				jl.setAccount_ID(MGLPostingConfig.getMGLPostingConfig(getCtx()).getIncentiveAcct_ID());
				jl.setC_BPartner_ID(getC_BPartner_ID());
				jl.setC_Project_ID(getC_Project_ID());
				jl.setUser1_ID(getC_ElementValue_ID()); // Quarry Profit Center
				jl.setAmtSourceDr(getIncentive());
				jl.setAmtAcctDr(getIncentive());
				jl.setIsGenerated(true);
				jl.saveEx();
			}
			*/
			
			//Salary Payable Cr.
			jl = new MJournalLine(j);
			line = line + 10;
			jl.setLine(line);			
			jl.setAccount_ID(MGLPostingConfig.getMGLPostingConfig(getCtx()).getSalaryPayable_Acct());
			jl.setC_BPartner_ID(empSalaryDet.getC_BPartner_ID());
			//jl.setC_Project_ID(getC_Project_ID());
			//jl.setUser1_ID(getC_ElementValue_ID()); // Quarry Profit Center
			jl.setAmtSourceCr(empSalaryDet.getNetSalary());
			jl.setAmtAcctCr(empSalaryDet.getNetSalary());
			jl.setIsGenerated(true);
			jl.saveEx();
			
			j.processIt(MJournal.ACTION_Complete);
			j.saveEx();
			
			//setGL_Journal_ID(j.getGL_Journal_ID());
		
		}
	}
}
