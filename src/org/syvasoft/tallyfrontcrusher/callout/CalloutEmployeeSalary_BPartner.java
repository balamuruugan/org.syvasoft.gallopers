package org.syvasoft.tallyfrontcrusher.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalaryOld;
import org.syvasoft.tallyfrontcrusher.model.MJobworkAssignedEmployee;

public class CalloutEmployeeSalary_BPartner implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		int C_BPartner_ID = 0;
		int AD_Org_ID = (int) mTab.getValue(MEmployeeSalaryOld.COLUMNNAME_AD_Org_ID);
		int C_Project_ID = 0;
		if(mTab.getValue(MEmployeeSalaryOld.COLUMNNAME_C_BPartner_ID) != null) {
			C_BPartner_ID = (int) mTab.getValue(MEmployeeSalaryOld.COLUMNNAME_C_BPartner_ID);
			MJobworkAssignedEmployee jwEmp = MJobworkAssignedEmployee.getJobwork(AD_Org_ID, C_BPartner_ID);
			if(jwEmp != null) {
				C_Project_ID = jwEmp.getC_Project_ID();
			}
		}
		if(C_Project_ID == 0)
			mTab.setValue(MEmployeeSalaryOld.COLUMNNAME_C_Project_ID, null);
		else
			mTab.setValue(MEmployeeSalaryOld.COLUMNNAME_C_Project_ID, C_Project_ID);
		return null;
	}

}
