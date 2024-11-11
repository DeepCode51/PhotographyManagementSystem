package com.ruanyuan.sys.vo;

import com.ruanyuan.sys.pojo.User;
/**
 * 用户包装类 
 * @Data 2020年4月12日 下午1:57:27
 */
public class UserVo extends User {

	/**
	 * 分页参数
	 */
	private Integer page;
	private Integer limit;
	
	/**
	 * 验证码
	 */
	private String code;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
