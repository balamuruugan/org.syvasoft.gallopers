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

/** Generated Interface for TF_EmployeeSalary_Det
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_TF_EmployeeSalary_Det 
{

    /** TableName=TF_EmployeeSalary_Det */
    public static final String Table_Name = "TF_EmployeeSalary_Det";

    /** AD_Table_ID=1000332 */
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

    /** Column name AdvancePaid */
    public static final String COLUMNNAME_AdvancePaid = "AdvancePaid";

	/** Set Advance Paid	  */
	public void setAdvancePaid (BigDecimal AdvancePaid);

	/** Get Advance Paid	  */
	public BigDecimal getAdvancePaid();

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

    /** Column name DateJoining */
    public static final String COLUMNNAME_DateJoining = "DateJoining";

	/** Set Date of Joining	  */
	public void setDateJoining (Timestamp DateJoining);

	/** Get Date of Joining	  */
	public Timestamp getDateJoining();

    /** Column name DeductAdvance */
    public static final String COLUMNNAME_DeductAdvance = "DeductAdvance";

	/** Set Deduct Advance	  */
	public void setDeductAdvance (BigDecimal DeductAdvance);

	/** Get Deduct Advance	  */
	public BigDecimal getDeductAdvance();

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

    /** Column name Designation */
    public static final String COLUMNNAME_Designation = "Designation";

	/** Set Designation	  */
	public void setDesignation (String Designation);

	/** Get Designation	  */
	public String getDesignation();

    /** Column name EmpNo */
    public static final String COLUMNNAME_EmpNo = "EmpNo";

	/** Set Emp No	  */
	public void setEmpNo (String EmpNo);

	/** Get Emp No	  */
	public String getEmpNo();

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

    /** Column name MessAdvance */
    public static final String COLUMNNAME_MessAdvance = "MessAdvance";

	/** Set Mess Advance	  */
	public void setMessAdvance (BigDecimal MessAdvance);

	/** Get Mess Advance	  */
	public BigDecimal getMessAdvance();

    /** Column name NetSalary */
    public static final String COLUMNNAME_NetSalary = "NetSalary";

	/** Set Net Salary	  */
	public void setNetSalary (BigDecimal NetSalary);

	/** Get Net Salary	  */
	public BigDecimal getNetSalary();

    /** Column name NoOfDays */
    public static final String COLUMNNAME_NoOfDays = "NoOfDays";

	/** Set No Of Days	  */
	public void setNoOfDays (BigDecimal NoOfDays);

	/** Get No Of Days	  */
	public BigDecimal getNoOfDays();

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

    /** Column name Salary */
    public static final String COLUMNNAME_Salary = "Salary";

	/** Set Salary	  */
	public void setSalary (BigDecimal Salary);

	/** Get Salary	  */
	public BigDecimal getSalary();

    /** Column name SalaryDue */
    public static final String COLUMNNAME_SalaryDue = "SalaryDue";

	/** Set Salary Due	  */
	public void setSalaryDue (BigDecimal SalaryDue);

	/** Get Salary Due	  */
	public BigDecimal getSalaryDue();

    /** Column name SNo */
    public static final String COLUMNNAME_SNo = "SNo";

	/** Set S No	  */
	public void setSNo (int SNo);

	/** Get S No	  */
	public int getSNo();

    /** Column name TF_EmployeeSalary_Det_ID */
    public static final String COLUMNNAME_TF_EmployeeSalary_Det_ID = "TF_EmployeeSalary_Det_ID";

	/** Set Employee Salary Detail	  */
	public void setTF_EmployeeSalary_Det_ID (int TF_EmployeeSalary_Det_ID);

	/** Get Employee Salary Detail	  */
	public int getTF_EmployeeSalary_Det_ID();

    /** Column name TF_EmployeeSalary_Det_UU */
    public static final String COLUMNNAME_TF_EmployeeSalary_Det_UU = "TF_EmployeeSalary_Det_UU";

	/** Set TF_EmployeeSalary_Det_UU	  */
	public void setTF_EmployeeSalary_Det_UU (String TF_EmployeeSalary_Det_UU);

	/** Get TF_EmployeeSalary_Det_UU	  */
	public String getTF_EmployeeSalary_Det_UU();

    /** Column name TF_EmployeeSalary_ID */
    public static final String COLUMNNAME_TF_EmployeeSalary_ID = "TF_EmployeeSalary_ID";

	/** Set Employee Salary	  */
	public void setTF_EmployeeSalary_ID (int TF_EmployeeSalary_ID);

	/** Get Employee Salary	  */
	public int getTF_EmployeeSalary_ID();

	public I_TF_EmployeeSalary getTF_EmployeeSalary() throws RuntimeException;

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
