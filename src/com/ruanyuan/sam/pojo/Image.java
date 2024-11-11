package com.ruanyuan.sam.pojo;
/**
 * 图片实体类
 * @date 2020年4月13日
 */
public class Image {
	/**
	 * 图片编号
	 */
	private Integer imageId;
	/**
	 * 图片路径
	 */
	private String imageAddress;
	/**
	 * 样片编号，外键
	 */
	private Integer sampleId;
	/**
	 * 附属属性
	 */
	private Sample sample;

	
	
	//属性的getter和setter方法
	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public String getImageAddress() {
		return imageAddress;
	}

	public void setImageAddress(String imageAddress) {
		this.imageAddress = imageAddress;
	}

	public Integer getSampleId() {
		return sampleId;
	}

	public void setSampleId(Integer sampleId) {
		this.sampleId = sampleId;
	}

	public Sample getSample() {
		return sample;
	}

	public void setSample(Sample sample) {
		this.sample = sample;
	}

	@Override
	public String toString() {
		return "Image [imageId=" + imageId + ", imageAddress=" + imageAddress + ", sampleId=" + sampleId
				+ ", sample=" + sample + "]";
	}
	
}
