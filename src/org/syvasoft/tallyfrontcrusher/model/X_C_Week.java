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

/** Generated Model for C_Week
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_C_Week extends PO implements I_C_Week, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20211002L;

    /** Standard Constructor */
    public X_C_Week (Properties ctx, int C_Week_ID, String trxName)
    {
      super (ctx, C_Week_ID, trxName);
      /** if (C_Week_ID == 0)
        {
			setC_Period_ID (0);
			setC_Week_ID (0);
			setName (null);
			setStartDate (new Timestamp( System.currentTimeMillis() ));
			setWeekNo (0);
        } */
    }

    /** Load Constructor */
    public X_C_Week (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_Week[")
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
			set_ValueNoCheck (COLUMNNAME_C_Period_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Period_ID, Integer.valueOf(C_Period_ID));
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

	/** Set Week .
		@param C_Week_ID Week 	  */
	public void setC_Week_ID (int C_Week_ID)
	{
		if (C_Week_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Week_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Week_ID, Integer.valueOf(C_Week_ID));
	}

	/** Get Week .
		@return Week 	  */
	public int getC_Week_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Week_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_Week_UU.
		@param C_Week_UU C_Week_UU	  */
	public void setC_Week_UU (String C_Week_UU)
	{
		set_ValueNoCheck (COLUMNNAME_C_Week_UU, C_Week_UU);
	}

	/** Get C_Week_UU.
		@return C_Week_UU	  */
	public String getC_Week_UU () 
	{
		return (String)get_Value(COLUMNNAME_C_Week_UU);
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

	/** Set End Date.
		@param EndDate 
		Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate)
	{
		set_Value (COLUMNNAME_EndDate, EndDate);
	}

	/** Get End Date.
		@return Last effective date (inclusive)
	  */
	public Timestamp getEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDate);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Start Date.
		@param StartDate 
		First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate)
	{
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return First effective day (inclusive)
	  */
	public Timestamp getStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
	}

	/** Set Week No.
		@param WeekNo Week No	  */
	public void setWeekNo (int WeekNo)
	{
		set_Value (COLUMNNAME_WeekNo, Integer.valueOf(WeekNo));
	}

	/** Get Week No.
		@return Week No	  */
	public int getWeekNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WeekNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}