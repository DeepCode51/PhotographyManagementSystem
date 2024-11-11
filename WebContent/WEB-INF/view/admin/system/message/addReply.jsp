<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>杰森工作室-留言管理</title>
<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css"><link>
<script type="text/javascript" charset="utf-8" src="${ctx }/resources/uud/js/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/resources/uud/js/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/resources/uud/js/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="${ctx }/resources/layui/layui.js"></script>
<script type="text/javascript">
layui.use(['jquery','table','layer','laydate','form'], function(){
  var $ = layui.jquery;
  var table = layui.table;
  var layer = layui.layer;
  var laydate = layui.laydate;
  var form = layui.form;
  //保存
  form.on("submit(doSubmits)",function(obj){
	  //序列号表单数据
	  var params = $("#dataForms").serialize();
	  $.ajax({
		  url:'${ctx }/reply/addReply',
		  data:params,
		  type:"post",
		  success:function(data){
			  layer.msg(data.msg);
			  setTimeout(function(){
	              var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	              parent.layer.close(index); //再执行关闭 
		        },1000);
			
		  }
	  })
  })
});
</script>
</head>
<body>
	<!-- 添加回复的弹出层开始 -->
<div style="padding:20px;" id="addOrReply">
	<form class="layui-form" action="" lay-filter="dataForm" id="dataForms">
		<input type="hidden" name="messageId" value="${messageId}">
		<div class="layui-form-item">

			<div class="layui-inline">
				<label class="layui-form-label">回复内容:</label><br>
				<div class="layui-input-inline">
				<textarea id="editor" name="replyContent" style="margin-left:35px;width:900px; height:300px;"></textarea>
				
				</div>
			</div>
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button type="button" class="layui-btn" lay-filter="doSubmits" lay-submit="">提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		  </div>
		  </div>
	</form>
</div>
<!-- 回复的弹出层结束 -->
<script type="text/javascript">
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue = UE.getEditor('editor');
</script>

</body>
</html>