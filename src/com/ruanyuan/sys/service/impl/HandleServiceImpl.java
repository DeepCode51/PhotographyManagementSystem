package com.ruanyuan.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruanyuan.sys.dao.HandleDao;
import com.ruanyuan.sys.pojo.Handle;
import com.ruanyuan.sys.service.HandleService;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.vo.HandleVo;
/**
 * 操作日志事务类
 * @date 2020年4月16日
 */
@Service
public class HandleServiceImpl implements HandleService {

	@Autowired
	private HandleDao handleDao;
	
	/**
	 * 查询所有的操作信息
	 * @return 返回layui格式操作信息
	 */
	public DataGridView getAllHandle(HandleVo handleVo) {
		//开启分页
		Page<Object> page  = PageHelper.startPage(handleVo.getPage(),handleVo.getLimit());
		//根据条件查询样片数据
		List<Handle> data=this.handleDao.getAllHandle(handleVo);
		//返回layui封装得 数据对象 
		return new DataGridView(page.getTotal(),data);
	}

	/**
	 * 添加操作日志信息
	 * @param handle
	 * @return 影响的行数
	 */
	public void addHandle(HandleVo handleVo) {
		// TODO Auto-generated method stub
		this.handleDao.addHandle(handleVo);
	}

	/**
	 * 删除操作日志信息
	 * @param handleId id
	 * @return 影响的行数
	 */
	public void deleteHandle(Integer handleId) {
		// TODO Auto-generated method stub
		this.handleDao.deleteHandle(handleId);
	}

	/**
	 * 根据用户id查询
	 * @param userId 用户id
	 * @return 返回layui格式操作信息
	 */
	public DataGridView getHandleUserId(Integer userId) {
		// TODO Auto-generated method stub
		List<Handle> data = this.handleDao.getHandleUserId(userId);
		return new DataGridView(data);
	}

	/**
	 * 批量删除操作日志
	 * @param ids:操作编号数组
	 * @return:返回受影响行数
	 */
	public void deleteHandleByArray(Integer[] ids) {
		// TODO Auto-generated method stub
		this.handleDao.deleteHandleByArray(ids);
	}

}
