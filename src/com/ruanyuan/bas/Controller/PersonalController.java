package com.ruanyuan.bas.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruanyuan.sys.utils.ResultObj;
import com.ruanyuan.sys.utils.WebUtils;


/**
 * 个人中心控制层
 *@Date 2020年04月14日 16:24
 */
@RestController
@RequestMapping("personal")
public class PersonalController {
	/**
	 * 清除客户信息
	 * @return 是否清除成功
	 */
	@RequestMapping("clearSession")
	public ResultObj clearSession() {
		try {
			//清除客户信息
			WebUtils.getHttpSession().removeAttribute("CUSTOMER");
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
}
