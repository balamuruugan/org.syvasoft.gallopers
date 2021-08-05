package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MTransportOrder;

public class ProcessTransporterOrder extends SvrProcess{
	
	
	int record_id = 0;
	MTransportOrder torder;
	private String docAction = "CO";

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		record_id = getRecord_ID();
		torder = new MTransportOrder(getCtx(), record_id, get_TrxName());
		ProcessInfoParameter[] para = getParameter();
		for(int i=0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if(name.equals("DocAction"))
				docAction = para[i].getParameterAsString();
		}
		
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		String m_processMsg = null;
		if(!torder.isProcessed()) {
			torder.Processit(docAction);
		}
		else if(torder.isProcessed() && docAction.equals("MO")) {
			torder.reverseIt();
		}
		
		torder.saveEx();
		
		return m_processMsg;
	}

}
