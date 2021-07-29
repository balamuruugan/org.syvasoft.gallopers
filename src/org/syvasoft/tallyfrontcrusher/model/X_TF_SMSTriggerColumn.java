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

/** Generated Model for TF_SMSTriggerColumn
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_SMSTriggerColumn extends PO implements I_TF_SMSTriggerColumn, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210525L;

    /** Standard Constructor */
    public X_TF_SMSTriggerColumn (Properties ctx, int TF_SMSTriggerColumn_ID, String trxName)
    {
      super (ctx, TF_SMSTriggerColumn_ID, trxName);
      /** if (TF_SMSTriggerColumn_ID == 0)
        {
			setTF_SMSTriggerColumn_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_SMSTriggerColumn (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_SMSTriggerColumn[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Column getAD_Column() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Column)MTable.get(getCtx(), org.compiere.model.I_AD_Column.Table_Name)
			.getPO(getAD_Column_ID(), get_TrxName());	}

	/** Set Column.
		@param AD_Column_ID 
		Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID)
	{
		if (AD_Column_ID < 1) 
			set_Value (COLUMNNAME_AD_Column_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Column_ID, Integer.valueOf(AD_Column_ID));
	}

	/** Get Column.
		@return Column in the table
	  */
	public int getAD_Column_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Column_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_TF_SmsNotification getTF_SmsNotification() throws RuntimeException
    {
		return (I_TF_SmsNotification)MTable.get(getCtx(), I_TF_SmsNotification.Table_Name)
			.getPO(getTF_SmsNotification_ID(), get_TrxName());	}

	/** Set TF_SmsNotification.
		@param TF_SmsNotification_ID TF_SmsNotification	  */
	public void setTF_SmsNotification_ID (int TF_SmsNotification_ID)
	{
		if (TF_SmsNotification_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_SmsNotification_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_SmsNotification_ID, Integer.valueOf(TF_SmsNotification_ID));
	}

	/** Get TF_SmsNotification.
		@return TF_SmsNotification	  */
	public int getTF_SmsNotification_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_SmsNotification_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set SMS Trigger Column.
		@param TF_SMSTriggerColumn_ID SMS Trigger Column	  */
	public void setTF_SMSTriggerColumn_ID (int TF_SMSTriggerColumn_ID)
	{
		if (TF_SMSTriggerColumn_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_SMSTriggerColumn_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_SMSTriggerColumn_ID, Integer.valueOf(TF_SMSTriggerColumn_ID));
	}

	/** Get SMS Trigger Column.
		@return SMS Trigger Column	  */
	public int getTF_SMSTriggerColumn_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_SMSTriggerColumn_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_SMSTriggerColumn_UU.
		@param TF_SMSTriggerColumn_UU TF_SMSTriggerColumn_UU	  */
	public void setTF_SMSTriggerColumn_UU (String TF_SMSTriggerColumn_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_SMSTriggerColumn_UU, TF_SMSTriggerColumn_UU);
	}

	/** Get TF_SMSTriggerColumn_UU.
		@return TF_SMSTriggerColumn_UU	  */
	public String getTF_SMSTriggerColumn_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_SMSTriggerColumn_UU);
	}
}