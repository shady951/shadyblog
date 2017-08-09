package com.shadyblog.controller;

import org.shady4j.framework.annotation.Behavior;
import org.shady4j.framework.annotation.Controller;
import org.shady4j.framework.annotation.Inject;
import org.shady4j.framework.bean.Param;
import org.shady4j.framework.bean.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shadyblog.service.NormalService;

@Controller
public class NormalController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NormalController.class);
	
	@Inject
	NormalService normalServie;
	
	@Behavior(method="get", path="/index")
	public View index(Param param) { 
		LOGGER.info("method in index");
//		normalService
		return new View("test.jsp");
	}
}
