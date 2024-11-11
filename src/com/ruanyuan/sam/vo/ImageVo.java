package com.ruanyuan.sam.vo;

import com.ruanyuan.sam.pojo.Image;

public class ImageVo extends Image {

	/**
	 * 分页参数
	 */
	private Integer page;
	private Integer limit;
	
	private String sampleName;
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
	public String getSampleName() {
		return sampleName;
	}
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	
}
