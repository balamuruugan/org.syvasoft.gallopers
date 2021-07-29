package org.syvasoft.tallyfrontcrusher.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.chatapi.whatsapp.api.WhatsAppUtil;
import org.compiere.model.MNote;
import org.compiere.model.MSysConfig;
import org.compiere.model.Query;
import org.compiere.util.DB;

public class MNotification extends X_TF_SmsNotification {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7211450842876080363L;

	public MNotification(Properties ctx, int TF_SmsNotification_ID, String trxName) {
		super(ctx, TF_SmsNotification_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MNotification(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
		
	public static MNotification get(Properties ctx, String Name, String trxName) {
		String whereClause = "Name = ?";
		MNotification msg = new Query(ctx, Table_Name, whereClause, trxName)
				.setClient_ID()
				.setParameters(Name)
				.first();
		return msg;
	}
	
	String hdrSql = "";		
	String SqlQuery = "";
	String ftrSql = "";
	String hdrMessage = "";
	String Message="";
	String ftrMessage = "";
	String Unicode="";
	boolean EnabledWhatsApp = MSysConfig.getBooleanValue("WHATSAPP_ENABLED", true);
	
	public String getSimpleMessageForConfiguredRecipients(String ID) {
		
		int maxRowMsgBody = MSysConfig.getIntValue("SMS_MAX_ROW_FOR_MSG_BODY", 5);
		
		PreparedStatement pstmt1 =  null;
		ResultSet rs1 = null;
		PreparedStatement hdrPstmt =  null;
		ResultSet hdrRs = null;
		PreparedStatement ftrPstmt =  null;
		ResultSet ftrRs = null;		
		try	{			
			
				pstmt1 = null;
				rs1 = null;
				hdrSql = getHeaderSql() != null ? getHeaderSql().replace("@ID@", ID) : "";
				SqlQuery= getSql().replace("@ID@", ID);	
				ftrSql = getFooterSql() != null ? getFooterSql().replace("@ID@", ID) : "";
				hdrMessage = getHeaderMsg();
				Message= getMessage();
				ftrMessage = getFooterMsg();
				Unicode= isUnicode() ? "Y" : "N";

				//Header
				if(hdrSql != null && hdrSql.length() > 0) {
					hdrPstmt = DB.prepareStatement (hdrSql, get_TrxName());
					hdrRs = hdrPstmt.executeQuery();
					ResultSetMetaData hdrMD = hdrRs.getMetaData();					
					while (hdrRs.next())
					{
						for(int i=1;i<=hdrMD.getColumnCount();i++) {
							String columnName = hdrMD.getColumnName(i); 
							hdrMessage=hdrMessage.replace("{"+columnName+"}", hdrRs.getString(columnName));
						}
						break;
					}
				}
				
				//Detail
				int row = 0;
				pstmt1 = DB.prepareStatement (SqlQuery.toString(), get_TrxName());
				rs1 = pstmt1.executeQuery();
				ResultSetMetaData md = rs1.getMetaData();
				StringBuilder msgBody = new StringBuilder("");
				if(hdrMessage != null)
					msgBody.append(hdrMessage);
				
				while (rs1.next())
				{
					String msgDetail = Message;	
					for(int i=1;i<=md.getColumnCount();i++) {
						String columnName = md.getColumnName(i); 
						String data = rs1.getString(columnName) == null ? "" : rs1.getString(columnName);
						msgDetail=msgDetail.replace("{"+columnName+"}", data );
					}
					msgBody.append(msgDetail);
					row++;
					if(row >= maxRowMsgBody)
						break;
				}								
				
				//Footer Message
				if(ftrSql != null && ftrSql.length() > 0) {
					ftrPstmt = DB.prepareStatement (ftrSql, get_TrxName());
					ftrRs = ftrPstmt.executeQuery();
					ResultSetMetaData ftrMD = ftrRs.getMetaData();
					while (ftrRs.next())
					{
						for(int i=1;i<=ftrMD.getColumnCount();i++) {
							String columnName = ftrMD.getColumnName(i); 
							ftrMessage = ftrMessage.replace("{"+columnName+"}", ftrRs.getString(columnName));
						}
						msgBody.append(ftrMessage);
						break;
					}
				}
								
			return msgBody.toString();
		}
		catch (SQLException e) {
						
			throw new DBException(e);
		}
		finally	{
			rs1 = null; pstmt1 = null;
			hdrRs = null; hdrPstmt = null;
			ftrRs = null; ftrPstmt = null;
		}		
	}
	
	public List<MNotificationRecipient> getRecipients() {
		//Find Receipients
		List<MNotificationRecipient> recipients = new Query(getCtx(), MNotificationRecipient.Table_Name, "TF_SmsNotification_ID = ?", get_TrxName())
				.setClient_ID()						
				.setParameters(getTF_SmsNotification_ID())
				.setOnlyActiveRecords(true)
				.list();
		/*if(recipients.size() == 0)
			throw new AdempiereException("Recipients not configured!");*/
		
		
		return recipients;
	}
	
	public void notifyMessage(String ID) {
		String msg = getSimpleMessageForConfiguredRecipients(ID);
		
		for(MNotificationRecipient recipient : getRecipients() ) {
			if(isWhatsApp() && EnabledWhatsApp) {
				WhatsAppUtil waUtil = new WhatsAppUtil();
				waUtil.sendMessage(getAD_Client_ID(), getAD_Org_ID(),getTF_SmsNotification_ID(), recipient.getChatId(), recipient.getMobileNo(), msg);
			}
			
			if(isNoticeFlag()) {
				MNote note = new MNote(getCtx(), 0, get_TrxName());
				
				note.setAD_Org_ID(getAD_Org_ID());
				note.setAD_Message_ID(getAD_Message_ID());
				note.setAD_User_ID(getAD_User_ID());
				note.setAD_Table_ID(getAD_Table_ID());
				note.setRecord_ID(Integer.parseInt(ID));
				note.setDescription(getRequiredAction());
				note.setTextMsg(msg);
				note.saveEx();
			}
		}
		
	}
	
	public static void notifyMessage(Properties ctx, String Name, String ID, String trxName) {
		MNotification msg = get(ctx, Name, trxName);
		msg.notifyMessage(ID);
	}

	public List<MNotificationTriggerColumn> getTriggerColumns() {
		String where = "TF_SmsNotification_ID = " + getTF_SmsNotification_ID();
		
		List<MNotificationTriggerColumn> triggerColumns = new Query(getCtx(), MNotificationTriggerColumn.Table_Name, where, get_TrxName())
						.setClient_ID().setOnlyActiveRecords(true).list();
		
		return triggerColumns;
	}
}
