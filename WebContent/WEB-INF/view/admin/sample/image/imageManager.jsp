<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>杰森工作室-图片管理</title>
</head>
<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="${ctx }/resources/css/public.css" media="all" />
<script src="${ctx}/resources/layui/layui.js"></script>
<style>
 img{
		 max-height:400px;
		 max-width: 500px; 
		 vertical-align:middle;
	 }

</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/echarts/js/jquery-3.1.1.min.js"></script>
<body class="childrenBody">
<!-- 搜索条件开始 -->
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	  <legend>查询条件</legend>
	</fieldset>
	<form class="layui-form" method="post" id="searchFrm">
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label">样片编号:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="sampleId"  autocomplete="off" class="layui-input">
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
	<table class="layui-hide" id="imageTable" lay-filter="imageTable"></table>
	<!--头工具栏 -->
	<div style="display: none;" id="imageToolBar">
	   <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
       <button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="deleteBatch">批量删除</button>
	</div>
	<!-- 行工具 -->
	<div  id="imageBar" style="display: none;">
	  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	  <a class="layui-btn layui-btn-xs" lay-event="viewImage">查看大图</a>
	  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</div>
<!-- 数据表格结束 -->
<!-- 添加弹出层开始 -->
<div style="display: none;padding: 20px" id="saveDiv" >
		<form class="layui-form"  lay-filter="dataFrm1" id="dataFrm1">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">所属样片Id:</label>
					<div class="layui-input-inline">
						<input type="text" name="sampleId" id="sampleId" lay-verify="required|isExist"   placeholder="请输入样片Id" autocomplete="off"class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-upload">
				<button type="button" class="layui-btn" id="test2">多图片上传</button>
				<blockquote class="layui-elem-quote layui-quote-nm"
					style="margin-top: 10px;">
					预览图：
					<div class="layui-upload-list" id="demo2"></div>
				</blockquote>
			</div>
			<div class="layui-form-item" style="text-align: center;">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn" lay-filter="doSubmit1" lay-submit="">提交</button>
			      <button type="reset" class="layui-btn layui-btn-primary" >重置</button>
			    </div>
		  	</div>
		</form>
	</div>
<!-- 添加结束 -->
<!-- 修改的弹出层开始 -->
	<div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
		<form class="layui-form layui-row layui-col-space10"  lay-filter="dataFrm" id="dataFrm">
			<div class="layui-col-md12 layui-col-xs12">
				<div class="layui-row layui-col-space10">
					<div class="layui-input-block">
						<input type="hidden" name="imageId">
						<input type="hidden" name="sampleId">
					</div>
						<div class="layui-upload-list thumbBox mag0 magt3" id="imgDiv">
							<!-- 显示上传的图片 -->
							<img class="layui-upload-img thumbImg"  id="showImg">
							<!-- 保存当前显示图片的地址 -->
							<input type="hidden" name="imageAddress" id="imageAddress1">
						</div>
				</div>
				<div class="layui-form-item magb0" style="text-align: center;">
					    <div class="layui-input-block">
					      <button type="button" class="layui-btn" lay-filter="doSubmit" lay-submit="">提交</button>
					    </div>
				</div>
			</div>
		</form>
	</div>
	<!-- 修改的弹出层结束 -->
<!-- 查看大图弹出的层 开始 -->
	<div id="viewImageDiv" style="display: none;text-align: center;">
		<img alt="图片" class="view_img" id="view_img">
	</div>
