package com.qf.listeners;

import com.qf.utils.ServletContextUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletContextListeners implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        ServletContextUtil.setServletContext(servletContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
