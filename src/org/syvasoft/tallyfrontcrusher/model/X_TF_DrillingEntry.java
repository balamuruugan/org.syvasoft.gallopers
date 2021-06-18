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

/** Generated Model for TF_DrillingEntry
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_DrillingEntry extends PO implements I_TF_DrillingEntry, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210618L;

    /** Standard Constructor */
    public X_TF_DrillingEntry (Properties ctx, int TF_DrillingEntry_ID, String trxName)
    {
      super (ctx, TF_DrillingEntry_ID, trxName);
      /** if (TF_DrillingEntry_ID == 0)
        {
			setC_BPartner_ID (0);
			setC_UOM_ID (0);
			setDateAcct (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setDrillingCost (Env.ZERO);
			setFeet (Env.ZERO);
			setFeetRate (Env.ZERO);
			setHoles (Env.ZERO);
			setM_Product_ID (0);
			setProcessed (false);
// N
			setTF_DrillingBlasting_ID (0);
			setTF_DrillingEntry_ID (0);
			setTF_Quarry_ID (0);
			setTotalFeet (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_TF_DrillingEntry (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_DrillingEntry[")
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

	public org.compiere.model.I_C_Invoice getC_Invoice() throws RuntimeException
    {
		return (org.compiere.model.I_C_Invoice)MTable.get(getCtx(), org.compiere.model.I_C_Invoice.Table_Name)
			.getPO(getC_Invoice_ID(), get_TrxName());	}

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID < 1) 
			set_Value (COLUMNNAME_C_Invoice_ID, null);
		else 
			set_Value (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
	}

	/** Get Invoice.
		@return Invoice Identifier
	  */
	public int getC_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Invoice_ID);
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

	/** Set Account Date.
		@param DateAcct 
		Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct)
	{
		set_Value (COLUMNNAME_DateAcct, DateAcct);
	}

	/** Get Account Date.
		@return Accounting Date
	  */
	public Timestamp getDateAcct () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateAcct);
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

	/** Set Drilling Cost.
		@param DrillingCost Drilling Cost	  */
	public void setDrillingCost (BigDecimal DrillingCost)
	{
		set_Value (COLUMNNAME_DrillingCost, DrillingCost);
	}

	/** Get Drilling Cost.
		@return Drilling Cost	  */
	public BigDecimal getDrillingCost () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DrillingCost);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Feet.
		@param Feet Feet	  */
	public void setFeet (BigDecimal Feet)
	{
		set_Value (COLUMNNAME_Feet, Feet);
	}

	/** Get Feet.
		@return Feet	  */
	public BigDecimal getFeet () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Feet);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Rate / Feet.
		@param FeetRate Rate / Feet	  */
	public void setFeetRate (BigDecimal FeetRate)
	{
		set_Value (COLUMNNAME_FeetRate, FeetRate);
	}

	/** Get Rate / Feet.
		@return Rate / Feet	  */
	public BigDecimal getFeetRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FeetRate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Holes.
		@param Holes Holes	  */
	public void setHoles (BigDecimal Holes)
	{
		set_Value (COLUMNNAME_Holes, Holes);
	}

	/** Get Holes.
		@return Holes	  */
	public BigDecimal getHoles () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Holes);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public I_TF_DrillingBlasting getTF_DrillingBlasting() throws RuntimeException
    {
		return (I_TF_DrillingBlasting)MTable.get(getCtx(), I_TF_DrillingBlasting.Table_Name)
			.getPO(getTF_DrillingBlasting_ID(), get_TrxName());	}

	/** Set TF_DrillingBlasting.
		@param TF_DrillingBlasting_ID TF_DrillingBlasting	  */
	public void setTF_DrillingBlasting_ID (int TF_DrillingBlasting_ID)
	{
		if (TF_DrillingBlasting_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_DrillingBlasting_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_DrillingBlasting_ID, Integer.valueOf(TF_DrillingBlasting_ID));
	}

	/** Get TF_DrillingBlasting.
		@return TF_DrillingBlasting	  */
	public int getTF_DrillingBlasting_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_DrillingBlasting_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Drilling Entry.
		@param TF_DrillingEntry_ID Drilling Entry	  */
	public void setTF_DrillingEntry_ID (int TF_DrillingEntry_ID)
	{
		if (TF_DrillingEntry_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_DrillingEntry_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_DrillingEntry_ID, Integer.valueOf(TF_DrillingEntry_ID));
	}

	/** Get Drilling Entry.
		@return Drilling Entry	  */
	public int getTF_DrillingEntry_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_DrillingEntry_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_DrillingEntry_UU.
		@param TF_DrillingEntry_UU TF_DrillingEntry_UU	  */
	public void setTF_DrillingEntry_UU (String TF_DrillingEntry_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_DrillingEntry_UU, TF_DrillingEntry_UU);
	}

	/** Get TF_DrillingEntry_UU.
		@return TF_DrillingEntry_UU	  */
	public String getTF_DrillingEntry_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_DrillingEntry_UU);
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

	public I_TF_TripSheet getTF_TripSheet() throws RuntimeException
    {
		return (I_TF_TripSheet)MTable.get(getCtx(), I_TF_TripSheet.Table_Name)
			.getPO(getTF_TripSheet_ID(), get_TrxName());	}

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

	/** Set Total Feet.
		@param TotalFeet Total Feet	  */
	public void setTotalFeet (BigDecimal TotalFeet)
	{
		set_Value (COLUMNNAME_TotalFeet, TotalFeet);
	}

	/** Get Total Feet.
		@return Total Feet	  */
	public BigDecimal getTotalFeet () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalFeet);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}