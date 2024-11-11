package com.ruanyuan.bas.service;

import java.util.List;

import com.ruanyuan.bas.pojo.Collect;
import com.ruanyuan.bas.vo.CollectVo;
import com.ruanyuan.sys.utils.DataGridView;

/**
 * 收藏业务接口
 *@Date 2020年04月15日 14:11
 */
public interface CollectService {
	/**
	 * 根据客户编号查询收藏信息
	 * @param CustomerId 客户编号
	 * @return 返回封装数据
	 */
	public DataGridView getCollectionByCustomerId(CollectVo collectVo);
	
	/**
	 * 查询所有收藏信息
	 * @param collectVo:收藏分装类
	 * @return:返回layui格式
	 */
	public DataGridView getAllCollection(CollectVo collectVo);
	
	/**
	 * 添加收藏信息
	 * @param collectVo:收藏包装类
	 * @return:返回受影响行数
	 */
	public void addCollection(CollectVo collectVo);
	/**
	 * 修改收藏信息
	 * @param collectVo:收藏分装类
	 * @return:返回受影响行数
	 */
	public void updateCollection(CollectVo collectVo);
	
	/**
	 * 单个删除收藏信息
	 * @param collectId:收藏编号
	 * @return:返回受影响行数
	 */
	public void deleteCollectionByCollectId(Integer collectId);
	/**
	 * 批量删除收藏信息
	 * @param ids:收藏编号数组
	 * @return:返回受影响行数
	 */
	public void deleteCollectionByArray(List<Integer> ids);
	/**
	 * 根据样片id删除收藏信息
	 * @param sampleId 样片id
	 * @return 是否删除成功
	 */
	public void deleteCollectBySampleId(Integer sampleId);
	/**
	 * 根据客户id查询收藏信息
	 * @param customerId 客户id
	 * @return 返回收藏对象集合
	 */
	public DataGridView getCollectionCustomerId(CollectVo collectVo);
	
	/**
	 * 根据收藏编号查询收藏信息
	 * @param customerId:收藏编号
	 * @return:返回收藏信息
	 */
	public Collect getCollectionByCollectId(Integer collectId);
	/**
	 * 根据客户id和样片Id查询收藏样片信息
	 * @return:返回收藏信息集合
	 */
	public List<Collect> getCollectionCustomerIdAndSampleId(CollectVo collectVo);
	/**
	 * 查询所有收藏信息
	 * @return:返回集合
	 */
	public List<Collect> getAllCollection();
}
