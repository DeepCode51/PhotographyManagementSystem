package com.ruanyuan.sys.pojo;
/**
 * 工作室信息表
 *@Date 2020年04月13日 09:10
 */
public class Studioinfor {
	/**
	 * 工作室编号
	 */
	private Integer studId;
	/**
	 * 工作室名称
	 */
	private String studName;
	/**
	 * 工作室简介
	 */
	private String studIntroduction;
	/**
	 * 成员阵容
	 */
	private String members;
	/**
	 * 关于我们
	 */
	private String aboutUs;
	
	public Integer getStudId() {
		return studId;
	}
	public void setStudId(Integer studId) {
		this.studId = studId;
	}
	public String getStudName() {
		return studName;
	}
	public void setStudName(String studName) {
		this.studName = studName;
	}
	public String getStudIntroduction() {
		return studIntroduction;
	}
	public void setStudIntroduction(String studIntroduction) {
		this.studIntroduction = studIntroduction;
	}
	public String getMembers() {
		return members;
	}
	public void setMembers(String members) {
		this.members = members;
	}
	public String getAboutUs() {
		return aboutUs;
	}
	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}
	@Override
	public String toString() {
		return "Studioinfor [studId=" + studId + ", studName=" + studName + ", studIntroduction=" + studIntroduction
				+ ", members=" + members + ", aboutUs=" + aboutUs + "]";
	}
}
