package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;

public class MMachineryStatement extends X_PM_MachineStmt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8837243841677553600L;

	public MMachineryStatement(Properties ctx, int PM_MachineStmt_ID, String trxName) {
		super(ctx, PM_MachineStmt_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MMachineryStatement(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	
	public static void deleteTripSheetEntries(Properties ctx, int TF_TripSheet_ID, String trxName) {
		String whereClause="TF_TripSheet_ID=?";
		List<MMachineryStatement> list =new Query(ctx, MMachineryStatement.Table_Name, whereClause, trxName)
						.setClient_ID()
						.setParameters(TF_TripSheet_ID)
						.list();
		for(MMachineryStatement mStatement : list)
			mStatement.delete(true);
		
	}
}
