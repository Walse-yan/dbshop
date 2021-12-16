package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import Utils.ResponseJsonUtils;
import Utils.mailUtils;
import beans.orderToCustomerBean;
import service.Service;
import beans.shopToCustomerBean;
public class addOrderServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    public addOrderServlet()
    {
        super();
    }

    protected  void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.service(request, response);
        request.setCharacterEncoding("UTF-8");

        Service service = new Service();
        HttpSession session = request.getSession();
        Map<Object, Object> userInformation = (Map<Object, Object>) (session.getAttribute("user"));
        String requestUserName = request.getParameter("userName");
        System.out.println("request中的用户名是" + requestUserName);
        System.out.println();
        String userName = userInformation.get("userName").toString();
        System.out.println("session中的用户名是：" + userName);

        if (userName.equals(requestUserName))
        {
            Boolean flag = false;
            try {
                orderToCustomerBean order = new orderToCustomerBean();
                order.setUserName(request.getParameter("userName"));

                List<shopToCustomerBean> shopCart = service.getCart(request.getParameter("userName"));   //通过用户名获取购物车
                Integer sum_price = 0;
                //订单针对单个物品项
                Map<String, Integer> items = new HashMap<String, Integer>();
                for (shopToCustomerBean i : shopCart) {
                    sum_price += i.getP_price() * i.getP_nums();
                    items.put(i.getP_id(), i.getP_nums());
                }
                order.setSum_price(sum_price);
                order.setItems(items);
                order.setOrder_status(Boolean.parseBoolean(request.getParameter("order_status")));

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");   //设置获取时间格式
                Date date = new Date();
                order.setOrder_time(dateFormat.format(date));

                flag = service.addOrder(order);   //购买
                if (flag)   //购买成功，则要清空购物车
                {
                    flag = service.delCart(order.getUserName());
                    if (flag)
                    {
                        //发送邮件
                        String address = userInformation.get("address").toString();
                        SimpleDateFormat dateformat_shop = new SimpleDateFormat("yyyy-MM-dd hh：mm：ss");   //设置获取时间格式
                        Date date_shop = new Date();
                        String content = "尊敬的" + order.getUserName() + "先生\\女士，您在" + dateformat_shop.format(date) + "购买商品成功，感谢您的惠顾";
                        flag = mailUtils.sendMail(address, content);
                    }
                }

                Map<Object, Object> map = new HashMap<Object, Object>();
                if (flag) {
                    map.put("success", true);
                    map.put("reason", "购买成功");
                    try {
                        ResponseJsonUtils.json(response, map);
                        System.out.println("购买成功");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        map.put("success", false);
                        map.put("reason", "购买失败，可能是信息不一致");
                        ResponseJsonUtils.json(response, map);
                        System.out.println("购买失败，可能是信息不一致");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } catch (SQLException e) {
                System.out.println("购买失败，可能是数据库出错");
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("购物模块受到非法攻击");
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
