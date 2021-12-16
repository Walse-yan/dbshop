package servlet;

import Utils.ResponseJsonUtils;
import beans.shopToCustomerBean;
import org.json.JSONArray;
import org.json.JSONObject;
import service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.swing.*;

//获取购物车列表
public class getCartServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public getCartServlet() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.service(request, response);
        request.setCharacterEncoding("UTF-8");

        //通过用户名获取购物车
        HttpSession session = request.getSession();
        Service service = new Service();
        String userName = request.getParameter("userName");
        Map<Object, Object> userData = (Map<Object, Object>) session.getAttribute("user");    //获取session的用户信息
        Map<Object, Object> map = new HashMap<Object, Object>();   //保存返回信息
        if (userName.equals(userData.get("userName")))    //用户已经登录
        {
            //返回购物车信息
            try
            {
                List<shopToCustomerBean> shopCart = service.getCart(userName);   //获取购物车物品对象数列

                JSONArray result = new JSONArray();
                for (shopToCustomerBean i:shopCart)
                {
                    JSONObject Product = new JSONObject();
                    Product.put("p_id", i.getP_id());
                    Product.put("p_nums", i.getP_nums());
                    Product.put("p_name", i.getP_name());
                    Product.put("p_photo", i.getP_photo());
                    Product.put("p_price", i.getP_price());
                    Product.put("p_category", i.getP_category());
                    Product.put("p_describe", i.getP_describe());
                    result.put(Product);
                }
                String shopCart_information = JSONObject.valueToString(result);
                map.put("shopCart", shopCart_information);
                map.put("success", true);
                map.put("reason", "获取购物车成功");
                System.out.println("获取购物车成功");
                try
                {
                    ResponseJsonUtils.json(response, map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            catch (SQLException e) {
                map.put("success", false);
                map.put("reason", "数据库繁忙");
                System.out.println("获取购物车失败，数据库问题");
                try
                {
                    ResponseJsonUtils.json(response, map);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        else
        {
            map.put("success", false);
            map.put("reason", "获取购物车失败,用户还未登录");
            System.out.println("获取购物车失败,用户还未登录");
            try
            {
                ResponseJsonUtils.json(response, map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get响应！");
        return;
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.doGet(request, response);    //md原来这里错了
        System.out.println("post响应！");
    }
}
