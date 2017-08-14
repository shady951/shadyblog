package com.shadyblog.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.shady4j.framework.annotation.Service;
import org.shady4j.framework.util.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shadyblog.dao.AkMapperMapper;
import com.shadyblog.dao.ArticleMapper;
import com.shadyblog.dao.ContentMapper;
import com.shadyblog.dao.KeywordMapper;
import com.shadyblog.dto.ArticleInfo;
import com.shadyblog.dto.PageNumInfo;
import com.shadyblog.pojo.Article;
import com.shadyblog.pojo.Content;
import com.shadyblog.pojo.Keyword;
import com.shadyblog.util.DBAccessUtil;
import com.shadyblog.util.DateUtil;
import com.shadyblog.util.PageUtil;

@Service
public class NormalService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NormalService.class);
	
	public Map<String, Object> getPageInfo(int pageNum, int keywordId) {
		Map<String, Object> pageInfoMap = new HashMap<String, Object>();
		pageNum = PageUtil.getCorrectPageNum(pageNum);
		PageNumInfo pageNumInfo = getPageNumInfo(pageNum, keywordId);
		List<ArticleInfo> articleInfoList = getArticleInfoList(pageNumInfo.getPageNum(), keywordId);
		pageInfoMap.put("pageNumInfo", pageNumInfo);
		pageInfoMap.put("articleInfoList", articleInfoList);
		return pageInfoMap;
	}
	
	/**
	 * 获取单个文章信息与内容
	 */
	public ArticleInfo getArticleInfo(int articleId) {
		ArticleInfo articleInfo = null;
		Article article = selectArticleByArticleId(articleId);
		List<Keyword> keywordList = selectKeywordByArticleId(articleId);
		if(article != null) {
			String dateString = DateUtil.parseToString(article.getCreateTime());
			articleInfo = new ArticleInfo(article, keywordList, dateString); 
		}
		LOGGER.debug("ArticleInfo : " + articleInfo);
		return articleInfo;
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
		LOGGER.debug("Content : " + content);
		return content;
	}

	/**
	 *	查询所有关键字 
	 */
	public List<Keyword> selectAllKeyword() {
		SqlSession sqlSession = null;
		List<Keyword> keywordList = null;
		try {
			sqlSession = DBAccessUtil.getSqlSession();
			KeywordMapper keywordMapper = sqlSession.getMapper(KeywordMapper.class);
			keywordList = keywordMapper.selectAll();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			DBAccessUtil.closeSqlSession(sqlSession);
		}
		LOGGER.debug("List<Keyword> : " + keywordList);
		return keywordList;
	}
	
	/**
	 * 获取页面文章信息与内容
	 */
	private List<ArticleInfo> getArticleInfoList(int pageNum, int keywordId) {
		List<ArticleInfo> articleInfoList = new ArrayList<ArticleInfo>();
		List<Article> articleList = selectAllArticleInOnePage(pageNum, keywordId);
		if(CollectionUtil.isNotEmpty(articleList)) {
			for(Article article : articleList) {
				List<Keyword> keywordList = selectKeywordByArticleId(article.getArticleId());
				String dateString = DateUtil.parseToString(article.getCreateTime());
				articleInfoList.add(new ArticleInfo(article, keywordList, dateString));
			}
		}
		LOGGER.debug("ArticleInfoList : " + articleInfoList);
		return articleInfoList;
	}

	/**
	 * 获取页面翻页信息 
	 */
	private PageNumInfo getPageNumInfo(int pageNum, int keywordId) {
		int articleAmount = selectArticleAmountBykeywordId(keywordId);
		int pageAmount = PageUtil.getPageAmount(articleAmount);
		if(pageNum > pageAmount) pageNum = pageAmount;
		return new PageNumInfo(pageNum, keywordId, pageAmount);
	}

	/**
	 * 查询关键字对应的文章数量
	 */
	private int selectArticleAmountBykeywordId(int keywordId) {
		SqlSession sqlSession = null;
		List<Integer> articleIdList;
		try {
			sqlSession = DBAccessUtil.getSqlSession();
			if(keywordId > 0) {
				AkMapperMapper akMapperMapper = sqlSession.getMapper(AkMapperMapper.class);
				articleIdList = akMapperMapper.selectArticleIdByKeywordId(keywordId);
			} else {
				ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
				articleIdList = articleMapper.selectAllArticleId();
			}
			return articleIdList.size();
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		} finally {
			DBAccessUtil.closeSqlSession(sqlSession);
		}
	}

	/**
	 * 根据文章信息id查询文章信息
	 */
	private Article selectArticleByArticleId(Integer articleId) {
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
		LOGGER.debug("Article : " + article);
		return article;
	}

	/**
	 *	 根据文章信息ID查询关键字
	 */
	private List<Keyword> selectKeywordByArticleId(Integer articleId) {
		SqlSession sqlSession = null;
		List<Keyword> keywordList = null;
		try {
			sqlSession = DBAccessUtil.getSqlSession();
			AkMapperMapper akMapperMapper = sqlSession.getMapper(AkMapperMapper.class);
			List<Integer> keywordIdList = akMapperMapper.selectKeywordIdByArticleId(articleId);
			if(CollectionUtil.isNotEmpty(keywordIdList)){
				KeywordMapper keywordMapper = sqlSession.getMapper(KeywordMapper.class);
				keywordList = keywordMapper.selectKeywordByKeywordIdList(keywordIdList);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			DBAccessUtil.closeSqlSession(sqlSession);
		}
		LOGGER.debug("List<Keyword> : " + keywordList);
		return keywordList;
	}

	/**
	 * 分类分页查询单页的所有文章信息
	 */
	private  List<Article> selectAllArticleInOnePage(int pageNum, int keywordId) {
		SqlSession sqlSession = null;
		List<Article> articleList = null;
		try {
			sqlSession = DBAccessUtil.getSqlSession();
			ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
			if(keywordId > 0) {
				AkMapperMapper akMapperMapper = sqlSession.getMapper(AkMapperMapper.class);
				List<Integer> articleIdList = akMapperMapper.selectArticleIdByKeywordId(keywordId);
				if(CollectionUtil.isNotEmpty(articleIdList)) {
					articleList = articleMapper.selectSpecialArticleInOnePage(PageUtil.offSet(pageNum), PageUtil.LIMIT, articleIdList);
				}
			}
			if(CollectionUtil.isEmpty(articleList)) {
				articleList = articleMapper.selectAllArticleInOnePage(PageUtil.offSet(pageNum), PageUtil.LIMIT);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			DBAccessUtil.closeSqlSession(sqlSession);
		}
		LOGGER.debug("List<Article> : " + articleList);
		return articleList;
	}
	
}
