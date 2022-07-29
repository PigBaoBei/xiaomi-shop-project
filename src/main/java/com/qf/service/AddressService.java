package com.qf.service;

import com.qf.entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAddressList(String uid);

    boolean addAddress(Address address,String uid);

    Address gAddressById(int aid);

    boolean deleteAddress(String aid);

    boolean update(String id, String name, String phone, String detail);

    boolean setDefaultState(String id,int uid);
}
