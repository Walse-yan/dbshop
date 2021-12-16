package service;

import Utils.DbUtil;
import beans.*;
import dao.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Service {
    //用户登录
    public userBean userLogin(userBean user) throws SQLException
    {
        userDao userD = new userDao();    
        System.out.println("到达userLogin服务窗！");
        Connection conn = DbUtil.getConnection();
        return userD.userLogin(conn, user);
    }
    //用户注册
    public Boolean userRegister(userBean user) throws SQLException
    {
    	userDao userD = new userDao();
    	System.out.println("到达userRegister服务窗");
    	Connection conn = DbUtil.getConnection();
		return userD.register(conn, user);
    }
    //管理员登录
    public adminBean adminLogin(adminBean admin) throws SQLException
    {
    	adminDao adminD = new adminDao();
    	System.out.println("到达adminLogin服务窗");
    	Connection conn = DbUtil.getConnection();
    	return adminD.adminLogin(conn, admin);
    }
    //增加商品
    public Boolean addProduct(productsBean product) throws SQLException
    {
    	productDao addPro = new productDao();
    	System.out.println("到达addProduct服务窗");
    	Connection conn = DbUtil.getConnection();
    	return addPro.addProduct(conn, product);
    }
    //加入购物车（或删除）
    public Boolean addShopCart(shopCartBean shopCart) throws SQLException
    {
    	cartDao cartD = new cartDao();
    	System.out.println("到达addShopCart服务窗");
    	Connection conn = DbUtil.getConnection();
    	return cartD.addCart(conn, shopCart);
    }
    //返回购物车，按JSON格式存放
    public List<shopToCustomerBean> getCart(String userName) throws SQLException
    {
        cartDao cartD = new cartDao();
        System.out.println("到达getCart服务窗");
        Connection conn = DbUtil.getConnection();
        return cartD.getCart(conn, userName);
    }
    //用户下单
    public boolean addOrder(orderToCustomerBean orderToC) throws  SQLException
    {
        ordersDao ordersD = new ordersDao();
        System.out.println("到达addOrder服务窗");
        Connection conn = DbUtil.getConnection();
        return ordersD.addOrders(conn, orderToC);
    }
    //用户删除购物车
    public boolean delCart(String userName) throws SQLException
    {
        cartDao cartD = new cartDao();
        System.out.println("到达delCart服务窗");
        Connection conn = DbUtil.getConnection();
        return cartD.delCart(conn, userName);
    }
    //用户获取自己的所有订单
    public List<orderToCustomerBean> getOrder(String userName) throws SQLException
    {
        ordersDao ordersD = new ordersDao();
        System.out.println("到达getOrderTOUser服务窗");
        Connection conn = DbUtil.getConnection();
        return ordersD.getOrder(conn, userName);
    }
    //管理员获取所有用户信息
    public List<userBean> getAllUsers() throws SQLException
    {
        userDao userD = new userDao();
        System.out.println("到达getAllUsers服务窗");
        Connection conn = DbUtil.getConnection();
        return userD.getUsers(conn);
    }
    //管理员获取所有订单信息
    public List<orderToCustomerBean> getAllOrder() throws SQLException
    {
        ordersDao ordersD = new ordersDao();
        System.out.println("到达getAllOrder服务窗");
        Connection conn = DbUtil.getConnection();
        return ordersD.getAllOrder(conn);
    }
    //管理员获取所有商品信息
    public List<productsBean> getAllProducts() throws SQLException
    {
        productDao productD = new productDao();
        System.out.println("到达getAllProducts服务窗");
        Connection conn = DbUtil.getConnection();
        return productD.getProducts(conn);
    }
}
