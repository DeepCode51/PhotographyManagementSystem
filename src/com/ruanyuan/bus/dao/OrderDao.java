package com.ruanyuan.bus.dao;

import java.util.List;

import com.ruanyuan.bus.pojo.Order;

/**
 * 订单数据访问接口 
* @author
* @Data :2020年4月13日 下午3:20:11
*/
public interface OrderDao {
	
	/**
	 * 查询所有订单信息
	 * @return 返回订单集合
	 */
	public List<Order> getAllOrder(Order order);
	
	/**
	 * 根据客户Id查询所有订单信息
	 * @return 返回订单集合
	 */
	public List<Order> getOrderByCustomerId(Integer customerId);
	/**
	 * 添加订单的信息
	 * @param orders 订单对象
	 * @return 受影响得行数
	 */
	public int insertOrder(Order order);
	
	/**
	 * 修改订单的信息
	 * @param orders 订单对象
	 * @return 受影响得行数
	 */
	public int updataOrderByOrderId(Order order);	
	
	/**
	 * 根据订单id删除信息
	 * @param orderId 订单ID
	 * @return 受影响得行数
	 */
	public int deleteOrderByOrderId(Integer orderId);
	
	/**
	 * 批量删除订单
	 * @param orderIds 订单id数组
	 * @return 返回影响的行数
	 */
	public int deleteOrderByArray(List<Integer> orderIds);
	/**
	 * 根据预约Id查询订单
	 *@param subId
	 */
	public Order getOrderBySubId(Integer subId);
	/**
	 * 根据Ids 查询订单信息 
	 *@param ids
	 *@return
	 */
	public List<Order> getOrderBySubIdByArray(Integer[] ids);
	/**
	 * 根据订单编号查询订单
	 * @param orderId:订单编号
	 * @return:返回订单信息
	 */
	public Order getOrderByOrderId(Integer orderId);
}
