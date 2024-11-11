<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>轮播图管理</title>
	<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${ctx }/resources/css/index.css" media="all" />
	<link rel="stylesheet" href="${ctx }/resources/css/public.css" media="all" />
	<style>
 img{
		 max-height:400px;
		 max-width: 500px; 
		 vertical-align:middle;
	 }

</style>
</head>
<body>

	<!-- 搜索条件开始 -->
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	  <legend>查询条件</legend>
	</fieldset>
	<form class="layui-form" action="" lay-filter="searchFrm" id="searchFrm">
	<div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label">轮播图名称</label>
	      <div class="layui-input-inline">
	        <input type="text" name="rotationName" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">样片名称</label>
	      <div class="layui-input-inline">
	        <input type="text" name="sampleName" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">上传时间</label>
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
	<table class="layui-hide" id="rotationTable" lay-filter="rotationTable" ></table>
	<!-- 头工具栏 -->
	<div style="display:none" id="rotationToolBar">
		<button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="batchDelete">批量删除</button>
		<button type="button" class="layui-btn layui-btn-sm" lay-event="add">添加</button>
	</div>
	<!-- 行工具栏 -->
	<div style="display:none" id="rotationBar">
	  <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="detail">查看</a>
	  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</div>
	<!-- 数据表格结束 -->
	
	<!-- 查看的弹出层开始 -->
	<div style="display:none;padding:20px;" id="detail">
		<div style="padding: 20px; background-color: #F2F2F2;">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<img alt="图片"  id="view_img">
				<div class="layui-card" id="details">
					<div class='layui-card-body' id="rotationId1"></div>
				    <div class='layui-card-body' id="rotationName1"></div>
				    <div class='layui-card-body' id="sampleName1"></div>
				    <div class='layui-card-body' id="ranking1"></div>
				    <div class='layui-card-body' id="rotationTime1"></div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- 查看的弹出层结束 -->
	
	<!-- 添加和修改的弹出层开始 -->
	<div style="display:none;padding:20px;" id="addOrUpdate">
		<form class="layui-form" action="" lay-filter="dataFrm" id="dataFrm">
			<input type="hidden" name="rotationId">
			<div class="layui-form-item">
				<div class="layui-inline">
				<label class="layui-form-label" style="margin-top:50px;"><i style="color: red;">* </i>轮播图</label>
			      <div class="layui-input-inline">
					<div class="layui-upload-list thumbBox mag0 magt3" id="rotationImageDiv">
						<!-- 显示上传的图片 -->
						<img class="layui-upload-img thumbImg" id="showRotationImage">
						<!-- 保存当前显示图片的地址 -->
						<input type="hidden" name="rotationImage" id="rotationImage1">
					</div>
				  </div>
			    </div>
			    <div class="layui-inline">
			      <label class="layui-form-label"><i style="color: red;">* </i>轮播图名称</label>
			      <div class="layui-input-inline">
			        <input type="text" name="rotationName" lay-verify="required" placeholder="请输入轮播图名称" autocomplete="off" class="layui-input">
			      </div>
			    </div>
			    <div class="layui-inline">
			      <label class="layui-form-label"><i style="color: red;">* </i>样片名称</label>
			      <div class="layui-input-inline">
			        <select name="sampleId" id="sampleId">
			        </select>
			      </div>
			    </div>
			    <div class="layui-inline">
			      <label class="layui-form-label"><i style="color: red;">* </i>排序值</label>
			      <div class="layui-input-inline">
			        <input type="text" name="ranking" lay-verify="required|number" placeholder="请输入排序值" autocomplete="off" class="layui-input">
			      </div>
			    </div>
			  </div>
			  <div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn" lay-filter="doSubmit" lay-submit="">提交</button>
			      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
			    </div>
			  </div>
		</form>
	</div>
	<!-- 添加和修改的弹出层结束 -->

	<script type="text/javascript" src="${ctx }/resources/layui/layui.js"></script>
	<script type="text/javascript">
		layui.use(['jquery','form','layer','laydate','table','upload'],function(){
			var $ = layui.jquery;
		    var table = layui.table;
		    var layer = layui.layer;
		    var laydate = layui.laydate;
		    var form = layui.form;
		    var upload = layui.upload;
		    
		  	//绑定事件选择器
		    laydate.render({
		  	  elem:'#startTime'
		    });
		    laydate.render({
		  	  elem:'#endTime'
		    })
		    
		    //渲染数据表格
		   var tableIns=table.render({
		        elem: '#rotationTable'
		        ,url:'${ctx}/rotation/getRotationAll'
		        ,page: true
		        ,toolbar: '#rotationToolBar' //开启头部工具栏，并为其绑定左侧模板
		       	,defaultToolbar: ['filter', 'print']
		        ,title: '轮播图数据表'
		        ,cols: [[
		           {type: 'checkbox', fixed: 'left',align:'center'}
		          ,{field:'rotationId', title:'ID', unresize: true, sort: true, align:'center'}
		          ,{field:'rotationName', title:'轮播图名称', align:'center'}
		          ,{field:'rotationImage', title:'轮播图', align:'center',templet:function(data){
			    	  return "<img width=40 height=30 src=${ctx}/file/downloadShowFile?path="+data.rotationImage+" />";
			      }}
		          ,{field:'sampleId', title:'样片ID',align:'center'}
		          ,{field:'sampleId', title:'样片名称', align:'center',templet:function(data){
		        	  return data.sample.sampleName;
		          }}
		          ,{field:'ranking', title:'排序值', sort: true,align:'center'}
		          ,{field:'rotationTime', title:'轮播图上传时间', align:'center',width:200}
		          ,{fixed: 'right', title:'操作', toolbar: '#rotationBar',width:180, align:'center'}
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
					url:"${ctx}/rotation/getRotationAll?"+params
					,page:{curr:1}
				})
			});
		 	 
			//监听头部工具栏事件
			  table.on('toolbar(rotationTable)', function(obj){
				  switch(obj.event){
				  	case 'add':
				      	openAddRotation();
				    break;
				    case 'batchDelete':
				    	openDeleteOrder();
				    break;
				  };
				});
			  
			  //监听工具条 
			  table.on('tool(rotationTable)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
			    var data = obj.data; //获得当前行数据
			    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			    if(layEvent === 'detail'){ //查看
			        detail(data);
			    }else if(layEvent === 'edit'){
			    	openUpdateRotation(data);
			    }else if(layEvent === 'del'){ //删除
			      layer.confirm('确定删除【'+data.rotationName+'】轮播图吗?',{icon: 3, title:'提示'}, function(index){
			        //向服务端发送删除指令
			        $.post("${ctx}/rotation/deleteRotation",{rotationId:data.rotationId},function(obj){
			        	layer.msg(obj.msg);
			        	//刷新数据 表格
						tableIns.reload();
			        })
			      });
			    }
			  });
			  
			  //打开查看页面
			  function detail(data){
				  layer.open({
					  type:1,
					  title:"查看【"+data.rotationName+"】的轮播图信息",
					  content:$("#detail"),
					  area:['600px','545px'],
					  success:function(index){
						  $("#view_img").attr("src","${ctx}/file/downloadShowFile?path="+data.rotationImage);
						  $("#rotationId1").html("轮播图编号 ："+data.rotationId);
						  $("#rotationName1").html("轮播图名称 ："+data.rotationName);
						  $("#sampleName1").html("样片名称 ："	+data.sample.sampleName);
						  $("#ranking1").html("排序值 ："	+data.ranking);
						  $("#rotationTime1").html("上传时间 ："+data.rotationTime);
					  }
				  })
			  }
			  
			  $(function(){
				//添加和修改的样片下拉框
				  $.post("${ctx}/sample/getAllSample1",function(data){
					if(data!=null && data!=""){
				          var html="";
				          $.each(data,function(index,sample){
				        	  html+="<option value="+sample.sampleId+">"+sample.sampleName+"</option>";
				          })
				           $("#sampleId").append(html);
			         }
				 });
			  	 form.render('select');
			  })
			  
			  var url;
			  var mainIndex;
			  //打开添加页面
			  function openAddRotation(){
				  mainIndex=layer.open({
					  type:1,
					  title:'添加轮播图',
					  content:$("#addOrUpdate"),
					  area:['400px','480px'],
					  success:function(index){
						  $("#showRotationImage").removeAttr("src");
						  //清空表单数据
						  $("#dataFrm")[0].reset();
						  url="${ctx}/rotation/addRotation";
					  }
				  })
			  }
			  
			  //打开修改页面
			  function openUpdateRotation(data){
				  mainIndex=layer.open({
					  type:1,
					  title:'修改【'+data.rotationName+'】的轮播图信息',
					  content:$("#addOrUpdate"),
					  area:['400px','480px'],
					  success:function(index){
						  $("#showRotationImage").attr("src","${ctx}/file/downloadShowFile?path="+data.rotationImage);
						  //初始化表单
						  form.val("dataFrm",data);
			      		  url="${ctx}/rotation/updateRotation";
					  }
				  })
			  }
			  
			//上传缩略图
		    upload.render({
		        elem: '#rotationImageDiv',
		        url: '${ctx}/file/uploadFile',
		        method : "post",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
		        acceptMime:'images/*',
		        field:"mf",
		        //auto: false,
		       // bindAction: '#doSubmit',
		        done: function(res, index, upload){
		            $('#showRotationImage').attr('src',"${ctx}/file/downloadShowFile?path="+res.data.src);
		            $('#rotationImage1').val(res.data.src);
		            $('#rotationImageDiv').css("background","#fff");
		        }
		    })
		    
		      //保存
			  form.on("submit(doSubmit)",function(obj){
				  //序列化表单数据
				  var params = $("#dataFrm").serialize();
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
			  
			  //批量删除
			  function openDeleteOrder(){
				  var checkStatus = table.checkStatus('rotationTable');
				  var data = checkStatus.data;
				  if(data.length > 0){
					  layer.confirm('确定删除这个轮播图吗?', {icon: 3, title:'提示'},function(index){
						  //map() 方法返回一个新数组，数组中的元素为原始数组元素调用函数处理后的值。
						  var rotationIds = data.map(function(item){
							  return item.rotationId
						  })
						  //向服务端发送请求
						  $.ajax({
					    	   url:"${ctx}/rotation/deleteRotationByArray?rotationIds="+rotationIds,
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