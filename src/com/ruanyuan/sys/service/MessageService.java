package com.ruanyuan.sys.service;


import java.util.List;

import com.ruanyuan.sys.pojo.Message;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.vo.MessageVo;

/**
* @author
* @Data :2020年4月15日 下午7:53:05
*/
public interface MessageService {
	
	/**
	 * 查找全部留言
	 * 
	 * @return 包含留言的集合
	 */
	public DataGridView getAllMessage(MessageVo messageVo);
	
	/**
	 * 根据留言编号获取留言信息
	 * @param messageId 留言编号
	 * @return 留言类
	 */
	public Message getMessageById(Integer messageId);
	
	/**
	 * 添加留言信息
	 * @param message 留言类
	 */
	public void addMessage(MessageVo messageVo);
	/**
	 * 修改留言信息
	 * @param message 留言类
	 */
	public void updateMessage(MessageVo messageVo);
	
	/**
	 * 根据留言编号删除留言信息
	 * @param messageId 留言编号
	 */
	public Integer deleteMessageById(Integer messageId);
	/**
	 * 批量删除留言信息
	 * @param messageIds 包含留言编号的数组
	 * @return 是否删除成功
	 */
	public int deleteMessageByArray(List<Integer> messageIds);
	
	/**
	 * 查找最新前十条留言（时间排序）
	 * 
	 * @return 包含留言的集合
	 */
	public DataGridView getPcLimitMessage();
	
}
