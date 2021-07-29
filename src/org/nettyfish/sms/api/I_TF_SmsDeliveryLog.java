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
package org.nettyfish.sms.api;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for TF_SmsDeliveryLog
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_TF_SmsDeliveryLog 
{

    /** TableName=TF_SmsDeliveryLog */
    public static final String Table_Name = "TF_SmsDeliveryLog";

    /** AD_Table_ID=1000287 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name Balance */
    public static final String COLUMNNAME_Balance = "Balance";

	/** Set Balance	  */
	public void setBalance (int Balance);

	/** Get Balance	  */
	public int getBalance();

    /** Column name BalanceStatus */
    public static final String COLUMNNAME_BalanceStatus = "BalanceStatus";

	/** Set Balance Status	  */
	public void setBalanceStatus (String BalanceStatus);

	/** Get Balance Status	  */
	public String getBalanceStatus();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name CurrentBalance */
    public static final String COLUMNNAME_CurrentBalance = "CurrentBalance";

	/** Set Current Balance.
	  * Current Balance
	  */
	public void setCurrentBalance (int CurrentBalance);

	/** Get Current Balance.
	  * Current Balance
	  */
	public int getCurrentBalance();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name LoginStatus */
    public static final String COLUMNNAME_LoginStatus = "LoginStatus";

	/** Set Login Status	  */
	public void setLoginStatus (String LoginStatus);

	/** Get Login Status	  */
	public String getLoginStatus();

    /** Column name Message */
    public static final String COLUMNNAME_Message = "Message";

	/** Set Message.
	  * EMail Message
	  */
	public void setMessage (String Message);

	/** Get Message.
	  * EMail Message
	  */
	public String getMessage();

    /** Column name MsgStatus */
    public static final String COLUMNNAME_MsgStatus = "MsgStatus";

	/** Set Msg Status	  */
	public void setMsgStatus (String MsgStatus);

	/** Get Msg Status	  */
	public String getMsgStatus();

    /** Column name Recipients */
    public static final String COLUMNNAME_Recipients = "Recipients";

	/** Set Recipients	  */
	public void setRecipients (String Recipients);

	/** Get Recipients	  */
	public String getRecipients();

    /** Column name SmsCount */
    public static final String COLUMNNAME_SmsCount = "SmsCount";

	/** Set Sms Count	  */
	public void setSmsCount (int SmsCount);

	/** Get Sms Count	  */
	public int getSmsCount();

    /** Column name TF_SmsDeliveryLog_ID */
    public static final String COLUMNNAME_TF_SmsDeliveryLog_ID = "TF_SmsDeliveryLog_ID";

	/** Set TF_SmsDeliveryLog	  */
	public void setTF_SmsDeliveryLog_ID (int TF_SmsDeliveryLog_ID);

	/** Get TF_SmsDeliveryLog	  */
	public int getTF_SmsDeliveryLog_ID();

    /** Column name TF_SmsDeliveryLog_UU */
    public static final String COLUMNNAME_TF_SmsDeliveryLog_UU = "TF_SmsDeliveryLog_UU";

	/** Set TF_SmsDeliveryLog_UU	  */
	public void setTF_SmsDeliveryLog_UU (String TF_SmsDeliveryLog_UU);

	/** Get TF_SmsDeliveryLog_UU	  */
	public String getTF_SmsDeliveryLog_UU();

    /** Column name TransactionID */
    public static final String COLUMNNAME_TransactionID = "TransactionID";

	/** Set Transaction ID	  */
	public void setTransactionID (String TransactionID);

	/** Get Transaction ID	  */
	public String getTransactionID();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name ValidNumbers */
    public static final String COLUMNNAME_ValidNumbers = "ValidNumbers";

	/** Set Valid Numbers	  */
	public void setValidNumbers (int ValidNumbers);

	/** Get Valid Numbers	  */
	public int getValidNumbers();
	
    /** Column name TF_SmsNotification_ID */
    public static final String COLUMNNAME_TF_SmsNotification_ID = "TF_SmsNotification_ID";

	/** Set TF_SmsNotification	  */
	public void setTF_SmsNotification_ID (int TF_SmsNotification_ID);

	/** Get TF_SmsNotification	  */
	public int getTF_SmsNotification_ID();
}
