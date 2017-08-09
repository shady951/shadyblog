package com.shadyblog.dao;

import com.shadyblog.pojo.AkMapper;

public interface AkMapperMapper {
    int deleteByPrimaryKey(Integer mapperId);

    int insert(AkMapper record);

    int insertSelective(AkMapper record);

    AkMapper selectByPrimaryKey(Integer mapperId);

    int updateByPrimaryKeySelective(AkMapper record);

    int updateByPrimaryKey(AkMapper record);
}