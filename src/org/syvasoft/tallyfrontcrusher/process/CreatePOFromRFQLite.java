package org.syvasoft.tallyfrontcrusher.process;

import java.sql.Timestamp;
import java.util.List;

import org.compiere.model.MPriceList;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;
import org.syvasoft.tallyfrontcrusher.model.TF_MRequisitionLine;

public class CreatePOFromRFQLite extends SvrProcess {

	Timestamp dateOrdered = null;
	int C_BPartner_ID = 0;
	int AD_Org_ID = 0;
	int C_DocType_ID = 0;
	
	@Override
	protected void prepare() {
		for(ProcessInfoParameter para : getParameter())
		{
			String name = para.getParameterName();
			if("DateOrdered".equals(name))
				dateOrdered = para.getParameterAsTimestamp();
			if("C_BPartner_ID".equals(name))
				C_BPartner_ID = para.getParameterAsInt();
			if("AD_Org_ID".equals(name))
				AD_Org_ID = para.getParameterAsInt();
			if("C_DocType_ID".equals(name))
				C_DocType_ID = para.getParameterAsInt();
		}
	}

	@Override
	protected String doIt() throws Exception {
		TF_MBPartner bp = new TF_MBPartner(getCtx(), C_BPartner_ID, get_TrxName());
		TF_MOrder ord = new TF_MOrder(getCtx(), 0, get_TrxName());
		ord.setAD_Org_ID(AD_Org_ID);
		ord.setBPartner(bp);
		ord.setDateAcct(dateOrdered);
		ord.setDateOrdered(dateOrdered);
		ord.setC_DocTypeTarget_ID(C_DocType_ID);
		ord.setC_DocType_ID(C_DocType_ID);
		ord.setIsSOTrx(false);
		ord.setPaymentRule(TF_MOrder.PAYMENTRULE_OnCredit);		
		int m_M_PriceList_ID = MPriceList.getDefault(getCtx(), false).getM_PriceList_ID();							
		ord.setM_PriceList_ID(m_M_PriceList_ID);
		ord.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
		ord.setDocStatus(TF_MOrder.DOCSTATUS_Drafted);
		ord.saveEx();
		
		String whereClause = "EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE "  + 
								" T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID = M_RequisitionLine.M_RequisitionLine_ID)";
		List<TF_MRequisitionLine> lines = new Query(getCtx(), TF_MRequisitionLine.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getAD_PInstance_ID())
				.list();
		
		for(TF_MRequisitionLine l : lines) {
			TF_MProduct p = new TF_MProduct(getCtx(), l.getM_Product_ID(), get_TrxName());
			TF_MOrderLine oLine = new TF_MOrderLine(ord);
			oLine.setM_Product_ID(l.getM_Product_ID(), l.getC_UOM_ID());
			oLine.setPrice(l.getPriceActual());
			oLine.setC_Tax_ID(p.getTax_ID(true, bp.isApplyTCS(), bp.isInterState()));
			oLine.setQty(l.getQty());
			oLine.saveEx();
			l.setC_OrderLine_ID(oLine.getC_OrderLine_ID());
			l.saveEx();
		}
		
		addLog(ord.get_Table_ID(), ord.getCreated(), null, ord.getDocumentNo() + " is created!", ord.get_Table_ID(), ord.get_ID());		
		return null;
	}

}
