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

/** Generated Model for PM_Spare
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_PM_Spare extends PO implements I_PM_Spare, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20201229L;

    /** Standard Constructor */
    public X_PM_Spare (Properties ctx, int PM_Spare_ID, String trxName)
    {
      super (ctx, PM_Spare_ID, trxName);
      /** if (PM_Spare_ID == 0)
        {
			setM_Product_ID (0);
			setMovementDate (new Timestamp( System.currentTimeMillis() ));
			setPM_Machinery_ID (0);
			setPM_Spare_ID (0);
			setProcessed (false);
// N
			setRemainingLife (Env.ZERO);
			setSpareLife_UOM_ID (0);
			setSpareLIfeGreenLimit (Env.ZERO);
// 0
			setSpareLIfeYellowLimit (Env.ZERO);
// 0
			setSpareStatus (null);
// A
			setSpareStdLife (Env.ZERO);
// 0
        } */
    }

    /** Load Constructor */
    public X_PM_Spare (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PM_Spare[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_ValueNoCheck (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Issued at.
		@param IssuedAt Issued at	  */
	public void setIssuedAt (BigDecimal IssuedAt)
	{
		set_Value (COLUMNNAME_IssuedAt, IssuedAt);
	}

	/** Get Issued at.
		@return Issued at	  */
	public BigDecimal getIssuedAt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_IssuedAt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Last Meter.
		@param LastMeter Last Meter	  */
	public void setLastMeter (BigDecimal LastMeter)
	{
		set_Value (COLUMNNAME_LastMeter, LastMeter);
	}

	/** Get Last Meter.
		@return Last Meter	  */
	public BigDecimal getLastMeter () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LastMeter);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Life Used.
		@param LifeUsed Life Used	  */
	public void setLifeUsed (BigDecimal LifeUsed)
	{
		set_Value (COLUMNNAME_LifeUsed, LifeUsed);
	}

	/** Get Life Used.
		@return Life Used	  */
	public BigDecimal getLifeUsed () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LifeUsed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
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

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Movement Date.
		@param MovementDate 
		Date a product was moved in or out of inventory
	  */
	public void setMovementDate (Timestamp MovementDate)
	{
		set_ValueNoCheck (COLUMNNAME_MovementDate, MovementDate);
	}

	/** Get Movement Date.
		@return Date a product was moved in or out of inventory
	  */
	public Timestamp getMovementDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_MovementDate);
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

	/** Set Spare.
		@param PM_Spare_ID Spare	  */
	public void setPM_Spare_ID (int PM_Spare_ID)
	{
		if (PM_Spare_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PM_Spare_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PM_Spare_ID, Integer.valueOf(PM_Spare_ID));
	}

	/** Get Spare.
		@return Spare	  */
	public int getPM_Spare_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PM_Spare_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set PM_Spare_UU.
		@param PM_Spare_UU PM_Spare_UU	  */
	public void setPM_Spare_UU (String PM_Spare_UU)
	{
		set_ValueNoCheck (COLUMNNAME_PM_Spare_UU, PM_Spare_UU);
	}

	/** Get PM_Spare_UU.
		@return PM_Spare_UU	  */
	public String getPM_Spare_UU () 
	{
		return (String)get_Value(COLUMNNAME_PM_Spare_UU);
	}

	public I_PM_SpareGroup getPM_SpareGroup() throws RuntimeException
    {
		return (I_PM_SpareGroup)MTable.get(getCtx(), I_PM_SpareGroup.Table_Name)
			.getPO(getPM_SpareGroup_ID(), get_TrxName());	}

	/** Set Spare Group.
		@param PM_SpareGroup_ID Spare Group	  */
	public void setPM_SpareGroup_ID (int PM_SpareGroup_ID)
	{
		if (PM_SpareGroup_ID < 1) 
			set_Value (COLUMNNAME_PM_SpareGroup_ID, null);
		else 
			set_Value (COLUMNNAME_PM_SpareGroup_ID, Integer.valueOf(PM_SpareGroup_ID));
	}

	/** Get Spare Group.
		@return Spare Group	  */
	public int getPM_SpareGroup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PM_SpareGroup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Remaining Life.
		@param RemainingLife Remaining Life	  */
	public void setRemainingLife (BigDecimal RemainingLife)
	{
		set_Value (COLUMNNAME_RemainingLife, RemainingLife);
	}

	/** Get Remaining Life.
		@return Remaining Life	  */
	public BigDecimal getRemainingLife () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_RemainingLife);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Replaced Date.
		@param ReplacedDate Replaced Date	  */
	public void setReplacedDate (Timestamp ReplacedDate)
	{
		set_Value (COLUMNNAME_ReplacedDate, ReplacedDate);
	}

	/** Get Replaced Date.
		@return Replaced Date	  */
	public Timestamp getReplacedDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ReplacedDate);
	}

	public org.compiere.model.I_C_UOM getSpareLife_UOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getSpareLife_UOM_ID(), get_TrxName());	}

	/** Set Spare Life UOM.
		@param SpareLife_UOM_ID Spare Life UOM	  */
	public void setSpareLife_UOM_ID (int SpareLife_UOM_ID)
	{
		if (SpareLife_UOM_ID < 1) 
			set_Value (COLUMNNAME_SpareLife_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_SpareLife_UOM_ID, Integer.valueOf(SpareLife_UOM_ID));
	}

	/** Get Spare Life UOM.
		@return Spare Life UOM	  */
	public int getSpareLife_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SpareLife_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Spare LIfe Green Limit.
		@param SpareLIfeGreenLimit Spare LIfe Green Limit	  */
	public void setSpareLIfeGreenLimit (BigDecimal SpareLIfeGreenLimit)
	{
		set_Value (COLUMNNAME_SpareLIfeGreenLimit, SpareLIfeGreenLimit);
	}

	/** Get Spare LIfe Green Limit.
		@return Spare LIfe Green Limit	  */
	public BigDecimal getSpareLIfeGreenLimit () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_SpareLIfeGreenLimit);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Spare LIfe Yellow Limit.
		@param SpareLIfeYellowLimit Spare LIfe Yellow Limit	  */
	public void setSpareLIfeYellowLimit (BigDecimal SpareLIfeYellowLimit)
	{
		set_Value (COLUMNNAME_SpareLIfeYellowLimit, SpareLIfeYellowLimit);
	}

	/** Get Spare LIfe Yellow Limit.
		@return Spare LIfe Yellow Limit	  */
	public BigDecimal getSpareLIfeYellowLimit () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_SpareLIfeYellowLimit);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** In Use = A */
	public static final String SPARESTATUS_InUse = "A";
	/** Replaced = R */
	public static final String SPARESTATUS_Replaced = "R";
	/** Voided = V */
	public static final String SPARESTATUS_Voided = "V";
	/** Set Spare Status.
		@param SpareStatus Spare Status	  */
	public void setSpareStatus (String SpareStatus)
	{

		set_Value (COLUMNNAME_SpareStatus, SpareStatus);
	}

	/** Get Spare Status.
		@return Spare Status	  */
	public String getSpareStatus () 
	{
		return (String)get_Value(COLUMNNAME_SpareStatus);
	}

	/** Set Spare Standard Life.
		@param SpareStdLife 
		Spare Standard Life (in Spare Life UOM)
	  */
	public void setSpareStdLife (BigDecimal SpareStdLife)
	{
		set_Value (COLUMNNAME_SpareStdLife, SpareStdLife);
	}

	/** Get Spare Standard Life.
		@return Spare Standard Life (in Spare Life UOM)
	  */
	public BigDecimal getSpareStdLife () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_SpareStdLife);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_TF_Fuel_Issue getTF_Fuel_Issue() throws RuntimeException
    {
		return (I_TF_Fuel_Issue)MTable.get(getCtx(), I_TF_Fuel_Issue.Table_Name)
			.getPO(getTF_Fuel_Issue_ID(), get_TrxName());	}

	/** Set Fuel Issue.
		@param TF_Fuel_Issue_ID Fuel Issue	  */
	public void setTF_Fuel_Issue_ID (int TF_Fuel_Issue_ID)
	{
		if (TF_Fuel_Issue_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_Fuel_Issue_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_Fuel_Issue_ID, Integer.valueOf(TF_Fuel_Issue_ID));
	}

	/** Get Fuel Issue.
		@return Fuel Issue	  */
	public int getTF_Fuel_Issue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_Fuel_Issue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_TF_Fuel_Issue getTF_FuelIssueReplace() throws RuntimeException
    {
		return (I_TF_Fuel_Issue)MTable.get(getCtx(), I_TF_Fuel_Issue.Table_Name)
			.getPO(getTF_FuelIssueReplace_ID(), get_TrxName());	}

	/** Set Replaced from.
		@param TF_FuelIssueReplace_ID Replaced from	  */
	public void setTF_FuelIssueReplace_ID (int TF_FuelIssueReplace_ID)
	{
		if (TF_FuelIssueReplace_ID < 1) 
			set_Value (COLUMNNAME_TF_FuelIssueReplace_ID, null);
		else 
			set_Value (COLUMNNAME_TF_FuelIssueReplace_ID, Integer.valueOf(TF_FuelIssueReplace_ID));
	}

	/** Get Replaced from.
		@return Replaced from	  */
	public int getTF_FuelIssueReplace_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_FuelIssueReplace_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}