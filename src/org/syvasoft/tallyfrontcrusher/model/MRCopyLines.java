package org.syvasoft.tallyfrontcrusher.model;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

public class MRCopyLines extends SvrProcess {

	int recordID = 0;
	int srcInOut_ID = 0;
	TF_MInOut toInOut = null;
	TF_MInOut srcInOut = null;
	
	@Override
	protected void prepare() {
		
		ProcessInfoParameter[] para = getParameter();		
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();
			if(name.equals("M_InOut_ID"))
				srcInOut_ID =  para[i].getParameterAsInt();
		}
		
		recordID = getRecord_ID();
		toInOut = new TF_MInOut(getCtx(), recordID, get_TrxName());
		srcInOut = new TF_MInOut(getCtx(), srcInOut_ID, get_TrxName());
	}

	@Override
	protected String doIt() throws Exception {
				
		toInOut.copyLinesFrom(srcInOut, false, false);
		
		return null;
	}

}
