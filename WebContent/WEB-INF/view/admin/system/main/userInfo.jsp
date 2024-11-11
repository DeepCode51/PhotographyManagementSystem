<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>个人资料</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${ctx }/resources/css/public.css" media="all" />
</head>
<body class="childrenBody">
<form class="layui-form layui-row" id="infoForm">
	<input type="hidden" name="userId" value="${loginUser.userId}" >
	<div class="layui-col-md6 layui-col-xs12">
		<div class="layui-form-item">
			<label class="layui-form-label">账号</label>
			<div class="layui-input-block">
				<input type="text" name="loginName" value="${loginUser.loginName}" disabled class="layui-input layui-disabled">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">用户组</label>
			<div class="layui-input-block">
				<input type="text" value='${loginUser.isApproval==1?"超级管理员":"普通管理员"}' disabled class="layui-input layui-disabled">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">真实姓名</label>
			<div class="layui-input-block">
				<input type="text" name="realName" value="${loginUser.realName}" placeholder="请输入真实姓名" lay-verify="required" class="layui-input ">
			</div>
		</div>
		<div class="layui-form-item" pane="">
			<label class="layui-form-label">性别</label>
			<div class="layui-input-block userSex">
				<input type="radio" name="sex" value="1" title="男" <c:if test="${loginUser.sex=='1' }">checked</c:if>  >
				<input type="radio" name="sex" value="2" title="女" <c:if test="${loginUser.sex=='2' }">checked</c:if> >
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">手机号码</label>
			<div class="layui-input-block">
				<input type="tel" name="phone" value="${loginUser.phone}" placeholder="请输入手机号码" lay-verify="phone" class="layui-input ">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">家庭地址</label>
			<div class="layui-input-block">
				<input type="text" name="address" value="${loginUser.address}" placeholder="请输入家庭住址" lay-verify="address"  class="layui-input ">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="updateInfo">立即提交</button>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript" src="${ctx }/resources/layui/layui.js"></script>
<script type="text/javascript">
		layui.use(['jquery','form','layer','laydate','table','upload'],function(){
			var $ = layui.jquery;
		    var table = layui.table;
		    var layer = layui.layer;
		    var laydate = layui.laydate;
		    var form = layui.form;
		    var upload = layui.upload;
		    
			//个人资料点击保存按钮后
			   form.on("submit(updateInfo)",function(obj){	
					$.ajax({
						type:"POST",
						url:"${ctx}/user/updateUserById",
						data: $("#infoForm").serialize(),
						success:function(data){
							//弹出提示信息
							layer.msg(data.msg);
							//重新渲染表单
							form.render();
						}
					});	
				});
		})
</script>
</body>
</html>