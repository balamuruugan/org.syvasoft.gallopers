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

/** Generated Model for TF_BiometricAttendence
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_BiometricAttendence extends PO implements I_TF_BiometricAttendence, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20211105L;

    /** Standard Constructor */
    public X_TF_BiometricAttendence (Properties ctx, int TF_BiometricAttendence_ID, String trxName)
    {
      super (ctx, TF_BiometricAttendence_ID, trxName);
      /** if (TF_BiometricAttendence_ID == 0)
        {
			setAttendenceTime (new Timestamp( System.currentTimeMillis() ));
			setC_BPartner_ID (0);
			setEnrollNo (0);
			setTF_Biometricattendence_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_BiometricAttendence (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_BiometricAttendence[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Attendence Time.
		@param AttendenceTime Attendence Time	  */
	public void setAttendenceTime (Timestamp AttendenceTime)
	{
		set_Value (COLUMNNAME_AttendenceTime, AttendenceTime);
	}

	/** Get Attendence Time.
		@return Attendence Time	  */
	public Timestamp getAttendenceTime () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AttendenceTime);
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

	/** Set Enroll No.
		@param EnrollNo Enroll No	  */
	public void setEnrollNo (int EnrollNo)
	{
		set_Value (COLUMNNAME_EnrollNo, Integer.valueOf(EnrollNo));
	}

	/** Get Enroll No.
		@return Enroll No	  */
	public int getEnrollNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EnrollNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Check In = 0 */
	public static final String INOUTMODE_CheckIn = "0";
	/** Check Out = 1 */
	public static final String INOUTMODE_CheckOut = "1";
	/** Break In = 2 */
	public static final String INOUTMODE_BreakIn = "2";
	/** Break Out = 3 */
	public static final String INOUTMODE_BreakOut = "3";
	/** Overtime Out = 5 */
	public static final String INOUTMODE_OvertimeOut = "5";
	/** Overtime In = 6 */
	public static final String INOUTMODE_OvertimeIn = "6";
	/** Set In Out Mode.
		@param InOutMode In Out Mode	  */
	public void setInOutMode (String InOutMode)
	{

		set_Value (COLUMNNAME_InOutMode, InOutMode);
	}

	/** Get In Out Mode.
		@return In Out Mode	  */
	public String getInOutMode () 
	{
		return (String)get_Value(COLUMNNAME_InOutMode);
	}

	/** Set Biometric Attendence.
		@param TF_Biometricattendence_ID Biometric Attendence	  */
	public void setTF_Biometricattendence_ID (int TF_Biometricattendence_ID)
	{
		if (TF_Biometricattendence_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_Biometricattendence_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_Biometricattendence_ID, Integer.valueOf(TF_Biometricattendence_ID));
	}

	/** Get Biometric Attendence.
		@return Biometric Attendence	  */
	public int getTF_Biometricattendence_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_Biometricattendence_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_Biometricattendence_UU.
		@param TF_Biometricattendence_UU TF_Biometricattendence_UU	  */
	public void setTF_Biometricattendence_UU (String TF_Biometricattendence_UU)
	{
		set_Value (COLUMNNAME_TF_Biometricattendence_UU, TF_Biometricattendence_UU);
	}

	/** Get TF_Biometricattendence_UU.
		@return TF_Biometricattendence_UU	  */
	public String getTF_Biometricattendence_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_Biometricattendence_UU);
	}

	/** Face = 15 */
	public static final String VERIFYMODE_Face = "15";
	/** Finger Print = 1 */
	public static final String VERIFYMODE_FingerPrint = "1";
	/** Set Verify Mode.
		@param VerifyMode Verify Mode	  */
	public void setVerifyMode (String VerifyMode)
	{

		set_Value (COLUMNNAME_VerifyMode, VerifyMode);
	}

	/** Get Verify Mode.
		@return Verify Mode	  */
	public String getVerifyMode () 
	{
		return (String)get_Value(COLUMNNAME_VerifyMode);
	}

	/** Set Work Code.
		@param WorkCode Work Code	  */
	public void setWorkCode (int WorkCode)
	{
		set_Value (COLUMNNAME_WorkCode, Integer.valueOf(WorkCode));
	}

	/** Get Work Code.
		@return Work Code	  */
	public int getWorkCode () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WorkCode);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}