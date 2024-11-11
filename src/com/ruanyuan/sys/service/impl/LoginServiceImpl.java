package com.ruanyuan.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruanyuan.sys.dao.LoginDao;
import com.ruanyuan.sys.pojo.Login;
import com.ruanyuan.sys.service.LoginService;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.vo.LoginVo;
/**
 * 登录日志事务类
 * @date 2020年4月16日
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;
	
	/**
	 * 查询所有的登录者信息
	 * @return 登录者信息
	 */
	public DataGridView getAllLogin(LoginVo loginVo) {
		//开启分页
		Page<Object> page  = PageHelper.startPage(loginVo.getPage(),loginVo.getLimit());
		//根据条件查询样片数据
		List<Login> data=this.loginDao.getAllLogin(loginVo);
		//返回layui封装得 数据对象 
		return new DataGridView(page.getTotal(),data);
	}

	/**
	 * 添加登录者信息
	 * @param login 登录者信息
	 * @return 影响的行数
	 */
	public void addLogin(LoginVo loginVo) {
		// TODO Auto-generated method stub
		this.loginDao.addLogin(loginVo);
	}

	/**
	 * 删除登录者信息
	 * @param loginId id
	 * @return 影响的行数
	 */
	public void deleteLogin(Integer loginId) {
		// TODO Auto-generated method stub
		this.loginDao.deleteLogin(loginId);
	}

	/**
	 * 根据用户id查询登录者信息
	 * @param userId 用户id
	 * @return 返回登录日志信息集合
	 */
	public DataGridView getLoginUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 批量删除登录日志
	 * @param ids:登录编号数组
	 * @return:返回受影响行数
	 */
	public void deleteLoginByArray(Integer[] ids) {
		// TODO Auto-generated method stub
		this.loginDao.deleteLoginByArray(ids);
	}

}
