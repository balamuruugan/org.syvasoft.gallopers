package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.util.List;

import org.compiere.model.MPriceList;
import org.compiere.model.MSysConfig;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.syvasoft.tallyfrontcrusher.model.MDestination;
import org.syvasoft.tallyfrontcrusher.model.MLumpSumRentConfig;
import org.syvasoft.tallyfrontcrusher.model.MRentedVehicle;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;

public class CreateSalesEntryFromWeighment extends SvrProcess {
	private int C_DocType_ID = 0;
	private int AD_Org_ID = 0;
	/*
	private Timestamp DateFrom = null;
	private Timestamp DateTo = null;
	*/
	@Override
	protected void prepare() {		
		ProcessInfoParameter[] para = getParameter();		
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();
			if(name.equals("C_DocType_ID"))
				C_DocType_ID = para[i].getParameterAsInt();			
			/*
			if(name.equals("AD_Org_ID"))
				AD_Org_ID = para[i].getParameterAsInt();
			if(name.equals("DateFrom"))
				DateFrom = para[i].getParameterAsTimestamp();
			if(name.equals("DateTo"))
				DateTo = para[i].getParameterAsTimestamp();
			*/
		}
	}

	@Override
	protected String doIt() throws Exception {
		String whereClause = " WeighmentEntryType = '1SO' AND Status = 'CO' AND EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE " +
				" T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID = TF_WeighmentEntry.TF_WeighmentEntry_ID) "
				+ "  ";
				//+ "AND C_Order.DocStatus IN ('CO','DR','IR'))";
		int i = 0;
		List<MWeighmentEntry> wEntries = new Query(getCtx(), MWeighmentEntry.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getAD_PInstance_ID())
				.list();
		for(MWeighmentEntry wEntry : wEntries) {
			if(wEntry.getDescription() != null && wEntry.getDescription().contains("ERROR:")) 
				continue;
			
			Trx trx = Trx.get(get_TrxName(), false);
			Savepoint sp = null;
			try {
				
				String msg = null;
				if(wEntry.getPrice().doubleValue() == 0) {
					msg = wEntry.getDocumentNo() +  " : Material Price not Set";
					addLog(wEntry.get_Table_ID(), wEntry.getGrossWeightTime(), null, msg, wEntry.get_Table_ID(), wEntry.get_ID());
				}
				
				
				TF_MOrder ord = new TF_MOrder(getCtx(), 0, get_TrxName());
				ord.setAD_Org_ID(wEntry.getAD_Org_ID());
				ord.setC_DocType_ID(wEntry.getC_DocType_ID());
				ord.setC_DocTypeTarget_ID(wEntry.getC_DocType_ID());
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
				if(wEntry.getPartyName() != null)
					ord.addDescription("Customer Name : " + wEntry.getPartyName());
				
				ord.setPaymentRule(wEntry.getPaymentRule());
				ord.setOnAccount(false);

				//Price List
				int m_M_PriceList_ID = MPriceList.getDefault(getCtx(), true).getM_PriceList_ID();							
				ord.setM_PriceList_ID(m_M_PriceList_ID);
				ord.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
				ord.setIsSOTrx(true);
				ord.setTF_WeighmentEntry_ID(wEntry.getTF_WeighmentEntry_ID());	
				ord.setTF_Destination_ID(wEntry.getTF_Destination_ID());
				ord.setVehicleNo(wEntry.getVehicleNo());
				ord.setTF_RentedVehicle_ID(wEntry.getTF_RentedVehicle_ID());
				ord.setItem1_BucketQty(null);
				
				
				//Item
				ord.setItem1_IsPermitSales(wEntry.isHasBalance());
				ord.setItem1_VehicleType_ID(wEntry.getTF_VehicleType_ID());
				if(wEntry.isHasBalance())
					ord.setItem1_SandType(TF_MOrder.ITEM1_SANDTYPE_PermitSand);
				else
					ord.setItem1_SandType(TF_MOrder.ITEM1_SANDTYPE_WithoutPermit);
				ord.setItem1_ID(wEntry.getM_Product_ID());
				
				int tonnage_uom_id = MSysConfig.getIntValue("TONNAGE_UOM", 1000069, Env.getAD_Client_ID(getCtx()));
				int uom_id = wEntry.getC_UOM_ID();
				if(uom_id == 0)
					uom_id = wEntry.getM_Product().getC_UOM_ID();
				
				ord.setItem1_UOM_ID(wEntry.getC_UOM_ID());
				ord.setItem1_Tax_ID(wEntry.getC_Tax_ID());
				BigDecimal qty = wEntry.getNetWeight();
				if(uom_id == tonnage_uom_id)
					qty = qty.divide(new BigDecimal(1000));
				else
					qty = wEntry.getNetWeightUnit();
				ord.setTonnage(qty);
				ord.setItem1_TotalLoad(BigDecimal.ONE);
				ord.setItem1_PermitIssued(wEntry.getPermitIssuedQty()); 
				ord.setMDPNo(wEntry.getMDPNo());
				ord.setItem1_Qty(qty);
				BigDecimal price = wEntry.getPrice();
				ord.setItem1_Price(price);
				ord.setItem1_UnitPrice(price);
				ord.setItem1_Amt(ord.getItem1_Qty().multiply(ord.getItem1_Price()));
				
				
				//Item2
				ord.setItem2_UOM_ID(ord.getItem2().getC_UOM_ID());
				ord.setItem2_Tax_ID(1000000);
				
				if(wEntry.getM_Product2() != null && wEntry.getM_Product2_ID()>0) {
					ord.setItem2_ID(wEntry.getM_Product2_ID());
					ord.setItem2_Qty(wEntry.getPassQtyIssued());	
					ord.setItem2_UOM_ID(wEntry.getC_UOM_ID());
					ord.setItem2_Price(wEntry.getPassPricePerUnit());
					ord.setItem2_Amt(wEntry.getPermitPassAmount());
				}
				else {
					ord.setItem2_ID(0);
					ord.setItem2_Qty(BigDecimal.ZERO);
				}

				if(ord.getTF_RentedVehicle_ID() > 0) {		
					ord.setIsLumpSumRent(false);
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
					ord.setCreateTransporterInvoice(bp.CreateTransportInvoice(Vendor_ID));					
				}
				
				ord.setIsRentBreakup(false);
				ord.setIsRentInclusive(true);			
				
				ord.setRent_Tax_ID(1000000);
				//ord.setRent_Amt(wEntry.getRent_Amt());
				ord.setSalesDiscountAmt(wEntry.getDiscountAmount());

				ord.setDriverTips(wEntry.getDriverTips());
				ord.setProcessed(false);
				ord.setOnAccount(false);
				ord.saveEx();				
				
				sp = trx.setSavepoint(wEntry.getDocumentNo());
				ord.setDocAction(DocAction.ACTION_Complete);
				ord.completeIt();
				ord.setDocStatus(TF_MOrder.DOCSTATUS_Completed);
				ord.saveEx();
				
				/*
				ord.setDocAction(DocAction.ACTION_Prepare);
				ord.setDocStatus(TF_MOrder.DOCSTATUS_Drafted);
				ord.saveEx();
				*/
				//String error = DocumentEngine.postImmediate(Env.getCtx(), ord.getAD_Client_ID(), ord.get_Table_ID(), ord.get_ID(), true, ord.get_TrxName());				
				//if (! Util.isEmpty(error)) {
				//		throw new AdempiereException(error);
				//}
				trx.releaseSavepoint(sp);
				addLog(ord.get_Table_ID(), ord.getCreated(), null, ord.getDocumentNo() + " is created!", ord.get_Table_ID(), ord.get_ID());
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
			i++;
		}
		return i + " Weighment Entries are processed!";
	}

}
