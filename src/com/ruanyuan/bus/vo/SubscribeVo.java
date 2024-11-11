package com.ruanyuan.bus.vo;

import com.ruanyuan.bus.pojo.Subscribe;

/**
 * 预约包装类
 * @Data 2020年4月13日 下午12:34:40
 */
public class SubscribeVo extends Subscribe{
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
	 * 真实姓名
	 */
	private String realName;
	
	/**
	 * 系统时间
	 */
	private String systime;
	
	/**
	 * 类别姓名
	 */
	private String typeName;
	/**
	 * 客户手机号
	 */
	private String phone;
	/**
	 * 预约时间参数
	 */
	private String starttime;
	private String endtime;
	
	/**
	 * 客户密码
	 */
	private String customerpwd;
	
	//私有属性的 getter/setter 方法
	
	public Integer getPage() {
		return page;
	}
	public String getSystime() {
		return systime;
	}
	public void setSystime(String systime) {
		this.systime = systime;
	}
	public String getCustomerpwd() {
		return customerpwd;
	}
	public void setCustomerpwd(String customerpwd) {
		this.customerpwd = customerpwd;
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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
