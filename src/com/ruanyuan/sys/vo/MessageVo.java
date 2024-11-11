package com.ruanyuan.sys.vo;

import com.ruanyuan.sys.pojo.Message;

/**
 * 留言包装类
 *@Date 2020年04月13日 21:16
 */
public class MessageVo extends Message{
	
	/**
	 * 分页参数
	 */
	private Integer page;
	private Integer limit;
	
	/**
	 * 客户姓名
	 */
	private String realName;
	
	/**
	 * 时间参数
	 */
	private String starttime;
	private String endtime;
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
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
}
