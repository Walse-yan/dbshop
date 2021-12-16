package servlet;

import java.io.*;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Struct;
import java.text.SimpleDateFormat;
import org.apache.commons.fileupload.*;

import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import Utils.ResponseJsonUtils;
import beans.productsBean;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.Service;

public class addProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public addProductServlet()
    {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	super.service(request, response);
    	request.setCharacterEncoding("UTF-8");
    	productsBean product = new productsBean();
    	//获取商品信息
		try
		{
			DiskFileItemFactory factory = new DiskFileItemFactory();
			File f = new File("/www/server/tomcat/img");    //设置缓存目录
			if (!f.exists())   //目录不存在则创建目录
			{
				f.mkdirs();
			}
			factory.setRepository(f);
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");

			List<FileItem> fileItems = upload.parseRequest(request);
			if (fileItems != null)
			{
				for (FileItem fileItem: fileItems)
				{
					if (fileItem.isFormField())    //是普通文本数据
					{
						String filedName = fileItem.getFieldName();
						String value = fileItem.getString("UTF-8");   //防止非文件表单中文乱码

						switch (filedName)
						{
							case "p_name":
								product.setP_name(value);
								break;
							case "p_price":
								product.setP_price(Integer.parseInt(value));
								break;
							case "p_nums":
								product.setP_nums(Integer.parseInt(value));
								break;
							case "p_category":
								product.setP_category(value);
								break;
							case "p_describe":
								product.setP_describe(value);
								break;
						}
						fileItem.delete();    //删除缓存文件
					}
					else    //非普通文件
					{
						String fileName = fileItem.getName();    //获取文件名
						File source = new File("../img/" + fileName);
						///product.setP_photo("../img/" + fileName);
						product.setP_photo("/www/server/tomcat/img/" + fileName);

						source.getParentFile().mkdirs();     //确保有目录文件
						//source.createNewFile();    //创建图片文件来缓存

						OutputStream out = new FileOutputStream(source);
						InputStream in = fileItem.getInputStream();   //获取上传的文件流
						byte[] bt = new byte[1024];
						int length = 0;
						while ((length = in.read(bt)) != -1)   //in.read()返回长度，数据保存在bt中
						{
							out.write(bt, 0, length);

						}
						in.close();
						out.close();
						fileItem.delete();
						System.out.println(fileName);
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

    	System.out.println("商品信息是：");
    	System.out.println("名字：" + product.getP_name() + "  图片：" + product.getP_photo() + "  价格：" + product.getP_price() + "  数量：" + product.getP_nums() + "  种类：" + product.getP_category() + "  描述：" + product.getP_describe());

		Date date = new Date();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");   //设置获取时间格式
    	product.setP_time(dateFormat.format(date));       //设置时间
    	
    	Service service = new Service();
    	try
		{
			Boolean flag = service.addProduct(product);   //添加商品
			
			System.out.println("增加商品是否成功：" + flag);
			String result = "增加成功";
			if (flag == false) result = "增加失败,可能是已经有重复商品，需要删除后再增加";
			
			try
			{
				ResponseJsonUtils.json(response, result);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
    	catch (SQLException e)
		{
			try 
			{
				ResponseJsonUtils.json(response, "连接数据库失败，无法增加商品信息");
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
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
		System.out.println("post响应");
	}

}
