package com.ruanyuan.sys.task;

import java.io.File;
import javax.servlet.ServletContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import com.ruanyuan.sys.constast.SysConstast;
/**
 * 定时清理
 * @author 
 * @Data 2020年4月14日 下午3:18:30
 */
@Component
@EnableScheduling  //开启定时任务
public class RecyleTempFileTask {
	/**
	 * 每天晚上12点执行0 0 0 * * ? 
	 */
	@Scheduled(cron="0 0 0 * * ?")
	public void recyleTempFile() {
		ServletContext context = ContextLoader.getCurrentWebApplicationContext().getServletContext();
		String realPath = context.getRealPath("/upload/");
		File file=new File(realPath);
		deleteFile(file);
	}
	/**
	 * 删除图片
	 * @param file
	 */
	public void deleteFile(File file) {
		if(null!=file) {
			File[] listFiles = file.listFiles();
			if(null!=listFiles&&listFiles.length>0) {
				for (File f : listFiles) {
					if(f.isFile()) {
						if(f.getName().endsWith(SysConstast.FILE_UPLOAD_TEMP)) {
							f.delete();
						}
					}else {
						//如果是文件夹，再递归删除
						deleteFile(f);
					}
				}
			}
		}
	}

}
