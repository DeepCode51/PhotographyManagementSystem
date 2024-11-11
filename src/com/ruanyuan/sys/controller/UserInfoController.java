package com.ruanyuan.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyuan.sys.dao.UserDao;
import com.ruanyuan.sys.pojo.User;
import com.ruanyuan.sys.service.UserService;
import com.ruanyuan.sys.utils.ResultObj;
import com.ruanyuan.sys.vo.UserVo;

/**
 * 用户个人信息控制层
 * @date 2020年4月13日
 */
@Controller
@RequestMapping("userinfo")
public class UserInfoController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserDao userDao;
	/**
	 * 跳转到用户个人信息页面
	 * @return
	 */
	@RequestMapping("toUserInfo")
	public String toUserInfo(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		//获取登陆者信息
		User user = (User)session.getAttribute("adminUser");
		User user2 = this.userService.getUserByUserId(user.getUserId());
		request.setAttribute("loginUser", user2);
		return "admin/system/main/userInfo";
	}
	/**
	 * 跳转到修改密码页面
	 * @return
	 */
	@RequestMapping("toChangePwd")
	public String toChangePwd(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		//获取登陆者信息
		User user = (User)session.getAttribute("adminUser");
		request.setAttribute("loginUser", user);
		return "admin/system/main/changePwd";
	}
	/**
	 * 修改密码操作
	 * @param userId
	 * @param pwd
	 * @return
	 */
	@RequestMapping("Change")
	public ResultObj changePwd(UserVo userVo) {
		int count = userDao.updateUser(userVo);
		if(count>0) {
			return ResultObj.UPDATE_SUCCESS;
		}else {
			return ResultObj.UPDATE_ERROR;
		}
		
	}
	
	/**
	 * 退出登录
	 * @param session
	 * @return
	 */
	@RequestMapping("outLogin")
	public String outLogin(HttpSession session) {
		session.removeAttribute("adminUser");
		return "admin/system/main/login";
	}
}
