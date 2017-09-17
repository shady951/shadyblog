package com.shadyblog.aspect;

import java.lang.reflect.Method;

import org.shady4j.framework.annotation.Aspect;
import org.shady4j.framework.annotation.Inject;
import org.shady4j.framework.bean.Param;
import org.shady4j.framework.proxy.AspectProxy;

import com.shadyblog.service.IpService;

@Aspect(Value="com.shadyblog.controller.NormalController")
public class ContentAspect extends AspectProxy{

	@Inject
	IpService ipService;
	
	//记录文章浏览量
	@Override
	public void before(Class<?> targetClass, Method targetMethod, Object[] methodParams) throws Throwable {
		String methodName = targetMethod.getName();
		if(methodName.equals("content")) {
			Param param = (Param)methodParams[0];
			int articleId = param.getInt("articleId");
			ipService.updateClickNumber(articleId);
		}
	}

}
