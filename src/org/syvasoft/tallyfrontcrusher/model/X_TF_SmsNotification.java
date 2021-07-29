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
import org.compiere.util.KeyNamePair;

/** Generated Model for TF_SmsNotification
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_SmsNotification extends PO implements I_TF_SmsNotification, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210605L;

    /** Standard Constructor */
    public X_TF_SmsNotification (Properties ctx, int TF_SmsNotification_ID, String trxName)
    {
      super (ctx, TF_SmsNotification_ID, trxName);
      /** if (TF_SmsNotification_ID == 0)
        {
			setIsScheduled (false);
// N
			setIsSMS (false);
// N
			setIsWhatsApp (false);
// N
			setMessage (null);
			setName (null);
			setSql (null);
			setTF_SmsNotification_ID (0);
			setUnicode (false);
        } */
    }

    /** Load Constructor */
    public X_TF_SmsNotification (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_SmsNotification[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Message getAD_Message() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Message)MTable.get(getCtx(), org.compiere.model.I_AD_Message.Table_Name)
			.getPO(getAD_Message_ID(), get_TrxName());	}

	/** Set Message.
		@param AD_Message_ID 
		System Message
	  */
	public void setAD_Message_ID (int AD_Message_ID)
	{
		if (AD_Message_ID < 1) 
			set_Value (COLUMNNAME_AD_Message_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Message_ID, Integer.valueOf(AD_Message_ID));
	}

	/** Get Message.
		@return System Message
	  */
	public int getAD_Message_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Message_ID);
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

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getAD_User_ID(), get_TrxName());	}

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Change Record.
		@param ChangeRecord Change Record	  */
	public void setChangeRecord (boolean ChangeRecord)
	{
		set_Value (COLUMNNAME_ChangeRecord, Boolean.valueOf(ChangeRecord));
	}

	/** Get Change Record.
		@return Change Record	  */
	public boolean isChangeRecord () 
	{
		Object oo = get_Value(COLUMNNAME_ChangeRecord);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Delivery Time.
		@param DeliveryTime Delivery Time	  */
	public void setDeliveryTime (String DeliveryTime)
	{
		set_Value (COLUMNNAME_DeliveryTime, DeliveryTime);
	}

	/** Get Delivery Time.
		@return Delivery Time	  */
	public String getDeliveryTime () 
	{
		return (String)get_Value(COLUMNNAME_DeliveryTime);
	}

	/** Set Footer Message.
		@param FooterMsg Footer Message	  */
	public void setFooterMsg (String FooterMsg)
	{
		set_Value (COLUMNNAME_FooterMsg, FooterMsg);
	}

	/** Get Footer Message.
		@return Footer Message	  */
	public String getFooterMsg () 
	{
		return (String)get_Value(COLUMNNAME_FooterMsg);
	}

	/** Set Footer Sql.
		@param FooterSql Footer Sql	  */
	public void setFooterSql (String FooterSql)
	{
		set_Value (COLUMNNAME_FooterSql, FooterSql);
	}

	/** Get Footer Sql.
		@return Footer Sql	  */
	public String getFooterSql () 
	{
		return (String)get_Value(COLUMNNAME_FooterSql);
	}

	/** Set Header Message.
		@param HeaderMsg Header Message	  */
	public void setHeaderMsg (String HeaderMsg)
	{
		set_Value (COLUMNNAME_HeaderMsg, HeaderMsg);
	}

	/** Get Header Message.
		@return Header Message	  */
	public String getHeaderMsg () 
	{
		return (String)get_Value(COLUMNNAME_HeaderMsg);
	}

	/** Set Header Sql.
		@param HeaderSql Header Sql	  */
	public void setHeaderSql (String HeaderSql)
	{
		set_Value (COLUMNNAME_HeaderSql, HeaderSql);
	}

	/** Get Header Sql.
		@return Header Sql	  */
	public String getHeaderSql () 
	{
		return (String)get_Value(COLUMNNAME_HeaderSql);
	}

	/** Set Scheduled.
		@param IsScheduled Scheduled	  */
	public void setIsScheduled (boolean IsScheduled)
	{
		set_Value (COLUMNNAME_IsScheduled, Boolean.valueOf(IsScheduled));
	}

	/** Get Scheduled.
		@return Scheduled	  */
	public boolean isScheduled () 
	{
		Object oo = get_Value(COLUMNNAME_IsScheduled);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set SMS.
		@param IsSMS SMS	  */
	public void setIsSMS (boolean IsSMS)
	{
		set_Value (COLUMNNAME_IsSMS, Boolean.valueOf(IsSMS));
	}

	/** Get SMS.
		@return SMS	  */
	public boolean isSMS () 
	{
		Object oo = get_Value(COLUMNNAME_IsSMS);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set WhatsApp.
		@param IsWhatsApp WhatsApp	  */
	public void setIsWhatsApp (boolean IsWhatsApp)
	{
		set_Value (COLUMNNAME_IsWhatsApp, Boolean.valueOf(IsWhatsApp));
	}

	/** Get WhatsApp.
		@return WhatsApp	  */
	public boolean isWhatsApp () 
	{
		Object oo = get_Value(COLUMNNAME_IsWhatsApp);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set New Record.
		@param NewRecord New Record	  */
	public void setNewRecord (boolean NewRecord)
	{
		set_Value (COLUMNNAME_NewRecord, Boolean.valueOf(NewRecord));
	}

	/** Get New Record.
		@return New Record	  */
	public boolean isNewRecord () 
	{
		Object oo = get_Value(COLUMNNAME_NewRecord);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Notice.
		@param NoticeFlag Notice	  */
	public void setNoticeFlag (boolean NoticeFlag)
	{
		set_Value (COLUMNNAME_NoticeFlag, Boolean.valueOf(NoticeFlag));
	}

	/** Get Notice.
		@return Notice	  */
	public boolean isNoticeFlag () 
	{
		Object oo = get_Value(COLUMNNAME_NoticeFlag);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Recipient SQL.
		@param RecipientSQL Recipient SQL	  */
	public void setRecipientSQL (String RecipientSQL)
	{
		set_Value (COLUMNNAME_RecipientSQL, RecipientSQL);
	}

	/** Get Recipient SQL.
		@return Recipient SQL	  */
	public String getRecipientSQL () 
	{
		return (String)get_Value(COLUMNNAME_RecipientSQL);
	}

	/** Set Required Action.
		@param RequiredAction Required Action	  */
	public void setRequiredAction (String RequiredAction)
	{
		set_Value (COLUMNNAME_RequiredAction, RequiredAction);
	}

	/** Get Required Action.
		@return Required Action	  */
	public String getRequiredAction () 
	{
		return (String)get_Value(COLUMNNAME_RequiredAction);
	}

	/** Set Sql.
		@param Sql Sql	  */
	public void setSql (String Sql)
	{
		set_Value (COLUMNNAME_Sql, Sql);
	}

	/** Get Sql.
		@return Sql	  */
	public String getSql () 
	{
		return (String)get_Value(COLUMNNAME_Sql);
	}

	/** Set Notification.
		@param TF_SmsNotification_ID Notification	  */
	public void setTF_SmsNotification_ID (int TF_SmsNotification_ID)
	{
		if (TF_SmsNotification_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_SmsNotification_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_SmsNotification_ID, Integer.valueOf(TF_SmsNotification_ID));
	}

	/** Get Notification.
		@return Notification	  */
	public int getTF_SmsNotification_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_SmsNotification_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_SmsNotification_UU.
		@param TF_SmsNotification_UU TF_SmsNotification_UU	  */
	public void setTF_SmsNotification_UU (String TF_SmsNotification_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_SmsNotification_UU, TF_SmsNotification_UU);
	}

	/** Get TF_SmsNotification_UU.
		@return TF_SmsNotification_UU	  */
	public String getTF_SmsNotification_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_SmsNotification_UU);
	}

	/** Set Unicode.
		@param Unicode Unicode	  */
	public void setUnicode (boolean Unicode)
	{
		set_Value (COLUMNNAME_Unicode, Boolean.valueOf(Unicode));
	}

	/** Get Unicode.
		@return Unicode	  */
	public boolean isUnicode () 
	{
		Object oo = get_Value(COLUMNNAME_Unicode);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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