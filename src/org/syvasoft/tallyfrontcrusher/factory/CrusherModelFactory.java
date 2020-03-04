package org.syvasoft.tallyfrontcrusher.factory;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.base.IModelFactory;
import org.compiere.model.MProductionPlan;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.*;

public class CrusherModelFactory implements IModelFactory {

	@Override
	public Class<?> getClass(String tableName) {
		if (MBoulderReceipt.Table_Name.equals(tableName))
			return MBoulderReceipt.class;
		else if (MQuarry.Table_Name.equals(tableName))
			return MQuarry.class;
		else if (MEmpSalaryConfig.Table_Name.equals(tableName))
			return MEmpSalaryConfig.class;
		else if (MEmployeeSalary.Table_Name.equals(tableName))
			return MEmployeeSalary.class;		
		else if (MVehicleRent.Table_Name.equals(tableName))
			return MVehicleRent.class;
		else if (MQuarryRentConfig.Table_Name.equals(tableName))
			return MQuarryRentConfig.class;
		else if (MQuarryRent.Table_Name.equals(tableName))
			return MQuarryRent.class;
		else if (MVehicleType.Table_Name.equals(tableName))
			return MVehicleType.class;
		else if (MGLPostingConfig.Table_Name.equals(tableName))
			return MGLPostingConfig.class;
		else if (MCrusherProduction.Table_Name.equals(tableName))
			return MCrusherProduction.class;
		else if (MCrusherProductionConfig.Table_Name.equals(tableName))
			return MCrusherProductionConfig.class;
		else if (TF_MInvoice.Table_Name.equals(tableName))
			return TF_MInvoice.class;
		else if (TF_MOrder.Table_Name.equals(tableName))			
			return TF_MOrder.class;
		else if (TF_MOrderLine.Table_Name.equals(tableName))			
			return TF_MOrderLine.class;
		else if (MFuelIssue.Table_Name.equals(tableName))
			return MFuelIssue.class;
		else if (MLabourWage.Table_Name.equals(tableName))
			return MLabourWage.class;
		else if (MLabourWageConfig.Table_Name.equals(tableName))			
			return MLabourWageConfig.class;
		else if (TF_MPayment.Table_Name.equals(tableName))
			return TF_MPayment.class;
		else if (TF_MCharge.Table_Name.equals(tableName))
			return TF_MCharge.class;
		else if (MLabourWageAdvance.Table_Name.equals(tableName))
			return MLabourWageAdvance.class;
		else if (MEmployeeSalaryAdvance.Table_Name.equals(tableName))
			return MEmployeeSalaryAdvance.class;
		else if (MLabourWageIssue.Table_Name.equals(tableName))
			return MLabourWageIssue.class;
		else if (MEmployeeSalaryIssue.Table_Name.equals(tableName))
			return MEmployeeSalaryIssue.class;
		else if (TF_MResourceType.Table_Name.equals(tableName))
			return TF_MResourceType.class;
		else if (TF_MResource.Table_Name.equals(tableName))
			return TF_MResource.class;
		else if (MVehicleRentalContract.Table_Name.equals(tableName))
			return MVehicleRentalContract.class;
		else if (MTripSheet.Table_Name.equals(tableName))
			return MTripSheet.class;
		else if (TF_MProject.Table_Name.equals(tableName))
			return TF_MProject.class;
		else if (MJobworkCharges.Table_Name.equals(tableName))
			return MJobworkCharges.class;
		else if (MJobworkIssuedItems.Table_Name.equals(tableName))
			return MJobworkIssuedItems.class;
		else if (MJobworkIssuedResource.Table_Name.equals(tableName))
			return MJobworkIssuedResource.class;
		else if (MJobworkProductPrice.Table_Name.equals(tableName))
			return MJobworkProductPrice.class;
		else if (MJobworkReceivedItems.Table_Name.equals(tableName))
			return MJobworkReceivedItems.class;
		else if (MJobworkItemIssue.Table_Name.equals(tableName))
			return MJobworkItemIssue.class;
		else if (MJobworkExpense.Table_Name.equals(tableName))
			return MJobworkExpense.class;
		else if (MJobworkExpenseEntry.Table_Name.equals(tableName))
			return MJobworkExpenseEntry.class;
		else if (MItemReceiptOtherSrc.Table_Name.equals(tableName))
			return MItemReceiptOtherSrc.class;
		else if (MTyre.Table_Name.equals(tableName))
			return MTyre.class;
		else if (MTyreType.Table_Name.equals(tableName))
			return MTyreType.class;
		else if (MTyreStatus.Table_Name.equals(tableName))
			return MTyreStatus.class;
		else if (MTyrePosition.Table_Name.equals(tableName))
			return MTyrePosition.class;
		else if (MTyreAssignment.Table_Name.equalsIgnoreCase(tableName))
			return MTyreAssignment.class;
		else if (MTyreStatusChange.Table_Name.equals(tableName))
			return MTyreStatusChange.class;
		else if (MTyreLife.Table_Name.equals(tableName))
			return MTyreLife.class;
		else if (MJobworkResourceRentEntry.Table_Name.equals(tableName))
			return MJobworkResourceRentEntry.class;
		else if (TF_MElementValue.Table_Name.equals(tableName))
			return TF_MElementValue.class;
		else if (TF_MBankAccount.Table_Name.equals(tableName))
			return TF_MBankAccount.class;
		else if (TF_MBPartner.Table_Name.equals(tableName)) 
			return TF_MBPartner.class;
		else if (MDestination.Table_Name.equals(tableName)) 
			return MDestination.class;
		else if (MRentedVehicle.Table_Name.equals(tableName)) 
			return MRentedVehicle.class;
		else if (MVehicleRentConfig.Table_Name.equals(tableName)) 
			return MVehicleRentConfig.class;
		else if (MWeighmentEntry.Table_Name.equals(tableName)) 
			return MWeighmentEntry.class;
		else if (TF_MJournal.Table_Name.equals(tableName)) 
			return TF_MJournal.class;
		else if (MCOAOpeningBalance.Table_Name.equals(tableName))
			return MCOAOpeningBalance.class;
		else if (MBPOpeningBalance.Table_Name.equals(tableName))
			return MBPOpeningBalance.class;
		else if (MInterOrgCashTransfer.Table_Name.equals(tableName))
			return MInterOrgCashTransfer.class;
		else if (TF_MProduct.Table_Name.equals(tableName))
			return TF_MProduct.class;
		else if (MSubcontractMaterialMovement.Table_Name.equals(tableName))
			return MSubcontractMaterialMovement.class;
		else if (MShareholderType.Table_Name.equals(tableName))
			return MShareholderType.class;
		else if (MShareholder.Table_Name.equals(tableName))
			return MShareholder.class;
		else if (MInvestmentStructure.Table_Name.equals(tableName))
			return MInvestmentStructure.class;
		else if (MInvestmentReceipt.Table_Name.equals(tableName))
			return MInvestmentReceipt.class;
		//else if (MHomePageShortcuts.Table_Name.equals(tableName))
		//	return MHomePageShortcuts.class;
		else if(MSubcontractType.Table_Name.equals(tableName))
			return MSubcontractType.class;
		else if(MWeighmentErrorLog.Table_Name.equals(tableName))
			return MWeighmentErrorLog.class;
		else if(MSandBlockBucketConfig.Table_Name.equals(tableName))
			return MSandBlockBucketConfig.class;
		else if(MPermitLedger.Table_Name.equals(tableName))
			return MPermitLedger.class;
		else if(MPermitLedgerLine.Table_Name.equals(tableName))
			return MPermitLedgerLine.class;
		else if(MYardEntry.Table_Name.equals(tableName))
			return MYardEntry.class;
		else if(MYardEntryConfig.Table_Name.equals(tableName))
			return MYardEntryConfig.class;
		else if(MJobworkAssignedEmployee.Table_Name.equals(tableName))
			return MJobworkAssignedEmployee.class;
		else if(MJobworkAssignedBPartner.Table_Name.equals(tableName))
			return MJobworkAssignedBPartner.class;
		else if(MJobworkAssignedAccount.Table_Name.equals(tableName))
			return MJobworkAssignedAccount.class;
		else if(MJobworkAssignedVehicle.Table_Name.equals(tableName))
			return MJobworkAssignedVehicle.class;
		else if(MKatingEntry.Table_Name.equals(tableName))
			return MKatingEntry.class;
		else if(MInterOrgCashTransferConfigLine.Table_Name.equals(tableName)) 
			return MInterOrgCashTransferConfigLine.class;
		else if(MInterOrgBPCashTransferConfig.Table_Name.equals(tableName))
			return MInterOrgBPCashTransferConfig.class;
		else if(MInterOrgBPCashTransferConfigLine.Table_Name.equals(tableName))
			return MInterOrgBPCashTransferConfigLine.class;	
		else if(MYardLoadConfig.Table_Name.equals(tableName))
			return MYardLoadConfig.class;
		else if(MYardCustomerVehicle.Table_Name.equals(tableName))
			return MYardCustomerVehicle.class;
		else if(MYardLoadEntry.Table_Name.equals(tableName))
			return MYardLoadEntry.class;
		else if(MYardPermitIssueEntry.Table_Name.equals(tableName))
			return MYardPermitIssueEntry.class;
		else if(MYardEntryApprove.Table_Name.equals(tableName))
			return MYardEntryApprove.class;
		else if(MYardEntryApproveLine.Table_Name.equals(tableName))
			return MYardEntryApproveLine.class;
		else if(MCrusherPermitLedger.Table_Name.equals(tableName))
			return MCrusherPermitLedger.class;
		else if(MCrusherPermitLedgerLine.Table_Name.equals(tableName))
			return MCrusherPermitLedgerLine.class;
		else if(MTaxInvoice.Table_Name.equals(tableName))
			return MTaxInvoice.class;
		else if(MPermitPurchase.Table_Name.equals(tableName))
			return MPermitPurchase.class;
		else if(MPermitPurchaseLine.Table_Name.equals(tableName))
			return MPermitPurchaseLine.class;
		else if(MWeighmentPermitEntry.Table_Name.equals(tableName))
			return MWeighmentPermitEntry.class;
		else if(MCrusherKatingConfig.Table_Name.equals(tableName))
			return MCrusherKatingConfig.class;
		else if(MDriverBetaConfig.Table_Name.equals(tableName))
			return MDriverBetaConfig.class;
		else if(MLumpSumRentConfig.Table_Name.equals(tableName))
			return MLumpSumRentConfig.class;
		else if(MDebitCreditNote.Table_Name.equals(tableName))
			return MDebitCreditNote.class;
		else if(MProductionPlant.Table_Name.equals(tableName))
			return MProductionPlant.class;
		else if(MPriceListUOM.Table_Name.equals(tableName))			
			return MPriceListUOM.class;
		else if(MSmsNotification.Table_Name.equals(tableName))			
			return MSmsNotification.class;
		else if(MSmsReceipient.Table_Name.equals(tableName))			
			return MSmsReceipient.class;
		else if(MTRTaxInvoice.Table_Name.equals(tableName))			
			return MTRTaxInvoice.class;
		else if(MTRTaxInvoiceLine.Table_Name.equals(tableName))			
			return MTRTaxInvoiceLine.class;
		else if(MCounterTransactionSetup.Table_Name.equals(tableName))			
			return MCounterTransactionSetup.class;
		else if(MCounterTransProductSetup.Table_Name.equals(tableName))			
			return MCounterTransProductSetup.class;
		else if(MGenerateTaxInvoice.Table_Name.equals(tableName))			
			return MGenerateTaxInvoice.class;
		else if(MGenerateTaxInvoiceLine.Table_Name.equals(tableName))			
			return MGenerateTaxInvoiceLine.class;
		else if(MCustomerType.Table_Name.equals(tableName))			
			return MCustomerType.class;
		else if(MTaxInvoiceCycle.Table_Name.equals(tableName))			
			return MTaxInvoiceCycle.class;
		else if(MDiscountRequest.Table_Name.equals(tableName))			
			return MDiscountRequest.class;
		else if(TF_MailText.Table_Name.equals(tableName))			
			return TF_MailText.class;
		else if(MEmailAlertSetup.Table_Name.equals(tableName))			
			return MEmailAlertSetup.class;
		else if(MEmailAlertSetupCC.Table_Name.equals(tableName))			
			return MEmailAlertSetupCC.class;
		else if(MDaySequence.Table_Name.equals(tableName))			
			return MDaySequence.class;
		else if(MToken.Table_Name.equals(tableName))			
			return MToken.class;
		return null;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
		Properties ctx = Env.getCtx();
		if (MBoulderReceipt.Table_Name.equals(tableName))
			return new MBoulderReceipt(ctx, Record_ID, trxName);
		else if (MQuarry.Table_Name.equals(tableName))
			return new MQuarry(ctx, Record_ID, trxName);
		else if (MEmpSalaryConfig.Table_Name.equals(tableName))
			return new MEmpSalaryConfig(ctx, Record_ID, trxName);
		else if (MEmployeeSalary.Table_Name.equals(tableName))
			return new MEmployeeSalary(ctx, Record_ID, trxName);		
		else if (MVehicleRent.Table_Name.equals(tableName))
			return new MVehicleRent(ctx, Record_ID, trxName);
		else if (MQuarryRentConfig.Table_Name.equals(tableName))
			return new MQuarryRentConfig(ctx, Record_ID, trxName);
		else if (MQuarryRent.Table_Name.equals(tableName))
			return new MQuarryRent(ctx, Record_ID, trxName);
		else if (MVehicleType.Table_Name.equals(tableName))
			return new MVehicleType(ctx, Record_ID, trxName);
		else if (MGLPostingConfig.Table_Name.equals(tableName))
			return new MGLPostingConfig(ctx, Record_ID, trxName);
		else if (MCrusherProduction.Table_Name.equals(tableName))
			return new MCrusherProduction(ctx, Record_ID, trxName);
		else if (MCrusherProductionConfig.Table_Name.equals(tableName))
			return new MCrusherProductionConfig(ctx, Record_ID, trxName);
		else if (TF_MInvoice.Table_Name.equals(tableName))
			return new TF_MInvoice(ctx, Record_ID, trxName);
		else if (TF_MOrder.Table_Name.equals(tableName))
			return new TF_MOrder(ctx, Record_ID, trxName);
		else if (TF_MOrderLine.Table_Name.equals(tableName))
			return new TF_MOrderLine(ctx, Record_ID, trxName);
		else if (MFuelIssue.Table_Name.equals(tableName))
			return new MFuelIssue(ctx, Record_ID, trxName);
		else if (MLabourWage.Table_Name.equals(tableName))			
			return new MLabourWage(ctx, Record_ID, trxName);
		else if (MLabourWageConfig.Table_Name.equals(tableName))
			return new MLabourWageConfig(ctx, Record_ID, trxName);
		else if (TF_MPayment.Table_Name.equals(tableName))
			return new TF_MPayment(ctx, Record_ID, trxName);
		else if (TF_MCharge.Table_Name.equals(tableName))
			return new TF_MCharge(ctx, Record_ID, trxName);
		else if (MLabourWageAdvance.Table_Name.equals(tableName))
			return new MLabourWageAdvance(ctx, Record_ID, trxName);
		else if (MEmployeeSalaryAdvance.Table_Name.equals(tableName))
			return new MEmployeeSalaryAdvance(ctx, Record_ID, trxName);
		else if (MLabourWageIssue.Table_Name.equals(tableName))
			return new MLabourWageIssue(ctx, Record_ID, trxName);
		else if (MEmployeeSalaryIssue.Table_Name.equals(tableName))
			return new MEmployeeSalaryIssue(ctx, Record_ID, trxName);
		else if (TF_MResourceType.Table_Name.equals(tableName))
			return new TF_MResourceType(ctx, Record_ID, trxName);
		else if (TF_MResource.Table_Name.equals(tableName))
			return new TF_MResource(ctx, Record_ID, trxName);
		else if (MVehicleRentalContract.Table_Name.equals(tableName))
			return new MVehicleRentalContract(ctx, Record_ID, trxName);
		else if (MTripSheet.Table_Name.equals(tableName))
			return new MTripSheet(ctx, Record_ID, trxName);
		else if (TF_MProject.Table_Name.equals(tableName))
			return new TF_MProject(ctx, Record_ID, trxName);
		else if (MJobworkCharges.Table_Name.equals(tableName))
			return new MJobworkCharges(ctx, Record_ID, trxName);
		else if (MJobworkIssuedItems.Table_Name.equals(tableName))
			return new MJobworkIssuedItems(ctx, Record_ID, trxName);
		else if (MJobworkIssuedResource.Table_Name.equals(tableName))
			return new MJobworkIssuedResource(ctx, Record_ID, trxName);
		else if (MJobworkProductPrice.Table_Name.equals(tableName))
			return new MJobworkProductPrice(ctx, Record_ID, trxName);
		else if (MJobworkReceivedItems.Table_Name.equals(tableName))
			return new MJobworkReceivedItems(ctx, Record_ID, trxName);
		else if (MJobworkItemIssue.Table_Name.equals(tableName))
			return new MJobworkItemIssue(ctx, Record_ID, trxName);
		else if (MJobworkExpense.Table_Name.equals(tableName))
			return new MJobworkExpense(ctx, Record_ID, trxName);
		else if (MJobworkExpenseEntry.Table_Name.equals(tableName))
			return new MJobworkExpenseEntry(ctx, Record_ID, trxName);
		else if (MItemReceiptOtherSrc.Table_Name.equals(tableName))
			return new MItemReceiptOtherSrc(ctx, Record_ID, trxName);
		else if (MTyre.Table_Name.equals(tableName))
			return new MTyre(ctx, Record_ID, trxName);
		else if (MTyreType.Table_Name.equals(tableName))
			return new MTyreType(ctx, Record_ID, trxName);
		else if (MTyreStatus.Table_Name.equals(tableName))
			return new MTyreStatus(ctx, Record_ID, trxName);
		else if (MTyrePosition.Table_Name.equals(tableName))
			return new MTyrePosition(ctx, Record_ID, trxName);
		else if (MTyreAssignment.Table_Name.equalsIgnoreCase(tableName))
			return new MTyreAssignment(ctx, Record_ID, trxName);
		else if (MTyreStatusChange.Table_Name.equals(tableName))
			return new MTyreStatusChange(ctx, Record_ID, trxName);
		else if (MTyreLife.Table_Name.equals(tableName))
			return new MTyreLife(ctx, Record_ID, trxName);
		else if (MJobworkResourceRentEntry.Table_Name.equals(tableName))
			return new MJobworkResourceRentEntry(ctx, Record_ID, trxName);
		else if (TF_MElementValue.Table_Name.equals(tableName))
			return new TF_MElementValue(ctx, Record_ID, trxName);
		else if (TF_MBankAccount.Table_Name.equals(tableName))
			return new TF_MBankAccount(ctx, Record_ID, trxName);
		else if (TF_MBPartner.Table_Name.equals(tableName)) 
			return new TF_MBPartner(ctx, Record_ID, trxName);
		else if (MDestination.Table_Name.equals(tableName)) 
			return new MDestination(ctx, Record_ID, trxName);
		else if (MRentedVehicle.Table_Name.equals(tableName)) 
			return new MRentedVehicle(ctx, Record_ID, trxName);
		else if (MVehicleRentConfig.Table_Name.equals(tableName)) 
			return new MVehicleRentConfig(ctx, Record_ID, trxName);
		else if (MWeighmentEntry.Table_Name.equals(tableName)) 
			return new MWeighmentEntry(ctx, Record_ID, trxName);
		else if (TF_MJournal.Table_Name.equals(tableName)) 
			return new TF_MJournal(ctx, Record_ID, trxName);
		else if (MCOAOpeningBalance.Table_Name.equals(tableName))
			return new MCOAOpeningBalance(ctx, Record_ID, trxName);
		else if (MBPOpeningBalance.Table_Name.equals(tableName))
			return new MBPOpeningBalance(ctx, Record_ID, trxName);
		else if (MInterOrgCashTransfer.Table_Name.equals(tableName))
			return new MInterOrgCashTransfer(ctx, Record_ID, trxName);
		else if (TF_MProduct.Table_Name.equals(tableName))
			return new TF_MProduct(ctx, Record_ID, trxName);
		else if (MSubcontractMaterialMovement.Table_Name.equals(tableName))
			return new MSubcontractMaterialMovement(ctx, Record_ID, trxName);
		else if (MShareholderType.Table_Name.equals(tableName))
			return new MShareholderType(ctx, Record_ID, trxName); 
		else if (MShareholder.Table_Name.equals(tableName))
			return new MShareholder(ctx, Record_ID, trxName);
		else if (MInvestmentStructure.Table_Name.equals(tableName))
			return new MInvestmentStructure(ctx, Record_ID, trxName);
		else if (MInvestmentReceipt.Table_Name.equals(tableName))
			return new MInvestmentReceipt(ctx, Record_ID, trxName);
		//else if (MHomePageShortcuts.Table_Name.equals(tableName))
		//	return new MHomePageShortcuts(ctx, Record_ID, tableName);
		else if(MSubcontractType.Table_Name.equals(tableName))
			return new MSubcontractType(ctx, Record_ID, trxName);
		else if(MWeighmentErrorLog.Table_Name.equals(tableName))
			return new MWeighmentErrorLog(ctx, Record_ID, trxName);
		else if(MSandBlockBucketConfig.Table_Name.equals(tableName))
			return new MSandBlockBucketConfig(ctx, Record_ID, trxName);
		else if(MPermitLedger.Table_Name.equals(tableName))
			return new MPermitLedger(ctx, Record_ID, trxName);
		else if(MPermitLedgerLine.Table_Name.equals(tableName))
			return new MPermitLedgerLine(ctx, Record_ID, trxName);
		else if(MYardEntry.Table_Name.equals(tableName))
			return new MYardEntry(ctx, Record_ID, trxName);
		else if(MYardEntryConfig.Table_Name.equals(tableName))
			return new MYardEntryConfig(ctx, Record_ID, trxName);
		else if(MJobworkAssignedEmployee.Table_Name.equals(tableName))			
			return new MJobworkAssignedEmployee(ctx, Record_ID, trxName);
		else if(MJobworkAssignedBPartner.Table_Name.equals(tableName))
			return new MJobworkAssignedBPartner(ctx, Record_ID, trxName);
		else if(MJobworkAssignedAccount.Table_Name.equals(tableName))
			return new MJobworkAssignedAccount(ctx, Record_ID, trxName);
		else if(MJobworkAssignedVehicle.Table_Name.equals(tableName))
			return new MJobworkAssignedVehicle(ctx, Record_ID, trxName);
		else if(MKatingEntry.Table_Name.equals(tableName))
			return new MKatingEntry(ctx, Record_ID, trxName);
		else if(MInterOrgCashTransferConfigLine.Table_Name.equals(tableName)) 
			return new MInterOrgCashTransferConfigLine(ctx, Record_ID, trxName);
		else if(MInterOrgBPCashTransferConfig.Table_Name.equals(tableName))
			return new MInterOrgBPCashTransferConfig(ctx, Record_ID, trxName);
		else if(MInterOrgBPCashTransferConfigLine.Table_Name.equals(tableName))
			return new MInterOrgBPCashTransferConfigLine(ctx, Record_ID, trxName);
		else if(MYardLoadConfig.Table_Name.equals(tableName))
			return new MYardLoadConfig(ctx, Record_ID, trxName);
		else if(MYardCustomerVehicle.Table_Name.equals(tableName))
			return new MYardCustomerVehicle(ctx, Record_ID, trxName);
		else if(MYardLoadEntry.Table_Name.equals(tableName))
			return new MYardLoadEntry(ctx, Record_ID, trxName);
		else if(MYardPermitIssueEntry.Table_Name.equals(tableName))
			return new MYardPermitIssueEntry(ctx, Record_ID, trxName);
		else if(MYardEntryApprove.Table_Name.equals(tableName))
			return new MYardEntryApprove(ctx, Record_ID, trxName);
		else if(MYardEntryApproveLine.Table_Name.equals(tableName))
			return new MYardEntryApproveLine(ctx, Record_ID, trxName);
		else if(MCrusherPermitLedger.Table_Name.equals(tableName))
			return new MCrusherPermitLedger(ctx, Record_ID, trxName);
		else if(MCrusherPermitLedgerLine.Table_Name.equals(tableName))
			return new MCrusherPermitLedgerLine(ctx, Record_ID, trxName);
		else if(MTaxInvoice.Table_Name.equals(tableName))
			return new MTaxInvoice(ctx, Record_ID, trxName);
		else if(MPermitPurchase.Table_Name.equals(tableName))
			return new MPermitPurchase(ctx, Record_ID, trxName);
		else if(MPermitPurchaseLine.Table_Name.equals(tableName))
			return new MPermitPurchaseLine(ctx, Record_ID, trxName);
		else if(MWeighmentPermitEntry.Table_Name.equals(tableName))
			return new MWeighmentPermitEntry(ctx, Record_ID, trxName);
		else if(MCrusherKatingConfig.Table_Name.equals(tableName))
			return new MCrusherKatingConfig(ctx, Record_ID, trxName);
		else if(MDriverBetaConfig.Table_Name.equals(tableName))
			return new MDriverBetaConfig(ctx, Record_ID, trxName);
		else if(MLumpSumRentConfig.Table_Name.equals(tableName))
			return new MLumpSumRentConfig(ctx, Record_ID, trxName);
		else if(MDebitCreditNote.Table_Name.equals(tableName))
			return new MDebitCreditNote(ctx, Record_ID, trxName);
		else if(MProductionPlant.Table_Name.equals(tableName))
			return new MProductionPlant(ctx, Record_ID, trxName);
		else if(MPriceListUOM.Table_Name.equals(tableName))			
			return new MPriceListUOM(ctx, Record_ID, trxName);
		else if(MSmsNotification.Table_Name.equals(tableName))			
			return new MSmsNotification(ctx, Record_ID, trxName);
		else if(MSmsReceipient.Table_Name.equals(tableName))			
			return new MSmsReceipient(ctx, Record_ID, trxName);
		else if(MTRTaxInvoice.Table_Name.equals(tableName))			
			return new MTRTaxInvoice(ctx, Record_ID, trxName);
		else if(MTRTaxInvoiceLine.Table_Name.equals(tableName))			
			return new MTRTaxInvoiceLine(ctx, Record_ID, trxName);
		else if(MCounterTransactionSetup.Table_Name.equals(tableName))			
			return new MCounterTransactionSetup(ctx, Record_ID, trxName);
		else if(MCounterTransProductSetup.Table_Name.equals(tableName))			
			return new MCounterTransProductSetup(ctx, Record_ID, trxName);
		else if(MGenerateTaxInvoice.Table_Name.equals(tableName))			
			return new MGenerateTaxInvoice(ctx, Record_ID, trxName);
		else if(MGenerateTaxInvoiceLine.Table_Name.equals(tableName))			
			return new MGenerateTaxInvoiceLine(ctx, Record_ID, trxName);
		else if(MCustomerType.Table_Name.equals(tableName))			
			return new MCustomerType(ctx, Record_ID, trxName);
		else if(MTaxInvoiceCycle.Table_Name.equals(tableName))			
			return new MTaxInvoiceCycle(ctx, Record_ID, trxName);
		else if(MDiscountRequest.Table_Name.equals(tableName))			
			return new MDiscountRequest(ctx, Record_ID, trxName);
		else if(TF_MailText.Table_Name.equals(tableName))			
			return new TF_MailText(ctx, Record_ID, trxName);
		else if(MEmailAlertSetup.Table_Name.equals(tableName))			
			return new MEmailAlertSetup(ctx, Record_ID, trxName);
		else if(MEmailAlertSetupCC.Table_Name.equals(tableName))			
			return new MEmailAlertSetupCC(ctx, Record_ID, trxName);
		else if(MDaySequence.Table_Name.equals(tableName))			
			return new MDaySequence(ctx, Record_ID, trxName);
		else if(MToken.Table_Name.equals(tableName))			
			return new MToken(ctx, Record_ID, trxName);
		return null;
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
		Properties ctx = Env.getCtx();
		if (MBoulderReceipt.Table_Name.equals(tableName))
			return new MBoulderReceipt(ctx, rs, trxName);
		else if (MQuarry.Table_Name.equals(tableName))
			return new MQuarry(ctx, rs, trxName);
		else if (MEmpSalaryConfig.Table_Name.equals(tableName))
			return new MEmpSalaryConfig(ctx, rs, trxName);
		else if (MEmployeeSalary.Table_Name.equals(tableName))
			return new MEmployeeSalary(ctx, rs, trxName);		
		else if (MVehicleRent.Table_Name.equals(tableName))
			return new MVehicleRent(ctx, rs, trxName);
		else if (MQuarryRentConfig.Table_Name.equals(tableName))
			return new MQuarryRentConfig(ctx, rs, trxName);
		else if (MQuarryRent.Table_Name.equals(tableName))
			return new MQuarryRent(ctx, rs, trxName);
		else if (MVehicleType.Table_Name.equals(tableName))
			return new MVehicleType(ctx, rs, trxName);
		else if (MGLPostingConfig.Table_Name.equals(tableName))
			return new MGLPostingConfig(ctx, rs, trxName);
		else if (MCrusherProduction.Table_Name.equals(tableName))
			return new MCrusherProduction(ctx, rs, trxName);
		else if (MCrusherProductionConfig.Table_Name.equals(tableName))
			return new MCrusherProductionConfig(ctx, rs, trxName);
		else if (TF_MInvoice.Table_Name.equals(tableName))
			return new TF_MInvoice(ctx, rs, trxName);
		else if (TF_MOrder.Table_Name.equals(tableName))
			return new TF_MOrder(ctx, rs, trxName);
		else if (TF_MOrderLine.Table_Name.equals(tableName))
			return new TF_MOrderLine(ctx, rs, trxName);
		else if (MFuelIssue.Table_Name.equals(tableName))
			return new MFuelIssue(ctx, rs, trxName);
		else if (MLabourWage.Table_Name.equals(tableName))			
			return new MLabourWage(ctx, rs, trxName);
		else if (MLabourWageConfig.Table_Name.equals(tableName))
			return new MLabourWageConfig(ctx, rs, trxName);
		else if (TF_MPayment.Table_Name.equals(tableName))
			return new TF_MPayment(ctx, rs, trxName);
		else if (TF_MCharge.Table_Name.equals(tableName))
			return new TF_MCharge(ctx, rs, trxName);
		else if (MLabourWageAdvance.Table_Name.equals(tableName))
			return new MLabourWageAdvance(ctx, rs, trxName);
		else if (MEmployeeSalaryAdvance.Table_Name.equals(tableName))
			return new MEmployeeSalaryAdvance(ctx, rs, trxName);
		else if (MLabourWageIssue.Table_Name.equals(tableName))
			return new MLabourWageIssue(ctx, rs, trxName);
		else if (MEmployeeSalaryIssue.Table_Name.equals(tableName))
			return new MEmployeeSalaryIssue(ctx, rs, trxName);
		else if (TF_MResourceType.Table_Name.equals(tableName))
			return new TF_MResourceType(ctx, rs, trxName);
		else if (TF_MResource.Table_Name.equals(tableName))
			return new TF_MResource(ctx, rs, trxName);
		else if (MVehicleRentalContract.Table_Name.equals(tableName))
			return new MVehicleRentalContract(ctx, rs, trxName);
		else if (MTripSheet.Table_Name.equals(tableName))
			return new MTripSheet(ctx, rs, trxName);
		else if (TF_MProject.Table_Name.equals(tableName))
			return new TF_MProject(ctx, rs, trxName);
		else if (MJobworkCharges.Table_Name.equals(tableName))
			return new MJobworkCharges(ctx, rs, trxName);
		else if (MJobworkIssuedItems.Table_Name.equals(tableName))
			return new MJobworkIssuedItems(ctx, rs, trxName);
		else if (MJobworkIssuedResource.Table_Name.equals(tableName))
			return new MJobworkIssuedResource(ctx, rs, trxName);
		else if (MJobworkProductPrice.Table_Name.equals(tableName))
			return new MJobworkProductPrice(ctx, rs, trxName);
		else if (MJobworkReceivedItems.Table_Name.equals(tableName))
			return new MJobworkReceivedItems(ctx, rs, trxName);
		else if (MJobworkItemIssue.Table_Name.equals(tableName))
			return new MJobworkItemIssue(ctx, rs, trxName);
		else if (MJobworkExpense.Table_Name.equals(tableName))
			return new MJobworkExpense(ctx, rs, trxName);
		else if (MJobworkExpenseEntry.Table_Name.equals(tableName))
			return new MJobworkExpenseEntry(ctx, rs, trxName);
		else if (MItemReceiptOtherSrc.Table_Name.equals(tableName))
			return new MItemReceiptOtherSrc(ctx, rs, trxName);
		else if (MTyre.Table_Name.equals(tableName))
			return new MTyre(ctx, rs, trxName);
		else if (MTyreType.Table_Name.equals(tableName))
			return new MTyreType(ctx, rs, trxName);
		else if (MTyreStatus.Table_Name.equals(tableName))
			return new MTyreStatus(ctx, rs, trxName);
		else if (MTyrePosition.Table_Name.equals(tableName))
			return new MTyrePosition(ctx, rs, trxName);
		else if (MTyreAssignment.Table_Name.equalsIgnoreCase(tableName))
			return new MTyreAssignment(ctx, rs, trxName);
		else if (MTyreStatusChange.Table_Name.equals(tableName))
			return new MTyreStatusChange(ctx, rs, trxName);
		else if (MTyreLife.Table_Name.equals(tableName))
			return new MTyreLife(ctx, rs, trxName);
		else if (MJobworkResourceRentEntry.Table_Name.equals(tableName))
			return new MJobworkResourceRentEntry(ctx, rs, trxName);
		else if (TF_MElementValue.Table_Name.equals(tableName))
			return new TF_MElementValue(ctx, rs, trxName);
		else if (TF_MBankAccount.Table_Name.equals(tableName))
			return new TF_MBankAccount(ctx, rs, trxName);
		else if (TF_MBPartner.Table_Name.equals(tableName)) 
			return new TF_MBPartner(ctx, rs, trxName);
		else if (MDestination.Table_Name.equals(tableName)) 
			return new MDestination(ctx, rs, trxName);
		else if (MRentedVehicle.Table_Name.equals(tableName)) 
			return new MRentedVehicle(ctx, rs, trxName);
		else if (MVehicleRentConfig.Table_Name.equals(tableName)) 
			return new MVehicleRentConfig(ctx, rs, trxName);
		else if (MWeighmentEntry.Table_Name.equals(tableName)) 
			return new MWeighmentEntry(ctx, rs, trxName);
		else if (TF_MJournal.Table_Name.equals(tableName)) 
			return new TF_MJournal(ctx, rs, trxName);
		else if (MCOAOpeningBalance.Table_Name.equals(tableName))
			return new MCOAOpeningBalance(ctx, rs, trxName);
		else if (MBPOpeningBalance.Table_Name.equals(tableName))
			return new MBPOpeningBalance(ctx, rs, trxName);
		else if (MInterOrgCashTransfer.Table_Name.equals(tableName))
			return new MInterOrgCashTransfer(ctx, rs, trxName);
		else if (TF_MProduct.Table_Name.equals(tableName))
			return new TF_MProduct(ctx, rs, trxName);
		else if (MSubcontractMaterialMovement.Table_Name.equals(tableName))
			return new MSubcontractMaterialMovement(ctx, rs, trxName);
		else if (MShareholderType.Table_Name.equals(tableName))
			return new MShareholderType(ctx, rs, trxName); 
		else if (MShareholder.Table_Name.equals(tableName))
			return new MShareholder(ctx, rs, trxName);
		else if (MInvestmentStructure.Table_Name.equals(tableName))
			return new MInvestmentStructure(ctx, rs, trxName);
		else if (MInvestmentReceipt.Table_Name.equals(tableName))
			return new MInvestmentReceipt(ctx, rs, trxName);
		//else if (MHomePageShortcuts.Table_Name.equals(tableName))
		//	return new MHomePageShortcuts(ctx, rs, tableName);
		else if(MSubcontractType.Table_Name.equals(tableName))
			return new MSubcontractType(ctx, rs, trxName);
		else if(MWeighmentErrorLog.Table_Name.equals(tableName))
			return new MWeighmentErrorLog(ctx, rs, trxName);
		else if(MSandBlockBucketConfig.Table_Name.equals(tableName))
			return new MSandBlockBucketConfig(ctx, rs, trxName);
		else if(MPermitLedger.Table_Name.equals(tableName))
			return new MPermitLedger(ctx, rs, trxName);
		else if(MPermitLedgerLine.Table_Name.equals(tableName))
			return new MPermitLedgerLine(ctx, rs, trxName);
		else if(MYardEntry.Table_Name.equals(tableName))
			return new MYardEntry(ctx, rs, trxName);
		else if(MYardEntryConfig.Table_Name.equals(tableName))
			return new MYardEntryConfig(ctx, rs, trxName);
		else if(MJobworkAssignedEmployee.Table_Name.equals(tableName))
			return new MJobworkAssignedEmployee(ctx, rs, trxName);
		else if(MJobworkAssignedBPartner.Table_Name.equals(tableName))
			return new MJobworkAssignedBPartner(ctx, rs, trxName);
		else if(MJobworkAssignedAccount.Table_Name.equals(tableName))
			return new MJobworkAssignedAccount(ctx, rs, trxName);
		else if(MJobworkAssignedVehicle.Table_Name.equals(tableName))
			return new MJobworkAssignedVehicle(ctx, rs, trxName);
		else if(MKatingEntry.Table_Name.equals(tableName))
			return new MKatingEntry(ctx, rs, trxName);
		else if(MInterOrgCashTransferConfigLine.Table_Name.equals(tableName)) 
			return new MInterOrgCashTransferConfigLine(ctx, rs, trxName);
		else if(MInterOrgBPCashTransferConfig.Table_Name.equals(tableName))
			return new MInterOrgBPCashTransferConfig(ctx, rs, trxName);
		else if(MInterOrgBPCashTransferConfigLine.Table_Name.equals(tableName))
			return new MInterOrgBPCashTransferConfigLine(ctx, rs, trxName);
		else if(MYardLoadConfig.Table_Name.equals(tableName))
			return new MYardLoadConfig(ctx, rs, trxName);
		else if(MYardCustomerVehicle.Table_Name.equals(tableName))
			return new MYardCustomerVehicle(ctx, rs, trxName);
		else if(MYardLoadEntry.Table_Name.equals(tableName))
			return new MYardLoadEntry(ctx, rs, trxName);
		else if(MYardPermitIssueEntry.Table_Name.equals(tableName))
			return new MYardPermitIssueEntry(ctx, rs, trxName);
		else if(MYardEntryApprove.Table_Name.equals(tableName))
			return new MYardEntryApprove(ctx, rs, trxName);
		else if(MYardEntryApproveLine.Table_Name.equals(tableName))
			return new MYardEntryApproveLine(ctx, rs, trxName);
		else if(MCrusherPermitLedger.Table_Name.equals(tableName))
			return new MCrusherPermitLedger(ctx, rs, trxName);
		else if(MCrusherPermitLedgerLine.Table_Name.equals(tableName))
			return new MCrusherPermitLedgerLine(ctx, rs, trxName);
		else if(MTaxInvoice.Table_Name.equals(tableName))
			return new MTaxInvoice(ctx, rs, trxName);
		else if(MPermitPurchase.Table_Name.equals(tableName))
			return new MPermitPurchase(ctx, rs, trxName);
		else if(MPermitPurchaseLine.Table_Name.equals(tableName))
			return new MPermitPurchaseLine(ctx, rs, trxName);
		else if(MWeighmentPermitEntry.Table_Name.equals(tableName))
			return new MWeighmentPermitEntry(ctx, rs, trxName);
		else if(MCrusherKatingConfig.Table_Name.equals(tableName))
			return new MCrusherKatingConfig(ctx, rs, trxName);
		else if(MDriverBetaConfig.Table_Name.equals(tableName))
			return new MDriverBetaConfig(ctx, rs, trxName);
		else if(MLumpSumRentConfig.Table_Name.equals(tableName))
			return new MLumpSumRentConfig(ctx, rs, trxName);
		else if(MCashCounter.Table_Name.equals(tableName))
			return new MCashCounter(ctx, rs, trxName);
		else if(MDebitCreditNote.Table_Name.equals(tableName))
			return new MDebitCreditNote(ctx, rs, trxName);
		else if(MProductionPlant.Table_Name.equals(tableName))
			return new MProductionPlant(ctx, rs, trxName);
		else if(MPriceListUOM.Table_Name.equals(tableName))			
			return new MPriceListUOM(ctx, rs, trxName);
		else if(MSmsNotification.Table_Name.equals(tableName))			
			return new MSmsNotification(ctx, rs, trxName);
		else if(MSmsReceipient.Table_Name.equals(tableName))			
			return new MSmsReceipient(ctx, rs, trxName);
		else if(MTRTaxInvoice.Table_Name.equals(tableName))			
			return new MTRTaxInvoice(ctx, rs, trxName);
		else if(MTRTaxInvoiceLine.Table_Name.equals(tableName))			
			return new MTRTaxInvoiceLine(ctx, rs, trxName);
		else if(MCounterTransactionSetup.Table_Name.equals(tableName))			
			return new MCounterTransactionSetup(ctx, rs, trxName);
		else if(MCounterTransProductSetup.Table_Name.equals(tableName))			
			return new MCounterTransProductSetup(ctx, rs, trxName);
		else if(MGenerateTaxInvoice.Table_Name.equals(tableName))
			return new MGenerateTaxInvoice(ctx, rs, trxName);
		else if(MGenerateTaxInvoiceLine.Table_Name.equals(tableName))
			return new MGenerateTaxInvoiceLine(ctx, rs, trxName);
		else if(MCustomerType.Table_Name.equals(tableName))			
			return new MCustomerType(ctx, rs, trxName);
		else if(MTaxInvoiceCycle.Table_Name.equals(tableName))			
			return new MTaxInvoiceCycle(ctx, rs, trxName);
		else if(MDiscountRequest.Table_Name.equals(tableName))			
			return new MDiscountRequest(ctx, rs, trxName);
		else if(TF_MailText.Table_Name.equals(tableName))			
			return new TF_MailText(ctx, rs, trxName);
		else if(MEmailAlertSetup.Table_Name.equals(tableName))			
			return new MEmailAlertSetup(ctx, rs, trxName);
		else if(MEmailAlertSetupCC.Table_Name.equals(tableName))			
			return new MEmailAlertSetupCC(ctx, rs, trxName);
		else if(MDaySequence.Table_Name.equals(tableName))			
			return new MDaySequence(ctx, rs, trxName);
		else if(MToken.Table_Name.equals(tableName))			
			return new MToken(ctx, rs, trxName);
		return null;
	}

}
