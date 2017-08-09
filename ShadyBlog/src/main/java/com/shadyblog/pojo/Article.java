package com.shadyblog.pojo;

import java.util.Date;

public class Article {
    private Integer articleId;

    private String title;

    private String summary;

    private Date updateTime;

    private Date createTime;

    private Integer clickNumber;

    public Article(Integer articleId, String title, String summary, Date updateTime, Date createTime, Integer clickNumber) {
        this.articleId = articleId;
        this.title = title;
        this.summary = summary;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.clickNumber = clickNumber;
    }

    public Article(String title, String summary) {
        this.title = title;
        this.summary = summary;
    }
    
    public Article() {
        super();
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getClickNumber() {
        return clickNumber;
    }

    public void setClickNumber(Integer clickNumber) {
        this.clickNumber = clickNumber;
    }

	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", title=" + title + ", summary=" + summary + ", updateTime=" + updateTime
				+ ", createTime=" + createTime + ", clickNumber=" + clickNumber + "]";
	}
    
    
}