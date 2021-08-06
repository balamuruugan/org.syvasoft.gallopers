package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;
import org.compiere.process.SvrProcess;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;

public class MDispensePlan extends X_TF_DispensePlan {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8086375269293054622L;

	public Timestamp ScheduleDate;
	
	public String ShipmentTo = "";
	
	public int ShipmentDestination = 0;
	
	public BigDecimal DispatchQty = BigDecimal.ZERO;
	
	public String DeliveryContact = "";
	
	public boolean OverDeliveryQty = false;
	
	public boolean CarryForwardPrevDayDP = false;
	
	public String Priority = "";
	
	public int TF_VehicleType_ID = 0;
	
	public int FreightUOM_ID = 0;
	
	public String ShipmentAddress = "";
	
	public String CustomerGSTIN = "";
	
	public BigDecimal ShipmentRate = BigDecimal.ZERO;
	
	public boolean CustomerTransporter = false;
	
	public boolean ArrangeTransport = false;
	
	
	public MDispensePlan(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDispensePlan(Properties ctx, int TF_DispensePlan_ID, String trxName) {	
		super(ctx, TF_DispensePlan_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(newRecord) {
			String where = " AD_Org_ID = " + getAD_Org_ID() + " AND ScheduleDate = '" + getScheduleDate() + "'";
			
			MDispensePlan dispensePlan = new Query(getCtx(), MDispensePlan.Table_Name, where, get_TrxName()).first();
			
			if(dispensePlan != null) {
				throw new AdempiereException("Schedule Date already exists in Dispatch Plan!");
			}
		}
		else {
			String where =  " AD_Org_ID = " + getAD_Org_ID() + " AND  ScheduleDate = '" + getScheduleDate() + "' AND TF_DispensePlan_ID != " + getTF_DispensePlan_ID();
			
			MDispensePlan dispensePlan = new Query(getCtx(), MDispensePlan.Table_Name, where, get_TrxName()).first();
			
			if(dispensePlan != null) {
				throw new AdempiereException("Schedule Date already exists in Dispatch Plan!");
			}
		}
		return super.beforeSave(newRecord);
	}
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if(newRecord) {
			
		}
		return super.afterSave(newRecord, success);
	}

	public void processIt(String docAction) {
		if(MDispensePlan.DOCSTATUS_Completed.equals(docAction)) {
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
			saveEx();
			List<MDispensePlanLine> dispenseLines = new Query(getCtx(), MDispensePlanLine.Table_Name,"TF_DispensePlan_ID = " + getTF_DispensePlan_ID(),get_TrxName()).list();
			
			for(MDispensePlanLine dpline : dispenseLines) {
				dpline.setDocStatus(DOCSTATUS_InProgress);
				dpline.saveEx();
			}
		}			
	}
	public void reverseIt() {
		setProcessed(false);
		setDocStatus(DOCSTATUS_Drafted);
	}
	
	public void createDP(boolean recreate)
	{
		
	}
	
	public MDispensePlanLine createDPLinesFromOrder(ResultSet rs) throws SQLException {
		
		String sql = "";
		if(ShipmentTo == null && ShipmentDestination == 0) {
			sql = " TF_DispensePlan_ID = "+ getTF_DispensePlan_ID() + " AND C_OrderLine_ID = " + rs.getInt(MDispensePlanLine.COLUMNNAME_C_OrderLine_ID);
		}
		else {
			sql = " ShipmentTo = '" + ShipmentTo + "' AND ShipmentDestination = '" + ShipmentDestination + "' AND  TF_DispensePlan_ID = "+ getTF_DispensePlan_ID() + " AND C_OrderLine_ID = " + rs.getInt(MDispensePlanLine.COLUMNNAME_C_OrderLine_ID);
		}
			
		MDispensePlanLine dispensePlan = new Query(getCtx(), MDispensePlanLine.Table_Name, sql, get_TrxName()).first();
		MDispensePlanLine dispenseLine = new MDispensePlanLine(getCtx(), 0, get_TrxName());
		
		if(dispensePlan == null) {		
			
			sql = " C_OrderLine_ID = "+ rs.getInt(MDispensePlanLine.COLUMNNAME_C_OrderLine_ID);
			
			MDispensePlanLine prevdispenseLine = new Query(getCtx(), MDispensePlanLine.Table_Name, sql, get_TrxName()).first();
			
			BigDecimal balanceQty = (rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_QtyOrdered)).subtract(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_QtyDelivered));
			
			if(ScheduleDate != null) {
				dispenseLine.setScheduleDate(ScheduleDate);
			}
			else {
				dispenseLine.setScheduleDate(getScheduleDate());
			}
			
			if(Priority == "") {
				dispenseLine.setPriority(MDispensePlanLine.PRIORITY_Normal);
			}
			else {
				dispenseLine.setPriority(Priority);
			}
				
			dispenseLine.setType(MDispensePlanLine.TYPE_Order);
			dispenseLine.setC_OrderLine_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_C_OrderLine_ID));
			dispenseLine.setDateOrdered(rs.getTimestamp(MDispensePlanLine.COLUMNNAME_DateOrdered));
			dispenseLine.setPaymentRule(rs.getString(MDispensePlanLine.COLUMNNAME_PaymentRule));
			dispenseLine.setC_BPartner_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_C_BPartner_ID));
			dispenseLine.setContactPerson(rs.getString(MDispensePlanLine.COLUMNNAME_ContactPerson));
			dispenseLine.setTF_VehicleType_ID(TF_VehicleType_ID);
			if(DeliveryContact != null) {
				dispenseLine.setDeliveryContact(DeliveryContact);
			}
			else {
				dispenseLine.setDeliveryContact(rs.getString(MDispensePlanLine.COLUMNNAME_DeliveryContact));
			}
			
			TF_MBPartner bpartner = new TF_MBPartner(getCtx(), rs.getInt(MDispensePlanLine.COLUMNNAME_C_BPartner_ID),get_TrxName());
			
			if(rs.getInt(MDispensePlanLine.COLUMNNAME_TF_Destination_ID) == 0) {
				String where = " Name = '" + bpartner.getAddress4() + "'";
				
				MDestination destination = new Query(getCtx(), MDestination.Table_Name, where, null).first();
				
				dispenseLine.setTF_Destination_ID(destination.getTF_Destination_ID());
				//dispenseLine.setShipmentDestination(destination.getTF_Destination_ID());
			}
			else {
				dispenseLine.setTF_Destination_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_TF_Destination_ID));
			}
			dispenseLine.setLine(10);
			dispenseLine.setM_Product_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_M_Product_ID));
			dispenseLine.setM_Warehouse_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_M_Warehouse_ID));
			dispenseLine.setDescription(rs.getString(MDispensePlanLine.COLUMNNAME_Description));
			dispenseLine.setC_UOM_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_C_UOM_ID));
			
			dispenseLine.setDispenseQty(DispatchQty);
			dispenseLine.setBalanceDPQty(DispatchQty);
			dispenseLine.setDeliveredDPQty(BigDecimal.ZERO);
			
			dispenseLine.setQtyOrdered(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_QtyOrdered));
			dispenseLine.setQtyDelivered(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_QtyDelivered));
			dispenseLine.setBalanceQty(balanceQty);
			
			dispenseLine.setC_Tax_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_C_Tax_ID));
			dispenseLine.setIsTaxIncluded(rs.getBoolean(MDispensePlanLine.COLUMNNAME_IsTaxIncluded));
			dispenseLine.setIsRoyaltyPassInclusive(rs.getBoolean(MDispensePlanLine.COLUMNNAME_IsRoyaltyPassInclusive));
			dispenseLine.setIsRentInclusive(rs.getBoolean(MDispensePlanLine.COLUMNNAME_IsRentInclusive));
			
			if(FreightUOM_ID > 0 && !rs.getBoolean(MDispensePlanLine.COLUMNNAME_IsRentInclusive)) {				
				dispenseLine.setFreightUOM_ID(FreightUOM_ID);
			}
			else {
				dispenseLine.setFreightUOM_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_FreightUOM_ID));
			}
			
			dispenseLine.setUnitPrice(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_UnitPrice));
			dispenseLine.setPriceEntered(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_PriceEntered));
			dispenseLine.setDiscount(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_Discount));
			dispenseLine.setFreightAmt(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_FreightAmt));
			dispenseLine.setLineNetAmt(BigDecimal.ZERO);	
			dispenseLine.setTF_DispensePlan_ID(getTF_DispensePlan_ID());
			
			if(ShipmentTo != null) {
				dispenseLine.setShipmentTo(ShipmentTo);
			}

			if(ShipmentDestination > 0) {
				dispenseLine.setShipmentDestination_ID(ShipmentDestination);
			}
			
			if(ShipmentAddress != null) {
				dispenseLine.setShipmentAddress(ShipmentAddress);
			}
			
			if(CustomerGSTIN != null) {
				dispenseLine.setCustomerGSTIN(CustomerGSTIN);
			}
			
			if(ShipmentRate != null) {
				dispenseLine.setShipmentRate(ShipmentRate);
			}
			dispenseLine.setOverUnitDelivery(OverDeliveryQty);			
			dispenseLine.setAllowCarryForward(CarryForwardPrevDayDP);
			
			if(prevdispenseLine != null) {
				dispenseLine.setOriginDate(prevdispenseLine.getOriginDate());
			}
			else {
				dispenseLine.setOriginDate(getScheduleDate());
			}
			dispenseLine.setIsPriceConfidential(rs.getBoolean(MDispensePlanLine.COLUMNNAME_IsPriceConfidential));
			
			if(getDocStatus().equals(MDispensePlan.DOCSTATUS_Drafted)) {
				dispenseLine.setDocStatus(MDispensePlanLine.DOCSTATUS_Drafted);
			}
			else {
				dispenseLine.setDocStatus(MDispensePlanLine.DOCSTATUS_InProgress);				
			}
			
			if(CustomerTransporter) {
				dispenseLine.setCustomerTransporter(CustomerTransporter);
			}
			else {
				dispenseLine.setCustomerTransporter(rs.getBoolean(MDispensePlanLine.COLUMNNAME_CustomerTransporter));
			}
			
			dispenseLine.setArrangeTransport(ArrangeTransport);
			dispenseLine.saveEx();
		}
		else {
			throw new AdempiereException("Selected Order Line already exists in Dispatch Plan!");
		}
		
		return dispenseLine;
	}
	
	public void createDPLinesFromPendingDP(ResultSet rs) throws SQLException {
		
		String sql = " TF_DispensePlan_ID = "+ getTF_DispensePlan_ID() + " AND Parent_ID = " + rs.getInt(MDispensePlanLine.COLUMNNAME_Parent_ID);
		
		MDispensePlanLine dispensePlan = new Query(getCtx(), MDispensePlanLine.Table_Name, sql, get_TrxName()).first();
		
		if(dispensePlan == null) {
			MDispensePlanLine dispenseLine = new MDispensePlanLine(getCtx(), 0, get_TrxName());
			
			sql = " C_OrderLine_ID = "+ rs.getInt(MDispensePlanLine.COLUMNNAME_C_OrderLine_ID);
			
			MDispensePlanLine prevdispenseLine = new Query(getCtx(), MDispensePlanLine.Table_Name, sql, get_TrxName()).first();
			
			
			BigDecimal balanceQty = (rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_DispenseQty)).subtract(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_DeliveredDPQty));
			
			dispenseLine.setScheduleDate(ScheduleDate);
			dispenseLine.setPriority(MDispensePlanLine.PRIORITY_Normal);
			dispenseLine.setType(MDispensePlanLine.TYPE_Instant);		
			dispenseLine.setC_OrderLine_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_C_OrderLine_ID));
			dispenseLine.setPaymentRule(rs.getString(MDispensePlanLine.COLUMNNAME_PaymentRule));
			dispenseLine.setC_BPartner_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_C_BPartner_ID));
			dispenseLine.setDateOrdered(rs.getTimestamp(MDispensePlanLine.COLUMNNAME_DateOrdered));
			dispenseLine.setTF_Destination_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_TF_Destination_ID));
			dispenseLine.setLine(10);
			dispenseLine.setM_Product_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_M_Product_ID));
			dispenseLine.setM_Warehouse_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_M_Warehouse_ID));
			dispenseLine.setDescription(rs.getString(MDispensePlanLine.COLUMNNAME_Description));
			dispenseLine.setShipmentTo(rs.getString(MDispensePlanLine.COLUMNNAME_ShipmentTo));
			dispenseLine.setShipmentDestination(rs.getString(MDispensePlanLine.COLUMNNAME_ShipmentDestination));
			dispenseLine.setContactPerson(rs.getString(MDispensePlanLine.COLUMNNAME_ContactPerson));
			dispenseLine.setDeliveryContact(rs.getString(MDispensePlanLine.COLUMNNAME_DeliveryContact));
			dispenseLine.setDispenseQty(BigDecimal.ZERO);
			dispenseLine.setC_UOM_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_C_UOM_ID));
			dispenseLine.setBalanceDPQty(balanceQty);
			dispenseLine.setDeliveredDPQty(BigDecimal.ZERO);
			
			dispenseLine.setQtyOrdered(BigDecimal.ZERO);
			dispenseLine.setQtyDelivered(BigDecimal.ZERO);
			dispenseLine.setBalanceQty(BigDecimal.ZERO);
			
			dispenseLine.setC_Tax_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_C_Tax_ID));
			dispenseLine.setIsTaxIncluded(rs.getBoolean(MDispensePlanLine.COLUMNNAME_IsTaxIncluded));
			dispenseLine.setIsRoyaltyPassInclusive(rs.getBoolean(MDispensePlanLine.COLUMNNAME_IsRoyaltyPassInclusive));
			dispenseLine.setIsRentInclusive(rs.getBoolean(MDispensePlanLine.COLUMNNAME_IsRentInclusive));
			dispenseLine.setUnitPrice(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_UnitPrice));
			dispenseLine.setPriceEntered(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_PriceEntered));
			dispenseLine.setDiscount(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_Discount));
			dispenseLine.setFreightAmt(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_FreightAmt));
			dispenseLine.setLineNetAmt(BigDecimal.ZERO);	
			dispenseLine.setParent_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_TF_DispensePlanLine_ID));
			dispenseLine.setTF_DispensePlan_ID(getTF_DispensePlan_ID());
			
			if(prevdispenseLine != null)
				dispenseLine.setOriginDate(prevdispenseLine.getOriginDate());
			else
				dispenseLine.setOriginDate(getScheduleDate());
			
			dispenseLine.setIsPriceConfidential(rs.getBoolean(MDispensePlanLine.COLUMNNAME_IsPriceConfidential));
			
			if(getDocStatus().equals(MDispensePlan.DOCSTATUS_Drafted)) {
				dispenseLine.setDocStatus(MDispensePlanLine.DOCSTATUS_Drafted);
			}
			else {
				dispenseLine.setDocStatus(MDispensePlanLine.DOCSTATUS_InProgress);				
			}
			dispenseLine.saveEx();
		}
	}
}
