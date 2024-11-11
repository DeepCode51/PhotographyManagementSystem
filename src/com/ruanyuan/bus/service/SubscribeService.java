package com.ruanyuan.bus.service;

import com.ruanyuan.bus.vo.SubscribeVo;

import java.util.List;

import com.ruanyuan.bas.pojo.Customer;
import com.ruanyuan.bus.pojo.Subscribe;
import com.ruanyuan.sys.utils.DataGridView;

/**
 * 预约的业务逻辑层接口
 * @Data 2020年4月13日 下午3:15:36
 */
public interface SubscribeService {
	/**
	 * 查询所有的预约信息
	 * @param Subscribe 预约对象
	 * @return 预约集合
	 */
	public DataGridView getAllSubscribe(SubscribeVo subscribeVo);
	/**
	 * 添加预约信息
	 * @param Subscribe 预约对象
	 * @return 受影响的行数
	 */
	public void addSubscribe(SubscribeVo subscribeVo);
	/**
	 * 修改预约信息
	 * @param Subscribe 预约对象
	 * @return 受影响的行数
	 */
	public void updateSubscribe(SubscribeVo subscribeVo);
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
	public List<Subscribe> getSubscribeByCustomerIdAndSampleId(SubscribeVo subscribeVo);
	/**
	 * 根据预约Id删除预约信息
	 *@param subId 预约Id
	 *@return  受影响的行数
	 */
	public int deleteSubscribeBySubId(Integer subId);
	/**
	 * 批量删除预约信息
	 *@param ids 预约Id数组
	 *@return  受影响的行数
	 */
	public int deleteSubscribeByArray(List<Integer> ids);
	/**
	 * 根据客户id查询预约信息
	 * @param  SubscriVe 预约对象包装类
	 * @return 返回预约对象集合
	 */
	public DataGridView getSubscribyCustomerId(SubscribeVo subscribeVo);
	/**
	 * 生成订单
	 *@param subscribeVo 预约包装类
	 */
	public int updateSubscribeBygenerateOrder(SubscribeVo subscribeVo,Customer customer);
}
