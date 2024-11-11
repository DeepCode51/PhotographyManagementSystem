package com.ruanyuan.sys.dao;

import java.util.List;

import com.ruanyuan.sys.pojo.Handle;

/**
 * 操作日志数据接口
 * 
 * @date:2020-04-13 13:49
 *
 */
public interface HandleDao {
	/**
	 * 查询所有的操作信息
	 * @return 返回操作信息集合
	 */
	public List<Handle> getAllHandle(Handle handle);
	/**
	 * 添加操作日志信息
	 * @param handle
	 * @return 影响的行数
	 */
	public Integer addHandle(Handle handle);
	/**
	 * 删除操作日志信息
	 * @param handleId id
	 * @return 影响的行数
	 */
	public Integer deleteHandle(Integer handleId);
	/**
	 * 根据用户id查询
	 * @param userId 用户id
	 * @return 返回操作信息集合
	 */
	public List<Handle> getHandleUserId(Integer userId);
	/**
	 * 批量删除操作日志
	 * @param ids:操作编号数组
	 * @return:返回受影响行数
	 */
	public int deleteHandleByArray(Integer[] ids);
}
