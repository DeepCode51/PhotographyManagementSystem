<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>首页-杰森工作室系统</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="Access-Control-Allow-Origin" content="*">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	
	<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${ctx }/resources/css/index.css" media="all" />
	<style type="text/css">
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
</head>
<body class="main_body">
	<div class="layui-layout layui-layout-admin">
		<!-- 顶部 -->
		<div class="layui-header header">
			<div class="layui-main mag0">
				<a href="#" class="logo">杰森工作室系统</a>
				<!-- 显示/隐藏菜单 -->
				<a href="javascript:;" class="seraph hideMenu icon-caidan"></a>
			    <!-- 顶部右侧菜单 -->
			    <ul class="layui-nav top_menu">
					<li class="layui-nav-item" pc>
						<a href="javascript:;" class="clearCache"><i class="layui-icon" data-icon="&#xe640;">&#xe640;</i><cite>清除缓存</cite><span class="layui-badge-dot"></span></a>
					</li>
					<li class="layui-nav-item" id="userInfo">
						<a href="javascript:;"><img src="${ctx }/resources/images/face.jpg" class="layui-nav-img userAvatar" width="35" height="35"><cite class="adminName">${adminUser.realName}</cite></a>
						<dl class="layui-nav-child">
							<dd><a href="javascript:;" data-url="${ctx }/userinfo/toUserInfo"><i class="seraph icon-ziliao" data-icon="icon-ziliao"></i><cite>个人资料</cite></a></dd>
							<dd><a href="javascript:;" id="updatePwd"><i class="seraph icon-xiugai" data-icon="icon-xiugai"></i><cite>修改密码</cite></a></dd>
							<dd pc><a href="javascript:;" class="functionSetting"><i class="layui-icon">&#xe620;</i><cite>功能设定</cite></a></dd>
							<dd pc><a href="javascript:;" class="changeSkin"><i class="layui-icon">&#xe61b;</i><cite>更换皮肤</cite></a></dd>
							<dd><a href="${ctx}/userinfo/outLogin" class="signOut"><i class="seraph icon-tuichu"></i><cite>退出</cite></a></dd>
						</dl>
					</li>
				</ul>
			</div>
		</div>
		<!-- 左侧导航 -->
		<div class="layui-side layui-bg-black">
			<div class="user-photo">
				<a class="img" title="我的头像" ><img src="${ctx }/resources/images/face.jpg" class="userAvatar"></a>
				<p>你好！<span class="userName">${adminUser.realName }</span>, 欢迎登录</p>
			</div>
			<div class="navBar layui-side-scroll" id="navBar">
				<ul class="layui-nav layui-nav-tree">
					<li class="layui-nav-item layui-this">
						
						<a href="javascript:;" data-url="${ctx}/statistics/ordersMonthStat"><i class="layui-icon" data-icon=""></i><cite>后台首页</cite></a>
						 
					</li>
				</ul>
			</div>
		</div>
		<!-- 右侧内容 -->
		<div class="layui-body layui-form">
			<div class="layui-tab mag0" lay-filter="bodyTab" id="top_tabs_box">
				<ul class="layui-tab-title top_tab" id="top_tabs">
					<li class="layui-this" lay-id=""><i class="layui-icon">&#xe68e;</i> <cite>后台首页</cite></li>
				</ul>
				<ul class="layui-nav closeBox">
				  <li class="layui-nav-item">
				    <a href="javascript:;"><i class="layui-icon caozuo">&#xe643;</i> 页面操作</a>
				    <dl class="layui-nav-child">
					  <dd><a href="javascript:;" class="refresh refreshThis"><i class="layui-icon">&#xe669;</i> 刷新当前</a></dd>
				      <dd><a href="javascript:;" class="closePageOther"><i class="seraph icon-prohibit"></i> 关闭其他</a></dd>
				      <dd><a href="javascript:;" class="closePageAll"><i class="seraph icon-guanbi"></i> 关闭全部</a></dd>
				    </dl>
				  </li>
				</ul>
				<div class="layui-tab-content clildFrame">
					<div class="layui-tab-item layui-show">
					 
						<iframe src="${ctx}/statistics/ordersMonthStat"></iframe>
					</div>
				</div>
			</div>
		</div>
		<!-- 底部 -->
		<div class="layui-footer footer">
			<p><span>杰森工作室系统</span></p>
		</div>
	</div>

	<!-- 移动导航 -->
	<div class="site-tree-mobile"><i class="layui-icon">&#xe602;</i></div>
	<div class="site-mobile-shade"></div>
	
	<!-- 修改密码弹出层 -->
	<div style="display: none;padding: 20px" id="updatePwdContent" >
		<form class="layui-form" id="updateForm" style="margin-top:40px;margin-left:150px;">
			<input type="hidden" id="userId" name="userId" value="${adminUser.userId }">
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
	
	
	<script type="text/javascript" src="${ctx }/resources/layui/layui.js"></script>
	<script type="text/javascript" src="${ctx }/resources/js/cache.js"></script>
	<script type="text/javascript">
	var $,tab,dataStr,layer;
	layui.config({
		base : "${ctx }/resources/js/"
	}).extend({
		"bodyTab" : "bodyTab"
	})
	layui.use(['bodyTab','form','element','layer','jquery'],function(){
		var form = layui.form,
			element = layui.element;
			$ = layui.$;
	    	layer = parent.layer === undefined ? layui.layer : top.layer;
			tab = layui.bodyTab({
				openTabNum : "50",  //最大可打开窗口数量
				url : "${ctx }/resources/json/navs.json" //获取菜单json地址
			});

		//通过顶部菜单获取左侧二三级菜单   注：此处只做演示之用，实际开发中通过接口传参的方式获取导航数据
		function getData(json){
			$.getJSON(tab.tabConfig.url,function(data){
	                dataStr = data;
	                //重新渲染左侧菜单
	                tab.render();
			})
		}
		//页面加载时判断左侧菜单是否显示
		//通过顶部菜单获取左侧菜单
		$(".topLevelMenus li,.mobileTopLevelMenus dd").click(function(){
			if($(this).parents(".mobileTopLevelMenus").length != "0"){
				$(".topLevelMenus li").eq($(this).index()).addClass("layui-this").siblings().removeClass("layui-this");
			}else{
				$(".mobileTopLevelMenus dd").eq($(this).index()).addClass("layui-this").siblings().removeClass("layui-this");
			}
			$(".layui-layout-admin").removeClass("showMenu");
			$("body").addClass("site-mobile");
			getData($(this).data("menu"));
			//渲染顶部窗口
			tab.tabMove();
		})

		//隐藏左侧导航
		$(".hideMenu").click(function(){
			if($(".topLevelMenus li.layui-this a").data("url")){
				layer.msg("此栏目状态下左侧菜单不可展开");  //主要为了避免左侧显示的内容与顶部菜单不匹配
				return false;
			}
			$(".layui-layout-admin").toggleClass("showMenu");
			//渲染顶部窗口
			tab.tabMove();
		})

		//通过顶部菜单获取左侧二三级菜单   注：此处只做演示之用，实际开发中通过接口传参的方式获取导航数据
		getData("contentManagement");

		//手机设备的简单适配
	    $('.site-tree-mobile').on('click', function(){
			$('body').addClass('site-mobile');
		});
	    $('.site-mobile-shade').on('click', function(){
			$('body').removeClass('site-mobile');
		});

		// 添加新窗口
		$("body").on("click",".layui-nav .layui-nav-item a:not('.mobileTopLevelMenus .layui-nav-item a')",function(){
			//如果不存在子级
			if($(this).siblings().length == 0){
				addTab($(this));
				$('body').removeClass('site-mobile');  //移动端点击菜单关闭菜单层
			}
			$(this).parent("li").siblings().removeClass("layui-nav-itemed");
		})

		//清除缓存
		$(".clearCache").click(function(){
			window.sessionStorage.clear();
	        window.localStorage.clear();
	        var index = layer.msg('清除缓存中，请稍候',{icon: 16,time:false,shade:0.8});
	        setTimeout(function(){
	            layer.close(index);
	            layer.msg("缓存清除成功！");
	        },1000);
	    })

		//刷新后还原打开的窗口
	    if(cacheStr == "true") {
	        if (window.sessionStorage.getItem("menu") != null) {
	            menu = JSON.parse(window.sessionStorage.getItem("menu"));
	            curmenu = window.sessionStorage.getItem("curmenu");
	            var openTitle = '';
	            for (var i = 0; i < menu.length; i++) {
	                openTitle = '';
	                if (menu[i].icon) {
	                    if (menu[i].icon.split("-")[0] == 'icon') {
	                        openTitle += '<i class="seraph ' + menu[i].icon + '"></i>';
	                    } else {
	                        openTitle += '<i class="layui-icon">' + menu[i].icon + '</i>';
	                    }
	                }
	                openTitle += '<cite>' + menu[i].title + '</cite>';
	                openTitle += '<i class="layui-icon layui-unselect layui-tab-close" data-id="' + menu[i].layId + '">&#x1006;</i>';
	                element.tabAdd("bodyTab", {
	                    title: openTitle,
	                    content: "<iframe src='" + menu[i].href + "' data-id='" + menu[i].layId + "'></frame>",
	                    id: menu[i].layId
	                })
	                //定位到刷新前的窗口
	                if (curmenu != "undefined") {
	                    if (curmenu == '' || curmenu == "null") {  //定位到后台首页
	                        element.tabChange("bodyTab", '');
	                    } else if (JSON.parse(curmenu).title == menu[i].title) {  //定位到刷新前的页面
	                        element.tabChange("bodyTab", menu[i].layId);
	                    }
	                } else {
	                    element.tabChange("bodyTab", menu[menu.length - 1].layId);
	                }
	            }
	            //渲染顶部窗口
	            tab.tabMove();
	        }
	    }else{
			window.sessionStorage.removeItem("menu");
			window.sessionStorage.removeItem("curmenu");
		}
		
		
		var url;
		//打开修改密码弹出层
		$("#updatePwd").click(function(){
			mainIndex=layer.open({
				type:1,
				title:'修改密码',
				content:$("#updatePwdContent"),
				area:['800px','400px'],
				success:function(index){
					url="${ctx}/user/updateUserById";
				}
			});
		});
		var isTrue;
		//var customerPassword=$("#customerPassword").val();
		var pwdMd5;//定义自己输入的密码被加密后的
		$("#pwd").blur(function(){
			pwdMd5=$("#pwd").val();
			console.log(pwdMd5);
			$.ajax({
				type:"POST",
				url:"${ctx}/user/pwdToMd5",
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
		    	if(value.length<6){
		    		return '密码长度不能小于6位！';
		    	}
		    }
		    ,password2:function(value){
		    	if(password2!=password || value.lenth==0){
		    		return '两次输入密码不一致';
		    	}
		    }
		});
		var userId=$("#userId").val();
		//保存密码
		form.on("submit(doSubmit)",function(obj){	
			$.ajax({
				type:"POST",
				url:"${ctx}/user/updateUserById",
				data:$("#updateForm").serialize(),
				dataType:"json",
				success:function(data){
					layer.msg(data.msg);
					//关闭弹出层
					layer.close(mainIndex);
					setTimeout(window.location.href="${ctx}/userinfo/outLogin", 2000);
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
		
	})

	//打开新窗口
	function addTab(_this){
		tab.tabAdd(_this);
	}
	
	
	</script>
	<script type="text/javascript" src="${ctx}/resources/css/pc/js/jquery-3.4.1.js"></script>
	<script src="${ctx}/resources/css/pc/js/swiper.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/css/pc/js/modernizr.custom.js"></script>
 	<script src="${ctx}/resources/css/pc/js/hideShowPassword.min.js"></script>
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