package com.shadyblog.dao;

import com.shadyblog.pojo.article;

public interface articleMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(article record);

    int insertSelective(article record);

    article selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(article record);

    int updateByPrimaryKey(article record);
}