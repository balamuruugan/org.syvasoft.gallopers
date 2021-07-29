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

/** Generated Model for TF_LumpSumRent_Customer
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_LumpSumRent_Customer extends PO implements I_TF_LumpSumRent_Customer, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210518L;

    /** Standard Constructor */
    public X_TF_LumpSumRent_Customer (Properties ctx, int TF_LumpSumRent_Customer_ID, String trxName)
    {
      super (ctx, TF_LumpSumRent_Customer_ID, trxName);
      /** if (TF_LumpSumRent_Customer_ID == 0)
        {
			setTF_LumpSumRent_Config_ID (0);
			setTF_LumpSumRent_Customer_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_LumpSumRent_Customer (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_LumpSumRent_Customer[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Rent Margin.
		@param RentMargin Rent Margin	  */
	public void setRentMargin (BigDecimal RentMargin)
	{
		set_Value (COLUMNNAME_RentMargin, RentMargin);
	}

	/** Get Rent Margin.
		@return Rent Margin	  */
	public BigDecimal getRentMargin () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_RentMargin);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_TF_LumpSumRent_Config getTF_LumpSumRent_Config() throws RuntimeException
    {
		return (I_TF_LumpSumRent_Config)MTable.get(getCtx(), I_TF_LumpSumRent_Config.Table_Name)
			.getPO(getTF_LumpSumRent_Config_ID(), get_TrxName());	}

	/** Set TF_LumpSumRent_Config.
		@param TF_LumpSumRent_Config_ID TF_LumpSumRent_Config	  */
	public void setTF_LumpSumRent_Config_ID (int TF_LumpSumRent_Config_ID)
	{
		if (TF_LumpSumRent_Config_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_LumpSumRent_Config_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_LumpSumRent_Config_ID, Integer.valueOf(TF_LumpSumRent_Config_ID));
	}

	/** Get TF_LumpSumRent_Config.
		@return TF_LumpSumRent_Config	  */
	public int getTF_LumpSumRent_Config_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_LumpSumRent_Config_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Rent Margin By Customer Level.
		@param TF_LumpSumRent_Customer_ID Rent Margin By Customer Level	  */
	public void setTF_LumpSumRent_Customer_ID (int TF_LumpSumRent_Customer_ID)
	{
		if (TF_LumpSumRent_Customer_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_LumpSumRent_Customer_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_LumpSumRent_Customer_ID, Integer.valueOf(TF_LumpSumRent_Customer_ID));
	}

	/** Get Rent Margin By Customer Level.
		@return Rent Margin By Customer Level	  */
	public int getTF_LumpSumRent_Customer_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_LumpSumRent_Customer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_LumpSumRent_Customer_UU.
		@param TF_LumpSumRent_Customer_UU TF_LumpSumRent_Customer_UU	  */
	public void setTF_LumpSumRent_Customer_UU (String TF_LumpSumRent_Customer_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_LumpSumRent_Customer_UU, TF_LumpSumRent_Customer_UU);
	}

	/** Get TF_LumpSumRent_Customer_UU.
		@return TF_LumpSumRent_Customer_UU	  */
	public String getTF_LumpSumRent_Customer_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_LumpSumRent_Customer_UU);
	}
}