package com.qf.dao.impl;

import com.qf.dao.OrderDetailDao;
import com.qf.entity.*;
import com.qf.utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {

    private QueryRunner queryRunner=new QueryRunner(DBUtil.getDateSource());

    @Override
    public Orders getOrderById(String id) {
        String sql="SELECT * FROM tb_order WHERE id=?";
        try {
            return queryRunner.query(sql,new BeanHandler<>(Orders.class),id);
        } catch (SQLException e) {
            throw new RuntimeException("查询订单信息失败");
        }
    }

    @Override
    public Address getAddressById(int aid) {
        String sql="SELECT * FROM tb_address WHERE id=?";

        try {
            return queryRunner.query(sql,new BeanHandler<>(Address.class),aid);
        } catch (SQLException e) {
            throw new RuntimeException("查询地址信息失败");
        }
    }

    @Override
    public List<OrderDetail> getOrderDetailById(String oid) {
        String sql="SELECT * FROM tb_orderdetail WHERE oid=?";
        try {
            return queryRunner.query(sql,new BeanListHandler<>(OrderDetail.class),oid);
        } catch (SQLException e) {
            throw new RuntimeException("查询订单详情失败");
        }
    }

    @Override
    public Goods getGoodById(int id) {
        String sql="SELECT * FROM tb_goods WHERE id=?";
        try {
            return queryRunner.query(sql,new BeanHandler<>(Goods.class),id);
        } catch (SQLException e) {
            throw new RuntimeException("查询商品信息失败");
        }
    }

    @Override
    public void addOrderDetail(String orderId, Integer pid, Integer num, Integer money) {
        String sql="INSERT INTO tb_orderdetail (oid,pid,num,money) VALUES(?,?,?,?)";
        try {
            queryRunner.update(sql,orderId,pid,num,money);
        } catch (SQLException e) {
            throw new RuntimeException("添加订单详细介绍失败");
        }
    }


}
