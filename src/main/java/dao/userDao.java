package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import beans.userBean;

import java.util.ArrayList;
import java.util.List;

public class userDao
{
	//包含用户登录和注册访问数据库的功能
	//登录
	public userBean userLogin(Connection conn, userBean user) throws SQLException
	{
		String sql;
		PreparedStatement pstmt = null;
		System.out.println("用户名是：" + user.getPhone() + "  密码是：" + user.getPassword());
		if (user.getUserName() != "")   //采用的是用户名登录
		{
			sql = "select * from users where userName=? and password=?";
			pstmt = conn.prepareStatement(sql);     //通过JDBC进行预处理生成底层数据库命令
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
		}
		else if (user.getPhone() != "")   //采用的是手机号登录
		{
			sql = "select * from users where phone= ? and password= ?";
			pstmt = conn.prepareStatement(sql);     //通过JDBC进行预处理生成底层数据库命令
			pstmt.setString(1, user.getPhone());
			pstmt.setString(2, user.getPassword());
		}
		ResultSet rs = pstmt.executeQuery();    //获取数据库查询结果
		userBean resultUser = new userBean();    //存放获取返回用户信息
		if (rs.next())
		{
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPhone(rs.getString("phone"));
			//resultUser.setPassword(rs.getString("password"));
			resultUser.setSex(rs.getString("sex"));
			resultUser.setAge(rs.getInt("age"));
			resultUser.setAddress(rs.getString("address"));
			resultUser.setJoinTime(rs.getString("joinTime"));
		}
		//System.out.println(resultUser.getUserName());
		return resultUser;    //返回用户的所有信息，除了密码	
	}
	
	//注册
	public boolean register(Connection conn, userBean user) throws SQLException
	{
		Boolean flag = false;
		String sql = "insert into users(userName, phone, password, address, joinTime) values(?,?,?,?,?)";
		if (conn == null)
		{
			System.out.println("数据库连接失败");
			return flag;
		}
		System.out.println("I am here");
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPhone());
		pstmt.setString(3, user.getPassword());
		pstmt.setString(4, user.getAddress());
		pstmt.setString(5, user.getJoinTime());
		int result = pstmt.executeUpdate();
		if (result > 0)     //插入成功
		{
			flag = true;
		}
		return flag;
	}
	//管理员获取所有用户信息
	public List<userBean> getUsers(Connection conn) throws SQLException
	{
		String sql = "select * from users";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<userBean> usersList = new ArrayList<userBean>();
		while (rs.next())
		{
			userBean user = new userBean();
			user.setUserName(rs.getString("userName"));
			user.setPhone(rs.getString("phone"));
			user.setPassword(rs.getString("password"));
			user.setSex(rs.getString("sex"));
			user.setAge(rs.getInt("age"));
			user.setAddress(rs.getString("address"));
			user.setJoinTime(rs.getString("joinTime"));
			usersList.add(user);
		}
		return usersList;
	}
}
