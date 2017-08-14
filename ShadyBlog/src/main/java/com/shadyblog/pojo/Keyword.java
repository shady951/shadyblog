package com.shadyblog.pojo;

import java.util.Date;

public class Keyword {
    private Integer keywordId;

    private String name;

    private Integer amount;

    private Date createTime;

    public Keyword(Integer keywordId, String name, Integer amount, Date createTime) {
        this.keywordId = keywordId;
        this.name = name;
        this.amount = amount;
        this.createTime = createTime;
    }

    public Keyword(String name, Integer amount, Date createTime) {
        this.name = name;
        this.amount = amount;
        this.createTime = createTime;
    }
    
    public Keyword() {
        super();
    }

    public Integer getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(Integer keywordId) {
        this.keywordId = keywordId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "Keyword [keywordId=" + keywordId + ", name=" + name + ", amount=" + amount + ", createTime=" + createTime + "]";
	}
    
    
}