package com.ruanyuan.sam.dao;

import java.util.List;

import com.ruanyuan.sam.pojo.Image;

/**
 * 图片访问接口
 * @date 2020年4月13日
 */
public interface ImageDao {
	/**
	 * 查询所有
	 * @return:返回查询到的图片信息集合
	 */
	public List<Image> getAllImage(Image image);
	/**
	 * 查询所有
	 * @return:返回查询到的图片信息集合
	 */
	public List<Image> getAllImage1(Image image);
	/**
	 * 添加图片信息
	 * @param image:图片实体类
	 * @return:返回受影响行数
	 */
	public int addImage(Image image);
	/**
	 * 修改图片信息
	 * @param image:图片实体类
	 * @return:返回受影响行数
	 */
	public int updateImage(Image image);
	/**
	 * 根据图片编号单个删除图片信息
	 * @param imageId:图片编号
	 * @return:返回受影响行数
	 */
	public int deleteImageByImageId(Integer imageId);
	/**
	 * 批量删除图片
	 * @param ids:图片编号数组
	 * @return:返回受影响行数
	 */
	public int deleteImageByArray(List<Integer> ids);
	/**
	 * 根据样片Id查询图片集合
	 *@param sampleId 样片Id
	 *@return 图片集合
	 */
	public List<Image> getImageBySample(Integer sampleId);
	/**
	 * 根据图片Id查询图片
	 *@param imageId
	 *@return
	 */
	public Image getImageByImageId(Integer imageId);
	/**
	 * 根据样片数组查询信息
	 *@param ids 
	 */
	public List<Image> getImageByImageIdByArray(Integer[] ids);
}
