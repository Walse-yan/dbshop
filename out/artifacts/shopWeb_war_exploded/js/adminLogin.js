$(function () {
    //忘记密码
    $("#forget_password").click(function () {
        alert("请联系超级管理员————颜经全！")
    });
    //登录
    $("#login_btn input").click(function () {
        if($("#manage_name").val() == "" || $("#manage_password").val() == ""){
            alert("请完善登录信息！");
        }else {
            var manage_name =  $("#manage_name").val();
            var manage_password = $("#manage_password").val();
            $.ajax({
                url:"../adminLogin",
                async:false,
                type:"post",
                dataType:"json",
                data:{
                    "m_account":manage_name,
                    "m_password":manage_password
                },
                success:function (data) {
                    if (data.success)    //登录成功
                    {
                        var admin = JSON.parse(data.admin);
                        SetCookie(admin);
                        location.href = "./orderToAdmin.html";
                    }
                    else
                    {
                        alert(data.reason);
                    }
                },
                error:function (data)
                {
                    alert("登录失败：" + data.status);
                }
            });
        }
    });
});

//判断登录状态
function isM_Login() {
    var Login = getCookie("m_name");
    if(Login == undefined)   //没有登录
    {
        return false;
    }
    else   //登录成功
    {
        $("#on_line").css({
            display:"block",
        });
        $("#on_line #pet_name").text(Login);    //保存当前登录的用户名
        return true;
    }
}