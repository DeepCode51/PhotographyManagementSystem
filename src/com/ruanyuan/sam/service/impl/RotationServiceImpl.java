package com.ruanyuan.sam.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruanyuan.sam.dao.RotationDao;
import com.ruanyuan.sam.pojo.Rotation;
import com.ruanyuan.sam.service.RotationService;
import com.ruanyuan.sam.vo.RotationVo;
import com.ruanyuan.sys.constast.SysConstast;
import com.ruanyuan.sys.utils.AppFileUtils;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.WebUtils;
/**
 * 轮播业务类
 * 
 * @date:2020-04-14 21:25
 *
 */
@Service
public class RotationServiceImpl implements RotationService {
	@Autowired
	private RotationDao rotationDao;
	/**
	 * 根据排序值查询前二个轮播图信息
	 */
	@Override
	public DataGridView getPcRotation() {
		// TODO 自动生成的方法存根
		return new DataGridView(rotationDao.getFourRotation());
	}
	/**
	 * 查询所有轮播图
	 */
	@Override
	public DataGridView getRotationAll(RotationVo rotationVo) {
		//开启分页
		Page<Object> page = PageHelper.startPage(rotationVo.getPage(), rotationVo.getLimit());
		//根据条件查询轮播图信息
		List<Rotation> data = rotationDao.getAllRotation(rotationVo);
		//返回layui封装的数据对象
		return new DataGridView(page.getTotal(), data);
	}
	/**
	 * 添加轮播图
	 */
	@Override
	public void addRotation(RotationVo rotationVo) {
		//添加轮播图
		String filePath=AppFileUtils.updateFileName(rotationVo.getRotationImage(),SysConstast.FILE_UPLOAD_TEMP);
		rotationVo.setRotationImage(filePath);
		//添加时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = df.format(new Date());
		rotationVo.setRotationTime(format);
		rotationDao.insertRotation(rotationVo);
	}
	/**
	 * 修改轮播图
	 */
	@Override
	public void updateRotation(RotationVo rotationVo) {
		String rotationImage = rotationVo.getRotationImage();
		if(rotationImage.endsWith(SysConstast.FILE_UPLOAD_TEMP)) {
			String filePath=AppFileUtils.updateFileName(rotationVo.getRotationImage(),SysConstast.FILE_UPLOAD_TEMP);
			rotationVo.setRotationImage(filePath);
			//得到要删除的原来的轮播图对象
			Rotation rotation = rotationDao.getRotationId(rotationVo.getRotationId());
			//删除原来的轮播图
			AppFileUtils.removeFileByPath(rotation.getRotationImage());
		}
		rotationDao.updataRotationByRotationId(rotationVo);
	}
	/**
	 * 删除轮播图
	 */
	@Override
	public void deleteRotation(Integer rotationId) {
		//得到轮播图对象
		Rotation rotation = rotationDao.getRotationId(rotationId);
		//删除轮播图
		AppFileUtils.removeFileByPath(rotation.getRotationImage());
		rotationDao.deleteRotationByRotationId(rotationId);
	}
	/**
	 * 批量删除轮播图
	 */
	@Override
	public void deleteRotationByArray(List<Integer> rotationIds) {
		//批量删除轮播图
		for (Integer integer : rotationIds) {
			//得到轮播图对象
			Rotation rotation = rotationDao.getRotationId(integer);
			//删除轮播图
			AppFileUtils.removeFileByPath(rotation.getRotationImage());
		}
		rotationDao.deleteRotationByArray(rotationIds);
	}
	
	/**
	 * 根据轮播图id查询轮播图信息
	 * @param rotationId 轮播图id
	 * @return 返回轮播图对象
	 */
	public Rotation getRotationId(Integer rotationId) {
		return this.rotationDao.getRotationId(rotationId);
	}
}
