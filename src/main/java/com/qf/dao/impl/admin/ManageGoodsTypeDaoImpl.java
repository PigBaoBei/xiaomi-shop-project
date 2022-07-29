package com.qf.dao.impl.admin;

import com.qf.dao.admin.ManageGoodsTypeDao;
import com.qf.entity.Parent;
import com.qf.entity.Type;
import com.qf.utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ManageGoodsTypeDaoImpl implements ManageGoodsTypeDao {

    private QueryRunner queryRunner=new QueryRunner(DBUtil.getDateSource());

    @Override
    public Parent getGodsTypeParentName(int parent) {
        String sql="SELECT * FROM tb_parent WHERE pid=?";
        try {
            return queryRunner.query(sql,new BeanHandler<>(Parent.class),parent);
        } catch (SQLException e) {
            throw new RuntimeException("查找所有商品分类信息失败");
        }
    }

    @Override
    public List<Type> getAllGoodsType() {
        String sql="SELECT * FROM tb_goods_type ";
        try {
            return queryRunner.query(sql,new BeanListHandler<>(Type.class));
        } catch (SQLException e) {
            throw new RuntimeException("查找所有商品分类信息失败");
        }
    }

    @Override
    public List<Type> getGoodsType() {
        String sql="SELECT * FROM tb_goods_type WHERE parent=0";
        try {
            return queryRunner.query(sql,new BeanListHandler<>(Type.class));
        } catch (SQLException e) {
            throw new RuntimeException("查询一级菜单失败");
        }
    }
}
