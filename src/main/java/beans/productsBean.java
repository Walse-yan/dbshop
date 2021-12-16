package beans;
//产品类
public class productsBean
{
	private String p_id;
	private String p_name;
	private String p_photo;   //图片路径
	private int p_price;  
	private int p_nums;    //商品库存
	private String p_category;    //商品类别
	private String p_describe;   //商品描述
	private String p_time;    //商品上架时间
	
	public String getP_id()
	{
		return p_id;
	}
	public void setP_id(String p_id)
	{
		this.p_id = p_id;
	}
	public String getP_name()
	{
		return p_name;
	}
	public void setP_name(String p_name)
	{
		this.p_name = p_name;
	}
	public String getP_photo()
	{
		return p_photo;
	}
	public void setP_photo(String p_photo)
	{
		this.p_photo = p_photo;
	}
	public int getP_price()
	{
		return p_price;
	}
	public void setP_price(int p_price)
	{
		this.p_price = p_price;
	}
	public int getP_nums()
	{
		return p_nums;
	}
	public void setP_nums(int p_nums)
	{
		this.p_nums = p_nums;
	}
	public String getP_category()
	{
		return p_category;
	}
	public void setP_category(String p_category)
	{
		this.p_category = p_category;
	}
	public String getP_describe()
	{
		return p_describe;
	}
	public void setP_describe(String p_describe)
	{
		this.p_describe = p_describe;
	}
	public String getP_time()
	{
		return p_time;
	}
	public void setP_time(String p_time)
	{
		this.p_time = p_time;
	}
}
