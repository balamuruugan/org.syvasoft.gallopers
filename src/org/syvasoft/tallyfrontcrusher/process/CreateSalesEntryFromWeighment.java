package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.sql.Savepoint;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MNote;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPriceList;
import org.compiere.model.MProduct;
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
import org.syvasoft.tallyfrontcrusher.model.TF_MInvoice;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class CreateSalesEntryFromWeighment extends SvrProcess {
	
	Savepoint sp = null;
	/*
	private Timestamp DateFrom = null;
	private Timestamp DateTo = null;
	*/
	private String InvoiceType = null;
	private boolean createTPandNonTPInvocies = false;
	private int RecordId = 0;
	@Override
	protected void prepare() {		
		ProcessInfoParameter[] para = getParameter();		
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();
			if(name.equals("InvoiceType"))
				InvoiceType = para[i].getParameterAsString();
			else if (name.equals("CreateTwoInvoices"))
				createTPandNonTPInvocies = para[i].getParameterAsBoolean();
			/*
			if(name.equals("AD_Org_ID"))
				AD_Org_ID = para[i].getParameterAsInt();
			if(name.equals("DateFrom"))
				DateFrom = para[i].getParameterAsTimestamp();
			if(name.equals("DateTo"))
				DateTo = para[i].getParameterAsTimestamp();
			*/
		}
		RecordId = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		
		String whereClause = " WeighmentEntryType = '1SO' AND TF_WeighmentEntry.Status IN ('CO') AND (SELECT OrgType FROM AD_Org WHERE "				
				+ "AD_Org.AD_Org_ID = TF_WeighmentEntry.AD_Org_ID) = 'C'"
				+ " AND NOT EXISTS(SELECT C_Order.TF_WeighmentEntry_ID FROM C_Order WHERE "
				+ "C_Order.TF_WeighmentEntry_ID =  TF_WeighmentEntry.TF_WeighmentEntry_ID)";
		
		//+ "AND C_Order.DocStatus IN ('CO','DR','IR'))";
		int i = 0;
		List<MWeighmentEntry> wEntries = new Query(getCtx(), MWeighmentEntry.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.list();
		
		for(MWeighmentEntry wEntry : wEntries) {
			if(wEntry.getDescription() != null && wEntry.getDescription().contains("ERROR:")) {
				addLog(wEntry.get_Table_ID(), wEntry.getGrossWeightTime(), null, wEntry.getDescription(), wEntry.get_Table_ID(), wEntry.get_ID());
				continue;
			}
			
			Trx trx = Trx.get(get_TrxName(), false);
			
			try {
				
				//if(createTPandNonTPInvocies)
					//wEntry.setInvoiceType(MWeighmentEntry.INVOICETYPE_TPWeight);
				String msg = null;
				if(wEntry.getInvoiceNo() == null) {
					String checkValidation = " WeighmentEntryType = '1SO' AND Status = 'CO' AND InvoiceNo IS NULL AND GrossWeightTime < ?";
					
					List<MWeighmentEntry> prevEntries = new Query(getCtx(), MWeighmentEntry.Table_Name, checkValidation, get_TrxName())
							.setClient_ID()
							.setParameters(wEntry.getGrossWeightTime())
							.list();
					
					
					if(prevEntries.size() > 0) {
						msg = wEntry.getDocumentNo() +  " : DC cannot be converted as invoice before Old DCs converted as invoice!";
						addLog(wEntry.get_Table_ID(), wEntry.getGrossWeightTime(), null, msg, wEntry.get_Table_ID(), wEntry.get_ID());
						continue;
					}
					
				}
				
								
				if(wEntry.getPrice().doubleValue() == 0) {
					msg = wEntry.getDocumentNo() +  " : Material Price not Set";
					addLog(wEntry.get_Table_ID(), wEntry.getGrossWeightTime(), null, msg, wEntry.get_Table_ID(), wEntry.get_ID());
					continue;
				}
				
				/*List<MNote> notes = new Query(getCtx(), MNote.Table_Name, " processed = 'N' AND ad_table_id = 1000212 AND record_id = " + wEntry.getTF_WeighmentEntry_ID() , get_TrxName()).list();
				
				if(notes.size() > 0) {
					msg = wEntry.getDocumentNo() +  " : Invoice cannot be generated due to pending Notice for this DC.";
					addLog(wEntry.get_Table_ID(), wEntry.getGrossWeightTime(), null, msg, wEntry.get_Table_ID(), wEntry.get_ID());
					continue;
				}*/ 
				
				//if(!createTPandNonTPInvocies) 
				{
				
					BigDecimal billQty = wEntry.getNetWeightUnit();
					
					if(wEntry.getC_OrderLine_ID() == 0)
						createSalesQuickEntry(wEntry, billQty, true, trx);
					else
						createInvoiceCustomer(wEntry, billQty, true, trx);
					
					if(MSysConfig.getValue("INCLUDE_PASS_AMOUNT_IN_INVOICE").equals("N")) {
						if(wEntry.getPermitIssuedQty().doubleValue() > 0) {
							createSalesQuickEntryForRoyaltyPass(wEntry, wEntry.getPermitIssuedQty(), true, trx);
						}
					}
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
			i++;
		}
		return i + " Weighment Entries are processed!";
	}
	
	
	private void createSalesQuickEntry(MWeighmentEntry wEntry, BigDecimal billedQty, boolean firstInvoice, Trx trx) throws Exception {
		TF_MOrder ord = new TF_MOrder(getCtx(), 0, get_TrxName());
		ord.firstInvoice = firstInvoice;
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
				
		int uom_id = wEntry.getC_UOM_ID();
		if(uom_id == 0)
			uom_id = wEntry.getM_Product().getC_UOM_ID();
		
		ord.setItem1_UOM_ID(wEntry.getC_UOM_ID());
		ord.setItem1_Tax_ID(wEntry.getC_Tax_ID());
		BigDecimal qty = wEntry.getBilledQty();
		if(billedQty != null)
			qty = billedQty;
		
		if(qty.doubleValue() == 0)
			throw new AdempiereException("Invalid Billing Qty!");
		
		//BigDecimal qty = wEntry.getNetWeight();
		//if(uom_id == tonnage_uom_id)
		//	qty = qty.divide(new BigDecimal(1000));
		//else
		//	qty = wEntry.getNetWeightUnit();
		ord.setTonnage(qty);
		ord.setItem1_TotalLoad(BigDecimal.ONE);
		ord.setItem1_PermitIssued(wEntry.getPermitIssuedQty()); 
		ord.setMDPNo(wEntry.getMDPNo());
		ord.setItem1_Qty(qty);
		BigDecimal price = wEntry.getMaterialPriceIncludedRent();
		price = wEntry.getMaterialPriceIncludedRoyaltyPass(price);
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
				
		ord.setRent_Amt(wEntry.getRent_Amt());										
		ord.setRentMargin(BigDecimal.ZERO);
		ord.setRentPayable(wEntry.getRent_Amt());
		
		ord.setIsRentBreakup(false);
		ord.setIsRentInclusive(true);			
		
		ord.setRent_Tax_ID(1000000);
		//ord.setRent_Amt(wEntry.getRent_Amt());
		ord.setSalesDiscountAmt(wEntry.getDiscountAmount());

		ord.setDriverTips(wEntry.getDriverTips());
		ord.setProcessed(false);		
		ord.saveEx();				
		
		sp = trx.setSavepoint(wEntry.getDocumentNo());
		ord.setDocAction(DocAction.ACTION_Complete);
		ord.completeIt();
		ord.setDocStatus(TF_MOrder.DOCSTATUS_Completed);
		ord.saveEx();
		
		//Assigning new generated invoices
		List<TF_MInvoice> invList = ord.getTFInvoices();
		if(invList.size() > 0) {
			if(firstInvoice && wEntry.getInvoiceNo() == null) {
				wEntry.setInvoiceNo(invList.get(0).getDocumentNo());				
			}
			else if(!firstInvoice & wEntry.getInvoiceNo2() == null) {
				wEntry.setInvoiceNo2(invList.get(0).getDocumentNo());				
			}
			wEntry.saveEx();
		}
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
		addLog(ord.get_Table_ID(), ord.getCreated(), null, " Sales Entry : " + ord.getDocumentNo() + " is created!", ord.get_Table_ID(), ord.get_ID());
	}

	private void createInvoiceCustomer(MWeighmentEntry wEntry, BigDecimal billedQty,  boolean firstInvoice, Trx trx) throws Exception {
		
		
		MOrderLine oLine = (MOrderLine) wEntry.getC_OrderLine();
		int C_Order_ID = oLine.getC_Order_ID();
		TF_MOrder order = new TF_MOrder(getCtx(), C_Order_ID, get_TrxName());
				
		//Invoice Header
		TF_MBPartner bp = new TF_MBPartner(getCtx(), order.getC_BPartner_ID(), get_TrxName());
				
		TF_MInvoice invoice = new TF_MInvoice(getCtx(), 0, get_TrxName());
		invoice.setC_Order_ID(C_Order_ID);
		invoice.setClientOrg(getAD_Client_ID(), wEntry.getAD_Org_ID());
		invoice.setC_DocTypeTarget_ID(wEntry.getC_DocTypeInvoice_ID());
		invoice.setIsSOTrx(true);
		invoice.setDateInvoiced(wEntry.getGrossWeightTime());
		invoice.setDateAcct(wEntry.getGrossWeightTime());
		
		//fetching already generated invoice no in case of reversing and recreating the existing invoices.
		if(wEntry.getInvoiceNo() != null) {
			invoice.setDocumentNo(wEntry.getInvoiceNo());
			
			if(invoice.getDocumentNo() == null)
				invoice.setDocumentNo(wEntry.getDocumentNo());
		}
		else
			invoice.setDocumentNo(wEntry.getDocumentNo());
		//
		invoice.setSalesRep_ID(Env.getAD_User_ID(getCtx()));		
		invoice.setPaymentRule(order.getPaymentRule());
		invoice.setC_PaymentTerm_ID(order.getC_PaymentTerm_ID());
		//
		
		invoice.setBPartner(bp);				
		invoice.setVehicleNo(wEntry.getVehicleNo());
		invoice.setDescription(wEntry.getDescription());
		
		//Price List
				
		
		invoice.setM_PriceList_ID(order.getM_PriceList_ID());
		invoice.setC_Currency_ID(order.getC_Currency_ID());
		
		//Financial Dimension - Profit Center		
		//invoice.setC_Project_ID(counterProj.getC_Project_ID());
		invoice.setTF_WeighmentEntry_ID(wEntry.getTF_WeighmentEntry_ID());		
		invoice.saveEx();
				
		//Create Invoice Line		
		MInvoiceLine invLine = new MInvoiceLine(invoice);
		int M_Product_ID = oLine.getM_Product_ID();
		invLine.setM_Product_ID(M_Product_ID , true);
		invLine.setC_UOM_ID(oLine.getC_UOM_ID());
		BigDecimal qty = wEntry.getBilledQty();
		if(billedQty != null)
			qty = billedQty;
		
		if(qty.doubleValue() == 0)
			throw new AdempiereException("Invalid Billing Qty!");
		
		invLine.setQty(qty);
		BigDecimal price = wEntry.getMaterialPriceIncludedRent();
		
		price = wEntry.getMaterialPriceIncludedRoyaltyPass(price);
		
		invLine.setPriceActual(price);
		invLine.setPriceList(price);
		invLine.setPriceLimit(price);
		invLine.setPriceEntered(price);		
		invLine.setC_Tax_ID(oLine.getC_Tax_ID());
		invLine.setDescription(oLine.getDescription());
		invLine.setC_Project_ID(order.getC_Project_ID());
		invLine.setC_OrderLine_ID(wEntry.getC_OrderLine_ID());
		if(oLine.getPriceEntered().doubleValue() == 0) {
			throw new AdempiereException("Invalid Price at Line: " + oLine.getLine() + " for Product Name : " + oLine.getM_Product().getName());
		}
		
		invLine.setM_InOutLine_ID(wEntry.getM_InOutLine_ID(invLine.getM_Product_ID()));
		
		invLine.saveEx();
				
		
		sp = trx.setSavepoint(wEntry.getDocumentNo());
		
		//Invoice DocAction
		if (!invoice.processIt(DocAction.ACTION_Complete))
			throw new AdempiereException("Failed when processing document - " + invoice.getProcessMsg());
		invoice.saveEx();
		
		//Set generated Invoice Number for the future refernce
		if(firstInvoice && wEntry.getInvoiceNo() == null) {
			wEntry.setInvoiceNo(invoice.getDocumentNo());				
		}
		else if(!firstInvoice & wEntry.getInvoiceNo2() == null) {
			wEntry.setInvoiceNo2(invoice.getDocumentNo());				
		}
		
		wEntry.saveEx();
		
		trx.releaseSavepoint(sp);
		addLog(invoice.get_Table_ID(), invoice.getCreated(), null, " Invoice No : " +  invoice.getDocumentNo() + " is created!", invoice.get_Table_ID(), invoice.get_ID());
	}	
	
	private void createSalesQuickEntryForRoyaltyPass(MWeighmentEntry wEntry, BigDecimal billedQty, boolean firstInvoice, Trx trx) throws Exception {
		TF_MOrder ord = new TF_MOrder(getCtx(), 0, get_TrxName());
		ord.firstInvoice = firstInvoice;
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
		
		//Item
		ord.setItem1_IsPermitSales(wEntry.isHasBalance());
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
		
		//BigDecimal qty = wEntry.getNetWeight();
		//if(uom_id == tonnage_uom_id)
		//	qty = qty.divide(new BigDecimal(1000));
		//else
		//	qty = wEntry.getNetWeightUnit();
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
		
		//Assigning new generated invoices
		List<TF_MInvoice> invList = ord.getTFInvoices();
		if(invList.size() > 0) {
			wEntry.setInvoiceNo(invList.get(0).getDocumentNo());				
			wEntry.saveEx();
		}
		
		trx.releaseSavepoint(sp);
		addLog(ord.get_Table_ID(), ord.getCreated(), null, " Sales Entry : " + ord.getDocumentNo() + " is created!", ord.get_Table_ID(), ord.get_ID());
	}
}
