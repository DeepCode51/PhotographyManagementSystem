package com.ruanyuan.bus.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruanyuan.bus.pojo.Approval;
import com.ruanyuan.bus.service.ApprovalService;
import com.ruanyuan.bus.vo.ApprovalVo;
import com.ruanyuan.bus.vo.SubscribeVo;
import com.ruanyuan.sys.pojo.User;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.ResultObj;
import com.ruanyuan.sys.utils.WebUtils;

/**
 * 审批控制类
 * @author 
 * @Data 2020年4月16日 上午9:17:27
 */
@RestController
@RequestMapping("approval")
public class ApprovalController {

	@Autowired
	private ApprovalService approvalService;
	/**
	 * 查询审批数据
	 * @param approvalVo 审批包装类
	 * @return
	 */
	@RequestMapping("getAllApproval")
	public DataGridView getAllApproval(ApprovalVo approvalVo) {
		return approvalService.getAllApproval(approvalVo);		
	}
	/**
	 *   根据审批I的查询信息
	 * @param approvalId 审批Id
	 * @return
	 */
	@RequestMapping("getApprovalByapprovalId")
	public Approval getApprovalByapprovalId(Integer approvalId) {
		return approvalService.getApprovalByApprovalId(approvalId);		
	}
	/**
	 * 修改审批
	 *@param approvalVo 审批包装类
	 *@return 返回状态信息
	 */
	@RequestMapping("updateApproval")
	public ResultObj updateApproval(ApprovalVo approvalVo) {
		try {
			User user = (User) WebUtils.getHttpSession().getAttribute("adminUser");
			if(user!=null) {
				if(user.getIsApproval()==1) {
					//添加时间
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String format = df.format(new Date());
					approvalVo.setApprovalTime(format);
					//设置修改人
					approvalVo.setApprover(user.getUserId());
					//修改审批
					approvalService.updateApproval(approvalVo);
					return ResultObj.UPDATE_SUCCESS;
				}else {
					return ResultObj.JURISDICTION_NO;
				}
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	/**
	 * 审批 未通过
	 *@param approvalId 审批Id
	 *@return 返回状态信息
	 */
	@RequestMapping("updateApprovalByNotPass")
	public ResultObj updateApprovalByNotPass(ApprovalVo approvalVo) {
		try {
			User user = (User) WebUtils.getHttpSession().getAttribute("adminUser");
			if(user!=null) {
				if(user.getIsApproval()==1) {
					//添加时间
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String format = df.format(new Date());
					approvalVo.setApprovalTime(format);
					//审批未通过
					approvalVo.setApprovalState(3);
					//设置修改人
					approvalVo.setApprover(user.getUserId());
					approvalService.updateApproval(approvalVo);
					return ResultObj.UPDATE_SUCCESS;
				}else {
					return ResultObj.JURISDICTION_NO;
				}
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	/**
	 * 审批 通过
	 *@param approvalId 审批Id
	 *@return 返回状态信息
	 */
	@RequestMapping("updateApprovalByadopt")
	public ResultObj updateApprovalByadopt(ApprovalVo approvalVo) {
		try {
			User user = (User) WebUtils.getHttpSession().getAttribute("adminUser");
			if(user!=null) {
				if(user.getIsApproval()==1) {
					//设置修改人
					approvalVo.setApprover(user.getUserId());
					approvalService.updateApprovalByadopt(approvalVo);
					return ResultObj.UPDATE_SUCCESS;
				}else {
					return ResultObj.JURISDICTION_NO;
				}
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	/**
	 * 删除审批
	 *@param approvalId 审批Id
	 *@return 返回状态信息
	 */
	@RequestMapping("deleteApprovalByApprovalId")
	public ResultObj deleteApprovalByApprovalId(Integer approvalId) {
		try {
			User user = (User) WebUtils.getHttpSession().getAttribute("adminUser");
			if(user!=null) {
				if(user.getIsApproval()==1) {
					//删除审批
					approvalService.deleteApprovalByApprovalId(approvalId);
					return ResultObj.DELETE_SUCCESS;
				}else {
					return ResultObj.JURISDICTION_NO;
				}
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	/**
	 * 批量删除审批
	 *@param ids 审批Id
	 *@return 返回状态信息
	 */
	@RequestMapping("deleteApprovalByArray")
	public ResultObj deleteApprovalByArray(@Param("ids")Integer[] ids) {
		List<Integer> idlist = new ArrayList<Integer>();
		for (int i = 0; i < ids.length; i++) {
			idlist.add(ids[i]);
		}
		try {
			User user = (User) WebUtils.getHttpSession().getAttribute("adminUser");
			if(user!=null) {
				if(user.getIsApproval()==1) {
					//批量删除审批
					approvalService.deleteApprovalByArray(idlist);
					return ResultObj.DELETE_SUCCESS;
				}else {
					return ResultObj.JURISDICTION_NO;
				}
			}else {
				return null;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
}
