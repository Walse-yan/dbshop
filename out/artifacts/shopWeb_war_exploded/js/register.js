$(function(){
	var pet_name_pass = false;
	var phone_pass = false;
	var address_pass = false;
	var password_pass = false;
	var repassword_pass = false;
	var verification_pass = false;
	//input对象
	var oPet_name = $("#pet_name input");
	var oPhone = $("#phone input");
	var oAddress = $("#address input");
	var oPassword = $("#password input");
	var oRepassword = $("#repassword input");
	var oVerification = $("#verification_code input");
	var oReading = $("reg_choose input");
	//tip对象
	var oPet_name_tip = $("#pet_name_tip font");
	var oPhone_tip = $("#phone_tip font");
	var oAddress_tip = $("#address_tip font");
	var oPassword_tip = $("#password_tip font");
	var oRepassword_tip = $("#repassword_tip font");
	var oVerification_tip = $("#verification_code_tip font");

	oPet_name.prop("value","");
	oPhone.prop("value","");

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
	//表单获取鼠标光标
	//昵称校验
	$("#pet_name input").focus(function(){
		$("#pet_name_tip font").text("昵称由2-6位中英文组成").css({
			display:"block",
			color:"grey"
		});
	}).blur(function(){
		if($(this).val().length < 2 || $(this).val().length >6 ){		//长度异常
			$("#pet_name_tip font").text("昵称由2-6位中英文组成").css({
				display:"block",
				color:"red",
			});
			pet_name_pass = false;
			// $(this).focus();
		}else{
			$("#pet_name_tip font").css({
				display:"none",
			});
			pet_name_pass = true;
		}
	});
	//手机号码校验
	$("#phone input").focus(function(){
		$("#phone_tip font").text("手机号用于客户登录、找回密码、接收订单通知等").css({
			display:"block",
			color:"grey"
		});
		// console.log("sss");
	}).blur(function(){
		var Phone_Number = /^(13[0-9]{9})|(15[0-9]{9})|(17[0-9]{9})|(18[0-9]{9})|(19[0-9]{9})$/;
		var phone = $(this).val();
		if(phone == '')
		{
			$("#phone_tip font").text("手机号不能为空[11位]").css({display:"block",color:"red"});
			// $(this).focus();
			phone_pass = false;
		}
		else if(!Phone_Number.test(phone))
		{
			$("#phone_tip font").text("请输入合法手机号!").css({display:"block",color:"red"});
			// $(this).focus();

			phone_pass = false;
		}
		else
		{
			phone_pass = true;
		}
	});
	//邮箱验证
	$("#address input").focus(function(){
		$("#address_tip font").text("手机号用于客户登录、找回密码、接收订单通知等").css({
			display:"block",
			color:"grey"
		});
	}).blur(function() {
		var address = $(this).val();
		if(address == ""){
			$("#address_tip font").text("邮箱地址不能为空").css({color:"red"});
			address_pass = false;
		}
		else address_pass = true;
	});
	//密码校验
	var password;
	var Password = /^[0-9a-zA-Z!@#$%^&*]{6,16}$/;
	$("#password input").focus(function(){
		$("#password_tip font").text("密码6-20位字符，可由英文、数字及符号组成").css({color:"grey",display:"block"});
	}).blur(function(){
		password = $(this).val();
		if(password == ""){
			$("#password_tip font").text("密码不能为空").css({color:"red"});
			password_pass = false;
		}else if(!Password.test(password)){
			$("#password_tip font").text("密码6-20位字符，可由英文、数字及符号组成").css({color:"red"});
			password_pass = false;
		}else{
			$("#password_tip font").css({display:"none"});
			password_pass = true;
		}
	});
	//
	$("#repassword input").focus(function(){
		$("#repassword_tip font").text("请再次输入密码").css({color:"grey",display:"block"});
	}).blur(function(){
		var repassword = $("#repassword input").val();
		if(repassword != password){
			$("#repassword_tip font").text("两次密码不统一").css({color:"red"});
			repassword_pass = false;
		}else{
			$("#repassword_tip font").css({display:"none"});
			repassword_pass = true;
		}
	});
	//验证码校验
	$("#verification_code input").focus(function(){
		$("#verification_code_tip font").text("请输入验证码").css({display:"block",color:"grey"});
	}).blur(function(){
		var verification = $(this).val();
		if(verification == ""){
			$("#verification_code_tip font").text("验证码不能为空，请输入验证码").css({color:"red"});
			verification_pass = false;
		}else{
			$("#verification_code_tip font").css({display:"none"});
			verification_pass = true;
		}
	});
	//获取验证码
	$("#getCode_button input").click(function(){
		if (!address_pass)
		{
			$("#address_tip font").text("请完善邮箱地址信息").css({display:"block",color:"red"});
		}
		else
		{
			$.ajax({
				url:"../GetSecurityCode",
				async: false,
				type: "post",
				dataType:"json",
				data:{
					"address": oAddress.val()
				},
				success:function (data)
				{

					var result = data.success;
					if (result)
					{
						$("#verification_code_tip font").text("获取验证码成功，请到邮箱查看").css({display:"block",color:"blue"});
					}
					else
					{
						var reason = data.reason
						$("#verification_code_tip font").text(reason).css({display:"block",color:"red"});
					}
				},
				error:function (data)
				{
					$("#verification_code_tip font").text("获取验证码失败,请稍后再试").css({display:"block",color:"red"});
				}
			})
		}
	});

	$("#reg_choose input").click(function(){
		$("#reg_reading font").css({background:""});
	});

	$("#submit_button input").click(function(){
		//获取阅读条款
		var readingPass = $("#reg_choose input").prop("checked");
		if(pet_name_pass && phone_pass && password_pass && repassword_pass && verification_pass && readingPass){
			$.ajax({
				url:"../register",
				async:true,
				type:"post",
				dataType:"json",
				data:{
					"username": oPet_name.val(),
					"phone": oPhone.val(),
					"address": oAddress.val(),
					"password": oPassword.val(),
					"securityCode":oVerification.val()
				},
				success:function (data)
				{
					if (data.success)
					{
						alert("注册成功");
						location.href = "./login.html";   //跳转登录页面
					}
					else
					{
						alert(data.reason);
					}
				},
				error:function (data)
				{
					alert("注册失败，可能是没有网络，如果网络问题解决，可能是服务器繁忙，请稍后再试");
				}
			})
		}else{
			if(!pet_name_pass){
				$("#pet_name_tip font").text("请完善昵称").css({display:"block",color:"red"});
			}
			if(!phone_pass){
				$("#phone_tip font").text("请完善手机号码信息").css({display:"block",color:"red"});
			}
			if (!address_pass)
			{
				$("#address_tip font").text("请完善邮箱地址信息").css({display:"block",color:"red"});
			}
			if(!password_pass){
				$("#password_tip font").text("请完善密码信息").css({display:"block",color:"red"});
			}
			if(!repassword_pass){
				$("#repassword_tip font").text("请核验密码信息").css({display:"block",color:"red"});
			}
			if(!verification_pass){
				$("#verification_code_tip font").text("请输入验证码").css({display:"block",color:"red"});
			}
			if(pet_name_pass && phone_pass && password_pass && repassword_pass && verification_pass){
				// $("#reg_reading font").css({background:"red"});
				alert("请勾选注册协议");
			}
		}
	});
});

