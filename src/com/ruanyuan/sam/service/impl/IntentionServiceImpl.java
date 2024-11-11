package com.ruanyuan.sam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruanyuan.bas.pojo.Customer;
import com.ruanyuan.bas.vo.CustomerVo;
import com.ruanyuan.sam.dao.IntentionDao;
import com.ruanyuan.sam.pojo.Intention;
import com.ruanyuan.sam.service.IntentionService;
import com.ruanyuan.sam.vo.IntentionVo;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.ResultObj;
import com.ruanyuan.sys.utils.WebUtils;
/**
 * 意向业务类
 * @date 2020年4月15日
 */
@Service
public class IntentionServiceImpl implements IntentionService {
	
	@Autowired
	private IntentionDao intentionDao;
	
	/**
	 * 查询所有意向信息
	 * @param intention:意向封装类
	 * @return:返回意向信息集合
	 */
	public DataGridView getAllIntention(IntentionVo intentionVo) {
		//开启分页
		Page<Object> page  = PageHelper.startPage(intentionVo.getPage(),intentionVo.getLimit());
		//根据条件查新样片数据
		List<Intention> data = this.intentionDao.getAllIntention(intentionVo);
		//返回layui封装得 数据对象 
		return new DataGridView(page.getTotal(),data);
	}

	/**
	 * 添加意向信息
	 * @param intentionVo:意向封装类
	 * @return:返回受影响行数
	 */
	public int addIntention(IntentionVo intentionVo) {
		int count = this.intentionDao.addIntention(intentionVo);
		return count;
	}

	/**
	 * 修改意向信息
	 * @param intentionVo:意向封装类
	 * @return:返回受影响行数
	 */
	public int updateIntention(IntentionVo intentionVo) {
		int count = this.intentionDao.updateIntention(intentionVo);
		return count;
	}

	/**
	 * 单个删除意向信息
	 * @param intentionId:意向编号
	 * @return:返回受影响行数
	 */
	public int deleteIntentionByIntentionId(Integer intentionId) {
		int count = this.intentionDao.deleteIntentionByIntentionId(intentionId);
		return count;
	}

	/**
	 * 批量删除意向信息
	 * @param ids:意向编号数组
	 * @return:返回受影响行数
	 */
	public int deleteIntentionByArray(List<Integer> ids) {
		int count = this.intentionDao.deleteIntentionByArray(ids);
		return count;
	}

	/**
	 * 根据客户id查询意向信息
	 * @param customerId 客户id
	 * @return 返回意向对象集合
	 */
	public List<Intention> getIntentionCustomerId(Integer customerId) {
		List<Intention> list = this.intentionDao.getIntentionCustomerId(customerId);
		return list;
	}

	/**
	 * 根据意向编号查询意向信息
	 * @param intentionId:意向编号
	 * @return:意向信息
	 */
	public Intention getIntentionById(Integer intentionId) {
		return this.intentionDao.getIntentionById(intentionId);
	}
	/**
	 * 根据条件查询样片数据
	 */
	public List<Intention> getIntentionCustomerIdAndSampleId(IntentionVo intentionVo) {
		//根据条件查新样片数据
		List<Intention> data = this.intentionDao.getIntentionCustomerIdAndSampleId(intentionVo);
		return data;
	}
	/**
	 * 前台
	 * 根据客户id查询意向信息
	 */
	@Override
	public DataGridView getIntentionByCustomerId(IntentionVo intentionVo) {
		//如果session中客户不为空，则向包装类中插入客户编号数据
		if(WebUtils.getHttpSession().getAttribute("CUSTOMER")!=null) {
			Customer customer=(Customer) WebUtils.getHttpSession().getAttribute("CUSTOMER");
			//将客户编号放入包装类中
			intentionVo.setCustomerId(customer.getCustomerId());
		}
		//开启分页
		Page<Object> page  = PageHelper.startPage(intentionVo.getPage(),intentionVo.getLimit());
		List<Intention> data=intentionDao.getIntentionByCustomerId(intentionVo.getCustomerId());
		System.out.print(data);
		return new DataGridView(page.getTotal(),data);
	}
	/**
	 * 根据意向编号删除意向样片
	 */
	@Override
	public Integer deleteIntentionByIntentionIdz(Integer intentionId) {
		return intentionDao.deleteIntentionByIntentionId(intentionId);
	}

}
