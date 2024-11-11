package com.ruanyuan.bas.dao;

import java.util.List;

import com.ruanyuan.bas.pojo.Customer;

/**
 * 客户数据访问接口
 * @author 
 *
 */
public interface CustomerDao {

	/**
	 * 查询所有客户
	 * @param customer 客户对象
	 * @return 返回客户对象集合
	 */
	public List<Customer> getCustomerAll(Customer customer);
	/**
	 * 根据客户编号查询客户信息
	 * @param customerId 客户编号
	 * @return 客户类
	 */
	public Customer getCustomerById(Integer customerId);
	/**
	 * 验证登录
	 * @return 登录信息
	 */
	public Customer login(Customer customer);
	/**
	 * 添加客户
	 * @param customer 客户对象
	 * @return 返回影响的行数
	 */
	public int addCustomer(Customer customer);
	
	/**
	 * 修改客户
	 * @param customer 客户对象
	 * @return 返回影响的行数
	 */
	public int updateCustomer(Customer customer);
	
	/**
	 * 删除客户
	 * @param customerId 客户id
	 * @return 返回影响的行数
	 */
	public int deleteCustomer(int customerId);
	
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
	public int updateCustomerPwd(Integer customerId);
	
	/**
	 * 账号查重
	 * @param loginName 账号
	 * @return 返回客户对象
	 */
	public Customer getCustomerLoginName(String loginName);
	
}
