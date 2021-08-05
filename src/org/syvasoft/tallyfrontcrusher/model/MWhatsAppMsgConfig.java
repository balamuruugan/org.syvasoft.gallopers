package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MPInstance;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;

public class MWhatsAppMsgConfig extends X_TF_WhatsAppMsgConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2265113524934642537L;

	public MWhatsAppMsgConfig(Properties ctx, int TF_WhatsAppMsgConfig_ID, String trxName) {
		super(ctx, TF_WhatsAppMsgConfig_ID, trxName);
		
	}

	public MWhatsAppMsgConfig(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

	public static MWhatsAppMsgConfig get(Properties ctx, int AD_PInstance_ID, ProcessInfoParameter[] para, int Record_ID, String trxName) {
		MWhatsAppMsgConfig waConfig = null;
		String value = null;
		MPInstance pi = new MPInstance(ctx, AD_PInstance_ID, trxName);;
		int AD_Process_ID = pi.getAD_Process_ID();
		
		String whereClause = "AD_Process_ID = ?";
		waConfig = new Query(ctx, MWhatsAppMsgConfig.Table_Name, whereClause, trxName)
				.setClient_ID()
				.setParameters(AD_Process_ID)
				.first();
				
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();
			if(name.equals("Value"))
				value = para[i].getParameterAsString();
		}
		
		if(waConfig == null) {
			
			if(value == null)
				throw new AdempiereException("Invalid Whatsapp Message Configuration!");
			
			waConfig = new Query(ctx, MWhatsAppMsgConfig.Table_Name, "Value = ?", trxName)
					.setClient_ID()
					.setParameters(value)
					.first();
			
		}
		waConfig.init(para, Record_ID);
		return waConfig;
	}
		
	private ProcessInfoParameter[] parameters;
	
	private String tableClause = "";
	private String whereClause = "";
	private String emailSql = "";
	private String phoneSql = "";
	private String fileNameSql = "";
	private String message = "";	
	private String whereSql = "";
	public void init(ProcessInfoParameter[] para, int ID) {
		parameters = para;		
		emailSql = getEmailSql();
		phoneSql = getPhoneSql();
		fileNameSql = getFileNameSQL();
		message = getMessage();
		if(getWhereClause() != null)
			whereSql = getWhereClause().replace("@RECORD_ID@", Integer.toString(ID));
		
		parseFields();
		
		if(getAD_Table_ID() > 0) {
			tableClause = getAD_Table().getTableName();
			whereClause = " WHERE " + whereSql;
			
			if(ID <= 0)
				throw new AdempiereException("Record ID is missing!");
		}		
	}
	
	//fill context variables from parameter
	private void parseFields() {
		for (int i = 0; i < parameters.length; i++)
		{						
			String name = parameters[i].getParameterName();			
			String value = parameters[i].getParameterAsString();
			
			if(value != null) {
				whereSql = whereSql.replace("@" + name + "@", value);
				if(emailSql != null)
					emailSql = emailSql.replace("@" + name + "@", value);
				phoneSql = phoneSql.replace("@" + name + "@", value);
				fileNameSql = fileNameSql.replace("@" + name + "@", value);
			}
		}
	}
	
	public String getParsedFileName() {
		String fileName = executeSql(fileNameSql);
		
		return (getPrefix() == null ? "" : getPrefix()) + ((fileName == null) ? "" : fileName.replace("/", "-").replace("\\", "-"));		 
	}
	
	public String getParsedMessage() {
		return message;
	}
	
	public String getParsedEmail() {
		return executeSql(emailSql);
	}
	
	public String getParsedPhone() {	
		return executeSql(phoneSql);
	}
	
	public String executeSql(String expression) {
		if(expression == null)
			return null;
		String sql = "SELECT (" + expression + ") "+ (tableClause.length() == 0 ? "" :  " FROM " + tableClause + whereClause);
		return DB.getSQLValueString(get_TrxName(), sql);
	}
	
}
