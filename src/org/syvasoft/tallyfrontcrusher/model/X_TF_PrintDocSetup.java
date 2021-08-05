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

/** Generated Model for TF_PrintDocSetup
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_PrintDocSetup extends PO implements I_TF_PrintDocSetup, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210322L;

    /** Standard Constructor */
    public X_TF_PrintDocSetup (Properties ctx, int TF_PrintDocSetup_ID, String trxName)
    {
      super (ctx, TF_PrintDocSetup_ID, trxName);
      /** if (TF_PrintDocSetup_ID == 0)
        {
			setC_DocType_ID (0);
			setTF_PrintDocSetup_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_PrintDocSetup (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_PrintDocSetup[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_DocType getC_DocType() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getC_DocType_ID(), get_TrxName());	}

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0) 
			set_ValueNoCheck (COLUMNNAME_C_DocType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set FooterText.
		@param FooterText FooterText	  */
	public void setFooterText (String FooterText)
	{
		set_Value (COLUMNNAME_FooterText, FooterText);
	}

	/** Get FooterText.
		@return FooterText	  */
	public String getFooterText () 
	{
		return (String)get_Value(COLUMNNAME_FooterText);
	}

	/** Set Terms & Conditions.
		@param TermsConditions Terms & Conditions	  */
	public void setTermsConditions (String TermsConditions)
	{
		set_Value (COLUMNNAME_TermsConditions, TermsConditions);
	}

	/** Get Terms & Conditions.
		@return Terms & Conditions	  */
	public String getTermsConditions () 
	{
		return (String)get_Value(COLUMNNAME_TermsConditions);
	}

	/** Set Print Document Setup.
		@param TF_PrintDocSetup_ID Print Document Setup	  */
	public void setTF_PrintDocSetup_ID (int TF_PrintDocSetup_ID)
	{
		if (TF_PrintDocSetup_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_PrintDocSetup_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_PrintDocSetup_ID, Integer.valueOf(TF_PrintDocSetup_ID));
	}

	/** Get Print Document Setup.
		@return Print Document Setup	  */
	public int getTF_PrintDocSetup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_PrintDocSetup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_PrintDocSetup_UU.
		@param TF_PrintDocSetup_UU TF_PrintDocSetup_UU	  */
	public void setTF_PrintDocSetup_UU (String TF_PrintDocSetup_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_PrintDocSetup_UU, TF_PrintDocSetup_UU);
	}

	/** Get TF_PrintDocSetup_UU.
		@return TF_PrintDocSetup_UU	  */
	public String getTF_PrintDocSetup_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_PrintDocSetup_UU);
	}
}