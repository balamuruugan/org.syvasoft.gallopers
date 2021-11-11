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

/** Generated Interface for TF_Employee_Salary
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_TF_Employee_Salary 
{

    /** TableName=TF_Employee_Salary */
    public static final String Table_Name = "TF_Employee_Salary";

    /** AD_Table_ID=1000169 */
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

    /** Column name C_ElementValue_ID */
    public static final String COLUMNNAME_C_ElementValue_ID = "C_ElementValue_ID";

	/** Set Account Element.
	  * Account Element
	  */
	public void setC_ElementValue_ID (int C_ElementValue_ID);

	/** Get Account Element.
	  * Account Element
	  */
	public int getC_ElementValue_ID();

	public org.compiere.model.I_C_ElementValue getC_ElementValue() throws RuntimeException;

    /** Column name C_Invoice_ID */
    public static final String COLUMNNAME_C_Invoice_ID = "C_Invoice_ID";

	/** Set Invoice.
	  * Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID);

	/** Get Invoice.
	  * Invoice Identifier
	  */
	public int getC_Invoice_ID();

	public org.compiere.model.I_C_Invoice getC_Invoice() throws RuntimeException;

    /** Column name C_Project_ID */
    public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";

	/** Set Subcontract / Project.
	  * Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID);

	/** Get Subcontract / Project.
	  * Financial Project
	  */
	public int getC_Project_ID();

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException;

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

    /** Column name DateFrom */
    public static final String COLUMNNAME_DateFrom = "DateFrom";

	/** Set Date From.
	  * Starting date for a range
	  */
	public void setDateFrom (Timestamp DateFrom);

	/** Get Date From.
	  * Starting date for a range
	  */
	public Timestamp getDateFrom();

    /** Column name DateTo */
    public static final String COLUMNNAME_DateTo = "DateTo";

	/** Set Date To.
	  * End date of a date range
	  */
	public void setDateTo (Timestamp DateTo);

	/** Get Date To.
	  * End date of a date range
	  */
	public Timestamp getDateTo();

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

    /** Column name DocStatus */
    public static final String COLUMNNAME_DocStatus = "DocStatus";

	/** Set Document Status.
	  * The current status of the document
	  */
	public void setDocStatus (String DocStatus);

	/** Get Document Status.
	  * The current status of the document
	  */
	public String getDocStatus();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name GL_Journal_ID */
    public static final String COLUMNNAME_GL_Journal_ID = "GL_Journal_ID";

	/** Set Journal.
	  * General Ledger Journal
	  */
	public void setGL_Journal_ID (int GL_Journal_ID);

	/** Get Journal.
	  * General Ledger Journal
	  */
	public int getGL_Journal_ID();

	public org.compiere.model.I_GL_Journal getGL_Journal() throws RuntimeException;

    /** Column name Incentive */
    public static final String COLUMNNAME_Incentive = "Incentive";

	/** Set Incentive / OT	  */
	public void setIncentive (BigDecimal Incentive);

	/** Get Incentive / OT	  */
	public BigDecimal getIncentive();

    /** Column name IncentiveDays */
    public static final String COLUMNNAME_IncentiveDays = "IncentiveDays";

	/** Set Incentive Days	  */
	public void setIncentiveDays (BigDecimal IncentiveDays);

	/** Get Incentive Days	  */
	public BigDecimal getIncentiveDays();

    /** Column name IncentiveEligibleDays */
    public static final String COLUMNNAME_IncentiveEligibleDays = "IncentiveEligibleDays";

	/** Set Incentive Eligible Days	  */
	public void setIncentiveEligibleDays (BigDecimal IncentiveEligibleDays);

	/** Get Incentive Eligible Days	  */
	public BigDecimal getIncentiveEligibleDays();

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

    /** Column name IsBiometricAttendance */
    public static final String COLUMNNAME_IsBiometricAttendance = "IsBiometricAttendance";

	/** Set Biometric Attendance	  */
	public void setIsBiometricAttendance (boolean IsBiometricAttendance);

	/** Get Biometric Attendance	  */
	public boolean isBiometricAttendance();

    /** Column name IsCalculated */
    public static final String COLUMNNAME_IsCalculated = "IsCalculated";

	/** Set Calculated.
	  * The value is calculated by the system
	  */
	public void setIsCalculated (boolean IsCalculated);

	/** Get Calculated.
	  * The value is calculated by the system
	  */
	public boolean isCalculated();

    /** Column name Present_Days */
    public static final String COLUMNNAME_Present_Days = "Present_Days";

	/** Set Present Days	  */
	public void setPresent_Days (BigDecimal Present_Days);

	/** Get Present Days	  */
	public BigDecimal getPresent_Days();

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

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name ProductionBonus */
    public static final String COLUMNNAME_ProductionBonus = "ProductionBonus";

	/** Set Production Bonus	  */
	public void setProductionBonus (BigDecimal ProductionBonus);

	/** Get Production Bonus	  */
	public BigDecimal getProductionBonus();

    /** Column name Salary_Amt */
    public static final String COLUMNNAME_Salary_Amt = "Salary_Amt";

	/** Set Earned Salary	  */
	public void setSalary_Amt (BigDecimal Salary_Amt);

	/** Get Earned Salary	  */
	public BigDecimal getSalary_Amt();

    /** Column name Std_Days */
    public static final String COLUMNNAME_Std_Days = "Std_Days";

	/** Set Standard Days	  */
	public void setStd_Days (BigDecimal Std_Days);

	/** Get Standard Days	  */
	public BigDecimal getStd_Days();

    /** Column name Std_Wage */
    public static final String COLUMNNAME_Std_Wage = "Std_Wage";

	/** Set Wage / day	  */
	public void setStd_Wage (BigDecimal Std_Wage);

	/** Get Wage / day	  */
	public BigDecimal getStd_Wage();

    /** Column name TF_Employee_Salary_ID */
    public static final String COLUMNNAME_TF_Employee_Salary_ID = "TF_Employee_Salary_ID";

	/** Set Employee Salary	  */
	public void setTF_Employee_Salary_ID (int TF_Employee_Salary_ID);

	/** Get Employee Salary	  */
	public int getTF_Employee_Salary_ID();

    /** Column name TF_Employee_Salary_UU */
    public static final String COLUMNNAME_TF_Employee_Salary_UU = "TF_Employee_Salary_UU";

	/** Set TF_Employee_Salary_UU	  */
	public void setTF_Employee_Salary_UU (String TF_Employee_Salary_UU);

	/** Get TF_Employee_Salary_UU	  */
	public String getTF_Employee_Salary_UU();

    /** Column name TF_Quarry_ID */
    public static final String COLUMNNAME_TF_Quarry_ID = "TF_Quarry_ID";

	/** Set Quarry	  */
	public void setTF_Quarry_ID (int TF_Quarry_ID);

	/** Get Quarry	  */
	public int getTF_Quarry_ID();

	public I_TF_Quarry getTF_Quarry() throws RuntimeException;

    /** Column name TF_TripSheet_ID */
    public static final String COLUMNNAME_TF_TripSheet_ID = "TF_TripSheet_ID";

	/** Set Trip Sheet	  */
	public void setTF_TripSheet_ID (int TF_TripSheet_ID);

	/** Get Trip Sheet	  */
	public int getTF_TripSheet_ID();

	public I_TF_TripSheet getTF_TripSheet() throws RuntimeException;

    /** Column name TF_VehicleType_ID */
    public static final String COLUMNNAME_TF_VehicleType_ID = "TF_VehicleType_ID";

	/** Set Vehicle Type	  */
	public void setTF_VehicleType_ID (int TF_VehicleType_ID);

	/** Get Vehicle Type	  */
	public int getTF_VehicleType_ID();

	public I_TF_VehicleType getTF_VehicleType() throws RuntimeException;

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

    /** Column name User1_ID */
    public static final String COLUMNNAME_User1_ID = "User1_ID";

	/** Set Department.
	  * User defined list element #1
	  */
	public void setUser1_ID (int User1_ID);

	/** Get Department.
	  * User defined list element #1
	  */
	public int getUser1_ID();

	public org.compiere.model.I_C_ElementValue getUser1() throws RuntimeException;
}
