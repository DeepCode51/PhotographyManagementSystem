package com.ruanyuan.stat.pojo;

/**
 * 订单率实体类
 * @author 
 *
 */
public class OrdersRate  {
//	样片名称
	private String sampleName;
//	订单选中率
	private float ordersRate;
//	getter/setter方法
	public String getSampleName() {
		return sampleName;
	}
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	public float getOrdersRate() {
		return ordersRate;
	}
	public void setOrdersRate(float ordersRate) {
		this.ordersRate = ordersRate;
	}
	
	public OrdersRate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrdersRate(String sampleName, float ordersRate) {
		super();
		this.sampleName = sampleName;
		this.ordersRate = ordersRate;
	}
	@Override
	public String toString() {
		return "OrdersRate [sampleName=" + sampleName + ", ordersRate=" + ordersRate + "]";
	}

}
