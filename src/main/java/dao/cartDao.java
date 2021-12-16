package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.shopCartBean;
import beans.shopToCustomerBean;

public class cartDao
{
	//加入购物车
	public Boolean addCart(Connection conn, shopCartBean shopCart) throws SQLException
	{
		String sql;
		sql = "select * from shopcart where userName= ? and p_id= ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, shopCart.getUserName());
		pstmt.setString(2, shopCart.getP_id());
		ResultSet resultSet = pstmt.executeQuery();
		Boolean flag = false;
		int res_num = 0;   //剩余数是0
		if (resultSet.next())
		{
			res_num += resultSet.getInt("p_nums");
			flag = true;    //该用户购物车存在这个商品
		}
		System.out.println("该记录是否存在：" + flag);
		if (flag)     //购物车商品存在
		{
			int p_nums = shopCart.getP_nums() + res_num;
			if (p_nums == 0)    //删除商品
			{
				sql = "delete from shopcart where userName= ? and p_id= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, shopCart.getUserName());
				pstmt.setString(2, shopCart.getP_id());
				System.out.println(pstmt.toString());
				int rs = pstmt.executeUpdate();
				return rs>0?true:false;
			}
			else          //增加商品
			{
				sql = "update shopcart set p_nums= ? where userName= ? and p_id= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(2, shopCart.getUserName());
				pstmt.setString(3, shopCart.getP_id());
				pstmt.setInt(1, p_nums);
				System.out.println(pstmt.toString());
				int rs = pstmt.executeUpdate();
				return rs>0?true:false;
			}
		}
		else     //商品不存在，则新建
		{
			if (shopCart.getP_nums() > 0)
			{
				sql = "insert into shopcart Values(?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, shopCart.getUserName());
				pstmt.setString(2, shopCart.getP_id());
				pstmt.setInt(3, shopCart.getP_nums());
				System.out.println(pstmt.toString());

				int rs = pstmt.executeUpdate();
				return rs>0?true:false;
			}
			else 
			{
				return false;
			}
		}
	}

	//通过用户名获取购物车
	public List<shopToCustomerBean> getCart(Connection conn, String userName) throws SQLException
	{
		String sql;
		sql = "select shopcart.p_id,shopcart.p_nums,p_name,p_photo,p_price,p_category,p_describe from shopcart,products where userName= ? and shopcart.p_id = products.p_id";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userName);
		System.out.println(pstmt.toString());
		ResultSet rs = pstmt.executeQuery();
		List<shopToCustomerBean> Lists = new ArrayList<shopToCustomerBean>();
		while (rs.next())
		{
			shopToCustomerBean shop = new shopToCustomerBean();
			shop.setP_id(rs.getString("p_id"));
			shop.setP_nums(rs.getInt("p_nums"));
			shop.setP_name(rs.getString("p_name"));
			shop.setP_photo(rs.getString("p_photo"));
			shop.setP_price(rs.getInt("p_price"));
			shop.setP_category(rs.getString("p_category"));
			shop.setP_describe(rs.getString("p_describe"));
			Lists.add(shop);
		}
		return Lists;
	}
	//通过用户名清空购物车
	public boolean delCart(Connection conn, String userName) throws SQLException
	{
		String sql = "delete from shopcart where userName= ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userName);
		System.out.println(pstmt.toString());
		int rs = pstmt.executeUpdate();
		return rs>0?true:false;
	}
}
