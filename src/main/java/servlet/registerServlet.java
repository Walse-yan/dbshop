package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

import Utils.ResponseJsonUtils;
import service.Service;
import beans.userBean;

public class registerServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public registerServlet()
	{
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		super.service(request, response);
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		System.out.println("注册界面的会议ID是：" + session.getId());
		Object securityCodeReal = session.getAttribute("securityCode");
		System.out.println("真的验证码是：" + securityCodeReal.toString());

		String securityCode = request.getParameter("securityCode"); // 获取返回的验证码
		System.out.println("用户验证码是：" + securityCode);

		if (securityCode.equalsIgnoreCase(securityCodeReal.toString()))
		{
			userBean user = new userBean();
			// 获取用户名等信息
			user.setUserName(request.getParameter("username"));
			user.setPhone(request.getParameter("phone"));
			user.setPassword(request.getParameter("password"));
			user.setAddress(request.getParameter("address"));

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");   //设置获取时间格式
			Date date = new Date();
			user.setJoinTime(dateFormat.format(date));       //设置注册时间（格式是14位）
			Service service = new Service();
			try
			{
				Boolean flag = service.userRegister(user);
				System.out.println("注册是否成功：" + flag);
				Map<Object, Object> map = new HashMap<Object, Object>();
				if (flag == true) // 注册成功，跳转到首页
				{
					try
					{
						//response.setHeader("PATH", "./html/index.html");
						map.put("success", true);
						map.put("reson", "注册成功");
						ResponseJsonUtils.json(response, map);
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				else // 注册失败
				{
					try
					{
						map.put("success", false);
						map.put("reson", "注册失败，可能是手机号码已经被注册、邮箱地址被注册");
						ResponseJsonUtils.json(response, map);
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
			catch (SQLException e) // 数据库出错
			{
				try
				{
					Map<Object, Object> map = new HashMap<Object, Object>();
					map.put("success", false);
					map.put("reason", "注册失败，可能是该用户已经被注册或者数据库请求繁忙中（稍后再试）");
					ResponseJsonUtils.json(response, map);
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		}
		else // 注册验证码错误
		{
			try
			{
				Map<Object, Object> map = new HashMap<Object, Object>();
				map.put("success", false);
				map.put("reason", "验证码填写错误");
				ResponseJsonUtils.json(response, map);
			} catch (Exception e)
			{
				e.printStackTrace();
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
		System.out.println("register的post响应");
	}

}
