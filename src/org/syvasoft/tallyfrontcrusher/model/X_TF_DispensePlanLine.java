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

/** Generated Model for TF_DispensePlanLine
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_DispensePlanLine extends PO implements I_TF_DispensePlanLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210616L;

    /** Standard Constructor */
    public X_TF_DispensePlanLine (Properties ctx, int TF_DispensePlanLine_ID, String trxName)
    {
      super (ctx, TF_DispensePlanLine_ID, trxName);
      /** if (TF_DispensePlanLine_ID == 0)
        {
			setArrangeTransport (false);
// N
			setC_Tax_ID (0);
			setC_UOM_ID (0);
			setDateOrdered (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setFreightAmt (Env.ZERO);
			setIsRentInclusive (false);
// N
			setIsRoyaltyPassInclusive (false);
// N
			setLine (0);
			setLineNetAmt (Env.ZERO);
			setM_Warehouse_ID (0);
			setPaymentRule (null);
			setPriceEntered (Env.ZERO);
			setPriority (null);
			setQtyDelivered (Env.ZERO);
			setQtyOrdered (Env.ZERO);
			setScheduleDate (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setTF_DispensePlanLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_DispensePlanLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_DispensePlanLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Allow Carry Forward Previous Day Dispatch Pending.
		@param AllowCarryForward Allow Carry Forward Previous Day Dispatch Pending	  */
	public void setAllowCarryForward (boolean AllowCarryForward)
	{
		set_Value (COLUMNNAME_AllowCarryForward, Boolean.valueOf(AllowCarryForward));
	}

	/** Get Allow Carry Forward Previous Day Dispatch Pending.
		@return Allow Carry Forward Previous Day Dispatch Pending	  */
	public boolean isAllowCarryForward () 
	{
		Object oo = get_Value(COLUMNNAME_AllowCarryForward);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Arrange Transport.
		@param ArrangeTransport Arrange Transport	  */
	public void setArrangeTransport (boolean ArrangeTransport)
	{
		set_Value (COLUMNNAME_ArrangeTransport, Boolean.valueOf(ArrangeTransport));
	}

	/** Get Arrange Transport.
		@return Arrange Transport	  */
	public boolean isArrangeTransport () 
	{
		Object oo = get_Value(COLUMNNAME_ArrangeTransport);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Balance Dispatch Qty.
		@param BalanceDPQty Balance Dispatch Qty	  */
	public void setBalanceDPQty (BigDecimal BalanceDPQty)
	{
		set_Value (COLUMNNAME_BalanceDPQty, BalanceDPQty);
	}

	/** Get Balance Dispatch Qty.
		@return Balance Dispatch Qty	  */
	public BigDecimal getBalanceDPQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_BalanceDPQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Balance Qty.
		@param BalanceQty Balance Qty	  */
	public void setBalanceQty (BigDecimal BalanceQty)
	{
		set_Value (COLUMNNAME_BalanceQty, BalanceQty);
	}

	/** Get Balance Qty.
		@return Balance Qty	  */
	public BigDecimal getBalanceQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_BalanceQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
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

	public org.compiere.model.I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner_Location)MTable.get(getCtx(), org.compiere.model.I_C_BPartner_Location.Table_Name)
			.getPO(getC_BPartner_Location_ID(), get_TrxName());	}

	/** Set Partner Location.
		@param C_BPartner_Location_ID 
		Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
	{
		if (C_BPartner_Location_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_Location_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_Location_ID, Integer.valueOf(C_BPartner_Location_ID));
	}

	/** Get Partner Location.
		@return Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getC_Charge_ID(), get_TrxName());	}

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Charge_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException
    {
		return (org.compiere.model.I_C_Currency)MTable.get(getCtx(), org.compiere.model.I_C_Currency.Table_Name)
			.getPO(getC_Currency_ID(), get_TrxName());	}

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1) 
			set_Value (COLUMNNAME_C_Currency_ID, null);
		else 
			set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_OrderLine getC_OrderLine() throws RuntimeException
    {
		return (org.compiere.model.I_C_OrderLine)MTable.get(getCtx(), org.compiere.model.I_C_OrderLine.Table_Name)
			.getPO(getC_OrderLine_ID(), get_TrxName());	}

	/** Set Sales Order Line.
		@param C_OrderLine_ID 
		Sales Order Line
	  */
	public void setC_OrderLine_ID (int C_OrderLine_ID)
	{
		if (C_OrderLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_OrderLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_OrderLine_ID, Integer.valueOf(C_OrderLine_ID));
	}

	/** Get Sales Order Line.
		@return Sales Order Line
	  */
	public int getC_OrderLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_OrderLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_OrderLine_UU.
		@param C_OrderLine_UU C_OrderLine_UU	  */
	public void setC_OrderLine_UU (String C_OrderLine_UU)
	{
		set_Value (COLUMNNAME_C_OrderLine_UU, C_OrderLine_UU);
	}

	/** Get C_OrderLine_UU.
		@return C_OrderLine_UU	  */
	public String getC_OrderLine_UU () 
	{
		return (String)get_Value(COLUMNNAME_C_OrderLine_UU);
	}

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException
    {
		return (org.compiere.model.I_C_Project)MTable.get(getCtx(), org.compiere.model.I_C_Project.Table_Name)
			.getPO(getC_Project_ID(), get_TrxName());	}

	/** Set Subcontract / Project.
		@param C_Project_ID 
		Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID)
	{
		if (C_Project_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Project_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Project_ID, Integer.valueOf(C_Project_ID));
	}

	/** Get Subcontract / Project.
		@return Financial Project
	  */
	public int getC_Project_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Project_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Tax getC_Tax() throws RuntimeException
    {
		return (org.compiere.model.I_C_Tax)MTable.get(getCtx(), org.compiere.model.I_C_Tax.Table_Name)
			.getPO(getC_Tax_ID(), get_TrxName());	}

	/** Set Tax.
		@param C_Tax_ID 
		Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID)
	{
		if (C_Tax_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Tax_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Tax_ID, Integer.valueOf(C_Tax_ID));
	}

	/** Get Tax.
		@return Tax identifier
	  */
	public int getC_Tax_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Tax_ID);
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

	/** Set Close Dispatch Plan.
		@param CloseDispatchPlan Close Dispatch Plan	  */
	public void setCloseDispatchPlan (String CloseDispatchPlan)
	{
		set_Value (COLUMNNAME_CloseDispatchPlan, CloseDispatchPlan);
	}

	/** Get Close Dispatch Plan.
		@return Close Dispatch Plan	  */
	public String getCloseDispatchPlan () 
	{
		return (String)get_Value(COLUMNNAME_CloseDispatchPlan);
	}

	/** Set Contact Person.
		@param ContactPerson Contact Person	  */
	public void setContactPerson (String ContactPerson)
	{
		set_ValueNoCheck (COLUMNNAME_ContactPerson, ContactPerson);
	}

	/** Get Contact Person.
		@return Contact Person	  */
	public String getContactPerson () 
	{
		return (String)get_Value(COLUMNNAME_ContactPerson);
	}

	/** Set Customer GST No.
		@param CustomerGSTIN Customer GST No	  */
	public void setCustomerGSTIN (String CustomerGSTIN)
	{
		set_Value (COLUMNNAME_CustomerGSTIN, CustomerGSTIN);
	}

	/** Get Customer GST No.
		@return Customer GST No	  */
	public String getCustomerGSTIN () 
	{
		return (String)get_Value(COLUMNNAME_CustomerGSTIN);
	}

	/** Set Customer's Transporter.
		@param CustomerTransporter Customer's Transporter	  */
	public void setCustomerTransporter (boolean CustomerTransporter)
	{
		set_Value (COLUMNNAME_CustomerTransporter, Boolean.valueOf(CustomerTransporter));
	}

	/** Get Customer's Transporter.
		@return Customer's Transporter	  */
	public boolean isCustomerTransporter () 
	{
		Object oo = get_Value(COLUMNNAME_CustomerTransporter);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Date Ordered.
		@param DateOrdered 
		Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered)
	{
		set_ValueNoCheck (COLUMNNAME_DateOrdered, DateOrdered);
	}

	/** Get Date Ordered.
		@return Date of Order
	  */
	public Timestamp getDateOrdered () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateOrdered);
	}

	/** Set Delivered Dispatch Qty.
		@param DeliveredDPQty Delivered Dispatch Qty	  */
	public void setDeliveredDPQty (BigDecimal DeliveredDPQty)
	{
		set_Value (COLUMNNAME_DeliveredDPQty, DeliveredDPQty);
	}

	/** Get Delivered Dispatch Qty.
		@return Delivered Dispatch Qty	  */
	public BigDecimal getDeliveredDPQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DeliveredDPQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Delivery Contact.
		@param DeliveryContact Delivery Contact	  */
	public void setDeliveryContact (String DeliveryContact)
	{
		set_Value (COLUMNNAME_DeliveryContact, DeliveryContact);
	}

	/** Get Delivery Contact.
		@return Delivery Contact	  */
	public String getDeliveryContact () 
	{
		return (String)get_Value(COLUMNNAME_DeliveryContact);
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

	/** Set Discount %.
		@param Discount 
		Discount in percent
	  */
	public void setDiscount (BigDecimal Discount)
	{
		set_ValueNoCheck (COLUMNNAME_Discount, Discount);
	}

	/** Get Discount %.
		@return Discount in percent
	  */
	public BigDecimal getDiscount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Discount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Dispatch Qty.
		@param DispenseQty Dispatch Qty	  */
	public void setDispenseQty (BigDecimal DispenseQty)
	{
		set_Value (COLUMNNAME_DispenseQty, DispenseQty);
	}

	/** Get Dispatch Qty.
		@return Dispatch Qty	  */
	public BigDecimal getDispenseQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DispenseQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Drafted = DR */
	public static final String DOCSTATUS_Drafted = "DR";
	/** In Progress = IP */
	public static final String DOCSTATUS_InProgress = "IP";
	/** Completed = CO */
	public static final String DOCSTATUS_Completed = "CO";
	/** Closed = CL */
	public static final String DOCSTATUS_Closed = "CL";
	/** Revised = RV */
	public static final String DOCSTATUS_Revised = "RV";
	/** Expired = EX */
	public static final String DOCSTATUS_Expired = "EX";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{

		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_ValueNoCheck (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Freight Amount.
		@param FreightAmt 
		Freight Amount 
	  */
	public void setFreightAmt (BigDecimal FreightAmt)
	{
		set_Value (COLUMNNAME_FreightAmt, FreightAmt);
	}

	/** Get Freight Amount.
		@return Freight Amount 
	  */
	public BigDecimal getFreightAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FreightAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_UOM getFreightUOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getFreightUOM_ID(), get_TrxName());	}

	/** Set Freight UOM.
		@param FreightUOM_ID Freight UOM	  */
	public void setFreightUOM_ID (int FreightUOM_ID)
	{
		if (FreightUOM_ID < 1) 
			set_Value (COLUMNNAME_FreightUOM_ID, null);
		else 
			set_Value (COLUMNNAME_FreightUOM_ID, Integer.valueOf(FreightUOM_ID));
	}

	/** Get Freight UOM.
		@return Freight UOM	  */
	public int getFreightUOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FreightUOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Price Confidential.
		@param IsPriceConfidential Price Confidential	  */
	public void setIsPriceConfidential (boolean IsPriceConfidential)
	{
		set_Value (COLUMNNAME_IsPriceConfidential, Boolean.valueOf(IsPriceConfidential));
	}

	/** Get Price Confidential.
		@return Price Confidential	  */
	public boolean isPriceConfidential () 
	{
		Object oo = get_Value(COLUMNNAME_IsPriceConfidential);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Freight Inclusive.
		@param IsRentInclusive 
		Whether Unit Price includes rent?
	  */
	public void setIsRentInclusive (boolean IsRentInclusive)
	{
		set_Value (COLUMNNAME_IsRentInclusive, Boolean.valueOf(IsRentInclusive));
	}

	/** Get Freight Inclusive.
		@return Whether Unit Price includes rent?
	  */
	public boolean isRentInclusive () 
	{
		Object oo = get_Value(COLUMNNAME_IsRentInclusive);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Royalty Pass Inclusive.
		@param IsRoyaltyPassInclusive Royalty Pass Inclusive	  */
	public void setIsRoyaltyPassInclusive (boolean IsRoyaltyPassInclusive)
	{
		set_Value (COLUMNNAME_IsRoyaltyPassInclusive, Boolean.valueOf(IsRoyaltyPassInclusive));
	}

	/** Get Royalty Pass Inclusive.
		@return Royalty Pass Inclusive	  */
	public boolean isRoyaltyPassInclusive () 
	{
		Object oo = get_Value(COLUMNNAME_IsRoyaltyPassInclusive);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Price includes Tax.
		@param IsTaxIncluded 
		Tax is included in the price 
	  */
	public void setIsTaxIncluded (boolean IsTaxIncluded)
	{
		set_Value (COLUMNNAME_IsTaxIncluded, Boolean.valueOf(IsTaxIncluded));
	}

	/** Get Price includes Tax.
		@return Tax is included in the price 
	  */
	public boolean isTaxIncluded () 
	{
		Object oo = get_Value(COLUMNNAME_IsTaxIncluded);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_ValueNoCheck (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line Amount.
		@param LineNetAmt 
		Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public void setLineNetAmt (BigDecimal LineNetAmt)
	{
		set_ValueNoCheck (COLUMNNAME_LineNetAmt, LineNetAmt);
	}

	/** Get Line Amount.
		@return Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public BigDecimal getLineNetAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LineNetAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	public org.compiere.model.I_M_Shipper getM_Shipper() throws RuntimeException
    {
		return (org.compiere.model.I_M_Shipper)MTable.get(getCtx(), org.compiere.model.I_M_Shipper.Table_Name)
			.getPO(getM_Shipper_ID(), get_TrxName());	}

	/** Set Shipper.
		@param M_Shipper_ID 
		Method or manner of product delivery
	  */
	public void setM_Shipper_ID (int M_Shipper_ID)
	{
		if (M_Shipper_ID < 1) 
			set_Value (COLUMNNAME_M_Shipper_ID, null);
		else 
			set_Value (COLUMNNAME_M_Shipper_ID, Integer.valueOf(M_Shipper_ID));
	}

	/** Get Shipper.
		@return Method or manner of product delivery
	  */
	public int getM_Shipper_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Shipper_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException
    {
		return (org.compiere.model.I_M_Warehouse)MTable.get(getCtx(), org.compiere.model.I_M_Warehouse.Table_Name)
			.getPO(getM_Warehouse_ID(), get_TrxName());	}

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1) 
			set_Value (COLUMNNAME_M_Warehouse_ID, null);
		else 
			set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Origin Date.
		@param OriginDate Origin Date	  */
	public void setOriginDate (Timestamp OriginDate)
	{
		set_Value (COLUMNNAME_OriginDate, OriginDate);
	}

	/** Get Origin Date.
		@return Origin Date	  */
	public Timestamp getOriginDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_OriginDate);
	}

	/** Set Allow Over Delivery Qty.
		@param OverUnitDelivery Allow Over Delivery Qty	  */
	public void setOverUnitDelivery (boolean OverUnitDelivery)
	{
		set_Value (COLUMNNAME_OverUnitDelivery, Boolean.valueOf(OverUnitDelivery));
	}

	/** Get Allow Over Delivery Qty.
		@return Allow Over Delivery Qty	  */
	public boolean isOverUnitDelivery () 
	{
		Object oo = get_Value(COLUMNNAME_OverUnitDelivery);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_TF_DispensePlanLine getParent() throws RuntimeException
    {
		return (I_TF_DispensePlanLine)MTable.get(getCtx(), I_TF_DispensePlanLine.Table_Name)
			.getPO(getParent_ID(), get_TrxName());	}

	/** Set Parent.
		@param Parent_ID 
		Parent of Entity
	  */
	public void setParent_ID (int Parent_ID)
	{
		if (Parent_ID < 1) 
			set_Value (COLUMNNAME_Parent_ID, null);
		else 
			set_Value (COLUMNNAME_Parent_ID, Integer.valueOf(Parent_ID));
	}

	/** Get Parent.
		@return Parent of Entity
	  */
	public int getParent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Parent_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Party Name.
		@param PartyName Party Name	  */
	public void setPartyName (String PartyName)
	{
		set_Value (COLUMNNAME_PartyName, PartyName);
	}

	/** Get Party Name.
		@return Party Name	  */
	public String getPartyName () 
	{
		return (String)get_Value(COLUMNNAME_PartyName);
	}

	/** PaymentRule AD_Reference_ID=195 */
	public static final int PAYMENTRULE_AD_Reference_ID=195;
	/** Cash = B */
	public static final String PAYMENTRULE_Cash = "B";
	/** Credit Card = K */
	public static final String PAYMENTRULE_CreditCard = "K";
	/** Direct Deposit = T */
	public static final String PAYMENTRULE_DirectDeposit = "T";
	/** Check = S */
	public static final String PAYMENTRULE_Check = "S";
	/** On Credit = P */
	public static final String PAYMENTRULE_OnCredit = "P";
	/** Direct Debit = D */
	public static final String PAYMENTRULE_DirectDebit = "D";
	/** Mixed POS Payment = M */
	public static final String PAYMENTRULE_MixedPOSPayment = "M";
	/** Cash  = Z */
	public static final String PAYMENTRULE_PrepaidCash = "Z";
	/** Set Payment Rule.
		@param PaymentRule 
		How you pay the invoice
	  */
	public void setPaymentRule (String PaymentRule)
	{

		set_Value (COLUMNNAME_PaymentRule, PaymentRule);
	}

	/** Get Payment Rule.
		@return How you pay the invoice
	  */
	public String getPaymentRule () 
	{
		return (String)get_Value(COLUMNNAME_PaymentRule);
	}

	/** Set Unit Price.
		@param PriceActual 
		Actual Price 
	  */
	public void setPriceActual (BigDecimal PriceActual)
	{
		set_ValueNoCheck (COLUMNNAME_PriceActual, PriceActual);
	}

	/** Get Unit Price.
		@return Actual Price 
	  */
	public BigDecimal getPriceActual () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceActual);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Price.
		@param PriceEntered 
		Price Entered - the price based on the selected/base UoM
	  */
	public void setPriceEntered (BigDecimal PriceEntered)
	{
		set_ValueNoCheck (COLUMNNAME_PriceEntered, PriceEntered);
	}

	/** Get Price.
		@return Price Entered - the price based on the selected/base UoM
	  */
	public BigDecimal getPriceEntered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceEntered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Limit Price.
		@param PriceLimit 
		Lowest price for a product
	  */
	public void setPriceLimit (BigDecimal PriceLimit)
	{
		set_Value (COLUMNNAME_PriceLimit, PriceLimit);
	}

	/** Get Limit Price.
		@return Lowest price for a product
	  */
	public BigDecimal getPriceLimit () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceLimit);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set List Price.
		@param PriceList 
		List Price
	  */
	public void setPriceList (BigDecimal PriceList)
	{
		set_Value (COLUMNNAME_PriceList, PriceList);
	}

	/** Get List Price.
		@return List Price
	  */
	public BigDecimal getPriceList () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceList);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Most Urgent = MU */
	public static final String PRIORITY_MostUrgent = "MU";
	/** Urgent = U */
	public static final String PRIORITY_Urgent = "U";
	/** Normal = N */
	public static final String PRIORITY_Normal = "N";
	/** Set Priority.
		@param Priority 
		Indicates if this request is of a high, medium or low priority.
	  */
	public void setPriority (String Priority)
	{

		set_Value (COLUMNNAME_Priority, Priority);
	}

	/** Get Priority.
		@return Indicates if this request is of a high, medium or low priority.
	  */
	public String getPriority () 
	{
		return (String)get_Value(COLUMNNAME_Priority);
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

	/** Set Delivered Quantity.
		@param QtyDelivered 
		Delivered Quantity
	  */
	public void setQtyDelivered (BigDecimal QtyDelivered)
	{
		set_ValueNoCheck (COLUMNNAME_QtyDelivered, QtyDelivered);
	}

	/** Get Delivered Quantity.
		@return Delivered Quantity
	  */
	public BigDecimal getQtyDelivered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyDelivered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity.
		@param QtyEntered 
		The Quantity Entered is based on the selected UoM
	  */
	public void setQtyEntered (BigDecimal QtyEntered)
	{
		set_ValueNoCheck (COLUMNNAME_QtyEntered, QtyEntered);
	}

	/** Get Quantity.
		@return The Quantity Entered is based on the selected UoM
	  */
	public BigDecimal getQtyEntered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyEntered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity Invoiced.
		@param QtyInvoiced 
		Invoiced Quantity
	  */
	public void setQtyInvoiced (BigDecimal QtyInvoiced)
	{
		set_ValueNoCheck (COLUMNNAME_QtyInvoiced, QtyInvoiced);
	}

	/** Get Quantity Invoiced.
		@return Invoiced Quantity
	  */
	public BigDecimal getQtyInvoiced () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyInvoiced);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ordered Quantity.
		@param QtyOrdered 
		Ordered Quantity
	  */
	public void setQtyOrdered (BigDecimal QtyOrdered)
	{
		set_ValueNoCheck (COLUMNNAME_QtyOrdered, QtyOrdered);
	}

	/** Get Ordered Quantity.
		@return Ordered Quantity
	  */
	public BigDecimal getQtyOrdered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyOrdered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Reserved Quantity.
		@param QtyReserved 
		Reserved Quantity
	  */
	public void setQtyReserved (BigDecimal QtyReserved)
	{
		set_ValueNoCheck (COLUMNNAME_QtyReserved, QtyReserved);
	}

	/** Get Reserved Quantity.
		@return Reserved Quantity
	  */
	public BigDecimal getQtyReserved () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyReserved);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Schedule Date.
		@param ScheduleDate Schedule Date	  */
	public void setScheduleDate (Timestamp ScheduleDate)
	{
		set_Value (COLUMNNAME_ScheduleDate, ScheduleDate);
	}

	/** Get Schedule Date.
		@return Schedule Date	  */
	public Timestamp getScheduleDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ScheduleDate);
	}

	/** Set Shipment Address.
		@param ShipmentAddress Shipment Address	  */
	public void setShipmentAddress (String ShipmentAddress)
	{
		set_Value (COLUMNNAME_ShipmentAddress, ShipmentAddress);
	}

	/** Get Shipment Address.
		@return Shipment Address	  */
	public String getShipmentAddress () 
	{
		return (String)get_Value(COLUMNNAME_ShipmentAddress);
	}

	/** Set Shipment Destination.
		@param ShipmentDestination Shipment Destination	  */
	public void setShipmentDestination (String ShipmentDestination)
	{
		set_Value (COLUMNNAME_ShipmentDestination, ShipmentDestination);
	}

	

	public I_TF_Destination getShipmentDestination() throws RuntimeException
    {
		return (I_TF_Destination)MTable.get(getCtx(), I_TF_Destination.Table_Name)
			.getPO(getShipmentDestination_ID(), get_TrxName());	}

	/** Set Shipment Destination.
		@param ShipmentDestination_ID Shipment Destination	  */
	public void setShipmentDestination_ID (int ShipmentDestination_ID)
	{
		if (ShipmentDestination_ID < 1) 
			set_Value (COLUMNNAME_ShipmentDestination_ID, null);
		else 
			set_Value (COLUMNNAME_ShipmentDestination_ID, Integer.valueOf(ShipmentDestination_ID));
	}

	/** Get Shipment Destination.
		@return Shipment Destination	  */
	public int getShipmentDestination_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ShipmentDestination_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Shipment Rate.
		@param ShipmentRate Shipment Rate	  */
	public void setShipmentRate (BigDecimal ShipmentRate)
	{
		set_Value (COLUMNNAME_ShipmentRate, ShipmentRate);
	}

	/** Get Shipment Rate.
		@return Shipment Rate	  */
	public BigDecimal getShipmentRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ShipmentRate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Shipment To.
		@param ShipmentTo Shipment To	  */
	public void setShipmentTo (String ShipmentTo)
	{
		set_Value (COLUMNNAME_ShipmentTo, ShipmentTo);
	}

	/** Get Shipment To.
		@return Shipment To	  */
	public String getShipmentTo () 
	{
		return (String)get_Value(COLUMNNAME_ShipmentTo);
	}

	public I_TF_Destination getTF_Destination() throws RuntimeException
    {
		return (I_TF_Destination)MTable.get(getCtx(), I_TF_Destination.Table_Name)
			.getPO(getTF_Destination_ID(), get_TrxName());	}

	/** Set Destination.
		@param TF_Destination_ID Destination	  */
	public void setTF_Destination_ID (int TF_Destination_ID)
	{
		if (TF_Destination_ID < 1) 
			set_Value (COLUMNNAME_TF_Destination_ID, null);
		else 
			set_Value (COLUMNNAME_TF_Destination_ID, Integer.valueOf(TF_Destination_ID));
	}

	/** Get Destination.
		@return Destination	  */
	public int getTF_Destination_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_Destination_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_TF_DispensePlan getTF_DispensePlan() throws RuntimeException
    {
		return (I_TF_DispensePlan)MTable.get(getCtx(), I_TF_DispensePlan.Table_Name)
			.getPO(getTF_DispensePlan_ID(), get_TrxName());	}

	/** Set Dispatch Plan.
		@param TF_DispensePlan_ID Dispatch Plan	  */
	public void setTF_DispensePlan_ID (int TF_DispensePlan_ID)
	{
		if (TF_DispensePlan_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_DispensePlan_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_DispensePlan_ID, Integer.valueOf(TF_DispensePlan_ID));
	}

	/** Get Dispatch Plan.
		@return Dispatch Plan	  */
	public int getTF_DispensePlan_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_DispensePlan_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Dispatch Plan Line.
		@param TF_DispensePlanLine_ID Dispatch Plan Line	  */
	public void setTF_DispensePlanLine_ID (int TF_DispensePlanLine_ID)
	{
		if (TF_DispensePlanLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_DispensePlanLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_DispensePlanLine_ID, Integer.valueOf(TF_DispensePlanLine_ID));
	}

	/** Get Dispatch Plan Line.
		@return Dispatch Plan Line	  */
	public int getTF_DispensePlanLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_DispensePlanLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_DispensePlanLine_UU.
		@param TF_DispensePlanLine_UU TF_DispensePlanLine_UU	  */
	public void setTF_DispensePlanLine_UU (String TF_DispensePlanLine_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_DispensePlanLine_UU, TF_DispensePlanLine_UU);
	}

	/** Get TF_DispensePlanLine_UU.
		@return TF_DispensePlanLine_UU	  */
	public String getTF_DispensePlanLine_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_DispensePlanLine_UU);
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

	/** Instant = I */
	public static final String TYPE_Instant = "I";
	/** Order = O */
	public static final String TYPE_Order = "O";
	/** Set Type.
		@param Type 
		Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type)
	{

		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
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
}