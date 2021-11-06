package org.syvasoft.tallyfrontcrusher.process;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MBiometricAttedenceLog;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeAttendance;

public class CalculateAttendance extends SvrProcess {
	/*
	private int AD_Org_ID ;
	private Timestamp dateFrom ;
	private boolean reCalcualte = false; 
	*/
	@Override
	protected void prepare() {
		/*
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
		*/
	}

	@Override
	protected String doIt() throws Exception {
		String whereClause = "Processed='N'";
		List<MBiometricAttedenceLog> list = new Query(getCtx(), MBiometricAttedenceLog.Table_Name, whereClause, get_TrxName())
				.setClient_ID()				
				.setOrderBy("AttendenceTime ")
				.list();
		
		for(MBiometricAttedenceLog bLog : list) {			
			bLog.setShift();
			bLog.saveEx();
		}
		
		int i = MEmployeeAttendance.generateAttendanceRecordsFromBiometricLog(getCtx(), get_TrxName());
		
		return i + " records created successfully!";
	}

}
