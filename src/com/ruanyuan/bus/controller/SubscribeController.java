package com.ruanyuan.bus.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruanyuan.bas.dao.CustomerDao;
import com.ruanyuan.bas.pojo.Customer;
import com.ruanyuan.bus.dao.SubscribeDao;
import com.ruanyuan.bus.pojo.Subscribe;
import com.ruanyuan.bus.service.SubscribeService;
import com.ruanyuan.bus.vo.SubscribeVo;
import com.ruanyuan.sam.service.SampleService;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.ResultObj;

import cn.hutool.crypto.SecureUtil;

/**
 * 预约控制类
 * @author 
 * @Data 2020年4月15日 下午1:36:18
 */
@RestController
@RequestMapping("subscribe")
public class SubscribeController {
	
	@Autowired
	private SubscribeService subscribeService;
	@Autowired
	private SampleService sampleService;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private SubscribeDao subscribeDao;
	/**
	 * 查询预约数据
	 * @param subscribeVo 预约包装类
	 * @return
	 */
	@RequestMapping("getAllSubscribe")
	public DataGridView getAllSubscribe(SubscribeVo subscribeVo) {
		return subscribeService.getAllSubscribe(subscribeVo);		
	}
	
	/**
	 * 根据客户Id和样片Id
	 * @param subscribeVo:预约封装类
	 * @return:返回layui格式的预约信息对象
	 */
	@RequestMapping("getSubscribeByCustomerIdAndSampleId")
	public List<Subscribe> getSubscribeByCustomerIdAndSampleId(SubscribeVo subscribeVo) {
		//查询预约信息	
		return subscribeService.getSubscribeByCustomerIdAndSampleId(subscribeVo);
	}
	
