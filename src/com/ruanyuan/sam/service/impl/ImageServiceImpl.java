package com.ruanyuan.sam.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruanyuan.sam.dao.ImageDao;
import com.ruanyuan.sam.pojo.Image;
import com.ruanyuan.sam.pojo.Sample;
import com.ruanyuan.sam.service.ImageService;
import com.ruanyuan.sam.vo.ImageVo;
import com.ruanyuan.sys.constast.SysConstast;
import com.ruanyuan.sys.utils.AppFileUtils;
import com.ruanyuan.sys.utils.DataGridView;
/**
 *    图片操作类
 * @author 
 * @Data 2020年4月14日 下午3:37:19
 */
@Service
public class ImageServiceImpl implements ImageService {
	
	@Autowired
	private ImageDao imageDao;
	
	/**
	  * 查询图片信息
	 *@param imageVo 图片包装类
	 *@return layui 封装得数据对象
	 */
	@Override
	public DataGridView getAllImage(ImageVo imageVo) {
		//开启分页
		Page<Object> page  = PageHelper.startPage(imageVo.getPage(),imageVo.getLimit());
		//根据条件查询样片数据
		List<Image> data = this.imageDao.getAllImage1(imageVo);
		//返回layui封装得 数据对象 
		return new DataGridView(page.getTotal(),data);
	}
	/**
	  * 查询图片信息
	 *@param imageVo 图片包装类
	 *@return layui 封装得数据对象
	 */
	@Override
	public DataGridView getPcAllImage(ImageVo imageVo) {
		//根据条件查询样片数据
		List<Image> data = this.imageDao.getAllImage(imageVo);
		//返回layui封装得 数据对象 
		return new DataGridView(data);
	}
	/**
	 * 删除图片
	 */
	@Override
	public void deleteImageByImageId(Integer imageId) {
		//得到要删除的原来的图片对象
		Image imageByImageId = this.imageDao.getImageByImageId(imageId);
		//删除原来 的图
		AppFileUtils.removeFileByPath(imageByImageId.getImageAddress());
		//删除图片
		imageDao.deleteImageByImageId(imageId);
	}
	/**
	 * 批量删除图片
	 */
	@Override
	public void deleteImageByArray(List<Integer> ids) {
		
		//删除服务器上的图片
		Integer[]  arr1 = new Integer[ids.size()];  
		ids.toArray(arr1);
		List<Image> imageByImageIdByArray = imageDao.getImageByImageIdByArray(arr1);
		for (Image image : imageByImageIdByArray) {
			AppFileUtils.removeFileByPath(image.getImageAddress());
		}
		//批量删除图片
		imageDao.deleteImageByArray(ids);
	}
	/**
	 * 根据图片路径集合和样片Id添加
	 *@param images 图片路径集合
	 *@param sampleId 样片Id
	 */
	@Override
	public void addImagesByArray(String[] images, Integer sampleId) {
		//创建图片路径集合
		List<String> image = new ArrayList<String>();
		//循环删除临时后缀
		for (int i = 0; i < images.length; i++) {
			String updateFileName = AppFileUtils.updateFileName(images[i],SysConstast.FILE_UPLOAD_TEMP);
			image.add(updateFileName);
		}
		//创建对象
		ImageVo imageVo = new ImageVo();
		imageVo.setSampleId(sampleId);
		for (String path : image) {
			imageVo.setImageAddress(path);
			imageDao.addImage(imageVo);
		}
	}
	/**
	 * 修改图片
	 *@param imageVo
	 *@param request
	 */
	@Override
	public void updateImage(ImageVo imageVo) {
		 String imageAddress = imageVo.getImageAddress();
		if(imageAddress.endsWith(SysConstast.FILE_UPLOAD_TEMP)) {
			String filePath=AppFileUtils.updateFileName(imageVo.getImageAddress(),SysConstast.FILE_UPLOAD_TEMP);
			imageVo.setImageAddress(filePath);
			//得到要删除的原来的图片对象
			Image imageByImageId = this.imageDao.getImageByImageId(imageVo.getImageId());
			//删除原来 的图
			AppFileUtils.removeFileByPath(imageByImageId.getImageAddress());
		}
		imageDao.updateImage(imageVo);
	}
	
	/**
	 * 根据图片Id查询图片
	 *@param imageId
	 *@return
	 */
	public Image getImageByImageId(Integer imageId) {
		return this.imageDao.getImageByImageId(imageId);
	}
}
