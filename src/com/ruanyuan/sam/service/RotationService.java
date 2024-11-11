package com.ruanyuan.sam.service;

import java.util.List;

import com.ruanyuan.sam.pojo.Rotation;
import com.ruanyuan.sam.vo.RotationVo;
import com.ruanyuan.sys.utils.DataGridView;

/**
 * 轮播图操作接口
 * 
 * @date:2020-04-14 20:27
 *
 */
public interface RotationService {
	/**
	 * 根据排序值倒叙 查询前二个轮播图
	 * @param ranking 排序值
	 *@return 返回layui封装数据
	 */
	public DataGridView getPcRotation();
	/**
	 * 查询所有轮播图
	 * @param rotationVo 轮播图包装类
	 * @return 返回layui封装数据
	 */
	public DataGridView getRotationAll(RotationVo rotationVo);
	/**
	 * 添加轮播图
	 * @param rotationVo 轮播图包装类
	 */
	public void addRotation(RotationVo rotationVo);
	/**
	 * 修改轮播图
	 * @param rotationVo 轮播图包装类
	 */
	public void updateRotation(RotationVo rotationVo);
	/**
	 * 删除轮播图
	 * @param rotationId 轮播图id
	 */
	public void deleteRotation(Integer rotationId);
	/**
	 * 批量删除轮播图
	 * @param rotationIds 轮播图id数组
	 */
	public void deleteRotationByArray(List<Integer> rotationIds);
	/**
	 * 根据轮播图id查询轮播图信息
	 * @param rotationId 轮播图id
	 * @return 返回轮播图对象
	 */
	public Rotation getRotationId(Integer rotationId);
}
