package beans;
//订单项
public class orderitemBean
{
	private String order_id;    //所属订单id
	private String p_id;   //商品名
	private int p_nums;    //商品数量
	public String getOrder_id()
	{
		return order_id;
	}
	public void setOrder_id(String order_id)
	{
		this.order_id = order_id;
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
	public void setP_nums(int p_num)
	{
		this.p_nums = p_num;
	}
}
