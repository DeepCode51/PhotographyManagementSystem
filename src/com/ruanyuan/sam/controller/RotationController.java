package com.ruanyuan.sam.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruanyuan.sam.service.RotationService;
import com.ruanyuan.sam.vo.RotationVo;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.ResultObj;

/**
 * 轮播图控制层
 * @author
 *
 */
@RestController
@RequestMapping("rotation")
public class RotationController {
	@Autowired
	private  RotationService rotationService;
	/**
	 * 查询前两个轮播图按照ranking降序
	 */
	@RequestMapping("getPcRotation")
	public DataGridView getPcRotation() {
		return rotationService.getPcRotation();
	}
	
	/**
	 * 查询所有轮播图
	 */
	@RequestMapping("getRotationAll")
	public DataGridView getRotationAll(RotationVo rotationVo) {
		return rotationService.getRotationAll(rotationVo);
	}
	
	/**
	 * 添加轮播图
	 */
	@RequestMapping("addRotation")
	public ResultObj addRotation(RotationVo rotationVo) {
		try {
			rotationService.addRotation(rotationVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	
	/**
	 * 修改轮播图
	 */
	@RequestMapping("updateRotation")
	public ResultObj updateRotation(RotationVo rotationVo) {
		try {
			rotationService.updateRotation(rotationVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	
	/**
	 * 删除轮播图
	 */
	@RequestMapping("deleteRotation")
	public ResultObj deleteRotation(Integer rotationId) {
		try {
			rotationService.deleteRotation(rotationId);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	/**
	 * 批量删除轮播图
	 */
	@RequestMapping("deleteRotationByArray")
	public ResultObj deleteRotationByArray(Integer[] rotationIds) {
		List<Integer> idlist = new ArrayList<Integer>();
		for (int i = 0; i < rotationIds.length; i++) {
			idlist.add(rotationIds[i]);
		}
		try {
			rotationService.deleteRotationByArray(idlist);;
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
}
	
