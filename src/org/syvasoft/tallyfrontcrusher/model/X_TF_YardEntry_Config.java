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

/** Generated Model for TF_YardEntry_Config
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_TF_YardEntry_Config extends PO implements I_TF_YardEntry_Config, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180201L;

    /** Standard Constructor */
    public X_TF_YardEntry_Config (Properties ctx, int TF_YardEntry_Config_ID, String trxName)
    {
      super (ctx, TF_YardEntry_Config_ID, trxName);
      /** if (TF_YardEntry_Config_ID == 0)
        {
			setTF_VehicleType_ID (0);
			setTF_YardEntry_Config_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_YardEntry_Config (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_YardEntry_Config[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Extra Bucket Price.
		@param ExtraBucketPrice Extra Bucket Price	  */
	public void setExtraBucketPrice (BigDecimal ExtraBucketPrice)
	{
		set_Value (COLUMNNAME_ExtraBucketPrice, ExtraBucketPrice);
	}

	/** Get Extra Bucket Price.
		@return Extra Bucket Price	  */
	public BigDecimal getExtraBucketPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ExtraBucketPrice);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Permit Price.
		@param PermitPrice Permit Price	  */
	public void setPermitPrice (BigDecimal PermitPrice)
	{
		set_Value (COLUMNNAME_PermitPrice, PermitPrice);
	}

	/** Get Permit Price.
		@return Permit Price	  */
	public BigDecimal getPermitPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PermitPrice);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Yard Entry Configuration.
		@param TF_YardEntry_Config_ID Yard Entry Configuration	  */
	public void setTF_YardEntry_Config_ID (int TF_YardEntry_Config_ID)
	{
		if (TF_YardEntry_Config_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_YardEntry_Config_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_YardEntry_Config_ID, Integer.valueOf(TF_YardEntry_Config_ID));
	}

	/** Get Yard Entry Configuration.
		@return Yard Entry Configuration	  */
	public int getTF_YardEntry_Config_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_YardEntry_Config_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_YardEntry_Config_UU.
		@param TF_YardEntry_Config_UU TF_YardEntry_Config_UU	  */
	public void setTF_YardEntry_Config_UU (String TF_YardEntry_Config_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_YardEntry_Config_UU, TF_YardEntry_Config_UU);
	}

	/** Get TF_YardEntry_Config_UU.
		@return TF_YardEntry_Config_UU	  */
	public String getTF_YardEntry_Config_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_YardEntry_Config_UU);
	}

	/** Set W/P Price.
		@param WpPrice W/P Price	  */
	public void setWpPrice (BigDecimal WpPrice)
	{
		set_Value (COLUMNNAME_WpPrice, WpPrice);
	}

	/** Get W/P Price.
		@return W/P Price	  */
	public BigDecimal getWpPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_WpPrice);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}