<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css"><link>
	<script type="text/javascript" src="${ctx }/resources/layui/layui.js"></script>	
    <title>杰森工作室系统 – 成员阵容</title>
    <script type="text/javascript" charset="utf-8" src="${ctx }/resources/uud/js/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx }/resources/uud/js/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${ctx }/resources/uud/js/lang/zh-cn/zh-cn.js"></script>
    <style type="text/css">
        textarea{
            width:100%;
        }
    </style>
</head>
<body>
<br><br><br>
<form class="layui-form layui-row" id="infoForm">
	<input type="hidden" name="studId" value="${studioinfor.studId}">

	<!--工作室简介  -->	
	<div class="layui-form-item">
    <label class="layui-form-label" style="width:100px;">成员阵容：</label>    
  	</div>
	<textarea id="editor" name="members" style="width:1024px;height:500px;" lay-verify="members">${studioinfor.members}</textarea>
	<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="updateInfo">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
	</div>
</form>
<script type="text/javascript">
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue = UE.getEditor('editor');
	
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
					url:"${ctx}/studioinfor/updateStudioinfor",
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