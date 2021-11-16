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

/** Generated Model for TF_InstantPettyCashLine
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_InstantPettyCashLine extends PO implements I_TF_InstantPettyCashLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20211116L;

    /** Standard Constructor */
    public X_TF_InstantPettyCashLine (Properties ctx, int TF_InstantPettyCashLine_ID, String trxName)
    {
      super (ctx, TF_InstantPettyCashLine_ID, trxName);
      /** if (TF_InstantPettyCashLine_ID == 0)
        {
			setLine (0);
			setPayAmt (Env.ZERO);
			setTF_InstantPettyCashLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_InstantPettyCashLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_InstantPettyCashLine[")
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
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
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

	public org.compiere.model.I_GL_Journal getGL_Journal() throws RuntimeException
    {
		return (org.compiere.model.I_GL_Journal)MTable.get(getCtx(), org.compiere.model.I_GL_Journal.Table_Name)
			.getPO(getGL_Journal_ID(), get_TrxName());	}

	/** Set Journal.
		@param GL_Journal_ID 
		General Ledger Journal
	  */
	public void setGL_Journal_ID (int GL_Journal_ID)
	{
		if (GL_Journal_ID < 1) 
			set_Value (COLUMNNAME_GL_Journal_ID, null);
		else 
			set_Value (COLUMNNAME_GL_Journal_ID, Integer.valueOf(GL_Journal_ID));
	}

	/** Get Journal.
		@return General Ledger Journal
	  */
	public int getGL_Journal_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GL_Journal_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Payment amount.
		@param PayAmt 
		Amount being paid
	  */
	public void setPayAmt (BigDecimal PayAmt)
	{
		set_Value (COLUMNNAME_PayAmt, PayAmt);
	}

	/** Get Payment amount.
		@return Amount being paid
	  */
	public BigDecimal getPayAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PayAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Payment Line.
		@param TF_InstantPettyCashLine_ID Payment Line	  */
	public void setTF_InstantPettyCashLine_ID (int TF_InstantPettyCashLine_ID)
	{
		if (TF_InstantPettyCashLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_InstantPettyCashLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_InstantPettyCashLine_ID, Integer.valueOf(TF_InstantPettyCashLine_ID));
	}

	/** Get Payment Line.
		@return Payment Line	  */
	public int getTF_InstantPettyCashLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_InstantPettyCashLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_InstantPettyCashLine_UU.
		@param TF_InstantPettyCashLine_UU TF_InstantPettyCashLine_UU	  */
	public void setTF_InstantPettyCashLine_UU (String TF_InstantPettyCashLine_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_InstantPettyCashLine_UU, TF_InstantPettyCashLine_UU);
	}

	/** Get TF_InstantPettyCashLine_UU.
		@return TF_InstantPettyCashLine_UU	  */
	public String getTF_InstantPettyCashLine_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_InstantPettyCashLine_UU);
	}
}