package com.ruanyuan.bas.dao;

import java.util.List;

import com.ruanyuan.bas.pojo.Collect;
import com.ruanyuan.bas.vo.CollectVo;

/**
 * 收藏数据访问接口
 * @date 2020年4月13日
 */
public interface CollectDao {
	/**
	 * 查询所有收藏信息
	 * @return:返回收藏信息集合
	 */
	public List<Collect> getAllCollection(Collect collect);
	/**
	 * 添加收藏信息
	 * @param collection:收藏实体类
	 * @return:返回受影响行数
	 */
	public int addCollection(Collect collection);
	/**
	 * 修改收藏信息
	 * @param collection:收藏实体类
	 * @return:返回受影响行数
	 */
	public int updateCollection(Collect collection);
	/**
	 * 单个删除收藏信息
	 * @param collectId:收藏编号
	 * @return:返回受影响行数
	 */
	public int deleteCollectionByCollectId(Integer collectId);
	/**
	 * 批量删除收藏信息
	 * @param ids:收藏编号数组
	 * @return:返回受影响行数
	 */
	public int deleteCollectionByArray(List<Integer> ids);
	/**
	 * 根据客户id查询收藏信息
	 * @param customerId 客户id
	 * @return 返回收藏对象集合
	 */
	public List<Collect> getCollectionCustomerId(Integer customerId);
	/**
	 * 根据样片id删除收藏信息
	 * @param sampleId 样片id
	 * @return 是否删除成功
	 */
	public Integer deleteCollectBySampleId(Integer sampleId);
	/**
	 * 根据客户id查询收藏信息并分页展示
	 * @param collectVo 收藏包装类
	 * @return 收藏集合对象
	 */
	public List<Collect> getCollectionByCustomerId(CollectVo collectVo);
	/**
	 * 根据收藏编号查询收藏信息
	 * @param customerId:收藏编号
	 * @return:返回收藏信息
	 */
	public Collect getCollectionByCollectId(Integer collectId);
	/**
	 * 根据样片Id查询收藏信息
	 *@param sampleId
	 *@return
	 */
	public List<Collect> getCollectBySampleId(Integer sampleId);
	/**
	 * 根据客户id和样片Id查询收藏样片信息
	 * @return:返回收藏信息集合
	 */
	public List<Collect> getCollectionCustomerIdAndSampleId(Collect collect);
	/**
	 * 查询所有收藏信息
	 * @return:返回集合
	 */
	public List<Collect> getAllCollection();
}
