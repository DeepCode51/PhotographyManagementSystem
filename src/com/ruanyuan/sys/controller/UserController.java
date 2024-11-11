package com.ruanyuan.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruanyuan.sys.pojo.User;
import com.ruanyuan.sys.service.UserService;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.ResultObj;
import com.ruanyuan.sys.utils.WebUtils;
import com.ruanyuan.sys.vo.UserVo;

import cn.hutool.crypto.SecureUtil;

/**
 * 用户控制类
 * @author 
 *
 */
@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 查询所有用户
	 */
	@RequestMapping("getUserAll")
	public DataGridView getUserAll(UserVo userVo) {
		return userService.getUserAll(userVo);
	}
	
	/**
	 * 添加用户
	 */
	@RequestMapping("addUser")
	public ResultObj addUser(UserVo userVo) {
		try {
			userService.addUser(userVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	
	/**
	 * 修改用户
	 */
	@RequestMapping("updateUser")
	public ResultObj updateUser(UserVo userVo) {
		try {
			userService.updateUser(userVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	
	/**
	 * 删除用户
	 */
	@RequestMapping("deleteUser")
	public ResultObj deleteUser(Integer userId) {
		try {
			if(userService.deleteUser(userId)==1) {
				return ResultObj.DELETE_SUCCESS;
			}else {
				return ResultObj.DELETE_USER_NO;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	/**
	 * 批量删除用户
	 */
	@RequestMapping("deleteUserByArray")
	public ResultObj deleteUserByArray(Integer[] userIds) {
		List<Integer> idlist = new ArrayList<Integer>();
		for (int i = 0; i < userIds.length; i++) {
			idlist.add(userIds[i]);
		}
		try {
			if(userService.deleteUserByArray(idlist)>0) {
				return ResultObj.DELETE_SUCCESS;
			}else {
				return ResultObj.DELETE_USER_NO;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	/**
	 * 重置密码
	 */
	@RequestMapping("updateUserPwd")
	public ResultObj updateUserPwd(Integer userId) {
		try {
			userService.updateUserPwd(userId);
			return ResultObj.RESET_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.RESET_ERROR;
		}
	}
	
	/**
	 * 账号查重
	 */
	@RequestMapping("getUserLoginName")
	public Boolean getUserLoginName(String loginName) {
		if(userService.getUserLoginName(loginName)==null) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 查询所有职位
	 */
	@RequestMapping("getUserByJob")
	public List<String> getUserByJob() {
		return userService.getUserByJob();
	}
	
	/**
	 * 检验输入框密码是否与原密码相等
	 * @param pwd 输入的新密码
	 * @return 是否相等
	 */
	@PostMapping("pwdToMd5")
	public Integer pwdToMd5(String pwd) {
		User user = new User();
		String pwd2=SecureUtil.md5(pwd);
		if(WebUtils.getHttpSession().getAttribute("adminUser")!=null) {
			user = (User)WebUtils.getHttpSession().getAttribute("adminUser");
		}
		User user2 = this.userService.getUserByUserId(user.getUserId());
		//如果密码是正确的则返回1 否则返回2
		if(pwd2.equals(user2.getPassword())) {
			return 1;
		}else {
			return 2;
		}
	}
	
	/**
	 * 修改用户信息和密码
	 */
	@RequestMapping("updateUserById")
	public ResultObj updateUserById(UserVo userVo) {
		if(userVo.getPassword() !="" && userVo.getPassword()!=null) {
		String pwd = userVo.getPassword();
		//调用hutool工具加密
		String password=SecureUtil.md5(pwd);
		userVo.setPassword(password);
		}
		try {
			userService.updateUserById(userVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	/**
	 * 查询所有用户pc
	 */
	@RequestMapping("getPcUserAll")
	public DataGridView gePctUserAll(UserVo userVo) {
		return userService.getPcUserAll(userVo);
	}
	/**
	 * 查询是否有审批权限
	 */
	@RequestMapping("isApproval")
	public Integer isApproval() {
		User user = (User) WebUtils.getHttpSession().getAttribute("adminUser");
		return user.getIsApproval();
	}
}
