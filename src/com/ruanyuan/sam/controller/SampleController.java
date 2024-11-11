package com.ruanyuan.sam.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruanyuan.bus.pojo.Approval;
import com.ruanyuan.bus.service.ApprovalService;
import com.ruanyuan.bus.vo.ApprovalVo;
import com.ruanyuan.sam.pojo.Sample;
import com.ruanyuan.sam.service.SampleService;
import com.ruanyuan.sam.vo.SampleVo;
import com.ruanyuan.sys.pojo.User;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.ResultObj;
import com.ruanyuan.sys.utils.WebUtils;
/**
 *    样片控制类
 * @author
 * @Data 2020年4月13日 下午8:26:26
 */
@RestController
@RequestMapping("sample")
public class SampleController {

	@Autowired
	private SampleService sampleService;
	@Autowired
	private ApprovalService approvalService;
	/**
	 * 查询样片信息
	 *@param sampleVo 样片包装类
	 *@return layui 封装得数据对象
	 */
	@RequestMapping("getAllSample")
	public DataGridView getAllSample(SampleVo sampleVo) {
		//查询样片
		return sampleService.getAllSample(sampleVo);
	}
	
	/**
	 * 前台展示样片信息
	 * 
	 * @return json格式类别信息
	 */
	@RequestMapping("getPcSample")
	public DataGridView getPcSample(SampleVo sampleVo) {
		//开启分页
		Page<Object> page  = PageHelper.startPage(sampleVo.getPage(),sampleVo.getLimit());
		//根据条件查询样片数据
		List<Sample> data = sampleService.getAllSample1(sampleVo);
		//返回layui封装得 数据对象 
		return new DataGridView(page.getTotal(),data);
		
	}
	/**
	 * 前台展示样片标识信息
	 * 
	 * @return json格式类别信息
	 */
	@RequestMapping("getPcSampleLogo")
	public List<Integer> getPcSampleLogo(SampleVo sampleVo) {
		//根据条件查询样片数据
		List<Sample> data = sampleService.getAllSample1(sampleVo);
		List<Integer> dataListNoDupAndSort = removeDuplicateUser(data);
		System.out.println(dataListNoDupAndSort);
		return dataListNoDupAndSort;
		
	}
	/**
	 * 样片查重
	 */
	@RequestMapping("getSampleBySampleName")
	public Boolean getSampleBySampleName(String sampleName) {
		if(sampleService.getSampleBySampleName(sampleName)==null) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 添加修改下拉列表框
	 * @param sampleVo
	 * @return
	 */
	@RequestMapping("getAllSample1")
	public List<Sample> getAllSample1(SampleVo sampleVo) {
		//查询样片
		return sampleService.getAllSample1(sampleVo);
	}
	/**
	 * 删除样片
	 *@param sampleId 样片Id
	 *@return 返回状态信息
	 */
	@RequestMapping("deleteSampleBySampleId")
	public ResultObj deleteSampleBySampleId(Integer sampleId) {
		try {
			//删除样片
			Integer deleteSampleBySampleId = sampleService.deleteSampleBySampleId(sampleId);
			if(deleteSampleBySampleId!=null) {
				return ResultObj.DELETE_SUCCESS;
			}else {
				return ResultObj.DELETE_SAMPLE_SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	/**
	 * 添加样片
	 *@param sampleVo 样片包装类
	 *@return layui 封装得数据对象
	 */
	@RequestMapping("addSample")
	public ResultObj addSample(SampleVo sampleVo) {
		try {
			sampleService.addSample(sampleVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	/**
	 * 修改样片
	 *@param sampleVo 样片包装类
	 *@return layui 封装得数据对象
	 */
	@RequestMapping("updateSample")
	public ResultObj updateSample(SampleVo sampleVo) {
		System.out.println(sampleVo);
		try {
			Sample sampleId = sampleService.getSampleId(sampleVo.getSampleId());
			if(sampleId.getSampleName().equals(sampleVo.getSampleName())) {
				sampleService.updateSample(sampleVo);
				return ResultObj.UPDATE_SUCCESS;
			}else {
				Sample sampleBySampleName = sampleService.getSampleBySampleName(sampleVo.getSampleName());
				if(sampleBySampleName!=null) {
					return ResultObj.SAMPLENAME_HAVE;
				}else {
					sampleService.updateSample(sampleVo);
					return ResultObj.UPDATE_SUCCESS;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	/**
	 * 根据类别查询样片信息(pc)
	 * @param typeId 类别id
	 * @return layui 封装得数据对象
	 */
	@RequestMapping("getAllTypeSample")
	public DataGridView getAllTypeSample(Integer typeId) {
		SampleVo sampleVo = new SampleVo();
		sampleVo.setTypeId(typeId);
		return sampleService.getAllTypeSample(sampleVo);
		
	}
	/**
	 * 根据样片id查询样片信息
	 */
	@RequestMapping("getSampleId")
	public Sample getSampleId(Integer sampleId) {
		return sampleService.getSampleId(sampleId);
	}
	
	/**
	 * 变更样片状态
	 */
	@RequestMapping("updateSampleLogo")
	public ResultObj updateSampleLogo(Integer sampleId,Integer sampleLogo) {
		System.out.println(sampleLogo);
		try {
			//验证用户权限
			User user = (User) WebUtils.getHttpSession().getAttribute("adminUser");
			if(user!=null) {
				if(user.getIsApproval()==1) {
					//修改样片状态
					sampleService.updateSampleLogo(sampleId,sampleLogo);
					return ResultObj.UPDATE_SUCCESS;
				}else {
					//判断审批表中是否存在未审批的字段
					int haha = 0;
					List<Approval> la = approvalService.getApprovalBySampleId(sampleId);
					for (Approval approval : la) {
						if(approval.getApprovalState()==1) {
							haha = 1;
						}
					}
					System.out.println(haha);
					if(haha>0) {
						return ResultObj.APPROVAL_HAS_SAMPLE_UNPROCESSED;
					}else {
						ApprovalVo approvalVo = new ApprovalVo();
						//添加时间
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String format = df.format(new Date());
						approvalVo.setApprovalTime(format);
						//设置审批内容
						approvalVo.setApprovalCount(sampleLogo);
						//设置被审批人
						approvalVo.setApprovedPerson(user.getUserId());
						//设置审批状态
						approvalVo.setApprovalState(1);
						//设置样片编号
						approvalVo.setSampleId(sampleId);
						//添加审批
						approvalService.addApproval(approvalVo);
						return ResultObj.JURISDICTION_NO_TI;
					}
					
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
	 * ampleLogo去重复方法
	 * @param users
	 * @return
	 */
	private static ArrayList<Integer> removeDuplicateUser(List<Sample> data) {
        Set<Integer> set = new HashSet<Integer>();
		for (Sample sample : data) {
			  Integer SampleLogo = sample.getSampleLogo();
			  	if (SampleLogo != null) {
				  if (!set.contains(SampleLogo)) { //set中不包含重复的
					  set.add(SampleLogo);
				} else {
					continue;
				 }
			  	}
			}
        return new ArrayList<Integer>(set);
	}
}