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

/** Generated Interface for PM_MachineStmt
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_PM_MachineStmt 
{

    /** TableName=PM_MachineStmt */
    public static final String Table_Name = "PM_MachineStmt";

    /** AD_Table_ID=1000314 */
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

    /** Column name C_Activity_ID */
    public static final String COLUMNNAME_C_Activity_ID = "C_Activity_ID";

	/** Set Activity.
	  * Business Activity
	  */
	public void setC_Activity_ID (int C_Activity_ID);

	/** Get Activity.
	  * Business Activity
	  */
	public int getC_Activity_ID();

	public org.compiere.model.I_C_Activity getC_Activity() throws RuntimeException;

    /** Column name C_ElementValue_ID */
    public static final String COLUMNNAME_C_ElementValue_ID = "C_ElementValue_ID";

	/** Set Account Element.
	  * Account Element
	  */
	public void setC_ElementValue_ID (int C_ElementValue_ID);

	/** Get Account Element.
	  * Account Element
	  */
	public int getC_ElementValue_ID();

	public org.compiere.model.I_C_ElementValue getC_ElementValue() throws RuntimeException;

    /** Column name C_Payment_ID */
    public static final String COLUMNNAME_C_Payment_ID = "C_Payment_ID";

	/** Set Payment.
	  * Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID);

	/** Get Payment.
	  * Payment identifier
	  */
	public int getC_Payment_ID();

	public org.compiere.model.I_C_Payment getC_Payment() throws RuntimeException;

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

    /** Column name Expense */
    public static final String COLUMNNAME_Expense = "Expense";

	/** Set Expense	  */
	public void setExpense (BigDecimal Expense);

	/** Get Expense	  */
	public BigDecimal getExpense();

    /** Column name Income */
    public static final String COLUMNNAME_Income = "Income";

	/** Set Income	  */
	public void setIncome (BigDecimal Income);

	/** Get Income	  */
	public BigDecimal getIncome();

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

    /** Column name PM_Job_ID */
    public static final String COLUMNNAME_PM_Job_ID = "PM_Job_ID";

	/** Set Maintenance Job	  */
	public void setPM_Job_ID (int PM_Job_ID);

	/** Get Maintenance Job	  */
	public int getPM_Job_ID();

	public I_PM_Job getPM_Job() throws RuntimeException;

    /** Column name PM_Machinery_ID */
    public static final String COLUMNNAME_PM_Machinery_ID = "PM_Machinery_ID";

	/** Set Machinery	  */
	public void setPM_Machinery_ID (int PM_Machinery_ID);

	/** Get Machinery	  */
	public int getPM_Machinery_ID();

	public I_PM_Machinery getPM_Machinery() throws RuntimeException;

    /** Column name PM_MachineStmt_ID */
    public static final String COLUMNNAME_PM_MachineStmt_ID = "PM_MachineStmt_ID";

	/** Set Machinery Statement	  */
	public void setPM_MachineStmt_ID (int PM_MachineStmt_ID);

	/** Get Machinery Statement	  */
	public int getPM_MachineStmt_ID();

    /** Column name PM_MachineStmt_UU */
    public static final String COLUMNNAME_PM_MachineStmt_UU = "PM_MachineStmt_UU";

	/** Set PM_MachineStmt_UU	  */
	public void setPM_MachineStmt_UU (String PM_MachineStmt_UU);

	/** Get PM_MachineStmt_UU	  */
	public String getPM_MachineStmt_UU();

    /** Column name Qty */
    public static final String COLUMNNAME_Qty = "Qty";

	/** Set Quantity.
	  * Quantity
	  */
	public void setQty (BigDecimal Qty);

	/** Get Quantity.
	  * Quantity
	  */
	public BigDecimal getQty();

    /** Column name QuarryProductionType */
    public static final String COLUMNNAME_QuarryProductionType = "QuarryProductionType";

	/** Set Quarry Production Type	  */
	public void setQuarryProductionType (String QuarryProductionType);

	/** Get Quarry Production Type	  */
	public String getQuarryProductionType();

    /** Column name Rate */
    public static final String COLUMNNAME_Rate = "Rate";

	/** Set Rate.
	  * Rate or Tax or Exchange
	  */
	public void setRate (BigDecimal Rate);

	/** Get Rate.
	  * Rate or Tax or Exchange
	  */
	public BigDecimal getRate();

    /** Column name TF_Fuel_Issue_ID */
    public static final String COLUMNNAME_TF_Fuel_Issue_ID = "TF_Fuel_Issue_ID";

	/** Set Fuel Issue	  */
	public void setTF_Fuel_Issue_ID (int TF_Fuel_Issue_ID);

	/** Get Fuel Issue	  */
	public int getTF_Fuel_Issue_ID();

	public I_TF_Fuel_Issue getTF_Fuel_Issue() throws RuntimeException;

    /** Column name TF_InstantPettyCash_ID */
    public static final String COLUMNNAME_TF_InstantPettyCash_ID = "TF_InstantPettyCash_ID";

	/** Set Instant Petty Cash Book Entry	  */
	public void setTF_InstantPettyCash_ID (int TF_InstantPettyCash_ID);

	/** Get Instant Petty Cash Book Entry	  */
	public int getTF_InstantPettyCash_ID();

	public I_TF_InstantPettyCash getTF_InstantPettyCash() throws RuntimeException;

    /** Column name TF_TripSheet_ID */
    public static final String COLUMNNAME_TF_TripSheet_ID = "TF_TripSheet_ID";

	/** Set Trip Sheet	  */
	public void setTF_TripSheet_ID (int TF_TripSheet_ID);

	/** Get Trip Sheet	  */
	public int getTF_TripSheet_ID();

	public I_TF_TripSheet getTF_TripSheet() throws RuntimeException;

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

    /** Column name User1_ID */
    public static final String COLUMNNAME_User1_ID = "User1_ID";

	/** Set Department.
	  * User defined list element #1
	  */
	public void setUser1_ID (int User1_ID);

	/** Get Department.
	  * User defined list element #1
	  */
	public int getUser1_ID();

	public org.compiere.model.I_C_ElementValue getUser1() throws RuntimeException;
}
