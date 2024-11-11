package com.ruanyuan.sys.service;

import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.vo.HandleVo;

/**
 * 操作日志事务接口
 * @date 2020年4月16日
 */
public interface HandleService {
	/**
	 * 查询所有的操作信息
	 * @return 返回layui格式操作信息
	 */
	public DataGridView getAllHandle(HandleVo handleVo);
	/**
	 * 添加操作日志信息
	 * @param handle
	 * @return 影响的行数
	 */
	public void addHandle(HandleVo handleVo);
	/**
	 * 删除操作日志信息
	 * @param handleId id
	 * @return 影响的行数
	 */
	public void deleteHandle(Integer handleId);
	/**
	 * 根据用户id查询
	 * @param userId 用户id
	 * @return 返回操作信息集合
	 */
	public DataGridView getHandleUserId(Integer userId);
	/**
	 * 批量删除操作日志
	 * @param ids:操作编号数组
	 * @return:返回受影响行数
	 */
	public void deleteHandleByArray(Integer[] ids);
	
	
}
