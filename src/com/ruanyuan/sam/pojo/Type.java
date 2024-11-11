package com.ruanyuan.sam.pojo;
/**
 * 类别实体类
 * @Data 2020年4月12日 下午4:10:52
 */
public class Type {

	/**
	 * 类别编号
	 */
	private Integer typeId;
	/**
	 * 类别名称
	 */
	private String typeName;
	/**
	 * 描述
	 */
	private String remark;
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Type [typeId=" + typeId + ", typeName=" + typeName + ", remark=" + remark + "]";
	}
	
	
}
