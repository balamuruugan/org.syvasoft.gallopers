package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.util.Env;

public class MDispensePlanLine extends X_TF_DispensePlanLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8086375269293054622L;
	
	public String ShipmentTo;
	
	public String ShipmentDestination;
	
	public BigDecimal DispatchQty;
	
	public Timestamp ScheduleDate;

	public MDispensePlanLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDispensePlanLine(Properties ctx, int TF_Destination_ID, String trxName) {
		super(ctx, TF_Destination_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(newRecord) {
			String where = "";
			
			if(getShipmentTo() == null && getShipmentDestination() == null) {
				where = " C_OrderLine_ID = " + getC_OrderLine_ID() + " AND TF_DispensePlan_ID = " + getTF_DispensePlan_ID();
			}
			else { 
				where = " ShipmentTo = '" + getShipmentTo() + "' AND ShipmentDestination = '" + getShipmentDestination() + "' AND C_OrderLine_ID = " + getC_OrderLine_ID() + " AND TF_DispensePlan_ID = " + getTF_DispensePlan_ID();
			}
			MDispensePlanLine dispensePlanLine = new Query(getCtx(), MDispensePlanLine.Table_Name, where, get_TrxName()).first();
			
			if(dispensePlanLine != null) {
				throw new AdempiereException("Selected Order Line already exists in Dispatch Plan!");
			}
			
			if(getOriginDate() == null) {
				MDispensePlan dispenseplan = new MDispensePlan(getCtx(), getTF_DispensePlan_ID() , get_TrxName());	
				setOriginDate(dispenseplan.getScheduleDate());
			}
			if(getC_OrderLine_ID() > 0) {
				setType(TYPE_Order);
			}
			else {
				setType(TYPE_Instant);
			}
		}
		else {
			String where = "";
			
			if(getShipmentTo() == null && getShipmentDestination() == null) {
				where = " C_OrderLine_ID = " + getC_OrderLine_ID()  + " AND TF_DispensePlan_ID = " + getTF_DispensePlan_ID() + " AND TF_DispensePlanLine_ID != " + getTF_DispensePlanLine_ID();	
			}
			else {
				where = " ShipmentTo = '" + getShipmentTo() + "' AND ShipmentDestination = '" + getShipmentDestination() + "' AND C_OrderLine_ID = " + getC_OrderLine_ID()  + " AND TF_DispensePlan_ID = " + getTF_DispensePlan_ID() + " AND TF_DispensePlanLine_ID != " + getTF_DispensePlanLine_ID();
			}
			
			MDispensePlanLine dispensePlanLine = new Query(getCtx(), MDispensePlanLine.Table_Name, where, get_TrxName()).first();
			
			if(dispensePlanLine != null) {
				throw new AdempiereException("Selected Order Line already exists in Dispatch Plan!");
			}
		}
		
		if(getTF_DispensePlan_ID() == 0) {
			setDispensePlanHeader();
		}
		MDispensePlan dispensePlan = new MDispensePlan(getCtx(), getTF_DispensePlan_ID(), get_TrxName());
		
		if(!getDocStatus().equals(MDispensePlanLine.DOCSTATUS_Closed) && !getDocStatus().equals(MDispensePlanLine.DOCSTATUS_Expired)) {
			if(dispensePlan.getDocStatus().equals(MDispensePlan.DOCSTATUS_Drafted)) {
				setDocStatus(MDispensePlanLine.DOCSTATUS_Drafted);
			}
			else {
				if(getBalanceDPQty().intValue() <= 0) {
					setDocStatus(MDispensePlanLine.DOCSTATUS_Completed);
				}
				else {
					if(newRecord) {
						setDocStatus(MDispensePlanLine.DOCSTATUS_InProgress);
					}
					else {
						if(is_ValueChanged(COLUMNNAME_DispenseQty)) {
							setDocStatus(MDispensePlanLine.DOCSTATUS_Revised);	
						}
						else {
							setDocStatus(MDispensePlanLine.DOCSTATUS_InProgress);
						}
					}
				}
			}
		}
		
		TF_MBPartner bp = new TF_MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
		
		if(bp != null) {
			if(isPriceConfidential() && !bp.get_ValueAsBoolean(COLUMNNAME_IsPriceConfidential)) {
				bp.set_ValueOfColumn(COLUMNNAME_IsPriceConfidential, isPriceConfidential());
				bp.saveEx();
			}
		}
			
		setAD_Org_ID(dispensePlan.getAD_Org_ID());
		return super.beforeSave(newRecord);
	}
	
	private void setDispensePlanHeader() {
		String whereClause = "AD_Org_ID = ? AND ScheduleDate=?";
		MDispensePlan dp = new Query(getCtx(), MDispensePlan.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getAD_Org_ID(), getScheduleDate())
				.first();
		if(dp == null) {
			dp = new MDispensePlan(getCtx(), 0, get_TrxName());
			dp.setAD_Org_ID(getAD_Org_ID());
			dp.setScheduleDate(getScheduleDate());
			dp.setDocStatus(DOCSTATUS_Drafted);
			dp.saveEx();
		}
		
		setTF_DispensePlan_ID(dp.get_ID());
			
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if(newRecord) {
			
		}
		//int C_BPartnerCustomer_ID = Env.getContextAsInt(getCtx(), "#C_BPartnerCustomer_ID");
		//if(getC_BPartner_ID() == C_BPartnerCustomer_ID)
		//	MNotification.notifyMessage(getCtx(), "CUSTOMER_DISPATCH_PLAN", get_ID() + "", get_TrxName());
		return super.afterSave(newRecord, success);
	}
	
	/*public void createDPLinesFromOrder(ResultSet rs) throws SQLException {
		
		String sql = " TF_DispensePlan_ID = "+ getTF_DispensePlan_ID() + " AND C_OrderLine_ID = " + rs.getInt(MDispensePlanLine.COLUMNNAME_C_OrderLine_ID);
		
		MDispensePlanLine dispensePlan = new Query(getCtx(), MDispensePlanLine.Table_Name, sql, get_TrxName()).first();
		
		if(dispensePlan == null) {
			MDispensePlanLine dispenseLine = new MDispensePlanLine(getCtx(), 0, get_TrxName());
			
			sql = " C_OrderLine_ID = "+ rs.getInt(MDispensePlanLine.COLUMNNAME_C_OrderLine_ID);
			
			MDispensePlanLine prevdispenseLine = new Query(getCtx(), MDispensePlanLine.Table_Name, sql, get_TrxName()).first();
			
			BigDecimal balanceQty = (rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_QtyOrdered)).subtract(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_QtyDelivered));
			dispenseLine.setScheduleDate(ScheduleDate);
			dispenseLine.setPriority(MDispensePlanLine.PRIORITY_Normal);
			dispenseLine.setType(MDispensePlanLine.TYPE_Order);
			dispenseLine.setC_OrderLine_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_C_OrderLine_ID));
			dispenseLine.setPaymentRule(rs.getString(MDispensePlanLine.COLUMNNAME_PaymentRule));
			dispenseLine.setC_BPartner_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_C_BPartner_ID));
			dispenseLine.setDateOrdered(rs.getTimestamp(MDispensePlanLine.COLUMNNAME_DateOrdered));
			dispenseLine.setTF_Destination_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_TF_Destination_ID));
			dispenseLine.setLine(10);
			dispenseLine.setM_Product_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_M_Product_ID));
			dispenseLine.setM_Warehouse_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_M_Warehouse_ID));
			dispenseLine.setDescription(rs.getString(MDispensePlanLine.COLUMNNAME_Description));
			
			dispenseLine.setC_UOM_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_C_UOM_ID));
			
			dispenseLine.setDispenseQty(DispatchQty);
			dispenseLine.setBalanceDPQty(BigDecimal.ZERO);
			dispenseLine.setDeliveredDPQty(DispatchQty);
			
			dispenseLine.setQtyOrdered(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_QtyOrdered));
			dispenseLine.setQtyDelivered(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_QtyDelivered));
			dispenseLine.setBalanceQty(balanceQty);
			
			dispenseLine.setC_Tax_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_C_Tax_ID));
			dispenseLine.setIsTaxIncluded(rs.getBoolean(MDispensePlanLine.COLUMNNAME_IsTaxIncluded));
			dispenseLine.setIsRoyaltyPassInclusive(rs.getBoolean(MDispensePlanLine.COLUMNNAME_IsRoyaltyPassInclusive));
			dispenseLine.setIsRentInclusive(rs.getBoolean(MDispensePlanLine.COLUMNNAME_IsRentInclusive));
			dispenseLine.setUnitPrice(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_UnitPrice));
			dispenseLine.setPriceEntered(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_PriceEntered));
			dispenseLine.setDiscount(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_Discount));
			dispenseLine.setFreightAmt(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_FreightAmt));
			dispenseLine.setLineNetAmt(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_LineNetAmt));	
			
			
			if(ShipmentTo != "") {
				dispenseLine.setShipmentTo(ShipmentTo);
			}

			if(ShipmentDestination != null) {
				dispenseLine.setShipmentDestination(ShipmentDestination);
			}
			
			if(prevdispenseLine != null) {
				dispenseLine.setOriginDate(prevdispenseLine.getOriginDate());
			}
			else {
				ScheduleDate = (ScheduleDate == null) ? new Timestamp(System.currentTimeMillis()) : ScheduleDate;
				dispenseLine.setOriginDate(ScheduleDate);
			}
			dispenseLine.setIsPriceConfidential(rs.getBoolean(MDispensePlanLine.COLUMNNAME_IsPriceConfidential));
			dispenseLine.setTF_DispensePlan_ID(getTF_DispensePlan_ID());
			dispenseLine.saveEx();
		}
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
			
			dispenseLine.setDispenseQty(BigDecimal.ZERO);
			dispenseLine.setC_UOM_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_C_UOM_ID));
			dispenseLine.setBalanceDPQty(BigDecimal.ZERO);
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
			dispenseLine.setLineNetAmt(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_LineNetAmt));	
			dispenseLine.setParent_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_TF_DispensePlanLine_ID));
		
			if(prevdispenseLine != null) {
				dispenseLine.setOriginDate(prevdispenseLine.getOriginDate());
			}
			else {
				ScheduleDate = (ScheduleDate == null) ? new Timestamp(System.currentTimeMillis()) : ScheduleDate;
				dispenseLine.setOriginDate(ScheduleDate);
			}
			dispenseLine.setIsPriceConfidential(rs.getBoolean(MDispensePlanLine.COLUMNNAME_IsPriceConfidential));
			dispenseLine.saveEx();
		}
	}*/
}
