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
	
	public static BigDecimal getRent(Properties ctx, int PM_Machinery_ID, int jobwork_id) {
	
		String whereClause = "PM_Machinery_ID = ? and jobwork_id = ?";
		MMachineryRentConfig m = new Query(ctx, Table_Name, whereClause, null)
				.setClient_ID()
				.setParameters(PM_Machinery_ID,jobwork_id)
				.first();
		
			 	return m.getUnitRent();
		
	}


}
