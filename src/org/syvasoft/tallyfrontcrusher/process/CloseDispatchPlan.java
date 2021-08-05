package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;

import org.compiere.model.MOrder;
import org.compiere.model.Query;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.syvasoft.tallyfrontcrusher.model.MCrusherProduction;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlan;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlanLine;

public class CloseDispatchPlan extends SvrProcess {
	
	private int tf_dispatchplanlineID = 0;
	MDispensePlanLine dispensePlanline;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();		
		
		tf_dispatchplanlineID =  getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
			
			dispensePlanline = new MDispensePlanLine(getCtx(), tf_dispatchplanlineID, get_TrxName());
			
			if(dispensePlanline != null) {
				dispensePlanline.setDocStatus(MDispensePlanLine.DOCSTATUS_Closed);
				dispensePlanline.saveEx();
			}
			
			return " Dispatch Plan Line are closed successfully!";	    
	}
}
