package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MPriceList;
import org.compiere.model.MSysConfig;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.syvasoft.tallyfrontcrusher.model.MDestination;
import org.syvasoft.tallyfrontcrusher.model.MGLPostingConfig;
import org.syvasoft.tallyfrontcrusher.model.MLumpSumRentConfig;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.MRentedVehicle;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;
import org.syvasoft.tallyfrontcrusher.model.TF_MInOut;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class CreatePurchaseEntryFromWeighment extends SvrProcess {

	Savepoint sp = null;
	private int C_DocType_ID = 1000050;
	private int RecordId = 0;
	
	@Override
	protected void prepare() {		
		RecordId = getRecord_ID();
	}

	protected String doIt() throws Exception {
		//String whereClause = " AD_Org_ID = ? AND TRUNC(GrossWeightTime) >= ? AND TRUNC(GrossWeightTime) <= ? AND "
		boolean createConsolidatedPurchaseInvoice = true;
		
		/*String whereClause = " WeighmentEntryType = '2PO' AND Status = 'CO' AND Processed='N' "
				+ " AND (SELECT M_Product_Category_ID FROM M_Product WHERE M_Product.M_Product_ID=TF_WeighmentEntry.M_Product_ID)=1000050"
				+ " AND NOT EXISTS(SELECT C_Order.TF_WeighmentEntry_ID FROM C_Order WHERE "
				+ "C_Order.TF_WeighmentEntry_ID =  TF_WeighmentEntry.TF_WeighmentEntry_ID)"
				+ " AND NOT EXISTS(SELECT M_InOut.TF_WeighmentEntry_ID FROM M_InOut WHERE "
				+ " M_InOut.TF_WeighmentEntry_ID =  TF_WeighmentEntry.TF_WeighmentEntry_ID AND M_InOut.DocStatus IN ('CO','CL'))";*/
		
		String whereClause = " WeighmentEntryType = '2PO' AND ((Status = 'CO' "
				+ " AND NOT EXISTS(SELECT C_Order.TF_WeighmentEntry_ID FROM C_Order WHERE C_Order.TF_WeighmentEntry_ID =  TF_WeighmentEntry.TF_WeighmentEntry_ID))"
				+ " OR (TF_WeighmentEntry_ID = ? AND TF_WeighmentEntry.Status IN ('UR','CO')))";
				
		int i = 0;
		List<MWeighmentEntry> wEntries = new Query(getCtx(), MWeighmentEntry.Table_Name, whereClause, get_TrxName())
				.setClient_ID().setParameters(RecordId).list();
		for(MWeighmentEntry wEntry : wEntries) {
			Trx trx = Trx.get(get_TrxName(), false);
			
			try {
				createPurchaseQuickEntry(wEntry, trx);
				
				if(wEntry.isIncludePassAmtInvoice()) {
					if(wEntry.getPermitPassAmount().doubleValue() > 0) {
						createPurchaseQuickEntryForRoyaltyPass(wEntry, wEntry.getPermitIssuedQty(), true, trx);
					}
				}
				i++;
			}
			catch (Exception ex) {
				if(sp != null)
					trx.rollback(sp);
				String desc = wEntry.getDescription();
				if(desc == null)
					desc = "";
				if(!desc.contains("ERROR:")) {
					wEntry.setDescription(desc + 
							" | ERROR: " + ex.getMessage());					
				}					
				wEntry.saveEx();
				addLog(wEntry.get_Table_ID(), wEntry.getGrossWeightTime(), null, ex.getMessage(), wEntry.get_Table_ID(), wEntry.get_ID());
			}
			
		}
		return i + " Weighment Entries are processed!";
	}
	
	private void createPurchaseQuickEntry(MWeighmentEntry wEntry, Trx trx) throws Exception {
		sp = trx.setSavepoint(wEntry.getDocumentNo());
		
		int C_BParner_ID = wEntry.getC_BPartner_ID();
		if(C_BParner_ID == 0)
			C_BParner_ID = 1000020;		
		TF_MBPartner bp = new TF_MBPartner(getCtx(), C_BParner_ID, get_TrxName());
		TF_MOrder ord = new TF_MOrder(getCtx(), 0, get_TrxName());
		ord.setAD_Org_ID(wEntry.getAD_Org_ID());
		ord.setC_DocTypeTarget_ID(wEntry.getC_DocType_ID(wEntry.getWeighmentEntryType()));
		ord.setC_DocType_ID(wEntry.getC_DocType_ID(wEntry.getWeighmentEntryType()));
		ord.setM_Warehouse_ID(wEntry.getM_Warehouse_ID());
		ord.setDateAcct(wEntry.getGrossWeightTime());
		ord.setDateOrdered(wEntry.getGrossWeightTime());
		ord.setBPartner(bp);
		ord.setDescription(wEntry.getDescription());				
		ord.setPaymentRule(wEntry.getPaymentRule());		
		//Price List
		int m_M_PriceList_ID = MPriceList.getDefault(getCtx(), false).getM_PriceList_ID();
		if(bp.getPO_PriceList_ID() > 0)
			m_M_PriceList_ID = bp.getPO_PriceList_ID();			
		ord.setM_PriceList_ID(m_M_PriceList_ID);
		ord.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
		ord.setIsSOTrx(false);
		ord.setTF_WeighmentEntry_ID(wEntry.getTF_WeighmentEntry_ID());	
		ord.setTF_Destination_ID(wEntry.getTF_Destination_ID());
		ord.setVehicleNo(wEntry.getVehicleNo());
		ord.setTF_RentedVehicle_ID(wEntry.getTF_RentedVehicle_ID());
		ord.setItem1_BucketQty(null);
		ord.setTF_Send_To(wEntry.getTF_Send_To());
		ord.setTF_ProductionPlant_ID(wEntry.getTF_ProductionPlant_ID());
		ord.setTF_BlueMetal_Type(wEntry.getTF_BlueMetal_Type());
		
		//Item
		ord.setItem1_IsPermitSales(wEntry.isHasBalance());
		ord.setItem1_VehicleType_ID(wEntry.getTF_VehicleType_ID());
		if(wEntry.isHasBalance())
			ord.setItem1_SandType(TF_MOrder.ITEM1_SANDTYPE_PermitSand);
		else
			ord.setItem1_SandType(TF_MOrder.ITEM1_SANDTYPE_WithoutPermit);
		ord.setItem1_ID(wEntry.getM_Product_ID());
		
		ord.setItem1_UOM_ID(wEntry.getC_UOM_ID());
		ord.setItem1_Tax_ID(wEntry.getC_Tax_ID());
		BigDecimal qty = wEntry.getNetWeightUnit();
		ord.setItem1_TotalLoad(BigDecimal.ONE);
		ord.setItem1_PermitIssued(wEntry.getPermitIssuedQty()); 
		ord.setMDPNo(wEntry.getMDPNo());
		ord.setItem1_Qty(qty);
		
		MWarehouse wh = (MWarehouse) wEntry.getM_Warehouse();
		ord.setM_Locator_ID(wh.getDefaultLocator().get_ID());
		
		//Get price from Purchase Price List by UOM
		BigDecimal price = wEntry.getMaterialPriceIncludedRent();
		price = wEntry.getMaterialPriceIncludedRoyaltyPass(price);
		
		ord.setItem1_Price(price);
		ord.setItem1_UnitPrice(price);
		ord.setItem1_Amt(ord.getItem1_Qty().multiply(ord.getItem1_Price()));
				
		ord.saveEx();				
				
		ord.setDocAction(DocAction.ACTION_Complete);
		ord.completeIt();
		ord.setDocStatus(TF_MOrder.DOCSTATUS_Completed);
		ord.saveEx();
		
		trx.releaseSavepoint(sp);
		addLog(ord.get_Table_ID(), ord.getCreated(), null, ord.getDocumentNo() + " is created!", ord.get_Table_ID(), ord.get_ID());
		wEntry.setStatus(MWeighmentEntry.STATUS_Billed);
		wEntry.saveEx();
	}
	
	private void createPurchaseQuickEntryForRoyaltyPass(MWeighmentEntry wEntry, BigDecimal billedQty, boolean firstInvoice, Trx trx) throws Exception {
		TF_MOrder ord = new TF_MOrder(getCtx(), 0, get_TrxName());
		ord.setAD_Org_ID(wEntry.getAD_Org_ID());
		ord.setC_DocType_ID(wEntry.getRoyaltyPass_DocType_ID());
		ord.setC_DocTypeTarget_ID(wEntry.getRoyaltyPass_DocType_ID());
		ord.setM_Warehouse_ID(wEntry.getM_Warehouse_ID());
		ord.setDateAcct(wEntry.getGrossWeightTime());
		ord.setDateOrdered(wEntry.getGrossWeightTime());
		int C_BParner_ID = wEntry.getC_BPartner_ID();
		if(C_BParner_ID == 0)
			C_BParner_ID = 1000020;		
		TF_MBPartner bp = new TF_MBPartner(getCtx(), C_BParner_ID, get_TrxName());
		ord.setBPartner(bp);
		ord.setPartyName(wEntry.getPartyName());
		ord.setPhone(wEntry.getPhone());
		ord.setDescription(wEntry.getDescription());
		if(wEntry.getPartyName() != null && bp.getIsPOSCashBP())
			ord.addDescription("Customer Name : " + wEntry.getPartyName());
		
		ord.setPaymentRule(wEntry.getPaymentRule());
		ord.setOnAccount(false);

		//Price List
		int m_M_PriceList_ID = MPriceList.getDefault(getCtx(), true).getM_PriceList_ID();							
		ord.setM_PriceList_ID(m_M_PriceList_ID);
		ord.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
		ord.setIsSOTrx(false);
		ord.setTF_WeighmentEntry_ID(wEntry.getTF_WeighmentEntry_ID());	
		ord.setTF_Destination_ID(wEntry.getTF_Destination_ID());
		ord.setVehicleNo(wEntry.getVehicleNo());
		ord.setTF_RentedVehicle_ID(wEntry.getTF_RentedVehicle_ID());
		
		//Item
		ord.setItem1_VehicleType_ID(wEntry.getTF_VehicleType_ID());
		ord.setItem1_ID(wEntry.getM_Product_Pass_ID());
		
		TF_MProduct product = new TF_MProduct(getCtx(),wEntry.getM_Product_Pass_ID(),get_TrxName());
		
		int uom_id = product.getC_UOM_ID();
		
		ord.setItem1_UOM_ID(product.getC_UOM_ID());
		ord.setItem1_Tax_ID(product.getTax_ID(false, bp.isInterState()));
		BigDecimal qty = wEntry.getPermitIssuedQty();
		if(billedQty != null)
			qty = billedQty;
		
		if(qty.doubleValue() == 0)
			throw new AdempiereException("Invalid Billing Qty!");
		
		ord.setTonnage(qty);
		ord.setItem1_TotalLoad(BigDecimal.ONE);
		
		ord.setItem1_Qty(qty);
		BigDecimal price = wEntry.getPassPricePerUnit();
		ord.setItem1_Price(price);
		ord.setItem1_UnitPrice(price);
		ord.setItem1_Amt(ord.getItem1_Qty().multiply(ord.getItem1_Price()));			
	
		ord.setProcessed(false);		
		ord.saveEx();				
		
		sp = trx.setSavepoint(wEntry.getDocumentNo());
		ord.setDocAction(DocAction.ACTION_Complete);
		ord.completeIt();
		ord.setDocStatus(TF_MOrder.DOCSTATUS_Completed);
		ord.saveEx();
	
		trx.releaseSavepoint(sp);
		addLog(ord.get_Table_ID(), ord.getCreated(), null, " Purchase Entry : " + ord.getDocumentNo() + " is created!", ord.get_Table_ID(), ord.get_ID());
	}
}
