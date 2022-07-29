package com.qf.service;

import com.qf.entity.User;

import javax.servlet.ServletContext;
import java.sql.SQLException;

public interface UserService {

    /**
     * 检验用户名合法性
     * @param username
     * @return
     */
    String checkUsername(String username);

    String checkPassword(String password);

    boolean registerUser(User user, ServletContext servletContext) throws SQLException;

    boolean active(String email, String code);

    String contrastPassword(String password, String checkPassword);

    String checkUserStatus(String username);
}
