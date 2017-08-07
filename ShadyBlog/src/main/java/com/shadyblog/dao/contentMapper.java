package com.shadyblog.dao;

import com.shadyblog.pojo.content;

public interface contentMapper {
    int deleteByPrimaryKey(Integer contentId);

    int insert(content record);

    int insertSelective(content record);

    content selectByPrimaryKey(Integer contentId);

    int updateByPrimaryKeySelective(content record);

    int updateByPrimaryKey(content record);
}