package com.qf.dao.impl;

import com.qf.dao.OrderDao;
import com.qf.entity.OrderDetail;
import com.qf.entity.Orders;
import com.qf.utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private QueryRunner queryRunner=new QueryRunner(DBUtil.getDateSource());

    @Override
    public int addOrder(Orders orders) {
        String sql="INSERT INTO tb_order (id,uid,money,status,aid) VALUES(?,?,?,?,?)";

        try {
            return queryRunner.update(sql,orders.getId(),
                    orders.getUid(),
                    orders.getMoney(),
                    orders.getStatus(),
                    orders.getAid());
        } catch (SQLException e) {
            throw new RuntimeException("添加订单失败");
        }
    }

    @Override
    public List<Orders> getOrder(int uid) {
        String sql="SELECT a.*,b.detail FROM tb_order a LEFT JOIN tb_address b ON a.aid=b.id WHERE a.uid=?";
        try {
            return queryRunner.query(sql,new BeanListHandler<>(Orders.class),uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}
