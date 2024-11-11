package com.ruanyuan.sys.utils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruanyuan.bas.pojo.Collect;
import com.ruanyuan.bas.pojo.Comment;
import com.ruanyuan.bas.pojo.Customer;
import com.ruanyuan.bas.service.CollectService;
import com.ruanyuan.bas.service.CommentService;
import com.ruanyuan.bas.service.CustomerService;
import com.ruanyuan.bus.pojo.Approval;
import com.ruanyuan.bus.pojo.Order;
import com.ruanyuan.bus.pojo.Subscribe;
import com.ruanyuan.bus.service.ApprovalService;
import com.ruanyuan.bus.service.OrderService;
import com.ruanyuan.bus.service.SubscribeService;
import com.ruanyuan.sam.pojo.Image;
import com.ruanyuan.sam.pojo.Intention;
import com.ruanyuan.sam.pojo.Rotation;
import com.ruanyuan.sam.pojo.Sample;
import com.ruanyuan.sam.pojo.Type;
import com.ruanyuan.sam.service.ImageService;
import com.ruanyuan.sam.service.IntentionService;
import com.ruanyuan.sam.service.RotationService;
import com.ruanyuan.sam.service.SampleService;
import com.ruanyuan.sam.service.TypeService;
import com.ruanyuan.sys.dao.HandleDao;
import com.ruanyuan.sys.dao.UserDao;
import com.ruanyuan.sys.pojo.Handle;
import com.ruanyuan.sys.pojo.Message;
import com.ruanyuan.sys.pojo.Reply;
import com.ruanyuan.sys.pojo.User;
import com.ruanyuan.sys.service.HandleService;
import com.ruanyuan.sys.service.LoginService;
import com.ruanyuan.sys.service.MessageService;
import com.ruanyuan.sys.service.ReplyService;
import com.ruanyuan.sys.service.UserService;
import com.ruanyuan.sys.vo.HandleVo;
import com.ruanyuan.sys.vo.UserVo;
/**
 * 记录操作日志
 * @date 2020年4月20日
 */

public class AfterLog implements MethodBeforeAdvice {
	
