package com.ruanyuan.sys.dao;

import java.util.List;

import com.ruanyuan.sys.pojo.User;

/**
 * 用户数据访问接口
 * @Data 2020年4月12日 下午2:07:43
 */
public interface UserDao {
	/**
	 * 根据用户名密码 查询用户信息
	 *@param user 用户对象
	 *@return 用户对象
	 */
	public User getUserByLoginNameAndPassword(User user);

	/**
	 * 查询所有用户
	 * @param user 用户对象
	 * @return 返回用户集合
	 */
	public List<User> getUserAll(User user);
	
	/**
	 * 添加用户
	 * @param user 用户对象
	 * @return 返回影响的行数
	 */
	public int addUser(User user);
	
	/**
	 * 修改用户
	 * @param user 用户对象
	 * @return 返回影响的行数
	 */
	public int updateUser(User user);
	
	/**
	 * 删除用户
	 * @param userId 用户id
	 * @return 返回影响的行数
	 */
	public int deleteUser(Integer userId);
	
	/**
	 * 批量删除用户
	 * @param userIds 用户id数组
	 * @return 返回影响的行数
	 */
	public int deleteUserByArray(List<Integer> userIds);
	
	/**
	 * 重置密码
	 * @param userId 用户id
	 * @return 返回影响的行数
	 */
	public int updateUserPwd(Integer userId);
	
	/**
	 * 根据用户编号查询用户信息
	 * @param userId:用户编号
	 * @return:返回用户信息
	 */
	public User getUserByUserId(Integer userId);
	/**
	 * 根据用户id查询用户信息
	 * @param userId 用户id
	 * @return 返回用户对象
	 */
	public User getUserId(Integer userId);
	
	/**
	 * 账号查重
	 * @param loginName 账号
	 * @return 返回用户对象
	 */
	public User getUserLoginName(String loginName);
	
	/**
	 * 查询所有职位
	 * @return 返回职位集合
	 */
	public List<String> getUserByJob();
	
}
