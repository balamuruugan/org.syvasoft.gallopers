package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.util.DB;

public class MEmployeeAttendance extends X_TF_EmployeeAttendance {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5912416642834450467L;

	public MEmployeeAttendance(Properties ctx, int TF_EmployeeAttendance_ID, String trxName) {
		super(ctx, TF_EmployeeAttendance_ID, trxName);
	}

	public MEmployeeAttendance(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);	
	}

	@Override
	protected boolean beforeDelete() {
		if(getBiometricLogs().size() > 0) {
			throw new AdempiereException("You cannot delete this attendance record since it was generated from Biometric Attendance logs!");
		}
		return super.beforeDelete();
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		processBiometricsLogs();
		return super.afterSave(newRecord, success);
	}
	
	public List<MBiometricAttedenceLog> getBiometricLogs() {
		String whereClause = "TF_EmployeeAttendance_ID = ?";
		List<MBiometricAttedenceLog> list = new Query(getCtx(), MBiometricAttedenceLog.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getTF_EmployeeAttendance_ID())
				.list();
		return list;
	}
	
	private void processBiometricsLogs() {
				
		for(MBiometricAttedenceLog log : getBiometricLogs()) {
			log.setProcessed(getStatus().equals(STATUS_Present));
			log.saveEx();
		}
	}
	
	public static MEmployeeAttendance get(Properties ctx, int AD_Org_ID, int C_BPartner_ID, 
			Timestamp dateAcct, int TF_EmpShift_ID, String trxName) {
		String whereClause = "AD_Org_ID = ? AND C_BPartner_ID = ? AND DateAcct =  ? AND (TF_EmpShift_ID = ? OR Status = ?)"; 
		//OR Status = ?  is added for the attendance record created manually without logs that could be in different shift
		//using above condition, we are reusing the same record for the correct shift.
		// that's why after querying the att instance, the correct shift id is set once again.
		
		MEmployeeAttendance att = new Query(ctx, Table_Name, whereClause, trxName)
				.setClient_ID()
				.setParameters(AD_Org_ID, C_BPartner_ID, dateAcct, TF_EmpShift_ID, STATUS_Unknown)
				.setOrderBy("CASE WHEN TF_EmpShift_ID = " + TF_EmpShift_ID + " THEN 1 ELSE 2 END")
				.first();
		if(att == null) {
			att = new MEmployeeAttendance(ctx, 0, trxName);
			att.setAD_Org_ID(AD_Org_ID);
			att.setC_BPartner_ID(C_BPartner_ID);
			att.setDateAcct(dateAcct);			
			att.setStatus(STATUS_Unknown);			
		}
		att.setTF_EmpShift_ID(TF_EmpShift_ID);
		return att;
	}
	
	//It will be used to generate unknown/absent attendance records
	public static List<TF_MBPartner> getEmployees(Properties ctx, int AD_Org_ID, Timestamp dateAcct) {
		String whereClause = "AD_Org_ID = ? AND IsEmployee='Y' AND EnrollNo > 0 AND "
				+ "NOT EXISTS(SELECT C_BPartner_ID FROM TF_BiometricAttendence  WHERE TF_BiometricAttendence.C_BPartner_ID = C_BPartner.C_BPartner_ID "
				+ " AND TF_BiometricAttendence.DateAcct = ? )" ;
		return new Query(ctx, TF_MBPartner.Table_Name, whereClause, null)
				.setClient_ID()
				.setParameters(AD_Org_ID, dateAcct)				
				.list();		
	}
	
	public void setAttendanceFromLogs() {
		//For individual records
	}
	
	public static int generateAttendanceRecordsFromBiometricLog(Properties ctx, String trxName) throws SQLException {
		String sql = "SELECT\r\n" + 
				"	AD_Org_ID, C_BPartner_ID,DateAcct,TF_EmpShift_ID,\r\n" + 
				"	MIN(CASE WHEN InOutMode='0' THEN AttendenceTime ELSE NULL END) InTime,\r\n" + 
				"	MAX(CASE WHEN InOutMode='1' THEN AttendenceTime ELSE NULL END) OutTime,\r\n" + 
				"	TO_CHAR((MAX(CASE WHEN InOutMode='1' THEN AttendenceTime ELSE NULL END) - \r\n" + 
				"	MIN(CASE WHEN InOutMode='0' THEN AttendenceTime ELSE NULL END)  ),'HH24:MI') TT\r\n" + 
				"	\r\n" + 
				"FROM\r\n" + 
				"	TF_BiometricAttendence \r\n" + 
				"WHERE\r\n" + 
				"	Processed='N'\r\n" + 
				"GROUP BY\r\n" + 
				"	 AD_Org_ID, C_BPartner_ID,DateAcct, TF_EmpShift_ID";
		
		PreparedStatement pstmt =  null;
		ResultSet rs = null;		
		int i = 0;
		try	{	
			
			pstmt = DB.prepareStatement (sql, trxName);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int AD_Org_ID = rs.getInt("AD_Org_ID");
				int C_BPartner_ID = rs.getInt("C_BPartner_ID");
				int TF_EmpShift_ID = rs.getInt("TF_EmpShift_ID");
				Timestamp dateAcct = rs.getTimestamp("DateAcct");
				Timestamp inTime = rs.getTimestamp("InTime");
				Timestamp outTime = rs.getTimestamp("OutTime");
				String duration = rs.getString("tt");
				
				MEmployeeAttendance att = MEmployeeAttendance.get(ctx, AD_Org_ID, C_BPartner_ID, dateAcct, TF_EmpShift_ID, trxName);
				att.setDateInTime(inTime);
				att.setDateOutTime(outTime);
				att.setDuration(duration);
				att.setStatus(outTime != null && inTime != null ? STATUS_Present : STATUS_Unknown);
				att.saveEx();
				
				String whereClause = "AD_Org_ID = ? AND C_BPartner_ID = ? AND TF_EmpShift_ID = ? AND DateAcct = ? ";
				List<MBiometricAttedenceLog> list = new Query(ctx, MBiometricAttedenceLog.Table_Name, whereClause, trxName)
						.setClient_ID()
						.setParameters(AD_Org_ID, C_BPartner_ID, TF_EmpShift_ID, dateAcct)
						.list();
				for(MBiometricAttedenceLog log : list) {
					log.setTF_EmployeeAttendance_ID(att.getTF_EmployeeAttendance_ID());					
					log.saveEx();
				}
				
				i++;
			}
			
			
		}
		finally	{
			rs = null; pstmt = null;
		}
		return i;
	}
	
	public static BigDecimal getPresentDays(Properties ctx, int AD_Org_ID, int C_BPartner_ID, Timestamp dateFrom, Timestamp dateTo) {
		
		String sql = "SELECT COUNT(*) FROM TF_EmployeeAttendance WHERE AD_Org_ID = ? AND C_BPartner_ID =  ? AND "
				+ " DateAcct >= ? AND DateAcct <= ? AND Status = ? ";
		
		BigDecimal presentDays = DB.getSQLValueBD(null, sql, AD_Org_ID, C_BPartner_ID, dateFrom, dateTo, MEmployeeAttendance.STATUS_Present);
		
		return presentDays;
	}
	
	public static BigDecimal getUnknownDays(Properties ctx, int AD_Org_ID, int C_BPartner_ID, Timestamp dateFrom, Timestamp dateTo) {
		
		String sql = "SELECT COUNT(*) FROM TF_EmployeeAttendance WHERE AD_Org_ID = ? AND C_BPartner_ID =  ? AND "
				+ " DateAcct >= ? AND DateAcct <= ? AND Status = ? ";
		
		BigDecimal unknownDays = DB.getSQLValueBD(null, sql, AD_Org_ID, C_BPartner_ID, dateFrom, dateTo, MEmployeeAttendance.STATUS_Unknown);
		
		return unknownDays;
	}
}
