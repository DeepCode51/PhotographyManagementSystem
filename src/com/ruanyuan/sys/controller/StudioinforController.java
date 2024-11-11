package com.ruanyuan.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruanyuan.sys.service.StudioinforService;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.ResultObj;
import com.ruanyuan.sys.vo.StudioinforVo;

/**
* 工作室信息控制类
* @author
* @Data :2020年4月22日 下午9:24:00
*/
@RestController
@RequestMapping("studioinfor")
public class StudioinforController {
	
	@Autowired
	private StudioinforService studioinforService;
	
	/**
	 * 查询工作室信息
	 * @param studioinforVo 工作室信息
	 * @return
	 */
	@RequestMapping("getAllStudioinfor")
	public DataGridView getAllStudioinfor(StudioinforVo studioinforVo) {
		return studioinforService.getAllStudioinfor(studioinforVo);
	}
	
	/**
	 * 修改工作室信息
	 * @param studioinforVo 工作室信息
	 * @return
	 */
	@RequestMapping("updateStudioinfor")
	public ResultObj updateStudioinfor(StudioinforVo studioinforVo) {
		try {
			//修改工作室信息
			studioinforService.updateStudioinfor(studioinforVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}

}
