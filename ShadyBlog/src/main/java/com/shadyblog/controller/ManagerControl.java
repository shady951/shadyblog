package com.shadyblog.controller;

import java.util.Map;

import org.shady4j.framework.annotation.Behavior;
import org.shady4j.framework.annotation.Controller;
import org.shady4j.framework.bean.Data;
import org.shady4j.framework.bean.FileParam;
import org.shady4j.framework.bean.Param;
import org.shady4j.framework.bean.View;
import org.shady4j.framework.helper.ServletHelper;
import org.shady4j.framework.helper.UploadHelper;
import org.shady4j.framework.util.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shadyblog.dto.EditormdImage;
import com.shadyblog.util.FileRenameUtil;

@Controller
public class ManagerControl {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ManagerControl.class);
	
	@Behavior(method="get", path="/manager/writearticle")
	public View writearticle(Param param) {
		LOGGER.info("method in writearticle()");
		return new View("writearticle.jsp");
	}
	
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
	
	@Behavior(method="post", path="/manager/uploadarticle")
	public View uploadarticle(Param param) {
		Map<String, Object> map = param.getFieldMap();
		for(Map.Entry<String, Object> me : map.entrySet()) {
			System.out.println("key:"+me.getKey());
			System.out.println("value:"+me.getValue());
			if(me.getValue() instanceof String) {
				System.out.println("this is string!!!!!!");
			}
		}
		if(CollectionUtil.isNotEmpty(map)) {
			return new View("test.jsp").addModel("editormd", String.valueOf(map.get("editorhtml")));
		}
		System.out.println("map is empty!");
		return null;
	}
}
