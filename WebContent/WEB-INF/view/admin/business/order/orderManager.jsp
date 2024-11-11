<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${ctx }/resources/css/index.css" media="all" />
</head>
<body>

	<!-- 搜索条件开始 -->
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	  <legend>查询条件</legend>
	</fieldset>
	<form class="layui-form" action="" lay-filter="searchFrm" id="searchFrm">
	<div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label">订单描述</label>
	      <div class="layui-input-inline">
	        <input type="text" name="orderName" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">客户名称</label>
	      <div class="layui-input-inline">
	        <input type="text" name="realName" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">订单时间</label>
	      <div class="layui-input-inline">
	        <input type="text" name="startTime" id="startTime" placeholder="开始时间" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	     <div class="layui-inline" >
	      <label class="layui-form-label" style="width:0;padding:0"></label>
	      <div class="layui-input-inline">
	        <input type="text" name="endTime" id="endTime" placeholder="结束时间" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	  </div>
	  </div>
	   <div class="layui-form-item" style="text-align: center;">
	    <div class="layui-input-block">
	      <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
	      <button type="reset" class="layui-btn layui-btn-warm layui-icon layui-icon-refresh">重置</button>
	    </div>
	  </div>
	</form>
	<!-- 搜索条件结束 -->
	
	<!-- 数据表格开始 -->
	<table class="layui-hide" id="orderTable" lay-filter="orderTable" ></table>
	<!-- 头工具栏 -->
	<div style="display:none" id="orderToolBar">
		<button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="batchDelete">批量删除</button>
		<a class="layui-btn layui-btn-sm" href="${ctx}/excel/orderdown">Excel导出</a>
	</div>
	<!-- 行工具栏 -->
	<div style="display:none" id="orderBar">
	  <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="detail">查看</a>
	  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</div>
	<!-- 数据表格结束 -->
	
	<!-- 查看的弹出层开始 -->
	<div style="display:none;padding:20px;" id="detail">
		<div style="padding: 20px; background-color: #F2F2F2;">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card" id="details">
					<div class='layui-card-body' id="orderId1"></div>
				    <div class='layui-card-body' id="realName1"></div>
				    <div class='layui-card-body' id="sampleName1"></div>
				    <div class='layui-card-body' id="orderName1"></div>
				    <div class='layui-card-body' id="orderTime1"></div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- 查看的弹出层结束 -->

	<script type="text/javascript" src="${ctx }/resources/layui/layui.js"></script>
	<script type="text/javascript">
		layui.use(['jquery','form','layer','laydate','table'],function(){
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
		    
		    //渲染数据表格
		   var tableIns=table.render({
		        elem: '#orderTable'
		        ,url:'${ctx}/order/getOrderAll'
		        ,page: true
		        ,toolbar: '#orderToolBar' //开启头部工具栏，并为其绑定左侧模板
		       	,defaultToolbar: ['filter', 'print']
		        ,title: '订单数据表'
		        ,cols: [[
		           {type: 'checkbox', fixed: 'left',align:'center'}
		          ,{field:'orderId', title:'ID', unresize: true, sort: true, align:'center'}
		          ,{field:'subId', title:'预约ID', align:'center'}
		          ,{field:'customerId', title:'客户ID', align:'center'}
		          ,{field:'subId', title:'客户名称', align:'center',templet:function(data){
		        	  var realName = "";
		        	  $.ajax({
		        		  url:"${ctx}/customer/getCustomerId?customerId="+data.subscribe.customerId,
		        		  type:"post",
		        		  async:false,
		        		  success:function(data){
		        			  realName = data.realName;
		        		  }
		        	  })
		        	  return realName;
		          }}
		          ,{field:'subId', title:'样片ID', align:'center',templet:function(data){
		        	  return data.subscribe.sampleId;
		          }}
		          ,{field:'subId', title:'样片名称', align:'center',templet:function(data){
		        	  var sampleName = "";
		        	  $.ajax({
		        		  url:"${ctx}/sample/getSampleId?sampleId="+data.subscribe.sampleId,
		        		  type:"post",
		        		  async:false,
		        		  success:function(data){
		        			  sampleName = data.sampleName;
		        		  }
		        	  })
		        	  return sampleName;
		          }}
		          ,{field:'orderName', title:'订单描述', align:'center'}
		          ,{field:'orderTime', title:'订单时间', align:'center',width:200}
		          ,{fixed: 'right', title:'操作', toolbar: '#orderBar',width:130, align:'center'}
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
		    
		 	 //模糊查询
			$("#doSearch").click(function(){
				//序列号表单数据
				var params=$("#searchFrm").serialize();
				 //刷新数据表格
				tableIns.reload({
					url:"${ctx}/order/getOrderAll?"+params
					,page:{curr:1}
				})
			});
		 	 
			  //监听头部工具栏事件
			  table.on('toolbar(orderTable)', function(obj){
				  switch(obj.event){
				    case 'batchDelete':
				    	openDeleteOrder();
				    break;
				  };
				});
			  
			  //监听工具条 
			  table.on('tool(orderTable)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
			    var data = obj.data; //获得当前行数据
			    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			    if(layEvent === 'detail'){ //查看
			        detail(data);
			      } else if(layEvent === 'del'){ //删除
			      	layer.confirm('确定删除【'+data.customer.realName+'】的订单信息吗?',{icon: 3, title:'提示'}, function(index){
			        //向服务端发送删除指令
			        $.post("${ctx}/order/deleteOrder",{orderId:data.orderId},function(obj){
			        	layer.msg(obj.msg);
			        	//刷新数据 表格
						tableIns.reload();
			        })
			      });
			    }
			  });
			  
			  //打开查看页面
			  function detail(data){
				  var html = "";
				  layer.open({
					  type:1,
					  title:"查看【"+data.customer.realName+"】购买的样片信息",
					  content:$("#detail"),
					  area:['400px','360px'],
					  success:function(index){
						  var realName = "";
			        	  $.ajax({
			        		  url:"${ctx}/customer/getCustomerId?customerId="+data.subscribe.customerId,
			        		  type:"post",
			        		  async:false,
			        		  success:function(data){
			        			  realName = data.realName;
			        		  }
			        	  })
			        	  var sampleName = "";
			        	  $.ajax({
			        		  url:"${ctx}/sample/getSampleId?sampleId="+data.subscribe.sampleId,
			        		  type:"post",
			        		  async:false,
			        		  success:function(data){
			        			  sampleName = data.sampleName;
			        		  }
			        	  })
			        	  var orderName = "";
			        	  if(data.orderName==null){
			        		  orderName = "";
			        	  }else{
			        		  orderName = data.orderName;
			        	  }
			        	  $("#orderId1").html("订单编号 ："+data.orderId);
						  $("#realName1").html("客户名称 ："+realName);
						  $("#sampleName1").html("样片名称 ："	+sampleName);
						  $("#orderName1").html("订单描述 ："	+orderName);
						  $("#orderTime1").html("订单时间 ："+data.orderTime);
					  }
				  })
				  $("#details").append(html);
			  }
			  
		  //批量删除
		  function openDeleteOrder(){
			  var checkStatus = table.checkStatus('orderTable');
			  var data = checkStatus.data;
			  if(data.length > 0){
				  layer.confirm('确定删除这个订单吗?', {icon: 3, title:'提示'},function(index){
					  //map() 方法返回一个新数组，数组中的元素为原始数组元素调用函数处理后的值。
					  var orderIds = data.map(function(item){
						  return item.orderId
					  })
					  //向服务端发送请求
					  $.ajax({
				    	   url:"${ctx}/order/deleteOrderByArray?orderIds="+orderIds,
				    	   type:'post',
				    	   success:function(obj){
				    		   	layer.msg(obj.msg)
					        	//刷新数据 表格
								tableIns.reload();
				    	   }
				       });
				  })
			  }else{
				  layer.msg("请选择要删除的数据")
			  }
		  }
		    
		})
	</script>
</body>
</html>