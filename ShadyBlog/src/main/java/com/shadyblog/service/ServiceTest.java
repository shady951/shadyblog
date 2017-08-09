package com.shadyblog.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shadyblog.dao.ArticleMapper;
import com.shadyblog.pojo.Article;
import com.shadyblog.util.DBAccessUtil;

public class ServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceTest.class); 
	
	public void testdo() {
		SqlSession ss = null;
		try {
			LOGGER.info("1");
			ss = DBAccessUtil.getSqlSession();
			LOGGER.info("2");
//			AkMapperMapper akMapperMapper = ss.getMapper(AkMapperMapper.class);
			ArticleMapper articleMapper = ss.getMapper(ArticleMapper.class);
			LOGGER.info("3");
//			int i = akMapperMapper.insert(new AkMapper(20, 22, 33));
//			Timestamp ts = new Timestamp(System.currentTimeMillis());
//			Date date = new Date();
//			int i = articleMapper.insertSelective(new Article(null, "asd", "dsa", ts, date, 3));
			Article a = articleMapper.selectByPrimaryKey(1);
			System.out.println(a.toString());
			Date dac = a.getCreateTime();
			Date dau = a.getUpdateTime();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(dac.toString());
			System.out.println(dau.toString());
			System.out.println(dac.getTime());
			System.out.println(df.format(dac));
//			ss.commit();
//			LOGGER.info("i:"+i);
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.info("5",e);
		} finally {
			if(ss != null) {
				ss.close();
			}
		}
	}
	
	public static void main(String[] args) {
		new ServiceTest().testdo();
	}
	
}