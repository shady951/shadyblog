package com.shadyblog.pojo;

public class akMapper {
    private Integer mapperId;

    private Integer articleId;

    private Integer keywordId;

    public akMapper(Integer mapperId, Integer articleId, Integer keywordId) {
        this.mapperId = mapperId;
        this.articleId = articleId;
        this.keywordId = keywordId;
    }

    public akMapper() {
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