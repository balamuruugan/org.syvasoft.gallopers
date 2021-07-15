package org.syvasoft.tallyfrontcrusher.factory;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.compiere.model.MInvoice;
import org.compiere.model.MJournalLine;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.syvasoft.tallyfrontcrusher.callout.*;
import org.syvasoft.tallyfrontcrusher.model.MBlastingEntry;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MCrusherKatingEntry;
import org.syvasoft.tallyfrontcrusher.model.MDrillingEntry;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalaryDet;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalaryOld;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalaryIssue;
import org.syvasoft.tallyfrontcrusher.model.MFuelIssue;
import org.syvasoft.tallyfrontcrusher.model.MGenerateTaxInvoice;
import org.syvasoft.tallyfrontcrusher.model.MGenerateTaxInvoiceLine;
import org.syvasoft.tallyfrontcrusher.model.MInterOrgCashTransfer;
import org.syvasoft.tallyfrontcrusher.model.MInvestmentReceipt;
import org.syvasoft.tallyfrontcrusher.model.MJobworkResourceRentEntry;
import org.syvasoft.tallyfrontcrusher.model.MLabourWage;
import org.syvasoft.tallyfrontcrusher.model.MLabourWageIssue;
import org.syvasoft.tallyfrontcrusher.model.MMeterLog;
import org.syvasoft.tallyfrontcrusher.model.MPMSchedule;
import org.syvasoft.tallyfrontcrusher.model.MPermitPurchase;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.MTRTaxInvoice;
import org.syvasoft.tallyfrontcrusher.model.MTRTaxInvoiceLine;
import org.syvasoft.tallyfrontcrusher.model.MTaxInvoice;
import org.syvasoft.tallyfrontcrusher.model.MToken;
import org.syvasoft.tallyfrontcrusher.model.MTripSheet;
import org.syvasoft.tallyfrontcrusher.model.MTripSheetAddionalMeter;
import org.syvasoft.tallyfrontcrusher.model.MTripSheetSalary;
import org.syvasoft.tallyfrontcrusher.model.MTyreAssignment;
import org.syvasoft.tallyfrontcrusher.model.MTyreStatusChange;
import org.syvasoft.tallyfrontcrusher.model.MVehicleRent;
import org.syvasoft.tallyfrontcrusher.model.MVehicleRentConfig;
import org.syvasoft.tallyfrontcrusher.model.MVehicleRentalContract;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.MYardEntry;
import org.syvasoft.tallyfrontcrusher.model.MYardEntryApproveLine;
import org.syvasoft.tallyfrontcrusher.model.TF_MElementValue;
import org.syvasoft.tallyfrontcrusher.model.TF_MInvoice;
import org.syvasoft.tallyfrontcrusher.model.TF_MJournal;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;
import org.syvasoft.tallyfrontcrusher.model.TF_MPayment;
import org.syvasoft.tallyfrontcrusher.model.TF_MRequisitionLine;

public class CrusherColumnCalloutFactory implements IColumnCalloutFactory {

