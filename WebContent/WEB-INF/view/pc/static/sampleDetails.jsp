<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>样片详情</title>
<link rel="stylesheet" href="${ctx}/resources/css/pc/css/reset.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/style.css" />
        <link rel="stylesheet" href="${ctx}/resources/css/pc/static/css/style.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/style-m.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/iconfont.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/swiper-3.4.2.min.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/pc/css/animate.css">
    <link rel="stylesheet" href="${ctx}/resources/css/pc/static/css/sampleDetails.css" />
 	<script type="text/javascript" src="${ctx}/resources/css/pc/js/jquery.min.js"></script>
 	<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
 	<script src="${ctx}/resources/layui/layui.js"></script>
</head>
<script type="text/javascript" async="async">
//加载总页数
var total;    //总条数
	//获得url里的Id值
	var getParam = function(name){
	    var search = document.location.search;
	    var pattern = new RegExp("[?&]"+name+"\=([^&]+)", "g");
	    var matcher = pattern.exec(search);
	    var items = null;
	    if(null != matcher){
	            try{
	                    items = decodeURIComponent(decodeURIComponent(matcher[1]));
	            }catch(e){
	                    try{
	                            items = decodeURIComponent(matcher[1]);
	                    }catch(e){
	                            items = matcher[1];
	                    }
	            }
	    }
	    return items;
	}
	var sampleId = getParam("sampleId");
	function showRecord(page,limit,sampleId){
		//回到页面顶部
		$(window).scrollTop(0); 
	//得到评论
	$.ajaxSettings.async = false;
	$.get("${ctx}/comment/getPcCommentBysampleId",
		        {
				 sampleId:sampleId,
				 page:page,
				 limit:limit
		        },
		        function (date) {
		        	total = date.count;
		        	var html ='';
		        	if(date.data.length<1){
		        		html+='<i class="down">暂无评论信息 </i>'
		        	}
		        	else{
				        	//加载后台返回的List集合数据
				            for (var i = 0; i < date.data.length; i++) {
				            	html+=''+
								' <dl>'+
							   ' <dt class="__r_c_" pan="M14_Movie_Overview_LongReview_1"><span>TOP</span><strong>'+(i+1)+'</strong></dt>'+
							    '<dd class="__r_c_" pan="M14_Movie_Overview_LongReview_1">'+
							       ' <p class="mt6"><h3><a target="_blank" href="#">'+date.data[i].customer.loginName+'</a></h3></p>'+   
							   ' </dd>'+
							   ' <dd class="clearfix mt20 __r_c_">'+
							       ' <div class="comboxcont clearfix">'+
							       ' <p>内容:'+date.data[i].commContent+'</p>'+
							        '</div>'+
							    '</dd>'+
							   ' <dd class="clearfix comboxuser">'+
							        '<div class="pic_58 __r_c_" pan="M14_Movie_Overview_LongReview_1_UserContent">'+
							            '<p class="px14 pt6"><a target="_blank" href="#"></a></p>'+
							            '<p class="mt6"><a target="_blank" href="#">'+date.data[i].commTime+'</a></p>'+
							       ' </div>'+
							   ' </dd>'+
							'</dl>'
				          }
		        	}
	
		            $('.longcombox').html(html);
		        },
		        "json"
		    );
		$.ajaxSettings.async = true;
		 }
