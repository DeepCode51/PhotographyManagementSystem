package com.ruanyuan.sys.dao;

import java.util.List;

import com.ruanyuan.sys.pojo.Message;

/**
 * 回复类接口
 *@Date 2020年04月13日 17:34
 */
public interface MessageDao {
	
	public List<Message> getAllMessage();
	
	/**
	 * 查找全部留言
	 * @param Message
	 * @return
	 */
	public List<Message> getAllMessage(Message Message);
	
	/**
	 * 根据留言编号获取留言信息
	 * @param messageId 留言编号
	 * @return 留言类
	 */
	public Message getMessageById(Integer messageId);
	/**
	 * 添加留言信息
	 * @param message 留言类
	 * @return 是否添加成功
	 */
	public Integer addMessage(Message message);
	/**
	 * 修改留言信息
	 * @param message 留言类
	 * @return 是否修改成功
	 */
	public Integer updateMessage(Message message);
	/**
	 * 根据留言编号删除留言信息
	 * @param messageId 留言编号
	 * @return 是否删除成功
	 */
	public Integer deleteMessageById(Integer messageId);
	/**
	 * 批量删除留言信息
	 * @param messageIds 包含留言编号的数组
	 * @return 是否删除成功
	 */
	public int deleteMessageByArray(List<Integer> messageIds);
	/**
	 * 根据客户id查询留言信息
	 * @param customerId 客户id
	 * @return 返回留言对象集合
	 */
	public List<Message> getMessageCustomerId(Integer customerId);
	/**
	 * 查找前十条留言
	 * @param Message
	 * @return
	 */
	public List<Message> getPcMessage();
}
