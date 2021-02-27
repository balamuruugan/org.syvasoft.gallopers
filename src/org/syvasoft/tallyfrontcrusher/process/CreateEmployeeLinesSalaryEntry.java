package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalary;

public class CreateEmployeeLinesSalaryEntry extends SvrProcess {

	private String docAction="CO";
	MEmployeeSalary salary;
	
	@Override
	protected void prepare() {
		salary = new MEmployeeSalary(getCtx(), getRecord_ID(), get_TrxName());
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
		salary.createEmployeeList();			
		salary.saveEx();
		return null;
	}

}