</script>
<script type="text/javascript">
$(function(){
	//获得url里的Id值
	var getParam = function(name){
	    var search = document.location.search;
	    var pattern = new RegExp("[?&]"+name+"\=([^&]+)", "g");
	    var matcher = pattern.exec(search);
	    var items = null;
	    if(null != matcher){
	            try{
	                    items = decodeURIComponent(decodeURIComponent(matcher[1]));
	            }catch(e){
	                    try{
	                            items = decodeURIComponent(matcher[1]);
	                    }catch(e){
	                            items = matcher[1];
	                    }
	            }
	    }
	    return items;
	}
	var sampleId = getParam("sampleId");
	//先初始化加载首页，五条数据
	showRecord(1,5,sampleId);

	getPage();     // 分页操作
	//根据样片编号查询样片信息
	$.ajax({
		type: "POST",
		url:"${ctx}/sample/getSampleId?sampleId="+sampleId,
		dataType: "json",
		success:function(data){
			var text ='';
			var Logo = data.sampleLogo == 1? "新增样片" : data.sampleLogo==2 ? "历史样片":"正在热售";
			var time = data.sampleTime.length > 10 ? data.sampleTime.substring(0,10) + "     " : title;
				text+='<form id="layui-form" class="layui-form" action="">'+
				'<div id="content-c"  class="top content-c"> '+
				'   <img class="sampleimg" src="${ctx}/file/downloadShowFile?path='+data.sampleImage+'" alt="" />'+
			      '<div class="detail-col">'+
	                '<a class="title clearfix   shop_link" href="#" target="_blank">'+
	                   ' <span class="tmall"></span>'+
	                   ' <span class="title">'+data.sampleName+'</span>'+
	                '</a>'+
	                '<div class="active-type" style="display: block;">'+
	                   ' <div class="act-bg">'+
	                    ' <span class="des">简介    :       '+data.sampleStock+'</span>'+
	       	            ' <input type="hidden" id="sampleId" name="sampleId" value='+data.sampleId+'>'+
		   	             '<input type="hidden" name="customerId" value="${CUSTOMER.customerId}">'+
		   	             '<input type="hidden" name="price" value='+data.samplePrice+'>'+
		   	             '<input type="hidden" name="subState" value="1">'+
	                '<div class="time-num"><span class="time">上新时间：'+time+'</span>'+
	                 '   <span class="num">'+
	                    '    累计销量                            ：'+
	                      '  <i style="color:#FF2E54 ">'+data.orderCount+'</i> 次                        </span>'+
	             '   </div>'+
               ' <div class="coupon"><span class="num">累计收藏           ：<i style="color:#FF2E54 ">'+data.collectionCount+'</i> 次   </span><span class="num">在线预约           ：<i style="color:#FF2E54 ">'+data.subscribeCount+'</i> 次   </span>  '+
               '</div>'+
                '<div class="goods-label">'+
                   '  样片标签：'+
                 '    <span class="label">'+Logo+'</span> '+                       
                    
                '</div>'+
                   '    <div class="price">¥ <i>'+data.samplePrice+'</i></div>'+
              '  <div class="buy-share">'
	            if(data.sampleLogo == 2 ||data.sampleLogo == 1){
	            	text+= '<div class="mod-title" id="" style="text-align:center;">'+
				     		'<a class="buy shop_link  " target="_blank" href="#" onclick="return false;">暂不出售</a>'+
					  		'</div>'
	            }else{
	            	text+= '<div class="mod-title" id="mod-title" style="text-align:center;">'+
		     		'<a class="buy shop_link  " target="_blank" href="${ctx }/pc/toLogin">登录进行操作</a>'+
			  		'</div>'
	            }
	        	text+='</div> '+
			'</div>'+
			'</div>'+
			'</div>'+
			'</form>'
			$('#main').html(text);
		}
	});
	//根据样片编号查询样片下图片信息
	$.ajax({
		type: "POST",
		url:"${ctx}/image/getPcAllImage?sampleId="+sampleId,
		dataType: "json",
		success:function(data){
			var html ='';
			$.each(data.data,function(comment){
				html+=''+
				' <div class="col_1_of_projects span_1_of_projects">'+
				  ' <div class="view view-first">'+
                 '<img src="${ctx}/file/downloadShowFile?path='+this.imageAddress+'" alt=""/>'+
                ' </div> '+
				'</div>'
			});
			$('.content-top').html(html);
			//业务
			var customerId = "${CUSTOMER.customerId}";
			if(customerId != null && customerId != ''){
				$.ajax({
					type: "POST",
					url:"${ctx}/intention/getIntentionCustomerIdAndSampleId",
					dataType: "json",
					data:{sampleId:sampleId,customerId:customerId},
					success:function(data){
						var html = "";
						if(data.length > 0){				
							//已经添加过意向
							html+='<form id="layui-form" class="layui-form" action="">'+
								'<a class="buy shop_link" onclick="getintention1('+sampleId+')">取消意向</a>'+
					   	    '<div class="layui-input-block share" style="margin-left: 0 ; margin-right: 110px ; float:left; text-align:left;">'+
					  		' <input type="checkbox" id="switchTest" value='+sampleId+' name="aaa" lay-filter="switchTest"  lay-skin="switch" lay-text="取消收藏|添加收藏">'+
					   		' </div>'+
					   	    '<div class="layui-input-block share fr">'+
					  		' <input type="checkbox" id="switchTest1" value='+sampleId+' name="ccc" lay-filter="switchTest1"  lay-skin="switch" lay-text="取消预约|添加预约">'+
					   		' </div>'+
					   		'</form>'
						}else{
							//未添加过意向
							html+='<a class="buy shop_link" onclick="getintention('+sampleId+')">加入意向</a>'
						}
						$('#mod-title').html(html);
						//如果form中的radio等元素没有渲染，则在加载页面添加以下代码亦可解决：
						layui.use('form', function() {
						      var form = layui.form; 
							  	var customerId = "${CUSTOMER.customerId}";
							  	//控制选中收藏
							      $.ajax({
							  		type: "POST",
							  		url:"${ctx}/collection/getCollectionCustomerIdAndSampleId",
							  		dataType: "json",
							  		data:{sampleId:sampleId,customerId:customerId},
							  		success:function(data){
							  			if(data.length > 0){				
							  				//已经添加过收藏
							  				//设置选中 
							  			     $('#switchTest').attr('checked',true); 
							  			  	 form.render();
							  			}
							  		}
							    });
								  //控制选中预约
							      $.ajax({
							  		type: "POST",
							  		url:"${ctx}/subscribe/getSubscribeByCustomerIdAndSampleId",
							  		dataType: "json",
							  		data:{sampleId:sampleId,customerId:customerId},
							  		success:function(data){
							  			if(data.length > 0){				
							  				//已经添加过预约
							  				//设置选中
							  			     $('#switchTest1').attr('checked',true); 
							  			  	 form.render();
							  			}
							  		}
							    });
							      form.render();
						   });
					}
				});
			};
		}
	});

});
</script>
<script type="text/javascript">
function getPage(){
	//获得url里的Id值
	var getParam = function(name){
	    var search = document.location.search;
	    var pattern = new RegExp("[?&]"+name+"\=([^&]+)", "g");
	    var matcher = pattern.exec(search);
	    var items = null;
	    if(null != matcher){
	            try{
	                    items = decodeURIComponent(decodeURIComponent(matcher[1]));
	            }catch(e){
	                    try{
	                            items = decodeURIComponent(matcher[1]);
	                    }catch(e){
	                            items = matcher[1];
	                    }
	            }
	    }
	    return items;
	}
	var sampleId = getParam("sampleId");
	//layui渲染
	layui.use(['jquery','form','laypage'], function(){
		var laypage = layui.laypage,$ = layui.$;
		  var form = layui.form;
		  var layer = layui.layer;
		  form.on('switch(switchTest)', function(data){
			    this.checked ? getcollection($(this).val()) : getcollection1($(this).val());
			  });
			//监听预约开关
			  form.on('switch(switchTest1)', function(data){
			    this.checked ? getsubscribe($(this).val()) : getsubscribe1($(this).val());
			  });
		  
		  laypage.render({
		       elem: $("#pages")
		       //注意，这里的 elem 指向存放分页的容器，值可以是容器ID、DOM对象。
		       //例1. elem: 'idName' 注意：如果这么写，这里不能加 # 号
			   //例2. elem: document.getElementById('idName')
			   //例3. elem: $("#idName")
		       ,count: total //数据总数，从服务端得到
		      , limit: 5                      //默认每页显示条数
		       , limits: [5, 10, 20]			//可选每页显示条数
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
		               $('.longcombox').empty();
		               //调用加载函数加载数据
		               showRecord(obj.curr,obj.limit,sampleId);
		           }
		       }
	 	});
	});
}
	// 渲染结束
