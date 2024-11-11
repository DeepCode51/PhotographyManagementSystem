package com.ruanyuan.sam.pojo;

import com.ruanyuan.bas.pojo.Customer;

/**
 * 意向样片实体类
 * @date 2020年4月13日
 */
public class Intention {
	/**
	 * 意向样片编号
	 */
	private Integer intentionId;
	/**
	 * 样片编号
	 */
	private Integer sampleId;
	/**
	 * 客户编号
	 */
	private Integer customerId;
	/**
	 * 加入意向样片时间
	 */
	private String intentionTime;
	
	/**
	 * 附属属性
	 */
	/**
	 * 样片实体类
	 */
	private Sample sample;
	/**
	 * 客户实体类
	 */
	private Customer customer;
	
	
	public Integer getIntentionId() {
		return intentionId;
	}
	public void setIntentionId(Integer intentionId) {
		this.intentionId = intentionId;
	}
	public Integer getSampleId() {
		return sampleId;
	}
	public void setSampleId(Integer sampleId) {
		this.sampleId = sampleId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getIntentionTime() {
		return intentionTime;
	}
	public void setIntentionTime(String intentionTime) {
		this.intentionTime = intentionTime;
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
		return "Intention [intentionId=" + intentionId + ", sampleId=" + sampleId + ", customerId=" + customerId
				+ ", intentionTime=" + intentionTime + ", sample=" + sample + ", customer=" + customer + "]";
	}
	
	
}
