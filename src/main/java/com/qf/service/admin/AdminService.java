package com.qf.service.admin;

import com.qf.entity.User;

import java.util.List;

public interface AdminService {


    List<User> getUserByStatus(int status);

    boolean deleteUser(String id);

    boolean setUserStatus(int status,String id);

    List<User> searchUser(String username, String gender, int status);
}
