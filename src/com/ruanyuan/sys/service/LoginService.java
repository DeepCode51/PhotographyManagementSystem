package com.ruanyuan.sys.service;

import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.vo.LoginVo;

/**
 * 登录日志事务接口
 * @date 2020年4月16日
 */
public interface LoginService {
	/**
	 * 查询所有的登录者信息
	 * @return 登录者信息
	 */
	public DataGridView getAllLogin(LoginVo loginVo);
	/**
	 * 添加登录者信息
	 * @param login 登录者信息
	 * @return 影响的行数
	 */
	public void addLogin(LoginVo loginVo);
	/**
	 * 删除登录者信息
	 * @param loginId id
	 * @return 影响的行数
	 */
	public void deleteLogin(Integer loginId);
	/**
	 * 根据用户id查询登录者信息
	 * @param userId 用户id
	 * @return 返回登录日志信息集合
	 */
	public DataGridView getLoginUserId(Integer userId);
	/**
	 * 批量删除登录日志
	 * @param ids:登录编号数组
	 * @return:返回受影响行数
	 */
	public void deleteLoginByArray(Integer[] ids);
}
