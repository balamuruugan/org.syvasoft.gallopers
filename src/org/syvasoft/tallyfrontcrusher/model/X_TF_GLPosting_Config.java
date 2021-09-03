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
/** Generated Model - DO NOT CHANGE */
package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for TF_GLPosting_Config
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_GLPosting_Config extends PO implements I_TF_GLPosting_Config, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210903L;

    /** Standard Constructor */
    public X_TF_GLPosting_Config (Properties ctx, int TF_GLPosting_Config_ID, String trxName)
    {
      super (ctx, TF_GLPosting_Config_ID, trxName);
      /** if (TF_GLPosting_Config_ID == 0)
        {
			setC_AcctSchema_ID (0);
			setJobworkExpenseAcct_ID (0);
			setJobworkExpenseVarianceAcct_ID (0);
			setJobworkPayableClearingAcct_ID (0);
			setTF_GLPosting_Config_ID (0);
			setVehicleExp_Acct (0);
			setVehicleRent_Acct (0);
        } */
    }

    /** Load Constructor */
    public X_TF_GLPosting_Config (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_TF_GLPosting_Config[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_ElementValue getBP_CreditBalanceAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getBP_CreditBalanceAcct_ID(), get_TrxName());	}

	/** Set BP Credit Balance Account.
		@param BP_CreditBalanceAcct_ID BP Credit Balance Account	  */
	public void setBP_CreditBalanceAcct_ID (int BP_CreditBalanceAcct_ID)
	{
		if (BP_CreditBalanceAcct_ID < 1) 
			set_Value (COLUMNNAME_BP_CreditBalanceAcct_ID, null);
		else 
			set_Value (COLUMNNAME_BP_CreditBalanceAcct_ID, Integer.valueOf(BP_CreditBalanceAcct_ID));
	}

	/** Get BP Credit Balance Account.
		@return BP Credit Balance Account	  */
	public int getBP_CreditBalanceAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BP_CreditBalanceAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_DocType getBP_CreditDocType() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getBP_CreditDocType_ID(), get_TrxName());	}

	/** Set Opening Vendor Inv Document Type.
		@param BP_CreditDocType_ID 
		Document Type used for setting opening balance for vendor
	  */
	public void setBP_CreditDocType_ID (int BP_CreditDocType_ID)
	{
		if (BP_CreditDocType_ID < 1) 
			set_Value (COLUMNNAME_BP_CreditDocType_ID, null);
		else 
			set_Value (COLUMNNAME_BP_CreditDocType_ID, Integer.valueOf(BP_CreditDocType_ID));
	}

	/** Get Opening Vendor Inv Document Type.
		@return Document Type used for setting opening balance for vendor
	  */
	public int getBP_CreditDocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BP_CreditDocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getBP_DebitBalanceAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getBP_DebitBalanceAcct_ID(), get_TrxName());	}

	/** Set BP Debit Balance Account.
		@param BP_DebitBalanceAcct_ID BP Debit Balance Account	  */
	public void setBP_DebitBalanceAcct_ID (int BP_DebitBalanceAcct_ID)
	{
		if (BP_DebitBalanceAcct_ID < 1) 
			set_Value (COLUMNNAME_BP_DebitBalanceAcct_ID, null);
		else 
			set_Value (COLUMNNAME_BP_DebitBalanceAcct_ID, Integer.valueOf(BP_DebitBalanceAcct_ID));
	}

	/** Get BP Debit Balance Account.
		@return BP Debit Balance Account	  */
	public int getBP_DebitBalanceAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BP_DebitBalanceAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_DocType getBP_DebitDocType() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getBP_DebitDocType_ID(), get_TrxName());	}

	/** Set Opening Cust. Inv Document Type.
		@param BP_DebitDocType_ID 
		Document Type used for setting opening balance for customer
	  */
	public void setBP_DebitDocType_ID (int BP_DebitDocType_ID)
	{
		if (BP_DebitDocType_ID < 1) 
			set_Value (COLUMNNAME_BP_DebitDocType_ID, null);
		else 
			set_Value (COLUMNNAME_BP_DebitDocType_ID, Integer.valueOf(BP_DebitDocType_ID));
	}

	/** Get Opening Cust. Inv Document Type.
		@return Document Type used for setting opening balance for customer
	  */
	public int getBP_DebitDocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BP_DebitDocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_AcctSchema getC_AcctSchema() throws RuntimeException
    {
		return (org.compiere.model.I_C_AcctSchema)MTable.get(getCtx(), org.compiere.model.I_C_AcctSchema.Table_Name)
			.getPO(getC_AcctSchema_ID(), get_TrxName());	}

	/** Set Accounting Schema.
		@param C_AcctSchema_ID 
		Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID)
	{
		if (C_AcctSchema_ID < 1) 
			set_Value (COLUMNNAME_C_AcctSchema_ID, null);
		else 
			set_Value (COLUMNNAME_C_AcctSchema_ID, Integer.valueOf(C_AcctSchema_ID));
	}

	/** Get Accounting Schema.
		@return Rules for accounting
	  */
	public int getC_AcctSchema_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_AcctSchema_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BankAccount getC_BankAccount() throws RuntimeException
    {
		return (org.compiere.model.I_C_BankAccount)MTable.get(getCtx(), org.compiere.model.I_C_BankAccount.Table_Name)
			.getPO(getC_BankAccount_ID(), get_TrxName());	}

	/** Set Bank/Cash Account.
		@param C_BankAccount_ID 
		Account at the Bank
	  */
	public void setC_BankAccount_ID (int C_BankAccount_ID)
	{
		if (C_BankAccount_ID < 1) 
			set_Value (COLUMNNAME_C_BankAccount_ID, null);
		else 
			set_Value (COLUMNNAME_C_BankAccount_ID, Integer.valueOf(C_BankAccount_ID));
	}

	/** Get Bank/Cash Account.
		@return Account at the Bank
	  */
	public int getC_BankAccount_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BankAccount_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getC_ElementGSTPayable() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getC_ElementGSTPayable_ID(), get_TrxName());	}

	/** Set GST Payable.
		@param C_ElementGSTPayable_ID GST Payable	  */
	public void setC_ElementGSTPayable_ID (int C_ElementGSTPayable_ID)
	{
		if (C_ElementGSTPayable_ID < 1) 
			set_Value (COLUMNNAME_C_ElementGSTPayable_ID, null);
		else 
			set_Value (COLUMNNAME_C_ElementGSTPayable_ID, Integer.valueOf(C_ElementGSTPayable_ID));
	}

	/** Get GST Payable.
		@return GST Payable	  */
	public int getC_ElementGSTPayable_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ElementGSTPayable_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getC_ElementValueCGST() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getC_ElementValueCGST_ID(), get_TrxName());	}

	/** Set CGST Account Head.
		@param C_ElementValueCGST_ID CGST Account Head	  */
	public void setC_ElementValueCGST_ID (int C_ElementValueCGST_ID)
	{
		if (C_ElementValueCGST_ID < 1) 
			set_Value (COLUMNNAME_C_ElementValueCGST_ID, null);
		else 
			set_Value (COLUMNNAME_C_ElementValueCGST_ID, Integer.valueOf(C_ElementValueCGST_ID));
	}

	/** Get CGST Account Head.
		@return CGST Account Head	  */
	public int getC_ElementValueCGST_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ElementValueCGST_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getC_ElementValueIGST() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getC_ElementValueIGST_ID(), get_TrxName());	}

	/** Set IGST Account Head.
		@param C_ElementValueIGST_ID IGST Account Head	  */
	public void setC_ElementValueIGST_ID (int C_ElementValueIGST_ID)
	{
		if (C_ElementValueIGST_ID < 1) 
			set_Value (COLUMNNAME_C_ElementValueIGST_ID, null);
		else 
			set_Value (COLUMNNAME_C_ElementValueIGST_ID, Integer.valueOf(C_ElementValueIGST_ID));
	}

	/** Get IGST Account Head.
		@return IGST Account Head	  */
	public int getC_ElementValueIGST_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ElementValueIGST_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getC_ElementValuePermitExp() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getC_ElementValuePermitExp_ID(), get_TrxName());	}

	/** Set Permit Expense Account Head.
		@param C_ElementValuePermitExp_ID Permit Expense Account Head	  */
	public void setC_ElementValuePermitExp_ID (int C_ElementValuePermitExp_ID)
	{
		if (C_ElementValuePermitExp_ID < 1) 
			set_Value (COLUMNNAME_C_ElementValuePermitExp_ID, null);
		else 
			set_Value (COLUMNNAME_C_ElementValuePermitExp_ID, Integer.valueOf(C_ElementValuePermitExp_ID));
	}

	/** Get Permit Expense Account Head.
		@return Permit Expense Account Head	  */
	public int getC_ElementValuePermitExp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ElementValuePermitExp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getC_ElementValueSGST() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getC_ElementValueSGST_ID(), get_TrxName());	}

	/** Set SGST Account Head.
		@param C_ElementValueSGST_ID SGST Account Head	  */
	public void setC_ElementValueSGST_ID (int C_ElementValueSGST_ID)
	{
		if (C_ElementValueSGST_ID < 1) 
			set_Value (COLUMNNAME_C_ElementValueSGST_ID, null);
		else 
			set_Value (COLUMNNAME_C_ElementValueSGST_ID, Integer.valueOf(C_ElementValueSGST_ID));
	}

	/** Get SGST Account Head.
		@return SGST Account Head	  */
	public int getC_ElementValueSGST_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ElementValueSGST_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getC_EleValueProdBonus() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getC_EleValueProdBonus_ID(), get_TrxName());	}

	/** Set Production Bonus.
		@param C_EleValueProdBonus_ID Production Bonus	  */
	public void setC_EleValueProdBonus_ID (int C_EleValueProdBonus_ID)
	{
		if (C_EleValueProdBonus_ID < 1) 
			set_Value (COLUMNNAME_C_EleValueProdBonus_ID, null);
		else 
			set_Value (COLUMNNAME_C_EleValueProdBonus_ID, Integer.valueOf(C_EleValueProdBonus_ID));
	}

	/** Get Production Bonus.
		@return Production Bonus	  */
	public int getC_EleValueProdBonus_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_EleValueProdBonus_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_DocType getDebitNote_DocType() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getDebitNote_DocType_ID(), get_TrxName());	}

	/** Set Debit Note Document Type.
		@param DebitNote_DocType_ID Debit Note Document Type	  */
	public void setDebitNote_DocType_ID (int DebitNote_DocType_ID)
	{
		if (DebitNote_DocType_ID < 1) 
			set_Value (COLUMNNAME_DebitNote_DocType_ID, null);
		else 
			set_Value (COLUMNNAME_DebitNote_DocType_ID, Integer.valueOf(DebitNote_DocType_ID));
	}

	/** Get Debit Note Document Type.
		@return Debit Note Document Type	  */
	public int getDebitNote_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DebitNote_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_DocType getDefaultSalesInvoiceDocType() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getDefaultSalesInvoiceDocType_ID(), get_TrxName());	}

	/** Set Vehicle Rent Sales Invoice Doc Type.
		@param DefaultSalesInvoiceDocType_ID 
		Vehicle Rent Sales Invoice
	  */
	public void setDefaultSalesInvoiceDocType_ID (int DefaultSalesInvoiceDocType_ID)
	{
		if (DefaultSalesInvoiceDocType_ID < 1) 
			set_Value (COLUMNNAME_DefaultSalesInvoiceDocType_ID, null);
		else 
			set_Value (COLUMNNAME_DefaultSalesInvoiceDocType_ID, Integer.valueOf(DefaultSalesInvoiceDocType_ID));
	}

	/** Get Vehicle Rent Sales Invoice Doc Type.
		@return Vehicle Rent Sales Invoice
	  */
	public int getDefaultSalesInvoiceDocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DefaultSalesInvoiceDocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getFuel_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getFuel_Product_ID(), get_TrxName());	}

	/** Set Fuel.
		@param Fuel_Product_ID 
		Fuel
	  */
	public void setFuel_Product_ID (int Fuel_Product_ID)
	{
		if (Fuel_Product_ID < 1) 
			set_Value (COLUMNNAME_Fuel_Product_ID, null);
		else 
			set_Value (COLUMNNAME_Fuel_Product_ID, Integer.valueOf(Fuel_Product_ID));
	}

	/** Get Fuel.
		@return Fuel
	  */
	public int getFuel_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Fuel_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Charge getFuelExpense_Charge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getFuelExpense_Charge_ID(), get_TrxName());	}

	/** Set Fuel Expense Charge.
		@param FuelExpense_Charge_ID Fuel Expense Charge	  */
	public void setFuelExpense_Charge_ID (int FuelExpense_Charge_ID)
	{
		if (FuelExpense_Charge_ID < 1) 
			set_Value (COLUMNNAME_FuelExpense_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_FuelExpense_Charge_ID, Integer.valueOf(FuelExpense_Charge_ID));
	}

	/** Get Fuel Expense Charge.
		@return Fuel Expense Charge	  */
	public int getFuelExpense_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FuelExpense_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getIncentiveAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getIncentiveAcct_ID(), get_TrxName());	}

	/** Set Incentive.
		@param IncentiveAcct_ID Incentive	  */
	public void setIncentiveAcct_ID (int IncentiveAcct_ID)
	{
		if (IncentiveAcct_ID < 1) 
			set_Value (COLUMNNAME_IncentiveAcct_ID, null);
		else 
			set_Value (COLUMNNAME_IncentiveAcct_ID, Integer.valueOf(IncentiveAcct_ID));
	}

	/** Get Incentive.
		@return Incentive	  */
	public int getIncentiveAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IncentiveAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getInterBankInTransit() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getInterBankInTransit_ID(), get_TrxName());	}

	/** Set Inter Bank In Transit.
		@param InterBankInTransit_ID Inter Bank In Transit	  */
	public void setInterBankInTransit_ID (int InterBankInTransit_ID)
	{
		if (InterBankInTransit_ID < 1) 
			set_Value (COLUMNNAME_InterBankInTransit_ID, null);
		else 
			set_Value (COLUMNNAME_InterBankInTransit_ID, Integer.valueOf(InterBankInTransit_ID));
	}

	/** Get Inter Bank In Transit.
		@return Inter Bank In Transit	  */
	public int getInterBankInTransit_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_InterBankInTransit_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Charge getItemIssue_Charge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getItemIssue_Charge_ID(), get_TrxName());	}

	/** Set Item Issue Charge.
		@param ItemIssue_Charge_ID Item Issue Charge	  */
	public void setItemIssue_Charge_ID (int ItemIssue_Charge_ID)
	{
		if (ItemIssue_Charge_ID < 1) 
			set_Value (COLUMNNAME_ItemIssue_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_ItemIssue_Charge_ID, Integer.valueOf(ItemIssue_Charge_ID));
	}

	/** Get Item Issue Charge.
		@return Item Issue Charge	  */
	public int getItemIssue_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ItemIssue_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getJobWork_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getJobWork_Product_ID(), get_TrxName());	}

	/** Set Job Work.
		@param JobWork_Product_ID Job Work	  */
	public void setJobWork_Product_ID (int JobWork_Product_ID)
	{
		if (JobWork_Product_ID < 1) 
			set_Value (COLUMNNAME_JobWork_Product_ID, null);
		else 
			set_Value (COLUMNNAME_JobWork_Product_ID, Integer.valueOf(JobWork_Product_ID));
	}

	/** Get Job Work.
		@return Job Work	  */
	public int getJobWork_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_JobWork_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getJobworkExpenseAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getJobworkExpenseAcct_ID(), get_TrxName());	}

	/** Set Jobwork Expense.
		@param JobworkExpenseAcct_ID Jobwork Expense	  */
	public void setJobworkExpenseAcct_ID (int JobworkExpenseAcct_ID)
	{
		if (JobworkExpenseAcct_ID < 1) 
			set_Value (COLUMNNAME_JobworkExpenseAcct_ID, null);
		else 
			set_Value (COLUMNNAME_JobworkExpenseAcct_ID, Integer.valueOf(JobworkExpenseAcct_ID));
	}

	/** Get Jobwork Expense.
		@return Jobwork Expense	  */
	public int getJobworkExpenseAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_JobworkExpenseAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getJobworkExpenseVarianceAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getJobworkExpenseVarianceAcct_ID(), get_TrxName());	}

	/** Set Jobwork Expense Variance.
		@param JobworkExpenseVarianceAcct_ID Jobwork Expense Variance	  */
	public void setJobworkExpenseVarianceAcct_ID (int JobworkExpenseVarianceAcct_ID)
	{
		if (JobworkExpenseVarianceAcct_ID < 1) 
			set_Value (COLUMNNAME_JobworkExpenseVarianceAcct_ID, null);
		else 
			set_Value (COLUMNNAME_JobworkExpenseVarianceAcct_ID, Integer.valueOf(JobworkExpenseVarianceAcct_ID));
	}

	/** Get Jobwork Expense Variance.
		@return Jobwork Expense Variance	  */
	public int getJobworkExpenseVarianceAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_JobworkExpenseVarianceAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getJobworkPayableClearingAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getJobworkPayableClearingAcct_ID(), get_TrxName());	}

	/** Set Jobwork Payable Clearing.
		@param JobworkPayableClearingAcct_ID Jobwork Payable Clearing	  */
	public void setJobworkPayableClearingAcct_ID (int JobworkPayableClearingAcct_ID)
	{
		if (JobworkPayableClearingAcct_ID < 1) 
			set_Value (COLUMNNAME_JobworkPayableClearingAcct_ID, null);
		else 
			set_Value (COLUMNNAME_JobworkPayableClearingAcct_ID, Integer.valueOf(JobworkPayableClearingAcct_ID));
	}

	/** Get Jobwork Payable Clearing.
		@return Jobwork Payable Clearing	  */
	public int getJobworkPayableClearingAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_JobworkPayableClearingAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getLoan() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getLoan_ID(), get_TrxName());	}

	/** Set Loan.
		@param Loan_ID Loan	  */
	public void setLoan_ID (int Loan_ID)
	{
		if (Loan_ID < 1) 
			set_Value (COLUMNNAME_Loan_ID, null);
		else 
			set_Value (COLUMNNAME_Loan_ID, Integer.valueOf(Loan_ID));
	}

	/** Get Loan.
		@return Loan	  */
	public int getLoan_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Loan_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_DocType getMaterialIssue_DocType() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getMaterialIssue_DocType_ID(), get_TrxName());	}

	/** Set Material Issue Doc Type.
		@param MaterialIssue_DocType_ID Material Issue Doc Type	  */
	public void setMaterialIssue_DocType_ID (int MaterialIssue_DocType_ID)
	{
		if (MaterialIssue_DocType_ID < 1) 
			set_Value (COLUMNNAME_MaterialIssue_DocType_ID, null);
		else 
			set_Value (COLUMNNAME_MaterialIssue_DocType_ID, Integer.valueOf(MaterialIssue_DocType_ID));
	}

	/** Get Material Issue Doc Type.
		@return Material Issue Doc Type	  */
	public int getMaterialIssue_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MaterialIssue_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_DocType getMaterialReceipt_DocType() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getMaterialReceipt_DocType_ID(), get_TrxName());	}

	/** Set Material Receipt Doc Type.
		@param MaterialReceipt_DocType_ID Material Receipt Doc Type	  */
	public void setMaterialReceipt_DocType_ID (int MaterialReceipt_DocType_ID)
	{
		if (MaterialReceipt_DocType_ID < 1) 
			set_Value (COLUMNNAME_MaterialReceipt_DocType_ID, null);
		else 
			set_Value (COLUMNNAME_MaterialReceipt_DocType_ID, Integer.valueOf(MaterialReceipt_DocType_ID));
	}

	/** Get Material Receipt Doc Type.
		@return Material Receipt Doc Type	  */
	public int getMaterialReceipt_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MaterialReceipt_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getOpeningBalAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getOpeningBalAcct_ID(), get_TrxName());	}

	/** Set Opening Balance Offset Account.
		@param OpeningBalAcct_ID 
		Offset Account
	  */
	public void setOpeningBalAcct_ID (int OpeningBalAcct_ID)
	{
		if (OpeningBalAcct_ID < 1) 
			set_Value (COLUMNNAME_OpeningBalAcct_ID, null);
		else 
			set_Value (COLUMNNAME_OpeningBalAcct_ID, Integer.valueOf(OpeningBalAcct_ID));
	}

	/** Get Opening Balance Offset Account.
		@return Offset Account
	  */
	public int getOpeningBalAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_OpeningBalAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Charge getOwnMining_Charge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getOwnMining_Charge_ID(), get_TrxName());	}

	/** Set Own Boulder Production Charge.
		@param OwnMining_Charge_ID Own Boulder Production Charge	  */
	public void setOwnMining_Charge_ID (int OwnMining_Charge_ID)
	{
		if (OwnMining_Charge_ID < 1) 
			set_Value (COLUMNNAME_OwnMining_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_OwnMining_Charge_ID, Integer.valueOf(OwnMining_Charge_ID));
	}

	/** Get Own Boulder Production Charge.
		@return Own Boulder Production Charge	  */
	public int getOwnMining_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_OwnMining_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getPLAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getPLAcct_ID(), get_TrxName());	}

	/** Set Profit and Loss Account.
		@param PLAcct_ID Profit and Loss Account	  */
	public void setPLAcct_ID (int PLAcct_ID)
	{
		if (PLAcct_ID < 1) 
			set_Value (COLUMNNAME_PLAcct_ID, null);
		else 
			set_Value (COLUMNNAME_PLAcct_ID, Integer.valueOf(PLAcct_ID));
	}

	/** Get Profit and Loss Account.
		@return Profit and Loss Account	  */
	public int getPLAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PLAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getPLApprAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getPLApprAcct_ID(), get_TrxName());	}

	/** Set Profit and Loss Appropriation Account.
		@param PLApprAcct_ID Profit and Loss Appropriation Account	  */
	public void setPLApprAcct_ID (int PLApprAcct_ID)
	{
		if (PLApprAcct_ID < 1) 
			set_Value (COLUMNNAME_PLApprAcct_ID, null);
		else 
			set_Value (COLUMNNAME_PLApprAcct_ID, Integer.valueOf(PLApprAcct_ID));
	}

	/** Get Profit and Loss Appropriation Account.
		@return Profit and Loss Appropriation Account	  */
	public int getPLApprAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PLApprAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getQuarryExp_A() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getQuarryExp_Acct(), get_TrxName());	}

	/** Set Quarry Expense.
		@param QuarryExp_Acct Quarry Expense	  */
	public void setQuarryExp_Acct (int QuarryExp_Acct)
	{
		set_Value (COLUMNNAME_QuarryExp_Acct, Integer.valueOf(QuarryExp_Acct));
	}

	/** Get Quarry Expense.
		@return Quarry Expense	  */
	public int getQuarryExp_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_QuarryExp_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getQuarryRent_A() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getQuarryRent_Acct(), get_TrxName());	}

	/** Set Quarry Rent.
		@param QuarryRent_Acct Quarry Rent	  */
	public void setQuarryRent_Acct (int QuarryRent_Acct)
	{
		set_Value (COLUMNNAME_QuarryRent_Acct, Integer.valueOf(QuarryRent_Acct));
	}

	/** Get Quarry Rent.
		@return Quarry Rent	  */
	public int getQuarryRent_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_QuarryRent_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getSalariesAdvanceAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getSalariesAdvanceAcct_ID(), get_TrxName());	}

	/** Set Salary Advance.
		@param SalariesAdvanceAcct_ID Salary Advance	  */
	public void setSalariesAdvanceAcct_ID (int SalariesAdvanceAcct_ID)
	{
		if (SalariesAdvanceAcct_ID < 1) 
			set_Value (COLUMNNAME_SalariesAdvanceAcct_ID, null);
		else 
			set_Value (COLUMNNAME_SalariesAdvanceAcct_ID, Integer.valueOf(SalariesAdvanceAcct_ID));
	}

	/** Get Salary Advance.
		@return Salary Advance	  */
	public int getSalariesAdvanceAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SalariesAdvanceAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getSalariesExpenseA() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getSalariesExpenseAcct(), get_TrxName());	}

	/** Set Salary Expense.
		@param SalariesExpenseAcct Salary Expense	  */
	public void setSalariesExpenseAcct (int SalariesExpenseAcct)
	{
		set_Value (COLUMNNAME_SalariesExpenseAcct, Integer.valueOf(SalariesExpenseAcct));
	}

	/** Get Salary Expense.
		@return Salary Expense	  */
	public int getSalariesExpenseAcct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SalariesExpenseAcct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getSalaryPayable_A() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getSalaryPayable_Acct(), get_TrxName());	}

	/** Set Salary Payable.
		@param SalaryPayable_Acct Salary Payable	  */
	public void setSalaryPayable_Acct (int SalaryPayable_Acct)
	{
		set_Value (COLUMNNAME_SalaryPayable_Acct, Integer.valueOf(SalaryPayable_Acct));
	}

	/** Get Salary Payable.
		@return Salary Payable	  */
	public int getSalaryPayable_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SalaryPayable_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getSalesDiscountAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getSalesDiscountAcct_ID(), get_TrxName());	}

	/** Set Sales Discount Account.
		@param SalesDiscountAcct_ID Sales Discount Account	  */
	public void setSalesDiscountAcct_ID (int SalesDiscountAcct_ID)
	{
		if (SalesDiscountAcct_ID < 1) 
			set_Value (COLUMNNAME_SalesDiscountAcct_ID, null);
		else 
			set_Value (COLUMNNAME_SalesDiscountAcct_ID, Integer.valueOf(SalesDiscountAcct_ID));
	}

	/** Get Sales Discount Account.
		@return Sales Discount Account	  */
	public int getSalesDiscountAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SalesDiscountAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GL Posting Configuratoin.
		@param TF_GLPosting_Config_ID GL Posting Configuratoin	  */
	public void setTF_GLPosting_Config_ID (int TF_GLPosting_Config_ID)
	{
		if (TF_GLPosting_Config_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_GLPosting_Config_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_GLPosting_Config_ID, Integer.valueOf(TF_GLPosting_Config_ID));
	}

	/** Get GL Posting Configuratoin.
		@return GL Posting Configuratoin	  */
	public int getTF_GLPosting_Config_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_GLPosting_Config_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_GLPosting_Config_UU.
		@param TF_GLPosting_Config_UU TF_GLPosting_Config_UU	  */
	public void setTF_GLPosting_Config_UU (String TF_GLPosting_Config_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_GLPosting_Config_UU, TF_GLPosting_Config_UU);
	}

	/** Get TF_GLPosting_Config_UU.
		@return TF_GLPosting_Config_UU	  */
	public String getTF_GLPosting_Config_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_GLPosting_Config_UU);
	}

	public org.compiere.model.I_C_ElementValue getTipsExpenseAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getTipsExpenseAcct_ID(), get_TrxName());	}

	/** Set Tips Expense.
		@param TipsExpenseAcct_ID Tips Expense	  */
	public void setTipsExpenseAcct_ID (int TipsExpenseAcct_ID)
	{
		if (TipsExpenseAcct_ID < 1) 
			set_Value (COLUMNNAME_TipsExpenseAcct_ID, null);
		else 
			set_Value (COLUMNNAME_TipsExpenseAcct_ID, Integer.valueOf(TipsExpenseAcct_ID));
	}

	/** Get Tips Expense.
		@return Tips Expense	  */
	public int getTipsExpenseAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TipsExpenseAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_DocType getTransporterInvoiceDocType() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getTransporterInvoiceDocType_ID(), get_TrxName());	}

	/** Set Transporter Invoice Doc Type.
		@param TransporterInvoiceDocType_ID Transporter Invoice Doc Type	  */
	public void setTransporterInvoiceDocType_ID (int TransporterInvoiceDocType_ID)
	{
		if (TransporterInvoiceDocType_ID < 1) 
			set_Value (COLUMNNAME_TransporterInvoiceDocType_ID, null);
		else 
			set_Value (COLUMNNAME_TransporterInvoiceDocType_ID, Integer.valueOf(TransporterInvoiceDocType_ID));
	}

	/** Get Transporter Invoice Doc Type.
		@return Transporter Invoice Doc Type	  */
	public int getTransporterInvoiceDocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TransporterInvoiceDocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getVehicleExp_A() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getVehicleExp_Acct(), get_TrxName());	}

	/** Set Vehicle Expense.
		@param VehicleExp_Acct Vehicle Expense	  */
	public void setVehicleExp_Acct (int VehicleExp_Acct)
	{
		set_Value (COLUMNNAME_VehicleExp_Acct, Integer.valueOf(VehicleExp_Acct));
	}

	/** Get Vehicle Expense.
		@return Vehicle Expense	  */
	public int getVehicleExp_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_VehicleExp_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getVehicleRent_A() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getVehicleRent_Acct(), get_TrxName());	}

	/** Set Vehicle Rent.
		@param VehicleRent_Acct Vehicle Rent	  */
	public void setVehicleRent_Acct (int VehicleRent_Acct)
	{
		set_Value (COLUMNNAME_VehicleRent_Acct, Integer.valueOf(VehicleRent_Acct));
	}

	/** Get Vehicle Rent.
		@return Vehicle Rent	  */
	public int getVehicleRent_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_VehicleRent_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getWageAdvanceAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getWageAdvanceAcct_ID(), get_TrxName());	}

	/** Set Wage Advance.
		@param WageAdvanceAcct_ID Wage Advance	  */
	public void setWageAdvanceAcct_ID (int WageAdvanceAcct_ID)
	{
		if (WageAdvanceAcct_ID < 1) 
			set_Value (COLUMNNAME_WageAdvanceAcct_ID, null);
		else 
			set_Value (COLUMNNAME_WageAdvanceAcct_ID, Integer.valueOf(WageAdvanceAcct_ID));
	}

	/** Get Wage Advance.
		@return Wage Advance	  */
	public int getWageAdvanceAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WageAdvanceAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getWageExpenseAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getWageExpenseAcct_ID(), get_TrxName());	}

	/** Set Wage Expense.
		@param WageExpenseAcct_ID Wage Expense	  */
	public void setWageExpenseAcct_ID (int WageExpenseAcct_ID)
	{
		if (WageExpenseAcct_ID < 1) 
			set_Value (COLUMNNAME_WageExpenseAcct_ID, null);
		else 
			set_Value (COLUMNNAME_WageExpenseAcct_ID, Integer.valueOf(WageExpenseAcct_ID));
	}

	/** Get Wage Expense.
		@return Wage Expense	  */
	public int getWageExpenseAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WageExpenseAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getWageIncentiveAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getWageIncentiveAcct_ID(), get_TrxName());	}

	/** Set Wage Incentive.
		@param WageIncentiveAcct_ID Wage Incentive	  */
	public void setWageIncentiveAcct_ID (int WageIncentiveAcct_ID)
	{
		if (WageIncentiveAcct_ID < 1) 
			set_Value (COLUMNNAME_WageIncentiveAcct_ID, null);
		else 
			set_Value (COLUMNNAME_WageIncentiveAcct_ID, Integer.valueOf(WageIncentiveAcct_ID));
	}

	/** Get Wage Incentive.
		@return Wage Incentive	  */
	public int getWageIncentiveAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WageIncentiveAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getWagePayableAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getWagePayableAcct_ID(), get_TrxName());	}

	/** Set Wage Payable.
		@param WagePayableAcct_ID Wage Payable	  */
	public void setWagePayableAcct_ID (int WagePayableAcct_ID)
	{
		if (WagePayableAcct_ID < 1) 
			set_Value (COLUMNNAME_WagePayableAcct_ID, null);
		else 
			set_Value (COLUMNNAME_WagePayableAcct_ID, Integer.valueOf(WagePayableAcct_ID));
	}

	/** Get Wage Payable.
		@return Wage Payable	  */
	public int getWagePayableAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WagePayableAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}