package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.util.DB;

public class MPriceListUOM extends X_TF_PriceListUOM {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1724960450410275886L;

	public MPriceListUOM(Properties ctx, int TF_PriceListUOM_ID, String trxName) {
		super(ctx, TF_PriceListUOM_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MPriceListUOM(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		
		validateUniqueness(newRecord);		
		return super.beforeSave(newRecord);
	}
	
	@Override
	public BigDecimal getPriceMin() {
		BigDecimal minPrice = super.getPriceMin(); 
		if(minPrice.doubleValue() > 0)
			return minPrice;
		else
			return getPrice();
	}
	
	public void validateUniqueness(boolean newRecord) {
		String sql = "SELECT COUNT(*) FROM TF_PriceListUOM WHERE AD_Org_ID = ? AND M_Product_ID = ? AND "
				+ " C_UOM_ID = ? AND IsSOTrx = ? AND COALESCE(C_BPartner_ID,0) = ? AND ValidFrom = ?" ;
		if(!newRecord) {
			sql += " AND TF_PriceListUOM_ID != ?";
		}
		int count = 0;
		if(newRecord) {
			count = DB.getSQLValue(get_TrxName(), sql, getAD_Org_ID(), getM_Product_ID(), getC_UOM_ID(), 
					isSOTrx() ? "Y" : "N", getC_BPartner_ID(), getValidFrom());
		}
		else {
			count = DB.getSQLValue(get_TrxName(), sql, getAD_Org_ID(), getM_Product_ID(), getC_UOM_ID(), 
					isSOTrx() ? "Y" : "N", getC_BPartner_ID(), getValidFrom(), getTF_PriceListUOM_ID());
		}
		if(count > 0) {
			throw new AdempiereException("Price is already entered for the current Product, UOM and Business Partner!");
		}
	}
	
	public static BigDecimal getPrice(Properties ctx, int M_Product_ID, int C_UOM_ID, 
			int C_BPartner_ID, boolean isSOTrx) {
		String whereClause = "M_Product_ID = ? AND C_UOM_ID = ? AND IsSOTrx=? "
				+ " AND C_BPartner_ID "
				+ (C_BPartner_ID == 0 ? " IS NULL " : " = " + C_BPartner_ID);
		MPriceListUOM priceUOM = new Query(ctx, Table_Name, whereClause, null)
				.setClient_ID()
				.setParameters(M_Product_ID, C_UOM_ID, isSOTrx ? "Y" : "N")
				.setOrderBy("ValidFrom DESC")
				.first();
		if(priceUOM != null) {
			return priceUOM.getPrice();
		}
		else {
			whereClause = "M_Product_ID = ? AND C_UOM_ID = ? AND IsSOTrx=? "
					+ " AND C_BPartner_ID IS NULL";
			priceUOM = new Query(ctx, Table_Name, whereClause, null)
					.setClient_ID()
					.setParameters(M_Product_ID, C_UOM_ID, isSOTrx ? "Y" : "N")
					.first();
			if(priceUOM != null) {
				return priceUOM.getPrice();
			}
			else {
				return BigDecimal.ZERO;
			}
		}
			
	}
	
	public static BigDecimal getPrice(Properties ctx,  int M_Product_ID, int C_UOM_ID, 
			int C_BPartner_ID, boolean isSOTrx, Timestamp dateAcct) {
		String whereClause = "M_Product_ID = ? AND C_UOM_ID = ? AND IsSOTrx=? "
				+ " AND C_BPartner_ID "
				+ (C_BPartner_ID == 0 ? " IS NULL " : " = " + C_BPartner_ID)
				+ " AND ValidFrom <= ?";
		MPriceListUOM priceUOM = new Query(ctx, Table_Name, whereClause, null)
				.setClient_ID()
				.setParameters(M_Product_ID, C_UOM_ID, isSOTrx ? "Y" : "N", dateAcct)
				.setOrderBy("ValidFrom DESC")
				.first();
		if(priceUOM != null) {
			return priceUOM.getPrice();
		}
		else {
			whereClause = "M_Product_ID = ? AND C_UOM_ID = ? AND IsSOTrx=? "
					+ " AND C_BPartner_ID IS NULL"
					+ " AND ValidFrom <= ?";
			priceUOM = new Query(ctx, Table_Name, whereClause, null)
					.setClient_ID()
					.setParameters(M_Product_ID, C_UOM_ID, isSOTrx ? "Y" : "N", dateAcct)
					.setOrderBy("ValidFrom DESC")
					.first();
			if(priceUOM != null) {
				return priceUOM.getPrice();
			}
			else {
				return BigDecimal.ZERO;
			}
		}
			
	}
	
	public static BigDecimal getPrice(Properties ctx,  int AD_Org_ID, int M_Product_ID, int C_UOM_ID, 
			int C_BPartner_ID, boolean isSOTrx, Timestamp dateAcct) {
		String whereClause = "AD_Org_ID IN (0,?) AND M_Product_ID = ? AND C_UOM_ID = ? AND IsSOTrx=? "
				+ " AND C_BPartner_ID "
				+ (C_BPartner_ID == 0 ? " IS NULL " : " = " + C_BPartner_ID)
				+ " AND ValidFrom <= ?";
		MPriceListUOM priceUOM = new Query(ctx, Table_Name, whereClause, null)
				.setClient_ID()
				.setParameters(AD_Org_ID, M_Product_ID, C_UOM_ID, isSOTrx ? "Y" : "N", dateAcct)
				.setOrderBy("ValidFrom DESC")
				.first();
		if(priceUOM != null) {
			return priceUOM.getPrice();
		}
		else {
			whereClause = "AD_Org_ID IN (0,?) AND M_Product_ID = ? AND C_UOM_ID = ? AND IsSOTrx=? "
					+ " AND C_BPartner_ID IS NULL"
					+ " AND ValidFrom <= ?";
			priceUOM = new Query(ctx, Table_Name, whereClause, null)
					.setClient_ID()
					.setParameters(AD_Org_ID, M_Product_ID, C_UOM_ID, isSOTrx ? "Y" : "N", dateAcct)
					.setOrderBy("ValidFrom DESC")
					.first();
			if(priceUOM != null) {
				return priceUOM.getPrice();
			}
			else {
				return BigDecimal.ZERO;
			}
		}
			
	}
	
	public static MPriceListUOM getPriceListUOM(Properties ctx, int M_Product_ID, int C_UOM_ID, 
			int C_BPartner_ID, boolean isSOTrx, Timestamp dateAcct) {
		String whereClause = "M_Product_ID = ? AND C_UOM_ID = ? AND IsSOTrx=? "
				+ " AND C_BPartner_ID "
				+ (C_BPartner_ID == 0 ? " IS NULL " : " = " + C_BPartner_ID)
				+ " AND ValidFrom <= ?";
		MPriceListUOM priceUOM = new Query(ctx, Table_Name, whereClause, null)
				.setClient_ID()
				.setParameters(M_Product_ID, C_UOM_ID, isSOTrx ? "Y" : "N", dateAcct)
				.setOrderBy("ValidFrom DESC")
				.first();
		if(priceUOM != null) {
			return priceUOM;
		}
		else {
			whereClause = "M_Product_ID = ? AND C_UOM_ID = ? AND IsSOTrx=? "
					+ " AND C_BPartner_ID IS NULL"
					+ " AND ValidFrom <= ?";
			priceUOM = new Query(ctx, Table_Name, whereClause, null)
					.setClient_ID()
					.setParameters(M_Product_ID, C_UOM_ID, isSOTrx ? "Y" : "N", dateAcct)
					.setOrderBy("ValidFrom DESC")
					.first();
			if(priceUOM != null) {
				return priceUOM;
			}
			else {
				return null;
			}
		}
			
	}
	
}
