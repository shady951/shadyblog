package com.shadyblog.pojo;

public class contentWithBLOBs extends content {
    private String textMd;

    private String textHtml;

    public contentWithBLOBs(Integer contentId, Integer articleId, String textMd, String textHtml) {
        super(contentId, articleId);
        this.textMd = textMd;
        this.textHtml = textHtml;
    }

    public contentWithBLOBs() {
        super();
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