package com.qf.controller;

import com.qf.entity.Address;
import com.qf.entity.User;
import com.qf.service.AddressService;
import com.qf.service.impl.AddressServiceImpl;
import com.qf.utils.Constants;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/address")
public class AddressController extends BaseController{

    private AddressService addressService=new AddressServiceImpl();

    public String show(HttpServletRequest req, HttpServletResponse resp){
        ServletContext servletContext = this.getServletContext();
        User user = (User) servletContext.getAttribute("userNow");
        String uid=String.valueOf(user.getId());
       List<Address> addressList= addressService.getAddressList(uid);
        req.setAttribute("address",addressList);
        return Constants.FORWARD+"self_info.jsp";
    }


    public  String add(HttpServletRequest req, HttpServletResponse resp){
        Address address=new Address();
        ServletContext servletContext = this.getServletContext();
        User user = (User) servletContext.getAttribute("userNow");
        Map<String, String[]> map = req.getParameterMap();
        String uid=String.valueOf(user.getId());
        try {
            BeanUtils.populate(address,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        if (addressService.addAddress(address,uid)){
            //添加成功,就应该刷新页面，并且将我们填的内容进行页面上的展示
            return Constants.FORWARD+"address?method=show&uid="+address.getUid();
        }else {
            //添加失败,重新刷新到地址页面
            req.setAttribute("msg","添加地址失败，用户信息或者地址信息不合法");
            return Constants.FORWARD+"message.jsp";
        }
    }

    public String delete(HttpServletRequest req, HttpServletResponse resp){
        ServletContext servletContext = this.getServletContext();
        User user = (User) servletContext.getAttribute("userNow");
        String id = req.getParameter("id");
        if ("".equals(id) || id==null){
            return Constants.FORWARD+"message.jsp";
        }
        boolean success=addressService.deleteAddress(id);
        if (success){
            return Constants.FORWARD+"address?method=show";
        }else {
            req.setAttribute("msg","删除失败");
            return Constants.FORWARD+"message.jsp";
        }
    }

    public String update(HttpServletRequest req, HttpServletResponse resp){
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String phone =req.getParameter("phone");
        String detail=req.getParameter("detail");
        boolean success=addressService.update(id,name,phone,detail);
        if (success){
            return Constants.FORWARD+"address?method=show";
        }
        req.setAttribute("msg","修改地址信息失败，用户信息或者地址信息不合法");
        return Constants.FORWARD+"message.jsp";
    }

    public String setDefault(HttpServletRequest req, HttpServletResponse resp){
        ServletContext servletContext = this.getServletContext();
        User user = (User) servletContext.getAttribute("userNow");
        String id = req.getParameter("id");
      boolean success= addressService.setDefaultState(id,user.getId());
        if (success){
            return Constants.FORWARD+"address?method=show";
        }
        req.setAttribute("msg","设置默认地址失败");
        return Constants.FORWARD+"message.jsp";
    }
}
