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

/** Generated Interface for AD_Org
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_AD_Org 
{

    /** TableName=AD_Org */
    public static final String Table_Name = "AD_Org";

    /** AD_Table_ID=155 */
    public static final int Table_ID = 155;

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name AD_Org_UU */
    public static final String COLUMNNAME_AD_Org_UU = "AD_Org_UU";

	/** Set AD_Org_UU	  */
	public void setAD_Org_UU (String AD_Org_UU);

	/** Get AD_Org_UU	  */
	public String getAD_Org_UU();

    /** Column name AD_OrgHO_ID */
    public static final String COLUMNNAME_AD_OrgHO_ID = "AD_OrgHO_ID";

	/** Set Head Office	  */
	public void setAD_OrgHO_ID (int AD_OrgHO_ID);

	/** Get Head Office	  */
	public int getAD_OrgHO_ID();

    /** Column name AD_ReplicationStrategy_ID */
    public static final String COLUMNNAME_AD_ReplicationStrategy_ID = "AD_ReplicationStrategy_ID";

	/** Set Replication Strategy.
	  * Data Replication Strategy
	  */
	public void setAD_ReplicationStrategy_ID (int AD_ReplicationStrategy_ID);

	/** Get Replication Strategy.
	  * Data Replication Strategy
	  */
	public int getAD_ReplicationStrategy_ID();

	public org.compiere.model.I_AD_ReplicationStrategy getAD_ReplicationStrategy() throws RuntimeException;

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

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name InstantPettyCashAcct_ID */
    public static final String COLUMNNAME_InstantPettyCashAcct_ID = "InstantPettyCashAcct_ID";

	/** Set Instant Petty Cash Account	  */
	public void setInstantPettyCashAcct_ID (int InstantPettyCashAcct_ID);

	/** Get Instant Petty Cash Account	  */
	public int getInstantPettyCashAcct_ID();

	public org.compiere.model.I_C_ElementValue getInstantPettyCashAcct() throws RuntimeException;

    /** Column name InvestmentAcct_ID */
    public static final String COLUMNNAME_InvestmentAcct_ID = "InvestmentAcct_ID";

	/** Set Investment Account	  */
	public void setInvestmentAcct_ID (int InvestmentAcct_ID);

	/** Get Investment Account	  */
	public int getInvestmentAcct_ID();

	public org.compiere.model.I_C_ElementValue getInvestmentAcct() throws RuntimeException;

    /** Column name InvestmentDetail */
    public static final String COLUMNNAME_InvestmentDetail = "InvestmentDetail";

	/** Set Investment Detail	  */
	public void setInvestmentDetail (String InvestmentDetail);

	/** Get Investment Detail	  */
	public String getInvestmentDetail();

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

    /** Column name IsDemo */
    public static final String COLUMNNAME_IsDemo = "IsDemo";

	/** Set Demo	  */
	public void setIsDemo (boolean IsDemo);

	/** Get Demo	  */
	public boolean isDemo();

    /** Column name IsDontCountMeInConcurrSessions */
    public static final String COLUMNNAME_IsDontCountMeInConcurrSessions = "IsDontCountMeInConcurrSessions";

	/** Set Don't count me in concurrent sessions.
	  * If you tick this checkbox, the record (user, role, org) won't be taken in consideration for checking limit of concurrent sessions
	  */
	public void setIsDontCountMeInConcurrSessions (boolean IsDontCountMeInConcurrSessions);

	/** Get Don't count me in concurrent sessions.
	  * If you tick this checkbox, the record (user, role, org) won't be taken in consideration for checking limit of concurrent sessions
	  */
	public boolean isDontCountMeInConcurrSessions();

    /** Column name IsOffsetCapitalAcct */
    public static final String COLUMNNAME_IsOffsetCapitalAcct = "IsOffsetCapitalAcct";

	/** Set Offset Capital A/c.
	  * Offset Capital A/c & Initial Expenses
	  */
	public void setIsOffsetCapitalAcct (boolean IsOffsetCapitalAcct);

	/** Get Offset Capital A/c.
	  * Offset Capital A/c & Initial Expenses
	  */
	public boolean isOffsetCapitalAcct();

    /** Column name IsSummary */
    public static final String COLUMNNAME_IsSummary = "IsSummary";

	/** Set Summary Level.
	  * This is a summary entity
	  */
	public void setIsSummary (boolean IsSummary);

	/** Get Summary Level.
	  * This is a summary entity
	  */
	public boolean isSummary();

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

    /** Column name OrganizationAcct_ID */
    public static final String COLUMNNAME_OrganizationAcct_ID = "OrganizationAcct_ID";

	/** Set Organization Account	  */
	public void setOrganizationAcct_ID (int OrganizationAcct_ID);

	/** Get Organization Account	  */
	public int getOrganizationAcct_ID();

	public org.compiere.model.I_C_ElementValue getOrganizationAcct() throws RuntimeException;

    /** Column name OrganizationEarningsAcct_ID */
    public static final String COLUMNNAME_OrganizationEarningsAcct_ID = "OrganizationEarningsAcct_ID";

	/** Set Earnings Account	  */
	public void setOrganizationEarningsAcct_ID (int OrganizationEarningsAcct_ID);

	/** Get Earnings Account	  */
	public int getOrganizationEarningsAcct_ID();

	public org.compiere.model.I_C_ElementValue getOrganizationEarningsAcct() throws RuntimeException;

    /** Column name OrgType */
    public static final String COLUMNNAME_OrgType = "OrgType";

	/** Set Organization Type	  */
	public void setOrgType (String OrgType);

	/** Get Organization Type	  */
	public String getOrgType();

    /** Column name ReportHeading */
    public static final String COLUMNNAME_ReportHeading = "ReportHeading";

	/** Set Report Heading	  */
	public void setReportHeading (String ReportHeading);

	/** Get Report Heading	  */
	public String getReportHeading();

    /** Column name ShortAddress */
    public static final String COLUMNNAME_ShortAddress = "ShortAddress";

	/** Set ShortAddress	  */
	public void setShortAddress (String ShortAddress);

	/** Get ShortAddress	  */
	public String getShortAddress();

    /** Column name ShortName */
    public static final String COLUMNNAME_ShortName = "ShortName";

	/** Set Short Name	  */
	public void setShortName (String ShortName);

	/** Get Short Name	  */
	public String getShortName();

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

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
