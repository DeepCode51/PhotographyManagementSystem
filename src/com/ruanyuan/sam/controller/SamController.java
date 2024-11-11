package com.ruanyuan.sam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面路由器
 * @author 
 * @Data 2020年4月13日 下午8:46:35
 */
@Controller
@RequestMapping("sam")
public class SamController {
	/**
	 * 跳转到样片管理页 
	 */
	@RequestMapping("toSampleManager")
	public String toSampleManager() {
		return "admin/sample/sample/sampleManager";
	}
	
	
	
	
	/**
	 * 跳转到类别管理页 
	 */
	@RequestMapping("toTypeManager")
	public String toTypeManager() {
		return "admin/sample/type/typeManager";
	}
	
	/**
	 * 跳转到图片管理页 
	 */
	@RequestMapping("toImageManager")
	public String toImageManager() {
		return "admin/sample/image/imageManager";
	}
	
	/**
	 * 跳转到轮播图管理页
	 */
	@RequestMapping("toRotationManager")
	public String toRotationManager() {
		return "admin/sample/rotation/rotationManager";
	}
	
	/**
	 * 跳转到意向展示页面
	 * @return
	 */
	@RequestMapping("toIntentionManager")
	public String toIntentionManager() {
		return "admin/sample/intention/intentionManager";
	}
}
