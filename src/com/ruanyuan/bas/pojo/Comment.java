package com.ruanyuan.bas.pojo;

import com.ruanyuan.sam.pojo.Sample;

/**
 * 用户实体类
 * @Data 2020年4月13日 上午10:27:23
 */
public class Comment {
	/**
	 * 评论编号
	 */
	private Integer commId;
	/**
	 * 客户编号
	 */
	private Integer customerId;
	/**
	 *附属属性 客户
	 */
	private Customer customer;
	/**
	 * 样片编号
	 */
	private Integer sampleId;
	/**
	 *附属属性 样片
	 */
	private Sample sample;
	/**
	 * 内容
	 */
	private String commContent;
	/**
	 * 时间
	 */
	private String commTime;
	//定义私有属性的 getter/setter方法
	public Integer getCommId() {
		return commId;
	}

	public void setCommId(Integer commId) {
		this.commId = commId;
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

	public Integer getSampleId() {
		return sampleId;
	}

	public void setSampleId(Integer sampleId) {
		this.sampleId = sampleId;
	}

	public Sample getSample() {
		return sample;
	}

	public void setSample(Sample sample) {
		this.sample = sample;
	}

	public String getCommContent() {
		return commContent;
	}

	public void setCommContent(String commContent) {
		this.commContent = commContent;
	}

	public String getCommTime() {
		return commTime;
	}

	public void setCommTime(String commTime) {
		this.commTime = commTime;
	}
	//重写私有属性的toString方法
	@Override
	public String toString() {
		return "Comment [commId=" + commId + ", customerId=" + customerId + ", customer=" + customer + ", sampleId="
				+ sampleId + ", sample=" + sample + ", commContent=" + commContent + ", commTime=" + commTime + "]";
	}



	
	

	
	
}
