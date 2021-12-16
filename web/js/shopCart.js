$(function (){
    var htmlStr = "";
    htmlStr += "<div class=\"good\">\n" +
        "    <table><tr><td width=30%>商品名称</td><td width=20%>单价</td><td width=20%>数量</td><td width=30%>操作</td></tr></table>\n" +
        "  </div>\n";
    if (isLogin())
    {
        var userName = getCookie("userName");    //获取用户名
        $.ajax({
            url: "../getCart",
            async: false,     //同步操作
            type: "post",
            dataType:"json",
            data: {
                "userName": userName
            },
            success:function (data)
            {
                if (data.success) {
                    var shopCart = JSON.parse(data.shopCart);    //获取购物车并转化为JSONArray
                    for (var i = 0; i < shopCart.length; i++) {
                        var p_name = shopCart[i].p_name;
                        var p_price = shopCart[i].p_price;
                        var p_nums = shopCart[i].p_nums;
                        var p_id = shopCart[i].p_id;
                        console.log(p_name);
                        htmlStr += `<div class="goods" id=${"goods" + p_id}>
                                    <table>
                                        <tr id=${p_id}>
                                        <td width=30%>${p_name}</td>
                                        <td width=20%>￥${p_price}</td>
                                        <td width=20%><button class="sub">-</button><a> ${p_nums} </a><button class="add">+</button> </td>
                                        <td width=30%><button class="delBtn">删除</button></td></tr>
                                    </table>
                                    </div>`;
                    }
                    htmlStr += "</div>\n" +
                        "  <div class=\"goods2\">\n" +
                        "    <table><tr><td width=560></td><td width=20%>小计：</td><td>总数：</td></tr></table>\n" +
                        "  </div>\n" +
                        "  <div class=\"goods1\">\n" +
                        "    <table><tr><td width=50%><button class=\"buyBtn\">确认购买</button></td><td><button class=\"delAllBtn\">全部清空</button></td></tr></table>\n" +
                        "  </div>\n";
                }
                else
                {
                    var reason = data.reason;
                    htmlStr += `<div>${reason}</div>\n`;
                }
            },
            error:function (data)
            {
                htmlStr += "<div>请稍后再试</div>\n";
            }
        })
    }
    else
    {
        htmlStr += "<div>请先登录</div>\n";
    }
    document.querySelector('.car').innerHTML = htmlStr;

    //减按钮
    $(".sub").click(function () {

        var btn = $(this);   //获取按钮对象
        var p_nums = -1;
        changeNum(p_nums, btn);
    });
    //加按钮
    $(".add").click(function (){
        var btn = $(this);   //获取按钮对象
        var p_nums = 1;
        changeNum(p_nums, btn);

    });
    //删除按钮
    $(".delBtn").click(function (){
        var res_num = parseInt($(this).parent().parent().children("td").eq(2).children("a").text());  //获取剩余数
        var p_id = $(this).parent().parent().attr("id");
        var userName = getCookie("userName");
        var flag = addCart(userName, p_id, -res_num);
        if (flag)
        {
            delProduction(p_id);
        }
    })
    //购买按钮
    $(".buyBtn").click(function (){
       $.ajax({
           url:"../addOrder",
           async:false,
           type:"post",
           dataType: "json",
           data:{
               "userName": getCookie("userName"),
               "order_status": true    //暂时都设置支付了
           },
           success:function (data)
           {
               if (data.success)
               {
                   alert("支付成功，已经向您发送一份购买凭证到您的邮箱，注意查收");
                   window.location.reload();   //重新加载页面
               }
               else
               {
                   alert("支付失败");
                   window.location.reload();   //重新加载页面
               }

           },
           error:function (data)
           {
               alert("购买失败");
           }
       })
    });
    //清空购物车按钮
    $(".delAllBtn").click(function (){
        $.ajax({
            url:"../delCart",
            async:true,
            type:"post",
            dataType: "json",
            data:{
                "userName": getCookie("userName"),
                "order_status": true    //暂时都设置支付了
            },
            success:function (data) {
                window.location.reload();   //重新加载页面
            },
            error:function (data)
            {
            }
        })
    })
})
function delProduction(p_id)   //删除对应的html
{
    var goods = document.getElementById(("goods" + p_id));
    goods.parentNode.removeChild(goods);    //移除该商品所有信息
}
function changeNum(p_nums, btn)    //btn是按钮对象
{
    var p_id = btn.parent().parent().attr("id");
    var res_num = parseInt(btn.parent().children("a").text());
    var userName = getCookie("userName");
    if (res_num + p_nums >= 0)
    {
        var flag = addCart(userName, p_id, p_nums);
        if (flag)    //加入购物车成功
        {
            if (res_num + p_nums == 0)
            {
                delProduction(p_id);
            }
            else
            {
                btn.parent().children("a").text(" " + (res_num + p_nums) + " ");
            }

            return true;
        }
        else
        {
            return false;
        }
    }

}