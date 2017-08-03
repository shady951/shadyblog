package com.shadyblog.dao;

import com.shadyblog.pojo.content;
import com.shadyblog.pojo.contentWithBLOBs;

public interface contentMapper {
    int deleteByPrimaryKey(Integer contentId);

    int insert(contentWithBLOBs record);

    int insertSelective(contentWithBLOBs record);

    contentWithBLOBs selectByPrimaryKey(Integer contentId);

    int updateByPrimaryKeySelective(contentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(contentWithBLOBs record);

    int updateByPrimaryKey(content record);
}