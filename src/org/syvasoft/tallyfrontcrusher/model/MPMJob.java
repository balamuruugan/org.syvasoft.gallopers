package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.process.DocAction;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;

public class MPMJob extends X_PM_Job {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7003447034439226741L;

	public MPMJob(Properties ctx, int PM_Job_ID, String trxName) {
		super(ctx, PM_Job_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MPMJob(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public void processIt(String docAction) {
		if(MPMJob.DOCSTATUS_Completed.equals(docAction)) {
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
			if(getPM_Schedule_ID() == 0)
				return;
			
			MPMSchedule schedule = new MPMSchedule(getCtx(), getPM_Schedule_ID(), get_TrxName());
			
			if(schedule.getScheduleType().equals(MPMSchedule.SCHEDULETYPE_Usage)) {
				if(getCompletedMeter() != null && getDateEnd() != null) {
					schedule.setLastMeter(getCompletedMeter());
					schedule.setNextMeter(getCompletedMeter().add(schedule.getInterval()));
					schedule.saveEx();
				}
				else
				{
					String error = "";
					if(getCompletedMeter() == null || getCompletedMeter().doubleValue() == 0) {
						error = "Completed Meter is mandatory.";
					}
					else if(getDateEnd() == null) {
						error = (error == "") ? "" : "\n ";
						error = error + "End Date is mandatory.";
					}
					throw new AdempiereUserError(error);
				}
			}
			else if(schedule.getScheduleType().equals(MPMSchedule.SCHEDULETYPE_Time)) {				
				if(getDateCompleted() != null) {
					Timestamp lastRun = getDateCompleted();
					
					schedule.setDateLastRun(getDateCompleted());
					schedule.setDateNextRun(TimeUtil.addDays(lastRun, schedule.getInterval().intValue()));
					schedule.saveEx();
				}
				else {
					throw new AdempiereUserError("Completed Date is mandatory.");
				}
			}
		}
		else if(MPMJob.DOCSTATUS_StartMaintenanceJob.equals(docAction)) {
			setDocStatus(DOCSTATUS_InProgress);
			setProcessed(false);
			
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			setDateStart(timestamp);
		}
		else if(MPMJob.DOCSTATUS_ModifyMaintenanceJob.equals(docAction)) {
			setDocStatus(DOCSTATUS_InProgress);
			setProcessed(false);
			reverseSchedule();
		}
	}
	public void reverseIt() {
		setDocStatus(DOCSTATUS_Voided);
		setProcessed(true);
		
		String sql = "SELECT MAX(pm_job_id) FROM pm_job WHERE pm_schedule_id = ? and docstatus <> 'VO'";
		int jobID = (int)DB.getSQLValue(get_TrxName(), sql, getPM_Schedule_ID());
		
		if(jobID == getPM_Job_ID()) {
			reverseSchedule();
		}
		else
			throw new AdempiereUserError("This old Job cannot be voided!");
	}
	
	private void reverseSchedule()	{
		if(getPM_Schedule_ID() == 0)
			return;
		
		MPMSchedule schedule = new MPMSchedule(getCtx(), getPM_Schedule_ID(), get_TrxName());
		
		if(schedule.getScheduleType().equals(MPMSchedule.SCHEDULETYPE_Usage)) {				
			schedule.setLastMeter(getLastMeter());
			schedule.setNextMeter(getDueMeter());
			schedule.saveEx();
		}
		else if(schedule.getScheduleType().equals(MPMSchedule.SCHEDULETYPE_Time)) {
			schedule.setDateLastRun(getDateLastDue());
			schedule.setDateNextRun(getDateDue());
			schedule.saveEx();
		}
	}
}
