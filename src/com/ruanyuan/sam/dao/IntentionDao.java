package com.ruanyuan.sam.dao;

import java.util.List;

import com.ruanyuan.sam.pojo.Intention;
import com.ruanyuan.sam.vo.IntentionVo;
/**
 * 意向数据接口
 * @date 2020年4月13日
 */
public interface IntentionDao {
	/**
	 * 查询所有意向信息
	 * @param intention:查询条件
	 * @return:返回意向信息集合
	 */
	public List<Intention> getAllIntention(Intention intention);
	/**
	 * 添加意向信息
	 * @param intention:意向实体类
	 * @return:返回受影响行数
	 */
	public int addIntention(Intention intention);
	/**
	 * 修改意向信息
	 * @param intention:意向实体类
	 * @return:返回受影响行数
	 */
	public int updateIntention(Intention intention);
	/**
	 * 单个删除意向信息
	 * @param intentionId:意向编号
	 * @return:返回受影响行数
	 */
	public int deleteIntentionByIntentionId(Integer intentionId);
	/**
	 * 根据样片编号单个删除意向样片
	 * @param intentionId
	 * @return:是否删除成功
	 */
	public Integer deleteIntentBySampleId(Integer intentionId);
	/**
	 * 批量删除意向信息
	 * @param ids:意向编号数组
	 * @return:返回受影响行数
	 */
	public int deleteIntentionByArray(List<Integer> ids);
	/**
	 * 根据客户id查询意向信息
	 * @param customerId 客户id
	 * @return 返回意向对象集合
	 */
	public List<Intention> getIntentionCustomerId(Integer customerId);
	/**
	 * 前台
	 * 根据客户id查询意向信息
	 * @param customerId 客户id
	 * @return 返回意向对象集合
	 */
	public List<Intention> getIntentionByCustomerId(Integer customerId);
	/**
	 * 根据意向编号查询意向信息
	 * @param intentionId:意向编号
	 * @return:返回意向信息
	 */
	public Intention getIntentionById(Integer intentionId);
	/**
	 * 根据客户id和样片Id查询意向样片信息
	 * @param intention 包装类
	 * @return 返回意向对象集合
	 */
	public List<Intention> getIntentionCustomerIdAndSampleId(IntentionVo intentionVo);
	/**
	 * 根据样片Id查询意向信息
	 *@param sampleId 样片Id
	 *@return 意向样片集合
	 */
	public List<Intention> getIntentionBySampleId(Integer sampleId);
}
