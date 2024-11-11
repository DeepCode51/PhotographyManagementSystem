package com.ruanyuan.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.apache.bcel.classfile.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyuan.sys.pojo.Reply;
import com.ruanyuan.sys.pojo.Studioinfor;
import com.ruanyuan.sys.service.ReplyService;
import com.ruanyuan.sys.service.StudioinforService;

/**
 * 页面跳转控制器
 * @author
 *
 */
@Controller
@RequestMapping("sys")
public class SysController {
	@Autowired
	private StudioinforService studioinfoService;
	
	@Autowired
	private ReplyService replyService;
	
	/**
	 * 跳转到用户管理页
	 */
	@RequestMapping("toUserManager")
	public String toUserManager() {
		return "admin/system/user/userManager";
	}	
	
	/**
	 * 跳转到留言管理页
	 */
	@RequestMapping("toMessageManager")
	public String toMessageManager() {
		return "admin/system/message/messageManager";
	}	
	
	/**
	 * 跳转到回复管理页
	 */
	@RequestMapping("toReplyManager")
	public String toReplyManager() {
		return "admin/system/reply/replyManager";
	}	
	
	/**
	 * 跳转到登录日志管理页面
	 */
	@RequestMapping("toLoginManager")
	public String toLoginManager() {
		return "admin/system/log/loginManager";
	}

	/**
	 * 跳转到操作日志管理页面
	 */
	@RequestMapping("toHandlemanager")
	public String toHandlemanager() {
		return "admin/system/log/handleManager";
	}
	
	/**
	 * 跳转到工作室信息管理页面
	 */
	@RequestMapping("toStudioinfor")
	public String toStudioinfor() {
		return "admin/system/studioinfor/studioinforManager";
	}

	/**
	 * 跳转到回复添加页面
	 */
	@RequestMapping("toAddReply")
	public String toAddReply(Integer messageId,HttpServletRequest request,HttpSession session) {
		//Integer messageId=(Integer)request.getAttribute("messageId");
		request.setAttribute("messageId", messageId);
		return "admin/system/message/addReply";
	}
	
	/**
	 * 跳转到修改回复页面
	 */
	@RequestMapping("toUpdateReply")
	public String toUpdateReply(Integer replyId,HttpServletRequest request,HttpSession session) {
		Reply reply=this.replyService.getReplyById(replyId);		
		request.setAttribute("reply", reply);
		return "admin/system/reply/updateReply";
	}
	
	/**
	 * 跳转到工作室信息管理页面
	 */
	@RequestMapping("addorupdateStudioinfor")
	public String addorupdateStudioinfor(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		Studioinfor studioinfor = this.studioinfoService.getAllStudioinforById(1);
		//studioinfor = this.studioinfoService.getAllStudioinforById(1);
		System.out.println("studioinfor"+studioinfor);
		request.setAttribute("studioinfor",studioinfor);
		return "admin/system/studioinfor/updatestudioinforManager";
	}
	
	/**
	 * 跳转到成员阵容管理页面
	 */
	@RequestMapping("updateMembers")
	public String updateMembers(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		Studioinfor studioinfor = this.studioinfoService.getAllStudioinforById(1);
		System.out.println("studioinfor"+studioinfor);
		request.setAttribute("studioinfor",studioinfor);
		return "admin/system/studioinfor/updateMembers";
	}
	
	/**
	 * 跳转到关于我们
	 */
	@RequestMapping("updateAboutUs")
	public String updateAboutUs(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		Studioinfor studioinfor = this.studioinfoService.getAllStudioinforById(1);
		System.out.println("studioinfor"+studioinfor);
		request.setAttribute("studioinfor",studioinfor);
		return "admin/system/studioinfor/updateAboutUs";
	}
	
}
