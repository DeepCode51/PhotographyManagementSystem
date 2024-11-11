package com.ruanyuan.sys.pojo;
/**
 * 用户实体类
 * @Data 2020年4月12日 下午12:37:09
 */
public class User {
	/**
	 * 用户编号
	 */
	private Integer userId;
	/**
	 * 用户编号
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
	 * 用户性别
	 */
	private Integer sex;
	/**
	 * 用户地址
	 */
	private String address;
	/**
	 * 用户工作照
	 */
	private String userImage;
	/**
	 * 是否有审批权限
	 */
	private Integer isApproval;
	/**
	 * 是否是成员阵容
	 */
	private Integer member;
	/**
	 * 用户联系方式
	 */
	private String phone;
	/**
	 * 用户职位
	 */
	private String job;
	
	
	public Integer getIsApproval() {
		return isApproval;
	}
	public void setIsApproval(Integer isApproval) {
		this.isApproval = isApproval;
	}
	public Integer getMember() {
		return member;
	}
	public void setMember(Integer member) {
		this.member = member;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", loginName=" + loginName + ", realName=" + realName + ", password="
				+ password + ", sex=" + sex + ", address=" + address + ", userImage=" + userImage + ", member=" + member
				+ ", phone=" + phone + ", job=" + job + "]";
	}
	
	
}
