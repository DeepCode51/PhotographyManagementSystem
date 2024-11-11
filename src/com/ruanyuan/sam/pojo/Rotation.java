package com.ruanyuan.sam.pojo;
/**
 * 轮播图实体类
* @author
* @Data :2020年4月13日 下午12:56:52
*/
public class Rotation {
	/**
	 * 轮播图编号
	 */
	private Integer rotationId;
	
	/**
	 * 轮播图名称
	 */
	private String rotationName;
	
	/**
	 * 轮播图图片地址
	 */
	private String rotationImage;
	/**
	 * 样片编号
	 */
	private Integer sampleId;
	
	/**
	 * 排序值
	 */
	private Integer ranking;
	/**
	 * 上传轮播图时间
	 */
	private String rotationTime;
	
	/**
	 *附属对象
	 */
	private Sample sample;

	public Integer getRotationId() {
		return rotationId;
	}

	public void setRotationId(Integer rotationId) {
		this.rotationId = rotationId;
	}

	public String getRotationName() {
		return rotationName;
	}

	public void setRotationName(String rotationName) {
		this.rotationName = rotationName;
	}

	public String getRotationImage() {
		return rotationImage;
	}

	public void setRotationImage(String rotationImage) {
		this.rotationImage = rotationImage;
	}

	public Integer getSampleId() {
		return sampleId;
	}

	public void setSampleId(Integer sampleId) {
		this.sampleId = sampleId;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public String getRotationTime() {
		return rotationTime;
	}

	public void setRotationTime(String rotationTime) {
		this.rotationTime = rotationTime;
	}

	public Sample getSample() {
		return sample;
	}

	public void setSample(Sample sample) {
		this.sample = sample;
	}

	@Override
	public String toString() {
		return "Rotation [rotationId=" + rotationId + ", rotationName=" + rotationName + ", rotationImage="
				+ rotationImage + ", sampleId=" + sampleId + ", ranking=" + ranking + ", rotationTime=" + rotationTime
				+ ", sample=" + sample + "]";
	}
}
