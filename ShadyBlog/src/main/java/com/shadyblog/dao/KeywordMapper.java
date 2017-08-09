package com.shadyblog.dao;

import com.shadyblog.pojo.Keyword;

public interface KeywordMapper {
    int deleteByPrimaryKey(Integer keywordId);

    int insert(Keyword record);

    int insertSelective(Keyword record);

    Keyword selectByPrimaryKey(Integer keywordId);

    int updateByPrimaryKeySelective(Keyword record);

    int updateByPrimaryKey(Keyword record);

	Integer selectIdByName(String name);

	int updateAmountByKeywordId(Integer keywordId);
}