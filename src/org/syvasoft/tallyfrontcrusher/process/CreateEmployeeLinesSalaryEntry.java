package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalary;

public class CreateEmployeeLinesSalaryEntry extends SvrProcess {

	private String docAction="CO";
	boolean reCreate = false;
	boolean updatePresentDays = false;
	boolean updateMonthlySalary = false;
	boolean updateAdvancePaid = false;
	
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
			else if (name.equals("reCreate"))
				reCreate =  para[i].getParameterAsBoolean();
			else if (name.equals("UpdatePresentDays"))
				updatePresentDays =  para[i].getParameterAsBoolean();
			else if (name.equals("UpdateMonthlySalary"))
				updateMonthlySalary =  para[i].getParameterAsBoolean();
			else if (name.equals("UpdateAdvancePaid"))
				updateAdvancePaid =  para[i].getParameterAsBoolean();
		}
	}

	@Override
	protected String doIt() throws Exception {
		salary.createEmployeeList(reCreate, updatePresentDays, updateMonthlySalary, updateAdvancePaid);			
		salary.saveEx();
		return null;
	}

}
