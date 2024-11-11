package com.ruanyuan.sys.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ruanyuan.sys.pojo.User;

/**
* @author
* @Data :2020年4月19日 下午6:30:34
*/
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		// 检查每个到来的请求对应的session域中是否有登录标识
		User user = (User) request.getSession().getAttribute("adminUser");
		//获取请求路径
		String requestURI=request.getRequestURI();
		System.out.println(requestURI+"...."+user);
		//前台页面不拦截
		if(requestURI.contains("pc")) {
			return true;
		}
		if (null == user) {
			// 未登录，重定向到登录页
			response.sendRedirect(request.getContextPath()+"/login/toAdminLogin");
			return false;
		}
		System.out.println("当前用户已登录，登录的用户名为： " + user.getRealName());
		return true;
	}

}
