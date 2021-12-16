package beans;

public class adminBean
{
	private String m_name;
	private String m_account;
	private String m_password;
	
	public String getM_name()    //用户名
	{
		return m_name;
	}
	public void setM_name(String m_name)
	{
		this.m_name = m_name;
	}
	public String getM_account()   //账号
	{
		return m_account;
	}
	public void setM_account(String m_account)   
	{
		this.m_account = m_account;
	}
	public String getM_password()   //密码
	{
		return m_password;
	}
	public void setM_password(String m_password)
	{
		this.m_password = m_password;
	}
}
