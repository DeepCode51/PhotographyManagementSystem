<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>修改密码--layui后台管理模板 2.0</title>
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
<form class="layui-form layui-row changePwd" id="updateForm" >
	<input type="hidden" name="userId" value="${loginUser.userId}" >
	<div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
	<input type="hidden" id="old" value="${loginUser.password}">
		<div class="layui-form-item">
			<label class="layui-form-label">用户名</label>
			<div class="layui-input-block">
				<input type="text" value="${loginUser.loginName}" disabled class="layui-input layui-disabled">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">旧密码</label>
			<div class="layui-input-block">
				<input type="password" id="pwd" name="pwd" placeholder="请输入旧密码" required lay-verify="required|oldPwd" class="layui-input pwd">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">新密码</label>
			<div class="layui-input-block">
				<input type="password" id="password" name="password" placeholder="请输入新密码" required lay-verify="required|newPwd" id="oldPwd" class="layui-input pwd">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">确认密码</label>
			<div class="layui-input-block">
				<input type="password" id="password2" name="password2" placeholder="请确认密码" required lay-verify="required|confirmPwd" class="layui-input pwd">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit=""  lay-filter="doSubmit">立即修改</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</div>
</form>
<form class="layui-form" id="updateForm" style="margin-top:40px;margin-left:150px;">
			<input type="hidden" id="customerId" name="customerId" value="${customer.customerId }">
		  <div class="layui-form-item" >
		    <label class="layui-form-label" style="width:120px;">请输入原密码：</label>
		    <div class="layui-input-inline">
		      <input type="password" id="pwd" name="pwd" lay-verify="pwd" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label" style="width:120px;">请输入新密码：</label>
		    <div class="layui-input-inline">
		      <input type="password" id="password" name="password" lay-verify="password" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label" style="width:120px;">请确认新密码：</label>
		    <div class="layui-input-inline">
		      <input type="password" id="password2" name="password2" lay-verify="password2" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" lay-filter="doSubmit" lay-submit="">确认修改</button>
			      <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >重置</button>
			    </div>
		  	</div>
		 </form>
<script type="text/javascript" src="${ctx }/resources/layui/layui.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/changePwd.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/jquery.md5.js"></script>
<script type="text/javascript">
		layui.use(['jquery','form','layer','laydate','table','upload'],function(){
			var $ = layui.jquery;
		    var table = layui.table;
		    var layer = layui.layer;
		    var laydate = layui.laydate;
		    var form = layui.form;
		    var upload = layui.upload;
		    
		    
		  //保存
			  form.on("submit(doSubmit)",function(obj){
				  //序列化表单数据
				  var params = $("#updateForm").serialize();
				  $.ajax({
					  url:url,
					  data:params,
					  type:"post",
					  success:function(obj){
						  layer.msg(obj.msg);
						  //关闭弹出层
						  layer.close(mainIndex);
						  //刷新数据表格
						  tableIns.reload();
					  }
				  })
			  })
		    
		})
</script>
</body>
</html>