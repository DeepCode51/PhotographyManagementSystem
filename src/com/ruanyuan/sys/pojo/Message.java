package com.ruanyuan.sys.pojo;

import com.ruanyuan.bas.pojo.Customer;

/**
 * 留言表实体类
 *@Date 2020年04月13日 09:08
 */
public class Message {
	/**
	 * 留言编号
	 */
	private Integer messageId;
	/**
	 * 客户编号，客户表外键
	 */
	private Integer customerId;
	/**
	 * 客户类外键
	 */
	private Customer customer;
	/**
	 * 留言内容
	 */
	private String messageContent;
	/**
	 * 留言时间
	 */
	private String messageTime;
	
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
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
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getMessageTime() {
		return messageTime;
	}
	public void setMessageTime(String messageTime) {
		this.messageTime = messageTime;
	}
	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", customerId=" + customerId + ", customer=" + customer
				+ ", messageContent=" + messageContent + ", messageTime=" + messageTime + "]";
	}
}
