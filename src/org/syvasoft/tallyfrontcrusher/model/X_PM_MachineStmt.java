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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for PM_MachineStmt
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_PM_MachineStmt extends PO implements I_PM_MachineStmt, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20211117L;

    /** Standard Constructor */
    public X_PM_MachineStmt (Properties ctx, int PM_MachineStmt_ID, String trxName)
    {
      super (ctx, PM_MachineStmt_ID, trxName);
      /** if (PM_MachineStmt_ID == 0)
        {
			setDateAcct (new Timestamp( System.currentTimeMillis() ));
			setPM_Machinery_ID (0);
			setPM_MachineStmt_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PM_MachineStmt (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PM_MachineStmt[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_Activity getC_Activity() throws RuntimeException
    {
		return (org.compiere.model.I_C_Activity)MTable.get(getCtx(), org.compiere.model.I_C_Activity.Table_Name)
			.getPO(getC_Activity_ID(), get_TrxName());	}

	/** Set Activity.
		@param C_Activity_ID 
		Business Activity
	  */
	public void setC_Activity_ID (int C_Activity_ID)
	{
		if (C_Activity_ID < 1) 
			set_Value (COLUMNNAME_C_Activity_ID, null);
		else 
			set_Value (COLUMNNAME_C_Activity_ID, Integer.valueOf(C_Activity_ID));
	}

	/** Get Activity.
		@return Business Activity
	  */
	public int getC_Activity_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Activity_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

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

	public org.compiere.model.I_C_Payment getC_Payment() throws RuntimeException
    {
		return (org.compiere.model.I_C_Payment)MTable.get(getCtx(), org.compiere.model.I_C_Payment.Table_Name)
			.getPO(getC_Payment_ID(), get_TrxName());	}

	/** Set Payment.
		@param C_Payment_ID 
		Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID)
	{
		if (C_Payment_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Payment_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Payment_ID, Integer.valueOf(C_Payment_ID));
	}

	/** Get Payment.
		@return Payment identifier
	  */
	public int getC_Payment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Payment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getC_UOM_ID(), get_TrxName());	}

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Account Date.
		@param DateAcct 
		Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct)
	{
		set_ValueNoCheck (COLUMNNAME_DateAcct, DateAcct);
	}

	/** Get Account Date.
		@return Accounting Date
	  */
	public Timestamp getDateAcct () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateAcct);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Expense.
		@param Expense Expense	  */
	public void setExpense (BigDecimal Expense)
	{
		set_Value (COLUMNNAME_Expense, Expense);
	}

	/** Get Expense.
		@return Expense	  */
	public BigDecimal getExpense () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Expense);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Income.
		@param Income Income	  */
	public void setIncome (BigDecimal Income)
	{
		set_Value (COLUMNNAME_Income, Income);
	}

	/** Get Income.
		@return Income	  */
	public BigDecimal getIncome () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Income);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_PM_Job getPM_Job() throws RuntimeException
    {
		return (I_PM_Job)MTable.get(getCtx(), I_PM_Job.Table_Name)
			.getPO(getPM_Job_ID(), get_TrxName());	}

	/** Set Maintenance Job.
		@param PM_Job_ID Maintenance Job	  */
	public void setPM_Job_ID (int PM_Job_ID)
	{
		if (PM_Job_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PM_Job_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PM_Job_ID, Integer.valueOf(PM_Job_ID));
	}

	/** Get Maintenance Job.
		@return Maintenance Job	  */
	public int getPM_Job_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PM_Job_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_PM_Machinery getPM_Machinery() throws RuntimeException
    {
		return (I_PM_Machinery)MTable.get(getCtx(), I_PM_Machinery.Table_Name)
			.getPO(getPM_Machinery_ID(), get_TrxName());	}

	/** Set Machinery.
		@param PM_Machinery_ID Machinery	  */
	public void setPM_Machinery_ID (int PM_Machinery_ID)
	{
		if (PM_Machinery_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PM_Machinery_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PM_Machinery_ID, Integer.valueOf(PM_Machinery_ID));
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

	/** Set Machinery Statement.
		@param PM_MachineStmt_ID Machinery Statement	  */
	public void setPM_MachineStmt_ID (int PM_MachineStmt_ID)
	{
		if (PM_MachineStmt_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PM_MachineStmt_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PM_MachineStmt_ID, Integer.valueOf(PM_MachineStmt_ID));
	}

	/** Get Machinery Statement.
		@return Machinery Statement	  */
	public int getPM_MachineStmt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PM_MachineStmt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set PM_MachineStmt_UU.
		@param PM_MachineStmt_UU PM_MachineStmt_UU	  */
	public void setPM_MachineStmt_UU (String PM_MachineStmt_UU)
	{
		set_ValueNoCheck (COLUMNNAME_PM_MachineStmt_UU, PM_MachineStmt_UU);
	}

	/** Get PM_MachineStmt_UU.
		@return PM_MachineStmt_UU	  */
	public String getPM_MachineStmt_UU () 
	{
		return (String)get_Value(COLUMNNAME_PM_MachineStmt_UU);
	}

	/** Set Quantity.
		@param Qty 
		Quantity
	  */
	public void setQty (BigDecimal Qty)
	{
		set_Value (COLUMNNAME_Qty, Qty);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Jack Hammer = J */
	public static final String QUARRYPRODUCTIONTYPE_JackHammer = "J";
	/** Graweller = G */
	public static final String QUARRYPRODUCTIONTYPE_Graweller = "G";
	/** Set Quarry Production Type.
		@param QuarryProductionType Quarry Production Type	  */
	public void setQuarryProductionType (String QuarryProductionType)
	{

		set_Value (COLUMNNAME_QuarryProductionType, QuarryProductionType);
	}

	/** Get Quarry Production Type.
		@return Quarry Production Type	  */
	public String getQuarryProductionType () 
	{
		return (String)get_Value(COLUMNNAME_QuarryProductionType);
	}

	/** Set Rate.
		@param Rate 
		Rate or Tax or Exchange
	  */
	public void setRate (BigDecimal Rate)
	{
		set_ValueNoCheck (COLUMNNAME_Rate, Rate);
	}

	/** Get Rate.
		@return Rate or Tax or Exchange
	  */
	public BigDecimal getRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_TF_Fuel_Issue getTF_Fuel_Issue() throws RuntimeException
    {
		return (I_TF_Fuel_Issue)MTable.get(getCtx(), I_TF_Fuel_Issue.Table_Name)
			.getPO(getTF_Fuel_Issue_ID(), get_TrxName());	}

	/** Set Fuel Issue.
		@param TF_Fuel_Issue_ID Fuel Issue	  */
	public void setTF_Fuel_Issue_ID (int TF_Fuel_Issue_ID)
	{
		if (TF_Fuel_Issue_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_Fuel_Issue_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_Fuel_Issue_ID, Integer.valueOf(TF_Fuel_Issue_ID));
	}

	/** Get Fuel Issue.
		@return Fuel Issue	  */
	public int getTF_Fuel_Issue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_Fuel_Issue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_TF_InstantPettyCash getTF_InstantPettyCash() throws RuntimeException
    {
		return (I_TF_InstantPettyCash)MTable.get(getCtx(), I_TF_InstantPettyCash.Table_Name)
			.getPO(getTF_InstantPettyCash_ID(), get_TrxName());	}

	/** Set Instant Petty Cash Book Entry.
		@param TF_InstantPettyCash_ID Instant Petty Cash Book Entry	  */
	public void setTF_InstantPettyCash_ID (int TF_InstantPettyCash_ID)
	{
		if (TF_InstantPettyCash_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_InstantPettyCash_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_InstantPettyCash_ID, Integer.valueOf(TF_InstantPettyCash_ID));
	}

	/** Get Instant Petty Cash Book Entry.
		@return Instant Petty Cash Book Entry	  */
	public int getTF_InstantPettyCash_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_InstantPettyCash_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_TF_TripSheet getTF_TripSheet() throws RuntimeException
    {
		return (I_TF_TripSheet)MTable.get(getCtx(), I_TF_TripSheet.Table_Name)
			.getPO(getTF_TripSheet_ID(), get_TrxName());	}

	/** Set Trip Sheet.
		@param TF_TripSheet_ID Trip Sheet	  */
	public void setTF_TripSheet_ID (int TF_TripSheet_ID)
	{
		if (TF_TripSheet_ID < 1) 
			set_Value (COLUMNNAME_TF_TripSheet_ID, null);
		else 
			set_Value (COLUMNNAME_TF_TripSheet_ID, Integer.valueOf(TF_TripSheet_ID));
	}

	/** Get Trip Sheet.
		@return Trip Sheet	  */
	public int getTF_TripSheet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_TripSheet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getUser1() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getUser1_ID(), get_TrxName());	}

	/** Set Department.
		@param User1_ID 
		User defined list element #1
	  */
	public void setUser1_ID (int User1_ID)
	{
		if (User1_ID < 1) 
			set_Value (COLUMNNAME_User1_ID, null);
		else 
			set_Value (COLUMNNAME_User1_ID, Integer.valueOf(User1_ID));
	}

	/** Get Department.
		@return User defined list element #1
	  */
	public int getUser1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_User1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}