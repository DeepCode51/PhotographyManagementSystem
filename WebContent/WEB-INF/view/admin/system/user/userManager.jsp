<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户管理</title>
	<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${ctx }/resources/css/index.css" media="all" />
	<link rel="stylesheet" href="${ctx }/resources/css/public.css" media="all" />
</head>
<body>

	<!-- 搜索条件开始 -->
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	  <legend>查询条件</legend>
	</fieldset>
	<form class="layui-form" action="" lay-filter="searchFrm" id="searchFrm">
	<div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label">账号</label>
	      <div class="layui-input-inline">
	        <input type="text" name="loginName" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">姓名</label>
	      <div class="layui-input-inline">
	        <input type="text" name="realName" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	     <div class="layui-inline">
	      <label class="layui-form-label">联系方式</label>
	      <div class="layui-input-inline">
	        <input type="text" name="phone" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	    <div class="layui-inline">
			<label class="layui-form-label">职位</label>
			<div class="layui-input-inline">
				<select name="job" lay-search="" id="job">
		        </select>
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">性别</label>
			<div class="layui-input-inline">
				<select name="sex">
		          <option value="">请选择</option>
		          <option value="1">男</option>
		          <option value="2">女</option>
		        </select>
			</div>
		</div>
		<div class="layui-inline">
	      <label class="layui-form-label">地址</label>
	      <div class="layui-input-inline">
	        <input type="text" name="address" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	    <div class="layui-inline">
			<label class="layui-form-label">审批权限</label>
			<div class="layui-input-inline">
				<select name="isApproval">
		          <option value="">请选择</option>
		          <option value="1">有</option>
		          <option value="2">无</option>
		        </select>
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
	<table class="layui-hide" id="userTable" lay-filter="userTable" ></table>
	<!-- 头工具栏 -->
	<div style="display:none" id="userToolBar">
		<button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="batchDelete">批量删除</button>
		<button type="button" class="layui-btn layui-btn-sm" lay-event="add">添加</button>
	</div>
	<!-- 行工具栏 -->
	<div style="display:none" id="userBar">
	  <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="reset">重置密码</a>
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
				<div class="layui-card" id="details">
					<div class='layui-card-body' id="userId1"></div>
				    <div class='layui-card-body' id="loginName1"></div>
				    <div class='layui-card-body' id="realName1"></div>
				    <div class='layui-card-body' id="sex1"></div>
				    <div class='layui-card-body' id="address1"></div>
				    <div class='layui-card-body' id="isApproval1"></div>
				    <div class='layui-card-body' id="phone1"></div>
				    <div class='layui-card-body' id="job1"></div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- 查看的弹出层结束 -->
	
	<!-- 添加和修改的弹出层开始 -->
	<div style="display:none;padding:20px;" id="addOrUpdate">
		<form class="layui-form" action="" lay-filter="dataFrm" id="dataFrm">
			<input type="hidden" name="userId">
			<div class="layui-form-item">
				<div class="layui-inline" id="userImage">
				<label class="layui-form-label" style="margin-top:50px;">工作照</label>
			      <div class="layui-input-inline">
					<div class="layui-upload-list thumbBox mag0 magt3" id="userImageDiv">
						<!-- 显示上传的图片 -->
						<img class="layui-upload-img thumbImg" id="showUserImage">
						<!-- 保存当前显示图片的地址 -->
						<input type="hidden" name="userImage" id="userImage1">
					</div>
				  </div>
			    </div>
			    <div class="layui-inline" id="loginName">
			      <label class="layui-form-label"><i style="color: red;">* </i>账号</label>
			      <div class="layui-input-inline">
			        <input type="text" name="loginName" lay-verify="required|isExist" placeholder="请输入账号" autocomplete="off" class="layui-input">
			      </div>
			    </div>
			    <div class="layui-inline">
					<label class="layui-form-label"><i style="color: red;">* </i>姓名</label>
					<div class="layui-input-inline">
						<input type="text" name="realName" lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
					</div>
				</div>
			    <div class="layui-inline password" style="display:none">
			      <label class="layui-form-label"><i style="color: red;">* </i>密码</label>
			      <div class="layui-input-inline">
			        <input type="password" name="password" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
			      </div>
			    </div>
			    <div class="layui-inline password" style="display:none">
			      <label class="layui-form-label"><i style="color: red;">* </i>确认密码</label>
			      <div class="layui-input-inline">
			        <input type="password" name="password" lay-verify="required|confirmPass" placeholder="请确认密码" autocomplete="off" class="layui-input">
			      </div>
			    </div>
			    <div class="layui-inline">
			      <label class="layui-form-label"><i style="color: red;">* </i>性别</label>
			      <div class="layui-input-inline">
			        <select name="sex">
			          <option value="1">男</option>
			          <option value="2">女</option>
			        </select>
			      </div>
			    </div>
			    <div class="layui-inline">
			      <label class="layui-form-label">地址</label>
			      <div class="layui-input-inline">
			        <input type="text" name="address" lay-verify="" placeholder="请输入地址" autocomplete="off" class="layui-input">
			      </div>
			    </div>
			    <div class="layui-inline" id="isApproval">
			      <label class="layui-form-label"><i style="color: red;">* </i>审批权限</label>
			      <div class="layui-input-inline">
			        <select name="isApproval">
			          <option value="1">有</option>
			          <option value="2">无</option>
			        </select>
			      </div>
			    </div>
			    <div class="layui-inline" id="member">
			      <label class="layui-form-label"><i style="color: red;">* </i>成员阵容</label>
			      <div class="layui-input-inline">
			        <select name="member">
			          <option value="1">是</option>
			          <option value="2">否</option>
			        </select>
			      </div>
			    </div>
			    <div class="layui-inline">
			      <label class="layui-form-label"><i style="color: red;">* </i>联系方式</label>
			      <div class="layui-input-inline">
			        <input type="text" name="phone" lay-verify="required|phone" placeholder="请输入联系方式" autocomplete="off" class="layui-input">
			      </div>
			    </div>
			    <div class="layui-inline">
			      <label class="layui-form-label"><i style="color: red;">* </i>职位</label>
			      <div class="layui-input-inline">
			        <input type="text" name="job" lay-verify="required" placeholder="请输入职位" autocomplete="off" class="layui-input">
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
		    
		    //渲染数据表格
		   var tableIns=table.render({
		        elem: '#userTable'
		        ,url:'${ctx}/user/getUserAll'
		        ,page: true
		        ,toolbar: '#userToolBar' //开启头部工具栏，并为其绑定左侧模板
		       	,defaultToolbar: ['filter', 'print']
		        ,title: '用户数据表'
		        ,cols: [[
		           {type: 'checkbox', fixed: 'left',align:'center'}
		          ,{field:'userId', title:'ID', unresize: true, sort: true, align:'center',width:70}
		          ,{field:'loginName', title:'账号', align:'center'}
		          ,{field:'realName', title:'姓名', align:'center'}
		          ,{field:'sex', title:'性别', align:'center',width:70,templet: function(data){
		            return data.sex==1?"男":"女";
		          }}
		          ,{field:'address', title:'地址', align:'center'}
		          ,{field:'isApproval', title:'审批权限', align:'center',width:90,templet: function(data){
		            return data.isApproval==1?"有":"无";
		          }}
		          ,{field:'phone', title:'联系方式', align:'center',width:150}
		          ,{field:'job', title:'职位', align:'center',width:120}
		          ,{fixed: 'right', title:'操作', toolbar: '#userBar',width:240, align:'center'}
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
		    
			   $(function(){
				//多条件的职位下拉框
				  $.post("${pageContext.request.contextPath}/user/getUserByJob",function(data){
					if(data!=null && data!=""){
				          var html="";
				         	  html+="<option value=''>请选择</option>";
				          $.each(data,function(index,job){
				        	  html+="<option value="+job+">"+job+"</option>";
				          })
				           $("#job").append(html);
			         }
					form.render('select');
				  })
				 })
		    
		 	 //模糊查询
			$("#doSearch").click(function(){
				//序列号表单数据
				var params=$("#searchFrm").serialize();
				 //刷新数据表格
				tableIns.reload({
					url:"${ctx}/user/getUserAll?"+params
					,page:{curr:1}
				})
			});
		 	 
			//监听头部工具栏事件
			  table.on('toolbar(userTable)', function(obj){
				  switch(obj.event){
				    case 'add':
				      	openAddUser();
				    break;
				    case 'batchDelete':
				    	openDeleteUser();
				    break;
				  };
				});
			  
			  //监听工具条 
			  table.on('tool(userTable)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
			    var data = obj.data; //获得当前行数据
			    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			    if(layEvent === 'detail'){ //查看
			        detail(data);
			      } else if(layEvent === 'del'){ //删除
			      	layer.confirm('确定删除【'+data.realName+'】吗?',{icon: 3, title:'提示'}, function(index){
			        //向服务端发送删除指令
			        $.post("${ctx}/user/deleteUser",{userId:data.userId},function(obj){
			        	layer.msg(obj.msg);
			        	//刷新数据 表格
						tableIns.reload();
			        })
			      });
			    } else if(layEvent === 'edit'){ //编辑
			    	openUpdateUser(data);
			    } else if(layEvent === 'reset'){ //编辑
			    	layer.confirm('确定要重置【'+data.realName+'】的密码为123456吗?',{icon: 3, title:'提示'}, function(index){
				        //向服务端发送删除指令
				        $.post("${ctx}/user/updateUserPwd",{userId:data.userId},function(obj){
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
					  title:'查看【'+data.realName+'】的信息',
					  content:$("#detail"),
					  area:['400px','500px'],
					  success:function(index){
						  var sex = "";
						  if(data.sex==1){
							  sex="男";
						  }else{
							  sex="女";
						  }
						  var isApproval = "";
						  if(data.isApproval==1){
							  isApproval="有";
						  }else{
							  isApproval="无";
						  }
						  var member = "";
						  if(data.member==1){
							  member="是";
						  }else{
							  member="否";
						  }
						  $("#userId1").html("用户编号 ："+data.userId);
						  $("#loginName1").html("用户账号 ："+data.loginName);
						  $("#realName1").html("用户姓名 ："	+data.realName);
						  $("#sex1").html("用户性别 ："	+sex);
						  $("#address1").html("用户地址 ："+data.address); 
						  $("#isApproval1").html("审批权限 ："+isApproval);
						  $("#phone1").html("联系方式 ："+data.phone);
						  $("#job1").html("用户职位 ："+data.job);
					  }
				  })
				  $("#details").append(html);
			  }
			  
			  var url;
			  var mainIndex;
			  //打开添加页面
			  function openAddUser(){
				  mainIndex=layer.open({
					  type:1,
					  title:'添加用户',
					  content:$("#addOrUpdate"),
					  area:['400px','500px'],
					  success:function(index){
						  $("#member").hide();
						  $("#userImage").hide();
						  $("#loginName").show();
						  $("#isApproval").show();
						  $('input[name=loginName]').attr("lay-verify","required|isExist");
						  $(".password").show();
						  $("#showUserImage").removeAttr("src");
						  //清空表单数据
						  $("#dataFrm")[0].reset();
						  url="${ctx}/user/addUser";
					  }
				  })
			  }
			  
			  //打开修改页面
			  function openUpdateUser(data){
				  mainIndex=layer.open({
					  type:1,
					  title:'修改【'+data.realName+'】用户',
					  content:$("#addOrUpdate"),
					  area:['400px','420px'],
					  success:function(index){
						  $.ajax({
							  url:"${ctx}/user/isApproval",
							  type:"post",
							  success:function(data){
								  if(data==2){
									  $("#isApproval").hide();
								  }
							  }
						  })
						  $("#member").hide();
						  $("#userImage").hide();
						  $("#loginName").hide();
						  $('input[name=loginName]').attr("lay-verify","required");
						  $(".password").hide();
						  $("#showUserImage").attr("src","${ctx}/file/downloadShowFile?path="+data.userImage);
						  //初始化表单
						  form.val("dataFrm",data);
			      		  url="${ctx}/user/updateUser";
					  }
				  })
			  }
			  
			//上传缩略图
		    upload.render({
		        elem: '#userImageDiv',
		        url: '${ctx}/file/uploadFile',
		        method : "post",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
		        acceptMime:'images/*',
		        field:"mf",
		        //auto: false,
		       // bindAction: '#doSubmit',
		        done: function(res, index, upload){
		            $('#showUserImage').attr('src',"${ctx}/file/downloadShowFile?path="+res.data.src);
		            $('#userImage1').val(res.data.src);
		            $('#userImageDiv').css("background","#fff");
		        }
		    });
			  
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
			  function openDeleteUser(){
				  var checkStatus = table.checkStatus('userTable');
				  var data = checkStatus.data;
				  if(data.length > 0){
					  layer.confirm('确定删除这些用户吗?', {icon: 3, title:'提示'},function(index){
						  //map() 方法返回一个新数组，数组中的元素为原始数组元素调用函数处理后的值。
						  var userIds = data.map(function(item){
							  return item.userId
						  })
						  //向服务端发送请求
						  $.ajax({
					    	   url:"${ctx}/user/deleteUserByArray?userIds="+userIds,
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
			  
			  // 校验两次密码是否一致
			  form.verify({
			      confirmPass:function(value){
			          if($('input[name=password]').val() !== value)
			              return '两次密码输入不一致！';
			      }
			  });
			  
			  //账号是否存在
			  form.verify({
			    isExist:function(value){
			    	var data;
			        $.ajax({
			            url: "${ctx}/user/getUserLoginName?loginName="+value,
			            type: "post",
			            async: false,
			            success: function(result) {
			            	data=result;
			            }
			        })
			       	if(!data){
			       		return '该账号已存在';
			       	}
			    }
			});
		    
		})
	</script>
</body>
</html>