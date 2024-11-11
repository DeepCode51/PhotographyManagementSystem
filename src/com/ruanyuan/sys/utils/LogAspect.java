package com.ruanyuan.sys.utils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruanyuan.sys.dao.HandleDao;
import com.ruanyuan.sys.pojo.Handle;
import com.ruanyuan.sys.pojo.User;
import com.ruanyuan.sys.service.HandleService;
import com.ruanyuan.sys.vo.HandleVo;
/**
 * 操作日志工具类
 * @date 2020年4月22日
 */
@Aspect
public class LogAspect {
	//@Autowired
	//private HandleService handleService;
	@Autowired
	private HandleDao handleDao;
	@Autowired
	private HttpSession session;
	
	
	/**
     * 添加业务逻辑方法切入点
     */
    @Pointcut("execution(* com.ruanyuan.*.service.impl.*.add*(..))")
    public void insertCell() {}

    /**
     * 修改业务逻辑方法切入点
     */
    @Pointcut("execution(* com.ruanyuan.*.service.impl.*.update*(..))")
    public void updateCell() {}

    /**
     * 删除业务逻辑方法切入点
     */
    @Pointcut("execution(* com.ruanyuan.*.service.impl.*.delete*(..))")
    public void deleteCell() {}

    /**
     * 添加操作日志(后置通知)
     *
     * @param joinPoint
     * @param object
     */
    @AfterReturning(value = "insertCell()", argNames = "joinPoint,object", returning = "object")
    public void insertLog(JoinPoint joinPoint,Object object)throws Throwable{
    	
    	 if (joinPoint.getArgs() == null) {// 没有参数
             return;
         }
         // 获取方法名
         String methodName = joinPoint.getSignature().getName();
         // 获取操作内容
         String opContent = optionContent(joinPoint.getArgs(), methodName);
         
         Handle handle = new Handle();
         User user = (User) session.getAttribute("adminUser");
         handle.setUserId(user.getUserId());
         handle.setHandleContent("添加"+getMethodChineseName(methodName));
         //添加操作时间
 		 SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		 handle.setHandleTime(date.format(new Date()));
 		 
 		 handleDao.addHandle(handle);
    }
    
    /**
     * 管理员修改操作日志(后置通知)
     *
     * @param joinPoint
     * @param object
     * @throws Throwable
     */
    @AfterReturning(value = "updateCell()", argNames = "joinPoint,object", returning = "object")
    public void updateLog(JoinPoint joinPoint, Object object) throws Throwable {
    	// 判断参数
        if (joinPoint.getArgs() == null) {// 没有参数
            return;
        }
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取操作内容
        String opContent = optionContent(joinPoint.getArgs(), methodName);

        // 创建日志对象
        Handle handle = new Handle();
        User user = (User) session.getAttribute("adminUser");
        handle.setUserId(user.getUserId());
        handle.setHandleContent("修改"+getMethodChineseName(methodName));
        //添加操作时间
		SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		handle.setHandleTime(date.format(new Date()));
		 
		handleDao.addHandle(handle);
        
    }
    
    /**
     * 删除操作
     *
     * @param joinPoint
     * @param object
     */
    @AfterReturning(value = "deleteCell()", argNames = "joinPoint,object", returning = "object")
    public void deleteLog(JoinPoint joinPoint, Object object) throws Throwable {
    	// 判断参数
        if (joinPoint.getArgs() == null) {// 没有参数
            return;
        }
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        
        // 创建日志对象
        Handle handle = new Handle();
        User user = (User) session.getAttribute("adminUser");
        handle.setUserId(user.getUserId());
        handle.setHandleContent("删除"+getMethodChineseName(methodName));
        //添加操作时间
		SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		handle.setHandleTime(date.format(new Date()));
		 
		handleDao.addHandle(handle);
    }
    	
    
    /**
     * 使用Java反射来获取被拦截方法(insert、update)的参数值， 将参数值拼接为操作内容
     *
     * @param args
     * @param mName
     * @return
     */
    public String optionContent(Object[] args, String mName) {
        if (args == null) {
            return null;
        }
        StringBuffer rs = new StringBuffer();
        rs.append(mName);
        String className = null;
        int index = 1;
        // 遍历参数对象
        for (Object info : args) {
            // 获取对象类型
            className = info.getClass().getName();
            className = className.substring(className.lastIndexOf(".") + 1);
            rs.append("[参数" + index + "，类型:" + className + "，值:");
            // 获取对象的所有方法
            Method[] methods = info.getClass().getDeclaredMethods();
            // 遍历方法，判断get方法
            for (Method method : methods) {
                String methodName = method.getName();
                // 判断是不是get方法
                if (methodName.indexOf("get") == -1) {// 不是get方法
                    continue;// 不处理
                }
                Object rsValue = null;
                try {
                    // 调用get方法，获取返回值
                    rsValue = method.invoke(info);
                } catch (Exception e) {
                    continue;
                }
                // 将值加入内容中
                rs.append("(" + methodName + ":" + rsValue + ")");
            }
            rs.append("]");
            index++;
        }
        return rs.toString();
    }

    /**
     * 判断操作的中文名（根据自己项目而定）
     * @param methodName
     * @return
     */
    public String getMethodChineseName(String methodName){
    	if (methodName.contains("User")) {
			return "用户";
		}else if (methodName.contains("user")) {
			return "用户";
		}else if (methodName.contains("Customer")) {
			return "客户";
		}else if (methodName.contains("customer")) {
			return "客户";
		}else if (methodName.contains("Type")) {
			return "样片类别";
		}else if (methodName.contains("type")) {
			return "样片类别";
		}else if (methodName.contains("Sample")) {
			return "样片";
		}else if (methodName.contains("sample")) {
			return "样片";
		}else if (methodName.contains("Image")) {
			return "图片";
		}else if (methodName.contains("image")) {
			return "图片";
		}else if (methodName.contains("Intention")) {
			return "意向样片";
		}else if (methodName.contains("intention")) {
			return "意向样片";
		}else if (methodName.contains("Rotation")) {
			return "轮播";
		}else if (methodName.contains("rotation")) {
			return "轮播";
		}else if (methodName.contains("Subscribe")) {
			return "预约";
		}else if (methodName.contains("subscribe")) {
			return "预约";
		}else if (methodName.contains("Order")) {
			return "订单";
		}else if (methodName.contains("order")) {
			return "订单";
		}else if (methodName.contains("Collect")) {
			return "收藏";
		}else if (methodName.contains("collect")) {
			return "收藏";
		}else if (methodName.contains("Comment")) {
			return "评论";
		}else if (methodName.contains("comment")) {
			return "评论";
		}else if (methodName.contains("Message")) {
			return "留言";
		}else if (methodName.contains("message")) {
			return "留言";
		}else if (methodName.contains("Reply")) {
			return "回复留言";
		}else if (methodName.contains("reply")) {
			return "回复留言";
		}else if (methodName.contains("Approval")) {
			return "审批";
		}else if (methodName.contains("approval")) {
			return "审批";
		}else if (methodName.contains("Login")) {
			return "登录日志";
		}else if (methodName.contains("login")) {
			return "登录日志";
		}else if (methodName.contains("Handle")) {
			return "操作日志";
		}else if (methodName.contains("handle")) {
			return "操作日志";
		}else {
			return "";
		}
    }

    
}
