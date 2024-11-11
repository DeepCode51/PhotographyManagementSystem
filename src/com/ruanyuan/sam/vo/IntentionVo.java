package com.ruanyuan.sam.vo;

import com.ruanyuan.sam.pojo.Intention;
/**
 * 意向封装类
 * @date 2020年4月13日
 */
public class IntentionVo extends Intention{
	/**
	 * 分页参数
	 */
	private Integer page;
	private Integer limit;
	
	/**
	 * 样片名称
	 */
	private String sampleName;
	/**
	 * 客户真实姓名
	 */
	private String realName;
	/**
	 *时间参数
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
	public String getSampleName() {
		return sampleName;
	}
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
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
	
	
}
