package com.ruanyuan.sys.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruanyuan.bas.pojo.Comment;
import com.ruanyuan.sys.dao.MessageDao;
import com.ruanyuan.sys.dao.ReplyDao;
import com.ruanyuan.sys.pojo.Message;
import com.ruanyuan.sys.pojo.Reply;
import com.ruanyuan.sys.service.MessageService;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.vo.MessageVo;

/**
 * 留言表业务层
* @author
* @Data :2020年4月15日 下午8:21:17
*/
@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private ReplyDao replyDao;
	/**
	 * 查询所有
	 */
	@Override
	public DataGridView getAllMessage(MessageVo messageVo) {
		// TODO Auto-generated method stub
		//开启分页
		Page<Object> page  = PageHelper.startPage(messageVo.getPage(),messageVo.getLimit());
		//根据条件查询样片数据
		List<Message> data=this.messageDao.getAllMessage(messageVo);
		//返回layui封装得 数据对象 
		return new DataGridView(page.getTotal(),data);
	}

	/**
	 * 根据留言id查询留言表
	 */
	@Override
	public Message getMessageById(Integer messageId) {
		// TODO Auto-generated method stub
		return messageDao.getMessageById(messageId);
	}

	/**
	 * 添加留言
	 */
	@Override
	public void addMessage(MessageVo messageVo) {
		// TODO Auto-generated method stub
		//添加时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = df.format(new Date());
		messageVo.setMessageTime(format);
		messageDao.addMessage(messageVo);
	}

	/**
	 * 修改留言
	 */
	@Override
	public void updateMessage(MessageVo messageVo) {
		// TODO Auto-generated method stub
		//添加时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = df.format(new Date());
		messageVo.setMessageTime(format);
		messageDao.updateMessage(messageVo);
	}

	/**
	 * 根据留言ID删除留言
	 */
	@Override
	public Integer deleteMessageById(Integer messageId) {
		// TODO Auto-generated method stub
		Integer deleteMessageById=null;
		//根据留言ID查询留言信息
		List<Reply> listReply=replyDao.getReplyMessageId(messageId);
		//判断删除
		if(!(listReply!=null&&listReply.size()>0)) {
			deleteMessageById=messageDao.deleteMessageById(messageId);
			return deleteMessageById;
		}else {
			return deleteMessageById;
		}
		
	}

	/**
	 * 批量删除留言
	 */
	@Override
	public int deleteMessageByArray(List<Integer> messageIds) {
		// TODO Auto-generated method stub
		Integer count = 0;
		for (Integer integer : messageIds) {
			List<Reply> listreply=replyDao.getReplyMessageId(integer);
			count += listreply.size();
		}
		if(count==0) {
			return messageDao.deleteMessageByArray(messageIds);
		}
		return 0;

		
	}
	/**
	 * 查询留言(pc)
	 * @param messageVo 留言包装类
	 * @return layui封装数据
	 */
	@Override
	public DataGridView getPcLimitMessage() {
		List<Message> data=this.messageDao.getPcMessage();
		return new DataGridView(data);
	}
}
