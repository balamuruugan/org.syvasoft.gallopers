package org.syvasoft.tallyfrontcrusher.model;

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
