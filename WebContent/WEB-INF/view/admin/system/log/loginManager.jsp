<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录日志展示</title>
</head>
<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
<body>
<!-- 多条件查询 开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend style="text-align:center">查询条件</legend>
</fieldset>
	<form class="layui-form" method="post" id="searchFrm">
		<div class="layui-form-item" style="text-align:center">
		    <div class="layui-inline">
		      <label class="layui-form-label">用户名称:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="realName"  autocomplete="off" class="layui-input" placeholder="用户名称">
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">登录IP:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="loginIp"  autocomplete="off" class="layui-input" placeholder="登录IP">
		      </div>
		    </div>
		    </div>
		    <div class="layui-form-item" style="text-align:center">
			<div class="layui-inline">
				<label class="layui-form-label">开始时间:</label>
				<div class="layui-input-inline">
					<input type="text" name="startLoginTime" class="layui-input" readonly="readonly"
						id="starttime" placeholder="开始时间">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">结束时间:</label>
				<div class="layui-input-inline">
					<input type="text" name="endLoginTime" class="layui-input" id="endtime" readonly="readonly"
						placeholder="结束时间">
				</div>
			</div>
		</div>
		 <div  class="layui-form-item" style="text-align: center;">
		  <div class="layui-input-block" >
		      <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
		      <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh">重置</button>
		    </div>
		 </div>
	</form>
<!-- 多条件查询 结束 -->

<!-- 意向数据表格 开始 -->
	<table class="layui-hide" id="loginTable" lay-filter="loginTable"></table>
	<!--头工具栏 -->
	<div style="display: none;" id="loginToolBar">
       <button type="button" class="layui-btn layui-btn-danger " lay-event="deleteBatch">批量删除</button>
	</div>
	<!-- 行工具 -->
	<div  id="loginBar" style="display: none;">
	  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</div>
<!-- 数据表格 结束 -->

</body>
<script src="${ctx }/resources/layui/layui.js"></script>
<script type="text/javascript" src="${ctx }/resources/echarts/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
var tableIns;
layui.use([ 'jquery', 'layer', 'form', 'table', 'laydate' ], function() {
	var $ = layui.jquery;
	var layer = layui.layer;
	var form = layui.form;
	var table = layui.table;
	var laydate = layui.laydate;
	//日期选择器
	/* laydate.render({
	    elem: '#starttime'
	  });
	laydate.render({
	    elem: '#endtime'
	  }); */
	  var startDate = laydate.render({
		  elem: '#starttime',
		  done: function (value, date) {
		  endDate.config.min = {
		  year: date.year,
		  month: date.month - 1,
		  date: date.date,
		  };
		  endDate.config.start = {
		  year: date.year,
		  month: date.month - 1,
		  date: date.date,
		  };
		  }
		  });
		  var endDate = laydate.render({
		  elem: '#endtime',
		  done: function (value, date) {
		  startDate.config.max = {
		  year: date.year,
		  month: date.month - 1,
		  date: date.date,
		  }
		  }
		  });
	
	//数据表格渲染
	tableIns=table.render({
	    elem: '#loginTable' //渲染的目标对象
	    ,url:'${ctx}/log/getAllLogin' //数据接口
	    ,toolbar: '#loginToolBar' //开启头部工具栏，并为其绑定左侧模板
	    ,defaultToolbar: ['filter']
		//,cellMinWidth:100 //设置列的最小默认宽度
	    ,title: '登录日志'
	    ,page: true
	    ,cols: [[
	      {type: 'checkbox', fixed: 'left'}
	      ,{field:'loginId', title:'ID',align:'center',sort: true}
	      ,{field:'userId', title:'用户编号',align:'center',sort: true}
	      ,{field:'realName', title:'用户名称',templet: function(d){
	          return d.user.realName;
	      },align:'center'}
	      ,{field:'loginIp', title:'登录ip',align:'center',sort: true}
	      ,{field:'loginTime', title:'登录时间', align:'center',sort: true}
	      ,{fixed: 'right', title:'操作', toolbar: '#loginBar',width:130, align:'center'}
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

	//多条件查询
	$("#doSearch").click(function(){
		var params=$("#searchFrm").serialize();
		tableIns.reload({
			url:"${ctx}/log/getAllLogin?"+params
			,page:{curr:1}		
		})
	});
	//监听头部工具栏事件
	table.on("toolbar(loginTable)",function(obj){
		 switch(obj.event){
		    case 'deleteBatch':
		      deleteBatch();
			break;
		  };
	});
	//监听行工具事件
	   table.on('tool(loginTable)', function(obj){
		   var data = obj.data; //获得当前行数据
		   var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		  if(layEvent === 'del'){ //删除
			  layer.confirm('确定删除ID为“'+data.loginId+'”的日志吗?', function(index){
			       //向服务端发送删除指令
			       $.post("${ctx}/log/deleteLogin",{loginId:data.loginId},function(res){
			    	   layer.msg(res.msg);
			    	    //刷新数据 表格
						tableIns.reload();
			       })
			     }); 
		   }
		 });
	
	//批量删除
	function deleteBatch(){
		//得到选中的数据行
		var checkStatus = table.checkStatus('loginTable');
	    var data = checkStatus.data;
	    if(checkStatus.data.length>0){
	    layer.confirm('确定要删除选中的日志吗?', function(index){
		    	 //将对象数组中的 loginId属性 转成数组
			    var result = data.map(function(item) {
		            return item.loginId;
				});
		    	 //向服务端发送删除指令
		       $.ajax({
		    	   url:"${ctx}/log/deleteLoginByArray",
		    	   type:'post',
		    	   data:"ids="+result,
		    	   success:function(res){
		    		   layer.msg(res.msg);
			    	    //刷新数据 表格
						tableIns.reload();
		    	   }
		       });
		     }); 
	    }else{
	    	layer.confirm('至少选择一个');
	    }
	}
});
</script>
</html>