package com.shadyblog.controller;

import java.util.Date;
import java.util.List;

import org.shady4j.framework.annotation.Behavior;
import org.shady4j.framework.annotation.Controller;
import org.shady4j.framework.annotation.Inject;
import org.shady4j.framework.bean.Data;
import org.shady4j.framework.bean.FileParam;
import org.shady4j.framework.bean.Param;
import org.shady4j.framework.bean.View;
import org.shady4j.framework.helper.ServletHelper;
import org.shady4j.framework.helper.UploadHelper;

import com.shadyblog.dto.ArticleInfo;
import com.shadyblog.dto.EditormdImage;
import com.shadyblog.pojo.Article;
import com.shadyblog.pojo.Content;
import com.shadyblog.pojo.Iprecord;
import com.shadyblog.service.IpService;
import com.shadyblog.service.ManagerService;
import com.shadyblog.service.NormalService;
import com.shadyblog.util.DateUtil;
import com.shadyblog.util.FileRenameUtil;
import com.shadyblog.util.KeywordUtil;

@Controller
public class ManagerController {

	private static final boolean NEWARTICLE = true;
	private static final boolean OLDARTICLE = false;
	
	@Inject
	private ManagerService managerService;
	
	@Inject
	private NormalService normalService;
	
	@Inject 
	private IpService ipservice;
	
	/**
	 * 重定向
	 */
	@Behavior(method="get", path="/manager/index")
	public View managerIndex(Param param) {
		List<Iprecord> iprecordList = ipservice.findAll();
		return new View("index").addModel("iprecordList", iprecordList);
	}
	
	/**
	 * 写文章 
	 */
	@Behavior(method="get", path="/manager/writearticle")
	public View writeArticle(Param param) { 
		return new View("editarticle.jsp").addModel("isNewArticle", NEWARTICLE);
	}
	
	/**
	 * 修改文章 
	 */
	@Behavior(method="get", path="/manager/alterarticle")
	public View alterArticle(Param param) { 
		int articleId = param.getInt("articleId");
		ArticleInfo articleInfo = normalService.getArticleInfo(articleId);
		Content content= normalService.selectContentByArticleId(articleId);
		String keywords = KeywordUtil.keywordListTokeywords(articleInfo.getKeywordList());
		return new View("editarticle.jsp").addModel("isNewArticle", OLDARTICLE).addModel("articleInfo", articleInfo)
				.addModel("content", content).addModel("keywords", keywords);
	}
	
	/**
	 * 图片上传 
	 */
	@Behavior(method="post", path="/manager/uploadimg")
	public Data uploadImg(Param param) {
		//editormd图片上传默认字段名editormd-image-file
		FileParam fileParam= param.getFile("editormd-image-file");
		if(fileParam != null) {
			//文件重命名
			fileParam.setFileName(FileRenameUtil.renameFile(fileParam.getFileName()));
			//应用根目录下相对路径
			String aimPath = "/staticresources/blogfile/picture/";
			String basePath = ServletHelper.getServletContext().getRealPath(aimPath);
			UploadHelper.uploadFile(basePath, fileParam);
			String url = ServletHelper.getRequest().getContextPath() + aimPath + fileParam.getFileName();
			return new Data(new EditormdImage(1, "upload success", url));
		}
		return new Data(new EditormdImage(0, "upload failed", null));
	}
	
	/**
	 * 添加新文章 
	 */
	@Behavior(method="post", path="/manager/addarticle")
	public Data addArticle(Param param) {
		Date createTime = DateUtil.parseToDate(param.getString("createtime"));
		Article article = new Article(param.getString("title"), param.getString("summary"), createTime); 
		Content content = new Content(param.getString("editormd"), param.getString("editorhtml"));
		if(managerService.addArticle(article, content, param.getString("keywords")))	 {
			return new Data("success");
		} else {
			return new Data("failure");
		}
	}
	
	/**
	 * 更新文章 
	 */
	@Behavior(method="post", path="/manager/updatearticle")
	public Data updateArticle(Param param) {
		Date createTime = DateUtil.parseToDate(param.getString("createtime"));
		Article article = new Article(param.getInt("articleId"), param.getString("title"), param.getString("summary"), createTime); 
		Content content = new Content(param.getInt("articleId"),param.getString("editormd"), param.getString("editorhtml"));
		if(managerService.updateArticle(article, content, param.getString("keywords")))	 {
			return new Data("success");
		} else {
			return new Data("failure");
		}
	}
	
	/*
	@Behavior(method="post", path="/manager/test")
	public Data test(Param param) {
		Map<String, Object> map = param.getFieldMap();
		if(CollectionUtil.isEmpty(map)) {
			System.out.println("map is empty!");
		} else {
			for(Map.Entry<String, Object> me : map.entrySet()) {
				System.out.println("key:"+me.getKey());
				System.out.println("value:"+me.getValue());
				if(me.getValue() instanceof String) {
					System.out.println("this is string!!!!!!");
				}
			}
		}
		return null;
	}
	 */
	
}
