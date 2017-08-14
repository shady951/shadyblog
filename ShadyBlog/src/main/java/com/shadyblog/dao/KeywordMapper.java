package com.shadyblog.dao;

import java.util.List;

import com.shadyblog.pojo.Keyword;

public interface KeywordMapper {
    int deleteByPrimaryKey(Integer keywordId);

    int insert(Keyword record);

    int insertSelective(Keyword record);

    Keyword selectByPrimaryKey(Integer keywordId);

    int updateByPrimaryKeySelective(Keyword record);

    int updateByPrimaryKey(Keyword record);
    //-----------------
    
	Integer selectIdByName(String name);

	List<Keyword> selectAll();

	List<Keyword> selectKeywordByKeywordIdList(List<Integer> keywordIdList);
	
	int updateAmountByKeywordId(Integer keywordId);

	int updateReduceAmountByKeywordId(List<Integer> keywordIdList);

	//删除没被任何文章引用过的关键字
	int deleteSpareKeywords();
}