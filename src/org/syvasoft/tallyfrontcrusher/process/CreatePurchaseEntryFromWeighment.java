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

public class CreatePurchaseEntryFromWeighment extends SvrProcess {

	private int C_DocType_ID = 1000050;
	
	@Override
	protected void prepare() {		

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
		
		String whereClause = " WeighmentEntryType = '2PO' AND Status = 'CO' AND EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE " +
				" T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID = TF_WeighmentEntry.TF_WeighmentEntry_ID) "
				+ " AND NOT EXISTS(SELECT C_Order.TF_WeighmentEntry_ID FROM C_Order WHERE "
				+ "C_Order.TF_WeighmentEntry_ID =  TF_WeighmentEntry.TF_WeighmentEntry_ID)"
				+ " AND NOT EXISTS(SELECT M_InOut.TF_WeighmentEntry_ID FROM M_InOut WHERE "
				+ " M_InOut.TF_WeighmentEntry_ID =  TF_WeighmentEntry.TF_WeighmentEntry_ID AND M_InOut.DocStatus IN ('CO','CL'))";
		int i = 0;
		List<MWeighmentEntry> wEntries = new Query(getCtx(), MWeighmentEntry.Table_Name, whereClause, get_TrxName())
				.setClient_ID().setParameters(getAD_PInstance_ID()).list();
		for(MWeighmentEntry wEntry : wEntries) {
			Trx trx = Trx.get(get_TrxName(), false);
			int C_BParner_ID = wEntry.getC_BPartner_ID();
			if(C_BParner_ID == 0)
				C_BParner_ID = 1000020;		
			TF_MBPartner bp = new TF_MBPartner(getCtx(), C_BParner_ID, get_TrxName());
			createConsolidatedPurchaseInvoice = MSysConfig.getBooleanValue("CONSOLIDATED_PURCHASE_INVOICE_ENABLED", true , getAD_Client_ID(), wEntry.getAD_Org_ID());
			Savepoint sp = null;
			try {
				if(!createConsolidatedPurchaseInvoice) {
					TF_MOrder ord = new TF_MOrder(getCtx(), 0, get_TrxName());
					ord.setAD_Org_ID(wEntry.getAD_Org_ID());
					ord.setC_DocTypeTarget_ID(C_DocType_ID);
					ord.setC_DocType_ID(C_DocType_ID);
					ord.setM_Warehouse_ID(wEntry.getM_Warehouse_ID());
					ord.setDateAcct(wEntry.getGrossWeightTime());
					ord.setDateOrdered(wEntry.getGrossWeightTime());
					ord.setBPartner(bp);
					ord.setDescription(wEntry.getDescription());				
					ord.setPaymentRule(wEntry.getPaymentRule());		
					//Price List
					int m_M_PriceList_ID = Env.getContextAsInt(getCtx(), "#M_PriceList_ID");
					if(bp.getM_PriceList_ID() > 0)
						m_M_PriceList_ID = bp.getM_PriceList_ID();			
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
					
					int tonnage_uom_id = MSysConfig.getIntValue("TONNAGE_UOM", 1000069, Env.getAD_Client_ID(getCtx()));
					int uom_id = wEntry.getM_Product().getC_UOM_ID();
					ord.setItem1_UOM_ID(ord.getItem1().getC_UOM_ID());
					ord.setItem1_Tax_ID(1000000);
					BigDecimal qty = wEntry.getNetWeightUnit();
					if(uom_id == tonnage_uom_id)
						qty = qty.divide(new BigDecimal(1000));
					ord.setItem1_TotalLoad(BigDecimal.ONE);
					ord.setItem1_PermitIssued(wEntry.getPermitIssuedQty()); 
					ord.setMDPNo(wEntry.getMDPNo());
					ord.setItem1_Qty(qty);
					
					//Get price from Purchase Price List by UOM
					BigDecimal price =BigDecimal.ZERO;
					
					MPriceListUOM pprice = MPriceListUOM.getPriceListUOM(getCtx(), wEntry.getM_Product_ID(),
							ord.getItem1_UOM_ID(), bp.getC_BPartner_ID(),0, false, wEntry.getGrossWeightTime());
					if( pprice == null)
						throw new AdempiereException("Please configure the Purchase Price!");
					
					MWarehouse wh = (MWarehouse) wEntry.getM_Warehouse();
					
					ord.setM_Locator_ID(wh.getDefaultLocator().get_ID());
					//price = pprice.getPrice();
					price = wEntry.getPrice();
					
					ord.setItem1_Price(price);
					ord.setItem1_UnitPrice(price);
					ord.setItem1_Amt(ord.getItem1_Qty().multiply(ord.getItem1_Price()));
					ord.setCreateTransporterInvoice(!pprice.isRentInclusive());
					
					//Setting Transporter Charge
					if(!pprice.isRentInclusive() && ord.getTF_RentedVehicle_ID() > 0) {					
						MDestination dest = new MDestination(getCtx(), ord.getTF_Destination_ID(), get_TrxName());
						MRentedVehicle rv = new MRentedVehicle(getCtx(), ord.getTF_RentedVehicle_ID(), get_TrxName());
						int Vendor_ID = rv.getC_BPartner_ID();					
						BigDecimal RateMT = MLumpSumRentConfig.getRateMT(getCtx(), ord.getAD_Org_ID(), Vendor_ID, ord.getC_BPartner_ID(), ord.getItem1_ID(), 
								ord.getTF_Destination_ID(), ord.getItem1_VehicleType_ID(), dest.getDistance(), get_TrxName());
						BigDecimal RateKM = MLumpSumRentConfig.getRateKm(getCtx(), ord.getAD_Org_ID(), Vendor_ID, ord.getC_BPartner_ID(), ord.getItem1_ID(),
								ord.getTF_Destination_ID(), ord.getItem1_VehicleType_ID(), dest.getDistance(), get_TrxName());
						BigDecimal RateMTKM = MLumpSumRentConfig.getRateMTKm(getCtx(), ord.getAD_Org_ID(), Vendor_ID, ord.getC_BPartner_ID(),
								ord.getItem1_ID(), ord.getTF_Destination_ID(), ord.getItem1_VehicleType_ID(), dest.getDistance(), get_TrxName());
						BigDecimal RentAmt = BigDecimal.ZERO;
						
						if(RateMT.doubleValue() > 0) {
							ord.setRate(RateMT);						
							RentAmt = RateMT.multiply(qty);				
						}
						else if(RateKM.doubleValue() > 0) {
							ord.setRate(RateKM);
							RentAmt = RateKM.multiply(dest.getDistance());
						}
						else if(RateMTKM.doubleValue() > 0) {
							ord.setRate(RateMTKM);
							RentAmt = RateMTKM.multiply(ord.getDistance()).multiply(qty);
						}
						else {								
							RentAmt=MLumpSumRentConfig.getLumpSumRent(getCtx(),ord.getAD_Org_ID(),Vendor_ID, ord.getC_BPartner_ID(), 
									ord.getItem1_ID(), ord.getTF_Destination_ID(), ord.getItem1_VehicleType_ID(), dest.getDistance(), get_TrxName());
							if(RentAmt.doubleValue() > 0)
								ord.setIsLumpSumRent(true);
						}
						
						
						ord.setRent_Amt(RentAmt);										
						ord.setRentMargin(BigDecimal.ZERO);
						ord.setRentPayable(RentAmt);
						
					}
					
					ord.saveEx();				
					
					sp = trx.setSavepoint(wEntry.getDocumentNo());
					ord.setDocAction(DocAction.ACTION_Complete);
					ord.completeIt();
					ord.setDocStatus(TF_MOrder.DOCSTATUS_Completed);
					ord.saveEx();
					
					//String error = DocumentEngine.postImmediate(Env.getCtx(), ord.getAD_Client_ID(), ord.get_Table_ID(), ord.get_ID(), true, ord.get_TrxName());				
					//if (! Util.isEmpty(error)) {
					//		throw new AdempiereException(error);
					//}
					trx.releaseSavepoint(sp);
					addLog(ord.get_Table_ID(), ord.getCreated(), null, ord.getDocumentNo() + " is created!", ord.get_Table_ID(), ord.get_ID());
				}
				else {
					//Material Receipt
			
					TF_MInOut inout = new TF_MInOut(getCtx(), 0, get_TrxName());
					inout.setAD_Org_ID(wEntry.getAD_Org_ID());
					inout.setTF_WeighmentEntry_ID(wEntry.getTF_WeighmentEntry_ID());					
					inout.setDescription(wEntry.getDescription());
					inout.setC_DocType_ID(MGLPostingConfig.getMGLPostingConfig(getCtx()).getMaterialReceipt_DocType_ID());
					inout.setMovementType(MInOut.MOVEMENTTYPE_VendorReceipts);
					inout.setMovementDate(wEntry.getGrossWeightTime());
					inout.setDateAcct(wEntry.getGrossWeightTime());
					inout.setC_BPartner_ID(wEntry.getC_BPartner_ID());
					inout.setC_BPartner_Location_ID(bp.getPrimaryC_BPartner_Location_ID());
					inout.setAD_User_ID(bp.getAD_User_ID());					
					inout.setM_Warehouse_ID(wEntry.getM_Warehouse_ID());
					inout.setPriorityRule(TF_MInOut.PRIORITYRULE_Medium);
					inout.setFreightCostRule(TF_MInOut.FREIGHTCOSTRULE_FreightIncluded);
					inout.setIsSOTrx(false);
					inout.saveEx(get_TrxName());
					
					//Material Issue Line
					MInOutLine ioLine = new MInOutLine(inout);
					MWarehouse wh = (MWarehouse) wEntry.getM_Warehouse();
					
					ioLine.setLine(10);
					ioLine.setM_Product_ID(wEntry.getM_Product_ID());
					ioLine.setM_Locator_ID(wh.getDefaultLocator().get_ID());
					ioLine.setQty(wEntry.getNetWeightUnit());
					ioLine.setC_UOM_ID(wEntry.getC_UOM_ID());
					ioLine.saveEx(get_TrxName());
					
					sp = trx.setSavepoint(wEntry.getDocumentNo());	
					//Material Issue DocAction
					if (!inout.processIt(DocAction.ACTION_Complete))
						throw new AdempiereException("Failed when processing document - " + inout.getProcessMsg());
					
					inout.saveEx();
					
							
															
					trx.releaseSavepoint(sp);
					addLog(inout.get_Table_ID(), inout.getCreated(), null, inout.getDocumentNo() + " is created!", inout.get_Table_ID(), inout.get_ID());
				}
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
			wEntry.setStatus(MWeighmentEntry.STATUS_Billed);
			wEntry.setProcessed(true);
			i++;
		}
		return i + " Weighment Entries are processed!";

	}
}
