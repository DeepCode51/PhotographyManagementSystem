package com.ruanyuan.bus.service;

import com.ruanyuan.bus.vo.ApprovalVo;

import java.util.List;

import com.ruanyuan.bus.pojo.Approval;
import com.ruanyuan.sys.utils.DataGridView;

/**
 * 审批的业务逻辑层接口
 * @Data 2020年4月13日 下午3:15:36
 */
public interface ApprovalService {
	/**
	 * 查询所有的审批信息
	 * @param ApprovalVo 审批对象
	 * @return layui封装得数据对象
	 */
	public DataGridView getAllApproval(ApprovalVo approvalVo);
	/**
	 * 添加审批信息
	 * @param Approval 审批对象
	 * @return 受影响的行数
	 */
	public void addApproval(ApprovalVo approvalVo);
	/**
	 * 修改审批信息
	 * @param Approval 审批对象
	 * @return 受影响的行数
	 */
	public void updateApproval(ApprovalVo approvalVo);
	/**
	 * 根据审批Id查询审批信息
	 * @param approvalId 审批Id
	 * @return 审批对象
	 */
	public Approval getApprovalByApprovalId(Integer approvalId);
	/**
	 * 根据审批Id删除审批信息
	 *@param approvalId 审批Id
	 *@return  受影响的行数
	 */
	public void deleteApprovalByApprovalId(Integer approvalId);
	/**
	 * 批量删除审批信息
	 *@param ids 审批Id数组
	 *@return  受影响的行数
	 */
	public void deleteApprovalByArray(List<Integer> ids);
	/**
	 * 审批通过操作
	 *@param approvalVo
	 */
	public void updateApprovalByadopt(ApprovalVo approvalVo);
	/**
	 * 通过样片Id查询审批信息
	 *@param sampleId 样片Id
	 *@return
	 */
	public List<Approval> getApprovalBySampleId(Integer sampleId);
}
