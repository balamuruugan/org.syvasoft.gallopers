package org.syvasoft.tallyfrontcrusher.process;

import java.util.List;

import org.compiere.model.Query;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MMachinery;

public class ProcessSpareMeter extends SvrProcess {

	@Override
	protected void prepare() {
	
	}

	@Override
	protected String doIt() throws Exception {
		String m_processMsg = null;

		String whereClause = "IsActive = 'Y'";
		
		List<MMachinery> machineryList = new Query(getCtx(), MMachinery.Table_Name, whereClause, get_TrxName())
				.setClient_ID().setOrderBy(MMachinery.COLUMNNAME_PM_Machinery_ID).list();
		
		for(MMachinery machinery : machineryList) {
			machinery.updateSpareMeter();
		}
		return m_processMsg;
	}
}
