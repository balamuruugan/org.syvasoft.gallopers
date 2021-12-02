package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MPriceList;
import org.compiere.model.MTax;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Trx;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;

public class CreateConsolidatedServiceInvoice extends SvrProcess {

	int C_Tax_ID = 0;
	int C_DocType_ID = 0;
	boolean IsTaxIncluded = false;
	int M_InoutLine_ID = 0;
	Savepoint sp = null;
	Timestamp dateInvoiced = null;
	boolean IsConsolidateInvoice = false;
	boolean OverrideTaxConfig = false;
	
	@Override
	protected void prepare() {
ProcessInfoParameter[] para = getParameter();	
		
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();
			if(name.equals("C_Tax_ID"))
				C_Tax_ID = para[i].getParameterAsInt();
			else if (name.equals("IsTaxIncluded"))
				IsTaxIncluded = para[i].getParameterAsBoolean();
			else if (name.equals("DateInvoiced"))
				dateInvoiced  = para[i].getParameterAsTimestamp();
			else if(name.equals("IsConsolidateInvoice"))
				IsConsolidateInvoice = para[i].getParameterAsBoolean();
			else if(name.equals("OverrideTaxConfig"))
				OverrideTaxConfig = para[i].getParameterAsBoolean();
			else if(name.equals("C_DocType_ID"))
				C_DocType_ID = para[i].getParameterAsInt();
		}
		
