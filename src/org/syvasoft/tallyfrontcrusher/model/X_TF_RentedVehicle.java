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

/** Generated Model for TF_RentedVehicle
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_RentedVehicle extends PO implements I_TF_RentedVehicle, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20211125L;

    /** Standard Constructor */
    public X_TF_RentedVehicle (Properties ctx, int TF_RentedVehicle_ID, String trxName)
    {
      super (ctx, TF_RentedVehicle_ID, trxName);
      /** if (TF_RentedVehicle_ID == 0)
        {
			setC_UOM_ID (0);
			setCapacity_CFT (Env.ZERO);
// 0
			setCapacity_Unit (Env.ZERO);
// 0
			setIsOwnVehicle (false);
// N
			setM_Product_Category_ID (0);
			setTF_RentedVehicle_ID (0);
			setVehicleNo (null);
        } */
    }

    /** Load Constructor */
    public X_TF_RentedVehicle (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_RentedVehicle[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public org.compiere.model.I_C_BPartner getC_BPartnerDriver() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartnerDriver_ID(), get_TrxName());	}

	/** Set Driver.
		@param C_BPartnerDriver_ID Driver	  */
	public void setC_BPartnerDriver_ID (int C_BPartnerDriver_ID)
	{
		if (C_BPartnerDriver_ID < 1) 
			set_Value (COLUMNNAME_C_BPartnerDriver_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartnerDriver_ID, Integer.valueOf(C_BPartnerDriver_ID));
	}

	/** Get Driver.
		@return Driver	  */
	public int getC_BPartnerDriver_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartnerDriver_ID);
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

	/** Set Capacity (CFT).
		@param Capacity_CFT Capacity (CFT)	  */
	public void setCapacity_CFT (BigDecimal Capacity_CFT)
	{
		set_Value (COLUMNNAME_Capacity_CFT, Capacity_CFT);
	}

	/** Get Capacity (CFT).
		@return Capacity (CFT)	  */
	public BigDecimal getCapacity_CFT () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Capacity_CFT);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Capacity (Unit).
		@param Capacity_Unit Capacity (Unit)	  */
	public void setCapacity_Unit (BigDecimal Capacity_Unit)
	{
		set_Value (COLUMNNAME_Capacity_Unit, Capacity_Unit);
	}

	/** Get Capacity (Unit).
		@return Capacity (Unit)	  */
	public BigDecimal getCapacity_Unit () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Capacity_Unit);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Tareweight Expiry Date.
		@param DateTareweightExpiry Tareweight Expiry Date	  */
	public void setDateTareweightExpiry (Timestamp DateTareweightExpiry)
	{
		set_Value (COLUMNNAME_DateTareweightExpiry, DateTareweightExpiry);
	}

	/** Get Tareweight Expiry Date.
		@return Tareweight Expiry Date	  */
	public Timestamp getDateTareweightExpiry () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTareweightExpiry);
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

	/** Set Own Vehicle.
		@param IsOwnVehicle Own Vehicle	  */
	public void setIsOwnVehicle (boolean IsOwnVehicle)
	{
		set_Value (COLUMNNAME_IsOwnVehicle, Boolean.valueOf(IsOwnVehicle));
	}

	/** Get Own Vehicle.
		@return Own Vehicle	  */
	public boolean isOwnVehicle () 
	{
		Object oo = get_Value(COLUMNNAME_IsOwnVehicle);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Transporter.
		@param IsTransporter Transporter	  */
	public void setIsTransporter (boolean IsTransporter)
	{
		set_Value (COLUMNNAME_IsTransporter, Boolean.valueOf(IsTransporter));
	}

	/** Get Transporter.
		@return Transporter	  */
	public boolean isTransporter () 
	{
		Object oo = get_Value(COLUMNNAME_IsTransporter);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_M_Product_Category getM_Product_Category() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product_Category)MTable.get(getCtx(), org.compiere.model.I_M_Product_Category.Table_Name)
			.getPO(getM_Product_Category_ID(), get_TrxName());	}

	/** Set Product Category.
		@param M_Product_Category_ID 
		Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID)
	{
		if (M_Product_Category_ID < 1) 
			set_Value (COLUMNNAME_M_Product_Category_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_Category_ID, Integer.valueOf(M_Product_Category_ID));
	}

	/** Get Product Category.
		@return Category of a Product
	  */
	public int getM_Product_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Category_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Old Tareweight.
		@param OldTareweight Old Tareweight	  */
	public void setOldTareweight (BigDecimal OldTareweight)
	{
		set_Value (COLUMNNAME_OldTareweight, OldTareweight);
	}

	/** Get Old Tareweight.
		@return Old Tareweight	  */
	public BigDecimal getOldTareweight () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_OldTareweight);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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
			set_Value (COLUMNNAME_PM_MachineryType_ID, null);
		else 
			set_Value (COLUMNNAME_PM_MachineryType_ID, Integer.valueOf(PM_MachineryType_ID));
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

	/** Set Rent Configuration.
		@param RequireRentConfig Rent Configuration	  */
	public void setRequireRentConfig (boolean RequireRentConfig)
	{
		set_Value (COLUMNNAME_RequireRentConfig, Boolean.valueOf(RequireRentConfig));
	}

	/** Get Rent Configuration.
		@return Rent Configuration	  */
	public boolean isRequireRentConfig () 
	{
		Object oo = get_Value(COLUMNNAME_RequireRentConfig);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Tare Weight (Kg).
		@param TareWeight Tare Weight (Kg)	  */
	public void setTareWeight (BigDecimal TareWeight)
	{
		set_Value (COLUMNNAME_TareWeight, TareWeight);
	}

	/** Get Tare Weight (Kg).
		@return Tare Weight (Kg)	  */
	public BigDecimal getTareWeight () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TareWeight);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Tare Weight Time.
		@param TareWeightTime Tare Weight Time	  */
	public void setTareWeightTime (Timestamp TareWeightTime)
	{
		set_Value (COLUMNNAME_TareWeightTime, TareWeightTime);
	}

	/** Get Tare Weight Time.
		@return Tare Weight Time	  */
	public Timestamp getTareWeightTime () 
	{
		return (Timestamp)get_Value(COLUMNNAME_TareWeightTime);
	}

	/** Set Rented Vehicle.
		@param TF_RentedVehicle_ID Rented Vehicle	  */
	public void setTF_RentedVehicle_ID (int TF_RentedVehicle_ID)
	{
		if (TF_RentedVehicle_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_RentedVehicle_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_RentedVehicle_ID, Integer.valueOf(TF_RentedVehicle_ID));
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

	/** Set TF_RentedVehicle_UU.
		@param TF_RentedVehicle_UU TF_RentedVehicle_UU	  */
	public void setTF_RentedVehicle_UU (String TF_RentedVehicle_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_RentedVehicle_UU, TF_RentedVehicle_UU);
	}

	/** Get TF_RentedVehicle_UU.
		@return TF_RentedVehicle_UU	  */
	public String getTF_RentedVehicle_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_RentedVehicle_UU);
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

	/** Set Tonnage / Load.
		@param TonnagePerLoad Tonnage / Load	  */
	public void setTonnagePerLoad (BigDecimal TonnagePerLoad)
	{
		set_Value (COLUMNNAME_TonnagePerLoad, TonnagePerLoad);
	}

	/** Get Tonnage / Load.
		@return Tonnage / Load	  */
	public BigDecimal getTonnagePerLoad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TonnagePerLoad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Unit Price.
		@param UnitPrice Unit Price	  */
	public void setUnitPrice (BigDecimal UnitPrice)
	{
		set_Value (COLUMNNAME_UnitPrice, UnitPrice);
	}

	/** Get Unit Price.
		@return Unit Price	  */
	public BigDecimal getUnitPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_UnitPrice);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Vehicle No.
		@param VehicleNo Vehicle No	  */
	public void setVehicleNo (String VehicleNo)
	{
		set_Value (COLUMNNAME_VehicleNo, VehicleNo);
	}

	/** Get Vehicle No.
		@return Vehicle No	  */
	public String getVehicleNo () 
	{
		return (String)get_Value(COLUMNNAME_VehicleNo);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getVehicleNo());
    }

	/** Both = B */
	public static final String VEHICLESOPOTYPE_Both = "B";
	/** Purchase = P */
	public static final String VEHICLESOPOTYPE_Purchase = "P";
	/** Sales = S */
	public static final String VEHICLESOPOTYPE_Sales = "S";
	/** Set Vehicle SO/PO Type.
		@param VehicleSOPOType Vehicle SO/PO Type	  */
	public void setVehicleSOPOType (String VehicleSOPOType)
	{

		set_Value (COLUMNNAME_VehicleSOPOType, VehicleSOPOType);
	}

	/** Get Vehicle SO/PO Type.
		@return Vehicle SO/PO Type	  */
	public String getVehicleSOPOType () 
	{
		return (String)get_Value(COLUMNNAME_VehicleSOPOType);
	}
}