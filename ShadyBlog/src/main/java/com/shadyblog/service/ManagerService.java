package com.shadyblog.service;

import java.io.IOException;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.shady4j.framework.annotation.Service;
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
	 * 添加新文章
	 */
	@SuppressWarnings("resource")
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
			KeywordMapper keywordMapper = sqlSession.getMapper(KeywordMapper.class);
			AkMapperMapper akMapperMapper = sqlSession.getMapper(AkMapperMapper.class);
			//若未设置关键字，则放入其它分类中
			if(keywords.equals("")) keywords = "其它";
			String[] keywordArr = keywords.split(",");
			for(String keywordName : keywordArr) {
				Integer keywordId = keywordMapper.selectIdByName(keywordName);
				LOGGER.info("keywordId1:"+keywordId);
				if(keywordId != null) {
					if(keywordMapper.updateAmountByKeywordId(keywordId) == 0) {
						throw new IOException("update keyword's amount failure!");
					}
				} else {
					Keyword keyword = new Keyword(keywordName, 1, date);
					//此insert返回主键ID至传入的实体对象中
					keywordMapper.insert(keyword);
					keywordId = keyword.getKeywordId();
					if(keywordId == null) throw new IOException("insert keyword failure!");
					LOGGER.info("keywordId2:"+keywordId);
				}
				//建立文章id与关键字id的映射
				if(akMapperMapper.insert(new AkMapper(articleId, keywordId)) == 0) {
					throw new IOException("insert akmapper failure!");
				}
			}
			//事务提交
			sqlSession.commit();
		} catch (IOException e) {
			//异常回滚
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			DBAccessUtil.closeSqlSession(sqlSession);
		}
		return true;
	}
	
//	public static void main(String[] args) {
//		new ManagerService().addArticle(new Article("1aaa", "1ssss"), new Content("1mdmd", "1html"), "1zzz,1xxx,1ccc");
//	}
}
