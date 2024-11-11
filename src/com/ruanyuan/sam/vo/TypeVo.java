package com.ruanyuan.sam.vo;

import com.ruanyuan.sam.pojo.Type;
/**
 * 类别包装类 
 * @Data 2020年4月12日 下午1:57:27
 */
public class TypeVo extends Type {

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
	 * 备注
	 */
	private String remark;
	
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
