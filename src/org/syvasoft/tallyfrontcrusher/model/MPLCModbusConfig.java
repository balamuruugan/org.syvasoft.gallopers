package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MPLCModbusConfig extends X_PLC_Modbus_Config {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4144785025283309131L;

	public MPLCModbusConfig(Properties ctx, int PLC_Modbus_Config_ID, String trxName) {
		super(ctx, PLC_Modbus_Config_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MPLCModbusConfig(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
