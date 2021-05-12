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

/** Generated Model for PM_SpareSetup
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_PM_SpareSetup extends PO implements I_PM_SpareSetup, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210111L;

    /** Standard Constructor */
    public X_PM_SpareSetup (Properties ctx, int PM_SpareSetup_ID, String trxName)
    {
      super (ctx, PM_SpareSetup_ID, trxName);
      /** if (PM_SpareSetup_ID == 0)
        {
			setPM_SpareSetup_ID (0);
			setQtyRequired (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_PM_SpareSetup (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PM_SpareSetup[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Spare.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Spare.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public I_PM_MachineryType getPM_MachineryType() throws RuntimeException
    {
		return (I_PM_MachineryType)MTable.get(getCtx(), I_PM_MachineryType.Table_Name)
			.getPO(getPM_MachineryType_ID(), get_TrxName());	}

	/** Set Machinery Type.
		@param PM_MachineryType_ID Machinery Type	  */
	public void setPM_MachineryType_ID (int PM_MachineryType_ID)
	{
		if (PM_MachineryType_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PM_MachineryType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PM_MachineryType_ID, Integer.valueOf(PM_MachineryType_ID));
	}

	/** Get Machinery Type.
		@return Machinery Type	  */
	public int getPM_MachineryType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PM_MachineryType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Spare Setup.
		@param PM_SpareSetup_ID Spare Setup	  */
	public void setPM_SpareSetup_ID (int PM_SpareSetup_ID)
	{
		if (PM_SpareSetup_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PM_SpareSetup_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PM_SpareSetup_ID, Integer.valueOf(PM_SpareSetup_ID));
	}

	/** Get Spare Setup.
		@return Spare Setup	  */
	public int getPM_SpareSetup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PM_SpareSetup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set PM_SpareSetup_UU.
		@param PM_SpareSetup_UU PM_SpareSetup_UU	  */
	public void setPM_SpareSetup_UU (String PM_SpareSetup_UU)
	{
		set_ValueNoCheck (COLUMNNAME_PM_SpareSetup_UU, PM_SpareSetup_UU);
	}

	/** Get PM_SpareSetup_UU.
		@return PM_SpareSetup_UU	  */
	public String getPM_SpareSetup_UU () 
	{
		return (String)get_Value(COLUMNNAME_PM_SpareSetup_UU);
	}

	/** Set Balance Quantity.
		@param QtyBalance Balance Quantity	  */
	public void setQtyBalance (BigDecimal QtyBalance)
	{
		throw new IllegalArgumentException ("QtyBalance is virtual column");	}

	/** Get Balance Quantity.
		@return Balance Quantity	  */
	public BigDecimal getQtyBalance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyBalance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity Issued.
		@param QtyIssued Quantity Issued	  */
	public void setQtyIssued (BigDecimal QtyIssued)
	{
		throw new IllegalArgumentException ("QtyIssued is virtual column");	}

	/** Get Quantity Issued.
		@return Quantity Issued	  */
	public BigDecimal getQtyIssued () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyIssued);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Qty Required.
		@param QtyRequired Qty Required	  */
	public void setQtyRequired (BigDecimal QtyRequired)
	{
		set_Value (COLUMNNAME_QtyRequired, QtyRequired);
	}

	/** Get Qty Required.
		@return Qty Required	  */
	public BigDecimal getQtyRequired () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyRequired);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Track Spare Life.
		@param TrackSpareLife Track Spare Life	  */
	public void setTrackSpareLife (boolean TrackSpareLife)
	{
		throw new IllegalArgumentException ("TrackSpareLife is virtual column");	}

	/** Get Track Spare Life.
		@return Track Spare Life	  */
	public boolean isTrackSpareLife () 
	{
		Object oo = get_Value(COLUMNNAME_TrackSpareLife);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}