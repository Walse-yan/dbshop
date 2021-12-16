package servlet;

import beans.userBean;
import service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class getAllUsers extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public getAllUsers() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.service(request, response);
        request.setCharacterEncoding("UTF-8");

        Service service = new Service();

        List<userBean> userList = new ArrayList<userBean>();

    }
}
