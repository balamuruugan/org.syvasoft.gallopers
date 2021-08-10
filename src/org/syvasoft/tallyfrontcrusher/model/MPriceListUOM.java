package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
	
		
	//Price as it is or excluded tax	
	public BigDecimal getPrice(boolean isTaxIncluded) {
		BigDecimal price = super.getPrice();		
		if(!isTaxIncluded() || isTaxIncluded) {
			return price;
		}
		TF_MProduct prod = new TF_MProduct(getCtx(), getM_Product_ID(), get_TrxName());
		BigDecimal gstRate = prod.getGSTRate().divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN).add(BigDecimal.ONE);
		price = price.divide(gstRate, 2, RoundingMode.HALF_EVEN);
		
		return price; 
	}

	//Excludes Tax Amount
	@Override
	public BigDecimal getPrice() {
		return getPrice(false);
	}
	
	
	//Price as it is or excluded tax	
	public BigDecimal getPriceMin(boolean isTaxIncluded) {
		BigDecimal minPrice = super.getPriceMin(); 
		if(isTaxIncluded)
			return minPrice;
		
		if(minPrice.doubleValue() > 0) {
			if(!isTaxIncluded()) {
				TF_MProduct prod = new TF_MProduct(getCtx(), getM_Product_ID(), get_TrxName());
				BigDecimal gstRate = prod.getGSTRate().divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN).add(BigDecimal.ONE);
				minPrice = minPrice.divide(gstRate, 2, RoundingMode.HALF_EVEN);
			}			
			return minPrice;			
		}
		else
			return getPrice();
	}
	
	//Excludes Tax Amount
	@Override
	public BigDecimal getPriceMin() {
		return getPriceMin(false);
	}
	
	public void validateUniqueness(boolean newRecord) {
		String sql = "SELECT COUNT(*) FROM TF_PriceListUOM WHERE AD_Org_ID = ? AND M_Product_ID = ? AND "
				+ " C_UOM_ID = ? AND IsSOTrx = ? AND COALESCE(C_BPartner_ID,0) = ? AND COALESCE(TF_Destination_ID,0) = ? AND ValidFrom = ?" ;
		if(!newRecord) {
			sql += " AND TF_PriceListUOM_ID != ?";
		}
		int count = 0;
		if(newRecord) {
			count = DB.getSQLValue(get_TrxName(), sql, getAD_Org_ID(), getM_Product_ID(), getC_UOM_ID(), 
					isSOTrx() ? "Y" : "N", getC_BPartner_ID(), getTF_Destination_ID(), getValidFrom());
		}
		else {
			count = DB.getSQLValue(get_TrxName(), sql, getAD_Org_ID(), getM_Product_ID(), getC_UOM_ID(), 
					isSOTrx() ? "Y" : "N", getC_BPartner_ID(), getTF_Destination_ID(), getValidFrom(), getTF_PriceListUOM_ID());
		}
		if(count > 0) {
			throw new AdempiereException("Price is already entered for the current Product, UOM and Business Partner!");
		}
	}
	
	
	
	@Deprecated
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
	
	public static BigDecimal getPrice(Properties ctx, int M_Product_ID, int C_UOM_ID, 
			int C_BPartner_ID, int TF_Destination_ID, boolean isSOTrx, Timestamp dateAcct) {
		MPriceListUOM priceUOM = getPriceListUOM(ctx, M_Product_ID, C_UOM_ID, C_BPartner_ID, TF_Destination_ID, isSOTrx, dateAcct);
		if(priceUOM != null)
			return priceUOM.getPrice();
		else
			return BigDecimal.ZERO;
	}
	
	public static MPriceListUOM getPriceListUOM(Properties ctx, int M_Product_ID, int C_UOM_ID, 
			int C_BPartner_ID, int TF_Destination_ID, boolean isSOTrx, Timestamp dateAcct) {
		//Sales Price List - Price Selection Priority
	    //1. Product, UOM, Customer and Destination
	    //2. Product, UOM and Customer, (Destination is Blank)
	    //3. Product, UOM and Destination (Customer is Blank)
	    //4. Product, UOM (Customer and Destination are blank)
				
		String isSales = isSOTrx ? "Y" : "N";
		
		 //1. Product, UOM, Customer and Destination
		String whereClause = "M_Product_ID = ? AND C_UOM_ID = ? AND IsSOTrx=? "
				+ " AND COALESCE(C_BPartner_ID,0) = ? AND COALESCE(TF_Destination_ID,0) = ? "				
				+ " AND ValidFrom <= ?";		
		MPriceListUOM priceUOM = new Query(ctx, Table_Name, whereClause, null)
				.setClient_ID()
				.setParameters(M_Product_ID, C_UOM_ID, isSales,C_BPartner_ID, TF_Destination_ID, dateAcct)
				.setOrderBy("ValidFrom DESC")
				.first();
		if(priceUOM != null)
			return priceUOM;
		
		//2. Product, UOM and Customer, (Destination is Blank)
		whereClause = "M_Product_ID = ? AND C_UOM_ID = ? AND IsSOTrx=? "
				+ " AND COALESCE(C_BPartner_ID,0) = ? AND TF_Destination_ID IS NULL "				
				+ " AND ValidFrom <= ?";
		priceUOM = new Query(ctx, Table_Name, whereClause, null)
				.setClient_ID()
				.setParameters(M_Product_ID, C_UOM_ID, isSales, C_BPartner_ID, dateAcct)
				.setOrderBy("ValidFrom DESC")
				.first();
		if(priceUOM != null)
			return priceUOM;
		
		 
		//3. Product, UOM and Destination (Customer is Blank)
		whereClause = "M_Product_ID = ? AND C_UOM_ID = ? AND IsSOTrx=? "
				+ " AND C_BPartner_ID IS NULL AND COALESCE(TF_Destination_ID,0) = ? "				
				+ " AND ValidFrom <= ?";
		priceUOM = new Query(ctx, Table_Name, whereClause, null)
				.setClient_ID()
				.setParameters(M_Product_ID, C_UOM_ID, isSales, TF_Destination_ID, dateAcct)
				.setOrderBy("ValidFrom DESC")
				.first();
		if(priceUOM != null)
			return priceUOM;
		
		 //4. Product, UOM (Customer and Destination are blank)
		
		whereClause = "M_Product_ID = ? AND C_UOM_ID = ? AND IsSOTrx=? "
				+ " AND C_BPartner_ID IS NULL AND TF_Destination_ID IS NULL "				
				+ " AND ValidFrom <= ?";
		priceUOM = new Query(ctx, Table_Name, whereClause, null)
				.setClient_ID()
				.setParameters(M_Product_ID, C_UOM_ID, isSales, dateAcct)
				.setOrderBy("ValidFrom DESC")
				.first();
		if(priceUOM != null)
			return priceUOM;
		
		return null;
	}
	
}
