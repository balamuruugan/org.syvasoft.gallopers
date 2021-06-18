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

/** Generated Interface for TF_DrillingEntry
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_TF_DrillingEntry 
{

    /** TableName=TF_DrillingEntry */
    public static final String Table_Name = "TF_DrillingEntry";

    /** AD_Table_ID=1000321 */
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

    /** Column name C_Invoice_ID */
    public static final String COLUMNNAME_C_Invoice_ID = "C_Invoice_ID";

	/** Set Invoice.
	  * Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID);

	/** Get Invoice.
	  * Invoice Identifier
	  */
	public int getC_Invoice_ID();

	public org.compiere.model.I_C_Invoice getC_Invoice() throws RuntimeException;

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

    /** Column name DateAcct */
    public static final String COLUMNNAME_DateAcct = "DateAcct";

	/** Set Account Date.
	  * Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct);

	/** Get Account Date.
	  * Accounting Date
	  */
	public Timestamp getDateAcct();

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

    /** Column name DocStatus */
    public static final String COLUMNNAME_DocStatus = "DocStatus";

	/** Set Document Status.
	  * The current status of the document
	  */
	public void setDocStatus (String DocStatus);

	/** Get Document Status.
	  * The current status of the document
	  */
	public String getDocStatus();

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

    /** Column name DrillingCost */
    public static final String COLUMNNAME_DrillingCost = "DrillingCost";

	/** Set Drilling Cost	  */
	public void setDrillingCost (BigDecimal DrillingCost);

	/** Get Drilling Cost	  */
	public BigDecimal getDrillingCost();

    /** Column name Feet */
    public static final String COLUMNNAME_Feet = "Feet";

	/** Set Feet	  */
	public void setFeet (BigDecimal Feet);

	/** Get Feet	  */
	public BigDecimal getFeet();

    /** Column name FeetRate */
    public static final String COLUMNNAME_FeetRate = "FeetRate";

	/** Set Rate / Feet	  */
	public void setFeetRate (BigDecimal FeetRate);

	/** Get Rate / Feet	  */
	public BigDecimal getFeetRate();

    /** Column name Holes */
    public static final String COLUMNNAME_Holes = "Holes";

	/** Set Holes	  */
	public void setHoles (BigDecimal Holes);

	/** Get Holes	  */
	public BigDecimal getHoles();

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

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name TF_DrillingBlasting_ID */
    public static final String COLUMNNAME_TF_DrillingBlasting_ID = "TF_DrillingBlasting_ID";

	/** Set TF_DrillingBlasting	  */
	public void setTF_DrillingBlasting_ID (int TF_DrillingBlasting_ID);

	/** Get TF_DrillingBlasting	  */
	public int getTF_DrillingBlasting_ID();

	public I_TF_DrillingBlasting getTF_DrillingBlasting() throws RuntimeException;

    /** Column name TF_DrillingEntry_ID */
    public static final String COLUMNNAME_TF_DrillingEntry_ID = "TF_DrillingEntry_ID";

	/** Set Drilling Entry	  */
	public void setTF_DrillingEntry_ID (int TF_DrillingEntry_ID);

	/** Get Drilling Entry	  */
	public int getTF_DrillingEntry_ID();

    /** Column name TF_DrillingEntry_UU */
    public static final String COLUMNNAME_TF_DrillingEntry_UU = "TF_DrillingEntry_UU";

	/** Set TF_DrillingEntry_UU	  */
	public void setTF_DrillingEntry_UU (String TF_DrillingEntry_UU);

	/** Get TF_DrillingEntry_UU	  */
	public String getTF_DrillingEntry_UU();

    /** Column name TF_Quarry_ID */
    public static final String COLUMNNAME_TF_Quarry_ID = "TF_Quarry_ID";

	/** Set Quarry	  */
	public void setTF_Quarry_ID (int TF_Quarry_ID);

	/** Get Quarry	  */
	public int getTF_Quarry_ID();

	public I_TF_Quarry getTF_Quarry() throws RuntimeException;

    /** Column name TF_TripSheet_ID */
    public static final String COLUMNNAME_TF_TripSheet_ID = "TF_TripSheet_ID";

	/** Set Trip Sheet	  */
	public void setTF_TripSheet_ID (int TF_TripSheet_ID);

	/** Get Trip Sheet	  */
	public int getTF_TripSheet_ID();

	public I_TF_TripSheet getTF_TripSheet() throws RuntimeException;

    /** Column name TotalFeet */
    public static final String COLUMNNAME_TotalFeet = "TotalFeet";

	/** Set Total Feet	  */
	public void setTotalFeet (BigDecimal TotalFeet);

	/** Get Total Feet	  */
	public BigDecimal getTotalFeet();

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
}
