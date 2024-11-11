<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心</title>
<link rel="stylesheet" href="${ctx}/resources/css/pc/css/reset.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/style.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/style-m.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/iconfont.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/swiper-3.4.2.min.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/animate.css">
    <link rel="stylesheet" href="${ctx}/resources/layui/css/layui.css" media="all">
 	<script type="text/javascript" src="${ctx}/resources/css/pc/js/jquery-3.4.1.js"></script>
 	<script type="text/javascript" src="${ctx}/resources/layui/layui.js"></script>
 	<!-- 修改密码框格式 -->
 	<script type="text/javascript" src="${ctx}/resources/css/pc/js/modernizr.custom.js"></script>
 	<script src="${ctx}/resources/css/pc/js/hideShowPassword.min.js"></script>
 	
 	<style type="text/css">
 	img{
		 max-height:400px;
		 max-width: 500px; 
		 vertical-align:middle;
	 }
 		.layui-table-cell{
 			height:80px;
 			line-height:80px;
 		}
 		.layui-table, .layui-table-view{
 			margin:0!important;
 		}
 		.content-menu{
 			margin-left:200px;
 			width:120px;
 			height:40px;
 			line-height:40px;
 			text-align:center;
 		}
 		.menu{
 			font-size:15px;
 			
 		}
 		.menu:hover{
 			color:green;
 		}
 		.clear{
 			clear:both;
 		}
 		/*竖版选项卡*/
 		.layui-tab-title li{
            display: block;
        }
        .layui-tab-title{
            float: left;
            width: 200px;
        }
        .layui-tab-content{
            float: left;
            width: 500px;
		}
		.layui-nav{
			background-color:white!important;
			color:#666!important;
			line-height:1px!important;
		}
		.layui-this a{
			background-color:#F2F2F2!important;
		}
		/*修改密码框格式*/
		.hideShowPassword-toggle {
		    background-image: url(${ctx}/resources/css/pc/img/wink.svg);
		    background-position: 0 center;
		    background-repeat: no-repeat;
		    cursor: pointer;
		    height: 70%;
		    
		    overflow: hidden;
		    text-indent: -9999em;
		    width: 44px;
		  }
		  .hideShowPassword-toggle-hide {
		    background-position: -44px center;
		  }
		  .hideShowPassword-hidden{
		  	margin:0 0 0 0!important;
		  }
		  .hideShowPassword-shown{
		  	margin:0 0 0 0!important;
		  }
 	</style>
	<script type="text/javascript">
	layui.use([ 'jquery', 'layer', 'form', 'table', 'laydate' ,'element' ], function() {
		var $ = layui.jquery;
		var layer = layui.layer;
		var form = layui.form;
		var table = layui.table;
		var laydate = layui.laydate;
		var element=layui.element;
		
		/*竖版选项卡*/
		//触发事件
        var active = {
            tabAdd: function(){
                //新增一个Tab项
                element.tabAdd('demo', {
                    title: '新选项'+ (Math.random()*1000|0) //用于演示
                    ,content: '内容'+ (Math.random()*1000|0)
                    ,id: new Date().getTime() //实际使用一般是规定好的id，这里以时间戳模拟下
                })
            }
            ,tabDelete: function(othis){
                //删除指定Tab项
                element.tabDelete('demo', '44'); //删除：“商品管理”
 
 
                othis.addClass('layui-btn-disabled');
            }
            ,tabChange: function(){
                //切换到指定Tab项
                element.tabChange('demo', '22'); //切换到：用户管理
            }
        };
 
	        $('.site-demo-active').on('click', function(){
	            var othis = $(this), type = othis.data('type');
	            active[type] ? active[type].call(this, othis) : '';
	        });
	 
	        //Hash地址的定位
	        var layid = location.hash.replace(/^#test=/, '');
	        element.tabChange('test', layid);
	 
	        element.on('tab(test)', function(elem){
	            location.hash = 'test='+ $(this).attr('lay-id');
	        });
      //获取个人信息 
		$(function(){
			 $.ajax({
				 type:"POST",
				 url:"${ctx}/customer/getCustomerById",
				 dataType:"json",
				 data: $("#infoForm").serialize(),
				 success:function(data){
						console.log(data);
					}
			 });
		  });
      	  //渲染收藏表格
	      tableIns=table.render({
		    elem: '#samTable'
		    ,url:'${pageContext.request.contextPath}/collection/getCollectionByCustomerId'
		    ,defaultToolbar:[]
		    ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
		    ,page:true
		    ,cols: [[ 
		      {field:'sample', title: '样片图片',align:'center', width: 170,templet:function(d){
		    	  return "<img width=80 height=60 style=cursor:pointer lay-event=view src=${ctx}/file/downloadShowFile?path="+d.sample.sampleImage+" />"
		    	}}
		      ,{field:'sample', title: '样片名称', align:'center',width: 140,templet: function(d){
		          return d.sample.sampleName;
		      }}
		      ,{field:'sample', title: '样片价格',align:'center', width: 100,templet: function(d){
		          return d.sample.samplePrice;
		      }}
		      ,{field:'sample', title: '样片简介', width: 301,templet: function(d){
		          return d.sample.sampleStock;
		      }}
		      ,{fixed: 'right', title:'操作',width: 345, toolbar: '#sampleBar',align:'center'}
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
		//监听行工具事件
	   table.on('tool(samTable)', function(obj){
		  var data = obj.data; //获得当前行数据
		  console.log(data);
		  var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		  if(layEvent === 'del'){ //删除
			  layer.confirm('确定取消【'+data.sample.sampleName+'】这个收藏吗', function(index){
			       //向服务端发送删除指令
			       $.post("${pageContext.request.contextPath}/collection/deleteCollectionByCollectId",{collectId:data.collectId},function(res){
			    	   if(res.msg=="删除成功"){
			    		   layer.msg("已成功取消！"); 
			    	   }else{
			    		   layer.msg("系统异常，请稍后再试！"); 
			    	   }
			    	    //刷新数据 表格
						tableIns.reload();
			       })
			     }); 
		   }else if(layEvent==='viewImage'){
			   showImage(data);
		   }else if(layEvent==='view'){
			   view(data);
		   }
		 });
		var mainIndex;
		//查看大图
		function showImage(data){	
			mainIndex=layer.open({
				type:1,
				title:"【"+data.sample.sampleName+'】的图片',
				content:$("#viewImageDiv"),
				area:['600px','500px'],
				success:function(index){
					$("#view_img").attr("src","${ctx}/file/downloadShowFile?path="+data.sample.sampleImage);
				}
			});
		};
		function view(data){
			window.location.href = "${ctx}/pc/sampleDetails?sampleId="+data.sampleId;
		};
		function view1(data){
			window.location.href = "${ctx}/pc/sampleDetails?sampleId="+data.subscribe.sampleId;
		};
		var url;
		//打开修改密码弹出层
		$("#updatePwd").click(function(){
			mainIndex=layer.open({
				type:1,
				title:'修改密码',
				content:$("#updatePwdContent"),
				area:['800px','400px'],
				success:function(index){
					url="${ctx}/customer/updateCustomer";
				}
			});
		});
		var isTrue;
		var customerPassword=$("#customerPassword").val();
		var pwdMd5;//定义自己输入的密码被加密后的
		$("#pwd").blur(function(){
			pwdMd5=$("#pwd").val();
			console.log(pwdMd5);
			$.ajax({
				type:"POST",
				url:"${ctx}/customer/pwdToMd5",
				data:{pwd:pwdMd5},
				dataType:"json",
				success:function(data){
					console.log(data);
					console.log(data.value);
					if(data==1){
						isTrue=1;
					}
				}
			});
		});
		//验证密码  密码不符合要求则不能进行修改
		var password;
		var password2;
		$("#password").blur(function(){
			password=$("#password").val();//第一次输入的新密码
			console.log(password);
		});
		$("#password2").blur(function(){
			password2=$("#password2").val();//确认密码
			console.log(password2);
		});
		//验证密码框的密码是否为原密码
		form.verify({
		    pwd: function(){
		      if(isTrue != 1){
		        return '亲，原密码不对哦~';
		      }
		    }
		    ,password:function(value){
		    	if(value.length==0){
		    		return '请输入密码！';
		    	}else if(value.length<3){
		    		return '新密码不能小于三位';
		    	}
		    }
		    ,password2:function(value){
		    	if(password2!=password || value.lenth==0){
		    		return '两次输入密码不一致';
		    	}
		    }
		});
		var customerId=$("#customerId").val();
		//保存密码
		form.on("submit(doSubmit)",function(obj){	
			$.ajax({
				type:"POST",
				url:"${ctx}/customer/updatePcCustomer",
				data:{customerId:customerId,password:password},
				dataType:"json",
				success:function(data){
					layer.msg(data.msg+",请重新登录");
					//关闭弹出层
					layer.close(mainIndex);
					setTimeout(function(){
						//跳转至主页
	  					window.location.href="${ctx}/pc/toLogin";
					},1000);
				}
			});	
		});	
		
	   //个人资料点击保存按钮后
	   form.on("submit(updateInfo)",function(obj){	
		 //  var param=$("#infoForm").serialize();
		  // console.log(param);
			$.ajax({
				type:"POST",
				url:"${ctx}/customer/updateCustomer",
				data: $("#infoForm").serialize(),
				success:function(data){
					//弹出提示信息
					layer.msg(data.msg);
					//重新渲染表单
					form.render();
				}
			});	
		});
	   //退出登录
	   $("#exit").click(function(){
		   if(confirm("您确定要退出登录吗？")) {
			 //清除session
	  		 //sessionStorage.clear();
	  		 $.ajax({
	  			type:"POST",
	  			url:"${ctx}/personal/clearSession",
	  			dataType:"json",
	  			success:function(data){
	  				if(data.msg=="删除成功"){
	  					layer.msg("退出成功");
	  					//跳转至主页
	  					window.location.href="${ctx}/pc/index";
	  					
	  				}else{
	  					layer.msg("系统异常");
	  				}
	  			}
	  		 });
		   }
	   });
	   //意向管理  
	   //渲染表格
      tableIns2=table.render({
	    elem: '#intentTable'
	    ,url:'${pageContext.request.contextPath}/intention/getIntentionByCustomerId'
	    ,defaultToolbar:[]
	    ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
	    ,page:true
	    ,cols: [[ 
	      {field:'sample', title: '样片图片',align:'center', width: 150,templet:function(d){
	    	  return "<img width=80 height=60 style=cursor:pointer lay-event=view src=${ctx}/file/downloadShowFile?path="+d.sample.sampleImage+" />"
	    	}}
	      ,{field:'sample', title: '样片名称', align:'center',width: 140,templet: function(d){
	          return d.sample.sampleName;
	      }}
	      ,{field:'sample', title: '样片价格',align:'center', width: 80,templet: function(d){
	          return d.sample.samplePrice;
	      }}
	      ,{field:'sample', title: '样片简介', width: 250,templet: function(d){
	          return d.sample.sampleStock;
	      }}
	      ,{fixed: 'right', title:'操作', width:436,toolbar: '#intentBar',align:'center'}
	    ]],
	    done:function(data,curr,count){
	    	//不是第一页时如果当前返回的的数据为0那么就返回上一页
	    	if(data.data.length==0&&curr!=1){
	    		tableIns2.reload({
				    page:{
				    	curr:curr-1
				    }
				});
	    	}
	    }
	  });
    //监听行工具事件
	   table.on('tool(intentTable)', function(obj){
		  var data = obj.data; //获得当前行数据
		  console.log(data);
		  var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		  if(layEvent === 'del'){ //删除
			  layer.confirm('确定取消【'+data.sample.sampleName+'】这个收藏吗', function(index){
			       //向服务端发送删除指令
			       $.post("${pageContext.request.contextPath}/intention/deleteIntentionByIntentionId",{intentionId:data.intentionId},function(res){  
			    	   if(res.msg=="删除成功"){
			    		   layer.msg("已成功取消！"); 
			    	   }else{
			    		   layer.msg("系统异常，请稍后再试！"); 
			    	   }
			    	    //刷新数据 表格
						tableIns2.reload();
			       })
			     }); 
		   }else if(layEvent==='viewImage'){
			   showImage(data);
		   }else if(layEvent==='view'){
			   view(data);
		   }else if(layEvent==='addCollect'){
			   toAddCollect(data);
		   }else if(layEvent==='addSubscribe'){
			   toAddSubscribe(data);
		   }
		 });

		//查看详情
		function view(data){
			window.location.href = "${ctx}/pc/sampleDetails?sampleId="+data.sampleId;
		};
		//添加至收藏
		function toAddCollect(data){
			layer.confirm('确定添加【'+data.sample.sampleName+'】这个收藏吗', function(index){
			console.log("添加收藏  样片id"+data.sampleId+"客户id"+data.customerId);
			$.ajax({
				type:"POST",
				url:"${ctx}/collection/addPcCollection",
				data:{sampleId:data.sampleId,customerId:data.customerId},
				dataType:"json",
				success:function(data){
					if(data.msg=="添加成功"){
						console.log("添加了么"+data);
						layer.msg(data.msg);
						setTimeout(function(){
							//跳转至收藏
							window.location.reload();
						},230);
					}else{
						layer.msg("此样片已存在，请勿重复添加");
					}
				}
			});
			});
		};
		//添加至预约
		function toAddSubscribe(data){
			layer.confirm('确定添加【'+data.sample.sampleName+'】这个预约吗', function(index){
			console.log("添加收藏  客户id"+data.customerId+"样片id"+data.sampleId);
			$.ajax({
				type:"POST",
				url:"${ctx}/subscribe/addPcSubscribe",
				data:{customerId:data.customerId,sampleId:data.sampleId},
				dataType:"json",
				success:function(data){
					console.log("添加了么"+data);
					layer.msg("添加成功");
					setTimeout(function(){
						//跳转至收藏
						window.location.reload();
					},230);
				}
			});
			});
		};
		
		tableIns3 = table.render({
		    elem: '#subTable'
		    ,url:'${pageContext.request.contextPath}/subscribe/getPcSubscribeByCustomerId'
		    ,defaultToolbar:[]
		    ,title: '预约查看'
		    ,cellMinWidth:60 //设置列的最小默认宽度
		    ,page:true
		    ,cols: [[ 
		       ,{field:'sampleImage', title: '样片图片',width:130, align:'center', templet:function(d){
			    	  return "<img width=100 height=80 src=${ctx}/file/downloadShowFile?path="+d.sample.sampleImage+" />";}}
		      ,{field:'sampleName', title: '样片名称',width:135, align:'center',templet: function(d){
		          return d.sample.sampleName;
		      }}
	          ,{field:'subState', title:'预约状态',width:135, align:'center',templet:function(d){
	        	  return d.subState=='1'?'<font color=black>待审核</font>':d.subState=='2'?'<font color=green>预约成功未使用</font>':d.subState=='3'?'<font color=blue>预约成功已使用</font>':'<font color=red>已过期</font>';
	          }}
	          ,{field:'price', title:'成交价格',width:135, align:'center'}
	          ,{field:'subeTime', title:'样片预约时间',width:155, align:'center',}
	          ,{fixed: 'right', title:'操作', toolbar: '#subscribeBar',width:366, align:'center'}
		    ]],
		    done:function(data,curr,count){
		    	//不是第一页时如果当前返回的的数据为0那么就返回上一页
		    	if(data.data.length==0&&curr!=1){
		    		tableIns3.reload({
					    page:{
					    	curr:curr-1
					    }
					});
		    	}
		    }
		  });
		//监听行工具事件
		   table.on('tool(subTable)', function(obj){
			  var data = obj.data; //获得当前行数据
			  var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			  if(layEvent === 'del'){ //删除
				  layer.confirm('真的要删除【'+data.subId+'】这个预约吗', function(index){
				       //向服务端发送删除指令
				       $.post("${pageContext.request.contextPath}/subscribe/deleteSubscribeBysubscribeId",{subId:data.subId},function(res){
					    	   if(res.msg=="删除成功"){
					    		   layer.msg("已成功取消！"); 
					    	   }else{
					    		   layer.msg("系统异常，请稍后再试！"); 
					    	   }
					    	    //刷新数据 表格
								tableIns2.reload();
				    	    //刷新数据 表格
							tableIns3.reload();
				       })
				     }); 
			   }else if(layEvent==='viewImage'){
				   showImage(data);
			   }else if(layEvent==='view'){
				   view(data);
			   }
			 });
		
		   tableIns4 = table.render({
			    elem: '#orderTable'
			    ,url:'${pageContext.request.contextPath}/order/getPcOrderByCustomerId'
			    ,defaultToolbar:[]
			    ,title: '订单查看'
			    ,cellMinWidth:60 //设置列的最小默认宽度
			    ,page:true
			    ,cols: [[ 
				      ,{field:'phone', title: '手机号', width:118, align:'center',templet: function(d){
				          return d.customer.phone;
				      }}
			          ,{field:'orderId', title:'订单编号', width:165, align:'center',templet: function(d){
				          return d.orderId;
				      }}
			          ,{field:'price', title: '价格', width:165, align:'center',templet: function(d){
				          return d.subscribe.price;
				      }}
			          ,{field:'orderTime', title:'订单生成时间',width:205, align:'center'}
			          ,{fixed: 'right', title:'操作', toolbar: '#orderbeBar',width:405, align:'center'}
			    ]],
			    done:function(data,curr,count){
			    	//不是第一页时如果当前返回的的数据为0那么就返回上一页
			    	if(data.data.length==0&&curr!=1){
			    		tableIns4.reload({
						    page:{
						    	curr:curr-1
						    }
						});
			    	}
			    }
			  });
			//监听行工具事件
			   table.on('tool(orderTable)', function(obj){
				  var data = obj.data; //获得当前行数据
				  var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
				  if(layEvent === 'del'){ //删除
					  layer.confirm('真的要删除【'+data.orderId+'】这个订单吗', function(index){
					       //向服务端发送删除指令
					       $.post("${pageContext.request.contextPath}/order/deleteOrder",{orderId:data.orderId},function(res){				
						    	   if(res.msg=="删除成功"){
						    		   layer.msg("已成功取消！"); 
						    	   }else{
						    		   layer.msg("系统异常，请稍后再试！"); 
						    	   }
					    	    //刷新数据 表格
								tableIns4.reload();
					       })
					     }); 
				   }else if(layEvent==='view'){
					   view1(data);
				   }
				 });
		
	});
	</script>
 	
</head>
<body>
	<div class="content">
		<!-- 1.头部开始 -->
		<div class="header">
			<div class="header-c PC">
				<a href="${ctx}/pc/index" class="logo"> <img
					src="${ctx}/resources/css/pc/img/jiesenlogo.jpg" alt=""
					class="logo1" />
				</a>
				<ul class="nav">
					<li class="nav-item"><a href="${ctx }/pc/index">首页</a></li>
					<li class="nav-item"><a href="${ctx}/pc/sampleAppretion">样片欣赏</a>
					</li>
					<li class="nav-item"><a href="${ctx}/pc/getAboutas">关于我们</a> <span class="line"></span>
					</li>
					<li class="nav-item"><a href="${ctx}/pc/guideselection">选片指南</a></li>
					<li class="nav-item active"><a href="${ctx }/pc/toPersonalCenter">个人中心</a></li>
					<li class="nav-item" style="margin-left:30px;">
						<c:choose>
							<c:when test="${CUSTOMER==null }">
								<a href="${ctx }/pc/toLogin">登录</a>
							</c:when>
							<c:otherwise>
								<ul class="layui-nav">
									<li class="layui-nav-item" style="height:50px;line-height:50px;width:183px!important;">
										<span style="float:left;">欢迎！</span><a style="float:left;font-weight:bold;width:100px;margin-left:-15px;text-decoration:none;color:#666;">${CUSTOMER.loginName }</a>
										<dl class="layui-nav-child" style="background-color:white!important;">
									      <dd><a href="javascript:;" id="updatePwd" style="text-decoration:none!important;">修改密码</a></dd>
									      <dd><a href="javascript:;" id="exit"  style="text-decoration:none;">退出登录</a></dd>
									    </dl>
									</li>
								</ul>
							</c:otherwise>
						</c:choose>
					</li>
				</ul>
				<div class="nav-login">
					<!-- <a href="./pages/about/index.html">注册</a> -->
					
				</div>
			</div>
		</div>
		<!-- 2 -->
		<div class="containner" id="containner" style="min-height:1000px;margin:0 auto;margin-top:123px;width:1200px;clear:both">
			<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
			    <ul class="layui-tab-title" style="margin-left:-30px;margin-top:15px;">
			        <li class="layui-this">我的收藏</li>
			        <li>我的意向</li>
			        <li>我的预约</li>
			        <li>我的订单</li>
			        <li>个人信息</li>
			    </ul>
			    <div class="layui-tab-content" style="height:100px;width:1120px;margin-left:150px;margin-top:-38px;">
			        <div class="layui-tab-item layui-show">
			        	<div class="content-main" style="float:left;margin-left:50px;margin-top:-10px;width:95%;height:50%;border:1px solid #ccc;">
							<!-- <iframe src="${ctx}/pc/toCollectSample" name="iframe" frameborder="0" style="border:1px solid #ccc;" width="100%" height="600px"></iframe> -->
							<!-- 收藏样片表格 -->
						<table class="layui-hide" id="samTable" lay-filter="samTable" style="width:100%" >
							
						</table>
						<div style="clear:both"></div>
						</div>
			        </div>
			        <div class="layui-tab-item">
			       		<div class="content-main" style="float:left;margin-left:50px;margin-top:-10px;width:95%;height:50%;border:1px solid #ccc;">
							<!-- <iframe src="${ctx}/pc/toCollectSample" name="iframe" frameborder="0" style="border:1px solid #ccc;" width="100%" height="600px"></iframe> -->
							<!-- 收藏样片表格 -->
						<table class="layui-hide" id="intentTable" lay-filter="intentTable" style="width:100%" >
							
						</table>
						<div style="clear:both"></div>
						</div>
			        </div>
			        <div class="layui-tab-item">
			        	<div class="content-main" style="float:left;margin-left:50px;margin-top:-10px;width:95%;height:50%;border:1px solid #ccc;">
							<!-- <iframe src="${ctx}/pc/toCollectSample" name="iframe" frameborder="0" style="border:1px solid #ccc;" width="100%" height="600px"></iframe> -->
							<!-- 预约样片表格 -->
						<table class="layui-hide" id="subTable" lay-filter="subTable" style="width:100%" >
							
						</table>
						<div style="clear:both"></div>
						</div>
			        </div>
			        <div class="layui-tab-item">
			        	<div class="content-main" style="float:left;margin-left:50px;margin-top:-10px;width:95%;height:50%;border:1px solid #ccc;">
							<!-- <iframe src="${ctx}/pc/toCollectSample" name="iframe" frameborder="0" style="border:1px solid #ccc;" width="100%" height="600px"></iframe> -->
							<!-- 订单样片表格 -->
						<table class="layui-hide" id="orderTable" lay-filter="orderTable" style="width:100%" >
							
						</table>
						<div style="clear:both"></div>
						</div>
			        </div>
			        <div class="layui-tab-item">
			        	<div class="content-main" style="float:left;margin-left:50px;width:75%;height:50%;border:1px solid #ccc;">
						<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
						  <legend>个人资料</legend>
						</fieldset>
						<form class="layui-form layui-form-pane" id="infoForm">
							<input type="hidden" name="customerId" value="${customer.customerId }">
							<div class="layui-form-item"
								style="margin-left: 25%; margin-top: 40px;">
								<label class="layui-form-label">账号：</label>
								<div class="layui-input-inline">
									<input type="text" readonly="readonly" name="loginName" value="${customer.loginName }" 
										autocomplete="off" class="layui-input layui-disabled">
								</div>
							</div>
							<div class="layui-form-item center" style="margin-left: 25%;">
								<label class="layui-form-label">真实姓名：</label>
								<div class="layui-input-inline">
									<input type="text" name="realName" value="${customer.realName }" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							<div class="layui-form-item" style="width:300px;margin-left: 25%;" pane="">
								<label class="layui-form-label">性别：</label>
								<div class="layui-input-block" style="width:300px;">
									<input type="radio" name="sex" value="1" <c:if test="${customer.sex=='1' }">checked</c:if>  title="男" >
									<input type="radio" name="sex" value="2" <c:if test="${customer.sex=='2' }">checked</c:if> title="女">
								</div>
							</div>
							<div class="layui-form-item" style="margin-left: 25%;">
								<div class="layui-inline">
									<label class="layui-form-label">联系方式：</label>
									<div class="layui-input-inline">
										<input type="tel" name="phone" value="${customer.phone }" lay-verify="required|phone"
											autocomplete="off" class="layui-input">
									</div>
								</div>
							</div>
							<div class="layui-form-item"
								style="margin-left: 25%;margin-top: 12px;">
								<label class="layui-form-label">家庭住址：</label>
								<div class="layui-input-inline">
									<input type="text" name="address" value="${customer.address }" placeholder="这个人很懒，没有填写住址~" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							<div class="layui-form-item" style="margin-left: 25%;">
								<label class="layui-form-label">余额：</label>
								<div class="layui-input-inline" style="width: 100px;">
									<input type="text" value="${customer.balance }"  placeholder="￥"
										autocomplete="off" readonly="readonly" class="layui-input">
								</div>
							</div>
							<div class="layui-form-item" style="margin-left: 38%;">
								<button type="button" class="layui-btn" lay-submit="" lay-filter="updateInfo">保存</button>
							</div>
						</form>
						</div>
			        </div>
			    </div>
			</div>
			
			
		</div>
		<!-- 3 -->
		<div class="main" style="position:relative;margin-top:35px;width:1001px;margin:100px auto;">
			
		</div>
		<!-- 4.底部 -->
		<div class="footer">
			<div class="footer-c">
				<div class="left">
					<p>网站备案号：豫ICP备*********号</p>
					<span>增值电信业务经营许可证号：豫************</span>
				</div>
				<div class="right">
					<p>© 2020 杰森摄影</p>
					<a href="#"> 联系我们 </a> <a href="#"> 加入我们 </a>
				</div>
			</div>
		</div>
		<!-- 5.广告跟随 -->
		<div class="toup" id="advance">
			<a href="#" class="animated bounceIn"></a>
		</div>
	</div>
	</div>
	</div>
	<!-- 修改密码弹出层 -->
	<div style="display: none;padding: 20px" id="updatePwdContent" >
		<form class="layui-form" id="updateForm" style="margin-top:40px;margin-left:150px;">
			<input type="hidden" id="customerId" name="customerId" value="${customer.customerId }">
		  <div class="layui-form-item" >
		    <label class="layui-form-label" style="width:120px;">请输入原密码：</label>
		    <div class="layui-input-inline">
		      <input type="password" id="pwd" name="pwd" lay-verify="pwd" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label" style="width:120px;">请输入新密码：</label>
		    <div class="layui-input-inline">
		      <input type="password" id="password" name="password" lay-verify="password" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label" style="width:120px;">请确认新密码：</label>
		    <div class="layui-input-inline">
		      <input type="password" id="password2" name="password2" lay-verify="password2" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" lay-filter="doSubmit" lay-submit="">确认修改</button>
			      <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >重置</button>
			    </div>
		  	</div>
		 </form>
	</div>
	<!-- 收藏表格行工具 -->
	<div id="sampleBar" style="display: none;">
		<a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="view">详情</a>
		<a class="layui-btn layui-btn-xs" lay-event="viewImage">查看大图</a>
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">取消收藏</a>
	</div>
	<!-- 意向表格行工具 -->
	<div id="intentBar" style="display: none;">
		<a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="view">详情</a>
		<a class="layui-btn layui-btn-xs" lay-event="viewImage">查看大图</a>
		<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="addCollect">添加至收藏</a>
		<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="addSubscribe">添加至预约</a>
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">取消意向</a>
	</div>
	<!-- 预约行工具 -->
	<div id="subscribeBar" style="display: none;">
	<a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="view">详情</a>
	  <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="viewImage">查看大图</a>
	 <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</div>
	<!-- 订单行工具 -->
	<div id="orderbeBar" style="display: none;">
			<a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="view">详情</a>
	  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</div>
	<!-- 查看大图弹出的层 开始 -->
	<div id="viewImageDiv" style="display: none;text-align: center;">
		<img alt="图片" class="view_img" id="view_img">
	</div>
	<script src="${ctx}/resources/css/pc/js/swiper.min.js"></script>
	<script src="${ctx}/resources/css/pc/js/main.js"></script>
	<script type="text/javascript">
	//修改密码框格式
	document.write('<script src=../resources/css/pc/js/' +
		('__proto__' in {} ? 'zepto.custom' : 'jquery') +
		'.js><\/script>');
		$('#pwd').hideShowPassword({
			  // Creates a wrapper and toggle element with minimal styles.
			  innerToggle: true,
			  // Makes the toggle functional in touch browsers without
			  // the element losing focus.
			  touchSupport: Modernizr.touch
			});
		$('#password').hideShowPassword({
			  // Creates a wrapper and toggle element with minimal styles.
			  innerToggle: true,
			  // Makes the toggle functional in touch browsers without
			  // the element losing focus.
			  touchSupport: Modernizr.touch
			});
		$('#password2').hideShowPassword({
			  // Creates a wrapper and toggle element with minimal styles.
			  innerToggle: true,
			  // Makes the toggle functional in touch browsers without
			  // the element losing focus.
			  touchSupport: Modernizr.touch
			});
	</script>
</body>
</html>