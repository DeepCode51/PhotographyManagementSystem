<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>杰森工作室-回复管理</title>
<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css"><link>
<script type="text/javascript" src="${ctx }/resources/layui/layui.js"></script>
<script type="text/javascript">
layui.use(['jquery','table','layer','laydate','form'], function(){
  var $ = layui.jquery;
  var table = layui.table;
  var layer = layui.layer;
  var laydate = layui.laydate;
  var form = layui.form;  
  
//开始时间结束时间
  var startDate = laydate.render({
	 elem: '#startTime',
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
    elem: '#empTable'	//绑定table表格
    ,url: '${ctx }/reply/getAllReply' //数据接口
    ,page: true //true表示分页
    ,toolbar: "#empToolBar"//表格的工具条
    ,defaultToolbar: ['filter', 'print']
    ,cols: [[ //表头
       {type: 'checkbox', fixed: 'left',align:'center'}
      ,{field: 'replyId', title: '回复编号', sort: true, align:'center'}
      ,{field: 'messageId', title: '留言编号', sort: true, align:'center'}
      ,{field: 'userId', title: '用户编号', sort: true, align:'center'}
      ,{field: 'userId[1]', title: '用户姓名',  sort: true,align:'center',templet:function(data){
    	  return data.user.realName;
      }}
      ,{field: 'messageId[1]', title: '留言内容',align:'center',templet:function(data){
    	  return data.message.messageContent;
      }}
      ,{field: 'replyTime', title: '回复时间',align:'center'}

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

  //监听头部工具栏事件
  table.on('toolbar(empTable)', function(obj){
	  switch(obj.event){
	    case 'add':
	    	openAddEmp();
	    break;
	    case 'batchDelete':
	    	deleteBatch();
	    break;
	  };
	});
  
  //监听工具条 
  table.on('tool(empTable)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    if(layEvent === 'detail'){ //查看
    	openView(data);
    } else if(layEvent === 'del'){ //删除
      layer.confirm('确定删除【'+data.user.realName+'】的回复吗?',{icon: 3, title:'提示'}, function(index){
        //向服务端发送删除指令
        $.post("${ctx }/reply/deleteReplyById",{replyId:data.replyId},function(data){
        	layer.msg(data.msg);
        	//刷新数据 表格
			tableIns.reload();
        })
      });
    } else if(layEvent === 'edit'){ //编辑
    	openUpdateReply(data);
    } 
  });
  
  var url;
  var mainIndex;
	
	 // 打开修改回复
	  function openUpdateReply(data){
			mainIndex=layer.open({
				type:2,
				title:'修改【'+data.user.realName+'】回复的信息',
				content:'${ctx }/sys/toUpdateReply?replyId='+data.replyId,
				area:['1000px','530px'],
				success:function(index){
					form.val("dataForm",data);
					url="${ctx }/reply/updateReply";
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
//打开查看页面
	function openView(data){
		mainIndex=layer.open({
			type:1,
			title:'查看【'+data.user.realName+'】回复的信息',
			content:$("#viewEmployee"),
			area:['700px','400px'],
			success:function(index){
				$.post("${ctx }/reply/getReplyById",{replyId:data.replyId},
						function(reply){
					console.log(reply);
					$("#replyId1").html("回复编号 ：" +reply.replyId);
					$("#messageId1").html("留言编号 ：" +reply.message.messageId);
					$("#messageContent1").html("留言内容 ：" +reply.message.messageContent);
					$("#userId1").html("用户姓名："+reply.user.realName);
					$("#replyContent1").html("回复内容 ："	+reply.replyContent);
					$("#replyTime1").html("回复时间 ："	+reply.replyTime);
				})
			}
		});
	};
	//批量删除
	function deleteBatch(){
		//得到选中的数据行
		var checkStatus = table.checkStatus('empTable');
	    var data = checkStatus.data;
	    if(checkStatus.data.length>0){
	    layer.confirm('确定删除吗', function(index){
		    	 //将对象数组中的 empno属性 转成数组
			    var result = data.map(function(item) {
		            return item.replyId;
				});
		    	 //向服务端发送删除指令
		       $.ajax({
		    	   url:"${ctx }/reply/deleteReplyByArray",
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
	    	layer.confirm("请选择要删除的数据");
	    }
	}
	
	
	//模糊查询
	$("#doSearch").click(function(){
		var params=$("#searchFrm").serialize();
		tableIns.reload({
			url:"${ctx }/reply/getAllReply?"+params
			,page:{curr:1}
		})
	});
});
</script>
</head>
<body>
<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>查询条件</legend>
</fieldset>
<form class="layui-form" action="" id="searchFrm">
<div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">用户姓名</label>
      <div class="layui-input-inline">
        <input type="tel" name="realName" id="realName" autocomplete="off" class="layui-input">
      </div>
    </div>

    <div class="layui-inline">
      <label class="layui-form-label">回复时间</label>
      <div class="layui-input-inline">
        <input type="text" name="startReplyTime" id="startTime" placeholder="开始时间"  autocomplete="off" class="layui-input">
      </div>
    </div>
     <div class="layui-inline" >
      <label class="layui-form-label" style="width:0;padding:0"></label>
      <div class="layui-input-inline">
        <input type="text" name="endReplyTime" id="endTime" placeholder="结束时间"  autocomplete="off" class="layui-input">
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
<table class="layui-hide" id="empTable" lay-filter="empTable" ></table>
<!-- 头工具栏 -->
<div style="display:none" id="empToolBar">
	<button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="batchDelete">批量删除</button>
</div>
<!-- 行工具栏 -->
<div style="display:none" id="empBar">
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="detail">查看</a>  
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</div>
<!-- 数据表格结束 -->

<!-- 添加和修改的弹出层开始 -->
<div style="display:none;padding:20px;" id="addOrUpdate">
	<form class="layui-form" action="" lay-filter="dataForm" id="dataForm">
		<input type="hidden" name="replyId">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">回复内容:</label><br>
				<div class="layui-input-inline">
				<textarea id="replyContent" name="replyContent"
				style="margin-left:35px;width:381px; height:215px;"></textarea>
				</div>
			</div>
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button type="button" class="layui-btn" lay-filter="doSubmit" lay-submit="">提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		  </div>
		  </div>
	</form>
</div>
<!-- 添加和修改的弹出层结束 -->
<!-- 查看弹出层的开始 -->
	<div style=" display:none ;padding: 20px" id="viewEmployee" >
	<div style="padding: 20px; background-color: #F2F2F2;">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-header">回复信息
					</div>
					<div class="layui-card-body"  id="replyId1"></div>
					<div class="layui-card-body"  id="messageId1"></div>
					<div class="layui-card-body"  id="messageContent1"></div>
					<div class="layui-card-body"  id="userId1"></div>
					<div class="layui-card-body"  id="replyContent1"></div>
					<div class="layui-card-body"  id="replyTime1"></div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- 查看弹出层的结束 -->
</body>
</html>