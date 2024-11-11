<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>杰森工作室-样片管理</title>
</head>
<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="${ctx }/resources/css/public.css" media="all" />
<script src="${ctx}/resources/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/echarts/js/jquery-3.1.1.min.js"></script>
<style>
 img{
		 max-height:400px;
		 max-width: 500px; 
		 vertical-align:middle;
	 }
</style>
<body class="childrenBody">
 <!-- 搜索条件开始 -->
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	  <legend>查询条件</legend>
	</fieldset>
	<form class="layui-form" method="post" id="searchFrm">
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label">样片名称:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="sampleName"  autocomplete="off" class="layui-input">
		      </div>
		    </div>
		    <div class="layui-inline">
				<label class="layui-form-label">类别名称:</label>
				 <div class="layui-input-inline">
				<select name="typeId" id="typeId1"  >
				</select> 
				 </div>
			</div>
		    <div class="layui-inline">
				<label class="layui-form-label">样片状态:</label>
				 <div class="layui-input-inline">
				<select name="sampleLogo" id="sampleLogo1"  >
				</select> 
				 </div>
			</div>
		    </div>
		    <div class="layui-form-item">
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
					<input type="text" name="endtime" class="layui-input" autocomplete="off" id="endtime" readonly="readonly"
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
	<!-- 搜索条件结束 -->
	<!-- 数据表格开始 -->
	<table class="layui-hide" id="sampleTable" lay-filter="sampleTable"></table>
	<!--头工具栏 -->
	<div style="display: none;" id="sampleToolBar">
	   <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
       <!-- <button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="deleteBatch">批量删除</button>-->
	</div>
	<!-- 行工具 -->
	<script type="text/html"  id="sampleBar">
		 {{#  if(d.sampleLogo ==1){ }}
	  		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	        <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="view">查看</a>
	        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="sell">变更为可出售</a>
	        <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="history">变更为历史</a>
	        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
 		 {{#  } else if(d.sampleLogo ==3) { }}
   		 	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	        <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="view">查看</a>
	        <a class="layui-btn layui-btn-xs layui-btn-xs" lay-event="newAdd">变更为新增</a>
	        <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="history">变更为历史</a>
	        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		 {{#  } else { }}
   		 	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	        <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="view">查看</a>
	        <a class="layui-btn layui-btn-xs layui-btn-xs" lay-event="newAdd">变更为新增</a>
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="sell">变更为可出售</a>
	        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
  		 {{#  } }}		
	</script>
<!-- 数据表格结束 -->
<!-- 添加和修改的弹出层开始 -->
	<div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
		<form class="layui-form layui-row layui-col-space10"  lay-filter="dataFrm" id="dataFrm">
			<div class="layui-col-md12 layui-col-xs12">
				<div class="layui-row layui-col-space10">
					<div class="layui-col-md9 layui-col-xs7">
						<div class="layui-form-item magt3">
							<label class="layui-form-label">样片名称:</label>
							<div class="layui-input-block">
								<input type="hidden" name="sampleId">
								<input type="hidden" name="sampleLogo">
								<input type="text" name="sampleName" id="sampleName" class="layui-input" lay-verify="required|isExist" placeholder="请输入样片名称">
							</div>
						</div>
						<div class="layui-form-item">
						<div class="layui-block">
							<label class="layui-form-label">类别:</label>
							<div class="layui-input-block">
								<select name="typeId" id="typeId2">
								</select>
							</div>
						</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">样片价格:</label>
							<div class="layui-input-block">
								<input type="text" name="samplePrice" class="layui-input"
									lay-verify="number" placeholder="样片价格">
							</div>
						</div>
					</div>
					<div class="layui-col-md3 layui-col-xs5">
						<div class="layui-upload-list thumbBox mag0 magt3" id="sampleimgDiv">
							<!-- 显示上传的图片 -->
							<img class="layui-upload-img thumbImg" id="showSampleImg">
							<!-- 保存当前显示图片的地址 -->
							<input type="hidden" name="sampleImage" id="sampleImage1">
						</div>
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">样片描述:</label>
					<div class="layui-input-block">
						<textarea name="sampleStock" placeholder="请输入描述" lay-verify="required" class="layui-textarea"></textarea>
					</div>
				</div>
				<div class="layui-form-item magb0" style="text-align: center;">
					    <div class="layui-input-block">
					      <button type="button" class="layui-btn" lay-filter="doSubmit" lay-submit="">提交</button>
					      <button type="reset" class="layui-btn layui-btn-primary" >重置</button>
					    </div>
				  	</div>
			</div>
		</form>
	</div>
	<!-- 添加和修改的弹出层结束 -->
	<!-- 查看弹出层的开始 -->
	<div style=" display:none ;padding: 20px" id="viewSample" >
	<div style="padding: 20px; background-color: #F2F2F2;">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-header">样片信息
					</div>
					<div class="layui-card-body"  id="sampleId2"></div>
					<div class="layui-card-body"  id="sampleName2"></div>
					<div class="layui-card-body"  id="typeId4"></div>
					<div class="layui-card-body"  id="userId2"></div>
					<div class="layui-card-body"  id="sampleStock2"></div>
					<div class="layui-card-body"  id="sampleLogo2"></div>
					<div class="layui-card-body"  id="sampleTime2"></div>
					<div class="layui-card-body"  id="sampleImage2"></div>
					<div class="layui-card-body"  id="samplePrice2"></div>
					<div class="layui-card-body"  id="collectionCount2"></div>
					<div class="layui-card-body"  id="orderCount2"></div>
					<div class="layui-card-body"  id="subscribeCount2"></div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- 查看弹出层的结束 -->
</body>
<script type="text/javascript">
	var tableIns;
	layui.use([ 'jquery', 'layer', 'form', 'table', 'laydate' ,'upload'], function() {
		var $ = layui.jquery;
		var layer = layui.layer;
		var form = layui.form;
		var table = layui.table;
		var laydate = layui.laydate;
		var upload = layui.upload;
		//日期选择器
	/* 	laydate.render({
		    elem: '#starttime'
		    ,type:'date'
		  });
		laydate.render({
		    elem: '#endtime'
		    ,type:'date'
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
		    elem: '#sampleTable' //渲染的目标对象
		    ,url:'${ctx}/sample/getAllSample' //数据接口
		    ,toolbar: '#sampleToolBar' //开启头部工具栏，并为其绑定左侧模板
		    ,defaultToolbar: ['filter', 'print']
			,cellMinWidth:100 //设置列的最小默认宽度
		    ,title: '样片数据表'
		    ,page: true
		    ,cols: [[
		      {type: 'checkbox', fixed: 'left'}
		      ,{field:'sampleId', title:'ID',unresize: true, sort: true,align:'center'}
		      ,{field:'sampleName', title:'样片名称',align:'center'}
		      ,{field:'type', title: '类别名称',templet: function(d){
		          return d.type.typeName;
		      }}
		      ,{field:'user', title: '上传人',templet: function(d){
		          return d.user.realName;
		      }}
		      ,{field:'sampleStock', title:'描述',align:'center'}
		      ,{field:'sampleLogo', title:'样片状态', align:'center',sort: true,templet: function(d){
		    	  return d.sampleLogo=='1'?'<font color=red>新增</font>':d.sampleLogo=='3'?'<font color=blue>可出售</font>':'<font>历史</font>';
		      }}
		      ,{field:'sampleTime', title:'上传时间',width:200, align:'center'}
		      ,{field:'sampleImage', title:'缩略图',align:'center',width:'120',templet:function(d){
		    	  return "<img width=40 height=30 src=${ctx}/file/downloadShowFile?path="+d.sampleImage+" />";
		      }}
		      ,{field:'samplePrice', title: '样片价格',align:'center'}
		      ,{field:'collectionCount', title: '收藏量',align:'center'}
		      ,{field:'orderCount', title: '订单量',align:'center'}
		      ,{field:'subscribeCount', title: '在线预约量',align:'center'}
		      ,{fixed: 'right', title:'操作',width:350, toolbar: '#sampleBar',align:'center'}
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
		//监听头部工具栏事件
		table.on("toolbar(sampleTable)",function(obj){
			 switch(obj.event){
			    case 'add':
			      openAddSample();
			    break;
			  };
		})
		//监听行工具事件
		   table.on('tool(sampleTable)', function(obj){
			   var data = obj.data; //获得当前行数据
			   var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			  if(layEvent === 'del'){ //删除
				  layer.confirm('真的删除【'+data.sampleName+'】这个样片吗', function(index){
				       //向服务端发送删除指令
				       $.post("${ctx}/sample/deleteSampleBySampleId",{sampleId:data.sampleId},function(res){
				    	   layer.msg(res.msg);
				    	    //刷新数据 表格
							tableIns.reload();
				       })
				     }); 
			   } else if(layEvent === 'edit'){ //编辑
			      openUpdateSample(data);
			   }else if(layEvent === 'view'){ //查看
			    	lopenView(data);
			   }else if(layEvent === 'newAdd'){ //变为新增
				   newAdd(data);
			   }else if(layEvent === 'sell'){ //变为可出售
				   sell(data);
			   }else if(layEvent === 'history'){ //变为历史
				   history(data);
			   }
			 });
		//变为新增样片
		function newAdd(data){
			layer.confirm('您确定要将【'+data.sampleName+'】变更为新增样片吗', function(index){
			       //向服务端发送删除指令
			       $.post("${pageContext.request.contextPath}/sample/updateSampleLogo",{sampleId:data.sampleId,sampleLogo:1},function(res){
			    	   layer.msg(res.msg);
			    	    //刷新数据 表格
						tableIns.reload();
			       })
			  }); 
		}
		//变为可出售样片
		function sell(data){
			layer.confirm('您确定要将【'+data.sampleName+'】变更为可出售样片吗', function(index){
			       //向服务端发送删除指令
			       $.post("${pageContext.request.contextPath}/sample/updateSampleLogo",{sampleId:data.sampleId,sampleLogo:3},function(res){
			    	   layer.msg(res.msg);
			    	    //刷新数据 表格
						tableIns.reload();
			       })
			  }); 
		}
		//变为历史样片
		function history(data){
			layer.confirm('您确定要将【'+data.sampleName+'】变更为历史样片吗', function(index){
			       //向服务端发送删除指令
			       $.post("${pageContext.request.contextPath}/sample/updateSampleLogo",{sampleId:data.sampleId,sampleLogo:2},function(res){
			    	   layer.msg(res.msg);
			    	    //刷新数据 表格
						tableIns.reload();
			       })
			  }); 
		}
		//打开查看页面
		function lopenView(data){
			mainIndex=layer.open({
				type:1,
				title:'查看【'+data.sampleName+'】的信息',
				content:$("#viewSample"),
				area:['800px','600px'],
				success:function(index){
					$.post("${pageContext.request.contextPath}/sample/getSampleId",{sampleId:data.sampleId},
							function(sample){
						$("#sampleId2").html("样片编号 ："	+sample.sampleId);
						$("#sampleName2").html("样片名称 ："+sample.sampleName);
						$("#typeId4").html("类别编号 ："	+sample.type.typeName);
						$("#userId2").html("上传人 ："	+sample.user.realName);
						$("#sampleStock2").html("样片描述 ："	+sample.sampleStock);
						$("#sampleLogo2").html("样片状态 ："	+(sample.sampleLogo=='1'?'新增样片':sample.sampleLogo=='3'?'可出售样片':'历史样片'));
						$("#sampleTime2").html("样片添加时间 ："	+sample.sampleTime);
						$("#sampleImage2").html("样片主图片 ："	+"<img  src=${ctx}/file/downloadShowFile?path="+sample.sampleImage+" />");
						$("#samplePrice2").html("价格 ："	+sample.samplePrice);
						$("#collectionCount2").html("收藏量 ："	+sample.collectionCount);
						$("#orderCount2").html("订单量 ："	+sample.orderCount);
						$("#subscribeCount2").html("在线预约量 ："	+sample.subscribeCount);
					})
				}
			});
		};
		var url;
		var mainIndex;
		//打开添加页面
		function openAddSample(){
			mainIndex=layer.open({
				type:1,
				title:'添加样片',
				content:$("#saveOrUpdateDiv"),
				area:['1000px','450px'],
				success:function(index){
					//添加验证
					$('input[name=sampleName]').attr("lay-verify","required|isExist");
					//清空表单数据       
					$("#dataFrm")[0].reset();
					///设置默认图片
					$("#showSampleImg").removeAttr("src");
					url="${ctx}/sample/addSample";
				}
			});
		}
		//打开修改页面
		function openUpdateSample(data){
			mainIndex=layer.open({
				type:1,
				title:'修改【'+data.sampleName+'】的信息',
				content:$("#saveOrUpdateDiv"),
				area:['1000px','450px'],
				success:function(index){
					$('input[name=sampleName]').attr("lay-verify","required");
					form.val("dataFrm",data);
					$("#showSampleImg").attr("src","${ctx}/file/downloadShowFile?path="+data.sampleImage);
					//修改时的绑定
	        		form.render('select');
					url="${ctx}/sample/updateSample";
				}
			});
		}
		//上传缩略图
	    upload.render({
	        elem: '#sampleimgDiv',
	        url: '${ctx}/file/uploadFile',
	        method : "post",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
	        accept:'images',
	        acceptMime:'image/*',
	        field:"mf",
	        done: function(res, index, upload){
	            $('#showSampleImg').attr('src',"${ctx}/file/downloadShowFile?path="+res.data.src);
	            $('#sampleImage1').val(res.data.src);
	            $('#sampleimgDiv').css("background","#fff");
	        }
	    });
		//保存
		form.on("submit(doSubmit)",function(obj){
			//序列化表单数据
			var params=$("#dataFrm").serialize();
			$.post(url,params,function(obj){
				layer.msg(obj.msg);
				console.log(obj.code);
				if((obj.code)!=2){
					//关闭弹出层
					layer.close(mainIndex);
				}
				//刷新数据 表格
				tableIns.reload();
			})
		});
		//渲染查询下拉框
		$.post("${pageContext.request.contextPath}/type/getAllType1",function(data){
			if(data!=undefined&&data!=null&&data!=""){
		           var html="";
		           html+="<option value=''>请选择</option>";
		               for(var i=0;i<data.length;i++){
		                   html+="<option value="+data[i]["typeId"]+">"+data[i]["typeName"]+"</option>";
		                }
		                  $("#typeId1").append(html);
		           }
	            //重新渲染select
	            form.render('select');
		});
		//模糊查询
		$("#doSearch").click(function(){
			var params=$("#searchFrm").serialize();
			tableIns.reload({
				url:"${ctx}/sample/getAllSample?"+params
				,page:{curr:1}		
			})
		});
		
		 //样片名是否存在
		  form.verify({
		    isExist:function(value){
		    	var data;
		        $.ajax({
		            url: "${ctx}/sample/getSampleBySampleName?sampleName="+value,
		            type: "post",
		            async: false,
		            success: function(result) {
		            	data=result;
		            }
		        })
		       	if(!data){
		       		return '样片名存在';
		       	}
		    }
		});
	});
	// 查询下拉框渲染
	function selectsamplelogo(){
		//设置样片状态下拉框
		 var html="";
		 html+="<option value=''>请选择</option>";
		 html+="<option value='1'>新增样片</option>";
		 html+="<option value='2'>历史样片</option>";
		 html+="<option value='3'>可出售样片</option>";
		 $("#sampleLogo1").append(html);
	};
	selectsamplelogo();
	// 添加 或者 修改 时的下拉框
	function addOrUpdateSelectRender (){
		//设置类别的		
		$.post("${pageContext.request.contextPath}/type/getAllType1",function(data){
		if(data!=undefined&&data!=null&&data!=""){
           var html="";
               for(var i=0;i<data.length;i++){
                   html+="<option value="+data[i]["typeId"]+">"+data[i]["typeName"]+"</option>";
                }
                  $("#typeId2").append(html);
           }
		});
	};
	addOrUpdateSelectRender ();
</script>
</html>