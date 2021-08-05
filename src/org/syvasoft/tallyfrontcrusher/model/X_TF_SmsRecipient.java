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

/** Generated Model for TF_SmsRecipient
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_SmsRecipient extends PO implements I_TF_SmsRecipient, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210519L;

    /** Standard Constructor */
    public X_TF_SmsRecipient (Properties ctx, int TF_SmsRecipient_ID, String trxName)
    {
      super (ctx, TF_SmsRecipient_ID, trxName);
      /** if (TF_SmsRecipient_ID == 0)
        {
			setTF_SmsRecipient_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_SmsRecipient (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_SmsRecipient[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Role getAD_Role() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Role)MTable.get(getCtx(), org.compiere.model.I_AD_Role.Table_Name)
			.getPO(getAD_Role_ID(), get_TrxName());	}

	/** Set Role.
		@param AD_Role_ID 
		Responsibility Role
	  */
	public void setAD_Role_ID (int AD_Role_ID)
	{
		if (AD_Role_ID < 0) 
			set_ValueNoCheck (COLUMNNAME_AD_Role_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Role_ID, Integer.valueOf(AD_Role_ID));
	}

	/** Get Role.
		@return Responsibility Role
	  */
	public int getAD_Role_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Role_ID);
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
			set_ValueNoCheck (COLUMNNAME_AD_User_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
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

	/** Set WhatsApp Chat Id.
		@param ChatId WhatsApp Chat Id	  */
	public void setChatId (String ChatId)
	{
		set_Value (COLUMNNAME_ChatId, ChatId);
	}

	/** Get WhatsApp Chat Id.
		@return WhatsApp Chat Id	  */
	public String getChatId () 
	{
		return (String)get_Value(COLUMNNAME_ChatId);
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

	/** Set Mobile No.
		@param MobileNo Mobile No	  */
	public void setMobileNo (String MobileNo)
	{
		set_Value (COLUMNNAME_MobileNo, MobileNo);
	}

	/** Get Mobile No.
		@return Mobile No	  */
	public String getMobileNo () 
	{
		return (String)get_Value(COLUMNNAME_MobileNo);
	}

	public I_TF_SmsNotification getTF_SmsNotification() throws RuntimeException
    {
		return (I_TF_SmsNotification)MTable.get(getCtx(), I_TF_SmsNotification.Table_Name)
			.getPO(getTF_SmsNotification_ID(), get_TrxName());	}

	/** Set TF_SmsNotification.
		@param TF_SmsNotification_ID TF_SmsNotification	  */
	public void setTF_SmsNotification_ID (int TF_SmsNotification_ID)
	{
		if (TF_SmsNotification_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_SmsNotification_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_SmsNotification_ID, Integer.valueOf(TF_SmsNotification_ID));
	}

	/** Get TF_SmsNotification.
		@return TF_SmsNotification	  */
	public int getTF_SmsNotification_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_SmsNotification_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_SmsRecipient.
		@param TF_SmsRecipient_ID TF_SmsRecipient	  */
	public void setTF_SmsRecipient_ID (int TF_SmsRecipient_ID)
	{
		if (TF_SmsRecipient_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_SmsRecipient_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_SmsRecipient_ID, Integer.valueOf(TF_SmsRecipient_ID));
	}

	/** Get TF_SmsRecipient.
		@return TF_SmsRecipient	  */
	public int getTF_SmsRecipient_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_SmsRecipient_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_SmsRecipient_UU.
		@param TF_SmsRecipient_UU TF_SmsRecipient_UU	  */
	public void setTF_SmsRecipient_UU (String TF_SmsRecipient_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_SmsRecipient_UU, TF_SmsRecipient_UU);
	}

	/** Get TF_SmsRecipient_UU.
		@return TF_SmsRecipient_UU	  */
	public String getTF_SmsRecipient_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_SmsRecipient_UU);
	}
}