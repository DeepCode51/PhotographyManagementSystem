package com.ruanyuan.sam.dao;

import java.util.List;

import com.ruanyuan.sam.pojo.Type;

/**
 * ww
 * 样片类别数据访问接口
 * @date:2020-04-13 09:58
 *
 */
public interface TypeDao {
	/**
	 * 查询类别信息
	 * @return 返回类别信息集合
	 */
	public List<Type> getAllType(Type type);
	
	/**
	 * 根据类别id 查询类别信息
	 * @param typeId 类别id
	 * @return 返回类别信息集合
	 */
	public Type getTypeByTypeId(Integer typeId);
	
	/**
	 * 添加类别信息
	 * @param type 类别
	 * @return 影响的行数
	 */
	public Integer addType(Type type);
	/**
	 * 修改类别信息
	 * @param  type 类别
	 * @return 影响的行数
	 */
	public Integer updateType(Type type);
	/**
	 * 删除类别信息
	 * @param typeId 要删除的类别id
	 * @return 影响的行数
	 */
	public Integer deleteType(Integer typeId);
	/**
	 * 查询类别信息(前台)
	 * @return 返回类别信息集合
	 */
	public List<Type> getAllType();
}
