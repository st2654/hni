package org.hni.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class HNIApplicationInitalizer implements WebApplicationInitializer {

	 @Override
	    public void onStartup(ServletContext container) {
	        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
	        appContext.setConfigLocation("/WEB-INF/spring/dispatcher-config.xml");

	        ServletRegistration.Dynamic registration = container.addServlet("dispatcher", new DispatcherServlet(appContext));
	        registration.setLoadOnStartup(1);
	        registration.addMapping("/");
	    }
}
