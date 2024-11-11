package com.ruanyuan.sam.dao;

import java.util.List;

import com.ruanyuan.sam.pojo.Rotation;

/**
* 轮播图数据访问接口 
* @author
* @Data :2020年4月13日 下午1:13:45
*/
public interface RotationDao {
	
	/**
	 * 查询轮播图信息
	 *@return 返回轮播图集合
	 */
	public List<Rotation> getAllRotation(Rotation rotation);
	/**
	 * 添加轮播图的信息
	 * @param rotation 轮播图对象
	 * @return 受影响得行数
	 */
	public int insertRotation(Rotation rotation);
	
	/**
	 * 修改轮播图得信息
	 * @param rotation 轮播图对象
	 * @return 受影响得行数
	 */
	public int updataRotationByRotationId(Rotation rotation);
	
	/**
	 * 根据轮播图id删除信息
	 * @param rotationId 轮播图ID
	 * @return 受影响得行数
	 */
	public int deleteRotationByRotationId(Integer rotationId);
	/**
	 * 根据排序值倒叙 查询前两个轮播图
	 * @param ranking 排序值
	 *@return 返回轮播图集合
	 */
	public List<Rotation> getFourRotation();
	/**
	 * 批量删除轮播图信息
	 * @param rotationIds 轮播图id数组
	 * @return 返回影响的行数
	 */
	public int deleteRotationByArray(List<Integer> rotationIds);
	/**
	 * 根据样片Id查询评论表getRotationBySampleId
	 *@param sampleId
	 *@return
	 */
	public List<Rotation> getRotationBySampleId(Integer sampleId);
	/**
	 * 根据轮播图id查询轮播图信息
	 * @param rotationId 轮播图id
	 * @return 返回轮播图对象
	 */
	public Rotation getRotationId(Integer rotationId);
	
}
