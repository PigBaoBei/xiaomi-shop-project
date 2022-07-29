package com.qf.controller;

import com.qf.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;

public class BaseController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameterMethod = req.getParameter(Constants.METHOD);
        if (Objects.isNull(parameterMethod)){
            //这里说明没有这样的操作,那么我就直接跳转登陆页
            parameterMethod = Constants.INDEX;
        }

        Class<? extends BaseController> clazz = this.getClass();
        try {
            Method method = clazz.getDeclaredMethod(parameterMethod, HttpServletRequest.class, HttpServletResponse.class);
            String result = (String) method.invoke(this, req, resp);
            //判断是不是是否经过处理后转发页面的请求
            if (result.contains(Constants.FORWARD)){
                req.getRequestDispatcher("/"+result.split(Constants.SEPARATOR)[1]).forward(req,resp);
            }else if (result.contains(Constants.REDIRECT)){
                resp.sendRedirect(req.getContextPath()+"/"+result.substring(result.lastIndexOf(Constants.SEPARATOR)+1));
            }else {
                resp.getWriter().println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.getSession().setAttribute("msg", "程序异常!请稍后再试！");
            resp.sendRedirect("/message.jsp");
        }
    }
}
