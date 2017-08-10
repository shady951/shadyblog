package com.shadyblog.controller;

import java.util.List;

import org.shady4j.framework.annotation.Behavior;
import org.shady4j.framework.annotation.Controller;
import org.shady4j.framework.annotation.Inject;
import org.shady4j.framework.bean.Param;
import org.shady4j.framework.bean.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shadyblog.pojo.Article;
import com.shadyblog.pojo.Content;
import com.shadyblog.service.NormalService;

@Controller
public class NormalController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NormalController.class);
	
	@Inject
	NormalService normalService;
	
	@Behavior(method="get", path="/index")
	public View index(Param param) { 
		LOGGER.info("method in index");
//		List<Article> articleList = normalService.selectAllArticleInOnePage(1);
//		return new View("test.jsp").addModel("articleList", articleList);
		return new View("test.jsp");
	}
	
	@Behavior(method="get", path="/content")
	public View content(Param param) { 
		LOGGER.info("method in content");
		Content content = normalService.selectContentByArticleId(9);
		return new View("testcontent.jsp").addModel("content", content);
	}
}
