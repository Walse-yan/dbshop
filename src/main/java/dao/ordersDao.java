package dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import beans.orderToCustomerBean;
import beans.orderitemBean;
import beans.userBean;

public class ordersDao
{
    public Boolean addOrders(Connection conn, orderToCustomerBean order) throws SQLException
    {
        Boolean flag = false;


        String sql = "select max(order_id) as nums from orders";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet resultSet = pstmt.executeQuery(sql);

        //设置订单号
        if (resultSet.next())
        {
            //将结果补充到12位作为p_id
            String last = resultSet.getString("nums");
            if (last != null)
            {
                BigInteger last_oid = new BigInteger(last);
                BigInteger add_oid = new BigInteger("1");
                String order_id = (last_oid.add(add_oid)).toString();
                while (order_id.length() < 12)
                {
                    order_id = "0" + order_id;
                }
                order.setOrder_id(order_id);
            }
            else
            {
                order.setOrder_id("000000000000");
            }
        }
        else
        {
            order.setOrder_id("000000000000");
        }
        System.out.println("这里暂时没有问题");
        //分成两个文件存放订单信息，一个是orders存放订单，orderitem存放每个订单中的物品购买项
        sql= "insert into orders(userName, order_id, sum_price, order_status, order_time) Values(?,?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, order.getUserName());
        pstmt.setString(2, order.getOrder_id());
        pstmt.setInt(3, order.getSum_price());
        pstmt.setBoolean(4, order.getOrder_status());
        pstmt.setString(5, order.getOrder_time());
        int result = pstmt.executeUpdate();

        if (result > 0)   //插入成功
        {
            flag = true;
        }

        Map<String, Integer> orderItems = order.getItems();
        orderitemBean orderitem = new orderitemBean();
        orderItemDao orderitemD = new orderItemDao();
        for (Map.Entry<String, Integer> entry: orderItems.entrySet())
        {
            orderitem.setOrder_id(order.getOrder_id());
            orderitem.setP_id(entry.getKey());
            orderitem.setP_nums(entry.getValue());
            flag = orderitemD.addOrderItem(conn, orderitem);    //将每个orderitemBean添加到数据库
        }
        return flag;
    }

    //用户名获取订单信息(用户名、订单号、总价、订单时间、支付状态、（商品名+商品ID）及其价格)
    public List<orderToCustomerBean> getOrder(Connection conn,  String userName) throws SQLException
    {
        String sql = "select * from orders where userName= ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userName);
        System.out.println(pstmt.toString());
        ResultSet rs = pstmt.executeQuery();
        List<orderToCustomerBean> orderList = new ArrayList<orderToCustomerBean>();
        while (rs.next())
        {
            orderToCustomerBean order = new orderToCustomerBean();
            order.setUserName(rs.getString("userName"));
            order.setOrder_id(rs.getString("order_id"));
            order.setSum_price(rs.getInt("sum_price"));
            order.setOrder_status(rs.getBoolean("order_status"));
            order.setOrder_time(rs.getString("order_time"));


            //获取订单下的子订单项
            sql = "select orderitem.p_id, p_name, orderitem.p_nums from orderitem, products where order_id= ? and orderitem.p_id = products.p_id";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, order.getOrder_id());

            ResultSet rs_items = pstmt.executeQuery();
            //System.out.println(rs_items.toString());
            Map<String, Integer> item = new HashMap<String, Integer>();
            while(rs_items.next())
            {
                item.put(rs_items.getString("p_id")+ rs_items.getString("p_name"), rs_items.getInt("p_nums"));
            }
            order.setItems(item);
            orderList.add(order);
        }

        return orderList;
    }
    //管理员获取订单信息
    public List<orderToCustomerBean> getAllOrder(Connection conn) throws SQLException
    {
        userDao userD = new userDao();
        List<userBean> usersList = userD.getUsers(conn);
        List<orderToCustomerBean> allOrdersList = new ArrayList<orderToCustomerBean>();    //保存订单结果
        for (int i = 0; i < usersList.size(); ++i)
        {
            userBean user = usersList.get(i);
            List<orderToCustomerBean> orderList = getOrder(conn, user.getUserName());
            for (int j = 0; j < orderList.size(); ++j)
                allOrdersList.add(orderList.get(j));
        }
        return allOrdersList;
    }
}
