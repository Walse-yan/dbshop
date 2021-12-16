package servlet;

import Utils.ResponseJsonUtils;
import beans.productsBean;
import beans.shopToCustomerBean;
import org.json.JSONArray;
import org.json.JSONObject;
import service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class getProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public getProductServlet() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.service(request, response);
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Service service = new Service();
        Map<Object, Object> map = new HashMap<Object, Object>();   //保存返回信息
        try
        {
            List<productsBean> proList = service.getAllProducts();
            JSONArray result = new JSONArray();
            for (productsBean i:proList)
            {
                JSONObject Product = new JSONObject();
                Product.put("p_id", i.getP_id());
                Product.put("p_nums", i.getP_nums());
                Product.put("p_name", i.getP_name());
                Product.put("p_photo", i.getP_photo());
                Product.put("p_price", i.getP_price());
                Product.put("p_category", i.getP_category());
                Product.put("p_describe", i.getP_describe());
                Product.put("p_time", i.getP_time());
                result.put(Product);
            }
            String producstList = JSONObject.valueToString(result);
            map.put("productsList", producstList);
            map.put("success", true);
            map.put("reason", "获取商品表成功");
            System.out.println("获取商品表成功");
            try
            {
                ResponseJsonUtils.json(response, map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (SQLException e)
        {
            map.put("success", false);
            map.put("reason", "数据库繁忙");
            System.out.println("获取商品详情单失败，数据库问题");
            try
            {
                ResponseJsonUtils.json(response, map);
            } catch (Exception e2) {
                e2.printStackTrace();
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
