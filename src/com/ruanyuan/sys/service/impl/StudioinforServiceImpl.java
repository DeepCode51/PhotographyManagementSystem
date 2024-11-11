package com.ruanyuan.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruanyuan.sys.dao.StudioinforDao;
import com.ruanyuan.sys.pojo.Message;
import com.ruanyuan.sys.pojo.Studioinfor;
import com.ruanyuan.sys.service.StudioinforService;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.vo.StudioinforVo;

/**
* 工作室信息表业务层
* @author
* @Data :2020年4月22日 下午9:18:06
*/
@Service
public class StudioinforServiceImpl implements StudioinforService {

	@Autowired
	private StudioinforDao studioinforDao;
	
	/**
	 * 查询工作室信息
	 */
	@Override
	public DataGridView getAllStudioinfor(StudioinforVo studioinforVo) {
		// TODO Auto-generated method stub
		//开启分页
		Page<Object> page  = PageHelper.startPage(studioinforVo.getPage(),studioinforVo.getLimit());
		//查询工作室数据
		List<Studioinfor> data=this.studioinforDao.getAllStudioinfor(studioinforVo);
		//返回layui封装得 数据对象 
		return new DataGridView(page.getTotal(),data);
	}

	/**
	 * 修改工作室信息
	 */
	@Override
	public void updateStudioinfor(StudioinforVo studioinforVo) {
		// TODO Auto-generated method stub
		studioinforDao.updateStudioinfor(studioinforVo);

	}
	
	/**
	 * 根据工作室编号查询工作室信息
	 * @param stuId:工作室编号
	 * @return:工作室信息
	 */
	public Studioinfor getAllStudioinforById(Integer studId) {
		return studioinforDao.getAllStudioinforById(studId);
	}
}
