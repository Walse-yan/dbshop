package servlet;

import Utils.ResponseJsonUtils;
import service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class delCartServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    public delCartServlet()
    {
        super();
    }

    protected  void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.service(request, response);
        request.setCharacterEncoding("UTF-8");

        Service service = new Service();
        HttpSession session = request.getSession();
        Map<Object, Object> userInformation = (Map<Object, Object>) (session.getAttribute("user"));
        String requestUserName = request.getParameter("userName");
        System.out.println("request中的用户名是" + requestUserName);
        System.out.println();
        String userName = userInformation.get("userName").toString();
        System.out.println("session中的用户名是：" + userName);
        boolean flag = false;
        if (userName.equals(requestUserName)) {
            try {
                flag = service.delCart(userName);
            } catch (SQLException e) {
                flag = false;
            }
        }
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("success", flag);
        System.out.println("清空购物车是否成功：" + flag);
        try {
            ResponseJsonUtils.json(response, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
        return;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post响应");
    }
}
