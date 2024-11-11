<%@page import="org.springframework.ui.Model"%>
<%@page import="cn.hutool.db.Session"%>
<%@page import="org.springframework.web.bind.annotation.ModelAttribute"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>样片欣赏</title>
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/reset.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/static/css/style.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/swiper-3.4.2.min.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/iconfont.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/static/css/style-m.css">
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/animate.css">
	<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${ctx}/resources/css/pc/static/css/sampleDetails.css" />
</head>
<script src="${ctx}/resources/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/resources/css/pc/js/jquery-1.7.1.js"></script>
<script type="text/javascript">
//先初始化加载首页，十二条数据
showRecord(1,12,0,0);

getPage();     // 分页操作

//加载总页数
var total;    //总条数
function showRecord(page,limit,setypeId,sesampleLogo){
	//回到页面顶部
	$(window).scrollTop(0); 
	
		//查询出样片类别
		$.ajax({
				type: "POST",
				url:"${ctx}/type/getPcType",
				dataType: "json",
				success:function(data){
					var html ='<option  value="0" >经典推荐</option>';
					$.each(data.data,function(comment){ //循环取出类别信息
						html+=''+
						'<option  value='+this.typeId+'>'+this.typeName+'</option>'
					})
		        	$('#select_box').html(html);
					//查询出样片标识
					$.ajax({
						type: "POST",
						url:"${ctx}/sample/getPcSampleLogo",
						dataType: "json",
						success:function(data){
							var htmls ='<option  value="0" >默认全部</option>';
							for (var i = 0; i < data.length; i++) {
								htmls+='<option  value='+data[i]+'>'
								htmls+=(data[i]=='1'?'新增样片':data[i]=='2'?'暂不出售':'正在热售')
								htmls+='</option>'
							}
				        	$('#select_box1').html(htmls); 
							$("#select_box").find("option[value="+setypeId+"]").attr("selected",true);
				        	$("#select_box1").find("option[value="+sesampleLogo+"]").attr("selected",true);
						}
					});
					//查询出样片
					$.ajax({
			    		type: "POST",
						url:"${ctx}/sample/getPcSample",
						dataType: "json",
						async:false,
						data:{page:page,limit:limit,typeId:setypeId,sampleLogo:sesampleLogo},
						success:function(date){
				        	var html = "";
				        	total = date.count;
				        	$.each(date.data,function(comment){
								html+=''+
								' <div class="col_1_of_projects span_1_of_projects">'+
								  ' <div class="view view-first">'+
								  '   <img  src="${ctx}/file/downloadShowFile?path='+this.sampleImage+'" alt="" />'+
				                   '<div class="mask">'+
				                     '<h2>'+this.sampleName+'</h2>'+
				                     '<p>'+this.sampleStock+'.</p>'+
				                     ' <a class="popup-with-zoom-anim" href="${ctx}/pc/sampleDetails?sampleId='+this.sampleId+'"> <div class="info">Read More</div></a>'+
				                   ' </div>'+
				                ' </div> '+
								 '</div>'
			        	});
				        $('.content-top').html(html);
			        },
				});
			}
		});		
	};
function getPage(){
	//layui渲染
	layui.use(['jquery','form','laypage'], function(){
		var laypage = layui.laypage,$ = layui.$;
		  var form = layui.form;
		  var layer = layui.layer;
		  laypage.render({
		       elem: $("#pages")
		       //注意，这里的 elem 指向存放分页的容器，值可以是容器ID、DOM对象。
		       //例1. elem: 'idName' 注意：如果这么写，这里不能加 # 号
			   //例2. elem: document.getElementById('idName')
			   //例3. elem: $("#idName")
		       ,count: total //数据总数，从服务端得到
		      , limit: 12                      //默认每页显示条数
		       , limits: [12, 24, 48]			//可选每页显示条数
		      //, curr: 1                        //起始页
		      //, groups: 5                      //连续页码个数
		       , prev: '上一页'                 //上一页文本
		       , netx: '下一页'                 //下一页文本
		       , first: 1                      //首页文本
		      //  , last: 100                     //尾页文本
		       , layout: ['prev', 'page', 'next','limit','refresh','skip']
		       //跳转页码时调用
		       , jump: function (obj, first) { //obj为当前页的属性和方法，第一次加载first为true
		           //非首次加载 do something
		           if (!first) {
		           	   //清空以前加载的数据
		               $('.content-top').empty();
					   var options1 = $("#select_box1 option:selected"); //获取选中的项
					   var sesampleLogo = options1.val();
		               var options = $("#select_box option:selected"); //获取选中的项
					   var setypeId = options.val();

		               //调用加载函数加载数据
		               showRecord(obj.curr,obj.limit,setypeId,sesampleLogo);
		           }
		       }
	 	});
	});
}
	//渲染结束
</script>
<script>
 </script>
