package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Utils.ResponseJsonUtils;
import beans.shopCartBean;
import service.Service;

//加入购物车功能
public class addCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public addCartServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.service(request, response);
		request.setCharacterEncoding("UTF-8");

		//通过商品名加入购物车
		shopCartBean shopCart = new shopCartBean();
		shopCart.setUserName(request.getParameter("userName"));
		shopCart.setP_id(request.getParameter("p_id"));
		shopCart.setP_nums(Integer.parseInt(request.getParameter("p_nums")));

		Service service = new Service();
		try {
			Boolean flag = service.addShopCart(shopCart);

			Map<Object, Object> map = new HashMap<Object, Object>();
			if (flag == true) // 加入商品成功
			{
				try {
					//response.setHeader("PATH", "./html/index.html");
					map.put("success", true);
					map.put("reson", "加入购物车成功");

					ResponseJsonUtils.json(response, map);
					System.out.println("修改购物车成功");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else // 加入商品失败
			{
				try {
					map.put("success", false);
					map.put("reson", "加入购物车失败，非法操作");
					ResponseJsonUtils.json(response, map);
					System.out.println(shopCart.getUserName() + "尝试加入" + shopCart.getP_id() + "到购物车失败");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		catch (SQLException e) // 数据库出错
		{
			try {
				Map<Object, Object> map = new HashMap<Object, Object>();
				map.put("success", false);
				map.put("reason", "加入购物车失败，数据库繁忙，请稍后再试");
				ResponseJsonUtils.json(response, map);
				System.out.println(shopCart.getUserName() + "尝试加入" + shopCart.getP_id() + "到购物车失败，可能是商品id错误或数据库繁忙");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
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
