package com.shadyblog.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.shady4j.framework.annotation.Service;
import org.shady4j.framework.util.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shadyblog.dao.AkMapperMapper;
import com.shadyblog.dao.ArticleMapper;
import com.shadyblog.dao.ContentMapper;
import com.shadyblog.dao.KeywordMapper;
import com.shadyblog.pojo.AkMapper;
import com.shadyblog.pojo.Article;
import com.shadyblog.pojo.Content;
import com.shadyblog.pojo.Keyword;
import com.shadyblog.util.DBAccessUtil;

@Service
public class ManagerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ManagerService.class);
	
	/**
	 * 更新文章
	 */
	public  boolean updateArticle(Article article, Content content, String keywords) {
		int articleId = article.getArticleId();
		if(articleId == 0 || article.getTitle().equals("")) return false;
		Date date = new Date();
		SqlSession sqlSession = null;
		try {
			sqlSession = DBAccessUtil.getSqlSession();
			ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
			article.setUpdateTime(date);
			//更新文章信息
			if(articleMapper.updateByPrimaryKeySelective(article) == 0) {
				throw new IOException("update article failure!");
			}
			//更新文章内容
			ContentMapper contentMapper = sqlSession.getMapper(ContentMapper.class);
			if(contentMapper.updateByArticleId(content) == 0) {
				throw new IOException("update content failure!");
			}
			removeKeywordMapper(articleId, sqlSession);
			addKeywordMapper(articleId, keywords, sqlSession);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			//异常回滚
			sqlSession.rollback();
			e.printStackTrace();
			return false;
		} finally {
			DBAccessUtil.closeSqlSession(sqlSession);
		}
	}
	
	/**
	 * 添加文章
	 */
	public  boolean addArticle(Article article, Content content, String keywords) { 
		//文章标题必填
		if(article.getTitle().equals("")) return false;
		//新文章设置默认点击量为0
		if(article.getClickNumber() == null) article.setClickNumber(0);
		Date date = new Date();
		SqlSession sqlSession = null;
		try {
			sqlSession = DBAccessUtil.getSqlSession();
			ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
			if(article.getCreateTime() == null) article.setCreateTime(date);
			if(article.getUpdateTime() == null) article.setUpdateTime(date);
			//此insert返回主键ID至传入的实体对象中
			articleMapper.insert(article);
			Integer articleId = article.getArticleId();
			LOGGER.info("articleId:"+articleId);
			if(articleId == null) {
				throw new IOException("insert article failure!");
			}
			ContentMapper contentMapper = sqlSession.getMapper(ContentMapper.class);
			//文章内容与文章信息通过文章信息id连接
			content.setArticleId(articleId);
			if(contentMapper.insert(content) != 1) {
				throw new IOException("insert content failure!");
			}
			addKeywordMapper(articleId, keywords, sqlSession);
			//事务提交
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			//异常回滚
			sqlSession.rollback();
			e.printStackTrace();
			return false;
		} finally {
			DBAccessUtil.closeSqlSession(sqlSession);
		}
	}

	/**
	 * 清楚文章对应的所有关键字映射，且对应的关键字计数-1
	 * 清除未被引用(计数为0)的关键字
	 * @throws IOException 
	 */
	private void removeKeywordMapper(int articleId, SqlSession sqlSession) throws IOException {
			AkMapperMapper akMapperMapper = sqlSession.getMapper(AkMapperMapper.class);
			KeywordMapper keywordMapper = sqlSession.getMapper(KeywordMapper.class);
			List<Integer> keywordIdList = akMapperMapper.selectKeywordIdByArticleId(articleId);
			if(CollectionUtil.isNotEmpty(keywordIdList)) {
				keywordMapper.updateReduceAmountByKeywordId(keywordIdList);
			}
			keywordMapper.deleteSpareKeywords();
			akMapperMapper.deleteByArticleId(articleId);
	}

	/**
	 * 添加关键字或关键字计数+1
	 * 添加关键字与文章的映射
	 */
	private void addKeywordMapper(Integer articleId, String keywords, SqlSession sqlSession) throws IOException {
			KeywordMapper keywordMapper = sqlSession.getMapper(KeywordMapper.class);
			AkMapperMapper akMapperMapper = sqlSession.getMapper(AkMapperMapper.class);
			//若未设置关键字，则放入其它分类中
			if(keywords.equals("")) keywords = "其它";
			String[] keywordArr = keywords.split(",");
			for(String keywordName : keywordArr) {
				Integer keywordId = keywordMapper.selectIdByName(keywordName);
				if(keywordId != null) {
					if(keywordMapper.updateAmountByKeywordId(keywordId) == 0) {
						throw new IOException("update keyword's amount failure!");
					}
				} else {
					Keyword keyword = new Keyword(keywordName, 1, new Date());
					//此insert返回主键ID至传入的实体对象中
					keywordMapper.insert(keyword);
					keywordId = keyword.getKeywordId();
					if(keywordId == null) throw new IOException("insert keyword failure!");
				}
				//建立文章id与关键字id的映射
				if(akMapperMapper.insert(new AkMapper(articleId, keywordId)) == 0) {
					throw new IOException("insert akmapper failure!");
				}
			}
	}
	
//	public static void main(String[] args) {
//		new ManagerService().addArticle(new Article("11aaa", "11ssss"), new Content("11mdmd", "11html"), "1zzz");
//	}
	
}
