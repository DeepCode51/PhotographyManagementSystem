package com.ruanyuan.bus.pojo;

import com.ruanyuan.bas.pojo.Customer;

/**
* @author 订单表实体类
* @Data :2020年4月13日 下午2:44:43
*/
public class Order {
	/**
	 * 订单编号
	 */
	private Integer orderId;
	/**
	 * 客户编号
	 */
	private Integer customerId;
	/**
	 * 预约编号
	 */
	private Integer subId;
	/**
	 * 订单名称
	 */
	private String orderName;
	/**
	 * 下单时间
	 */
	private String orderTime;	
	/**
	 * 附属外键
	 */
	private Subscribe subscribe;
	private Customer customer;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getSubId() {
		return subId;
	}
	public void setSubId(Integer subId) {
		this.subId = subId;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public Subscribe getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Subscribe subscribe) {
		this.subscribe = subscribe;
	}
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customerId=" + customerId + ", subId=" + subId + ", orderName="
				+ orderName + ", orderTime=" + orderTime + ", subscribe=" + subscribe + ", customer=" + customer + "]";
	}

	
}
