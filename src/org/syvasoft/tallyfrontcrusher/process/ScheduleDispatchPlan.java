package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.List;
import org.compiere.model.MOrder;
import org.compiere.model.MUserRoles;
import org.compiere.model.Query;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MCrusherProduction;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlan;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlanLine;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;

public class ScheduleDispatchPlan extends SvrProcess {
	
	private int p_TF_DispensePlan_ID = 0;
	private Timestamp ScheduleDate;
	private String ShipmentTo;
	private int ShipmentDestination;
	private String ShipmentAddress;
	private String CustomerGSTIN;
	private BigDecimal ShipmentRate;
	private String DeliveryContact;
	private BigDecimal DispenseQty;
	private boolean OverDeliveryQty = false;
	private boolean CarryForwardPrevDayDP = false;
	private boolean CustomerTransporter = false;
	private boolean ArrangeTransport = false;
	private String Priority;
	private int TF_VehicleType_ID;
	private int FreightUOM_ID;
	private int c_orderlineID;
	MDispensePlan dispensePlan;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();		
		for (int i = 0; i < para.length; i++){
			String name = para[i].getParameterName();
			
			if(name.toLowerCase().equals("scheduledate"))
				ScheduleDate = para[i].getParameterAsTimestamp();
			else if(name.toLowerCase().equals("shipmentto"))
				ShipmentTo = para[i].getParameterAsString();
			else if(name.toLowerCase().equals("shipmentdestination"))
				ShipmentDestination = para[i].getParameterAsInt();
			else if(name.toLowerCase().equals("dispenseqty"))
				DispenseQty = para[i].getParameterAsBigDecimal();
			else if(name.toLowerCase().equals("deliverycontact"))
				DeliveryContact = para[i].getParameterAsString();
			else if(name.toLowerCase().equals("overunitdelivery"))
				OverDeliveryQty = para[i].getParameterAsBoolean();
			else if(name.toLowerCase().equals("allowcarryforward"))
				CarryForwardPrevDayDP = para[i].getParameterAsBoolean();
			else if(name.toLowerCase().equals("priority"))
				Priority = para[i].getParameterAsString();
			else if(name.toLowerCase().equals("tf_vehicletype_id"))
				TF_VehicleType_ID = para[i].getParameterAsInt();
			else if(name.toLowerCase().equals("freightuom_id") && para[i].getParameterAsString() != null)
				FreightUOM_ID = Integer.parseInt(para[i].getParameterAsString());
			else if(name.toLowerCase().equals("shipmentaddress"))
				ShipmentAddress = para[i].getParameterAsString();
			else if(name.toLowerCase().equals("customergstin"))
				CustomerGSTIN = para[i].getParameterAsString();
			else if(name.toLowerCase().equals("shipmentrate"))
				ShipmentRate = para[i].getParameterAsBigDecimal();
			else if(name.toLowerCase().equals("customertransporter"))
				CustomerTransporter = para[i].getParameterAsBoolean();
			else if(name.toLowerCase().equals("arrangetransport"))
				ArrangeTransport = para[i].getParameterAsBoolean();
		}
		c_orderlineID =  getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		String sql = "";
		int i = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		if(c_orderlineID == 0) {
			sql ="SELECT COUNT(*) FROM c_order o INNER JOIN c_orderline ol ON ol.c_order_id = o.c_order_id WHERE " + 
				" (EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE T_Selection.AD_PInstance_ID="+ getAD_PInstance_ID() + " AND T_Selection.T_Selection_ID = ol.c_orderline_id))";
		}
		else {
			sql ="SELECT COUNT(*) FROM c_order o INNER JOIN c_orderline ol ON ol.c_order_id = o.c_order_id WHERE ol.c_orderline_id = " + c_orderlineID;
		}
		pstmt = DB.prepareStatement(sql, get_TrxName());
		rs = pstmt.executeQuery();
		rs.next();
	    int rowCount = rs.getInt(1);
	    String msg = "";
	    
