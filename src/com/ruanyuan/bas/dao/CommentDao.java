package com.ruanyuan.bas.dao;
/**
 * 评论数据访问层接口
 * @Data 2020年4月13日 上午11:50:54
 */

import java.util.List;

import com.ruanyuan.bas.pojo.Comment;

public interface CommentDao {
	/**
	 * 查询所有的评论信息
	 * @param comment 评论对象
	 * @return 评论集合
	 */
	public List<Comment> getAllComment(Comment comment);
	/**
	 * 添加评论信息
	 * @param comment 评论对象
	 * @return 受影响的行数
	 */
	public Integer addComment(Comment comment);
	/**
	 * 修改评论信息
	 * @param comment 评论对象
	 * @return 受影响的行数
	 */
	public Integer updateComment(Comment comment);
	/**
	 * 根据评论Id查询评论信息
	 * @param commId 评论Id
	 * @return 评论对象
	 */
	public Comment getCommentByCommId(Integer commId);
	/**
	 * 根据样片Id查询评论信息
	 * @param commId 样片Id
	 * @return 评论对象
	 */
	public List<Comment> getCommentBySampleId(Comment comment);
	/**
	 * 根据样片Id查询评论信息
	 * @param commId 样片Id
	 * @return 评论对象
	 */
	public List<Comment> getCommentBySampleId1(Comment comment);
	/**
	 * 根据评论Id删除评论信息
	 *@param commId 评论Id
	 *@return  受影响的行数
	 */
	public Integer deleteCommentByCommId(Integer commId);
	/**
	 * 批量删除评论信息
	 *@param ids 评论Id数组
	 *@return  受影响的行数
	 */
	public Integer deleteCommentByArray(List<Integer> ids);
	/**
	 * 根据客户id查询评论信息
	 * @param customerId 客户id
	 * @return 返回评论对象集合
	 */
	public List<Comment> getCommentCustomerId(Integer customerId);
	
}
