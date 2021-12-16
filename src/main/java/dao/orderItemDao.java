package dao;


import beans.orderitemBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//加入商品单，删除订单
public class orderItemDao
{
    public Boolean addOrderItem(Connection conn, orderitemBean orderItem) throws SQLException
    {
        String sql = "insert into orderitem(order_id, p_id, p_nums) values(?,?,?)";
        Boolean flag = false;
        if (conn == null)
        {
            return false;
        }
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, orderItem.getOrder_id());
        pstmt.setString(2, orderItem.getP_id());
        pstmt.setInt(3, orderItem.getP_nums());
        int result = pstmt.executeUpdate();
        if (result > 0)   //插入成功
        {
            flag = true;
        }
        return flag;
    }
}
