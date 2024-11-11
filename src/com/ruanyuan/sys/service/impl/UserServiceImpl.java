package com.ruanyuan.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruanyuan.sam.dao.SampleDao;
import com.ruanyuan.sam.pojo.Sample;
import com.ruanyuan.sys.constast.SysConstast;
import com.ruanyuan.sys.dao.HandleDao;
import com.ruanyuan.sys.dao.LoginDao;
import com.ruanyuan.sys.dao.ReplyDao;
import com.ruanyuan.sys.dao.UserDao;
import com.ruanyuan.sys.pojo.Handle;
import com.ruanyuan.sys.pojo.Login;
import com.ruanyuan.sys.pojo.Reply;
import com.ruanyuan.sys.pojo.User;
import com.ruanyuan.sys.service.UserService;
import com.ruanyuan.sys.utils.AppFileUtils;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.WebUtils;
import com.ruanyuan.sys.vo.UserVo;

import cn.hutool.crypto.SecureUtil;
/**
 * 用户业务类
 * @author qwj
 * @Data 2020年4月12日 下午2:24:52
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private HandleDao handleDao;
	@Autowired
	private LoginDao loginDao;
	@Autowired
	private ReplyDao replyDao;
	@Autowired
	private SampleDao sampleDao;
	
	/**
	 * 用户登录
	 */
	public User login(UserVo userVo) {
		//调用hutool工具 生成密文
		String pwd = SecureUtil.md5(userVo.getPassword());
		userVo.setPassword(pwd);
		//调用userDao的根据用户名 和密码 查询 用户的方法
		return userDao.getUserByLoginNameAndPassword(userVo);
	}

	/**
	 * 查询所有用户
	 */
	public DataGridView getUserAll(UserVo userVo) {
		//开启分页
		Page<Object> page = PageHelper.startPage(userVo.getPage(), userVo.getLimit());
		//根据条件查询用户
		List<User> data = userDao.getUserAll(userVo);
		//返回layui封装的数据对象
		return new DataGridView(page.getTotal(), data);
	}
	
	/**
	 * 添加用户
	 */
	@Override
	public void addUser(UserVo userVo) {
		//添加工作照
		String filePath=AppFileUtils.updateFileName(userVo.getUserImage(),SysConstast.FILE_UPLOAD_TEMP);
		userVo.setUserImage(filePath);
		//截取密码
		String pwd = userVo.getPassword().substring(0, userVo.getPassword().indexOf(","));
		//调用hutool工具加密
		String password=SecureUtil.md5(pwd);
		userVo.setPassword(password);
		userDao.addUser(userVo);
	}

	/**
	 * 修改用户
	 */
	@Override
	public void updateUser(UserVo userVo) {
		String userImage = userVo.getUserImage();
		if(userImage.endsWith(SysConstast.FILE_UPLOAD_TEMP)) {
			String filePath=AppFileUtils.updateFileName(userVo.getUserImage(),SysConstast.FILE_UPLOAD_TEMP);
			userVo.setUserImage(filePath);
			//得到要删除的原来的用户对象
			User user = userDao.getUserId(userVo.getUserId());
			//删除原来的工作照
			AppFileUtils.removeFileByPath(user.getUserImage());
		}
		//获取原来的密码
		userVo.setPassword(userDao.getUserId(userVo.getUserId()).getPassword());
		userDao.updateUser(userVo);
	}
	
	
	/**
	 * 删除用户
	 */
	@Override
	public int deleteUser(Integer userId) {
		//根据用户id查询操作日志信息
		List<Handle> handles = handleDao.getHandleUserId(userId);
		//根据用户id查询登录日志信息
		List<Login> logins = loginDao.getLoginUserId(userId);
		//根据用户id查询回复表信息
		List<Reply> replys = replyDao.getReplyUserId(userId);
		//根据用户id查询样片信息
		List<Sample> samples = sampleDao.getSampleUserId(userId);
		if(handles.size()==0 & logins.size()==0 & replys.size()==0 & samples.size()==0) {
			//得到用户对象
			User user = userDao.getUserId(userId);
			//删除工作照
			AppFileUtils.removeFileByPath(user.getUserImage());
			return userDao.deleteUser(userId);
		}
		return 0;
	}
	
	/**
	 * 批量删除用户
	 */
	@Override
	public int deleteUserByArray(List<Integer> userIds) {
		Integer count = 0;
		for (Integer integer : userIds) {
			//根据用户id查询操作日志信息
			List<Handle> handles = handleDao.getHandleUserId(integer);
			count += handles.size();
			//根据用户id查询登录日志信息
			List<Login> logins = loginDao.getLoginUserId(integer);
			count += logins.size();
			//根据用户id查询回复表信息
			List<Reply> replys = replyDao.getReplyUserId(integer);
			count += replys.size();
			//根据用户id查询样片信息
			List<Sample> samples = sampleDao.getSampleUserId(integer);
			count += samples.size();
		}
		if(count==0) {
			for (Integer integer : userIds) {
				//得到用户对象
				User user = userDao.getUserId(integer);
				//删除工作照
				AppFileUtils.removeFileByPath(user.getUserImage());
			}
			return userDao.deleteUserByArray(userIds);
		}
		return 0;
	}

	/**
	 * 重置密码
	 */
	@Override
	public void updateUserPwd(Integer userId) {
		userDao.updateUserPwd(userId);
	}

	/**
	 * 根据用户编号查询用户信息
	 * @param userId:用户编号
	 * @return:返回用户信息
	 */
	public User getUserByUserId(Integer userId) {
		return userDao.getUserByUserId(userId);
	}
	/**
	 * 账号查重
	 */
	@Override
	public User getUserLoginName(String loginName) {
		return userDao.getUserLoginName(loginName);
	}
	
	/**
	 * 修改用户信息和密码
	 * @param userVo
	 * @return
	 */
	public int updateUserById(UserVo userVo) {
		return userDao.updateUser(userVo);
	}
	/**
	 * 查询所有用户pc
	 */
	public DataGridView getPcUserAll(UserVo userVo) {
		//根据条件查询用户
		List<User> data = userDao.getUserAll(userVo);
		//返回layui封装的数据对象
		return new DataGridView(data);
	}

	/**
	 * 查询所有职位
	 */
	@Override
	public List<String> getUserByJob() {
		return userDao.getUserByJob();
	}
}
