package org.chatapi.whatsapp.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.compiere.model.MSysConfig;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.nettyfish.sms.api.MSMSDeliveryLog;

public class WhatsAppUtil {

	String m_Phone = null;
	String m_ChatId = null;
	int m_AD_Org_ID = 0;
	String m_msg = null;
	int m_TF_SmsNotification_ID = 0;
	public WhatsAppUtil() {
		
	}

	public void sendMessage(int AD_Client_ID,int AD_Org_ID, int TF_SmsNotification_ID, String ChatId, String Phone,String Message) {
		String whatsappapiURLMessage = MSysConfig.getValue("WhatsappAPI") 
				+ MSysConfig.getValue("WhatsappInstanceId")
				 + "/sendMessage?token=" + MSysConfig.getValue("WhatsappToken");
		m_ChatId = ChatId;
		m_Phone = Phone;
		m_msg = Message;
		m_AD_Org_ID = AD_Org_ID;
		m_TF_SmsNotification_ID = TF_SmsNotification_ID;
		
		HashMap<String, String> textMsg  = new HashMap<>();
		if(!Util.isEmpty(Phone))
			textMsg.put("phone", Phone);
		else
			textMsg.put("chatId", ChatId);
		try {
			textMsg.put("body", URLEncoder.encode(Message, "UTF-8" ));
		} catch (UnsupportedEncodingException e) {
			
		}
		
		sendWhatsAppMessage(whatsappapiURLMessage, textMsg);
	}
	
	
	private void sendWhatsAppMessage(String apiUrl, HashMap<String, String> data) {
		String result;
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : data.entrySet()) {
		    sb.append("&" + entry.getKey() + "=" + entry.getValue());		    
		}
		
		try {
			URL url = new URL(apiUrl);				
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			
			wr.writeBytes(sb.toString());
			wr.flush();
			wr.close();
										
			conn.getResponseCode();
						
			BufferedReader rd = new BufferedReader(new
			InputStreamReader(conn.getInputStream()));
			String line;
			StringBuffer buffer = new StringBuffer();
			while ((line = rd.readLine()) != null){
				buffer.append(line).append("\n");
			}
			rd.close();
			conn.disconnect();
			result=buffer.toString();
			saveMessageLog(result);
	
		} catch (MalformedURLException e) {
			saveMessageLog(e.getMessage());			
		}catch (IOException e) {			
			saveMessageLog(e.getMessage());
		} 
	}
	
	private void saveMessageLog(String result) {
		
		MSMSDeliveryLog sdlog=new MSMSDeliveryLog(Env.getCtx(), 0, null);			
		String recipient = m_ChatId;
		if(m_Phone != null)
			recipient = m_Phone;
		sdlog.setAD_Org_ID(m_AD_Org_ID);
		sdlog.setTF_SmsNotification_ID(m_TF_SmsNotification_ID);
		sdlog.setRecipients(recipient);
		sdlog.setMessage(m_msg);
		sdlog.setResult(result);
		sdlog.saveEx();		
	}
	
}
