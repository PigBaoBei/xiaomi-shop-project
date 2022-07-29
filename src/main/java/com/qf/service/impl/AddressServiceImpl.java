package com.qf.service.impl;

import com.qf.dao.AddressDao;
import com.qf.dao.OrderDao;
import com.qf.dao.impl.AddressDaoImpl;
import com.qf.dao.impl.OrderDaoImpl;
import com.qf.entity.Address;
import com.qf.entity.Orders;
import com.qf.service.AddressService;

import java.util.List;

public class AddressServiceImpl implements AddressService {

    private AddressDao addressDao=new AddressDaoImpl();



    @Override
    public List<Address> getAddressList(String uid) {
        return addressDao.getAddressList(Integer.parseInt(uid));
    }

    @Override
    public boolean addAddress(Address address,String uid) {
        //默认订单创建的时候为未支付状态
        if ("".equals(address.getName()) || address.getName()==null){
            return false;
        }
        if ("".equals(address.getDetail()) || address.getDetail()==null){
            return false;
        }
        if (address.getPhone().length()!=11 || "".equals(address.getPhone())){
            return false;
        }
        address.setState(0);
        address.setUid(Long.parseLong(uid));
        int result = addressDao.addAddress(address);
        return result==1;
    }

    @Override
    public Address gAddressById(int id) {
        return addressDao.gAddressById(id);
    }

    @Override
    public boolean deleteAddress(String id) {
        return addressDao.deleteAddress(Integer.parseInt(id))==1;
    }

    @Override
    public boolean update(String id, String name, String phone, String detail) {
        Address address = addressDao.gAddressById(Integer.parseInt(id));
        address.setName(name);
        address.setPhone(phone);
        address.setDetail(detail);
       return addressDao.updateAddressInfo(address)==1;
    }

    @Override
    public boolean setDefaultState(String id,int uid) {
        //在所有的地址中应该只存在一个默认地址，所以设置默认地址前我们将所有的地址都置为非默认状态
         addressDao.setState(uid);
      return  addressDao.setsDefaultState(Integer.parseInt(id))==1;

    }

}
