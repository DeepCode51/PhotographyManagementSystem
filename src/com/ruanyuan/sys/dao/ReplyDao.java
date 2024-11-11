package com.ruanyuan.sys.dao;

import java.util.List;

import com.ruanyuan.sys.pojo.Reply;

/**
 * 回复类接口
 *@Date 2020年04月13日 10:11
 */
public interface ReplyDao {
	/**
	 * 查找全部回复信息
	 * @return 包含回复信息的集合
	 */
	public List<Reply> getAllReply();
	
	/**
	 * 查询全部方法重载
	 * @param reply 回复对象
	 * @return 包含回复信息的集合
	 */
	public List<Reply> getAllReply(Reply reply);
	/**
	 * 根据回复编号获得回复信息
	 * @return 回复类
	 */
	public Reply getReplyById(Integer replyId);
	/**
	 * 添加回复信息
	 * @return 是否添加成功
	 */
	public Integer addReply(Reply reply);
	/**
	 * 修改回复信息
	 * @return 是否修改成功
	 */
	public Integer updateReply(Reply reply);
	/**
	 * 根据编号删除回复信息
	 * @return 是否删除成功
	 */
	public Integer deleteReplyById(Integer replyId);
	/**
	 * 批量删除回复信息
	 * @return 是否删除成功
	 */
	public Integer deleteReplyByArray(List<Integer> replyIds);
	/**
	 * 根据用户id查询回复信息
	 * @param userId 用户id
	 * @return 返回回复对象结合
	 */
	public List<Reply> getReplyUserId(Integer userId);
	
	/**
	 * 根据留言id查询回复信息
	 * @param arr1 留言id
	 * @return 返回回复对象结合
	 */
	public List<Reply> getReplyMessageId(Integer[] arr1);
	public List<Reply> getReplyMessageId(Integer messageId);
	/**
	 * 根据留言Id查询回复信息
	 *@param messageId 留言编号
	 *@return
	 */
	public Reply getReplyByMessageId(Integer messageId);
}
