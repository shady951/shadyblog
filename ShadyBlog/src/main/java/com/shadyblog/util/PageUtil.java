package com.shadyblog.util;

public final class PageUtil {

	//每页显示数量
	public static final int LIMIT = 5;
	
	public static int offSet(int pageNum) {
		pageNum = getCorrectPageNum(pageNum);
		return (pageNum - 1) * LIMIT;
	}

	public static int getPageAmount(int size) {
		return (int)Math.ceil((double)size / LIMIT);
	}

	public static int getCorrectPageNum(int pageNum) {
		return pageNum < 1 ? 1 : pageNum;
	}
	
}
