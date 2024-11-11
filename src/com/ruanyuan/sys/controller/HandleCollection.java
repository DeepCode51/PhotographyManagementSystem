package com.ruanyuan.sys.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruanyuan.sys.pojo.User;
import com.ruanyuan.sys.service.HandleService;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.ResultObj;
import com.ruanyuan.sys.vo.HandleVo;

@RestController
@RequestMapping("handle")
public class HandleCollection {

	@Autowired
	private HandleService handleService;
	@Autowired
	private HttpSession session;
	
	/**
	 * 查询操作日志所有
	 * @param loginVo:操作封装类
	 * @return:返回layui格式
	 */
	@RequestMapping("getAllHandle")
	public DataGridView getAllHandle(HandleVo handleVo) {
		return handleService.getAllHandle(handleVo);
	}
	
	/**
	 * 删除操作日志
	 * @param loginId id
	 * @return 错误信息
	 */
	@RequestMapping("deleteHandle")
	public ResultObj deleteHandle(Integer handleId) {
		User user = (User)session.getAttribute("adminUser");
		if(user.getIsApproval().equals(1)) {
			try {
				//删除
				handleService.deleteHandle(handleId);
				return ResultObj.DELETE_SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
				return ResultObj.DELETE_ERROR;
			}
		}else {
			return ResultObj.JURISDICTION_NO;
		}
		
	}
	
	/**
	 * 批量删除操作日志
	 * @param ids:登录编号数组
	 * @return:返回受影响行数
	 */
	@RequestMapping("deleteHandleByArray")
	public ResultObj deleteHandleByArray(Integer[] ids) {
		User user = (User)session.getAttribute("adminUser");
		if(user.getIsApproval().equals(1)) {
			try {
				//删除
				handleService.deleteHandleByArray(ids);
				return ResultObj.DELETE_SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
				return ResultObj.DELETE_ERROR;
			}
		}
		else {
			return ResultObj.JURISDICTION_NO;
		}
	}
	
	
	
}
