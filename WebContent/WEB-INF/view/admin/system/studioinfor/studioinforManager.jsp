<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css"><link>
	<script type="text/javascript" src="${ctx }/resources/layui/layui.js"></script>	
    <title>杰森工作室系统 – classic editor build sample</title>
    <script type="text/javascript" charset="utf-8" src="${ctx }/resources/uud/js/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx }/resources/uud/js/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${ctx }/resources/uud/js/lang/zh-cn/zh-cn.js"></script>
</head>
<body>

<!-- 数据表格开始 -->
<table class="layui-hide" id="empTable" lay-filter="empTable" ></table>
<!-- 行工具栏 -->
<div style="display:none" id="empBar">
  <a class="layui-btn layui-btn-xs"  lay-event="edit" href="${ctx}/sys/addorupdateStudioinfor">编辑</a>
  
</div>
<!-- 数据表格结束 -->
<!-- 修改的弹出层开始 -->
<div style="display:none;padding:20px;" id="addOrUpdate">
	<form class="layui-form" action="" lay-filter="dataForm" id="dataForm">
		<input type="hidden" name="studId">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label" style="width:120px;">工作室名称:</label>
					<div class="layui-input-inline">
						<input type="text" name="studName" lay-verify="required"
							autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label" style="width:120px;">联系方式:</label>
					<div class="layui-input-inline">
						<input type="text" name="studPhone" lay-verify="required"
							autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label"  style="width:120px;">工作室简介:</label>
					<div class="layui-input-inline">
						<input type="text" name="studIntroduction" lay-verify="required"
							autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label" style="width:120px;">发展历程:</label>
					<div class="layui-input-inline">
						<input type="text" name="developmentHistory" lay-verify="required"
							autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label"  style="width:120px;">涉猎方向:</label>
					<div class="layui-input-inline">
						<input type="text" name="direction" lay-verify="required"
							autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label"  style="width:120px;">资源设备:</label>
					<div class="layui-input-inline">
						<input type="text" name="resource" lay-verify="required"
							autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label"  style="width:120px;">店面地址:</label>
					<div class="layui-input-inline">
						<input type="text" name="studAddress" lay-verify="required"
							autocomplete="off" class="layui-input">
					</div>
				</div>


				<div class="layui-form-item">
					<div class="layui-input-block">
						<button type="button" class="layui-btn" lay-filter="doSubmit"
							lay-submit="">提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</div>
		</form>
</div>
<!-- 修改的弹出层结束 -->


<script>
layui.use(['jquery','table','layer','laydate','form'], function(){
	  var $ = layui.jquery;
	  var table = layui.table;
	  var layer = layui.layer;
	  var laydate = layui.laydate;
	  var form = layui.form;  
	  
	  //绑定事件选择器
	  laydate.render({
		  elem:'#startTime'
	  });
	  laydate.render({
		  elem:'#endTime'
	  })
	  laydate.render({
		  elem:'#hireDate'
	  })
	  
	  //渲染数据表格
	  var tableIns=table.render({
	    elem: '#empTable'	//绑定table表格
	    ,url: '${ctx }/studioinfor/getAllStudioinfor' //数据接口
	    ,page: false //true表示分页
	    ,toolbar: "#empToolBar"//表格的工具条
	    ,defaultToolbar: ['filter', 'print']
	    ,cols: [[ //表头
	       {type: 'checkbox', fixed: 'left',align:'center'}
	      ,{field: 'studId', title: '工作室编号', sort: false, align:'center'}
	      ,{field: 'studName', title: '工作室名称', sort: true, align:'center'}
	      ,{field: 'studPhone', title: '联系方式', align:'center'}
	      ,{field: 'direction', title: '涉猎方向', sort: true, align:'center'}
	      ,{field: 'studIntroduction', title: '工作室简介', sort: true, align:'center'}
	      ,{field: 'developmentHistory', title: '发展历程', sort: true, align:'center'}	      
	      ,{field: 'resource', title: '资源设备',align:'center'}
	      ,{field: 'studAddress', title: '店面地址',align:'center'}
	      ,{fixed: 'right', title:'操作', toolbar: '#empBar', width:170 ,align:'center'}
	    ]],
	    done:function(data,curr,count){
	    	//不是第一页时如果当前返回的的数据为0那么就返回上一页
	    	if(data.data.length==0&&curr!=1){
	    		tableIns.reload({
				    page:{
				    	curr:curr-1
				    }
				});
	    	}
	    }
	  });

	  
	  
	  
	  
	  //监听工具条 
	  table.on('tool(empTable)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
	    var data = obj.data; //获得当前行数据
	    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	    //if(layEvent === 'edit'){ //修改
	    //	openUpdateEmployee(data);
	    //}
	  });
	  
	  var url;
	  var mainIndex;
		//打开修改页面
		function openUpdateEmployee(data){
			mainIndex=layer.open({
				type:1,
				title:'修改工作室信息',
				content:$("#addOrUpdate"),
				  area:['480px','470px'],
				success:function(index){
					form.val("dataForm",data);
					url="${ctx }/addorupdateStudioinfor/updatestudioinforManager";
				}
			});
		};
	  
	  

	  //保存
	  form.on("submit(doSubmit)",function(obj){
		  //序列号表单数据
		  var params = $("#dataForm").serialize();
		  $.ajax({
			  url:url,
			  data:params,
			  type:"post",
			  success:function(data){
		          layer.msg(data.msg);
				  //关闭弹出层
				  layer.close(mainIndex);
				  //刷新数据表格
				  tableIns.reload();
			  }
		  })
		  
	  })
	});
//实例化编辑器
//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
var ue = UE.getEditor('editor');
</script>
</body>
</html>