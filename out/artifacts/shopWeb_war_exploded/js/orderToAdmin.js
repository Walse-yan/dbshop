$(function() {
    //检查登录状态
    var isLogin = isM_Login();
    var htmlStr = "";
    htmlStr += `<div class="title">
        <table>
            <tr>
                <td width=10%>用户名</td>
                <td width=10%>订单号</td>
                <td width=50%>订单详情</td>
                <td width=10%>总价</td>
                <td width=10%>订单状态</td>
                <td width=10%>订单时间</td>
            </tr>
        </table>
    </div>`;
    if (isLogin)
    {
        $.ajax({
            url:"../getAllOrder",
            async: false,
            type: "post",
            dataType:"json",
            data:{},
            success:function (data)
            {
                if (data.success)
                {
                    var orders = JSON.parse(data.orders);
                    for (var i = 0; i < orders.length; i++)
                    {
                        var userName = orders[i].userName;
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
                                        <td width=10%>${userName}</td>
                                        <td width=10%>${order_id}</td>
                                        <td width=50%>${order_item}</td>
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


    //注销
    $("#log_off").click(function () {
        //删除用户信息cookie和session
        var m_name = getCookie("m_name");
        if (m_name != undefined) {
            $.ajax({
                url: "../logout",
                async: false,
                type: "post",
                data: {
                    "m_name": m_name,
                },
                success: function (data) {
                    deleteCookieGroup(['m_name', 'm_account']);
                    //刷新页面
                    alert("注销成功");
                    location.href = "./adminLogin.html";
                },
                error: function (data) {
                    deleteCookieGroup(['m_name', 'm_account']);
                    alert("注销失败");
                    location.href = "./adminLogin.html";
                }
            });
        }
        else
        {
            location.href = "./adminLogin.html";
        }
    });

    //超链接鼠标移动变色
    var color;
    $("a").mouseover(function () {
        color = $(this).css("color");
        $(this).css({
            color: "red",
        });
    }).mouseout(function () {
        $(this).css({
            color: color,
        });
    });

});