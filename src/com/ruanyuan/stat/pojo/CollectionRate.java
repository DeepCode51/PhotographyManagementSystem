package com.ruanyuan.stat.pojo;

import com.ruanyuan.sam.pojo.Sample;

/**
 * 收藏统计
 * @author 
 */
public class CollectionRate extends Sample{
//	样片名称
	private String sampleName;
//  收藏率
	private float collRate;


	public String getSampleName() {
		return sampleName;
	}


	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}


	public float getCollRate() {
		return collRate;
	}


	public void setCollRate(float collRate) {
		this.collRate = collRate;
	}


	public CollectionRate() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CollectionRate(String sampleName, float collRate) {
		super();
		this.sampleName = sampleName;
		this.collRate = collRate;
	}


	@Override
	public String toString() {
		return "CollectionRate [sampleName=" + sampleName + ", collRate=" + collRate + "]";
	}

}
