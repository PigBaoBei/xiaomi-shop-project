package com.qf.dao.impl.admin;

import com.qf.dao.admin.AdminDao;
import com.qf.entity.User;
import com.qf.utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    private QueryRunner queryRunner = new QueryRunner(DBUtil.getDateSource());

    @Override
    public List<User> getUserByState(int status) {
        String sql = "SELECT * FROM tb_user WHERE status=?";
        try {
            return queryRunner.query(sql, new BeanListHandler<>(User.class), status);
        } catch (SQLException e) {
            throw new RuntimeException("查询会员失败");
        }
    }

    @Override
    public int deleteUser(int id) {
        String sql = "DELETE FROM tb_user WHERE id=?";
        try {
            return queryRunner.update(sql, id);
        } catch (SQLException e) {
            throw new RuntimeException("删除用户失败");
        }
    }

    @Override
    public int setUserStatus(int status, int id) {
        String sql = "UPDATE tb_user SET status=? WHERE id=?";

        try {
            return queryRunner.update(sql, status, id);
        } catch (SQLException e) {
            throw new RuntimeException("修改用户状态失败");

        }
    }

    @Override
    public List<User> searchUser(String username, String gender, int status) {
        String sql = "SELECT * FROM tb_user WHERE status=? ";
        StringBuilder builder = new StringBuilder(sql);

        if (!"".equals(username) && username != null) {
            //当用户传输进来的带有名字的时候将username属性直接和sql语句进行拼接
            builder.append(" AND username LIKE '%").append(username).append("%'");
        }
        if (!"".equals(gender) && gender != null) {
            builder.append(" AND gender='").append(gender+"'");
        }
        sql=builder.toString();
        try {
            return queryRunner.query(sql, new BeanListHandler<>(User.class), status);
        } catch (SQLException e) {
            throw new RuntimeException("查询用户失败");

        }

    }
}
