package com.ruanyuan.bas.pojo;

/**
 * 客户实体类
 * @author 
 *
 */
public class Customer {
	/**
	 * 客户编号
	 */
	private Integer customerId;
	/**
	 * 客户账号
	 */
	private String loginName;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 性别 0男1女
	 */
	private Integer sex;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 练习方式
	 */
	private String phone;
	/**
	 * 客户余额
	 */
	private String balance;
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", loginName=" + loginName + ", realName=" + realName
				+ ", password=" + password + ", sex=" + sex + ", address=" + address + ", phone=" + phone + ", balance="
				+ balance + "]";
	}
	
}
