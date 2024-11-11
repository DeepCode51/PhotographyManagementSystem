package com.ruanyuan.bus.service;

import java.util.List;

import com.ruanyuan.bus.pojo.Order;
import com.ruanyuan.bus.vo.OrderVo;
import com.ruanyuan.sys.utils.DataGridView;

/**
 * 订单业务接口
 * @author
 *
 */
public interface OrderService {
	
	/**
	 * 查询所有订单
	 * @param orderVo 订单包装类
	 * @return 返回layui封装格式数据
	 */
	public DataGridView getOrderAll(OrderVo orderVo);
	
	/**
	 * 根据客户Id查询所有订单信息
	 * @return 返回订单集合
	 */
	public DataGridView getOrderByCustomerId(OrderVo orderVo);
	/**
	 * 根据客户Id查询所有订单信息1
	 * @return 返回订单集合
	 */
	public List<Order> getOrderByCustomerId1(OrderVo orderVo);
	/**
	 * 删除订单
	 * @param orderId 订单id
	 */
	public void deleteOrder(Integer orderId);
	
	/**
	 * 批量删除订单
	 * @param orderIds 订单id数组
	 */
	public void deleteOrderByArray(List<Integer> orderIds);
	
	/**
	 * 根据订单编号查询订单
	 * @param orderId:订单编号
	 * @return:返回订单信息
	 */
	public Order getOrderByOrderId(Integer orderId);
	
	/**
	 * Excel导出查询所有订单
	 * @param orderVo 订单包装类
	 */
	public List<Order> getOrderAll2(OrderVo orderVo);
}
