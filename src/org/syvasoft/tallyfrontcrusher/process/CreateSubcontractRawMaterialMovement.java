package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.util.List;

import org.compiere.model.Query;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MSubcontractMaterialMovement;
import org.syvasoft.tallyfrontcrusher.model.MSubcontractType;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.TF_MProject;

public class CreateSubcontractRawMaterialMovement extends SvrProcess {

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception {
		
		//Quarry Production to Crusher Production Raw Material movements		
		//MSubcontractMaterialMovement.createRawmaterialMovementsFromWeighment(get_TrxName());
		String whereClause = "WeighmentEntryType = '4SR' AND C_Project_ID IS NOT NULL AND ((Status='CO' AND Processed='N' "
				+ "AND TareWeightTime IS NOT NULL AND GrossWeightTime IS NOT NULL AND IsSecondary='N') OR (TF_WeighmentEntry_ID = ? AND TF_WeighmentEntry.Status IN ('UR','CO')))";
		List<MWeighmentEntry> wEntries = new Query(Env.getCtx(), MWeighmentEntry.Table_Name, whereClause, get_TrxName())
				.setParameters(getRecord_ID())
				.list();
		
		for(MWeighmentEntry entry : wEntries) {		
			if(!entry.isSecondary()) {
				TF_MProject proj = new TF_MProject(getCtx(), entry.getC_Project_ID(), get_TrxName());
				MSubcontractType st = new MSubcontractType(getCtx(), proj.getTF_SubcontractType_ID(), get_TrxName());
				if(!st.isIncludeRMProduction()) {
					String desc = entry.getDescription();
					if(desc == null)
						desc = "";
					if(!desc.contains("ERROR:")) {
						entry.setDescription(desc + 
								" | ERROR: This Subcontract does not have the weighment entry! Pls check SubcontractType configuration.");					
					}
					//MWeighmentErrorLog log = new MWeighmentErrorLog(getCtx(), 0, get_TrxName());
					//log.setTF_WeighmentEntry_ID(entry.getTF_WeighmentEntry_ID());
					//log.setMessage("ERROR: This Subcontract does not have the weighment entry! Pls check SubcontractType configuration.");
					//log.setDateError(entry.getTareWeightTime());
					//log.saveEx();				
				}
				else if(st.isCreateBoulderReceipt()) {
					MBoulderReceipt br = new MBoulderReceipt(getCtx(), 0, get_TrxName());
					try {
						br.createFromWeighmentEntry(entry);
						br.saveEx();
						if(!br.isProcessed()) {
							br.processIt(MBoulderReceipt.DOCACTION_Complete);					
							br.saveEx();					
						}			
										
					}
					catch (Exception ex) {
						String desc = br.getDescription();
						if(desc == null)
							desc = "";
						if(!desc.contains("ERROR:")) {
							br.setDescription(desc + 
									" | ERROR: " + ex.getMessage());					
						}					
						br.saveEx();
					}
					entry.setStatus(MWeighmentEntry.STATUS_Billed);
					entry.setProcessed(true);
				}
				else if(st.isIncludeRMProduction() && st.isTrackMaterialMovement()) {
					MSubcontractMaterialMovement mov = new MSubcontractMaterialMovement(Env.getCtx(), 0, get_TrxName());
					mov.setAD_Org_ID(entry.getAD_Org_ID());
					mov.setC_Project_ID(entry.getC_Project_ID());
					mov.setC_BPartner_ID(entry.getC_BPartner_ID());
					mov.setM_Product_ID(entry.getM_Product_ID());
					mov.setTF_WeighmentEntry_ID(entry.getTF_WeighmentEntry_ID());
					mov.setMovementDate(entry.getGrossWeightTime());
					mov.setQty_Receipt(new BigDecimal(entry.getNetWeight().doubleValue()/1000));				
					mov.setProcessed(true);
					mov.saveEx();
					entry.setStatus(MWeighmentEntry.STATUS_Billed);
					entry.setProcessed(true);					
				}
				entry.saveEx();	
			}
		}
		
		
		//Own Quarry Production Receipt to Crusher Production
		whereClause = "WeighmentEntryType = '3PR' AND Status='CO' AND Processed='N' AND TareWeightTime IS NOT NULL AND GrossWeightTime IS NOT NULL AND IsSecondary='N'";
		wEntries = new Query(Env.getCtx(), MWeighmentEntry.Table_Name, whereClause, get_TrxName())
				.list();
		
		for(MWeighmentEntry entry : wEntries) {	
			MBoulderReceipt br = new MBoulderReceipt(getCtx(), 0, get_TrxName());
			try {
				if(!entry.isSecondary()) {
					br.createFromWeighmentEntry(entry);
					br.saveEx();
					//br.createSubcontractMovement();
					br.saveEx();
					if(!br.isProcessed()) {
						br.processIt(MBoulderReceipt.DOCACTION_Complete);					
						br.saveEx();					
					}
				}				
			}
			catch (Exception ex) {
				String desc = br.getDescription();
				if(desc == null)
					desc = "";
				if(!desc.contains("ERROR:")) {
					br.setDescription(desc + 
							" | ERROR: " + ex.getMessage());					
				}					
				br.saveEx();
			}
			entry.setStatus(MWeighmentEntry.STATUS_Billed);
			entry.setProcessed(true);
			entry.saveEx();	
		}
		
		return null;
	}

}
