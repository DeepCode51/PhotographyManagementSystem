<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>首页</title>
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/reset.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/style.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/style-m.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/iconfont.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/swiper-3.4.2.min.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/animate.css">
    <link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
 	<script type="text/javascript" src="${ctx}/resources/css/pc/js/jquery-3.4.1.js"></script>
 	<script type="text/javascript">
 		$(function(){
 			$.ajax({
 				type: "POST",
 				url:"${ctx}/type/getPcType",
 				dataType: "json",
 				async: false,//同步请求
 				success:function(data){
 					var html ='';//类别可出售
 					var html2=  '<div class="wedding" >'+  //新增
						'<div class="title">'+
						'<h3>当季新品</h3>'+
						'<span class="eng">Sample</span>'+
						'</div>'+
						'<div class="swiper-contai">'+
						'<div class="swiper-wrapper">';
					var	html3=  '<div class="wedding" >'+  //历史
						'<div class="title">'+
						'<h3>历史样片</h3>'+
						'<span class="eng">Sample</span>'+
						'</div>'+
						'<div class="swiper-contai">'+
						'<div class="swiper-wrapper">';	
 					$.each(data.data,function(comment){ //循环取出类别信息
 						var typeId = this.typeId;
	 						html+=  '<div class="wedding" >'+
							'<div class="title">'+
							'<h3>'+this.typeName+'</h3>'+
							'<input type="hidden" id="typeId" value='+'/>'+
							'<span class="eng">Sample</span>'+
							'</div>'+
							'<div class="swiper-contai">'+
							'<div class="swiper-wrapper">';					
 						$.ajax({ //根据类别id查询样片信息
 			 				type: "POST",
 			 				url:"${ctx}/sample/getAllTypeSample?typeId="+typeId,
 			 				dataType: "json",
 			 				async: false,//同步请求
 			 				success:function(data){
 			 					$.each(data.data,function(val){
 			 							var time = this.sampleTime.substring(0,16);
 			 							if(this.sampleLogo==3){
 			 								//可出售
 											html+=	
												'<div class="swiper-slide">'+
												' <a href="${ctx}/pc/sampleDetails?sampleId='+this.sampleId+'">'+
											' <img src="${ctx}/file/downloadShowFile?path='+this.sampleImage+'" style="height:360px;width:270px;"/>'+
													'<div class="mask">'+
													'<h3>'+(this.sampleName.substring(0,10))+'</h3>'+
													'<p>价格:￥'+this.samplePrice+'</p>'+
				                                   	'<p>上传时间:'+time+'</p>'+
				                                     '<p>'+((this.sampleLogo==1)?'新增样片':(this.sampleLogo==2)?'历史样片':'可出售样片')+'</p>'+
													'</div>'+
													'</a>'+
													'</div>';
 			 							}else if(this.sampleLogo==2){
 			 								//历史
 											html3+=	
												'<div class="swiper-slide">'+
												' <a href="${ctx}/pc/sampleDetails?sampleId='+this.sampleId+'">'+
											' <img src="${ctx}/file/downloadShowFile?path='+this.sampleImage+'" style="height:360px;width:270px;"/>'+
													'<div class="mask">'+
													'<h3>'+(this.sampleName.substring(0,10))+'</h3>'+
													'<p>价格:￥'+this.samplePrice+'</p>'+
				                                   	'<p>上传时间:'+time+'</p>'+
				                                     '<p>'+((this.sampleLogo==1)?'新增样片':(this.sampleLogo==2)?'历史样片':'可出售样片')+'</p>'+
													'</div>'+
													'</a>'+
													'</div>';
 			 							}else if(this.sampleLogo==1){
 											html2+=	
												'<div class="swiper-slide">'+
												' <a href="${ctx}/pc/sampleDetails?sampleId='+this.sampleId+'">'+
											' <img src="${ctx}/file/downloadShowFile?path='+this.sampleImage+'" style="height:360px;width:270px;"/>'+
													'<div class="mask">'+
													'<h3>'+(this.sampleName.substring(0,10))+'</h3>'+
													'<p>价格:￥'+this.samplePrice+'</p>'+
				                                   	'<p>上传时间:'+time+'</p>'+
				                                     '<p>'+((this.sampleLogo==1)?'新增样片':(this.sampleLogo==2)?'历史样片':'可出售样片')+'</p>'+
													'</div>'+
													'</a>'+
													'</div>';
 			 							}
									time='';//清楚之前的时间
 			 					})
 			 				}
 						})
 						html+='</div>'+'</div>'+'<div class="more"><a href="${ctx}/pc/sampleAppretion">MORE</a></div>'+'</div>';

 			 					})
 			 			html2+='</div>'+'</div>'+'<div class="more"><a href="${ctx}/pc/sampleAppretion">MORE</a></div>'+'</div>';
 						html3+='</div>'+'</div>'+'<div class="more"><a href="${ctx}/pc/sampleAppretion">MORE</a></div>'+'</div>';
 			 			var  html4 = html+html2+html3;
 			 			$('.main-c').html(html4);
 						
 			 			}
 			 });
 			//查询轮播图
			$.ajax({
 				type: "POST",
 				url:"${ctx}/rotation/getPcRotation",
 				dataType: "json",
 				success:function(data){
 					var html='';
 					$.each(data.data,function(val){
 						
 						html+= ' <div class="swiper-slide">'+
 									' <a href="${ctx}/pc/sampleDetails?sampleId='+this.sampleId+'"><img style="height:100% "src="${ctx}/file/downloadShowFile?path='+this.rotationImage+'"/>'+
 								'</a></div>';
 					})
 					$('#rotation').html(html);
 					callBack();//再次调用js
 				}
 				
 			});
			
 		})
 	</script>
