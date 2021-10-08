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

/** Generated Interface for PLC_Reading_Log
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_PLC_Reading_Log 
{

    /** TableName=PLC_Reading_Log */
    public static final String Table_Name = "PLC_Reading_Log";

    /** AD_Table_ID=1000369 */
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

    /** Column name DateLog */
    public static final String COLUMNNAME_DateLog = "DateLog";

	/** Set Log Date	  */
	public void setDateLog (Timestamp DateLog);

	/** Get Log Date	  */
	public Timestamp getDateLog();

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

    /** Column name PLC_Modbus_Config_ID */
    public static final String COLUMNNAME_PLC_Modbus_Config_ID = "PLC_Modbus_Config_ID";

	/** Set PLC Modbus Configuration	  */
	public void setPLC_Modbus_Config_ID (int PLC_Modbus_Config_ID);

	/** Get PLC Modbus Configuration	  */
	public int getPLC_Modbus_Config_ID();

	public I_PLC_Modbus_Config getPLC_Modbus_Config() throws RuntimeException;

    /** Column name PLC_Reading_Log_ID */
    public static final String COLUMNNAME_PLC_Reading_Log_ID = "PLC_Reading_Log_ID";

	/** Set PLC_Reading_Log	  */
	public void setPLC_Reading_Log_ID (int PLC_Reading_Log_ID);

	/** Get PLC_Reading_Log	  */
	public int getPLC_Reading_Log_ID();

    /** Column name PLC_Reading_Log_UU */
    public static final String COLUMNNAME_PLC_Reading_Log_UU = "PLC_Reading_Log_UU";

	/** Set PLC_Reading_Log_UU	  */
	public void setPLC_Reading_Log_UU (String PLC_Reading_Log_UU);

	/** Get PLC_Reading_Log_UU	  */
	public String getPLC_Reading_Log_UU();

    /** Column name PM_Machinery_ID */
    public static final String COLUMNNAME_PM_Machinery_ID = "PM_Machinery_ID";

	/** Set Machinery	  */
	public void setPM_Machinery_ID (int PM_Machinery_ID);

	/** Get Machinery	  */
	public int getPM_Machinery_ID();

	public I_PM_Machinery getPM_Machinery() throws RuntimeException;

    /** Column name Result */
    public static final String COLUMNNAME_Result = "Result";

	/** Set Result.
	  * Result of the action taken
	  */
	public void setResult (String Result);

	/** Get Result.
	  * Result of the action taken
	  */
	public String getResult();

    /** Column name Running_Meter */
    public static final String COLUMNNAME_Running_Meter = "Running_Meter";

	/** Set Running Meter	  */
	public void setRunning_Meter (BigDecimal Running_Meter);

	/** Get Running Meter	  */
	public BigDecimal getRunning_Meter();

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
