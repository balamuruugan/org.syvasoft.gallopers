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

/** Generated Model for TF_TripSheetProduct
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_TripSheetProduct extends PO implements I_TF_TripSheetProduct, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210618L;

    /** Standard Constructor */
    public X_TF_TripSheetProduct (Properties ctx, int TF_TripSheetProduct_ID, String trxName)
    {
      super (ctx, TF_TripSheetProduct_ID, trxName);
      /** if (TF_TripSheetProduct_ID == 0)
        {
			setM_Product_Category_ID (0);
			setM_Product_ID (0);
			setTF_TripSheetProduct_ID (0);
			setTotalMT (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_TF_TripSheetProduct (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_TripSheetProduct[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public I_TF_TripSheet getTF_TripSheet() throws RuntimeException
    {
		return (I_TF_TripSheet)MTable.get(getCtx(), I_TF_TripSheet.Table_Name)
			.getPO(getTF_TripSheet_ID(), get_TrxName());	}

	/** Set Trip Sheet.
		@param TF_TripSheet_ID Trip Sheet	  */
	public void setTF_TripSheet_ID (int TF_TripSheet_ID)
	{
		if (TF_TripSheet_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_TripSheet_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_TripSheet_ID, Integer.valueOf(TF_TripSheet_ID));
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

	/** Set Trip Sheet Product Detail.
		@param TF_TripSheetProduct_ID Trip Sheet Product Detail	  */
	public void setTF_TripSheetProduct_ID (int TF_TripSheetProduct_ID)
	{
		if (TF_TripSheetProduct_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_TripSheetProduct_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_TripSheetProduct_ID, Integer.valueOf(TF_TripSheetProduct_ID));
	}

	/** Get Trip Sheet Product Detail.
		@return Trip Sheet Product Detail	  */
	public int getTF_TripSheetProduct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_TripSheetProduct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_TripSheetProduct_UU.
		@param TF_TripSheetProduct_UU TF_TripSheetProduct_UU	  */
	public void setTF_TripSheetProduct_UU (String TF_TripSheetProduct_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_TripSheetProduct_UU, TF_TripSheetProduct_UU);
	}

	/** Get TF_TripSheetProduct_UU.
		@return TF_TripSheetProduct_UU	  */
	public String getTF_TripSheetProduct_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_TripSheetProduct_UU);
	}

	/** Set Total MT (Manual).
		@param TotalMT Total MT (Manual)	  */
	public void setTotalMT (BigDecimal TotalMT)
	{
		set_Value (COLUMNNAME_TotalMT, TotalMT);
	}

	/** Get Total MT (Manual).
		@return Total MT (Manual)	  */
	public BigDecimal getTotalMT () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalMT);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}