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

/** Generated Model for TF_LoadingSlip
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_LoadingSlip extends PO implements I_TF_LoadingSlip, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20211029L;

    /** Standard Constructor */
    public X_TF_LoadingSlip (Properties ctx, int TF_LoadingSlip_ID, String trxName)
    {
      super (ctx, TF_LoadingSlip_ID, trxName);
      /** if (TF_LoadingSlip_ID == 0)
        {
			setDocumentNo (null);
			setProcessed (false);
// N
			setProductName (null);
			setStatus (null);
// IP
			setTF_LoadingSlip_ID (0);
			setVehicleNo (null);
        } */
    }

    /** Load Constructor */
    public X_TF_LoadingSlip (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_LoadingSlip[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getAD_User_ID(), get_TrxName());	}

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Loaded Time.
		@param LoadedTime Loaded Time	  */
	public void setLoadedTime (Timestamp LoadedTime)
	{
		set_Value (COLUMNNAME_LoadedTime, LoadedTime);
	}

	/** Get Loaded Time.
		@return Loaded Time	  */
	public Timestamp getLoadedTime () 
	{
		return (Timestamp)get_Value(COLUMNNAME_LoadedTime);
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

	/** Set Product Name.
		@param ProductName 
		Name of the Product
	  */
	public void setProductName (String ProductName)
	{
		set_ValueNoCheck (COLUMNNAME_ProductName, ProductName);
	}

	/** Get Product Name.
		@return Name of the Product
	  */
	public String getProductName () 
	{
		return (String)get_Value(COLUMNNAME_ProductName);
	}

	/** In Progress = IP */
	public static final String STATUS_InProgress = "IP";
	/** Unbilled = CO */
	public static final String STATUS_Unbilled = "CO";
	/** Billed = CL */
	public static final String STATUS_Billed = "CL";
	/** Voided = VO */
	public static final String STATUS_Voided = "VO";
	/** Under Review = UR */
	public static final String STATUS_UnderReview = "UR";
	/** Primary DC void = PV */
	public static final String STATUS_PrimaryDCVoid = "PV";
	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{

		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}

	/** Set Loading Slip.
		@param TF_LoadingSlip_ID Loading Slip	  */
	public void setTF_LoadingSlip_ID (int TF_LoadingSlip_ID)
	{
		if (TF_LoadingSlip_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_LoadingSlip_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_LoadingSlip_ID, Integer.valueOf(TF_LoadingSlip_ID));
	}

	/** Get Loading Slip.
		@return Loading Slip	  */
	public int getTF_LoadingSlip_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_LoadingSlip_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_LoadingSlip_UU.
		@param TF_LoadingSlip_UU TF_LoadingSlip_UU	  */
	public void setTF_LoadingSlip_UU (String TF_LoadingSlip_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_LoadingSlip_UU, TF_LoadingSlip_UU);
	}

	/** Get TF_LoadingSlip_UU.
		@return TF_LoadingSlip_UU	  */
	public String getTF_LoadingSlip_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_LoadingSlip_UU);
	}

	/** Set Vehicle No.
		@param VehicleNo Vehicle No	  */
	public void setVehicleNo (String VehicleNo)
	{
		set_ValueNoCheck (COLUMNNAME_VehicleNo, VehicleNo);
	}

	/** Get Vehicle No.
		@return Vehicle No	  */
	public String getVehicleNo () 
	{
		return (String)get_Value(COLUMNNAME_VehicleNo);
	}
}