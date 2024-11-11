package com.ruanyuan.stat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyuan.stat.dao.StatisticsDao;
import com.ruanyuan.stat.service.StatisticsService;
/**
 * 统计业务实现
 *@Date 2020年04月22日 11:55
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
	@Autowired
	private StatisticsDao statisticsDao;
	/**
	 *统计每年的订单成交量
	 */
	@Override
	public List<Integer> loadOrdersYearGradeStat(String year) {
		// TODO Auto-generated method stub
		return statisticsDao.loadOrdersYearGradeStat(year);
	}
	
}
