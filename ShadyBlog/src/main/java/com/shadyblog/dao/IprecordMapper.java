package com.shadyblog.dao;

import java.util.List;

import com.shadyblog.pojo.Iprecord;

public interface IprecordMapper {

	Integer selectId(String ip);

	int insertIprecord(Iprecord iprecord);

	int updateIndexnum(Iprecord id);

	int updateContentnum(Iprecord id);

	List<Iprecord> selectAll();
}
