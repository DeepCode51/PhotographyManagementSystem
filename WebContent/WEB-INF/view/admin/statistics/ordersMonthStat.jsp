<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>每月交易额统计</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="${ctx }/resources/css/public.css" media="all" />
</head>
<body>
<!-- 搜索条件开始 -->
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	  <legend>查询条件</legend>
	</fieldset>
	<form class="layui-form" method="post" id="searchFrm">
		<div class="layui-form-item">
			<div class="layui-inline">
			      <label class="layui-form-label">选择年份:</label>
			      <div class="layui-input-inline">
			       	       <input type="text" class="layui-input" id="year" readonly="readonly" placeholder="yyyy">
			      </div>
		     </div>
			<div class="layui-inline">
		            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
		     </div>
		 </div>
	</form>
	<!-- 搜索条件结束 -->
       <div id="container" style="height:600px;"></div>
       <script type="text/javascript" src="${ctx }/resources/echarts/js/echarts.js" charset="utf-8"></script>
       <script type="text/javascript" src="${ctx }/resources/echarts/js/jquery-3.1.1.min.js"></script>
       <script src="${ctx }/resources/layui/layui.js"></script>
      
       <script type="text/javascript">
       layui.use([ 'jquery', 'layer', 'form', 'table','laydate'  ], function() {
			var $ = layui.jquery;
			var layer = layui.layer;
			var form = layui.form;
			var table = layui.table;
			var laydate=layui.laydate;
			
			laydate.render({
				elem:'#year',
				type:'year',
				value:new Date()
			});
		    $("#doSearch").click(function(){
		    	getData();
		    });
		    
		    function getData(){
				var year=$("#year").val();
				if(year===""){
					year=new Date().getFullYear();
				}
				$.get("${ctx}/analysis/loadOrdersYearGradeStat",{year:year},function(data){
					 var dom = document.getElementById("container");
					 var myChart = echarts.init(dom);
					 var app = {};
					 option = null;
					 app.title = '坐标轴刻度与标签对齐';

					 option = {
							    title : {
							        text: '年度交易额统计',
							        x:'center'
							    },
							  	//工具箱
							    toolbox: {
							        show: true,
							        right:100,
							        feature: {
							            //下载
							            saveAsImage: {
							                show: true
							            }
							        }
							    },
								tooltip : {
							        trigger: 'axis',
							        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
							            type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
							        }
							    },
							    xAxis: {
							        type: 'category',
							        name:'单位:月',
							        data: ['一月', '二月', '三月', '四月', '五月', '六月', '七月','八月','九月','十月','十一月','十二月']
							    },
							    yAxis: {
							        type: 'value',
							        name:'单位：人民币(元)'
							    },
							    series: [{
							        data: data,
							        type: 'line'
							    }]
							};
							;
							if (option && typeof option === "object") {
							    myChart.setOption(option, true);
							}
						});
					}
					getData();
       });
      
       </script>
</body>
</html>