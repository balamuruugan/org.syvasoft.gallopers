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

/** Generated Interface for TF_TripSheet
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_TF_TripSheet 
{

    /** TableName=TF_TripSheet */
    public static final String Table_Name = "TF_TripSheet";

    /** AD_Table_ID=1000182 */
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

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException;

    /** Column name Closing_Fuel */
    public static final String COLUMNNAME_Closing_Fuel = "Closing_Fuel";

	/** Set Closing Fuel	  */
	public void setClosing_Fuel (BigDecimal Closing_Fuel);

	/** Get Closing Fuel	  */
	public BigDecimal getClosing_Fuel();

    /** Column name Closing_Meter */
    public static final String COLUMNNAME_Closing_Meter = "Closing_Meter";

	/** Set Closing Meter	  */
	public void setClosing_Meter (BigDecimal Closing_Meter);

	/** Get Closing Meter	  */
	public BigDecimal getClosing_Meter();

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

    /** Column name DateEnd */
    public static final String COLUMNNAME_DateEnd = "DateEnd";

	/** Set End Date	  */
	public void setDateEnd (Timestamp DateEnd);

	/** Get End Date	  */
	public Timestamp getDateEnd();

    /** Column name DateReport */
    public static final String COLUMNNAME_DateReport = "DateReport";

	/** Set Report Date.
	  * Expense/Time Report Date
	  */
	public void setDateReport (Timestamp DateReport);

	/** Get Report Date.
	  * Expense/Time Report Date
	  */
	public Timestamp getDateReport();

    /** Column name DateStart */
    public static final String COLUMNNAME_DateStart = "DateStart";

	/** Set Date Start.
	  * Date Start for this Order
	  */
	public void setDateStart (Timestamp DateStart);

	/** Get Date Start.
	  * Date Start for this Order
	  */
	public Timestamp getDateStart();

    /** Column name DayIncentive */
    public static final String COLUMNNAME_DayIncentive = "DayIncentive";

	/** Set Day Incentive	  */
	public void setDayIncentive (BigDecimal DayIncentive);

	/** Get Day Incentive	  */
	public BigDecimal getDayIncentive();

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

    /** Column name Expensed_Fuel */
    public static final String COLUMNNAME_Expensed_Fuel = "Expensed_Fuel";

	/** Set Fuel Expensed	  */
	public void setExpensed_Fuel (BigDecimal Expensed_Fuel);

	/** Get Fuel Expensed	  */
	public BigDecimal getExpensed_Fuel();

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

    /** Column name IsManual */
    public static final String COLUMNNAME_IsManual = "IsManual";

	/** Set Manual.
	  * This is a manual process
	  */
	public void setIsManual (boolean IsManual);

	/** Get Manual.
	  * This is a manual process
	  */
	public boolean isManual();

    /** Column name JobWork_Product_ID */
    public static final String COLUMNNAME_JobWork_Product_ID = "JobWork_Product_ID";

	/** Set Job Work	  */
	public void setJobWork_Product_ID (int JobWork_Product_ID);

	/** Get Job Work	  */
	public int getJobWork_Product_ID();

	public org.compiere.model.I_M_Product getJobWork_Product() throws RuntimeException;

    /** Column name NoOfLoad */
    public static final String COLUMNNAME_NoOfLoad = "NoOfLoad";

	/** Set No of Load	  */
	public void setNoOfLoad (BigDecimal NoOfLoad);

	/** Get No of Load	  */
	public BigDecimal getNoOfLoad();

    /** Column name Opening_Fuel */
    public static final String COLUMNNAME_Opening_Fuel = "Opening_Fuel";

	/** Set Opening Fuel	  */
	public void setOpening_Fuel (BigDecimal Opening_Fuel);

	/** Get Opening Fuel	  */
	public BigDecimal getOpening_Fuel();

    /** Column name Opening_Meter */
    public static final String COLUMNNAME_Opening_Meter = "Opening_Meter";

	/** Set Opening Meter	  */
	public void setOpening_Meter (BigDecimal Opening_Meter);

	/** Get Opening Meter	  */
	public BigDecimal getOpening_Meter();

    /** Column name PM_Machinery_ID */
    public static final String COLUMNNAME_PM_Machinery_ID = "PM_Machinery_ID";

	/** Set Machinery	  */
	public void setPM_Machinery_ID (int PM_Machinery_ID);

	/** Get Machinery	  */
	public int getPM_Machinery_ID();

	public I_PM_Machinery getPM_Machinery() throws RuntimeException;

    /** Column name PM_Meter_Log_ID */
    public static final String COLUMNNAME_PM_Meter_Log_ID = "PM_Meter_Log_ID";

	/** Set Machinery Meter Log	  */
	public void setPM_Meter_Log_ID (int PM_Meter_Log_ID);

	/** Get Machinery Meter Log	  */
	public int getPM_Meter_Log_ID();

	public I_PM_Meter_Log getPM_Meter_Log() throws RuntimeException;

    /** Column name PostLabourWage */
    public static final String COLUMNNAME_PostLabourWage = "PostLabourWage";

	/** Set Post Labour Wage	  */
	public void setPostLabourWage (String PostLabourWage);

	/** Get Post Labour Wage	  */
	public String getPostLabourWage();

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

    /** Column name Qty */
    public static final String COLUMNNAME_Qty = "Qty";

	/** Set Quantity.
	  * Quantity
	  */
	public void setQty (BigDecimal Qty);

	/** Get Quantity.
	  * Quantity
	  */
	public BigDecimal getQty();

    /** Column name Rate */
    public static final String COLUMNNAME_Rate = "Rate";

	/** Set Rate.
	  * Rate or Tax or Exchange
	  */
	public void setRate (BigDecimal Rate);

	/** Get Rate.
	  * Rate or Tax or Exchange
	  */
	public BigDecimal getRate();

    /** Column name Received_Fuel */
    public static final String COLUMNNAME_Received_Fuel = "Received_Fuel";

	/** Set Received Fuel	  */
	public void setReceived_Fuel (BigDecimal Received_Fuel);

	/** Get Received Fuel	  */
	public BigDecimal getReceived_Fuel();

    /** Column name Rent_Amt */
    public static final String COLUMNNAME_Rent_Amt = "Rent_Amt";

	/** Set Rent (Amount)	  */
	public void setRent_Amt (BigDecimal Rent_Amt);

	/** Get Rent (Amount)	  */
	public BigDecimal getRent_Amt();

    /** Column name Rent_UOM_ID */
    public static final String COLUMNNAME_Rent_UOM_ID = "Rent_UOM_ID";

	/** Set Delivery UOM	  */
	public void setRent_UOM_ID (int Rent_UOM_ID);

	/** Get Delivery UOM	  */
	public int getRent_UOM_ID();

	public org.compiere.model.I_C_UOM getRent_UOM() throws RuntimeException;

    /** Column name Running_Meter */
    public static final String COLUMNNAME_Running_Meter = "Running_Meter";

	/** Set Running Meter	  */
	public void setRunning_Meter (BigDecimal Running_Meter);

	/** Get Running Meter	  */
	public BigDecimal getRunning_Meter();

    /** Column name Shift */
    public static final String COLUMNNAME_Shift = "Shift";

	/** Set Shift	  */
	public void setShift (String Shift);

	/** Get Shift	  */
	public String getShift();

    /** Column name Subcon_Invoice_ID */
    public static final String COLUMNNAME_Subcon_Invoice_ID = "Subcon_Invoice_ID";

	/** Set Subcontractor Invoice	  */
	public void setSubcon_Invoice_ID (int Subcon_Invoice_ID);

	/** Get Subcontractor Invoice	  */
	public int getSubcon_Invoice_ID();

	public org.compiere.model.I_C_Invoice getSubcon_Invoice() throws RuntimeException;

    /** Column name Subcontractor_ID */
    public static final String COLUMNNAME_Subcontractor_ID = "Subcontractor_ID";

	/** Set Subcontractor	  */
	public void setSubcontractor_ID (int Subcontractor_ID);

	/** Get Subcontractor	  */
	public int getSubcontractor_ID();

	public org.compiere.model.I_C_BPartner getSubcontractor() throws RuntimeException;

    /** Column name TF_Employee_Salary_ID */
    public static final String COLUMNNAME_TF_Employee_Salary_ID = "TF_Employee_Salary_ID";

	/** Set Employee Salary	  */
	public void setTF_Employee_Salary_ID (int TF_Employee_Salary_ID);

	/** Get Employee Salary	  */
	public int getTF_Employee_Salary_ID();

	public I_TF_Employee_Salary getTF_Employee_Salary() throws RuntimeException;

    /** Column name TF_Jobwork_IssuedResource_ID */
    public static final String COLUMNNAME_TF_Jobwork_IssuedResource_ID = "TF_Jobwork_IssuedResource_ID";

	/** Set Issued Vehicles / Resources	  */
	public void setTF_Jobwork_IssuedResource_ID (int TF_Jobwork_IssuedResource_ID);

	/** Get Issued Vehicles / Resources	  */
	public int getTF_Jobwork_IssuedResource_ID();

	public I_TF_Jobwork_IssuedResource getTF_Jobwork_IssuedResource() throws RuntimeException;

    /** Column name TF_Labour_Wage_ID */
    public static final String COLUMNNAME_TF_Labour_Wage_ID = "TF_Labour_Wage_ID";

	/** Set Labour Wage Entry	  */
	public void setTF_Labour_Wage_ID (int TF_Labour_Wage_ID);

	/** Get Labour Wage Entry	  */
	public int getTF_Labour_Wage_ID();

	public I_TF_Labour_Wage getTF_Labour_Wage() throws RuntimeException;

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

    /** Column name TF_TripSheet_UU */
    public static final String COLUMNNAME_TF_TripSheet_UU = "TF_TripSheet_UU";

	/** Set TF_TripSheet_UU	  */
	public void setTF_TripSheet_UU (String TF_TripSheet_UU);

	/** Get TF_TripSheet_UU	  */
	public String getTF_TripSheet_UU();

    /** Column name TF_Vehicle_Rental_Contract_ID */
    public static final String COLUMNNAME_TF_Vehicle_Rental_Contract_ID = "TF_Vehicle_Rental_Contract_ID";

	/** Set Vehicle Rental Contract	  */
	public void setTF_Vehicle_Rental_Contract_ID (int TF_Vehicle_Rental_Contract_ID);

	/** Get Vehicle Rental Contract	  */
	public int getTF_Vehicle_Rental_Contract_ID();

	public I_TF_Vehicle_Rental_Contract getTF_Vehicle_Rental_Contract() throws RuntimeException;

    /** Column name TonnagePerLoad */
    public static final String COLUMNNAME_TonnagePerLoad = "TonnagePerLoad";

	/** Set MT / Load	  */
	public void setTonnagePerLoad (BigDecimal TonnagePerLoad);

	/** Get MT / Load	  */
	public BigDecimal getTonnagePerLoad();

    /** Column name Total_Wage */
    public static final String COLUMNNAME_Total_Wage = "Total_Wage";

	/** Set Total Earned Wage	  */
	public void setTotal_Wage (BigDecimal Total_Wage);

	/** Get Total Earned Wage	  */
	public BigDecimal getTotal_Wage();

    /** Column name TotalMT */
    public static final String COLUMNNAME_TotalMT = "TotalMT";

	/** Set Total MT (Manual)	  */
	public void setTotalMT (BigDecimal TotalMT);

	/** Get Total MT (Manual)	  */
	public BigDecimal getTotalMT();

    /** Column name TotalMTExtended */
    public static final String COLUMNNAME_TotalMTExtended = "TotalMTExtended";

	/** Set Total MT + Running Meter	  */
	public void setTotalMTExtended (BigDecimal TotalMTExtended);

	/** Get Total MT + Running Meter	  */
	public BigDecimal getTotalMTExtended();

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

    /** Column name Vehicle_ID */
    public static final String COLUMNNAME_Vehicle_ID = "Vehicle_ID";

	/** Set Vehicle	  */
	public void setVehicle_ID (int Vehicle_ID);

	/** Get Vehicle	  */
	public int getVehicle_ID();

	public org.compiere.model.I_M_Product getVehicle() throws RuntimeException;
}
