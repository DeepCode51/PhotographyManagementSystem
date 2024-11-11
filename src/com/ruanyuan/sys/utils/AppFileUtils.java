package com.ruanyuan.sys.utils;

import java.io.File;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
public class AppFileUtils {
	/**
	 * 得到文件上传的路径
	 */
	public static String getPath() {
		String path = WebUtils.getHttpServletRequest().getServletContext().getRealPath("/upload/");
		//System.out.println(path);
		return path;
	}
	/**
	 * 文件下载
	 * @param response
	 * @param path
	 * @param oldName
	 * @return
	 */
	public static  ResponseEntity<Object> downloadFile(HttpServletRequest request, String path, String oldName) {
		//4,使用绝对路径+相对路径去找到文件对象
		String PATH = AppFileUtils.getPath();
		File file=new File(PATH,path);
		//5,判断文件是否存在
		if(file.exists()) {
			try {
				try {
					oldName = AppFileUtils.getFilename(request,oldName);
				} catch (Exception e) {
					e.printStackTrace();
				}
				//把file转成一个bytes
				byte [] bytes=FileUtils.readFileToByteArray(file);
				HttpHeaders header=new HttpHeaders();
				//封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
				header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				//设置下载的文件的名称
				header.setContentDispositionFormData("attachment", oldName);
				//创建ResponseEntity对象
				ResponseEntity<Object> entity=
						new ResponseEntity<Object>(bytes, header, HttpStatus.CREATED);
				return entity;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}else {
			return null;
		}
	}
	
	/**
	 * 更改文件名
	 * @param carimg
	 */
	public static String updateFileName(String img,String suffix) {
		//找到文件
		try {
			String PATH = AppFileUtils.getPath();
			File file=new File(PATH,img);
			if(file.exists()) {
				file.renameTo(new File(PATH,img.replace(suffix, "")));
				return img.replace(suffix, "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据相对路径删除硬盘上文件
	 * @param path2
	 */
	public static void deleteFileUsePath(String path) {
		String PATH = AppFileUtils.getPath();
		String realPath=PATH+path;
		//根据文件
		File file=new File(realPath);
		if(file.exists()) {
			file.delete();
		}
	}
	/**
	 * 根据路径 删除图片
	 * @param img
	 */
	public static void removeFileByPath(String img) {
		String PATH = AppFileUtils.getPath();
		try {
			File file=new File(PATH,img);
			if(file.exists()) {
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据浏览器的不同进行编码设置，返回编码后的文件
	 * @param request
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public static String getFilename(HttpServletRequest request, String filename) throws Exception {
		// TODO Auto-generated method stub
		//IE不同版本User-Agent中出现的关键词
		String[] IEBrowserKeyWords= {"MSIE","Trident","Edge"};
		//获取请求头代理信息
		String userAgent = request.getHeader("User-Agent");
		for (String keyWord : IEBrowserKeyWords) {
			if(userAgent.contains(keyWord)) {
				//IE内核浏览器，统一为UTF-8编码显示
				return URLEncoder.encode(filename,"UTF-8");
			}
		}
		//谷歌等其他浏览器统一为ISO-8859-1编码显示
		return new String(filename.getBytes("UTF-8"),"ISO-8859-1");
	}
}
