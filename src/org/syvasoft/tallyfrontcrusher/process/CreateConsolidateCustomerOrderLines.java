package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrderLine;
import org.compiere.model.MSysConfig;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.MRentedVehicle;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;
import org.syvasoft.tallyfrontcrusher.model.TF_MInOut;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class CreateConsolidateCustomerOrderLines extends SvrProcess {

	TF_MOrder ord = null;
	
	Timestamp dateFrom = null;
	Timestamp dateTo = null;
	boolean reCreate = false;	
	
	@Override
	protected void prepare() {
		
		ord = new TF_MOrder(getCtx(), getRecord_ID(), get_TrxName());
		
		for(ProcessInfoParameter para : getParameter())
		{
			String name = para.getParameterName();			
			if("DateFrom".equals(name))
				dateFrom = para.getParameterAsTimestamp();
			else if("DateTo".equals(name) ) 
				dateTo = para.getParameterAsTimestamp();
			else if (name.equals("reCreate"))
				reCreate = para.getParameterAsBoolean();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception {
		String sqlUpdate = null;
		if(reCreate) {
			sqlUpdate = "UPDATE M_InOut SET C_Order_ID = NULL WHERE C_Order_ID = " + ord.getC_Order_ID();
			DB.executeUpdate(sqlUpdate, get_TrxName());
			for(MOrderLine ordLine : ord.getLines(true, null)) {
				ordLine.delete(true, get_TrxName());
			}
		}
		
		if(ord.getLines(true, null).length > 0)
			return "The Consolidated Order Lines are already created!";
		
		sqlUpdate = "UPDATE M_Inout SET C_Order_ID = ? WHERE AD_Org_ID = ? AND C_DocType_ID = ? AND C_BPartner_ID = ? "
				+ " AND Docstatus IN ('CO','CL') AND IsSOTrx='Y' "  
				+ "	AND MovementDate >= ? AND MovementDate <= ? AND C_Order_ID IS NULL "
				+ " AND EXISTS(SELECT * FROM TF_WeighmentEntry we WHERE we.TF_WeighmentEntry_ID = M_InOut.TF_WeighmentEntry_ID AND we.Status = 'CO')";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(ord.getC_Order_ID());
		params.add(ord.getAD_Org_ID());
		params.add(ord.getC_DocTypeTarget().getC_DocTypeShipment_ID());
		params.add(ord.getC_BPartner_ID());		
		params.add(dateFrom);
		params.add(dateTo);
		DB.executeUpdateEx(sqlUpdate, params.toArray(), get_TrxName());
						
		int RoyaltyPassProduct_ID = MSysConfig.getIntValue("ROYALTY_PASS_PRODUCT_ID", 1000329, getAD_Client_ID(), ord.getAD_Org_ID());
		
		 //Excluding Royalty Pass Shipment Line because Royalty Pass price is already included in the Material Price
		String sql = "SELECT MAX(p.Name) ProductName, ioLine.M_Product_ID, ioLIne.C_UOM_ID, SUM(MovementQty) qtyShipped, ioLine.Price, ioLine.Description  FROM "
				+ " M_InOut io INNER JOIN M_InOutLine ioLine ON io.M_InOut_ID = ioLine.M_InOut_ID "
				+ " INNER JOIN M_Product p ON ioLine.M_Product_ID = p.M_Product_ID "
				+ " WHERE io.C_Order_ID = ? AND ioLine.M_Product_ID != ? AND " 
				+ "	io.DocStatus = 'CO' AND io.IsSOTrx='Y' "
				+ " GROUP BY ioLine.M_Product_ID, ioLine.C_UOM_ID, ioLine.Price, ioLine.Description "
				+ " ORDER BY C_UOM_ID, ProductName";
		
		PreparedStatement pstmt =  null;
		ResultSet rs = null;
		try	{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			params = new ArrayList<Object>();
			params.add(ord.getC_Order_ID());
			params.add(RoyaltyPassProduct_ID);
			
			DB.setParameters(pstmt,params.toArray());
			rs = pstmt.executeQuery();
			
			TF_MBPartner bp = new TF_MBPartner(getCtx(), ord.getC_BPartner_ID(), get_TrxName());
			while (rs.next()) {
				int M_Product_ID = rs.getInt("M_Product_ID");
				int C_UOM_ID = rs.getInt("C_UOM_ID");
				BigDecimal qtyShipped = rs.getBigDecimal("qtyShipped");
				String description = rs.getString("Description");
				BigDecimal price = rs.getBigDecimal("Price");
				boolean isVehicle = MRentedVehicle.get(getCtx(), M_Product_ID, get_TrxName()) != null;
				
				TF_MOrderLine ordLine = new TF_MOrderLine(ord);
				ordLine.setM_Product_ID(M_Product_ID, true);
				ordLine.setC_UOM_ID(C_UOM_ID);								
				ordLine.setQty(qtyShipped);
				if(!isVehicle) {
					// create order line
					//the Credit Customer material price always include Royalty Pass amount
					price = MPriceListUOM.getPrice(getCtx(), M_Product_ID, C_UOM_ID, ord.getC_BPartner_ID(), ord.getTF_Destination_ID(), ord.isSOTrx(), ord.getDateAcct());
				}
				
				if(price == null) {
					price = BigDecimal.ZERO;
				}
				
				ordLine.setPriceActual(price);
				ordLine.setPriceList(price);
				ordLine.setPriceLimit(price);
				ordLine.setPriceEntered(price);
				TF_MProduct prod = new TF_MProduct(getCtx(), M_Product_ID, get_TrxName());
				ordLine.setC_Tax_ID(prod.getTax_ID(true, bp.isInterState()));
				ordLine.setDescription(description);
				ordLine.saveEx();
			}	
			
		}
		catch (SQLException e) {
			rollback();
			//log.log(Level.SEVERE, "", e);
			throw new DBException(e, sql.toString());
		}
		finally	{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		
		//Create Consolidated Vehicle Rent 
		
		return null;
	}
	
}
