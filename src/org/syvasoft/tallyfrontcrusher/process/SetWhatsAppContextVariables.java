package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MWhatsAppMsgConfig;


/***
 * TWO APPROACHES FOR SETTING CONTEXT VARIABLES
 * 
 * CONFIG SELECTION APPROACH 1
 * Process based
 * 
 * CONFIG SELECTION APPROACH 2
 * Search Key based - Passed from Parameter
 * 
 * BUILD MESSAGE APPROACH - Table Based
 * Table is optional
 * 
 * BUILD MESSAGE APPROACH - Parameter Based
 * Context Variables will be filled from the Parameters 
 * 
 */
public class SetWhatsAppContextVariables extends SvrProcess {

	MWhatsAppMsgConfig waConfig = null;

	
	@Override
	protected void prepare() {
		waConfig = MWhatsAppMsgConfig.get(getCtx(), getAD_PInstance_ID(), getParameter(), getRecord_ID(), get_TrxName());
	}

	@Override
	protected String doIt() throws Exception {
		Env.setContext(Env.getCtx(), "#EmailAddress", waConfig.getParsedEmail());
		Env.setContext(Env.getCtx(), "#WhatsAppPhone", waConfig.getParsedPhone());
		Env.setContext(Env.getCtx(), "#WhatsAppMessage", waConfig.getParsedMessage());
		Env.setContext(Env.getCtx(), "#FileName", waConfig.getParsedFileName());
		
		return null;
	}

}
