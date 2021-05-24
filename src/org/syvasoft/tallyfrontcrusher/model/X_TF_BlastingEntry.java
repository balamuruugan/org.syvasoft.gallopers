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

/** Generated Model for TF_BlastingEntry
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_BlastingEntry extends PO implements I_TF_BlastingEntry, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210524L;

    /** Standard Constructor */
    public X_TF_BlastingEntry (Properties ctx, int TF_BlastingEntry_ID, String trxName)
    {
      super (ctx, TF_BlastingEntry_ID, trxName);
      /** if (TF_BlastingEntry_ID == 0)
        {
			setC_UOM_ID (0);
			setM_Locator_ID (0);
			setM_Product_Category_ID (0);
			setM_Product_ID (0);
			setTF_BlastingEntry_ID (0);
			setTF_DrillingBlasting_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_BlastingEntry (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_TF_BlastingEntry[")
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

	public I_M_Locator getM_Locator() throws RuntimeException
    {
		return (I_M_Locator)MTable.get(getCtx(), I_M_Locator.Table_Name)
			.getPO(getM_Locator_ID(), get_TrxName());	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Locator_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product_Category getM_Product_Category() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product_Category)MTable.get(getCtx(), org.compiere.model.I_M_Product_Category.Table_Name)
			.getPO(getM_Product_Category_ID(), get_TrxName());	}

	/** Set Product Category.
		@param M_Product_Category_ID 
		Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID)
	{
		if (M_Product_Category_ID < 1) 
			set_Value (COLUMNNAME_M_Product_Category_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_Category_ID, Integer.valueOf(M_Product_Category_ID));
	}

	/** Get Product Category.
		@return Category of a Product
	  */
	public int getM_Product_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Category_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Price.
		@param Price 
		Price
	  */
	public void setPrice (BigDecimal Price)
	{
		set_Value (COLUMNNAME_Price, Price);
	}

	/** Get Price.
		@return Price
	  */
	public BigDecimal getPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Price);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Available Quantity.
		@param QtyAvailable 
		Available Quantity (On Hand - Reserved)
	  */
	public void setQtyAvailable (BigDecimal QtyAvailable)
	{
		set_ValueNoCheck (COLUMNNAME_QtyAvailable, QtyAvailable);
	}

	/** Get Available Quantity.
		@return Available Quantity (On Hand - Reserved)
	  */
	public BigDecimal getQtyAvailable () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyAvailable);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set TF_BlastingEntry.
		@param TF_BlastingEntry_ID TF_BlastingEntry	  */
	public void setTF_BlastingEntry_ID (int TF_BlastingEntry_ID)
	{
		if (TF_BlastingEntry_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_BlastingEntry_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_BlastingEntry_ID, Integer.valueOf(TF_BlastingEntry_ID));
	}

	/** Get TF_BlastingEntry.
		@return TF_BlastingEntry	  */
	public int getTF_BlastingEntry_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_BlastingEntry_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_BlastingEntry_UU.
		@param TF_BlastingEntry_UU TF_BlastingEntry_UU	  */
	public void setTF_BlastingEntry_UU (String TF_BlastingEntry_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_BlastingEntry_UU, TF_BlastingEntry_UU);
	}

	/** Get TF_BlastingEntry_UU.
		@return TF_BlastingEntry_UU	  */
	public String getTF_BlastingEntry_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_BlastingEntry_UU);
	}

	public I_TF_DrillingBlasting getTF_DrillingBlasting() throws RuntimeException
    {
		return (I_TF_DrillingBlasting)MTable.get(getCtx(), I_TF_DrillingBlasting.Table_Name)
			.getPO(getTF_DrillingBlasting_ID(), get_TrxName());	}

	/** Set TF_DrillingBlasting.
		@param TF_DrillingBlasting_ID TF_DrillingBlasting	  */
	public void setTF_DrillingBlasting_ID (int TF_DrillingBlasting_ID)
	{
		if (TF_DrillingBlasting_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_DrillingBlasting_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_DrillingBlasting_ID, Integer.valueOf(TF_DrillingBlasting_ID));
	}

	/** Get TF_DrillingBlasting.
		@return TF_DrillingBlasting	  */
	public int getTF_DrillingBlasting_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_DrillingBlasting_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
}