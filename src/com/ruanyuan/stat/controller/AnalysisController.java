package com.ruanyuan.stat.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruanyuan.sam.pojo.Sample;
import com.ruanyuan.sam.service.SampleService;
import com.ruanyuan.stat.pojo.CollectionRate;
import com.ruanyuan.stat.pojo.OrdersRate;
import com.ruanyuan.stat.service.StatisticsService;


/**
 * 统计分析控制层
 *
 */
@RestController
@RequestMapping("analysis")
public class AnalysisController {
	//注解注入业务层
	@Autowired
	private SampleService sampleService;
	@Autowired
	private StatisticsService statisticsService;
	/**
	 * 收藏统计
	 * @return
	 */
	@RequestMapping("collect")
	public List<CollectionRate> collect() {
//		//收藏率集合
		List<CollectionRate> coList = new ArrayList<CollectionRate>();
		//查询所有的样片
		List<Sample> saList = sampleService.getAllSample();
		Integer count = 0;
		if(saList.size()>10){
			count = 10;
		}else {
			count = saList.size();
		}
		//总收藏
		float num=0;
		for (Sample sample : saList) {
			//收藏量
			Float subscribecount = Float.parseFloat(sample.getCollectionCount());
			num += subscribecount;
		}
		for (Sample sample2 : saList) {
			//收藏量
			Float subscribecount2 = Float.parseFloat(sample2.getCollectionCount());
			//收藏率
			float collRate = 0;
			if (num != 0) {
				collRate = (float)(Math.round((subscribecount2/num)*100)/(float)100);
			}
			//样片名称
			String sampleName = sample2.getSampleName();
			coList.add(new CollectionRate(sampleName,collRate));
		    Collections.sort(coList, new Comparator<CollectionRate>() {
            public int compare(CollectionRate arg0, CollectionRate arg1) {
                if (arg0.getCollRate()>arg1.getCollRate()){
                    return -1;
                }else if(arg0.getCollRate()<arg1.getCollRate()){
                    return 1;
                }else{
                    return 0;
                }
            }
        });
		}
		return coList.subList(0, count);
	}
	
	/**
	 * 订单统计
	 * @return
	 */
	@RequestMapping("orders")
	public List<OrdersRate> orders() {
//		//订单率集合
		List<OrdersRate> orList = new ArrayList<OrdersRate>();
		//查询所有的样片
		List<Sample> saList = sampleService.getAllSample();
		Integer count = 0;
		if(saList.size()>10){
			count = 10;
		}else {
			count = saList.size();
		}
		//总订单量
		float num=0;
		for (Sample sample : saList) {
			//订单量
			Float orderCount = Float.parseFloat(sample.getOrderCount());
			num += orderCount;
		}
		for (Sample sample2 : saList) {
			//订单量
			Float orderCount2 = Float.parseFloat(sample2.getOrderCount());
			//订单率
			float ordersRate = 0;
			if (num != 0) {
				ordersRate = (float)(Math.round((orderCount2/num)*100)/(float)100);
			}
			//样片名称
			String sampleName = sample2.getSampleName();
			orList.add(new OrdersRate(sampleName,ordersRate));
		    Collections.sort(orList, new Comparator<OrdersRate>() {
            public int compare(OrdersRate arg0, OrdersRate arg1) {
                if (arg0.getOrdersRate()>arg1.getOrdersRate()){
                    return -1;
                }else if(arg0.getOrdersRate()<arg1.getOrdersRate()){
                    return 1;
                }else{
                    return 0;
                }
            }
        });
		}
		return orList.subList(0, count);
	}
	/**
	 * 加载订单量年度统计数据
	 */
	@RequestMapping("loadOrdersYearGradeStat")
	public List<Integer> loadOrdersYearGradeStat(String year){
		List<Integer> entities=this.statisticsService.loadOrdersYearGradeStat(year);
		for (int i = 0; i < entities.size(); i++) {
			if(null==entities.get(i)) {
				entities.set(i, 0);
			}
		}
		System.out.println(entities);
		return entities;
	}
}