package com.qf.dao.impl.admin;

import com.qf.dao.admin.ManageOrderDao;
import com.qf.entity.Orders;
import com.qf.entity.User;
import com.qf.utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ManageOrderDaoImpl implements ManageOrderDao {

    private QueryRunner queryRunner=new QueryRunner(DBUtil.getDateSource());

    @Override
    public List<Orders> getAllOrder() {
        String sql="SELECT * FROM tb_order";
        try {
            return queryRunner.query(sql,new BeanListHandler<>(Orders.class));
        } catch (SQLException e) {
            throw new RuntimeException("查询所有订单失败");

        }
    }

    @Override
    public User getOrderUsername(int uid) {
        String sql="SELECT username FROM tb_user WHERE id=?";
        try {
            return queryRunner.query(sql,new BeanHandler<>(User.class),uid);
        } catch (SQLException e) {
            throw new RuntimeException("查询订单所属用户名失败");

        }
    }
}