	    if((ShipmentTo != null || ShipmentDestination > 0) && rowCount > 1) {
	    	msg = "Error: Please choose one Order for Shipment to and Shipment Destination to schedule Dispatch Plan!";
	    	addLog(msg);
	    }
	    else {
	    	if(c_orderlineID == 0) {
				sql = "SELECT " + 
						" o.ad_org_id, o.c_order_id, c_orderline_id,o.paymentrule,o.c_bpartner_id,o.dateordered,ol.tf_destination_id,ol.m_product_id,ol.m_warehouse_id,o.DocStatus," + 
						"	ol.description,ol.c_uom_id,ol.qtyordered,ol.qtydelivered,ol.c_tax_id,ol.istaxincluded,ol.isrentinclusive,ol.isroyaltypassinclusive,ol.customertransporter," + 
						"	ol.unitprice,ol.priceentered,ol.discount,ol.freightamt,ol.linenetamt,o.ispriceconfidential,ol.ContactPerson,ol.DeliveryContact, ol.freightuom_id " +
						" FROM c_order o INNER JOIN c_orderline ol ON ol.c_order_id = o.c_order_id WHERE " + 
						" (EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE T_Selection.AD_PInstance_ID="+ getAD_PInstance_ID() + " AND T_Selection.T_Selection_ID = ol.c_orderline_id))";
	    	}
	    	else {
	    		sql = "SELECT " + 
						" o.ad_org_id, o.c_order_id, c_orderline_id,o.paymentrule,o.c_bpartner_id,o.dateordered,ol.tf_destination_id,ol.m_product_id,ol.m_warehouse_id,o.DocStatus," + 
						"	ol.description,ol.c_uom_id,ol.qtyordered,ol.qtydelivered,ol.c_tax_id,ol.istaxincluded,ol.isrentinclusive,ol.isroyaltypassinclusive,ol.customertransporter," + 
						"	ol.unitprice,ol.priceentered,ol.discount,ol.freightamt,ol.linenetamt,o.ispriceconfidential,ol.ContactPerson,ol.DeliveryContact, ol.freightuom_id " +
						" FROM c_order o INNER JOIN c_orderline ol ON ol.c_order_id = o.c_order_id WHERE ol.c_orderline_id = " + c_orderlineID;
	    	}
			
			try {
				pstmt = DB.prepareStatement(sql, get_TrxName());
				rs = pstmt.executeQuery();		
				
				int RoleId = Env.getContextAsInt(getCtx(), "#AD_Role_ID");
				
				while (rs.next()) {			
					
					sql = " ad_org_id = '"+ rs.getInt(TF_MOrder.COLUMNNAME_AD_Org_ID)  +"' AND trunc(scheduledate) = '" + ScheduleDate + "'";
					
					dispensePlan = new Query(getCtx(), MDispensePlan.Table_Name, sql, get_TrxName()).first();
					
					
					if(dispensePlan == null) {
						dispensePlan = new MDispensePlan(getCtx(), 0, get_TrxName());
						dispensePlan.setAD_Org_ID(rs.getInt(TF_MOrder.COLUMNNAME_AD_Org_ID));
						dispensePlan.setScheduleDate(ScheduleDate);
						dispensePlan.setDocStatus(MDispensePlan.DOCSTATUS_Drafted);
						dispensePlan.saveEx();
					}
					
					//dispensePlanline = new MDispensePlanLine(getCtx(), 0, null);
					
					dispensePlan.ScheduleDate = ScheduleDate;		
					dispensePlan.ShipmentTo = ShipmentTo;
					dispensePlan.ShipmentDestination = ShipmentDestination;
					dispensePlan.DispatchQty = DispenseQty;
					dispensePlan.DeliveryContact = DeliveryContact;
					dispensePlan.OverDeliveryQty = OverDeliveryQty;
					dispensePlan.CarryForwardPrevDayDP = CarryForwardPrevDayDP;
					dispensePlan.Priority = Priority;
					dispensePlan.TF_VehicleType_ID = TF_VehicleType_ID;
					dispensePlan.FreightUOM_ID = FreightUOM_ID;
					dispensePlan.ShipmentAddress = ShipmentAddress;
					dispensePlan.ShipmentRate = ShipmentRate;
					dispensePlan.CustomerGSTIN = CustomerGSTIN;
					dispensePlan.CustomerTransporter = CustomerTransporter;
					dispensePlan.ArrangeTransport = ArrangeTransport;
					
					
					if(rs.getString(MDispensePlan.COLUMNNAME_DocStatus).equals(MDispensePlan.DOCSTATUS_Completed)) {
						MDispensePlanLine dispensePlanLine = dispensePlan.createDPLinesFromOrder(rs);
						String userMsg = "";
						if(dispensePlanLine.getDocStatus().equals(MDispensePlanLine.DOCSTATUS_Drafted) && RoleId != 1000036)
							userMsg = " Please complete the plan!";
						addLog(dispensePlan.get_Table_ID(), dispensePlan.getCreated(), null, " Dispense Plan : " + dispensePlan.getDocumentNo() + " is created!" + userMsg, dispensePlanLine.get_Table_ID(), dispensePlanLine.get_ID());
						i=i+1;
					}
					else if(rs.getString(MDispensePlan.COLUMNNAME_DocStatus).equals(MDispensePlan.DOCSTATUS_Closed)) {
						msg = "Error: DP Line can't be created for closed order!";
						addLog(msg);
					}
					else {
						
						msg = "Error: Order Status should be completed before creating DP Line!";
				    	addLog(dispensePlan.get_Table_ID(), dispensePlan.getScheduleDate(), null, msg, TF_MOrder.Table_ID, rs.getInt("c_order_id"));
					}
					
				}
			} catch (SQLException e) {
				rollback();
				throw new DBException(e, sql);
			} 
			catch (Exception ex) {
				addLog(ex.getMessage());
			}
			finally {
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
	    }
	    return i + " Orders are scheduled successfully!";
	}
}
