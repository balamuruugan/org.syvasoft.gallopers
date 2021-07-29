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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for TF_DispensePlan
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_DispensePlan extends PO implements I_TF_DispensePlan, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210405L;

    /** Standard Constructor */
    public X_TF_DispensePlan (Properties ctx, int TF_DispensePlan_ID, String trxName)
    {
      super (ctx, TF_DispensePlan_ID, trxName);
      /** if (TF_DispensePlan_ID == 0)
        {
			setProcessed (false);
			setScheduleDate (new Timestamp( System.currentTimeMillis() ));
			setTF_DispensePlan_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_DispensePlan (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_TF_DispensePlan[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Create Dispense Plan Line.
		@param CreateDPLine Create Dispense Plan Line	  */
	public void setCreateDPLine (String CreateDPLine)
	{
		set_ValueNoCheck (COLUMNNAME_CreateDPLine, CreateDPLine);
	}

	/** Get Create Dispense Plan Line.
		@return Create Dispense Plan Line	  */
	public String getCreateDPLine () 
	{
		return (String)get_Value(COLUMNNAME_CreateDPLine);
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

	/** Set Notify WalkIn Customer.
		@param NotifyWalkInCustomer Notify WalkIn Customer	  */
	public void setNotifyWalkInCustomer (boolean NotifyWalkInCustomer)
	{
		set_Value (COLUMNNAME_NotifyWalkInCustomer, Boolean.valueOf(NotifyWalkInCustomer));
	}

	/** Get Notify WalkIn Customer.
		@return Notify WalkIn Customer	  */
	public boolean isNotifyWalkInCustomer () 
	{
		Object oo = get_Value(COLUMNNAME_NotifyWalkInCustomer);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Schedule Date.
		@param ScheduleDate Schedule Date	  */
	public void setScheduleDate (Timestamp ScheduleDate)
	{
		set_Value (COLUMNNAME_ScheduleDate, ScheduleDate);
	}

	/** Get Schedule Date.
		@return Schedule Date	  */
	public Timestamp getScheduleDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ScheduleDate);
	}

	/** Set Dispense Plan.
		@param TF_DispensePlan_ID Dispense Plan	  */
	public void setTF_DispensePlan_ID (int TF_DispensePlan_ID)
	{
		if (TF_DispensePlan_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_DispensePlan_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_DispensePlan_ID, Integer.valueOf(TF_DispensePlan_ID));
	}

	/** Get Dispense Plan.
		@return Dispense Plan	  */
	public int getTF_DispensePlan_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_DispensePlan_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_DispensePlan_UU.
		@param TF_DispensePlan_UU TF_DispensePlan_UU	  */
	public void setTF_DispensePlan_UU (String TF_DispensePlan_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_DispensePlan_UU, TF_DispensePlan_UU);
	}

	/** Get TF_DispensePlan_UU.
		@return TF_DispensePlan_UU	  */
	public String getTF_DispensePlan_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_DispensePlan_UU);
	}
}