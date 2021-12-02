package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MDocType;
import org.compiere.model.MPriceList;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTax;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Trx;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.TF_MInOut;
import org.syvasoft.tallyfrontcrusher.model.TF_MInOutLine;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;

public class CreateTransporterInvoice extends SvrProcess {

	int C_Tax_ID = 0;
	boolean IsTaxIncluded = false;
	int M_InoutLine_ID = 0;
	Savepoint sp = null;
	Timestamp dateInvoiced = null;
	boolean IsConsolidateInvoice = false;
	boolean OverrideTaxConfig = false;
	boolean OnAccount = false;
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();	
		
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();
			if(name.equals("C_Tax_ID"))
				C_Tax_ID = para[i].getParameterAsInt();
			else if (name.equals("IsTaxIncluded"))
				IsTaxIncluded = para[i].getParameterAsBoolean();
			else if (name.equals("DateInvoiced"))
				dateInvoiced  = para[i].getParameterAsTimestamp();
			else if(name.equals("IsConsolidateInvoice"))
				IsConsolidateInvoice = para[i].getParameterAsBoolean();
			else if(name.equals("OverrideTaxConfig"))
				OverrideTaxConfig = para[i].getParameterAsBoolean();
			else if(name.endsWith("OnAccount"))
				OnAccount = para[i].getParameterAsBoolean();
		}
		
		M_InoutLine_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		boolean automatic_process = MSysConfig.getValue("AUTOMATIC_TRANSPORTER_INVOICE_CREATION","Y").equals("Y");
		String whereClause = "";
		List<TF_MInOutLine> list;
		if(automatic_process) {
			whereClause = " DocStatus = 'CO' AND (EXISTS (SELECT m_inout_id FROM m_inout io JOIN tf_weighmententry w ON w.tf_weighmententry_id = io.tf_weighmententry_id WHERE io.issotrx='N'" + 
					" AND io.m_inout_id = m_inoutline.m_inout_id AND io.docstatus <> 'RE' AND w.Status = 'CL')) ";
			
			list = new Query(getCtx(), TF_MInOutLine.Table_Name, whereClause, get_TrxName())
					.setClient_ID()
					.setOrderBy("(SELECT C_BPartner_ID FROM M_InOut WHERE M_InOut.M_InOut_ID = M_InOutLine.M_InOut_ID), "
							+ "(SELECT MovementDate FROM M_InOut WHERE M_InOut.M_InOut_ID = M_InOutLine.M_InOut_ID)")
					.list();
			
			OnAccount = false;
		}
		else {
			whereClause = " DocStatus = 'CO' AND (EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE " +
					" T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID = M_InOutLIne.M_InOutLIne_ID) OR M_InOutLine_ID = ?) ";
			
			list = new Query(getCtx(), TF_MInOutLine.Table_Name, whereClause, get_TrxName())
					.setClient_ID()
					.setParameters(getAD_PInstance_ID(), M_InoutLine_ID)
					.setOrderBy("(SELECT C_BPartner_ID FROM M_InOut WHERE M_InOut.M_InOut_ID = M_InOutLine.M_InOut_ID), "
							+ "(SELECT MovementDate FROM M_InOut WHERE M_InOut.M_InOut_ID = M_InOutLine.M_InOut_ID)")
					.list();
		}		
		
		
		
		//Validation
		for(TF_MInOutLine ioLine : list) {
			TF_MInOut io = new TF_MInOut(getCtx(), ioLine.getM_InOut_ID(), get_TrxName());
			if(!io.getDocStatus().equals(TF_MInOut.STATUS_Completed)) {
				addLog(0, null, null, "Invalid Material Receipt Document Status : " + io.getDocStatusName(), io.get_Table_ID(), io.get_ID());
				
				if(!automatic_process) {
					return "Please exclude this transporter DC to create Transporter Invoice!";
				}
			}
			
			if(ioLine.getPrice().doubleValue() == 0) {
				addLog(0, null, null, "Doc No: " + io.getDocumentNo() + " - Invalid Price!" , io.get_Table_ID(), io.get_ID());
				
				if(!automatic_process) {
					return "Please set the Price!";
				}
			}
			
		}
		
		if(IsConsolidateInvoice) 
			return createConsolidatedInvoice(list);
		else
			return createSeparateInvoice(list);
		
	}

	private String createSeparateInvoice(List<TF_MInOutLine> list) throws SQLException {
		int i = 0;
		MTax tax = new MTax(getCtx(), C_Tax_ID, get_TrxName());
		BigDecimal taxRate = tax.getRate().divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN).add(BigDecimal.ONE);
		
		for(TF_MInOutLine ioLine : list) {
			TF_MInOut io = new TF_MInOut(getCtx(), ioLine.getM_InOut_ID(), get_TrxName());
			
			Trx trx = Trx.get(get_TrxName(), false);
			
			if(!io.getDocStatus().equals(TF_MInOut.STATUS_Completed)) {
				addLog(0, null, null, "Invalid Material Receipt Document Status : " + io.getDocStatusName(), io.get_Table_ID(), io.get_ID());
				ioLine.set_ValueOfColumn("DocStatus", MWeighmentEntry.STATUS_UnderReview);
				ioLine.saveEx();
				continue;
			}
			
			if(ioLine.getPrice().doubleValue() == 0) {
				addLog(0, null, null, "Doc No: " + io.getDocumentNo() + " - Invalid Price!" , io.get_Table_ID(), io.get_ID());
				ioLine.set_ValueOfColumn("DocStatus", MWeighmentEntry.STATUS_UnderReview);
				ioLine.saveEx();
				continue;
			}
			
			try {
				String OnAccountValue = (OnAccount == true)?"Y":"N";
				sp = trx.setSavepoint(io.getDocumentNo());
				TF_MOrder ord = new TF_MOrder(getCtx(), 0, get_TrxName());
				
				MDocType dt = new Query(getCtx(),MDocType.Table_Name,"OnAccount = '" + OnAccountValue + "' AND C_DocTypeShipment_ID = " + io.getC_DocType_ID() ,get_TrxName()).first();
				ord.setAD_Org_ID(io.getAD_Org_ID());
				ord.setIsSOTrx(false);
				ord.setC_DocTypeTarget_ID(dt.getC_DocType_ID());
				ord.setC_DocType_ID(dt.getC_DocType_ID());
				ord.setDateAcct(io.getDateAcct());
				ord.setDateOrdered(io.getDateAcct());
				ord.setC_BPartner_ID(io.getC_BPartner_ID());
				ord.setM_Warehouse_ID(io.getM_Warehouse_ID());
				ord.setPaymentRule(TF_MOrder.PAYMENTRULE_OnCredit);
				//Price List
				int m_M_PriceList_ID = MPriceList.getDefault(getCtx(), true).getM_PriceList_ID();							
				ord.setM_PriceList_ID(m_M_PriceList_ID);
				ord.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
				
				ord.setTF_Destination_ID(ioLine.getTF_Destination_ID());
				ord.setDistance(ioLine.getDistance());				
				
				ord.saveEx();
											
				//create lines
				TF_MOrderLine ordLine = new TF_MOrderLine(ord);
				BigDecimal rateMTKM = ioLine.getRateMTKM();
				BigDecimal price = ioLine.getPrice();
				BigDecimal distance = ioLine.getDistance();
				MWeighmentEntry wEntry = new MWeighmentEntry(getCtx(), io.getTF_WeighmentEntry_ID(), get_TrxName());
				ordLine.setDescription("Customer DC#: " + wEntry.getDocumentNo());
				if(wEntry.getMTKM_UOM_ID() == ioLine.getC_UOM_ID()) {
					price = price.multiply(ioLine.getDistance());
					ordLine.addDescription("Rate MT/km : " + rateMTKM.doubleValue() + ", Distance (km): " + distance);
				}
				
				if(OverrideTaxConfig) {
					if(IsTaxIncluded)
						price = price.divide(taxRate, 2, RoundingMode.HALF_EVEN);
					ordLine.setC_Tax_ID(C_Tax_ID);
				}
				else {
					if(ioLine.isTaxIncluded()) {
						MTax taxMain = new MTax(getCtx(), ioLine.getC_Tax_ID(), get_TrxName());
						BigDecimal taxRateMain = taxMain.getRate().divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN).add(BigDecimal.ONE);				
						price = price.divide(taxRateMain, 2, RoundingMode.HALF_EVEN);
					}
					ordLine.setC_Tax_ID(ioLine.getC_Tax_ID());
				}
				
				
				ordLine.set_ValueOfColumn(TF_MOrder.COLUMNNAME_TF_WeighmentEntry_ID, wEntry.getTF_WeighmentEntry_ID());
				ordLine.setM_Product_ID(ioLine.getM_Product_ID(), ioLine.getC_UOM_ID());
				ordLine.setC_UOM_ID(ioLine.getC_UOM_ID());
				ordLine.setPriceActual(price);
				ordLine.setPriceList(price);
				ordLine.setPriceLimit(price);
				ordLine.setPriceEntered(price);
				ordLine.setQty(ioLine.getMovementQty());

				
				
				ordLine.saveEx();
				
				io.setC_Order_ID(ord.getC_Order_ID());
				io.saveEx();
				
				ioLine.setC_OrderLine_ID(ordLine.getC_OrderLine_ID());
				ioLine.set_ValueOfColumn("DocStatus", MWeighmentEntry.STATUS_Billed);
				ioLine.saveEx();
				
				//complete document
				if (!ord.processIt(DocAction.ACTION_Complete))
					throw new AdempiereException("Failed when processing document - " + ord.getProcessMsg());
				ord.setDocStatus(TF_MOrder.DOCSTATUS_Completed);
				ord.setProcessed(true);
				ord.saveEx();
												
				trx.releaseSavepoint(sp);
				addLog(ord.get_Table_ID(), null, null, " Transporter PO Entry : " +  ord.getDocumentNo() + " is created!", ord.get_Table_ID(), ord.get_ID());
				
			}
			catch (Exception ex) {
				if(sp != null)
					trx.rollback(sp);				
				addLog(0, null, null, ex.getMessage(), ioLine.get_Table_ID(), ioLine.get_ID());
			}
			i++;
		}
		
		return i + "  Entries are processed!";
	}
	
	private String createConsolidatedInvoice(List<TF_MInOutLine> list) throws SQLException {
		int i = 0;
		int C_BPartner_ID = 0;
		TF_MOrder ord = null;
		MTax tax = new MTax(getCtx(), C_Tax_ID, get_TrxName());
		BigDecimal taxRate = tax.getRate().divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN).add(BigDecimal.ONE);
		
		for(TF_MInOutLine ioLine : list) {
			TF_MInOut io = new TF_MInOut(getCtx(), ioLine.getM_InOut_ID(), get_TrxName());
			
			//Different Transporter
			if(io.getC_BPartner_ID() != C_BPartner_ID) {
				//complete the current document
				if(ord != null) {						
					if (!ord.processIt(DocAction.ACTION_Complete))
						throw new AdempiereException("Failed when processing document - " + ord.getProcessMsg());
					ord.setDocStatus(TF_MOrder.DOCSTATUS_Completed);
					ord.setProcessed(true);
					ord.saveEx();
					
					addLog(ord.get_Table_ID(), null, null, " Transporter PO Entry : " +  ord.getDocumentNo() + " is created!", ord.get_Table_ID(), ord.get_ID());
				}
								
				ord = new TF_MOrder(getCtx(), 0, get_TrxName());
				ord.setIsSOTrx(false);
				ord.setC_DocTypeTarget_ID(TF_MOrder.getC_TransporterInvoiceDocType_ID());
				ord.setC_DocType_ID(TF_MOrder.getC_TransporterInvoiceDocType_ID());
				ord.setDateAcct(dateInvoiced);
				ord.setDateOrdered(dateInvoiced);
				ord.setC_BPartner_ID(io.getC_BPartner_ID());
				ord.setM_Warehouse_ID(io.getM_Warehouse_ID());
				ord.setPaymentRule(TF_MOrder.PAYMENTRULE_OnCredit);
				//Price List
				int m_M_PriceList_ID = MPriceList.getDefault(getCtx(), true).getM_PriceList_ID();							
				ord.setM_PriceList_ID(m_M_PriceList_ID);
				ord.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
				
				ord.setTF_Destination_ID(ioLine.getTF_Destination_ID());
				ord.setDistance(ioLine.getDistance());				
				
				ord.saveEx();
				
				C_BPartner_ID = io.getC_BPartner_ID();
			}
						
			//create lines
			TF_MOrderLine ordLine = new TF_MOrderLine(ord);
			BigDecimal rateMTKM = ioLine.getRateMTKM();
			BigDecimal price = ioLine.getPrice();
			BigDecimal distance = ioLine.getDistance();
			MWeighmentEntry wEntry = new MWeighmentEntry(getCtx(), io.getTF_WeighmentEntry_ID(), get_TrxName());
			ordLine.setDescription("Customer DC#: " + wEntry.getDocumentNo());
			if(wEntry.getMTKM_UOM_ID() == ioLine.getC_UOM_ID()) {
				price = price.multiply(ioLine.getDistance());
				ordLine.addDescription("Rate MT/km : " + rateMTKM.doubleValue() + ", Distance (km): " + distance);
			}
			
			if(OverrideTaxConfig) {
				if(IsTaxIncluded)
					price = price.divide(taxRate, 2, RoundingMode.HALF_EVEN);
				ordLine.setC_Tax_ID(C_Tax_ID);
			}
			else {
				if(ioLine.isTaxIncluded()) {
					MTax taxMain = new MTax(getCtx(), ioLine.getC_Tax_ID(), get_TrxName());
					BigDecimal taxRateMain = taxMain.getRate().divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN).add(BigDecimal.ONE);				
					price = price.divide(taxRateMain, 2, RoundingMode.HALF_EVEN);
				}
				ordLine.setC_Tax_ID(ioLine.getC_Tax_ID());
			}
			
			
			ordLine.set_ValueOfColumn(TF_MOrder.COLUMNNAME_TF_WeighmentEntry_ID, wEntry.getTF_WeighmentEntry_ID());
			ordLine.setM_Product_ID(ioLine.getM_Product_ID(), ioLine.getC_UOM_ID());
			ordLine.setC_UOM_ID(ioLine.getC_UOM_ID());
			ordLine.setPriceActual(price);
			ordLine.setPriceList(price);
			ordLine.setPriceLimit(price);
			ordLine.setPriceEntered(price);
			ordLine.setQty(ioLine.getMovementQty());
			
						
			ordLine.saveEx();
			
			io.setC_Order_ID(ord.getC_Order_ID());
			io.saveEx();
			
			ioLine.setC_OrderLine_ID(ordLine.getC_OrderLine_ID());
			ioLine.set_ValueOfColumn("DocStatus", MWeighmentEntry.STATUS_Billed);
			ioLine.saveEx();
			
			i++;
		}
		//complete the current document
		if(ord != null) {						
			if (!ord.processIt(DocAction.ACTION_Complete))
				throw new AdempiereException("Failed when processing document - " + ord.getProcessMsg());
			ord.setDocStatus(TF_MOrder.DOCSTATUS_Completed);
			ord.setProcessed(true);
			ord.saveEx();
			
			addLog(ord.get_Table_ID(), null, null, " Transporter PO Entry : " +  ord.getDocumentNo() + " is created!", ord.get_Table_ID(), ord.get_ID());
		}
		return i + "  Entries are processed!";
	}
}
