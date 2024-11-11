package com.ruanyuan.bus.pojo;

import com.ruanyuan.sam.pojo.Sample;
import com.ruanyuan.sys.pojo.User;

/**
 *   审批实体类
 * @Data 2020年4月13日 上午11:18:51
 */
public class Approval {
	/**
	 * 审批编号
	 */
	private Integer approvalId;
	/**
	 * 审批内容
	 */
	private Integer approvalCount;
	/**
	 * 被审批人
	 */
	private Integer approvedPerson;
	/**
	 * 审批人
	 */
	private Integer approver;
	/**
	 * 样片编号
	 */
	private Integer sampleId;
	/**
	 * 审批状态
	 */
	private Integer approvalState;
	/**
	 * 原因
	 */
	private String why;
	/**
	 * 审批时间
	 */
	private String approvalTime;
	
	/**
	 * 附属对象
	 * 
	 */
	private User user1;
	private User user2;
	private Sample sample;
	public Integer getApprovalId() {
		return approvalId;
	}
	public void setApprovalId(Integer approvalId) {
		this.approvalId = approvalId;
	}
	public Integer getApprovedPerson() {
		return approvedPerson;
	}
	
	
	public Integer getApprovalCount() {
		return approvalCount;
	}
	public void setApprovalCount(Integer approvalCount) {
		this.approvalCount = approvalCount;
	}
	public void setApprovedPerson(Integer approvedPerson) {
		this.approvedPerson = approvedPerson;
	}
	public Integer getApprover() {
		return approver;
	}
	public void setApprover(Integer approver) {
		this.approver = approver;
	}
	public Integer getSampleId() {
		return sampleId;
	}
	public void setSampleId(Integer sampleId) {
		this.sampleId = sampleId;
	}
	public Integer getApprovalState() {
		return approvalState;
	}
	public void setApprovalState(Integer approvalState) {
		this.approvalState = approvalState;
	}
	public String getWhy() {
		return why;
	}
	public void setWhy(String why) {
		this.why = why;
	}
	public String getApprovalTime() {
		return approvalTime;
	}
	public void setApprovalTime(String approvalTime) {
		this.approvalTime = approvalTime;
	}
	public User getUser1() {
		return user1;
	}
	public void setUser1(User user1) {
		this.user1 = user1;
	}
	public User getUser2() {
		return user2;
	}
	public void setUser2(User user2) {
		this.user2 = user2;
	}
	public Sample getSample() {
		return sample;
	}
	public void setSample(Sample sample) {
		this.sample = sample;
	}
	@Override
	public String toString() {
		return "Approval [approvalId=" + approvalId + ", approvalCount=" + approvalCount + ", approvedPerson="
				+ approvedPerson + ", approver=" + approver + ", sampleId=" + sampleId + ", approvalState="
				+ approvalState + ", why=" + why + ", approvalTime=" + approvalTime + ", user1=" + user1 + ", user2="
				+ user2 + ", sample=" + sample + "]";
	}
	
	
}
