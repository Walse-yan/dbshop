$(function(){
	//鼠标移动到a标签上变色
	var color;
	var username_pass = false;
	var password_pass = false;
	$("a").mouseover(function(){
		color = $(this).css("color");
		$(this).css({
			color:"red",
		}
		);
	}).mouseout(function(){
		$(this).css({
			color:color,
		});
	});
	
	//验证登录信息(输入信息)
	var oUsername = $("#Login_username");
	var oPassword = $("#Login_password");
	//提示信息
	var oUsername_tip = $("#tip_name");
	var oPassword_tip = $("#tip_psw");
	//清除信息
	oUsername.prop("value","");
	//验证帐号
	oUsername.focus(function(){
		oUsername_tip.text("邮箱/昵称/手机号码").css({display:"block",color:"grey"});
	}).blur(function(){
		if($(this).val() == ""){
			oUsername_tip.text("帐号不能为空").css({display:"block",color:"red"});
		}else{
			username_pass = true;
			oUsername_tip.css({display:"none"});
		}
	});
	//验证密码
	var Password = /^[0-9a-zA-Z!@#$%^&*]{6,16}$/;
	oPassword.focus(function(){
		oPassword_tip.text("6-20位英文、数字及符号的组合").css({display:"block",color:"grey"});
	}).blur(function(){
		var password = $(this).val();
		if(password == ""){
			oPassword_tip.text("密码不能为空").css({display:"block",color:"red"});
		}else if(!Password.test(password)){
			oPassword_tip.text("6-20位英文、数字及符号的组合").css({display:"block",color:"red"});
		} else{
			password_pass = true;
			oPassword_tip.css({display:"none"});
		}
	});
	
	//登录
	$("#Login_action #Login_submit input").click(function () {
		if(username_pass && password_pass){
			//alert(oUsername.val()+oPassword.val());
			$.ajax({
				url: "../login",
				async:false,
				type:"post",
				dataType:"json",
				data:{
					"username":oUsername.val(),
					"password":oPassword.val()
				},
				success:function (data) 
				{
					if(data.success)    //登录成功
					{
						var user = JSON.parse(data.user);       //JSON。parse将JSON字符串转化为JSON格式
						var temp = JSON.stringify({cookieTime: 0});     //stringify将变量转化为JSON字符串
						temp = JSON.parse(temp);

						$.extend(user, temp);      //$.extend将temp合并到user中
						SetCookie(user);
						location.href = "./index.html";
					}
					else
					{
						alert(data.reason);
					}
				},
				error:function (data)
				{
					alert("登录失败，可能是网络问题，请确认网络是否正常，如果正常，则可能是服务器繁忙，请稍后再试");
				}
			});
		}else{
			if(username_pass == false){
				oUsername_tip.text("请完善帐号信息").css({display:"block",color:"red"});
			}
			if(password_pass == false){
				oPassword_tip.text("请完善密码信息").css({display:"block",color:"red"});
			}
			return false;
		}
	});
});