</head>
<body>
    <div class="content">
        <!-- 1.头部开始 -->
        <div class="header">
            <div class="header-c PC">
                <a href="${ctx}/pc/index" class="logo" >
                    <img src="${ctx}/resources/css/pc/img/jiesenlogo.jpg" alt="" class="logo1" />
                </a>
                <ul class="nav">
                    <li class="nav-item active">
                        <a href="${ctx}/pc/index">首页</a>
                    </li>
                    <li class="nav-item  ">
                        <a href="${ctx}/pc/sampleAppretion">样片欣赏</a>
                    </li>
                    <li class="nav-item">
                        <a href="${ctx}/pc/getAboutas">关于我们</a>
                        <span class="line"></span>
                    </li>
                    <li class="nav-item">
                         <a href="${ctx}/pc/guideselection">选片指南</a>
                        <span class="line"></span>
                    </li>
                    <li class="nav-item">
                        <a href="${ctx }/pc/toPersonalCenter">个人中心</a>
                    </li>
                </ul>
                    <div class="nav-login">
                        <!-- <a href="./pages/about/index.html">注册</a> -->
                       	<c:choose>
                       		<c:when test="${CUSTOMER==null }">
                       			<a href="${ctx }/pc/toLogin" style="color:#666;">登录</a>
                       		</c:when>
                       		<c:otherwise>
                       			欢迎！<a  style="font-weight:bold;text-decoration:none;color:#666;" href="${ctx }/pc/toPersonalCenter">${CUSTOMER.loginName }</a>
                       		</c:otherwise>
                       	</c:choose>
                    </div>
            </div>        
        </div>
        </div>
        <div class="containner">
            <!-- 2.banner -->
            <div class="banner">
                <div class="swiper-container" style="height: 600px;">
                    <div class="swiper-wrapper" id="rotation">

                    </div>
                    <!-- Add Pagination -->
                    <div class="swiper-pagination"></div>
                    <!-- Add Arrows -->
                    <div class="swiper-button-next"></div>
                    <div class="swiper-button-prev"></div>
                </div>
            </div>
        </div>
            <!-- 3.内容板块 -->
            <div class="main">

            <div class="main-c">

            </div>

       </div>
            <!-- 4.底部 -->
            <div class="footer">
            <div class="footer-c">
                <div class="left">
                    <p>网站备案号：豫ICP备*********号</p>
                    <span>增值电信业务经营许可证号：豫************</span>
                </div>
                <div class="right">
                    <p>
                        © 2020 杰森摄影
                    </p>
                    <a href="#">
                        联系我们
                    </a>
                    <a href="#">
                        加入我们
                    </a>
                </div>
            </div>

            </div>
            <!-- 5.广告跟随 -->
            <div class="toup" id="advance">
            <a href="#" class="animated bounceIn"></a>
            </div>

        
      
   	 
	
    <script src="${ctx}/resources/css/pc/js/swiper.min.js"></script>
 	<script src="${ctx}/resources/css/pc/js/main.js"></script>
