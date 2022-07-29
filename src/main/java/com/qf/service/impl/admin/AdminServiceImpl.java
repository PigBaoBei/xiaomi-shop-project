package com.qf.service.impl.admin;

import com.qf.dao.admin.AdminDao;
import com.qf.dao.impl.admin.AdminDaoImpl;
import com.qf.entity.User;
import com.qf.service.admin.AdminService;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    private AdminDao adminDao=new AdminDaoImpl();

    @Override
    public List<User> getUserByStatus(int status) {
        return adminDao.getUserByState(status);
    }

    @Override
    public boolean deleteUser(String id) {
        return adminDao.deleteUser(Integer.parseInt(id))==1;
    }

    @Override
    public boolean setUserStatus(int status,String id) {
        return adminDao.setUserStatus(status,Integer.parseInt(id))==1;
    }

    @Override
    public List<User> searchUser(String username, String gender, int status) {

        return adminDao.searchUser(username,gender,status);
    }
}
