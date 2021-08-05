package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.FillMandatoryException;
import org.adempiere.exceptions.WarehouseLocatorConflictException;
import org.compiere.model.I_M_AttributeSet;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MLocator;
import org.compiere.model.MLocatorType;
import org.compiere.model.MProduct;
import org.compiere.model.MTable;
import org.compiere.model.MUOM;
import org.compiere.model.MWarehouse;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class TF_MInOutLine extends MInOutLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7570231307360187519L;

	public TF_MInOutLine(Properties ctx, int M_InOutLine_ID, String trxName) {
		super(ctx, M_InOutLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public TF_MInOutLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public TF_MInOutLine(MInOut inout) {
		super(inout);
		// TODO Auto-generated constructor stub
	}
	
	
	
	public static final String COLUMNNAME_TF_Fuel_Issue_ID = "TF_Fuel_Issue_ID";
	/** Set Fuel Issue.
	@param TF_Fuel_Issue_ID Fuel Issue	  */
	public void setTF_Fuel_Issue_ID (int TF_Fuel_Issue_ID)
	{
		if (TF_Fuel_Issue_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_Fuel_Issue_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_Fuel_Issue_ID, Integer.valueOf(TF_Fuel_Issue_ID));
	}
	
	/** Get Fuel Issue.
		@return Fuel Issue	  */
	public int getTF_Fuel_Issue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_Fuel_Issue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Column name Distance */
    public static final String COLUMNNAME_Distance = "Distance";
	/** Set Distance (km).
	@param Distance Distance (km)	  */
	public void setDistance (BigDecimal Distance)
	{
		set_Value (COLUMNNAME_Distance, Distance);
	}
	
	/** Get Distance (km).
		@return Distance (km)	  */
	public BigDecimal getDistance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Distance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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
	public static String COLUMNNAME_PM_Machinery_ID = "PM_Machinery_ID";
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
	
	public static String COLUMNNAME_C_Tax_ID = "C_Tax_ID";

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
			set_Value(COLUMNNAME_C_Tax_ID, null);
		else 
			set_Value(COLUMNNAME_C_Tax_ID, Integer.valueOf(C_Tax_ID));
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
	
	public static String COLUMNNAME_IsTaxIncluded = "IsTaxIncluded";
	/** Set Price includes Tax.
		@param IsTaxIncluded 
		Tax is included in the price 
	  */
	public void setIsTaxIncluded (boolean IsTaxIncluded)
	{
		set_ValueNoCheck (COLUMNNAME_IsTaxIncluded, Boolean.valueOf(IsTaxIncluded));
	}

	/** Get Price includes Tax.
		@return Tax is included in the price 
	  */
	public boolean isTaxIncluded () 
	{
		Object oo =  get_Value(COLUMNNAME_IsTaxIncluded);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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
	
    /** Column name RateMTKM */
    public static final String COLUMNNAME_RateMTKM = "RateMTKM";
	/** Set Rate / MT / KM.
		@param RateMTKM Rate / MT / KM	  */
	public void setRateMTKM (BigDecimal RateMTKM)
	{
		set_Value (COLUMNNAME_RateMTKM, RateMTKM);
	}

	/** Get Rate / MT / KM.
		@return Rate / MT / KM	  */
	public BigDecimal getRateMTKM () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_RateMTKM);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
	public static final String COLUMNNAME_RentMargin = "RentMargin";
	
	/** Set Rent Margin.
	@param RentMargin Rent Margin	  */
	public void setRentMargin (BigDecimal RentMargin)
	{
		set_Value (COLUMNNAME_RentMargin, RentMargin);
	}
	
	/** Get Rent Margin.
		@return Rent Margin	  */
	public BigDecimal getRentMargin () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_RentMargin);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
	public static final String COLUMNNAME_Price = "Price";
	
	/** Set Rent Margin.
	@param RentMargin Rent Margin	  */
	public void setPrice (BigDecimal Price)
	{
		set_Value (COLUMNNAME_Price, Price);
	}
	
	/** Get Rent Margin.
		@return Rent Margin	  */
	public BigDecimal getPrice() 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Price);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}


	public static final String COLUMNNAME_TF_LumpSumRent_Config_ID = "TF_LumpSumRent_Config_ID";
	/** Set TF_LumpSumRent_Config.
	@param TF_LumpSumRent_Config_ID TF_LumpSumRent_Config	  */
	public void setTF_LumpSumRent_Config_ID (int TF_LumpSumRent_Config_ID)
	{
		if (TF_LumpSumRent_Config_ID < 1) 
			set_Value(COLUMNNAME_TF_LumpSumRent_Config_ID, null);
		else 
			set_Value(COLUMNNAME_TF_LumpSumRent_Config_ID, Integer.valueOf(TF_LumpSumRent_Config_ID));
	}
	
	
	/** Get TF_LumpSumRent_Config.
		@return TF_LumpSumRent_Config	  */
	public int getTF_LumpSumRent_Config_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_LumpSumRent_Config_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		log.fine("");
		if (newRecord && getParent().isComplete()) {
			log.saveError("ParentComplete", Msg.translate(getCtx(), "M_InOutLine"));
			return false;
		}
		// Locator is mandatory if no charge is defined - teo_sarca BF [ 2757978 ]
		if(getProduct() != null && MProduct.PRODUCTTYPE_Item.equals(getProduct().getProductType()))
		{
			if (getM_Locator_ID() <= 0 && getC_Charge_ID() <= 0)
			{
				throw new FillMandatoryException(COLUMNNAME_M_Locator_ID);
			}
		}

		//	Get Line No
		if (getLine() == 0)
		{
			String sql = "SELECT COALESCE(MAX(Line),0)+10 FROM M_InOutLine WHERE M_InOut_ID=?";
			int ii = DB.getSQLValueEx (get_TrxName(), sql, getM_InOut_ID());
			setLine (ii);
		}
		//	UOM
		if (getC_UOM_ID() == 0)
			setC_UOM_ID (Env.getContextAsInt(getCtx(), "#C_UOM_ID"));
		if (getC_UOM_ID() == 0)
		{
			int C_UOM_ID = MUOM.getDefault_UOM_ID(getCtx());
			if (C_UOM_ID > 0)
				setC_UOM_ID (C_UOM_ID);
		}
		//	Qty Precision
		if (newRecord || is_ValueChanged("QtyEntered"))
			setQtyEntered(getQtyEntered());
		if (newRecord || is_ValueChanged("MovementQty"))
			setMovementQty(getMovementQty());

		//	Order/RMA Line
		//if (getC_OrderLine_ID() == 0 && getM_RMALine_ID() == 0)
		//{
		//	if (getParent().isSOTrx())
		//	{
		//		log.saveError("FillMandatory", Msg.translate(getCtx(), "C_Order_ID"));
		//		return false;
		//	}
		//}

		// Validate Locator/Warehouse - teo_sarca, BF [ 2784194 ]
		if (getM_Locator_ID() > 0)
		{
			MLocator locator = MLocator.get(getCtx(), getM_Locator_ID());
			if (getM_Warehouse_ID() != locator.getM_Warehouse_ID())
			{
				//throw new WarehouseLocatorConflictException(
				//		MWarehouse.get(getCtx(), getM_Warehouse_ID()),
				//		locator,
				//		getLine());
			}

	        // IDEMPIERE-2668
			if (MInOut.MOVEMENTTYPE_CustomerShipment.equals(getParent().getMovementType())) {
	        	if (locator.getM_LocatorType_ID() > 0) {
	        		MLocatorType lt = MLocatorType.get(getCtx(), locator.getM_LocatorType_ID());
	        		if (! lt.isAvailableForShipping()) {
	    				log.saveError("Error", Msg.translate(getCtx(), "LocatorNotAvailableForShipping"));
	    				return false;
	        		}
	        	}
	        }
	        
		}
		I_M_AttributeSet attributeset = null;
		if (getM_Product_ID() > 0)
			attributeset = MProduct.get(getCtx(), getM_Product_ID()).getM_AttributeSet();
		boolean isAutoGenerateLot = false;
		if (attributeset != null)
			isAutoGenerateLot = attributeset.isAutoGenerateLot();
		if (getReversalLine_ID() == 0 && !getParent().isSOTrx() && !getParent().getMovementType().equals(MInOut.MOVEMENTTYPE_VendorReturns) && isAutoGenerateLot
				&& getM_AttributeSetInstance_ID() == 0)
		{
			MAttributeSetInstance asi = MAttributeSetInstance.generateLot(getCtx(), (MProduct)getM_Product(), get_TrxName());
			setM_AttributeSetInstance_ID(asi.getM_AttributeSetInstance_ID());
		}
	//	if (getC_Charge_ID() == 0 && getM_Product_ID() == 0)
	//		;

		/**	 Qty on instance ASI
		if (getM_AttributeSetInstance_ID() != 0)
		{
			MProduct product = getProduct();
			int M_AttributeSet_ID = product.getM_AttributeSet_ID();
			boolean isInstance = M_AttributeSet_ID != 0;
			if (isInstance)
			{
				MAttributeSet mas = MAttributeSet.get(getCtx(), M_AttributeSet_ID);
				isInstance = mas.isInstanceAttribute();
			}
			//	Max
			if (isInstance)
			{
				MStorage storage = MStorage.get(getCtx(), getM_Locator_ID(),
					getM_Product_ID(), getM_AttributeSetInstance_ID(), get_TrxName());
				if (storage != null)
				{
					BigDecimal qty = storage.getQtyOnHand();
					if (getMovementQty().compareTo(qty) > 0)
					{
						log.warning("Qty - Stock=" + qty + ", Movement=" + getMovementQty());
						log.saveError("QtyInsufficient", "=" + qty);
						return false;
					}
				}
			}
		}	/**/

		/* Carlos Ruiz - globalqss
		 * IDEMPIERE-178 Orders and Invoices must disallow amount lines without product/charge
		 */
		if (getParent().getC_DocType().isChargeOrProductMandatory()) {
			if (getC_Charge_ID() == 0 && getM_Product_ID() == 0) {
				log.saveError("FillMandatory", Msg.translate(getCtx(), "ChargeOrProductMandatory"));
				return false;
			}
		}
		
		return true;
	}

}
