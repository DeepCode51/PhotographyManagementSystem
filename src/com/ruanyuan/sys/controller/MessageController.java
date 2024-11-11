package com.ruanyuan.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruanyuan.sys.pojo.Message;
import com.ruanyuan.sys.service.MessageService;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.ResultObj;
import com.ruanyuan.sys.vo.MessageVo;

/**
 * 留言控制类
* @author
* @Data :2020年4月15日 下午9:27:09
*/
@RestController
@RequestMapping("message")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	/**
	 * 查询留言数据
	 * @param messageVo 留言包装类
	 * @return
	 */
	@RequestMapping("getAllMessage")
	public DataGridView getAllMessage(MessageVo messageVo) {
		return messageService.getAllMessage(messageVo);
	}
	
	/**
	 * 根据留言id查询留言信息
	 * @param messageId 留言id
	 * @return
	 */
	@RequestMapping("getMessageById")
	public Message getMessageById(Integer messageId) {
		return messageService.getMessageById(messageId);
	}
	
	/**
	 * 添加留言
	 * @param messageVo 留言包装类
	 * @return
	 */
	@RequestMapping("addMessage")
	public ResultObj addMessage(MessageVo messageVo) {
		try {
			//添加类别
			messageService.addMessage(messageVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	
	
	/**
	 * 修改类别
	 * @param messageVo 留言包装类
	 * @return
	 */
	@RequestMapping("updateMessage")
	public ResultObj updateMessage(MessageVo messageVo) {
		try {
			//修改类别
			messageService.updateMessage(messageVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	
	/**
	 * 根据留言id删除留言
	 * @param messageId 留言id
	 * @return
	 */
	@RequestMapping("deleteMessageById")
	public ResultObj deleteMessageById(Integer messageId) {
		
		try {
			//删除留言
			Integer deleteSampleBySampleId = messageService.deleteMessageById(messageId);
			if(deleteSampleBySampleId!=null) {
				return ResultObj.DELETE_SUCCESS;
			}else {
				return ResultObj.DELETE_MESSAGE_NO;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	/**
	 * 批量删除留言
	 * @param messageIds
	 * @return
	 */
	@RequestMapping("deleteMessageByArray")
	public ResultObj deleteMessageByArray(@Param("ids")Integer[] ids){
		List<Integer> idlist = new ArrayList<Integer>();
		for (int i = 0; i < ids.length; i++) {
			idlist.add(ids[i]);
		}
		try {
			//批量删除留言			
			for (Integer integer : idlist) {
				System.out.println("我要删除的是："+idlist);
			}
			int deleteMessageByArray = messageService.deleteMessageByArray(idlist);
			if(deleteMessageByArray>0) {
				return ResultObj.DELETE_SUCCESS;
			}else {
				return ResultObj.DELETE_MESSAGE_NO;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	/**
	 * 查询留言数据(pc)
	 * @param messageVo 留言包装类
	 * @return
	 */
	@RequestMapping("getPcMessage")
	public DataGridView getPcMessage() {
		DataGridView  dl = messageService.getPcLimitMessage();
		return dl;
	}
	/**
	 * 添加留言(pc)
	 * @param messageVo 留言包装类
	 * @return
	 */
	@RequestMapping("addPcMessage")
	public ResultObj addPcMessage(MessageVo messageVo) {
		try {
			messageService.addMessage(messageVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
}
