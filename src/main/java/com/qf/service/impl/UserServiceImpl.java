package com.qf.service.impl;

import com.qf.dao.UserDao;
import com.qf.dao.impl.UserDaoImpl;
import com.qf.entity.JsonResult;
import com.qf.entity.User;
import com.qf.service.UserService;
import com.qf.utils.*;
import com.qf.utils.bcrypt.BCryptPasswordEncoder;

import javax.servlet.ServletContext;
import java.sql.SQLException;
import java.util.Objects;

public class UserServiceImpl implements UserService {

    private UserDao userDao=new UserDaoImpl();

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public String checkUsername(String username) {
        //判断用户名的合法性
        if (Objects.isNull(username) || username.length()==0){
            return JSON.stringify(JsonResult.error("用户名不合法"));
        }
        if (userDao.checkUsername(username)==1){
            return JSON.stringify(JsonResult.error("用户已经存在"));
        }
        return JSON.stringify(JsonResult.success("用户名可以使用"));

    }

    @Override
    public String checkPassword(String password){
        if (Objects.isNull(password) || password.length()==0){
            return JSON.stringify(JsonResult.error("请输入密码"));
        }
        if (password.length()<6){
            return JSON.stringify(JsonResult.error("请输入6位以上字符"));
        }
       return JSON.stringify(JsonResult.success("密码可以使用"));
    }

    @Override
    public boolean registerUser(User user, ServletContext servletContext) throws SQLException {
        if(!AccountValidatorUtil.isEmail(user.getEmail())){
            return false;
        }
        // 针对明文密码处理.依然采用hash算法.不同的是,我加盐了.
        // 脱敏处理.防止暴力破解.

        String realPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(realPassword);

        user.setStatus(Constants.USER_NOT_ACTIVE);
        user.setRole(Constants.ROLE_CUSTOMER);
        user.setCode(MD5Utils.md5(user.getUsername()));
        int result = userDao.registerUser(user);
        // 当前注册的用户保存起来
        servletContext.setAttribute("userNow", user);
        return result == 1;
    }

    @Override
    public boolean active(String email, String code) {
        return userDao.active(email,code)==1;
    }

    @Override
    public String contrastPassword(String password, String checkPassword) {
       if (Objects.isNull(checkPassword)||checkPassword.length()==0 ){
            checkPassword(checkPassword);
       }
       if (!password.equals(checkPassword)){
           return JSON.stringify(JsonResult.error("密码不一致"));
       }
        return JSON.stringify(JsonResult.success("确认密码一致"));
    }

    @Override
    public String checkUserStatus(String username) {
        // 参数校验
        // 参数校验
        // 参数校验
        // 参数校验
        User user = userDao.checkUserStatus(username);
        // 1. 判断是否为,如查是空,则表示该账号不存在.
        if(Objects.isNull(user)){
            return JSON.stringify(JsonResult.error("账号不存在", "http://localhost:8080/register.jsp"));
        }else{
            // 存储域对象,
            // 工具类, ServletContextUtil.getMyServletContext()返回值是ServletContext对象.
            // 未激活
            ServletContextUtil.getMyServletContext().setAttribute("userNow",user);
            if(Constants.NOT_HAS_USER.equals(user.getStatus())){
                return JSON.stringify(JsonResult.error("账号未激活, 请去激活去!", "http://localhost:8080/user?method=sendEmailTo"));
            }else if(Constants.USER_NOT_NORMAL.equals(user.getStatus())){
                return JSON.stringify(JsonResult.error("账号异常,请联系管理理"));
            }
        }
        return JSON.stringify(JsonResult.success("账号正常，可以执行登陆"));
    }
}

