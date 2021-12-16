package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class logoutServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    public logoutServlet()
    {
        super();
    }
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String Name = request.getParameter("userName");
        //不需要返回任何信息
        //请求注销的是普通用户
        if (Name != null)
        {
            String userName = Name;
            Map<Object, Object> userData = (Map<Object, Object>) session.getAttribute("user");
            Map<Object, Object> data = new HashMap<Object, Object>();
            if (userName.equals(userData.get("userName"))) {
                session.removeAttribute("user");    //移除用户信息
                System.out.println(userName + "注销成功");
                data.put("success", true);
            } else {
                System.out.println(userName + "用户未登录");
                data.put("success", false);
            }
        }
        else
        {
            String m_name = request.getParameter("m_name");
            if (m_name != null)
            {
                Map<Object, Object> adminData = (Map<Object, Object>) session.getAttribute("admin");
                Map<Object, Object> data = new HashMap<Object, Object>();
                if (m_name.equals(adminData.get("m_name"))) {
                    session.removeAttribute("admin");    //移除用户信息
                    System.out.println(m_name + "管理员注销成功");
                    data.put("success", true);
                } else {
                    System.out.println(m_name + "管理员未登录");
                    data.put("success", false);
                }
            }
            else
            {
                System.out.println(m_name);
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
