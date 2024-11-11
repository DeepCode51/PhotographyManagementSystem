package com.ruanyuan.bus.dao;

import java.util.List;

import com.ruanyuan.bus.pojo.Subscribe;

/**
 * 预约的数据访问层接口
 * @Data 2020年4月13日 下午3:03:41
 */
public interface SubscribeDao {
	/**
	 * 查询所有的预约信息
	 * @param Subscribe 预约对象
	 * @return 预约集合
	 */
	public List<Subscribe> getAllSubscribe(Subscribe subscribe);
	/**
	 * 添加预约信息
	 * @param Subscribe 预约对象
	 * @return 受影响的行数
	 */
	public Integer addSubscribe(Subscribe subscribe);
	/**
	 * 修改预约信息
	 * @param Subscribe 预约对象
	 * @return 受影响的行数
	 */
	public Integer updateSubscribe(Subscribe subscribe);
	/**
	 * 根据预约Id查询预约信息
	 * @param subId 预约Id
	 * @return 预约对象
	 */
	public Subscribe getSubscribeBySubId(Integer subId);
	/**
	 * 根据样片Id查询预约信息
	 * @param commId 样片Id
	 * @return 预约对象
	 */
	public Subscribe getSubscribeBySampleId(Integer sampleId);
	/**
	 * 根据样片Id查询预约信息
	 * @param commId 样片Id
	 * @return 预约对象
	 */
	public List<Subscribe> getSubscribeBySampleId1(Integer sampleId);
	/**
	 * 根据客户Id查询预约信息
	 * @param customerId 客户Id
	 * @return 预约对象
	 */
	public List<Subscribe> getSubscribeByCustomerId(Integer customerId);
	/**
	 * 根据客户id和样片Id查询预约信息
	 * @param intention 包装类
	 * @return 返回预约对象集合
	 */
	public List<Subscribe> getSubscribeByCustomerIdAndSampleId(Subscribe subscribe);
	/**
	 * 根据预约Id删除预约信息
	 *@param subId 预约Id
	 *@return  受影响的行数
	 */
	public Integer deleteSubscribeBySubId(Integer subId);
	/**
	 * 批量删除预约信息
	 *@param ids 预约Id数组
	 *@return  受影响的行数
	 */
	public Integer deleteSubscribeByArray(List<Integer> ids);
	/**
	 * 根据客户id查询预约信息
	 * @param customerId 客户id
	 * @return 返回预约对象集合
	 */
	public List<Subscribe> getSubscribeCustomerId(Integer customerId);
	/**
	 * 判断过期时间
	 *@param subscribe
	 *@return
	 */
	public Subscribe getSubscribeBySysTimeAndSubId (Subscribe subscribe);
}