<script type="text/javascript">
	function callBack(){//回调样式
		//轮播
		var swiperbanner = new Swiper('.swiper-container', {
		    loop: true,// 循环模式选项
		    autoplay: true,//可选选项，自动滑动
		    autoplayDisableOnInteraction:false,//自动播放时若用户与对象有交互行为时，自动播放会停止
		    effect: 'fade',//可设置为"fade"（淡入）"cube"（方块）"coverflow"（3d流）"flip"（3d翻转）
		    pagination: { //分页，下方的点
		        el: '.swiper-pagination',
		        clickable: true,
		        renderBullet: function (index, className) {
		          return '<span class="' + className + '">' + '</span>';
		        },
		    },
		    navigation: {
		        nextEl: '.swiper-button-next',
		        prevEl: '.swiper-button-prev',
		      } 
		    ,onAutoplayStop: function(swiperbanner){
		        //事件发生时你的代码
		        $('.swiper-container').mouseover()
		    }
		 });
		swiperbanner.el.onmouseover = function(){ //鼠标放上暂停轮播
			swiperbanner.autoplay.stop();
		    }
		swiperbanner.el.onmouseleave = function(){
			swiperbanner.autoplay.start();
		    }
		 //样片
		// var nameArr=["新增","历史","可出售"]
		 if(window.innerWidth<768){
		  new Swiper('.swiper-contai', {
		    slidesPerView:1.03,
		     } );
		 }else{
		  new Swiper('.swiper-contai', {
		    slidesPerView: 4,// 一行显示slider的个数
		    slidesPerGroup: 4,// 定义slides的数量多少为一组
		    spaceBetween: 40,
		    freeMode: true,
		 } );
		 };
		// 广告高随以及头部变化
		var header=document.querySelector(".header")
		   // 广告当前距离页面顶部的距离 = 滚动条的高度 + 初始广告距离页面顶部的距离
		   var originY = advance.offsetTop;

		   window.onscroll = function() {
		     // advance.style.top = originY + sct() + "px";
		     move(advance, originY + sct());
		     //PC端 页面滚动头部导航栏高度变化
		     if(sct()>60){
		      header.classList.add("active")
		     }else{
		      header.classList.remove("active")
		     }
		   };

		   function move(ele, t) {
		     t = Math.floor(t);
		     if (ele.timer) clearInterval(ele.timer);
		     ele.timer = setInterval(function() {
		       // 起点
		       var start = ele.offsetTop;
		       // 步长
		       var step = (t - start) / 10;
		       if (Math.abs(step) < 1) {
		         step = step > 0 ? 1 : Math.floor(step);
		       }
		       // 运动
		       ele.style.top = start + step + "px";
		      //  console.log(start, step, t);
		       if (start + step === t) {
		        //  console.log("stop ...");
		         clearInterval(ele.timer);
		       }
		     }, 17);
		   }

		   function sct() {
		     return (
		       document.documentElement.scrollTop ||
		       window.pageYOffset ||
		       document.body.scrollTop
		     );
		   }

	}
</script>
</body>
</html>