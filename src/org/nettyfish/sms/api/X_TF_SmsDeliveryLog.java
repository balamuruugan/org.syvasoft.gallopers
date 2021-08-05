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
package org.nettyfish.sms.api;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.syvasoft.tallyfrontcrusher.model.I_TF_SmsNotification;

/** Generated Model for TF_SmsDeliveryLog
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_SmsDeliveryLog extends PO implements I_TF_SmsDeliveryLog, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190123L;

    /** Standard Constructor */
    public X_TF_SmsDeliveryLog (Properties ctx, int TF_SmsDeliveryLog_ID, String trxName)
    {
      super (ctx, TF_SmsDeliveryLog_ID, trxName);
      /** if (TF_SmsDeliveryLog_ID == 0)
        {
			setLoginStatus (null);
			setMessage (null);
			setRecipients (null);
			setSmsCount (0);
			setTF_SmsDeliveryLog_ID (0);
			setValidNumbers (0);
        } */
    }

    /** Load Constructor */
    public X_TF_SmsDeliveryLog (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_SmsDeliveryLog[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Balance.
		@param Balance Balance	  */
	public void setBalance (int Balance)
	{
		set_ValueNoCheck (COLUMNNAME_Balance, Integer.valueOf(Balance));
	}

	/** Get Balance.
		@return Balance	  */
	public int getBalance () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Balance);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Balance Status.
		@param BalanceStatus Balance Status	  */
	public void setBalanceStatus (String BalanceStatus)
	{
		set_Value (COLUMNNAME_BalanceStatus, BalanceStatus);
	}

	/** Get Balance Status.
		@return Balance Status	  */
	public String getBalanceStatus () 
	{
		return (String)get_Value(COLUMNNAME_BalanceStatus);
	}

	/** Set Current Balance.
		@param CurrentBalance 
		Current Balance
	  */
	public void setCurrentBalance (int CurrentBalance)
	{
		set_Value (COLUMNNAME_CurrentBalance, Integer.valueOf(CurrentBalance));
	}

	/** Get Current Balance.
		@return Current Balance
	  */
	public int getCurrentBalance () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CurrentBalance);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Login Status.
		@param LoginStatus Login Status	  */
	public void setLoginStatus (String LoginStatus)
	{
		set_Value (COLUMNNAME_LoginStatus, LoginStatus);
	}

	/** Get Login Status.
		@return Login Status	  */
	public String getLoginStatus () 
	{
		return (String)get_Value(COLUMNNAME_LoginStatus);
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

	/** Set Msg Status.
		@param MsgStatus Msg Status	  */
	public void setMsgStatus (String MsgStatus)
	{
		set_Value (COLUMNNAME_MsgStatus, MsgStatus);
	}

	/** Get Msg Status.
		@return Msg Status	  */
	public String getMsgStatus () 
	{
		return (String)get_Value(COLUMNNAME_MsgStatus);
	}

	/** Set Recipients.
		@param Recipients Recipients	  */
	public void setRecipients (String Recipients)
	{
		set_Value (COLUMNNAME_Recipients, Recipients);
	}

	/** Get Recipients.
		@return Recipients	  */
	public String getRecipients () 
	{
		return (String)get_Value(COLUMNNAME_Recipients);
	}

	/** Set Sms Count.
		@param SmsCount Sms Count	  */
	public void setSmsCount (int SmsCount)
	{
		set_Value (COLUMNNAME_SmsCount, Integer.valueOf(SmsCount));
	}

	/** Get Sms Count.
		@return Sms Count	  */
	public int getSmsCount () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SmsCount);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_SmsDeliveryLog.
		@param TF_SmsDeliveryLog_ID TF_SmsDeliveryLog	  */
	public void setTF_SmsDeliveryLog_ID (int TF_SmsDeliveryLog_ID)
	{
		if (TF_SmsDeliveryLog_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_SmsDeliveryLog_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_SmsDeliveryLog_ID, Integer.valueOf(TF_SmsDeliveryLog_ID));
	}

	/** Get TF_SmsDeliveryLog.
		@return TF_SmsDeliveryLog	  */
	public int getTF_SmsDeliveryLog_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_SmsDeliveryLog_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_SmsDeliveryLog_UU.
		@param TF_SmsDeliveryLog_UU TF_SmsDeliveryLog_UU	  */
	public void setTF_SmsDeliveryLog_UU (String TF_SmsDeliveryLog_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_SmsDeliveryLog_UU, TF_SmsDeliveryLog_UU);
	}

	/** Get TF_SmsDeliveryLog_UU.
		@return TF_SmsDeliveryLog_UU	  */
	public String getTF_SmsDeliveryLog_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_SmsDeliveryLog_UU);
	}

	/** Set Transaction ID.
		@param TransactionID Transaction ID	  */
	public void setTransactionID (String TransactionID)
	{
		set_Value (COLUMNNAME_TransactionID, TransactionID);
	}

	/** Get Transaction ID.
		@return Transaction ID	  */
	public String getTransactionID () 
	{
		return (String)get_Value(COLUMNNAME_TransactionID);
	}

	/** Set Valid Numbers.
		@param ValidNumbers Valid Numbers	  */
	public void setValidNumbers (int ValidNumbers)
	{
		set_Value (COLUMNNAME_ValidNumbers, Integer.valueOf(ValidNumbers));
	}

	/** Get Valid Numbers.
		@return Valid Numbers	  */
	public int getValidNumbers () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ValidNumbers);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
}