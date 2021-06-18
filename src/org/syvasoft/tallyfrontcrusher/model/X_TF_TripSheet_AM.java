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

/** Generated Model for TF_TripSheet_AM
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_TripSheet_AM extends PO implements I_TF_TripSheet_AM, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210618L;

    /** Standard Constructor */
    public X_TF_TripSheet_AM (Properties ctx, int TF_TripSheet_AM_ID, String trxName)
    {
      super (ctx, TF_TripSheet_AM_ID, trxName);
      /** if (TF_TripSheet_AM_ID == 0)
        {
			setC_UOM_ID (0);
			setClosing_Meter (Env.ZERO);
			setDateReport (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setOpening_Meter (Env.ZERO);
			setPM_Machinery_ID (0);
			setProcessed (false);
// N
			setRunning_Meter (Env.ZERO);
			setTF_TripSheet_AM_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_TripSheet_AM (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_TripSheet_AM[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Amount.
		@param Amount 
		Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount)
	{
		set_Value (COLUMNNAME_Amount, Amount);
	}

	/** Get Amount.
		@return Amount in a defined currency
	  */
	public BigDecimal getAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Tripsheet Additional Meter Readings.
		@param TF_TripSheet_AM_ID Tripsheet Additional Meter Readings	  */
	public void setTF_TripSheet_AM_ID (int TF_TripSheet_AM_ID)
	{
		if (TF_TripSheet_AM_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_TripSheet_AM_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_TripSheet_AM_ID, Integer.valueOf(TF_TripSheet_AM_ID));
	}

	/** Get Tripsheet Additional Meter Readings.
		@return Tripsheet Additional Meter Readings	  */
	public int getTF_TripSheet_AM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_TripSheet_AM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_TripSheet_AM_UU.
		@param TF_TripSheet_AM_UU TF_TripSheet_AM_UU	  */
	public void setTF_TripSheet_AM_UU (String TF_TripSheet_AM_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_TripSheet_AM_UU, TF_TripSheet_AM_UU);
	}

	/** Get TF_TripSheet_AM_UU.
		@return TF_TripSheet_AM_UU	  */
	public String getTF_TripSheet_AM_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_TripSheet_AM_UU);
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
}