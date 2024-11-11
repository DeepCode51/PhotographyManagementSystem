package com.ruanyuan.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruanyuan.sys.service.LoginService;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.ResultObj;
import com.ruanyuan.sys.vo.LoginVo;
/**
 * 登录日志控制层
 * @date 2020年4月17日
 */
@RestController
@RequestMapping("log")
public class LogController {

	@Autowired
	private LoginService loginService;

	
	/**
	 * 查询登录日志所有
	 * @param loginVo:登录封装类
	 * @return:返回layui格式
	 */
	@RequestMapping("getAllLogin")
	public DataGridView getAllLogin(LoginVo loginVo) {
		return loginService.getAllLogin(loginVo);
	}
	
	/**
	 * 删除登录者信息
	 * @param loginId id
	 * @return 错误信息
	 */
	@RequestMapping("deleteLogin")
	public ResultObj deleteLogin(Integer loginId) {
		try {
			//删除
			loginService.deleteLogin(loginId);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	/**
	 * 批量删除登录日志
	 * @param ids:登录编号数组
	 * @return:返回受影响行数
	 */
	@RequestMapping("deleteLoginByArray")
	public ResultObj deleteLoginByArray(Integer[] ids) {
		try {
			//删除
			loginService.deleteLoginByArray(ids);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
}
