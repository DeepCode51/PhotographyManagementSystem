package com.ruanyuan.sam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruanyuan.sam.dao.SampleDao;
import com.ruanyuan.sam.dao.TypeDao;
import com.ruanyuan.sam.pojo.Sample;
import com.ruanyuan.sam.pojo.Type;
import com.ruanyuan.sam.service.TypeService;
import com.ruanyuan.sam.vo.TypeVo;
import com.ruanyuan.sys.pojo.Reply;
import com.ruanyuan.sys.utils.DataGridView;

/**
 * 类别业务类
* @author
* @Data :2020年4月13日 下午10:08:37
*/
@Service
public class TypeServiceImpl implements TypeService {
	
	@Autowired
	private TypeDao typeDao;

	@Autowired
	private SampleDao sampleDao;
	
	/**
	 * 查询所有类别信息
	 */
	public DataGridView getAll(TypeVo typeVo) {
		// TODO Auto-generated method stub
		//开启分页
		Page<Object> page  = PageHelper.startPage(typeVo.getPage(),typeVo.getLimit());
		//根据条件查询样片数据
		List<Type> data = this.typeDao.getAllType(typeVo);
		//返回layui封装得 数据对象 
		return new DataGridView(page.getTotal(),data);
	}

	/**
	 * 添加类别信息
	 */
	public void addType(Type type) {
		// TODO Auto-generated method stub
		//类别添加
		typeDao.addType(type);
	}

	/**
	 * 修改类别信息
	 */
	public void updateType(Type type) {
		//类别修改
		typeDao.updateType(type);
	}

	/**
	 * 根据类别ID删除信息
	 */
	public Integer deleteType(Integer typeId) {
		// TODO Auto-generated method stub
		Integer deleteType = null;
		// 根据留言ID查询留言信息
		 List<Sample> listSample = sampleDao.getSampleTypeId(typeId);
		// 判断删除
		if (!(listSample != null && listSample.size() > 0)) {
			deleteType = typeDao.deleteType(typeId);
			return deleteType;
		} else {
			return deleteType;
		}

	}
	/**
	 * 前台展示所有类别信息
	 */
	public DataGridView getPcType() {
		List<Type> data = typeDao.getAllType();
		return  new DataGridView(data);
	}
	
	
	public List<Type> getAll1(TypeVo typeVo) {
		//根据条件查询样片数据
		return this.typeDao.getAllType(typeVo);
	}

	/**
	 * 根据类别id 查询类别信息
	 */
	@Override
	public Type getTypeByTypeId(Integer typeId) {
		// TODO Auto-generated method stub
		return typeDao.getTypeByTypeId(typeId);
	}
}
