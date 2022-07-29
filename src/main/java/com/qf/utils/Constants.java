package com.qf.utils;

/**
 * 项目的常量类
 */
public class Constants {

    public static final String METHOD = "method";

    public static final String  FORWARD = "forward:";

    public static final String  REDIRECT = "redirect:";

    public static final String SEPARATOR = ":";

    public static final String INDEX = "index";

    /**
     * 定义用户模块涉及的常量
     */

    public static final String HAS_USER = "1";

    public static final String NOT_HAS_USER = "0";

    public static final String USER_ACTIVE = "1";
    // 账号异常
    public static final String USER_NOT_NORMAL = "2";

    public static final String USER_NOT_ACTIVE = "0";

    public static final int ROLE_CUSTOMER = 1;

    public static final int ROLE_ADMIN = 0;

    /**
     * 用户模块激活状态
     */
    public static final int ACTIVE_FAIL = 0;
    public static final int ACTIVE_SUCCESS = 1;
    public static final int ACTIVE_ALREADY = 2;

    /**
     * 自动登录cookie名
     */
    public static final String AUTO_NAME = "autoUser";


    // 订单状态 0 未付款，1已经付款未发货 2发货待收货 3 收货待评价 4订单完成 5 退货状态
    public static final String ORDER_NO_PAY = "0"; // 未付款
    public static final String ORDER_PAY_NO_SEND = "1"; // 给钱了未发货
    public static final String ORDER_SEND_WAIT_RECEIVE = "2"; // 已经发货,但未收获
    public static final String ORDER_RECEIVE_NO_SAY = "3"; // 收到货物未评价
    public static final String ORDER_OVER= "4"; // 订单完成
    public static final String ORDER_NO_WANT= "5"; // 退货


}
