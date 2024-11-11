package com.ruanyuan.sys.service;

import com.ruanyuan.sys.pojo.Studioinfor;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.vo.StudioinforVo;

/**
* 工作室信息业务接口 
* @author
* @Data :2020年4月22日 下午8:54:41
*/
public interface StudioinforService {
	
	/**
	 * 查询工作室信息
	 * @param studioinforVo 工作室信息
	 * @return
	 */
	public DataGridView getAllStudioinfor(StudioinforVo studioinforVo);
	
	/**
	 * 修改工作室信息
	 * @param StudioinforVo 工作室信息
	 */
	public void updateStudioinfor(StudioinforVo studioinforVo);
	
	/**
	 * 根据工作室编号查询工作室信息
	 * @param stuId:工作室编号
	 * @return:工作室信息
	 */
	public Studioinfor getAllStudioinforById(Integer studId);
	
}
