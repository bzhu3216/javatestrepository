package com.qh.test.util;

import java.text.NumberFormat;

public class StringUtil {
	public static boolean isEmpty(String str) {
		if ("".equals(str) || str == null) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotEmpty(String str) {
		if (!"".equals(str) && str != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String getPercentFormat(double  d) {
		NumberFormat nf=java.text.NumberFormat.getPercentInstance();
		nf.setMaximumFractionDigits(3);
		nf.setMinimumFractionDigits(2);
		String str=nf.format(d);
		return str;
	}

}
