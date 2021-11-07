package org.syvasoft.tallyfrontcrusher.process;

import java.sql.Timestamp;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeAttendance;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;

public class GenerateAttendanceRecords extends SvrProcess {

	private int AD_Org_ID ;
	private Timestamp dateAcct ;
	 
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();		
		
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();
			if (name.equals("AD_Org_ID"))
				AD_Org_ID = para[i].getParameterAsInt();
			else if (name.equals("DateAcct")) 
				dateAcct = para[i].getParameterAsTimestamp();
		}

	}

	@Override
	protected String doIt() throws Exception {
		int i = 0;
		for(TF_MBPartner bp : MEmployeeAttendance.getEmployees(getCtx(), AD_Org_ID, dateAcct)) {
			MEmployeeAttendance att = new MEmployeeAttendance(getCtx(), 0, get_TrxName());
			att.setAD_Org_ID(bp.getAD_Org_ID());
			att.setC_BPartner_ID(bp.getC_BPartner_ID());
			att.setDateAcct(dateAcct);			
			att.setStatus(MEmployeeAttendance.STATUS_Unknown);
			att.saveEx();
			i++;
		}
		return i + " records are created successfully!";
	}

}