<!-- 查看大图弹出的层 结束 -->
</body>
<script type="text/javascript">
var tableIns;
layui.use([ 'jquery', 'layer', 'form', 'table','upload' ], function() {
	var $ = layui.jquery;
	var layer = layui.layer;
	var form = layui.form;
	var table = layui.table;
	var upload = layui.upload;
	//数据表格渲染
	tableIns=table.render({
	    elem: '#imageTable' //渲染的目标对象
	    ,url:'${ctx}/image/getAllImage.action' //数据接口
	    ,toolbar: '#imageToolBar' //开启头部工具栏，并为其绑定左侧模板
	    ,defaultToolbar: ['filter', 'print']
		,cellMinWidth:100 //设置列的最小默认宽度
	    ,title: '图片数据表'
	    ,page: true
	    ,cols: [[
	      {type: 'checkbox', fixed: 'left'}
	      ,{field:'imageId', title:'ID',align:'center',sort: true}
	      ,{field:'imageAddress', title:'缩略图',align:'center',templet:function(d){
	    	  return "<img width=40 height=30 src=${ctx}/file/downloadShowFile?path="+d.imageAddress+" />";
	      }}
	      ,{field:'sampleName', title:'样片名称',align:'center',templet:function(d){
	    	  return d.sample.sampleName;
	      }}
	      ,{field:'sampleId', title:'样片编号',align:'center',}
	      ,{fixed: 'right',title:'操作', toolbar: '#imageBar',align:'center'}
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
	
	 //样片Id是否存在
	  form.verify({
		 isExist:function(value){
	    	var data;
	        $.ajax({
	            url: "${ctx}/image/getImageBySampleId?sampleId="+value,
	            type: "post",
	            async: false,
	            success: function(result) {
	            	data=result;
	            }
	        })
	       	if(!data){
	       		return '样片编号不存在';
	       	}
	    }
	});
	
	
	//模糊查询
	$("#doSearch").click(function(){
		var params=$("#searchFrm").serialize();
		tableIns.reload({
			url:"${pageContext.request.contextPath}/image/getAllImage?"+params
			,page:{curr:1}		
		})
	});
	//监听头部工具栏事件
	table.on("toolbar(imageTable)",function(obj){
		 switch(obj.event){
		    case 'add':
		      openAddImage();
		    break;
		    case 'deleteBatch':
		      deleteBatch();
			break;
		  };
	});
	//监听行工具事件
	   table.on('tool(imageTable)', function(obj){
		   var data = obj.data; //获得当前行数据
		   var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		  if(layEvent === 'del'){ //删除
			  layer.confirm('真的删除样片名是【'+data.sample.sampleName+'】下的这个图片吗', function(index){
			       //向服务端发送删除指令
			       $.post("${pageContext.request.contextPath}/image/deleteImageByImageId",{imageId:data.imageId},function(res){
			    	   layer.msg(res.msg);
			    	    //刷新数据 表格
						tableIns.reload();
			       })
			     }); 
		   } else if(layEvent === 'edit'){ //编辑
		     openUpdateImage(data);
		   }else if(layEvent==='viewImage'){
			   showImage(data);
		   }
		 });
	var url;
	var mainIndex;
	var arr=[];
	//打开添加页面
	function openAddImage(){
		mainIndex=layer.open({
			type:1,
			title:'添加图片',
			content:$("#saveDiv"),
			area:['800px','400px'],
			success:function(index){
				arr.length = 0;
				//清空表单数据       
				$("#dataFrm1")[0].reset();
				$('#demo2').html("");
			}
		});
	}
	
	//打开修改页面
	function openUpdateImage(data){
		mainIndex=layer.open({
			type:1,
			title:'修改样片名是【'+data.sample.sampleName+'】下的图片',
			content:$("#saveOrUpdateDiv"),
			area:['450px','450px'],
			success:function(index){
				form.val("dataFrm",data);
				$("#showImg").attr("src","${ctx}/file/downloadShowFile?path="+data.imageAddress);
				//修改时的绑定
				url="${ctx}/image/updateImage";
			}
		});
	}
	
	//上传缩略图
    upload.render({
        elem: '#imgDiv',
        url: '${ctx}/file/uploadFile',
        method : "post",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
        accept:'images',
        acceptMime:'image/*',
        field:"mf",
        done: function(res, index, upload){
            $('#showImg').attr('src',"${ctx}/file/downloadShowFile?path="+res.data.src);
            $('#imageAddress1').val(res.data.src);
            $('#sampleimgDiv').css("background","#fff");
        }
    });
	
	//多图片上传
	  upload.render({
	    elem: '#test2'
	    ,url: '${ctx}/file/uploadFile' //改成您自己的上传接口
	    ,multiple: true
	    ,accept:'images'
        ,acceptMime:'image/*'
	    ,field:"mf"
	    ,before: function(obj){
	      //预读本地文件示例，不支持ie8
	      obj.preview(function(index, file, result){
	        $('#demo2').append('<img src="'+ result +'" style="width:200px;height:200px"; alt="'+ file.name +'" class="layui-upload-img">   ')
	      });
	    }
	    ,done: function(res,index, upload){
	    	 arr.push(res.data.src);
	    }
	  });
	//保存
	form.on("submit(doSubmit1)",function(){
		var sampleId1 = $("#sampleId").val();
	    $.ajax({
	    	   url:"${pageContext.request.contextPath}/image/addImagesByArray",
	    	   type:'post',
	    	   data:"images1="+arr+"&sampleId="+sampleId1,
	    	   success:function(res){
	    		   layer.msg(res.msg);
	    		   layer.close(mainIndex);
		    	    //刷新数据 表格
					tableIns.reload();
					
	    	   }
	       });
	});
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
	function deleteBatch(){
		//得到选中的数据行
		var checkStatus = table.checkStatus('imageTable');
	    var data = checkStatus.data;
	    //console.log(result);
	    //console.log(data);
	    if(checkStatus.data.length>0){
	    layer.confirm('真的删除选中的这些图片吗', function(index){
		    	 //将对象数组中的 imageId属性 转成数组
			    var result = data.map(function(item) {
		            return item.imageId;
				});
		    	 //向服务端发送删除指令
		       $.ajax({
		    	   url:"${pageContext.request.contextPath}/image/deleteImageByArray",
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
	//查看大图
	function showImage(data){
		mainIndex=layer.open({
			type:1,
			title:'查看样片名是【'+data.sample.sampleName+'】下大图',
			content:$("#viewImageDiv"),
			area:['600px','500px'],
			success:function(index){
				$("#view_img").attr("src","${ctx}/file/downloadShowFile?path="+data.imageAddress);
			}
		});
	}
	
});
</script>
