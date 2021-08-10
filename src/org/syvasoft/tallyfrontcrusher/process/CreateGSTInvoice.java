package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTax;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MDestination;
import org.syvasoft.tallyfrontcrusher.model.MTRTaxInvoice;
import org.syvasoft.tallyfrontcrusher.model.MTRTaxInvoiceLine;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;
import org.syvasoft.tallyfrontcrusher.model.TF_MBankAccount;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class CreateGSTInvoice extends SvrProcess {
	
	private int C_BankAccount_ID = 0;
	private Timestamp dateInvoiced = null;
	
	@Override
	protected void prepare() {		
		ProcessInfoParameter[] para = getParameter();		
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();						
			if(name.equals("C_BankAccount_ID"))
				C_BankAccount_ID = para[i].getParameterAsInt();
			else if(name.equals("DateInvoiced"))
				dateInvoiced = para[i].getParameterAsTimestamp();
		}
	}

	@Override
	protected String doIt() throws Exception {
		String whereClause = "EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE " +
				" T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID = C_Order.C_Order_ID) "
				+ " AND C_Order.DocStatus='CO' AND TF_TRTaxInvoice_ID IS NULL ";
				
		List<TF_MOrder> list = new Query(getCtx(), TF_MOrder.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getAD_PInstance_ID())
				.setOrderBy("DateAcct, DocumentNo ")
				.list();
		for(TF_MOrder ord : list) {
			createGSTInvoice(ord);
		}
		return list.size() + " sales entries are procesed!";
	}
	
	public void createGSTInvoice(TF_MOrder ord) {
		if(ord.getTF_TRTaxInvoice_ID() > 0 || !ord.isSOTrx())
			return;
		
		/*if(getItem1_PermitIssued().doubleValue() <= 0)
			throw new AdempiereException("Invalid Permit Issued!");*/
		
		MTRTaxInvoice inv = new MTRTaxInvoice(getCtx(),0, get_TrxName());
		inv.setAD_Org_ID(ord.getAD_Org_ID());
		inv.setIsSOTrx(true);
		inv.setC_Order_ID(ord.getC_Order_ID());
		if(dateInvoiced == null)
			inv.setDateAcct(ord.getDateAcct());
		else
			inv.setDateAcct(dateInvoiced);
		inv.setM_Warehouse_ID(ord.getM_Warehouse_ID());
		inv.setPartyName(ord.getPartyName());
		if(MSysConfig.getBooleanValue("POST_GST_TO_CUSTOMER", false)) {
			inv.setPostTaxToCustomer(true);
			inv.setPostGSTAsExpense(false);
		}
		else {
			inv.setPostTaxToCustomer(false);
			inv.setPostGSTAsExpense(true);
		}
		inv.setC_BPartner_ID(ord.getC_BPartner_ID());		
		inv.setDateSupply(ord.getDateAcct());
		
		MWeighmentEntry wEntry = new MWeighmentEntry(getCtx(), ord.getTF_WeighmentEntry_ID(), get_TrxName());
		TF_MBPartner partner = new TF_MBPartner(getCtx(),ord.getC_BPartner_ID(),get_TrxName());
		if(ord.getTF_WeighmentEntry_ID() > 0 &&  wEntry != null && partner.getIsPOSCashBP() && wEntry.getPartyName() == null) {
			inv.setPartyName(wEntry.getPartyName());
		}
		
		MDestination dest = new MDestination(getCtx(), ord.getTF_Destination_ID(), get_TrxName());
		
		inv.setPlaceOfSupply(dest.getName());
		
		inv.setVehicleNo(ord.getVehicleNo());		
		//inv.calcAmounts();		
		inv.setC_BankAccount_ID(TF_MBankAccount.getDefaultBankAccount(getCtx(), Env.getAD_Org_ID(getCtx()), null));
		if(C_BankAccount_ID > 0)
			inv.setC_BankAccount_ID(C_BankAccount_ID);
		String msg = inv.validateInvoiceDate();
		if(msg != null)
			throw new AdempiereException(msg);
		
		inv.saveEx();

		MTRTaxInvoiceLine invLine = new MTRTaxInvoiceLine(inv);
		invLine.setAD_Org_ID(ord.getAD_Org_ID());
		invLine.setM_Product_ID(ord.getItem1_ID());
		invLine.setC_UOM_ID(ord.getItem1_UOM_ID());
		
		//TF_MBPartner bp = partner;
		//MCustomerType custType = new MCustomerType(getCtx(), bp.getTF_CustomerType_ID(), get_TrxName());
		TF_MProduct prod=new TF_MProduct(getCtx(), ord.getItem1_ID(), get_TrxName());
		
		
		BigDecimal price = ord.getItem1_Price();
		//Set Price based on Customer Type Billing Price Ratio
		//BigDecimal price = getItem1_Price();		
		//BigDecimal price = MCustomerBillPrice.getBillPrice(getCtx(), getC_BPartner_ID(), getItem1_ID());
		//if(price == null || price.doubleValue() == 0)
		//	throw new AdempiereException("Please set Bill Price for " + prod.getName());
		/*
		if(isRentBreakup())
		{
			if(isRentInclusive()) {
				price = getItem1_UnitPrice();
			}
			else {
				price = getItem1_UnitPrice().add(getItem1_UnitRent());
			}
		}
		
		if(custType.getBillingPriceRatio().doubleValue() > 0)
			price = price.multiply(custType.getBillingPriceRatio());
		*/
		
		//Set Qty based on Customer Type Billing Qty Ratio
		// When BillingQtyRation is ZERO then Based on the amount BillingQty has to be calcualted. 
		BigDecimal qty = ord.getItem1_Qty();
		//if(custType.getBillingQtyRatio().doubleValue() > 0)
		//	qty = qty.multiply(custType.getBillingQtyRatio());
		//else {
			//qty = getGrandTotal().divide(price, 2, RoundingMode.HALF_EVEN);
		//}
		invLine.setQty(qty);
		
		
		//Exclude Tax amount from Price
		//TF_MProduct prod = new TF_MProduct(getCtx(), getItem1_ID(), get_TrxName());
		MTax tax = new MTax(getCtx(), prod.getTax_ID(true, partner.isInterState()), get_TrxName());				
		BigDecimal taxRate = tax.getRate();
		BigDecimal hundred = new BigDecimal("100");				
		BigDecimal priceExcludesTax = price.divide(BigDecimal.ONE
				.add(taxRate.divide(hundred,2,RoundingMode.HALF_UP)), 2, RoundingMode.HALF_UP);		// priceExcludesTax = Price/1.05		
		invLine.setPrice(priceExcludesTax);
		invLine.setTaxableAmount(priceExcludesTax.multiply(invLine.getQty()));
						
		BigDecimal SGST_Rate = taxRate.divide(new BigDecimal(2), 2, RoundingMode.HALF_EVEN);				
		invLine.setSGST_Rate(SGST_Rate);
		invLine.setCGST_Rate(SGST_Rate);
		invLine.setIGST_Rate(BigDecimal.ZERO);
		invLine.setIGST_Amt(BigDecimal.ZERO);
		
		invLine.calcAmounts();
		invLine.saveEx();
		
		//No Rent Included
		
		ord.setTF_TRTaxInvoice_ID(inv.getTF_TRTaxInvoice_ID());
		ord.saveEx();
		inv.processIt(DocAction.ACTION_Complete);
		inv.saveEx();
		addLog(inv.get_Table_ID(), null, null, inv.getDocumentNo() + " is created!", inv.get_Table_ID(), inv.get_ID());
	}

}
