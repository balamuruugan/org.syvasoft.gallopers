package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MCrusherProduction;
import org.syvasoft.tallyfrontcrusher.model.MDrillingBlastingEntry;

public class ProcessDrillingBlasting extends SvrProcess {

	int m_recordID = 0;
	MDrillingBlastingEntry drillBlast;
	private String docAction="CO";

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		m_recordID = getRecord_ID();
		drillBlast = new MDrillingBlastingEntry(getCtx(), m_recordID, get_TrxName());
		ProcessInfoParameter[] para = getParameter();		
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();
			if (name.equals("DocAction"))
				docAction =  para[i].getParameterAsString();
		}

	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		String m_processMsg = null;
		if(!drillBlast.isProcessed()) {
				drillBlast.processIt(MBoulderReceipt.DOCACTION_Complete);		
			if(m_processMsg == null)
				drillBlast.saveEx();
			else
				rollback();
		}
		else if(drillBlast.isProcessed() && docAction.equals("RE")) {
			drillBlast.reverseIt();
			drillBlast.saveEx();
		}
		
		return m_processMsg;
	}

}
