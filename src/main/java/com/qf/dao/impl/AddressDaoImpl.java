package com.qf.dao.impl;

import com.qf.dao.AddressDao;
import com.qf.entity.Address;
import com.qf.utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class AddressDaoImpl implements AddressDao {

    private QueryRunner queryRunner=new QueryRunner(DBUtil.getDateSource());

    @Override
    public List<Address> getAddressList(int uid) {
        String sql="SELECT * FROM tb_address WHERE uid=?";
        try {
            return queryRunner.query(sql,new BeanListHandler<>(Address.class),uid);
        } catch (SQLException e) {
            throw new RuntimeException("查找地址信息失败");
        }
    }

    @Override
    public int addAddress(Address address) {
        String sql="INSERT INTO tb_address (detail,name,phone,uid,state) VALUE(?,?,?,?,?)";

        try {
            return queryRunner.update(sql,
                    address.getDetail(),
                    address.getName(),
                    address.getPhone(),
                    address.getUid(),
                    address.getState());
        } catch (SQLException e) {
            throw new RuntimeException("添加地址信息失败");
        }
    }

    @Override
    public Address gAddressById(int id) {
        String sql="SELECT * FROM tb_address WHERE id=?";
        try {
            return queryRunner.query(sql,new BeanHandler<>(Address.class),id);
        } catch (SQLException e) {
            throw new RuntimeException("查找地址信息失败");
        }
    }

    @Override
    public int deleteAddress(int id) {
        String sql="DELETE FROM tb_address WHERE id=?";
        try {
            return queryRunner.update(sql,id);
        } catch (SQLException e) {
            throw new RuntimeException("删除地址失败");
        }
    }

    @Override
    public int updateAddressInfo(Address address) {
        String sql="UPDATE tb_address SET name=? ,phone=? ,detail=? WHERE id=?";
        try {
            return queryRunner.update(sql,
                    address.getName(),
                    address.getPhone(),
                    address.getDetail(),
                    address.getId());
        } catch (SQLException e) {
            throw new RuntimeException("修改地址信息失败");

        }
    }

    @Override
    public void setState(int uid) {
        String sql="UPDATE tb_address SET state=0 WHERE uid=?";

        try {
            queryRunner.update(sql, uid);
        } catch (SQLException e) {
            throw new RuntimeException("修改所有地址状态错误");
        }
    }

    @Override
    public int setsDefaultState(int id) {
        String sql="UPDATE tb_address SET state=1 WHERE id=?";
        try {
            return queryRunner.update(sql,id);
        } catch (SQLException e) {
            throw new RuntimeException("修改默认地址失败");
        }
    }
}
