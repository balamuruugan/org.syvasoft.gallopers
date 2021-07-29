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

/** Generated Interface for TF_LumpSumRent_Config
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_TF_LumpSumRent_Config 
{

    /** TableName=TF_LumpSumRent_Config */
    public static final String Table_Name = "TF_LumpSumRent_Config";

    /** AD_Table_ID=1000283 */
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

    /** Column name C_Tax_ID */
    public static final String COLUMNNAME_C_Tax_ID = "C_Tax_ID";

	/** Set Tax.
	  * Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID);

	/** Get Tax.
	  * Tax identifier
	  */
	public int getC_Tax_ID();

	public org.compiere.model.I_C_Tax getC_Tax() throws RuntimeException;

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

    /** Column name FreightPrice */
    public static final String COLUMNNAME_FreightPrice = "FreightPrice";

	/** Set Freight Rate	  */
	public void setFreightPrice (BigDecimal FreightPrice);

	/** Get Freight Rate	  */
	public BigDecimal getFreightPrice();

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

    /** Column name IsTaxIncluded */
    public static final String COLUMNNAME_IsTaxIncluded = "IsTaxIncluded";

	/** Set Price includes Tax.
	  * Tax is included in the price 
	  */
	public void setIsTaxIncluded (boolean IsTaxIncluded);

	/** Get Price includes Tax.
	  * Tax is included in the price 
	  */
	public boolean isTaxIncluded();

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

    /** Column name MaxKM */
    public static final String COLUMNNAME_MaxKM = "MaxKM";

	/** Set MaxKM	  */
	public void setMaxKM (int MaxKM);

	/** Get MaxKM	  */
	public int getMaxKM();

    /** Column name MinKM */
    public static final String COLUMNNAME_MinKM = "MinKM";

	/** Set MinKM	  */
	public void setMinKM (int MinKM);

	/** Get MinKM	  */
	public int getMinKM();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name ratekm */
    public static final String COLUMNNAME_ratekm = "ratekm";

	/** Set Rate / KM	  */
	public void setratekm (BigDecimal ratekm);

	/** Get Rate / KM	  */
	public BigDecimal getratekm();

    /** Column name RateMT */
    public static final String COLUMNNAME_RateMT = "RateMT";

	/** Set Rate / MT	  */
	public void setRateMT (BigDecimal RateMT);

	/** Get Rate / MT	  */
	public BigDecimal getRateMT();

    /** Column name RateMTKM */
    public static final String COLUMNNAME_RateMTKM = "RateMTKM";

	/** Set Rate / MT / KM	  */
	public void setRateMTKM (BigDecimal RateMTKM);

	/** Get Rate / MT / KM	  */
	public BigDecimal getRateMTKM();

    /** Column name Rent_Amt */
    public static final String COLUMNNAME_Rent_Amt = "Rent_Amt";

	/** Set Rent (Amount)	  */
	public void setRent_Amt (BigDecimal Rent_Amt);

	/** Get Rent (Amount)	  */
	public BigDecimal getRent_Amt();

    /** Column name RentMargin */
    public static final String COLUMNNAME_RentMargin = "RentMargin";

	/** Set Rent Margin	  */
	public void setRentMargin (BigDecimal RentMargin);

	/** Get Rent Margin	  */
	public BigDecimal getRentMargin();

    /** Column name TF_Destination_ID */
    public static final String COLUMNNAME_TF_Destination_ID = "TF_Destination_ID";

	/** Set Destination	  */
	public void setTF_Destination_ID (int TF_Destination_ID);

	/** Get Destination	  */
	public int getTF_Destination_ID();

	public I_TF_Destination getTF_Destination() throws RuntimeException;

    /** Column name TF_LumpSumRent_Config_ID */
    public static final String COLUMNNAME_TF_LumpSumRent_Config_ID = "TF_LumpSumRent_Config_ID";

	/** Set TF_LumpSumRent_Config	  */
	public void setTF_LumpSumRent_Config_ID (int TF_LumpSumRent_Config_ID);

	/** Get TF_LumpSumRent_Config	  */
	public int getTF_LumpSumRent_Config_ID();

    /** Column name TF_LumpSumRent_Config_UU */
    public static final String COLUMNNAME_TF_LumpSumRent_Config_UU = "TF_LumpSumRent_Config_UU";

	/** Set TF_LumpSumRent_Config_UU	  */
	public void setTF_LumpSumRent_Config_UU (String TF_LumpSumRent_Config_UU);

	/** Get TF_LumpSumRent_Config_UU	  */
	public String getTF_LumpSumRent_Config_UU();

    /** Column name TF_TOrder_ID */
    public static final String COLUMNNAME_TF_TOrder_ID = "TF_TOrder_ID";

	/** Set TF_Torder	  */
	public void setTF_TOrder_ID (int TF_TOrder_ID);

	/** Get TF_Torder	  */
	public int getTF_TOrder_ID();

	public I_TF_TOrder getTF_TOrder() throws RuntimeException;

    /** Column name TF_VehicleType_ID */
    public static final String COLUMNNAME_TF_VehicleType_ID = "TF_VehicleType_ID";

	/** Set Vehicle Type	  */
	public void setTF_VehicleType_ID (int TF_VehicleType_ID);

	/** Get Vehicle Type	  */
	public int getTF_VehicleType_ID();

	public I_TF_VehicleType getTF_VehicleType() throws RuntimeException;

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

    /** Column name Vendor_ID */
    public static final String COLUMNNAME_Vendor_ID = "Vendor_ID";

	/** Set Vendor.
	  * The Vendor of the product/service
	  */
	public void setVendor_ID (int Vendor_ID);

	/** Get Vendor.
	  * The Vendor of the product/service
	  */
	public int getVendor_ID();

	public org.compiere.model.I_C_BPartner getVendor() throws RuntimeException;
}
