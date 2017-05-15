package com.ipartek.formacion.controller.resolver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class MyMappingExceptionResolver extends SimpleMappingExceptionResolver {

	@Override
	protected String buildLogMessage(Exception ex, HttpServletRequest request) {
		
		return "Biblioteca MTG Exception: " + ex.getLocalizedMessage();
	}

	
}
