package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MProductCategory;

public class TF_MProductCategory extends MProductCategory {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8871919604163434832L;
	public TF_MProductCategory(Properties ctx, int M_Product_Category_ID, String trxName) {
		super(ctx, M_Product_Category_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public TF_MProductCategory(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

    /** Column name SpareExpensesAcct_ID */
    public static final String COLUMNNAME_SpareExpensesAcct_ID = "SpareExpensesAcct_ID";

	
	/** Set Spare Expenses Account.
	@param SpareExpensesAcct_ID Spare Expenses Account	  */
	public void setSpareExpensesAcct_ID (int SpareExpensesAcct_ID)
	{
		if (SpareExpensesAcct_ID < 1) 
			set_Value (COLUMNNAME_SpareExpensesAcct_ID, null);
		else 
			set_Value (COLUMNNAME_SpareExpensesAcct_ID, Integer.valueOf(SpareExpensesAcct_ID));
	}
	
	/** Get Spare Expenses Account.
		@return Spare Expenses Account	  */
	public int getSpareExpensesAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SpareExpensesAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}


	
}
