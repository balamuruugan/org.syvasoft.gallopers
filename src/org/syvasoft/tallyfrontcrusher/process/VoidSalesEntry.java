package org.syvasoft.tallyfrontcrusher.process;

import java.sql.Savepoint;
import java.sql.Timestamp;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.TF_MInOut;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;

public class VoidSalesEntry extends SvrProcess {	

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		/*
		ProcessInfoParameter[] para = getParameter();		
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();
			if(name.equals("C_DocType_ID"))
				C_DocType_ID = para[i].getParameterAsInt();			
			if(name.equals("AD_Org_ID"))
				AD_Org_ID = para[i].getParameterAsInt();
			if(name.equals("isSOTrx"))
				isSOTrx = para[i].getParameterAsBoolean();
			if(name.equals("DateFrom"))
				DateFrom = para[i].getParameterAsTimestamp();
			if(name.equals("DateTo"))
				DateTo = para[i].getParameterAsTimestamp();
		}
		 */
	}

	@Override
	protected String doIt() throws Exception {
		String whereClause = " WeighmentEntryType = '1SO' AND Status = 'CL' AND EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE " +
				" T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID = TF_WeighmentEntry.TF_WeighmentEntry_ID) "
				+ "  ";
				//+ "AND C_Order.DocStatus IN ('CO','DR','IR'))";
		int i = 0;
		String oWhereClause = "TF_WeighmentEntry_ID = ? AND C_BPartner_ID = ? AND IsSOTrx = 'Y' AND DocStatus IN ('CO','CL')";
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
				sp = trx.setSavepoint(wEntry.getDocumentNo());
				TF_MInOut io = new Query(getCtx(), TF_MInOut.Table_Name, oWhereClause, get_TrxName())
						.setClient_ID()
						.setParameters(wEntry.getTF_WeighmentEntry_ID(), wEntry.getC_BPartner_ID())
						.first();
				if(io != null) {
					io.setDocAction(DocAction.ACTION_Reverse_Correct);
					io.voidIt();
					io.setDocStatus(TF_MOrder.DOCSTATUS_Reversed);
					io.saveEx();
				}
				
				TF_MOrder sale = new Query(getCtx(), TF_MOrder.Table_Name, oWhereClause, get_TrxName())
						.setClient_ID()
						.setParameters(wEntry.getTF_WeighmentEntry_ID(), wEntry.getC_BPartner_ID())
						.first();
				sale.setDocAction(DocAction.ACTION_Void);
				sale.voidIt();
				sale.setDocStatus(TF_MOrder.DOCSTATUS_Voided);
				sale.saveEx();				
				trx.releaseSavepoint(sp);
			}
			catch (Exception ex) {
			if(sp != null)
				trx.rollback(sp);
			}					
		}
			
			

		return null;
	}

}
