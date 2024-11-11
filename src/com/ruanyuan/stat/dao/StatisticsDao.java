package com.ruanyuan.stat.dao;

import java.util.List;
/**
 * 统计接口
 *@Date 2020年04月22日 12:01
 */
public interface StatisticsDao {
	/**
	 * 统计每年每月的订单成交量
	 * @param year 年份
	 * @return 带有信息的集合
	 */
	public List<Integer> loadOrdersYearGradeStat(String year);
}
