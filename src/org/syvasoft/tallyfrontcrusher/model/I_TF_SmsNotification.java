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
package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for TF_SmsNotification
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_TF_SmsNotification 
{

    /** TableName=TF_SmsNotification */
    public static final String Table_Name = "TF_SmsNotification";

    /** AD_Table_ID=1000290 */
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

    /** Column name AD_Message_ID */
    public static final String COLUMNNAME_AD_Message_ID = "AD_Message_ID";

	/** Set Message.
	  * System Message
	  */
	public void setAD_Message_ID (int AD_Message_ID);

	/** Get Message.
	  * System Message
	  */
	public int getAD_Message_ID();

	public org.compiere.model.I_AD_Message getAD_Message() throws RuntimeException;

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

    /** Column name AD_Table_ID */
    public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

	/** Set Table.
	  * Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID);

	/** Get Table.
	  * Database Table information
	  */
	public int getAD_Table_ID();

	public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException;

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException;

    /** Column name ChangeRecord */
    public static final String COLUMNNAME_ChangeRecord = "ChangeRecord";

	/** Set Change Record	  */
	public void setChangeRecord (boolean ChangeRecord);

	/** Get Change Record	  */
	public boolean isChangeRecord();

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

    /** Column name DeliveryTime */
    public static final String COLUMNNAME_DeliveryTime = "DeliveryTime";

	/** Set Delivery Time	  */
	public void setDeliveryTime (String DeliveryTime);

	/** Get Delivery Time	  */
	public String getDeliveryTime();

    /** Column name FooterMsg */
    public static final String COLUMNNAME_FooterMsg = "FooterMsg";

	/** Set Footer Message	  */
	public void setFooterMsg (String FooterMsg);

	/** Get Footer Message	  */
	public String getFooterMsg();

    /** Column name FooterSql */
    public static final String COLUMNNAME_FooterSql = "FooterSql";

	/** Set Footer Sql	  */
	public void setFooterSql (String FooterSql);

	/** Get Footer Sql	  */
	public String getFooterSql();

    /** Column name HeaderMsg */
    public static final String COLUMNNAME_HeaderMsg = "HeaderMsg";

	/** Set Header Message	  */
	public void setHeaderMsg (String HeaderMsg);

	/** Get Header Message	  */
	public String getHeaderMsg();

    /** Column name HeaderSql */
    public static final String COLUMNNAME_HeaderSql = "HeaderSql";

	/** Set Header Sql	  */
	public void setHeaderSql (String HeaderSql);

	/** Get Header Sql	  */
	public String getHeaderSql();

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

    /** Column name IsScheduled */
    public static final String COLUMNNAME_IsScheduled = "IsScheduled";

	/** Set Scheduled	  */
	public void setIsScheduled (boolean IsScheduled);

	/** Get Scheduled	  */
	public boolean isScheduled();

    /** Column name IsSMS */
    public static final String COLUMNNAME_IsSMS = "IsSMS";

	/** Set SMS	  */
	public void setIsSMS (boolean IsSMS);

	/** Get SMS	  */
	public boolean isSMS();

    /** Column name IsWhatsApp */
    public static final String COLUMNNAME_IsWhatsApp = "IsWhatsApp";

	/** Set WhatsApp	  */
	public void setIsWhatsApp (boolean IsWhatsApp);

	/** Get WhatsApp	  */
	public boolean isWhatsApp();

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

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name NewRecord */
    public static final String COLUMNNAME_NewRecord = "NewRecord";

	/** Set New Record	  */
	public void setNewRecord (boolean NewRecord);

	/** Get New Record	  */
	public boolean isNewRecord();

    /** Column name NoticeFlag */
    public static final String COLUMNNAME_NoticeFlag = "NoticeFlag";

	/** Set Notice	  */
	public void setNoticeFlag (boolean NoticeFlag);

	/** Get Notice	  */
	public boolean isNoticeFlag();

    /** Column name RecipientSQL */
    public static final String COLUMNNAME_RecipientSQL = "RecipientSQL";

	/** Set Recipient SQL	  */
	public void setRecipientSQL (String RecipientSQL);

	/** Get Recipient SQL	  */
	public String getRecipientSQL();

    /** Column name RequiredAction */
    public static final String COLUMNNAME_RequiredAction = "RequiredAction";

	/** Set Required Action	  */
	public void setRequiredAction (String RequiredAction);

	/** Get Required Action	  */
	public String getRequiredAction();

    /** Column name Sql */
    public static final String COLUMNNAME_Sql = "Sql";

	/** Set Sql	  */
	public void setSql (String Sql);

	/** Get Sql	  */
	public String getSql();

    /** Column name TF_SmsNotification_ID */
    public static final String COLUMNNAME_TF_SmsNotification_ID = "TF_SmsNotification_ID";

	/** Set Notification	  */
	public void setTF_SmsNotification_ID (int TF_SmsNotification_ID);

	/** Get Notification	  */
	public int getTF_SmsNotification_ID();

    /** Column name TF_SmsNotification_UU */
    public static final String COLUMNNAME_TF_SmsNotification_UU = "TF_SmsNotification_UU";

	/** Set TF_SmsNotification_UU	  */
	public void setTF_SmsNotification_UU (String TF_SmsNotification_UU);

	/** Get TF_SmsNotification_UU	  */
	public String getTF_SmsNotification_UU();

    /** Column name Unicode */
    public static final String COLUMNNAME_Unicode = "Unicode";

	/** Set Unicode	  */
	public void setUnicode (boolean Unicode);

	/** Get Unicode	  */
	public boolean isUnicode();

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

    /** Column name WhereClause */
    public static final String COLUMNNAME_WhereClause = "WhereClause";

	/** Set Sql WHERE.
	  * Fully qualified SQL WHERE clause
	  */
	public void setWhereClause (String WhereClause);

	/** Get Sql WHERE.
	  * Fully qualified SQL WHERE clause
	  */
	public String getWhereClause();
}