</script>
<!-- 业务操作 -->
<script>
//添加意向
function getintention(sampleId){
	var customerId = "${CUSTOMER.customerId}";
	if(customerId=="null"||customerId==""){
			 alert("您还未登录，请先登录！");
		}else{
			$.ajax({
				type: "POST",
				url:"${ctx }/intention/addPcIntention",
				dataType: "json",
				data:{sampleId:sampleId,customerId:customerId},
				success:function(data){
					if(data.msg=='添加成功'){
					//	alert("添加意向成功");
						location.reload(true);
					}else{
						alert("添加意向失败");
					}
				}
		});
	}
 };
//添加收藏
 function getcollection(sampleId){
 	var customerId = "${CUSTOMER.customerId}";
 	if(customerId=="null"||customerId==""){
 			 alert("您还未登录，请先登录！");
 		}else{
 			$.ajax({
 				type: "POST",
 				url:"${ctx }/collection/addPcCollection",
 				dataType: "json",
 				data:{sampleId:sampleId,customerId:customerId},
 				success:function(data){
 					if(data.msg=='添加成功'){
 					//	alert("添加收藏成功");
 					}else{
 						alert("添加收藏失败");
 					}
 				}
 		});
 	}
  };
//添加预约
  function getsubscribe(sampleId){
  	var customerId = "${CUSTOMER.customerId}";
  	if(customerId=="null"||customerId==""){
  			 alert("您还未登录，请先登录！");
  		}else{
  			$.ajax({
  				type: "POST",
  				url:"${ctx }/subscribe/addPcSubscribe",
  				dataType: "json",
  				data:{sampleId:sampleId,customerId:customerId},
  				success:function(data){
  					if(data.msg=='添加成功'){
  						//alert("添加预约成功");
  					}else{
  						alert("添加预约失败");
  					}
  				}
  		});
  	}
   };
