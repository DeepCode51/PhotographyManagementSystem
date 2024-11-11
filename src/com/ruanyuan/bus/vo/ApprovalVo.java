package com.ruanyuan.bus.vo;

import com.ruanyuan.bus.pojo.Approval;
/**
 *   审批包装类
 * @Data 2020年4月13日 上午11:32:43
 */
public class ApprovalVo extends Approval{

	/**
	 * 分页参数
	 */
	private Integer page;
	private Integer limit;
	
	/**
	  * 被审批人
	 */
	private String userName1;
	/**
	  * 审批人
	 */
	private String userName2;
	/**
	 * 样片名称
	 */
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
	public String getUserName1() {
		return userName1;
	}
	public void setUserName1(String userName1) {
		this.userName1 = userName1;
	}
	public String getUserName2() {
		return userName2;
	}
	public void setUserName2(String userName2) {
		this.userName2 = userName2;
	}
	public String getSampleName() {
		return sampleName;
	}
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
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
