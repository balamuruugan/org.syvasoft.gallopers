package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.sql.Savepoint;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MSysConfig;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.SvrProcess;
import org.compiere.util.Trx;
import org.syvasoft.tallyfrontcrusher.model.MCrusherKatingConfig;
import org.syvasoft.tallyfrontcrusher.model.MCrusherKatingEntry;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;

public class CreateCrusherKatingEntryFromWeighment extends SvrProcess {

	@Override
	protected void prepare() {
		
	}

	@Override
	protected String doIt() throws Exception {
		
		String whereClause = " (WeighmentEntryType = '5KA') AND ((Status = 'CO' "
				+ " AND NOT EXISTS(SELECT k.TF_WeighmentEntry_ID FROM TF_CrusherKatingEntry k WHERE "
				+ "k.TF_WeighmentEntry_ID =  TF_WeighmentEntry.TF_WeighmentEntry_ID))  OR (TF_WeighmentEntry_ID = ? AND TF_WeighmentEntry.Status IN ('UR','CO')))";
		int i = 0;
		
		List<MWeighmentEntry> wEntries = new Query(getCtx(), MWeighmentEntry.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getRecord_ID())
				.list();
		for(MWeighmentEntry wEntry : wEntries) {
			Trx trx = Trx.get(get_TrxName(), false);
			Savepoint sp = null;
			try {
				MCrusherKatingEntry katEntry = new MCrusherKatingEntry(getCtx(), 0, get_TrxName());
				MCrusherKatingConfig config = MCrusherKatingConfig.getConfig(wEntry.getAD_Org_ID());
				
				katEntry.setAD_Org_ID(wEntry.getAD_Org_ID());
				katEntry.setDateAcct(wEntry.getGrossWeightTime());
				katEntry.setTF_WeighmentEntry_ID(wEntry.getTF_WeighmentEntry_ID());
				katEntry.setKatingEntryType( config == null ? MCrusherKatingEntry.KATINGENTRYTYPE_Tonnage : config.getKatingEntryType());
				katEntry.setM_Warehouse_ID(wEntry.getM_Warehouse_ID());
				katEntry.setM_Product_ID(wEntry.getM_Product_ID());
				katEntry.setTonnage(wEntry.getNetWeight().divide(new BigDecimal(1000)));
				katEntry.setTotalLoad(BigDecimal.ONE);
				katEntry.setTF_RentedVehicle_ID(wEntry.getTF_RentedVehicle_ID());
				katEntry.setTransport_Price(config == null ? BigDecimal.ZERO : config.getTransport_Price());				
				katEntry.setLoaderVehicle_ID(config == null ? 0 : config.getLoaderVehicle_ID());
				katEntry.setLoading_Price(config == null ? BigDecimal.ZERO : config.getLoading_Price());
				katEntry.setDescription(wEntry.getDescription());
				katEntry.setTF_Send_To(wEntry.getTF_Send_To());
				katEntry.setTF_BlueMetal_Type(wEntry.getTF_BlueMetal_Type());
				katEntry.setTF_ProductionPlant_ID(wEntry.getTF_ProductionPlant_ID());
				
				if(katEntry.getKatingEntryType().equals(MCrusherKatingEntry.KATINGENTRYTYPE_Tonnage)) {
					katEntry.setTransport_Amount(katEntry.getTransport_Price().multiply(katEntry.getTonnage()));
					katEntry.setLoading_Amount(katEntry.getLoading_Price().multiply(katEntry.getTonnage()));
				}
				else {
					katEntry.setTransport_Amount(katEntry.getTransport_Price().multiply(katEntry.getTotalLoad()));
					katEntry.setLoading_Amount(katEntry.getLoading_Price().multiply(katEntry.getTotalLoad()));
				}
				
				katEntry.saveEx();
				sp = trx.setSavepoint(wEntry.getDocumentNo());
				katEntry.processIt(DocAction.ACTION_Complete);
				katEntry.saveEx();
				
				addLog(katEntry.get_Table_ID(), katEntry.getCreated(), null, katEntry.getDocumentNo() + " is created!", 
						katEntry.get_Table_ID(), katEntry.get_ID());
			}
			catch (Exception ex) {			
				if(sp != null)
					trx.rollback(sp);
				String desc = wEntry.getDescription();
				if(desc == null)
					desc = "";
				if(!desc.contains("ERROR:")) {
					wEntry.setDescription(desc + 
							" | ERROR: " + ex.getMessage());					
				}					
				wEntry.saveEx();
				addLog(wEntry.get_Table_ID(), wEntry.getGrossWeightTime(), null, ex.getMessage(), wEntry.get_Table_ID(), wEntry.get_ID());
			}
			i++;
		}
			
		return i +" weighment entries are processed!";
	}

}
