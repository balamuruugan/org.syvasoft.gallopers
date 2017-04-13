/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for TF_Employee_Salary_Issue
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_TF_Employee_Salary_Issue 
{

    /** TableName=TF_Employee_Salary_Issue */
    public static final String Table_Name = "TF_Employee_Salary_Issue";

    /** AD_Table_ID=1000186 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name Advance_Balance */
    public static final String COLUMNNAME_Advance_Balance = "Advance_Balance";

	/** Set Balance Advance	  */
	public void setAdvance_Balance (BigDecimal Advance_Balance);

	/** Get Balance Advance	  */
	public BigDecimal getAdvance_Balance();

    /** Column name Advance_Deduct */
    public static final String COLUMNNAME_Advance_Deduct = "Advance_Deduct";

	/** Set Deduct Advance	  */
	public void setAdvance_Deduct (BigDecimal Advance_Deduct);

	/** Get Deduct Advance	  */
	public BigDecimal getAdvance_Deduct();

    /** Column name Advance_Paid */
    public static final String COLUMNNAME_Advance_Paid = "Advance_Paid";

	/** Set Advance Paid	  */
	public void setAdvance_Paid (BigDecimal Advance_Paid);

	/** Get Advance Paid	  */
	public BigDecimal getAdvance_Paid();

    /** Column name C_BankAccount_ID */
    public static final String COLUMNNAME_C_BankAccount_ID = "C_BankAccount_ID";

	/** Set Bank/Cash Account.
	  * Account at the Bank
	  */
	public void setC_BankAccount_ID (int C_BankAccount_ID);

	/** Get Bank/Cash Account.
	  * Account at the Bank
	  */
	public int getC_BankAccount_ID();

	public org.compiere.model.I_C_BankAccount getC_BankAccount() throws RuntimeException;

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Employee.
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Employee.
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_ElementValue_ID */
    public static final String COLUMNNAME_C_ElementValue_ID = "C_ElementValue_ID";

	/** Set Profit Center.
	  * Account Element
	  */
	public void setC_ElementValue_ID (int C_ElementValue_ID);

	/** Get Profit Center.
	  * Account Element
	  */
	public int getC_ElementValue_ID();

	public org.compiere.model.I_C_ElementValue getC_ElementValue() throws RuntimeException;

    /** Column name C_Payment_ID */
    public static final String COLUMNNAME_C_Payment_ID = "C_Payment_ID";

	/** Set Cash Book Entry.
	  * Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID);

	/** Get Cash Book Entry.
	  * Payment identifier
	  */
	public int getC_Payment_ID();

	public org.compiere.model.I_C_Payment getC_Payment() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name DateAcct */
    public static final String COLUMNNAME_DateAcct = "DateAcct";

	/** Set Account Date.
	  * Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct);

	/** Get Account Date.
	  * Accounting Date
	  */
	public Timestamp getDateAcct();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name DocStatus */
    public static final String COLUMNNAME_DocStatus = "DocStatus";

	/** Set Document Status.
	  * The current status of the document
	  */
	public void setDocStatus (String DocStatus);

	/** Get Document Status.
	  * The current status of the document
	  */
	public String getDocStatus();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name GL_Journal_ID */
    public static final String COLUMNNAME_GL_Journal_ID = "GL_Journal_ID";

	/** Set Journal.
	  * General Ledger Journal
	  */
	public void setGL_Journal_ID (int GL_Journal_ID);

	/** Get Journal.
	  * General Ledger Journal
	  */
	public int getGL_Journal_ID();

	public org.compiere.model.I_GL_Journal getGL_Journal() throws RuntimeException;

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name Loan_Balance */
    public static final String COLUMNNAME_Loan_Balance = "Loan_Balance";

	/** Set Balance Loan	  */
	public void setLoan_Balance (BigDecimal Loan_Balance);

	/** Get Balance Loan	  */
	public BigDecimal getLoan_Balance();

    /** Column name Loan_Deduct */
    public static final String COLUMNNAME_Loan_Deduct = "Loan_Deduct";

	/** Set Deduct Loan	  */
	public void setLoan_Deduct (BigDecimal Loan_Deduct);

	/** Get Deduct Loan	  */
	public BigDecimal getLoan_Deduct();

    /** Column name Loan_Paid */
    public static final String COLUMNNAME_Loan_Paid = "Loan_Paid";

	/** Set Loan Paid	  */
	public void setLoan_Paid (BigDecimal Loan_Paid);

	/** Get Loan Paid	  */
	public BigDecimal getLoan_Paid();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name Salary_Amt */
    public static final String COLUMNNAME_Salary_Amt = "Salary_Amt";

	/** Set Earned Salary	  */
	public void setSalary_Amt (BigDecimal Salary_Amt);

	/** Get Earned Salary	  */
	public BigDecimal getSalary_Amt();

    /** Column name Salary_Paid */
    public static final String COLUMNNAME_Salary_Paid = "Salary_Paid";

	/** Set Salary Paid	  */
	public void setSalary_Paid (BigDecimal Salary_Paid);

	/** Get Salary Paid	  */
	public BigDecimal getSalary_Paid();

    /** Column name Salary_Payable */
    public static final String COLUMNNAME_Salary_Payable = "Salary_Payable";

	/** Set Balance Salary	  */
	public void setSalary_Payable (BigDecimal Salary_Payable);

	/** Get Balance Salary	  */
	public BigDecimal getSalary_Payable();

    /** Column name TF_Employee_Salary_Issue_ID */
    public static final String COLUMNNAME_TF_Employee_Salary_Issue_ID = "TF_Employee_Salary_Issue_ID";

	/** Set Employee Salary Issue	  */
	public void setTF_Employee_Salary_Issue_ID (int TF_Employee_Salary_Issue_ID);

	/** Get Employee Salary Issue	  */
	public int getTF_Employee_Salary_Issue_ID();

    /** Column name TF_Employee_Salary_Issue_UU */
    public static final String COLUMNNAME_TF_Employee_Salary_Issue_UU = "TF_Employee_Salary_Issue_UU";

	/** Set TF_Employee_Salary_Issue_UU	  */
	public void setTF_Employee_Salary_Issue_UU (String TF_Employee_Salary_Issue_UU);

	/** Get TF_Employee_Salary_Issue_UU	  */
	public String getTF_Employee_Salary_Issue_UU();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
