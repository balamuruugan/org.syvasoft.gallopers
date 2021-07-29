package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import javax.management.Query;

import org.adempiere.util.ProcessUtil;
import org.compiere.model.I_C_BPartner;

public class MTransportOrder extends X_TF_TOrder {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6415785500222776872L;

	public MTransportOrder(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MTransportOrder(Properties ctx, int TF_TOrder_ID, String trxName) {
		super(ctx, TF_TOrder_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public void Processit(String docAction) {
		// TODO Auto-generated method stub
		
		if(MTransportOrder.DOCSTATUS_Completed.equals(docAction)) {
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
			
			List<MTransportOrderLine> torderLine = new org.compiere.model.Query(getCtx(), MTransportOrderLine.Table_Name, "TF_TOrder_ID=" + getTF_TOrder_ID(), get_TrxName()).list();
			
			for(MTransportOrderLine toline : torderLine) {
			
				MLumpSumRentConfig rentConfig = new MLumpSumRentConfig (getCtx(),0,get_TrxName());
			
				rentConfig.setAD_Org_ID(getAD_Org_ID());
				rentConfig.setTF_VehicleType_ID(toline.getTF_VehicleType_ID());
				rentConfig.setVendor_ID(getC_BPartner_ID());
				rentConfig.setTF_TOrder_ID(getTF_TOrder_ID());
				rentConfig.setTF_Destination_ID(toline.getTF_Destination_ID());
				rentConfig.setC_UOM_ID(toline.getC_UOM_ID());
				rentConfig.setFreightPrice(toline.getFreightPrice());
				rentConfig.setIsTaxIncluded(toline.isTaxIncluded());
				rentConfig.setC_Tax_ID(toline.getC_Tax_ID());
			//	rentConfig.setTF_TOrder_ID(getTF_TOrder_ID());
				rentConfig.saveEx();
				
			}
			
		}

	}

	public void reverseIt() {
		// TODO Auto-generated method stub
		setProcessed(false);
		setDocStatus(DOCSTATUS_Drafted);
		
		String whereClause = "TF_TOrder_ID = ?";
		List<MLumpSumRentConfig> rentConfig = new org.compiere.model.Query(getCtx(), MLumpSumRentConfig.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getTF_TOrder_ID())
				.list();
		for(MLumpSumRentConfig rconfig : rentConfig) {
			rconfig.setIsActive(false);
			rconfig.saveEx();
			
		}
	}

}
