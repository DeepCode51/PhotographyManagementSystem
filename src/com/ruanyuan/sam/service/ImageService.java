package com.ruanyuan.sam.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ruanyuan.sam.pojo.Image;
/**
 *   图片操作接口
 * @author 
 * @Data 2020年4月14日 下午3:29:32
 */
import com.ruanyuan.sam.vo.ImageVo;
import com.ruanyuan.sys.utils.DataGridView;
public interface ImageService {
	/**
	  * 查询图片信息
	 *@param imageVo 图片包装类
	 *@return layui 封装得数据对象
	 */
	public DataGridView getAllImage(ImageVo imageVo);
	/**
	  * 查询图片信息
	 *@param imageVo 图片包装类
	 *@return layui 封装得数据对象
	 */
	public DataGridView getPcAllImage(ImageVo imageVo);
	/**
	 * 删除图片
	 *@param imageId 图片Id
	 */
	public void deleteImageByImageId(Integer imageId);
	/**
	 * 批量删除图片
	 *@param ids 图片Id数组
	 */
	public void deleteImageByArray(List<Integer> ids);
	/**
	 * 根据图片路径集合和样片Id添加
	 *@param images 图片路径集合
	 *@param sampleId 样片Id
	 */
	public void addImagesByArray(String[] images, Integer sampleId);
	/**
	 * 修改图片
	 *@param imageVo
	 *@param request
	 */
	public void updateImage(ImageVo imageVo);
	/**
	 * 根据图片Id查询图片
	 *@param imageId
	 *@return
	 */
	public Image getImageByImageId(Integer imageId);
}
