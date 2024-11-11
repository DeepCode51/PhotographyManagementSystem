package com.ruanyuan.bas.service;

import java.util.List;

import com.ruanyuan.bas.pojo.Comment;
import com.ruanyuan.bas.vo.CommentVo;
import com.ruanyuan.sys.utils.DataGridView;

/**
 * 评论的业务逻辑层接口
 * @Data 2020年4月13日 上午11:52:32
 */
public interface CommentService {
	/**
	 * 查询所有的评论信息
	 * @param comment 评论对象
	 * @return layui封装得数据
	 */
	public DataGridView getAllComment(CommentVo commentVo);
	
	/**
	 * 查询所有的评论信息
	 * @param commentVo 评论对象
	 * @return
	 */
	public List<Comment> getAllComment1(CommentVo commentVo);
	
	
	/**
	 * 添加评论信息
	 * @param comment 评论对象
	 */
	public void addComment(CommentVo commentVo);
	/**
	 * 修改评论信息
	 * @param comment 评论对象
	 */
	public void updateComment(CommentVo commentVo);
	/**
	 * 根据评论Id查询评论信息
	 * @param commId 评论Id
	 * @return 评论对象
	 */
	public Comment getCommentByCommId(Integer commId);
	/**
	 * 根据样片Id查询评论信息
	 * @param commId 样片Id
	 * @return 评论对象集合
	 */
	public List<Comment> getCommentBySampleId(CommentVo commentVo);
	/**
	 * 根据样片Id查询评论信息
	 * @param commId 样片Id
	 * @return 评论对象集合
	 */
	public List<Comment> getCommentBySampleId1(CommentVo commentVo);
	/**
	 * 根据评论Id删除评论信息
	 *@param commId 评论Id
	 */
	public void deleteCommentByCommId(Integer commId);
	/**
	 * 批量删除评论信息
	 *@param ids 评论Id数组
	 */
	public void deleteCommentByArray(List<Integer> ids);
}
