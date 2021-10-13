package org.syvasoft.restprocess;

import java.io.File;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

public class UploadFile extends SvrProcess {

	String base64String = null;
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("base64"))
				base64String = para[i].getParameterAsString();
		}
		
	}

	@Override
	protected String doIt() throws Exception {
		byte[] decoder = Base64.getDecoder().decode(base64String);
		File pdf = new File("D:/unv/base64.pdf");
		FileUtils.writeByteArrayToFile(pdf, decoder);
		return null;
	}

}
