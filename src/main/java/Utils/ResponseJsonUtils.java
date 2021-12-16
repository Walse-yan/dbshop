package Utils;
import org.json.JSONObject;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ResponseJsonUtils
{
    public static void json(HttpServletResponse response, Object o) throws Exception    //将数据转化为json格式发出
    {   	
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String json = JSONObject.valueToString(o);
        //System.out.println(json);
        out.write(json);
        out.flush();
        out.close();
    }
}
