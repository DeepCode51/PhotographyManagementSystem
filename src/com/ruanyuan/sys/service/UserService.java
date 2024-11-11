package com.ruanyuan.sys.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ruanyuan.sam.vo.SampleVo;
import com.ruanyuan.sys.pojo.User;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.vo.UserVo;

/**
 * 用户业务接口 
 * @Data 2020年4月12日 下午2:18:51
 */
public interface UserService {
	/**
	 * 用户登录
	 *@param userVo 用户包装类
	 *@return 用户对象 
	 */
	public User login(UserVo userVo); 
	
	/**
	 * 查询所有用户
	 * @param userVo 用户包装类
	 * @return layui封装格式数据
	 */
	public DataGridView getUserAll(UserVo userVo);
	
	/**
	 * 添加用户
	 * @param userVo 用户包装类
	 */
	public void addUser(UserVo userVo);
	
	/**
	 * 修改用户
	 * @param userVo 用户包装类
	 */
	public void updateUser(UserVo userVo);
	
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
	public void updateUserPwd(Integer userId);
	
	/**
	 * 根据用户编号查询用户信息
	 * @param userId:用户编号
	 * @return:返回用户信息
	 */
	public User getUserByUserId(Integer userId);
	
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
	
	/**
	 * 修改用户信息和密码
	 * @param userVo
	 * @return
	 */
	public int updateUserById(UserVo userVo);
	/**
	 * 查询所有用户pc
	 */
	public DataGridView getPcUserAll(UserVo userVo);
}