	/**
	 * 根据客户Id查询预约数据
	 * @param subscribeVo 预约包装类
	 * @return
	 */
	@RequestMapping("getPcSubscribeByCustomerId")
	public DataGridView getPcSubscribeByCustomerId(SubscribeVo subscribeVo) {
		return subscribeService.getSubscribyCustomerId(subscribeVo);
	}
	/**
	 * 添加预约
	 *@param subscribeVo 预约包装类
	 *@return 返回状态信息
	 */
	@RequestMapping("addPcSubscribe")
	public ResultObj addPcSubscribe(SubscribeVo subscribeVo) {
		try {
			//根据样片Id获取样片的价格
			String price = sampleService.getSampleId(subscribeVo.getSampleId()).getSamplePrice();
			subscribeVo.setPrice(price);
			//添加预约状态 默认待审批
			subscribeVo.setSubState(1);
			//获取当前时间
			Date date = new Date();
			SimpleDateFormat dateFormat_min=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");//设置当前时间的格式，为年-月-日 时-分-秒
			String subeTime = dateFormat_min.format(date);
			subscribeVo.setSubeTime(subeTime);
			//添加预约
			subscribeService.addSubscribe(subscribeVo);
			//预约量+1
			sampleService.updateSampleBySubscribeCountAddOne(subscribeVo.getSampleId());
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	/**
	 * 修改预约
	 *@param subscribeVo 预约包装类
	 *@return 返回状态信息
	 */
	@RequestMapping("updateSubscribe")
	public ResultObj updateSubscribe(SubscribeVo subscribeVo) {
		try {
			//修改预约
			subscribeService.updateSubscribe(subscribeVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	/**
	 * 审核通过
	 *@param subscribeId 预约编号
	 *@param subState 预约状态
	 *@return 返回状态信息
	 */
	@RequestMapping("updateSubscribeByauditPass")
	public ResultObj updateSubscribeByauditPass(Integer subId,Integer subState) {
		try {
			//修改预约
			SubscribeVo subscribeVo = new SubscribeVo();
			Subscribe subscribeBySubId = subscribeService.getSubscribeBySubId(subId);
			System.out.println(subscribeBySubId);
			if(subState==4) {
				Date date = new Date();
				SimpleDateFormat dateFormat_min=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");//设置当前时间的格式，为年-月-日 时-分-秒
				String sysTime = dateFormat_min.format(date);
				Subscribe subscribeVo1 = new Subscribe();
				subscribeVo1.setSubeTime(sysTime);
				subscribeVo1.setSubId(subId);
			    if((subscribeDao.getSubscribeBySysTimeAndSubId(subscribeVo1))!=null) {
			    	subscribeVo.setSubId(subId);
					subscribeVo.setSubState(subState);
					subscribeService.updateSubscribe(subscribeVo);
					return ResultObj.UPDATE_SUCCESS;
			    }else {
			    	return ResultObj.TIME_BEOVERDUE;
			    }
			}else {
				subscribeVo.setSubId(subId);
				subscribeVo.setSubState(subState);
				subscribeService.updateSubscribe(subscribeVo);
				return ResultObj.UPDATE_SUCCESS;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	/**
	 * 生成订单
	 *@param subscribeId 预约编号
	 *@param subState 预约状态
	 *@return 返回状态信息
	 */
	@RequestMapping("updateSubscribeBygenerateOrder")
	public ResultObj updateSubscribeBygenerateOrder(SubscribeVo subscribeVo ) {
		try {
			System.out.println(subscribeVo.getCustomerId());
			Customer customerById = customerDao.getCustomerId(subscribeVo.getCustomerId());
			String password = customerById.getPassword();
			//加密
			String pwd = SecureUtil.md5(subscribeVo.getCustomerpwd());
			//修改预约
			if(pwd.equals(password)) {
				Integer updateSubscribeBygenerateOrder = subscribeService.updateSubscribeBygenerateOrder(subscribeVo,customerById);
				if(updateSubscribeBygenerateOrder>0) {
					return ResultObj.UPDATE_SUCCESS;
				}else {
					return ResultObj.PRICE_NO;
				}
			}else {
				return ResultObj.UPDATE_PWD_ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	/**
	 * 根据预约ID查询
	 *@param subId 预约包装类
	 *@return 返回预约对象
	 */
	@RequestMapping("getSubscribeBysubId")
	public Subscribe getSubscribeBysubId(Integer subId) {
		return subscribeService.getSubscribeBySubId(subId);
	}
	
	/**
	 * 前台删除预约
	 *@param subscribeVo 预约包装类
	 *@return 返回状态信息
	 */
	@RequestMapping("deletePcSubscribe")
	public ResultObj deletePcSubscribe(SubscribeVo subscribeVo) {
		try {
			//查询预约信息	
			List<Subscribe> list = subscribeService.getSubscribeByCustomerIdAndSampleId(subscribeVo);
			for (Subscribe subscribe : list) {
				//删除预约
				int count = subscribeService.deleteSubscribeBySubId(subscribe.getSubId());
				if(count>0) {
					//预约量-1
					sampleService.updateSampleBySubscribeCountReduceOne(subscribe.getSampleId());
				}else {
					return ResultObj.DELETE_ERROR;
				}
			}
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	/**
	 * 删除预约
	 *@param subscribeVo 预约包装类
	 *@return 返回状态信息
	 */
	@RequestMapping("deleteSubscribeBysubscribeId")
	public ResultObj deleteSubscribeBysubscribeId(Integer subId) {
		try {
			//删除预约
			int deleteSubscribeBySubId = subscribeService.deleteSubscribeBySubId(subId);
			if(deleteSubscribeBySubId>0) {
				return ResultObj.DELETE_SUCCESS;
			}else {
				return ResultObj.DELETE__HASORDER;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	/**
	 * 批量删除预约
	 *@param ids 图片Id
	 *@return 返回状态信息
	 */
	@RequestMapping("deleteSubscribeByArray")
	public ResultObj deleteSubscribeByArray(@Param("ids")Integer[] ids) {
		List<Integer> idlist = new ArrayList<Integer>();
		for (int i = 0; i < ids.length; i++) {
			idlist.add(ids[i]);
		}
		try {
			//批量删除预约
			int deleteSubscribeByArray = subscribeService.deleteSubscribeByArray(idlist);
			if(deleteSubscribeByArray>0) {
				return ResultObj.DELETE_SUCCESS;
			}else {
				return ResultObj.DELETE__HASORDER;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	

}
