package com.qf.service.impl.admin;

import com.qf.dao.admin.ManageGoodsTypeDao;
import com.qf.dao.impl.admin.ManageGoodsTypeDaoImpl;
import com.qf.entity.Parent;
import com.qf.entity.Type;
import com.qf.service.admin.ManageGoodsTypeService;

import java.util.List;

public class ManageGoodsTypeServiceImpl implements ManageGoodsTypeService {

    private ManageGoodsTypeDao manageGoodsTypeDao=new ManageGoodsTypeDaoImpl();

    @Override
    public List<Type> getAllGoodsType() {
        List<Type> types=manageGoodsTypeDao.getAllGoodsType();
        for (Type type : types) {
          Parent parent= manageGoodsTypeDao.getGodsTypeParentName(type.getParent());
          type.setParentName(parent.getName());
        }
        return types;
    }

    @Override
    public List<Type> getGoodsType() {
        return manageGoodsTypeDao.getGoodsType();
    }
}
