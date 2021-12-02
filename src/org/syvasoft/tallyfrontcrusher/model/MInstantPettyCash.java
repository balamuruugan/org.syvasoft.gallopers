package org.syvasoft.tallyfrontcrusher.model;


import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MJournal;
import org.compiere.model.MJournalLine;
import org.compiere.model.MPayment;
import org.compiere.model.MPeriod;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.Env;

public class MInstantPettyCash extends X_TF_InstantPettyCash {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5832491475146926547L;

	public MInstantPettyCash(Properties ctx, int TF_InstantPettyCash_ID, String trxName) {
		super(ctx, TF_InstantPettyCash_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MInstantPettyCash(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public void processIt(String docAction) {
		if(DocAction.ACTION_Prepare.equals(docAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(DocAction.ACTION_Complete.equals(docAction)) {
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
			
			if(isReceipt()) {
				createReceipt();
			}
			else {
				createPayment();
			}
		}
	}
	
	public void reverseIt() {
		setProcessed(false);
		setDocStatus(DOCSTATUS_InProgress);
		
		if(getC_Payment_ID() > 0) {
			MPayment p = new MPayment(getCtx(), getC_Payment_ID(), get_TrxName());		
			p.setDocumentNo(getDocumentNo() + "-" + p.get_ID());
			p.saveEx();
			if(p.getDocStatus().equals(DOCSTATUS_Completed)){
				 p.reverseCorrectIt();
				 p.saveEx();
			}
			setC_Payment_ID(0);
		}
		
		if(getGL_Journal_ID() > 0) {		
			MJournal j = new MJournal(getCtx(), getGL_Journal_ID(), get_TrxName());
			if(j.getDocStatus().equals(TF_MInvoice.DOCSTATUS_Completed)) {
				if (!j.processIt(DocAction.ACTION_Reverse_Correct))
					throw new AdempiereException("Failed when processing document - " + j.getProcessMsg());
				j.saveEx();
			}			
			setGL_Journal_ID(0);
		}
		
		MMachineryStatement.deleteInstantPettyCash(getCtx(), getTF_InstantPettyCash_ID(), get_TrxName());
	}
	
	private void createPayment() {
		if(isReceipt())
			return;
		createCashBookEntry();		
	}
	
	
	private void createReceipt() {
		if(!isReceipt())
			return;
		
		if(getPayAmt().doubleValue() != 0) {
			createCashBookEntry();
		}
		
		 List<MInstantPettyCashLine> lines = getLines();
		 
		 if(lines.size() > 0) {
			 
			//Posting GL journal for Employee Salary 
			MJournal j = new MJournal(getCtx(), 0, get_TrxName());
			j.setAD_Org_ID(getAD_Org_ID());
			
			String description = null;
			if(getDescription() != null)
				description = "Instant Petty Cash :" + getDocumentNo()  +" | " + getDescription();
				
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
			for(MInstantPettyCashLine l : lines) {
				if(l.getC_BPartner_ID() > 0)
					throw new AdempiereException("Vendor Payment is not implemented yet!");
				
				jl = new MJournalLine(j);
				line = line + 10;
				jl.setLine(line);			
				jl.setAccount_ID(l.getC_ElementValue_ID());
				jl.setC_BPartner_ID(getC_BPartner_ID());
				jl.setAmtSourceDr(l.getPayAmt());
				jl.setAmtAcctDr(l.getPayAmt());				
				jl.setIsGenerated(true);
				String lineDesc = (l.getPM_Machinery_ID() > 0 ? "For " + l.getPM_Machinery().getMachineryNo() : "");
				jl.setDescription(lineDesc);
				jl.saveEx();
				
				jl = new MJournalLine(j);
				line = line + 10;
				jl.setLine(line);			
				jl.setAccount_ID(getC_ElementValue_ID());
				jl.setC_BPartner_ID(getC_BPartner_ID());
				jl.setAmtSourceCr(l.getPayAmt());
				jl.setAmtAcctCr(l.getPayAmt());				
				jl.setIsGenerated(true);
				jl.setDescription(lineDesc);
				jl.saveEx();
				
				
				
				//Create machinery statement
				if(l.getPM_Machinery_ID() > 0) {
					MMachineryStatement mStatement=new MMachineryStatement(getCtx(), 0, get_TrxName());
					mStatement.setAD_Org_ID(getAD_Org_ID());
					mStatement.setDateAcct(getDateAcct());
					mStatement.setPM_Machinery_ID(l.getPM_Machinery_ID());					
					mStatement.setC_ElementValue_ID(l.getC_ElementValue_ID());					
					mStatement.setExpense(l.getPayAmt());					
					mStatement.setDescription(l.getDescription());
					mStatement.setTF_InstantPettyCash_ID(getTF_InstantPettyCash_ID());
					mStatement.saveEx();
				}
			}
			
			 j.processIt(MJournal.ACTION_Complete);
			 j.saveEx();
				
			 setGL_Journal_ID(j.getGL_Journal_ID());
		 }
		 
		
	}
	
	private void createCashBookEntry() {
		TF_MPayment payment = new TF_MPayment(getCtx(), 0, get_TrxName());
		payment.setDocumentNo(getDocumentNo());
		payment.setAD_Org_ID(getAD_Org_ID());
		payment.setDateAcct(getDateAcct());
		payment.setDateTrx(getDateAcct());
		payment.setDescription(getDescription());		
		payment.setC_DocType_ID(getC_DocType_ID());
		TF_MCharge charge = TF_MCharge.createChargeFromAccount(getCtx(), getC_ElementValue_ID(), null);
		payment.setC_Charge_ID(charge.getC_Charge_ID());
		payment.setC_ElementValue_ID(getC_ElementValue_ID());
		payment.setTenderType(getTenderType());
		payment.setC_BankAccount_ID(getC_BankAccount_ID());		
		payment.setC_BPartner_ID(getC_BPartner_ID());
		payment.setPayAmt(getPayAmt());		
		payment.setC_Currency_ID(Env.getContextAsInt(getCtx(), "$C_Currency_ID"));
		payment.setDocStatus(TF_MOrder.DOCSTATUS_InProgress);		
		payment.saveEx();
		payment.processIt(DocAction.ACTION_Complete);
		payment.saveEx();
		
		setC_Payment_ID(payment.getC_Payment_ID());
	}
	
	public List<MInstantPettyCashLine> getLines() {
		String whereClause = "TF_InstantPettyCash_ID = ? ";
		List<MInstantPettyCashLine> lines = new Query(getCtx(), MInstantPettyCashLine.Table_Name, whereClause, get_TrxName()) 
				.setClient_ID()
				.setParameters(getTF_InstantPettyCash_ID())
				.setOrderBy("Line")
				.list();
		return lines;
	}
	
}
