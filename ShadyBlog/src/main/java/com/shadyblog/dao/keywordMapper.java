package com.shadyblog.dao;

import com.shadyblog.pojo.keyword;

public interface keywordMapper {
    int deleteByPrimaryKey(Integer keywordId);

    int insert(keyword record);

    int insertSelective(keyword record);

    keyword selectByPrimaryKey(Integer keywordId);

    int updateByPrimaryKeySelective(keyword record);

    int updateByPrimaryKey(keyword record);
}