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
		List<Article> articleList = null;
		try {
			sqlSession = DBAccessUtil.getSqlSession();
			ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
			articleList = articleMapper.selectAllArticleInOnePage(PageUtil.offSet(pageNum), PageUtil.LIMITNUMBER);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			DBAccessUtil.closeSqlSession(sqlSession);
		}
		return articleList;
	}
	
	/**
	 * 根据文章信息id查询文章信息
	 */
	public Article selectArticleByArticleId(Integer articleId) {
		SqlSession sqlSession = null;
		Article article = null;
		try {
			sqlSession = DBAccessUtil.getSqlSession();
			ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
			article= articleMapper.selectByPrimaryKey(articleId);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			DBAccessUtil.closeSqlSession(sqlSession);
		}
		return article;
	}
	
	/**
	 * 根据文章信息id查询文章内容
	 */
	public Content selectContentByArticleId(Integer articleId) {
		SqlSession sqlSession = null;
		Content content = null;
		try {
			sqlSession = DBAccessUtil.getSqlSession();
			ContentMapper contentMapper = sqlSession.getMapper(ContentMapper.class);
			content = contentMapper.selectContentByArticleId(articleId);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			DBAccessUtil.closeSqlSession(sqlSession);
		}
		return content;
	}
	
//	public static void main(String[] args) {
//		Content content = new NormalService().selectContentByArticleId(8);
//		System.out.println(content);
//	}
	
}
