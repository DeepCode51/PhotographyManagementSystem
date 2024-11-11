<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>关于我们</title>
    <link rel="stylesheet" href="${ctx}/resources/css/pc/aboutstatic/static/css/style.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/aboutstatic/static/css/style-m.css">
    <link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
    <!-- f  -->
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/style.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/style-m.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/iconfont.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/swiper-3.4.2.min.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/animate.css">
    <script type="text/javascript" src="${ctx}/resources/css/pc/js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
    		$.ajax({
    			type: "POST",
 				url:"${ctx}/studioinfor/getAllStudioinfor",
 				data:{page:1,limit:1},
 				dataType: "json",
 				success:function(data){
 					var html='';
 					var html2='';
 					var html3='';
 					$.each(data.data, function(data){
 						html += this.studIntroduction;
 						html2=this.aboutUs;
 						html3=this.members;
 					})
 					$(".about1").html(html);
 					$(".about2").html(html2);
 					$(".about3").html(html3);
 				}
    		}) 
    </script>
</head>
<body>
<div class="content">
       <!-- 1.头部开始 -->
       <div class="header">
        <div class="header-c PC">
            <a href="${ctx}/pc/index" class="logo">
                    <img src="${ctx}/resources/css/pc/img/jiesenlogo.jpg" alt="" class="logo1" />
            </a>
            <ul class="nav">
                    <li class="nav-item ">
                        <a href="${ctx}/pc/index">首页</a>
                    </li>
                    <li class="nav-item">
                        <a href="${ctx}/pc/sampleAppretion">样片欣赏</a>
                    </li>
                    <li class="nav-item active">
                         <a href="${ctx}/pc/getAboutas">关于我们</a>
                        <span class="line"></span>
                    </li>
                     <li class="nav-item">
                        <a href="${ctx }/pc/guideselection">选片指南</a>
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
    <!--  内容部分 -->
    <div class="main" style="margin-top: 80px;">
    		<div class="center" style="width: 1200px;margin: 0 auto;min-height: 1000px;">
    			<div class="about1"></div>
    			<div class="about3"></div>
    			<div class="about2" style="margin: 50px 0;"></div>
    		</div>
<!--     <div class="link">
            </div>
            <div class="main-c">
           	</div> -->         
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
    <script src="${ctx}/resources/css/pc/js/swiper.min.js"></script>
   <!-- <script src="${ctx}/resources/css/pc/js/main.js"></script> --> 
    <script type="text/javascript">
	function callBack(){//回调样式
		 //样片
		// var nameArr=["新增","历史","可出售"]
		 if(window.innerWidth<768){
		  new Swiper('.swiper-contai', {
		    slidesPerView:1.03,
		        pagination: {
		            el: '.swiper-pagination',
		            clickable: true,
		        //    renderBullet: function (index, className) {
		         //     return '<span class="' + className + '">' +nameArr[index] +'</span>';
		        //    },
		        },
		     } );
		 }else{
		  new Swiper('.swiper-contai', {
		    slidesPerView: 4,// 一行显示slider的个数
		    slidesPerGroup: 4,// 定义slides的数量多少为一组
		    spaceBetween: 40,
		    //   freeMode: true,
		    pagination: {
		        el: '.swiper-pagination',
		        clickable: true,
		     //   renderBullet: function (index, className) {
		       //   return '<span class="' + className + '">' +nameArr[index] +'</span>';
		     //   },
		    },
		 } );
		 };
	}
</script>
</body>
</html>