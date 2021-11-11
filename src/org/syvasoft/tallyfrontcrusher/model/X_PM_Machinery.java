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
import org.compiere.util.KeyNamePair;

/** Generated Model for PM_Machinery
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_PM_Machinery extends PO implements I_PM_Machinery, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20211111L;

    /** Standard Constructor */
    public X_PM_Machinery (Properties ctx, int PM_Machinery_ID, String trxName)
    {
      super (ctx, PM_Machinery_ID, trxName);
      /** if (PM_Machinery_ID == 0)
        {
			setisIoTLinked (false);
// N
			setMachineryNo (null);
			setPM_Machinery_ID (0);
			setPM_MachineryType_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PM_Machinery (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PM_Machinery[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_Activity getC_Activity() throws RuntimeException
    {
		return (org.compiere.model.I_C_Activity)MTable.get(getCtx(), org.compiere.model.I_C_Activity.Table_Name)
			.getPO(getC_Activity_ID(), get_TrxName());	}

	/** Set Activity.
		@param C_Activity_ID 
		Business Activity
	  */
	public void setC_Activity_ID (int C_Activity_ID)
	{
		if (C_Activity_ID < 1) 
			set_Value (COLUMNNAME_C_Activity_ID, null);
		else 
			set_Value (COLUMNNAME_C_Activity_ID, Integer.valueOf(C_Activity_ID));
	}

	/** Get Activity.
		@return Business Activity
	  */
	public int getC_Activity_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Activity_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
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

	/** Set IoT Linked.
		@param isIoTLinked IoT Linked	  */
	public void setisIoTLinked (boolean isIoTLinked)
	{
		set_Value (COLUMNNAME_isIoTLinked, Boolean.valueOf(isIoTLinked));
	}

	/** Get IoT Linked.
		@return IoT Linked	  */
	public boolean isIoTLinked () 
	{
		Object oo = get_Value(COLUMNNAME_isIoTLinked);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Summary Level.
		@param IsSummary 
		This is a summary entity
	  */
	public void setIsSummary (boolean IsSummary)
	{
		set_Value (COLUMNNAME_IsSummary, Boolean.valueOf(IsSummary));
	}

	/** Get Summary Level.
		@return This is a summary entity
	  */
	public boolean isSummary () 
	{
		Object oo = get_Value(COLUMNNAME_IsSummary);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Machinery No.
		@param MachineryNo Machinery No	  */
	public void setMachineryNo (String MachineryNo)
	{
		set_Value (COLUMNNAME_MachineryNo, MachineryNo);
	}

	/** Get Machinery No.
		@return Machinery No	  */
	public String getMachineryNo () 
	{
		return (String)get_Value(COLUMNNAME_MachineryNo);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getMachineryNo());
    }

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

	/** Set PM_Machinery_UU.
		@param PM_Machinery_UU PM_Machinery_UU	  */
	public void setPM_Machinery_UU (String PM_Machinery_UU)
	{
		set_ValueNoCheck (COLUMNNAME_PM_Machinery_UU, PM_Machinery_UU);
	}

	/** Get PM_Machinery_UU.
		@return PM_Machinery_UU	  */
	public String getPM_Machinery_UU () 
	{
		return (String)get_Value(COLUMNNAME_PM_Machinery_UU);
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

	/** Set Purchase Date.
		@param PurchaseDate Purchase Date	  */
	public void setPurchaseDate (Timestamp PurchaseDate)
	{
		set_Value (COLUMNNAME_PurchaseDate, PurchaseDate);
	}

	/** Get Purchase Date.
		@return Purchase Date	  */
	public Timestamp getPurchaseDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_PurchaseDate);
	}

	/** Jack Hammer = J */
	public static final String QUARRYPRODUCTIONTYPE_JackHammer = "J";
	/** Graweller = G */
	public static final String QUARRYPRODUCTIONTYPE_Graweller = "G";
	/** Set Quarry Production Type.
		@param QuarryProductionType Quarry Production Type	  */
	public void setQuarryProductionType (String QuarryProductionType)
	{

		set_Value (COLUMNNAME_QuarryProductionType, QuarryProductionType);
	}

	/** Get Quarry Production Type.
		@return Quarry Production Type	  */
	public String getQuarryProductionType () 
	{
		return (String)get_Value(COLUMNNAME_QuarryProductionType);
	}

	public I_TF_RentedVehicle getTF_RentedVehicle() throws RuntimeException
    {
		return (I_TF_RentedVehicle)MTable.get(getCtx(), I_TF_RentedVehicle.Table_Name)
			.getPO(getTF_RentedVehicle_ID(), get_TrxName());	}

	/** Set Rented Vehicle.
		@param TF_RentedVehicle_ID Rented Vehicle	  */
	public void setTF_RentedVehicle_ID (int TF_RentedVehicle_ID)
	{
		if (TF_RentedVehicle_ID < 1) 
			set_Value (COLUMNNAME_TF_RentedVehicle_ID, null);
		else 
			set_Value (COLUMNNAME_TF_RentedVehicle_ID, Integer.valueOf(TF_RentedVehicle_ID));
	}

	/** Get Rented Vehicle.
		@return Rented Vehicle	  */
	public int getTF_RentedVehicle_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_RentedVehicle_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public org.compiere.model.I_C_ElementValue getUser1() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getUser1_ID(), get_TrxName());	}

	/** Set Department.
		@param User1_ID 
		User defined list element #1
	  */
	public void setUser1_ID (int User1_ID)
	{
		if (User1_ID < 1) 
			set_Value (COLUMNNAME_User1_ID, null);
		else 
			set_Value (COLUMNNAME_User1_ID, Integer.valueOf(User1_ID));
	}

	/** Get Department.
		@return User defined list element #1
	  */
	public int getUser1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_User1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}