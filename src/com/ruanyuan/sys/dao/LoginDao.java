package com.ruanyuan.sys.dao;

import java.util.List;

import com.ruanyuan.sys.pojo.Login;

/**
 * 登录日志数据接口
 * 
 * @date:2020-04-13 13:48
 *
 */
public interface LoginDao {
	/**
	 * 查询所有的登录者信息
	 * @return 登录者信息
	 */
	public List<Login> getAllLogin(Login login);
	/**
	 * 添加登录者信息
	 * @param login 登录者信息
	 * @return 影响的行数
	 */
	public Integer addLogin(Login login);
	/**
	 * 删除登录者信息
	 * @param loginId id
	 * @return 影响的行数
	 */
	public Integer deleteLogin(Integer loginId);
	/**
	 * 根据用户id查询登录者信息
	 * @param userId 用户id
	 * @return 返回登录日志信息集合
	 */
	public List<Login> getLoginUserId(Integer userId);
	/**
	 * 批量删除登录日志
	 * @param ids:登录编号数组
	 * @return:返回受影响行数
	 */
	public int deleteLoginByArray(Integer[] ids);
}
