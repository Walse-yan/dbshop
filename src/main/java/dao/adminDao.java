package dao;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.adminBean;
public class adminDao
{
	public adminBean adminLogin(Connection conn, adminBean admin) throws SQLException
	{
		String sql;
		//只能账号登录
		sql = "select * from administrator where m_account= ? and m_password= ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, admin.getM_account());
		pstmt.setString(2, admin.getM_password());
		System.out.println(pstmt.toString());
		ResultSet rs = pstmt.executeQuery();    //获取查询结果
		
		adminBean resultAdmin = new adminBean();
		if (rs.next())
		{
			resultAdmin.setM_name(rs.getString("m_name"));
			resultAdmin.setM_account(rs.getString("m_account"));
			//resultAdmin.setM_password(rs.getString("m_password"));
		}
		return resultAdmin;    //返回除密码外的管理员信息
		
	}
}
