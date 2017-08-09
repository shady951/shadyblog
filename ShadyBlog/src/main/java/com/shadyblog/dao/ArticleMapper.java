package com.shadyblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shadyblog.pojo.Article;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);
    
    List<Article> selectAllArticleInOnePage(@Param("offset")int offset, @Param("limit")int limit);
}