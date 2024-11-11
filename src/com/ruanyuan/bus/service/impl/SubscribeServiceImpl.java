package com.ruanyuan.bus.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruanyuan.bus.vo.SubscribeVo;
import com.ruanyuan.sam.dao.SampleDao;
import com.ruanyuan.bas.dao.CustomerDao;
import com.ruanyuan.bas.pojo.Customer;
import com.ruanyuan.bus.dao.OrderDao;
import com.ruanyuan.bus.dao.SubscribeDao;
import com.ruanyuan.bus.pojo.Order;
import com.ruanyuan.bus.pojo.Subscribe;
import com.ruanyuan.bus.service.SubscribeService;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.WebUtils;
/**
 * 预约业务类
 * @Data 2020年4月13日 下午3:23:08
 */
@Service
public class SubscribeServiceImpl implements SubscribeService{
	@Autowired
	private SubscribeDao subscribeDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private SampleDao sampleDao;
	
	/**
	 * 查询所有预约信息
	 */
	public DataGridView getAllSubscribe(SubscribeVo subscribeVo) {
		Page<Object> page = PageHelper.startPage(subscribeVo.getPage(), subscribeVo.getLimit());
		//查询预约信息
		List<Subscribe> data = subscribeDao.getAllSubscribe(subscribeVo);
		return new DataGridView(page.getTotal(), data);
	}
	/**
	 * 预约添加
	 */
	public void addSubscribe(SubscribeVo SubscribeVo) {
		//预约添加
		subscribeDao.addSubscribe(SubscribeVo);
	}

	/**
	 * 预约修改
	 */
	public void updateSubscribe(SubscribeVo SubscribeVo) {
		Subscribe subscribeBySubId = subscribeDao.getSubscribeBySubId(SubscribeVo.getSubId());
		if(SubscribeVo.getSubState()==4) {
			sampleDao.updateSampleBySubscribeCountReduceOne(subscribeBySubId.getSampleId());
		}
		//预约修改
		subscribeDao.updateSubscribe(SubscribeVo);
	}

	/**
	 * 根据预约Id查询预约信息
	 * @param subId 预约Id
	 * @return 预约对象
	 */
	public Subscribe getSubscribeBySubId(Integer subId) {
		//根据预约Id查询预约信息
		return subscribeDao.getSubscribeBySubId(subId);
	}
	/**
	 * 根据样片Id查询预约信息
	 * @param commId 样片Id
	 * @return 预约对象
	 */
	public Subscribe getSubscribeBySampleId(Integer sampleId) {
		//根据样片Id查询预约信息
		return subscribeDao.getSubscribeBySampleId(sampleId);
	}
	/**
	 * 根据客户Id查询预约信息
	 */
	public List<Subscribe> getSubscribeByCustomerId(Integer customerId) {
		//根据客户Id查询预约信息
		return subscribeDao.getSubscribeByCustomerId(customerId);
	}
	/**
	 * 根据预约Id删除预约信息
	 *@param subId 预约Id
	 */
	public int deleteSubscribeBySubId(Integer subId) {
		Integer deleteSubscribeBySubId = 0;
		Order orderBySubId = orderDao.getOrderBySubId(subId);
		if(orderBySubId==null) {
			//根据预约Id删除预约信息
			 deleteSubscribeBySubId = subscribeDao.deleteSubscribeBySubId(subId);
		}
		return deleteSubscribeBySubId;
	}
	/**
	 * 批量删除预约信息
	 *@param ids 预约Id数组
	 */
	public int deleteSubscribeByArray(List<Integer>ids) {
		//根据id数组查询订单得信息
		int deleteSubscribeByArray = 0;
		//删除服务器上的图片
		Integer[]  arr1 = new Integer[ids.size()];  
		ids.toArray(arr1);
		List<Order> lo  = orderDao.getOrderBySubIdByArray(arr1);
		if(!(lo!=null&&lo.size()>0)) {
			//根据预约Id批量删除预约信息
			deleteSubscribeByArray = subscribeDao.deleteSubscribeByArray(ids);
		}
		return deleteSubscribeByArray;
		
	}
	/**
	 * 根据客户Id查询预约信息
	 */
	@Override
	public DataGridView getSubscribyCustomerId(SubscribeVo subscribeVo) {
		//如果session中客户不为空，则向包装类中插入客户编号数据
		if(WebUtils.getHttpSession().getAttribute("CUSTOMER")!=null) {
			Customer customer=(Customer) WebUtils.getHttpSession().getAttribute("CUSTOMER");
			//将客户编号放入包装类中
			subscribeVo.setCustomerId(customer.getCustomerId());
		}
		Page<Object> page = PageHelper.startPage(subscribeVo.getPage(), subscribeVo.getLimit());
		//查询预约信息
		List<Subscribe> data = subscribeDao.getSubscribeByCustomerId(subscribeVo.getCustomerId());
		return new DataGridView(page.getTotal(), data);
	}
	/**
	  * 生成订单
	 *@param subscribeVo 预约包装类
	 */
	public int updateSubscribeBygenerateOrder(SubscribeVo subscribeVo,Customer customer) {
		
		Integer bs = 0;
		subscribeVo.setSubState(3);
		//获取成交价格
		Double price = Double.parseDouble(subscribeVo.getPrice());
		//添加订单	
		Order order = new Order();
		//预约编号
		order.setSubId(subscribeVo.getSubId());
		//客户编号
		order.setCustomerId(subscribeVo.getCustomerId());
		Date date = new Date();
		SimpleDateFormat dateFormat_min=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");//设置当前时间的格式，为年-月-日 时-分-秒
		String orderTime = dateFormat_min.format(date);
		order.setOrderTime(orderTime);
		
		String balance = customer.getBalance();
		Double parseInt = Double.parseDouble(balance);
		Double ss = (parseInt-price);
		customer.setBalance(String.valueOf(ss));
		//根据预约Id查询预约信息
		Subscribe subscribeBySubId = subscribeDao.getSubscribeBySubId(subscribeVo.getSubId());
		if(subscribeVo.getSubState()==3) {
			//订单量加一
			sampleDao.updateSampleByOrderCountAddOne(subscribeBySubId.getSampleId());
			//在线预约人数减一
			sampleDao.updateSampleBySubscribeCountReduceOne(subscribeBySubId.getSampleId());
		}
		if(ss>0) {
			//修改预约状态
			subscribeDao.updateSubscribe(subscribeVo);
			//添加到订单
			orderDao.insertOrder(order);
			//修改用户价格
			customerDao.updateCustomer(customer);
			bs=1;
		}
		return bs;
		
	}
	/**
	 * 根据客户Id和样片Id查询预约信息
	 */
	@Override
	public List<Subscribe> getSubscribeByCustomerIdAndSampleId(SubscribeVo subscribeVo) {
		//根据条件查新样片数据
		List<Subscribe> data = this.subscribeDao.getSubscribeByCustomerIdAndSampleId(subscribeVo);
		return data;
	}

}
