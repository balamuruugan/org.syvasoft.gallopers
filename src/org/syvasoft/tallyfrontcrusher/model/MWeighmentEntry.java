package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MDocType;
import org.compiere.model.MInOutLine;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MSysConfig;
import org.compiere.model.Query;
import org.compiere.util.DB;

public class MWeighmentEntry extends X_TF_WeighmentEntry {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2613943323993702690L;
	public MWeighmentEntry(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	public MWeighmentEntry(Properties ctx, int TF_WeighmentEntry_ID, String trxName) {
		super(ctx, TF_WeighmentEntry_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	void CreateBP() {
		if(getPartyName()!=null && getC_BPartner_ID()==0) {
			
			//Check Customer with Phone No
			String whereClause="lower(replace(Phone,' ',''))=lower(replace(?,' ','')) and IsCustomer='Y'";
			TF_MBPartner bp = new Query(getCtx(), TF_MBPartner.Table_Name, whereClause, get_TrxName())
					.setClient_ID().setParameters(getPhone().replace(" ", "")).first();
			if(bp != null)
				setC_BPartner_ID(bp.getC_BPartner_ID());
			else{
				//Get Destination
				String CustomerDest="";
				if(getTF_Destination_ID()>0) {
					MDestination destc=new MDestination(getCtx(), getTF_Destination_ID(), get_TrxName());
					CustomerDest=destc.getName();
				}
				else {
					CustomerDest=getNewDestination();
				}
				TF_MBPartner bpnew=new TF_MBPartner(getCtx(), 0, get_TrxName());
				bpnew.setAD_Org_ID(getAD_Org_ID());
				bpnew.setValue(getPhone() != null ? getPhone() : getPartyName().toUpperCase());
				bpnew.setName(getPartyName());
				bpnew.setC_BP_Group_ID(1000001);
				bpnew.setPhone(getPhone());
				bpnew.setContactName(getPartyName());
				bpnew.setAddress1(CustomerDest); //set Destination
				bpnew.setCity(CustomerDest); // Set Destination
				bpnew.setIsCustomer(true);
				bpnew.setTF_CustomerType_ID(1000000);
				bpnew.setC_Country_ID(208);
				bpnew.saveEx();
				
				setC_BPartner_ID(bpnew.getC_BPartner_ID());
			}
		}
	}
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		//CreateBP();
		CreateDestination();
		CreateCustomerVehicle();
		
		if(getTF_RentedVehicle_ID() > 0 && (getVehicleNo() == null || getVehicleNo().length() == 0))
				setVehicleNo(getTF_RentedVehicle().getVehicleNo());
		
		if(getC_BPartner_ID() == 0 && getPaymentRule().equals(PAYMENTRULE_Cash)) {
			TF_MBPartner bp = new Query(getCtx(), TF_MBPartner.Table_Name, "IsPOSCashBP='Y'", get_TrxName())
					.setClient_ID().first();
			if(bp != null)
				setC_BPartner_ID(bp.getC_BPartner_ID());
		}
		
		if(getTF_RentedVehicle_ID()>0) {
			String rvwhere="COALESCE(Tareweight,0)!=? AND IsTransporter='N' AND TF_RentedVehicle_ID=?";
			MRentedVehicle rv= new Query(getCtx(), MRentedVehicle.Table_Name, rvwhere, get_TrxName())
					.setClient_ID()
					.setParameters(getTareWeight(),getTF_RentedVehicle_ID())
					.first();
			
			if(rv!=null) {
				if(rv.getTareWeight()!=null) {
					rv.setOldTareweight(rv.getTareWeight());
				}
				rv.setTareWeight(getTareWeight());
				rv.saveEx();
			}
					
		}
		
		if(!newRecord && getWeighmentEntryType().equals(WEIGHMENTENTRYTYPE_Sales)) {
			if(is_Changed() && getStatus().equals(STATUS_Unbilled) && !is_ValueChanged(COLUMNNAME_Status)) {
				setStatus(STATUS_UnderReview);
			}
		}
		
		/*
		//csv import support
		if(newRecord) {
			
			//Set weighment entry type by product
			if(getWeighmentEntryType() == null) {
				if(!getM_Product().isSold()) {
					setWeighmentEntryType(WEIGHMENTENTRYTYPE_OwnProductionReceipt);
					setTF_Quarry_ID(1000006);
					setTF_Send_To(TF_SEND_TO_Stock);
				}
				else if(getM_Product().isSold()) {
					setWeighmentEntryType(WEIGHMENTENTRYTYPE_Sales);
				}
			}
			//set Rented Vehicle ID
			String whereClause = "AD_Org_ID IN (0,?) AND REPLACE(UPPER(VehicleNo),' ','') = ?";
			MRentedVehicle rv = new Query(getCtx(), MRentedVehicle.Table_Name, whereClause, get_TrxName())
					.setClient_ID()
					.setParameters(getAD_Org_ID(), getVehicleNo().toUpperCase().replace(" ", ""))
					.first();
			if(rv != null)
				setTF_RentedVehicle_ID(rv.getTF_RentedVehicle_ID());
			
			//set Payment Rule by BP
			if(getC_BPartner_ID() > 0) {
				TF_MBPartner bp = new TF_MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
				if(bp.getIsPOSCashBP()) 
					setPaymentRule(PAYMENTRULE_Cash);
				else
					setPaymentRule(PAYMENTRULE_OnCredit);
			}
			//set deafult warehouse
			MOrgInfo oInfo = MOrgInfo.get(getCtx(), getAD_Org_ID(), get_TrxName());
			setM_Warehouse_ID(oInfo.getM_Warehouse_ID());
			
			if(getNetWeightUnit() == null || getNetWeightUnit().doubleValue() == 0)
				setNetWeightUnit(getNetWeight());
		}
		*/
		//	if(getTF_RentedVehicle_ID()>0	&& is_ValueChanged(COLUMNNAME_TareWeight)) {
			//Timestamp tareWeightTime = new Timestamp(System.currentTimeMillis());
			//setTareWeightTime(tareWeightTime);
		//	setStatus(STATUS_InProgress);
		
		//if(getGrossWeight().doubleValue() > 0 && getTareWeight().doubleValue() <=0)
		//	throw new AdempiereException("Tare Weight should be greater than ZERO!");
		
		//if(getNetWeight().doubleValue() < 0)
		//	throw new AdempiereException("Gross Weight should be greater Tare Weight!");
		
		//if(getTareWeight().doubleValue() > 0 
		//		&& is_ValueChanged(COLUMNNAME_TareWeight)) {
			//Timestamp tareWeightTime = new Timestamp(System.currentTimeMillis());
			//setTareWeightTime(tareWeightTime);
		//	setStatus(STATUS_InProgress);
			
		//	if (getGrossWeight().doubleValue() > 0)
		//		setStatus(STATUS_Unbilled);
		//}
		//else if(getTareWeight().doubleValue() == 0) {
		//	setTareWeightTime(null);
		//	setStatus(STATUS_InProgress);
		//}
		
		//if(getGrossWeight().doubleValue() > 0 
		//		&& is_ValueChanged(COLUMNNAME_GrossWeight) ) {
			//Timestamp grossWeightTime = new Timestamp(System.currentTimeMillis());
			//setGrossWeightTime(grossWeightTime);		
		//	setStatus(STATUS_Unbilled);
		//}
		//else if(getGrossWeight().doubleValue() == 0) {
			//setGrossWeightTime(null);
		//	setStatus(STATUS_InProgress);
		//}
		
		/*
		if(getTF_RentedVehicle_ID() > 0 && !getTF_RentedVehicle().getTareWeight().equals(getTareWeight())) {
			MRentedVehicle v = new MRentedVehicle(getCtx(), getTF_RentedVehicle_ID(), get_TrxName());
			v.setTareWeight(getTareWeight());
			v.saveEx();
			int expiryDays = MSysConfig.getIntValue("TAREWEIGHT_EXPIRY_DAYS", 10);
			String sql = "UPDATE TF_RentedVehicle SET DateTareweightExpiry = now() + " + expiryDays
					+ " WHERE TF_RentedVehicle_ID = " + getTF_RentedVehicle_ID();
			DB.executeUpdate(sql, get_TrxName());
			
		}
		*/
		boolean ok = super.beforeSave(newRecord);
		return ok;
	}
	void CreateCustomerVehicle() {
		if(getTF_RentedVehicle_ID() == 0 && getPaymentRule().equals(PAYMENTRULE_OnCredit.toString())) {
			String whereClause="UPPER(replace(vehicleno,' ',''))='"+getVehicleNo().replace(" ","").toUpperCase()+"'";
			
			MRentedVehicle rentedVehicle = new Query(getCtx(), MRentedVehicle.Table_Name, whereClause, get_TrxName())
											.setClient_ID()
											.first();
			
			if(rentedVehicle != null) {
				setTF_RentedVehicle_ID(rentedVehicle.getTF_RentedVehicle_ID());
			}
			else {
				MRentedVehicle rentedVehiclenew = new MRentedVehicle(getCtx(), 0, get_TrxName());
				int C_UOM_ID = MSysConfig.getIntValue("Vehicle_UOM_ID", 100);
				int Product_Category_ID = MSysConfig.getIntValue("Vehicle_Product_Category_ID", 1000055);
				
				rentedVehiclenew.setAD_Org_ID(getAD_Org_ID());
				rentedVehiclenew.setVehicleNo(getVehicleNo().replace(" ","").toUpperCase());
				
				
				if(getTransporter_ID() > 0) {
					rentedVehiclenew.setC_BPartner_ID(getTransporter_ID());
					rentedVehiclenew.setIsTransporter(true);
				}
				else {
					rentedVehiclenew.setC_BPartner_ID(getC_BPartner_ID());
				}
				rentedVehiclenew.setTareWeight(getTareWeight());
				rentedVehiclenew.setVehicleSOPOType(MRentedVehicle.VEHICLESOPOTYPE_Sales);
				rentedVehiclenew.setTF_VehicleType_ID(getTF_VehicleType_ID());
				rentedVehiclenew.setM_Product_Category_ID(Product_Category_ID);
				rentedVehiclenew.setC_UOM_ID(C_UOM_ID);
				rentedVehiclenew.setIsActive(true);
				rentedVehiclenew.saveEx();
				
				setTF_RentedVehicle_ID(rentedVehiclenew.get_ID());
			}
		}
	}
	
	void CreateDestination() {
		if(getNewDestination()!=null && getTF_Destination_ID()==0) {
			String whereClause="UPPER(replace(Name,' ',''))='"+getNewDestination().replace(" ","").toUpperCase()+"'";
			MDestination dest=new Query(getCtx(),MDestination.Table_Name, whereClause,get_TrxName())
					.setClient_ID()
					.first();
			if(dest!=null) {
				setTF_Destination_ID(dest.getTF_Destination_ID());
			}
			else {
				MDestination destnew=new MDestination(getCtx(), 0, get_TrxName());
				destnew.setAD_Org_ID(getAD_Org_ID());
				destnew.setName(getNewDestination());
				destnew.setDistance(BigDecimal.ZERO);
				destnew.setRate(BigDecimal.ZERO);
				destnew.setIsActive(true);
				destnew.saveEx();
				
				setTF_Destination_ID(destnew.get_ID());
			}
		}
	}
	
	public void close() {
		if(getStatus().equals(STATUS_Billed))
			throw new AdempiereException("Weighment Entry is already processed!");
		setStatus(STATUS_Billed);
		//setProcessed(true);		
	}
	public void reverse() {
		setStatus(STATUS_Unbilled);		
		//setProcessed(false);
		//Only Shipment document will set processed as True
		//or false while reversing shipment document.
	}
	
	public BigDecimal getCFTMultiplyRate() {
		if(getNetWeightUnit() == null)
			return null;
		if(getNetWeight().doubleValue() == getNetWeightUnit().doubleValue())
			return BigDecimal.ONE;
		return getNetWeightUnit().divide(getNetWeight()
					.divide(new BigDecimal(1000), 2, RoundingMode.HALF_EVEN)
					, 2, RoundingMode.HALF_EVEN);
	}
	
	public boolean isGST() {
		//BigDecimal royaltyPassQty = getPassQtyIssued();
		//if(royaltyPassQty == null)
		//	royaltyPassQty = BigDecimal.ZERO;
		
		return getGSTAmount().doubleValue() > 0;
	}
	
	/***
	 * Returns Sales Quick Entry Document Type ID
	 * @return
	 */
	public int getC_DocType_ID() {
		if(isGST())
			return TF_MOrder.GSTOrderDocType_ID(getCtx());
		else
			return TF_MOrder.NonGSTOrderDocType_ID(getCtx());
	}
	
	/***
	 * Returns Shipment (Customer) Document Type ID
	 * @return
	 */
	public int getC_DocTypeShipment_ID() {
		MDocType dt = new MDocType(getCtx(), getC_DocType_ID(), get_TrxName());
		return dt.getC_DocTypeShipment_ID();
	}
	
	public int getRoyaltyPassProduct_ID() {
		return MSysConfig.getIntValue("ROYALTY_PASS_PRODUCT_ID", 0, getAD_Client_ID(), getAD_Org_ID());
	}
	
	public int getC_Tax_ID() {
		TF_MProduct p = new TF_MProduct(getCtx(), getM_Product_ID(), get_TrxName());
		return p.getTax_ID(isGST());
	}
	
	public int getM_InOut_ID() {
		String whereClause = "TF_WeighmentEntry_ID = ? AND DocStatus IN ('CO','CL')";		
		TF_MInOut inout = new Query(getCtx(), TF_MInOut.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getTF_WeighmentEntry_ID())
				.first();
		if(inout != null)
			return inout.get_ID();
		else
			return 0;
	}
	
	public int getM_InOutLine_ID() {
		String whereClause = "TF_WeighmentEntry_ID = ? AND DocStatus IN ('CO','CL')";		
		TF_MInOut inout = new Query(getCtx(), TF_MInOut.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getTF_WeighmentEntry_ID())
				.first();
		
			
		if(inout != null) {
			for(MInOutLine line : inout.getLines()) {
				if(line.getM_Product_ID() == getM_Product_ID()) {
					return line.get_ID();
				}
			}
		}
		
		 return 0;
	}
	
	
	public int getM_InOutLine_ID(int M_Product_ID) {
		String whereClause = "TF_WeighmentEntry_ID = ? AND DocStatus IN ('CO','CL')";		
		TF_MInOut inout = new Query(getCtx(), TF_MInOut.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getTF_WeighmentEntry_ID())
				.first();
		
			
		if(inout != null) {
			for(MInOutLine line : inout.getLines()) {
				if(line.getM_Product_ID() == M_Product_ID) {
					return line.get_ID();
				}
			}
		}
		
		 return 0;
	}
	
	public void shipped() {
		setProcessed(true);
	}
	
	public void reverseShipped() {
		setProcessed(false);
	}
	
	public BigDecimal getMT() {
		return getNetWeight().divide(new BigDecimal(1000), RoundingMode.HALF_EVEN);
	}
	
	public int getMT_UOM_ID() {
		return MSysConfig.getIntValue("TONNAGE_UOM", 1000069, getAD_Client_ID());
	}
	
	@Override
	public String getPartyName() {
		TF_MBPartner bp = new TF_MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
		if(bp.getIsPOSCashBP())
			return super.getPartyName();
		else
			return null;
	}
			
}
