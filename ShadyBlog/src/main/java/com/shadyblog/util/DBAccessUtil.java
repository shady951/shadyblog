package com.shadyblog.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public final class DBAccessUtil {

//	private static final Logger LOGGER	=	LoggerFactory.getLogger(DBAccessUtil.class);
//	
//	private static Reader reader;
	
//	static {
//			try {
//				reader = Resources.getResourceAsReader("mybatis-config.xml");
//			} catch (IOException e) {
//				LOGGER.error("failed to get resourceasreader in mybatis-config.xml");
//				throw new RuntimeException(e);
//			}
//	}
	
	public static SqlSession getSqlSession() throws IOException {
		//通过配置文件获取数据库连接信息
		Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
		//通过配置信息构建SqlSessionFactory
		//SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		//通过sqlSessionFactory打开数据库会话
		return new SqlSessionFactoryBuilder().build(reader).openSession();
	}
	
	public static void closeSqlSession(SqlSession sqlSession) {
		if(sqlSession != null) {
			sqlSession.close();
		}
	}
}
