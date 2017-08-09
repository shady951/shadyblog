package com.shadyblog.pojo;

public class AkMapper {
    private Integer mapperId;

    private Integer articleId;

    private Integer keywordId;

    public AkMapper(Integer mapperId, Integer articleId, Integer keywordId) {
        this.mapperId = mapperId;
        this.articleId = articleId;
        this.keywordId = keywordId;
    }

    public AkMapper(Integer articleId, Integer keywordId) {
        this.articleId = articleId;
        this.keywordId = keywordId;
    }
    
    public AkMapper() {
        super();
    }

    public Integer getMapperId() {
        return mapperId;
    }

    public void setMapperId(Integer mapperId) {
        this.mapperId = mapperId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(Integer keywordId) {
        this.keywordId = keywordId;
    }
}