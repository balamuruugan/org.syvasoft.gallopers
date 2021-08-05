package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MCrusherProduction;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlan;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlanLine;
import org.syvasoft.tallyfrontcrusher.model.MPMJob;

public class ProcessDispensePlanLine extends SvrProcess {

	int m_recordID = 0;
	MDispensePlan dispensePlan;
	private String docAction="CO";
	private String recordType="";
	@Override
	protected void prepare() {
		m_recordID = getRecord_ID();
		
		ProcessInfoParameter[] para = getParameter();		
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();
			if (name.equals("DocAction"))
				docAction =  para[i].getParameterAsString();
			else if(name.equals("RecordType"))
				recordType = para[i].getParameterAsString();
		}
	}

	@Override
	protected String doIt() throws Exception {
		String m_processMsg = null;
		
		if(recordType.equals("Header")) {
			dispensePlan = new MDispensePlan(getCtx(), m_recordID, get_TrxName());
			if(!dispensePlan.isProcessed()) {
				dispensePlan.processIt(docAction);
			}
			else if(dispensePlan.isProcessed() && docAction.equals("MO")) {
				dispensePlan.reverseIt();
			}
		}
		else {
			MDispensePlanLine dispensePlanLine = new MDispensePlanLine(getCtx(), m_recordID, get_TrxName());
			
			dispensePlan = new MDispensePlan(getCtx(), dispensePlanLine.getTF_DispensePlan_ID(), get_TrxName());
			if(!dispensePlan.isProcessed()) {
				dispensePlan.processIt(docAction);
			}
			else if(dispensePlan.isProcessed() && docAction.equals("MO")) {
				dispensePlan.reverseIt();
			}
		}
			
		
		dispensePlan.saveEx();
		return m_processMsg;
	}

}
