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

/** Generated Interface for TF_WeighmentEntry
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_TF_WeighmentEntry 
{

    /** TableName=TF_WeighmentEntry */
    public static final String Table_Name = "TF_WeighmentEntry";

    /** AD_Table_ID=1000212 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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

    /** Column name Amount */
    public static final String COLUMNNAME_Amount = "Amount";

	/** Set Amount.
	  * Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount);

	/** Get Amount.
	  * Amount in a defined currency
	  */
	public BigDecimal getAmount();

    /** Column name BillingName */
    public static final String COLUMNNAME_BillingName = "BillingName";

	/** Set Billing Name	  */
	public void setBillingName (String BillingName);

	/** Get Billing Name	  */
	public String getBillingName();

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

    /** Column name C_Order_ID */
    public static final String COLUMNNAME_C_Order_ID = "C_Order_ID";

	/** Set Order.
	  * Order
	  */
	public void setC_Order_ID (int C_Order_ID);

	/** Get Order.
	  * Order
	  */
	public int getC_Order_ID();

	public org.compiere.model.I_C_Order getC_Order() throws RuntimeException;

    /** Column name C_OrderLine_ID */
    public static final String COLUMNNAME_C_OrderLine_ID = "C_OrderLine_ID";

	/** Set Sales Order Line.
	  * Sales Order Line
	  */
	public void setC_OrderLine_ID (int C_OrderLine_ID);

	/** Get Sales Order Line.
	  * Sales Order Line
	  */
	public int getC_OrderLine_ID();

	public org.compiere.model.I_C_OrderLine getC_OrderLine() throws RuntimeException;

    /** Column name C_Project_ID */
    public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";

	/** Set Subcontract / Project.
	  * Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID);

	/** Get Subcontract / Project.
	  * Financial Project
	  */
	public int getC_Project_ID();

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException;

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

    /** Column name ChangeAmt */
    public static final String COLUMNNAME_ChangeAmt = "ChangeAmt";

	/** Set ChangeAmt	  */
	public void setChangeAmt (BigDecimal ChangeAmt);

	/** Get ChangeAmt	  */
	public BigDecimal getChangeAmt();

    /** Column name CompletedBy */
    public static final String COLUMNNAME_CompletedBy = "CompletedBy";

	/** Set Completed By	  */
	public void setCompletedBy (String CompletedBy);

	/** Get Completed By	  */
	public String getCompletedBy();

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

    /** Column name DiscountAmount */
    public static final String COLUMNNAME_DiscountAmount = "DiscountAmount";

	/** Set Discount Amount	  */
	public void setDiscountAmount (BigDecimal DiscountAmount);

	/** Get Discount Amount	  */
	public BigDecimal getDiscountAmount();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name DriverName */
    public static final String COLUMNNAME_DriverName = "DriverName";

	/** Set Driver Name	  */
	public void setDriverName (String DriverName);

	/** Get Driver Name	  */
	public String getDriverName();

    /** Column name DriverTips */
    public static final String COLUMNNAME_DriverTips = "DriverTips";

	/** Set Driver Tips	  */
	public void setDriverTips (BigDecimal DriverTips);

	/** Get Driver Tips	  */
	public BigDecimal getDriverTips();

    /** Column name GrossWeight */
    public static final String COLUMNNAME_GrossWeight = "GrossWeight";

	/** Set Gross Weight (Kg)	  */
	public void setGrossWeight (BigDecimal GrossWeight);

	/** Get Gross Weight (Kg)	  */
	public BigDecimal getGrossWeight();

    /** Column name GrossWeightTime */
    public static final String COLUMNNAME_GrossWeightTime = "GrossWeightTime";

	/** Set Gross Weight Time	  */
	public void setGrossWeightTime (Timestamp GrossWeightTime);

	/** Get Gross Weight Time	  */
	public Timestamp getGrossWeightTime();

    /** Column name GSTAmount */
    public static final String COLUMNNAME_GSTAmount = "GSTAmount";

	/** Set GST Amount	  */
	public void setGSTAmount (BigDecimal GSTAmount);

	/** Get GST Amount	  */
	public BigDecimal getGSTAmount();

    /** Column name HasBalance */
    public static final String COLUMNNAME_HasBalance = "HasBalance";

	/** Set Has Balance.
	  * Permit Sales / Non Permit Sales
	  */
	public void setHasBalance (boolean HasBalance);

	/** Get Has Balance.
	  * Permit Sales / Non Permit Sales
	  */
	public boolean isHasBalance();

    /** Column name InvoiceNo */
    public static final String COLUMNNAME_InvoiceNo = "InvoiceNo";

	/** Set Invoice No.
	  * Invoice No generated from weighbridge app
	  */
	public void setInvoiceNo (String InvoiceNo);

	/** Get Invoice No.
	  * Invoice No generated from weighbridge app
	  */
	public String getInvoiceNo();

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

    /** Column name IsManual */
    public static final String COLUMNNAME_IsManual = "IsManual";

	/** Set Manual.
	  * This is a manual process
	  */
	public void setIsManual (boolean IsManual);

	/** Get Manual.
	  * This is a manual process
	  */
	public boolean isManual();

    /** Column name IsPermitSales */
    public static final String COLUMNNAME_IsPermitSales = "IsPermitSales";

	/** Set Permit Sales	  */
	public void setIsPermitSales (boolean IsPermitSales);

	/** Get Permit Sales	  */
	public boolean isPermitSales();

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

    /** Column name M_Product2_ID */
    public static final String COLUMNNAME_M_Product2_ID = "M_Product2_ID";

	/** Set Product 2	  */
	public void setM_Product2_ID (int M_Product2_ID);

	/** Get Product 2	  */
	public int getM_Product2_ID();

	public org.compiere.model.I_M_Product getM_Product2() throws RuntimeException;

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException;

    /** Column name MDPNo */
    public static final String COLUMNNAME_MDPNo = "MDPNo";

	/** Set MDP No	  */
	public void setMDPNo (String MDPNo);

	/** Get MDP No	  */
	public String getMDPNo();

    /** Column name NetWeight */
    public static final String COLUMNNAME_NetWeight = "NetWeight";

	/** Set Net Weight (Kg)	  */
	public void setNetWeight (BigDecimal NetWeight);

	/** Get Net Weight (Kg)	  */
	public BigDecimal getNetWeight();

    /** Column name NetWeightUnit */
    public static final String COLUMNNAME_NetWeightUnit = "NetWeightUnit";

	/** Set Net Weight (Unit)	  */
	public void setNetWeightUnit (BigDecimal NetWeightUnit);

	/** Get Net Weight (Unit)	  */
	public BigDecimal getNetWeightUnit();

    /** Column name NewDestination */
    public static final String COLUMNNAME_NewDestination = "NewDestination";

	/** Set New Destination	  */
	public void setNewDestination (String NewDestination);

	/** Get New Destination	  */
	public String getNewDestination();

    /** Column name NewProduct */
    public static final String COLUMNNAME_NewProduct = "NewProduct";

	/** Set New Product	  */
	public void setNewProduct (String NewProduct);

	/** Get New Product	  */
	public String getNewProduct();

    /** Column name PartyName */
    public static final String COLUMNNAME_PartyName = "PartyName";

	/** Set Party Name	  */
	public void setPartyName (String PartyName);

	/** Get Party Name	  */
	public String getPartyName();

    /** Column name PassPricePerUnit */
    public static final String COLUMNNAME_PassPricePerUnit = "PassPricePerUnit";

	/** Set Pass Price	  */
	public void setPassPricePerUnit (BigDecimal PassPricePerUnit);

	/** Get Pass Price	  */
	public BigDecimal getPassPricePerUnit();

    /** Column name PassQtyIssued */
    public static final String COLUMNNAME_PassQtyIssued = "PassQtyIssued";

	/** Set PassQtyIssued	  */
	public void setPassQtyIssued (BigDecimal PassQtyIssued);

	/** Get PassQtyIssued	  */
	public BigDecimal getPassQtyIssued();

    /** Column name PaymentRule */
    public static final String COLUMNNAME_PaymentRule = "PaymentRule";

	/** Set Payment Rule.
	  * How you pay the invoice
	  */
	public void setPaymentRule (String PaymentRule);

	/** Get Payment Rule.
	  * How you pay the invoice
	  */
	public String getPaymentRule();

    /** Column name PermitIssuedQty */
    public static final String COLUMNNAME_PermitIssuedQty = "PermitIssuedQty";

	/** Set Permit Issued Qty	  */
	public void setPermitIssuedQty (BigDecimal PermitIssuedQty);

	/** Get Permit Issued Qty	  */
	public BigDecimal getPermitIssuedQty();

    /** Column name PermitPassAmount */
    public static final String COLUMNNAME_PermitPassAmount = "PermitPassAmount";

	/** Set Permit Issue Amount	  */
	public void setPermitPassAmount (BigDecimal PermitPassAmount);

	/** Get Permit Issue Amount	  */
	public BigDecimal getPermitPassAmount();

    /** Column name Phone */
    public static final String COLUMNNAME_Phone = "Phone";

	/** Set Phone.
	  * Identifies a telephone number
	  */
	public void setPhone (String Phone);

	/** Get Phone.
	  * Identifies a telephone number
	  */
	public String getPhone();

    /** Column name Price */
    public static final String COLUMNNAME_Price = "Price";

	/** Set Price.
	  * Price
	  */
	public void setPrice (BigDecimal Price);

	/** Get Price.
	  * Price
	  */
	public BigDecimal getPrice();

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

    /** Column name Rent_Amt */
    public static final String COLUMNNAME_Rent_Amt = "Rent_Amt";

	/** Set Rent (Amount)	  */
	public void setRent_Amt (BigDecimal Rent_Amt);

	/** Get Rent (Amount)	  */
	public BigDecimal getRent_Amt();

    /** Column name RoundingOff */
    public static final String COLUMNNAME_RoundingOff = "RoundingOff";

	/** Set Rounding Off	  */
	public void setRoundingOff (BigDecimal RoundingOff);

	/** Get Rounding Off	  */
	public BigDecimal getRoundingOff();

    /** Column name Status */
    public static final String COLUMNNAME_Status = "Status";

	/** Set Status.
	  * Status of the currently running check
	  */
	public void setStatus (String Status);

	/** Get Status.
	  * Status of the currently running check
	  */
	public String getStatus();

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

    /** Column name TenderAmount */
    public static final String COLUMNNAME_TenderAmount = "TenderAmount";

	/** Set Tender Amount.
	  * Tender Amount
	  */
	public void setTenderAmount (BigDecimal TenderAmount);

	/** Get Tender Amount.
	  * Tender Amount
	  */
	public BigDecimal getTenderAmount();

    /** Column name TF_BlueMetal_Type */
    public static final String COLUMNNAME_TF_BlueMetal_Type = "TF_BlueMetal_Type";

	/** Set Production Type	  */
	public void setTF_BlueMetal_Type (String TF_BlueMetal_Type);

	/** Get Production Type	  */
	public String getTF_BlueMetal_Type();

    /** Column name TF_Boulder_Receipt_ID */
    public static final String COLUMNNAME_TF_Boulder_Receipt_ID = "TF_Boulder_Receipt_ID";

	/** Set Boulder Receipt	  */
	public void setTF_Boulder_Receipt_ID (int TF_Boulder_Receipt_ID);

	/** Get Boulder Receipt	  */
	public int getTF_Boulder_Receipt_ID();

	public I_TF_Boulder_Receipt getTF_Boulder_Receipt() throws RuntimeException;

    /** Column name TF_Destination_ID */
    public static final String COLUMNNAME_TF_Destination_ID = "TF_Destination_ID";

	/** Set Destination	  */
	public void setTF_Destination_ID (int TF_Destination_ID);

	/** Get Destination	  */
	public int getTF_Destination_ID();

	public I_TF_Destination getTF_Destination() throws RuntimeException;

    /** Column name TF_ProductionPlant_ID */
    public static final String COLUMNNAME_TF_ProductionPlant_ID = "TF_ProductionPlant_ID";

	/** Set Production Plant	  */
	public void setTF_ProductionPlant_ID (int TF_ProductionPlant_ID);

	/** Get Production Plant	  */
	public int getTF_ProductionPlant_ID();

	public I_TF_ProductionPlant getTF_ProductionPlant() throws RuntimeException;

    /** Column name TF_Quarry_ID */
    public static final String COLUMNNAME_TF_Quarry_ID = "TF_Quarry_ID";

	/** Set Quarry	  */
	public void setTF_Quarry_ID (int TF_Quarry_ID);

	/** Get Quarry	  */
	public int getTF_Quarry_ID();

	public I_TF_Quarry getTF_Quarry() throws RuntimeException;

    /** Column name TF_RentedVehicle_ID */
    public static final String COLUMNNAME_TF_RentedVehicle_ID = "TF_RentedVehicle_ID";

	/** Set Rented Vehicle	  */
	public void setTF_RentedVehicle_ID (int TF_RentedVehicle_ID);

	/** Get Rented Vehicle	  */
	public int getTF_RentedVehicle_ID();

	public I_TF_RentedVehicle getTF_RentedVehicle() throws RuntimeException;

    /** Column name TF_Send_To */
    public static final String COLUMNNAME_TF_Send_To = "TF_Send_To";

	/** Set Send To	  */
	public void setTF_Send_To (String TF_Send_To);

	/** Get Send To	  */
	public String getTF_Send_To();

    /** Column name TF_VehicleType_ID */
    public static final String COLUMNNAME_TF_VehicleType_ID = "TF_VehicleType_ID";

	/** Set Vehicle Type	  */
	public void setTF_VehicleType_ID (int TF_VehicleType_ID);

	/** Get Vehicle Type	  */
	public int getTF_VehicleType_ID();

	public I_TF_VehicleType getTF_VehicleType() throws RuntimeException;

    /** Column name TF_WeighmentEntry_ID */
    public static final String COLUMNNAME_TF_WeighmentEntry_ID = "TF_WeighmentEntry_ID";

	/** Set Weighment Entry	  */
	public void setTF_WeighmentEntry_ID (int TF_WeighmentEntry_ID);

	/** Get Weighment Entry	  */
	public int getTF_WeighmentEntry_ID();

    /** Column name TF_WeighmentEntry_UU */
    public static final String COLUMNNAME_TF_WeighmentEntry_UU = "TF_WeighmentEntry_UU";

	/** Set TF_WeighmentEntry_UU	  */
	public void setTF_WeighmentEntry_UU (String TF_WeighmentEntry_UU);

	/** Get TF_WeighmentEntry_UU	  */
	public String getTF_WeighmentEntry_UU();

    /** Column name TotalAmt */
    public static final String COLUMNNAME_TotalAmt = "TotalAmt";

	/** Set Total Amount.
	  * Total Amount
	  */
	public void setTotalAmt (BigDecimal TotalAmt);

	/** Get Total Amount.
	  * Total Amount
	  */
	public BigDecimal getTotalAmt();

    /** Column name Transporter */
    public static final String COLUMNNAME_Transporter = "Transporter";

	/** Set Transporter	  */
	public void setTransporter (String Transporter);

	/** Get Transporter	  */
	public String getTransporter();

    /** Column name Transporter_ID */
    public static final String COLUMNNAME_Transporter_ID = "Transporter_ID";

	/** Set Transporter	  */
	public void setTransporter_ID (int Transporter_ID);

	/** Get Transporter	  */
	public int getTransporter_ID();

	

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

    /** Column name UserName */
    public static final String COLUMNNAME_UserName = "UserName";

	/** Set User Name	  */
	public void setUserName (String UserName);

	/** Get User Name	  */
	public String getUserName();

    /** Column name VehicleNo */
    public static final String COLUMNNAME_VehicleNo = "VehicleNo";

	/** Set Vehicle No	  */
	public void setVehicleNo (String VehicleNo);

	/** Get Vehicle No	  */
	public String getVehicleNo();

    /** Column name WeighmentEntryType */
    public static final String COLUMNNAME_WeighmentEntryType = "WeighmentEntryType";

	/** Set Type	  */
	public void setWeighmentEntryType (String WeighmentEntryType);

	/** Get Type	  */
	public String getWeighmentEntryType();
}
