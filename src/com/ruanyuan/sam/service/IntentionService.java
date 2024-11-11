package com.ruanyuan.sam.service;

import java.util.List;

import com.ruanyuan.bas.vo.CustomerVo;
import com.ruanyuan.sam.pojo.Intention;
import com.ruanyuan.sam.vo.IntentionVo;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.ResultObj;

/**
 * 意向业务接口
 * @date 2020年4月15日
 */
public interface IntentionService {
	/**
	 * 查询所有意向信息
	 * @param intention:意向封装类
	 * @return:返回意向信息集合
	 */
	public DataGridView getAllIntention(IntentionVo intentionVo);
	/**
	 * 根据客户id和样片Id查询意向样片信息
	 * @param intention 包装类
	 * @return 返回意向对象集合
	 */
	public List<Intention> getIntentionCustomerIdAndSampleId(IntentionVo intentionVo);
	/**
	 * 添加意向信息
	 * @param intentionVo:意向封装类
	 * @return:返回受影响行数
	 */
	public int addIntention(IntentionVo intentionVo);
	/**
	 * 修改意向信息
	 * @param intentionVo:意向封装类
	 * @return:返回受影响行数
	 */
	public int updateIntention(IntentionVo intentionVo);
	/**
	 * 单个删除意向信息
	 * @param intentionId:意向编号
	 * @return:返回受影响行数
	 */
	public int deleteIntentionByIntentionId(Integer intentionId);
	/**
	 * 前台
	 * 单个删除意向信息
	 * @param intentionId:意向编号
	 * @return:返回受影响行数
	 */
	public Integer deleteIntentionByIntentionIdz(Integer intentionId);
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
	 * @param customerId:客户编号
	 * @return:layui封装数据
	 */
	public DataGridView getIntentionByCustomerId(IntentionVo intentionVo);
	/**
	 * 根据意向编号查询意向信息
	 * @param intentionId:意向编号
	 * @return:意向信息
	 */
	public Intention getIntentionById(Integer intentionId);
	
	
	
}
