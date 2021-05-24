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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for TF_TripSheet
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_TripSheet extends PO implements I_TF_TripSheet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210524L;

    /** Standard Constructor */
    public X_TF_TripSheet (Properties ctx, int TF_TripSheet_ID, String trxName)
    {
      super (ctx, TF_TripSheet_ID, trxName);
      /** if (TF_TripSheet_ID == 0)
        {
			setC_UOM_ID (0);
			setDocumentNo (null);
			setProcessed (false);
			setTF_TripSheet_ID (0);
			setVehicle_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_TripSheet (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_TF_TripSheet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getC_ElementValue() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getC_ElementValue_ID(), get_TrxName());	}

	/** Set Account Element.
		@param C_ElementValue_ID 
		Account Element
	  */
	public void setC_ElementValue_ID (int C_ElementValue_ID)
	{
		if (C_ElementValue_ID < 1) 
			set_Value (COLUMNNAME_C_ElementValue_ID, null);
		else 
			set_Value (COLUMNNAME_C_ElementValue_ID, Integer.valueOf(C_ElementValue_ID));
	}

	/** Get Account Element.
		@return Account Element
	  */
	public int getC_ElementValue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ElementValue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException
    {
		return (org.compiere.model.I_C_Project)MTable.get(getCtx(), org.compiere.model.I_C_Project.Table_Name)
			.getPO(getC_Project_ID(), get_TrxName());	}

	/** Set Subcontract / Project.
		@param C_Project_ID 
		Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID)
	{
		if (C_Project_ID < 1) 
			set_Value (COLUMNNAME_C_Project_ID, null);
		else 
			set_Value (COLUMNNAME_C_Project_ID, Integer.valueOf(C_Project_ID));
	}

	/** Get Subcontract / Project.
		@return Financial Project
	  */
	public int getC_Project_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Project_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getC_UOM_ID(), get_TrxName());	}

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Closing Fuel.
		@param Closing_Fuel Closing Fuel	  */
	public void setClosing_Fuel (BigDecimal Closing_Fuel)
	{
		set_Value (COLUMNNAME_Closing_Fuel, Closing_Fuel);
	}

	/** Get Closing Fuel.
		@return Closing Fuel	  */
	public BigDecimal getClosing_Fuel () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Closing_Fuel);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Closing Meter.
		@param Closing_Meter Closing Meter	  */
	public void setClosing_Meter (BigDecimal Closing_Meter)
	{
		set_Value (COLUMNNAME_Closing_Meter, Closing_Meter);
	}

	/** Get Closing Meter.
		@return Closing Meter	  */
	public BigDecimal getClosing_Meter () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Closing_Meter);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set End Date.
		@param DateEnd End Date	  */
	public void setDateEnd (Timestamp DateEnd)
	{
		set_Value (COLUMNNAME_DateEnd, DateEnd);
	}

	/** Get End Date.
		@return End Date	  */
	public Timestamp getDateEnd () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateEnd);
	}

	/** Set Report Date.
		@param DateReport 
		Expense/Time Report Date
	  */
	public void setDateReport (Timestamp DateReport)
	{
		set_Value (COLUMNNAME_DateReport, DateReport);
	}

	/** Get Report Date.
		@return Expense/Time Report Date
	  */
	public Timestamp getDateReport () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateReport);
	}

	/** Set Date Start.
		@param DateStart 
		Date Start for this Order
	  */
	public void setDateStart (Timestamp DateStart)
	{
		set_Value (COLUMNNAME_DateStart, DateStart);
	}

	/** Get Date Start.
		@return Date Start for this Order
	  */
	public Timestamp getDateStart () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateStart);
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

	/** DocStatus AD_Reference_ID=131 */
	public static final int DOCSTATUS_AD_Reference_ID=131;
	/** Drafted = DR */
	public static final String DOCSTATUS_Drafted = "DR";
	/** Completed = CO */
	public static final String DOCSTATUS_Completed = "CO";
	/** Approved = AP */
	public static final String DOCSTATUS_Approved = "AP";
	/** Not Approved = NA */
	public static final String DOCSTATUS_NotApproved = "NA";
	/** Voided = VO */
	public static final String DOCSTATUS_Voided = "VO";
	/** Invalid = IN */
	public static final String DOCSTATUS_Invalid = "IN";
	/** Reversed = RE */
	public static final String DOCSTATUS_Reversed = "RE";
	/** Closed = CL */
	public static final String DOCSTATUS_Closed = "CL";
	/** Unknown = ?? */
	public static final String DOCSTATUS_Unknown = "??";
	/** In Progress = IP */
	public static final String DOCSTATUS_InProgress = "IP";
	/** Waiting Payment = WP */
	public static final String DOCSTATUS_WaitingPayment = "WP";
	/** Waiting Confirmation = WC */
	public static final String DOCSTATUS_WaitingConfirmation = "WC";
	/** Suspended = SU */
	public static final String DOCSTATUS_Suspended = "SU";
	/** Activated = AC */
	public static final String DOCSTATUS_Activated = "AC";
	/** Canceled = CA */
	public static final String DOCSTATUS_Canceled = "CA";
	/** Overdue = OD */
	public static final String DOCSTATUS_Overdue = "OD";
	/** Due = DU */
	public static final String DOCSTATUS_Due = "DU";
	/** Upcoming = UP */
	public static final String DOCSTATUS_Upcoming = "UP";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{

		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_ValueNoCheck (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Earned Wage.
		@param Earned_Wage Earned Wage	  */
	public void setEarned_Wage (BigDecimal Earned_Wage)
	{
		set_Value (COLUMNNAME_Earned_Wage, Earned_Wage);
	}

	/** Get Earned Wage.
		@return Earned Wage	  */
	public BigDecimal getEarned_Wage () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Earned_Wage);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Fuel Expensed.
		@param Expensed_Fuel Fuel Expensed	  */
	public void setExpensed_Fuel (BigDecimal Expensed_Fuel)
	{
		set_Value (COLUMNNAME_Expensed_Fuel, Expensed_Fuel);
	}

	/** Get Fuel Expensed.
		@return Fuel Expensed	  */
	public BigDecimal getExpensed_Fuel () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Expensed_Fuel);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Incentive / OT.
		@param Incentive Incentive / OT	  */
	public void setIncentive (BigDecimal Incentive)
	{
		set_Value (COLUMNNAME_Incentive, Incentive);
	}

	/** Get Incentive / OT.
		@return Incentive / OT	  */
	public BigDecimal getIncentive () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Incentive);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_M_Product getJobWork_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getJobWork_Product_ID(), get_TrxName());	}

	/** Set Job Work.
		@param JobWork_Product_ID Job Work	  */
	public void setJobWork_Product_ID (int JobWork_Product_ID)
	{
		if (JobWork_Product_ID < 1) 
			set_Value (COLUMNNAME_JobWork_Product_ID, null);
		else 
			set_Value (COLUMNNAME_JobWork_Product_ID, Integer.valueOf(JobWork_Product_ID));
	}

	/** Get Job Work.
		@return Job Work	  */
	public int getJobWork_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_JobWork_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Opening Fuel.
		@param Opening_Fuel Opening Fuel	  */
	public void setOpening_Fuel (BigDecimal Opening_Fuel)
	{
		set_Value (COLUMNNAME_Opening_Fuel, Opening_Fuel);
	}

	/** Get Opening Fuel.
		@return Opening Fuel	  */
	public BigDecimal getOpening_Fuel () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Opening_Fuel);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Opening Meter.
		@param Opening_Meter Opening Meter	  */
	public void setOpening_Meter (BigDecimal Opening_Meter)
	{
		set_Value (COLUMNNAME_Opening_Meter, Opening_Meter);
	}

	/** Get Opening Meter.
		@return Opening Meter	  */
	public BigDecimal getOpening_Meter () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Opening_Meter);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_PM_Machinery getPM_Machinery() throws RuntimeException
    {
		return (I_PM_Machinery)MTable.get(getCtx(), I_PM_Machinery.Table_Name)
			.getPO(getPM_Machinery_ID(), get_TrxName());	}

	/** Set Machinery.
		@param PM_Machinery_ID Machinery	  */
	public void setPM_Machinery_ID (int PM_Machinery_ID)
	{
		if (PM_Machinery_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PM_Machinery_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PM_Machinery_ID, Integer.valueOf(PM_Machinery_ID));
	}

	/** Get Machinery.
		@return Machinery	  */
	public int getPM_Machinery_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PM_Machinery_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_PM_Meter_Log getPM_Meter_Log() throws RuntimeException
    {
		return (I_PM_Meter_Log)MTable.get(getCtx(), I_PM_Meter_Log.Table_Name)
			.getPO(getPM_Meter_Log_ID(), get_TrxName());	}

	/** Set Machinery Meter Log.
		@param PM_Meter_Log_ID Machinery Meter Log	  */
	public void setPM_Meter_Log_ID (int PM_Meter_Log_ID)
	{
		if (PM_Meter_Log_ID < 1) 
			set_Value (COLUMNNAME_PM_Meter_Log_ID, null);
		else 
			set_Value (COLUMNNAME_PM_Meter_Log_ID, Integer.valueOf(PM_Meter_Log_ID));
	}

	/** Get Machinery Meter Log.
		@return Machinery Meter Log	  */
	public int getPM_Meter_Log_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PM_Meter_Log_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Post Labour Wage.
		@param PostLabourWage Post Labour Wage	  */
	public void setPostLabourWage (String PostLabourWage)
	{
		set_Value (COLUMNNAME_PostLabourWage, PostLabourWage);
	}

	/** Get Post Labour Wage.
		@return Post Labour Wage	  */
	public String getPostLabourWage () 
	{
		return (String)get_Value(COLUMNNAME_PostLabourWage);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Rate.
		@param Rate 
		Rate or Tax or Exchange
	  */
	public void setRate (BigDecimal Rate)
	{
		set_Value (COLUMNNAME_Rate, Rate);
	}

	/** Get Rate.
		@return Rate or Tax or Exchange
	  */
	public BigDecimal getRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Received Fuel.
		@param Received_Fuel Received Fuel	  */
	public void setReceived_Fuel (BigDecimal Received_Fuel)
	{
		set_Value (COLUMNNAME_Received_Fuel, Received_Fuel);
	}

	/** Get Received Fuel.
		@return Received Fuel	  */
	public BigDecimal getReceived_Fuel () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Received_Fuel);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Rent (Amount).
		@param Rent_Amt Rent (Amount)	  */
	public void setRent_Amt (BigDecimal Rent_Amt)
	{
		set_Value (COLUMNNAME_Rent_Amt, Rent_Amt);
	}

	/** Get Rent (Amount).
		@return Rent (Amount)	  */
	public BigDecimal getRent_Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rent_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Running Meter.
		@param Running_Meter Running Meter	  */
	public void setRunning_Meter (BigDecimal Running_Meter)
	{
		set_Value (COLUMNNAME_Running_Meter, Running_Meter);
	}

	/** Get Running Meter.
		@return Running Meter	  */
	public BigDecimal getRunning_Meter () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Running_Meter);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Day = D */
	public static final String SHIFT_Day = "D";
	/** Night = N */
	public static final String SHIFT_Night = "N";
	/** All Day = A */
	public static final String SHIFT_AllDay = "A";
	/** Set Shift.
		@param Shift Shift	  */
	public void setShift (String Shift)
	{

		set_Value (COLUMNNAME_Shift, Shift);
	}

	/** Get Shift.
		@return Shift	  */
	public String getShift () 
	{
		return (String)get_Value(COLUMNNAME_Shift);
	}

	public org.compiere.model.I_C_Invoice getSubcon_Invoice() throws RuntimeException
    {
		return (org.compiere.model.I_C_Invoice)MTable.get(getCtx(), org.compiere.model.I_C_Invoice.Table_Name)
			.getPO(getSubcon_Invoice_ID(), get_TrxName());	}

	/** Set Subcontractor Invoice.
		@param Subcon_Invoice_ID Subcontractor Invoice	  */
	public void setSubcon_Invoice_ID (int Subcon_Invoice_ID)
	{
		if (Subcon_Invoice_ID < 1) 
			set_Value (COLUMNNAME_Subcon_Invoice_ID, null);
		else 
			set_Value (COLUMNNAME_Subcon_Invoice_ID, Integer.valueOf(Subcon_Invoice_ID));
	}

	/** Get Subcontractor Invoice.
		@return Subcontractor Invoice	  */
	public int getSubcon_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Subcon_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_TF_Jobwork_IssuedResource getTF_Jobwork_IssuedResource() throws RuntimeException
    {
		return (I_TF_Jobwork_IssuedResource)MTable.get(getCtx(), I_TF_Jobwork_IssuedResource.Table_Name)
			.getPO(getTF_Jobwork_IssuedResource_ID(), get_TrxName());	}

	/** Set Issued Vehicles / Resources.
		@param TF_Jobwork_IssuedResource_ID Issued Vehicles / Resources	  */
	public void setTF_Jobwork_IssuedResource_ID (int TF_Jobwork_IssuedResource_ID)
	{
		if (TF_Jobwork_IssuedResource_ID < 1) 
			set_Value (COLUMNNAME_TF_Jobwork_IssuedResource_ID, null);
		else 
			set_Value (COLUMNNAME_TF_Jobwork_IssuedResource_ID, Integer.valueOf(TF_Jobwork_IssuedResource_ID));
	}

	/** Get Issued Vehicles / Resources.
		@return Issued Vehicles / Resources	  */
	public int getTF_Jobwork_IssuedResource_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_Jobwork_IssuedResource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_TF_Labour_Wage getTF_Labour_Wage() throws RuntimeException
    {
		return (I_TF_Labour_Wage)MTable.get(getCtx(), I_TF_Labour_Wage.Table_Name)
			.getPO(getTF_Labour_Wage_ID(), get_TrxName());	}

	/** Set Labour Wage Entry.
		@param TF_Labour_Wage_ID Labour Wage Entry	  */
	public void setTF_Labour_Wage_ID (int TF_Labour_Wage_ID)
	{
		if (TF_Labour_Wage_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_Labour_Wage_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_Labour_Wage_ID, Integer.valueOf(TF_Labour_Wage_ID));
	}

	/** Get Labour Wage Entry.
		@return Labour Wage Entry	  */
	public int getTF_Labour_Wage_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_Labour_Wage_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_TF_Quarry getTF_Quarry() throws RuntimeException
    {
		return (I_TF_Quarry)MTable.get(getCtx(), I_TF_Quarry.Table_Name)
			.getPO(getTF_Quarry_ID(), get_TrxName());	}

	/** Set Quarry.
		@param TF_Quarry_ID Quarry	  */
	public void setTF_Quarry_ID (int TF_Quarry_ID)
	{
		if (TF_Quarry_ID < 1) 
			set_Value (COLUMNNAME_TF_Quarry_ID, null);
		else 
			set_Value (COLUMNNAME_TF_Quarry_ID, Integer.valueOf(TF_Quarry_ID));
	}

	/** Get Quarry.
		@return Quarry	  */
	public int getTF_Quarry_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_Quarry_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Trip Sheet.
		@param TF_TripSheet_ID Trip Sheet	  */
	public void setTF_TripSheet_ID (int TF_TripSheet_ID)
	{
		if (TF_TripSheet_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_TripSheet_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_TripSheet_ID, Integer.valueOf(TF_TripSheet_ID));
	}

	/** Get Trip Sheet.
		@return Trip Sheet	  */
	public int getTF_TripSheet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_TripSheet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_TripSheet_UU.
		@param TF_TripSheet_UU TF_TripSheet_UU	  */
	public void setTF_TripSheet_UU (String TF_TripSheet_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_TripSheet_UU, TF_TripSheet_UU);
	}

	/** Get TF_TripSheet_UU.
		@return TF_TripSheet_UU	  */
	public String getTF_TripSheet_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_TripSheet_UU);
	}

	public I_TF_Vehicle_Rental_Contract getTF_Vehicle_Rental_Contract() throws RuntimeException
    {
		return (I_TF_Vehicle_Rental_Contract)MTable.get(getCtx(), I_TF_Vehicle_Rental_Contract.Table_Name)
			.getPO(getTF_Vehicle_Rental_Contract_ID(), get_TrxName());	}

	/** Set Vehicle Rental Contract.
		@param TF_Vehicle_Rental_Contract_ID Vehicle Rental Contract	  */
	public void setTF_Vehicle_Rental_Contract_ID (int TF_Vehicle_Rental_Contract_ID)
	{
		if (TF_Vehicle_Rental_Contract_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_Vehicle_Rental_Contract_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_Vehicle_Rental_Contract_ID, Integer.valueOf(TF_Vehicle_Rental_Contract_ID));
	}

	/** Get Vehicle Rental Contract.
		@return Vehicle Rental Contract	  */
	public int getTF_Vehicle_Rental_Contract_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_Vehicle_Rental_Contract_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Total Earned Wage.
		@param Total_Wage Total Earned Wage	  */
	public void setTotal_Wage (BigDecimal Total_Wage)
	{
		set_Value (COLUMNNAME_Total_Wage, Total_Wage);
	}

	/** Get Total Earned Wage.
		@return Total Earned Wage	  */
	public BigDecimal getTotal_Wage () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Total_Wage);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_M_Product getVehicle() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getVehicle_ID(), get_TrxName());	}

	/** Set Vehicle.
		@param Vehicle_ID Vehicle	  */
	public void setVehicle_ID (int Vehicle_ID)
	{
		if (Vehicle_ID < 1) 
			set_Value (COLUMNNAME_Vehicle_ID, null);
		else 
			set_Value (COLUMNNAME_Vehicle_ID, Integer.valueOf(Vehicle_ID));
	}

	/** Get Vehicle.
		@return Vehicle	  */
	public int getVehicle_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Vehicle_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}