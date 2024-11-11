<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>影楼登录</title>
<link rel="stylesheet" href="${ctx }/resources/css/pc/css/login.css">
<script src="${ctx }/resources/css/pc/js/jquery.min.js"></script>
<script src="${ctx }/resources/css/pc/js/common.js"></script>
<!--背景图片自动更换-->
<script src="${ctx }/resources/css/pc/js/supersized.3.2.7.min.js"></script>
<script src="${ctx }/resources/css/pc/js/supersized-init.js"></script>
<!--表单验证-->
<script src="${ctx }/resources/css/pc/js/jquery.validate.min.js?var1.14.0"></script>
<script type="text/javascript">
	function toLogin(){
		var username=$("#username").val();
		var password=$("#password").val();
		if(username==""){
			return false;
		}else if(password==""){
			return false;
		}else{
			$.ajax({
				url:"${ctx}/customer/login",
				type:"POST",
				dataType:"json",
				data:$("#loginForm").serialize(),
				success:function(data){
					if(data.msg=='登录成功'){
						//跳转至登录
						window.location.href ="${ctx}/pc/index";
					}else{
						alert("用户名或密码错误！请重新输入");
					}
				}
			});
		}
	};
</script>
</head>
<body>
<div class="login-container">
	<h1>登录</h1>
	
	<div class="connect">
		<p>Please login first</p>
	</div>
	
	<form id="loginForm">
		<div>
			<input type="text" id="username" name="username" class="username" placeholder="用户名" autocomplete="off"/>
		</div>
		<div>
			<input type="password" id="password" name="password" class="password" placeholder="密码" oncontextmenu="return false" onpaste="return false" />
		</div>
		
	</form>
		<button id="submit" onclick="toLogin()" type="submit">登 录</button>
	<div>
		<a href="${ctx }/pc/toRegister">
			<button type="button" class="register-tis">还有没有账号？</button>
		</a>
	</div>

</div>
</body>
</html>