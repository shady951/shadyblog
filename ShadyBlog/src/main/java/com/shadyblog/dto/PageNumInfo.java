package com.shadyblog.dto;

public class PageNumInfo {

	private int pageNum;
	
	private int keywordId;
	
	private int pageAmount;
	
	public PageNumInfo(int pageNum, int keywordId, int pageAmount) {
		this.pageNum = pageNum;
		this.keywordId = keywordId;
		this.pageAmount = pageAmount;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	
	public int getKeywordId() {
		return keywordId;
	}
	
	public int getPageAmount() {
		return pageAmount;
	}

	@Override
	public String toString() {
		return "PageNumInfo [pageNum=" + pageNum + ", keywordId=" + keywordId + ", pageAmount=" + pageAmount + "]";
	}
	
}
