package com.ruanyuan.bus.vo;

import com.ruanyuan.bus.pojo.Order;

/**
* @author
* @Data :2020年4月13日 下午9:22:07
*/
public class OrderVo extends Order {
	/**
	 * 分页参数
	 */
	private Integer page;
	private Integer limit;
	/**
	 * 订单时间参数
	 */
	private String startTime;
	private String endTime;
	/**
	 * 客户名称
	 */
	private String realName;
	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