<body>
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
                    <li class="nav-item  active">
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
                       			<a href="${ctx }/pc/toLogin">登录</a>
                       		</c:when>
                       		<c:otherwise>
                       			欢迎！<a  style="font-weight:bold;text-decoration:none;color:#666;" href="${ctx }/pc/toPersonalCenter">${CUSTOMER.loginName }</a>
                       		</c:otherwise>
                       	</c:choose>
                    </div>
            </div>         
        </div>
    <div class="containner"> </div>
      <!-- 2.内容部分 -->
      <div id="mains" class="mains">
      		<div class="wrap">  
			     <div class="m_3"><span class="left_line"></span>
			     	<div class="search-wrap cf">
					<p class="search-ttl">请选择</p>
					<label for="select-area" class="select-group">
						<select id="select_box"></select>
					</label>
					<label for="select-area" class="select-group">
						<select id="select_box1"></select>
					</label>
					</div>

			     </div>
			     <div class="content-top">

			 	</div>
	        </div>
      </div>
      <!-- 分页数据 -->
      <div style="text-align: center; margin: auto;" id="pages"></div>
      <!-- 4.底部 -->
      <div id="footer" class="footer">
          <div class="footer-c">
              <div class="left">
                  <p>网站备案号：豫ICP备110121210号</p>
                  <span>增值电信业务经营许可证号：豫B2-20100033</span>
              </div>
              <div class="right">
                  <p>
                      © 2018 VERONICA
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
     
    </div>
  </body>
    <script src="${ctx}/resources/css/pc/js/swiper.min.js"></script>
    <script src="${ctx}/resources/css/pc/static/js/main.js"></script>
    <script type="text/javascript">
	 $("#select_box").change(function(){
		 var page=1; //设置首页页码
		 var limit=12;  //设置一页显示的条数
	  	 var setypeId =  $("#select_box").val();
	  	 var sesampleLogo = $("#select_box1").val();
			$.ajax({
				type: "POST",
				url:"${ctx}/type/getPcType",
				dataType: "json",
				success:function(data){
					var html ='<option id="0" value="0" selected="true">经典推荐</option>';
					$.each(data.data,function(comment){ //循环取出类别信息
						html+=''+
						'<option id='+this.typeId+' value='+this.typeId+'>'+this.typeName+'</option>'
					})
		        	$('#select_box').html(html); 

					//查询出样片标识
					$.ajax({
						type: "POST",
						url:"${ctx}/sample/getPcSampleLogo",
						dataType: "json",
						success:function(data){
							var html ='<option id="0" value="0" selected="true">默认全部</option>';
							for (var i = 0; i < data.length; i++) {
								html+='<option id='+data[i]+' value='+data[i]+'>'
								html+=(data[i]=='1'?'新增样片':data[i]=='2'?'暂不出售':'正在热售')
								html+='</option>'
							}
				        	$('#select_box1').html(html); 
							$("#select_box").find("option[value="+setypeId+"]").attr("selected",true);
				        	$("#select_box1").find("option[value="+sesampleLogo+"]").attr("selected",true);
						}
					});
					$.ajax({
			    		type: "POST",
						url:"${ctx}/sample/getPcSample",
						dataType: "json",
						data:{page:page,limit:limit,typeId:setypeId,sampleLogo:sesampleLogo},
						success:function(date){
				        	var html = "";
				        	total = date.count;
				        	getPage();
				        	$.each(date.data,function(comment){
								html+=''+
								' <div class="col_1_of_projects span_1_of_projects">'+
								  ' <div class="view view-first">'+
								  '   <img  src="${ctx}/file/downloadShowFile?path='+this.sampleImage+'" alt="" />'+
				                   '<div class="mask">'+
				                     '<h2>'+this.sampleName+'</h2>'+
				                     '<p>'+this.sampleStock+'.</p>'+
				                     ' <a class="popup-with-zoom-anim" href="${ctx}/pc/sampleDetails?sampleId='+this.sampleId+'"> <div class="info">Read More</div></a>'+
				                   ' </div>'+
				                ' </div> '+
								 '</div>'
			        	});
				        $('.content-top').html(html);

			        },
				});
			}
		});	
 	});
    </script>
<script type="text/javascript">
	 $("#select_box1").change(function(){
		 var page=1; //设置首页页码
		 var limit=12;  //设置一页显示的条数
	  	 var sesampleLogo = $("#select_box1").val();
	  	 var setypeId = $("#select_box").val();
			$.ajax({
				type: "POST",
				url:"${ctx}/type/getPcType",
				dataType: "json",
				success:function(data){
					var html ='<option id="0" value="0" selected="true">经典推荐</option>';
					$.each(data.data,function(comment){ //循环取出类别信息
						html+=''+
						'<option id='+this.typeId+' value='+this.typeId+'>'+this.typeName+'</option>'
					})
		        	$('#select_box').html(html); 

					//查询出样片标识
					$.ajax({
						type: "POST",
						url:"${ctx}/sample/getPcSampleLogo",
						dataType: "json",
						success:function(data){
							var html = '<option id="0" value="0" selected="true">默认全部</option>';
							for (var i = 0; i < data.length; i++) {
								html+='<option id='+data[i]+' value='+data[i]+'>'
								html+=(data[i]=='1'?'新增样片':data[i]=='2'?'暂不出售':'正在热售')
								html+='</option>'
							}
				        	$('#select_box1').html(html); 
							$("#select_box").find("option[value="+setypeId+"]").attr("selected",true);
				        	$("#select_box1").find("option[value="+sesampleLogo+"]").attr("selected",true);
						}
					});
					$.ajax({
			    		type: "POST",
						url:"${ctx}/sample/getPcSample",
						dataType: "json",
						data:{page:page,limit:limit,typeId:setypeId,sampleLogo:sesampleLogo},
						success:function(date){
				        	var html = "";
				        	total = date.count;
				        	getPage();
				        	$.each(date.data,function(comment){
								html+=''+
								' <div class="col_1_of_projects span_1_of_projects">'+
								  ' <div class="view view-first">'+
								  '   <img  src="${ctx}/file/downloadShowFile?path='+this.sampleImage+'" alt="" />'+
				                   '<div class="mask">'+
				                     '<h2>'+this.sampleName+'</h2>'+
				                     '<p>'+this.sampleStock+'.</p>'+
				                     ' <a class="popup-with-zoom-anim" href="${ctx}/pc/sampleDetails?sampleId='+this.sampleId+'"> <div class="info">Read More</div></a>'+
				                   ' </div>'+
				                ' </div> '+
								 '</div>'
			        	});
				        $('.content-top').html(html);

			        },
				});
			}
		});	
 	});
    </script>
</html>