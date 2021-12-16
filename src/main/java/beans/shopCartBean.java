package beans;
//购物车
public class shopCartBean
{
	private String userName;
	private String p_id;
	private int p_nums;
	
	public String getUserName()
	{
		return userName; 
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getP_id()
	{
		return p_id;
	}
	public void setP_id(String p_id)
	{
		this.p_id = p_id;
	}
	public int getP_nums()
	{
		return p_nums;
	}
	public void setP_nums(int p_nums)
	{
		this.p_nums = p_nums;
	}
}
