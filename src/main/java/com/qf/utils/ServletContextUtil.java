package com.qf.utils;

import javax.servlet.ServletContext;

public class ServletContextUtil {
    private static ServletContext myServletContext = null;

    public static void  setServletContext(ServletContext servletContext){
        myServletContext = servletContext;
    }

    public static ServletContext getMyServletContext(){
        return myServletContext;
    }
}
