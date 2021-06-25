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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for TF_TripSheet_Salary
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_TripSheet_Salary extends PO implements I_TF_TripSheet_Salary, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210625L;

    /** Standard Constructor */
    public X_TF_TripSheet_Salary (Properties ctx, int TF_TripSheet_Salary_ID, String trxName)
    {
      super (ctx, TF_TripSheet_Salary_ID, trxName);
      /** if (TF_TripSheet_Salary_ID == 0)
        {
			setEarned_Wage (Env.ZERO);
// 0
			setIncentive (Env.ZERO);
// 0
			setTF_TripSheet_Salary_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_TripSheet_Salary (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_TripSheet_Salary[")
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

	/** Set Day Incentive.
		@param DayIncentive Day Incentive	  */
	public void setDayIncentive (BigDecimal DayIncentive)
	{
		set_Value (COLUMNNAME_DayIncentive, DayIncentive);
	}

	/** Get Day Incentive.
		@return Day Incentive	  */
	public BigDecimal getDayIncentive () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DayIncentive);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Drilling Qty.
		@param DrillingQty Drilling Qty	  */
	public void setDrillingQty (BigDecimal DrillingQty)
	{
		set_Value (COLUMNNAME_DrillingQty, DrillingQty);
	}

	/** Get Drilling Qty.
		@return Drilling Qty	  */
	public BigDecimal getDrillingQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DrillingQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Eligible Unit.
		@param EligibleUnit Eligible Unit	  */
	public void setEligibleUnit (BigDecimal EligibleUnit)
	{
		set_Value (COLUMNNAME_EligibleUnit, EligibleUnit);
	}

	/** Get Eligible Unit.
		@return Eligible Unit	  */
	public BigDecimal getEligibleUnit () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_EligibleUnit);
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

	/** MT = MT */
	public static final String INCENTIVETYPE_MT = "MT";
	/** Hour = HR */
	public static final String INCENTIVETYPE_Hour = "HR";
	/** Day = DA */
	public static final String INCENTIVETYPE_Day = "DA";
	/** Month = MO */
	public static final String INCENTIVETYPE_Month = "MO";
	/** Meter = ME */
	public static final String INCENTIVETYPE_Meter = "ME";
	/** Set Incentive Type.
		@param IncentiveType Incentive Type	  */
	public void setIncentiveType (String IncentiveType)
	{

		set_Value (COLUMNNAME_IncentiveType, IncentiveType);
	}

	/** Get Incentive Type.
		@return Incentive Type	  */
	public String getIncentiveType () 
	{
		return (String)get_Value(COLUMNNAME_IncentiveType);
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

	/** Set Addtional Labour Salary.
		@param TF_TripSheet_Salary_ID Addtional Labour Salary	  */
	public void setTF_TripSheet_Salary_ID (int TF_TripSheet_Salary_ID)
	{
		if (TF_TripSheet_Salary_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_TripSheet_Salary_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_TripSheet_Salary_ID, Integer.valueOf(TF_TripSheet_Salary_ID));
	}

	/** Get Addtional Labour Salary.
		@return Addtional Labour Salary	  */
	public int getTF_TripSheet_Salary_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_TripSheet_Salary_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_TripSheet_Salary_UU.
		@param TF_TripSheet_Salary_UU TF_TripSheet_Salary_UU	  */
	public void setTF_TripSheet_Salary_UU (String TF_TripSheet_Salary_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_TripSheet_Salary_UU, TF_TripSheet_Salary_UU);
	}

	/** Get TF_TripSheet_Salary_UU.
		@return TF_TripSheet_Salary_UU	  */
	public String getTF_TripSheet_Salary_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_TripSheet_Salary_UU);
	}

	/** Set Unit Incentive.
		@param UnitIncentive Unit Incentive	  */
	public void setUnitIncentive (BigDecimal UnitIncentive)
	{
		set_Value (COLUMNNAME_UnitIncentive, UnitIncentive);
	}

	/** Get Unit Incentive.
		@return Unit Incentive	  */
	public BigDecimal getUnitIncentive () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_UnitIncentive);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}