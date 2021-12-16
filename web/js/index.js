$(function(){
    //检查登录状态
    isLogin();

    //注销
    $("#log_off").click(function () {
        //删除用户信息cookie和session
        var userName = getCookie("userName");
        if (userName != undefined)
        {
            $.ajax({
                url: "../logout",
                async: false,
                type: "post",
                data: {
                    userName: userName
                },
                success: function (data) {
                    deleteCookieGroup(['userName', 'phone', 'join_time', 'age', 'sex', 'address']);
                    //刷新页面
                    window.location.reload();
                },
                error: function (data) {
                    deleteCookieGroup(['userName', 'phone', 'join_time', 'age', 'sex', 'address']);
                    window.location.reload();
                }
            });
        }
    });

    //加入购物车
    $("#production_select .addCart").click(function ()
    {
        if (isLogin())    //
        {
            var userName = getCookie("userName");
            var productionID = $(this).parent().attr("id");
            var p_nums = 1;
            var flag = addCart(userName, productionID, p_nums);
            if (flag)
            {
                alert("加入成功");
            }
            else
            {
                alert("加入失败");
            }
        }
        else
        {
            alert("请先登录");
        }


    });

    //超链接鼠标移动变色
    var color;
    $("a").mouseover(function(){
        color = $(this).css("color");
        $(this).css({
            color:"red",
        });
    }).mouseout(function(){
        $(this).css({
            color:color,
        });
    });

    //跳转购物车页面
    $("#goToShopCart").click(function ()
    {
      if (isLogin())   //已经登录
      {
          location.href = "./shopCart.html";
      }
      else
      {
          location.href = "./login.html";
      }
    })

    //跳转我的订单页面
    $("#goToMyOrder").click(function ()
    {
        if (isLogin())   //已经登录
        {
            location.href = "./myOrder.html";
        }
        else
        {
            location.href = "./login.html";
        }
    })

    //启动加载页面商品信息
    //loadCommodity();

    //点击商品跳转至商品详情页
    // $("#production_select>div:gt(1)").click(function () {
    //     $(this).attr("name");
    //     var time = new Date();
    //     time.setTime(time.getTime()+7*24*60*60*1000); 	//设定七天有效期
    //     var cookie = "pitch_up_book="+$(this).attr("name")+";expires="+time.toUTCString()+";path=/";
    //     document.cookie = cookie;
    //     window.location.href = "html/Commodity_detail.html";	//成功跳转
    // });


});
//加载商品
function loadCommodity() {
    $.ajax({
        url:"loadCommodity",
        async:false,
        type:"post",
        data:{},
        success:function (data) {
            // console.log(data);
            // console.log(encodeURIComponent(data));
            var data = transitionArrString(data);
            var count = 3;
            var location = "";
            data.forEach(function (value) {
                location = "#second_select>div:nth-child("+count+")";
                var json = change(value);
                $(location+" .photo img").attr("src","img/book/"+json.b_photo_1);
                $(location+" .message p").text(json.b_name);
                $(location+" .message .now_price").html("秒杀价 ￥"+json.b_newprice+"&nbsp;&nbsp;");
                $(location+" .message .original_price").html(""+json.b_oldprice);
                $(location).attr("name",json.b_id);
                count++;
            });
        },
        error: function(xhr, exception){
            alert(data.status);
        }
    });
}

//加入购物车
function addCart(userName, productionID, p_nums)
{
    var flag = false;
    $.ajax({
        url: "../addCart",
        async: false,
        type: "post",
        dataType: "json",
        data: {
            "userName": userName,
            "p_id": productionID,
            "p_nums": p_nums
            },
        success:function (data)
        {
            if (data.success)
            {
                flag = true;
            }

        },
        error:function (data)
        {
        }
        });
    return flag;
}
//判断登录状态
function isLogin() {
    var Login = getCookie("userName");
    if(Login == undefined)   //没有登录
    {
        $("#on_line").css({
            display:"none",
        });
        $("#off_line").css({
            display:"block",
        });
        return false;
    }
    else   //登录成功
    {
        $("#off_line").css({
            display:"none",
        });
        $("#on_line").css({
            display:"block",
        });
        $("#on_line #pet_name").text(Login);    //保存当前登录的用户名
        return true;
    }
}

