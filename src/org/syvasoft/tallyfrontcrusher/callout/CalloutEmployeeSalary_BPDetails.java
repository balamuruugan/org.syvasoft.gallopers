package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MPeriod;
import org.compiere.model.Query;
import org.syvasoft.tallyfrontcrusher.model.MEmpSalaryConfig;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalary;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalaryOld;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalaryAdvance;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalaryDet;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;

public class CalloutEmployeeSalary_BPDetails implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,GridField mField, Object value, Object oldValue) {
		BigDecimal stdDays = BigDecimal.ZERO;
		BigDecimal absentees = (BigDecimal)mTab.getValue(MEmployeeSalaryDet.COLUMNNAME_Absentees);
		
		TF_MBPartner bpartner = new TF_MBPartner(ctx, (int)mTab.getValue(MEmployeeSalaryDet.COLUMNNAME_C_BPartner_ID), null);
		
		if(bpartner != null) {
			
			String whereClause = "TF_EmployeeSalary_ID = ?";
			
			MEmployeeSalary EmployeeSalary =  new Query(ctx,MEmployeeSalary.Table_Name, whereClause, null).setClient_ID()
					.setParameters((int)mTab.getValue(MEmployeeSalaryDet.COLUMNNAME_TF_EmployeeSalary_ID)).first();
			
			String wherecondition = "TF_EmployeeSalary.TF_EmployeeSalary_id IN (SELECT d.TF_EmployeeSalary_id FROM TF_EmployeeSalary_Det d " + 
					"WHERE d.TF_EmployeeSalary_id = TF_EmployeeSalary.TF_EmployeeSalary_id AND d.C_BPartner_ID = ?) AND DateAcct < ?";
			
			MEmployeeSalary prevEmployeeSalary =  new Query(ctx,MEmployeeSalary.Table_Name, wherecondition, null).setClient_ID()
					.setParameters(bpartner.getC_BPartner_ID(), EmployeeSalary.getDateAcct()).setOrderBy(MEmployeeSalary.COLUMNNAME_DateAcct + " desc").first();
			
			wherecondition = "TF_EmployeeSalary_id = ? AND C_BPartner_ID = ?";
			MEmployeeSalaryDet prevEmployeeSalaryDet = null;
			
			if(prevEmployeeSalary != null)
				prevEmployeeSalaryDet = new Query(ctx,MEmployeeSalaryDet.Table_Name, wherecondition, null).setClient_ID()
					.setParameters(prevEmployeeSalary.getTF_EmployeeSalary_ID(), bpartner.getC_BPartner_ID()).first();
			
			wherecondition = "TF_EmployeeSalary_Det.TF_EmployeeSalary_id IN (SELECT h.TF_EmployeeSalary_id FROM TF_EmployeeSalary h " + 
					"WHERE h.TF_EmployeeSalary_id = TF_EmployeeSalary_Det.TF_EmployeeSalary_id AND h.DateAcct < ?) and C_BPartner_ID = ?";
			
			Query query = new Query(ctx,MEmployeeSalaryDet.Table_Name, wherecondition, null).setClient_ID()
					.setParameters(EmployeeSalary.getDateAcct(), bpartner.getC_BPartner_ID());
			
			BigDecimal deductedAmt = (BigDecimal) query.aggregate(MEmployeeSalaryDet.COLUMNNAME_DeductAdvance, Query.AGGREGATE_SUM);
			
			wherecondition = "C_BPartner_ID = ? AND DateAcct < ? AND DocStatus IN ('CO','CL')";
			
			Query querySalAdvance = new Query(ctx, MEmployeeSalaryAdvance.Table_Name, wherecondition, null).setClient_ID()
					.setParameters(bpartner.getC_BPartner_ID(), EmployeeSalary.getDateAcct());
			
			BigDecimal salaryAdvance = (BigDecimal) querySalAdvance.aggregate(MEmployeeSalaryAdvance.COLUMNNAME_Advance_Amt, Query.AGGREGATE_SUM);
			
			BigDecimal salaryAdvRemaining = BigDecimal.ZERO;
			
			if(salaryAdvance.intValue() > 0)
				salaryAdvRemaining = salaryAdvance.subtract(deductedAmt);
			
			if(prevEmployeeSalaryDet != null)
				prevEmployeeSalaryDet =  query.first();
			
			mTab.setValue(MEmployeeSalaryDet.COLUMNNAME_Designation, bpartner.getDesignation());
			mTab.setValue(MEmployeeSalaryDet.COLUMNNAME_Salary, bpartner.getMonthlySalary());
			mTab.setValue(MEmployeeSalaryDet.COLUMNNAME_NoOfDays, EmployeeSalary.getStd_Days());
			mTab.setValue(MEmployeeSalaryDet.COLUMNNAME_SalaryDue, bpartner.getMonthlySalary());
			mTab.setValue(MEmployeeSalaryDet.COLUMNNAME_AdvancePaid, salaryAdvRemaining);
			
			if(prevEmployeeSalaryDet != null) {
				mTab.setValue(MEmployeeSalaryDet.COLUMNNAME_UnpaidSalary, prevEmployeeSalaryDet.getSalaryWithheld());
				mTab.setValue(MEmployeeSalaryDet.COLUMNNAME_NetSalary, bpartner.getMonthlySalary().add(prevEmployeeSalaryDet.getSalaryWithheld()).setScale(0,RoundingMode.HALF_UP));
			}
			else {
				mTab.setValue(MEmployeeSalaryDet.COLUMNNAME_UnpaidSalary, BigDecimal.ZERO);
				mTab.setValue(MEmployeeSalaryDet.COLUMNNAME_NetSalary, bpartner.getMonthlySalary().setScale(0,RoundingMode.HALF_UP));				
			}
		}
		
		return null;
	}

	
}
