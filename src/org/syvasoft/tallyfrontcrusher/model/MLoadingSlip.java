package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

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
		return super.beforeSave(newRecord);
	}
}
