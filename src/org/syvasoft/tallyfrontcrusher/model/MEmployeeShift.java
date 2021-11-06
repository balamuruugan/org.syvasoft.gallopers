package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.TimeUtil;

public class MEmployeeShift extends X_TF_EmpShift{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6237619174328064099L;

	public MEmployeeShift(Properties ctx, int TF_EmpShift_ID, String trxName) {
		super(ctx, TF_EmpShift_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MEmployeeShift(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	Timestamp startTime = null;
	Timestamp endTime = null;
	
	public Timestamp getStartTime(Timestamp date) {
		if(startTime != null)
			return startTime;
		String sql = "SELECT TO_timestamp(TO_CHAR(?::timestamp without time zone,'yyyy-MM-dd ') ||  ?,'yyyy-MM-dd HH24:MI')";
		startTime = DB.getSQLValueTSEx(get_TrxName(), sql, date, getBeginTime());				
		return startTime;
	}

	public Timestamp getEndTime(Timestamp date) {
		if(endTime != null)
			return endTime;
		
		String sql = "SELECT TO_timestamp(TO_CHAR(?::timestamp without time zone,'yyyy-MM-dd ') ||   ?,'yyyy-MM-dd HH24:MI')";
		endTime = DB.getSQLValueTSEx(get_TrxName(), sql, date, getEndTime());
		
		if(endTime.compareTo(getStartTime(date)) < 0)
			endTime = TimeUtil.addDays(endTime, 1);
		
		return endTime;
	}
	
	public Timestamp getDateAcct(Timestamp date, String inoutMode) {
		String sql = "SELECT getAttendanceAcctDate(? :: integer, ?, ?)";
		Timestamp dateAcct = DB.getSQLValueTSEx(get_TrxName(), sql, getTF_EmpShift_ID(), date, inoutMode);
		
		return dateAcct;
		
	}
}
