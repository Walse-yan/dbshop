package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import Utils.ResponseJsonUtils;
import beans.adminBean;
import org.json.JSONObject;
import service.Service;

public class adminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public adminLoginServlet()
	{
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		super.service(request, response);
		request.setCharacterEncoding("UTF-8");

    	//只允许账户登录
    	String m_account = request.getParameter("m_account");    //获取账户
    	String m_password = request.getParameter("m_password");   
    	
    	Service service = new Service();
    	adminBean admin = new adminBean();
    	
    	admin.setM_account(m_account);
    	admin.setM_password(m_password);

		Map<Object, Object> map = new HashMap<Object, Object>();
    	try
		{
			admin = service.adminLogin(admin);

			if (admin.getM_name() != null)    //登录成功
			{
				System.out.println("登录的管理员是：" + admin.getM_name());
				try
				{
					Map<Object, Object> data = new HashMap<Object, Object>();
					data.put("m_name",admin.getM_name());
					data.put("m_account",admin.getM_account());

					//会话，保存用户信息
					HttpSession session = request.getSession();
					session.setAttribute("admin", data);

					String admin_information = JSONObject.valueToString(data);    //把用户信息转为json格式
					map.put("admin", admin_information);    //返回管理员的信息
					map.put("success", true);
					map.put("reason", "登录成功");
					ResponseJsonUtils.json(response, map);
					System.out.println("登录成功");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else     //登录失败
			{
				System.out.println("登录失败，账号或密码错误");
				try
				{

					map.put("success", false);
					map.put("reason", "登录失败，账号或密码错误");
					ResponseJsonUtils.json(response, map);
					System.out.println("登录失败，账号或密码错误");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
    	catch (SQLException e)
		{
			System.out.println("数据库繁忙，请稍后再试");
			try
			{
				map.put("success", false);
				map.put("reason", "数据库繁忙，请稍后再试");
				ResponseJsonUtils.json(response, map);
				System.out.println("数据库繁忙，请稍后再试");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("adminLoginServlet的post响应");
	}

}
