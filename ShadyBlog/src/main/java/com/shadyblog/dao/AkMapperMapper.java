package com.shadyblog.dao;

import java.util.List;

import com.shadyblog.pojo.AkMapper;

public interface AkMapperMapper {
    int deleteByPrimaryKey(Integer mapperId);

    int insert(AkMapper record);

    int insertSelective(AkMapper record);

    AkMapper selectByPrimaryKey(Integer mapperId);

    int updateByPrimaryKeySelective(AkMapper record);

    int updateByPrimaryKey(AkMapper record);
    //---------------------------------------
	List<Integer> selectArticleIdByKeywordId(Integer keywordId);

	List<Integer> selectKeywordIdByArticleId(Integer articleId);

	int deleteByArticleId(int articleId);
}