package com.qf.dao.admin;

import com.qf.entity.User;

import java.util.List;

public interface AdminDao {

    List<User> getUserByState(int status);


    int deleteUser(int parseInt);

    int setUserStatus(int status,int id);

    List<User> searchUser(String username, String gender, int status);
}
