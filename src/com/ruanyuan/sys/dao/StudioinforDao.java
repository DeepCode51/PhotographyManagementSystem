package com.ruanyuan.sys.dao;

import java.util.List;

import com.ruanyuan.sys.pojo.Studioinfor;
/**
 * 工作室接口
 *@Date 2020年04月13日 18:09
 */
public interface StudioinforDao {
	/**
	 * 修改工作室信息
	 * @param studioinfor 工作室类
	 * @return 是否修改成功
	 */
	public Integer updateStudioinfor(Studioinfor studioinfor);
	/**
	 * 查询工作室信息管理
	 * @return 返回带工作室有信息的集合
	 */
	public List<Studioinfor> getAllStudioinfor(Studioinfor studioinfor);
	/**
	 * 根据工作室编号查询工作室信息
	 * @param stuId:工作室编号
	 * @return:工作室信息
	 */
	public Studioinfor getAllStudioinforById(Integer studId);
}
