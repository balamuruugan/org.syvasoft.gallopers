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

/** Generated Model for TF_TorderLine
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_TorderLine extends PO implements I_TF_TorderLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210619L;

    /** Standard Constructor */
    public X_TF_TorderLine (Properties ctx, int TF_TorderLine_ID, String trxName)
    {
      super (ctx, TF_TorderLine_ID, trxName);
      /** if (TF_TorderLine_ID == 0)
        {
			setC_UOM_ID (0);
			setTF_Destination_ID (0);
			setTF_TorderLine_ID (0);
			setTF_VehicleType_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_TorderLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_TorderLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_Tax getC_Tax() throws RuntimeException
    {
		return (org.compiere.model.I_C_Tax)MTable.get(getCtx(), org.compiere.model.I_C_Tax.Table_Name)
			.getPO(getC_Tax_ID(), get_TrxName());	}

	/** Set Tax.
		@param C_Tax_ID 
		Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID)
	{
		if (C_Tax_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Tax_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Tax_ID, Integer.valueOf(C_Tax_ID));
	}

	/** Get Tax.
		@return Tax identifier
	  */
	public int getC_Tax_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Tax_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Freight Rate.
		@param FreightPrice Freight Rate	  */
	public void setFreightPrice (BigDecimal FreightPrice)
	{
		set_Value (COLUMNNAME_FreightPrice, FreightPrice);
	}

	/** Get Freight Rate.
		@return Freight Rate	  */
	public BigDecimal getFreightPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FreightPrice);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Price includes Tax.
		@param IsTaxIncluded 
		Tax is included in the price 
	  */
	public void setIsTaxIncluded (boolean IsTaxIncluded)
	{
		set_ValueNoCheck (COLUMNNAME_IsTaxIncluded, Boolean.valueOf(IsTaxIncluded));
	}

	/** Get Price includes Tax.
		@return Tax is included in the price 
	  */
	public boolean isTaxIncluded () 
	{
		Object oo = get_Value(COLUMNNAME_IsTaxIncluded);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_TF_Destination getTF_Destination() throws RuntimeException
    {
		return (I_TF_Destination)MTable.get(getCtx(), I_TF_Destination.Table_Name)
			.getPO(getTF_Destination_ID(), get_TrxName());	}

	/** Set Destination.
		@param TF_Destination_ID Destination	  */
	public void setTF_Destination_ID (int TF_Destination_ID)
	{
		if (TF_Destination_ID < 1) 
			set_Value (COLUMNNAME_TF_Destination_ID, null);
		else 
			set_Value (COLUMNNAME_TF_Destination_ID, Integer.valueOf(TF_Destination_ID));
	}

	/** Get Destination.
		@return Destination	  */
	public int getTF_Destination_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_Destination_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_TF_TOrder getTF_TOrder() throws RuntimeException
    {
		return (I_TF_TOrder)MTable.get(getCtx(), I_TF_TOrder.Table_Name)
			.getPO(getTF_TOrder_ID(), get_TrxName());	}

	/** Set TF_Torder.
		@param TF_TOrder_ID TF_Torder	  */
	public void setTF_TOrder_ID (int TF_TOrder_ID)
	{
		if (TF_TOrder_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_TOrder_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_TOrder_ID, Integer.valueOf(TF_TOrder_ID));
	}

	/** Get TF_Torder.
		@return TF_Torder	  */
	public int getTF_TOrder_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_TOrder_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_TorderLine.
		@param TF_TorderLine_ID TF_TorderLine	  */
	public void setTF_TorderLine_ID (int TF_TorderLine_ID)
	{
		if (TF_TorderLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_TorderLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_TorderLine_ID, Integer.valueOf(TF_TorderLine_ID));
	}

	/** Get TF_TorderLine.
		@return TF_TorderLine	  */
	public int getTF_TorderLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_TorderLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_TorderLine_UU.
		@param TF_TorderLine_UU TF_TorderLine_UU	  */
	public void setTF_TorderLine_UU (String TF_TorderLine_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_TorderLine_UU, TF_TorderLine_UU);
	}

	/** Get TF_TorderLine_UU.
		@return TF_TorderLine_UU	  */
	public String getTF_TorderLine_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_TorderLine_UU);
	}

	public I_TF_VehicleType getTF_VehicleType() throws RuntimeException
    {
		return (I_TF_VehicleType)MTable.get(getCtx(), I_TF_VehicleType.Table_Name)
			.getPO(getTF_VehicleType_ID(), get_TrxName());	}

	/** Set Vehicle Type.
		@param TF_VehicleType_ID Vehicle Type	  */
	public void setTF_VehicleType_ID (int TF_VehicleType_ID)
	{
		if (TF_VehicleType_ID < 1) 
			set_Value (COLUMNNAME_TF_VehicleType_ID, null);
		else 
			set_Value (COLUMNNAME_TF_VehicleType_ID, Integer.valueOf(TF_VehicleType_ID));
	}

	/** Get Vehicle Type.
		@return Vehicle Type	  */
	public int getTF_VehicleType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_VehicleType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}