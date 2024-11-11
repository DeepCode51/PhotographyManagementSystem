package com.ruanyuan.sam.dao;

import java.util.List;

import com.ruanyuan.sam.pojo.Sample;

/**
 * 样片数据访问接口 
 * @Data 2020年4月12日 下午8:32:20
 */
public interface SampleDao {
	/**
	 * 查询样片信息
	 *@return 返回样片集合
	 */
	public List<Sample> getAllSample(Sample sample);
	/**
	 * 添加样片得信息
	 *@param sample 样片对象
	 *@return 受影响得行数
	 */
	public int insertSample(Sample sample);
	/**
	 * 根据样片Id修改样片得信息
	 *@param sample 样片对象
	 *@return 受影响得行数
	 */
	public int updateSampleBySampleId(Sample sample);
	/**
	  * 根据样片Id删除样片得信息
	 *@param sampleId 样片编号
	 *@return 受影响得行数
	 */
	public int deleteSampleBySampleId(Integer sampleId);
	/**
	 * 根据样片Id在线预约量加一
	 *@return 受影响得行数
	 */
	public int updateSampleBySubscribeCountAddOne(Integer sampleId);
	/**
	 * 根据样片Id在线预约量减一
	 *@return 受影响得行数
	 */
	public int updateSampleBySubscribeCountReduceOne(Integer sampleId);
	/**
	 * 根据样片Id在线收藏量加一
	 *@return 受影响得行数
	 */
	public int updateSampleByCollectionCountAddOne(Integer sampleId);
	/**
	 * 根据样片Id在线收藏量减一
	 *@return 受影响得行数
	 */
	public int updateSampleByCollectionCountReduceOne(Integer sampleId);
	/**
	 * 根据样片Id在线订单量加一
	 *@return 受影响得行数
	 */
	public int updateSampleByOrderCountAddOne(Integer sampleId);
	/**
	 * 根据样片Id在线订单量减一
	 *@return 受影响得行数
	 */
	public int updateSampleByOrderCountReduceOne(Integer sampleId);
	/**
	 * 根据用户id查询样片信息
	 * @param userId 用户id
	 * @return 返回样片对象集合
	 */
	public List<Sample> getSampleUserId(Integer userId);
	/**
	 *根据类别id查询样片信息 
	 * @param typeId 类别id
	 * @return 返回样片对象集合
	 */
	public List<Sample> getSampleTypeId(Integer typeId);
	/**
	 * 查询样片信息(前台)
	 * @return 返回样片信息集合
	 */
	public List<Sample> getAllSample();
	/**
	 * 根据样片id查询样片信息
	 * @param sampleId 样片id
	 * @return 返回样片对象
	 */
	public Sample getSampleId(Integer sampleId);
	/**
	 * 根据样片名称查询
	 *@param sampleName
	 *@return
	 */
	public Sample getSampleBySampleName(String sampleName);
	
}
