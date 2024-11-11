package com.ruanyuan.sys.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyuan.sys.constast.SysConstast;
import com.ruanyuan.sys.pojo.Login;
import com.ruanyuan.sys.pojo.User;
import com.ruanyuan.sys.service.LoginService;
import com.ruanyuan.sys.service.UserService;
import com.ruanyuan.sys.utils.WebUtils;
import com.ruanyuan.sys.vo.LoginVo;
import com.ruanyuan.sys.vo.UserVo;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;

/**
 *登录控制类 
 * @Data 2020年4月12日 下午2:30:32
 */
@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	private UserService userService;
	@Autowired
	private LoginService loginService;
	/**
	 * 跳转到后台登录页面
	 */
	@RequestMapping("toAdminLogin")
	public String toAdminLogin() {
		return "admin/system/main/login";
	}
	
	/**
	 * 跳转到后台登录页面
	 */
	@RequestMapping("toIndex")
	public String toIndex() {
		
		return "admin/system/main/index";
	}
	
	/**
	 * 登陆方法
	 */
	@RequestMapping("adminLogin")
	public String adminLogin(UserVo userVo, Model model) {
		String code=WebUtils.getHttpSession().getAttribute("code").toString();
		if(userVo.getCode().equals(code)) {
			//调用登录的方法
			User user = this.userService.login(userVo);
			if (null != user) {
				// 放到session
				WebUtils.getHttpSession().setAttribute("adminUser", user);
				// 记录登陆日志
				LoginVo login = new LoginVo();
				//登录者用户编号
				login.setUserId(user.getUserId());
				//登录时间
				SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				login.setLoginTime(date.format(new Date()));
				//获取登录IP
				String ip = "";
				try {
					ip = InetAddress.getLocalHost().getHostAddress();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    login.setLoginIp(ip);
			    loginService.addLogin(login);
				return "redirect:toIndex";
			} else {
				//用户名或密码不正确
				model.addAttribute("error", SysConstast.USER_LOGIN_ERROR_MSG);
				//跳转到后台登录页
				return "admin/system/main/login";
			}
		}else {
			//验证码不正确
			model.addAttribute("error", SysConstast.USER_LOGIN_CODE_ERROR_MSG);
			//跳转到后台登录页
			return "admin/system/main/login";
		}
		
	}
	/**
	 * 得到登陆验证码
	 * @throws IOException 
	 */
	@RequestMapping("getCode")
	public void getCode(HttpServletResponse response,HttpSession session) throws IOException {
		// 定义图形验证码的长和宽
		LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116, 36,4,5);
		session.setAttribute("code", lineCaptcha.getCode());
		ServletOutputStream outputStream = response.getOutputStream();
		ImageIO.write(lineCaptcha.getImage(), "JPEG", outputStream);
	}
	
	
}
