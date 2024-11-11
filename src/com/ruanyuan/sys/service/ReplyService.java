package com.ruanyuan.sys.service;

import java.util.List;

import com.ruanyuan.sys.pojo.Reply;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.vo.ReplyVo;

/**
* @author
* @Data :2020年4月16日 上午12:06:09
*/
public interface ReplyService {
	
	/**
	 * 查询全部回复类别信息
	 * @param reply 回复对象
	 * @return 包含回复信息的集合
	 */
	public DataGridView getAllReply(ReplyVo replyVo);
	
	/**
	 * 根据回复编号获得回复信息
	 * @return 回复类
	 */
	public Reply getReplyById(Integer replyId);
	
	/**
	 * 添加回复信息
	 * @return 是否添加成功
	 */
	public void addReply(ReplyVo replyVo);
	/**
	 * 修改回复信息
	 * @return 是否修改成功
	 */
	public void updateReply(ReplyVo replyVo);
	/**
	 * 根据编号删除回复信息
	 * @return 是否删除成功
	 */
	public void deleteReplyById(Integer replyId);
	/**
	 * 批量删除回复信息
	 * @return 是否删除成功
	 */
	public void deleteReplyByArray(List<Integer> replyIds);
	/**
	 * 根据留言id查询回复信息
	 * @param replyVo 包装类
	 * @return 回复信息的集合
	 */
	public DataGridView getPcReply(ReplyVo replyVo);

	/**
	 * 根据留言Id查询回复信息
	 *@param messageId 留言编号
	 *@return
	 */
	public Reply getReplyByMessageId(Integer messageId);
}
