package org.syvasoft.tallyfrontcrusher.process;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.adempiere.util.IProcessUI;
import org.compiere.acct.Doc;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAllocationHdr;
import org.compiere.model.MClient;

import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.TF_MInOut;
import org.syvasoft.tallyfrontcrusher.model.TF_MInvoice;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MPayment;

public class UpdateDateAcctTime extends SvrProcess {
	
	private Timestamp DateFrom = null;
	private String type = null;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();		
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();
			if(name.equals("DateFrom"))
				DateFrom = para[i].getParameterAsTimestamp();
			else if(name.equals("WeighmentEntryType"))
				type = para[i].getParameterAsString();
		}

	}

	@Override
	protected String doIt() throws Exception {
		String whereClause = "WeighmentEntryType = ? AND Status='CL' AND GrossWeightTime >= ?";
		List<MWeighmentEntry> list = new Query(getCtx(), MWeighmentEntry.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(type, DateFrom)
				.setOrderBy("GrossWeightTime")
				.list();
		
		MClient client = MClient.get(getCtx());		
		MAcctSchema[] ass= new MAcctSchema[1];
		ass[0] = client.getAcctSchema();
		IProcessUI processMonitor = Env.getProcessUI(getCtx());
		
		int total = list.size();
		int i = 0;
		
		for(MWeighmentEntry wEntry : list) {
			
			
			String wh = "TF_WeighmentEntry_ID = ?";
			//Order Table
			List<TF_MOrder> orders = new Query(getCtx(), TF_MOrder.Table_Name, wh, get_TrxName())
					.setClient_ID()
					.setParameters(wEntry.getTF_WeighmentEntry_ID())
					.list();
			for(TF_MOrder ord : orders) {
				ord.setDateAcct(wEntry.getGrossWeightTime());
				ord.setDateOrdered(ord.getDateAcct());
				ord.saveEx();
				
				//Payment Table
				List<TF_MPayment> payments = new Query(getCtx(), TF_MPayment.Table_Name, "C_Payment_ID = ? ", get_TrxName())
						.setClient_ID()
						.setParameters(ord.getTF_DriverTips_Pay_ID())
						.list();
				for(TF_MPayment payment : payments) {
					String sql = "UPDATE C_Payment SET DateAcct = ?, DateTrx = ? WHERE C_Payment_ID =  ?";
					ArrayList<Object> params = new ArrayList<>();
					params.add(wEntry.getGrossWeightTime());
					params.add(wEntry.getGrossWeightTime());
					params.add(payment.getC_Payment_ID());
					DB.executeUpdate(sql, params.toArray(), false, get_TrxName());				
					Doc.postImmediate(ass, TF_MPayment.Table_ID, payment.get_ID(), true, get_TrxName());
									
					String wh1 = "C_AllocationHdr_ID IN (SELECT C_AllocationLine.C_AllocationHdr_ID FROM C_AllocationLine "
							+ " WHERE C_AllocationLine.C_Payment_ID = ?)";
					List<MAllocationHdr> allocations = new Query(getCtx(), MAllocationHdr.Table_Name, wh1, get_TrxName())
							.setClient_ID()
							.setParameters(payment.getC_Payment_ID())
							.list();
					for(MAllocationHdr alloc : allocations) {
						alloc.setDateAcct(payment.getDateAcct());
						alloc.setDateTrx(alloc.getDateAcct());
						alloc.saveEx();
						
						Doc.postImmediate(ass, MAllocationHdr.Table_ID, alloc.get_ID(), true, get_TrxName());
					}
				}
			}
			

			//Boulder Receipt
			List<MBoulderReceipt> receipts = new Query(getCtx(), MBoulderReceipt.Table_Name, wh, get_TrxName())			
					.setClient_ID()
					.setParameters(wEntry.getTF_WeighmentEntry_ID())
					.list();
			
			for(MBoulderReceipt br : receipts) {
				br.setDateReceipt(wEntry.getGrossWeightTime());
				br.setDateAcct(wEntry.getGrossWeightTime());
				br.saveEx();
				
				//update Aggregate material movements
				String sql = "UPDATE TF_RMSubcon_Movement SET MovementDate = ? WHERE TF_WeighmentEntry_ID = ? ";
				ArrayList<Object> Params = new ArrayList<Object>();
				Params.add(wEntry.getGrossWeightTime());
				Params.add(wEntry.getTF_WeighmentEntry_ID());
				
				DB.executeUpdateEx(sql, Params.toArray(), get_TrxName());
				
				//update Boulder Movement
				sql = "UPDATE TF_Boulder_Movement SET MovementDate = ? WHERE TF_WeighmentEntry_ID = ? ";
				DB.executeUpdateEx(sql, Params.toArray(), get_TrxName());
				
				
				//Subcontractor Invoice
				TF_MInvoice inv = new TF_MInvoice(getCtx(), br.getSubcon_Invoice_ID(), get_TrxName());
				inv.setDateAcct(wEntry.getGrossWeightTime());
				inv.setDateInvoiced(inv.getDateAcct());
				inv.setDateOrdered(inv.getDateAcct());				
				inv.saveEx();
				Doc.postImmediate(ass, TF_MInvoice.Table_ID, inv.get_ID(), true, get_TrxName());
				
			}
			
			//Invoice Table
			List<TF_MInvoice> invoices = new Query(getCtx(), TF_MInvoice.Table_Name, wh, get_TrxName())
					.setClient_ID()
					.setParameters(wEntry.getTF_WeighmentEntry_ID())
					.list();
			
			for(TF_MInvoice inv: invoices) {
				inv.setDateAcct(wEntry.getGrossWeightTime());
				inv.setDateInvoiced(inv.getDateAcct());
				inv.setDateOrdered(inv.getDateAcct());				
				inv.saveEx();
				Doc.postImmediate(ass, TF_MInvoice.Table_ID, inv.get_ID(), true, get_TrxName());
				
				//Payment Table
				List<TF_MPayment> payments = new Query(getCtx(), TF_MPayment.Table_Name, "C_Invoice_ID = ? ", get_TrxName())
						.setClient_ID()
						.setParameters(inv.get_ID())
						.list();
				for(TF_MPayment payment : payments) {
					String sql = "UPDATE C_Payment SET DateAcct = ?, DateTrx = ? WHERE C_Payment_ID =  ?";
					ArrayList<Object> params = new ArrayList<>();
					params.add(wEntry.getGrossWeightTime());
					params.add(wEntry.getGrossWeightTime());
					params.add(payment.getC_Payment_ID());
					DB.executeUpdate(sql, params.toArray(), false, get_TrxName());
					Doc.postImmediate(ass, TF_MPayment.Table_ID, payment.get_ID(), true, get_TrxName());
									
					String wh1 = "C_AllocationHdr_ID IN (SELECT C_AllocationLine.C_AllocationHdr_ID FROM C_AllocationLine "
							+ " WHERE C_AllocationLine.C_Payment_ID = ?)";
					List<MAllocationHdr> allocations = new Query(getCtx(), MAllocationHdr.Table_Name, wh1, get_TrxName())
							.setClient_ID()
							.setParameters(payment.getC_Payment_ID())
							.list();
					for(MAllocationHdr alloc : allocations) {
						alloc.setDateAcct(payment.getDateAcct());
						alloc.setDateTrx(alloc.getDateAcct());
						alloc.saveEx();
						
						Doc.postImmediate(ass, MAllocationHdr.Table_ID, alloc.get_ID(), true, get_TrxName());
					}
				}
				
				
				
			}
			
			//Shipment Table
			List<TF_MInOut> inouts = new Query(getCtx(), TF_MInOut.Table_Name, wh, get_TrxName())
					.setClient_ID()
					.setParameters(wEntry.getTF_WeighmentEntry_ID())
					.list();
			for(TF_MInOut io : inouts) {
				io.setDateAcct(wEntry.getGrossWeightTime());				
				io.setDateOrdered(io.getDateAcct());		
				io.setMovementDate(io.getDateAcct());				
				io.saveEx();
				Doc.postImmediate(ass, TF_MInOut.Table_ID, io.get_ID(), true, get_TrxName());
			}
			
			
		
			if (processMonitor != null)
			{
				processMonitor.statusUpdate( ++i + "/" + total + " completed - " + wEntry.getDocumentNo());
			}		
			
		}
		
		
		
		return null;
	}

}
