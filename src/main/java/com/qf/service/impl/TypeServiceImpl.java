package com.qf.service.impl;

import com.qf.dao.TypeDao;
import com.qf.dao.impl.TypeDaoImpl;
import com.qf.entity.Goods;
import com.qf.entity.Type;
import com.qf.service.TypeService;

import java.util.List;

public class TypeServiceImpl implements TypeService {

    private TypeDao typeDao= new TypeDaoImpl();

    @Override
    public List<Type> getGoodsType() {
       return typeDao.getGoodsType();
    }

    @Override
    public List<Goods> getGoodsDetails(String tid) {
        return typeDao.getGoodsDetails(tid);
    }

    @Override
    public List<Goods> getGoodsDetails() {
        return typeDao.getGoodsDetails();
    }
}
