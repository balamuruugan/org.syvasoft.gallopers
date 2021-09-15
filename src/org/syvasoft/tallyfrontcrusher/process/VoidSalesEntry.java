package org.syvasoft.tallyfrontcrusher.process;

import java.sql.Savepoint;
import java.sql.Timestamp;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MSysConfig;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.TF_MInOut;
import org.syvasoft.tallyfrontcrusher.model.TF_MInvoice;
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
		String whereClause = " WeighmentEntryType IN ('1SO', '2PO')	 AND Status IN ('CL','CO') AND (EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE " +
				" T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID = TF_WeighmentEntry.TF_WeighmentEntry_ID) OR TF_WeighmentEntry_ID = ?) "
				+ "  ";
				//+ "AND C_Order.DocStatus IN ('CO','DR','IR'))";
		int i = 0;
		String oWhereClause = "TF_WeighmentEntry_ID = ? AND C_BPartner_ID = ? AND IsSOTrx = 'Y' AND DocStatus IN ('CO','CL')";
		List<MWeighmentEntry> wEntries = new Query(getCtx(), MWeighmentEntry.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getAD_PInstance_ID(), getRecord_ID())
				.list();
		
		for(MWeighmentEntry wEntry : wEntries) {
			if(wEntry.getDescription() != null && wEntry.getDescription().contains("ERROR:")) 
				continue;
			
			Trx trx = Trx.get(get_TrxName(), false);
			Savepoint sp = null;
			try {
				String msg = null;
				sp = trx.setSavepoint(wEntry.getDocumentNo());
				
				wEntry.voidWeighmentEntry();
				wEntry.setStatus(MWeighmentEntry.STATUS_UnderReview);
				wEntry.saveEx();
				
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
