package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;;

public class ProcessRevisePrice extends SvrProcess {

	private Timestamp ValidFrom;
	private BigDecimal ReviseMargin;
	private boolean Inactive;
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();		
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();
			if (name.equals("ValidFrom"))
				ValidFrom =  para[i].getParameterAsTimestamp();
			else if (name.equals("ReviseMargin"))
				ReviseMargin =  para[i].getParameterAsBigDecimal();
			else if (name.equals("Inactive"))
				Inactive =  para[i].getParameterAsBoolean();
		}
	}

	@Override
	protected String doIt() throws Exception {
		String m_processMsg = null;
		
		String whereClause ="";
		whereClause = " (EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE " +
				" T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID = TF_PriceListUOM.TF_PriceListUOM_ID))";
		
		//+ "AND C_Order.DocStatus IN ('CO','DR','IR'))";
		int i = 0;
		
		List<MPriceListUOM> pricelists = new Query(getCtx(),MPriceListUOM.Table_Name,whereClause,get_TrxName())
											 .setClient_ID().setParameters(getAD_PInstance_ID()).list();
		
		for(MPriceListUOM pricelist : pricelists) {
			BigDecimal finalPrice = BigDecimal.ZERO;		
			BigDecimal price = pricelist.getPrice(true);
			
			finalPrice = price.add(price.multiply(ReviseMargin).divide(new BigDecimal(100)));
					
			MPriceListUOM newpricelist = new MPriceListUOM(getCtx(), 0, get_TrxName());
			
			newpricelist.setAD_Org_ID(pricelist.getAD_Org_ID());
			newpricelist.setM_Product_ID(pricelist.getM_Product_ID());
			newpricelist.setC_UOM_ID(pricelist.getC_UOM_ID());
			newpricelist.setTF_Destination_ID(pricelist.getTF_Destination_ID());
			newpricelist.setC_BPartner_ID(pricelist.getC_BPartner_ID());
			newpricelist.setIsTaxIncluded(pricelist.isTaxIncluded());
			newpricelist.setIsRentInclusive(pricelist.isRentInclusive());
			newpricelist.setIsRoyaltyPassInclusive(pricelist.isRoyaltyPassInclusive());
			newpricelist.setValidFrom(ValidFrom);
			newpricelist.setDescription(pricelist.getDescription());
			newpricelist.setIsSOTrx(true);
			newpricelist.setPrice(finalPrice);
			newpricelist.saveEx();
			
			if(Inactive) {
				pricelist.setIsActive(false);
				pricelist.saveEx();
			}
		}
		
		return "Price revised successfully!";
	}

}
