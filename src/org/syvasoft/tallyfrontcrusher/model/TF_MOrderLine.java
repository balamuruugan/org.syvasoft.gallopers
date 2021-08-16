package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTable;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class TF_MOrderLine extends MOrderLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5835318473934629869L;

	public TF_MOrderLine(MOrder order) {
		super(order);
		// TODO Auto-generated constructor stub
	}
	
	public static String COLUMNNAME_QtyIssued = "QtyIssued";
	/** Set Quantity Issued.
	@param QtyIssued Quantity Issued	  */
	public void setQtyIssued (BigDecimal QtyIssued)
	{
		set_Value (COLUMNNAME_QtyIssued, QtyIssued);
	}
	
	/** Get Quantity Issued.
		@return Quantity Issued	  */
	public BigDecimal getQtyIssued () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyIssued);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public static String COLUMNNAME_PM_Machinery_ID   = "PM_Machinery_ID";
	/** Set Machinery.
	@param PM_Machinery_ID Machinery	  */
	public void setPM_Machinery_ID (int PM_Machinery_ID)
	{
		if (PM_Machinery_ID < 1) 
			set_Value (COLUMNNAME_PM_Machinery_ID, null);
		else 
			set_Value (COLUMNNAME_PM_Machinery_ID, Integer.valueOf(PM_Machinery_ID));
	}
	
	/** Get Machinery.
		@return Machinery	  */
	public int getPM_Machinery_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PM_Machinery_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name FreightUOM_ID */
    public static final String COLUMNNAME_FreightUOM_ID = "FreightUOM_ID";
    
	public void setFreightUOM_ID (int FreightUOM_ID)
	{
		if (FreightUOM_ID < 1) 
			set_Value (COLUMNNAME_FreightUOM_ID, null);
		else 
			set_Value (COLUMNNAME_FreightUOM_ID, Integer.valueOf(FreightUOM_ID));
	}

	public int getFreightUOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FreightUOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Column name TF_WeighmentEntry_ID */
    public static final String COLUMNNAME_TF_WeighmentEntry_ID = "TF_WeighmentEntry_ID";
    /** Set Weighment Entry.
	@param TF_WeighmentEntry_ID Weighment Entry	  */
	public void setTF_WeighmentEntry_ID (int TF_WeighmentEntry_ID)
	{
		if (TF_WeighmentEntry_ID < 1) 
			set_Value (COLUMNNAME_TF_WeighmentEntry_ID, null);
		else 
			set_Value (COLUMNNAME_TF_WeighmentEntry_ID, Integer.valueOf(TF_WeighmentEntry_ID));
	}
	
	/** Get Weighment Entry.
		@return Weighment Entry	  */
	public int getTF_WeighmentEntry_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_WeighmentEntry_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	
	public TF_MOrderLine(Properties ctx, int C_OrderLine_ID, String trxName) {
		super(ctx, C_OrderLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public TF_MOrderLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public static final String COLUMNNAME_DeliveryContact = "DeliveryContact";
	
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

	public static final String COLUMNNAME_CustomerTransporter = "CustomerTransporter";
	
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
	

	public static final String COLUMNNAME_ContactPerson = "ContactPerson";
	
	/** Set Contact Person.
	@param ContactPerson Contact Person	  */
	public void setContactPerson (String ContactPerson)
	{
		set_Value (COLUMNNAME_ContactPerson, ContactPerson);
	}
	
	/** Get Contact Person.
		@return Contact Person	  */
	public String getContactPerson () 
	{
		return (String)get_Value(COLUMNNAME_ContactPerson);
	}

	/** Column name IsUpdatePrice */
    public static final String COLUMNNAME_IsUpdatePrice = "IsUpdatePrice";
    /** Set Update Price.
	@param IsUpdatePrice 
	Update Price into Price List
	  */
	public void setIsUpdatePrice (boolean IsUpdatePrice)
	{
		set_Value (COLUMNNAME_IsUpdatePrice, Boolean.valueOf(IsUpdatePrice));
	}
	
	/** Get Update Price.
		@return Update Price into Price List
	  */
	public boolean isUpdatePrice () 
	{
		Object oo = get_Value(COLUMNNAME_IsUpdatePrice);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
	
	/** Column name IsPermitSales */
    public static final String COLUMNNAME_IsPermitSales = "IsPermitSales";
    /** Set Permit Sales.
	@param IsPermitSales Permit Sales	  */
	public void setIsPermitSales (boolean IsPermitSales)
	{
		set_Value (COLUMNNAME_IsPermitSales, Boolean.valueOf(IsPermitSales));
	}
	
	/** Get Permit Sales.
		@return Permit Sales	  */
	public boolean isPermitSales () 
	{
		Object oo = get_Value(COLUMNNAME_IsPermitSales);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Column name BucketQty */
    public static final String COLUMNNAME_BucketQty = "BucketQty";
    /** Set Bucket Qty.
	@param BucketQty Bucket Qty	  */
	public void setBucketQty (BigDecimal BucketQty)
	{
		set_Value (COLUMNNAME_BucketQty, BucketQty);
	}
	
	/** Get Bucket Qty.
		@return Bucket Qty	  */
	public BigDecimal getBucketQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_BucketQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
	/** Column name TonePerBucket */
    public static final String COLUMNNAME_TonePerBucket = "TonePerBucket";
    /** Set Tone (per Bucket).
	@param TonePerBucket Tone (per Bucket)	  */
	public void setTonePerBucket (BigDecimal TonePerBucket)
	{
		set_Value (COLUMNNAME_TonePerBucket, TonePerBucket);
	}
	
	/** Get Tone (per Bucket).
		@return Tone (per Bucket)	  */
	public BigDecimal getTonePerBucket () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TonePerBucket);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
    /** Column name BucketRate */
    public static final String COLUMNNAME_BucketRate = "BucketRate";
    /** Set Bucket Rate.
	@param BucketRate Bucket Rate	  */
	public void setBucketRate (BigDecimal BucketRate)
	{
		set_Value (COLUMNNAME_BucketRate, BucketRate);
	}
	
	/** Column name SandType */
    public static final String COLUMNNAME_SandType = "SandType";
    /** Permit Sand = PM */
	public static final String SANDTYPE_PermitSand = "PM";
	/** Extra Bucket = EX */
	public static final String SANDTYPE_ExtraBucket = "EX";
	/** Without Permit = WP */
	public static final String SANDTYPE_WithoutPermit = "WP";
	/** Set Sand Type.
		@param SandType Sand Type	  */
	public void setSandType (String SandType)
	{

		set_Value (COLUMNNAME_SandType, SandType);
	}

	/** Get Sand Type.
		@return Sand Type	  */
	public String getSandType () 
	{
		return (String)get_Value(COLUMNNAME_SandType);
	}

	
	/** Get Bucket Rate.
		@return Bucket Rate	  */
	public BigDecimal getBucketRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_BucketRate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Column name TotalLoad */
    public static final String COLUMNNAME_TotalLoad = "TotalLoad";
    /** Set Total Load.
	@param TotalLoad Total Load	  */
	public void setTotalLoad (BigDecimal TotalLoad)
	{
		set_Value (COLUMNNAME_TotalLoad, TotalLoad);
	}
	
	/** Column name TF_VehicleType_ID */
    public static final String COLUMNNAME_TF_VehicleType_ID = "TF_VehicleType_ID";
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
	
	/** Get Total Load.
		@return Total Load	  */
	public BigDecimal getTotalLoad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalLoad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

    /** Column name UnitPrice */
    public static final String COLUMNNAME_UnitPrice = "UnitPrice";
    
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

	/** Column name TF_Destination_ID */
    public static final String COLUMNNAME_TF_Destination_ID = "TF_Destination_ID";
    
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

	/** Column name IsTaxIncluded */
    public static final String COLUMNNAME_IsTaxIncluded = "IsTaxIncluded";
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
	

	/** Column name IsRentInclusive */
    public static final String COLUMNNAME_IsRentInclusive = "IsRentInclusive";
    
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

    /** Column name IsRoyaltyPassInclusive */
    public static final String COLUMNNAME_IsRoyaltyPassInclusive = "IsRoyaltyPassInclusive";
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
	
	public static final String COLUMNNAME_IsPriceConfidential = "IsPriceConfidential";
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
	
    /** Column name M_Locator_ID */
    public static final String COLUMNNAME_M_Locator_ID = "M_Locator_ID";
	public org.compiere.model.I_M_Locator getM_Locator() throws RuntimeException
    {
		return (org.compiere.model.I_M_Locator)MTable.get(getCtx(), org.compiere.model.I_M_Locator.Table_Name)
			.getPO(getM_Locator_ID(), get_TrxName());	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1) 
			set_Value (COLUMNNAME_M_Locator_ID, null);
		else 
			set_Value (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {		
		Boolean ok = super.afterSave(newRecord, success);
		updateOrderTonnage();
		if(isUpdatePrice()) {
			TF_MOrder.updateProductPricing(getM_Product_ID(), getC_Order().getM_PriceList_ID(), getC_BPartner_ID(), 
					getQtyEntered(), getPriceEntered(), getC_Order().getDateOrdered(), getC_Order().isSOTrx());
		}
		return ok;
	}	
	
    /** Column name Barcode */
    public static final String COLUMNNAME_Barcode = "Barcode";
    
	/** Set Barcode.
	@param Barcode Barcode	  */
	public void setBarcode (String Barcode)
	{
		set_Value (COLUMNNAME_Barcode, Barcode);
	}
	
	/** Get Barcode.
		@return Barcode	  */
	public String getBarcode () 
	{
		return (String)get_Value(COLUMNNAME_Barcode);
	}

	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		setQtyReserved(BigDecimal.ZERO);
		//everytime price list price will be updated with current price
		TF_MOrder.addProductPricingIfNot(getM_Product_ID(), getC_Order().getM_PriceList_ID(), getC_BPartner_ID(), 
				getQtyEntered(), getPriceEntered(), getC_Order().getDateOrdered(), getC_Order().isSOTrx());
		int C_UOM_ID = getC_UOM_ID();
		if(is_ValueChanged(COLUMNNAME_QtyEntered)) {
			setQtyOrdered(getQtyEntered());
		}
		
		
		boolean success = super.beforeSave(newRecord);
		setC_UOM_ID(C_UOM_ID);
		
			
		TF_MBPartner bp = new TF_MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
		
		if(bp != null) {
			if(isPriceConfidential() && !bp.get_ValueAsBoolean(COLUMNNAME_IsPriceConfidential)) {
				bp.set_ValueOfColumn(COLUMNNAME_IsPriceConfidential, isPriceConfidential());
				bp.saveEx();
			}
		}
		return success;
	}

	@Override
	protected boolean beforeDelete() {
		setQtyReserved(BigDecimal.ZERO);
		return super.beforeDelete();
	}

	@Override
	protected boolean afterDelete(boolean success) {		
		boolean ok =  super.afterDelete(success);
		updateOrderTonnage();
		return ok;
	}

	private void updateOrderTonnage() {		
		
		int tonnage_uom_id = MSysConfig.getIntValue("TONNAGE_UOM", 1000069, getAD_Client_ID());
		int kg_uom_id = MSysConfig.getIntValue("KG_UOM", 1000070, getAD_Client_ID());
		String sql = " SELECT SUM(CASE WHEN C_UOM_ID = ? THEN QtyEntered/1000 ELSE QtyEntered END) FROM C_OrderLine WHERE C_Order_ID=? AND C_UOM_ID IN (?,?)";
		BigDecimal tonnage = DB.getSQLValueBD(get_TrxName(), sql, kg_uom_id, getC_Order_ID(), tonnage_uom_id,kg_uom_id);
		if(tonnage == null)
			tonnage = BigDecimal.ZERO;
		
		sql = "UPDATE C_Order SET Tonnage = " + tonnage.doubleValue() + " WHERE C_Order_ID = " + getC_Order_ID();
		DB.executeUpdate(sql, get_TrxName());
				
		//sql = "UPDATE C_Order SET Rent_Amt = Distance * Rate WHERE C_Order_ID = " + getC_Order_ID()
		//	+ " AND TF_Destination_ID IS NOT NULL AND TF_RentedVehicle_ID IS NOT NULL AND IsLumpSumRent='N'";
		//DB.executeUpdate(sql, get_TrxName());
	}

	
}
