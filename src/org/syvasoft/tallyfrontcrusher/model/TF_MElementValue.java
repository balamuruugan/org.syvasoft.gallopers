package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MElement;
import org.compiere.model.MElementValue;
import org.compiere.model.MJournal;
import org.compiere.model.MPeriod;
import org.compiere.model.MTable;
import org.compiere.model.MTree;
import org.compiere.model.Query;
import org.compiere.model.X_I_ElementValue;
import org.compiere.process.DocAction;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class TF_MElementValue extends MElementValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 599110180395281014L;

	public TF_MElementValue(X_I_ElementValue imp) {
		super(imp);
		// TODO Auto-generated constructor stub
	}

	public TF_MElementValue(Properties ctx, String Value, String Name, String Description, String AccountType,
			String AccountSign, boolean IsDocControlled, boolean IsSummary, String trxName) {
		super(ctx, Value, Name, Description, AccountType, AccountSign, IsDocControlled, IsSummary, trxName);
		// TODO Auto-generated constructor stub
	}

	public TF_MElementValue(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public TF_MElementValue(Properties ctx, int C_ElementValue_ID, String trxName) {
		super(ctx, C_ElementValue_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	 /** Column name AccountGroup_ID */
    public static final String COLUMNNAME_AccountGroup_ID = "AccountGroup_ID";
    
    public org.compiere.model.I_C_ElementValue getAccountGroup() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getAccountGroup_ID(), get_TrxName());	
	}
    
	/** Set Account Group.
		@param AccountGroup_ID Account Group	  */
	public void setAccountGroup_ID (int AccountGroup_ID)
	{
		if (AccountGroup_ID < 1) 
			set_Value (COLUMNNAME_AccountGroup_ID, null);
		else 
			set_Value (COLUMNNAME_AccountGroup_ID, Integer.valueOf(AccountGroup_ID));
	}

	/** Get Account Group.
		@return Account Group	  */
	public int getAccountGroup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AccountGroup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Column name DefaultOrg_ID */
    public static final String COLUMNNAME_DefaultOrg_ID = "DefaultOrg_ID";
    /** Set Default Org.
	@param DefaultOrg_ID Default Org	  */
	public void setDefaultOrg_ID (int DefaultOrg_ID)
	{
		if (DefaultOrg_ID < 1) 
			set_Value (COLUMNNAME_DefaultOrg_ID, null);
		else 
			set_Value (COLUMNNAME_DefaultOrg_ID, Integer.valueOf(DefaultOrg_ID));
	}
	
	/** Get Default Org.
		@return Default Org	  */
	public int getDefaultOrg_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DefaultOrg_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
    /** Column name TrackReceivablePayable */
    public static final String COLUMNNAME_TrackReceivablePayable = "TrackReceivablePayable";

    /** Set Track Receivable / Payable.
	@param TrackReceivablePayable 
	The Account will be included in Receivable / Payable Report.
  */
	public void setTrackReceivablePayable (boolean TrackReceivablePayable)
	{
		set_Value (COLUMNNAME_TrackReceivablePayable, Boolean.valueOf(TrackReceivablePayable));
	}
	
	/** Get Track Receivable / Payable.
		@return The Account will be included in Receivable / Payable Report.
	  */
	public boolean isTrackReceivablePayable () 
	{
		Object oo = get_Value(COLUMNNAME_TrackReceivablePayable);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public boolean isMandatoryCostCenter() {
		Object oo = get_Value("IsMandatoryCostCenter");
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
	
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(getC_Element_ID() == 0) {
			setC_Element_ID(Env.getContextAsInt(getCtx(), "#C_Element_ID"));
		}		
		if(newRecord) {
			String sql = "SELECT o.Name FROM C_ElementValue e INNER JOIN AD_Org o ON e.DefaultOrg_ID = o.AD_Org_ID " +
			" WHERE C_Element_ID = ? AND UPPER(TRIM(e.Name)) = UPPER(TRIM(?)) ";
			String orgName = DB.getSQLValueString(get_TrxName(), sql, getC_Element_ID(), getName());
			if(orgName != null) {
				throw new AdempiereException("This account head is already existed under " + orgName + " organization!" );
			}
			sql = "SELECT o.Name FROM C_ElementValue e INNER JOIN AD_Org o ON e.DefaultOrg_ID = o.AD_Org_ID " +
			" WHERE C_Element_ID = ? AND UPPER(TRIM(e.Value)) = UPPER(TRIM(?)) ";
			orgName = DB.getSQLValueString(get_TrxName(), sql, getC_Element_ID(), getValue());
			if(orgName != null) {
				throw new AdempiereException("Please specify unique Search Key !" );
			}
		}
		boolean ok = super.beforeSave(newRecord);
	
		return ok;
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {		
		boolean ok = super.afterSave(newRecord, success);
		int ad_Tree_ID = 0;
		//It works only for Chart of Account to manage tree.
		if(getC_Element().getElementType().equals(MElement.ELEMENTTYPE_Account)) {
			ad_Tree_ID= (new MElement(getCtx(), getC_Element_ID(), get_TrxName())).getAD_Tree_ID();
			String treeType= (new MTree(getCtx(),ad_Tree_ID,get_TrxName())).getTreeType();
			
			String sql = " UPDATE AD_TreeNode SET Parent_ID = " + getAccountGroup_ID() + 
					" WHERE AD_Tree_ID = " + ad_Tree_ID  + 
					"  AND Node_ID = " + getC_ElementValue_ID();			
			DB.executeUpdate(sql, get_TrxName());
			
			update_Tree(treeType);
		}
		if(isSummary() && (newRecord || is_ValueChanged(COLUMNNAME_TrackReceivablePayable)))
			updateTrackReceivablePayableIntoChild(ad_Tree_ID, getC_ElementValue_ID(), isTrackReceivablePayable());
		return ok;
	}
	
	private void updateTrackReceivablePayableIntoChild(int ad_tree_id, int node_id,  boolean Trackable) {
		String whereClause = "C_ElementValue_ID IN (SELECT Node_ID FROM AD_TreeNode WHERE AD_Tree_ID = ? AND Parent_ID = ?)";
		List<TF_MElementValue> nodes = new Query(getCtx(), Table_Name, whereClause, get_TrxName())
				.setClient_ID().setParameters(ad_tree_id, node_id).list();
		for(TF_MElementValue node : nodes) {
			node.setTrackReceivablePayable(Trackable);
			node.saveEx();
			if(node.isSummary()) {				
				updateTrackReceivablePayableIntoChild(ad_tree_id, node.getC_ElementValue_ID(), Trackable);
			}
		}
	}

}
