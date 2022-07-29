package com.qf.dao.admin;

import com.qf.entity.Parent;
import com.qf.entity.Type;

import java.util.List;

public interface ManageGoodsTypeDao {

    Parent getGodsTypeParentName(int parent);

    List<Type> getAllGoodsType();

    List<Type> getGoodsType();

}
