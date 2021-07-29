package org.syvasoft.tallyfrontcrusher.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import org.adempiere.exceptions.DBException;
import org.compiere.model.MSysConfig;
import org.compiere.model.Query;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.nettyfish.sms.api.SMSUtil;
import org.syvasoft.tallyfrontcrusher.model.MNotificationRecipient;


public class ProcessSmsNotification extends SvrProcess {

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub

	}

	String hdrSql = "";		
	String SqlQuery = "";
	String ftrSql = "";
	String hdrMessage = "";
	String Message="";
	String ftrMessage = "";
	String Unicode="";
	
	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		
		String Sql="SELECT * FROM TF_SmsNotification WHERE  IsScheduled = 'Y' AND IsSMS = 'Y' AND AND IsActive='Y' AND  to_timestamp(deliverytime,'HH24:MI') :: time = to_timestamp(to_char(now(),'HH24:MI'),'HH24:MI')::time";
		PreparedStatement pstmt =  null;
		PreparedStatement rpPstmt = null;
		ResultSet rs = null;
		ResultSet rpRs = null;
		try	{
			pstmt = DB.prepareStatement(Sql, get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String recipientSQL = rs.getString("RecipientSQL");
				if(recipientSQL != null && recipientSQL.length() > 0) {
					rpPstmt = DB.prepareStatement (recipientSQL, get_TrxName());
					rpRs = rpPstmt.executeQuery();									
					
					while (rpRs.next())
					{
						sendSMSByRecipientSQL(rs,rpRs);
					}
				}
				else {
					sendSMSToConfiguredRecipients(rs);
				}
			}
		}
		catch (SQLException e) {
			rollback();
			//log.log(Level.SEVERE, "", e);
			throw new DBException(e, Sql);
		}
		finally	{
			DB.close(rs, pstmt);
			DB.close(rpRs, rpPstmt);		
			rs = null; pstmt = null;
			rpRs = null; rpPstmt = null;
		}
		return null;
	}
	
	
	private String parseSQL(ResultSet recipientRS, String sql) throws SQLException {
		if(sql == null)
			return null;
		ResultSetMetaData rpMD = recipientRS.getMetaData();
		for(int i=1;i<=rpMD.getColumnCount();i++) {
			String columnName = rpMD.getColumnName(i); 
			sql =sql.replace("@"+columnName+"@", recipientRS.getString(columnName));
		}
		return sql;	
	}
	
	private void sendSMSByRecipientSQL(ResultSet rs, ResultSet recipientRS) {
		
		int maxRowMsgBody = MSysConfig.getIntValue("SMS_MAX_ROW_FOR_MSG_BODY", 8);
		
		PreparedStatement pstmt1 =  null;
		ResultSet rs1 = null;
		PreparedStatement hdrPstmt =  null;
		ResultSet hdrRs = null;
		PreparedStatement ftrPstmt =  null;
		ResultSet ftrRs = null;	
		try	{			
			
				pstmt1 = null;
				rs1 = null;
				hdrSql = parseSQL(recipientRS, rs.getString("HeaderSql"));
				SqlQuery = parseSQL(recipientRS, rs.getString("Sql"));	
				ftrSql = parseSQL(recipientRS, rs.getString("FooterSql"));
				hdrMessage = rs.getString("HeaderMsg");
				Message=rs.getString("Message");
				ftrMessage = rs.getString("FooterMsg");
				Unicode=rs.getString("Unicode");

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
						msgDetail=msgDetail.replace("{"+columnName+"}", rs1.getString(columnName));
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
				else {
					msgBody.append(ftrMessage);
				}

				//Send sms
				String phoneNo = recipientRS.getString("Phone");
				SMSUtil sutil=new SMSUtil();
				sutil.SendSMS(getAD_Client_ID(),rs.getInt("AD_Org_ID"),phoneNo,msgBody.toString(),"1");	
			
		}
		catch (SQLException e) {
			rollback();
			//log.log(Level.SEVERE, "", e);
			throw new DBException(e);
		}
		finally	{
			rs1 = null; pstmt1 = null;
			hdrRs = null; hdrPstmt = null;
			ftrRs = null; ftrPstmt = null;
		}
	}
	
	private void sendSMSToConfiguredRecipients(ResultSet rs) {
		
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
				hdrSql = rs.getString("HeaderSql");
				SqlQuery=rs.getString("Sql");	
				ftrSql = rs.getString("FooterSql");
				hdrMessage = rs.getString("HeaderMsg");
				Message=rs.getString("Message");
				ftrMessage = rs.getString("FooterMsg");
				Unicode=rs.getString("Unicode");

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
						msgDetail=msgDetail.replace("{"+columnName+"}", rs1.getString(columnName));
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
				
				//Find Receipients
				List<MNotificationRecipient> recipients = new Query(getCtx(), MNotificationRecipient.Table_Name, "TF_SmsNotification_ID = ?", get_TrxName())
						.setClient_ID()						
						.setParameters(rs.getInt("TF_SmsNotification_ID"))
						.setOnlyActiveRecords(true)
						.list();
				//Sql="SELECT mobileno FROM TF_SmsRecipient Where IsActive='Y' AND TF_SmsNotification_ID="+rs.getInt("TF_SmsNotification_ID");
				//pstmt2 = DB.prepareStatement(Sql, get_TrxName());
				//rs2 = pstmt2.executeQuery();			
				
				String Receipient="";
				for(MNotificationRecipient recipient : recipients)
				{
					Receipient=recipient.getMobileNo();					
					SMSUtil sutil=new SMSUtil();
					if(Unicode.equals("Y")) {
						sutil.SendSMS(getAD_Client_ID(),rs.getInt("AD_Org_ID"),Receipient,msgBody.toString(),"1");
					}
					else {
						sutil.SendSMS(getAD_Client_ID(),rs.getInt("AD_Org_ID"),Receipient,msgBody.toString(),"0");
					}
						
				}				
			
		}
		catch (SQLException e) {
			rollback();
			//log.log(Level.SEVERE, "", e);
			throw new DBException(e);
		}
		finally	{
			rs1 = null; pstmt1 = null;
			hdrRs = null; hdrPstmt = null;
			ftrRs = null; ftrPstmt = null;
		}
	}

}
