package com.ruanyuan.sam.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruanyuan.bus.vo.SubscribeVo;
import com.ruanyuan.sam.pojo.Intention;
import com.ruanyuan.sam.service.IntentionService;
import com.ruanyuan.sam.vo.IntentionVo;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.ResultObj;
/**
 * 意向控制类
 * @date 2020年4月15日
 */
@RestController
@RequestMapping("intention")
public class IntentionController {
	@Autowired
	private IntentionService intentionService;
	
	/**
	 * 查询意向信息
	 * @param intentionVo:意向封装类
	 * @return:返回layui格式的意向信息对象
	 */
	@RequestMapping("getAllIntention")
	public DataGridView getAllIntention(IntentionVo intentionVo) {
		//查询意向信息
		return intentionService.getAllIntention(intentionVo);
	}
	/**
	 * 根据客户id查询样片信息
	 * @param IntentionVo:样片包装类
	 * @return:layui封装数据
	 */
	@RequestMapping("getIntentionByCustomerId")
	public DataGridView getIntentionByCustomerId(IntentionVo IntentionVo){
		return intentionService.getIntentionByCustomerId(IntentionVo);
	}
	/**
	 * 查询意向信息1
	 * @param intentionVo:意向封装类
	 * @return:返回layui格式的意向信息对象
	 */
	@RequestMapping("getIntentionCustomerIdAndSampleId")
	public List<Intention> getIntentionCustomerIdAndSampleId(IntentionVo intentionVo) {
		System.out.println(intentionVo);
		//查询意向信息	
		return intentionService.getIntentionCustomerIdAndSampleId(intentionVo);
	}
	/**
	 * 根据意向编号单个删除意向信息
	 * @param IntentionId:意向编号
	 * @return:错误信息
	 */
	@RequestMapping("deleteIntentionByIntentionId")
	public ResultObj deleteIntentionByIntentionId(Integer intentionId) {
		try {
			//删除意向
			intentionService.deleteIntentionByIntentionId(intentionId);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	/**
	 * 前台添加意向
	 *@param subscribeVo 意向包装类
	 *@return 返回状态信息
	 */
	@RequestMapping("addPcIntention")
	public ResultObj addPcIntention(IntentionVo intentionVo) {
		try {
			//添加意向
			Date date = new Date();
			SimpleDateFormat dateFormat_min=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");//设置当前时间的格式，为年-月-日 时-分-秒
			String intentionTime = dateFormat_min.format(date);
			intentionVo.setIntentionTime(intentionTime);
			intentionService.addIntention(intentionVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	/**
	 * 前台删除意向
	 *@param subscribeVo 意向包装类
	 *@return 返回状态信息
	 */
	@RequestMapping("deletePcIntention")
	public ResultObj deletePcIntention(IntentionVo intentionVo) {
		try {
			//查询意向信息	
			List<Intention> list = intentionService.getIntentionCustomerIdAndSampleId(intentionVo);
			for (Intention intention : list) {
				//删除意向
				intentionService.deleteIntentionByIntentionId(intention.getIntentionId());
			}
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	/**
	 * 批量删除意向信息
	 * @param ids:意向编号数组
	 * @return:错误信息
	 */
	@RequestMapping("deleteIntentionByArray")
	public ResultObj deleteIntentionByArray(@Param("ids")Integer[] ids) {
		List<Integer> idlist = new ArrayList<Integer>();
		for (int i = 0; i < ids.length; i++) {
			idlist.add(ids[i]);
		}
		try {
			//删除意向
			intentionService.deleteIntentionByArray(idlist);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	/**
	 * 根据意向编号查询意向信息
	 */
	@RequestMapping("getIntentionById")
	public Intention getIntentionById(Integer intentionId) {
		return this.intentionService.getIntentionById(intentionId);
	}
}
