package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.util.Properties;

public class MPLCReadingLog extends X_PLC_Reading_Log {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8129186551236948327L;

	public MPLCReadingLog(Properties ctx, int PLC_Reading_Log_ID, String trxName) {
		super(ctx, PLC_Reading_Log_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MPLCReadingLog(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(newRecord) {
			MPLCModbusConfig modbus = new MPLCModbusConfig(getCtx(), getPLC_Modbus_Config_ID(), get_TrxName());
			setPM_Machinery_ID(modbus.getPM_Machinery_ID());
			setC_UOM_ID(modbus.getC_UOM_ID());
			String resultPattern = modbus.getResultPattern();
			if(resultPattern.equals("HH-MM")) {
				String[] result = getResult().split("-");
				double meter = 0;
				for(int i = 0; i < result.length; i++) {
					if(i == 0) {
						meter = Double.parseDouble(result[i]);
					}
					else if(i == 1) {
						double mins = Double.parseDouble(result[1]) / 60;
						meter = meter + mins;
					}					
				}
				
				setRunning_Meter(new BigDecimal(meter).setScale(2, RoundingMode.HALF_EVEN));
			}
			else if(resultPattern.equals("0")) {
				setRunning_Meter(new BigDecimal(getResult()));
			}
		}
		return super.beforeSave(newRecord);
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if(newRecord) {
			MMeter.updateCurrentMeter(getCtx(), getPM_Machinery_ID(), getC_UOM_ID(), get_TrxName());
		}
		return super.afterSave(newRecord, success);
	}
}
