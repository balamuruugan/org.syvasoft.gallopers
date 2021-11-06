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

/** Generated Interface for TF_BiometricAttendence
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_TF_BiometricAttendence 
{

    /** TableName=TF_BiometricAttendence */
    public static final String Table_Name = "TF_BiometricAttendence";

    /** AD_Table_ID=1000382 */
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

    /** Column name AttendenceTime */
    public static final String COLUMNNAME_AttendenceTime = "AttendenceTime";

	/** Set Attendence Time	  */
	public void setAttendenceTime (Timestamp AttendenceTime);

	/** Get Attendence Time	  */
	public Timestamp getAttendenceTime();

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

    /** Column name DateAcct */
    public static final String COLUMNNAME_DateAcct = "DateAcct";

	/** Set Account Date.
	  * Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct);

	/** Get Account Date.
	  * Accounting Date
	  */
	public Timestamp getDateAcct();

    /** Column name EnrollNo */
    public static final String COLUMNNAME_EnrollNo = "EnrollNo";

	/** Set Enroll No	  */
	public void setEnrollNo (int EnrollNo);

	/** Get Enroll No	  */
	public int getEnrollNo();

    /** Column name InOutMode */
    public static final String COLUMNNAME_InOutMode = "InOutMode";

	/** Set In Out Mode	  */
	public void setInOutMode (String InOutMode);

	/** Get In Out Mode	  */
	public String getInOutMode();

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

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name TF_Biometricattendence_ID */
    public static final String COLUMNNAME_TF_Biometricattendence_ID = "TF_Biometricattendence_ID";

	/** Set Biometric Attendence	  */
	public void setTF_Biometricattendence_ID (int TF_Biometricattendence_ID);

	/** Get Biometric Attendence	  */
	public int getTF_Biometricattendence_ID();

    /** Column name TF_Biometricattendence_UU */
    public static final String COLUMNNAME_TF_Biometricattendence_UU = "TF_Biometricattendence_UU";

	/** Set TF_Biometricattendence_UU	  */
	public void setTF_Biometricattendence_UU (String TF_Biometricattendence_UU);

	/** Get TF_Biometricattendence_UU	  */
	public String getTF_Biometricattendence_UU();

    /** Column name TF_EmployeeAttendance_ID */
    public static final String COLUMNNAME_TF_EmployeeAttendance_ID = "TF_EmployeeAttendance_ID";

	/** Set Employee Attendance	  */
	public void setTF_EmployeeAttendance_ID (int TF_EmployeeAttendance_ID);

	/** Get Employee Attendance	  */
	public int getTF_EmployeeAttendance_ID();

	public I_TF_EmployeeAttendance getTF_EmployeeAttendance() throws RuntimeException;

    /** Column name TF_EmpShift_ID */
    public static final String COLUMNNAME_TF_EmpShift_ID = "TF_EmpShift_ID";

	/** Set Employee Shift	  */
	public void setTF_EmpShift_ID (int TF_EmpShift_ID);

	/** Get Employee Shift	  */
	public int getTF_EmpShift_ID();

	public I_TF_EmpShift getTF_EmpShift() throws RuntimeException;

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

    /** Column name VerifyMode */
    public static final String COLUMNNAME_VerifyMode = "VerifyMode";

	/** Set Verify Mode	  */
	public void setVerifyMode (String VerifyMode);

	/** Get Verify Mode	  */
	public String getVerifyMode();

    /** Column name WorkCode */
    public static final String COLUMNNAME_WorkCode = "WorkCode";

	/** Set Work Code	  */
	public void setWorkCode (int WorkCode);

	/** Get Work Code	  */
	public int getWorkCode();
}
