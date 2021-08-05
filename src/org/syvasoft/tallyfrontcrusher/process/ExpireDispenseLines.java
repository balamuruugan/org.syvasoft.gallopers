package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.adempiere.exceptions.DBException;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.syvasoft.tallyfrontcrusher.model.MCrusherProduction;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlan;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlanLine;

public class ExpireDispenseLines extends SvrProcess {
	
	@Override
	protected void prepare() {
		
		for(ProcessInfoParameter para : getParameter())
		{
			String name = para.getParameterName();
		}
	}

	@Override
	protected String doIt() throws Exception {
		String whereClause = "trunc(tf_dispenseplanline.scheduledate) < getdate() " +				 
				" AND tf_dispenseplanline.AllowCarryForward = 'N' and tf_dispenseplanline.docstatus IN ('IP','RV','DR')";
		
		List<MDispensePlanLine> dispenselines = new Query(getCtx(), MDispensePlanLine.Table_Name, whereClause, get_TrxName()).list();
		
		for(MDispensePlanLine dispenseline : dispenselines) {
			dispenseline.setDocStatus(MDispensePlanLine.DOCSTATUS_Expired);
			dispenseline.saveEx();
		}		
	
		return null;
	}
}
