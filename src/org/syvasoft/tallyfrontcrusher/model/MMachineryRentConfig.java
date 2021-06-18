package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.Query;

public class MMachineryRentConfig extends X_TF_Machinery_RentConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3092877792605071908L;
	private static final BigDecimal UnitRent = null;


	public MMachineryRentConfig(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}


	public MMachineryRentConfig(Properties ctx, int TF_Machinery_RentConfig_ID, String trxName) {
		super(ctx, TF_Machinery_RentConfig_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public static BigDecimal getRent(Properties ctx, int PM_Machinery_ID, int jobwork_id, int rentUOM_ID) {
	
		String whereClause = "PM_Machinery_ID = ? and COALESCE(JobWork_Product_ID,0)  = ? AND C_UOM_ID = ?";
		MMachineryRentConfig m = new Query(ctx, Table_Name, whereClause, null)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(PM_Machinery_ID,jobwork_id, rentUOM_ID)
				.first();
		
		BigDecimal unitRent = BigDecimal.ZERO;
		if(m != null)
			unitRent = m.getUnitRent();
		
		return unitRent;
		
	}

	public static MMachineryRentConfig getExpense(Properties ctx, int PM_Machinery_ID, int jobwork_id, int UOM_ID) {
		
		String whereClause = "PM_Machinery_ID = ? and COALESCE(JobWork_Product_ID,0)  = ? AND C_UOM_ID = ?";
		MMachineryRentConfig m = new Query(ctx, Table_Name, whereClause, null)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(PM_Machinery_ID,jobwork_id, UOM_ID)
				.first();
				
		
		return m;
		
	}

}