//取消意向
  function getintention1(sampleId){
  	var customerId = "${CUSTOMER.customerId}";
  	if(customerId=="null"||customerId==""){
  			 alert("您还未登录，请先登录！");
  		}else{
  			$.ajax({
  				type: "POST",
  				url:"${ctx }/intention/deletePcIntention",
  				dataType: "json",
  				data:{sampleId:sampleId,customerId:customerId},
  				success:function(data){
  					if(data.msg=='删除成功'){
  					//	alert("取消意向成功")
  						location.reload(true);
  					}else{
  						alert("取消意向失败");
  					}
  				}
  		});
  	 }
 };
//取消收藏
 function getcollection1(sampleId){
 	var customerId = "${CUSTOMER.customerId}";
 	if(customerId=="null"||customerId==""){
 			 alert("您还未登录，请先登录！");
 		}else{
 			$.ajax({
 				type: "POST",
 				url:"${ctx }/collection/deletePcCollection",
 				dataType: "json",
 				data:{sampleId:sampleId,customerId:customerId},
 				success:function(data){
 					if(data.msg=='删除成功'){
 					//	alert("取消收藏成功");
 					//	location.reload(true);
 					}else{
 						alert("取消收藏失败");
 					}
 				}
 		});
 	}
  };
//取消预约
  function getsubscribe1(sampleId){
  	var customerId = "${CUSTOMER.customerId}";
  	if(customerId=="null"||customerId==""){
  			 alert("您还未登录，请先登录！");
  		}else{
  			$.ajax({
  				type: "POST",
  				url:"${ctx }/subscribe/deletePcSubscribe",
  				dataType: "json",
  				data:{sampleId:sampleId,customerId:customerId},
  				success:function(data){
  					if(data.msg=='删除成功'){
  						//alert("取消预约成功");
  					//	location.reload(true);
  					}else{
  						alert("该预约已生成订单 无法取消。");
 						location.reload(true);
  					}
  				}
  		});
  	}
   };
