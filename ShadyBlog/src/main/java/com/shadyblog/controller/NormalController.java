package com.shadyblog.controller;

import java.util.List;
import java.util.Map;

import org.shady4j.framework.annotation.Behavior;
import org.shady4j.framework.annotation.Controller;
import org.shady4j.framework.annotation.Inject;
import org.shady4j.framework.bean.Param;
import org.shady4j.framework.bean.View;

import com.shadyblog.dto.ArticleInfo;
import com.shadyblog.dto.PageNumInfo;
import com.shadyblog.pojo.Content;
import com.shadyblog.pojo.Keyword;
import com.shadyblog.service.NormalService;

@Controller
public class NormalController {
	
	
	@Inject
	private NormalService normalService;
	
	@Behavior(method="get", path="/")
	public View init(Param param) {
		return new View("/index");
	}
	
	@Behavior(method="get", path="/index")
	public View index(Param param) {
		int pageNum = param.getInt("pageNum");
		int keywordId= param.getInt("keywordId");
		Map<String, Object> pageInfoMap = normalService.getPageInfo(pageNum, keywordId);
		List<Keyword> keywordList = normalService.selectAllKeyword();
		PageNumInfo pageNumInfo = (PageNumInfo)pageInfoMap.get("pageNumInfo");
		@SuppressWarnings("unchecked")
		List<ArticleInfo> articleInfoList = (List<ArticleInfo>)pageInfoMap.get("articleInfoList");
		return new View("blog.jsp").addModel("articleInfoList", articleInfoList)
				.addModel("pageNumInfo", pageNumInfo).addModel("keywordList", keywordList);
	}
	
	@Behavior(method="get", path="/content")
	public View content(Param param) { 
		int articleId = param.getInt("articleId");
		ArticleInfo articleInfo = normalService.getArticleInfo(articleId);
		Content content= normalService.selectContentByArticleId(articleId);
		return new View("content.jsp").addModel("articleInfo", articleInfo)
				.addModel("content", content);
	}
	
}
