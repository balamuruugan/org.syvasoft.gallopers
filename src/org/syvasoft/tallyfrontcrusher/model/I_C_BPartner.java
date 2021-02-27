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

/** Generated Interface for C_BPartner
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_C_BPartner 
{

    /** TableName=C_BPartner */
    public static final String Table_Name = "C_BPartner";

    /** AD_Table_ID=291 */
    public static final int Table_ID = 291;

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AccountNo */
    public static final String COLUMNNAME_AccountNo = "AccountNo";

	/** Set Account No.
	  * Account Number
	  */
	public void setAccountNo (String AccountNo);

	/** Get Account No.
	  * Account Number
	  */
	public String getAccountNo();

    /** Column name AcqusitionCost */
    public static final String COLUMNNAME_AcqusitionCost = "AcqusitionCost";

	/** Set Acquisition Cost.
	  * The cost of gaining the prospect as a customer
	  */
	public void setAcqusitionCost (BigDecimal AcqusitionCost);

	/** Get Acquisition Cost.
	  * The cost of gaining the prospect as a customer
	  */
	public BigDecimal getAcqusitionCost();

    /** Column name ActualLifeTimeValue */
    public static final String COLUMNNAME_ActualLifeTimeValue = "ActualLifeTimeValue";

	/** Set Actual Life Time Value.
	  * Actual Life Time Revenue
	  */
	public void setActualLifeTimeValue (BigDecimal ActualLifeTimeValue);

	/** Get Actual Life Time Value.
	  * Actual Life Time Revenue
	  */
	public BigDecimal getActualLifeTimeValue();

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Language */
    public static final String COLUMNNAME_AD_Language = "AD_Language";

	/** Set Language.
	  * Language for this entity
	  */
	public void setAD_Language (String AD_Language);

	/** Get Language.
	  * Language for this entity
	  */
	public String getAD_Language();

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

    /** Column name AD_OrgBP_ID */
    public static final String COLUMNNAME_AD_OrgBP_ID = "AD_OrgBP_ID";

	/** Set Linked Organization.
	  * The Business Partner is another Organization for explicit Inter-Org transactions
	  */
	public void setAD_OrgBP_ID (String AD_OrgBP_ID);

	/** Get Linked Organization.
	  * The Business Partner is another Organization for explicit Inter-Org transactions
	  */
	public String getAD_OrgBP_ID();

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException;

    /** Column name Address1 */
    public static final String COLUMNNAME_Address1 = "Address1";

	/** Set Address 1.
	  * Address line 1 for this location
	  */
	public void setAddress1 (String Address1);

	/** Get Address 1.
	  * Address line 1 for this location
	  */
	public String getAddress1();

    /** Column name Address2 */
    public static final String COLUMNNAME_Address2 = "Address2";

	/** Set Address 2.
	  * Address line 2 for this location
	  */
	public void setAddress2 (String Address2);

	/** Get Address 2.
	  * Address line 2 for this location
	  */
	public String getAddress2();

    /** Column name Address3 */
    public static final String COLUMNNAME_Address3 = "Address3";

	/** Set Address 3.
	  * Address Line 3 for the location
	  */
	public void setAddress3 (String Address3);

	/** Get Address 3.
	  * Address Line 3 for the location
	  */
	public String getAddress3();

    /** Column name Address4 */
    public static final String COLUMNNAME_Address4 = "Address4";

	/** Set Address 4.
	  * Address Line 4 for the location
	  */
	public void setAddress4 (String Address4);

	/** Get Address 4.
	  * Address Line 4 for the location
	  */
	public String getAddress4();

    /** Column name BankName */
    public static final String COLUMNNAME_BankName = "BankName";

	/** Set Bank Name	  */
	public void setBankName (String BankName);

	/** Get Bank Name	  */
	public String getBankName();

    /** Column name BPartner_Parent_ID */
    public static final String COLUMNNAME_BPartner_Parent_ID = "BPartner_Parent_ID";

	/** Set Partner Parent.
	  * Business Partner Parent
	  */
	public void setBPartner_Parent_ID (int BPartner_Parent_ID);

	/** Get Partner Parent.
	  * Business Partner Parent
	  */
	public int getBPartner_Parent_ID();

    /** Column name BranchName */
    public static final String COLUMNNAME_BranchName = "BranchName";

	/** Set Branch Name	  */
	public void setBranchName (String BranchName);

	/** Get Branch Name	  */
	public String getBranchName();

    /** Column name C_BP_Group_ID */
    public static final String COLUMNNAME_C_BP_Group_ID = "C_BP_Group_ID";

	/** Set Business Partner Group.
	  * Business Partner Group
	  */
	public void setC_BP_Group_ID (int C_BP_Group_ID);

	/** Get Business Partner Group.
	  * Business Partner Group
	  */
	public int getC_BP_Group_ID();

	public org.compiere.model.I_C_BP_Group getC_BP_Group() throws RuntimeException;

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

    /** Column name C_BPartner_UU */
    public static final String COLUMNNAME_C_BPartner_UU = "C_BPartner_UU";

	/** Set C_BPartner_UU	  */
	public void setC_BPartner_UU (String C_BPartner_UU);

	/** Get C_BPartner_UU	  */
	public String getC_BPartner_UU();

    /** Column name C_Country_ID */
    public static final String COLUMNNAME_C_Country_ID = "C_Country_ID";

	/** Set Country.
	  * Country 
	  */
	public void setC_Country_ID (int C_Country_ID);

	/** Get Country.
	  * Country 
	  */
	public int getC_Country_ID();

	public org.compiere.model.I_C_Country getC_Country() throws RuntimeException;

    /** Column name C_Dunning_ID */
    public static final String COLUMNNAME_C_Dunning_ID = "C_Dunning_ID";

	/** Set Dunning.
	  * Dunning Rules for overdue invoices
	  */
	public void setC_Dunning_ID (int C_Dunning_ID);

	/** Get Dunning.
	  * Dunning Rules for overdue invoices
	  */
	public int getC_Dunning_ID();

	public org.compiere.model.I_C_Dunning getC_Dunning() throws RuntimeException;

    /** Column name C_Greeting_ID */
    public static final String COLUMNNAME_C_Greeting_ID = "C_Greeting_ID";

	/** Set Greeting.
	  * Greeting to print on correspondence
	  */
	public void setC_Greeting_ID (int C_Greeting_ID);

	/** Get Greeting.
	  * Greeting to print on correspondence
	  */
	public int getC_Greeting_ID();

	public org.compiere.model.I_C_Greeting getC_Greeting() throws RuntimeException;

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

    /** Column name C_InvoiceSchedule_ID */
    public static final String COLUMNNAME_C_InvoiceSchedule_ID = "C_InvoiceSchedule_ID";

	/** Set Invoice Schedule.
	  * Schedule for generating Invoices
	  */
	public void setC_InvoiceSchedule_ID (int C_InvoiceSchedule_ID);

	/** Get Invoice Schedule.
	  * Schedule for generating Invoices
	  */
	public int getC_InvoiceSchedule_ID();

	public org.compiere.model.I_C_InvoiceSchedule getC_InvoiceSchedule() throws RuntimeException;

    /** Column name C_Location_ID */
    public static final String COLUMNNAME_C_Location_ID = "C_Location_ID";

	/** Set Address.
	  * Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID);

	/** Get Address.
	  * Location or Address
	  */
	public int getC_Location_ID();

	public I_C_Location getC_Location() throws RuntimeException;

    /** Column name C_PaymentTerm_ID */
    public static final String COLUMNNAME_C_PaymentTerm_ID = "C_PaymentTerm_ID";

	/** Set Payment Term.
	  * The terms of Payment (timing, discount)
	  */
	public void setC_PaymentTerm_ID (int C_PaymentTerm_ID);

	/** Get Payment Term.
	  * The terms of Payment (timing, discount)
	  */
	public int getC_PaymentTerm_ID();

	public org.compiere.model.I_C_PaymentTerm getC_PaymentTerm() throws RuntimeException;

    /** Column name C_TaxGroup_ID */
    public static final String COLUMNNAME_C_TaxGroup_ID = "C_TaxGroup_ID";

	/** Set Tax Group	  */
	public void setC_TaxGroup_ID (int C_TaxGroup_ID);

	/** Get Tax Group	  */
	public int getC_TaxGroup_ID();

	public org.eevolution.model.I_C_TaxGroup getC_TaxGroup() throws RuntimeException;

    /** Column name CareOf */
    public static final String COLUMNNAME_CareOf = "CareOf";

	/** Set Care Of	  */
	public void setCareOf (String CareOf);

	/** Get Care Of	  */
	public String getCareOf();

    /** Column name City */
    public static final String COLUMNNAME_City = "City";

	/** Set City.
	  * Identifies a City
	  */
	public void setCity (String City);

	/** Get City.
	  * Identifies a City
	  */
	public String getCity();

    /** Column name ContactName */
    public static final String COLUMNNAME_ContactName = "ContactName";

	/** Set Contact Name.
	  * Business Partner Contact Name
	  */
	public void setContactName (String ContactName);

	/** Get Contact Name.
	  * Business Partner Contact Name
	  */
	public String getContactName();

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

    /** Column name CreditBalance */
    public static final String COLUMNNAME_CreditBalance = "CreditBalance";

	/** Set Credit Balance	  */
	public void setCreditBalance (BigDecimal CreditBalance);

	/** Get Credit Balance	  */
	public BigDecimal getCreditBalance();

    /** Column name CustomerProfileID */
    public static final String COLUMNNAME_CustomerProfileID = "CustomerProfileID";

	/** Set Customer Profile ID	  */
	public void setCustomerProfileID (String CustomerProfileID);

	/** Get Customer Profile ID	  */
	public String getCustomerProfileID();

    /** Column name DateJoining */
    public static final String COLUMNNAME_DateJoining = "DateJoining";

	/** Set Date of Joining	  */
	public void setDateJoining (Timestamp DateJoining);

	/** Get Date of Joining	  */
	public Timestamp getDateJoining();

    /** Column name DebitBalance */
    public static final String COLUMNNAME_DebitBalance = "DebitBalance";

	/** Set Debit Balance	  */
	public void setDebitBalance (BigDecimal DebitBalance);

	/** Get Debit Balance	  */
	public BigDecimal getDebitBalance();

    /** Column name Default1099Box_ID */
    public static final String COLUMNNAME_Default1099Box_ID = "Default1099Box_ID";

	/** Set Default 1099 Box	  */
	public void setDefault1099Box_ID (int Default1099Box_ID);

	/** Get Default 1099 Box	  */
	public int getDefault1099Box_ID();

	public org.compiere.model.I_C_1099Box getDefault1099Box() throws RuntimeException;

    /** Column name DeliveryRule */
    public static final String COLUMNNAME_DeliveryRule = "DeliveryRule";

	/** Set Delivery Rule.
	  * Defines the timing of Delivery
	  */
	public void setDeliveryRule (String DeliveryRule);

	/** Get Delivery Rule.
	  * Defines the timing of Delivery
	  */
	public String getDeliveryRule();

    /** Column name DeliveryViaRule */
    public static final String COLUMNNAME_DeliveryViaRule = "DeliveryViaRule";

	/** Set Delivery Via.
	  * How the order will be delivered
	  */
	public void setDeliveryViaRule (String DeliveryViaRule);

	/** Get Delivery Via.
	  * How the order will be delivered
	  */
	public String getDeliveryViaRule();

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

    /** Column name Designation */
    public static final String COLUMNNAME_Designation = "Designation";

	/** Set Designation	  */
	public void setDesignation (String Designation);

	/** Get Designation	  */
	public String getDesignation();

    /** Column name DocumentCopies */
    public static final String COLUMNNAME_DocumentCopies = "DocumentCopies";

	/** Set Document Copies.
	  * Number of copies to be printed
	  */
	public void setDocumentCopies (int DocumentCopies);

	/** Get Document Copies.
	  * Number of copies to be printed
	  */
	public int getDocumentCopies();

    /** Column name DunningGrace */
    public static final String COLUMNNAME_DunningGrace = "DunningGrace";

	/** Set Dunning Grace Date	  */
	public void setDunningGrace (Timestamp DunningGrace);

	/** Get Dunning Grace Date	  */
	public Timestamp getDunningGrace();

    /** Column name DUNS */
    public static final String COLUMNNAME_DUNS = "DUNS";

	/** Set D-U-N-S.
	  * Dun & Bradstreet Number
	  */
	public void setDUNS (String DUNS);

	/** Get D-U-N-S.
	  * Dun & Bradstreet Number
	  */
	public String getDUNS();

    /** Column name EmployeeType */
    public static final String COLUMNNAME_EmployeeType = "EmployeeType";

	/** Set Employee Type	  */
	public void setEmployeeType (String EmployeeType);

	/** Get Employee Type	  */
	public String getEmployeeType();

    /** Column name FirstSale */
    public static final String COLUMNNAME_FirstSale = "FirstSale";

	/** Set First Sale.
	  * Date of First Sale
	  */
	public void setFirstSale (Timestamp FirstSale);

	/** Get First Sale.
	  * Date of First Sale
	  */
	public Timestamp getFirstSale();

    /** Column name FlatDiscount */
    public static final String COLUMNNAME_FlatDiscount = "FlatDiscount";

	/** Set Flat Discount %.
	  * Flat discount percentage 
	  */
	public void setFlatDiscount (BigDecimal FlatDiscount);

	/** Get Flat Discount %.
	  * Flat discount percentage 
	  */
	public BigDecimal getFlatDiscount();

    /** Column name FreightCostRule */
    public static final String COLUMNNAME_FreightCostRule = "FreightCostRule";

	/** Set Freight Cost Rule.
	  * Method for charging Freight
	  */
	public void setFreightCostRule (String FreightCostRule);

	/** Get Freight Cost Rule.
	  * Method for charging Freight
	  */
	public String getFreightCostRule();

    /** Column name IFSCCode */
    public static final String COLUMNNAME_IFSCCode = "IFSCCode";

	/** Set IFSC Code	  */
	public void setIFSCCode (String IFSCCode);

	/** Get IFSC Code	  */
	public String getIFSCCode();

    /** Column name Invoice_PrintFormat_ID */
    public static final String COLUMNNAME_Invoice_PrintFormat_ID = "Invoice_PrintFormat_ID";

	/** Set Invoice Print Format.
	  * Print Format for printing Invoices
	  */
	public void setInvoice_PrintFormat_ID (int Invoice_PrintFormat_ID);

	/** Get Invoice Print Format.
	  * Print Format for printing Invoices
	  */
	public int getInvoice_PrintFormat_ID();

	public org.compiere.model.I_AD_PrintFormat getInvoice_PrintFormat() throws RuntimeException;

    /** Column name InvoiceRule */
    public static final String COLUMNNAME_InvoiceRule = "InvoiceRule";

	/** Set Invoice Rule.
	  * Frequency and method of invoicing 
	  */
	public void setInvoiceRule (String InvoiceRule);

	/** Get Invoice Rule.
	  * Frequency and method of invoicing 
	  */
	public String getInvoiceRule();

    /** Column name Is1099Vendor */
    public static final String COLUMNNAME_Is1099Vendor = "Is1099Vendor";

	/** Set 1099 Vendor	  */
	public void setIs1099Vendor (boolean Is1099Vendor);

	/** Get 1099 Vendor	  */
	public boolean is1099Vendor();

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

    /** Column name IsCanaraBank */
    public static final String COLUMNNAME_IsCanaraBank = "IsCanaraBank";

	/** Set Canara Bank	  */
	public void setIsCanaraBank (boolean IsCanaraBank);

	/** Get Canara Bank	  */
	public boolean isCanaraBank();

    /** Column name IsCustomer */
    public static final String COLUMNNAME_IsCustomer = "IsCustomer";

	/** Set Customer.
	  * Indicates if this Business Partner is a Customer
	  */
	public void setIsCustomer (boolean IsCustomer);

	/** Get Customer.
	  * Indicates if this Business Partner is a Customer
	  */
	public boolean isCustomer();

    /** Column name IsDiscountPrinted */
    public static final String COLUMNNAME_IsDiscountPrinted = "IsDiscountPrinted";

	/** Set Discount Printed.
	  * Print Discount on Invoice and Order
	  */
	public void setIsDiscountPrinted (boolean IsDiscountPrinted);

	/** Get Discount Printed.
	  * Print Discount on Invoice and Order
	  */
	public boolean isDiscountPrinted();

    /** Column name IsEmployee */
    public static final String COLUMNNAME_IsEmployee = "IsEmployee";

	/** Set Employee.
	  * Indicates if  this Business Partner is an employee
	  */
	public void setIsEmployee (boolean IsEmployee);

	/** Get Employee.
	  * Indicates if  this Business Partner is an employee
	  */
	public boolean isEmployee();

    /** Column name IsInterState */
    public static final String COLUMNNAME_IsInterState = "IsInterState";

	/** Set Inter State	  */
	public void setIsInterState (boolean IsInterState);

	/** Get Inter State	  */
	public boolean isInterState();

    /** Column name IsManufacturer */
    public static final String COLUMNNAME_IsManufacturer = "IsManufacturer";

	/** Set Is Manufacturer.
	  * Indicate role of this Business partner as Manufacturer
	  */
	public void setIsManufacturer (boolean IsManufacturer);

	/** Get Is Manufacturer.
	  * Indicate role of this Business partner as Manufacturer
	  */
	public boolean isManufacturer();

    /** Column name IsOneTime */
    public static final String COLUMNNAME_IsOneTime = "IsOneTime";

	/** Set One time transaction	  */
	public void setIsOneTime (boolean IsOneTime);

	/** Get One time transaction	  */
	public boolean isOneTime();

    /** Column name IsPermitSales */
    public static final String COLUMNNAME_IsPermitSales = "IsPermitSales";

	/** Set Permit Sales	  */
	public void setIsPermitSales (boolean IsPermitSales);

	/** Get Permit Sales	  */
	public boolean isPermitSales();

    /** Column name IsPOSCashBP */
    public static final String COLUMNNAME_IsPOSCashBP = "IsPOSCashBP";

	/** Set POS Cash BP	  */
	public void setIsPOSCashBP (boolean IsPOSCashBP);

	/** Get POS Cash BP	  */
	public boolean isPOSCashBP();

    /** Column name IsPOTaxExempt */
    public static final String COLUMNNAME_IsPOTaxExempt = "IsPOTaxExempt";

	/** Set PO Tax exempt.
	  * Business partner is exempt from tax on purchases
	  */
	public void setIsPOTaxExempt (boolean IsPOTaxExempt);

	/** Get PO Tax exempt.
	  * Business partner is exempt from tax on purchases
	  */
	public boolean isPOTaxExempt();

    /** Column name IsProspect */
    public static final String COLUMNNAME_IsProspect = "IsProspect";

	/** Set Prospect.
	  * Indicates this is a Prospect
	  */
	public void setIsProspect (boolean IsProspect);

	/** Get Prospect.
	  * Indicates this is a Prospect
	  */
	public boolean isProspect();

    /** Column name IsRentBreakup */
    public static final String COLUMNNAME_IsRentBreakup = "IsRentBreakup";

	/** Set Show Rent Breakup	  */
	public void setIsRentBreakup (boolean IsRentBreakup);

	/** Get Show Rent Breakup	  */
	public boolean isRentBreakup();

    /** Column name IsRentInclusive */
    public static final String COLUMNNAME_IsRentInclusive = "IsRentInclusive";

	/** Set Rent Inclusive.
	  * Whether Unit Price includes rent?
	  */
	public void setIsRentInclusive (boolean IsRentInclusive);

	/** Get Rent Inclusive.
	  * Whether Unit Price includes rent?
	  */
	public boolean isRentInclusive();

    /** Column name IsRequiredTaxInvoicePerLoad */
    public static final String COLUMNNAME_IsRequiredTaxInvoicePerLoad = "IsRequiredTaxInvoicePerLoad";

	/** Set Require Taxinvoice per load	  */
	public void setIsRequiredTaxInvoicePerLoad (boolean IsRequiredTaxInvoicePerLoad);

	/** Get Require Taxinvoice per load	  */
	public boolean isRequiredTaxInvoicePerLoad();

    /** Column name IsSalesRep */
    public static final String COLUMNNAME_IsSalesRep = "IsSalesRep";

	/** Set Sales Representative.
	  * Indicates if  the business partner is a sales representative or company agent
	  */
	public void setIsSalesRep (boolean IsSalesRep);

	/** Get Sales Representative.
	  * Indicates if  the business partner is a sales representative or company agent
	  */
	public boolean isSalesRep();

    /** Column name IsSubcontractor */
    public static final String COLUMNNAME_IsSubcontractor = "IsSubcontractor";

	/** Set Subcontractor.
	  * Indicates if  this Business Partner is Subcontractor
	  */
	public void setIsSubcontractor (boolean IsSubcontractor);

	/** Get Subcontractor.
	  * Indicates if  this Business Partner is Subcontractor
	  */
	public boolean isSubcontractor();

    /** Column name IsSummary */
    public static final String COLUMNNAME_IsSummary = "IsSummary";

	/** Set Summary Level.
	  * This is a summary entity
	  */
	public void setIsSummary (boolean IsSummary);

	/** Get Summary Level.
	  * This is a summary entity
	  */
	public boolean isSummary();

    /** Column name IsTaxExempt */
    public static final String COLUMNNAME_IsTaxExempt = "IsTaxExempt";

	/** Set SO Tax exempt.
	  * Business partner is exempt from tax on sales
	  */
	public void setIsTaxExempt (boolean IsTaxExempt);

	/** Get SO Tax exempt.
	  * Business partner is exempt from tax on sales
	  */
	public boolean isTaxExempt();

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

    /** Column name IsVendor */
    public static final String COLUMNNAME_IsVendor = "IsVendor";

	/** Set Vendor.
	  * Indicates if this Business Partner is a Vendor
	  */
	public void setIsVendor (boolean IsVendor);

	/** Get Vendor.
	  * Indicates if this Business Partner is a Vendor
	  */
	public boolean isVendor();

    /** Column name Logo_ID */
    public static final String COLUMNNAME_Logo_ID = "Logo_ID";

	/** Set Logo	  */
	public void setLogo_ID (int Logo_ID);

	/** Get Logo	  */
	public int getLogo_ID();

    /** Column name M_DiscountSchema_ID */
    public static final String COLUMNNAME_M_DiscountSchema_ID = "M_DiscountSchema_ID";

	/** Set Discount Schema.
	  * Schema to calculate the trade discount percentage
	  */
	public void setM_DiscountSchema_ID (int M_DiscountSchema_ID);

	/** Get Discount Schema.
	  * Schema to calculate the trade discount percentage
	  */
	public int getM_DiscountSchema_ID();

	public org.compiere.model.I_M_DiscountSchema getM_DiscountSchema() throws RuntimeException;

    /** Column name M_PriceList_ID */
    public static final String COLUMNNAME_M_PriceList_ID = "M_PriceList_ID";

	/** Set Price List.
	  * Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID);

	/** Get Price List.
	  * Unique identifier of a Price List
	  */
	public int getM_PriceList_ID();

	public org.compiere.model.I_M_PriceList getM_PriceList() throws RuntimeException;

    /** Column name MonthlySalary */
    public static final String COLUMNNAME_MonthlySalary = "MonthlySalary";

	/** Set Monthly Salary	  */
	public void setMonthlySalary (BigDecimal MonthlySalary);

	/** Get Monthly Salary	  */
	public BigDecimal getMonthlySalary();

    /** Column name NAICS */
    public static final String COLUMNNAME_NAICS = "NAICS";

	/** Set NAICS/SIC.
	  * Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html
	  */
	public void setNAICS (String NAICS);

	/** Get NAICS/SIC.
	  * Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html
	  */
	public String getNAICS();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Name2 */
    public static final String COLUMNNAME_Name2 = "Name2";

	/** Set Name 2.
	  * Additional Name
	  */
	public void setName2 (String Name2);

	/** Get Name 2.
	  * Additional Name
	  */
	public String getName2();

    /** Column name NameOutstanding */
    public static final String COLUMNNAME_NameOutstanding = "NameOutstanding";

	/** Set Name + Outstanding Balance	  */
	public void setNameOutstanding (String NameOutstanding);

	/** Get Name + Outstanding Balance	  */
	public String getNameOutstanding();

    /** Column name NumberEmployees */
    public static final String COLUMNNAME_NumberEmployees = "NumberEmployees";

	/** Set Employees.
	  * Number of employees
	  */
	public void setNumberEmployees (int NumberEmployees);

	/** Get Employees.
	  * Number of employees
	  */
	public int getNumberEmployees();

    /** Column name OpeningDate */
    public static final String COLUMNNAME_OpeningDate = "OpeningDate";

	/** Set AS On	  */
	public void setOpeningDate (Timestamp OpeningDate);

	/** Get AS On	  */
	public Timestamp getOpeningDate();

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

    /** Column name PaymentRulePO */
    public static final String COLUMNNAME_PaymentRulePO = "PaymentRulePO";

	/** Set Payment Rule.
	  * Purchase payment option
	  */
	public void setPaymentRulePO (String PaymentRulePO);

	/** Get Payment Rule.
	  * Purchase payment option
	  */
	public String getPaymentRulePO();

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

    /** Column name PO_DiscountSchema_ID */
    public static final String COLUMNNAME_PO_DiscountSchema_ID = "PO_DiscountSchema_ID";

	/** Set PO Discount Schema.
	  * Schema to calculate the purchase trade discount percentage
	  */
	public void setPO_DiscountSchema_ID (int PO_DiscountSchema_ID);

	/** Get PO Discount Schema.
	  * Schema to calculate the purchase trade discount percentage
	  */
	public int getPO_DiscountSchema_ID();

	public org.compiere.model.I_M_DiscountSchema getPO_DiscountSchema() throws RuntimeException;

    /** Column name PO_PaymentTerm_ID */
    public static final String COLUMNNAME_PO_PaymentTerm_ID = "PO_PaymentTerm_ID";

	/** Set PO Payment Term.
	  * Payment rules for a purchase order
	  */
	public void setPO_PaymentTerm_ID (int PO_PaymentTerm_ID);

	/** Get PO Payment Term.
	  * Payment rules for a purchase order
	  */
	public int getPO_PaymentTerm_ID();

	public org.compiere.model.I_C_PaymentTerm getPO_PaymentTerm() throws RuntimeException;

    /** Column name PO_PriceList_ID */
    public static final String COLUMNNAME_PO_PriceList_ID = "PO_PriceList_ID";

	/** Set Purchase Pricelist.
	  * Price List used by this Business Partner
	  */
	public void setPO_PriceList_ID (int PO_PriceList_ID);

	/** Get Purchase Pricelist.
	  * Price List used by this Business Partner
	  */
	public int getPO_PriceList_ID();

	public org.compiere.model.I_M_PriceList getPO_PriceList() throws RuntimeException;

    /** Column name POReference */
    public static final String COLUMNNAME_POReference = "POReference";

	/** Set Order Reference.
	  * Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	  */
	public void setPOReference (String POReference);

	/** Get Order Reference.
	  * Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	  */
	public String getPOReference();

    /** Column name Postal */
    public static final String COLUMNNAME_Postal = "Postal";

	/** Set ZIP.
	  * Postal code
	  */
	public void setPostal (String Postal);

	/** Get ZIP.
	  * Postal code
	  */
	public String getPostal();

    /** Column name PotentialLifeTimeValue */
    public static final String COLUMNNAME_PotentialLifeTimeValue = "PotentialLifeTimeValue";

	/** Set Potential Life Time Value.
	  * Total Revenue expected
	  */
	public void setPotentialLifeTimeValue (BigDecimal PotentialLifeTimeValue);

	/** Get Potential Life Time Value.
	  * Total Revenue expected
	  */
	public BigDecimal getPotentialLifeTimeValue();

    /** Column name Rating */
    public static final String COLUMNNAME_Rating = "Rating";

	/** Set Rating.
	  * Classification or Importance
	  */
	public void setRating (String Rating);

	/** Get Rating.
	  * Classification or Importance
	  */
	public String getRating();

    /** Column name ReferenceNo */
    public static final String COLUMNNAME_ReferenceNo = "ReferenceNo";

	/** Set Reference No.
	  * Your customer or vendor number at the Business Partner's site
	  */
	public void setReferenceNo (String ReferenceNo);

	/** Get Reference No.
	  * Your customer or vendor number at the Business Partner's site
	  */
	public String getReferenceNo();

    /** Column name RegionName */
    public static final String COLUMNNAME_RegionName = "RegionName";

	/** Set Region.
	  * Name of the Region
	  */
	public void setRegionName (String RegionName);

	/** Get Region.
	  * Name of the Region
	  */
	public String getRegionName();

    /** Column name SalesRep_ID */
    public static final String COLUMNNAME_SalesRep_ID = "SalesRep_ID";

	/** Set Sales Representative.
	  * Sales Representative or Company Agent
	  */
	public void setSalesRep_ID (int SalesRep_ID);

	/** Get Sales Representative.
	  * Sales Representative or Company Agent
	  */
	public int getSalesRep_ID();

	public org.compiere.model.I_AD_User getSalesRep() throws RuntimeException;

    /** Column name SalesVolume */
    public static final String COLUMNNAME_SalesVolume = "SalesVolume";

	/** Set Sales Volume in 1.000.
	  * Total Volume of Sales in Thousands of Currency
	  */
	public void setSalesVolume (int SalesVolume);

	/** Get Sales Volume in 1.000.
	  * Total Volume of Sales in Thousands of Currency
	  */
	public int getSalesVolume();

    /** Column name SendEMail */
    public static final String COLUMNNAME_SendEMail = "SendEMail";

	/** Set Send EMail.
	  * Enable sending Document EMail
	  */
	public void setSendEMail (boolean SendEMail);

	/** Get Send EMail.
	  * Enable sending Document EMail
	  */
	public boolean isSendEMail();

    /** Column name SetOpeningBalance */
    public static final String COLUMNNAME_SetOpeningBalance = "SetOpeningBalance";

	/** Set Set Opening Balance.
	  * Sets Opening Balance
	  */
	public void setSetOpeningBalance (String SetOpeningBalance);

	/** Get Set Opening Balance.
	  * Sets Opening Balance
	  */
	public String getSetOpeningBalance();

    /** Column name ShareOfCustomer */
    public static final String COLUMNNAME_ShareOfCustomer = "ShareOfCustomer";

	/** Set Share.
	  * Share of Customer's business as a percentage
	  */
	public void setShareOfCustomer (int ShareOfCustomer);

	/** Get Share.
	  * Share of Customer's business as a percentage
	  */
	public int getShareOfCustomer();

    /** Column name ShelfLifeMinPct */
    public static final String COLUMNNAME_ShelfLifeMinPct = "ShelfLifeMinPct";

	/** Set Min Shelf Life %.
	  * Minimum Shelf Life in percent based on Product Instance Guarantee Date
	  */
	public void setShelfLifeMinPct (int ShelfLifeMinPct);

	/** Get Min Shelf Life %.
	  * Minimum Shelf Life in percent based on Product Instance Guarantee Date
	  */
	public int getShelfLifeMinPct();

    /** Column name SO_CreditLimit */
    public static final String COLUMNNAME_SO_CreditLimit = "SO_CreditLimit";

	/** Set Credit Limit.
	  * Total outstanding invoice amounts allowed
	  */
	public void setSO_CreditLimit (BigDecimal SO_CreditLimit);

	/** Get Credit Limit.
	  * Total outstanding invoice amounts allowed
	  */
	public BigDecimal getSO_CreditLimit();

    /** Column name SO_CreditUsed */
    public static final String COLUMNNAME_SO_CreditUsed = "SO_CreditUsed";

	/** Set Credit Used.
	  * Current open balance
	  */
	public void setSO_CreditUsed (BigDecimal SO_CreditUsed);

	/** Get Credit Used.
	  * Current open balance
	  */
	public BigDecimal getSO_CreditUsed();

    /** Column name SO_Description */
    public static final String COLUMNNAME_SO_Description = "SO_Description";

	/** Set Order Description.
	  * Description to be used on orders
	  */
	public void setSO_Description (String SO_Description);

	/** Get Order Description.
	  * Description to be used on orders
	  */
	public String getSO_Description();

    /** Column name SOCreditStatus */
    public static final String COLUMNNAME_SOCreditStatus = "SOCreditStatus";

	/** Set Credit Status.
	  * Business Partner Credit Status
	  */
	public void setSOCreditStatus (String SOCreditStatus);

	/** Get Credit Status.
	  * Business Partner Credit Status
	  */
	public String getSOCreditStatus();

    /** Column name Std_Days */
    public static final String COLUMNNAME_Std_Days = "Std_Days";

	/** Set Standard Days	  */
	public void setStd_Days (BigDecimal Std_Days);

	/** Get Standard Days	  */
	public BigDecimal getStd_Days();

    /** Column name Std_Wage */
    public static final String COLUMNNAME_Std_Wage = "Std_Wage";

	/** Set Standard Wage	  */
	public void setStd_Wage (BigDecimal Std_Wage);

	/** Get Standard Wage	  */
	public BigDecimal getStd_Wage();

    /** Column name TaxID */
    public static final String COLUMNNAME_TaxID = "TaxID";

	/** Set Tax ID.
	  * Tax Identification
	  */
	public void setTaxID (String TaxID);

	/** Get Tax ID.
	  * Tax Identification
	  */
	public String getTaxID();

    /** Column name TF_CustomerType_ID */
    public static final String COLUMNNAME_TF_CustomerType_ID = "TF_CustomerType_ID";

	/** Set Customer Type	  */
	public void setTF_CustomerType_ID (int TF_CustomerType_ID);

	/** Get Customer Type	  */
	public int getTF_CustomerType_ID();

    /** Column name TF_TaxInvoiceCycle_ID */
    public static final String COLUMNNAME_TF_TaxInvoiceCycle_ID = "TF_TaxInvoiceCycle_ID";

	/** Set Tax Invoice Cycle	  */
	public void setTF_TaxInvoiceCycle_ID (int TF_TaxInvoiceCycle_ID);

	/** Get Tax Invoice Cycle	  */
	public int getTF_TaxInvoiceCycle_ID();

    /** Column name TotalOpenBalance */
    public static final String COLUMNNAME_TotalOpenBalance = "TotalOpenBalance";

	/** Set Open Balance.
	  * Total Open Balance Amount in primary Accounting Currency
	  */
	public void setTotalOpenBalance (BigDecimal TotalOpenBalance);

	/** Get Open Balance.
	  * Total Open Balance Amount in primary Accounting Currency
	  */
	public BigDecimal getTotalOpenBalance();

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

    /** Column name URL */
    public static final String COLUMNNAME_URL = "URL";

	/** Set URL.
	  * Full URL address - e.g. http://www.idempiere.org
	  */
	public void setURL (String URL);

	/** Get URL.
	  * Full URL address - e.g. http://www.idempiere.org
	  */
	public String getURL();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
