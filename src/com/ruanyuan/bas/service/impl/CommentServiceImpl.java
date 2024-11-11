package com.ruanyuan.bas.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruanyuan.bas.dao.CommentDao;
import com.ruanyuan.bas.pojo.Comment;
import com.ruanyuan.bas.service.CommentService;
import com.ruanyuan.bas.vo.CommentVo;
import com.ruanyuan.sys.utils.DataGridView;

/**
 * 评论业务类
 * @Data 2020年4月13日 上午11:52:36
 */
@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentDao commentDao;
	/**
	 * 查询所有的评论信息
	 */
	public DataGridView getAllComment(CommentVo commentVo) {
		Page<Object> page = PageHelper.startPage(commentVo.getPage(), commentVo.getLimit());
		//查询评论信息
		List<Comment> data = commentDao.getAllComment(commentVo);
		return new DataGridView(page.getTotal(), data);
	}

	/**
	 * 评论添加
	 */
	public void addComment(CommentVo commentVo) {
		//添加时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = df.format(new Date());
		commentVo.setCommTime(format);
		//评论添加
		commentDao.addComment(commentVo);
	}

	/**
	 * 评论修改
	 */
	public void updateComment(CommentVo commentVo) {
		//添加时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = df.format(new Date());
		commentVo.setCommTime(format);
		//评论修改
		commentDao.updateComment(commentVo);
	}

	/**
	 * 根据评论Id查询评论信息
	 * @param commId 评论Id
	 * @return 评论对象
	 */
	public Comment getCommentByCommId(Integer commId) {
		//根据评论Id查询评论信息
		return commentDao.getCommentByCommId(commId);
	}

	/**
	 * 根据样片Id查询评论信息
	 * @param commId 样片Id
	 * @return 评论对象
	 */
	public List<Comment> getCommentBySampleId(CommentVo commentVo) {
		//根据样片Id查询评论信息
		return commentDao.getCommentBySampleId(commentVo);
	}
	/**
	 * 根据样片Id查询评论信息1
	 * @param commId 样片Id
	 * @return 评论对象
	 */
	@Override
	public List<Comment> getCommentBySampleId1(CommentVo commentVo) {
		//根据样片Id查询评论信息
		return commentDao.getCommentBySampleId1(commentVo);
	}
	/**
	 * 根据评论Id删除评论信息
	 *@param commId 评论Id
	 */
	public void deleteCommentByCommId(Integer commId) {
		//根据评论Id删除评论信息
		commentDao.deleteCommentByCommId(commId);
	}
	/**
	 * 批量删除评论信息
	 *@param ids 评论Id数组
	 */
	public void deleteCommentByArray(List<Integer> ids) {
		//根据评论Id批量删除评论信息
		commentDao.deleteCommentByArray(ids);
	}

	@Override
	public List<Comment> getAllComment1(CommentVo commentVo) {
		// TODO Auto-generated method stub
		return commentDao.getAllComment(commentVo);
	}


}
