package org.syvasoft.tallyfrontcrusher.process;

import java.util.List;

import org.adempiere.util.IProcessUI;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MGLPostingConfig;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class CreateCostingRecords extends SvrProcess {
	int AD_Org_ID = 0;
	String costingMethod = null;
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();		
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();						
			if(name.equals("AD_Org_ID"))
				AD_Org_ID = para[i].getParameterAsInt();			
			else if(name.equals("CostingMethod"))
				costingMethod = para[i].getParameterAsString();
		}

	}

	@Override
	protected String doIt() throws Exception {
		String whereClause = "AD_Org_ID IN (0,?) AND ProductType='I'";
		List<TF_MProduct> list = new Query(getCtx(), TF_MProduct.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID)
				.list();
		IProcessUI processMonitor = Env.getProcessUI(getCtx());
		MAcctSchema as = (MAcctSchema) MGLPostingConfig.getMGLPostingConfig(getCtx()).getC_AcctSchema();
		for(TF_MProduct prod : list) {
						
			MCost cost = prod.getCostingRecord(as, AD_Org_ID, 0, costingMethod);				
			if (cost == null) {			
				//Create Costing record for the product.
				int M_CostElement_ID = MCostElement.getByCostingMethod(getCtx(), costingMethod).get(0).get_ID();
				cost = MCost.get(prod, 0,
						as, AD_Org_ID,M_CostElement_ID , get_TrxName());
				cost.saveEx();
				
				if (processMonitor != null)
				{
					processMonitor.statusUpdate( "Costing Record created for " + prod.getName());;
				}
			}
		}
		return null;
	}

}
