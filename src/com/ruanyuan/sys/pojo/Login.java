package com.ruanyuan.sys.pojo;
/**
 * 登录日志实体类
 * 
 * @date:2020-04-13 09:32
 *
 */
public class Login {
	/**
	 * 登录日志编号
	 */
	private  Integer  loginId;
	/**
	 * 登录人编号
	 */
	private  Integer  userId;
	/**
	 * 登录时间
	 */
	private String  loginTime;
	/**
	 * 登录ip
	 */
	private String  loginIp;
	/**
	 * 附属对象
	 */
	private User  user;

	
	public Integer getLoginId() {
		return loginId;
	}
	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	@Override
	public String toString() {
		return "Login [loginId=" + loginId + ", userId=" + userId + ", loginTime=" + loginTime + ", loginIp=" + loginIp
				+ ", user=" + user + "]";
	}
	
}
