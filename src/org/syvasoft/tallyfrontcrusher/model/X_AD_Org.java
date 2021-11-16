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

/** Generated Model for AD_Org
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_AD_Org extends PO implements I_AD_Org, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20211115L;

    /** Standard Constructor */
    public X_AD_Org (Properties ctx, int AD_Org_ID, String trxName)
    {
      super (ctx, AD_Org_ID, trxName);
      /** if (AD_Org_ID == 0)
        {
			setIsDontCountMeInConcurrSessions (true);
// Y
			setIsSummary (false);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_AD_Org (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_Org[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set AD_Org_UU.
		@param AD_Org_UU AD_Org_UU	  */
	public void setAD_Org_UU (String AD_Org_UU)
	{
		set_Value (COLUMNNAME_AD_Org_UU, AD_Org_UU);
	}

	/** Get AD_Org_UU.
		@return AD_Org_UU	  */
	public String getAD_Org_UU () 
	{
		return (String)get_Value(COLUMNNAME_AD_Org_UU);
	}

	/** Set Head Office.
		@param AD_OrgHO_ID Head Office	  */
	public void setAD_OrgHO_ID (int AD_OrgHO_ID)
	{
		if (AD_OrgHO_ID < 1) 
			set_Value (COLUMNNAME_AD_OrgHO_ID, null);
		else 
			set_Value (COLUMNNAME_AD_OrgHO_ID, Integer.valueOf(AD_OrgHO_ID));
	}

	/** Get Head Office.
		@return Head Office	  */
	public int getAD_OrgHO_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgHO_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_ReplicationStrategy getAD_ReplicationStrategy() throws RuntimeException
    {
		return (org.compiere.model.I_AD_ReplicationStrategy)MTable.get(getCtx(), org.compiere.model.I_AD_ReplicationStrategy.Table_Name)
			.getPO(getAD_ReplicationStrategy_ID(), get_TrxName());	}

	/** Set Replication Strategy.
		@param AD_ReplicationStrategy_ID 
		Data Replication Strategy
	  */
	public void setAD_ReplicationStrategy_ID (int AD_ReplicationStrategy_ID)
	{
		if (AD_ReplicationStrategy_ID < 1) 
			set_Value (COLUMNNAME_AD_ReplicationStrategy_ID, null);
		else 
			set_Value (COLUMNNAME_AD_ReplicationStrategy_ID, Integer.valueOf(AD_ReplicationStrategy_ID));
	}

	/** Get Replication Strategy.
		@return Data Replication Strategy
	  */
	public int getAD_ReplicationStrategy_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ReplicationStrategy_ID);
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

	public org.compiere.model.I_C_ElementValue getInstantPettyCashAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getInstantPettyCashAcct_ID(), get_TrxName());	}

	/** Set Instant Petty Cash Account.
		@param InstantPettyCashAcct_ID Instant Petty Cash Account	  */
	public void setInstantPettyCashAcct_ID (int InstantPettyCashAcct_ID)
	{
		if (InstantPettyCashAcct_ID < 1) 
			set_Value (COLUMNNAME_InstantPettyCashAcct_ID, null);
		else 
			set_Value (COLUMNNAME_InstantPettyCashAcct_ID, Integer.valueOf(InstantPettyCashAcct_ID));
	}

	/** Get Instant Petty Cash Account.
		@return Instant Petty Cash Account	  */
	public int getInstantPettyCashAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_InstantPettyCashAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getInvestmentAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getInvestmentAcct_ID(), get_TrxName());	}

	/** Set Investment Account.
		@param InvestmentAcct_ID Investment Account	  */
	public void setInvestmentAcct_ID (int InvestmentAcct_ID)
	{
		if (InvestmentAcct_ID < 1) 
			set_Value (COLUMNNAME_InvestmentAcct_ID, null);
		else 
			set_Value (COLUMNNAME_InvestmentAcct_ID, Integer.valueOf(InvestmentAcct_ID));
	}

	/** Get Investment Account.
		@return Investment Account	  */
	public int getInvestmentAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_InvestmentAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Investment Detail.
		@param InvestmentDetail Investment Detail	  */
	public void setInvestmentDetail (String InvestmentDetail)
	{
		set_Value (COLUMNNAME_InvestmentDetail, InvestmentDetail);
	}

	/** Get Investment Detail.
		@return Investment Detail	  */
	public String getInvestmentDetail () 
	{
		return (String)get_Value(COLUMNNAME_InvestmentDetail);
	}

	/** Set Demo.
		@param IsDemo Demo	  */
	public void setIsDemo (boolean IsDemo)
	{
		set_Value (COLUMNNAME_IsDemo, Boolean.valueOf(IsDemo));
	}

	/** Get Demo.
		@return Demo	  */
	public boolean isDemo () 
	{
		Object oo = get_Value(COLUMNNAME_IsDemo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Don't count me in concurrent sessions.
		@param IsDontCountMeInConcurrSessions 
		If you tick this checkbox, the record (user, role, org) won't be taken in consideration for checking limit of concurrent sessions
	  */
	public void setIsDontCountMeInConcurrSessions (boolean IsDontCountMeInConcurrSessions)
	{
		set_Value (COLUMNNAME_IsDontCountMeInConcurrSessions, Boolean.valueOf(IsDontCountMeInConcurrSessions));
	}

	/** Get Don't count me in concurrent sessions.
		@return If you tick this checkbox, the record (user, role, org) won't be taken in consideration for checking limit of concurrent sessions
	  */
	public boolean isDontCountMeInConcurrSessions () 
	{
		Object oo = get_Value(COLUMNNAME_IsDontCountMeInConcurrSessions);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Offset Capital A/c.
		@param IsOffsetCapitalAcct 
		Offset Capital A/c & Initial Expenses
	  */
	public void setIsOffsetCapitalAcct (boolean IsOffsetCapitalAcct)
	{
		set_Value (COLUMNNAME_IsOffsetCapitalAcct, Boolean.valueOf(IsOffsetCapitalAcct));
	}

	/** Get Offset Capital A/c.
		@return Offset Capital A/c & Initial Expenses
	  */
	public boolean isOffsetCapitalAcct () 
	{
		Object oo = get_Value(COLUMNNAME_IsOffsetCapitalAcct);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Summary Level.
		@param IsSummary 
		This is a summary entity
	  */
	public void setIsSummary (boolean IsSummary)
	{
		set_Value (COLUMNNAME_IsSummary, Boolean.valueOf(IsSummary));
	}

	/** Get Summary Level.
		@return This is a summary entity
	  */
	public boolean isSummary () 
	{
		Object oo = get_Value(COLUMNNAME_IsSummary);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	public org.compiere.model.I_C_ElementValue getOrganizationAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getOrganizationAcct_ID(), get_TrxName());	}

	/** Set Organization Account.
		@param OrganizationAcct_ID Organization Account	  */
	public void setOrganizationAcct_ID (int OrganizationAcct_ID)
	{
		if (OrganizationAcct_ID < 1) 
			set_Value (COLUMNNAME_OrganizationAcct_ID, null);
		else 
			set_Value (COLUMNNAME_OrganizationAcct_ID, Integer.valueOf(OrganizationAcct_ID));
	}

	/** Get Organization Account.
		@return Organization Account	  */
	public int getOrganizationAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_OrganizationAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getOrganizationEarningsAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getOrganizationEarningsAcct_ID(), get_TrxName());	}

	/** Set Earnings Account.
		@param OrganizationEarningsAcct_ID Earnings Account	  */
	public void setOrganizationEarningsAcct_ID (int OrganizationEarningsAcct_ID)
	{
		if (OrganizationEarningsAcct_ID < 1) 
			set_Value (COLUMNNAME_OrganizationEarningsAcct_ID, null);
		else 
			set_Value (COLUMNNAME_OrganizationEarningsAcct_ID, Integer.valueOf(OrganizationEarningsAcct_ID));
	}

	/** Get Earnings Account.
		@return Earnings Account	  */
	public int getOrganizationEarningsAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_OrganizationEarningsAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Crusher = C */
	public static final String ORGTYPE_Crusher = "C";
	/** Sand Block Bucket = S */
	public static final String ORGTYPE_SandBlockBucket = "S";
	/** Trading = T */
	public static final String ORGTYPE_Trading = "T";
	/** Head Office = H */
	public static final String ORGTYPE_HeadOffice = "H";
	/** Sand Block Weighbridge = W */
	public static final String ORGTYPE_SandBlockWeighbridge = "W";
	/** Set Organization Type.
		@param OrgType Organization Type	  */
	public void setOrgType (String OrgType)
	{

		set_Value (COLUMNNAME_OrgType, OrgType);
	}

	/** Get Organization Type.
		@return Organization Type	  */
	public String getOrgType () 
	{
		return (String)get_Value(COLUMNNAME_OrgType);
	}

	/** Set Report Heading.
		@param ReportHeading Report Heading	  */
	public void setReportHeading (String ReportHeading)
	{
		set_Value (COLUMNNAME_ReportHeading, ReportHeading);
	}

	/** Get Report Heading.
		@return Report Heading	  */
	public String getReportHeading () 
	{
		return (String)get_Value(COLUMNNAME_ReportHeading);
	}

	/** Set ShortAddress.
		@param ShortAddress ShortAddress	  */
	public void setShortAddress (String ShortAddress)
	{
		set_Value (COLUMNNAME_ShortAddress, ShortAddress);
	}

	/** Get ShortAddress.
		@return ShortAddress	  */
	public String getShortAddress () 
	{
		return (String)get_Value(COLUMNNAME_ShortAddress);
	}

	/** Set Short Name.
		@param ShortName Short Name	  */
	public void setShortName (String ShortName)
	{
		set_Value (COLUMNNAME_ShortName, ShortName);
	}

	/** Get Short Name.
		@return Short Name	  */
	public String getShortName () 
	{
		return (String)get_Value(COLUMNNAME_ShortName);
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
}