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

/** Generated Model for TF_EmployeeSalary
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_EmployeeSalary extends PO implements I_TF_EmployeeSalary, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210226L;

    /** Standard Constructor */
    public X_TF_EmployeeSalary (Properties ctx, int TF_EmployeeSalary_ID, String trxName)
    {
      super (ctx, TF_EmployeeSalary_ID, trxName);
      /** if (TF_EmployeeSalary_ID == 0)
        {
			setDateAcct (new Timestamp( System.currentTimeMillis() ));
			setEmployeeType (null);
			setProcessed (false);
			setTF_EmployeeSalary_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_EmployeeSalary (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_EmployeeSalary[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_Period getC_Period() throws RuntimeException
    {
		return (org.compiere.model.I_C_Period)MTable.get(getCtx(), org.compiere.model.I_C_Period.Table_Name)
			.getPO(getC_Period_ID(), get_TrxName());	}

	/** Set Period.
		@param C_Period_ID 
		Period of the Calendar
	  */
	public void setC_Period_ID (int C_Period_ID)
	{
		if (C_Period_ID < 1) 
			set_Value (COLUMNNAME_C_Period_ID, null);
		else 
			set_Value (COLUMNNAME_C_Period_ID, Integer.valueOf(C_Period_ID));
	}

	/** Get Period.
		@return Period of the Calendar
	  */
	public int getC_Period_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Period_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Create Lines.
		@param CreateLines Create Lines	  */
	public void setCreateLines (String CreateLines)
	{
		set_Value (COLUMNNAME_CreateLines, CreateLines);
	}

	/** Get Create Lines.
		@return Create Lines	  */
	public String getCreateLines () 
	{
		return (String)get_Value(COLUMNNAME_CreateLines);
	}

	/** Set Account Date.
		@param DateAcct 
		Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct)
	{
		set_ValueNoCheck (COLUMNNAME_DateAcct, DateAcct);
	}

	/** Get Account Date.
		@return Accounting Date
	  */
	public Timestamp getDateAcct () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateAcct);
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

	/** PCS = PCS */
	public static final String EMPLOYEETYPE_PCS = "PCS";
	/** GEE = GEE */
	public static final String EMPLOYEETYPE_GEE = "GEE";
	/** Set Employee Type.
		@param EmployeeType Employee Type	  */
	public void setEmployeeType (String EmployeeType)
	{

		set_Value (COLUMNNAME_EmployeeType, EmployeeType);
	}

	/** Get Employee Type.
		@return Employee Type	  */
	public String getEmployeeType () 
	{
		return (String)get_Value(COLUMNNAME_EmployeeType);
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

	/** Set Recreate.
		@param Recreate Recreate	  */
	public void setRecreate (boolean Recreate)
	{
		set_Value (COLUMNNAME_Recreate, Boolean.valueOf(Recreate));
	}

	/** Get Recreate.
		@return Recreate	  */
	public boolean isRecreate () 
	{
		Object oo = get_Value(COLUMNNAME_Recreate);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Standard Days.
		@param Std_Days Standard Days	  */
	public void setStd_Days (BigDecimal Std_Days)
	{
		set_Value (COLUMNNAME_Std_Days, Std_Days);
	}

	/** Get Standard Days.
		@return Standard Days	  */
	public BigDecimal getStd_Days () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Std_Days);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Employee Salary.
		@param TF_EmployeeSalary_ID Employee Salary	  */
	public void setTF_EmployeeSalary_ID (int TF_EmployeeSalary_ID)
	{
		if (TF_EmployeeSalary_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_EmployeeSalary_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_EmployeeSalary_ID, Integer.valueOf(TF_EmployeeSalary_ID));
	}

	/** Get Employee Salary.
		@return Employee Salary	  */
	public int getTF_EmployeeSalary_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_EmployeeSalary_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_EmployeeSalary_UU.
		@param TF_EmployeeSalary_UU TF_EmployeeSalary_UU	  */
	public void setTF_EmployeeSalary_UU (String TF_EmployeeSalary_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_EmployeeSalary_UU, TF_EmployeeSalary_UU);
	}

	/** Get TF_EmployeeSalary_UU.
		@return TF_EmployeeSalary_UU	  */
	public String getTF_EmployeeSalary_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_EmployeeSalary_UU);
	}
}