package com.shadyblog.pojo;

public class content {
    private Integer contentId;

    private Integer articleId;

    private String textMd;

    private String textHtml;

    public content(Integer contentId, Integer articleId, String textMd, String textHtml) {
        this.contentId = contentId;
        this.articleId = articleId;
        this.textMd = textMd;
        this.textHtml = textHtml;
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

    public String getTextMd() {
        return textMd;
    }

    public void setTextMd(String textMd) {
        this.textMd = textMd == null ? null : textMd.trim();
    }

    public String getTextHtml() {
        return textHtml;
    }

    public void setTextHtml(String textHtml) {
        this.textHtml = textHtml == null ? null : textHtml.trim();
    }
}