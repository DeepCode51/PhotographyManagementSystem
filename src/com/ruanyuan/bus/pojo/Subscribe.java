package com.ruanyuan.bus.pojo;

import com.ruanyuan.bas.pojo.Customer;
import com.ruanyuan.sam.pojo.Sample;

/**
 * 预约实体类
 * @Data 2020年4月13日 下午1:06:42
 */
public class Subscribe {
	/**
	 * 预约编号
	 */
	private Integer subId;
	/**
	 * 客户编号
	 */
	private Integer customerId;
	/**
	 * 样片编号
	 */
	private Integer sampleId;
	/**
	 * 成交价格
	 */
	private String price;
	/**
	 * 预约状态 1-待审核 2-未使用，3-已使用，4-逾期时间
	 */
	private Integer subState;
	/**
	 * 样片预约时间
	 */
	private String subeTime;
    /**
     * 附属对象
     */
    private Sample sample;
    
    private Customer customer;

	public Integer getSubId() {
		return subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getSampleId() {
		return sampleId;
	}

	public void setSampleId(Integer sampleId) {
		this.sampleId = sampleId;
	}


	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getSubState() {
		return subState;
	}

	public void setSubState(Integer subState) {
		this.subState = subState;
	}

	public String getSubeTime() {
		return subeTime;
	}

	public void setSubeTime(String subeTime) {
		this.subeTime = subeTime;
	}

	public Sample getSample() {
		return sample;
	}

	public void setSample(Sample sample) {
		this.sample = sample;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Subscribe [subId=" + subId + ", customerId=" + customerId + ", sampleId=" + sampleId + ", price="
				+ price + ", subState=" + subState + ", subeTime=" + subeTime + ", sample=" + sample + ", customer="
				+ customer + "]";
	}
    
}
