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

/** Generated Model for TF_ProdCategorySetup
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_ProdCategorySetup extends PO implements I_TF_ProdCategorySetup, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210816L;

    /** Standard Constructor */
    public X_TF_ProdCategorySetup (Properties ctx, int TF_ProdCategorySetup_ID, String trxName)
    {
      super (ctx, TF_ProdCategorySetup_ID, trxName);
      /** if (TF_ProdCategorySetup_ID == 0)
        {
			setIsDefault (false);
// N
			setM_Locator_ID (0);
			setTF_ProdCategorySetup_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_ProdCategorySetup (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_ProdCategorySetup[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Default.
		@param IsDefault 
		Default value
	  */
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	public boolean isDefault () 
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_M_Locator getM_Locator() throws RuntimeException
    {
		return (org.compiere.model.I_M_Locator)MTable.get(getCtx(), org.compiere.model.I_M_Locator.Table_Name)
			.getPO(getM_Locator_ID(), get_TrxName());	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1) 
			set_Value (COLUMNNAME_M_Locator_ID, null);
		else 
			set_Value (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
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
			set_ValueNoCheck (COLUMNNAME_M_Product_Category_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Product_Category_ID, Integer.valueOf(M_Product_Category_ID));
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

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException
    {
		return (org.compiere.model.I_M_Warehouse)MTable.get(getCtx(), org.compiere.model.I_M_Warehouse.Table_Name)
			.getPO(getM_Warehouse_ID(), get_TrxName());	}

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1) 
			set_Value (COLUMNNAME_M_Warehouse_ID, null);
		else 
			set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product Category Store Setup.
		@param TF_ProdCategorySetup_ID Product Category Store Setup	  */
	public void setTF_ProdCategorySetup_ID (int TF_ProdCategorySetup_ID)
	{
		if (TF_ProdCategorySetup_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_ProdCategorySetup_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_ProdCategorySetup_ID, Integer.valueOf(TF_ProdCategorySetup_ID));
	}

	/** Get Product Category Store Setup.
		@return Product Category Store Setup	  */
	public int getTF_ProdCategorySetup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_ProdCategorySetup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_ProdCategorySetup_UU.
		@param TF_ProdCategorySetup_UU TF_ProdCategorySetup_UU	  */
	public void setTF_ProdCategorySetup_UU (String TF_ProdCategorySetup_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_ProdCategorySetup_UU, TF_ProdCategorySetup_UU);
	}

	/** Get TF_ProdCategorySetup_UU.
		@return TF_ProdCategorySetup_UU	  */
	public String getTF_ProdCategorySetup_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_ProdCategorySetup_UU);
	}
}