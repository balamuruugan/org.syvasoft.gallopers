package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.Query;

public class MQuarryToken extends X_TF_QuarryToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8171579574879607265L;

	public MQuarryToken(Properties ctx, int TF_QuarryToken_ID, String trxName) {
		super(ctx, TF_QuarryToken_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MQuarryToken(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(newRecord && getVehicleNo() == null) {
			MRentedVehicle rv = (MRentedVehicle) getTF_RentedVehicle();
			setVehicleNo(rv.getVehicleNo());
		}
		if(getDateEnd() != null)
			setProcessed(true);
		
		return super.beforeSave(newRecord);
	}
	
	
	public static void completeToken(MWeighmentEntry we) {
		String whereClause = "AD_Org_ID = ? AND UPPER(VehicleNo)=UPPER(?) AND Processed='N'";
		MQuarryToken qt = new Query(we.getCtx(), Table_Name, whereClause, we.get_TrxName())
				.setClient_ID()
				.setParameters(we.getAD_Org_ID(), we.getVehicleNo())
				.setOrderBy("DateStart")
				.first();
		if(qt != null) {
			qt.setTF_WeighmentEntry_ID(we.getTF_WeighmentEntry_ID());
			qt.setDateEnd(we.getGrossWeightTime());
			qt.setProcessed(true);
			qt.saveEx();
		}
			
	}
}
