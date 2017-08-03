package com.shadyblog.pojo;

public class content {
    private Integer contentId;

    private Integer articleId;

    public content(Integer contentId, Integer articleId) {
        this.contentId = contentId;
        this.articleId = articleId;
    }

    public content() {
        super();
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}