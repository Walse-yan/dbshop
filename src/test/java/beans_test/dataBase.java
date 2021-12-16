package beans_test;

import java.sql.*;

public class dataBase
{
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/shopdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

	static final String USER = "root";
	static final String PASS = "abc123456";

	public static void main(String[] args)
	{
		Connection conn = null;
		Statement stmt = null;
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
			System.out.println("连接成功");
			String sql = "select * from users;";
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next())
			{
				String userName = resultSet.getString("userName");
				String account = resultSet.getString("phone");
				String password = resultSet.getString("password");
				System.out.println("用户名：" + userName + "  账户：" + account + "  密码：" + password);
			}
			resultSet.close();
            stmt.close();
            conn.close();
			
		} 
		catch (SQLException se)
		{
			System.out.println("连接失败");
			// TODO: handle exception
		}
		
		System.out.println("OK");
	}
}
