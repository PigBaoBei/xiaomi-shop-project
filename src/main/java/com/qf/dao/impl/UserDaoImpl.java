package com.qf.dao.impl;

import com.qf.dao.UserDao;
import com.qf.entity.User;
import com.qf.utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final QueryRunner queryRunner = new QueryRunner(DBUtil.getDateSource());



    @Override
    public int checkUsername(String username) {
        String sql = "select username,password from tb_user where username=?";
        try {
            List<User> user = (List<User>) queryRunner.query(sql, new BeanListHandler<>(User.class), username);
            return user.size();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int registerUser(User user) {

        String sql = "insert into tb_user (username,password,email,gender,status,role,code) values(?,?,?,?,?,?,?)";
        int result = 0;
        try {
            result = queryRunner.update(sql,
                    user.getUsername(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getGender(),
                    user.getStatus(),
                    user.getRole(),
                    user.getCode());
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int active(String email, String code) {
        String sql = "update tb_user set status=1 where email=? and code=?";
        try {
            return queryRunner.update(sql,email,code);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User checkUserStatus(String username) {
        String sql= "SELECT id,username,password,email,gender,status,role,code FROM tb_user WHERE username=?";
        try {
            User user = queryRunner.query(sql, new BeanHandler<>(User.class), username);
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}
