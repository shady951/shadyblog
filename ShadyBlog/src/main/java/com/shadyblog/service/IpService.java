package com.shadyblog.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.shady4j.framework.annotation.Service;

import com.shadyblog.dao.ArticleMapper;
import com.shadyblog.dao.IprecordMapper;
import com.shadyblog.pojo.Iprecord;
import com.shadyblog.util.DBAccessUtil;
import com.shadyblog.util.IpUtil;

@Service
public class IpService {

	//记录客户端IP行为
	public void insertAndUpdateIp(String ip, String methodName) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBAccessUtil.getSqlSession();
			IprecordMapper iprecordMapper = sqlSession.getMapper(IprecordMapper.class);
			Integer id = iprecordMapper.selectId(ip);
			Date date = new Date(); 
			if(id == null) {
				//第一次访问的ip无法记录行为，无伤大雅不想优化
				String address = IpUtil.queryIP(ip);
				iprecordMapper.insertIprecord(new Iprecord(ip, address, date, date));
			}
			if(methodName.equals("index")) {
				iprecordMapper.updateIndexnum(new Iprecord(id, date));
			}
			if(methodName.equals("content")) {
				iprecordMapper.updateContentnum(new Iprecord(id, date));
			}
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBAccessUtil.closeSqlSession(sqlSession);
		}
	}

	//更新文章点击量
	public void updateClickNumber(int articleId) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBAccessUtil.getSqlSession();
			ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
			articleMapper.updateClickNumber(articleId);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBAccessUtil.closeSqlSession(sqlSession);
		}
	}

	public List<Iprecord> findAll() {
		SqlSession sqlSession = null;
		List<Iprecord> iprecordList = null;
		try {
			sqlSession = DBAccessUtil.getSqlSession();
			IprecordMapper iprecordMapper = sqlSession.getMapper(IprecordMapper.class);
			iprecordList = iprecordMapper.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBAccessUtil.closeSqlSession(sqlSession);
		}
		return iprecordList;
	}
	
}
