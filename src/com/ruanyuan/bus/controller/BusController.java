package com.ruanyuan.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转控制器
 * @author 
 *
 */
@Controller
@RequestMapping("bus")
public class BusController {

	/**
	 * 跳转到订单管理页
	 */
	@RequestMapping("toOrderManager")
	public String toOrderManager() {
		return "admin/business/order/orderManager";
	}
	/**
	 * 跳转到订单管理页
	 */
	@RequestMapping("toSubscribeManager")
	public String toSubscribeManager() {
		return "admin/business/subscribe/subscribeManager";
	}
	/**
	 * 跳转到审批管理页
	 */
	@RequestMapping("toApprovalManager")
	public String toApprovalManager() {
		return "admin/business/approval/approvalManager";
	}
	
}