</script>
<script type="text/javascript">
//获得url里的Id值
var getParam = function(name){
    var search = document.location.search;
    var pattern = new RegExp("[?&]"+name+"\=([^&]+)", "g");
    var matcher = pattern.exec(search);
    var items = null;
    if(null != matcher){
            try{
                    items = decodeURIComponent(decodeURIComponent(matcher[1]));
            }catch(e){
                    try{
                            items = decodeURIComponent(matcher[1]);
                    }catch(e){
                            items = matcher[1];
                    }
            }
    }
    return items;
}
	//发表评论
		function addComment(){
		var sampleId = getParam("sampleId");
		var commContent  = $("#tweetBox").val();
	  	var customerId = "${CUSTOMER.customerId}";
		if(customerId=="null"||customerId==""){
			 alert("您还未登录，请先登录！");
			 return false;
		 }else{
				//查询是否购买订单
			  	$.ajax({
			  		type:"get",
			  		url:"${ctx }/order/getPcOrderByCustomerId1",
			  		dataType:"json",
			  		data:{sampleId:sampleId,customerId:customerId},
			  		success:function(data){
						if(data == "1"){
				  			if(commContent == "null" || commContent == ""){
				  				alert("请输入内容.");
				  				return false;
				  			}else{
				  					$.ajax({
				  						type: "get",
				  						url:"${ctx }/comment/addPcComment",
				  						dataType: "json",
				  						data:{sampleId:sampleId,customerId:customerId,commContent:commContent},
				  						success:function(data){
				  							if(data.msg=='添加成功'){
				  								location.reload(true);
				  							}else{
				  								alert("评论失败");
				  								location.reload(true);
				  							}
				  						}
				  				});
				  	 		}
						}else{
							alert("请购买后再进行评论.");
							return false;
						}
			  		}
			  	})

		 }
	 };
</script>

<style type="text/css">
.mfp-hide {
  display: none !important; }
</style>
<body>
<p class="close-btn"><a href="javascript:history.back()"><img src="${ctx}/resources/images/close_g.png" width="38" height="60" alt="close"></a></p>
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
                        <a href="#">关于我们</a>
                        <span class="line"></span>
                    </li>
                    <li class="nav-item">
                        <a href="${ctx}/pc/guideselection">选片指南</a>
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
		</div>
		<!-- 2 -->
		<div class="containner"></div>
		<!-- 2.内容部分1 -->
      <div id="main" class="main">
      </div>
		<!-- 3 内容部分2-->
		<div class="mains">
			<div class="wrap">  
			     <div class="m_3"><span class="left_line"></span>
			     	<span class="right_line" id="right_line"></span>
			     </div>
			      <div class="content-top">

			 		</div>
	        </div>
		</div>
		<div class="mains" style="background: #127bab;">     
		<div class="collection" style=" width: 690px;  margin: 0 auto; padding-bottom: 30px;">  
			<div id="ratingDownRegion" class="editmpscore">
				<div class="per_message">
					<textarea class="c_a5" onkeypress = "return(clickButton());" name="commContent" id="tweetBox" style="background-color: rgb(255, 255, 255); background-position: -620px 0px;" placeholder="对影片的简短评论"></textarea>
				</div>
				<div class="per_btn">
				<div class="fr">
					<a class="btn_dblue __r_c_" style="cursor: pointer;" id="tweetButtonDown" onclick="addComment();">发表</a>
				</div>
				</div>
			</div>                                                                        
			<div id="reviewRegion" class="db_longcom">
			    <h4>样片评论</h4>
			    <div class="longcombox" id="longcombox"></div>			
			</div>
		</div> 
	</div>
	<!-- 分页数据 -->
     <div style="margin-top:10px; text-align: center; margin: auto;" id="pages"></div> 
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
	<script src="${ctx}/resources/css/pc/js/swiper.min.js"></script>
	<script src="${ctx}/resources/css/pc/js/main.js"></script>
<script>
//屏蔽回车输入 |  回车提交
 function clickButton()
{
    if ( event.keyCode == 13 )
    {
    	addComment();
        return false;
    }
}
</script>
</body>
</html>