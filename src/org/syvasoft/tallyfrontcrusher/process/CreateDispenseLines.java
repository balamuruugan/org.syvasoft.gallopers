package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.adempiere.exceptions.DBException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.syvasoft.tallyfrontcrusher.model.MCrusherProduction;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlan;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlanLine;

public class CreateDispenseLines extends SvrProcess {
	
	private int p_TF_DispensePlan_ID = 0;
	private boolean recreate = false;
	private boolean carryforward = false;
	MDispensePlan dispensePlan;
	
	@Override
	protected void prepare() {
		dispensePlan = new MDispensePlan(getCtx(), getRecord_ID(), get_TrxName());
		
		for(ProcessInfoParameter para : getParameter())
		{
			String name = para.getParameterName();
			if(name.toLowerCase().equals("recreate"))
				recreate = "Y".equals(para.getParameter());
			else if(name.toLowerCase().equals("carryforward"))
				carryforward = "Y".equals(para.getParameter());
		}
		p_TF_DispensePlan_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		
		if(recreate) {
			String sqlDelete = "DELETE FROM TF_DispensePlanLine WHERE TF_DispensePlan_ID = " + getRecord_ID();
			DB.executeUpdate(sqlDelete, get_TrxName());
		}
		
		String sql = "SELECT " +  
					 "	C_OrderLine_ID,o.paymentrule,o.c_bpartner_id,o.dateordered,ol.tf_destination_id,ol.m_product_id,ol.m_warehouse_id," + 
					 " ol.description,ol.c_uom_id,ol.qtyordered,ol.qtydelivered,ol.c_tax_id,ol.istaxincluded,ol.isrentinclusive,ol.isroyaltypassinclusive," + 
					 " ol.unitprice,ol.priceentered,ol.discount,ol.freightamt,ol.linenetamt,o.ispriceconfidential "	+ 
					 " FROM c_order o INNER JOIN c_orderline ol ON ol.c_order_id = o.c_order_id WHERE " + 
				" trunc(o.dateordered)=trunc(getdate()) " + 
				" AND c_doctypetarget_id = 1000032 AND docstatus IN ('CO')";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();		
			while (rs.next()) {
				dispensePlan.createDPLinesFromOrder(rs);
			}
		} catch (SQLException e) {
			rollback();
			throw new DBException(e, sql);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		if(carryforward) {
			sql = "SELECT " +
					"	C_OrderLine_ID,o.paymentrule,o.c_bpartner_id,o.dateordered,ol.tf_destination_id,ol.m_product_id,ol.m_warehouse_id," + 
					 " ol.description,ol.c_uom_id,ol.qtyordered,ol.qtydelivered,ol.c_tax_id,ol.istaxincluded,ol.isrentinclusive,ol.isroyaltypassinclusive," + 
					 " ol.unitprice,ol.priceentered,ol.discount,ol.freightamt,ol.linenetamt,o.ispriceconfidential "	+
					 " FROM c_order o INNER JOIN c_orderline ol ON ol.c_order_id = o.c_order_id WHERE " + 
					" (trunc(o.dateordered) < trunc(getdate()) AND (qtyordered - qtydelivered) > 0) " + 
					" AND c_doctypetarget_id = 1000032 AND docstatus IN ('CO')";
			
			pstmt = null;
			rs = null;
			try {
				pstmt = DB.prepareStatement(sql, get_TrxName());
				rs = pstmt.executeQuery();		
				while (rs.next()) {
					dispensePlan.createDPLinesFromOrder(rs);
				}
			} catch (SQLException e) {
				rollback();
				throw new DBException(e, sql);
			} finally {
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
			
			sql = "SELECT * FROM tf_dispenseplan d INNER JOIN tf_dispenseplanline dl ON d.tf_dispenseplan_id = dl.tf_dispenseplan_id " + 
					"WHERE trunc(d.scheduledate) < trunc(getdate()) AND (dl.dispenseqty - dl.delivereddpqty) > 0 AND " +
					"(dl.c_orderline_id IS NULL OR (dl.c_orderline_id IS NOT NULL AND dl.shipmentdestination IS NOT NULL))";
			
			pstmt = null;
			rs = null;
			try {
				pstmt = DB.prepareStatement(sql, get_TrxName());
				rs = pstmt.executeQuery();		
				while (rs.next()) {
					dispensePlan.createDPLinesFromPendingDP(rs);
				}
			} catch (SQLException e) {
				rollback();
				throw new DBException(e, sql);
			} finally {
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
		}
		return null;
	}
}
