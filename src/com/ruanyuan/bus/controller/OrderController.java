package com.ruanyuan.bus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruanyuan.bus.pojo.Order;
import com.ruanyuan.bus.service.OrderService;
import com.ruanyuan.bus.vo.OrderVo;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.ResultObj;

@Controller
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	/**
	 * 查询所有订单
	 */
	@RequestMapping("getOrderAll")
	@ResponseBody
	public DataGridView getOrderAll(OrderVo orderVo) {
		return orderService.getOrderAll(orderVo);
	}	
	/**
	 * 根据客户Id查询订单数据
	 * @param OrderVo 订单包装类
	 * @return
	 */
	@RequestMapping("getPcOrderByCustomerId")
	@ResponseBody
	public DataGridView getPcOrderByCustomerId(OrderVo orderVo) {
		return orderService.getOrderByCustomerId(orderVo);
	}
	/**
	 * 根据客户Id查询订单数据1
	 * @param OrderVo 订单包装类
	 * @return
	 */
	@RequestMapping("getPcOrderByCustomerId1")
	@ResponseBody
	public String getPcOrderByCustomerId1(OrderVo orderVo,String sampleId) {
		List<Order> list =  orderService.getOrderByCustomerId1(orderVo);
		for (Order order : list) {
			if(order.getSubscribe().getSampleId() == Integer.parseInt(sampleId)) {
				return "1";
			}
		}
		return "0";
	}
	/**
	 * 删除订单
	 */
	@RequestMapping("deleteOrder")
	@ResponseBody
	public ResultObj deleteOrder(Integer orderId) {
		try {
			orderService.deleteOrder(orderId);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	/**
	 * 批量删除订单
	 */
	@RequestMapping("deleteOrderByArray")
	@ResponseBody
	public ResultObj deleteOrderByArray(Integer[] orderIds) {
		List<Integer> idlist = new ArrayList<Integer>();
		for (int i = 0; i < orderIds.length; i++) {
			idlist.add(orderIds[i]);
		}
		try {
			orderService.deleteOrderByArray(idlist);;
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
}
