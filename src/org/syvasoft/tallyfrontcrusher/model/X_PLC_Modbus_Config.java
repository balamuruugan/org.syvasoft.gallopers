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
import org.compiere.util.KeyNamePair;

/** Generated Model for PLC_Modbus_Config
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_PLC_Modbus_Config extends PO implements I_PLC_Modbus_Config, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20211006L;

    /** Standard Constructor */
    public X_PLC_Modbus_Config (Properties ctx, int PLC_Modbus_Config_ID, String trxName)
    {
      super (ctx, PLC_Modbus_Config_ID, trxName);
      /** if (PLC_Modbus_Config_ID == 0)
        {
			setC_UOM_ID (0);
			setModbusAddress (null);
			setName (null);
			setPLC_Modbus_Config_ID (0);
			setPM_Machinery_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PLC_Modbus_Config (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PLC_Modbus_Config[")
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

	/** Set Interval.
		@param Interval Interval	  */
	public void setInterval (BigDecimal Interval)
	{
		set_Value (COLUMNNAME_Interval, Interval);
	}

	/** Get Interval.
		@return Interval	  */
	public BigDecimal getInterval () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Interval);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Last Reading On.
		@param LastRead Last Reading On	  */
	public void setLastRead (Timestamp LastRead)
	{
		set_Value (COLUMNNAME_LastRead, LastRead);
	}

	/** Get Last Reading On.
		@return Last Reading On	  */
	public Timestamp getLastRead () 
	{
		return (Timestamp)get_Value(COLUMNNAME_LastRead);
	}

	/** Set Modbus Address.
		@param ModbusAddress Modbus Address	  */
	public void setModbusAddress (String ModbusAddress)
	{
		set_Value (COLUMNNAME_ModbusAddress, ModbusAddress);
	}

	/** Get Modbus Address.
		@return Modbus Address	  */
	public String getModbusAddress () 
	{
		return (String)get_Value(COLUMNNAME_ModbusAddress);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

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

	/** Set PLC_Modbus_Config_UU.
		@param PLC_Modbus_Config_UU PLC_Modbus_Config_UU	  */
	public void setPLC_Modbus_Config_UU (String PLC_Modbus_Config_UU)
	{
		set_ValueNoCheck (COLUMNNAME_PLC_Modbus_Config_UU, PLC_Modbus_Config_UU);
	}

	/** Get PLC_Modbus_Config_UU.
		@return PLC_Modbus_Config_UU	  */
	public String getPLC_Modbus_Config_UU () 
	{
		return (String)get_Value(COLUMNNAME_PLC_Modbus_Config_UU);
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
			set_Value (COLUMNNAME_PM_Machinery_ID, null);
		else 
			set_Value (COLUMNNAME_PM_Machinery_ID, Integer.valueOf(PM_Machinery_ID));
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

	/** Set Result Pattern.
		@param ResultPattern Result Pattern	  */
	public void setResultPattern (String ResultPattern)
	{
		set_Value (COLUMNNAME_ResultPattern, ResultPattern);
	}

	/** Get Result Pattern.
		@return Result Pattern	  */
	public String getResultPattern () 
	{
		return (String)get_Value(COLUMNNAME_ResultPattern);
	}
}