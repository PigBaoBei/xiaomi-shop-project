package com.qf.controller;

import com.qf.entity.PagePassword;
import com.qf.entity.User;
import com.qf.service.UserService;
import com.qf.service.impl.UserServiceImpl;
import com.qf.utils.Base64Utils;
import com.qf.utils.Constants;
import com.qf.utils.EmailUtils;
import com.qf.utils.bcrypt.BCryptPasswordEncoder;
import com.wf.captcha.utils.CaptchaUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/user")
public class UserController extends BaseController {

    private UserService userService = new UserServiceImpl();

    private PagePassword pagePassword=new PagePassword();

    private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();



    public String register(HttpServletRequest req, HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException, SQLException {
        User user = new User();
        //从请求总获取参数
        Map<String, String[]> map = req.getParameterMap();
        ServletContext servletContext = this.getServletContext();
        BeanUtils.populate(user, map);
        //调用业务层执行业务操作
        boolean success = userService.registerUser(user, servletContext);
        if (success) {
            return Constants.FORWARD + "/"+"registerSuccess.jsp";
        } else {
            req.setAttribute("msg","注册失败了，看看哪个环节出问题了");
            return Constants.FORWARD+"/message.jsp";
        }
    }

    public String activate(HttpServletRequest req, HttpServletResponse resp){
        String base64Email= req.getParameter("e");
        String base64Code= req.getParameter("c");

        String email = Base64Utils.decode(base64Email);
        String code = Base64Utils.decode(base64Code);

        boolean success=userService.active(email,code);
        if (success){
            return Constants.FORWARD+"/login.jsp";
        }else {
            return Constants.FORWARD+"/index.jsp";
        }
    }

    /**
     * 发送邮件完成激活
     * @param req
     * @param resp
     * @return
     */
    public String sendEmailTo(HttpServletRequest req, HttpServletResponse resp){
        // 1. 四件事
        ServletContext servletContext = this.getServletContext();
        User userNow = (User) servletContext.getAttribute("userNow");
        new Thread(() ->{
            EmailUtils.sendEmail(userNow);
        }).start();

        // 发邮件即可;
        // 发送邮件之后,咱们直接打开那个对应的邮件地址登录.
        String email = userNow.getEmail();
        // 1756866565@qq.com  -----> qq.com
        String suffix = email.substring(email.lastIndexOf("@") + 1);
        return Constants.REDIRECT + "https://mail." + suffix;
    }

    /**
     * 登陆
     * @param req
     * @param resp
     * @return
     */
    public String login(HttpServletRequest req, HttpServletResponse resp){
        String captcha = req.getParameter("captcha");
        ServletContext servletContext = this.getServletContext();
        User userNow = (User) servletContext.getAttribute("userNow");

        if (!CaptchaUtil.ver(captcha,req)){
            CaptchaUtil.clear(req);
            req.setAttribute("msg","验证码错误");
            //验证码错误重定向到登陆页面
            return Constants.FORWARD+"/login.jsp";
        }
        //验证码验证成功，而checkUserStatus已经校验过用户是否存在的问题，所以这里校验用户只需要将密码进行比对就可以了
        String password = req.getParameter("password");
        boolean result = bCryptPasswordEncoder.matches(password, userNow.getPassword());
        if (!result){
            req.setAttribute("msg","用户名或者密码错误");
            return Constants.FORWARD+"login.jsp";
        }
        req.setAttribute("msg","登陆成功");
        return Constants.FORWARD+"index.jsp";
    }

    /**
     * 退出登陆
     * @param req
     * @param resp
     * @return
     */
    public String logout(HttpServletRequest req, HttpServletResponse resp){
        ServletContext servletContext = this.getServletContext();
        HttpSession session = req.getSession();
        Cookie[] cookies = req.getCookies();
        servletContext.removeAttribute("userNow");
        //让session实效
        session.invalidate();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
        }

        return Constants.FORWARD+"index.jsp";
    }

    /**
     * 校验登陆用户状态
     * @param req
     * @param resp
     * @return
     */
    public String checkUserStatus(HttpServletRequest req, HttpServletResponse resp){
        //登陆应该拿到用户名，对这个用户进行查询，主要查询这个用户的状态是否能够执行登陆的操作
       String username = req.getParameter("username");
       String result = userService.checkUserStatus(username);
       return result;
    }

    /**
     * 校验用户名称是否可用
     *
     * @param req
     * @param resp
     * @return
     */
    public String checkUsername(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        System.out.println(username);
        //可以获取用户名，那么这里我们就需要判断
        String result = userService.checkUsername(username);
        return result;
    }

    /**
     * 校验密码的合法性
     * @param req
     * @param resp
     * @return
     */
    public String checkPassword(HttpServletRequest req, HttpServletResponse resp) {
        String password = req.getParameter("password");
        System.out.println(password);
        //可以获取用户名，那么这里我们就需要判断
        pagePassword.setPassword(password);
        String result = userService.checkPassword(password);
        return result;
    }

    /**
     * 校验密码的合法性
     * @param req
     * @param resp
     * @return
     */
    public String contrastPassword(HttpServletRequest req, HttpServletResponse resp) {
        String checkPassword = req.getParameter("checkPassword");
        System.out.println(checkPassword);
        String password = pagePassword.getPassword();
        System.out.println(password);
        //可以获取用户名，那么这里我们就需要判断
        String result = userService.contrastPassword(password,checkPassword);
        return result;
    }
}
