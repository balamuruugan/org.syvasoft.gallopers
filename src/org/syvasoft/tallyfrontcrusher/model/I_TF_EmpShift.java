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

/** Generated Interface for TF_EmpShift
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_TF_EmpShift 
{

    /** TableName=TF_EmpShift */
    public static final String Table_Name = "TF_EmpShift";

    /** AD_Table_ID=1000383 */
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

    /** Column name BeginTime */
    public static final String COLUMNNAME_BeginTime = "BeginTime";

	/** Set Begin Time (HH:MM 24hr fmt)	  */
	public void setBeginTime (String BeginTime);

	/** Get Begin Time (HH:MM 24hr fmt)	  */
	public String getBeginTime();

    /** Column name Break1BeginTime */
    public static final String COLUMNNAME_Break1BeginTime = "Break1BeginTime";

	/** Set Break1 Begin Time (HH:MM 24hr fmt)	  */
	public void setBreak1BeginTime (String Break1BeginTime);

	/** Get Break1 Begin Time (HH:MM 24hr fmt)	  */
	public String getBreak1BeginTime();

    /** Column name Break1EndTime */
    public static final String COLUMNNAME_Break1EndTime = "Break1EndTime";

	/** Set Break1 End Time (HH:MM 24hr fmt)	  */
	public void setBreak1EndTime (String Break1EndTime);

	/** Get Break1 End Time (HH:MM 24hr fmt)	  */
	public String getBreak1EndTime();

    /** Column name Break2BeginTime */
    public static final String COLUMNNAME_Break2BeginTime = "Break2BeginTime";

	/** Set Break2 Begin Time (HH:MM 24hr fmt)	  */
	public void setBreak2BeginTime (String Break2BeginTime);

	/** Get Break2 Begin Time (HH:MM 24hr fmt)	  */
	public String getBreak2BeginTime();

    /** Column name Break2EndTime */
    public static final String COLUMNNAME_Break2EndTime = "Break2EndTime";

	/** Set Break2 End Time (HH:MM 24hr fmt)	  */
	public void setBreak2EndTime (String Break2EndTime);

	/** Get Break2 End Time (HH:MM 24hr fmt)	  */
	public String getBreak2EndTime();

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

    /** Column name EndTime */
    public static final String COLUMNNAME_EndTime = "EndTime";

	/** Set End Time (HH:MM 24hr fmt).
	  * End of the time span
	  */
	public void setEndTime (String EndTime);

	/** Get End Time (HH:MM 24hr fmt).
	  * End of the time span
	  */
	public String getEndTime();

    /** Column name GraceTime */
    public static final String COLUMNNAME_GraceTime = "GraceTime";

	/** Set Grace Time (mins).
	  * To calculate late attendance
	  */
	public void setGraceTime (int GraceTime);

	/** Get Grace Time (mins).
	  * To calculate late attendance
	  */
	public int getGraceTime();

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

    /** Column name IsBreak1 */
    public static final String COLUMNNAME_IsBreak1 = "IsBreak1";

	/** Set Break1	  */
	public void setIsBreak1 (boolean IsBreak1);

	/** Get Break1	  */
	public boolean isBreak1();

    /** Column name IsBreak2 */
    public static final String COLUMNNAME_IsBreak2 = "IsBreak2";

	/** Set Break2	  */
	public void setIsBreak2 (boolean IsBreak2);

	/** Get Break2	  */
	public boolean isBreak2();

    /** Column name IsFlexiShift */
    public static final String COLUMNNAME_IsFlexiShift = "IsFlexiShift";

	/** Set Is Flexi Shift	  */
	public void setIsFlexiShift (boolean IsFlexiShift);

	/** Get Is Flexi Shift	  */
	public boolean isFlexiShift();

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

    /** Column name PartialDay */
    public static final String COLUMNNAME_PartialDay = "PartialDay";

	/** Set Partial Day	  */
	public void setPartialDay (String PartialDay);

	/** Get Partial Day	  */
	public String getPartialDay();

    /** Column name PartialDayBeginTime */
    public static final String COLUMNNAME_PartialDayBeginTime = "PartialDayBeginTime";

	/** Set Partial Day Begin Time	  */
	public void setPartialDayBeginTime (String PartialDayBeginTime);

	/** Get Partial Day Begin Time	  */
	public String getPartialDayBeginTime();

    /** Column name PartialDayEndTime */
    public static final String COLUMNNAME_PartialDayEndTime = "PartialDayEndTime";

	/** Set Partial Day End Time	  */
	public void setPartialDayEndTime (String PartialDayEndTime);

	/** Get Partial Day End Time	  */
	public String getPartialDayEndTime();

    /** Column name PunchBeginBefore */
    public static final String COLUMNNAME_PunchBeginBefore = "PunchBeginBefore";

	/** Set Punch Begin Before (mins)	  */
	public void setPunchBeginBefore (int PunchBeginBefore);

	/** Get Punch Begin Before (mins)	  */
	public int getPunchBeginBefore();

    /** Column name PunchEndAfter */
    public static final String COLUMNNAME_PunchEndAfter = "PunchEndAfter";

	/** Set Punch End After (mins).
	  * Default is Next Day Shift Begin Time - Punch Begin Duration
	  */
	public void setPunchEndAfter (int PunchEndAfter);

	/** Get Punch End After (mins).
	  * Default is Next Day Shift Begin Time - Punch Begin Duration
	  */
	public int getPunchEndAfter();

    /** Column name SName */
    public static final String COLUMNNAME_SName = "SName";

	/** Set Short Name	  */
	public void setSName (String SName);

	/** Get Short Name	  */
	public String getSName();

    /** Column name TF_EmpShift_ID */
    public static final String COLUMNNAME_TF_EmpShift_ID = "TF_EmpShift_ID";

	/** Set Employee Shift	  */
	public void setTF_EmpShift_ID (int TF_EmpShift_ID);

	/** Get Employee Shift	  */
	public int getTF_EmpShift_ID();

    /** Column name TF_EmpShift_UU */
    public static final String COLUMNNAME_TF_EmpShift_UU = "TF_EmpShift_UU";

	/** Set TF_EmpShift_UU	  */
	public void setTF_EmpShift_UU (String TF_EmpShift_UU);

	/** Get TF_EmpShift_UU	  */
	public String getTF_EmpShift_UU();

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
