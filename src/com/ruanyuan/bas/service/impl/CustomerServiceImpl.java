package com.ruanyuan.bas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruanyuan.bas.dao.CollectDao;
import com.ruanyuan.bas.dao.CommentDao;
import com.ruanyuan.bas.dao.CustomerDao;
import com.ruanyuan.bas.pojo.Collect;
import com.ruanyuan.bas.pojo.Comment;
import com.ruanyuan.bas.pojo.Customer;
import com.ruanyuan.bas.service.CustomerService;
import com.ruanyuan.bas.vo.CustomerVo;
import com.ruanyuan.bus.dao.SubscribeDao;
import com.ruanyuan.bus.pojo.Subscribe;
import com.ruanyuan.sam.dao.IntentionDao;
import com.ruanyuan.sam.pojo.Intention;
import com.ruanyuan.sys.dao.MessageDao;
import com.ruanyuan.sys.pojo.Message;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.WebUtils;

import cn.hutool.crypto.SecureUtil;
/**
 * 客户业务类
 *@Date 2020年04月14日 11:34
 */
@Service
public class CustomerServiceImpl implements CustomerService {
	//注解注入dao
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CollectDao collectDao;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private IntentionDao intentionDao;
	@Autowired
	private MessageDao messageDao;
	@Autowired
	private SubscribeDao subscribeDao;
	
	/**
	 * 查询所有客户
	 */
	@Override
	public DataGridView getCustomerAll(CustomerVo customerVo) {
		//开启分页
		Page<Object> page = PageHelper.startPage(customerVo.getPage(), customerVo.getLimit());
		//根据条件查询客户
		List<Customer> data = customerDao.getCustomerAll(customerVo);
		//返回layui封装的数据对象
		return new DataGridView(page.getTotal(), data);
	}

	/**
	 * 添加客户
	 */
	@Override
	public void addCustomer(CustomerVo customerVo) {
		//截取密码
		String pwd = customerVo.getPassword().substring(0, customerVo.getPassword().indexOf(","));
		//调用hutool工具加密
		String password=SecureUtil.md5(pwd);
		customerVo.setPassword(password);
		customerDao.addCustomer(customerVo);
	}

	/**
	 * 修改客户
	 */
	@Override
	public void updateCustomer(CustomerVo customerVo) {
		//获取原来的密码
		customerVo.setPassword(customerDao.getCustomerId(customerVo.getCustomerId()).getPassword());
		customerDao.updateCustomer(customerVo);
	}
	/**
	 * 前台修改客户信息（密码）
	 */
	@Override
	public void updatePcCustomer(CustomerVo customerVo) {
		//如果密码不为空，则为修改密码 转换数据
		if(customerVo.getPassword()!=null) {
			String pwd=SecureUtil.md5(customerVo.getPassword());
			customerVo.setPassword(pwd);
		}
		customerDao.updateCustomer(customerVo);
	}
	/**
	 * 删除客户
	 */
	@Override
	public int deleteCustomer(Integer customerId) {
		//查询收藏表中客户信息
		List<Collect> collects = collectDao.getCollectionCustomerId(customerId);
		//查询评论表中客户信息
		List<Comment> comments = commentDao.getCommentCustomerId(customerId);
		//查询意向表中客户信息
		List<Intention> intentions = intentionDao.getIntentionCustomerId(customerId);
		//查询留言表中客户信息
		List<Message> messages = messageDao.getMessageCustomerId(customerId);
		//查询预约表中客户信息
		List<Subscribe> subscribes = subscribeDao.getSubscribeCustomerId(customerId);
		if(collects.size()==0 & comments.size()==0 & intentions.size()==0 & messages.size()==0 & subscribes.size()==0) {
			return customerDao.deleteCustomer(customerId);
		}else {
			return 0;
		}
	}
	
	/**
	 * 验证登录
	 */
	@Override
	public Customer login(Customer customer) {
		//调用hutool工具生成密文
		String pwd=SecureUtil.md5(customer.getPassword());
		customer.setPassword(pwd);
		return customerDao.login(customer);
	}
	/**
	 * 根据客户编号查询客户信息
	 */
	@Override
	public DataGridView getCustomerById(Integer customerId) {
		// 从session中取出数据
		Customer customer2=new Customer();
		if(WebUtils.getHttpSession().getAttribute("CUSTOMER")!=null) {
			Customer customer=(Customer) WebUtils.getHttpSession().getAttribute("CUSTOMER");
			//给客户编号赋值
			customerId=customer.getCustomerId();
			//获得当前客户信息
			customer2=customerDao.getCustomerById(customerId);
		}
		return new DataGridView(customer2);
	}
	/**
	 * 查询所有返回List集合
	 */
	@Override
	public List<Customer> getCustomerAll1(CustomerVo customerVo) {
		// TODO Auto-generated method stub
		return customerDao.getCustomerAll(customerVo);
	}

	/**
	 * 根据客户id查询客户信息
	 */
	@Override
	public Customer getCustomerId(Integer customerId) {
		return customerDao.getCustomerId(customerId);
	}

	/**
	 * 重置密码
	 */
	@Override
	public void updateCustomerPwd(Integer customerId) {
		customerDao.updateCustomerPwd(customerId);
	}

	/**
	 * 账号查重
	 */
	@Override
	public Customer getCustomerLoginName(String loginName) {
		return customerDao.getCustomerLoginName(loginName);
	}
	/**
	 * 前台添加用户
	 */
	@Override
	public void addPcCustomer(CustomerVo customerVo) {
		//截取密码
		String pwd = customerVo.getPassword();
		//调用hutool工具加密
		String password=SecureUtil.md5(pwd);
		customerVo.setPassword(password);
		customerVo.setBalance("0.00");
		customerDao.addCustomer(customerVo);
	}

}
