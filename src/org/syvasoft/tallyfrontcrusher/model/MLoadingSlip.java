package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.Query;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.acct.Doc_BankStatement;

public class MLoadingSlip extends X_TF_LoadingSlip {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1474924178778903106L;

	public MLoadingSlip(Properties ctx, int TF_LoadingSlip_ID, String trxName) {
		super(ctx, TF_LoadingSlip_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MLoadingSlip(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(!newRecord) {
			String oldSTatus = get_ValueOld(COLUMNNAME_Status).toString();
			if(oldSTatus.equals(STATUS_Unbilled) && is_ValueChanged(COLUMNNAME_LoadedTime))
				throw new AdempiereException("It is already completed by other loader!");
			if(getStatus().equals(STATUS_Unbilled))
				setProcessed(true);
		}
		
		if(getStatus().equals(STATUS_Unbilled)) {
			MWeighmentEntry weighment = new Query(getCtx(),MWeighmentEntry.Table_Name,"DocumentNo = '" + getDocumentNo() + "'",get_TrxName()).first();
		
			if(weighment != null) {
				weighment.setLoadedTime(getLoadedTime());
				weighment.setLoader_User_ID(getAD_User_ID());
				weighment.saveEx();
			}
		}
		
		return super.beforeSave(newRecord);
	}
}
