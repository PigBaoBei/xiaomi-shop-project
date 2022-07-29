package com.qf.controller.admin;

import com.qf.controller.BaseController;
import com.qf.entity.Goods;
import com.qf.entity.User;
import com.qf.service.admin.AdminService;
import com.qf.service.admin.ManageGoodsService;
import com.qf.service.impl.admin.AdminServiceImpl;
import com.qf.service.impl.admin.ManageGoodsServiceImpl;
import com.qf.utils.Constants;
import com.qf.utils.JSON;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/admin")
public class AdminController extends BaseController {

    private AdminService adminService=new AdminServiceImpl();

    private ManageGoodsService manageGoodsService=new ManageGoodsServiceImpl();

    public String login(HttpServletRequest req, HttpServletResponse resp){
        User user=new User();
        user.setUsername("root");
        user.setPassword("123456");
        req.setAttribute("admin",user);
        return Constants.FORWARD+"admin/admin.jsp";
    }

    public String getVIPUser(HttpServletRequest req, HttpServletResponse resp){
       List<User> userList= adminService.getUserByStatus(1);
       return JSON.stringify(userList);
    }

    public String getNotVIPUser(HttpServletRequest req, HttpServletResponse resp){
        List<User> userList = adminService.getUserByStatus(0);
        return JSON.stringify(userList);
    }

    public boolean deleteUser(HttpServletRequest req, HttpServletResponse resp){
        String id = req.getParameter("id");
        return adminService.deleteUser(id);
    }


    public boolean setNotVip(HttpServletRequest req, HttpServletResponse resp) {
        int status = Integer.parseInt(req.getParameter("status"));
        String id = req.getParameter("id");
        return adminService.setUserStatus(0, id);
    }

    public boolean setVip(HttpServletRequest req, HttpServletResponse resp) {
        int status = Integer.parseInt(req.getParameter("status"));
        String id = req.getParameter("id");
        return adminService.setUserStatus(1, id);
    }

    public String searchByVip(HttpServletRequest req, HttpServletResponse resp){
        String username = req.getParameter("username");
        String gender = req.getParameter("gender");
        List<User> userList=adminService.searchUser(username,gender,1);
        return JSON.stringify(userList);
    }

    public String searchByNotVip(HttpServletRequest req, HttpServletResponse resp){
        String username = req.getParameter("username");
        String gender = req.getParameter("gender");
        List<User> userList=adminService.searchUser(username,gender,0);
        return JSON.stringify(userList);
    }



    public String showGoods(HttpServletRequest req, HttpServletResponse resp){
        List<Goods> goodsList= manageGoodsService.getAllGoods();
        req.setAttribute("goodsList",goodsList);
        return Constants.FORWARD+"admin/showGoods.jsp";
    }
}
