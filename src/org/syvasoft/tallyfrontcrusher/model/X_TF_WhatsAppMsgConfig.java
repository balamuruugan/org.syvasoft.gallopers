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

/** Generated Model for TF_WhatsAppMsgConfig
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_WhatsAppMsgConfig extends PO implements I_TF_WhatsAppMsgConfig, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210603L;

    /** Standard Constructor */
    public X_TF_WhatsAppMsgConfig (Properties ctx, int TF_WhatsAppMsgConfig_ID, String trxName)
    {
      super (ctx, TF_WhatsAppMsgConfig_ID, trxName);
      /** if (TF_WhatsAppMsgConfig_ID == 0)
        {
			setFileNameSQL (null);
			setPhoneSql (null);
			setTF_WhatsAppMsgConfig_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_TF_WhatsAppMsgConfig (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_TF_WhatsAppMsgConfig[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Process getAD_Process() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Process)MTable.get(getCtx(), org.compiere.model.I_AD_Process.Table_Name)
			.getPO(getAD_Process_ID(), get_TrxName());	}

	/** Set Process.
		@param AD_Process_ID 
		Process or Report
	  */
	public void setAD_Process_ID (int AD_Process_ID)
	{
		if (AD_Process_ID < 1) 
			set_Value (COLUMNNAME_AD_Process_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Process_ID, Integer.valueOf(AD_Process_ID));
	}

	/** Get Process.
		@return Process or Report
	  */
	public int getAD_Process_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Process_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Table)MTable.get(getCtx(), org.compiere.model.I_AD_Table.Table_Name)
			.getPO(getAD_Table_ID(), get_TrxName());	}

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1) 
			set_Value (COLUMNNAME_AD_Table_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Email Sql.
		@param EmailSql Email Sql	  */
	public void setEmailSql (String EmailSql)
	{
		set_Value (COLUMNNAME_EmailSql, EmailSql);
	}

	/** Get Email Sql.
		@return Email Sql	  */
	public String getEmailSql () 
	{
		return (String)get_Value(COLUMNNAME_EmailSql);
	}

	/** Set File Name SQL.
		@param FileNameSQL File Name SQL	  */
	public void setFileNameSQL (String FileNameSQL)
	{
		set_Value (COLUMNNAME_FileNameSQL, FileNameSQL);
	}

	/** Get File Name SQL.
		@return File Name SQL	  */
	public String getFileNameSQL () 
	{
		return (String)get_Value(COLUMNNAME_FileNameSQL);
	}

	/** Set Message.
		@param Message 
		EMail Message
	  */
	public void setMessage (String Message)
	{
		set_Value (COLUMNNAME_Message, Message);
	}

	/** Get Message.
		@return EMail Message
	  */
	public String getMessage () 
	{
		return (String)get_Value(COLUMNNAME_Message);
	}

	/** Set Phone Sql.
		@param PhoneSql Phone Sql	  */
	public void setPhoneSql (String PhoneSql)
	{
		set_Value (COLUMNNAME_PhoneSql, PhoneSql);
	}

	/** Get Phone Sql.
		@return Phone Sql	  */
	public String getPhoneSql () 
	{
		return (String)get_Value(COLUMNNAME_PhoneSql);
	}

	/** Set Prefix.
		@param Prefix 
		Prefix before the sequence number
	  */
	public void setPrefix (String Prefix)
	{
		set_Value (COLUMNNAME_Prefix, Prefix);
	}

	/** Get Prefix.
		@return Prefix before the sequence number
	  */
	public String getPrefix () 
	{
		return (String)get_Value(COLUMNNAME_Prefix);
	}

	/** Set WhatsApp Msg Configuration.
		@param TF_WhatsAppMsgConfig_ID WhatsApp Msg Configuration	  */
	public void setTF_WhatsAppMsgConfig_ID (int TF_WhatsAppMsgConfig_ID)
	{
		if (TF_WhatsAppMsgConfig_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_WhatsAppMsgConfig_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_WhatsAppMsgConfig_ID, Integer.valueOf(TF_WhatsAppMsgConfig_ID));
	}

	/** Get WhatsApp Msg Configuration.
		@return WhatsApp Msg Configuration	  */
	public int getTF_WhatsAppMsgConfig_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_WhatsAppMsgConfig_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_WhatsAppMsgConfig_UU.
		@param TF_WhatsAppMsgConfig_UU TF_WhatsAppMsgConfig_UU	  */
	public void setTF_WhatsAppMsgConfig_UU (String TF_WhatsAppMsgConfig_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_WhatsAppMsgConfig_UU, TF_WhatsAppMsgConfig_UU);
	}

	/** Get TF_WhatsAppMsgConfig_UU.
		@return TF_WhatsAppMsgConfig_UU	  */
	public String getTF_WhatsAppMsgConfig_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_WhatsAppMsgConfig_UU);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

	/** Set Sql WHERE.
		@param WhereClause 
		Fully qualified SQL WHERE clause
	  */
	public void setWhereClause (String WhereClause)
	{
		set_Value (COLUMNNAME_WhereClause, WhereClause);
	}

	/** Get Sql WHERE.
		@return Fully qualified SQL WHERE clause
	  */
	public String getWhereClause () 
	{
		return (String)get_Value(COLUMNNAME_WhereClause);
	}
}