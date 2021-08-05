package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.model.MInvoiceLine;


import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.syvasoft.tallyfrontcrusher.model.MDestination;
import org.syvasoft.tallyfrontcrusher.model.MJobworkProductPrice;
import org.syvasoft.tallyfrontcrusher.model.MLumpSumRentConfig;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.MRentedVehicle;
import org.syvasoft.tallyfrontcrusher.model.MSubcontractType;
import org.syvasoft.tallyfrontcrusher.model.TF_MInvoice;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;
import org.syvasoft.tallyfrontcrusher.model.TF_MProject;

public class CreateInvoiceForMaterialReceipt extends SvrProcess {

	private int recordID = 0;	
	private int m_AD_Org_ID = 0;	
	private Timestamp DateFrom = null;
	private Timestamp DateTo = null;
	boolean reCreate = false;	
	
	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		recordID = getRecord_ID();
		ProcessInfoParameter[] para = getParameter();		
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();			
			if (name.equals("AD_Org_ID"))				
				m_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("DateFrom")) {
				DateFrom = para[i].getParameterAsTimestamp();
			}
			else if (name.equals("DateTo"))
				DateTo = para[i].getParameterAsTimestamp();
			else if (name.equals("reCreate"))
				reCreate = para[i].getParameterAsBoolean();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

	@Override
	protected String doIt() throws Exception {
		TF_MInvoice invoice=new TF_MInvoice(getCtx(), recordID, get_TrxName());
		String sqlUpdate = null;
		
		if(reCreate) {
			sqlUpdate = "UPDATE M_InOut SET C_Invoice_ID = NULL WHERE C_Invoice_ID = " + invoice.getC_Invoice_ID();
			DB.executeUpdate(sqlUpdate, get_TrxName());
			for(MInvoiceLine invLine : invoice.getLines(true)) {
				invLine.delete(true, get_TrxName());
			}
		}
		
		
		sqlUpdate = "UPDATE M_InOut SET C_Invoice_ID = ? WHERE AD_Org_ID = ? AND C_BPartner_ID = ? AND DocStatus IN ('CO','CL') "
				+ "	AND MovementDate >= ? AND MovementDate <= ? AND IsSOTrx = 'N' AND C_Invoice_ID IS NULL";

		ArrayList<Object> params = new ArrayList<Object>();
		params.add(invoice.getC_Invoice_ID());
		params.add(m_AD_Org_ID);
		params.add(invoice.getC_BPartner_ID());
		params.add(DateFrom);
		params.add(DateTo);
		
		DB.executeUpdateEx(sqlUpdate, params.toArray(), get_TrxName());
		
		int i = createInvoiceLinesForMaterialReceipt(invoice);
		i =  i + createInvoiceLinesForTransportVehicleServiceReceipts(invoice);
		i = i + createCrusherSubcontractInvoiceLines(invoice);
		i = i + CreateQuarrySubcontractInvoiceLines(invoice);
		
		return i + " invoice lines are created successfully!";
	}

	public int createInvoiceLinesForMaterialReceipt(TF_MInvoice invoice) {
			// Consolidated invoice line will be created only for the new Products which are not in Invoice Lines already.
			String sql = "SELECT inl.M_Product_ID, inl.C_UOM_ID, sum (inl.QtyEntered) QtyEntered " + 
					" FROM	M_InOut io INNER JOIN M_InOutLine inl ON inl.M_InOut_ID=io.M_InOut_ID " +
					" WHERE io.C_Invoice_ID = ? AND inl.M_Product_ID NOT IN (SELECT invLine.M_Product_ID FROM C_InvoiceLine invLine WHERE invLine.C_Invoice_ID = io.C_Invoice_ID ) " +
					" AND inl.M_Product_ID NOT IN (SELECT rv.M_Product_ID FROM TF_RentedVehicle rv WHERE rv.AD_Org_ID = inl.AD_Org_ID) "  + 
					" GROUP BY inl.M_Product_ID, inl.C_UOM_ID "; 
					
			PreparedStatement pstmt =  null;
			ResultSet rs = null;
			int i = 0;
			try	{
				
				ArrayList<Object> params = new ArrayList<Object>();
				pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
				params = new ArrayList<Object>();
				params.add(invoice.getC_Invoice_ID());
				DB.setParameters(pstmt,params.toArray());
				rs = pstmt.executeQuery();
				
				
				while (rs.next()) {
				
					//Create Invoice Line
					MInvoiceLine invLine = new MInvoiceLine(invoice);				
					invLine.setM_Product_ID(rs.getInt("M_Product_ID"), true);
					invLine.setQty(rs.getBigDecimal("QtyEntered"));
					invLine.setC_UOM_ID(rs.getInt("C_UOM_ID"));			
					
					
					MPriceListUOM pprice = MPriceListUOM.getPriceListUOM(getCtx(), rs.getInt("M_Product_ID"),
							rs.getInt("C_UOM_ID"), invoice.getC_BPartner_ID(),0, false, invoice.getDateInvoiced());
					
					TF_MProduct prod = new TF_MProduct(getCtx(), invLine.getM_Product_ID(), get_TrxName());
					BigDecimal price = BigDecimal.ZERO;
					if( pprice == null && !prod.isVehicle()) {
						throw new AdempiereException("Please configure the Purchase Price for " + prod.getName() + "!");
					}					
					else if(pprice != null) {
						price = pprice.getPrice();		
					}
					
						
					invLine.setPriceActual(price);
					invLine.setPriceList(price);
					invLine.setPriceLimit(price);
					invLine.setPriceEntered(price);				
				
					invLine.setC_Tax_ID(prod.getTax_ID(true));								
					invLine.saveEx();				
					
					i++;
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
		return i;
	}
	
	public int createInvoiceLinesForTransportVehicleServiceReceipts(TF_MInvoice invoice) {
		// Consolidated invoice line will be created only for the new Products which are not in Invoice Lines already.
		String sql = "SELECT inl.M_Product_ID, inl.C_UOM_ID, sum (inl.QtyEntered) QtyEntered, inl.Price, inl.Description, w.TF_Destination_ID " + 
				" FROM	M_InOut io INNER JOIN M_InOutLine inl ON inl.M_InOut_ID=io.M_InOut_ID " + 
				" LEFT OUTER JOIN TF_WeighmentEntry w ON io.TF_WeighmentEntry_ID = w.TF_WeighmentEntry_ID " +
				" WHERE io.C_Invoice_ID = ? AND inl.M_Product_ID NOT IN (SELECT invLine.M_Product_ID FROM C_InvoiceLine invLine WHERE invLine.C_Invoice_ID = io.C_Invoice_ID ) "  +
				" AND inl.M_Product_ID IN (SELECT rv.M_Product_ID FROM TF_RentedVehicle rv WHERE rv.AD_Org_ID = inl.AD_Org_ID) "  +
				" GROUP BY inl.M_Product_ID, inl.C_UOM_ID, inl.Price, inl.Description, w.TF_Destination_ID";
				
		PreparedStatement pstmt =  null;
		ResultSet rs = null;
		int i = 0;
		try	{
			
			ArrayList<Object> params = new ArrayList<Object>();
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			params = new ArrayList<Object>();
			params.add(invoice.getC_Invoice_ID());
			DB.setParameters(pstmt,params.toArray());
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
			
				//Create Invoice Line
				MInvoiceLine invLine = new MInvoiceLine(invoice);				
				invLine.setM_Product_ID(rs.getInt("M_Product_ID"), true);
				invLine.setQty(rs.getBigDecimal("QtyEntered"));
				invLine.setC_UOM_ID(rs.getInt("C_UOM_ID"));				
				BigDecimal price = rs.getBigDecimal("Price");				
				String destinationInfo = rs.getString("Description");
				int TF_Destination_ID = rs.getInt("TF_Destination_ID");
				
				MPriceListUOM pprice = MPriceListUOM.getPriceListUOM(getCtx(), rs.getInt("M_Product_ID"),
						rs.getInt("C_UOM_ID"), invoice.getC_BPartner_ID(),0, false, invoice.getDateInvoiced());
				
				TF_MProduct prod = new TF_MProduct(getCtx(), invLine.getM_Product_ID(), get_TrxName());
				
				if( pprice == null && !prod.isVehicle()) {
					throw new AdempiereException("Please configure the Purchase Price for " + prod.getName() + "!");
				}
				else if(prod.isVehicle()) {
					invLine.setDescription(destinationInfo);
					if(price == null || price.doubleValue() == 0 ) {
						//get Rent Amount from Vehicle Rent Configuration
						MDestination dest = new MDestination(getCtx(), TF_Destination_ID, get_TrxName());
						MRentedVehicle rv = prod.getTF_RentedVehicle();
						int Vendor_ID = invoice.getC_BPartner_ID();					
						BigDecimal RateMT = MLumpSumRentConfig.getRateMT(getCtx(), invoice.getAD_Org_ID(), Vendor_ID, 0, 0, 
								TF_Destination_ID, rv.getTF_VehicleType_ID(), dest.getDistance(), get_TrxName());
						//BigDecimal RateKM = MLumpSumRentConfig.getRateKm(getCtx(), ord.getAD_Org_ID(), Vendor_ID, ord.getC_BPartner_ID(), ord.getItem1_ID(),
						//		ord.getTF_Destination_ID(), ord.getItem1_VehicleType_ID(), dest.getDistance(), get_TrxName());
						//BigDecimal RateMTKM = MLumpSumRentConfig.getRateMTKm(getCtx(), ord.getAD_Org_ID(), Vendor_ID, ord.getC_BPartner_ID(),
						//		ord.getItem1_ID(), ord.getTF_Destination_ID(), ord.getItem1_VehicleType_ID(), dest.getDistance(), get_TrxName());
						BigDecimal RentAmt = BigDecimal.ZERO;
						
						if(RateMT.doubleValue() > 0) {
							price = RateMT;
						}
						//else if(RateKM.doubleValue() > 0) {
						//	ord.setRate(RateKM);
						//	RentAmt = RateKM.multiply(dest.getDistance());
						//}
						//else if(RateMTKM.doubleValue() > 0) {
						//	ord.setRate(RateMTKM);
						//	RentAmt = RateMTKM.multiply(ord.getDistance()).multiply(qty);
						//}
						else {								
							RentAmt=MLumpSumRentConfig.getLumpSumRent(getCtx(), invoice.getAD_Org_ID() ,Vendor_ID, 0, 
									0, TF_Destination_ID, rv.getTF_VehicleType_ID(), dest.getDistance(), get_TrxName());
							price = RentAmt;
						}						
					}
				}
				else if(pprice != null) {
					price = pprice.getPrice();		
				}
				
					
				invLine.setPriceActual(price);
				invLine.setPriceList(price);
				invLine.setPriceLimit(price);
				invLine.setPriceEntered(price);				
			
				invLine.setC_Tax_ID(prod.getTax_ID(true));								
				invLine.saveEx();				
				
				i++;
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
		return i;
	}

	public int createCrusherSubcontractInvoiceLines(TF_MInvoice invoice) {
		
		
		TF_MProject proj = new TF_MProject(getCtx(), invoice.getC_Project_ID(), get_TrxName());
		int i = 0;
		if(proj.getC_Project_ID() == 0 || !proj.getSubcontractType().equals(MSubcontractType.SUBCONTRACTTYPE_CrusherProduction))
			return i;
		
		// if the invoice line item already created then it will not allow to create the
		// invoice again.
		String sqlCnt = "SELECT COUNT(*) FROM C_InvoiceLine WHERE C_Invoice_ID = ? AND M_Product_ID = ? ";
		int count =  DB.getSQLValue(get_TrxName(), sqlCnt, invoice.getC_Invoice_ID(), proj.getJobWork_Product_ID());
		if(count > 0)
			return i;
	

		String sql = "SELECT round(sum(Qty_Receipt),2) as QtyReceived FROM TF_RMSubcon_Movement"
				+ " WHERE AD_Org_ID = ? AND  MovementDate >= ? AND MovementDate <= ? " + " AND C_Invoice_ID IS NULL AND Qty_Receipt IS NOT NULL";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			ArrayList<Object> params = new ArrayList<Object>();
			params.add(invoice.getAD_Org_ID());
			params.add(DateFrom);
			params.add(DateTo);
			DB.setParameters(pstmt, params.toArray());
			rs = pstmt.executeQuery();		
			while (rs.next()) {
				if(rs.getBigDecimal("QtyReceived") == null)
					continue;
				// to create the invoice line item by clicking button Create Crusher Production Invoice line button				
				BigDecimal minContractAmt = proj.getMinContract_Amt();
				BigDecimal purchasePrice = MJobworkProductPrice.getPrice(getCtx(), invoice.getC_Project_ID(),
						proj.getJobWork_Product_ID(), invoice.getDateAcct());
				MInvoiceLine invLine = new MInvoiceLine(invoice);
				BigDecimal amount = purchasePrice.multiply(rs.getBigDecimal("QtyReceived"));		
				invLine.setM_Product_ID(proj.getJobWork_Product_ID(), true);
				if (amount.compareTo(minContractAmt) < 0) {
					purchasePrice = minContractAmt;				
					invLine.setC_UOM_ID(106); 				
					invLine.setQty(1);
					
				} else {			
					invLine.setQty(rs.getBigDecimal("QtyReceived"));
					invLine.setC_UOM_ID(proj.getC_UOM_ID());
				}		
				
				invLine.setDescription(proj.getDescription());
				invLine.setPriceActual(purchasePrice);
				invLine.setPriceList(purchasePrice);
				invLine.setPriceLimit(purchasePrice);
				invLine.setPriceEntered(purchasePrice);
				invLine.setC_Tax_ID(proj.getC_Tax_ID());
				
				invLine.saveEx();
				i ++;
			}
		} catch (SQLException e) {
			rollback();
			throw new DBException(e, sql);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		return i;
	}
		
	
	public int CreateQuarrySubcontractInvoiceLines(TF_MInvoice invoice) {
		int i = 0;		
		TF_MProject proj = new TF_MProject(getCtx(), invoice.getC_Project_ID(), get_TrxName());		
		if(proj.getC_Project_ID() == 0 || !proj.getSubcontractType().equals(MSubcontractType.SUBCONTRACTTYPE_QuarryProducton))
			return i;
		
		// if the invoice line item already created then it will not allow to create the
		// invoice again.
		String sqlCnt = "SELECT COUNT(*) FROM C_InvoiceLine WHERE C_Invoice_ID = ? AND M_Product_ID IN (?,?) ";
		int count =  DB.getSQLValue(get_TrxName(), sqlCnt, invoice.getC_Invoice_ID(), proj.getJobWork_Product_ID(), proj.getJobWorkWOTrans_Product_ID());
		if(count > 0)
			return 1;
		
		String sql = "SELECT Max(C_Project_ID) as C_Project_ID,round(sum(QtyReceived),2) as QtyReceived,Max(C_UOM_ID) as C_UOM_ID, " + 
				"JobWork_Product_ID FROM TF_Boulder_Receipt WHERE AD_Org_ID = ? AND Subcontractor_ID  = ? AND C_Project_ID = ? " + 
				" AND DateReceipt  >=? AND DateReceipt  <=?  " + 
				" AND DocStatus='CO'  "
				+ "AND Subcon_Invoice_ID IS NULL "
				+ "GROUP BY JobWork_Product_ID";
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			ArrayList<Object> params = new ArrayList<Object>();
			params.add(invoice.getAD_Org_ID());
			params.add(invoice.getC_BPartner_ID()); 
			params.add(proj.getC_Project_ID());
			params.add(DateFrom);
			params.add(DateTo);			
		
			DB.setParameters(pstmt,params.toArray());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				//to create the invoice line item by clicking button Create Quarry Subcontract Invoice Line
					
				BigDecimal purchasePrice = MJobworkProductPrice.getPrice(getCtx(), rs.getInt("C_Project_ID"), rs.getInt("JobWork_Product_ID"), invoice.getDateAcct());
				MInvoiceLine invLine = new MInvoiceLine(invoice);
				invLine.setM_Product_ID(rs.getInt("JobWork_Product_ID") , true);
				
				if(purchasePrice == null)
					throw new AdempiereException("Please configure the Purchase Price for " + invLine.getM_Product().getName() + "!");
								
				invLine.setQty(rs.getBigDecimal("QtyReceived"));		
				invLine.setDescription(proj.getDescription());		
				invLine.setPriceActual(purchasePrice);
				invLine.setPriceList(purchasePrice);
				invLine.setPriceLimit(purchasePrice);
				invLine.setPriceEntered(purchasePrice);		
				invLine.setC_Tax_ID(proj.getC_Tax_ID());
				invLine.saveEx();
				i++;
			}
		} catch (SQLException e) {
			rollback();			
			throw new DBException(e, sql);
		} finally {
			DB.close(rs, pstmt);			
			rs = null;
			pstmt = null;			
		}
		
		return i;
	}
	
}
