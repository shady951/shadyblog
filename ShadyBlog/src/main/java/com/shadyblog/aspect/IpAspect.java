package com.shadyblog.aspect;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.shady4j.framework.annotation.Aspect;
import org.shady4j.framework.annotation.Inject;
import org.shady4j.framework.helper.ServletHelper;
import org.shady4j.framework.proxy.AspectProxy;

import com.shadyblog.service.IpService;
import com.shadyblog.util.IpUtil;

@Aspect(Value="com.shadyblog.controller.NormalController")
public class IpAspect extends AspectProxy{

	@Inject
	IpService ipService;
	
	//记录客户端IP操作
	@Override
	public void before(Class<?> targetClass, Method targetMethod, Object[] methodParams) throws Throwable {
		String methodName = targetMethod.getName();
		HttpServletRequest request = ServletHelper.getRequest();
		String ip = IpUtil.getip(request);
		ipService.insertAndUpdateIp(ip, methodName);
	}
	
}
