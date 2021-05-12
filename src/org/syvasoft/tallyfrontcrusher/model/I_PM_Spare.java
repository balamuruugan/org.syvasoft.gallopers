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

/** Generated Interface for PM_Spare
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_PM_Spare 
{

    /** TableName=PM_Spare */
    public static final String Table_Name = "PM_Spare";

    /** AD_Table_ID=1000328 */
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

    /** Column name IssuedAt */
    public static final String COLUMNNAME_IssuedAt = "IssuedAt";

	/** Set Issued at	  */
	public void setIssuedAt (BigDecimal IssuedAt);

	/** Get Issued at	  */
	public BigDecimal getIssuedAt();

    /** Column name LastMeter */
    public static final String COLUMNNAME_LastMeter = "LastMeter";

	/** Set Last Meter	  */
	public void setLastMeter (BigDecimal LastMeter);

	/** Get Last Meter	  */
	public BigDecimal getLastMeter();

    /** Column name LifeUsed */
    public static final String COLUMNNAME_LifeUsed = "LifeUsed";

	/** Set Life Used	  */
	public void setLifeUsed (BigDecimal LifeUsed);

	/** Get Life Used	  */
	public BigDecimal getLifeUsed();

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

    /** Column name MovementDate */
    public static final String COLUMNNAME_MovementDate = "MovementDate";

	/** Set Movement Date.
	  * Date a product was moved in or out of inventory
	  */
	public void setMovementDate (Timestamp MovementDate);

	/** Get Movement Date.
	  * Date a product was moved in or out of inventory
	  */
	public Timestamp getMovementDate();

    /** Column name PM_Machinery_ID */
    public static final String COLUMNNAME_PM_Machinery_ID = "PM_Machinery_ID";

	/** Set Machinery	  */
	public void setPM_Machinery_ID (int PM_Machinery_ID);

	/** Get Machinery	  */
	public int getPM_Machinery_ID();

	public I_PM_Machinery getPM_Machinery() throws RuntimeException;

    /** Column name PM_Spare_ID */
    public static final String COLUMNNAME_PM_Spare_ID = "PM_Spare_ID";

	/** Set Spare	  */
	public void setPM_Spare_ID (int PM_Spare_ID);

	/** Get Spare	  */
	public int getPM_Spare_ID();

    /** Column name PM_Spare_UU */
    public static final String COLUMNNAME_PM_Spare_UU = "PM_Spare_UU";

	/** Set PM_Spare_UU	  */
	public void setPM_Spare_UU (String PM_Spare_UU);

	/** Get PM_Spare_UU	  */
	public String getPM_Spare_UU();

    /** Column name PM_SpareGroup_ID */
    public static final String COLUMNNAME_PM_SpareGroup_ID = "PM_SpareGroup_ID";

	/** Set Spare Group	  */
	public void setPM_SpareGroup_ID (int PM_SpareGroup_ID);

	/** Get Spare Group	  */
	public int getPM_SpareGroup_ID();

	public I_PM_SpareGroup getPM_SpareGroup() throws RuntimeException;

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

    /** Column name RemainingLife */
    public static final String COLUMNNAME_RemainingLife = "RemainingLife";

	/** Set Remaining Life	  */
	public void setRemainingLife (BigDecimal RemainingLife);

	/** Get Remaining Life	  */
	public BigDecimal getRemainingLife();

    /** Column name ReplacedDate */
    public static final String COLUMNNAME_ReplacedDate = "ReplacedDate";

	/** Set Replaced Date	  */
	public void setReplacedDate (Timestamp ReplacedDate);

	/** Get Replaced Date	  */
	public Timestamp getReplacedDate();

    /** Column name SpareLife_UOM_ID */
    public static final String COLUMNNAME_SpareLife_UOM_ID = "SpareLife_UOM_ID";

	/** Set Spare Life UOM	  */
	public void setSpareLife_UOM_ID (int SpareLife_UOM_ID);

	/** Get Spare Life UOM	  */
	public int getSpareLife_UOM_ID();

	public org.compiere.model.I_C_UOM getSpareLife_UOM() throws RuntimeException;

    /** Column name SpareLIfeGreenLimit */
    public static final String COLUMNNAME_SpareLIfeGreenLimit = "SpareLIfeGreenLimit";

	/** Set Spare LIfe Green Limit	  */
	public void setSpareLIfeGreenLimit (BigDecimal SpareLIfeGreenLimit);

	/** Get Spare LIfe Green Limit	  */
	public BigDecimal getSpareLIfeGreenLimit();

    /** Column name SpareLIfeYellowLimit */
    public static final String COLUMNNAME_SpareLIfeYellowLimit = "SpareLIfeYellowLimit";

	/** Set Spare LIfe Yellow Limit	  */
	public void setSpareLIfeYellowLimit (BigDecimal SpareLIfeYellowLimit);

	/** Get Spare LIfe Yellow Limit	  */
	public BigDecimal getSpareLIfeYellowLimit();

    /** Column name SpareStatus */
    public static final String COLUMNNAME_SpareStatus = "SpareStatus";

	/** Set Spare Status	  */
	public void setSpareStatus (String SpareStatus);

	/** Get Spare Status	  */
	public String getSpareStatus();

    /** Column name SpareStdLife */
    public static final String COLUMNNAME_SpareStdLife = "SpareStdLife";

	/** Set Spare Standard Life.
	  * Spare Standard Life (in Spare Life UOM)
	  */
	public void setSpareStdLife (BigDecimal SpareStdLife);

	/** Get Spare Standard Life.
	  * Spare Standard Life (in Spare Life UOM)
	  */
	public BigDecimal getSpareStdLife();

    /** Column name TF_Fuel_Issue_ID */
    public static final String COLUMNNAME_TF_Fuel_Issue_ID = "TF_Fuel_Issue_ID";

	/** Set Fuel Issue	  */
	public void setTF_Fuel_Issue_ID (int TF_Fuel_Issue_ID);

	/** Get Fuel Issue	  */
	public int getTF_Fuel_Issue_ID();

	public I_TF_Fuel_Issue getTF_Fuel_Issue() throws RuntimeException;

    /** Column name TF_FuelIssueReplace_ID */
    public static final String COLUMNNAME_TF_FuelIssueReplace_ID = "TF_FuelIssueReplace_ID";

	/** Set Replaced from	  */
	public void setTF_FuelIssueReplace_ID (int TF_FuelIssueReplace_ID);

	/** Get Replaced from	  */
	public int getTF_FuelIssueReplace_ID();

	public I_TF_Fuel_Issue getTF_FuelIssueReplace() throws RuntimeException;

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
