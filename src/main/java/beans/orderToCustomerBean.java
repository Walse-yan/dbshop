package beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class orderToCustomerBean
{
    private String userName;
    private String order_id;
    private int sum_price;
    private Boolean order_status;
    private String order_time;
    private Map<String, Integer> items;

    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
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
    public Map<String, Integer> getItems()
    {
        return items;
    }
    public void setItems(Map<String, Integer> items)
    {
        this.items = items;
    }
}
