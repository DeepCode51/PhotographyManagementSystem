package com.ruanyuan.sam.vo;

import com.ruanyuan.sam.pojo.Rotation;

/**
* @author
* @Data :2020年4月13日 下午9:35:16
*/
public class RotationVo extends Rotation {
	
	/**
	 * 分页参数
	 */
	private Integer page;
	private Integer limit;
	/**
	 * 轮播图上传时间
	 */
	private String startTime;
	private String endTime;
	/**
	 * 样片名称
	 */
	private String sampleName;
	
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
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getSampleName() {
		return sampleName;
	}
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	
}
