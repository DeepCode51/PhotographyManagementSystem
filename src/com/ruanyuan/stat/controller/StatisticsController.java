package com.ruanyuan.stat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("statistics")
public class StatisticsController {
	/**
	 * 收藏率页面
	 * @return
	 */
	@RequestMapping("collectionRate")
	public String getcollectionRate() {
		return "admin/statistics/collectionRate";
	}
	/**
	 * 订单率页面
	 * @return
	 */
	@RequestMapping("ordersRate")
	public String getordersRate() {
		return "admin/statistics/ordersRate";
	}
	@RequestMapping("ordersMonthStat")
	public String getEveryMonthOrders() {
		return "admin/statistics/ordersMonthStat";
	}
}
