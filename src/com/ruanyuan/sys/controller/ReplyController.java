package com.ruanyuan.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruanyuan.sam.vo.TypeVo;
import com.ruanyuan.sys.pojo.Reply;
import com.ruanyuan.sys.service.ReplyService;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.ResultObj;
import com.ruanyuan.sys.vo.ReplyVo;

/**
* 回复控制层
* @author
* @Data :2020年4月16日 上午12:20:49
*/
@RestController
@RequestMapping("reply")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	/**
	 * 查询回复数据
	 * @param replyVo 回复包装类
	 * @return
	 */
	@RequestMapping("getAllReply")
	public DataGridView getAllReply(ReplyVo replyVo) {
		return replyService.getAllReply(replyVo);
	}
	
	/**
	 * 根据回复id查询回复信息
	 * @param replyId 回复id
	 * @return 返回回复对象
	 */
	@RequestMapping("getReplyById")
	public Reply getReplyById(Integer replyId) {
		return replyService.getReplyById(replyId);
	}	
	
	/**
	 * 添加回复
	 * @param replyVo 回复包装类
	 * @return
	 */
	@RequestMapping("addReply")
	public ResultObj addReply(ReplyVo replyVo,String replyContent) {
		try {
			//添加回复
			replyService.addReply(replyVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	/**
	 * 根据留言id查询回复信息
	 * @param messageId 留言id
	 */
	@RequestMapping("getReplyByMessageId")
	public ResultObj getReplyByMessageId(Integer messageId) {
		try {
			Reply replyByMessageId = replyService.getReplyByMessageId(messageId);			
			if(replyByMessageId!=null) {
				return ResultObj.MESSAGE_HAS_REPLY;
			}else {
				return ResultObj.CODE_TWO;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultObj.CODE_TWO;		 
	}
	
	/**
	 * 根据留言id查询回复信息
	 * @param messageId 留言id
	 */
	@RequestMapping("getReplyByMessageIds")
	public Reply getReplyByMessageIds(Integer messageId) {
		/*
		 * try { Reply replyByMessageId = replyService.getReplyByMessageId(messageId);
		 * System.out.println("-----------------------"+replyByMessageId);
		 * 
		 * if(replyByMessageId==null) { return ResultObj.MESSAGE_NO_REPLY; }else {
		 * return ResultObj.CODE_TWO; } } catch (Exception e) { e.printStackTrace(); }
		 * return ResultObj.CODE_TWO;
		 */	 
		Reply replyByMessageId = replyService.getReplyByMessageId(messageId);
		return replyByMessageId;
	}
	
	/**
	 * 修改回复
	 *@param replyVo 回复包装类
	 *@return 返回状态信息
	 */
	@RequestMapping("updateReply")
	public ResultObj updateReply(ReplyVo replyVo) {
		try {
			//修改回复
			replyService.updateReply(replyVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	
	/**
	 * 根据回复id删除回复
	 *@param replyId 回复id
	 *@return 返回状态信息
	 */
	@RequestMapping("deleteReplyById")
	public ResultObj deleteReplyById(Integer replyId) {
		try {
			//删除回复
			replyService.deleteReplyById(replyId);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	/**
	 * 根据留言id查询回复信息
	 * @param messageId 留言id
	 * @return  回复信息集合
	 */
	@RequestMapping("getPcReply")
	public DataGridView getPcReply(Integer messageId) {
		ReplyVo	replyVo = new  ReplyVo();
		replyVo.setMessageId(messageId);
		return replyService.getPcReply(replyVo);
	}
	/**
	 * 批量删除回复
	 * @param ids
	 * @return
	 */
	@RequestMapping("deleteReplyByArray")
	public ResultObj deleteReplyByArray(@Param("ids")Integer[] ids) {
		List<Integer> idlist = new ArrayList<Integer>();
		for (int i = 0; i < ids.length; i++) {
			idlist.add(ids[i]);
		}
		try {
			//批量删除回复
			replyService.deleteReplyByArray(idlist);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
}
