package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Utils.ResponseJsonUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import service.Service;
import beans.*;
import java.util.HashMap;
import java.util.Map;
public class loginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       

    public loginServlet() {
        super();
    }

    //只重载service请求
    @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		super.service(request, response);
		request.setCharacterEncoding("UTF-8");
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		
		Service service = new Service();   
		userBean user = new userBean();   //用户bean类
		
		//用户手机号码登录
        if(userName.length() == 11)
        {
        	user.setPhone(userName);
        }
        else    //用户名登录
        {
        	user.setUserName(userName);
		}
        user.setPassword(password);
        
        //请求service登录
		Map<Object, Object> map = new HashMap<Object, Object>();     //存放response的信息
		try
		{
			user = service.userLogin(user);
			System.out.println("用户邮箱是：" + user.getAddress());

	        //发送user的信息，处理成hash格式
	        if (user.getUserName() == "")    //没有该用户信息
	        {
	        	try
				{
					map.put("success", false);
					map.put("reason", "登录失败，账号或密码错误");
					ResponseJsonUtils.json(response, map);
					System.out.println("登录失败，账号或密码错误");
				} catch (Exception e)
				{
					e.printStackTrace();
				}
	        }
	        else
	        {
	        	try 
		        {
					Map<Object, Object> data = new HashMap<Object, Object>();
					data.put("userName",user.getUserName());
					data.put("phone",user.getPhone());
					data.put("join_time",user.getJoinTime());
					data.put("age",user.getAge());
					data.put("sex",user.getSex());
					data.put("address",user.getAddress());

					//会话，保存用户信息
					HttpSession session = request.getSession();
					session.setAttribute("user", data);

					String user_information = JSONObject.valueToString(data);    //把用户信息转为json格式

					map.put("user", user_information);   //返回用户信息，除了密码
					map.put("success", true);
					map.put("reason", "登录成功");
		        	//response.sendRedirect("./index.jsp");     //修改登录成功后的界面
		            ResponseJsonUtils.json(response, map);
					System.out.println("登录成功");
		        }catch (Exception e)     //发送response失败
		        {
		            e.printStackTrace();
		        }
	        }  
		} 
		catch (SQLException e1)    //登录失败
		{
			try
			{
				map.put("success", false);
				map.put("reason", "数据库繁忙，请稍后再试");
				ResponseJsonUtils.json(response, map);   //返回错误
				System.out.println("数据库繁忙，请稍后再试");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get响应！");
		return;
	}
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//super.doGet(request, response);    //md原来这里错了
		System.out.println("post响应！");
	}

}
