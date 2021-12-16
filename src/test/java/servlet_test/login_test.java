package servlet_test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import beans.userBean;
import service.Service;
public class login_test
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		Service service = new Service();  
		userBean user = new userBean();   //用户bean类
		String userName = "15975526586";
		String password = "20010502";
		if(userName.length() == 11)
        {
        	user.setPhone(userName);
        }
        else
        {
        	user.setUserName(userName);
		}
        user.setPassword(password);
		user = service.userLogin(user);
		Map<String, Object> data = new HashMap<String, Object>();
        data.put("pet_name",user.getUserName());
        data.put("phone",user.getPhone());
        data.put("join_time",user.getJoinTime());
        data.put("age",user.getAge());
        data.put("sex",user.getSex());
        data.put("address",user.getAddress());
        System.out.println(data.toString());
		
	}
}
	