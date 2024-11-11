<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选片指南</title>
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/reset.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/static/css/style.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/swiper-3.4.2.min.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/iconfont.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/static/css/style-m.css">
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/animate.css">
    <link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
   	<style type="text/css">
		.ylcon{width:100%;min-width:320px;}
		.tit{height:26px;line-height:26px;padding:0px 15px;position:relative;font-size:15px;color:#aaa;border-bottom:1px solid rgba(0, 0, 0, 0.15);}
		 
		.story{border-bottom:1px dashed #cecece;padding:0 15px 3px;position:relative;}
		.story_t{font-size:1.2em;color:rgba(0,0,0,1);padding-top:5px;padding-bottom:2px;}
		.story_m{color:rgba(110,110,110,1);line-height:21px;word-break:break-all;word-wrap:break-word;overflow:hidden;font-size:1.2em;padding:2px 0;}
		.story_time{color:rgba(154,154,154,1);padding:2px 0;}
		.story_hf{background:rgb(245,245,245);font-size:1.2em;border:1px solid rgba(204,204,204,0.2);border-radius:2px;color:rgba(110,110,110,1);padding:4px;margin-bottom:5px;}
		.opbtn{position:absolute;top: 0;right: 0;}
		.gtuijian  li{
			display: block;
		    height: 2rem;
		    font-size: 0.7rem;
		    overflow: hidden;
		    white-space: nowrap;
		    text-overflow: ellipsis;
		    float: left;
		    color: #999;
		    width: 90%;
		    padding: 0 5%;
		}
		 .guide {
		    font-size: 0.8rem;
		    color: #222;
		    padding: 1rem 5% 0.5rem;
		}
		
	</style>
    <script type="text/javascript" src="${ctx}/resources/css/pc/js/jquery-3.4.1.js"></script>
    <script src="${ctx}/resources/layui/layui.js"></script>
    <script type="text/javascript">
//layui渲染
layui.use(['jquery','laypage','element', 'layer'], function(){
	var laypage = layui.laypage,$ = layui.$;
	var layer = layui.layer;
	var element = layui.element;
	var layer = layui.layer;
})
</script>
</head>
<body >
<div class="content">
       <!-- 1.头部开始 -->
       <div class="header">
            <div class="header-c PC">
                <a href="${ctx}/pc/index" class="logo" >
                    <img src="${ctx}/resources/css/pc/img/jiesenlogo.jpg" alt="" class="logo1" />
                </a>
                <ul class="nav">
                    <li class="nav-item">
                        <a href="${ctx}/pc/index">首页</a>
                    </li>
                    <li class="nav-item  ">
                        <a href="${ctx}/pc/sampleAppretion">样片欣赏</a>
                    </li>
                    <li class="nav-item">
                        <a href="${ctx}/pc/getAboutas">关于我们</a>
                        <span class="line"></span>
                    </li>
                    <li class="nav-item active">
                         <a href="${ctx}/pc/guideselection"> 选片指南</a>
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

    <!-- 展示区域 -->
    <div style="width: 1202px;margin:84px auto;background-color:#f2f2f2 ">
    	<!-- 左侧区域 -->
    	<div style="width: 240px;border: 5px solid #f2f2f2;float: left;background-color: #fff;">
    		  <h3 class="guide"><b>最新留言</b></h3>
			  <ul class="gtuijian" >
			  </ul>
    	</div>
    	<!-- 右侧侧区域 -->
    	<div style="width: 935px;float: left; border: 5px solid #f2f2f2;margin-left: 5px;background-color: #fff;">
    			<!-- 留言标题 -->
    			<div class="bt" style="margin:20px 0;">

    			</div>
    			<!--  回复信息 富文本 -->
    			<div class="huifu">
    	
    			</div>
    	</div>
    	<div style="clear: both;"></div>
    </div>
     <div class="main" style="min-height:200px;">
     	<div style="width: 1200px;height: 200px;margin: 10px auto;">
	     		<div class="layui-collapse" lay-filter="test">
		 			<div class="layui-colla-item">
					    <h2 class="layui-colla-title">留言？</h2>
					    <div class="layui-colla-content">
					      <textarea rows="" cols="" id="message" style="width: 1180px;height: 64px;resize:none;maxwidth:1180px;maxheight:64px;" placeholder="期待你的留言" onkeypress = "return(clickButton());"></textarea>
     						<div style="margin: 0 auto;text-align: center;">
     						<button id="sub" class="layui-btn"  onclick="addmessage()">留言</button>
     						</div>
					    </div>
	  				</div>
  				</div>
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
    <script src="${ctx}/resources/css/pc/js/swiper.min.js"></script>
 	<script>
 		//留言
 		function addmessage(){
 	 		var values = $("#message").val();
 	 		var customerId = "${CUSTOMER.customerId}";
 			if(customerId=="null"||customerId==""){
 				 alert("您还未登陆，请先登录！");
 			}else{
 				if(values !=null && values !=''){
 					$.ajax({
 						type: "POST",
 		 				url:"${ctx}/message/addPcMessage",
 		 				data:{customerId:customerId,messageContent:values},
 		 				dataType: "json",
 		 				success:function(data){
 		 					alert("留言成功");
 		 					window.location.reload();
 		 				},
 		 				error:function(data){
 		 					alert("留言失败");
 		 					window.location.reload();
 		 				}
 					});
 				}else{
 					alert("留言不能为空");
 				}
 			}
 		}
 	</script>
 <script>
 	//查询前十条留言
 $.ajax({
		type: "POST",
			url:"${ctx}/message/getPcMessage",
			dataType: "json",
			success: function(data){
				var i = '1';
				var html = '';
				var html2= '';
				$.each(data.data,function(a,val1){
					var name = "'"+this.customer.realName+"'";
					var content = "'"+this.messageContent+"'";
					var time = "'"+(this.messageTime).substring(0,16)+"'";
					html += '<li class="selectguidsss" id="'+(i=='1'?'zs1':'')+'" onclick="tist('+this.messageId+','+name+','+content+','+time+')"><a href="javascript:;" title="'+this.messageContent+'">'+(this.messageContent.substring(0,20))+'</a></li>';
					name = '';
					i++;
				})
				$(".gtuijian").html(html);
			}	
			});
 
function tist(messageId,name,content,time){
 		//var id = $(".selectguidsss").val();
 		 $.ajax({//根据留言id查询回复信息

	        	type: "POST",
	         	url:"${ctx}/reply/getPcReply?messageId="+messageId,
	         	dataType: "json",
	         	success:function(data){	
	         		var html3='';
	         		var html2='';
	         		if(data.data == ''){
         				html3 = '<h1 style="font-size: 1rem;font-weight: bold;">'+content+'</h1>'+
    					'<p style="color: #999;font-size: 0.6rem;line-height: 1.2rem;">'+name+'留言于'+time+'</p>';
         				html2+='暂无回复,等待工作室人员处理。';
         			}else{
	         			$.each(data.data,function(){
	         			
		         			html3 = '<h1 style="font-size: 1rem;font-weight: bold;">'+this.message.messageContent+'</h1>'+
	    					'<p style="color: #999;font-size: 0.6rem;line-height: 1.2rem;">'+name+'留言于'+this.message.messageTime+'</p>';
						     html2+=this.replyContent;
	         			

	         		})
         			}
	         		$(".bt").html(html3);	
	         		$(".huifu").html(html2);
	         	}
	        });
 	}
function clickButton()
{
    if ( event.keyCode == 13 )
    {
       // return false;
        document.getElementById("sub").click();
    }
}
function dj(){
	 document.getElementById("zs1").click();
}
window.onload=dj;
 </script>
</body>
</html>