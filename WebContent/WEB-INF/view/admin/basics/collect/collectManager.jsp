<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收藏展示</title>
</head>
<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
<style>
 img{
		 max-height:400px;
		 max-width: 500px; 
		 vertical-align:middle;
	 }
</style>
<body>
<!-- 多条件查询 开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend style="text-align:center">查询条件</legend>
</fieldset>
	<form class="layui-form" method="post" id="searchFrm">
		<div class="layui-form-item" style="text-align:center">
		    <div class="layui-inline">
		      <label class="layui-form-label">样片名称:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="sampleName"  autocomplete="off" class="layui-input" placeholder="样片名称">
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">客户名称:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="realName"  autocomplete="off" class="layui-input" placeholder="客户名称">
		      </div>
		    </div>
		 </div>
		 <div class="layui-form-item" style="text-align:center">
			<div class="layui-inline">
				<label class="layui-form-label">开始时间:</label>
				<div class="layui-input-inline">
					<input type="text" name="starttime" class="layui-input" readonly="readonly"
						id="starttime" placeholder="开始时间">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">结束时间:</label>
				<div class="layui-input-inline">
					<input type="text" name="endtime" class="layui-input" id="endtime" readonly="readonly"
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
	<table class="layui-hide" id="collectionTable" lay-filter="collectionTable"></table>
	<!--头工具栏 -->
	<div style="display: none;" id="collectionToolBar">
       <!-- <button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="deleteBatch">批量删除</button> -->
	</div>
	<!-- 行工具 -->
	<div  id="collectionBar" style="display: none;">
	  <!-- <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a> -->
	  <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="view">查看大图</a>
	</div>
<!-- 数据表格 结束 -->

<!-- 查看弹出层的开始 -->
	<div id="viewSample" style="display: none;text-align: center;">
		<img alt="图片" id="view_img">
	</div>
<!-- 查看弹出层的结束 -->

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
	    elem: '#collectionTable' //渲染的目标对象
	    ,url:'${ctx}/collection/getAllCollect' //数据接口
	    ,toolbar: '#collectionToolBar' //开启头部工具栏，并为其绑定左侧模板
	    ,defaultToolbar: ['filter', 'print']
		,cellMinWidth:168 //设置列的最小默认宽度
	    ,title: '意向数据表'
	    ,page: true
	    ,cols: [[
	      {type: 'checkbox', fixed: 'left'}
	      ,{field:'collectId', title:'ID',align:'center',sort: true}
	      ,{field:'sampleId', title:'样片编号',align:'center',sort: true}
	      ,{field:'sampleName', title:'样片名称',templet: function(d){
	          return d.sample.sampleName;
	      },align:'center'}
	      ,{field:'sampleImage', title:'缩略图',align:'center',width:'120',templet:function(d){
	    	  return "<img width=40 height=30 src=${ctx}/file/downloadShowFile?path="+d.sample.sampleImage+" />";
	      }}
	      ,{field:'customerId', title:'客户编号',align:'center',sort: true}
	      ,{field:'realName', title:'客户名称',templet: function(d){
	          return d.customer.realName;
	      },align:'center'}
	      ,{field:'collectTime', title:'添加时间', align:'center',width:200,sort: true}
	      ,{fixed: 'right', title:'操作', toolbar: '#collectionBar',width:150, align:'center'}
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
	
	//打开查看页面
	function lopenView(data){
		mainIndex=layer.open({
			type:1,
			title:"查看大图信息",
			content:$("#viewSample"),
			area:['600px','500px'],
			success:function(index){
				$.post("${pageContext.request.contextPath}/collection/getCollectionByCollectId",{collectId:data.collectId},
						function(collect){
					console.log(collect);
					$("#view_img").attr("src","${ctx}/file/downloadShowFile?path="+collect.sample.sampleImage);
				})
			}
		});
	};

	//多条件查询
	$("#doSearch").click(function(){
		var params=$("#searchFrm").serialize();
		tableIns.reload({
			url:"${ctx}/collection/getAllCollect?"+params
			,page:{curr:1}		
		})
	});
	//监听头部工具栏事件
	table.on("toolbar(collectionTable)",function(obj){
		 switch(obj.event){
		    case 'deleteBatch':
		      deleteBatch();
			break;
		  };
	});
	//监听行工具事件
	   table.on('tool(collectionTable)', function(obj){
		   var data = obj.data; //获得当前行数据
		   var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		  if(layEvent === 'del'){ //删除
			  layer.confirm('确定删除ID为“'+data.collectId+'”的收藏吗?', function(index){
			       //向服务端发送删除指令
			       $.post("${ctx}/collection/deleteCollectionByCollectId",{collectId:data.collectId},function(res){
			    	   layer.msg(res.msg);
			    	    //刷新数据 表格
						tableIns.reload();
			       })
			     }); 
		   }else if(layEvent === 'view'){ //查看
		    	lopenView(data);
		   }
		 });
	
	//批量删除
	function deleteBatch(){
		//得到选中的数据行
		var checkStatus = table.checkStatus('collectionTable');
	    var data = checkStatus.data;
	    if(checkStatus.data.length>0){
	    layer.confirm('确定要删除选中的意向吗?', function(index){
		    	 //将对象数组中的 collectId属性 转成数组
			    var result = data.map(function(item) {
		            return item.collectId;
				});
		    	 //向服务端发送删除指令
		       $.ajax({
		    	   url:"${ctx}/collection/deleteCollectionByArray",
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