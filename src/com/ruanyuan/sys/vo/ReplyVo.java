package com.ruanyuan.sys.vo;

import com.ruanyuan.sys.pojo.Reply;

/**
 * 回复包装类
 *@Date 2020年04月13日 21:13
 */
public class ReplyVo extends Reply{
	
	/**
	 * 分页参数
	 */
	private Integer page;
	private Integer limit;
	
	/**
	 * 留言內容
	 */
	private String messageContent;
	
	/**
	 * 留言姓名
	 */
	private String realName;
	
	/**
	 * 时间参数
	 */
	private String startReplyTime;
	private String endReplyTime;
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
	public String getStartReplyTime() {
		return startReplyTime;
	}
	public void setStartReplyTime(String startReplyTime) {
		this.startReplyTime = startReplyTime;
	}
	public String getEndReplyTime() {
		return endReplyTime;
	}
	public void setEndReplyTime(String endReplyTime) {
		this.endReplyTime = endReplyTime;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
}
