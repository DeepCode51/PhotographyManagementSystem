<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>预约管理</title>
	<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${ctx }/resources/css/index.css" media="all" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/echarts/js/jquery-3.1.1.min.js"></script>
</head>
<body>

	<!-- 搜索条件开始 -->
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	  <legend>查询条件</legend>
	</fieldset>
	<form class="layui-form" action="" lay-filter="searchFrm" id="searchFrm">
	<div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label">客户名称</label>
	      <div class="layui-input-inline">
	        <input type="text" name="realName" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	       <div class="layui-inline">
	      <label class="layui-form-label">样片名称</label>
	      <div class="layui-input-inline">
	        <input type="text" name="sampleName" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	       <div class="layui-inline">
	      <label class="layui-form-label">客户电话</label>
	      <div class="layui-input-inline">
	        <input type="text" name="phone" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	    </div>
	   <div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">开始时间:</label>
				<div class="layui-input-inline">
					<input type="text" name="starttime" class="layui-input" readonly="readonly"
						id="startTime" placeholder="开始时间">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">结束时间:</label>
				<div class="layui-input-inline">
					<input type="text" name="endtime" class="layui-input" id="endTime" readonly="readonly"
						placeholder="结束时间">
				</div>
			</div>
			  <div class="layui-inline">
				<label class="layui-form-label">预约状态:</label>
				 <div class="layui-input-inline">
				<select name="subState" id="subState1"  >
				</select> 
				 </div>
			</div>
		</div>
	   <div class="layui-form-item" style="text-align: center;">
	    <div class="layui-input-block">
	      <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
	      <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh">重置</button>
	    </div>
	  </div>
	</form>
	<!-- 搜索条件结束 -->
	
	<!-- 数据表格开始 -->
	<table class="layui-hide" id="subscribeTable" lay-filter="subscribeTable" ></table>
	<!-- 头工具栏 -->
	<div style="display:none" id="subscribeToolBar">
		<button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="batchDelete">批量删除</button>
	</div>
	<!-- 行工具 -->
	<script type="text/html"  id="subscribeBar">
		 {{#  if(d.subState ==1){ }}
	  		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	        <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="detail">查看</a>
			<a class="layui-btn layui-btn-xs layui-btn-xs" lay-event="beOverdue">过期</a>
	        <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="auditPass">审核通过</a>
 		 {{#  } else if(d.subState ==2) { }}
	        <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="detail">查看</a>
			<a class="layui-btn layui-btn-xs layui-btn-xs" lay-event="beOverdue">过期</a>
	        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="generateOrder">生成订单</a>
 		{{#  } else if(d.subState ==3) { }}
	        <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="detail">查看</a>
	        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		 {{#  } else { }}
	        <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="detail">查看</a>
	        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
  		 {{#  } }}		
	</script>
	<!-- 行工具栏 -->
	<!-- 数据表格结束 -->
	<!-- 查看弹出层的开始 -->
	<div style=" display:none ;padding: 20px" id="viewSubscribe" >
	<div style="padding: 20px; background-color: #F2F2F2;">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-header">预约信息
					</div>
					<div class="layui-card-body"  id="subId1"></div>
					<div class="layui-card-body"  id="customerName1"></div>
					<div class="layui-card-body"  id="customerPhone1"></div>
					<div class="layui-card-body"  id="sampleId1"></div>
					<div class="layui-card-body"  id="sampleName3"></div>
					<div class="layui-card-body"  id="price1"></div>
					<div class="layui-card-body"  id="subState2"></div>
					<div class="layui-card-body"  id="subTime1"></div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- 查看弹出层的结束 -->
	<!-- 添加和修改的弹出层开始 -->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
		<form class="layui-form"  lay-filter="dataFrm" id="dataFrm">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">成交价格:</label>
					<div class="layui-input-inline">
						<input type="hidden" name="subId">
						<input type="hidden" name="customerId">
						<input type="hidden" name="sampleId">
						<input type="hidden" name="subState">
						<input type="text" name="price" lay-verify="required"  autocomplete="off"class="layui-input">
					</div>
				</div>
				</div>
				<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">样片预约时间:</label>
					<div class="layui-input-inline">
						<input type="text" name="subeTime" placeholder="请输入预约时间" id="subeTime1" readonly="readonly" class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn" lay-filter="doSubmit" lay-submit="">提交</button>
			      <button type="reset" class="layui-btn layui-btn-primary" >重置</button>
			    </div>
		  	</div>
		</form>
	</div>
<!-- 添加和修改的弹出层结束 -->
<!-- 输入密码开始 -->
<div style="display: none;padding: 20px" id="customerPwd3" >
		<form class="layui-form"  lay-filter="dataFrm1" id="dataFrm1">
			<div class="layui-form-item">
				<div class="layui-inline">
						<input type="hidden" name="subId">
						<input type="hidden" name="customerId">
						<input type="hidden" name="sampleId">
						<input type="hidden" name="subState">
						<input type="hidden" name="price">
					<label class="layui-form-label">密码:</label>
					<div class="layui-input-inline">
						<input type="password" name="customerpwd" id="customerpwd" lay-verify="required"  placeholder="请输入密码"  autocomplete="off"class="layui-input">
					</div>
				</div>
				</div>
				<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">确认密码:</label>
					<div class="layui-input-inline">
						<input type="password" name="customerpwd1" id="customerpwd1" placeholder="请再次输入密码"   class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item" >
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn" lay-filter="doSubmit1" lay-submit="">提交</button>
			      <button type="reset" class="layui-btn layui-btn-primary" >重置</button>
			    </div>
		  	</div>
		</form>
	</div>
<!-- 输入密码开始结束 -->
	<script type="text/javascript" src="${ctx }/resources/layui/layui.js"></script>
	<script type="text/javascript">
		layui.use(['jquery','form','layer','laydate','table'],function(){
			var $ = layui.jquery;
		    var table = layui.table;
		    var layer = layui.layer;
		    var laydate = layui.laydate;
		    var form = layui.form;
		    
		  	//绑定事件选择器
		/*     laydate.render({
		  	  elem:'#startTime'
		  	,type: 'datetime'
		    });
		    laydate.render({
		  	  elem:'#endTime'
		  	,type: 'datetime'
		    }) */
		     laydate.render({
		  	  elem:'#subeTime1'
		  	,type: 'datetime'
		    })
		    
		      var startDate = laydate.render({
			  elem: '#startTime',
			  type: 'datetime',
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
			  elem: '#endTime',
			  type: 'datetime',
			  done: function (value, date) {
			  startDate.config.max = {
			  year: date.year,
			  month: date.month - 1,
			  date: date.date,
			  }
			  }
			  });
		    
		    
		    
		    //渲染数据表格
		   var tableIns=table.render({
		        elem: '#subscribeTable'
		        ,url:'${ctx}/subscribe/getAllSubscribe'
		        ,page: true
		        //,height:'full-200'
		        ,toolbar: '#subscribeToolBar' //开启头部工具栏，并为其绑定左侧模板
		       	,defaultToolbar: ['filter', 'print']
		        ,title: '预约数据表'
		        ,cols: [[
		           {type: 'checkbox', fixed: 'left',align:'center'}
		          ,{field:'subId', title:'ID', unresize: true, sort: true, width:100,align:'center'}
		          ,{field:'customerId', title:'客户编号',width:100, align:'center',templet:function(d){
		        	  return d.customer.customerId;
		          }}
		          ,{field:'customerName', title:'客户姓名',width:100, align:'center',templet:function(d){
		        	  return d.customer.realName;
		          }}
		          ,{field:'phone', title:'客户手机号', width:200, align:'center',templet:function(d){
		        	  return d.customer.phone;
		          }}
		          ,{field:'sampleId', title:'样片编号', width:100,align:'center',templet:function(d){
		        	  return d.sample.sampleId;
		          }}
		          ,{field:'sampleName', title:'样片名称',width:100, align:'center',templet:function(d){
		        	  return d.sample.sampleName;
		          }}
		          ,{field:'subState', title:'预约状态', align:'center',width:150,templet:function(d){
		        	  return d.subState=='1'?'<font color=black>待审核</font>':d.subState=='2'?'<font color=green>预约成功未使用</font>':d.subState=='3'?'<font color=blue>预约成功已使用</font>':'<font color=red>已过期</font>';
		          }}
		          ,{field:'price', title:'成交价格',width:100, align:'center'}
		          ,{field:'subeTime', title:'样片预约时间', align:'center',width:200}
		          ,{fixed: 'right', title:'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作', toolbar: '#subscribeBar',width:250,}
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
					url:"${ctx}/subscribe/getAllSubscribe?"+params
					,page:{curr:1}
				})
			});
		 	 
			//监听头部工具栏事件
			  table.on('toolbar(subscribeTable)', function(obj){
				  switch(obj.event){
				    case 'batchDelete':
				    	openDeleteSubscribe();
				    break;
				  };
				});
			  
			  //监听工具条 
			  table.on('tool(subscribeTable)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
			    var data = obj.data; //获得当前行数据
			    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			    if(layEvent === 'detail'){ //查看
			    	lopenView(data);
			    } else if(layEvent === 'del'){ //删除
			      layer.confirm('确定要删除【'+data.customer.realName+'】预约的样片名是【'+data.sample.sampleName+'】的信息吗?', function(index){
			        //向服务端发送删除指令
			        $.post("${ctx}/subscribe/deleteSubscribeBysubscribeId",{subId:data.subId},function(obj){
			        	layer.msg(obj.msg);
			        	//刷新数据 表格
			        	tableIns.reload();
			        })
			      });
			    }else if(layEvent === 'edit'){ //编辑
				     openUpdateSubscribe(data);
			    }else if(layEvent === 'auditPass'){ //审核通过
			    	auditPass(data);
			    }else if(layEvent === 'generateOrder'){ //生成订单
			    	openCustomerPwd(data);
			    }else if(layEvent === 'beOverdue'){ //过期
			    	beOverdue(data);
			    }
			  });
			//审核通过	
			function auditPass(data){
				layer.confirm('你确定要审核通过【'+data.customer.realName+'】预约的样片名是【'+data.sample.sampleName+'】的信息吗?', function(index){
				       //向服务端发送删除指令
				       $.post("${pageContext.request.contextPath}/subscribe/updateSubscribeByauditPass",{subId:data.subId,subState:2},function(res){
				    	   layer.msg(res.msg);
				    	    //刷新数据 表格
							tableIns.reload();
				       })
				  }); 
			}
			//审核通过	
			function beOverdue(data){
				layer.confirm('你确定要过期【'+data.customer.realName+'】预约的样片名是【'+data.sample.sampleName+'】的信息吗?', function(index){
				       //向服务端发送删除指令
				       $.post("${pageContext.request.contextPath}/subscribe/updateSubscribeByauditPass",{subId:data.subId,subState:4},function(res){
				    	   layer.msg(res.msg);
				    	    //刷新数据 表格
							tableIns.reload();
				       })
				  }); 
			}
			//密码页面
			function openCustomerPwd(data){
				mainIndex=layer.open({
					type:1,
					title:'客户【'+data.customer.realName+'】的支付密码',
					content:$("#customerPwd3"),
					area:['400px','300px'],
					success:function(index){
						//清空表单数据       
						$("#dataFrm1")[0].reset();
						form.val("dataFrm1",data);
					}
				});
			};
			//保存
			form.on("submit(doSubmit1)",function(data){
				var customerpwd = $("#customerpwd").val();
				var customerpwd1 = $("#customerpwd1").val();
				if(customerpwd==customerpwd1){
					var params=$("#dataFrm1").serialize();
					console.log(params);
					       //向服务端发送删除指令
					       $.post("${pageContext.request.contextPath}/subscribe/updateSubscribeBygenerateOrder",params,function(res){
					    	   layer.msg(res.msg);
					    	    //刷新数据 表格
					    	    layer.close(mainIndex);
								tableIns.reload();
					       })
				}else{
					layer.msg("两次密码输入不一致");
				}
				});
			//打开查看页面
			function lopenView(data){
				mainIndex=layer.open({
					type:1,
					title:'查看【'+data.customer.realName+'】预约的样片名是【'+data.sample.sampleName+'】的信息',
					content:$("#viewSubscribe"),
					area:['800px','500px'],
					success:function(index){
						$.post("${pageContext.request.contextPath}/subscribe/getSubscribeBysubId",{subId:data.subId},
								function(subscribe){
							console.log(subscribe);
							$("#subId1").html("预约编号 ："	+subscribe.subId);
							$("#customerName1").html("客户真实姓名 ："+subscribe.customer.realName);
							$("#customerPhone1").html("客户电话 ："	+subscribe.customer.phone);
							$("#sampleId1").html("样片编号 ："	+subscribe.sample.sampleId);
							$("#sampleName3").html("样片名称 ："	+subscribe.sample.sampleName);
							$("#price1").html("成交价格 ："	+subscribe.price);
							$("#subState2").html("预约状态 ："	+(subscribe.subState=='1'?'待审核':subscribe.subState=='2'?'预约成功未使用':subscribe.subState=='3'?'预约成功已使用':'过期'));
							$("#subTime1").html("样片预约时间 ："	+subscribe.subeTime);
						})
					}
				});
			};
			//修改页面
			function openUpdateSubscribe(data){
				mainIndex=layer.open({
					type:1,
					title:'修改【'+data.customer.realName+'】预约的样片名是【'+data.sample.sampleName+'】的信息',
					content:$("#saveOrUpdateDiv"),
					area:['430px','300px'],
					success:function(index){
						form.val("dataFrm",data);
						url="${pageContext.request.contextPath}/subscribe/updateSubscribe";
					}
				});
			};
			//保存
			form.on("submit(doSubmit)",function(obj){
				//序列化表单数据
				var params=$("#dataFrm").serialize();
				$.post(url,params,function(obj){
					layer.msg(obj.msg);
					//关闭弹出层
					layer.close(mainIndex)
					//刷新数据 表格
					tableIns.reload();
				})
			});
		  //批量删除
		  function openDeleteSubscribe(){
			  var checkStatus = table.checkStatus('subscribeTable');
			  var data = checkStatus.data;
			  if(data.length > 0){
				  layer.confirm('确定删除吗?', {icon: 3, title:'提示'},function(index){
					  //map() 方法返回一个新数组，数组中的元素为原始数组元素调用函数处理后的值。
					  var ids = data.map(function(item){
						  return item.subId
					  })
					  //向服务端发送请求
					  $.ajax({
				    	   url:"${ctx}/subscribe/deleteSubscribeByArray?ids="+ids,
				    	   type:'post',
				    	   success:function(obj){
				    		   	layer.msg(obj.msg)
					        	//刷新数据 表格
								tableIns.reload({
									page:{curr:1}
								})
				    	   }
				       });
				  })
			  }else{
				  layer.msg("请选择要删除的数据")
			  }
		  }
		})
	// 查询下拉框渲染
	function selectSubState(){
		//设置样片状态下拉框
		 var html="";
		 html+="<option value=''>请选择</option>";
		 html+="<option value='1'>待审核</option>";
		 html+="<option value='2'>成功未使用</option>";
		 html+="<option value='3'>成功已使用</option>";
		 html+="<option value='4'>过期</option>";
		 $("#subState1").append(html);
	};
	selectSubState();
	</script>
</body>
</html>