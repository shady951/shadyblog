package com.shadyblog.util;

public final class PageUtil {

	public static final int LIMITNUMBER = 5;
	
	public static int offSet(int pageNum) {
		if(pageNum < 1) pageNum = 1;
		return (pageNum - 1) * LIMITNUMBER;
	}
}
