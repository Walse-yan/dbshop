
package beans;

public class userBean
{
	String userName;         //用户名
	String phone;        //用户手机号码（作为账户）
	String password;            //用户密码
	int age;                 //用户年龄
	String sex;             //用户性别
	String address;      //用户邮箱
	String joinTime;    //用户注册时间

	public userBean() 
	{
		userName = "";
		phone = "";
		password = "";
		age = 0;
		sex = "";
		address = "";
		joinTime = "";
    }

	public String getUserName()
	{
		return this.userName;
	}

	public void setUserName(String userName)
	{
		this.userName= userName;
	}

	public String getPhone()
	{
		return this.phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public int getAge()
	{
		return this.age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public String getSex()
	{
		return this.sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getAddress()
	{
		return this.address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getJoinTime()
	{
		return this.joinTime;
	}

	public void setJoinTime(String joinTime)
	{
		this.joinTime = joinTime;
	}
}
