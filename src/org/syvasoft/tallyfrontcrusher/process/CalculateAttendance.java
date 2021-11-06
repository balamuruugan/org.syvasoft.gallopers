package org.syvasoft.tallyfrontcrusher.process;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MBiometricAttedenceLog;

public class CalculateAttendance extends SvrProcess {

	private int AD_Org_ID ;
	private Timestamp dateFrom ;
	private boolean reCalcualte = false; 
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();		
		
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();
			if (name.equals("AD_Org_ID"))
				AD_Org_ID = para[i].getParameterAsInt();
			else if (name.equals("DateFrom")) 
				dateFrom = para[i].getParameterAsTimestamp();
			else if (name.equals("reCalculate")) 
				reCalcualte = para[i].getParameterAsBoolean();
			
		}
	}

	@Override
	protected String doIt() throws Exception {
		String whereClause = "AD_Org_ID = ? AND AttendenceTime  >= ?";
		List<MBiometricAttedenceLog> list = new Query(getCtx(), MBiometricAttedenceLog.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(AD_Org_ID, dateFrom)
				.setOrderBy("AttendenceTime ")
				.list();
		
		for(MBiometricAttedenceLog bLog : list) {
			if(reCalcualte && bLog.isProcessed() || !bLog.isProcessed())
			bLog.setShift();
			bLog.saveEx();
		}
		return null;
	}

}
