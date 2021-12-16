package beans;

//展示给用户的商品信息
public class shopToCustomerBean
{
    private String p_id;
    private String p_name;   //商品的名称
    private int p_nums;   //购物的数量
    private String p_photo;
    private int p_price;   //商品单价
    private String p_category;   //商品种类
    private String p_describe;   //商品描述

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
    public int getP_nums()
    {
        return p_nums;
    }
    public void setP_nums(int p_nums)
    {
        this.p_nums = p_nums;
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
        return this.p_describe;
    }
    public void setP_describe(String p_describe)
    {
        this.p_describe = p_describe;
    }
}
