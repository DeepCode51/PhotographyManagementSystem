package com.ruanyuan.bas.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruanyuan.bas.pojo.Collect;
import com.ruanyuan.bas.service.CollectService;
import com.ruanyuan.bas.vo.CollectVo;
import com.ruanyuan.sam.service.SampleService;
import com.ruanyuan.sys.pojo.User;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.ResultObj;

/**
 * 收藏控制层
 *@Date 2020年04月15日 14:05
 */
@RestController
@RequestMapping("collection")
public class CollectionController {
	//注解注入业务层
	@Autowired
	private CollectService collectService;
	@Autowired
	private SampleService sampleService;
	@Autowired
	private HttpSession session;
	/**
	 * 根据客户编号查询客户信息
	 * @param collectVo
	 * @return 封装数据
	 */
	@RequestMapping("getCollectionByCustomerId")
	public DataGridView getCollectionByCustomerId(CollectVo collectVo) {
		return collectService.getCollectionByCustomerId(collectVo);
	}
	/**
	 * 添加收藏
	 *@param collectVo 收藏包装类
	 *@return 返回状态信息
	 */
	@RequestMapping("addPcCollection")
	public ResultObj addPcCollection(CollectVo collectVo) {
		try {
			//添加时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format = df.format(new Date());
			collectVo.setCollectTime(format);
			List<Collect> data=collectService.getCollectionCustomerIdAndSampleId(collectVo);
			if(data.size()>0) {
				//如果此收藏存在的话 则不能继续添加
				return ResultObj.ADD_ERROR;
			}else {
				collectService.addCollection(collectVo);
				//收藏量+1
				sampleService.updateSampleByCollectionCountAddOne(collectVo.getSampleId());
				return ResultObj.ADD_SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	
	/**
	 * 查询所有收藏信息
	 * @param collectVo:收藏封装类
	 * @return:返回layui格式
	 */
	@RequestMapping("getAllCollect")
	public DataGridView getAllCollect(CollectVo collectVo) {
		return collectService.getAllCollection(collectVo);
	}
	
	/**
	 * 查询所有收藏信息1
	 * @param collectVo:收藏封装类
	 * @return:返回layui格式
	 */
	@RequestMapping("getCollectionCustomerIdAndSampleId")
	public List<Collect> getCollectionCustomerIdAndSampleId(CollectVo collectVo) {
		return collectService.getCollectionCustomerIdAndSampleId(collectVo);
	}
	
	/**
	 * 根据样片编号单个删除收藏
	 * @param sampleId 样片编号
	 * @return:返回状态信息
	 */
	@RequestMapping("deleteCollectBySampleId")
	public ResultObj deleteCollectBySampleId(Integer sampleId) {
		User user = (User)session.getAttribute("adminUser");
		if(user.getIsApproval().equals(1)) {
			try {
				//根据样片编号单个删除收藏
				collectService.deleteCollectBySampleId(sampleId);
				//收藏量-1
				sampleService.updateSampleByCollectionCountReduceOne(sampleId);
				return ResultObj.DELETE_SUCCESS;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ResultObj.DELETE_ERROR;
			}	
		}else {
			return ResultObj.JURISDICTION_NO;
		}
		
	}
	/**
	 * 根据收藏编号单个删除收藏
	 * @param collectId:收藏编号
	 * @return:返回状态信息
	 */
	@RequestMapping("deleteCollectionByCollectId")
	public ResultObj deleteCollectionByCollectId(Integer collectId) {
		try {
			Collect collect = this.collectService.getCollectionByCollectId(collectId); 
			//收藏量-1
			sampleService.updateSampleByCollectionCountReduceOne(collect.getSampleId());
			//根据收藏编号单个删除收藏
			collectService.deleteCollectionByCollectId(collectId);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	/**
	 * 根据收藏编号批量删除收藏信息
	 * @param ids:收藏编号数组
	 * @return:返回状态信息
	 */
	@RequestMapping("deleteCollectionByArray")
	public ResultObj deleteCollectionByArray(Integer[] ids) {
		User user = (User)session.getAttribute("adminUser");
		if(user.getIsApproval().equals(1)) {
			List<Integer> idlist = new ArrayList<Integer>();
			for (int i = 0; i < ids.length; i++) {
				idlist.add(ids[i]);
			}
			try {
				for (Integer collectId : ids) {
					Collect collect = this.collectService.getCollectionByCollectId(collectId); 
					//收藏量-1
					sampleService.updateSampleByCollectionCountReduceOne(collect.getSampleId());
				}
				//根据收藏编号批量删除收藏
				collectService.deleteCollectionByArray(idlist);
				return ResultObj.DELETE_SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
				return ResultObj.DELETE_ERROR;
			}
		}else {
			return ResultObj.JURISDICTION_NO;
		}
		
	}
	
	/**
	 * 前台删除收藏
	 *@param subscribeVo 意向包装类
	 *@return 返回状态信息
	 */
	@RequestMapping("deletePcCollection")
	public ResultObj deletePcCollection(CollectVo collectVo) {
		try {
			//查询收藏信息	
			List<Collect> list = collectService.getCollectionCustomerIdAndSampleId(collectVo);
			for (Collect collect : list) {
				//删除收藏
				collectService.deleteCollectionByCollectId(collect.getCollectId());
				//收藏量-1
				sampleService.updateSampleByCollectionCountReduceOne(collect.getSampleId());
			}
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	/**
	 * 根据收藏编号查询收藏信息
	 * @param customerId:收藏编号
	 * @return:返回收藏信息
	 */
	@RequestMapping("getCollectionByCollectId")
	public Collect getCollectionByCollectId(Integer collectId) {
		return this.collectService.getCollectionByCollectId(collectId);
	}
}
