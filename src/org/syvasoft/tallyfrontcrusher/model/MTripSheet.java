package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
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
		else if(DocAction.ACTION_Complete.equals(docAction)) {
			setDocStatus(DOCSTATUS_Completed);
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
			ms.setC_UOM_ID(getRent_UOM_ID());
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
}
