package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MInstantPettyCash;

public class ProcessInstantPettyCash extends SvrProcess {

	MInstantPettyCash cash = null;
	private String docAction="CO";
	
	@Override
	protected void prepare() {
		cash = new MInstantPettyCash(getCtx(), getRecord_ID(), get_TrxName());
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
		if(!cash.isProcessed())
			cash.processIt(DocAction.ACTION_Complete);
		else if(cash.isProcessed() && docAction.equals("MO"))
			cash.reverseIt();
			
		cash.saveEx();
		return null;
	}

}
