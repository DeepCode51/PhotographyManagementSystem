package com.ruanyuan.bus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruanyuan.bas.pojo.Customer;
import com.ruanyuan.bus.dao.OrderDao;
import com.ruanyuan.bus.pojo.Order;
import com.ruanyuan.bus.service.OrderService;
import com.ruanyuan.bus.vo.OrderVo;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.WebUtils;

/**
 * 用户业务类
 * @author
 *
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;

	/**
	 * 查询所有用户
	 */
	@Override
	public DataGridView getOrderAll(OrderVo orderVo) {
		//开启分页
		Page<Object> page = PageHelper.startPage(orderVo.getPage(),orderVo.getLimit());
		//根据条件查询订单
		List<Order> data = orderDao.getAllOrder(orderVo);
		//返回layui封装的数据对象
		return new DataGridView(page.getTotal(), data);
	}

	/**
	 * 删除订单
	 */
	@Override
	public void deleteOrder(Integer orderId) {
		orderDao.deleteOrderByOrderId(orderId);
	}

	/**
	 * 批量删除订单
	 */
	@Override
	public void deleteOrderByArray(List<Integer> orderIds) {
		orderDao.deleteOrderByArray(orderIds);
	}
	/**
	 * 根据客户Id查询所有订单信息
	 */
	@Override
	public DataGridView getOrderByCustomerId(OrderVo orderVo) {
		//如果session中客户不为空，则向包装类中插入客户编号数据
		if(WebUtils.getHttpSession().getAttribute("CUSTOMER")!=null) {
			Customer customer=(Customer) WebUtils.getHttpSession().getAttribute("CUSTOMER");
			//将客户编号放入包装类中
			orderVo.setCustomerId(customer.getCustomerId());
		}
		//开启分页
		Page<Object> page = PageHelper.startPage(orderVo.getPage(),orderVo.getLimit());
		//根据条件查询订单
		List<Order> data = orderDao.getOrderByCustomerId(orderVo.getCustomerId());
		//返回layui封装的数据对象
		return new DataGridView(page.getTotal(), data);
	}

	/**
	 * 根据订单编号查询订单
	 * @param orderId:订单编号
	 * @return:返回订单信息
	 */
	public Order getOrderByOrderId(Integer orderId) {
		return this.orderDao.getOrderByOrderId(orderId);
	}
	/**
	 * Excel导出查询所有订单
	 */
	@Override
	public List<Order> getOrderAll2(OrderVo orderVo) {
		return orderDao.getAllOrder(orderVo);
	}
	/**
	 * 客户编号查询订单1
	 */
	@Override
	public List<Order> getOrderByCustomerId1(OrderVo orderVo) {
		//根据条件查询订单
		return orderDao.getAllOrder(orderVo);
	}

}
