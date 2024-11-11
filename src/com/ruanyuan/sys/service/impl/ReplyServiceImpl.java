package com.ruanyuan.sys.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruanyuan.sys.dao.ReplyDao;
import com.ruanyuan.sys.pojo.Reply;
import com.ruanyuan.sys.pojo.User;
import com.ruanyuan.sys.service.ReplyService;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.WebUtils;
import com.ruanyuan.sys.vo.ReplyVo;

/**
 * 回复业务类
* @author
* @Data :2020年4月16日 上午12:11:35
*/
@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDao replyDao;
	
	/**
	 * 查询全部回复
	 */
	@Override
	public DataGridView getAllReply(ReplyVo replyVo) {
		// TODO Auto-generated method stub
		//开启分页
		Page<Object> page  = PageHelper.startPage(replyVo.getPage(),replyVo.getLimit());
		//根据条件查询回复数据
		List<Reply> data = this.replyDao.getAllReply(replyVo);
		//返回layui封装得 数据对象 
		return new DataGridView(page.getTotal(),data);
	}

	/**
	 * 根据回复ID查询留言信息
	 */
	@Override
	public Reply getReplyById(Integer replyId) {
		// TODO Auto-generated method stub
		return replyDao.getReplyById(replyId);
	}

	/**
	 * 添加回复
	 */
	@Override
	public void addReply(ReplyVo replyVo){
		// TODO Auto-generated method stub
		//添加时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = df.format(new Date());
		replyVo.setReplyTime(format);
		//获取user
		User user = (User) WebUtils.getHttpSession().getAttribute("adminUser");
		if(user!=null) {
			replyVo.setUserId(user.getUserId());
		}
		replyDao.addReply(replyVo);
	}

	/**
	 * 修改回复
	 */
	@Override
	public void updateReply(ReplyVo replyVo) {
		// TODO Auto-generated method stub
		//添加时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = df.format(new Date());
		replyVo.setReplyTime(format);
		replyDao.updateReply(replyVo);
	}

	/**
	 * 根据回复id删除回复信息
	 */
	@Override
	public void deleteReplyById(Integer replyId) {
		// TODO Auto-generated method stub
		replyDao.deleteReplyById(replyId);
	}

	/**
	 * 批量删除回复
	 */
	@Override
	public void deleteReplyByArray(List<Integer> replyIds) {
		// TODO Auto-generated method stub
		replyDao.deleteReplyByArray(replyIds);
	}
	/**
	 * 根据回复id查询回复信息
	 */
	@Override
	public DataGridView getPcReply(ReplyVo replyVo) {
		// TODO Auto-generated method stub
		//根据条件查询回复数据
		List<Reply> data = this.replyDao.getAllReply(replyVo);
		//返回layui封装得 数据对象 
		return new DataGridView(data);
	}
	/**
	 * 根据留言Id查询回复信息
	 *@param messageId 留言编号
	 *@return
	 */
	public Reply getReplyByMessageId(Integer messageId) {
		return replyDao.getReplyByMessageId(messageId);
	}
}
