package com.qf.dao;

import com.qf.entity.User;

import java.sql.SQLException;

public interface UserDao {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    int checkUsername(String username);


    /**
     * 添加一个user
     * @param user
     * @return
     */
    int registerUser(User user) throws SQLException;

    /**
     * 改变用户的状态码，从而激活账号
     * @param email
     * @param code
     * @return
     */
    int active(String email, String code);

    User checkUserStatus(String username);
}
