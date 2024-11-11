package com.ruanyuan.sam.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ruanyuan.sam.pojo.Sample;
import com.ruanyuan.sam.vo.SampleVo;
import com.ruanyuan.sys.utils.DataGridView;

/**
 * 样片业务接口
 * @author 
 * @Data 2020年4月13日 下午7:33:13
 */
public interface SampleService {
	/**
	  *  查询样片信息
	 *@param sampleVo 样片包装
	 *@return 返回 layui封装得数据对象
	 */
	public DataGridView getAllSample(SampleVo sampleVo);	
	/**
	 * 添加修改方法下拉类别框
	 * @param sampleVo
	 * @return
	 */
	public List<Sample> getAllSample1(SampleVo sampleVo);
	/**
	 * 添加样片
	 *@param sampleVo 样片包装类
	 */
	public void addSample(SampleVo sampleVo);
	/**
	 * 根据样片Id在线预约量加一
	 *@return 受影响得行数
	 */
	public int updateSampleBySubscribeCountAddOne(Integer sampleId);
	/**
	 * 根据样片Id在线预约量减一
	 *@return 受影响得行数
	 */
	public int updateSampleBySubscribeCountReduceOne(Integer sampleId);
	/**
	 * 根据样片Id在线收藏量加一
	 *@return 受影响得行数
	 */
	public int updateSampleByCollectionCountAddOne(Integer sampleId);
	/**
	 * 根据样片Id在线收藏量减一
	 *@return 受影响得行数
	 */
	public int updateSampleByCollectionCountReduceOne(Integer sampleId);
	/**
	 * 根据样片Id在线订单量加一
	 *@return 受影响得行数
	 */
	public int updateSampleByOrderCountAddOne(Integer sampleId);
	/**
	 * 根据样片Id在线订单量减一
	 *@return 受影响得行数
	 */
	public int updateSampleByOrderCountReduceOne(Integer sampleId);
	/**
	 *  前台展示样片信息
	 * @return DataGridView
	 */
	public DataGridView getPcSample();
	/**
	 * 根据类别id查询样片信息（pc）
	 * @param sampleVo 包装类
	 * @return 返回 layui封装得数据对象 
	 */
	public DataGridView getAllTypeSample(SampleVo sampleVo);
	/**
	 * 根据样片id查询样片信息
	 * @param sampleId 样片id
	 * @return 返回样片对象
	 */
	public Sample getSampleId(Integer sampleId);
	/**
	 * 变更样片状态
	 *@param sampleId  样片Id
	 *@param sampleLogo  样片 要更改得样片状态
	 */
	public void updateSampleLogo(Integer sampleId,Integer sampleLogo);
	
	/**
	 * 根据样片Id删除样片
	 *@param sampleId 样片Id
	 */
	public Integer deleteSampleBySampleId(Integer sampleId);
	/**
	 * 根据样片名称查询
	 *@param sampleName
	 *@return
	 */
	public Sample getSampleBySampleName(String sampleName);
	/**
	 * 修改样片
	 *@param sampleVo 样片包装类
	 *@param request 
	 */
	public void updateSample(SampleVo sampleVo);
	/**
	 * 查询样片信息(前台)
	 * @return 返回样片信息集合
	 */
	public List<Sample> getAllSample();
	
}
