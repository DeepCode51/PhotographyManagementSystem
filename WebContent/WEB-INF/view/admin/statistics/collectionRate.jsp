<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>杰森工作室-统计分析</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/echarts/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/echarts/js/echarts.js"  charset="utf-8"></script>
		<script type="text/javascript">
		$(function(){
			    var myCharts = echarts.init(document.getElementById('main'));
			    //用来盛放X轴坐标值，即节目名称
				var spName=[];  
				//用来盛放投票数  
		        var spcount=[];   
				//通过ajax动态填充数据
				$.ajax({
		  			url:"${pageContext.request.contextPath}/analysis/collect",
		  			type:"post",
		  			dataType:"json",
		  			success:function(data){
		  				$.each(data,function(i,values){
		  					//将获取的样片名称和相应收藏率加入数组
		  					spName.push(data[i].sampleName);
		  					spcount.push(data[i].collRate);
		  			})
		           var option= {
		           		  //标题            
						  title: {
						  		//标题内容
						        text: '收藏率前'+data.length+'图示',
						        x:'center'
							//	y:'center'
						  },
						  color: ['#3398DB'],
						  //工具箱
						  toolbox: {
						        show: true,
						        feature: {
						            //下载
						            saveAsImage: {
						                show: true
						            }
						        }
						    },
						    tooltip : {	
						    //触发类型'item'鼠标移入柱形形成区域 | 'axis'鼠标移入整个柱形区（包括空白区域）				
						        trigger: 'item',
						        //坐标轴指示器
						        // 默认为直线，可选为：'line'为直线 | 'shadow'为阴影					        		
						        axisPointer : {         
						            type : 'line'        
						        },
						   		formatter: "{b} : {c} %"
						        
						    },
						    xAxis: {//横坐标
						        type: 'category',
						        //data的内容为我们获取的参数
						        data: spName,
						        "axisTick": {
						            "alignWithLabel": true
						        },
						        "nameTextStyle": {
						            "color": "#82b0ec"
						        },
						        "axisLine": {
						            "lineStyle": {
						                "color": "#82b0ec"
						            }
						        },
						        "axisLabel": {
						    		interval: 0,
						            "textStyle": {
						                "color": "#000"
						            },
						            margin: 30
						        }
						    },
						    yAxis: {//纵坐标
						        type: 'value',
						        "axisLabel": {
						            "textStyle": {
						                "color": "#82b0ec"
						            },
						            // "formatter": "{value}%"
						        },
						    },
						    series: [{
					            type: 'pictorialBar',
					            symbolSize: [60, 16],
					            symbolOffset: [0, -10],
					            symbolPosition: 'end',
					            z: 12,
						    	//data的内容为我们获取的参数
						        data: spcount,
						        
						        //标签，主要是用来满足需求：要在坐标轴的每个点上将各自对应的数据显示下来
						        label: {
					                "normal": {
					                    "show": true,
					                    "position": "inside",
					                    "formatter": "{c}%",
					                    fontSize: 20,
					                    fontWeight: 'bold',
					                    color: '#34DCFF'
					              },
					            },
					            "itemStyle": {
				                    "color": "#2DB1EF"
				                },
						       // type: 'bar'
						    },
						    {
					            name: '',
					            type: 'pictorialBar',
					            symbolSize: [60, 16],
					            symbolOffset: [0, 10],
					            // "barWidth": "20",
					            z: 12,
					            data: spcount,
					            "itemStyle": {
				                    "color": "#2DB1EF"
				                }
					        },
					        {
					            name: '',
					            type: 'pictorialBar',
					            symbolSize: [90, 30],
					            symbolOffset: [0, 20],
					            z: 10,
					            itemStyle: {
					                normal: {
					                    color: 'transparent',
					                    borderColor: '#2EA9E5',
					                    borderType: 'solid',
					                    borderWidth: 1
					                }
					            },
					            data: spcount
					           
					        },					    
						       {
						            type: 'bar',
						            itemStyle: {
						                normal: {
						                    //color: '#14b1eb',
						                    opacity: .9
						                }
						            },
						            //silent: true,
						            "barWidth": "60",
						            barGap: '10%', // Make series be overlap
						            barCateGoryGap: '10%',
						            data: spcount					
						        }						    						   						    
						    ]
						};
						myCharts.setOption(option);
					}
  				})
		})		
	    </script>
  </head>
  
  <body style="background:#fff;">
    <div style="position: relative;">
  	<center>
		<table>
			<tr>
			<td><div id="main" style="width:1100px;height:600px;position: absolute;top:50%;left:5%;z-index:1;">
			</div></td>
			</tr>
			<tr>
				<td align="center">

				</td>	
			</tr>
		</table>
	</center>
	</div>	
  </body>
</html>
