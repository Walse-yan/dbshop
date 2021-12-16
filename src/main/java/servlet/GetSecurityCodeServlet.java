package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Utils.ResponseJsonUtils;
import Utils.mailUtils;

/**
 * Servlet implementation class GetSecurityCodeServlet
 */
public class GetSecurityCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetSecurityCodeServlet()
	{
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		super.service(request, response);
		request.setCharacterEncoding("UTF-8");

		String address = request.getParameter("address");    //邮箱地址
		int code = (int)((Math.random() * 9 + 1) * 100000);
		String mailMsg = "新用户注册\n注册验证码：" + code;
		System.out.println(mailMsg);
		HttpSession session = request.getSession();
		session.setAttribute("securityCode", code);	   //保存验证码
		boolean flag = mailUtils.sendMail(address, mailMsg);
		Map<Object, Object> map = new HashMap<Object, Object>();
		if (flag)   //发送成功
		{
			map.put("success", true);
			map.put("reason", "注册成功");
			try {
				ResponseJsonUtils.json(response, map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			map.put("success", false);
			map.put("reason", "邮件发送失败");
			try {
				ResponseJsonUtils.json(response, map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("post响应");
	}

}
