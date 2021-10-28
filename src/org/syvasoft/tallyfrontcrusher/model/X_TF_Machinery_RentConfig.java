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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for TF_Machinery_RentConfig
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_Machinery_RentConfig extends PO implements I_TF_Machinery_RentConfig, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20211028L;

    /** Standard Constructor */
    public X_TF_Machinery_RentConfig (Properties ctx, int TF_Machinery_RentConfig_ID, String trxName)
    {
      super (ctx, TF_Machinery_RentConfig_ID, trxName);
      /** if (TF_Machinery_RentConfig_ID == 0)
        {
			setC_UOM_ID (0);
			setTF_Machinery_RentConfig_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_Machinery_RentConfig (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_Machinery_RentConfig[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public I_PM_Machinery getPM_Machinery() throws RuntimeException
    {
		return (I_PM_Machinery)MTable.get(getCtx(), I_PM_Machinery.Table_Name)
			.getPO(getPM_Machinery_ID(), get_TrxName());	}

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

	/** Set Machinery Rent Config.
		@param TF_Machinery_RentConfig_ID Machinery Rent Config	  */
	public void setTF_Machinery_RentConfig_ID (int TF_Machinery_RentConfig_ID)
	{
		if (TF_Machinery_RentConfig_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_Machinery_RentConfig_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_Machinery_RentConfig_ID, Integer.valueOf(TF_Machinery_RentConfig_ID));
	}

	/** Get Machinery Rent Config.
		@return Machinery Rent Config	  */
	public int getTF_Machinery_RentConfig_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_Machinery_RentConfig_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_Machinery_RentConfig_UU.
		@param TF_Machinery_RentConfig_UU TF_Machinery_RentConfig_UU	  */
	public void setTF_Machinery_RentConfig_UU (String TF_Machinery_RentConfig_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_Machinery_RentConfig_UU, TF_Machinery_RentConfig_UU);
	}

	/** Get TF_Machinery_RentConfig_UU.
		@return TF_Machinery_RentConfig_UU	  */
	public String getTF_Machinery_RentConfig_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_Machinery_RentConfig_UU);
	}

	/** Set Unit Expense.
		@param UnitExpense Unit Expense	  */
	public void setUnitExpense (BigDecimal UnitExpense)
	{
		set_Value (COLUMNNAME_UnitExpense, UnitExpense);
	}

	/** Get Unit Expense.
		@return Unit Expense	  */
	public BigDecimal getUnitExpense () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_UnitExpense);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set UnitRent.
		@param UnitRent UnitRent	  */
	public void setUnitRent (BigDecimal UnitRent)
	{
		set_Value (COLUMNNAME_UnitRent, UnitRent);
	}

	/** Get UnitRent.
		@return UnitRent	  */
	public BigDecimal getUnitRent () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_UnitRent);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Sales = 1SO */
	public static final String WEIGHMENTENTRYTYPE_Sales = "1SO";
	/** Input = 2PO */
	public static final String WEIGHMENTENTRYTYPE_Input = "2PO";
	/** Own Production Receipt = 3PR */
	public static final String WEIGHMENTENTRYTYPE_OwnProductionReceipt = "3PR";
	/** Subcontract Production Receipt = 4SR */
	public static final String WEIGHMENTENTRYTYPE_SubcontractProductionReceipt = "4SR";
	/** Stock to Crusher = 5KA */
	public static final String WEIGHMENTENTRYTYPE_StockToCrusher = "5KA";
	/** Other Purchase = 8OP */
	public static final String WEIGHMENTENTRYTYPE_OtherPurchase = "8OP";
	/** Crusher to Stock = 9CA */
	public static final String WEIGHMENTENTRYTYPE_CrusherToStock = "9CA";
	/** Set Type.
		@param WeighmentEntryType Type	  */
	public void setWeighmentEntryType (String WeighmentEntryType)
	{

		set_Value (COLUMNNAME_WeighmentEntryType, WeighmentEntryType);
	}

	/** Get Type.
		@return Type	  */
	public String getWeighmentEntryType () 
	{
		return (String)get_Value(COLUMNNAME_WeighmentEntryType);
	}
}