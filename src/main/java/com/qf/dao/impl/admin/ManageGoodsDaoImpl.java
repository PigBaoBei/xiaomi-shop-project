package com.qf.dao.impl.admin;

import com.qf.dao.admin.ManageGoodsDao;
import com.qf.entity.Goods;
import com.qf.entity.Type;
import com.qf.utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class ManageGoodsDaoImpl implements ManageGoodsDao {

    private QueryRunner queryRunner=new QueryRunner(DBUtil.getDateSource());

    @Override
    public List<Goods> getAllGoods() {
        String sql="SELECT * FROM tb_goods";
        try {
            return queryRunner.query(sql,new BeanListHandler<>(Goods.class));
        } catch (SQLException e) {
            throw new RuntimeException("查找所有商品失败");

        }
    }

    @Override
    public Type getGoodType(int tid) {
        String sql="SELECT name FROM tb_goods_type WHERE id=?";
        try {
            return queryRunner.query(sql,new BeanHandler<>(Type.class),tid);
        } catch (SQLException e) {
            throw new RuntimeException("查找商品类型失败");

        }
    }
}
