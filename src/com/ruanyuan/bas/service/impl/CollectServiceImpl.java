package com.ruanyuan.bas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruanyuan.bas.dao.CollectDao;
import com.ruanyuan.bas.pojo.Collect;
import com.ruanyuan.bas.pojo.Customer;
import com.ruanyuan.bas.service.CollectService;
import com.ruanyuan.bas.vo.CollectVo;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.WebUtils;
/**
 * 收藏业务类
 *@Date 2020年04月15日 14:14
 */
@Service
public class CollectServiceImpl implements CollectService {
	//注解注入dao层
	@Autowired
	private CollectDao collectDao;
	/**
	 * 根据客户编号查询收藏信息
	 */
	@Override
	public DataGridView getCollectionByCustomerId(CollectVo collectVo) {
		//如果session中客户不为空，则向包装类中插入客户编号数据
		if(WebUtils.getHttpSession().getAttribute("CUSTOMER")!=null) {
			Customer customer=(Customer) WebUtils.getHttpSession().getAttribute("CUSTOMER");
			//将客户编号放入包装类中
			collectVo.setCustomerId(customer.getCustomerId());
		}
		Page<Object> page= PageHelper.startPage(collectVo.getPage(), collectVo.getLimit());
		List<Collect> data = collectDao.getCollectionByCustomerId(collectVo);
		for(Collect d:data) {
			System.out.print(d);
		}
		return new DataGridView(page.getTotal(), data);
	}

	/**
	 * 查询所有收藏信息
	 * @param collectVo:收藏分装类
	 * @return:返回layui格式
	 */
	public DataGridView getAllCollection(CollectVo collectVo) {
		//开启分页
		Page<Object> page  = PageHelper.startPage(collectVo.getPage(),collectVo.getLimit());
		//根据条件查寻收藏数据
		List<Collect> data=this.collectDao.getAllCollection(collectVo);
		//返回layui封装得 数据对象 
		return new DataGridView(page.getTotal(),data);
	}

	/**
	 * 添加收藏信息
	 * @param collectVo:收藏分装类
	 * @return:返回受影响行数
	 */
	public void addCollection(CollectVo collectVo) {
		this.collectDao.addCollection(collectVo);
	}

	/**
	 * 修改收藏信息
	 * @param collectVo:收藏分装类
	 * @return:返回受影响行数
	 */
	public void updateCollection(CollectVo collectVo) {
		this.collectDao.updateCollection(collectVo);
	}

	/**
	 * 单个删除收藏信息
	 * @param collectId:收藏编号
	 * @return:返回受影响行数
	 */
	public void deleteCollectionByCollectId(Integer collectId) {
		this.collectDao.deleteCollectionByCollectId(collectId);
	}

	/**
	 * 批量删除收藏信息
	 * @param ids:收藏编号数组
	 * @return:返回受影响行数
	 */
	public void deleteCollectionByArray(List<Integer> ids) {
		this.collectDao.deleteCollectionByArray(ids);
	}

	@Override
	public DataGridView getCollectionCustomerId(CollectVo collectVo) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 根据样片编号查询收藏信息
	 */
	@Override
	public void deleteCollectBySampleId(Integer sampleId) {
		// TODO Auto-generated method stub
		this.collectDao.deleteCollectBySampleId(sampleId);
	}
	
	/**
	 * 根据收藏编号查询收藏信息
	 * @param customerId:收藏编号
	 * @return:返回收藏信息
	 */
	public Collect getCollectionByCollectId(Integer collectId) {
		return this.collectDao.getCollectionByCollectId(collectId);
	}
	/**
	 * 根据客户id和样片Id查询收藏样片信息
	 */
	public List<Collect> getCollectionCustomerIdAndSampleId(CollectVo collectVo) {
		return this.collectDao.getCollectionCustomerIdAndSampleId(collectVo);
	}
	/**
	 * 查询收藏集合
	 */
	@Override
	public List<Collect> getAllCollection() {
		return this.collectDao.getAllCollection();
	}
}
