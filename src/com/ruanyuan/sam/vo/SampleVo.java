package com.ruanyuan.sam.vo;

import com.ruanyuan.sam.pojo.Sample;

/**
 * 样片包装类 
 * @Data 2020年4月12日 下午1:57:27
 */
public class SampleVo extends Sample {
	/**
	 * 分页参数
	 */
	private Integer page;
	private Integer limit;
	/**
	 * 类别名称
	 */
	private String typeName;
	/**
	 *时间参数
	 */
	private String starttime;
	private String endtime;
	
	
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
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
	
	
}
