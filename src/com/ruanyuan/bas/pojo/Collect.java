package com.ruanyuan.bas.pojo;

import com.ruanyuan.sam.pojo.Sample;
/**
 * 收藏实体类
 * @date 2020年4月13日
 */
public class Collect {
	/**
	 * 收藏编号
	 */
	private Integer collectId;
	/**
	 * 样片编号
	 */
	private Integer sampleId;
	/**
	 * 客户编号
	 */
	private Integer customerId;
	/**
	 * 收藏时间
	 */
	private String collectTime;
	
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

	public Integer getCollectId() {
		return collectId;
	}

	public void setCollectId(Integer collectId) {
		this.collectId = collectId;
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

	public String getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(String collectTime) {
		this.collectTime = collectTime;
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
		return "Collection [collectId=" + collectId + ", sampleId=" + sampleId + ", customerId=" + customerId
				+ ", collectTime=" + collectTime + ", sample=" + sample + ", customer=" + customer + "]";
	}
	
	
	
}
