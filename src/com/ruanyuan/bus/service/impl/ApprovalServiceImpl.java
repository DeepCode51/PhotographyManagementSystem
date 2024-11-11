package com.ruanyuan.bus.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruanyuan.bus.dao.ApprovalDao;
import com.ruanyuan.bus.pojo.Approval;
import com.ruanyuan.bus.service.ApprovalService;
import com.ruanyuan.bus.vo.ApprovalVo;
import com.ruanyuan.sam.dao.SampleDao;
import com.ruanyuan.sam.vo.SampleVo;
import com.ruanyuan.sys.utils.DataGridView;
/**
 *   审批业务类
 * @author 
 * @Data 2020年4月16日 上午9:13:16
 */
@Service
public class ApprovalServiceImpl implements ApprovalService {

	@Autowired
	private ApprovalDao approvalDao;
	
	@Autowired
	private SampleDao sampleDao;
	/**
	 * 查询所有的审批信息
	 * @param ApprovalVo 审批包装类
	 * @return layui封装得数据对象
	 */
	@Override
	public DataGridView getAllApproval(ApprovalVo approvalVo) {
		Page<Object> page = PageHelper.startPage(approvalVo.getPage(), approvalVo.getLimit());
		//查询审批信息
		List<Approval> data = approvalDao.getAllApproval(approvalVo);
		return new DataGridView(page.getTotal(), data);
	}
	/**
	 * 添加审批信息
	 * @param ApprovalVo 审批对象包装类
	 */
	@Override
	public void addApproval(ApprovalVo approvalVo) {
		approvalDao.addApproval(approvalVo);
	}
	/**
	 * 修改审批信息
	 * @param ApprovalVo 审批对象包装类
	 */
	@Override
	public void updateApproval(ApprovalVo approvalVo) {
		
		approvalDao.updateApprovalByApprovalId(approvalVo);
	}
	/**
	   *    根据审查Id查询信息
	 * @param approvalId 审批Id
	 * @return 审批对象
	 */
	@Override
	public Approval getApprovalByApprovalId(Integer approvalId) {
		
		return approvalDao.getApprovalByApprovalId(approvalId);
	}
	/**
	 * 根据审批Id删除审批信息
	 *@param approvalId 审批Id
	 */
	@Override
	public void deleteApprovalByApprovalId(Integer approvalId) {
		approvalDao.deleteApprovalByApprovalId(approvalId);
	}
	/**
	 * 批量删除审批信息
	 *@param ids 审批Id数组
	 *@return  受影响的行数
	 */
	@Override
	public void deleteApprovalByArray(List<Integer> ids) {
		approvalDao.deleteApprovalByArray(ids);

	}
	/**
	 * 审批通过操作
	 *@param approvalVo
	 */
	@Override
	public void updateApprovalByadopt(ApprovalVo approvalVo) {
		//添加时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = df.format(new Date());
		approvalVo.setApprovalTime(format);
		//审批通过
		approvalVo.setApprovalState(2);
		//获取要更改为什么样片得标识
		Integer approvalCount = approvalVo.getApprovalCount();
		//得到要审批得样片
		Integer sampleId = approvalVo.getSampleId();
		SampleVo sampleVo = new SampleVo();
		//修改样片状态
		sampleVo.setSampleLogo(approvalCount);
		sampleVo.setSampleId(sampleId);
		//修改审批状态
		approvalDao.updateApprovalByApprovalId(approvalVo);
		//修改样片状态
		sampleDao.updateSampleBySampleId(sampleVo);
	}
	/**
	 * 通过样片Id查询审批信息
	 *@param sampleId 样片Id
	 *@return
	 */
	public List<Approval> getApprovalBySampleId(Integer sampleId) {
		return approvalDao.getApprovalBySampleId(sampleId);
	}

}
