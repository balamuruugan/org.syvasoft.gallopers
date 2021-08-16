package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.model.GridTab;

public class CalloutUtil {

	public static int getIntValue(GridTab mTab, String columnName) {
		Object o = mTab.getValue(columnName);
		if(o != null)
			return (int) o;
		return 0;
	}
	
	public static BigDecimal getBDValue(GridTab mTab, String columnName) {
		Object o = mTab.getValue(columnName);
		if(o != null)
			return (BigDecimal) o;
		return BigDecimal.ZERO;
	}
	
	public static Timestamp getTimestamp(GridTab mTab, String columnName) {
		Object o = mTab.getValue(columnName);
		if(o != null)
			return (Timestamp) o;
		return null;
	}
	
	public static String getString(GridTab mTab, String columnName) {
		Object o = mTab.getValue(columnName);
		if(o != null)
			return o.toString();
		return null;
	}
}
