package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.model.MBPartner;
import org.compiere.model.MClient;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MPriceList;
import org.compiere.model.MSysConfig;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MTripSheet extends X_TF_TripSheet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3586090598937825044L;

	public static String DayShiftStartTime =  MSysConfig.getValue("DAY_SHIFT_START_TIME", "06:00 AM"); 
	public static String DayShiftEndTime =  MSysConfig.getValue("DAY_SHIFT_END_TIME", "06:00 PM");
	public static String NightShiftStartTime =  MSysConfig.getValue("NIGHT_SHIFT_START_TIME", "06:00 PM");
	public static String NightShiftEndTime =  MSysConfig.getValue("NIGHT_SHIFT_END_TIME", "06:00 AM");
	
	public MTripSheet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MTripSheet(Properties ctx, int TF_TripSheet_ID, String trxName) {
		super(ctx, TF_TripSheet_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public static BigDecimal getReceivedFuel(int vehicle_ID, Timestamp dateReport) {
		String sql = "SELECT SUM(Qty) FROM TF_Fuel_Issue WHERE TF_TripSheet_ID IS NULL AND Vehicle_ID = ? " +
					" AND DateAcct <= ? AND DocStatus = 'CO' ";
		BigDecimal receivedFuel = DB.getSQLValueBD(null, sql, vehicle_ID, dateReport);
		if(receivedFuel == null)
			receivedFuel = BigDecimal.ZERO; 
		return receivedFuel;		
	}

	public static BigDecimal getOpeningMeter(int vehicle_ID, Timestamp dateReport) {
		String sql = " SELECT Closing_Meter FROM TF_TripSheet WHERE Vehicle_ID=? AND DateReport <= ? AND " +
				" DocStatus = 'CO' ORDER BY DateReport DESC, Updated DESC ";		
		BigDecimal openingMeter = DB.getSQLValueBD(null, sql, vehicle_ID, dateReport);
		if(openingMeter == null)
			openingMeter = BigDecimal.ZERO;
		return openingMeter;
	}
	
	public static BigDecimal getOpeningFuel(int vehicle_ID, Timestamp dateReport) {
		String sql = " SELECT Closing_Fuel FROM TF_TripSheet WHERE Vehicle_ID=? AND DateReport <= ? AND " +
				" DocStatus = 'CO' ORDER BY DateReport DESC, Updated DESC ";		
		BigDecimal openingFuel = DB.getSQLValueBD(null, sql, vehicle_ID, dateReport);
		if(openingFuel == null)
			openingFuel = BigDecimal.ZERO;
		return openingFuel;
	}
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(getProductDetailIncentiveQty().doubleValue() > 0 ) {
			updateIncentiveQty();
		}
		
		setTotal_Wage(getEarned_Wage().add(getIncentive()));
		
		//If the Employee is created from Quick Entry
		if(!getC_BPartner().isEmployee() && getC_BPartner_ID() > 0) {
			MBPartner bp = new MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
			bp.setIsEmployee(true);
			bp.setIsCustomer(false);
			bp.setIsVendor(false);
			bp.saveEx();
		}
		
		//Set Issued Resource/Vehicle
		if(getC_Project_ID() > 0) {
			//MJobworkIssuedResource res = MJobworkIssuedResource.getByResource(getCtx(), getC_Project_ID(), getVehicle_ID(), get_TrxName());
			//setTF_Jobwork_IssuedResource_ID(res.getTF_Jobwork_IssuedResource_ID());
		}
		else {
			setTF_Jobwork_IssuedResource_ID(0);
		}
		
		return super.beforeSave(newRecord);
	}

	private void issueDiesel() {
		String dieselIssue = MSysConfig.getValue("TF_DIESEL_ISSUE_FROM_TRIPSHEET", "N");
		if(dieselIssue.equals("Y") && getReceived_Fuel().doubleValue() > 0) {
			MFuelIssue issue = new MFuelIssue(getCtx(), 0, get_TrxName());
			issue.setDateAcct(getDateReport());
			issue.setM_Warehouse_ID(Env.getContextAsInt(getCtx(), "#M_Warehouse_ID"));
			int dieselID = MGLPostingConfig.getMGLPostingConfig(getCtx()).getFuel_Product_ID();
			issue.setM_Product_ID(dieselID);
			issue.setVehicle_ID(getVehicle_ID());
			issue.setQty(getReceived_Fuel());
			issue.setIsCalculated(true);
			issue.setDocStatus(DOCSTATUS_Drafted);
			issue.setTF_TripSheet_ID(getTF_TripSheet_ID());
			issue.saveEx();
			issue.processIt(DocAction.ACTION_Complete);
			issue.saveEx();
		}
	}
	
	public void processIt(String docAction) {
		if(DocAction.ACTION_Prepare.equals(docAction)) {
			
			setDocStatus(DOCSTATUS_InProgress);
		}
		else {
			setDocStatus(DOCSTATUS_Completed);
			createProductDetail();
			setProcessed(true);
			updateRentQty();
			if(getExpensed_Fuel().doubleValue() > 0) {
				String dieselIssue = MSysConfig.getValue("TF_DIESEL_ISSUE_FROM_TRIPSHEET", "N");
				if(dieselIssue.equals("N")) { 
					String sql = "UPDATE TF_Fuel_Issue SET TF_TripSheet_ID = ?" +  
							"  WHERE TF_TripSheet_ID IS NULL AND Vehicle_ID = ? " +  
							" AND DateAcct <= ? AND DocStatus = 'CO' ";			
					Object[] obj = new Object[3];
					obj[0] = getTF_TripSheet_ID();
					obj[1] = getVehicle_ID();
					obj[2] = getDateReport();			
					DB.executeUpdateEx(sql,obj, get_TrxName());
				}
				else {
					issueDiesel();
				}
			}
			//Post readings into Machinery Meter Log
			if(getPM_Machinery_ID() > 0) {
				MMachineryType mt = (MMachineryType) getPM_Machinery().getPM_MachineryType();
				int defaultMeterType_ID = getC_UOM_ID();
				
				//Create Meter log if the opening meter and closing meter entered
				if(getClosing_Meter().doubleValue() > 0 ) {
					MMeterLog mLog = new MMeterLog(getCtx(), 0, get_TrxName());
					mLog.setAD_Org_ID(getAD_Org_ID());
					mLog.setDateReport(getDateReport());
					mLog.setShift(getShift());
					mLog.setPM_Machinery_ID(getPM_Machinery_ID());
					mLog.setOpening_Meter(getOpening_Meter());
					mLog.setClosing_Meter(getClosing_Meter());
					mLog.setRunning_Meter(getRunning_Meter());
					mLog.setTF_TripSheet_ID(getTF_TripSheet_ID());
					mLog.setC_UOM_ID(defaultMeterType_ID);
					mLog.setProcessed(true);
					mLog.saveEx();
				}
			}
			
			if(getTotal_Wage().doubleValue() != 0 && getC_BPartner_ID() > 0){
				MEmployeeSalaryOld salary = new MEmployeeSalaryOld(getCtx(), 0, get_TrxName());
				
				salary.setAD_Org_ID(getAD_Org_ID());
				salary.setDateAcct(getDateReport());
				salary.setPresent_Days(BigDecimal.ONE);
				salary.setC_BPartner_ID(getC_BPartner_ID());				
				salary.setSalary_Amt(getEarned_Wage());
				salary.setIncentive(getIncentive());
				salary.setDescription("TripSheet No: " + getDocumentNo());
				salary.setDocStatus(MEmployeeSalaryOld.DOCSTATUS_Drafted);		
				salary.setTF_TripSheet_ID(getTF_TripSheet_ID());
				salary.saveEx();
				
				setTF_Employee_Salary_ID(salary.getTF_Employee_Salary_ID());
				
				salary.processIt(DocAction.ACTION_Complete);
				salary.saveEx();
				
				//operator salary expenses
				MGLPostingConfig glConfig = MGLPostingConfig.getMGLPostingConfig(getCtx());
				MMachineryStatement st = new MMachineryStatement(getCtx(), 0, get_TrxName());
				st = new MMachineryStatement(getCtx(), 0, get_TrxName());
				st.setAD_Org_ID(getAD_Org_ID());
				st.setDateAcct(getDateReport());
				st.setPM_Machinery_ID(getPM_Machinery_ID());
				st.setC_ElementValue_ID(glConfig.getSalariesExpenseAcct());
				st.setDescription(getC_BPartner().getName());
				st.setC_Activity_ID(getC_Activity_ID());
				BigDecimal amount = getTotal_Wage();
				st.setExpense(amount);
				st.setTF_TripSheet_ID(getTF_TripSheet_ID());		
				st.saveEx();
				
			}
						
			//post Machinery Rent
			if(getRent_Amt().doubleValue() > 0 && getDrillingEntries().size() == 0 &&
					getRentEntries().size() == 0 ) {
				int rentAccount  = getPM_Machinery().getPM_MachineryType().getC_ElementValueRentIncome_ID();
				if(rentAccount == 0)
					throw new AdempiereException("Please set Machinery Rent Income Account!");
				
				
				//BigDecimal qty = isManual() ? getTotalMTExtended() : getRunning_Meter();
				
				// For CRANE kind of machinery, rent will be posted in the shift/day Basis.
				//if(getC_UOM_ID() != getRent_UOM_ID())
				//	qty = BigDecimal.ONE;
				
				MMachineryStatement ms = new MMachineryStatement(getCtx(), 0, get_TrxName());
				ms.setAD_Org_ID(getAD_Org_ID());
				ms.setDateAcct(getDateReport());
				ms.setPM_Machinery_ID(getPM_Machinery_ID());				
				ms.setQty(getQty());
				ms.setM_Product_ID(getJobWork_Product_ID());
				ms.setC_UOM_ID(getRent_UOM_ID());
				ms.setRate(getRate());
				ms.setIncome(getRent_Amt());
				ms.setC_ElementValue_ID(rentAccount);
				ms.setTF_TripSheet_ID(getTF_TripSheet_ID());
				ms.setC_Activity_ID(getC_Activity_ID());
				ms.saveEx();
			}
			
			processDrillingEntries();
			processRentEntries();
			processAdditionalMeters();
			processAdditionalLabourSalaries();
		}
	}
	
	public void reverseIt() {
		if(getSubcon_Invoice_ID()>0) {			
			throw new AdempiereException("You cannot modify this entry before Reverse Correct Subcontractor Invoice!");
		}
		if(getTF_Labour_Wage_ID()>0) {
			MLabourWage wage = new MLabourWage(getCtx(), getTF_Labour_Wage_ID(), get_TrxName());
			wage.reverseIt();
			wage.saveEx();
			
			setTF_Labour_Wage_ID(0);
			wage.deleteEx(true);
		}
		String dieselIssue = MSysConfig.getValue("TF_DIESEL_ISSUE_FROM_TRIPSHEET", "N");
		if(dieselIssue.equals("Y") && getReceived_Fuel().doubleValue() > 0) {
			List<MFuelIssue> issues = new Query(getCtx(), MFuelIssue.Table_Name, "DocStatus='CO' AND TF_TripSheet_ID=?", get_TrxName())
				.setParameters(getTF_TripSheet_ID()).list();
			for(MFuelIssue issue : issues) {
				issue.reverseIt();
				issue.saveEx();
				issue.deleteEx(true,get_TrxName());
			}
		}
		
		MMeterLog.deleteTripSheetMeterLog(getCtx(), getTF_TripSheet_ID(), get_TrxName());
		MMachineryStatement.deleteTripSheetEntries(getCtx(), getTF_TripSheet_ID(), get_TrxName());
		reverseDriverSalary();
		reverseDrillingEntries();
		reverseAdditionalMeters();
		reverseAdditionalLabourSalaries();
		
		setProcessed(false);
		setDocStatus(DOCSTATUS_Drafted);
	}
	
	public void reverseDriverSalary() {
		if(getTF_Employee_Salary_ID() == 0)
			return;
		MEmployeeSalaryOld salary = new MEmployeeSalaryOld(getCtx(), getTF_Employee_Salary_ID(), get_TrxName());
		
		salary.reverseIt();
		salary.setDocStatus(MEmployeeSalary.DOCSTATUS_Voided);
		salary.setProcessed(true);
		salary.saveEx();
		
	}
	
	public void updateRentQty() {
		String sql = "SELECT SUM(TotalFeet) FROM TF_DrillingEntry WHERE TF_TripSheet_ID = ? ";
		BigDecimal qty = DB.getSQLValueBDEx(get_TrxName(), sql, getTF_TripSheet_ID());
		if(qty != null) {
			setQty(qty);
			setRent_Amt(getQty().multiply(getRate()));
		}
	}
	
	public List<MDrillingEntry> getDrillingEntries() {
		String whereClause = "TF_TripSheet_ID = ?";
		List<MDrillingEntry> list = new Query(getCtx(), MDrillingEntry.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getTF_TripSheet_ID())
				.list();
		return list;
	}
	
	public List<MTripSheetProduct> getRentEntries() {
		String whereClause = "TF_TripSheet_ID = ? AND COALESCE(Rent_Amt, 0) > 0";
		List<MTripSheetProduct> list = new Query(getCtx(), MTripSheetProduct.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getTF_TripSheet_ID())
				.list();
		return list;
	}
	
	private void processRentEntries() {
		List<MTripSheetProduct> list = getRentEntries();
		if(list.size() == 0)
			return;
		int rentAccount  = getPM_Machinery().getPM_MachineryType().getC_ElementValueRentIncome_ID();
		if(rentAccount == 0)
			throw new AdempiereException("Please set Machinery Rent Income Account!");
		
		for(MTripSheetProduct rent : list) {
			MMachineryStatement ms = new MMachineryStatement(getCtx(), 0, get_TrxName());						
			ms.setAD_Org_ID(getAD_Org_ID());
			ms.setDateAcct(getDateReport());
			ms.setPM_Machinery_ID(getPM_Machinery_ID());				
			ms.setQty(rent.getTotalMT());
			ms.setM_Product_ID(rent.getM_Product_ID());
			ms.setC_UOM_ID(rent.getC_UOM_ID() > 0 ? rent.getC_UOM_ID() :  getRent_UOM_ID());
			ms.setRate(rent.getRateMT());
			ms.setIncome(rent.getRent_Amt());
			ms.setDescription(rent.getDescription());
			ms.setC_ElementValue_ID(rentAccount);
			ms.setTF_TripSheet_ID(getTF_TripSheet_ID());
			ms.setC_Activity_ID(rent.getC_Activity_ID());
			ms.saveEx();
		}
	}
	
	private void processDrillingEntries() {
		List<MDrillingEntry> list = getDrillingEntries();
		if(list.size() == 0)
			return;
		int rentAccount  = getPM_Machinery().getPM_MachineryType().getC_ElementValueRentIncome_ID();
		if(rentAccount == 0)
			throw new AdempiereException("Please set Machinery Rent Income Account!");
		
		for(MDrillingEntry drill : list) {
			MMachineryStatement ms = new MMachineryStatement(getCtx(), 0, get_TrxName());
			String desc = "Holes : " + drill.getHoles().intValue() + ", Type:" + drill.getFeet().intValue() + " " + drill.getC_UOM().getName();			
			ms.setAD_Org_ID(getAD_Org_ID());
			ms.setDateAcct(getDateReport());
			ms.setPM_Machinery_ID(getPM_Machinery_ID());				
			ms.setQty(drill.getTotalFeet());
			ms.setM_Product_ID(drill.getM_Product_ID());
			ms.setC_UOM_ID(drill.getC_UOM_ID());
			ms.setRate(drill.getFeetRate());
			ms.setIncome(drill.getDrillingCost());
			ms.setDescription(desc);
			ms.setC_ElementValue_ID(rentAccount);
			ms.setC_Activity_ID(getC_Activity_ID());
			ms.setTF_TripSheet_ID(getTF_TripSheet_ID());
			ms.saveEx();
			
			drill.setProcessed(true);
			drill.saveEx();			
		}
		
		if(getSubcontractor_ID() == 0)
			return;
		
		//Create Subcontractor Invoice
		//Purchase Invoice Header
		TF_MBPartner bp = new TF_MBPartner(getCtx(), getSubcontractor_ID(), get_TrxName());
		MGLPostingConfig config = MGLPostingConfig.getMGLPostingConfig(getCtx());
		TF_MInvoice invoice = new TF_MInvoice(getCtx(), 0, get_TrxName());
		invoice.setClientOrg(getAD_Client_ID(), getAD_Org_ID());
		invoice.setC_DocTypeTarget_ID(config.getTransporterInvoiceDocType_ID());	// AP Invoice		
		invoice.setDateInvoiced(getDateReport());
		invoice.setDateAcct(getDateReport());
		//
		invoice.setSalesRep_ID(Env.getAD_User_ID(getCtx()));
		//
		
		invoice.setBPartner(bp);
		invoice.setIsSOTrx(false);		
		
		
		//String desc = getFeet().doubleValue() + " Feet X "  + getHoles().doubleValue() + " Holes" ;		
		String desc ="";
		invoice.setDescription(desc);
		
		//Price List
		int m_M_PriceList_ID = Env.getContextAsInt(getCtx(), "#M_PriceList_ID");
		if(bp.getPO_PriceList_ID() > 0)
			m_M_PriceList_ID = bp.getPO_PriceList_ID();
		if(m_M_PriceList_ID == 0) {
			MPriceList pl = new Query(getCtx(), MPriceList.Table_Name, "IsDefault='Y' AND IsActive='Y'", get_TrxName())
					.setClient_ID().first();
			if(pl != null)
				m_M_PriceList_ID = pl.getM_PriceList_ID();
		}
		invoice.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
		if(invoice.getC_Currency_ID() == 0)
			invoice.setC_Currency_ID(MClient.get(Env.getCtx()).getC_Currency_ID());
		
				
		invoice.saveEx();
		//End Invoice Header
		for(MDrillingEntry drilEntry : list) {
			//Invoice Line - Vehicle Rental Charge
			MInvoiceLine invLine = new MInvoiceLine(invoice);
			invLine.setM_Product_ID(drilEntry.getM_Product_ID(), true);				
			invLine.setC_UOM_ID(drilEntry.getC_UOM_ID());
			
			invLine.setQty(drilEntry.getTotalFeet());
			desc = "Holes : " + drilEntry.getHoles().intValue() + ", Type:" + drilEntry.getFeet().intValue() + " " + drilEntry.getC_UOM().getName();
			invLine.setDescription(desc);
			
			invLine.setPriceActual(drilEntry.getFeetRate());
			invLine.setPriceList(drilEntry.getFeetRate());
			invLine.setPriceLimit(drilEntry.getFeetRate());
			invLine.setPriceEntered(drilEntry.getFeetRate());
			
			invLine.setC_Tax_ID(1000000);
			invLine.saveEx();
		}
		//Invoice DocAction
		if (!invoice.processIt(DocAction.ACTION_Complete))
			throw new AdempiereException("Failed when processing document - " + invoice.getProcessMsg());
		invoice.saveEx();
		
		setC_Invoice_ID(invoice.getC_Invoice_ID());		
	}
	
	private void reverseDrillingEntries() {
		List<MDrillingEntry> list = getDrillingEntries();
		if(list.size() == 0)
			return;
		
		for(MDrillingEntry drill : list) {
			drill.setProcessed(false);
			drill.saveEx();			
		}
		
		if(getC_Invoice_ID()>0) {
			TF_MInvoice inv = new TF_MInvoice(getCtx(), getC_Invoice_ID(), get_TrxName());
			if(inv.getDocStatus().equals(DOCSTATUS_Completed))
				inv.reverseCorrectIt();
			inv.saveEx();			
			setC_Invoice_ID(0);						
		}

		
	}
	
	public List<MTripSheetAddionalMeter> getAdditionalMeters() {
		String whereClause = "TF_TripSheet_ID = ? ";
		List<MTripSheetAddionalMeter> list = new Query(getCtx(), MTripSheetAddionalMeter.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getTF_TripSheet_ID())
				.list();
		return list;
	}
	
	private void processAdditionalMeters() {
		List<MTripSheetAddionalMeter> list = getAdditionalMeters();
		if(list.size() == 0)
			return;
		for(MTripSheetAddionalMeter am : list) {
			am.processIt();
			am.saveEx();
		}
	}
	
	private void reverseAdditionalMeters() {
		List<MTripSheetAddionalMeter> list = getAdditionalMeters();
		if(list.size() == 0)
			return;
		for(MTripSheetAddionalMeter am : list) {
			am.reverseIt();
			am.saveEx();
		}
	}
	
	public void updateDrillingQty() {
		String sql = "SELECT SUM(TotalFeet) FROM TF_DrillingEntry WHERE TF_TripSheet_ID = ?";
		BigDecimal drillingQty = DB.getSQLValueBDEx(get_TrxName(), sql, getTF_TripSheet_ID());
		setDrillingQty(drillingQty);
		
		if(getUnitIncentive().doubleValue() > 0) {
			setIncentive(getUnitIncentive().multiply(getDrillingQty()));
		}
		else {
			setIncentive(getDayIncentive());
		}
		
		for(MTripSheetSalary salary : getSalaryEntries()) {
			salary.setDrillingQty(drillingQty);
			salary.saveEx();
		}
		
	}
	
	
	public BigDecimal getProductDetailIncentiveQty() {
		MEmployeeIncentive inc = MEmployeeIncentive.get(getCtx(), getAD_Org_ID(), getC_BPartner_ID());		
		
		if(inc == null)
			inc = MEmployeeIncentive.get(getCtx(), getAD_Org_ID(), getC_BPartner_ID(), getC_UOM_ID());
		
		if(inc == null)
			return BigDecimal.ZERO;;
		
		String sql = "SELECT COALESCE(SUM(TotalMT),0) FROM TF_TripSheetProduct WHERE TF_TripSheet_ID = ? AND "
				+ " M_Product_ID IN (SELECT M_Product_ID FROM TF_IncentiveConfig_Applicable WHERE  TF_IncentiveConfig_ID=?)";
		BigDecimal qty = DB.getSQLValueBD(get_TrxName(), sql, getTF_TripSheet_ID(), inc.get_ID());
		return qty;
	}
	
	public void updateIncentiveQty() {
		if(getC_BPartner_ID() == 0 || getC_UOM_ID() != 1000069)
			return;
		
		BigDecimal qty = getProductDetailIncentiveQty();
		
		if(qty.doubleValue() == 0)
			return;
		
		setQtyIncentive(qty);
		
		BigDecimal incentiveAmt = BigDecimal.ZERO;
		
		boolean calcIncentive = qty.doubleValue() >= getEligibleUnit().doubleValue();
		if(calcIncentive) {
			if(getUnitIncentive().doubleValue() > 0)
				incentiveAmt = qty.multiply(getUnitIncentive());
			else if(getDayIncentive().doubleValue() > 0)
				incentiveAmt = getDayIncentive();
		}
		
		setIncentive(incentiveAmt);
	}
	
	public List<MTripSheetSalary> getSalaryEntries() {
		String whereClause = "TF_TripSheet_ID = ? ";
		List<MTripSheetSalary> list = new Query(getCtx(), MTripSheetSalary.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getTF_TripSheet_ID())
				.list();
		return list;
	}
	
	public void processAdditionalLabourSalaries() {
		for(MTripSheetSalary s : getSalaryEntries()) {
			s.processIt();
			s.saveEx();
		}
	}
	
	public void reverseAdditionalLabourSalaries() {
		for(MTripSheetSalary s : getSalaryEntries()) {
			s.reverseIt();
			s.saveEx();
		}
		
		List<MEmployeeSalaryOld> list = new Query(getCtx(), MEmployeeSalaryOld.Table_Name, "TF_TripSheet_ID = ? AND DocStatus IN ('CO') ", get_TrxName())
				.setClient_ID()
				.setParameters(getTF_TripSheet_ID())
				.list();
		
		for(MEmployeeSalaryOld salary : list) {						
			salary.reverseIt();
			salary.setDocStatus(MEmployeeSalary.DOCSTATUS_Voided);
			salary.setProcessed(true);
			salary.saveEx();
		}
	}
			
	public void createProductDetail() {
		
		int ownVehicle_ID = getPM_Machinery().getTF_RentedVehicle_ID();
		//release previously associated weighment entries for this tripsheet.
		String sql = "UPDATE TF_WeighmentEntry SET TF_TripSheet_ID = NULL, TF_TripSheetProduct_ID = NULL WHERE TF_TripSheet_ID = " + getTF_TripSheet_ID();
		DB.executeUpdate(sql, get_TrxName());
		
		//Selecting right weighment entries for the current machinery
		sql = "UPDATE TF_WeighmentEntry SET TF_TripSheet_ID = ?, TF_TripSheetProduct_ID = NULL " +  
				"  WHERE AD_Org_ID = ? AND TF_RentedVehicle_ID = ? AND GrossWeightTime BETWEEN ? AND ? AND Status IN ('CO','CL','UR')";			
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(getTF_TripSheet_ID());
		params.add(getAD_Org_ID());
		params.add(ownVehicle_ID);
		params.add(getDateStart());
		params.add(getDateEnd());					
		DB.executeUpdateEx(sql,params.toArray(), get_TrxName());
		
		//Deleting previously generated Product Detail Entries
		sql = "DELETE FROM TF_TripSheetProduct WHERE TF_TripSheet_ID = ? AND IsGenerated = 'Y'";			
		params = new ArrayList<Object>();
		params.add(getTF_TripSheet_ID());
		DB.executeUpdateEx(sql,params.toArray(), get_TrxName());
		
		// 1. Own Production, SubcContract Production
		
		sql ="SELECT\r\n" + 
				"	 we.m_product_id,p.m_product_category_id,we.quarryproductiontype,count(*) loads,\r\n" + 
				"	 sum(we.netweightunit)netweight, we.C_UOM_ID, max(r.unitrent)unitrent\r\n" + 
				"FROM \r\n" + 
				"	 tf_weighmententry we INNER JOIN m_product p ON we.m_product_id = p.m_product_id \r\n" + 
				"	 INNER JOIN TF_Machinery_RentConfig R ON we.AD_Org_ID = r.AD_Org_ID AND r.WeighmentEntryType IS NULL AND we.m_product_id = r.JobWork_Product_ID \r\n" + 
				"WHERE \r\n" + 
				"	 we.WeighmentEntryType IN ('3PR','4SR') AND TF_TripSheet_ID = ? \r\n" + 
				"GROUP BY \r\n" + 
				"	we.m_product_id,p.m_product_category_id,we.quarryproductiontype, we.C_UOM_ID";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		params = new ArrayList<Object>();
		params.add(getTF_TripSheet_ID());
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			DB.setParameters(pstmt, params.toArray());
			rs = pstmt.executeQuery();		
			while (rs.next()) {
				MTripSheetProduct product = new MTripSheetProduct(getCtx(), 0, get_TrxName());
				
				product.setAD_Org_ID(getAD_Org_ID());
				product.setTF_TripSheet_ID(getTF_TripSheet_ID());
				product.setQuarryProductionType(rs.getString("quarryproductiontype"));
				product.setM_Product_Category_ID(rs.getInt("m_product_category_id"));
				product.setM_Product_ID(rs.getInt("m_product_id"));
				product.setNoOfLoad(rs.getBigDecimal("loads"));
				product.setC_UOM_ID(rs.getInt("C_UOM_ID"));
				BigDecimal unitrent = rs.getBigDecimal("unitrent");
				BigDecimal totalmt = rs.getBigDecimal("netweight");
				
				product.setTotalMT(totalmt);
				product.setRateMT(unitrent);
				product.setRent_Amt(unitrent.multiply(totalmt));
				product.setDescription("Quarry to Crusher");
				product.setIsGenerated(true);
				 
				product.saveEx();
				
				sql = "UPDATE TF_WeighmentEntry SET TF_TripSheetProduct_ID = ? "   
					+ " WHERE TF_TripSheet_ID = ? AND WeighmentEntryType IN ('3PR','4SR') AND M_Product_ID = ? "
					+ " AND C_UOM_ID = ? ";
				params = new ArrayList<Object>();
				params.add(product.getTF_TripSheetProduct_ID());
				params.add(getTF_TripSheet_ID());
				params.add(product.getM_Product_ID());
				params.add(product.getC_UOM_ID());
				DB.executeUpdateEx(sql,params.toArray(), get_TrxName());
			}
		} catch (SQLException e) {
			throw new DBException(e, sql);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		/*
		 * 5KA = Stock to Crusher
		 * 9CA = Crusher to Stock (Sledge)
		 */
		sql = "SELECT \r\n" + 
				"	we.m_product_id,p.m_product_category_id,we.quarryproductiontype,count(*) loads,\r\n" + 
				"	 sum(we.netweightunit)netweight, we.C_UOM_ID, max(r.unitrent)unitrent, \r\n" + 
				"	 R.DESCRIPTION, we.WeighmentEntryType \r\n" + 
				"FROM\r\n" + 
				"	tf_weighmententry we INNER JOIN m_product p ON we.m_product_id = p.m_product_id \r\n" + 
				"	 INNER JOIN TF_Machinery_RentConfig R ON we.AD_Org_ID = r.AD_Org_ID AND  we.m_product_id = r.JobWork_Product_ID  AND \r\n" + 
				"	 	r.WeighmentEntryType = we.WeighmentEntryType\r\n" + 
				"WHERE\r\n" + 
				"	 we.WeighmentEntryType IN ('5KA', '9CA') AND TF_TripSheet_ID = ? \n" +
				 
				"GROUP BY we.m_product_id,p.m_product_category_id,we.quarryproductiontype, we.C_UOM_ID,\r\n" + 
				"	R.DESCRIPTION,  we.WeighmentEntryType" ;

		pstmt = null;
		rs = null;
		params = new ArrayList<Object>();
		params.add(getTF_TripSheet_ID());		
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			DB.setParameters(pstmt, params.toArray());
			rs = pstmt.executeQuery();		
			while (rs.next()) {
				MTripSheetProduct product = new MTripSheetProduct(getCtx(), 0, get_TrxName());
				
				product.setAD_Org_ID(getAD_Org_ID());
				product.setTF_TripSheet_ID(getTF_TripSheet_ID());
				product.setQuarryProductionType(rs.getString("quarryproductiontype"));
				product.setM_Product_Category_ID(rs.getInt("m_product_category_id"));
				product.setM_Product_ID(rs.getInt("m_product_id"));
				product.setNoOfLoad(rs.getBigDecimal("loads"));
				
				BigDecimal unitrent = rs.getBigDecimal("unitrent");
				BigDecimal totalmt = rs.getBigDecimal("netweight");
				
				product.setC_UOM_ID(rs.getInt("C_UOM_ID"));
				product.setTotalMT(totalmt);
				product.setRateMT(unitrent);
				product.setRent_Amt(unitrent.multiply(totalmt));
				product.setDescription(rs.getString("Description"));				
				product.setIsGenerated(true);
				
				product.saveEx();
				
				
				sql = "UPDATE TF_WeighmentEntry SET TF_TripSheetProduct_ID = ?, Status='CL' "   // There may be a bug arise incase of scheduler delay of processing
						+ " WHERE TF_TripSheet_ID = ? AND WeighmentEntryType IN  ('5KA', '9CA') AND M_Product_ID = ? "
						+ " AND C_UOM_ID = ? AND WeighmentEntryType = ?";
				params = new ArrayList<Object>();
				params.add(product.getTF_TripSheetProduct_ID());
				params.add(getTF_TripSheet_ID());
				params.add(product.getM_Product_ID());
				params.add(product.getC_UOM_ID());				
				params.add(rs.getString("WeighmentEntryType"));
				DB.executeUpdateEx(sql,params.toArray(), get_TrxName());
				
			}
		} catch (SQLException e) {
			throw new DBException(e, sql);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
	
		// Sales Delivery
		sql = "SELECT \r\n" + 
				"	we.m_product_id,p.m_product_category_id,	\r\n" + 
				"	count(*) loads, \r\n" + 
				"	sum(IL.QtyEntered)netweight,\r\n" + 
				"	il.C_UOM_ID,\r\n" + 
				"	il.Price unitrent\r\n" + 
				//"	'Sales Delivery - ' || il.Description Description \r\n" + 
				"FROM\r\n" + 
				"	tf_weighmententry we INNER JOIN M_InOut io \r\n" + 
				"		ON we.TF_WeighmentEntry_ID = io.TF_WeighmentEntry_ID AND\r\n" + 
				"			io.DocStatus IN ('CO','CL')\r\n" + 
				"	INNER JOIN TF_RentedVehicle rv \r\n" + 
				"		ON rv.TF_RentedVehicle_ID = we.TF_RentedVehicle_ID\r\n" + 
				"	INNER JOIN M_InOutLine il \r\n" + 
				"		ON il.M_Product_ID = rv.M_Product_ID AND il.M_InOut_ID = io.M_InOut_ID	\r\n" + 
				"	INNER JOIN M_Product p\r\n" + 
				"		ON p.M_Product_ID = we.M_Product_ID\r\n" + 
				"WHERE \r\n" + 
				"	we.WeighmentEntryType IN ('1SO') AND we.TF_TripSheet_ID = ?  \r\n" + 
				"GROUP BY \r\n" + 
				"	we.m_product_id,p.m_product_category_id,il.Price, il.C_UOM_ID";

		pstmt = null;
		rs = null;
		params = new ArrayList<Object>();
		params.add(getTF_TripSheet_ID());
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			DB.setParameters(pstmt, params.toArray());
			rs = pstmt.executeQuery();		
			while (rs.next()) {
				MTripSheetProduct product = new MTripSheetProduct(getCtx(), 0, get_TrxName());
				
				product.setAD_Org_ID(getAD_Org_ID());
				product.setTF_TripSheet_ID(getTF_TripSheet_ID());				
				product.setM_Product_Category_ID(rs.getInt("m_product_category_id"));
				product.setM_Product_ID(rs.getInt("m_product_id"));
				product.setNoOfLoad(rs.getBigDecimal("loads"));				
				BigDecimal unitrent = rs.getBigDecimal("unitrent");
				BigDecimal totalmt = rs.getBigDecimal("netweight");				
				product.setTotalMT(totalmt);
				product.setRateMT(unitrent);
				product.setC_UOM_ID(rs.getInt("C_UOM_ID"));
				product.setRent_Amt(unitrent.multiply(totalmt));
				//product.setDescription(rs.getString("Description"));	
				product.setDescription("Sales Delivery");
				product.setIsGenerated(true);
				
				product.saveEx();
				
				
				sql = "UPDATE TF_WeighmentEntry SET TF_TripSheetProduct_ID = ? "   
						+ " WHERE TF_TripSheet_ID = ? AND WeighmentEntryType IN ('1SO') AND M_Product_ID = ? "
						+ " AND FreightRule_ID = ? AND FreightPrice = ?";
				params = new ArrayList<Object>();
				params.add(product.getTF_TripSheetProduct_ID());
				params.add(getTF_TripSheet_ID());
				params.add(product.getM_Product_ID());
				params.add(product.getC_UOM_ID());
				params.add(product.getRateMT());
				DB.executeUpdateEx(sql,params.toArray(), get_TrxName());
					
			}
		} catch (SQLException e) {
			throw new DBException(e, sql);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		
		updatePurchaseFreightDetails();
		
		
		sql = "SELECT \r\n" + 
				"	we.m_product_id, p.m_product_category_id,	\r\n" + 
				"	count(*) loads, \r\n" + 
				"	sum(we.FreightQty )netweight,\r\n" + 
				"	we.FreightRule_ID C_UOM_ID,\r\n" + 
				"	we.FreightPrice  unitrent\r\n" + 
				"	\r\n" + 
				"FROM\r\n" + 
				"	tf_weighmententry we INNER JOIN M_Product p\r\n" + 
				"	 ON p.M_Product_ID = we.M_Product_ID\r\n" + 
				"WHERE \r\n" + 
				"	we.WeighmentEntryType IN ('2PO') AND we.TF_TripSheet_ID = ? \r\n" + 
				"GROUP BY \r\n" + 
				"	p.m_product_category_id, we.m_product_id,we.FreightPrice , WE.FreightRule_ID \r\n"; 
				

		pstmt = null;
		rs = null;
		
		params = new ArrayList<Object>();
		params.add(getTF_TripSheet_ID());
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			DB.setParameters(pstmt, params.toArray());
			rs = pstmt.executeQuery();		
			while (rs.next()) {
				MTripSheetProduct product = new MTripSheetProduct(getCtx(), 0, get_TrxName());
				
				product.setAD_Org_ID(getAD_Org_ID());
				product.setTF_TripSheet_ID(getTF_TripSheet_ID());				
				product.setM_Product_Category_ID(rs.getInt("m_product_category_id"));
				product.setM_Product_ID(rs.getInt("m_product_id"));
				product.setNoOfLoad(rs.getBigDecimal("loads"));
				
				BigDecimal unitrent = rs.getBigDecimal("unitrent");
				BigDecimal totalmt = rs.getBigDecimal("netweight");
				
				product.setTotalMT(totalmt);
				product.setRateMT(unitrent);
				product.setRent_Amt(unitrent.multiply(totalmt));
				product.setC_UOM_ID(rs.getInt("C_UOM_ID"));
				product.setDescription("Purchase Delivery");			
				product.setIsGenerated(true);
				
				product.saveEx();
				
				
				sql = "UPDATE TF_WeighmentEntry SET TF_TripSheetProduct_ID = ? "   
						+ " WHERE TF_TripSheet_ID = ? AND WeighmentEntryType IN ('2PO') AND M_Product_ID = ? "
						+ " AND FreightRule_ID = ? AND FreightPrice = ?";
				params = new ArrayList<Object>();
				params.add(product.getTF_TripSheetProduct_ID());
				params.add(getTF_TripSheet_ID());
				params.add(product.getM_Product_ID());
				params.add(product.getC_UOM_ID());
				params.add(product.getRateMT());
				DB.executeUpdateEx(sql,params.toArray(), get_TrxName());
			}
		} catch (SQLException e) {
			throw new DBException(e, sql);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
	}
	
	public void updatePurchaseFreightDetails() {
		String whereClause = "WeighmentEntryType='2PO' AND Status IN ('CO','CL') AND TF_TripSheet_ID = ? AND TF_TripSheetProduct_ID IS NULL";
				
		List<MWeighmentEntry> weList = new Query(getCtx(), MWeighmentEntry.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getTF_TripSheet_ID())
				.list();
		
		BigDecimal qty = BigDecimal.ZERO;
		BigDecimal price = BigDecimal.ZERO;
		BigDecimal RateMTKM = BigDecimal.ZERO;
		MLumpSumRentConfig lumpsumConfig;
		int Load_UOM_ID = MSysConfig.getIntValue("LOAD_UOM", 1000072, getAD_Client_ID());
		int KM_UOM_ID = MSysConfig.getIntValue("KM_UOM", 1000071, getAD_Client_ID());
		int MT_KM_UOM_ID = MSysConfig.getIntValue("MT_KM_UOM", 1000071, getAD_Client_ID());
		int Rent_UOM_ID = 0;
		int TF_LumpSumRentConfig_ID = 0;
		BigDecimal RentMargin = BigDecimal.ZERO;
		
		for(MWeighmentEntry we : weList) {
			MDestination dest = new MDestination(getCtx(), we.getTF_Destination_ID(), get_TrxName());
			lumpsumConfig = MLumpSumRentConfig.getFreightConfig(getCtx(), we.getAD_Org_ID(), 0, we.getC_BPartner_ID(), we.getM_Product_ID(), 
					we.getTF_Destination_ID(), we.getTF_VehicleType_ID(), dest.getDistance(), get_TrxName());
				
			if(lumpsumConfig != null) {
				//ioLine.set_ValueOfColumn("FreightRule", we.getFreightRule());
				price = lumpsumConfig.getCustomerFreightPrice(we.getC_BPartner_ID());
				
				if(lumpsumConfig.getC_UOM_ID() == Load_UOM_ID)
				{
					Rent_UOM_ID = Load_UOM_ID;
					qty = BigDecimal.ONE;
					
				}
				else if(lumpsumConfig.getC_UOM_ID() == KM_UOM_ID)
				{
					Rent_UOM_ID = KM_UOM_ID;
					qty = dest.getDistance();									
				}
				else if(lumpsumConfig.getC_UOM_ID() == MT_KM_UOM_ID)
				{
					Rent_UOM_ID = MT_KM_UOM_ID;
					qty = we.getMT();									
					RateMTKM = price;
				}
				else
				{
					Rent_UOM_ID = lumpsumConfig.getC_UOM_ID();
					qty = we.getNetWeightUnit();									
				}
				TF_LumpSumRentConfig_ID = lumpsumConfig.getTF_LumpSumRent_Config_ID();
				RentMargin = (BigDecimal) lumpsumConfig.getCustomerFreightMargin(we.getC_BPartner_ID());
				
				we.setTF_LumpSumRent_Config_ID(TF_LumpSumRentConfig_ID);	
				
			}
			else {
				Rent_UOM_ID = Load_UOM_ID;
				qty = BigDecimal.ONE;
				price = BigDecimal.ZERO;
			}
			we.setFreightRule_ID(Rent_UOM_ID);
			we.setFreightPrice(price);
			we.setFreightQty(qty);
			//MT KM is pending 
			we.saveEx();
		}
	}
}
