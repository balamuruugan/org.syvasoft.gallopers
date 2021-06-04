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

/** Generated Model for TF_IncentiveConfig
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_IncentiveConfig extends PO implements I_TF_IncentiveConfig, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210602L;

    /** Standard Constructor */
    public X_TF_IncentiveConfig (Properties ctx, int TF_IncentiveConfig_ID, String trxName)
    {
      super (ctx, TF_IncentiveConfig_ID, trxName);
      /** if (TF_IncentiveConfig_ID == 0)
        {
			setEligibleUnit (Env.ZERO);
			setIncentiveAsDayWage (false);
// N
			setIncentiveType (null);
			setIncentiveUOM (null);
			setTF_IncentiveConfig_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_IncentiveConfig (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_IncentiveConfig[")
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

	/** Set Incentive as Day Wage.
		@param IncentiveAsDayWage Incentive as Day Wage	  */
	public void setIncentiveAsDayWage (boolean IncentiveAsDayWage)
	{
		set_Value (COLUMNNAME_IncentiveAsDayWage, Boolean.valueOf(IncentiveAsDayWage));
	}

	/** Get Incentive as Day Wage.
		@return Incentive as Day Wage	  */
	public boolean isIncentiveAsDayWage () 
	{
		Object oo = get_Value(COLUMNNAME_IncentiveAsDayWage);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** MT = MT */
	public static final String INCENTIVETYPE_MT = "MT";
	/** Hour = HR */
	public static final String INCENTIVETYPE_Hour = "HR";
	/** Day = DA */
	public static final String INCENTIVETYPE_Day = "DA";
	/** Month = MO */
	public static final String INCENTIVETYPE_Month = "MO";
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

	/** MT = MT */
	public static final String INCENTIVEUOM_MT = "MT";
	/** Hour = HR */
	public static final String INCENTIVEUOM_Hour = "HR";
	/** Day = DA */
	public static final String INCENTIVEUOM_Day = "DA";
	/** Month = MO */
	public static final String INCENTIVEUOM_Month = "MO";
	/** Set Incentive UOM.
		@param IncentiveUOM Incentive UOM	  */
	public void setIncentiveUOM (String IncentiveUOM)
	{

		set_Value (COLUMNNAME_IncentiveUOM, IncentiveUOM);
	}

	/** Get Incentive UOM.
		@return Incentive UOM	  */
	public String getIncentiveUOM () 
	{
		return (String)get_Value(COLUMNNAME_IncentiveUOM);
	}

	/** Set Remaining Unit Incentive.
		@param RemainingUnitIncentive Remaining Unit Incentive	  */
	public void setRemainingUnitIncentive (BigDecimal RemainingUnitIncentive)
	{
		set_Value (COLUMNNAME_RemainingUnitIncentive, RemainingUnitIncentive);
	}

	/** Get Remaining Unit Incentive.
		@return Remaining Unit Incentive	  */
	public BigDecimal getRemainingUnitIncentive () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_RemainingUnitIncentive);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Employee Incentive Configuration.
		@param TF_IncentiveConfig_ID Employee Incentive Configuration	  */
	public void setTF_IncentiveConfig_ID (int TF_IncentiveConfig_ID)
	{
		if (TF_IncentiveConfig_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_IncentiveConfig_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_IncentiveConfig_ID, Integer.valueOf(TF_IncentiveConfig_ID));
	}

	/** Get Employee Incentive Configuration.
		@return Employee Incentive Configuration	  */
	public int getTF_IncentiveConfig_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_IncentiveConfig_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_IncentiveConfig_UU.
		@param TF_IncentiveConfig_UU TF_IncentiveConfig_UU	  */
	public void setTF_IncentiveConfig_UU (String TF_IncentiveConfig_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_IncentiveConfig_UU, TF_IncentiveConfig_UU);
	}

	/** Get TF_IncentiveConfig_UU.
		@return TF_IncentiveConfig_UU	  */
	public String getTF_IncentiveConfig_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_IncentiveConfig_UU);
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