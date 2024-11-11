package com.ruanyuan.sys.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ruanyuan.sys.constast.SysConstast;
import com.ruanyuan.sys.utils.AppFileUtils;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.RandomUtils;
/**
 * 文件控制类
 * @author 
 * @Data 2020年4月14日 上午11:15:10
 */
@Controller
@RequestMapping("file")
public class FileController {
	/**
	  * 添加
	 * 
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping("uploadFile")
	@ResponseBody
	public DataGridView uploadFile(MultipartFile mf) throws IllegalStateException, IOException {
		// 文件上传的父目录
		String parentPath = AppFileUtils.getPath();
		// 得到当前日期作为文件夹名称
		String dirName = RandomUtils.getCurrentDateForString();
		// 构造文件夹对象
		File dirFile = new File(parentPath, dirName);
		if (!dirFile.exists()) {
			dirFile.mkdirs();// 创建文件夹
		}
		// 得到文件原名
		String oldName = mf.getOriginalFilename();
		// 根据文件原名得到新名
		String newName = RandomUtils.createFileNameUseTime(oldName,SysConstast.FILE_UPLOAD_TEMP);
		File dest = new File(dirFile, newName);
		mf.transferTo(dest);
		Map<String,Object> map=new HashMap<>();
		map.put("src", dirName+"/"+newName);
		return new DataGridView(map);
	}
	/**
	 * 不下载只显示
	 */
	@RequestMapping("downloadShowFile")
	public ResponseEntity<Object> downloadShowFile(String path, HttpServletRequest request) {
		return AppFileUtils.downloadFile(request, path, "");	
	}
	
	/**
	 * 下载图片
	 * @param path
	 * @param response
	 * @return
	 */
	@RequestMapping("downloadFile")
	public ResponseEntity<Object> downloadFile(String path, HttpServletRequest request) {
		String oldName="";
		return AppFileUtils.downloadFile(request, path, oldName);	
	}
	
}
