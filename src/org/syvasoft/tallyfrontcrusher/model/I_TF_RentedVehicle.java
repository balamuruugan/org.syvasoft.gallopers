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
package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for TF_RentedVehicle
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_TF_RentedVehicle 
{

    /** TableName=TF_RentedVehicle */
    public static final String Table_Name = "TF_RentedVehicle";

    /** AD_Table_ID=1000208 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_BPartnerDriver_ID */
    public static final String COLUMNNAME_C_BPartnerDriver_ID = "C_BPartnerDriver_ID";

	/** Set Driver	  */
	public void setC_BPartnerDriver_ID (int C_BPartnerDriver_ID);

	/** Get Driver	  */
	public int getC_BPartnerDriver_ID();

	public org.compiere.model.I_C_BPartner getC_BPartnerDriver() throws RuntimeException;

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException;

    /** Column name Capacity_CFT */
    public static final String COLUMNNAME_Capacity_CFT = "Capacity_CFT";

	/** Set Capacity (CFT)	  */
	public void setCapacity_CFT (BigDecimal Capacity_CFT);

	/** Get Capacity (CFT)	  */
	public BigDecimal getCapacity_CFT();

    /** Column name Capacity_Unit */
    public static final String COLUMNNAME_Capacity_Unit = "Capacity_Unit";

	/** Set Capacity (Unit)	  */
	public void setCapacity_Unit (BigDecimal Capacity_Unit);

	/** Get Capacity (Unit)	  */
	public BigDecimal getCapacity_Unit();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name DateTareweightExpiry */
    public static final String COLUMNNAME_DateTareweightExpiry = "DateTareweightExpiry";

	/** Set Tareweight Expiry Date	  */
	public void setDateTareweightExpiry (Timestamp DateTareweightExpiry);

	/** Get Tareweight Expiry Date	  */
	public Timestamp getDateTareweightExpiry();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsOwnVehicle */
    public static final String COLUMNNAME_IsOwnVehicle = "IsOwnVehicle";

	/** Set Own Vehicle	  */
	public void setIsOwnVehicle (boolean IsOwnVehicle);

	/** Get Own Vehicle	  */
	public boolean isOwnVehicle();

    /** Column name IsTransporter */
    public static final String COLUMNNAME_IsTransporter = "IsTransporter";

	/** Set Transporter	  */
	public void setIsTransporter (boolean IsTransporter);

	/** Get Transporter	  */
	public boolean isTransporter();

    /** Column name M_Product_Category_ID */
    public static final String COLUMNNAME_M_Product_Category_ID = "M_Product_Category_ID";

	/** Set Product Category.
	  * Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID);

	/** Get Product Category.
	  * Category of a Product
	  */
	public int getM_Product_Category_ID();

	public org.compiere.model.I_M_Product_Category getM_Product_Category() throws RuntimeException;

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

    /** Column name OldTareweight */
    public static final String COLUMNNAME_OldTareweight = "OldTareweight";

	/** Set Old Tareweight	  */
	public void setOldTareweight (BigDecimal OldTareweight);

	/** Get Old Tareweight	  */
	public BigDecimal getOldTareweight();

    /** Column name PM_MachineryType_ID */
    public static final String COLUMNNAME_PM_MachineryType_ID = "PM_MachineryType_ID";

	/** Set Machinery Type	  */
	public void setPM_MachineryType_ID (int PM_MachineryType_ID);

	/** Get Machinery Type	  */
	public int getPM_MachineryType_ID();

	public I_PM_MachineryType getPM_MachineryType() throws RuntimeException;

    /** Column name RequireRentConfig */
    public static final String COLUMNNAME_RequireRentConfig = "RequireRentConfig";

	/** Set Rent Configuration	  */
	public void setRequireRentConfig (boolean RequireRentConfig);

	/** Get Rent Configuration	  */
	public boolean isRequireRentConfig();

    /** Column name TareWeight */
    public static final String COLUMNNAME_TareWeight = "TareWeight";

	/** Set Tare Weight (Kg)	  */
	public void setTareWeight (BigDecimal TareWeight);

	/** Get Tare Weight (Kg)	  */
	public BigDecimal getTareWeight();

    /** Column name TareWeightTime */
    public static final String COLUMNNAME_TareWeightTime = "TareWeightTime";

	/** Set Tare Weight Time	  */
	public void setTareWeightTime (Timestamp TareWeightTime);

	/** Get Tare Weight Time	  */
	public Timestamp getTareWeightTime();

    /** Column name TF_RentedVehicle_ID */
    public static final String COLUMNNAME_TF_RentedVehicle_ID = "TF_RentedVehicle_ID";

	/** Set Rented Vehicle	  */
	public void setTF_RentedVehicle_ID (int TF_RentedVehicle_ID);

	/** Get Rented Vehicle	  */
	public int getTF_RentedVehicle_ID();

    /** Column name TF_RentedVehicle_UU */
    public static final String COLUMNNAME_TF_RentedVehicle_UU = "TF_RentedVehicle_UU";

	/** Set TF_RentedVehicle_UU	  */
	public void setTF_RentedVehicle_UU (String TF_RentedVehicle_UU);

	/** Get TF_RentedVehicle_UU	  */
	public String getTF_RentedVehicle_UU();

    /** Column name TF_VehicleType_ID */
    public static final String COLUMNNAME_TF_VehicleType_ID = "TF_VehicleType_ID";

	/** Set Vehicle Type	  */
	public void setTF_VehicleType_ID (int TF_VehicleType_ID);

	/** Get Vehicle Type	  */
	public int getTF_VehicleType_ID();

	public I_TF_VehicleType getTF_VehicleType() throws RuntimeException;

    /** Column name TonnagePerLoad */
    public static final String COLUMNNAME_TonnagePerLoad = "TonnagePerLoad";

	/** Set Tonnage / Load	  */
	public void setTonnagePerLoad (BigDecimal TonnagePerLoad);

	/** Get Tonnage / Load	  */
	public BigDecimal getTonnagePerLoad();

    /** Column name UnitPrice */
    public static final String COLUMNNAME_UnitPrice = "UnitPrice";

	/** Set Unit Price	  */
	public void setUnitPrice (BigDecimal UnitPrice);

	/** Get Unit Price	  */
	public BigDecimal getUnitPrice();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name VehicleNo */
    public static final String COLUMNNAME_VehicleNo = "VehicleNo";

	/** Set Vehicle No	  */
	public void setVehicleNo (String VehicleNo);

	/** Get Vehicle No	  */
	public String getVehicleNo();

    /** Column name VehicleSOPOType */
    public static final String COLUMNNAME_VehicleSOPOType = "VehicleSOPOType";

	/** Set Vehicle SO/PO Type	  */
	public void setVehicleSOPOType (String VehicleSOPOType);

	/** Get Vehicle SO/PO Type	  */
	public String getVehicleSOPOType();
}
