package com.ruanyuan.sam.service;

import java.util.List;

import com.ruanyuan.sam.pojo.Type;
import com.ruanyuan.sam.vo.TypeVo;
import com.ruanyuan.sys.utils.DataGridView;

/**
* @author
* @Data :2020年4月13日 下午9:56:38
*/
public interface TypeService {
	
	/**
	 * 查询类别信息
	 * @param typeVo 类别包装类
	 * @return layui封装得格式
	 */
	public DataGridView getAll(TypeVo typeVo);
	
	/**
	 * 根据类别id 查询类别信息
	 * @param typeId 类别id
	 * @return 返回类别信息集合
	 */
	public Type getTypeByTypeId(Integer typeId);
	
	/**
	 * 查询类别信息
	 * @param typeVo 类别包装类
	 * @return 
	 */
	public List<Type> getAll1(TypeVo typeVo);
	
	/**
	 * 添加类别信息
	 * @param type类别对象
	 */
	public void addType(Type type);
	
	/**
	 *    修改类别信息
	 * @param type 类别对象
	 */
	public void updateType(Type type);
	
	/**
	 * 根据类别ID删除类别信息
	 * @param typeId 类别ID
	 */
	public Integer deleteType(Integer typeId);
	/**
	 * 前台展示类别信息
	 * @param type 类别对象
	 */
	public DataGridView getPcType();
}
