package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTax;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MGenerateTaxInvoice extends X_TF_Generate_TaxInvoice{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2995552885424149296L;
	
	public MGenerateTaxInvoice(Properties ctx, int TF_YardEntryApprove_ID, String trxName) {
		super(ctx, TF_YardEntryApprove_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MGenerateTaxInvoice(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	//*** Create a sales invoice for the specified invoice amount,
	// price and qty will be calculated dynamically to match the total amount. 
	// The list of materials should be selected based on sales history.
	public void createInvoiceLinesForInvoiceAmount(boolean reCreate, boolean ignoreVehicleRent) {
		if(!reCreate && get_ValueAsBoolean(COLUMNNAME_IsCreated))
			throw new AdempiereException("Invoice Lines are already generated!");
		
		//Delete existing lines.
		if(reCreate) {
			List<MGenerateTaxInvoiceLine> lines = new Query(getCtx(), MGenerateTaxInvoiceLine.Table_Name,
					COLUMNNAME_TF_Generate_Taxinvoice_ID + " = ? " , get_TrxName())
					.setClient_ID()
					.setParameters(getTF_Generate_Taxinvoice_ID())
					.list();
			for(MGenerateTaxInvoiceLine line : lines) {
				line.deleteEx(true);
			}
		}
		
		TF_MBPartner bp = new TF_MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
		//MCustomerType custType = new MCustomerType(getCtx(), bp.getTF_CustomerType_ID(), get_TrxName());
		BigDecimal actualSalesAmount = getActualSalesAmount();
		if(actualSalesAmount.doubleValue() < getTotalInvAmt().doubleValue()) {
			if(!MSysConfig.getBooleanValue("ENABLE_OVERBILLING", true))
				throw new AdempiereException("Actual Sales Amount: " + actualSalesAmount.doubleValue() +
						" which is less than Total Invoice Amount, so Over billing is not allowed!");
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try
		{			
			String sqlInvoiceLines = "SELECT\r\n" + 
					"	il.M_Product_ID, il.C_UOM_ID, \r\n" + 
					"	MAX(p.Name) Product, MAX(u.Name) UOM, SUM(il.QtyEntered) QtyEntered, \r\n" + 
					"	SUM(il.PriceEntered) PriceEntered, SUM(il.LineNetAmt) LineNetAmt\r\n" + 
					"FROM\r\n" + 
					"	C_Invoice i INNER JOIN C_InvoiceLine il\r\n" + 
					"	 ON i.C_Invoice_ID = il.C_Invoice_ID\r\n" + 
					"	INNER JOIN C_DocType d \r\n" + 
					"	 ON i.C_DocType_ID=d.C_DocType_ID\r\n" + 
					"	INNER JOIN M_Product p\r\n" + 
					"	 ON il.M_Product_ID = p.M_Product_ID\r\n" + 
					"	INNER JOIN C_UOM u\r\n" + 
					"	 ON il.C_UOM_ID = u.C_UOM_ID\r\n" + 
					"	 \r\n" + 
					"WHERE\r\n" + 
					"	i.AD_Client_ID = 1000000  AND i.Processed='Y'  AND i.DocStatus IN ('CO','CL')\r\n" + 
					"	AND p.M_Product_Category_ID = 1000056\r\n" + 
					"	AND I.AD_Org_ID =  ? AND d.DocBaseType= 'ARI'\r\n" + 
					"	AND i.C_BPartner_id =?  \r\n" + 
					"	AND i.DateAcct >= ? AND i.DateAcct <= ?\r\n" + 
					"GROUP BY\r\n" + 
					"	il.M_Product_ID, il.C_UOM_ID";
		
		pstmt = DB.prepareStatement(sqlInvoiceLines, get_TrxName());
		pstmt.setInt(1, getAD_Org_ID());
		pstmt.setInt(2, getC_BPartner_ID());
		pstmt.setTimestamp(3,getDateFrom());
		pstmt.setTimestamp(4, getDateTo());
		
		rs = pstmt.executeQuery();
		//int ProductCategory_ID = MSysConfig.getIntValue("VEHICLE_PRODCUCT_CATEGORY_ID", 1000055);
		int lineNo = 10;
		while(rs.next()) {
			MGenerateTaxInvoiceLine invoiceLine = new MGenerateTaxInvoiceLine(getCtx(), 0, get_TrxName());
			
			invoiceLine.setClientOrg(getAD_Client_ID(), getAD_Org_ID());
			invoiceLine.setTF_Generate_Taxinvoice_ID(getTF_Generate_Taxinvoice_ID());
			invoiceLine.setLine(lineNo);
			
			int M_Product_ID =  rs.getInt("M_Product_id");
			int C_UOM_ID = rs.getInt("C_Uom_id");
			invoiceLine.setM_Product_ID(M_Product_ID);
			invoiceLine.setC_UOM_ID(C_UOM_ID);		
			
			BigDecimal productSalesAmt = rs.getBigDecimal("LineNetAmt");
			BigDecimal salesRatio = productSalesAmt.divide(actualSalesAmount,3,RoundingMode.HALF_EVEN);
			BigDecimal productInvAmt = getTotalInvAmt().multiply(salesRatio);			
			
			TF_MProduct prod = new TF_MProduct(getCtx(), rs.getInt("M_Product_id"), get_TrxName());
			BigDecimal price = prod.getBillPrice();
			if(price == null || price.doubleValue() == 0) {
				DB.close(rs, pstmt);
				throw new AdempiereException("Please set Bill Price for " + prod.getName());
			}
			
			//Set Price based on Customer Type Billing Price Ratio
			//BigDecimal price = MPriceListUOM.getPrice(getCtx(), M_Product_ID, C_UOM_ID, getC_BPartner_ID(), true, getDateAcct());
			//if(custType.getBillingPriceRatio().doubleValue() > 0)
			//price = price.multiply(custType.getBillingPriceRatio());
			
			//Set Qty based on Customer Type Billing Qty Ratio
			// When BillingQtyRation is ZERO then Based on the amount BillingQty has to be calcualted.
			BigDecimal qty =  productInvAmt.divide(price, 2, RoundingMode.HALF_EVEN);
			invoiceLine.setQty(qty);
			
			//Price always includes tax
			//Exclude Tax amount from Price
			
			MTax tax = new MTax(getCtx(), prod.getTax_ID(true, bp.isInterState()), get_TrxName());				
			BigDecimal taxRate = tax.getRate();
			BigDecimal hundred = new BigDecimal("100");				
			BigDecimal priceExcludesTax = price.divide(BigDecimal.ONE
					.add(taxRate.divide(hundred,2,RoundingMode.HALF_UP)), 2, RoundingMode.HALF_UP);		
					
			invoiceLine.setPrice(priceExcludesTax);
			invoiceLine.setTaxableAmount(priceExcludesTax.multiply(invoiceLine.getQty()));
							
			BigDecimal SGST_Rate = taxRate.divide(new BigDecimal(2), 2, RoundingMode.HALF_EVEN);				
			invoiceLine.setSGST_Rate(SGST_Rate);
			invoiceLine.setCGST_Rate(SGST_Rate);
			invoiceLine.setIGST_Rate(BigDecimal.ZERO);
			invoiceLine.setIGST_Amt(BigDecimal.ZERO);
			//invoiceLine.setTF_Destination_ID(rs.getInt("tf_destination_id"));
			invoiceLine.calcAmounts();
			invoiceLine.saveEx();
			lineNo = lineNo + 10;
		}
		DB.close(rs, pstmt);
		if(lineNo > 10)
			setIsCreated("Y");
		}
		catch(Exception e)
		{
			throw new AdempiereException(e.getMessage(), e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}
	}
	
	public BigDecimal getActualSalesAmount() {
		String sqlInvoiceLines = "SELECT\r\n" +				 
				"	SUM(il.LineNetAmt) LineNetAmt\r\n" + 
				"FROM\r\n" + 
				"	C_Invoice i INNER JOIN C_InvoiceLine il\r\n" + 
				"	 ON i.C_Invoice_ID = il.C_Invoice_ID\r\n" + 
				"	INNER JOIN C_DocType d \r\n" + 
				"	 ON i.C_DocType_ID=d.C_DocType_ID\r\n" + 
				"	INNER JOIN M_Product p\r\n" + 
				"	 ON il.M_Product_ID = p.M_Product_ID\r\n" + 
				"	INNER JOIN C_UOM u\r\n" + 
				"	 ON il.C_UOM_ID = u.C_UOM_ID\r\n" + 
				"	 \r\n" + 
				"WHERE\r\n" + 
				"	i.AD_Client_ID = 1000000  AND i.Processed='Y'  AND i.DocStatus IN ('CO','CL')\r\n " + 
				"	AND p.M_Product_Category_ID = 1000056\r\n" + 
				"	AND I.AD_Org_ID =  ? AND d.DocBaseType= 'ARI'\r\n" + 
				"	AND i.C_BPartner_id =?  \r\n" + 
				"	AND i.DateAcct >= ? AND i.DateAcct <= ?\r\n"; 
		BigDecimal actualSalesAmount = DB.getSQLValueBD(get_TrxName(), sqlInvoiceLines, 
				getAD_Org_ID(), getC_BPartner_ID(), getDateFrom(), getDateTo());
		if(actualSalesAmount == null)
			actualSalesAmount = BigDecimal.ZERO;
		return actualSalesAmount;
	}
	//Create Invoice Lines for the actual sold amount
	public void createInvoiceLines(boolean reCreate, boolean ignoreVehicleRent) {
		
		if(!reCreate && get_ValueAsBoolean(COLUMNNAME_IsCreated))
			throw new AdempiereException("Invoice Lines are already generated!");
		
		//Delete existing lines.
		if(reCreate) {
			List<MGenerateTaxInvoiceLine> lines = new Query(getCtx(), MGenerateTaxInvoiceLine.Table_Name,
					COLUMNNAME_TF_Generate_Taxinvoice_ID + " = ? " , get_TrxName())
					.setClient_ID()
					.setParameters(getTF_Generate_Taxinvoice_ID())
					.list();
			for(MGenerateTaxInvoiceLine line : lines) {
				line.deleteEx(true);
			}
		}
		
		TF_MBPartner bp = new TF_MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
		MCustomerType custType = new MCustomerType(getCtx(), bp.getTF_CustomerType_ID(), get_TrxName());
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
				String sqlInvoiceLines = "	SELECT o.C_Order_ID, i.C_Invoice_ID, CASE WHEN m_product_category_id <> 1000055 THEN 1 ELSE 2 END sort,"
						+ "il.M_Product_id M_Product_id, il.C_Uom_id C_Uom_id,il.QtyEntered QtyEntered,"
						+ "il.PriceEntered PriceEntered, il.LineNetAmt LineNetAmt, c.Rate / 2 SGSTRate,c.Rate / 2 CGSTRate,0 IGSTRate," 
						+ "il.LineNetAmt * (c.rate / 2)/100 SGSTAmt,il.LineNetAmt * (c.rate / 2)/100 CGSTAmt,0 IGSTAmt,o.tf_destination_id" 
						+ "	FROM C_Invoice i INNER JOIN C_InvoiceLine il ON i.C_Invoice_ID = il.C_Invoice_ID" 
						+ "	LEFT JOIN c_order o ON o.c_order_id = i.c_order_id " 
						+ "	INNER JOIN c_tax c ON c.c_tax_id = il.c_tax_id " 
						+ "	INNER JOIN m_product m ON m.m_product_id = il.m_product_id " 
						+ "	WHERE " 
						//+ "	i.AD_Client_ID = 1000000  AND i.Processed='Y' AND o.IsTaxIncluded1 = 'Y' AND i.DocStatus IN ('CO','CL') "
						// For Namakkal bluemetals, default amount includes tax, but tax amount will be known after generating sales tax invoice
						+ "	i.AD_Client_ID = 1000000  AND i.Processed='Y' AND i.DocStatus IN ('CO','CL') " 
						+ "	AND TF_TRTaxInvoice_ID IS NULL AND o.OnAccount = 'N' AND I.AD_Org_ID =  ? AND i.IsSOTrx = 'Y'" 
						+ "	AND i.C_BPartner_id = ? AND i.DateAcct >= ? AND i.DateAcct <= ? ORDER BY 1";
			
			pstmt = DB.prepareStatement(sqlInvoiceLines, get_TrxName());
			pstmt.setInt(1, getAD_Org_ID());
			pstmt.setInt(2, getC_BPartner_ID());
			pstmt.setTimestamp(3,getDateFrom());
			pstmt.setTimestamp(4, getDateTo());
			
			rs = pstmt.executeQuery();
			int ProductCategory_ID = MSysConfig.getIntValue("VEHICLE_PRODCUCT_CATEGORY_ID", 1000055);
			int lineNo = 10;
			while(rs.next()) {
				MGenerateTaxInvoiceLine invoiceLine = new MGenerateTaxInvoiceLine(getCtx(), 0, get_TrxName());
				
				invoiceLine.setClientOrg(getAD_Client_ID(), getAD_Org_ID());
				invoiceLine.setTF_Generate_Taxinvoice_ID(getTF_Generate_Taxinvoice_ID());
				invoiceLine.setLine(lineNo);
				invoiceLine.setC_Order_ID(rs.getInt("C_Order_ID"));
				invoiceLine.setC_Invoice_ID(rs.getInt("C_Invoice_ID"));
				invoiceLine.setM_Product_ID(rs.getInt("M_Product_id"));
				invoiceLine.setC_UOM_ID(rs.getInt("C_Uom_id"));				
				
				//Ignoring Vehicle Rent
				if(invoiceLine.getM_Product().getM_Product_Category_ID() == ProductCategory_ID && ignoreVehicleRent)
					continue;
				
				
				TF_MProduct prod = new TF_MProduct(getCtx(), rs.getInt("M_Product_id"), get_TrxName());
				BigDecimal price = prod.getBillPrice();
				if(price == null || price.doubleValue() == 0) {
					DB.close(rs, pstmt);
					throw new AdempiereException("Please set Bill Price for " + prod.getName());
				}
				//Set Price based on Customer Type Billing Price Ratio
				//BigDecimal price = rs.getBigDecimal("PriceEntered");
				//if(custType.getBillingPriceRatio().doubleValue() > 0)
				//	price = rs.getBigDecimal("PriceEntered").multiply(custType.getBillingPriceRatio());
				
				//Set Qty based on Customer Type Billing Qty Ratio
				// When BillingQtyRation is ZERO then Based on the amount BillingQty has to be calcualted. 
				BigDecimal qty = rs.getBigDecimal("QtyEntered");
				/*
				if(custType.getBillingQtyRatio().doubleValue() > 0)
					qty = rs.getBigDecimal("QtyEntered").multiply(custType.getBillingQtyRatio());
				else if(custType.getBillingQtyRatio().doubleValue() == 0)
				*/
				qty =  rs.getBigDecimal("LineNetAmt").divide(price, 2, RoundingMode.HALF_EVEN);
				
				invoiceLine.setQty(qty);
				
				//Price always includes tax
				//Exclude Tax amount from Price
				
				MTax tax = new MTax(getCtx(), prod.getTax_ID(true, bp.isInterState()), get_TrxName());				
				BigDecimal taxRate = tax.getRate();
				BigDecimal hundred = new BigDecimal("100");				
				BigDecimal priceExcludesTax = price.divide(BigDecimal.ONE
						.add(taxRate.divide(hundred,2,RoundingMode.HALF_UP)), 2, RoundingMode.HALF_UP);				
				invoiceLine.setPrice(priceExcludesTax);
				invoiceLine.setTaxableAmount(priceExcludesTax.multiply(invoiceLine.getQty()));
								
				BigDecimal SGST_Rate = taxRate.divide(new BigDecimal(2), 2, RoundingMode.HALF_EVEN);				
				invoiceLine.setSGST_Rate(SGST_Rate);
				invoiceLine.setCGST_Rate(SGST_Rate);
				invoiceLine.setIGST_Rate(BigDecimal.ZERO);
				invoiceLine.setIGST_Amt(BigDecimal.ZERO);
				invoiceLine.setTF_Destination_ID(rs.getInt("tf_destination_id"));
				invoiceLine.calcAmounts();
				invoiceLine.saveEx();
				lineNo = lineNo + 10;
			}
			DB.close(rs, pstmt);
			if(lineNo > 10)
				setIsCreated("Y");
		}	
		catch(Exception e)
		{
			throw new AdempiereException(e.getMessage(), e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}
	}
	
	public void processIt(String docAction) {
		if(DocAction.ACTION_Prepare.equals(docAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(DocAction.ACTION_Complete.equals(docAction)) {
			setProcessed(true);
			setDocStatus(DOCSTATUS_Completed);
			createSalesTaxInvoice();
		}
	}
	
	public void createSalesTaxInvoice() {
		MTRTaxInvoice taxInvoice;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int lineNo = 10;
			
		
		try {
			TF_MBPartner bp = new TF_MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
			taxInvoice=new MTRTaxInvoice(getCtx(), 0, get_TrxName());								
			taxInvoice.setAD_Org_ID(getAD_Org_ID());
			taxInvoice.setDateAcct(getDateAcct());
			taxInvoice.setC_BPartner_ID(getC_BPartner_ID());
			taxInvoice.setDateSupply(getDateAcct());
			taxInvoice.setPlaceOfSupply(bp.getCity());
			taxInvoice.setPostGSTAsExpense(true);
			taxInvoice.setPostTaxToCustomer(false);
			taxInvoice.setC_BankAccount_ID(TF_MBankAccount.getDefaultBankAccount(getCtx(), Env.getAD_Org_ID(getCtx()), null));
			taxInvoice.setTF_Generate_Taxinvoice_ID(getTF_Generate_Taxinvoice_ID());			
			taxInvoice.setIsSOTrx(true);
			taxInvoice.setRoundOff(getRoundOff());
			taxInvoice.saveEx();
			String sqlInvoiceLines = "	SELECT 1 sortby,il.m_product_id,il.c_uom_id,sum(il.qty) QtyEntered, " +
									 "il.price PriceEntered, sum(il.taxableamount) LineNetAmt, il.sgst_rate,il.cgst_rate, " +
									 "il.igst_rate, sum(il.sgst_amt) sgst_amt,sum(il.cgst_amt)cgst_amt,sum(il.igst_amt)igst_amt," +
									 "sum(il.linetotalamt) totalamt " +
									 " FROM Tf_Generate_TaxInvoiceLine il INNER JOIN m_product m ON m.m_product_id = il.m_product_id " +
									"WHERE " + 
									  	"il.tf_generate_taxinvoice_id = ? AND "+  
										" m.m_product_category_id <> 1000055" +
									"GROUP BY " +
										"il.m_product_id,il.c_uom_id,il.price,il.sgst_rate,il.cgst_rate,il.igst_rate " +
							
									"UNION " +
							
									///"SELECT 2 sortby,(SELECT m_product_id FROM m_product WHERE value='TRANSPORTCHARGE') m_product_id, " +
									//"(SELECT c_uom_id FROM m_product WHERE value='TRANSPORTCHARGE')	c_uom_id,sum(il.qty) QtyEntered, " +
									
									"SELECT 2 sortby, il.m_product_id, " +
									" il.c_uom_id, sum(il.qty) QtyEntered, " +
									"	   il.Price PriceEntered, sum(il.taxableamount) LineNetAmt, il.sgst_rate,il.cgst_rate, " +
									"	   il.igst_rate, sum(il.sgst_amt),sum(il.cgst_amt)cgst_amt,sum(il.igst_amt)igst_amt, " +
									" sum(il.linetotalamt) totalamt " +
									" FROM  " +
									"	Tf_Generate_TaxInvoiceLine il  " +
									"	INNER JOIN m_product m ON m.m_product_id = il.m_product_id  " +
									" LEFT JOIN tf_destination d ON d.tf_destination_id = il.tf_destination_id  " +
									" WHERE " + 
									   	" il.tf_generate_taxinvoice_id = ? AND " + 
										" m.m_product_category_id = 1000055 " +
									" GROUP BY " +
									   " il.M_Product_ID, il.C_UOM_ID, il.Price, d.name,il.sgst_rate,il.cgst_rate,il.igst_rate " +
									   " order by 1,2,3 " ;
			
			pstmt = DB.prepareStatement(sqlInvoiceLines, get_TrxName());
			pstmt.setInt(1, getTF_Generate_Taxinvoice_ID());
			pstmt.setInt(2, getTF_Generate_Taxinvoice_ID());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MTRTaxInvoiceLine invoiceLine = new MTRTaxInvoiceLine(getCtx(), 0, get_TrxName());
				
				invoiceLine.setAD_Org_ID(getAD_Org_ID());
				invoiceLine.setTF_TRTaxInvoice_ID(taxInvoice.getTF_TRTaxInvoice_ID());
				invoiceLine.setM_Product_ID(rs.getInt("M_Product_id"));
				invoiceLine.setC_UOM_ID(rs.getInt("C_Uom_id"));
				BigDecimal qty = rs.getBigDecimal("QtyEntered");
				BigDecimal price = rs.getBigDecimal("PriceEntered");
				invoiceLine.setQty(qty);
				invoiceLine.setPrice(price);
				invoiceLine.setTaxableAmount(rs.getBigDecimal("LineNetAmt"));
				invoiceLine.setSGST_Rate(rs.getBigDecimal("sgst_rate"));
				invoiceLine.setCGST_Rate(rs.getBigDecimal("cgst_rate"));
				invoiceLine.setIGST_Rate(rs.getBigDecimal("igst_rate"));
				invoiceLine.setSGST_Amt(rs.getBigDecimal("sgst_amt"));
				invoiceLine.setCGST_Amt(rs.getBigDecimal("cgst_amt"));
				invoiceLine.setIGST_Amt(rs.getBigDecimal("igst_amt"));
				
				BigDecimal totalAmount = rs.getBigDecimal("LineNetAmt").add(rs.getBigDecimal("sgst_amt"))
						.add(rs.getBigDecimal("cgst_amt")).add(rs.getBigDecimal("igst_amt"));
				
				invoiceLine.setLineTotalAmt(totalAmount);
				invoiceLine.saveEx();
				lineNo = lineNo + 10;
			}
			DB.close(rs, pstmt);
			
			taxInvoice.processIt(DocAction.ACTION_Complete);
			taxInvoice.saveEx();
			
			//Setting sales tax invoice reference into sales entries.
			String sql = "UPDATE C_Order SET TF_TRTaxInvoice_ID = " + taxInvoice.getTF_TRTaxInvoice_ID() 
				+ " WHERE C_Order_ID IN (SELECT il.C_Order_ID FROM Tf_Generate_TaxInvoiceLine il WHERE "
				+ " TF_Generate_Taxinvoice_ID = " + getTF_Generate_Taxinvoice_ID() + ")";
			DB.executeUpdate(sql, get_TrxName());
			
		}
		catch (Exception ex) {
			throw new AdempiereException(ex.getMessage());
		}
		finally {
			rs = null;
			pstmt = null;
		}
	}
	
	public void reverseIt() {
		setProcessed(false);
		setDocStatus(DOCSTATUS_InProgress);
		String whereClause = COLUMNNAME_TF_Generate_Taxinvoice_ID + " = ? AND DocStatus = 'CO'"; 
		MTRTaxInvoice taxInv = new Query(getCtx(), MTRTaxInvoice.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getTF_Generate_Taxinvoice_ID())
				.first();
		if(taxInv != null) {
			taxInv.reverseIt();
			taxInv.setDocStatus(DOCSTATUS_Voided);
			taxInv.setProcessed(true);
			taxInv.saveEx();
		}
		
		//removing sales tax invoice reference from Sales Entry.
		String sql = "UPDATE C_Order SET TF_TRTaxInvoice_ID = NULL WHERE TF_TRTaxInvoice_ID = " + taxInv.getTF_TRTaxInvoice_ID();
		DB.executeUpdate(sql, get_TrxName());
		
	}
	
}
