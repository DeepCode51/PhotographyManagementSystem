package com.ruanyuan.sys.vo;

import com.ruanyuan.sys.pojo.Login;

/**
 * 
 * 登录日志包装类
 * @date:2020-04-13 09:36
 *
 */
public class LoginVo extends Login{
	/**
	 * 分页参数
	 */
	private Integer page;
	private Integer limit;
	/**
	 * 时间参数
	 * 
	 */
	private String startLoginTime;
	private String endLoginTime;
	/**
	 * 用户名称
	 */
	private String realName;
	private String userName;
	
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
	public String getStartLoginTime() {
		return startLoginTime;
	}
	public void setStartLoginTime(String startLoginTime) {
		this.startLoginTime = startLoginTime;
	}
	public String getEndLoginTime() {
		return endLoginTime;
	}
	public void setEndLoginTime(String endLoginTime) {
		this.endLoginTime = endLoginTime;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
