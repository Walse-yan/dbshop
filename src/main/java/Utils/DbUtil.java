package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtil
{
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/shopdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	//static final String DB_URL = "jdbc:mysql://120.76.250.115:3306/shopdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

	static final String USER = "root2";
	static final String PASS = "abc123456";
	
	private static Connection conn;

	static
	{
		try
		{
			try
			{
				Class.forName(JDBC_DRIVER);
			} 
			catch (ClassNotFoundException e)
			{
				System.out.println("这里错了");
			}
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		}
		catch (Exception ex)
		{
			System.out.println("连接数据库失败");
			ex.printStackTrace();
		}
	}

	public static Connection getConnection() // 连接数据库
	{
		return conn;
	}

	public static void close(Connection con, Statement stat)
	{

		if (stat != null)
		{
			try
			{
				stat.close();
			} catch (SQLException ex)
			{
			}
		}

		if (con != null)
		{
			try
			{
				con.close();
			} catch (SQLException ex)
			{
			}
		}

	}

	public static void close(Connection con, Statement stat, ResultSet rs)
	{
		if (rs != null)
		{
			try
			{
				rs.close();
			} catch (SQLException ex)
			{
			}
		}

		if (stat != null)
		{
			try
			{
				stat.close();
			} catch (SQLException ex)
			{
			}
		}

		if (con != null)
		{
			try
			{
				con.close();
			} catch (SQLException ex)
			{
			}
		}

	}
}
