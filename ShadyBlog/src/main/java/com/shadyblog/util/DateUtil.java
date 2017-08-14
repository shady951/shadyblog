package com.shadyblog.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {

	private static final String DEFAULT_FORMAT = "yyyy-MM-dd";
	
	public static String parseToString(Date createTime, String format) {
		if(createTime == null) return "";
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(createTime);
	}

	public static String parseToString(Date createTime) {
		if(createTime == null) return "";
		DateFormat dateFormat = new SimpleDateFormat(DEFAULT_FORMAT);
		return dateFormat.format(createTime);
	}
	
}
