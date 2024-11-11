package com.ruanyuan.sam.pojo;

import com.ruanyuan.sys.pojo.User;

/**
 * 样片实体类
 * @Data 2020年4月12日 下午4:17:59
 */
public class Sample {
	/**
	 * 样片编号
	 */
	private Integer sampleId;
	/**
	 * 样片名称
	 */
    private String sampleName;
    /**
	 * 类别编号 外键
	 */
    private Integer typeId;
    /**
           * 上传人编号 
     */
    private Integer userId;
    /**
	 * 样片描述
	 */
    private String sampleStock;
    /**
	 * 样片标识
	 */
    private Integer sampleLogo;
    /**
	 * 样片添加时间
	 */
    private String sampleTime;
    /**
	 * 样片主图片
	 */
    private String sampleImage;
    /**
	 * 样片价格
	 */
    private String samplePrice;
    /**
	 * 收藏量
	 */
    private String collectionCount;
    /**
	 * 订单量
	 */
    private String orderCount;
    /**
	 * 预约量
	 */
    private String subscribeCount;

    /**
     * 附属对象
     */
    private Type type;
    private User user;
	public Integer getSampleId() {
		return sampleId;
	}
	public void setSampleId(Integer sampleId) {
		this.sampleId = sampleId;
	}
	public String getSampleName() {
		return sampleName;
	}
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getSampleStock() {
		return sampleStock;
	}
	public void setSampleStock(String sampleStock) {
		this.sampleStock = sampleStock;
	}
	public Integer getSampleLogo() {
		return sampleLogo;
	}
	public void setSampleLogo(Integer sampleLogo) {
		this.sampleLogo = sampleLogo;
	}
	public String getSampleTime() {
		return sampleTime;
	}
	public void setSampleTime(String sampleTime) {
		this.sampleTime = sampleTime;
	}
	public String getSampleImage() {
		return sampleImage;
	}
	public void setSampleImage(String sampleImage) {
		this.sampleImage = sampleImage;
	}
	public String getSamplePrice() {
		return samplePrice;
	}
	public void setSamplePrice(String samplePrice) {
		this.samplePrice = samplePrice;
	}
	public String getCollectionCount() {
		return collectionCount;
	}
	public void setCollectionCount(String collectionCount) {
		this.collectionCount = collectionCount;
	}
	public String getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(String orderCount) {
		this.orderCount = orderCount;
	}
	public String getSubscribeCount() {
		return subscribeCount;
	}
	public void setSubscribeCount(String subscribeCount) {
		this.subscribeCount = subscribeCount;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Sample [sampleId=" + sampleId + ", sampleName=" + sampleName + ", typeId=" + typeId + ", userId="
				+ userId + ", sampleStock=" + sampleStock + ", sampleLogo=" + sampleLogo + ", sampleTime=" + sampleTime
				+ ", sampleImage=" + sampleImage + ", samplePrice=" + samplePrice + ", collectionCount="
				+ collectionCount + ", orderCount=" + orderCount + ", subscribeCount=" + subscribeCount + ", type="
				+ type + ", user=" + user + "]";
	}
    
    

    
    
	
}
