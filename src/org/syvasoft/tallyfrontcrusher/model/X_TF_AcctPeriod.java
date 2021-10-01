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

/** Generated Model for TF_AcctPeriod
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_AcctPeriod extends PO implements I_TF_AcctPeriod, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20211001L;

    /** Standard Constructor */
    public X_TF_AcctPeriod (Properties ctx, int TF_AcctPeriod_ID, String trxName)
    {
      super (ctx, TF_AcctPeriod_ID, trxName);
      /** if (TF_AcctPeriod_ID == 0)
        {
			setC_BankAccount_ID (0);
			setDateFrom (new Timestamp( System.currentTimeMillis() ));
			setDateTo (new Timestamp( System.currentTimeMillis() ));
			setTF_AcctPeriod_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_AcctPeriod (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_AcctPeriod[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_BankAccount getC_BankAccount() throws RuntimeException
    {
		return (org.compiere.model.I_C_BankAccount)MTable.get(getCtx(), org.compiere.model.I_C_BankAccount.Table_Name)
			.getPO(getC_BankAccount_ID(), get_TrxName());	}

	/** Set Bank/Cash Account.
		@param C_BankAccount_ID 
		Account at the Bank
	  */
	public void setC_BankAccount_ID (int C_BankAccount_ID)
	{
		if (C_BankAccount_ID < 1) 
			set_Value (COLUMNNAME_C_BankAccount_ID, null);
		else 
			set_Value (COLUMNNAME_C_BankAccount_ID, Integer.valueOf(C_BankAccount_ID));
	}

	/** Get Bank/Cash Account.
		@return Account at the Bank
	  */
	public int getC_BankAccount_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BankAccount_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date From.
		@param DateFrom 
		Starting date for a range
	  */
	public void setDateFrom (Timestamp DateFrom)
	{
		set_Value (COLUMNNAME_DateFrom, DateFrom);
	}

	/** Get Date From.
		@return Starting date for a range
	  */
	public Timestamp getDateFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateFrom);
	}

	/** Set Date To.
		@param DateTo 
		End date of a date range
	  */
	public void setDateTo (Timestamp DateTo)
	{
		set_Value (COLUMNNAME_DateTo, DateTo);
	}

	/** Get Date To.
		@return End date of a date range
	  */
	public Timestamp getDateTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTo);
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

	/** Set Accounting Period.
		@param TF_AcctPeriod_ID Accounting Period	  */
	public void setTF_AcctPeriod_ID (int TF_AcctPeriod_ID)
	{
		if (TF_AcctPeriod_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_AcctPeriod_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_AcctPeriod_ID, Integer.valueOf(TF_AcctPeriod_ID));
	}

	/** Get Accounting Period.
		@return Accounting Period	  */
	public int getTF_AcctPeriod_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_AcctPeriod_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_AcctPeriod_UU.
		@param TF_AcctPeriod_UU TF_AcctPeriod_UU	  */
	public void setTF_AcctPeriod_UU (String TF_AcctPeriod_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_AcctPeriod_UU, TF_AcctPeriod_UU);
	}

	/** Get TF_AcctPeriod_UU.
		@return TF_AcctPeriod_UU	  */
	public String getTF_AcctPeriod_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_AcctPeriod_UU);
	}
}