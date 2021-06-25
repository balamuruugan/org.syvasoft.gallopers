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

/** Generated Interface for TF_TripSheet_Salary
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_TF_TripSheet_Salary 
{

    /** TableName=TF_TripSheet_Salary */
    public static final String Table_Name = "TF_TripSheet_Salary";

    /** AD_Table_ID=1000343 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

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

    /** Column name DayIncentive */
    public static final String COLUMNNAME_DayIncentive = "DayIncentive";

	/** Set Day Incentive	  */
	public void setDayIncentive (BigDecimal DayIncentive);

	/** Get Day Incentive	  */
	public BigDecimal getDayIncentive();

    /** Column name DrillingQty */
    public static final String COLUMNNAME_DrillingQty = "DrillingQty";

	/** Set Drilling Qty	  */
	public void setDrillingQty (BigDecimal DrillingQty);

	/** Get Drilling Qty	  */
	public BigDecimal getDrillingQty();

    /** Column name Earned_Wage */
    public static final String COLUMNNAME_Earned_Wage = "Earned_Wage";

	/** Set Earned Wage	  */
	public void setEarned_Wage (BigDecimal Earned_Wage);

	/** Get Earned Wage	  */
	public BigDecimal getEarned_Wage();

    /** Column name EligibleUnit */
    public static final String COLUMNNAME_EligibleUnit = "EligibleUnit";

	/** Set Eligible Unit	  */
	public void setEligibleUnit (BigDecimal EligibleUnit);

	/** Get Eligible Unit	  */
	public BigDecimal getEligibleUnit();

    /** Column name Incentive */
    public static final String COLUMNNAME_Incentive = "Incentive";

	/** Set Incentive / OT	  */
	public void setIncentive (BigDecimal Incentive);

	/** Get Incentive / OT	  */
	public BigDecimal getIncentive();

    /** Column name IncentiveType */
    public static final String COLUMNNAME_IncentiveType = "IncentiveType";

	/** Set Incentive Type	  */
	public void setIncentiveType (String IncentiveType);

	/** Get Incentive Type	  */
	public String getIncentiveType();

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

    /** Column name TF_TripSheet_ID */
    public static final String COLUMNNAME_TF_TripSheet_ID = "TF_TripSheet_ID";

	/** Set Trip Sheet	  */
	public void setTF_TripSheet_ID (int TF_TripSheet_ID);

	/** Get Trip Sheet	  */
	public int getTF_TripSheet_ID();

	public I_TF_TripSheet getTF_TripSheet() throws RuntimeException;

    /** Column name TF_TripSheet_Salary_ID */
    public static final String COLUMNNAME_TF_TripSheet_Salary_ID = "TF_TripSheet_Salary_ID";

	/** Set Addtional Labour Salary	  */
	public void setTF_TripSheet_Salary_ID (int TF_TripSheet_Salary_ID);

	/** Get Addtional Labour Salary	  */
	public int getTF_TripSheet_Salary_ID();

    /** Column name TF_TripSheet_Salary_UU */
    public static final String COLUMNNAME_TF_TripSheet_Salary_UU = "TF_TripSheet_Salary_UU";

	/** Set TF_TripSheet_Salary_UU	  */
	public void setTF_TripSheet_Salary_UU (String TF_TripSheet_Salary_UU);

	/** Get TF_TripSheet_Salary_UU	  */
	public String getTF_TripSheet_Salary_UU();

    /** Column name UnitIncentive */
    public static final String COLUMNNAME_UnitIncentive = "UnitIncentive";

	/** Set Unit Incentive	  */
	public void setUnitIncentive (BigDecimal UnitIncentive);

	/** Get Unit Incentive	  */
	public BigDecimal getUnitIncentive();

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
}
