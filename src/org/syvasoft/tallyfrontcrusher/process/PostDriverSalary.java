package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MEmpSalaryConfig;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalaryOld;

public class PostDriverSalary extends SvrProcess {
	
	private int m_AD_Client_ID = 0;
	private int m_AD_Org_ID = 0;
	private boolean isSimulate = false;
	private Timestamp m_DateAcct = null;
	private Timestamp m_DateReceipt_1 = null;
	private Timestamp m_DateReceipt_2 = null;
	private int m_TF_Quarry_ID = 0;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		m_AD_Client_ID = Env.getAD_Client_ID(getCtx());
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();			
			if (name.equals("AD_Org_ID"))				
				m_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("TF_Quarry_ID"))
				m_TF_Quarry_ID = ((BigDecimal)para[i].getParameter()).intValue();			
			else if (name.equals("IsSimulate"))
				isSimulate = para[i].getParameterAsBoolean();
			else if (name.equals("DateAcct"))
				m_DateAcct = para[i].getParameterAsTimestamp();
			else if (name.equals("DateReceipt")) {
				m_DateReceipt_1 = para[i].getParameterAsTimestamp();
				m_DateReceipt_2 = para[i].getParameter_ToAsTimestamp();
			}
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception {
		String where = "TF_Quarry_ID = ? AND DocStatus = 'CO' AND Processed = 'Y' AND DateReceipt >= ? AND DateReceipt <= ?  " +
					" AND TF_Employee_Salary_ID IS NULL " ;
		
		String sql = "SELECT TF_Quarry_ID, Driver_ID, TF_VehicleType_ID, Count (Distinct DateReceipt) PresentDays " + 
					" FROM	TF_Boulder_Receipt br INNER JOIN M_Product p 	ON br.Vehicle_ID = p.M_Product_ID " +
					" WHERE " + where +  
					" GROUP BY TF_Quarry_ID, Driver_ID, p.TF_VehicleType_ID ";
		int no = 0;		
		PreparedStatement pstmt =  null;
		ResultSet rs = null;
		try	{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			ArrayList<Object> params = new ArrayList<Object>();
			params.add(m_TF_Quarry_ID);
			params.add(m_DateReceipt_1);
			params.add(m_DateReceipt_2);
			DB.setParameters(pstmt,params.toArray());
			rs = pstmt.executeQuery();			
			while (rs.next()) {
				no++;
				int C_BPartner_ID = rs.getInt("Driver_ID");
				int TF_VehicleType_ID = rs.getInt("TF_VehicleType_ID");
				BigDecimal presentDays = rs.getBigDecimal("PresentDays");
				MEmpSalaryConfig salConfig = MEmpSalaryConfig.getEmpSalaryConfig(getCtx(), C_BPartner_ID, TF_VehicleType_ID, m_DateAcct);
				//Create Employee Salary Entry
				MEmployeeSalaryOld salary = new MEmployeeSalaryOld(getCtx(), 0, get_TrxName());								
				salary.setAD_Org_ID(m_AD_Org_ID);
				salary.setDateAcct(m_DateAcct);				
				salary.setC_BPartner_ID(C_BPartner_ID);
				salary.setTF_VehicleType_ID(TF_VehicleType_ID);				
								
				//If no salary config, skip to next salary entry.
				if(salConfig == null) {					
					String errMsg = " NO SALARY CONFIG -> EMP:" + salary.getC_BPartner().getName() +
							" | VehicleType:" + salary.getTF_VehicleType().getName() + 
							" | Account Date:" + new SimpleDateFormat("MM/dd/yyyy").format(m_DateAcct);
					addLog(errMsg);
					continue;
				}
				//Create Salary Entry
				if(!isSimulate) {
					salary.setStd_Wage(salConfig.getStd_Wage());
					salary.setStd_Days(salConfig.getStd_Days());
					salary.setPresent_Days(presentDays);
					salary.setIsCalculated(true);
					salary.setDescription("Generated from Boulder Receipts");				
					salary.setTF_Quarry_ID(m_TF_Quarry_ID);				
					salary.setDateFrom(m_DateReceipt_1);
					salary.setDateTo(m_DateReceipt_2);
					salary.setC_ElementValue_ID(salary.getTF_Quarry().getC_ElementValue_ID());
					salary.saveEx();
					salary.processIt(MBoulderReceipt.DOCACTION_Complete);
					salary.saveEx();
					//End Employee Salary Entry
					
					//Update Employee Salary ID
					sql = " UPDATE TF_Boulder_Receipt SET TF_Employee_Salary_ID = ? WHERE " + where + " AND Driver_ID = ? AND " + 
							"(SELECT TF_VehicleType_ID FROM M_Product p WHERE p.M_Product_ID = TF_Boulder_Receipt.Vehicle_ID) = ? " ;
					params = new ArrayList<Object>();
					params.add(salary.getTF_Employee_Salary_ID());
					params.add(m_TF_Quarry_ID);
					params.add(m_DateReceipt_1);
					params.add(m_DateReceipt_2);
					params.add(C_BPartner_ID);
					params.add(TF_VehicleType_ID);
					DB.executeUpdateEx(sql, params.toArray(), get_TrxName());
					//End Update
					
					String msg = "Employee:" + salary.getC_BPartner().getName() + 
								" | Present Days:" + salary.getPresent_Days().toString() +
								" | Salary:" + salary.getSalary_Amt().toString();
					
					addLog(0, null,null, msg, MEmployeeSalaryOld.Table_ID, salary.getTF_Employee_Salary_ID());
				} // End Create
				else {
					String msg = "Employee:" + salary.getC_BPartner().getName() + 
							" | Present  Days:" + presentDays.toString() +
							" | Salary:" + salConfig.getStd_Wage().multiply(presentDays.divide(salConfig.getStd_Days()));
					addLog(msg);
				}
			}			
		}
		catch (SQLException e) {
			rollback();
			//log.log(Level.SEVERE, "", e);
			throw new DBException(e, sql.toString());
		}
		finally	{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
			
		if(isSimulate)
			return no + " Salary Entries will be posted!";
		else 
			return no + " Salary Entries have been posted!";
	}

}
