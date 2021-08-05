package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MDiscountRequest;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;

public class ProcessDiscountRequest extends SvrProcess {
	private int C_Order_ID=0;
	
	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		ProcessInfoParameter[] para = getParameter();		
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();
			if(name.equals("C_Order_ID"))
				C_Order_ID =  para[i].getParameterAsInt();	
		}

	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		if(C_Order_ID==0)
			return "Invalid Sales Order";
		TF_MOrder ord=new TF_MOrder(getCtx(), C_Order_ID, get_TrxName());
		if(ord.equals(null))
			return "Invalid Sales Order";
				
		String bpName="";
		TF_MBPartner bp;		
		if (ord.getPartyName() != null) {
			bpName=ord.getPartyName();
		}
		else {
			bp=new TF_MBPartner(getCtx(), ord.getC_BPartner_ID(), get_TrxName());
			bpName=bp.getName();
		}
			
		MPriceListUOM priceUOM = MPriceListUOM.getPriceListUOM(getCtx(), ord.getItem1_ID(), ord.getItem1_UOM_ID(), 
				ord.getC_BPartner_ID(),ord.getTF_Destination_ID(), true, ord.getDateAcct());
		BigDecimal price=ord.getItem1_UnitPrice();
		if(priceUOM == null) {
			throw new AdempiereException("Please define Sales Price for " + ord.getItem1().getName());
		}
		
		if(price.doubleValue() >= priceUOM.getPriceMin().doubleValue()) {
			throw new AdempiereException("Invalid Discount Request!");
		}
		
		MDiscountRequest dr=new MDiscountRequest(getCtx(), 0, get_TrxName());
		dr.setAD_Org_ID(ord.getAD_Org_ID());
		dr.setC_Order_ID(ord.getC_Order_ID());
		dr.setDateAcct(ord.getDateAcct());
		dr.setC_BPartner_ID(ord.getC_BPartner_ID());
		dr.setTF_Destination_ID(ord.getTF_Destination_ID());
		dr.setPartyName(bpName);
		dr.setC_UOM_ID(ord.getItem1_UOM_ID());
		dr.setM_Product_ID(ord.getItem1_ID());
		dr.setQty(ord.getItem1_Qty());
		dr.setIsRentInclusive(ord.isRentInclusive());
		dr.setStdPrice(priceUOM.getPrice());
		dr.setReqPrice(price);
		dr.setApprovedPrice(price);
		dr.setDiscntStatus(MDiscountRequest.DISCNTSTATUS_Requested);
		dr.saveEx();

		ord.setTF_DiscountRequest_ID(dr.getTF_DiscountRequest_ID());
		ord.saveEx();
		
		return "Discount Request created successfully";
	}

}
