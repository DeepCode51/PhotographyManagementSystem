<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>杰森工作室-类别管理</title>
<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css">
<link>
<script type="text/javascript" src="${ctx }/resources/layui/layui.js"></script>
<script type="text/javascript">
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
    ,url: '${ctx }/type/getAllType' //数据接口
    ,page: true //true表示分页
    ,toolbar: "#empToolBar"//表格的工具条
    ,defaultToolbar: ['filter', 'print']
    ,cols: [[ //表头
       {type: 'checkbox', fixed: 'left',align:'center'}
      ,{field: 'typeId', title: '编号', sort: true, align:'center'}
      ,{field: 'typeName', title: '类别',align:'center'}
      ,{field: 'remark', title: '备注',align:'center'}
      ,{fixed: 'right', title:'操作', toolbar: '#empBar', width:200 ,align:'center'}
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
      layer.confirm('确定删除【'+data.typeName+'】吗?',{icon: 3, title:'提示'}, function(index){
        //向服务端发送删除指令
        $.post("${pageContext.request.contextPath}/type/deleteTypeBytypeId",{typeId:data.typeId},function(data){
        	layer.msg(data.msg);
        	//刷新数据 表格
			tableIns.reload();
        })
      });
    } else if(layEvent === 'edit'){ //编辑
    	openUpdateEmployee(data);
    } 
  });
  
  var url;
  var mainIndex;

	
  
  //打开添加页面
  function openAddEmp(){
	  mainIndex=layer.open({
		  type:1,
		  title:'添加类别',
		  content:$("#addOrUpdate"),
		  area:['500px','400px'],
		  success:function(index){
			  //清空表单数据
			  $("#dataForm")[0].reset();
			  url="${ctx }/type/addType";
		  }
	  })
  }
	//打开修改页面
	function openUpdateEmployee(data){
		mainIndex=layer.open({
			type:1,
			title:"修改【"+data.typeName+"】类别",
			content:$("#addOrUpdate"),
			area:['500px','400px'],
			success:function(index){
				form.val("dataForm",data);
				url="${ctx }/type/updateType";
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
			title:"查看【"+data.typeName+"】的信息",
			content:$("#viewEmployee"),
			area:['500px','350px'],
			success:function(index){
				$.post("${ctx }/type/getTypeByTypeId",{typeId:data.typeId},
						function(type){
					console.log(type);
					$("#typeId1").html("类别编号 ："	+type.typeId);
					$("#typeName1").html("类别名称 ："	+type.typeName);
					$("#remark1").html("备注 ："	+type.remark);
				})
			}
		});
	};
  
	
	//模糊查询
	$("#doSearch").click(function(){
		var params=$("#searchFrm").serialize();
		tableIns.reload({
			url:"${ctx }/type/getAllType?"+params
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
      <label class="layui-form-label">类别名称</label>
      <div class="layui-input-inline">
        <input type="tel" name="typeName" id="typeName" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">备注</label>
      <div class="layui-input-inline">
        <input type="text" name="remark" id="remark" autocomplete="off" class="layui-input">
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
	<button type="button" class="layui-btn layui-btn-sm" lay-event="add">添加</button>
</div>
<!-- 行工具栏 -->
<div style="display:none" id="empBar">
  <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="detail">查看</a>
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</div>
<!-- 数据表格结束 -->

<!-- 添加和修改的弹出层开始 -->
<div style="display:none;padding:20px;" id="addOrUpdate">
	<form class="layui-form" action="" lay-filter="dataForm" id="dataForm">
		<input type="hidden" name="typeId">
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label">类别</label>
		      <div class="layui-input-inline">
		        <input type="text" name="typeName" lay-verify="required" autocomplete="off" class="layui-input">
		      </div>
		    </div>
		    <div class="layui-inline">
				<label class="layui-form-label">备注:</label><br>
				<div class="layui-input-inline">
				<textarea name="remark"	style="margin-left:107px;width:304px; height:163px;"></textarea>
				
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
					<div class="layui-card-header">类别信息
					</div>
					<div class="layui-card-body"  id="typeId1"></div>
					<div class="layui-card-body"  id="typeName1"></div>
					<div class="layui-card-body"  id="remark1"></div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- 查看弹出层的结束 -->
</body>
</html>