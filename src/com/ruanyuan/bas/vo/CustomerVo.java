package com.ruanyuan.bas.vo;

import com.ruanyuan.bas.pojo.Customer;

/**
 * 样片包装类
 * @author 
 *
 */
public class CustomerVo extends Customer {
	
	/**
	 * 分页参数
	 */
	private Integer page;
	private Integer limit;
	/**
	 * 客户金额参数
	 */
	private Integer startMoney;
	private Integer endMoney;
	/**
	 * 注册页面中的name属性
	 */
	private String username;
	/**
	 * 注册页面中的手机号name属性
	 */
	private String phone_number;
	
	public Integer getStartMoney() {
		return startMoney;
	}

	public void setStartMoney(Integer startMoney) {
		this.startMoney = startMoney;
	}

	public Integer getEndMoney() {
		return endMoney;
	}

	public void setEndMoney(Integer endMoney) {
		this.endMoney = endMoney;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
}
