package com.ruanyuan.bus.dao;

import java.util.List;

import com.ruanyuan.bus.pojo.Approval;

/**
 * 审批数据访问接口 
 * @Data 2020年4月13日 下午12:15:37
 */
public interface ApprovalDao {
	/**
	 * 查询审批信息
	 *@param approval 审批对象
	 *@return 审批信息集合
	 */
	public List<Approval> getAllApproval(Approval approval);
	/**
	  * 添加审批
	 *@param approval 审批对象
	 *@return 返回受影响得行数
	 */
	public int addApproval(Approval approval);
	/**
	  * 修改审批
	 *@param approval 审批对象
	 *@return 返回受影响得行数
	 */
	public int updateApprovalByApprovalId(Approval approval);
	/**
	  *  根据审批编号删除审批
	 *@param approvalId 审批编号
	 *@return 返回受影响得行数
	 */
	public int deleteApprovalByApprovalId(Integer approvalId);
	/**
	 * 根据审批Id查询信息
	 *@param approvalId 审批Id
	 *@return 审批对象
	 */ 
	public Approval getApprovalByApprovalId(Integer approvalId);
	/**
	 * 批量删除审批
	 * @param ids:审批编号数组
	 * @return:返回受影响行数
	 */
	public int deleteApprovalByArray(List<Integer> ids);
	/**
	 * 通过样片Id查询审批信息
	 *@param sampleId 样片Id
	 *@return
	 */
	public List<Approval> getApprovalBySampleId(Integer sampleId);
	
	
}
