package com.qf.dao.impl;

import com.qf.dao.GoodsDao;
import com.qf.entity.Goods;
import com.qf.utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class GoodsDaoImpl implements GoodsDao {

    private QueryRunner queryRunner=new QueryRunner(DBUtil.getDateSource());

    @Override
    public Long getCurrent(String tid) {
        String sql="SELECT count(1) FROM tb_goods WHERE tid=?";
        try {
          return   queryRunner.query(sql,new ScalarHandler<>(),tid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Goods> getGoodsDetails(String tid, long index, Long currentSize) {
        String sql="SELECT * FROM tb_goods WHERE tid=? limit ?,?";
        try {
           return queryRunner.query(sql,new BeanListHandler<>(Goods.class),tid,index,currentSize);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long getCurrent() {
        String sql="SELECT count(1) FROM tb_goods";
        try {
            return   queryRunner.query(sql,new ScalarHandler<>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Goods> getGoodsDetails( long index, Long currentSize) {
        String sql="SELECT * FROM tb_goods  limit ?,?";
        try {
            return queryRunner.query(sql,new BeanListHandler<>(Goods.class),index,currentSize);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Goods getGoodsById(int id) {
        String sql="SELECT * FROM tb_goods WHERE id=?";
        try {
            return queryRunner.query(sql,new BeanHandler<>(Goods.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
