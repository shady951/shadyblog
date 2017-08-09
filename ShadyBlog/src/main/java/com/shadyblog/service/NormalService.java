package com.shadyblog.service;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.shady4j.framework.annotation.Service;

import com.shadyblog.dao.ArticleMapper;
import com.shadyblog.dao.ContentMapper;
import com.shadyblog.pojo.Article;
import com.shadyblog.pojo.Content;
import com.shadyblog.util.DBAccessUtil;
import com.shadyblog.util.PageUtil;

@Service
public class NormalService {
	
	/**
	 * 查询某一页的所有文章信息
	 */
	public  List<Article> selectAllArticleInOnePage(Integer pageNum) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBAccessUtil.getSqlSession();
			ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
			List<Article> articleList = articleMapper.selectAllArticleInOnePage(PageUtil.offSet(pageNum), PageUtil.LIMITNUMBER);
			return articleList;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			DBAccessUtil.closeSqlSession(sqlSession);
		}
		return null;
	}
	
	/**
	 * 根据文章信息id查询文章信息
	 */
	public Article selectArticleByArticleId(Integer articleId) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBAccessUtil.getSqlSession();
			ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
			Article article= articleMapper.selectByPrimaryKey(articleId);
			return article;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			DBAccessUtil.closeSqlSession(sqlSession);
		}
		return null;
	}
	
	/**
	 * 根据文章信息id查询文章内容
	 */
	public Content selectContentByArticleId(Integer articleId) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBAccessUtil.getSqlSession();
			ContentMapper contentMapper = sqlSession.getMapper(ContentMapper.class);
			Content content= contentMapper.selectByPrimaryKey(articleId);
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			DBAccessUtil.closeSqlSession(sqlSession);
		}
		return null;
	}
	
//	public static void main(String[] args) {
//		List<Article> la = new NormalService().selectAllArticleInOnePage(1);
//		System.out.println(la.size());
//		System.out.println(la.get(0));
//		System.out.println(la.get(1));
//		System.out.println(la.get(2));
//	}
	
}
