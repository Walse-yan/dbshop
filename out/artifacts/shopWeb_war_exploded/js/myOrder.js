$(function (){
    var htmlStr = "";
    htmlStr += `<div class="title">
        <table>
            <tr>
                <td width=10%>订单号</td>
                <td width=60%>订单详情</td>
                <td width=10%>总价</td>
                <td width=10%>订单状态</td>
                <td width=10%>订单时间</td>
            </tr>
        </table>
    </div>`;
    if (isLogin())
    {
        var userName = getCookie("userName");
        $.ajax({
            url: "../getOrder",
            async: false,     //同步操作
            type: "post",
            dataType:"json",
            data: {
                "userName": userName
            },
            success:function (data)
            {
                if (data.success){
                    var orders = JSON.parse(data.orders);
                    for (var i = 0; i < orders.length; i++)
                    {
                        var order_id =  orders[i].order_id;
                        var order_item = orders[i].order_item;
                        var sum_price = orders[i].sum_price;
                        var order_status = orders[i].order_status;
                        var order_time = orders[i].order_time;
                        var status_str = "未支付";
                        if (order_status)
                        {
                            status_str = "已支付";
                        }
                        htmlStr += `<div class="item">
                                    <table>
                                    <tr>
                                        <td width=10%>${order_id}</td>
                                        <td width=60%>${order_item}</td>
                                        <td width=10%>${sum_price}</td>
                                        <td width=10%>${status_str}</td>
                                        <td width=10%>${order_time}</td>
                                    </tr>
                                    </table>
                                    </div>`;
                    }
                }
                else
                {
                    htmlStr += `<div>{$data.reason}</div>`;
                }
            },
            error:function (data)
            {
                alert("连接服务器失败");
            }
        })
    }
    else
    {
        htmlStr += "<div>请先登录</div>\n";
    }
    document.querySelector('.order').innerHTML = htmlStr;

});