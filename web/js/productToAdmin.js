$(function() {
    //检查登录状态

    var isLogin = isM_Login();
    var htmlStr = "";
    htmlStr = `<div class="title">
                   <table><tr>
                   <td width=10%>商品id</td>
                   <td width=10%>商品名</td>
                   <td width=16%>图片</td>
                   <td width=8%>单价</td>
                   <td width=8%>库存</td>
                   <td width=8%>类别</td>
                   <td width=30%>描述</td>
                   <td width=10%>添加时间</td>
                   </tr></table>
                   </div>`;
    if (isLogin) {
        $.ajax({
            url: "../getProduct",
            async: false,
            type: "post",
            dataType: "json",
            data: {},
            success: function (data) {
                if (data.success) {
                    var list = JSON.parse(data.productsList);
                    for (var i = 0; i < list.length; i++)
                    {
                        var p_id = list[i].p_id;
                        var p_name = list[i].p_name;
                        var p_photo = list[i].p_photo;
                        var p_price = list[i].p_price;
                        var p_nums = list[i].p_nums;
                        var p_category = list[i].p_category;
                        var p_describe = list[i].p_describe;
                        var p_time = list[i].p_time;
                        htmlStr += `<div class="item">
                                    <table>
                                    <tr>
                                        <td width=10%>${p_id}</td>
                                        <td width=10%>${p_name}</td>
                                        <td width=16%>${p_photo}</td>
                                        <td width=8%>${p_price}</td>
                                        <td width=8%>${p_nums}</td>
                                        <td width=8%>${p_category}</td>
                                        <td width=30%>${p_describe}</td>
                                        <td width=10%>${p_time}</td>
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
            error: function (data) {
                alert("连接服务器失败");
            }
        });
    }
    else
    {
        htmlStr += `<div>请先登录</div>`;
    }
    document.querySelector('.productions').innerHTML = htmlStr;

    //上传图片
    var imgFile = "";
    $("#photoBtn").change(function (){
        if (isLogin)
        {
            imgPath = $("#photoBtn").val();
            imgFile = imgPreview(this);
        }

    });
    //生成预览图
    function imgPreview(input)
    {
        //判断是否支持FileReader
        if(window.FileReader) {
            var reader = new FileReader();
        } else {
            alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
        }
        var file = input.files[0];
        var url = window.URL.createObjectURL(file);
        $("#p_photo").attr("src", url);
        return file;
    }

    //增加商品
    $(".addBtn").click(function (){
       if (isLogin)
       {
           //获取商品信息的对象
           var p_name = $("#p_id");
           var photoBtn = $("#photoBtn");
           var p_price = $("#p_price");
           var p_nums = $("#p_nums");
           var p_category = $("#p_category");
           var p_describe = $("#p_describe");



           if (p_name.val() != "" && photoBtn.val() != "" && p_price.val() != "" && p_nums.val() != "" && p_category.val() != "" && p_describe.val() != "")
           {
               var formdata = new FormData();
               var filePath = photoBtn.val();
               var pos = filePath.lastIndexOf("\\");
               formdata.append(filePath.substring(pos + 1), imgFile);   //保存图片
               formdata.append("p_name", p_name.val());
               formdata.append("p_price", p_price.val());
               formdata.append("p_nums", p_nums.val());
               formdata.append("p_category", p_category.val());
               formdata.append("p_describe", p_describe.val());
               $.ajax({
                   url:"../addProduct",
                   async: true,
                   processData:false,   //对data参数进行序列化处理
                   contentType:false,   //内容编码类型
                   type: "post",
                   //dataType: "json",
                   data: formdata,
                   success:function (data)
                   {
                       alert("上传成功");
                       window.location.reload();   //重新加载页面

                   },
                   error:function (data)
                   {
                       alert("上传失败");
                   }
               });
           }
           else
           {
               alert("请填写完整的商品信息");
           }
       }
    });

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