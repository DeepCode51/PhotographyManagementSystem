package com.ruanyuan.sys.pojo;
/**
 * 回复实体类
 *@Date 2020年04月13日 09:03
 */
public class Reply {
	/**
	 * 回复编号
	 */
	private Integer replyId;
	/**
	 * 留言编号，留言表外键
	 */
	private Integer messageId;
	/**
	 * 用户编号，用户表外键
	 */
	private Integer userId;
	/**
	 * 回复留言内容
	 */
	private String replyContent;
	/**
	 * 回复时间
	 */
	private String replyTime;
	/**
	 * 留言表外键
	 */
	private Message message;
	/**
	 * 用户编号
	 */
	private User user;
	
	public Integer getReplyId() {
		return replyId;
	}
	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}
	@Override
	public String toString() {
		return "Reply [replyId=" + replyId + ", messageId=" + messageId + ", message=" + message + ", userId=" + userId
				+ ", user=" + user + ", replyContent=" + replyContent + ", replyTime=" + replyTime + "]";
	}
}
