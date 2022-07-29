package com.qf.dao.impl;

import com.qf.dao.TypeDao;
import com.qf.entity.Goods;
import com.qf.entity.Type;
import com.qf.utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class TypeDaoImpl implements TypeDao {

    private QueryRunner queryRunner=new QueryRunner(DBUtil.getDateSource());

    @Override
    public List<Type> getGoodsType() {
        String sql="SELECT * FROM tb_goods_type WHERE parent=0";
        try {
           return  queryRunner.query(sql,new BeanListHandler<>(Type.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Goods> getGoodsDetails(String tid) {
        String sql="SELECT * FROM tb_goods WHERE tid=?";
        try {
            return queryRunner.query(sql,new BeanListHandler<>(Goods.class),tid);
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public List<Goods> getGoodsDetails() {
        String sql="SELECT * FROM tb_goods ";
        try {
            return queryRunner.query(sql,new BeanListHandler<>(Goods.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }


}
