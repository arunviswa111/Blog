package com.niit.configuration;

import javax.servlet.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//web.xml - Java based configuration.

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static final Logger logger = 
			LoggerFactory.getLogger(AppInitializer.class);
	@Override
	protected Class[] getRootConfigClasses() {
		logger.debug("Starting of the method getRootConfigClasses");
		return new Class[] { AppConfig.class, WebSocketConfig.class };
	}

	@Override
	protected Class[] getServletConfigClasses() {
		logger.debug("Starting of the method getServletConfigClasses");
		return new Class[] { AppConfig.class, WebSocketConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		logger.debug("Starting of the method getServletMappings");
		return new String[] { "/" };
	}
   
	@Override
	protected Filter[] getServletFilters(){
		Filter [] singleton = { new CORSFilter() };
		return singleton;
	}
}