package org.syvasoft.tallyfrontcrusher.process;


import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MJobworkIssuedResource;
import org.syvasoft.tallyfrontcrusher.model.MTripSheet;
import org.syvasoft.tallyfrontcrusher.model.MVehicleRentalContract;

public class ProcessTripSheet extends SvrProcess {

	private String docAction="CO";
	MTripSheet tripSheet; 
	
	@Override
	protected void prepare() {
		tripSheet = new MTripSheet(getCtx(), getRecord_ID(), get_TrxName());
		ProcessInfoParameter[] para = getParameter();		
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();
			if (name.equals("DocAction"))
				docAction =  para[i].getParameterAsString();
		}
	}

	@Override
	protected String doIt() throws Exception {
		if(!tripSheet.isProcessed())
			tripSheet.processIt(docAction);
		else if(tripSheet.isProcessed() && docAction.equals("MO"))
			tripSheet.reverseIt();
		
		tripSheet.saveEx();
		
		//Rental Contract Updates
		int rentalContractID = tripSheet.getTF_Vehicle_Rental_Contract_ID(); 
		if( rentalContractID > 0) {
			MVehicleRentalContract rentalContract = new MVehicleRentalContract(getCtx(), rentalContractID, get_TrxName());
			rentalContract.updateTripSheetBasedFields();
			rentalContract.saveEx();
		}
		if(tripSheet.getC_Project_ID() > 0) {
			MJobworkIssuedResource issuedResource = MJobworkIssuedResource.getByResource(getCtx(), tripSheet.getC_Project_ID(), 
					tripSheet.getVehicle_ID(), get_TrxName());
			if(issuedResource != null) {
				issuedResource.updateTripSheetBasedFields();
				issuedResource.saveEx();
			}
			else {
				throw new AdempiereException("Invalid Vehicle for this Subcontract!");
			}
		}
		return null;
	}

}
