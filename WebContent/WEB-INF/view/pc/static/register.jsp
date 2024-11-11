<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>立即注册</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/pc/css/login.css">
<script src="${pageContext.request.contextPath }/resources/css/pc/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/css/pc/js/common.js"></script>
<!--背景图片自动更换-->
<script src="${pageContext.request.contextPath }/resources/css/pc/js/supersized.3.2.7.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/css/pc/js/supersized-init.js"></script>
<!--表单验证-->
<script src="${pageContext.request.contextPath }/resources/css/pc/js/jquery.validate.min.js?var1.14.0"></script>
<script type="text/javascript">
	//注册
	function toReg(){
		//var isExist;
		//先判断账号是否存在
		var phone=/^1[3456789]\d{9}$/;//手机号正则
		var loginName=$(".username").val();
		var realName=$(".realName").val();
		var password=$(".password").val();
		var confirm_password=$(".confirm_password").val();
		var phone_number=$(".phone_number").val();
		var sex=$(".sex").val();
		//$.ajax({
		//	url:"${ctx}/customer/getCustomerLoginName",
		//	type:"POST",
		//	dataType:"json",
		//	data:{loginName:loginName},
		//	success:function(data){
		//		if(data==false){
		//			isExist==null;
		//		}else{
		//			ixExist==1;
		//		}
		//	}
		//});
		//当所有标签均不为空时，可提交
		if(loginName==""){
			return false;
		}else if(realName==""){
			return false;
		}else if(password==""){
			return false;
		}else if(confirm_password==""){
			return false;
		}else if(!phone.test(phone_number)){
			return false;
		}else{
			$.ajax({
				url:"${ctx}/customer/addPcCustomer",
				type:"POST",
				dataType:"json",
				data:$("#registerForm").serialize(),
				success:function(data){
					if(data.msg=="添加成功"){
						alert("注册成功！");
						//跳转至登录
						window.location.href ="${ctx}/pc/toLogin";
					}else{
						alert("系统繁忙，请稍后尝试！");
					}
				}
			});
		}
	};
</script>
</head>
<body>
	<div class="register-container">
	<h1>立即注册</h1>
	
	<div class="connect">
		<p>sign up right away.</p>
	</div>
	
	<form action="" method="post" id="registerForm">
		<div>
			<input type="text" name="username" class="username" placeholder="您的用户名" autocomplete="off"/>
		</div>
		<div>
			<input type="text" name="realName" class="realName" placeholder="您的真实姓名" autocomplete="off"/>
		</div>
		<div>
			<input type="password" name="password" class="password" placeholder="输入密码" oncontextmenu="return false" onpaste="return false" />
		</div>
		<div>
			<input type="password" name="confirm_password" class="confirm_password" placeholder="再次输入密码" oncontextmenu="return false" onpaste="return false" />
		</div>
		<div>
			<input type="text" name="phone_number" class="phone_number" placeholder="输入手机号码" autocomplete="off" id="number"/>
		</div>
		<div style="position:relative">
			<input type="radio" checked="" name="sex" class="sex" style="margin-right:10px;width:50px;margin-top:10px;padding-left:10px;" value="1"/><span style="position:absolute;top:27%;color:white;margin-top:-10px;margin-left:-10px;line-height:50px;">男</span>
			<input type="radio" name="sex" class="sex" style="width:50px;margin-top:10px;margin-left:15px;" value="2"/><span style="position:absolute;top:24%;line-height:50px;color:white;margin-top:-7px;">女</span>
		</div>
		<button id="register" onclick="toReg()" type="submit">注 册</button>
	</form>
	<a href="${ctx }/pc/toLogin">
		<button type="button" class="register-tis">已经有账号？</button>
	</a>

</div>
</body>
</html>