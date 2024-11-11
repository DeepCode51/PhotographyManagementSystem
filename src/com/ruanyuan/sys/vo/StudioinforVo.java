package com.ruanyuan.sys.vo;

import com.ruanyuan.sys.pojo.Studioinfor;

/**
 * 工作室信息包装类
 *@Date 2020年04月13日 21:17
 */
public class StudioinforVo extends Studioinfor{
	/**
	 * 分页参数
	 */
	private Integer page;
	private Integer limit;
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
