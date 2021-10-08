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

/** Generated Model for PLC_Reading_Log
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_PLC_Reading_Log extends PO implements I_PLC_Reading_Log, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20211006L;

    /** Standard Constructor */
    public X_PLC_Reading_Log (Properties ctx, int PLC_Reading_Log_ID, String trxName)
    {
      super (ctx, PLC_Reading_Log_ID, trxName);
      /** if (PLC_Reading_Log_ID == 0)
        {
			setC_UOM_ID (0);
			setDateLog (new Timestamp( System.currentTimeMillis() ));
			setPLC_Reading_Log_ID (0);
			setPM_Machinery_ID (0);
			setResult (null);
        } */
    }

    /** Load Constructor */
    public X_PLC_Reading_Log (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PLC_Reading_Log[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Log Date.
		@param DateLog Log Date	  */
	public void setDateLog (Timestamp DateLog)
	{
		set_Value (COLUMNNAME_DateLog, DateLog);
	}

	/** Get Log Date.
		@return Log Date	  */
	public Timestamp getDateLog () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateLog);
	}

	public I_PLC_Modbus_Config getPLC_Modbus_Config() throws RuntimeException
    {
		return (I_PLC_Modbus_Config)MTable.get(getCtx(), I_PLC_Modbus_Config.Table_Name)
			.getPO(getPLC_Modbus_Config_ID(), get_TrxName());	}

	/** Set PLC Modbus Configuration.
		@param PLC_Modbus_Config_ID PLC Modbus Configuration	  */
	public void setPLC_Modbus_Config_ID (int PLC_Modbus_Config_ID)
	{
		if (PLC_Modbus_Config_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PLC_Modbus_Config_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PLC_Modbus_Config_ID, Integer.valueOf(PLC_Modbus_Config_ID));
	}

	/** Get PLC Modbus Configuration.
		@return PLC Modbus Configuration	  */
	public int getPLC_Modbus_Config_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PLC_Modbus_Config_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set PLC_Reading_Log.
		@param PLC_Reading_Log_ID PLC_Reading_Log	  */
	public void setPLC_Reading_Log_ID (int PLC_Reading_Log_ID)
	{
		if (PLC_Reading_Log_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PLC_Reading_Log_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PLC_Reading_Log_ID, Integer.valueOf(PLC_Reading_Log_ID));
	}

	/** Get PLC_Reading_Log.
		@return PLC_Reading_Log	  */
	public int getPLC_Reading_Log_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PLC_Reading_Log_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set PLC_Reading_Log_UU.
		@param PLC_Reading_Log_UU PLC_Reading_Log_UU	  */
	public void setPLC_Reading_Log_UU (String PLC_Reading_Log_UU)
	{
		set_ValueNoCheck (COLUMNNAME_PLC_Reading_Log_UU, PLC_Reading_Log_UU);
	}

	/** Get PLC_Reading_Log_UU.
		@return PLC_Reading_Log_UU	  */
	public String getPLC_Reading_Log_UU () 
	{
		return (String)get_Value(COLUMNNAME_PLC_Reading_Log_UU);
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

	/** Set Result.
		@param Result 
		Result of the action taken
	  */
	public void setResult (String Result)
	{
		set_ValueNoCheck (COLUMNNAME_Result, Result);
	}

	/** Get Result.
		@return Result of the action taken
	  */
	public String getResult () 
	{
		return (String)get_Value(COLUMNNAME_Result);
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
}