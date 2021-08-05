package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MLumpSumRentConfig;
import org.syvasoft.tallyfrontcrusher.model.MLumpSumRentRentMargin;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;;

public class ProcessReviseMargin extends SvrProcess {

	private BigDecimal ReviseMargin;
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();		
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();
			if (name.equals("ReviseMargin"))
				ReviseMargin =  para[i].getParameterAsBigDecimal();
		}
	}

	@Override
	protected String doIt() throws Exception {
		String m_processMsg = null;
		
		String whereClause ="";
		whereClause = " (EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE " +
				" T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID = TF_LumpSumRent_Customer.TF_LumpSumRent_Customer_ID))";
		
		//+ "AND C_Order.DocStatus IN ('CO','DR','IR'))";
		int i = 0;
		
		List<MLumpSumRentRentMargin> rentconfigs = new Query(getCtx(),MLumpSumRentRentMargin.Table_Name,whereClause,get_TrxName())
											 .setClient_ID().setParameters(getAD_PInstance_ID()).list();
		
		for(MLumpSumRentRentMargin rentconfig : rentconfigs) {
			rentconfig.setRentMargin(ReviseMargin);
			rentconfig.saveEx();
		}
		
		return "Rent Margin revised successfully!";
	}

}
