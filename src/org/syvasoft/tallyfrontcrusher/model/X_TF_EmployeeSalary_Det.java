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

/** Generated Model for TF_EmployeeSalary_Det
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_EmployeeSalary_Det extends PO implements I_TF_EmployeeSalary_Det, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210308L;

    /** Standard Constructor */
    public X_TF_EmployeeSalary_Det (Properties ctx, int TF_EmployeeSalary_Det_ID, String trxName)
    {
      super (ctx, TF_EmployeeSalary_Det_ID, trxName);
      /** if (TF_EmployeeSalary_Det_ID == 0)
        {
			setC_BPartner_ID (0);
			setNetSalary (Env.ZERO);
			setNoOfDays (Env.ZERO);
			setSalary (Env.ZERO);
			setSalaryDue (Env.ZERO);
			setSNo (0);
			setTF_EmployeeSalary_Det_ID (0);
			setTF_EmployeeSalary_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_EmployeeSalary_Det (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_EmployeeSalary_Det[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Absentees.
		@param Absentees Absentees	  */
	public void setAbsentees (BigDecimal Absentees)
	{
		set_Value (COLUMNNAME_Absentees, Absentees);
	}

	/** Get Absentees.
		@return Absentees	  */
	public BigDecimal getAbsentees () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Absentees);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Advance Paid.
		@param AdvancePaid Advance Paid	  */
	public void setAdvancePaid (BigDecimal AdvancePaid)
	{
		set_Value (COLUMNNAME_AdvancePaid, AdvancePaid);
	}

	/** Get Advance Paid.
		@return Advance Paid	  */
	public BigDecimal getAdvancePaid () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AdvancePaid);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
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

	/** Set Date of Joining.
		@param DateJoining Date of Joining	  */
	public void setDateJoining (Timestamp DateJoining)
	{
		throw new IllegalArgumentException ("DateJoining is virtual column");	}

	/** Get Date of Joining.
		@return Date of Joining	  */
	public Timestamp getDateJoining () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateJoining);
	}

	/** Set Deduct Advance.
		@param DeductAdvance Deduct Advance	  */
	public void setDeductAdvance (BigDecimal DeductAdvance)
	{
		set_Value (COLUMNNAME_DeductAdvance, DeductAdvance);
	}

	/** Get Deduct Advance.
		@return Deduct Advance	  */
	public BigDecimal getDeductAdvance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DeductAdvance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Designation.
		@param Designation Designation	  */
	public void setDesignation (String Designation)
	{
		set_Value (COLUMNNAME_Designation, Designation);
	}

	/** Get Designation.
		@return Designation	  */
	public String getDesignation () 
	{
		return (String)get_Value(COLUMNNAME_Designation);
	}

	/** Set Emp No.
		@param EmpNo Emp No	  */
	public void setEmpNo (String EmpNo)
	{
		set_Value (COLUMNNAME_EmpNo, EmpNo);
	}

	/** Get Emp No.
		@return Emp No	  */
	public String getEmpNo () 
	{
		return (String)get_Value(COLUMNNAME_EmpNo);
	}

	/** Set Mess Advance.
		@param MessAdvance Mess Advance	  */
	public void setMessAdvance (BigDecimal MessAdvance)
	{
		set_Value (COLUMNNAME_MessAdvance, MessAdvance);
	}

	/** Get Mess Advance.
		@return Mess Advance	  */
	public BigDecimal getMessAdvance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MessAdvance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Net Salary.
		@param NetSalary Net Salary	  */
	public void setNetSalary (BigDecimal NetSalary)
	{
		set_Value (COLUMNNAME_NetSalary, NetSalary);
	}

	/** Get Net Salary.
		@return Net Salary	  */
	public BigDecimal getNetSalary () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_NetSalary);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set No Of Days.
		@param NoOfDays No Of Days	  */
	public void setNoOfDays (BigDecimal NoOfDays)
	{
		set_Value (COLUMNNAME_NoOfDays, NoOfDays);
	}

	/** Get No Of Days.
		@return No Of Days	  */
	public BigDecimal getNoOfDays () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_NoOfDays);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Salary.
		@param Salary Salary	  */
	public void setSalary (BigDecimal Salary)
	{
		set_Value (COLUMNNAME_Salary, Salary);
	}

	/** Get Salary.
		@return Salary	  */
	public BigDecimal getSalary () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Salary);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Salary Due.
		@param SalaryDue Salary Due	  */
	public void setSalaryDue (BigDecimal SalaryDue)
	{
		set_Value (COLUMNNAME_SalaryDue, SalaryDue);
	}

	/** Get Salary Due.
		@return Salary Due	  */
	public BigDecimal getSalaryDue () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_SalaryDue);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Salary Withheld.
		@param SalaryWithheld Salary Withheld	  */
	public void setSalaryWithheld (BigDecimal SalaryWithheld)
	{
		set_Value (COLUMNNAME_SalaryWithheld, SalaryWithheld);
	}

	/** Get Salary Withheld.
		@return Salary Withheld	  */
	public BigDecimal getSalaryWithheld () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_SalaryWithheld);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set S No.
		@param SNo S No	  */
	public void setSNo (int SNo)
	{
		set_Value (COLUMNNAME_SNo, Integer.valueOf(SNo));
	}

	/** Get S No.
		@return S No	  */
	public int getSNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Employee Salary Detail.
		@param TF_EmployeeSalary_Det_ID Employee Salary Detail	  */
	public void setTF_EmployeeSalary_Det_ID (int TF_EmployeeSalary_Det_ID)
	{
		if (TF_EmployeeSalary_Det_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_EmployeeSalary_Det_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_EmployeeSalary_Det_ID, Integer.valueOf(TF_EmployeeSalary_Det_ID));
	}

	/** Get Employee Salary Detail.
		@return Employee Salary Detail	  */
	public int getTF_EmployeeSalary_Det_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_EmployeeSalary_Det_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_EmployeeSalary_Det_UU.
		@param TF_EmployeeSalary_Det_UU TF_EmployeeSalary_Det_UU	  */
	public void setTF_EmployeeSalary_Det_UU (String TF_EmployeeSalary_Det_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_EmployeeSalary_Det_UU, TF_EmployeeSalary_Det_UU);
	}

	/** Get TF_EmployeeSalary_Det_UU.
		@return TF_EmployeeSalary_Det_UU	  */
	public String getTF_EmployeeSalary_Det_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_EmployeeSalary_Det_UU);
	}

	public I_TF_EmployeeSalary getTF_EmployeeSalary() throws RuntimeException
    {
		return (I_TF_EmployeeSalary)MTable.get(getCtx(), I_TF_EmployeeSalary.Table_Name)
			.getPO(getTF_EmployeeSalary_ID(), get_TrxName());	}

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

	/** Set Unpaid Salary.
		@param UnpaidSalary Unpaid Salary	  */
	public void setUnpaidSalary (BigDecimal UnpaidSalary)
	{
		set_Value (COLUMNNAME_UnpaidSalary, UnpaidSalary);
	}

	/** Get Unpaid Salary.
		@return Unpaid Salary	  */
	public BigDecimal getUnpaidSalary () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_UnpaidSalary);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}