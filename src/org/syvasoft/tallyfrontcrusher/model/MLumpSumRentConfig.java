package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MSysConfig;
import org.compiere.model.Query;
import org.compiere.util.Env;

public class MLumpSumRentConfig extends X_TF_LumpSumRent_Config {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6002430357564063143L;

	public MLumpSumRentConfig(Properties ctx, int TF_LumpSumRent_Config_ID, String trxName) {
		super(ctx, TF_LumpSumRent_Config_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MLumpSumRentConfig(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	//old method -- do not use it.
	public static BigDecimal getRateKm(Properties ctx,int AD_Org_ID,int TF_Destination_ID, 
			int TF_VehicleType_ID,BigDecimal Distinace, String trxName) {
		BigDecimal RateKM=BigDecimal.ZERO;
		String Where;
		
		Where=" AD_Org_ID=? AND TF_VehicleType_ID=? AND (COALESCE(TF_Destination_ID,0) = ? OR ? between minkm AND maxkm)";
		MLumpSumRentConfig lumpDistConfig=new Query(ctx, Table_Name, Where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID,TF_VehicleType_ID,TF_Destination_ID, Distinace)
				.setOrderBy("COALESCE(TF_Destination_ID,0) DESC")
				.first();
		if(lumpDistConfig!=null) {
			RateKM=lumpDistConfig.getratekm();
		}
		return RateKM;
	}
	

	
	@Deprecated
	public static MLumpSumRentConfig getLumpSumRentConfig(Properties ctx,int AD_Org_ID,int Vendor_ID, int C_BPartner_ID, int M_Product_ID, int TF_Destination_ID, 
			int TF_VehicleType_ID,BigDecimal Distance, String trxName) {		
		String Where;
		//1 -- Vehicle Type, Vendor, Customer, Product
		//2 -- Vehicle Type, Customer, Product
		//3 -- Vehicle Type, Vendor, Product
		//4 -- Vehicle Type, Vendor, Customer for any product
		//5 -- Vehicle Type, Customer
		//6 -- Vehicle Type, Vendor
		//7 -- Vehicle Type, Product
		//8 -- Vehicle Type
		
		if(Distance.doubleValue() == 0)
			Distance = BigDecimal.ONE;
		
		//1 -- Vehicle Type, Vendor, Customer, Product
		Where=" AD_Org_ID=? AND Vendor_ID = ? AND C_BPartner_ID = ? AND TF_VehicleType_ID=? AND M_Product_ID = ? AND "
				+ "(COALESCE(TF_Destination_ID,0) = ? OR ? between minkm AND maxkm)";
		MLumpSumRentConfig lumpDistConfig=new Query(ctx, Table_Name, Where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID,Vendor_ID, C_BPartner_ID, TF_VehicleType_ID, M_Product_ID, TF_Destination_ID, Distance)
				.setOrderBy("COALESCE(TF_Destination_ID,0) DESC")
				.first();
		if(lumpDistConfig != null)
			return lumpDistConfig;
		
		//2 -- Vehicle Type, Customer, Product
		Where=" AD_Org_ID=? AND Vendor_ID IS NULL AND  C_BPartner_ID = ? AND TF_VehicleType_ID=? AND M_Product_ID = ? AND "
				+ "(COALESCE(TF_Destination_ID,0) = ? OR ? between minkm AND maxkm)";
		lumpDistConfig=new Query(ctx, Table_Name, Where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID,C_BPartner_ID, TF_VehicleType_ID, M_Product_ID, TF_Destination_ID, Distance)
				.setOrderBy("COALESCE(TF_Destination_ID,0) DESC")
				.first();
		if(lumpDistConfig != null)
			return lumpDistConfig;
		
		//3 -- Vehicle Type, Vendor, Product
		Where=" AD_Org_ID=? AND Vendor_ID = ? AND  C_BPartner_ID IS NULL AND TF_VehicleType_ID=? AND M_Product_ID = ? AND "
				+ "(COALESCE(TF_Destination_ID,0) = ? OR ? between minkm AND maxkm)";
		lumpDistConfig=new Query(ctx, Table_Name, Where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID,Vendor_ID, TF_VehicleType_ID, M_Product_ID, TF_Destination_ID, Distance)
				.setOrderBy("COALESCE(TF_Destination_ID,0) DESC")
				.first();
		if(lumpDistConfig != null)
			return lumpDistConfig;
		
		//4 -- Vehicle Type, Vendor, Customer for any product
		Where=" AD_Org_ID=? AND C_BPartner_ID = ? AND Vendor_ID = ? AND TF_VehicleType_ID=? AND M_Product_ID IS NULL AND "
				+ " (COALESCE(TF_Destination_ID,0) = ? OR ? between minkm AND maxkm)";
		lumpDistConfig=new Query(ctx, Table_Name, Where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID,C_BPartner_ID, Vendor_ID, TF_VehicleType_ID,TF_Destination_ID, Distance)
				.setOrderBy("COALESCE(TF_Destination_ID,0) DESC")
				.first();
		if(lumpDistConfig != null)
			return lumpDistConfig;
		
		//5 -- Vehicle Type, Customer
		Where=" AD_Org_ID=? AND C_BPartner_ID = ? AND Vendor_ID IS NULL AND TF_VehicleType_ID=? AND M_Product_ID IS NULL AND "
				+ " (COALESCE(TF_Destination_ID,0) = ? OR ? between minkm AND maxkm)";
		lumpDistConfig=new Query(ctx, Table_Name, Where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID,C_BPartner_ID, TF_VehicleType_ID,TF_Destination_ID, Distance)
				.setOrderBy("COALESCE(TF_Destination_ID,0) DESC")
				.first();
		if(lumpDistConfig != null)
			return lumpDistConfig;
				
		//6 -- Vehicle Type, Vendor
		Where=" AD_Org_ID=? AND C_BPartner_ID IS NULL AND Vendor_ID = ? AND TF_VehicleType_ID=? AND M_Product_ID IS NULL AND "
				+ " (COALESCE(TF_Destination_ID,0) = ? OR ? between minkm AND maxkm)";
		lumpDistConfig=new Query(ctx, Table_Name, Where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID,Vendor_ID, TF_VehicleType_ID,TF_Destination_ID, Distance)
				.setOrderBy("COALESCE(TF_Destination_ID,0) DESC")
				.first();
		if(lumpDistConfig != null)
			return lumpDistConfig;
						
		
		//7 -- Vehicle Type, Product 
		Where=" AD_Org_ID=? AND TF_VehicleType_ID=? AND M_Product_ID = ? AND C_BPartner_ID IS NULL AND Vendor_ID IS NULL AND "
				+ "(COALESCE(TF_Destination_ID,0) = ? OR ? between minkm AND maxkm)";
		lumpDistConfig=new Query(ctx, Table_Name, Where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID, TF_VehicleType_ID, M_Product_ID, TF_Destination_ID, Distance)
				.setOrderBy("COALESCE(TF_Destination_ID,0) DESC")
				.first();
		if(lumpDistConfig != null)
			return lumpDistConfig;
					
		
		//8 Vehicle Type
		Where=" AD_Org_ID=? AND TF_VehicleType_ID=? AND Vendor_ID IS NULL AND C_BPartner_ID IS NULL AND M_Product_ID IS NULL AND "
				+ " (COALESCE(TF_Destination_ID,0) = ? OR ? between minkm AND maxkm)";
		
		lumpDistConfig=new Query(ctx, Table_Name, Where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID,TF_VehicleType_ID,TF_Destination_ID, Distance)
				.setOrderBy("COALESCE(TF_Destination_ID,0) DESC")
				.first();
		
		return lumpDistConfig;
	}
	
	public static MLumpSumRentConfig getFreightConfig(Properties ctx,int AD_Org_ID,int Vendor_ID, int C_BPartner_ID, int M_Product_ID, int TF_Destination_ID, 
			int TF_VehicleType_ID,BigDecimal Distance, String trxName) {		
		String Where;
		int KM_UOM_ID = MSysConfig.getIntValue("KM_UOM", 1000071, Env.getAD_Client_ID(ctx));
		int MT_KM_UOM_ID = MSysConfig.getIntValue("MT_KM_UOM", 1000071, Env.getAD_Client_ID(ctx));
		
		//Transporter is mandatory for the transporter vehicle
		//For the Own Vehicle Transporter is blank
		
		//1 -- Vehicle Type, Vendor, Customer, Product
		//2 -- Vehicle Type, Vendor, Product
		//3 -- Vehicle Type, Vendor, Customer for any product		
		//4 -- Vehicle Type, Vendor		
		
		
		if(Distance.doubleValue() == 0)
			Distance = BigDecimal.ONE;
		
		//1 -- Vehicle Type, Vendor, Customer, Product
		Where=" AD_Org_ID=? AND COALESCE(Vendor_ID,0) = ? AND C_BPartner_ID = ? AND TF_VehicleType_ID=? AND M_Product_ID = ? AND "
				+ "(COALESCE(TF_Destination_ID,0) = ? OR (C_UOM_ID IN (?,?) AND TF_Destination_ID IS NULL))";
		MLumpSumRentConfig lumpDistConfig=new Query(ctx, Table_Name, Where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID,Vendor_ID, C_BPartner_ID, TF_VehicleType_ID, M_Product_ID, TF_Destination_ID, KM_UOM_ID,MT_KM_UOM_ID)
				.setOrderBy("COALESCE(TF_Destination_ID,0) DESC")
				.first();
		if(lumpDistConfig != null)
			return lumpDistConfig;
				
		
		//2 -- Vehicle Type, Vendor, Product
		Where=" AD_Org_ID=? AND Vendor_ID = ? AND  C_BPartner_ID IS NULL AND TF_VehicleType_ID=? AND M_Product_ID = ? AND "
				+ "(COALESCE(TF_Destination_ID,0) = ? OR (C_UOM_ID IN (?,?) AND TF_Destination_ID IS NULL))";
		lumpDistConfig=new Query(ctx, Table_Name, Where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID,Vendor_ID, TF_VehicleType_ID, M_Product_ID, TF_Destination_ID, KM_UOM_ID, MT_KM_UOM_ID)
				.setOrderBy("COALESCE(TF_Destination_ID,0) DESC")
				.first();
		if(lumpDistConfig != null)
			return lumpDistConfig;
		
		//3 -- Vehicle Type, Vendor, Customer for any product
		Where=" AD_Org_ID=? AND C_BPartner_ID = ? AND Vendor_ID = ? AND TF_VehicleType_ID=? AND M_Product_ID IS NULL AND "
				+ " (COALESCE(TF_Destination_ID,0) = ? OR (C_UOM_ID IN (?,?) AND TF_Destination_ID IS NULL)) ";
		lumpDistConfig=new Query(ctx, Table_Name, Where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID,C_BPartner_ID, Vendor_ID, TF_VehicleType_ID,TF_Destination_ID, KM_UOM_ID, MT_KM_UOM_ID)
				.setOrderBy("COALESCE(TF_Destination_ID,0) DESC")
				.first();
		if(lumpDistConfig != null)
			return lumpDistConfig;
		
		
		//4 -- Vehicle Type, Vendor
		Where=" AD_Org_ID=? AND C_BPartner_ID IS NULL AND Vendor_ID = ? AND TF_VehicleType_ID=? AND M_Product_ID IS NULL AND "
				+ " (COALESCE(TF_Destination_ID,0) = ? OR (C_UOM_ID IN (?,?) AND TF_Destination_ID IS NULL)) ";
		lumpDistConfig=new Query(ctx, Table_Name, Where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID,Vendor_ID, TF_VehicleType_ID,TF_Destination_ID, KM_UOM_ID, MT_KM_UOM_ID)
				.setOrderBy("COALESCE(TF_Destination_ID,0) DESC")
				.first();
		if(lumpDistConfig != null)
			return lumpDistConfig;
		
		//5 -- Vehicle Type, Vendor
		Where=" AD_Org_ID=? AND COALESCE(C_BPartner_ID,0) = ? AND COALESCE(Vendor_ID,0) = ? AND TF_VehicleType_ID=? AND M_Product_ID IS NULL AND "
				+ " (COALESCE(TF_Destination_ID,0) = ? OR (C_UOM_ID IN (?,?) AND TF_Destination_ID IS NULL)) ";
		lumpDistConfig=new Query(ctx, Table_Name, Where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID,C_BPartner_ID, Vendor_ID, TF_VehicleType_ID, TF_Destination_ID, KM_UOM_ID, MT_KM_UOM_ID)
				.setOrderBy("COALESCE(TF_Destination_ID,0) DESC")
				.first();
		if(lumpDistConfig != null)
			return lumpDistConfig;
		
		return lumpDistConfig;
	}
	
	public static MLumpSumRentConfig getFreightPrice(Properties ctx,int AD_Org_ID,int Vendor_ID, int C_BPartner_ID, int M_Product_ID, int TF_Destination_ID, 
			int TF_VehicleType_ID,BigDecimal Distance, int C_UOM_ID, String trxName) {		
		String Where;
		int KM_UOM_ID = MSysConfig.getIntValue("KM_UOM", 1000071, Env.getAD_Client_ID(ctx));
		int MT_KM_UOM_ID = MSysConfig.getIntValue("MT_KM_UOM", 1000071, Env.getAD_Client_ID(ctx));
		
		//Transporter is mandatory for the transporter vehicle
		//For the Own Vehicle Transporter is blank
		
		//1 -- Vehicle Type, Transporter, Customer, Product
		//2 -- Vehicle Type, Transporter, Product
		//3 -- Vehicle Type, Transporter, Customer for any product		
		//4 -- Vehicle Type, Transporter		
		//5 -- Vehicle Type, Customer, any transporter and any product
		
		if(Distance.doubleValue() == 0)
			Distance = BigDecimal.ONE;
		
		//1 -- Vehicle Type, Vendor, Customer, Product
		Where=" AD_Org_ID=? AND COALESCE(Vendor_ID,0) = ? AND C_BPartner_ID = ? AND TF_VehicleType_ID=? AND M_Product_ID = ? AND "
				+ "(COALESCE(TF_Destination_ID,0) = ? OR (C_UOM_ID IN (?,?) AND TF_Destination_ID IS NULL)) AND C_UOM_ID = ?";
		MLumpSumRentConfig lumpDistConfig=new Query(ctx, Table_Name, Where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID,Vendor_ID, C_BPartner_ID, TF_VehicleType_ID, M_Product_ID, TF_Destination_ID, KM_UOM_ID,MT_KM_UOM_ID , C_UOM_ID)
				.setOrderBy("COALESCE(TF_Destination_ID,0) DESC")
				.first();
		if(lumpDistConfig != null)
			return lumpDistConfig;
				
		
		//2 -- Vehicle Type, Vendor, Product
		Where=" AD_Org_ID=? AND Vendor_ID = ? AND  C_BPartner_ID IS NULL AND TF_VehicleType_ID=? AND M_Product_ID = ? AND "
				+ "(COALESCE(TF_Destination_ID,0) = ? OR (C_UOM_ID IN (?,?) AND TF_Destination_ID IS NULL)) AND C_UOM_ID = ?";
		lumpDistConfig=new Query(ctx, Table_Name, Where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID,Vendor_ID, TF_VehicleType_ID, M_Product_ID, TF_Destination_ID, KM_UOM_ID, MT_KM_UOM_ID, C_UOM_ID)
				.setOrderBy("COALESCE(TF_Destination_ID,0) DESC")
				.first();
		if(lumpDistConfig != null)
			return lumpDistConfig;
		
		//3 -- Vehicle Type, Vendor, Customer for any product
		Where=" AD_Org_ID=? AND C_BPartner_ID = ? AND Vendor_ID = ? AND TF_VehicleType_ID=? AND M_Product_ID IS NULL AND "
				+ "(COALESCE(TF_Destination_ID,0) = ? OR (C_UOM_ID IN (?,?) AND TF_Destination_ID IS NULL)) AND C_UOM_ID = ?";
		lumpDistConfig=new Query(ctx, Table_Name, Where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID,C_BPartner_ID, Vendor_ID, TF_VehicleType_ID,TF_Destination_ID, KM_UOM_ID, MT_KM_UOM_ID, C_UOM_ID)
				.setOrderBy("COALESCE(TF_Destination_ID,0) DESC")
				.first();
		if(lumpDistConfig != null)
			return lumpDistConfig;
		
		
		//4 -- Vehicle Type, Vendor
		Where=" AD_Org_ID=? AND C_BPartner_ID IS NULL AND Vendor_ID = ? AND TF_VehicleType_ID=? AND M_Product_ID IS NULL AND "
				+ "(COALESCE(TF_Destination_ID,0) = ? OR (C_UOM_ID IN (?,?) AND TF_Destination_ID IS NULL)) AND C_UOM_ID = ?";
		lumpDistConfig=new Query(ctx, Table_Name, Where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID,Vendor_ID, TF_VehicleType_ID,TF_Destination_ID, KM_UOM_ID, MT_KM_UOM_ID, C_UOM_ID)
				.setOrderBy("COALESCE(TF_Destination_ID,0) DESC")
				.first();
		if(lumpDistConfig != null)
			return lumpDistConfig;
		
		//5 -- Vehicle Type, Customer
		Where=" AD_Org_ID=? AND C_BPartner_ID = ? AND Vendor_ID IS NULL AND TF_VehicleType_ID=? AND M_Product_ID IS NULL AND "
				+ "(COALESCE(TF_Destination_ID,0) = ? OR (C_UOM_ID IN (?,?) AND TF_Destination_ID IS NULL)) AND C_UOM_ID = ?";
		lumpDistConfig=new Query(ctx, Table_Name, Where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID,Vendor_ID, TF_VehicleType_ID,TF_Destination_ID, KM_UOM_ID, MT_KM_UOM_ID, C_UOM_ID)
				.setOrderBy("COALESCE(TF_Destination_ID,0) DESC")
				.first();
		if(lumpDistConfig != null)
			return lumpDistConfig;
		
		return lumpDistConfig;
	}
	
	public static BigDecimal getRateMT(Properties ctx,int AD_Org_ID,int Vendor_ID, int C_BPartner_ID, int M_Product_ID, int TF_Destination_ID, 
			int TF_VehicleType_ID,BigDecimal Distance, String trxName) {
		BigDecimal RateMT=BigDecimal.ZERO;		
		
		MLumpSumRentConfig lumpDistConfig = getLumpSumRentConfig(ctx, AD_Org_ID, Vendor_ID, C_BPartner_ID, M_Product_ID, TF_Destination_ID, 
				TF_VehicleType_ID, Distance, trxName);
		if(lumpDistConfig != null)
			return lumpDistConfig.getRateMT();
		else
			return RateMT;
		
	}
	
	public static BigDecimal getRateKm(Properties ctx,int AD_Org_ID,int Vendor_ID, int C_BPartner_ID, int M_Product_ID, int TF_Destination_ID, 
			int TF_VehicleType_ID,BigDecimal Distance, String trxName) {
		BigDecimal RateKM=BigDecimal.ZERO;		
		
		
		MLumpSumRentConfig lumpDistConfig = getLumpSumRentConfig(ctx, AD_Org_ID, Vendor_ID, C_BPartner_ID, M_Product_ID, TF_Destination_ID,
				TF_VehicleType_ID, Distance, trxName);		
		if(lumpDistConfig != null)
			return lumpDistConfig.getratekm();
		else
			return RateKM;
		
	}
	
	public static BigDecimal getRateMTKm(Properties ctx,int AD_Org_ID,int Vendor_ID, int C_BPartner_ID, int M_Product_ID, int TF_Destination_ID, 
			int TF_VehicleType_ID,BigDecimal Distance, String trxName) {
		BigDecimal RateMTKM=BigDecimal.ZERO;		
		
		
		MLumpSumRentConfig lumpDistConfig = getLumpSumRentConfig(ctx, AD_Org_ID, Vendor_ID, C_BPartner_ID, M_Product_ID, TF_Destination_ID,
				TF_VehicleType_ID, Distance, trxName);		
		if(lumpDistConfig != null)
			return lumpDistConfig.getRateMTKM();
		else
			return RateMTKM;
		
	}
	
public static BigDecimal getLumpSumRent(Properties ctx,int AD_Org_ID, int Vendor_ID, int C_BPartner_ID, int M_Product_ID, int TF_Destination_ID, 
		int TF_VehicleType_ID,BigDecimal Distance, String trxName) {
		BigDecimal Rent=BigDecimal.ZERO;						
		MLumpSumRentConfig lumpDistConfig = getLumpSumRentConfig(ctx, AD_Org_ID, Vendor_ID, C_BPartner_ID, M_Product_ID, TF_Destination_ID, 
				TF_VehicleType_ID, Distance, trxName); 
		
		if(lumpDistConfig!=null) {
			if(lumpDistConfig.getRent_Amt().equals(BigDecimal.ZERO))
			{
				Rent=Distance.multiply(lumpDistConfig.getratekm());
			}
			else
			{
				return lumpDistConfig.getRent_Amt();
			}
		}
		
		return Rent;
	}
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		// TODO Auto-generated method stub
		
		int KM_UOM_ID = MSysConfig.getIntValue("KM_UOM", 1000071, getAD_Client_ID());
		int MT_KM_UOM_ID = MSysConfig.getIntValue("MT_KM_UOM", 1000071, getAD_Client_ID());

		if(getC_UOM_ID() != KM_UOM_ID && getC_UOM_ID() != MT_KM_UOM_ID) {
			if(getTF_Destination_ID()==0 && (getMaxKM()==0 && getMinKM()==0)) {
				throw new AdempiereException("Please enter either Destination or Kilometer Range!");
			}
			
			
			if(getTF_Destination_ID()>0 && (getMaxKM()>0 || getMinKM()>0)) {
				throw new AdempiereException("Please enter either Destination or Kilometer Range!");
			}
		}
		
		BigDecimal lumpsumRent=getRent_Amt();
		BigDecimal RateKm=getratekm();
		BigDecimal RateMT = getRateMT();
		BigDecimal RateMTKM = getRateMTKM();
		int result1 = lumpsumRent.compareTo(BigDecimal.ZERO);
		int result2 = RateKm.compareTo(BigDecimal.ZERO);
		int result3 = RateMT.compareTo(BigDecimal.ZERO);
		int result4 = RateMTKM.compareTo(BigDecimal.ZERO);
		
		if(result1==1 && result2==1 && result3 == 1 && result3 == result4) {
			throw new AdempiereException("Please enter either Lumpsum Rent or Rate/KM or Rate/MT or Rate/MT/Km!");
		}
			
		return super.beforeSave(newRecord);
	}
	
	public BigDecimal getCustomerFreightMargin(int C_BPartner_ID) {
		String whereClause = "C_BPartner_ID = ? AND TF_LumpSumRent_Config_ID = ?";
		MLumpSumRentRentMargin margin = new Query(getCtx(), MLumpSumRentRentMargin.Table_Name, whereClause, get_TrxName())
				.setParameters(C_BPartner_ID, getTF_LumpSumRent_Config_ID())
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.first();
		BigDecimal rentMargin = getRentMargin();
		if(margin != null)
			rentMargin = margin.getRentMargin();
		return rentMargin;
	}
	
	public BigDecimal getCustomerFreightPrice(int C_BPartner_ID) {
	
		BigDecimal rentMargin =  getCustomerFreightMargin(C_BPartner_ID);
		
		BigDecimal marginRate = rentMargin.divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN).add(BigDecimal.ONE);
		BigDecimal freightPrice = getFreightPrice().multiply(marginRate);
		
		return freightPrice;
	}
}
