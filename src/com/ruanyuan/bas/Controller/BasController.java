package com.ruanyuan.bas.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @author
* @Data :2020年4月14日 下午3:12:37
*/
@Controller
@RequestMapping("bas")
public class BasController {
	
	@RequestMapping("toCommentManager")
	public String toCommentManager() {
		return "admin/basics/comment/commentManager";
	}
	
	/**
	 * 跳转到客户管理页
	 */
	@RequestMapping("toCustomerManager")
	public String toCustomerManager() {
		return "admin/basics/customer/customerManager";
	}
	
	/**
	 * 跳转到收藏页面
	 */
	@RequestMapping("toCollectManager")
	public String toCollectManager() {
		return "admin/basics/collect/collectManager";
	}
}