	@Override
	public IColumnCallout[] getColumnCallouts(String tableName,
			String columnName) {
		List<IColumnCallout> list = new ArrayList<IColumnCallout>();
		
		//C_Invoice / C_Order - Calc Header Item Amount
		if((tableName.equals(TF_MInvoice.Table_Name) || tableName.equals(TF_MOrder.Table_Name)) && 
				(columnName.equals(TF_MInvoice.COLUMNNAME_Item1_Qty) || columnName.equals(TF_MInvoice.COLUMNNAME_Item1_Price) ||
				 columnName.equals(TF_MInvoice.COLUMNNAME_Item2_Qty) || columnName.equals(TF_MInvoice.COLUMNNAME_Item2_Price)))
			list.add(new CalloutInvoiceHeaderItemAmount());
		
		if(tableName.equals(TF_MOrder.Table_Name)) {
			if(columnName.equals(TF_MOrder.COLUMNNAME_AD_Org_ID))
				list.add(new CalloutOrder_Org());
		}
		
		//TF_MOrder - Set Cash Payment Rule for POS BP
		if(tableName.equals(TF_MOrder.Table_Name) && columnName.equals(TF_MOrder.COLUMNNAME_C_BPartner_ID))
		{
			list.add(new CalloutOrder_POSCashBP());
		}
		//TF_MOrder / C_Invoice - Set Unit Price
		if((tableName.equals(TF_MOrder.Table_Name) || tableName.equals(TF_MInvoice.Table_Name)) && (columnName.equals(TF_MOrder.COLUMNNAME_Item1_ID)				
				|| columnName.equals(TF_MOrder.COLUMNNAME_Item2_ID)  )) {
			list.add(new CalloutOrderQuickEntry_SetPrice());			
		}
		
		//Set Price based on Selected UOM
		if((tableName.equals(TF_MOrder.Table_Name) || tableName.equals(TF_MInvoice.Table_Name)) && (columnName.equals(TF_MOrder.COLUMNNAME_Item1_ID)				
				|| columnName.equals(TF_MOrder.COLUMNNAME_Item2_ID) || columnName.equals(TF_MOrder.COLUMNNAME_Item1_UOM_ID) 
				|| columnName.equals(TF_MOrder.COLUMNNAME_Item2_UOM_ID) || columnName.equals(TF_MOrder.COLUMNNAME_DateAcct)
				|| columnName.equals(TF_MOrder.COLUMNNAME_C_BPartner_ID) )) {			
			list.add(new CalloutOrderQuickEntry_SetPriceUOM());
		}
		
		//TF_MOrder - Set Vehicle No
		if(tableName.equals(TF_MOrder.Table_Name) && columnName.equals(TF_MOrder.COLUMNNAME_Vehicle_ID))
				list.add(new CalloutOrderQuickEntry_SetVehicleNo());
				
		if(tableName.equals(MTripSheet.Table_Name)) {
			//TF_TripSheet - Calc Running Meter
			if(columnName.equals(MTripSheet.COLUMNNAME_Opening_Meter) || 
					columnName.equals(MTripSheet.COLUMNNAME_Closing_Meter)) {
				list.add(new CalloutTripSheetRunningMeter());
				list.add(new CalloutTripSheet_CalcManualMeter());
				list.add(new CalloutTripsheet_CalcRentAmt());
				list.add(new CalloutTripSheet_CalcIncentive());
			}
			
			if(columnName.equals(MTripSheet.COLUMNNAME_NoOfLoad) || 
					columnName.equals(MTripSheet.COLUMNNAME_TotalMT) ||
					columnName.equals(MTripSheet.COLUMNNAME_TonnagePerLoad) || 
					columnName.equals(MTripSheet.COLUMNNAME_Running_Meter)) { 
				list.add(new CalloutTripSheet_CalcManualMeter());
				list.add(new CalloutTripsheet_CalcRentAmt());
				list.add(new CalloutTripSheet_CalcIncentive());
			}
			
			//TF_TripSheet - Calc Running Meter
			if(columnName.equals(MTripSheet.COLUMNNAME_Opening_Fuel) || 
					columnName.equals(MTripSheet.COLUMNNAME_Closing_Fuel) || columnName.equals(MTripSheet.COLUMNNAME_Received_Fuel) )
				list.add(new CalloutTripSheetFuelExpensed());
			
			//TF_TripSheet - Set Opening Meter / Fuel
			if(columnName.equals(MTripSheet.COLUMNNAME_Vehicle_ID))
				list.add(new CalloutTripSheetOpeningEntries());
			
			//TF_TripSheet - Set Rental Contract 
			if(columnName.equals(MTripSheet.COLUMNNAME_Vehicle_ID))
				list.add(new CalloutTripSheetRentalContract());
			
			//TF_TripSheet - Set Subcontract / Jobwork 
			if(columnName.equals(MTripSheet.COLUMNNAME_Vehicle_ID))
				list.add(new CalloutTripSheetJobwork());
			
			if(columnName.equals(MTripSheet.COLUMNNAME_PM_Machinery_ID))
				list.add(new CalloutTripsheet_Vehicle());
			
			if(columnName.equals(MTripSheet.COLUMNNAME_Rate) || 
					columnName.equals(MTripSheet.COLUMNNAME_Opening_Meter) || 
					columnName.equals(MTripSheet.COLUMNNAME_Closing_Meter) || 
					columnName.equals(MTripSheet.COLUMNNAME_Rent_UOM_ID)) 
				list.add(new CalloutTripsheet_CalcRentAmt());
			
			if(columnName.equals(MTripSheet.COLUMNNAME_C_UOM_ID) ||
					columnName.equals(MTripSheet.COLUMNNAME_C_BPartner_ID)) {
				list.add(new CalloutTripSheet_SetIncentiveRules());
				list.add(new CalloutTripSheet_CalcIncentive());
			}
			
			if(columnName.equals(MTripSheet.COLUMNNAME_PM_Machinery_ID) || 
					columnName.equals(MTripSheet.COLUMNNAME_JobWork_Product_ID) || 
					columnName.equals(MTripSheet.COLUMNNAME_Rent_UOM_ID))
				list.add(new CalloutTripSheet_SetMachineryRentInfo());
			
		}
		
		if(tableName.equals(TF_MOrderLine.Table_Name)) {
			if(columnName.equals(TF_MOrderLine.COLUMNNAME_M_Product_ID) || 
					columnName.equals(TF_MOrderLine.COLUMNNAME_C_UOM_ID)) {
				list.add(new CalloutOrderLine_SetPriceUOM());
			}
		}
		
		if(tableName.equals(MTripSheetSalary.Table_Name)) {
			if(columnName.equals(MTripSheetSalary.COLUMNNAME_C_BPartner_ID)) {
				list.add(new CalloutTripSheet_SetIncentiveRules());
				list.add(new CalloutTripSheet_CalcIncentive());
			}
		}
		
		if(tableName.equals(MTripSheetAddionalMeter.Table_Name)) {
			if(columnName.equals(MTripSheetAddionalMeter.COLUMNNAME_PM_Machinery_ID) || 
					columnName.equals(MTripSheetAddionalMeter.COLUMNNAME_C_UOM_ID)) {
				list.add(new CalloutTripSheetAM_SetRate());
				list.add(new CalloutTripSheetAM_CalcAmount());
			}
			
			if(columnName.equals(MTripSheetAddionalMeter.COLUMNNAME_Opening_Meter) || 
					columnName.equals(MTripSheetAddionalMeter.COLUMNNAME_Closing_Meter) ||
					columnName.equals(MTripSheetAddionalMeter.COLUMNNAME_Running_Meter) ) {
				list.add(new CalloutTripSheetRunningMeter());
				list.add(new CalloutTripSheetAM_CalcAmount());
			}
				
		}
		
		//C_Payment - Cash Type
		//if(tableName.equals(MPayment.Table_Name) && (columnName.equals("CashType")))
		//	list.add(new CalloutPaymentCashType());
		if(tableName.equals(MPayment.Table_Name)) { 
			if(columnName.equals(TF_MPayment.COLUMNNAME_TF_BPartner_ID)) 
				list.add(new CalloutPayment_TFBPartner());
			if(columnName.equals(TF_MPayment.COLUMNNAME_C_ElementValue_ID) || columnName.equals(TF_MPayment.COLUMNNAME_DateTrx)) {
				list.add(new CalloutPayment_ElementValue());
				list.add(new CalloutPayment_CalcSalaryBalannceAmts());
			}
			if(columnName.equals(TF_MPayment.COLUMNNAME_Advance_Deduct) || columnName.equals(TF_MPayment.COLUMNNAME_PayAmt))
				list.add(new CalloutPayment_CalcSalaryBalannceAmts());
			if(columnName.equals(TF_MPayment.COLUMNNAME_C_DocType_ID))
				list.add(new CalloutPayment_DocumentType());
			if(columnName.equals(TF_MPayment.COLUMNNAME_AD_Org_ID))
				list.add(new CalloutPayment_Org());
			if(columnName.equals(TF_MPayment.COLUMNNAME_FromTo_BankAccount_ID) || 
					columnName.equals(TF_MPayment.COLUMNNAME_IsInterCashBookEntry) || 
					columnName.equals(TF_MPayment.COLUMNNAME_C_DocType_ID))
				list.add(new CalloutPayment_FromToBankAccount());
		}
		//TF_Employee_Salary - Load Salary Config
		if(tableName.equals(MEmployeeSalaryOld.Table_Name)) {
			if((columnName.equals(MEmployeeSalaryOld.COLUMNNAME_C_BPartner_ID)
				|| columnName.equals(MEmployeeSalaryOld.COLUMNNAME_DateAcct) || columnName.equals(MEmployeeSalaryOld.COLUMNNAME_Present_Days)
				|| columnName.equals(MEmployeeSalaryOld.COLUMNNAME_IsCalculated) 
				)) {
				list.add(new CalloutEmployeeSalary());
			}
			
			if(columnName.equals(MEmployeeSalaryOld.COLUMNNAME_C_BPartner_ID)) {
				list.add(new CalloutEmployeeSalary_BPartner());
			}
		}
		//TF_Labour_Wage - Load Wage Config
		if(tableName.equals(MLabourWage.Table_Name) && (columnName.equals(MLabourWage.COLUMNNAME_C_BPartner_ID) ||
				columnName.equals(MLabourWage.COLUMNNAME_DateAcct) || columnName.equals(MLabourWage.COLUMNNAME_Present_Days) || 
				columnName.equals(MLabourWage.COLUMNNAME_TF_VehicleType_ID) || columnName.equals(MLabourWage.COLUMNNAME_Incentive)))
			list.add(new CalloutLabourWage());
		
		//TF_Labour_Wage_Issue - Load Earned Wage and Advance Paid
		if(tableName.equals(MLabourWageIssue.Table_Name) && (columnName.equals(MLabourWageIssue.COLUMNNAME_DateAcct) ||
				columnName.equals(MLabourWageIssue.COLUMNNAME_C_BPartner_ID)))
			list.add(new CalloutLabourWageIssue_SetOpenAmt());
		
		//TF_Labour_Wage_Issue - Calculate Balance amounts
		if(tableName.equals(MLabourWageIssue.Table_Name) && (columnName.equals(MLabourWageIssue.COLUMNNAME_Advance_Deduct) ||
				columnName.equals(MLabourWageIssue.COLUMNNAME_Wages_Payable) || columnName.equals(MLabourWageIssue.COLUMNNAME_Wages_Paid)))
			list.add(new CalloutLabourWageIssue_CalcBalanceAmts());
		
		//TF_Employee_Salary_Issue - Load Earned Salary and Advance Paid
		if(tableName.equals(MEmployeeSalaryIssue.Table_Name) && (columnName.equals(MEmployeeSalaryIssue.COLUMNNAME_DateAcct) ||
				columnName.equals(MEmployeeSalaryIssue.COLUMNNAME_C_BPartner_ID)))
			list.add(new CalloutEmployeeSalaryIssue_SetOpenAmt());
				
		//TF_Employee_Salary_Issue - Calculate Balance amounts
		if(tableName.equals(MEmployeeSalaryIssue.Table_Name) && (columnName.equals(MEmployeeSalaryIssue.COLUMNNAME_Advance_Deduct) ||
				columnName.equals(MEmployeeSalaryIssue.COLUMNNAME_Loan_Deduct) ||
				columnName.equals(MEmployeeSalaryIssue.COLUMNNAME_Salary_Paid) || columnName.equals(MEmployeeSalaryIssue.COLUMNNAME_Salary_Payable)))
			list.add(new CalloutEmployeeSalaryIssue_CalcBalanceAmts());
		
		//TF_Vehicle_Rental_Contract - Vehicle No
		if(tableName.equals(MVehicleRentalContract.Table_Name) && columnName.equals(MVehicleRentalContract.COLUMNNAME_VehicleNo))
			list.add(new CalloutRentalContract_VehicleNo());
		
		//TF_Vehicle_Rental_Contract - Resource Type
		if(tableName.equals(MVehicleRentalContract.Table_Name) && columnName.equals(MVehicleRentalContract.COLUMNNAME_S_ResourceType_ID))
			list.add(new CalloutRentalContract_ResourceType());
		
		//TF_Boulder Receipt - Subcontract / Jobwork
		if(tableName.equals(MBoulderReceipt.Table_Name)) {
			if(columnName.equals(MBoulderReceipt.COLUMNNAME_C_Project_ID)) {
				list.add(new CalloutBoulderReceipt_JobWork());
				list.add(new CalloutBoulderReceipt_Warehouse());
			}
			if(columnName.equals(MBoulderReceipt.COLUMNNAME_M_Warehouse_ID))
				list.add(new CalloutBoulderReceipt_Warehouse());
			if(columnName.equals(MBoulderReceipt.COLUMNNAME_M_Product_ID))
				list.add(new CalloutBoulderReceipt_Product());
		}
		
		//C_Invoice Vendor - Subcontract tamil
		  if(tableName.equals(MInvoice.Table_Name)) {
					if(columnName.equals(MInvoice.COLUMNNAME_C_Project_ID)) {
						list.add(new CalloutInvoice_Subcontract());			
					}
		  }
			
		
		//TF_TyreAssignment - Tyre, Tyre Assignment Type
		if(tableName.equals(MTyreAssignment.Table_Name) && (columnName.equals(MTyreAssignment.COLUMNNAME_TF_Tyre_ID) 
				|| columnName.equals(MTyreAssignment.COLUMNNAME_TyreAssignmentType)))
			list.add(new CalloutTyreAssignment());
		
		//TF_TyreAssignment - Release Tyre Movement
		if(tableName.equals(MTyreAssignment.Table_Name) && columnName.equals(MTyreAssignment.COLUMNNAME_RD_TF_TyreMovement_ID))
			list.add(new CalloutTyreAssignment_ReleaseTyreMovement());
		
		//TF_TyreAssignment - Calc Running Meter
		if(tableName.equals(MTyreAssignment.Table_Name) && (columnName.equals(MTyreAssignment.COLUMNNAME_RD_TF_TyreMovement_ID)
				|| columnName.equals(MTyreAssignment.COLUMNNAME_RD_End_Meter)))
			list.add(new CalloutTyreAssignment_CalcRunningMeter());
		
		//TF_TyreStatusChange - Tyre
		if(tableName.equals(MTyreStatusChange.Table_Name) && columnName.equals(MTyreStatusChange.COLUMNNAME_TF_Tyre_ID))
			list.add(new CalloutTyreStatusChange_Tyre());
		
		//TF_Jobwork_ResRentEntry - Calc Contract Amount
		if(tableName.equals(MJobworkResourceRentEntry.Table_Name) && (columnName.equals(MJobworkResourceRentEntry.COLUMNNAME_Qty))
				|| (columnName.equals(MJobworkResourceRentEntry.COLUMNNAME_Unit_Price)))
				list.add(new CalloutMJobworkResourceRentEntry_CalcContractAmt());
		
		//C_ElementValue - Update Account Type and Account Sign from Account Group.
		if(tableName.equals(TF_MElementValue.Table_Name) && columnName.equals(TF_MElementValue.COLUMNNAME_AccountGroup_ID))
			list.add(new CalloutElementValue_AccountGroup());
		
		//TF_VehicleRent_TajConfig - Get default for destination from Destination Master
		if(tableName.equals(MVehicleRentConfig.Table_Name) && columnName.equals(MVehicleRentConfig.COLUMNNAME_TF_Destination_ID))
			list.add(new CalloutVehicleRentConfig_Destination());
		
		if(tableName.equals(TF_MOrder.Table_Name) && (columnName.equals(TF_MOrder.COLUMNNAME_TF_Destination_ID) ||
				columnName.equals(TF_MOrder.COLUMNNAME_TF_RentedVehicle_ID))) {
			list.add(new CalloutOrder_Destination());
			list.add(new CalloutOrder_RentedVehicle());
			list.add(new CalloutOrder_CalcRentAmount());
			list.add(new CalloutOrder_SOUnitPriceRent());
			list.add(new CalloutOrder_VehicleRent());
		}
		
		if(tableName.equals(TF_MOrder.Table_Name) && (columnName.equals(TF_MOrder.COLUMNNAME_Distance) ||
				columnName.equals(TF_MOrder.COLUMNNAME_Rate) || columnName.equals(TF_MOrder.COLUMNNAME_IsLumpSumRent))) {			
			list.add(new CalloutOrder_CalcRentAmount());
			list.add(new CalloutOrder_SOUnitPriceRent());
		}
		
		if(tableName.equals(TF_MOrder.Table_Name) && columnName.equals(TF_MOrder.COLUMNNAME_M_Warehouse_ID)) {
			list.add(new CalloutOrder_Warehouse());
		}
		
		if(tableName.equals(MWeighmentEntry.Table_Name)) {
			if(columnName.equals(MWeighmentEntry.COLUMNNAME_TareWeight) || 
					columnName.equals(MWeighmentEntry.COLUMNNAME_GrossWeight)) {
						list.add(new CalloutWeighmentEntry_CalcNetWeight());
					}
			if(columnName.equals(MWeighmentEntry.COLUMNNAME_TF_RentedVehicle_ID)) {
				list.add(new CalloutWeighmentEntry_Vehicle());
				list.add(new CalloutOrder_VehicleRent());
			}
			
			if(columnName.equals(MWeighmentEntry.COLUMNNAME_Price) || 
					columnName.equals(MWeighmentEntry.COLUMNNAME_GSTAmount) || 
					columnName.equals(MWeighmentEntry.COLUMNNAME_IsPermitSales) ||
					columnName.equals(MWeighmentEntry.COLUMNNAME_DriverTips)) {
				list.add(new CalloutWeighmentEntry_CalcAmount());
			}
		}
		
		if(tableName.equals(TF_MOrder.Table_Name) && columnName.equals(TF_MOrder.COLUMNNAME_TF_WeighmentEntry_ID)) {
			list.add(new CalloutOrder_WeighmentEntry());			
			list.add(new CalloutOrder_VehicleRent());
			list.add(new CalloutOrder_VehicleType());
			list.add(new CalloutOrder_SOUnitPriceRent());
		}

		if(tableName.equals(TF_MOrder.Table_Name) && columnName.equals(TF_MOrder.COLUMNNAME_Item1_VehicleType_ID)) {
			list.add(new CalloutOrder_VehicleType());
			list.add(new CalloutOrder_Distance());
			list.add(new CalloutOrder_VehicleRent());
		}

		if(tableName.equals(TF_MOrder.Table_Name) && columnName.equals(TF_MOrder.COLUMNNAME_Distance)) {
			list.add(new CalloutOrder_Distance());
			//list.add(new CalloutOrder_VehicleRent());
		}
				
		
		if(tableName.equals(TF_MOrder.Table_Name) && (columnName.equals(TF_MOrder.COLUMNNAME_Item1_Qty)
					|| columnName.equals(TF_MOrder.COLUMNNAME_Item1_ID))) {
			list.add(new CalloutOrder_SetTonnage());
			list.add(new CalloutOrder_CalcRentAmount());
			list.add(new CalloutOrder_SOUnitPriceRent());
			list.add(new CalloutOrder_CalcRentPayable());
		}
		
		if(tableName.equals(TF_MOrder.Table_Name) && columnName.equals(TF_MOrder.COLUMNNAME_Rent_Amt)) {			
			list.add(new CalloutOrder_SOUnitPriceRent());
			list.add(new CalloutOrder_CalcRentPayable());
		}
		
		if(tableName.equals(TF_MOrder.Table_Name) && (columnName.equals(TF_MOrder.COLUMNNAME_IsRentBreakup)
				|| columnName.equals(TF_MOrder.COLUMNNAME_Item1_UnitPrice)
				|| columnName.equals(TF_MOrder.COLUMNNAME_IsRentInclusive)
				|| columnName.equals(TF_MOrder.COLUMNNAME_IsRoyaltyPassInclusive)
				|| columnName.equals(TF_MOrder.COLUMNNAME_IsRoyaltyPassBreakup)) ) {
			list.add(new CalloutOrder_SOUnitPriceRent());
		}
		
		if(tableName.equals(TF_MOrder.Table_Name) && columnName.equals(TF_MOrder.COLUMNNAME_RentMargin)) {
			list.add(new CalloutOrder_CalcRentPayable());
		}
				
		if(tableName.equals(MFuelIssue.Table_Name))  {
			if (columnName.equals(MFuelIssue.COLUMNNAME_Qty)
				|| columnName.equals(MFuelIssue.COLUMNNAME_Rate)) {
				list.add(new CalloutFuelIssue_CalcAmount());
			}
			if(columnName.equals(MFuelIssue.COLUMNNAME_Vehicle_ID) || columnName.equals(MFuelIssue.COLUMNNAME_C_Project_ID)
					|| columnName.equals(MFuelIssue.COLUMNNAME_Account_ID) || columnName.equals(MFuelIssue.COLUMNNAME_M_Product_ID))
				list.add(new CalloutFuelIssue_TypeChange());
			if(columnName.equals(MFuelIssue.COLUMNNAME_IssueType))
				list.add(new CalloutFuelIssue_IssueType());
			if(columnName.equals(MFuelIssue.COLUMNNAME_Vehicle_ID) || columnName.equals(MFuelIssue.COLUMNNAME_DateAcct)) {
				list.add(new CalloutFuelIssue_Vehicle());
				list.add(new CalloutFuelIssue_SetPrice());
			}
			if(columnName.equals(MFuelIssue.COLUMNNAME_M_Product_ID))
				list.add(new CalloutFuelIssue_SetPrice());
			if(columnName.equals(MFuelIssue.COLUMNNAME_M_Locator_ID) || columnName.equals(MFuelIssue.COLUMNNAME_M_Product_ID)) {
				list.add(new CalloutFuelIssue_AvailableQty());
			}
		}
		if(tableName.equals(MInterOrgCashTransfer.Table_Name)) {
			if(columnName.equals(MInterOrgCashTransfer.COLUMNNAME_Dest_Acct_ID))
				list.add(new CalloutInterOrgCash_DestAccount());
			if(columnName.equals(MInterOrgCashTransfer.COLUMNNAME_Src_Org_ID))
				list.add(new CalloutInterOrgCash_SrcOrg());
		}
		
		//if(tableName.equals(TF_MProduct.Table_Name)) {
		//	if(columnName.equals(TF_MProduct.COLUMNNAME_Qty) || columnName.equals(TF_MProduct.COLUMNNAME_Price))
		//		list.add(new CalloutProduct_CalcTotalValue());
		//}
		
		if(tableName.equals(MInvestmentReceipt.Table_Name)) {
			if(columnName.equals(MInvestmentReceipt.COLUMNNAME_TF_Shareholder_ID) || 
					columnName.equals(MInvestmentReceipt.COLUMNNAME_C_BankAccount_ID) ||
					columnName.equals(MInvestmentReceipt.COLUMNNAME_C_ElementValue_ID) ||
					columnName.equals(MInvestmentReceipt.COLUMNNAME_PayAmt) ||
					columnName.equals(MInvestmentReceipt.COLUMNNAME_InvestmentReceiptType))
				list.add(new CalloutInvestmentReceipt_AutoDescription());
		}
		
		if(tableName.equals(TF_MOrder.Table_Name)) {
			if(columnName.equals(TF_MOrder.COLUMNNAME_Item1_BucketQty) ||
					columnName.equals(TF_MOrder.COLUMNNAME_Item2_BucketQty) || 
					columnName.equals(TF_MOrder.COLUMNNAME_TonePerBucket) || 
					columnName.equals(TF_MOrder.COLUMNNAME_Item1_BucketRate) || 
					columnName.equals(TF_MOrder.COLUMNNAME_Item2_BucketRate) ||
					columnName.equals(TF_MOrder.COLUMNNAME_Item2_TonePerBucket) ) {
				list.add(new CalloutOrder_SandBlockQtyPrice());
			}	
			
			if(columnName.equals(TF_MOrder.COLUMNNAME_Item1_IsPermitSales) || 
					columnName.equals(TF_MOrder.COLUMNNAME_C_BPartner_ID) ||
					columnName.equals(TF_MOrder.COLUMNNAME_Item2_BucketQty) ||
					columnName.equals(TF_MOrder.COLUMNNAME_Item1_BucketQty) ||
					columnName.equals(TF_MOrder.COLUMNNAME_M_Warehouse_ID) ||
					columnName.equals(TF_MOrder.COLUMNNAME_Item1_VehicleType_ID)) {
				list.add(new CalloutOrder_SandBlockLine1());
			}
		}
		
		if(tableName.equals(MYardEntry.Table_Name) || tableName.equals(MYardEntryApproveLine.Table_Name)) {
			if(columnName.equals(MYardEntry.COLUMNNAME_TF_VehicleType_ID) || 
					columnName.equals(MYardEntry.COLUMNNAME_C_BPartner_ID)) {
				list.add(new CalloutYardEntry_VehicleType());
				list.add(new CalloutYardEntry_CalcAmount());
			}
			if(columnName.equals(MYardEntry.COLUMNNAME_PermitCancelledQty) ||
					columnName.equals(MYardEntry.COLUMNNAME_PermitIssuedQty) ||
					columnName.equals(MYardEntry.COLUMNNAME_PermitPrice) ||
					columnName.equals(MYardEntry.COLUMNNAME_ExtraBucketQty) ||
					columnName.equals(MYardEntry.COLUMNNAME_ExtraBucketPrice) ||
					columnName.equals(MYardEntry.COLUMNNAME_ExtraBucketQty) ||
					columnName.equals(MYardEntry.COLUMNNAME_WpPrice) ||
					columnName.equals(MYardEntry.COLUMNNAME_TotalLoad))
				list.add(new CalloutYardEntry_CalcAmount());					
			
		}
		
		if(tableName.equals(TF_MJournal.Table_Name)) {
			if(columnName.equals(TF_MJournal.COLUMNNAME_IsDistributeProfit) ||
					columnName.equals(TF_MJournal.COLUMNNAME_DateAcct)	)
				list.add(new CalloutJournal_DistributeProfit());
			if(columnName.equals(TF_MJournal.COLUMNNAME_IsQuickEntry)) {
				list.add(new CalloutJournal_QuickEntryMode());
			}
			if(columnName.equals(TF_MJournal.COLUMNNAME_TF_CreditAcct_ID) || 
					columnName.equals(TF_MJournal.COLUMNNAME_TF_DebitAcct_ID) ) {
				list.add(new CalloutJournal_SetJobWork());
			}				
		}
		if(tableName.equals(MJournalLine.Table_Name)) {
			if(columnName.equals(MJournalLine.COLUMNNAME_Account_ID)) {
				list.add(new CalloutJournal_SetJobWork());
			}
		}
		
		if(tableName.equals(MVehicleRent.Table_Name)) {
			if(columnName.equals(MVehicleRent.COLUMNNAME_Vehicle_ID)) {
				list.add(new CalloutVehicleRent_Vehicle());
				list.add(new CalloutVehicleRent_CalcAmount());
			}
			if(columnName.equals(MVehicleRent.COLUMNNAME_Qty) || 
					columnName.equals(MVehicleRent.COLUMNNAME_Price)) {			
				list.add(new CalloutVehicleRent_CalcAmount());
			}
		}
		
		if(tableName.equals(TF_MOrder.Table_Name)) {
			if(columnName.equals(TF_MOrder.COLUMNNAME_C_BPartner_ID)) {
				list.add(new CalloutOrder_SetProject());
			}
		}
		
		if(tableName.equals(MCrusherKatingEntry.Table_Name)) {
			if(columnName.equals(MCrusherKatingEntry.COLUMNNAME_AD_Org_ID)) { 
				list.add(new CalloutCrusherKatingEntry_SetPrice());
			}
			if(columnName.equals(MCrusherKatingEntry.COLUMNNAME_TF_WeighmentEntry_ID)) { 
				list.add(new CalloutCrusherKatingEntry_WeighmentEntry());
				list.add(new CalloutCrusherKatingEntry_SetPrice());
				list.add(new CalloutCrusherKatingEntry_CalcAmount());
			}
			if(columnName.equals(MCrusherKatingEntry.COLUMNNAME_TF_RentedVehicle_ID) || 
					columnName.equals(MCrusherKatingEntry.COLUMNNAME_LoaderVehicle_ID) ||
					columnName.equals(MCrusherKatingEntry.COLUMNNAME_KatingEntryType)) {				
				list.add(new CalloutCrusherKatingEntry_CalcAmount());
			}			
		}
		
		if(tableName.equals(MCrusherKatingEntry.Table_Name)) {
			if(columnName.equals(MCrusherKatingEntry.COLUMNNAME_Tonnage) ||
					columnName.equals(MCrusherKatingEntry.COLUMNNAME_TotalLoad) ||
					columnName.equals(MCrusherKatingEntry.COLUMNNAME_Loading_Price) ||
					columnName.equals(MCrusherKatingEntry.COLUMNNAME_Transport_Price)) {
				list.add(new CalloutCrusherKatingEntry_CalcAmount());				
			}
		}
		
		if(tableName.equals(MPermitPurchase.Table_Name)) {
			if(columnName.equals(MPermitPurchase.COLUMNNAME_PermitQty) ||
					columnName.equals(MPermitPurchase.COLUMNNAME_Price)) 
				list.add(new CalloutPermitPurchase_CalcAmount());
			if(columnName.equals(MPermitPurchase.COLUMNNAME_TF_Quarry_ID))
				list.add(new CalloutPermitPurchase_Quarry());
		}
		
		if(tableName.equals(MTaxInvoice.Table_Name)) {
			if(columnName.equals(MTaxInvoice.COLUMNNAME_M_Product_ID)) {
				list.add(new CalloutTaxInvoice_Product());
			}
			if(columnName.equals(MTaxInvoice.COLUMNNAME_Qty) ||
					columnName.equals(MTaxInvoice.COLUMNNAME_Price) ||
					columnName.equals(MTaxInvoice.COLUMNNAME_CGST_Rate) || 
					columnName.equals(MTaxInvoice.COLUMNNAME_SGST_Rate) ||
					columnName.equals(MTaxInvoice.COLUMNNAME_IGST_Rate) ||
					columnName.equals(MTaxInvoice.COLUMNNAME_RoundingOff))
				list.add(new CalloutTaxInvoice_CalcAmount());
		}
		
		if(tableName.equals(MTRTaxInvoice.Table_Name) || tableName.equals(MGenerateTaxInvoice.Table_Name)) {
			if(columnName.equals(MTRTaxInvoice.COLUMNNAME_RoundOff)) {
				list.add(new CalloutTRTaxInvoice_CalTotalAmt());
			}
			
		}
		
		if(tableName.equals(MTRTaxInvoiceLine.Table_Name) || tableName.equals(MGenerateTaxInvoiceLine.Table_Name)) {
			if(columnName.equals(MTRTaxInvoiceLine.COLUMNNAME_Qty) ||
					columnName.equals(MTRTaxInvoiceLine.COLUMNNAME_Price) ||
					columnName.equals(MTRTaxInvoiceLine.COLUMNNAME_SGST_Rate) ||
					columnName.equals(MTRTaxInvoiceLine.COLUMNNAME_CGST_Rate) ||
					columnName.equals(MTRTaxInvoiceLine.COLUMNNAME_IGST_Rate)) {
				list.add(new CalloutTRTaxInvoiceLine_CalcAmount());
			}
			
		}
		
		if(tableName.equals(MPriceListUOM.Table_Name)) {
			if(columnName.equals(MPriceListUOM.COLUMNNAME_C_BPartner_ID) ||
					columnName.equals(MPriceListUOM.COLUMNNAME_M_Product_ID) )
				list.add(new CalloutPriceList_BPartner());
		}
		
		if(tableName.equals(MPriceListUOM.Table_Name)) {
			if(columnName.equals(MPriceListUOM.COLUMNNAME_C_BPartner_ID) ||
					columnName.equals(TF_MOrder.COLUMNNAME_TF_WeighmentEntry_ID) ||
					columnName.equals(TF_MOrder.COLUMNNAME_TF_Token_ID) ) {
				list.add(new CalloutOrder_CreateTaxInvoice());
			}
		}
		
		if(tableName.equals(TF_MOrder.Table_Name)) {
			if(columnName.equals(TF_MOrder.COLUMNNAME_C_BPartner_ID) || 
					columnName.equals(TF_MOrder.COLUMNNAME_Item1_ID) ||
					columnName.equals(TF_MOrder.COLUMNNAME_IsTaxIncluded1) ||
					columnName.equals(TF_MOrder.COLUMNNAME_TF_Destination_ID) ||
					columnName.equals(TF_MOrder.COLUMNNAME_TF_RentedVehicle_ID) ||
					columnName.equals(TF_MOrder.COLUMNNAME_TF_WeighmentEntry_ID) ||
					columnName.equals(TF_MOrder.COLUMNNAME_TF_Token_ID)) {
				list.add(new CalloutOrder_PriceIncludesTax());
			}
		}
		if(tableName.equals(TF_MOrder.Table_Name)) {
			if(columnName.equals(TF_MOrder.COLUMNNAME_C_BPartner_ID) || 
					columnName.equals(TF_MOrder.COLUMNNAME_Item1_ID) ||
					columnName.equals(TF_MOrder.COLUMNNAME_Item1_Tax_ID) ||
					columnName.equals(TF_MOrder.COLUMNNAME_Item1_UnitPrice) ||
					columnName.equals(TF_MOrder.COLUMNNAME_IsRentInclusive) ||
					columnName.equals(TF_MOrder.COLUMNNAME_IsTaxIncluded1) ||
					columnName.equals(TF_MOrder.COLUMNNAME_Item1_UOM_ID) ||
					columnName.equals(TF_MOrder.COLUMNNAME_TF_Destination_ID) ||
					columnName.equals(TF_MOrder.COLUMNNAME_TF_RentedVehicle_ID) || 
					columnName.equals(TF_MOrder.COLUMNNAME_Distance) ||
					columnName.equals(TF_MOrder.COLUMNNAME_Rate) || 
					columnName.equals(TF_MOrder.COLUMNNAME_IsLumpSumRent) ||
					columnName.equals(TF_MOrder.COLUMNNAME_TF_WeighmentEntry_ID) ||
					columnName.equals(TF_MOrder.COLUMNNAME_TF_Token_ID) ||
					columnName.equals(TF_MOrder.COLUMNNAME_Rent_Amt)) {
				list.add(new CalloutOrder_Item1Tax());
			}
		}
		if(tableName.equals(TF_MOrder.Table_Name)) {
			if(columnName.equals(TF_MOrder.COLUMNNAME_Item1_UnitPrice)) {
				list.add(new CalloutOrder_UnitPrice());
			}
		}
		if(tableName.equals(MOrder.Table_Name)) {
			if(columnName.equals(TF_MOrder.COLUMNNAME_TF_Token_ID)) {
				list.add(new CalloutOrder_TokenNo());
				list.add(new CalloutOrder_SOUnitPriceRent());
				list.add(new CalloutOrder_VehicleRent());
				list.add(new CalloutOrder_VehicleType());
			}
		}
		
		if(tableName.equals(MToken.Table_Name)) {
			if(columnName.equals(MToken.COLUMNNAME_M_Product_ID))
				list.add(new CalloutToken_SetUOM());
		}

		if(tableName.equals(MDrillingEntry.Table_Name)) {
			if(columnName.equals(MDrillingEntry.COLUMNNAME_Holes) ||
					columnName.equals(MDrillingEntry.COLUMNNAME_Feet) ||
					columnName.equals(MDrillingEntry.COLUMNNAME_C_UOM_ID) ||
					columnName.equals(MDrillingEntry.COLUMNNAME_FeetRate))
				list.add(new CalloutDrillingEntry_CalcDrillingCost());
		}
		
		if(tableName.equals(MPMSchedule.Table_Name)) {
			if(columnName.equals(MPMSchedule.COLUMNNAME_ScheduleType) ||
					columnName.equals(MPMSchedule.COLUMNNAME_PM_Period_ID) ||
					columnName.equals(MPMSchedule.COLUMNNAME_DateLastRun) ||
					columnName.equals(MPMSchedule.COLUMNNAME_Interval) ||
					columnName.equals(MPMSchedule.COLUMNNAME_C_UOM_ID) ||
					columnName.equals(MPMSchedule.COLUMNNAME_LastMeter) ||
					columnName.equals(MPMSchedule.COLUMNNAME_Interval))
				list.add(new CalloutPMSchedule_TypeChange());
			
			if(columnName.equals(MPMSchedule.COLUMNNAME_PM_Machinery_ID))
				list.add(new CalloutPM_SetMachineryType());
		}
		
		//Set Price based on Selected UOM
		if((tableName.equals(TF_MRequisitionLine.Table_Name)) && (columnName.equals(TF_MRequisitionLine.COLUMNNAME_M_Product_ID)				
				|| columnName.equals(TF_MRequisitionLine.COLUMNNAME_C_UOM_ID) || columnName.equals(TF_MRequisitionLine.COLUMNNAME_C_BPartner_ID) )) {			
			list.add(new CalloutRequisition_SetPriceUOM());
		}
		
		if((tableName.equals(MEmployeeSalaryDet.Table_Name)) && (columnName.equals(MEmployeeSalaryDet.COLUMNNAME_C_BPartner_ID))) {			
			list.add(new CalloutEmployeeSalary_BPDetails());
		}
		
		if((tableName.equals(MEmployeeSalaryDet.Table_Name)) && (columnName.equals(MEmployeeSalaryDet.COLUMNNAME_Absentees))) {			
			list.add(new CalloutEmployeeSalary_PresentDays());
		}
		
		if((tableName.equals(MEmployeeSalaryDet.Table_Name)) && (columnName.equals(MEmployeeSalaryDet.COLUMNNAME_Salary)				
				|| columnName.equals(MEmployeeSalaryDet.COLUMNNAME_NoOfDays) || columnName.equals(MEmployeeSalaryDet.COLUMNNAME_Absentees))) {			
			list.add(new CalloutEmployeeSalary_SalaryDue());
		}
		
		if((tableName.equals(MEmployeeSalaryDet.Table_Name)) && (columnName.equals(MEmployeeSalaryDet.COLUMNNAME_Salary)	
				|| columnName.equals(MEmployeeSalaryDet.COLUMNNAME_Absentees)
				|| columnName.equals(MEmployeeSalaryDet.COLUMNNAME_NoOfDays) || columnName.equals(MEmployeeSalaryDet.COLUMNNAME_MessAdvance) 
				|| columnName.equals(MEmployeeSalaryDet.COLUMNNAME_DeductAdvance) || columnName.equals(MEmployeeSalaryDet.COLUMNNAME_SalaryWithheld))) {			
			list.add(new CalloutEmployeeSalary_NetSalary());
		}
		
				
		if((tableName.equals(MMeterLog.Table_Name) || tableName.equals(MTripSheetAddionalMeter.Table_Name)) && (columnName.equals(MMeterLog.COLUMNNAME_PM_Machinery_ID)				
				|| columnName.equals(MMeterLog.COLUMNNAME_C_UOM_ID))) {			
			list.add(new CalloutMeterLog_SetOpeningMeter());
		}

		if(tableName.equals(MBlastingEntry.Table_Name)) {
			if(columnName.equals(MBlastingEntry.COLUMNNAME_M_Locator_ID) || columnName.equals(MBlastingEntry.COLUMNNAME_M_Product_ID)) {
				list.add(new CalloutBlastingEntry_SetPriceUom());
				list.add(new CalloutBlastingEntry_AvailableQty());
			}
		}
		return list != null ? list.toArray(new IColumnCallout[0]) : new IColumnCallout[0];
	}
}
