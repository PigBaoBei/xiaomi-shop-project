package com.qf.dao;

import com.qf.entity.Address;

import java.util.List;

public interface AddressDao {

    List<Address> getAddressList(int uid);

    int addAddress(Address address);

    Address gAddressById(int aid);

    int deleteAddress(int id);

    int updateAddressInfo(Address address);

    void setState(int uid);

    int setsDefaultState(int id);
}
