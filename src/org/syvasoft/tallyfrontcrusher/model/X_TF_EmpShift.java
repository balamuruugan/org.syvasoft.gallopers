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
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for TF_EmpShift
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_EmpShift extends PO implements I_TF_EmpShift, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20211101L;

    /** Standard Constructor */
    public X_TF_EmpShift (Properties ctx, int TF_EmpShift_ID, String trxName)
    {
      super (ctx, TF_EmpShift_ID, trxName);
      /** if (TF_EmpShift_ID == 0)
        {
			setBeginTime (null);
			setBreak1BeginTime (null);
			setBreak1EndTime (null);
			setBreak2BeginTime (null);
			setBreak2EndTime (null);
			setEndTime (null);
			setGraceTime (0);
			setIsFlexiShift (false);
			setName (null);
			setPartialDay (null);
			setPartialDayBeginTime (null);
			setPartialDayEndTime (null);
			setPunchBeginBefore (0);
			setPunchEndAfter (0);
			setSName (null);
			setTF_EmpShift_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_EmpShift (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_EmpShift[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Begin Time.
		@param BeginTime Begin Time	  */
	public void setBeginTime (String BeginTime)
	{
		set_Value (COLUMNNAME_BeginTime, BeginTime);
	}

	/** Get Begin Time.
		@return Begin Time	  */
	public String getBeginTime () 
	{
		return (String)get_Value(COLUMNNAME_BeginTime);
	}

	/** Set Break1 Begin Time.
		@param Break1BeginTime Break1 Begin Time	  */
	public void setBreak1BeginTime (String Break1BeginTime)
	{
		set_Value (COLUMNNAME_Break1BeginTime, Break1BeginTime);
	}

	/** Get Break1 Begin Time.
		@return Break1 Begin Time	  */
	public String getBreak1BeginTime () 
	{
		return (String)get_Value(COLUMNNAME_Break1BeginTime);
	}

	/** Set Break1 End Time.
		@param Break1EndTime Break1 End Time	  */
	public void setBreak1EndTime (String Break1EndTime)
	{
		set_Value (COLUMNNAME_Break1EndTime, Break1EndTime);
	}

	/** Get Break1 End Time.
		@return Break1 End Time	  */
	public String getBreak1EndTime () 
	{
		return (String)get_Value(COLUMNNAME_Break1EndTime);
	}

	/** Set Break2 Begin Time.
		@param Break2BeginTime Break2 Begin Time	  */
	public void setBreak2BeginTime (String Break2BeginTime)
	{
		set_Value (COLUMNNAME_Break2BeginTime, Break2BeginTime);
	}

	/** Get Break2 Begin Time.
		@return Break2 Begin Time	  */
	public String getBreak2BeginTime () 
	{
		return (String)get_Value(COLUMNNAME_Break2BeginTime);
	}

	/** Set Break2 End Time.
		@param Break2EndTime Break2 End Time	  */
	public void setBreak2EndTime (String Break2EndTime)
	{
		set_Value (COLUMNNAME_Break2EndTime, Break2EndTime);
	}

	/** Get Break2 End Time.
		@return Break2 End Time	  */
	public String getBreak2EndTime () 
	{
		return (String)get_Value(COLUMNNAME_Break2EndTime);
	}

	/** Set End Time.
		@param EndTime 
		End of the time span
	  */
	public void setEndTime (String EndTime)
	{
		set_Value (COLUMNNAME_EndTime, EndTime);
	}

	/** Get End Time.
		@return End of the time span
	  */
	public String getEndTime () 
	{
		return (String)get_Value(COLUMNNAME_EndTime);
	}

	/** Set Grace Time.
		@param GraceTime Grace Time	  */
	public void setGraceTime (int GraceTime)
	{
		set_Value (COLUMNNAME_GraceTime, Integer.valueOf(GraceTime));
	}

	/** Get Grace Time.
		@return Grace Time	  */
	public int getGraceTime () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GraceTime);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Is Flexi Shift.
		@param IsFlexiShift Is Flexi Shift	  */
	public void setIsFlexiShift (boolean IsFlexiShift)
	{
		set_Value (COLUMNNAME_IsFlexiShift, Boolean.valueOf(IsFlexiShift));
	}

	/** Get Is Flexi Shift.
		@return Is Flexi Shift	  */
	public boolean isFlexiShift () 
	{
		Object oo = get_Value(COLUMNNAME_IsFlexiShift);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** PartialDay AD_Reference_ID=167 */
	public static final int PARTIALDAY_AD_Reference_ID=167;
	/** Sunday = 7 */
	public static final String PARTIALDAY_Sunday = "7";
	/** Monday = 1 */
	public static final String PARTIALDAY_Monday = "1";
	/** Tuesday = 2 */
	public static final String PARTIALDAY_Tuesday = "2";
	/** Wednesday = 3 */
	public static final String PARTIALDAY_Wednesday = "3";
	/** Thursday = 4 */
	public static final String PARTIALDAY_Thursday = "4";
	/** Friday = 5 */
	public static final String PARTIALDAY_Friday = "5";
	/** Saturday = 6 */
	public static final String PARTIALDAY_Saturday = "6";
	/** Set Partial Day.
		@param PartialDay Partial Day	  */
	public void setPartialDay (String PartialDay)
	{

		set_Value (COLUMNNAME_PartialDay, PartialDay);
	}

	/** Get Partial Day.
		@return Partial Day	  */
	public String getPartialDay () 
	{
		return (String)get_Value(COLUMNNAME_PartialDay);
	}

	/** Set Partial Day Begin Time.
		@param PartialDayBeginTime Partial Day Begin Time	  */
	public void setPartialDayBeginTime (String PartialDayBeginTime)
	{
		set_Value (COLUMNNAME_PartialDayBeginTime, PartialDayBeginTime);
	}

	/** Get Partial Day Begin Time.
		@return Partial Day Begin Time	  */
	public String getPartialDayBeginTime () 
	{
		return (String)get_Value(COLUMNNAME_PartialDayBeginTime);
	}

	/** Set Partial Day End Time.
		@param PartialDayEndTime Partial Day End Time	  */
	public void setPartialDayEndTime (String PartialDayEndTime)
	{
		set_Value (COLUMNNAME_PartialDayEndTime, PartialDayEndTime);
	}

	/** Get Partial Day End Time.
		@return Partial Day End Time	  */
	public String getPartialDayEndTime () 
	{
		return (String)get_Value(COLUMNNAME_PartialDayEndTime);
	}

	/** Set Punch Begin Before.
		@param PunchBeginBefore Punch Begin Before	  */
	public void setPunchBeginBefore (int PunchBeginBefore)
	{
		set_Value (COLUMNNAME_PunchBeginBefore, Integer.valueOf(PunchBeginBefore));
	}

	/** Get Punch Begin Before.
		@return Punch Begin Before	  */
	public int getPunchBeginBefore () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PunchBeginBefore);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Punch End After.
		@param PunchEndAfter Punch End After	  */
	public void setPunchEndAfter (int PunchEndAfter)
	{
		set_Value (COLUMNNAME_PunchEndAfter, Integer.valueOf(PunchEndAfter));
	}

	/** Get Punch End After.
		@return Punch End After	  */
	public int getPunchEndAfter () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PunchEndAfter);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Short Name.
		@param SName Short Name	  */
	public void setSName (String SName)
	{
		set_Value (COLUMNNAME_SName, SName);
	}

	/** Get Short Name.
		@return Short Name	  */
	public String getSName () 
	{
		return (String)get_Value(COLUMNNAME_SName);
	}

	/** Set Employee Shift.
		@param TF_EmpShift_ID Employee Shift	  */
	public void setTF_EmpShift_ID (int TF_EmpShift_ID)
	{
		if (TF_EmpShift_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_EmpShift_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_EmpShift_ID, Integer.valueOf(TF_EmpShift_ID));
	}

	/** Get Employee Shift.
		@return Employee Shift	  */
	public int getTF_EmpShift_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_EmpShift_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_EmpShift_UU.
		@param TF_EmpShift_UU TF_EmpShift_UU	  */
	public void setTF_EmpShift_UU (String TF_EmpShift_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_EmpShift_UU, TF_EmpShift_UU);
	}

	/** Get TF_EmpShift_UU.
		@return TF_EmpShift_UU	  */
	public String getTF_EmpShift_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_EmpShift_UU);
	}
}