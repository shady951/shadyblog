package com.shadyblog.dao;

import com.shadyblog.pojo.akMapper;

public interface akMapperMapper {
    int deleteByPrimaryKey(Integer mapperId);

    int insert(akMapper record);

    int insertSelective(akMapper record);

    akMapper selectByPrimaryKey(Integer mapperId);

    int updateByPrimaryKeySelective(akMapper record);

    int updateByPrimaryKey(akMapper record);
}