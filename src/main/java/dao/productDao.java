package dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import beans.productsBean;

//添加商品信息到数据库
public class productDao
{
	public boolean addProduct(Connection conn, productsBean product) throws SQLException
	{
		String sql;
		PreparedStatement pstmt = null;
		
		sql = "select max(p_id) as nums from products";     //查询商品表中的最大商品号
		
		pstmt = conn.prepareStatement(sql);
		ResultSet resultSet = pstmt.executeQuery(sql);
		if (resultSet.next())
		{
			//将结果补充到12位作为p_id
			String last = resultSet.getString("nums");
			if (last != null)
			{
				BigInteger last_pid = new BigInteger(last);
				BigInteger add_pid = new BigInteger("1");
				String p_id = (last_pid.add(add_pid)).toString();
				while (p_id.length() < 12)
				{
					p_id = "0" + p_id;
				}
				product.setP_id(p_id);
			}
			else
			{
				product.setP_id("000000000000");
			}
		}
		else    //设置初始为12个零
		{
			product.setP_id("000000000000");
		}
		System.out.println("商品名是：" + product.getP_name() + "  商品数量是：" + product.getP_nums());
		sql = "insert into products(p_id, p_name, p_photo, p_price, p_nums, p_category, p_describe, p_time) values(?,?,?,?,?,?,?,?)";

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, product.getP_id());
		pstmt.setString(2, product.getP_name());
		pstmt.setString(3, product.getP_photo());
		pstmt.setLong(4, product.getP_price());
		pstmt.setLong(5, product.getP_nums());
		pstmt.setString(6, product.getP_category());
		pstmt.setString(7, product.getP_describe());
		pstmt.setString(8, product.getP_time());
		System.out.println(sql);
		int result = pstmt.executeUpdate();
		Boolean flag = false;  
		if (result > 0)   //插入成功
		{
			flag = true;
		}
		return flag;
	}
	public List<productsBean> getProducts(Connection conn) throws SQLException {
		String sql = "select * from products";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<productsBean> productsList = new ArrayList<productsBean>();
		while (rs.next())
		{
			productsBean product = new productsBean();
			product.setP_id(rs.getString("p_id"));
			product.setP_name(rs.getString("p_name"));
			product.setP_price(rs.getInt("p_price"));
			product.setP_nums(rs.getInt("p_nums"));
			product.setP_photo(rs.getString("p_photo"));
			product.setP_category(rs.getString("p_category"));
			product.setP_describe(rs.getString("p_describe"));
			product.setP_time(rs.getString("p_time"));
			productsList.add(product);
		}
		return productsList;
	}
}
