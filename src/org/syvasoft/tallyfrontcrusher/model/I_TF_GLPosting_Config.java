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

/** Generated Interface for TF_GLPosting_Config
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_TF_GLPosting_Config 
{

    /** TableName=TF_GLPosting_Config */
    public static final String Table_Name = "TF_GLPosting_Config";

    /** AD_Table_ID=1000177 */
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

    /** Column name BP_CreditBalanceAcct_ID */
    public static final String COLUMNNAME_BP_CreditBalanceAcct_ID = "BP_CreditBalanceAcct_ID";

	/** Set BP Credit Balance Account	  */
	public void setBP_CreditBalanceAcct_ID (int BP_CreditBalanceAcct_ID);

	/** Get BP Credit Balance Account	  */
	public int getBP_CreditBalanceAcct_ID();

	public org.compiere.model.I_C_ElementValue getBP_CreditBalanceAcct() throws RuntimeException;

    /** Column name BP_CreditDocType_ID */
    public static final String COLUMNNAME_BP_CreditDocType_ID = "BP_CreditDocType_ID";

	/** Set Opening Vendor Inv Document Type.
	  * Document Type used for setting opening balance for vendor
	  */
	public void setBP_CreditDocType_ID (int BP_CreditDocType_ID);

	/** Get Opening Vendor Inv Document Type.
	  * Document Type used for setting opening balance for vendor
	  */
	public int getBP_CreditDocType_ID();

	public org.compiere.model.I_C_DocType getBP_CreditDocType() throws RuntimeException;

    /** Column name BP_DebitBalanceAcct_ID */
    public static final String COLUMNNAME_BP_DebitBalanceAcct_ID = "BP_DebitBalanceAcct_ID";

	/** Set BP Debit Balance Account	  */
	public void setBP_DebitBalanceAcct_ID (int BP_DebitBalanceAcct_ID);

	/** Get BP Debit Balance Account	  */
	public int getBP_DebitBalanceAcct_ID();

	public org.compiere.model.I_C_ElementValue getBP_DebitBalanceAcct() throws RuntimeException;

    /** Column name BP_DebitDocType_ID */
    public static final String COLUMNNAME_BP_DebitDocType_ID = "BP_DebitDocType_ID";

	/** Set Opening Cust. Inv Document Type.
	  * Document Type used for setting opening balance for customer
	  */
	public void setBP_DebitDocType_ID (int BP_DebitDocType_ID);

	/** Get Opening Cust. Inv Document Type.
	  * Document Type used for setting opening balance for customer
	  */
	public int getBP_DebitDocType_ID();

	public org.compiere.model.I_C_DocType getBP_DebitDocType() throws RuntimeException;

    /** Column name C_AcctSchema_ID */
    public static final String COLUMNNAME_C_AcctSchema_ID = "C_AcctSchema_ID";

	/** Set Accounting Schema.
	  * Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID);

	/** Get Accounting Schema.
	  * Rules for accounting
	  */
	public int getC_AcctSchema_ID();

	public org.compiere.model.I_C_AcctSchema getC_AcctSchema() throws RuntimeException;

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

    /** Column name C_ElementGSTPayable_ID */
    public static final String COLUMNNAME_C_ElementGSTPayable_ID = "C_ElementGSTPayable_ID";

	/** Set GST Payable	  */
	public void setC_ElementGSTPayable_ID (int C_ElementGSTPayable_ID);

	/** Get GST Payable	  */
	public int getC_ElementGSTPayable_ID();

	public org.compiere.model.I_C_ElementValue getC_ElementGSTPayable() throws RuntimeException;

    /** Column name C_ElementValueCGST_ID */
    public static final String COLUMNNAME_C_ElementValueCGST_ID = "C_ElementValueCGST_ID";

	/** Set CGST Account Head	  */
	public void setC_ElementValueCGST_ID (int C_ElementValueCGST_ID);

	/** Get CGST Account Head	  */
	public int getC_ElementValueCGST_ID();

	public org.compiere.model.I_C_ElementValue getC_ElementValueCGST() throws RuntimeException;

    /** Column name C_ElementValueIGST_ID */
    public static final String COLUMNNAME_C_ElementValueIGST_ID = "C_ElementValueIGST_ID";

	/** Set IGST Account Head	  */
	public void setC_ElementValueIGST_ID (int C_ElementValueIGST_ID);

	/** Get IGST Account Head	  */
	public int getC_ElementValueIGST_ID();

	public org.compiere.model.I_C_ElementValue getC_ElementValueIGST() throws RuntimeException;

    /** Column name C_ElementValuePermitExp_ID */
    public static final String COLUMNNAME_C_ElementValuePermitExp_ID = "C_ElementValuePermitExp_ID";

	/** Set Permit Expense Account Head	  */
	public void setC_ElementValuePermitExp_ID (int C_ElementValuePermitExp_ID);

	/** Get Permit Expense Account Head	  */
	public int getC_ElementValuePermitExp_ID();

	public org.compiere.model.I_C_ElementValue getC_ElementValuePermitExp() throws RuntimeException;

    /** Column name C_ElementValueSGST_ID */
    public static final String COLUMNNAME_C_ElementValueSGST_ID = "C_ElementValueSGST_ID";

	/** Set SGST Account Head	  */
	public void setC_ElementValueSGST_ID (int C_ElementValueSGST_ID);

	/** Get SGST Account Head	  */
	public int getC_ElementValueSGST_ID();

	public org.compiere.model.I_C_ElementValue getC_ElementValueSGST() throws RuntimeException;

    /** Column name C_EleValueProdBonus_ID */
    public static final String COLUMNNAME_C_EleValueProdBonus_ID = "C_EleValueProdBonus_ID";

	/** Set Production Bonus	  */
	public void setC_EleValueProdBonus_ID (int C_EleValueProdBonus_ID);

	/** Get Production Bonus	  */
	public int getC_EleValueProdBonus_ID();

	public org.compiere.model.I_C_ElementValue getC_EleValueProdBonus() throws RuntimeException;

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

    /** Column name DebitNote_DocType_ID */
    public static final String COLUMNNAME_DebitNote_DocType_ID = "DebitNote_DocType_ID";

	/** Set Debit Note Document Type	  */
	public void setDebitNote_DocType_ID (int DebitNote_DocType_ID);

	/** Get Debit Note Document Type	  */
	public int getDebitNote_DocType_ID();

	public org.compiere.model.I_C_DocType getDebitNote_DocType() throws RuntimeException;

    /** Column name DefaultSalesInvoiceDocType_ID */
    public static final String COLUMNNAME_DefaultSalesInvoiceDocType_ID = "DefaultSalesInvoiceDocType_ID";

	/** Set Vehicle Rent Sales Invoice Doc Type.
	  * Vehicle Rent Sales Invoice
	  */
	public void setDefaultSalesInvoiceDocType_ID (int DefaultSalesInvoiceDocType_ID);

	/** Get Vehicle Rent Sales Invoice Doc Type.
	  * Vehicle Rent Sales Invoice
	  */
	public int getDefaultSalesInvoiceDocType_ID();

	public org.compiere.model.I_C_DocType getDefaultSalesInvoiceDocType() throws RuntimeException;

    /** Column name Fuel_Product_ID */
    public static final String COLUMNNAME_Fuel_Product_ID = "Fuel_Product_ID";

	/** Set Fuel.
	  * Fuel
	  */
	public void setFuel_Product_ID (int Fuel_Product_ID);

	/** Get Fuel.
	  * Fuel
	  */
	public int getFuel_Product_ID();

	public org.compiere.model.I_M_Product getFuel_Product() throws RuntimeException;

    /** Column name FuelExpense_Charge_ID */
    public static final String COLUMNNAME_FuelExpense_Charge_ID = "FuelExpense_Charge_ID";

	/** Set Fuel Expense Charge	  */
	public void setFuelExpense_Charge_ID (int FuelExpense_Charge_ID);

	/** Get Fuel Expense Charge	  */
	public int getFuelExpense_Charge_ID();

	public org.compiere.model.I_C_Charge getFuelExpense_Charge() throws RuntimeException;

    /** Column name IncentiveAcct_ID */
    public static final String COLUMNNAME_IncentiveAcct_ID = "IncentiveAcct_ID";

	/** Set Incentive	  */
	public void setIncentiveAcct_ID (int IncentiveAcct_ID);

	/** Get Incentive	  */
	public int getIncentiveAcct_ID();

	public org.compiere.model.I_C_ElementValue getIncentiveAcct() throws RuntimeException;

    /** Column name InterBankInTransit_ID */
    public static final String COLUMNNAME_InterBankInTransit_ID = "InterBankInTransit_ID";

	/** Set Inter Bank In Transit	  */
	public void setInterBankInTransit_ID (int InterBankInTransit_ID);

	/** Get Inter Bank In Transit	  */
	public int getInterBankInTransit_ID();

	public org.compiere.model.I_C_ElementValue getInterBankInTransit() throws RuntimeException;

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

    /** Column name ItemIssue_Charge_ID */
    public static final String COLUMNNAME_ItemIssue_Charge_ID = "ItemIssue_Charge_ID";

	/** Set Item Issue Charge	  */
	public void setItemIssue_Charge_ID (int ItemIssue_Charge_ID);

	/** Get Item Issue Charge	  */
	public int getItemIssue_Charge_ID();

	public org.compiere.model.I_C_Charge getItemIssue_Charge() throws RuntimeException;

    /** Column name JobWork_Product_ID */
    public static final String COLUMNNAME_JobWork_Product_ID = "JobWork_Product_ID";

	/** Set Job Work	  */
	public void setJobWork_Product_ID (int JobWork_Product_ID);

	/** Get Job Work	  */
	public int getJobWork_Product_ID();

	public org.compiere.model.I_M_Product getJobWork_Product() throws RuntimeException;

    /** Column name JobworkExpenseAcct_ID */
    public static final String COLUMNNAME_JobworkExpenseAcct_ID = "JobworkExpenseAcct_ID";

	/** Set Jobwork Expense	  */
	public void setJobworkExpenseAcct_ID (int JobworkExpenseAcct_ID);

	/** Get Jobwork Expense	  */
	public int getJobworkExpenseAcct_ID();

	public org.compiere.model.I_C_ElementValue getJobworkExpenseAcct() throws RuntimeException;

    /** Column name JobworkExpenseVarianceAcct_ID */
    public static final String COLUMNNAME_JobworkExpenseVarianceAcct_ID = "JobworkExpenseVarianceAcct_ID";

	/** Set Jobwork Expense Variance	  */
	public void setJobworkExpenseVarianceAcct_ID (int JobworkExpenseVarianceAcct_ID);

	/** Get Jobwork Expense Variance	  */
	public int getJobworkExpenseVarianceAcct_ID();

	public org.compiere.model.I_C_ElementValue getJobworkExpenseVarianceAcct() throws RuntimeException;

    /** Column name JobworkPayableClearingAcct_ID */
    public static final String COLUMNNAME_JobworkPayableClearingAcct_ID = "JobworkPayableClearingAcct_ID";

	/** Set Jobwork Payable Clearing	  */
	public void setJobworkPayableClearingAcct_ID (int JobworkPayableClearingAcct_ID);

	/** Get Jobwork Payable Clearing	  */
	public int getJobworkPayableClearingAcct_ID();

	public org.compiere.model.I_C_ElementValue getJobworkPayableClearingAcct() throws RuntimeException;

    /** Column name Loan_ID */
    public static final String COLUMNNAME_Loan_ID = "Loan_ID";

	/** Set Loan	  */
	public void setLoan_ID (int Loan_ID);

	/** Get Loan	  */
	public int getLoan_ID();

	public org.compiere.model.I_C_ElementValue getLoan() throws RuntimeException;

    /** Column name MaterialIssue_DocType_ID */
    public static final String COLUMNNAME_MaterialIssue_DocType_ID = "MaterialIssue_DocType_ID";

	/** Set Material Issue Doc Type	  */
	public void setMaterialIssue_DocType_ID (int MaterialIssue_DocType_ID);

	/** Get Material Issue Doc Type	  */
	public int getMaterialIssue_DocType_ID();

	public org.compiere.model.I_C_DocType getMaterialIssue_DocType() throws RuntimeException;

    /** Column name MaterialReceipt_DocType_ID */
    public static final String COLUMNNAME_MaterialReceipt_DocType_ID = "MaterialReceipt_DocType_ID";

	/** Set Material Receipt Doc Type	  */
	public void setMaterialReceipt_DocType_ID (int MaterialReceipt_DocType_ID);

	/** Get Material Receipt Doc Type	  */
	public int getMaterialReceipt_DocType_ID();

	public org.compiere.model.I_C_DocType getMaterialReceipt_DocType() throws RuntimeException;

    /** Column name OpeningBalAcct_ID */
    public static final String COLUMNNAME_OpeningBalAcct_ID = "OpeningBalAcct_ID";

	/** Set Opening Balance Offset Account.
	  * Offset Account
	  */
	public void setOpeningBalAcct_ID (int OpeningBalAcct_ID);

	/** Get Opening Balance Offset Account.
	  * Offset Account
	  */
	public int getOpeningBalAcct_ID();

	public org.compiere.model.I_C_ElementValue getOpeningBalAcct() throws RuntimeException;

    /** Column name OwnMining_Charge_ID */
    public static final String COLUMNNAME_OwnMining_Charge_ID = "OwnMining_Charge_ID";

	/** Set Own Boulder Production Charge	  */
	public void setOwnMining_Charge_ID (int OwnMining_Charge_ID);

	/** Get Own Boulder Production Charge	  */
	public int getOwnMining_Charge_ID();

	public org.compiere.model.I_C_Charge getOwnMining_Charge() throws RuntimeException;

    /** Column name PLAcct_ID */
    public static final String COLUMNNAME_PLAcct_ID = "PLAcct_ID";

	/** Set Profit and Loss Account	  */
	public void setPLAcct_ID (int PLAcct_ID);

	/** Get Profit and Loss Account	  */
	public int getPLAcct_ID();

	public org.compiere.model.I_C_ElementValue getPLAcct() throws RuntimeException;

    /** Column name PLApprAcct_ID */
    public static final String COLUMNNAME_PLApprAcct_ID = "PLApprAcct_ID";

	/** Set Profit and Loss Appropriation Account	  */
	public void setPLApprAcct_ID (int PLApprAcct_ID);

	/** Get Profit and Loss Appropriation Account	  */
	public int getPLApprAcct_ID();

	public org.compiere.model.I_C_ElementValue getPLApprAcct() throws RuntimeException;

    /** Column name QuarryExp_Acct */
    public static final String COLUMNNAME_QuarryExp_Acct = "QuarryExp_Acct";

	/** Set Quarry Expense	  */
	public void setQuarryExp_Acct (int QuarryExp_Acct);

	/** Get Quarry Expense	  */
	public int getQuarryExp_Acct();

	public org.compiere.model.I_C_ElementValue getQuarryExp_A() throws RuntimeException;

    /** Column name QuarryRent_Acct */
    public static final String COLUMNNAME_QuarryRent_Acct = "QuarryRent_Acct";

	/** Set Quarry Rent	  */
	public void setQuarryRent_Acct (int QuarryRent_Acct);

	/** Get Quarry Rent	  */
	public int getQuarryRent_Acct();

	public org.compiere.model.I_C_ElementValue getQuarryRent_A() throws RuntimeException;

    /** Column name SalariesAdvanceAcct_ID */
    public static final String COLUMNNAME_SalariesAdvanceAcct_ID = "SalariesAdvanceAcct_ID";

	/** Set Salary Advance	  */
	public void setSalariesAdvanceAcct_ID (int SalariesAdvanceAcct_ID);

	/** Get Salary Advance	  */
	public int getSalariesAdvanceAcct_ID();

	public org.compiere.model.I_C_ElementValue getSalariesAdvanceAcct() throws RuntimeException;

    /** Column name SalariesExpenseAcct */
    public static final String COLUMNNAME_SalariesExpenseAcct = "SalariesExpenseAcct";

	/** Set Salary Expense	  */
	public void setSalariesExpenseAcct (int SalariesExpenseAcct);

	/** Get Salary Expense	  */
	public int getSalariesExpenseAcct();

	public org.compiere.model.I_C_ElementValue getSalariesExpenseA() throws RuntimeException;

    /** Column name SalaryPayable_Acct */
    public static final String COLUMNNAME_SalaryPayable_Acct = "SalaryPayable_Acct";

	/** Set Salary Payable	  */
	public void setSalaryPayable_Acct (int SalaryPayable_Acct);

	/** Get Salary Payable	  */
	public int getSalaryPayable_Acct();

	public org.compiere.model.I_C_ElementValue getSalaryPayable_A() throws RuntimeException;

    /** Column name SalesDiscountAcct_ID */
    public static final String COLUMNNAME_SalesDiscountAcct_ID = "SalesDiscountAcct_ID";

	/** Set Sales Discount Account	  */
	public void setSalesDiscountAcct_ID (int SalesDiscountAcct_ID);

	/** Get Sales Discount Account	  */
	public int getSalesDiscountAcct_ID();

	public org.compiere.model.I_C_ElementValue getSalesDiscountAcct() throws RuntimeException;

    /** Column name TF_GLPosting_Config_ID */
    public static final String COLUMNNAME_TF_GLPosting_Config_ID = "TF_GLPosting_Config_ID";

	/** Set GL Posting Configuratoin	  */
	public void setTF_GLPosting_Config_ID (int TF_GLPosting_Config_ID);

	/** Get GL Posting Configuratoin	  */
	public int getTF_GLPosting_Config_ID();

    /** Column name TF_GLPosting_Config_UU */
    public static final String COLUMNNAME_TF_GLPosting_Config_UU = "TF_GLPosting_Config_UU";

	/** Set TF_GLPosting_Config_UU	  */
	public void setTF_GLPosting_Config_UU (String TF_GLPosting_Config_UU);

	/** Get TF_GLPosting_Config_UU	  */
	public String getTF_GLPosting_Config_UU();

    /** Column name TipsExpenseAcct_ID */
    public static final String COLUMNNAME_TipsExpenseAcct_ID = "TipsExpenseAcct_ID";

	/** Set Tips Expense	  */
	public void setTipsExpenseAcct_ID (int TipsExpenseAcct_ID);

	/** Get Tips Expense	  */
	public int getTipsExpenseAcct_ID();

	public org.compiere.model.I_C_ElementValue getTipsExpenseAcct() throws RuntimeException;

    /** Column name TransporterInvoiceDocType_ID */
    public static final String COLUMNNAME_TransporterInvoiceDocType_ID = "TransporterInvoiceDocType_ID";

	/** Set Transporter Invoice Doc Type	  */
	public void setTransporterInvoiceDocType_ID (int TransporterInvoiceDocType_ID);

	/** Get Transporter Invoice Doc Type	  */
	public int getTransporterInvoiceDocType_ID();

	public org.compiere.model.I_C_DocType getTransporterInvoiceDocType() throws RuntimeException;

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

    /** Column name VehicleExp_Acct */
    public static final String COLUMNNAME_VehicleExp_Acct = "VehicleExp_Acct";

	/** Set Vehicle Expense	  */
	public void setVehicleExp_Acct (int VehicleExp_Acct);

	/** Get Vehicle Expense	  */
	public int getVehicleExp_Acct();

	public org.compiere.model.I_C_ElementValue getVehicleExp_A() throws RuntimeException;

    /** Column name VehicleRent_Acct */
    public static final String COLUMNNAME_VehicleRent_Acct = "VehicleRent_Acct";

	/** Set Vehicle Rent	  */
	public void setVehicleRent_Acct (int VehicleRent_Acct);

	/** Get Vehicle Rent	  */
	public int getVehicleRent_Acct();

	public org.compiere.model.I_C_ElementValue getVehicleRent_A() throws RuntimeException;

    /** Column name WageAdvanceAcct_ID */
    public static final String COLUMNNAME_WageAdvanceAcct_ID = "WageAdvanceAcct_ID";

	/** Set Wage Advance	  */
	public void setWageAdvanceAcct_ID (int WageAdvanceAcct_ID);

	/** Get Wage Advance	  */
	public int getWageAdvanceAcct_ID();

	public org.compiere.model.I_C_ElementValue getWageAdvanceAcct() throws RuntimeException;

    /** Column name WageExpenseAcct_ID */
    public static final String COLUMNNAME_WageExpenseAcct_ID = "WageExpenseAcct_ID";

	/** Set Wage Expense	  */
	public void setWageExpenseAcct_ID (int WageExpenseAcct_ID);

	/** Get Wage Expense	  */
	public int getWageExpenseAcct_ID();

	public org.compiere.model.I_C_ElementValue getWageExpenseAcct() throws RuntimeException;

    /** Column name WageIncentiveAcct_ID */
    public static final String COLUMNNAME_WageIncentiveAcct_ID = "WageIncentiveAcct_ID";

	/** Set Wage Incentive	  */
	public void setWageIncentiveAcct_ID (int WageIncentiveAcct_ID);

	/** Get Wage Incentive	  */
	public int getWageIncentiveAcct_ID();

	public org.compiere.model.I_C_ElementValue getWageIncentiveAcct() throws RuntimeException;

    /** Column name WagePayableAcct_ID */
    public static final String COLUMNNAME_WagePayableAcct_ID = "WagePayableAcct_ID";

	/** Set Wage Payable	  */
	public void setWagePayableAcct_ID (int WagePayableAcct_ID);

	/** Get Wage Payable	  */
	public int getWagePayableAcct_ID();

	public org.compiere.model.I_C_ElementValue getWagePayableAcct() throws RuntimeException;
}