		M_InoutLine_ID = getRecord_ID();

	}

	@Override
	protected String doIt() throws Exception {
		String whereClause = " C_BPartner_ID IN (SELECT C_BPartner_ID FROM M_InOut INNER JOIN M_InOutLIne ON M_InOut.M_InOut_ID =  M_InOutLIne.M_InOut_ID "
				+ " WHERE  (EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE " +
				" T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID = M_InOutLIne.M_InOutLIne_ID) OR M_InOutLIne.M_InOutLine_ID = ?)"
				+ " AND M_InOut.DocStatus = 'CO' AND M_InOutLine.DocStatus = 'CO' "
				+ ") "
				+ "  ";
		List<TF_MBPartner> bpList = new Query(getCtx(), TF_MBPartner.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getAD_PInstance_ID(), M_InoutLine_ID)
				.list();
		
		//Validation
		for(TF_MBPartner bp : bpList) {
			createConsolidatedInvoice(bp);
		}
		
		 
		return bpList.size() + " Invoices are created!";
		
	}
	
	private void createConsolidatedInvoice(TF_MBPartner bp) {
		// Consolidated invoice line will be created only for the new Products which are not in Invoice Lines already.
		String sql = "SELECT " +
				" inl.M_Product_ID, inl.C_UOM_ID, sum (inl.QtyEntered) QtyEntered, inl.Price, COALESCE(inl.C_Tax_ID,1000000) C_Tax_ID " +
				 " FROM " +
				 	 " M_InOut io INNER JOIN M_InOutLine inl ON inl.M_InOut_ID=io.M_InOut_ID "  + 
				" WHERE  " +
					" io.DocStatus = 'CO' AND inl.DocStatus = 'CO' AND (EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE " +  
					" T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID = inl.M_InOutLIne_ID) AND  io.C_BPartner_ID = ?) AND io.C_Order_ID IS NULL"  +
				 " GROUP BY			inl.M_Product_ID, inl.C_UOM_ID, inl.Price, inl.C_Tax_ID";
		
		PreparedStatement pstmt =  null;
		ResultSet rs = null;		
		Trx trx = Trx.get(get_TrxName(), false);
		MTax tax = new MTax(getCtx(), C_Tax_ID, get_TrxName());
		BigDecimal taxRate = tax.getRate().divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN).add(BigDecimal.ONE);
		
		try	{
			
			ArrayList<Object> params = new ArrayList<Object>();
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			params = new ArrayList<Object>();
			params.add(getAD_PInstance_ID());
			params.add(bp.getC_BPartner_ID());			
			DB.setParameters(pstmt,params.toArray());
			rs = pstmt.executeQuery();
			sp = trx.setSavepoint(bp.getName());
			
			MOrgInfo oInfo = MOrgInfo.get(getCtx(), bp.getAD_Org_ID(), get_TrxName());
			
			TF_MOrder ord = new TF_MOrder(getCtx(), 0, get_TrxName());
			ord.setAD_Org_ID(bp.getAD_Org_ID());
			ord.setIsSOTrx(false);
			ord.setC_DocTypeTarget_ID(C_DocType_ID);
			ord.setC_DocType_ID(C_DocType_ID);
			ord.setDateAcct(dateInvoiced);
			ord.setDateOrdered(dateInvoiced);
			ord.setC_BPartner_ID(bp.getC_BPartner_ID());
			ord.setM_Warehouse_ID(oInfo.getM_Warehouse_ID());
			ord.setPaymentRule(TF_MOrder.PAYMENTRULE_OnCredit);
			//Price List
			int m_M_PriceList_ID = MPriceList.getDefault(getCtx(), true).getM_PriceList_ID();							
			ord.setM_PriceList_ID(m_M_PriceList_ID);
			ord.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
			
			ord.saveEx();
			
			//update Order in all the IO header
			//by BP
			
			String sqlOrderUpdate = "UPDATE M_InOut SET C_Order_ID = ? WHERE  M_InOut_ID IN (SELECT M_InOut_ID FROM M_InOutLIne \r\n" + 
					"				 WHERE (EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE \r\n" + 
					"				 T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID = M_InOutLIne.M_InOutLine_ID) OR M_InOutLIne.M_InOutLine_ID = ?)"
					+ " AND C_BPartner_ID = ? AND  DocStatus = 'CO' )";
			
			params = new ArrayList<Object>();
			params.add(ord.getC_Order_ID());
			params.add(getAD_PInstance_ID());
			params.add(M_InoutLine_ID);
			params.add(bp.getC_BPartner_ID());				
			DB.executeUpdateEx(sqlOrderUpdate, params.toArray(), get_TrxName());
			
			while (rs.next()) {
				
				BigDecimal price = rs.getBigDecimal("Price");
				int M_PRoduct_ID = rs.getInt("M_Product_ID");
				int Tax_ID = rs.getInt("C_Tax_ID");
				BigDecimal qty = rs.getBigDecimal("QtyEntered");
				int C_UOM_ID = rs.getInt("C_UOM_ID");
				
				//create lines
				TF_MOrderLine ordLine = new TF_MOrderLine(ord);				
				
				
				if(OverrideTaxConfig) {
					if(IsTaxIncluded)
						price = price.divide(taxRate, 2, RoundingMode.HALF_EVEN);
					ordLine.setC_Tax_ID(C_Tax_ID);
				}
				else {				
					if(Tax_ID == 0)
						Tax_ID = 1000000;
					ordLine.setC_Tax_ID(Tax_ID);
				}
				
								
				ordLine.setM_Product_ID(M_PRoduct_ID, C_UOM_ID);
				ordLine.setC_UOM_ID(C_UOM_ID);
				ordLine.setPriceActual(price);
				ordLine.setPriceList(price);
				ordLine.setPriceLimit(price);
				ordLine.setPriceEntered(price);
				ordLine.setQty(qty);				
				ordLine.saveEx();
				
				//Update all the iolines which are related for this consolidated order..
				//using update query for the IO Header associated with Order
				String sqlUpdateLine = "UPDATE M_InOutLine SET C_OrderLine_ID = ?, DocStatus = 'CL' WHERE M_InOut_ID IN (SELECT M_InOut_ID FROM M_InOut WHERE C_Order_ID = ?) "
						+ " AND M_Product_ID = ? AND C_UOM_ID = ?  AND Price = ? "
						+ " AND DocStatus = 'CO'";
				params = new ArrayList<Object>();
				params.add(ordLine.getC_OrderLine_ID());
				params.add(ordLine.getC_Order_ID());
				params.add(M_PRoduct_ID);
				params.add(C_UOM_ID);				
				params.add(price);
				DB.executeUpdateEx(sqlUpdateLine, params.toArray(), get_TrxName());
				
			}
			
			//complete document
			if (!ord.processIt(DocAction.ACTION_Complete))
				throw new AdempiereException("Failed when processing document - " + ord.getProcessMsg());
			ord.setDocStatus(TF_MOrder.DOCSTATUS_Completed);
			ord.setProcessed(true);
			ord.saveEx();
											
			trx.releaseSavepoint(sp);
			addLog(ord.get_Table_ID(), null, null, " Service PO Entry : " +  ord.getDocumentNo() + " is created!", ord.get_Table_ID(), ord.get_ID());
		
			
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
					
	}
		
}
