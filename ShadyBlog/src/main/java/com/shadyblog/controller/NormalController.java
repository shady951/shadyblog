package com.shadyblog.controller;

import java.util.List;
import java.util.Map;

import org.shady4j.framework.annotation.Behavior;
import org.shady4j.framework.annotation.Controller;
import org.shady4j.framework.annotation.Inject;
import org.shady4j.framework.bean.Param;
import org.shady4j.framework.bean.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shadyblog.dto.ArticleInfo;
import com.shadyblog.dto.PageNumInfo;
import com.shadyblog.pojo.Content;
import com.shadyblog.service.NormalService;

@Controller
public class NormalController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NormalController.class);
	
	@Inject
	private NormalService normalService;
	
	@Behavior(method="get", path="/index")
	public View index(Param param) {
		LOGGER.info("method in /index");
		int pageNum = param.getInt("pageNum");
		LOGGER.info("pageNum:"+pageNum);
		int keywordId= param.getInt("keywordId");
		Map<String, Object> pageInfoMap = normalService.getPageInfo(pageNum, keywordId);
		PageNumInfo pageNumInfo = (PageNumInfo)pageInfoMap.get("pageNumInfo");
		@SuppressWarnings("unchecked")
		List<ArticleInfo> articleInfoList = (List<ArticleInfo>)pageInfoMap.get("articleInfoList");
		return new View("blog.jsp").addModel("articleInfoList", articleInfoList).addModel("pageNumInfo", pageNumInfo);
	}
	
	/*
	@Behavior(method="get", path="/keywords")
	public View keywords(Param param) {
		LOGGER.info("method in /keywords");
		List<Keyword> keywordList = normalService.selectAllKeyword();
		return new View("keywords.jsp").addModel("keywrodList", keywordList); //TODO
	}
	 */
	
	@Behavior(method="get", path="/content")
	public View content(Param param) { 
		LOGGER.info("method in /content");
		int articleId = param.getInt("articleId");
		ArticleInfo articleInfo = normalService.getArticleInfo(articleId);
		Content content= normalService.selectContentByArticleId(articleId);
		return new View("content.jsp").addModel("articleInfo", articleInfo)
				.addModel("content", content);
	}
	
}
