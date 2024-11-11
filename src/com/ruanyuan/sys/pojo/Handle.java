package com.ruanyuan.sys.pojo;
/**
 * 操作日志实体类
 * 
 * @date:2020-04-13 09:30
 *
 */
public class Handle {
	/**
	 * 操作日志编号
	 */
	private Integer handleId;
	/**
	 * 操作人编号
	 */
	private Integer userId;
	/**
	 * 操作内容
	 */
	private String handleContent;
	/**
	 * 操作时间
	 */
	private String handleTime;
	/**
	 * 附属对象
	 */
	private User user;
	
	
	public Integer getHandleId() {
		return handleId;
	}
	public void setHandleId(Integer handleId) {
		this.handleId = handleId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getHandleContent() {
		return handleContent;
	}
	public void setHandleContent(String handleContent) {
		this.handleContent = handleContent;
	}
	public String getHandleTime() {
		return handleTime;
	}
	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	@Override
	public String toString() {
		return "Handle [handleId=" + handleId + ", userId=" + userId + ", handleContent=" + handleContent
				+ ", handleTime=" + handleTime + ", user=" + user + "]";
	}
	
	
	
}
