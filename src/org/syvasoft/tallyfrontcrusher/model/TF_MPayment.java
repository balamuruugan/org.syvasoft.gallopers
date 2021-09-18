package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MDocType;
import org.compiere.model.MDocTypeCounter;
import org.compiere.model.MJournalLine;
import org.compiere.model.MPayment;
import org.compiere.model.MPeriod;
import org.compiere.model.MSequence;
import org.compiere.model.MTable;
import org.compiere.model.MUser;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class TF_MPayment extends MPayment {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6395097676538568134L;
	public TF_MPayment(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	public TF_MPayment(Properties ctx, int C_Payment_ID, String trxName) {
		super(ctx, C_Payment_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	 /** Column name CashType */
    public static final String COLUMNNAME_CashType = "CashType";
    
    /** General Expense = E */
	public static final String CASHTYPE_GeneralExpense = "E";
	/** General Receipt = R */
	public static final String CASHTYPE_GeneralReceipt = "R";
	/** Vendor Payment = V */
	public static final String CASHTYPE_VendorPayment = "V";
	/** Customer Payment = C */
	public static final String CASHTYPE_CustomerPayment = "C";
	/** Employee Payment = Y */
	public static final String CASHTYPE_EmployeePayment = "Y";
	/** Set Cash Type.
		@param CashType 
		Source of Cash
	  */
	public void setCashType (String CashType)
	{

		set_Value (COLUMNNAME_CashType, CashType);
	}

	/** Get Cash Type.
		@return Source of Cash
	  */
	public String getCashType () 
	{
		return (String)get_Value(COLUMNNAME_CashType);
	}
	
	/** Column name TF_WeighmentEntry_ID */
    public static final String COLUMNNAME_TF_WeighmentEntry_ID = "TF_WeighmentEntry_ID";
    /** Set Weighment Entry.
	@param TF_WeighmentEntry_ID Weighment Entry	  */
	public void setTF_WeighmentEntry_ID (int TF_WeighmentEntry_ID)
	{
		if (TF_WeighmentEntry_ID < 1) 
			set_Value (COLUMNNAME_TF_WeighmentEntry_ID, null);
		else 
			set_Value (COLUMNNAME_TF_WeighmentEntry_ID, Integer.valueOf(TF_WeighmentEntry_ID));
	}
	
	/** Get Weighment Entry.
		@return Weighment Entry	  */
	public int getTF_WeighmentEntry_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_WeighmentEntry_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	
	/** Column name C_ElementValue_ID */
    public static final String COLUMNNAME_C_ElementValue_ID = "C_ElementValue_ID";
    
    public org.compiere.model.I_C_ElementValue getC_ElementValue() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getC_ElementValue_ID(), get_TrxName());	}

	/** Set Account Element.
		@param C_ElementValue_ID 
		Account Element
	  */
	public void setC_ElementValue_ID (int C_ElementValue_ID)
	{
		if (C_ElementValue_ID < 1) 
			set_Value (COLUMNNAME_C_ElementValue_ID, null);
		else 
			set_Value (COLUMNNAME_C_ElementValue_ID, Integer.valueOf(C_ElementValue_ID));
	}

	/** Get Account Element.
		@return Account Element
	  */
	public int getC_ElementValue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ElementValue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name Subcon_Invoice_ID */
    public static final String COLUMNNAME_Subcon_Invoice_ID = "Subcon_Invoice_ID";
	
    public org.compiere.model.I_C_Invoice getSubcon_Invoice() throws RuntimeException
    {
		return (org.compiere.model.I_C_Invoice)MTable.get(getCtx(), org.compiere.model.I_C_Invoice.Table_Name)
			.getPO(getSubcon_Invoice_ID(), get_TrxName());	}

	/** Set Subcontractor Invoice.
		@param Subcon_Invoice_ID Subcontractor Invoice	  */
	public void setSubcon_Invoice_ID (int Subcon_Invoice_ID)
	{
		if (Subcon_Invoice_ID < 1) 
			set_Value (COLUMNNAME_Subcon_Invoice_ID, null);
		else 
			set_Value (COLUMNNAME_Subcon_Invoice_ID, Integer.valueOf(Subcon_Invoice_ID));
	}

	/** Get Subcontractor Invoice.
		@return Subcontractor Invoice	  */
	public int getSubcon_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Subcon_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	
	/** Column name TF_BPartner_ID */
    public static final String COLUMNNAME_TF_BPartner_ID = "TF_BPartner_ID";
    public org.compiere.model.I_C_BPartner getTF_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getTF_BPartner_ID(), get_TrxName());	}

	/** Set Customer / Vendor.
		@param TF_BPartner_ID Customer / Vendor	  */
	public void setTF_BPartner_ID (int TF_BPartner_ID)
	{
		if (TF_BPartner_ID < 1) 
			set_Value (COLUMNNAME_TF_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_TF_BPartner_ID, Integer.valueOf(TF_BPartner_ID));
	}

	/** Get Customer / Vendor.
		@return Customer / Vendor	  */
	public int getTF_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name IsQuickEntry */
    public static final String COLUMNNAME_IsQuickEntry = "IsQuickEntry";
	/** Set Quick Entry.
	@param IsQuickEntry Quick Entry	  */
	public void setIsQuickEntry (boolean IsQuickEntry)
	{
		set_Value (COLUMNNAME_IsQuickEntry, Boolean.valueOf(IsQuickEntry));
	}
	
	/** Get Quick Entry.
		@return Quick Entry	  */
	public boolean isQuickEntry () 
	{
		Object oo = get_Value(COLUMNNAME_IsQuickEntry);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
	
	/** Column name FromTo_BankAccount_ID */
    public static final String COLUMNNAME_FromTo_BankAccount_ID = "FromTo_BankAccount_ID";
	public org.compiere.model.I_C_BankAccount getFromTo_BankAccount() throws RuntimeException
    {
		return (org.compiere.model.I_C_BankAccount)MTable.get(getCtx(), org.compiere.model.I_C_BankAccount.Table_Name)
			.getPO(getFromTo_BankAccount_ID(), get_TrxName());	}

	/** Set From/To Bank/Cash Account .
		@param FromTo_BankAccount_ID From/To Bank/Cash Account 	  */
	public void setFromTo_BankAccount_ID (int FromTo_BankAccount_ID)
	{
		if (FromTo_BankAccount_ID < 1) 
			set_Value (COLUMNNAME_FromTo_BankAccount_ID, null);
		else 
			set_Value (COLUMNNAME_FromTo_BankAccount_ID, Integer.valueOf(FromTo_BankAccount_ID));
	}

	/** Get From/To Bank/Cash Account .
		@return From/To Bank/Cash Account 	  */
	public int getFromTo_BankAccount_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FromTo_BankAccount_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
    
	/** Column name IsInterCashBookEntry */
    public static final String COLUMNNAME_IsInterCashBookEntry = "IsInterCashBookEntry";
    /** Set Inter Cash Book Entry.
     * 
	@param IsInterCashBookEntry Inter Cash Book Entry	  */
	public void setIsInterCashBookEntry (boolean IsInterCashBookEntry)
	{
		set_Value (COLUMNNAME_IsInterCashBookEntry, Boolean.valueOf(IsInterCashBookEntry));
	}
	
	/** Get Inter Cash Book Entry.
		@return Inter Cash Book Entry	  */
	public boolean isInterCashBookEntry () 
	{
		//Object oo = get_Value(COLUMNNAME_IsInterCashBookEntry);
		//if (oo != null) 
		//{
		//	 if (oo instanceof Boolean) 
		//		 return ((Boolean)oo).booleanValue(); 
		//	return "Y".equals(oo);
		//}
		return getFromTo_BankAccount_ID()>0;
	}
    
	/** Column name IsAutocomplete */
    public static final String COLUMNNAME_IsAutocomplete = "IsAutocomplete";
    /** Set Autocomplete.
	@param IsAutocomplete 
	Automatic completion for textfields
	  */
	public void setIsAutocomplete (boolean IsAutocomplete)
	{
		set_Value (COLUMNNAME_IsAutocomplete, Boolean.valueOf(IsAutocomplete));
	}
	
	/** Get Autocomplete.
		@return Automatic completion for textfields
	  */
	public boolean isAutocomplete () 
	{
		Object oo = get_Value(COLUMNNAME_IsAutocomplete);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
	
    /** Column name Salary_Amt */
    public static final String COLUMNNAME_Salary_Amt = "Salary_Amt";
    /** Set Earned Salary.
	@param Salary_Amt Earned Salary	  */
	public void setSalary_Amt (BigDecimal Salary_Amt)
	{
		set_Value (COLUMNNAME_Salary_Amt, Salary_Amt);
	}
	
	/** Get Earned Salary.
		@return Earned Salary	  */
	public BigDecimal getSalary_Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Salary_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
    /** Column name Advance_Paid */
    public static final String COLUMNNAME_Advance_Paid = "Advance_Paid";
    /** Set Advance Paid.
	@param Advance_Paid Advance Paid	  */
	public void setAdvance_Paid (BigDecimal Advance_Paid)
	{
		set_Value (COLUMNNAME_Advance_Paid, Advance_Paid);
	}
	
	/** Get Advance Paid.
		@return Advance Paid	  */
	public BigDecimal getAdvance_Paid () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Advance_Paid);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

    /** Column name Advance_Deduct */
    public static final String COLUMNNAME_Advance_Deduct = "Advance_Deduct";
    
    /** Set Deduct Advance.
	@param Advance_Deduct Deduct Advance	  */
	public void setAdvance_Deduct (BigDecimal Advance_Deduct)
	{
		set_Value (COLUMNNAME_Advance_Deduct, Advance_Deduct);
	}
	
	/** Get Deduct Advance.
		@return Deduct Advance	  */
	public BigDecimal getAdvance_Deduct () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Advance_Deduct);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
	/** Column name Advance_Balance */
    public static final String COLUMNNAME_Advance_Balance = "Advance_Balance";
    /** Set Balance Advance.
	@param Advance_Balance Balance Advance	  */
	public void setAdvance_Balance (BigDecimal Advance_Balance)
	{
		set_Value (COLUMNNAME_Advance_Balance, Advance_Balance);
	}
	
	/** Get Balance Advance.
		@return Balance Advance	  */
	public BigDecimal getAdvance_Balance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Advance_Balance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

    /** Column name Salary_Payable */
    public static final String COLUMNNAME_Salary_Payable = "Salary_Payable";
    /** Set Balance Salary.
	@param Salary_Payable Balance Salary	  */
	public void setSalary_Payable (BigDecimal Salary_Payable)
	{
		set_Value (COLUMNNAME_Salary_Payable, Salary_Payable);
	}
	
	/** Get Balance Salary.
		@return Balance Salary	  */
	public BigDecimal getSalary_Payable () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Salary_Payable);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
	/** Column name EmpAdv_Journal_ID */
    public static final String COLUMNNAME_EmpAdv_Journal_ID = "EmpAdv_Journal_ID";
    public org.compiere.model.I_GL_Journal getEmpAdv_Journal() throws RuntimeException
    {
		return (org.compiere.model.I_GL_Journal)MTable.get(getCtx(), org.compiere.model.I_GL_Journal.Table_Name)
			.getPO(getEmpAdv_Journal_ID(), get_TrxName());	}

	/** Set Advance Deduction Journal.
		@param EmpAdv_Journal_ID 
		Advance Deduction Journal
	  */
	public void setEmpAdv_Journal_ID (int EmpAdv_Journal_ID)
	{
		if (EmpAdv_Journal_ID < 1) 
			set_Value (COLUMNNAME_EmpAdv_Journal_ID, null);
		else 
			set_Value (COLUMNNAME_EmpAdv_Journal_ID, Integer.valueOf(EmpAdv_Journal_ID));
	}

	/** Get Advance Deduction Journal.
		@return Advance Deduction Journal
	  */
	public int getEmpAdv_Journal_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EmpAdv_Journal_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name IsEmployee */
    public static final String COLUMNNAME_IsEmployee = "IsEmployee";
    /** Set Employee.
	@param IsEmployee 
	Indicates if  this Business Partner is an employee
  */
	public void setIsEmployee (boolean IsEmployee)
	{
		set_Value (COLUMNNAME_IsEmployee, Boolean.valueOf(IsEmployee));
	}
	
	/** Get Employee.
		@return Indicates if  this Business Partner is an employee
	  */
	public boolean isEmployee () 
	{
		Object oo = get_Value(COLUMNNAME_IsEmployee);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

    /** Column name IsSalaryPayment */
    public static final String COLUMNNAME_IsSalaryPayment = "IsSalaryPayment";
    /** Set Salary Payment.
	@param IsSalaryPayment Salary Payment	  */
	public void setIsSalaryPayment (boolean IsSalaryPayment)
	{
		set_Value (COLUMNNAME_IsSalaryPayment, Boolean.valueOf(IsSalaryPayment));
	}
	
	/** Get Salary Payment.
		@return Salary Payment	  */
	public boolean isSalaryPayment () 
	{
		Object oo = get_Value(COLUMNNAME_IsSalaryPayment);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
	
	/** Column name IsBankAccount */
    public static final String COLUMNNAME_IsBankAccount = "IsBankAccount";
    /** Set Bank Account.
	@param IsBankAccount 
	Indicates if this is the Bank Account
  */
	public void setIsBankAccount (boolean IsBankAccount)
	{
		set_Value (COLUMNNAME_IsBankAccount, Boolean.valueOf(IsBankAccount));
	}
	
	/** Get Bank Account.
		@return Indicates if this is the Bank Account
	  */
	public boolean isBankAccount () 
	{
		Object oo = get_Value(COLUMNNAME_IsBankAccount);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
	
    /** Column name DateBankTrx */
    public static final String COLUMNNAME_DateBankTrx = "DateBankTrx";
    /** Set Bank Trx Date.
	@param DateBankTrx Bank Trx Date	  */
	public void setDateBankTrx (Timestamp DateBankTrx)
	{
		set_Value (COLUMNNAME_DateBankTrx, DateBankTrx);
	}
	
	/** Get Bank Trx Date.
		@return Bank Trx Date	  */
	public Timestamp getDateBankTrx () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateBankTrx);
	}

	/** Column name IsInterOrgBPCashTransferX */
    public static final String COLUMNNAME_IsInterOrgBPCashTransferX = "IsInterOrgBPCashTransferX";
    /** Set Inter Org BP Cash Transfer X.
	@param IsInterOrgBPCashTransferX Inter Org BP Cash Transfer X	  */
	public void setIsInterOrgBPCashTransferX (boolean IsInterOrgBPCashTransferX)
	{
		set_Value (COLUMNNAME_IsInterOrgBPCashTransferX, Boolean.valueOf(IsInterOrgBPCashTransferX));
	}
	
	/** Get Inter Org BP Cash Transfer X.
		@return Inter Org BP Cash Transfer X	  */
	public boolean isInterOrgBPCashTransferX () 
	{
		Object oo = get_Value(COLUMNNAME_IsInterOrgBPCashTransferX);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
	
	 /** Column name IsInterOrgCashTransferX */
    public static final String COLUMNNAME_IsInterOrgCashTransferX = "IsInterOrgCashTransferX";
    /** Set Inter Org Cash Transfer X.
	@param IsInterOrgCashTransferX Inter Org Cash Transfer X	  */
	public void setIsInterOrgCashTransferX (boolean IsInterOrgCashTransferX)
	{
		set_Value (COLUMNNAME_IsInterOrgCashTransferX, Boolean.valueOf(IsInterOrgCashTransferX));
	}
	
	/** Get Inter Org Cash Transfer X.
		@return Inter Org Cash Transfer X	  */
	public boolean isInterOrgCashTransferX () 
	{
		Object oo = get_Value(COLUMNNAME_IsInterOrgCashTransferX);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Column name Ref2_Payment_ID */
    public static final String COLUMNNAME_Ref2_Payment_ID = "Ref2_Payment_ID";
    public org.compiere.model.I_C_Payment getRef2_Payment() throws RuntimeException
    {
		return (org.compiere.model.I_C_Payment)MTable.get(getCtx(), org.compiere.model.I_C_Payment.Table_Name)
			.getPO(getRef2_Payment_ID(), get_TrxName());	}

	/** Set Additional Cash Transfer.
		@param Ref2_Payment_ID Additional Cash Transfer	  */
	public void setRef2_Payment_ID (int Ref2_Payment_ID)
	{
		if (Ref2_Payment_ID < 1) 
			set_Value (COLUMNNAME_Ref2_Payment_ID, null);
		else 
			set_Value (COLUMNNAME_Ref2_Payment_ID, Integer.valueOf(Ref2_Payment_ID));
	}

	/** Get Additional Cash Transfer.
		@return Additional Cash Transfer	  */
	public int getRef2_Payment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref2_Payment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Column name TF_OrgBPCashTrans_Configx_ID */
    public static final String COLUMNNAME_TF_OrgBPCashTrans_Configx_ID = "TF_OrgBPCashTrans_Configx_ID";
    /** Set Additional BP Cash Transfer in Destination Org.
	@param TF_OrgBPCashTrans_Configx_ID Additional BP Cash Transfer in Destination Org	  */
	public void setTF_OrgBPCashTrans_Configx_ID (int TF_OrgBPCashTrans_Configx_ID)
	{
		if (TF_OrgBPCashTrans_Configx_ID < 1) 
			set_Value (COLUMNNAME_TF_OrgBPCashTrans_Configx_ID, null);
		else 
			set_Value (COLUMNNAME_TF_OrgBPCashTrans_Configx_ID, Integer.valueOf(TF_OrgBPCashTrans_Configx_ID));
	}
	
	/** Get Additional BP Cash Transfer in Destination Org.
		@return Additional BP Cash Transfer in Destination Org	  */
	public int getTF_OrgBPCashTrans_Configx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_OrgBPCashTrans_Configx_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name TF_OrgCashTransfer_Configx_ID */
    public static final String COLUMNNAME_TF_OrgCashTransfer_Configx_ID = "TF_OrgCashTransfer_Configx_ID";
    /** Set Destination Org Additional Cash Transfer.
	@param TF_OrgCashTransfer_Configx_ID Destination Org Additional Cash Transfer	  */
	public void setTF_OrgCashTransfer_Configx_ID (int TF_OrgCashTransfer_Configx_ID)
	{
		if (TF_OrgCashTransfer_Configx_ID < 1) 
			set_Value (COLUMNNAME_TF_OrgCashTransfer_Configx_ID, null);
		else 
			set_Value (COLUMNNAME_TF_OrgCashTransfer_Configx_ID, Integer.valueOf(TF_OrgCashTransfer_Configx_ID));
	}
	
	/** Get Destination Org Additional Cash Transfer.
		@return Destination Org Additional Cash Transfer	  */
	public int getTF_OrgCashTransfer_Configx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_OrgCashTransfer_Configx_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name C_ElementValuePayTo_ID */
    public static final String COLUMNNAME_C_ElementValuePayTo_ID = "C_ElementValuePayTo_ID";
    public org.compiere.model.I_C_ElementValue getC_ElementValuePayTo() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getC_ElementValuePayTo_ID(), get_TrxName());	}

	/** Set Pay to.
		@param C_ElementValuePayTo_ID 
		Additional Cash Book Transfer
	  */
	public void setC_ElementValuePayTo_ID (int C_ElementValuePayTo_ID)
	{
		if (C_ElementValuePayTo_ID < 1) 
			set_Value (COLUMNNAME_C_ElementValuePayTo_ID, null);
		else 
			set_Value (COLUMNNAME_C_ElementValuePayTo_ID, Integer.valueOf(C_ElementValuePayTo_ID));
	}

	/** Get Pay to.
		@return Additional Cash Book Transfer
	  */
	public int getC_ElementValuePayTo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ElementValuePayTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	  /** Column name OnAccount */
    public static final String COLUMNNAME_OnAccount = "OnAccount";
	/** Set On Account.
	@param OnAccount On Account	  */
	public void setOnAccount (boolean OnAccount)
	{
		set_Value (COLUMNNAME_OnAccount, Boolean.valueOf(OnAccount));
	}
	
	/** Get On Account.
		@return On Account	  */
	public boolean isOnAccount () 
	{
		Object oo = get_Value(COLUMNNAME_OnAccount);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
	
	/** Column name DocumentNo2 */
    public static final String COLUMNNAME_DocumentNo2 = "DocumentNo2";
    /** Set Document No 2.
	@param DocumentNo2 Document No 2	  */
	public void setDocumentNo2 (String DocumentNo2)
	{
		set_Value (COLUMNNAME_DocumentNo2, DocumentNo2);
	}
	
	/** Get Document No 2.
		@return Document No 2	  */
	public String getDocumentNo2 () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo2);
	}
	
	/** Column name TF_CashCounter_ID */
    public static final String COLUMNNAME_TF_CashCounter_ID = "TF_CashCounter_ID";
    /** Set Cash Counter.
	@param TF_CashCounter_ID Cash Counter	  */
	public void setTF_CashCounter_ID (int TF_CashCounter_ID)
	{
		if (TF_CashCounter_ID < 1) 
			set_Value (COLUMNNAME_TF_CashCounter_ID, null);
		else 
			set_Value (COLUMNNAME_TF_CashCounter_ID, Integer.valueOf(TF_CashCounter_ID));
	}
	
	/** Get Cash Counter.
		@return Cash Counter	  */
	public int getTF_CashCounter_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_CashCounter_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    
	public static final String  COLUMNNAME_PM_Job_ID = "PM_Job_ID";
	
	public void setPM_Job_ID (int PM_Job_ID)
	{
		if (PM_Job_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PM_Job_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PM_Job_ID, Integer.valueOf(PM_Job_ID));
	}

	public int getPM_Job_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PM_Job_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	 /** Column name PM_Machinery_ID */
    public static final String COLUMNNAME_PM_Machinery_ID = "PM_Machinery_ID";
	/** Set Machinery.
	@param PM_Machinery_ID Machinery	  */
	public void setPM_Machinery_ID (int PM_Machinery_ID)
	{
		if (PM_Machinery_ID < 1) 
			set_Value (COLUMNNAME_PM_Machinery_ID, null);
		else 
			set_Value (COLUMNNAME_PM_Machinery_ID, Integer.valueOf(PM_Machinery_ID));
	}
	
	/** Get Machinery.
		@return Machinery	  */
	public int getPM_Machinery_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PM_Machinery_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
    /** Column name TF_DebitCreditNote_ID */
    public static final String COLUMNNAME_TF_DebitCreditNote_ID = "TF_DebitCreditNote_ID";
	
	/** Set TF_DebitCreditNote.
	@param TF_DebitCreditNote_ID TF_DebitCreditNote	  */
	public void setTF_DebitCreditNote_ID (int TF_DebitCreditNote_ID)
	{
		if (TF_DebitCreditNote_ID < 1) 
			set_Value (COLUMNNAME_TF_DebitCreditNote_ID, null);
		else 
			set_Value (COLUMNNAME_TF_DebitCreditNote_ID, Integer.valueOf(TF_DebitCreditNote_ID));
	}
	
	/** Get TF_DebitCreditNote.
		@return TF_DebitCreditNote	  */
	public int getTF_DebitCreditNote_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_DebitCreditNote_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
		/** Set Amount.
		@param Amount 
		Amount in a defined currency
	  */
	public void setDiscountAmount2 (BigDecimal DiscountAmt2)
	{
		set_Value ("DiscountAmt2", DiscountAmt2);
	}
	
	/** Get Amount.
		@return Amount in a defined currency
	  */
	public BigDecimal getDiscountAmt2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value("DiscountAmt2");
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
		
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		
		boolean ok = super.afterSave(newRecord, success);
		if(isAutocomplete() && (getDocStatus().equals(DOCSTATUS_Drafted) || getDocStatus().equals(DOCSTATUS_InProgress))) {
			if(!processIt(DocAction.ACTION_Complete))
				throw new AdempiereException("Failed when processing document - " + getProcessMsg());			
		}
		return ok;
	}
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(newRecord || is_ValueChanged(COLUMNNAME_C_ElementValue_ID) || 
				(getC_Charge_ID() == 0 && getC_ElementValue_ID() > 0 )
				) {
			//if(getC_ElementValue_ID()>0 ) { 
				TF_MCharge charge = TF_MCharge.createChargeFromAccount(getCtx(), getC_ElementValue_ID(), get_TrxName());
				if(charge != null )
					setC_Charge_ID(charge.get_ID());
			//}
		}
		
		setIsReceipt(getC_DocType().isSOTrx());
		
		if(getDateBankTrx() == null) {
			setIsBankAccount(!getC_BankAccount().getBankAccountType().equals(TF_MBankAccount.BANKACCOUNTTYPE_Cash));
			setDateBankTrx(getDateTrx());
			//if(isBankAccount())
			//	throw new AdempiereException("Bank Trx Date is mandatory!");
		}
		
		if(getC_ElementValue_ID()==0)
			setC_ElementValue_ID(0);
		
		if(getC_ElementValue_ID() > 0 && getTF_BPartner_ID() == 0 && getC_BPartner_ID() == 0) {
			MUser user = MUser.get(getCtx(), Env.getAD_User_ID(getCtx()));				
			int bPartnerID = user.getC_BPartner_ID();
			setC_BPartner_ID(bPartnerID);
		}
		
		if(getC_Invoice_ID() > 0 && isReceipt() != getC_Invoice().isSOTrx())
			throw new AdempiereException("Invalid Invoice for the selected Document Type!");
		
		if(getC_Invoice_ID() > 0 && getC_BPartner_ID() > 0 && getTF_BPartner_ID() ==0)
			setTF_BPartner_ID(getC_BPartner_ID());
		
		setDocumentNo2();	
		
		// Set Default Cash Counter
		if(newRecord && getTF_CashCounter_ID ()==0) {
			String Where=" IsDefault='Y'";

			MCashCounter cashCounter= new Query(getCtx(),MCashCounter.Table_Name , Where, get_TrxName())
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.first();
			if(cashCounter!=null) {
				setTF_CashCounter_ID(cashCounter.getTF_CashCounter_ID());
			}
		}
		
		return super.beforeSave(newRecord);
	}
	
	@Override
	public String completeIt() {
		//Subcontract / Job Work
		
		String msg = super.completeIt();
		createInterCashBookEntry();
		createInterOrgCashBookEntry();
		createInterOrgBPCashBookEntry();
		createPayToCashBookEntry();
		if(isEmployee())
			postAdvanceAdjustmentJournal();
		if(getPM_Machinery_ID()>0)
			createMachineryStatement();
		//createCreditNote();
		
		return msg;
	}
	@Override
	public boolean reverseCorrectIt() {		
		if(getSubcon_Invoice_ID()>0) {			
			throw new AdempiereException("You cannot modify this entry before Reverse Correct Subcontractor Invoice!");
		}
		
		reverseAdvanceAdjustmentJournal();
		reverseCreditNote();
		
		boolean ok = super.reverseCorrectIt();
		ok = ok && reverseInterCashBookEntry();
		return ok;
	}
	
	//Intra Organization / Within the Organization.
	public void createInterCashBookEntry() {
		if(!isInterCashBookEntry() || getRef_Payment_ID() > 0)
			return;
		
		MDocTypeCounter counterDoc = new Query(getCtx(), MDocTypeCounter.Table_Name, "C_DocType_ID=? OR Counter_C_DocType_ID=?", null)
				.setClient_ID().setParameters(getC_DocType_ID(), getC_DocType_ID()).first();
		int c_doctype_id = 0;
		
		if(counterDoc != null ) {
			if(getC_DocType_ID() == counterDoc.getC_DocType_ID())
				c_doctype_id = counterDoc.getCounter_C_DocType_ID();
			else
				c_doctype_id = counterDoc.getC_DocType_ID();
		}
		
		TF_MPayment payment = new TF_MPayment(getCtx(), 0, get_TrxName());
		payment.setAD_Org_ID(getAD_Org_ID());
		payment.setOnAccount(isOnAccount());
		payment.setRef_Payment_ID(getC_Payment_ID());
		payment.setDateTrx(getDateTrx());
		payment.setDateAcct(getDateAcct());		
		payment.setDateBankTrx(getDateBankTrx());
		payment.setC_BankAccount_ID(getFromTo_BankAccount_ID());
		payment.setIsInterCashBookEntry(true);
		payment.setFromTo_BankAccount_ID(getC_BankAccount_ID());
		payment.setC_DocType_ID(c_doctype_id);
		payment.setIsReceipt(!isReceipt());
		payment.setC_ElementValue_ID(getC_ElementValue_ID());
		payment.setUser1_ID(getUser1_ID());
		payment.setUser2_ID(getUser2_ID());
		payment.setC_BPartner_ID(getC_BPartner_ID());
		payment.setC_Charge_ID(getC_Charge_ID());
		payment.setC_Currency_ID(getC_Currency_ID());
		payment.setPayAmt(getPayAmt());
		payment.setTenderType(getTenderType());
		payment.setDescription(getDescription());		
		payment.saveEx();
		
		if(!payment.processIt(DocAction.ACTION_Complete))
			throw new AdempiereException("Failed when processing document - " + payment.getProcessMsg());
		payment.saveEx();
		
		setRef_Payment_ID(payment.getC_Payment_ID());
		
	}
	
	//Intra/Inter Organization
	public boolean reverseInterCashBookEntry() {
		//if(!isInterCashBookEntry())
		if(getRef_Payment_ID() == 0)
			return true;
		
		//Call this method before current document is reversed.
		//Because following condition enforces so
		MPayment counter = new MPayment(getCtx(), getRef_Payment_ID(), get_TrxName());
		if(counter.getDocStatus().equals(DOCSTATUS_Completed)){
			 boolean ok = counter.reverseCorrectIt();
			 counter.saveEx();
		}
		else if(counter.getDocStatus().equals(DOCSTATUS_InProgress)) {
			boolean ok = counter.voidIt();
			counter.setDocStatus(DOCSTATUS_Voided);
			counter.saveEx();
		}
		
		if(getRef2_Payment_ID() == 0)
			return true;
		
		//Additional Cash Transfer Reverse..
		counter = new MPayment(getCtx(), getRef2_Payment_ID(), get_TrxName());
		if(counter.getDocStatus().equals(DOCSTATUS_Completed)){
			 boolean ok = counter.reverseCorrectIt();
			 counter.saveEx();
			 return ok;
		}		
		else if(counter.getDocStatus().equals(DOCSTATUS_InProgress)) {
			boolean ok = counter.voidIt();
			counter.setDocStatus(DOCSTATUS_Voided);
			counter.saveEx();
		}
		return true;
		
	}
	
	public static String getInterCashBookDescription(int fromBank_ID, int toBank_ID) {
		TF_MBankAccount ac1 = new TF_MBankAccount(Env.getCtx(), fromBank_ID, null);
		TF_MBankAccount ac2 = new TF_MBankAccount(Env.getCtx(), toBank_ID, null);
		String desc = "Cash transferred from " + ac1.getName() + " to " + ac2.getName();
		return desc;
	}
	
	public void postAdvanceAdjustmentJournal() {		
		//Posting Opening Balance GL journal 
		int m_C_DocTypeTarget_ID = 1000000;
		MGLPostingConfig glConfig = MGLPostingConfig.getMGLPostingConfig(getCtx());
		if(glConfig.getSalariesAdvanceAcct_ID() == getC_ElementValue_ID())
			return;
		
		TF_MJournal j = new TF_MJournal(getCtx(), 0, get_TrxName());
		j.setDescription("Advance Deducted from " + getDocumentNo());
		j.setAD_Org_ID(getAD_Org_ID());
		j.setC_AcctSchema_ID(glConfig.getC_AcctSchema_ID());
		j.setC_Currency_ID(glConfig.getC_AcctSchema().getC_Currency_ID());
		j.setPostingType(TF_MJournal.POSTINGTYPE_Actual);
		j.setC_DocType_ID(m_C_DocTypeTarget_ID);
		j.setDateDoc(getDateTrx());
		j.setDateAcct(getDateAcct());
		j.setDocStatus(TF_MJournal.DOCSTATUS_Drafted);
		MPeriod period = MPeriod.get(getCtx(), getDateAcct());
		j.setC_Period_ID(period.getC_Period_ID());
		j.setGL_Category_ID(1000000);
		j.setC_ConversionType_ID(114);
		j.setC_Project_ID(getC_Project_ID());
		//j.setIsQuickEntry(true);
		//j.setAmount(getAdvance_Deduct());
		//j.setTF_DebitAcct_ID(getC_ElementValue_ID());
		//j.setTF_CreditAcct_ID(glConfig.getSalariesAdvanceAcct_ID());
		j.saveEx();
		
		//Salaries Payable Dr.
		MJournalLine jl;				
		jl = new MJournalLine(j);
		jl.setLine(10);			
		jl.setAccount_ID(getC_ElementValue_ID());
		jl.setC_BPartner_ID(getC_BPartner_ID());		
		jl.setAmtSourceDr(getAdvance_Deduct());
		jl.setAmtAcctDr(getAdvance_Deduct());
		jl.setIsGenerated(true);
		jl.saveEx();
		
		//
		jl = new MJournalLine(j);
		jl.setLine(10);			
		jl.setAccount_ID(glConfig.getSalariesAdvanceAcct_ID());
		jl.setC_BPartner_ID(getC_BPartner_ID());		
		jl.setAmtSourceCr(getAdvance_Deduct());
		jl.setAmtAcctCr(getAdvance_Deduct());
		jl.setIsGenerated(true);
		jl.saveEx();
		
		//DocAction
		if (!j.processIt(DocAction.ACTION_Complete))
			throw new AdempiereException("Failed when processing document - " + j.getProcessMsg());
		j.saveEx();
		
		DB.executeUpdate("UPDATE C_Payment SET " + COLUMNNAME_EmpAdv_Journal_ID +"="+ j.getGL_Journal_ID() + " WHERE C_Payment_ID="
				+ getC_Payment_ID() , get_TrxName());
	}
	
	public void reverseAdvanceAdjustmentJournal() {
		if(getEmpAdv_Journal_ID() > 0) {
			TF_MJournal j = new TF_MJournal(getCtx(), getEmpAdv_Journal_ID(), get_TrxName());
			if(j.getDocStatus().equals(TF_MInvoice.DOCSTATUS_Completed)) {
				if (!j.processIt(DocAction.ACTION_Reverse_Correct))
					throw new AdempiereException("Failed when processing document - " + j.getProcessMsg());
				j.saveEx();
			}
		}
	}
	
	public void createInterOrgCashBookEntry() {
		
		if(getRef_Payment_ID() > 0)
			return;						
		
		int counterOrgID=0;
		int counterCashID=0;
		int counterAcctID=0;		
		String whereClause = "Src_BankAccount_ID = ? AND Dest_Acct_ID = ? AND IsActive='Y'";
		MInterOrgCashTransfer config = new Query(getCtx(), MInterOrgCashTransfer.Table_Name, whereClause, get_TrxName())
				.setParameters(getC_BankAccount_ID(), getC_ElementValue_ID()).first();
		if(config == null) {
			whereClause = "Dest_BankAccount_ID = ? AND Src_Acct_ID = ? AND Direction='B' AND IsActive='Y'";
			config = new Query(getCtx(), MInterOrgCashTransfer.Table_Name, whereClause, get_TrxName())
					.setParameters(getC_BankAccount_ID(), getC_ElementValue_ID()).first();
			if(config != null) {
				counterOrgID = config.getSrc_Org_ID();
				counterCashID = config.getSrc_BankAccount_ID();
				counterAcctID = config.getDest_Acct_ID();				
			}
		}
		else {
			counterOrgID = config.getDest_Org_ID();
			counterCashID = config.getDest_BankAccount_ID();
			counterAcctID = config.getSrc_Acct_ID();			
		}
		
		//It is not Inter Org Cash Transfer
		if(config == null)
			return;
		
		
		//It Is Inter Org Cash Transfer		
		
		MDocTypeCounter counterDoc = new Query(getCtx(), MDocTypeCounter.Table_Name, "C_DocType_ID=? OR Counter_C_DocType_ID=?", null)
				.setClient_ID().setParameters(getC_DocType_ID(), getC_DocType_ID()).first();
		int c_doctype_id = 0;
		
		if(counterDoc != null ) {
			if(getC_DocType_ID() == counterDoc.getC_DocType_ID())
				c_doctype_id = counterDoc.getCounter_C_DocType_ID();
			else
				c_doctype_id = counterDoc.getC_DocType_ID();
		}
				
		
		TF_MPayment payment = new TF_MPayment(getCtx(), 0, get_TrxName());
		payment.setAD_Org_ID(counterOrgID);
		payment.setRef_Payment_ID(getC_Payment_ID());
		payment.setOnAccount(isOnAccount());
		payment.setDateTrx(getDateTrx());
		payment.setDateAcct(getDateAcct());		
		payment.setC_BankAccount_ID(counterCashID);
		payment.setC_DocType_ID(c_doctype_id);
		payment.setIsReceipt(!isReceipt());
		payment.setC_ElementValue_ID(counterAcctID);
		TF_MCharge charge = TF_MCharge.createChargeFromAccount(getCtx(), payment.getC_ElementValue_ID(), null);
		if(charge != null )
			payment.setC_Charge_ID(charge.get_ID());		
		payment.setUser1_ID(getUser1_ID());
		payment.setUser2_ID(getUser2_ID());
		payment.setC_BPartner_ID(getC_BPartner_ID());		
		payment.setC_Currency_ID(getC_Currency_ID());
		payment.setPayAmt(getPayAmt());
		payment.setTenderType(getTenderType());
		payment.setDescription(getDescription());
		payment.setDocStatus(DOCSTATUS_InProgress);
		payment.saveEx();
		
		if(!config.isRequiredApproval()) {
			if(!payment.processIt(DocAction.ACTION_Complete))
				throw new AdempiereException("Failed when processing document - " + payment.getProcessMsg());
			payment.saveEx();
		}
		setRef_Payment_ID(payment.getC_Payment_ID());
		
		//Additional Cash Transfer in Destination Organization
		if(getTF_OrgCashTransfer_Configx_ID() == 0)
			return;
		
		MInterOrgCashTransferConfigLine ctConfig = new MInterOrgCashTransferConfigLine(getCtx(), getTF_OrgCashTransfer_Configx_ID(), get_TrxName());		
		TF_MPayment payment2 = new TF_MPayment(getCtx(), 0, get_TrxName());
		payment2.setAD_Org_ID(ctConfig.getDest_Org_ID());
		payment2.setOnAccount(isOnAccount());
		payment2.setRef_Payment_ID(getRef_Payment_ID());
		payment2.setRef2_Payment_ID(getC_Payment_ID());
		payment2.setDateTrx(getDateTrx());
		payment2.setDateAcct(getDateAcct());		
		payment2.setC_BankAccount_ID(counterCashID);
		payment2.setC_DocType_ID(ctConfig.getC_DocType_ID());
		payment2.setIsReceipt(false);
		payment2.setC_ElementValue_ID(ctConfig.getDest_Acct_ID());
		charge = TF_MCharge.createChargeFromAccount(getCtx(), payment2.getC_ElementValue_ID(), null);
		if(charge != null )
			payment2.setC_Charge_ID(charge.get_ID());
		payment2.setUser1_ID(getUser1_ID());
		payment2.setUser2_ID(getUser2_ID());
		if(ctConfig.getDest_Partner_ID() > 0) {
			payment2.setC_BPartner_ID(ctConfig.getDest_Partner_ID());
			payment2.setTF_BPartner_ID(ctConfig.getDest_Partner_ID());			
			payment2.setIsEmployee(payment2.getC_BPartner().isEmployee());
		}
		else {
			MUser user = MUser.get(getCtx(), Env.getAD_User_ID(Env.getCtx()));				
			int bPartnerID = user.getC_BPartner_ID();
			payment2.setC_BPartner_ID(bPartnerID);
		}				
		payment2.setC_Currency_ID(getC_Currency_ID());
		payment2.setPayAmt(getPayAmt());
		payment2.setTenderType(getTenderType());
		payment2.setDescription(getDescription());
		payment2.setDocStatus(DOCSTATUS_InProgress);
		payment2.saveEx();
		
		if(!config.isRequiredApproval()) {
			if(!payment2.processIt(DocAction.ACTION_Complete))
				throw new AdempiereException("Failed when processing document - " + payment2.getProcessMsg());
			payment2.saveEx();
		}
		setRef2_Payment_ID(payment2.getC_Payment_ID());
		payment.setRef2_Payment_ID(payment2.getC_Payment_ID());
		payment.saveEx();
		
	}
	
	public void createInterOrgBPCashBookEntry() {

		if(getRef_Payment_ID() > 0)
			return;						
		
		int counterOrgID=0;
		int counterCashID=0;
		int counterPartnerID = 0;
		int counterProjectID = 0;
		
		MInterOrgBPCashTransferConfig config = MInterOrgBPCashTransferConfig.getConfig(getC_BankAccount_ID(), getTF_BPartner_ID()); 
				
		if(config != null) {
			counterOrgID = config.getDest_Org_ID();
			counterCashID = config.getDest_BankAccount_ID();
			counterPartnerID = config.getSrc_Partner_ID();
			counterProjectID = config.getDest_Project_ID();
		}
		
		//It is not Inter Org BP Cash Transfer
		if(config == null)
			return;
		
		
		//It Is Inter Org Cash Transfer		
		
		MDocTypeCounter counterDoc = new Query(getCtx(), MDocTypeCounter.Table_Name, "C_DocType_ID=? OR Counter_C_DocType_ID=?", null)
				.setClient_ID().setParameters(getC_DocType_ID(), getC_DocType_ID()).first();
		int c_doctype_id = 0;
		
		if(counterDoc != null ) {
			if(getC_DocType_ID() == counterDoc.getC_DocType_ID())
				c_doctype_id = counterDoc.getCounter_C_DocType_ID();
			else
				c_doctype_id = counterDoc.getC_DocType_ID();
		}
				
		
		TF_MPayment payment = new TF_MPayment(getCtx(), 0, get_TrxName());
		payment.setAD_Org_ID(counterOrgID);
		payment.setOnAccount(isOnAccount());
		payment.setRef_Payment_ID(getC_Payment_ID());
		payment.setDateTrx(getDateTrx());
		payment.setDateAcct(getDateAcct());		
		payment.setC_BankAccount_ID(counterCashID);
		payment.setC_DocType_ID(c_doctype_id);
		payment.setIsReceipt(!isReceipt());
		payment.setTF_BPartner_ID(counterPartnerID);
		payment.setC_BPartner_ID(counterPartnerID);		
		TF_MCharge charge = TF_MCharge.createChargeFromAccount(getCtx(), payment.getC_ElementValue_ID(), null);
		if(charge != null )
			payment.setC_Charge_ID(charge.get_ID());		
		payment.setUser1_ID(getUser1_ID());
		payment.setUser2_ID(getUser2_ID());
		payment.setC_Project_ID(counterProjectID);
		payment.setC_Currency_ID(getC_Currency_ID());
		payment.setPayAmt(getPayAmt());
		payment.setTenderType(getTenderType());
		payment.setDescription(getDescription());
		payment.setDocStatus(DOCSTATUS_InProgress);
		payment.saveEx();
		
		if(!config.isRequiredApproval()) {
			if(!payment.processIt(DocAction.ACTION_Complete))
				throw new AdempiereException("Failed when processing document - " + payment.getProcessMsg());
			payment.saveEx();
		}
		setRef_Payment_ID(payment.getC_Payment_ID());
		
		//Additional BP Cash Transfer in Destination Organization
		if(getTF_OrgBPCashTrans_Configx_ID() == 0)
			return;
		
		MInterOrgBPCashTransferConfigLine ctConfig = new MInterOrgBPCashTransferConfigLine(getCtx(), getTF_OrgBPCashTrans_Configx_ID(), get_TrxName());		
		TF_MPayment payment2 = new TF_MPayment(getCtx(), 0, get_TrxName());
		payment2.setAD_Org_ID(ctConfig.getDest_Org_ID());
		payment2.setOnAccount(isOnAccount());
		payment2.setRef_Payment_ID(getRef_Payment_ID());
		payment2.setRef2_Payment_ID(getC_Payment_ID());
		payment2.setDateTrx(getDateTrx());
		payment2.setDateAcct(getDateAcct());		
		payment2.setC_BankAccount_ID(counterCashID);
		payment2.setC_DocType_ID(ctConfig.getC_DocType_ID());
		payment2.setIsReceipt(false);
		payment2.setC_ElementValue_ID(ctConfig.getDest_Acct_ID());		
		charge = TF_MCharge.createChargeFromAccount(getCtx(), payment2.getC_ElementValue_ID(), null);
		if(charge != null )
			payment2.setC_Charge_ID(charge.get_ID());
		payment2.setUser1_ID(getUser1_ID());
		payment2.setUser2_ID(getUser2_ID());
		payment2.setC_Project_ID(counterProjectID);
		if(ctConfig.getDest_Partner_ID() > 0) {
			payment2.setC_BPartner_ID(ctConfig.getDest_Partner_ID());
			payment2.setTF_BPartner_ID(ctConfig.getDest_Partner_ID());
			payment2.setIsEmployee(true);
		}
		else {
			MUser user = MUser.get(getCtx(), Env.getAD_User_ID(Env.getCtx()));				
			int bPartnerID = user.getC_BPartner_ID();
			payment2.setC_BPartner_ID(bPartnerID);
		}
		payment2.setC_Currency_ID(getC_Currency_ID());
		payment2.setPayAmt(getPayAmt());
		payment2.setTenderType(getTenderType());
		payment2.setDescription(getDescription());
		payment2.setDocStatus(DOCSTATUS_InProgress);
		payment2.saveEx();
		if(!config.isRequiredApproval()) {
			if(!payment2.processIt(DocAction.ACTION_Complete))
				throw new AdempiereException("Failed when processing document - " + payment2.getProcessMsg());
			payment2.saveEx();
		}
		setRef2_Payment_ID(payment2.getC_Payment_ID());
		payment.setRef2_Payment_ID(payment2.getC_Payment_ID());
		payment.saveEx();
	}
	
	public void createPayToCashBookEntry() {
		if(getRef_Payment_ID() > 0 || getC_ElementValuePayTo_ID() == 0)
			return;
		
		MDocTypeCounter counterDoc = new Query(getCtx(), MDocTypeCounter.Table_Name, "C_DocType_ID=? OR Counter_C_DocType_ID=?", null)
				.setClient_ID().setParameters(getC_DocType_ID(), getC_DocType_ID()).first();
		int c_doctype_id = 0;
		
		if(counterDoc != null ) {
			if(getC_DocType_ID() == counterDoc.getC_DocType_ID())
				c_doctype_id = counterDoc.getCounter_C_DocType_ID();
			else
				c_doctype_id = counterDoc.getC_DocType_ID();
		}
		
		TF_MPayment payment = new TF_MPayment(getCtx(), 0, get_TrxName());
		payment.setAD_Org_ID(getAD_Org_ID());
		payment.setOnAccount(isOnAccount());
		payment.setRef_Payment_ID(getC_Payment_ID());
		payment.setDateTrx(getDateTrx());
		payment.setDateAcct(getDateAcct());		
		payment.setC_BankAccount_ID(getC_BankAccount_ID());
		payment.setC_DocType_ID(c_doctype_id);
		payment.setIsReceipt(!isReceipt());
		payment.setC_ElementValue_ID(getC_ElementValuePayTo_ID());
		payment.setUser1_ID(getUser1_ID());
		payment.setUser2_ID(getUser2_ID());
		payment.setC_Project_ID(getC_Project_ID());
		MUser user = MUser.get(getCtx(), Env.getAD_User_ID(Env.getCtx()));				
		int bPartnerID = user.getC_BPartner_ID();
		payment.setC_BPartner_ID(bPartnerID);
		payment.setC_Currency_ID(getC_Currency_ID());
		payment.setPayAmt(getPayAmt());
		payment.setTenderType(getTenderType());
		payment.setDescription(getDescription());		
		payment.saveEx();
		
		if(!payment.processIt(DocAction.ACTION_Complete))
			throw new AdempiereException("Failed when processing document - " + payment.getProcessMsg());
		payment.saveEx();
		
		setRef_Payment_ID(payment.getC_Payment_ID());
	}
	
	//This seq no is maintained for On Account Module
	private void setDocumentNo2() {
		if(!isOnAccount() || (isOnAccount() && getDocumentNo2() != null && getDocumentNo2().length() > 0))
			return;
		
		MSequence seq = new Query(getCtx(), MSequence.Table_Name, "Name='CashBook2'", get_TrxName())
				.setClient_ID().first();
		if(seq == null)
			throw new AdempiereException("Create CashBook2 Doument Sequence!");
		
		String seqNo = MSequence.getDocumentNoFromSeq(seq, get_TrxName(), this);
		setDocumentNo2(seqNo);
	}
	
	private void createMachineryStatement() {
		MMachineryStatement mStatement=new MMachineryStatement(getCtx(), 0, get_TrxName());
		mStatement.setAD_Org_ID(getAD_Org_ID());
		mStatement.setDateAcct(getDateAcct());
		mStatement.setPM_Machinery_ID(getPM_Machinery_ID());
		mStatement.setPM_Job_ID(getPM_Job_ID());
		mStatement.setC_ElementValue_ID(getC_ElementValue_ID());
		if(!isReceipt()) {
			mStatement.setExpense(getPayAmt());
		}
		else {
			mStatement.setIncome(getPayAmt());
		}
		mStatement.setDescription(getDescription());
		mStatement.setC_Payment_ID(getC_Payment_ID());
		mStatement.saveEx();
	}

	
	private void createCreditNote() {
		if(getTF_WeighmentEntry_ID() == 0 || getDiscountAmt2().doubleValue() == 0)
			return;
		
		String whereClause = "C_DocType.DocBaseType IN ('ARC','APC') AND IsSOTrx = ?";
		MDocType dt = new Query(getCtx(), MDocType.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(isReceipt() ? "Y" : "N")
				.first();
		
		MGLPostingConfig glConfig = MGLPostingConfig.getMGLPostingConfig(getCtx());
		
		MWeighmentEntry we = new MWeighmentEntry(getCtx(), getTF_WeighmentEntry_ID(), get_TrxName());
		
		MDebitCreditNote dc = new MDebitCreditNote(getCtx(), 0, get_TrxName());
		dc.setAD_Org_ID(getAD_Org_ID());
		dc.setDateAcct(getDateAcct());
		dc.setC_DocType_ID(dt.getC_DocType_ID());
		dc.setC_BPartner_ID(getC_BPartner_ID());
		dc.setTF_WeighmentEntry_ID(getTF_WeighmentEntry_ID());
		dc.setC_ElementValue_ID(glConfig.getSalesDiscountAcct_ID());
		dc.setAmount(getDiscountAmt2());
		dc.setDescription("Discounted for " + we.getDocumentNo());
		dc.saveEx();
		
		dc.processIt(DOCACTION_Complete);
		dc.saveEx();
		
		setTF_DebitCreditNote_ID(dc.get_ID());
	}
	
	private void reverseCreditNote() {
		if(getTF_DebitCreditNote_ID() == 0)
			return;
		
		MDebitCreditNote dc = new MDebitCreditNote(getCtx(), getTF_DebitCreditNote_ID(), get_TrxName());
		dc.reverseIt();
		dc.setDocumentNo(DOCSTATUS_Voided);
		dc.setProcessed(true);
		dc.saveEx();
	}
	
		
}
