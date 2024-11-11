package com.ruanyuan.bas.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruanyuan.bas.pojo.Comment;
import com.ruanyuan.bas.service.CommentService;
import com.ruanyuan.bas.vo.CommentVo;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.ResultObj;


/**
 * 	评论控制类
 * @Data 2020年4月14日 下午1:47:41
 */
@RestController
@RequestMapping("comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	
	/**
	 *  查询所有评论信息
	 * @param commentVo 评论包装类
	 * @return 返回评论信息
	 */
	@RequestMapping("getAllComment")
	public DataGridView getAllComment(CommentVo commentVo) {
		return commentService.getAllComment(commentVo);
	}
	
	/**
	 * 查询所有评论信息
	 * @param commentVo 评论包装类
	 * @return
	 */
	@RequestMapping("getAllComment1")
	public List<Comment> getAllComment1(CommentVo commentVo) {
		return commentService.getAllComment1(commentVo);
	}
	
	
	/**
	 * 根据评论id查询评论信息
	 * @param commId 评论id
	 * @return json格式评论信息
	 */
	@RequestMapping("getCommentByCommId")
	public Comment getCommentByCommId(Integer commId) {
		return commentService.getCommentByCommId(commId);
	}
	
	
	/**
	 * 添加评论
	 * @param commentVo 评论包装类
	 * @return
	 */
	@RequestMapping("addComment")
	public ResultObj addComment(CommentVo commentVo) {
		try {
			//添加评论
			commentService.addComment(commentVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	/**
	 * 前台添加评论
	 * @param commentVo 评论包装类
	 * @return
	 */
	@RequestMapping("addPcComment")
	public ResultObj addPcComment(CommentVo commentVo) {
		try {
			Date date = new Date();
			SimpleDateFormat dateFormat_min=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");//设置当前时间的格式，为年-月-日 时-分-秒
			String commTime = dateFormat_min.format(date);
			commentVo.setCommTime(commTime);
			//添加评论
			commentService.addComment(commentVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	/**
	 * 修改评论
	 * @param commentVo 评论包装类
	 * @return
	 */
	@RequestMapping("updateComment")
	public ResultObj updateComment(CommentVo commentVo) {
		try {
			//修改评论
			commentService.updateComment(commentVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	
	/**
	 * 根据评论ID删除评论
	 * @param CommId 评论ID
	 * @return
	 */
	@RequestMapping("deleteCommentByCommId")
	public ResultObj deleteCommentByCommId(Integer commId) {
		try {
			//删除评论
			commentService.deleteCommentByCommId(commId);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	/**
	 * 批量删除评论
	 * @param ids
	 * @return
	 */
	@RequestMapping("deleteCommentByArray")
	public ResultObj deleteCommentByArray(@Param("ids")Integer[] ids) {
		List<Integer> idlist = new ArrayList<Integer>();
		for (int i = 0; i < ids.length; i++) {
			idlist.add(ids[i]);
		}
		try {
			//批量删除评论
			commentService.deleteCommentByArray(idlist);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	

	/**
	 * 根据样片Id获取评论信息
	 * 
	 * @return json格式类别信息
	 */
	@RequestMapping("getPcCommentBysampleId")
	public DataGridView getPcCommentBysampleId(CommentVo commentVo) {
		Page<Object> page = PageHelper.startPage(commentVo.getPage(), commentVo.getLimit());
		//得到该样片的评论信息
		List<Comment> commentList = commentService.getCommentBySampleId1(commentVo);
		return new DataGridView(page.getTotal(), commentList);
	}
	
	
}
