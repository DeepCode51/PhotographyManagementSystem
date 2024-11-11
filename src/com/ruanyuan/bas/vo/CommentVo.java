package com.ruanyuan.bas.vo;

import com.ruanyuan.bas.pojo.Comment;

/**
 * 评论包装类
 * @Data 2020年4月13日 下午12:34:40
 */
public class CommentVo extends Comment{
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
	 * 类别姓名
	 */
	private String typeName;
	/**
	 * 评论时间参数
	 */
	private String starttime;
	private String endtime;
	//私有属性的 getter/setter 方法
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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
