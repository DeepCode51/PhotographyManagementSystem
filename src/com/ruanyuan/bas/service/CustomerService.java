package com.ruanyuan.bas.service;

import java.util.List;

import com.ruanyuan.bas.pojo.Customer;
import com.ruanyuan.bas.vo.CustomerVo;
import com.ruanyuan.sys.utils.DataGridView;
/**
 * 客户业务接口
 *@Date 2020年04月14日 11:32
 */
public interface CustomerService {
	/**
	 * 查询所有客户
	 * @param customerVo 客户包装类
	 * @return 返回layui封装格式数据
	 */
	public DataGridView getCustomerAll(CustomerVo customerVo);
	
	public List<Customer> getCustomerAll1(CustomerVo customerVo);
	
	
	
	/**
	 * 根据客户编号查询客户信息
	 * @param customerId 客户编号
	 * @return 客户实体类
	 */
	public DataGridView getCustomerById(Integer customerId);
	
	
	
	/**
	 * 添加客户
	 * @param customerVo 客户包装类
	 */
	public void addCustomer(CustomerVo customerVo);
	/**
	 * 前台注册用户
	 * @param customerVo 客户包装类
	 */
	public void addPcCustomer(CustomerVo customerVo);
	/**
	 * 修改客户
	 * @param customerVo 客户包装类
	 */
	public void updateCustomer(CustomerVo customerVo);
	/**
	 * 前台修改客户
	 * @param customerVo 客户包装类
	 */
	public void updatePcCustomer(CustomerVo customerVo);
	/**
	 * 删除客户
	 * @param customerId 客户id
	 */
	public int deleteCustomer(Integer customerId);
	
	/**
	 * 验证登录
	 * @param customer 登录信息
	 * @return 登录信息
	 */
	public Customer login(Customer customer);
	
	/**
	 * 根据客户id查询客户信息
	 * @param customerId 客户id
	 * @return 返回客户对象
	 */
	public Customer getCustomerId(Integer customerId);
	
	/**
	 * 重置密码
	 * @param customerId 客户id
	 * @return 返回影响的行数
	 */
	public void updateCustomerPwd(Integer customerId);
	
	/**
	 * 账号查重
	 * @param loginName 账号
	 * @return 返回客户对象
	 */
	public Customer getCustomerLoginName(String loginName);
	
}
