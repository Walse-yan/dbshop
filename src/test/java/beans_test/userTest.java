package beans_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

import dao.userDao;
import beans.userBean;
public class userTest
{
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/shopdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

	static final String USER = "root";
	static final String PASS = "abc123456";
	
	public static void main(String[] args)
	{
		Connection conn = null;
		Statement stmt = null;
		userDao testUsers = new userDao();
		try
		{
			try
			{
				Class.forName(JDBC_DRIVER);
			} 
			catch (ClassNotFoundException e)
			{
			}
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			
			userBean testUserBean = new userBean();
			testUserBean.setPhone("15975526586");
			testUserBean.setPassword("20010502");
			System.out.println("OK");
			userBean resultUsers = new userBean();
			resultUsers = testUsers.userLogin(conn,  testUserBean);
			System.out.println("用户名：" + resultUsers.getUserName() + "  账户：" + resultUsers.getPhone() + "  密码：" + resultUsers.getPassword());
		}
		catch (SQLException e)
		{
			System.out.println("连接失败，得不到用户信息");
		}
		try
		{
			try
			{
				Class.forName(JDBC_DRIVER);
			} 
			catch (ClassNotFoundException e)
			{
			}
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			userBean testUserBean = new userBean();
			testUserBean.setUserName("YJQ");;
			testUserBean.setPhone("123456");
			testUserBean.setPassword("123456");
			Boolean flag = testUsers.register(conn, testUserBean);
			System.out.println(flag);
		} catch (SQLException e)
		{
			// TODO: handle exception
		}
	}
}
