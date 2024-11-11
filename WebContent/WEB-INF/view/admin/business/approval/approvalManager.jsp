<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>杰森工作室-审批管理</title>
</head>
<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="${ctx }/resources/css/public.css" media="all" />
<script src="${ctx}/resources/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/echarts/js/jquery-3.1.1.min.js"></script>
<body class="childrenBody">
<!-- 搜索条件开始 -->
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	  <legend>查询条件</legend>
	</fieldset>
	<form class="layui-form" method="post" id="searchFrm">
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label">被审批人:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="userName1"  autocomplete="off" class="layui-input">
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">审批人:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="userName2"  autocomplete="off" class="layui-input">
		      </div>
		    </div>
		     <div class="layui-inline">
				<label class="layui-form-label">审批状态:</label>
				 <div class="layui-input-inline">
				<select name="approvalState" id="approvalState1"  >
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
<!-- 搜索条件结束 -->
<!-- 数据表格开始 -->
	<table class="layui-hide" id="approvalTable" lay-filter="approvalTable"></table>
	<!--头工具栏 -->
	<div style="display: none;" id="approvalToolBar">
       <button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="deleteBatch">批量删除</button>
	</div>
	<!-- 行工具 -->
	<script type="text/html"  id="approvalBar">
		 {{#  if(d.approvalState ==1){ }}
	  		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	        <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="view">查看</a>
	        <a class="layui-btn layui-btn-xs layui-btn-xs" lay-event="adopt">通过</a>
	        <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="notPass">驳回</a>
	        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
 		 {{#  } else if(d.approvalState ==2) { }}
   		 	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	        <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="view">查看</a>
	        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		 {{#  } else { }}
   		 	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	        <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="view">查看</a>
	        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
  		 {{#  } }}		
	</script>
<!-- 数据表格结束 -->
<!-- 查看弹出层的开始 -->
	<div style=" display:none ;padding: 20px" id="viewApproval" >
	<div style="padding: 20px; background-color: #F2F2F2;">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-header">审批信息
					</div>
					<div class="layui-card-body"  id="approvalId2"></div>
					<div class="layui-card-body"  id="approvalCount2"></div>
					<div class="layui-card-body"  id="approvedPerson2"></div>
					<div class="layui-card-body"  id="approver2"></div>
					<div class="layui-card-body"  id="sampleId2"></div>
					<div class="layui-card-body"  id="sampleName2"></div>
					<div class="layui-card-body"  id="sampleLogo2"></div>
					<div class="layui-card-body"  id="approvalState2"></div>
					<div class="layui-card-body"  id="why2"></div>
					<div class="layui-card-body"  id="approvalTime2"></div>
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
					<label class="layui-form-label">原因:</label>
					<div class="layui-input-inline">
						<input type="hidden" name="approvalId">
						<input type="hidden" name="approvalCount">
						<input type="hidden" name="approvedPerson">
						<input type="hidden" name="approver">
						<input type="hidden" name="sampleId">
						<input type="hidden" name="approvalState">
						<input type="hidden" name="approvalTime">
						<textarea type="text" name="why"   autocomplete="off"class="layui-textarea"></textarea>
					</div>
				</div>
				</div>
			<div class="layui-form-item" >
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn" lay-filter="doSubmit" lay-submit="">提交</button>
			      <button type="reset" class="layui-btn layui-btn-primary" >重置</button>
			    </div>
		  	</div>
		</form>
	</div>
<!-- 添加和修改的弹出层结束 -->
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
	    elem: '#approvalTable' //渲染的目标对象
	    ,url:'${ctx}/approval/getAllApproval' //数据接口
	    ,toolbar: '#approvalToolBar' //开启头部工具栏，并为其绑定左侧模板
	    ,defaultToolbar: ['filter', 'print']
	    //,height:'full+100'
	    //,height:'full-200'
		,cellMinWidth:100 //设置列的最小默认宽度
	    ,title: '审批数据表'
	    ,page: true
	    ,cols: [[
	      {type: 'checkbox', fixed: 'left'}
	      ,{field:'approvalId', title:'ID',unresize: true, sort: true,align:'center'}
	      ,{field:'approvalCount', title:'审批内容', align:'center',width:200,sort: true,templet: function(d){
	    	  return d.approvalCount=='1'?'<font color=red>变更为新增样片</font>':d.approvalCount=='2'?'<font>变更为历史样片</font>':'<font color=blue>变更为可出售样片</font>';
	      }}
	      ,{field:'approvedPerson', title: '被审批人',templet: function(d){
	          return d.user1.realName;
	      }}
	      ,{field:'approver', title: '审批人',templet: function(d){
	          return d.user2==null?"":d.user2.realName;
	      }}
	      ,{field:'sampleId', title: '样片编号',templet: function(d){
	          return d.sample.sampleId;
	      }}
	      ,{field:'sampleName', title: '样片名称',templet: function(d){
	          return d.sample.sampleName;
	      }}
	      ,{field:'sampleLogo', title:'样片状态', align:'center',sort: true,templet: function(d){
	    	  return d.sample.sampleLogo=='1'?'<font color=red>新增</font>':d.sample.sampleLogo=='2'?'<font>历史</font>':'<font color=blue>可出售</font>';
	      }}
	      ,{field:'approvalState', title:'审批状态', align:'center',sort: true,templet: function(d){
	    	  return d.approvalState=='1'?'<font color=blue>待审核</font>':d.approvalState=='2'?'<font color=green>通过</font>':'<font color=red>未通过</font>';
	      }}
	      ,{field:'why', title:'原因',width:200, align:'center'}
	      ,{field:'approvalTime', title: '审批时间',width:200,align:'center'}
	      ,{fixed: 'right', title:'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作',width:290, toolbar: '#approvalBar'}
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
		var params=$("#searchFrm").serialize();
		tableIns.reload({
			url:"${ctx}/approval/getAllApproval?"+params
			,page:{curr:1}		
		})
	});
	//监听头部工具栏事件
	table.on("toolbar(approvalTable)",function(obj){
		 switch(obj.event){
		    case 'deleteBatch':
		    	deleteBatch();
		    break;
		  };
	})
	//监听行工具事件
	   table.on('tool(approvalTable)', function(obj){
		   var data = obj.data; //获得当前行数据
		   var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		  if(layEvent === 'del'){ //删除
			  layer.confirm('真的删除 被审批人是【'+data.user1.realName+'】样片名是【'+data.sample.sampleName+'】的这个审批吗', function(index){
			       //向服务端发送删除指令
			       $.post("${ctx}/approval/deleteApprovalByApprovalId",{approvalId:data.approvalId},function(res){
			    	   layer.msg(res.msg);
			    	    //刷新数据 表格
						tableIns.reload();
			       })
			     }); 
		   } else if(layEvent === 'edit'){ //编辑
		      openUpdateApproval(data);
		   }else if(layEvent === 'view'){ //查看
		    	lopenView(data);
		   }else if(layEvent === 'notPass'){ //查看adopt
			   notPass(data);
		   }
		   else if(layEvent === 'adopt'){ //查看adopt
			   adopt(data);
		   }
		 });
	//打开查看页面
	function lopenView(data){
		mainIndex=layer.open({
			type:1,
			title:'查看 被审批人是【'+data.user1.realName+'】样片名是【'+data.sample.sampleName+'】的审批信息',
			content:$("#viewApproval"),
			area:['800px','600px'],
			success:function(index){
				$.post("${pageContext.request.contextPath}/approval/getApprovalByapprovalId",{approvalId:data.approvalId},
						function(approval){
					console.log(approval);
					$("#approvalId2").html("审批编号 ："	+approval.approvalId);
					$("#approvalCount2").html("审批内容 ："+(approval.approvalCount=='1'?'变更为新增样片':approval.approvalCount=='2'?'变更为历史样片':'变更为可出售样片'));
					$("#approvedPerson2").html("被审批人 ："	+approval.user1.realName);
					$("#approver2").html("审批人 ："	+(approval.user2==null?'':approval.user2.realName));
					$("#sampleId2").html("样片编号 ："	+approval.sample.sampleId);
					$("#sampleName2").html("样片名称 ："	+approval.sample.sampleName);
					$("#sampleLogo2").html("样片当前状态 ："	+(approval.sample.sampleLogo=='1'?'新增样片':approval.sample.sampleLogo=='3'?'可出售样片':'历史样片'));
					$("#approvalState2").html("审批状态 ："	+(approval.approvalState=='1'?'未审批':approval.approvalState=='2'?'通过':'未过期'));
					$("#why2").html("原因 ："	+(approval.why==null?'':approval.why));
					$("#approvalTime2").html("审批时间 ："	+approval.approvalTime);
				})
			}
		});
	};
	//修改页面
	function openUpdateApproval(data){
		mainIndex=layer.open({
			type:1,
			title:'修改 被审批人是【'+data.user1.realName+'】样片名是【'+data.sample.sampleName+'】的审批信息',
			content:$("#saveOrUpdateDiv"),
			area:['500px','300px'],
			success:function(index){
				form.val("dataFrm",data);
				url="${pageContext.request.contextPath}/approval/updateApproval";
			}
		});
	};
	//保存
	form.on("submit(doSubmit)",function(obj){
		//序列化表单数据
		var params=$("#dataFrm").serialize();
		console.log(params);
		$.post(url,params,function(obj){
			layer.msg(obj.msg);
			//关闭弹出层
			layer.close(mainIndex)
			//刷新数据 表格
			tableIns.reload();
		})
	});
	//通过操作
	function adopt(data){adopt
		form.val("dataFrm",data);
		var params=$("#dataFrm").serialize();
	    layer.confirm('您确定要通过 被审批人是【'+data.user1.realName+'】样片名是【'+data.sample.sampleName+'】的审批吗', function(index){
	       //向服务端发送删除指令
	       $.post("${ctx}/approval/updateApprovalByadopt",params,function(res){
	    	   layer.msg(res.msg);
	    	    //刷新数据 表格
				tableIns.reload();
	       })
	     }); 
	}
	//不通过操作
	function notPass(data){
		mainIndex=layer.open({
			type:1,
			title:'添加 被审批人是【'+data.user1.realName+'】样片名是【'+data.sample.sampleName+'】的驳回信息',
			content:$("#saveOrUpdateDiv"),
			area:['500px','280px'],
			success:function(index){
				$("#dataFrm")[0].reset();
				form.val("dataFrm",data);
				url="${ctx}/approval/updateApprovalByNotPass";
			}
		});
	}
	//批量删除
	function deleteBatch(){
		//得到选中的数据行
		var checkStatus = table.checkStatus('approvalTable');
	    var data = checkStatus.data;
	    //console.log(result);
	    //console.log(data);
	    if(checkStatus.data.length>0){
	    layer.confirm('真的删除选中的这些审批吗', function(index){
		    	 //将对象数组中的 empno属性 转成数组
			    var result = data.map(function(item) {
		            return item.approvalId;
				});
		    	 //向服务端发送删除指令
		       $.ajax({
		    	   url:"${pageContext.request.contextPath}/approval/deleteApprovalByArray",
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
	    	layer.confirm('请至少选择一个');
	    }
	}
})
// 查询下拉框渲染
	function selectapprovalState(){
		//设置审批状态下拉框
		 var html="";
		 html+="<option value=''>请选择</option>";
		 html+="<option value='1'>待审核</option>";
		 html+="<option value='2'>通过</option>";
		 html+="<option value='3'>未通过</option>";
		 $("#approvalState1").append(html);
	};
	selectapprovalState();
</script>
</html>