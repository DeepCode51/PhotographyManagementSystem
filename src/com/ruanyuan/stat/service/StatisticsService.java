package com.ruanyuan.stat.service;

import java.util.List;

/**
 * 统计业务接口
 *@Date 2020年04月22日 11:55
 */
public interface StatisticsService {
	/**
	 * 统计每年每月的订单成交量
	 * @param year 年份
	 * @return 带有信息的集合
	 */
	public List<Integer> loadOrdersYearGradeStat(String year);
}
