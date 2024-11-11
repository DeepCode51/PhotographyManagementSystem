package com.ruanyuan.sam.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruanyuan.sam.service.ImageService;
import com.ruanyuan.sam.service.SampleService;
import com.ruanyuan.sam.vo.ImageVo;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.ResultObj;


/**
 *    图片控制器
 * @author 
 * @Data 2020年4月14日 下午4:12:06
 */
@RestController
@RequestMapping("image")
public class ImageController {

	@Autowired
	private ImageService imageService;
	@Autowired
	private SampleService sampleService;
	/**
	 * 查询样片信息
	 *@param imageVo 样片包装类
	 *@return layui 封装得数据对象
	 */
	@RequestMapping("getAllImage")
	public DataGridView getAllImage(ImageVo imageVo) {
		System.out.println(imageVo.getSampleId());
		//查询图片
		return imageService.getAllImage(imageVo);
	}
	
	/**
	 * 样片是否存在
	 */
	@RequestMapping("getImageBySampleId")
	public Boolean getImageBySampleId(Integer sampleId) {
		System.out.println(sampleId);
		if(sampleService.getSampleId(sampleId)!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	
	/**
	 * 查询样片信息
	 *@param imageVo 样片包装类
	 *@return layui 封装得数据对象
	 */
	@RequestMapping("getPcAllImage")
	public DataGridView getPcAllImage(ImageVo imageVo) {
		//查询图片
		return imageService.getPcAllImage(imageVo);
	}
	/**
	 * 删除图片
	 *@param imageId 图片Id
	 *@return 返回状态信息
	 */
	@RequestMapping("deleteImageByImageId")
	public ResultObj deleteImageByImageId(Integer imageId) {
		try {
			//删除图片
			imageService.deleteImageByImageId(imageId);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	/**
	 * 批量删除图片
	 *@param ids 图片Id
	 *@return 返回状态信息
	 */
	@RequestMapping("deleteImageByArray")
	public ResultObj deleteImageByArray(@Param("ids")Integer[] ids) {
		List<Integer> idlist = new ArrayList<Integer>();
		for (int i = 0; i < ids.length; i++) {
			idlist.add(ids[i]);
		}
		try {
			//批量删除图片
			imageService.deleteImageByArray(idlist);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	/**
	 * 修改图片
	 *@param imageVo 图片包装类
	 *@return layui 封装得数据对象
	 */
	@RequestMapping("updateImage")
	public ResultObj updateImage(ImageVo imageVo) {
		System.out.println(imageVo);
		try {
			imageService.updateImage(imageVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	/**
	 * 根据数组添加图片
	 *@param ids 图片Id
	 *@return 返回状态信息
	 */
	@RequestMapping("addImagesByArray")
	public ResultObj addImagesByArray(String[] images1,Integer sampleId) {
		try {
			//添加图片
			imageService.addImagesByArray(images1,sampleId);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	
	
	
}
