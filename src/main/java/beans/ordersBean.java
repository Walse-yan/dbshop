package beans;

public class ordersBean
{
	private String user_name;    //用户名
	private String order_id;   //订单id
	private int sum_price;    //订单总价
	private Boolean order_status;    //订单状态，是否支付
	private String order_time;   //下单时间
	
	public String getUser_name()
	{
		return user_name;
	}
	public void setUser_name(String user_name)
	{
		this.user_name = user_name;
	}
	public String getOrder_id()
	{
		return order_id;
	}
	public void setOrder_id(String order_id)
	{
		this.order_id = order_id;
	}
	public int getSum_price()
	{
		return sum_price;
	}
	public void setSum_price(int sum_price)
	{
		this.sum_price = sum_price;
	}
	public Boolean getOrder_status()
	{
		return order_status;
	}
	public void setOrder_status(Boolean order_status)
	{
		this.order_status = order_status;
	}
	public String getOrder_time()
	{
		return order_time;
	}
	public void setOrder_time(String order_time)
	{
		this.order_time = order_time;
	}
}
