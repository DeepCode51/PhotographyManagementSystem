package com.ruanyuan.sys.vo;

import com.ruanyuan.sys.pojo.Handle;

/**
 * 操作日志实体类
 * 
 * @date:2020-04-13 09:40
 *
 */
public class HandleVo extends Handle{
	/**
	 * 分页参数
	 */
	private Integer page;
	private Integer limit;
	/**
	 * 时间参数
	 * 
	 */
	private String startHandleTime;
	private String endHandleTime;
	/**
	 * 用户名称
	 */
	private String realName;
	
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
	public String getStartHandleTime() {
		return startHandleTime;
	}
	public void setStartHandleTime(String startHandleTime) {
		this.startHandleTime = startHandleTime;
	}
	public String getEndHandleTime() {
		return endHandleTime;
	}
	public void setEndHandleTime(String endHandleTime) {
		this.endHandleTime = endHandleTime;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}

	
}
