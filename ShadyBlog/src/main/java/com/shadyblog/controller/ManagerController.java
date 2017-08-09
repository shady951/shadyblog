package com.shadyblog.controller;

import org.shady4j.framework.annotation.Behavior;
import org.shady4j.framework.annotation.Controller;
import org.shady4j.framework.annotation.Inject;
import org.shady4j.framework.bean.Data;
import org.shady4j.framework.bean.FileParam;
import org.shady4j.framework.bean.Param;
import org.shady4j.framework.bean.View;
import org.shady4j.framework.helper.ServletHelper;
import org.shady4j.framework.helper.UploadHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shadyblog.dto.EditormdImage;
import com.shadyblog.service.ManagerService;
import com.shadyblog.util.FileRenameUtil;

@Controller
public class ManagerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ManagerController.class);
	
	@Inject
	ManagerService managerService;
	
	@Behavior(method="get", path="/manager/writearticle")
	public View writearticle(Param param) { 
		LOGGER.info("method in writearticle(1)");
		return new View("/blog");
	}

	@Behavior(method="get", path="/blog")
	public View witearticl(Param param) { 
		LOGGER.info("method in writearticle(3)");
		return new View("blog.jsp");
	}
	
	/**
	 * 图片上传 
	 */
	@Behavior(method="post", path="/manager/uploadimg")
	public Data uploadimg(Param param) {
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
	public Data uploadarticle(Param param) {
//		Article article = new Article(param.getString("title"), param.getString("summary")); 
//		Content content = new Content(param.getString("editormd"), param.getString("editorhtml"));
//		if(managerService.addArticle(article, content, param.getString("keywords")))	 {
//			return new Data(new Success() {
//				boolean success = true;
//			});
//		}
		
//		Map<String, Object> map = param.getFieldMap();
//		for(Map.Entry<String, Object> me : map.entrySet()) {
//			System.out.println("key:"+me.getKey());
//			System.out.println("value:"+me.getValue());
//			if(me.getValue() instanceof String) {
//				System.out.println("this is string!!!!!!");
//			}
//		}
//		if(CollectionUtil.isNotEmpty(map)) {
//			return new View("test.jsp").addModel("editormd", String.valueOf(map.get("editorhtml")));
//		}
//		System.out.println("map is empty!");
		return null;
	}
}
