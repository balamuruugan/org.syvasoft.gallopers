package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.model.Query;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.io.StreamTokenizer;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.compiere.model.MPeriod;
import org.compiere.model.MSysConfig;
import org.compiere.model.MYear;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MFuelIssue;
import org.syvasoft.tallyfrontcrusher.model.MWeek;

public class GenerateWeeksByPeriod extends SvrProcess {
	
	private String docAction="CO";
	private int RecordId = 0;
	
	@Override
	protected void prepare() {
		RecordId = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		
		String strStartDayOfWeek =  MSysConfig.getValue("START_DAY_OF_WEEK", "7");
		
		List<MPeriod> periods = new Query(getCtx(), MPeriod.Table_Name, "C_Year_ID = " + RecordId,get_TrxName()).setClient_ID().list();
		
		int StartDayOfWeek = Integer.parseInt(strStartDayOfWeek);
		int  EndDayOfWeek = 0;
		
		if(StartDayOfWeek == 0) {
			EndDayOfWeek = 6;
		}
		else {
			EndDayOfWeek = StartDayOfWeek - 1;
		}
			
		for (MPeriod mPeriod : periods) {
			Timestamp startDate = mPeriod.getStartDate();
			Timestamp endDate = mPeriod.getEndDate();
			Timestamp weekendDate = null;
			int DayNo = startDate.getDay();
			
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(startDate.getTime());
		    cal.add(Calendar.DAY_OF_MONTH, EndDayOfWeek - DayNo);
		    
		    weekendDate = new Timestamp(cal.getTime().getTime());		
		    
		    int weekno = 1;
		    while (weekendDate.before(endDate)) {
		    	MWeek week = new MWeek(getCtx(), 0, get_TrxName());
		    	
		    	week.setAD_Org_ID(mPeriod.getAD_Org_ID());
		    	
		    	String name = (new SimpleDateFormat("dd-MM-yyyy").format(startDate)) + " - " + (new SimpleDateFormat("dd-MM-yyyy").format(weekendDate));
		    	
		    	week.setName(name);
		    	week.setWeekNo(weekno);
		    	week.setStartDate(startDate);
		    	week.setEndDate(weekendDate);
		    	week.setC_Period_ID(mPeriod.getC_Period_ID());
		    	week.saveEx();
		    	
		    	// Get next week info
		    	weekno++;
		    	cal = Calendar.getInstance();
				cal.setTimeInMillis(weekendDate.getTime());
			    cal.add(Calendar.DAY_OF_MONTH, 1);
			    
			    startDate = new Timestamp(cal.getTime().getTime());		
			    
			    cal = Calendar.getInstance();
				cal.setTimeInMillis(startDate.getTime());
			    cal.add(Calendar.DAY_OF_MONTH, EndDayOfWeek - startDate.getDay());
			    
			    weekendDate = new Timestamp(cal.getTime().getTime());	
			    
			    if(weekendDate.after(endDate) || weekendDate.equals(endDate))
			    {
			    	week = new MWeek(getCtx(), 0, get_TrxName());
			    	
			    	week.setAD_Org_ID(mPeriod.getAD_Org_ID());
			    	
			    	name = (new SimpleDateFormat("dd-MM-yyyy").format(startDate)) + " - " + (new SimpleDateFormat("dd-MM-yyyy").format(endDate));
			    	
			    	week.setName(name);
			    	week.setWeekNo(weekno);
			    	week.setStartDate(startDate);
			    	week.setEndDate(endDate);
			    	week.setC_Period_ID(mPeriod.getC_Period_ID());
			    	week.saveEx();
			    }
		    }
		}
		
		return null;
	}

}