	//用户
	@Autowired
	private UserService userService;
	// 客户
	@Autowired
	private CustomerService customerService;
	//样片类别
	@Autowired
	private TypeService typeService;
	//样片
	@Autowired
	private SampleService sampleService;
	//图片
	@Autowired
	private ImageService imageService;
	//意向样片
	@Autowired
	private IntentionService intentionService;
	//轮播图
	@Autowired
	private RotationService rotationService;
	//预约
	@Autowired
	private SubscribeService subscribeService;
	//订单
	@Autowired
	private OrderService orderService;
	//收藏
	@Autowired
	private CollectService collectService;
	//评论
	@Autowired
	private CommentService commentService;
	//留言
	@Autowired
	private MessageService messageService;
	//回复
	@Autowired
	private ReplyService replyService;
	//审批
	@Autowired
	private ApprovalService approvalService;
	//登录日志
	@Autowired
	private LoginService LoginService;
	//操作日志
	//@Autowired
	//private HandleService handleService;
	@Autowired
	private HandleDao handleDao;
	@Autowired
	private HttpSession session;

	
	public void before(Method method, Object[] args, Object traget) throws Throwable {
		User user = (User) session.getAttribute("adminUser");
		if(user!=null) {
			//创建操作日志实体类
			Handle handle = new Handle();
			//添加操作时间
			SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			handle.setHandleTime(date.format(new Date()));
			/**
			 * 判断那个表
			 */
			String name=method.getName();
			if (name.contains("User")) {
				name ="用户";
			}else if (name.contains("user")) {
				name ="用户";
			}else if (name.contains("Customer")) {
				name ="客户";
			}else if (name.contains("customer")) {
				name ="客户";
			}else if (name.contains("Type")) {
				name ="样片类别";
			}else if (name.contains("type")) {
				name ="样片类别";
			}else if (name.contains("Sample")) {
				name ="样片";
			}else if (name.contains("sample")) {
				name ="样片";
			}else if (name.contains("Image")) {
				name ="图片";
			}else if (name.contains("image")) {
				name ="图片";
			}else if (name.contains("Intention")) {
				name ="意向样片";
			}else if (name.contains("intention")) {
				name ="意向样片";
			}else if (name.contains("Rotation")) {
				name ="轮播";
			}else if (name.contains("rotation")) {
				name ="轮播";
			}else if (name.contains("Subscribe")) {
				name ="预约";
			}else if (name.contains("subscribe")) {
				name ="预约";
			}else if (name.contains("Order")) {
				name ="订单";
			}else if (name.contains("order")) {
				name ="订单";
			}else if (name.contains("Collect")) {
				name ="收藏";
			}else if (name.contains("collect")) {
				name ="收藏";
			}else if (name.contains("Comment")) {
				name ="评论";
			}else if (name.contains("comment")) {
				name ="评论";
			}else if (name.contains("Message")) {
				name ="留言";
			}else if (name.contains("message")) {
				name ="留言";
			}else if (name.contains("Reply")) {
				name ="回复留言";
			}else if (name.contains("reply")) {
				name ="回复留言";
			}else if (name.contains("Approval")) {
				name ="审批";
			}else if (name.contains("approval")) {
				name ="审批";
			}else if (name.contains("Login")) {
				name ="登录日志";
			}else if (name.contains("login")) {
				name ="登录日志";
			}else if (name.contains("Handle")) {
				name ="操作日志";
			}else if (name.contains("handle")) {
				name ="操作日志";
			}else {
				name =method.getName();
			}
			
			/**
			 * 判断不写入操作日志的表
			 */
			switch (name) {
			case "登录日志":
				//System.out.println("不写入的登录日志表");
				break;
			case "操作日志":
				//System.out.println("不写入的操作日志表");
				break;
			/**
			 * 如果不是以上表则写入操作日志
			 */
			default:
				//参数处理
				String params = ""; 
				//System.out.println("args:"+args);
				for (Object arg : args) {
					System.out.println("arg:"+arg);
					params+=arg;	
				}
				
				//System.out.println("参数"+params+"长度"+params.length());
				/**
				 * 根据类型写入数据
				 */
				
				if(method.getName().contains("update")){
					handle.setUserId(user.getUserId());
					handle.setHandleContent("修改了"+name);
					handleDao.addHandle(handle);
					System.out.println("写入成功");
				}
				if(method.getName().contains("add" )){
					handle.setUserId(user.getUserId());
					handle.setHandleContent("添加了"+name);
					handleDao.addHandle(handle);
					System.out.println("写入成功");
				}
				/**
				 * 删除
				 */
				if(method.getName().contains("del")){
					handle.setUserId(user.getUserId());
					/**
					 * 判断表
					 */
					switch (name) {
					case "用户":
						//判断表查询删除字段
						if (params.contains("[")) {
							String[] spli =params.split(",");
							for (int i = 0; i < spli.length; i++) {
								spli[i] =spli[i].replace("[", "");
								spli[i] =spli[i].replace("]", "");
								//System.out.println(spli[i]);
								String str = spli[i];
								String strs =str.trim();
								Integer num = Integer.parseInt(strs);
								User user1 = userService.getUserByUserId(num);
								handle.setHandleContent("删除了"+user1.getRealName()+name);
								handleDao.addHandle(handle);
								System.out.println("写入成功");
							}
						}else {
							User user1 = userService.getUserByUserId(Integer.parseInt(params));
							handle.setHandleContent("删除了"+user1.getRealName()+name);
							handleDao.addHandle(handle);
							System.out.println("写入成功");
						}
						break;
					case "客户":
						//判断表查询删除字段
						if (params.contains("[")) {
							String[] spli =params.split(",");
							for (int i = 0; i < spli.length; i++) {
								spli[i] =spli[i].replace("[", "");
								spli[i] =spli[i].replace("]", "");
								//System.out.println(spli[i]);
								String str = spli[i];
								String strs =str.trim();
								Integer num = Integer.parseInt(strs);
								Customer customer = customerService.getCustomerId(num);
								handle.setHandleContent("删除了"+customer.getRealName()+name);
								handleDao.addHandle(handle);
								System.out.println("写入成功");
							}
						}else {
							Customer customer = customerService.getCustomerId(Integer.parseInt(params));
							handle.setHandleContent("删除了"+customer.getRealName()+name);
							handleDao.addHandle(handle);
							System.out.println("写入成功");
						}
						break;
					case "样片类别":
						//判断表查询删除字段
						if (params.contains("[")) {
							String[] spli =params.split(",");
							for (int i = 0; i < spli.length; i++) {
								spli[i] =spli[i].replace("[", "");
								spli[i] =spli[i].replace("]", "");
								//System.out.println(spli[i]);
								String str = spli[i];
								String strs =str.trim();
								Integer num = Integer.parseInt(strs);
								Type type = typeService.getTypeByTypeId(num);
								handle.setHandleContent("删除了"+type.getTypeName()+name);
								handleDao.addHandle(handle);
								System.out.println("写入成功");
							}
						}else {
							Type type = typeService.getTypeByTypeId(Integer.parseInt(params));
							handle.setHandleContent("删除了"+type.getTypeName()+name);
							handleDao.addHandle(handle);
							System.out.println("写入成功");
						}
						break;
					case "样片":
						//判断表查询删除字段
						if (params.contains("[")) {
							String[] spli =params.split(",");
							for (int i = 0; i < spli.length; i++) {
								spli[i] =spli[i].replace("[", "");
								spli[i] =spli[i].replace("]", "");
								//System.out.println(spli[i]);
								String str = spli[i];
								String strs =str.trim();
								Integer num = Integer.parseInt(strs);
								Sample sample = sampleService.getSampleId(num);
								handle.setHandleContent("删除了"+sample.getSampleName()+name);
								handleDao.addHandle(handle);
								System.out.println("写入成功");
							}
						}else {
							Sample sample = sampleService.getSampleId(Integer.parseInt(params));
							handle.setHandleContent("删除了"+sample.getSampleName()+name);
							handleDao.addHandle(handle);
							System.out.println("写入成功");
						}
						break;
					case "图片":
						//判断表查询删除字段
						if (params.contains("[")) {
							String[] spli =params.split(",");
							for (int i = 0; i < spli.length; i++) {
								spli[i] =spli[i].replace("[", "");
								spli[i] =spli[i].replace("]", "");
								//System.out.println(spli[i]);
								String str = spli[i];
								String strs =str.trim();
								Integer num = Integer.parseInt(strs);
								Image image = imageService.getImageByImageId(num);
								handle.setHandleContent("删除了编号为"+image.getImageId()+name);
								handleDao.addHandle(handle);
								System.out.println("写入成功");
							}
						}else {
							Image image = imageService.getImageByImageId(Integer.parseInt(params));
							handle.setHandleContent("删除了编号为"+image.getImageId()+name);
							handleDao.addHandle(handle);
							System.out.println("写入成功");
						}
						break;
					case "意向样片":
						//判断表查询删除字段
						System.out.println("params:"+params);
						if (params.contains("[")) {
							String[] spli =params.split(",");
							for (int i = 0; i < spli.length; i++) {
								spli[i] =spli[i].replace("[", "");
								spli[i] =spli[i].replace("]", "");
								//System.out.println(spli[i]);
								String str = spli[i];
								String strs =str.trim();
								Integer num = Integer.parseInt(strs);
								Intention intention = intentionService.getIntentionById(num);
								handle.setHandleContent("删除了编号为"+intention.getIntentionId()+name);
								handleDao.addHandle(handle);
								System.out.println("写入成功");
							}
						}else {
							Intention intention = intentionService.getIntentionById(Integer.parseInt(params));
							handle.setHandleContent("删除了编号为"+intention.getIntentionId()+name);
							handleDao.addHandle(handle);
							System.out.println("写入成功");
						}
						break;
					case "轮播":
						//判断表查询删除字段
						if (params.contains("[")) {
							String[] spli =params.split(",");
							for (int i = 0; i < spli.length; i++) {
								spli[i] =spli[i].replace("[", "");
								spli[i] =spli[i].replace("]", "");
								//System.out.println(spli[i]);
								String str = spli[i];
								String strs =str.trim();
								Integer num = Integer.parseInt(strs);
								Rotation rotation = rotationService.getRotationId(num);
								handle.setHandleContent("删除了"+rotation.getRotationName()+name);
								handleDao.addHandle(handle);
								System.out.println("写入成功");
							}
						}else {
							Rotation rotation = rotationService.getRotationId(Integer.parseInt(params));
							handle.setHandleContent("删除了"+rotation.getRotationName()+name);
							handleDao.addHandle(handle);
							System.out.println("写入成功");
						}
						break;
					case "预约":
						//判断表查询删除字段
						if (params.contains("[")) {
							String[] spli =params.split(",");
							for (int i = 0; i < spli.length; i++) {
								spli[i] =spli[i].replace("[", "");
								spli[i] =spli[i].replace("]", "");
								//System.out.println(spli[i]);
								String str = spli[i];
								String strs =str.trim();
								Integer num = Integer.parseInt(strs);
								Subscribe subscribe = subscribeService.getSubscribeBySubId(num);
								handle.setHandleContent("删除了编号为"+subscribe.getSubId()+name);
								handleDao.addHandle(handle);
								System.out.println("写入成功");
							}
						}else {
							Subscribe subscribe = subscribeService.getSubscribeBySubId(Integer.parseInt(params));
							handle.setHandleContent("删除了编号为"+subscribe.getSubId()+name);
							handleDao.addHandle(handle);
							System.out.println("写入成功");
						}
						break;
					case "订单":
						//判断表查询删除字段
						if (params.contains("[")) {
							String[] spli =params.split(",");
							for (int i = 0; i < spli.length; i++) {
								spli[i] =spli[i].replace("[", "");
								spli[i] =spli[i].replace("]", "");
								//System.out.println(spli[i]);
								String str = spli[i];
								String strs =str.trim();
								Integer num = Integer.parseInt(strs);
								Order order = orderService.getOrderByOrderId(num);
								handle.setHandleContent("删除了"+order.getOrderName()+name);
								handleDao.addHandle(handle);
								System.out.println("写入成功");
							}
						}else {
							Order order = orderService.getOrderByOrderId(Integer.parseInt(params));
							handle.setHandleContent("删除了"+order.getOrderName()+name);
							handleDao.addHandle(handle);
							System.out.println("写入成功");
						}
						break;
					case "收藏":
						//判断表查询删除字段
						if (params.contains("[")) {
							String[] spli =params.split(",");
							for (int i = 0; i < spli.length; i++) {
								spli[i] =spli[i].replace("[", "");
								spli[i] =spli[i].replace("]", "");
								//System.out.println(spli[i]);
								String str = spli[i];
								String strs =str.trim();
								Integer num = Integer.parseInt(strs);
								Collect collect = collectService.getCollectionByCollectId(num);
								handle.setHandleContent("删除了编号为"+collect.getCollectId()+name);
								handleDao.addHandle(handle);
								System.out.println("写入成功");
							}
						}else {
							Collect collect = collectService.getCollectionByCollectId(Integer.parseInt(params));
							handle.setHandleContent("删除了编号为"+collect.getCollectId()+name);
							handleDao.addHandle(handle);
							System.out.println("写入成功");
						}
						break;
					case "评论":
						//判断表查询删除字段
						if (params.contains("[")) {
							String[] spli =params.split(",");
							for (int i = 0; i < spli.length; i++) {
								spli[i] =spli[i].replace("[", "");
								spli[i] =spli[i].replace("]", "");
								//System.out.println(spli[i]);
								String str = spli[i];
								String strs =str.trim();
								Integer num = Integer.parseInt(strs);
								Comment comment = commentService.getCommentByCommId(num);
								handle.setHandleContent("删除了编号为"+comment.getCommId()+name);
								handleDao.addHandle(handle);
								System.out.println("写入成功");
							}
						}else {
							Comment comment = commentService.getCommentByCommId(Integer.parseInt(params));
							handle.setHandleContent("删除了编号为"+comment.getCommId()+name);
							handleDao.addHandle(handle);
							System.out.println("写入成功");
						}
						break;
					case "留言":
						//判断表查询删除字段
						if (params.contains("[")) {
							String[] spli =params.split(",");
							for (int i = 0; i < spli.length; i++) {
								spli[i] =spli[i].replace("[", "");
								spli[i] =spli[i].replace("]", "");
								//System.out.println(spli[i]);
								String str = spli[i];
								String strs =str.trim();
								Integer num = Integer.parseInt(strs);
								Message message = messageService.getMessageById(num);
								handle.setHandleContent("删除了编号为"+message.getMessageId()+name);
								handleDao.addHandle(handle);
								System.out.println("写入成功");
							}
						}else {
							Message message = messageService.getMessageById(Integer.parseInt(params));
							handle.setHandleContent("删除了编号为"+message.getMessageId()+name);
							handleDao.addHandle(handle);
							System.out.println("写入成功");
						}
						break;
					case "回复留言":
						//判断表查询删除字段
						if (params.contains("[")) {
							String[] spli =params.split(",");
							for (int i = 0; i < spli.length; i++) {
								spli[i] =spli[i].replace("[", "");
								spli[i] =spli[i].replace("]", "");
								//System.out.println(spli[i]);
								String str = spli[i];
								String strs =str.trim();
								Integer num = Integer.parseInt(strs);
								Reply reply = replyService.getReplyById(num);
								handle.setHandleContent("删除了编号为"+reply.getReplyId()+name);
								handleDao.addHandle(handle);
								System.out.println("写入成功");
							}
						}else {
							Reply reply = replyService.getReplyById(Integer.parseInt(params));
							handle.setHandleContent("删除了编号为"+reply.getReplyId()+name);
							handleDao.addHandle(handle);
							System.out.println("写入成功");
						}
						break;
					case "审批":
						//判断表查询删除字段
						if (params.contains("[")) {
							String[] spli =params.split(",");
							for (int i = 0; i < spli.length; i++) {
								spli[i] =spli[i].replace("[", "");
								spli[i] =spli[i].replace("]", "");
								//System.out.println(spli[i]);
								String str = spli[i];
								String strs =str.trim();
								Integer num = Integer.parseInt(strs);
								Approval approval = approvalService.getApprovalByApprovalId(num);
								handle.setHandleContent("删除了编号为"+approval.getApprovalId()+name);
								handleDao.addHandle(handle);
								System.out.println("写入成功");
							}
						}else {
							Approval approval = approvalService.getApprovalByApprovalId(Integer.parseInt(params));
							handle.setHandleContent("删除了编号为"+approval.getApprovalId()+name);
							handleDao.addHandle(handle);
							System.out.println("写入成功");
						}
						break;
					default:
						handle.setHandleContent(name+"删除了"+params);
						handleDao.addHandle(handle);
						System.out.println("写入成功");
						break;	
					}
					break;
				}
			}
		}
	}

}
