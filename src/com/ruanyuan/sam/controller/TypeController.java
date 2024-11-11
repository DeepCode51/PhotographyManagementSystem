package com.ruanyuan.sam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruanyuan.sam.pojo.Type;
import com.ruanyuan.sam.service.TypeService;
import com.ruanyuan.sam.vo.TypeVo;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.ResultObj;

/**
* @author
* @Data :2020年4月13日 下午10:26:20
*/
@RestController
@RequestMapping("type")
public class TypeController {
	
	@Autowired
	private TypeService typeService;

	/**
	 * 查询类别数据
	 * @param typeVo 类别包装类
	 * @return
	 */
	@RequestMapping("getAllType")
	public DataGridView getAllType(TypeVo typeVo) {
		return typeService.getAll(typeVo);		
	}
	
	/**
	 * 添加类别
	 * @param typeVo 类别包装类
	 * @return
	 */
	@RequestMapping("addType")
	public ResultObj addType(TypeVo typeVo) {
		try {
			//添加类别
			typeService.addType(typeVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	
	
	/**
	 * 修改类别
	 *@param typeVo 类别包装类
	 *@return 返回状态信息
	 */
	@RequestMapping("updateType")
	public ResultObj updateType(TypeVo typeVo) {
		try {
			//修改类别
			typeService.updateType(typeVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	
	/**
	 * 根据typeId删除类别
	 *@param typeVo 类别包装类
	 *@return 返回状态信息
	 */
	@RequestMapping("deleteTypeBytypeId")
	public ResultObj deleteTypeBytypeId(Integer typeId) {
		
		try {
			//删除类别
			Integer deleteType = typeService.deleteType(typeId);
			if(deleteType!=null) {
				return ResultObj.DELETE_SUCCESS;
			}else {
				return ResultObj.DELETE_TYPE_NO;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	/**
	 * 前台展示类别信息
	 * 
	 * @return json格式类别信息
	 */
	@RequestMapping("getPcType")
	public DataGridView getPcType() {
		return typeService.getPcType();
		
	}
	/**
	 * 查询类别数据
	 * @param typeVo 类别包装类
	 * @return
	 */
	@RequestMapping("getAllType1")
	public List<Type> getAllType1(TypeVo typeVo) {
		return typeService.getAll1(typeVo);		
	}
	
	/**
	 * 根据类别id查询类别信息
	 * @param typeId 类别id
	 * @return json格式类别信息
	 */
	@RequestMapping("getTypeByTypeId")
	public Type getTypeByTypeId(Integer typeId) {
		return typeService.getTypeByTypeId(typeId);
	}
}
