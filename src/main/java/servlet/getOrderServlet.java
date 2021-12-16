package servlet;

import Utils.ResponseJsonUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import beans.*;
import java.util.List;

public class getOrderServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    public getOrderServlet()
    {
        super();
    }

    protected  void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.service(request, response);
        request.setCharacterEncoding("UTF-8");

        Service service = new Service();
        String userName = request.getParameter("userName");
        Map<Object, Object> map = new HashMap<Object, Object>();
        try {
            List<orderToCustomerBean> order = service.getOrder(userName);    //通过用户名获取个人订单

            JSONArray result = new JSONArray();
            for (orderToCustomerBean i : order) {
                JSONObject myOrder = new JSONObject();
                myOrder.put("userName", i.getUserName());
                myOrder.put("order_id", i.getOrder_id());
                myOrder.put("sum_price", i.getSum_price());
                myOrder.put("order_item", JSONObject.valueToString(i.getItems()));
                myOrder.put("order_status", i.getOrder_status());
                myOrder.put("order_time", i.getOrder_time());
                result.put(myOrder);
            }
            String order_information = JSONObject.valueToString(result);
            map.put("orders", order_information);
            map.put("success", true);
            map.put("reason", "获取订单成功");
            System.out.println("获取订单成功");
            try {
                ResponseJsonUtils.json(response, map);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        catch (SQLException e) {
            map.put("success", false);
            map.put("reason", "数据库繁忙");
            System.out.println("获取订单失败，数据库问题");
            try {
                ResponseJsonUtils.json(response, map);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
        return;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post响应");
    }
}
