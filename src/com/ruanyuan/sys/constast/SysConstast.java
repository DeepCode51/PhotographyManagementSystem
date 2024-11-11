package com.ruanyuan.sys.constast;

/**
 * 
 * 常量接口
 * @Data 2020年4月7日 下午1:30:52
 */
public interface SysConstast {
	
	/**
	 * 权限
	 */
	String JURISDICTION_NO = "您没有权限";
	
	String USER_LOGIN_ERROR_MSG = "用户名或密码不正确";
	/**
	 * 登录状态
	 */
	String LOGIN_SUCCESS = "登录成功";
	String LOGIN_ERROR = "登录失败";
	/**
	 * 注册状态
	 */
	String REGISTER_SUCCESS = "注册成功";
	String REGISTER_ERROR = "注册失败";
	/**
	 * 可用状态
	 */
	Integer AVAILABLE_TRUE = 1;
	Integer AVAILABLE_FALSE = 0;
	/**
	 * 临时文件标记
	 */
	String FILE_UPLOAD_TEMP = "_temp";
	/**
	 * 操作状态
	 */
	String ADD_SUCCESS="添加成功";
	String ADD_ERROR="添加失败";
	
	String UPDATE_SUCCESS="更新成功";
	String UPDATE_ERROR="更新失败";
	
	String DELETE_SUCCESS="删除成功";
	String DELETE_ERROR="删除失败";
	
	String RESET_SUCCESS="重置成功，密码为123456";
	String RESET_ERROR="重置失败";
	
	String DISPATCH_SUCCESS="分配成功";
	String DISPATCH_ERROR="分配失败";
	
	String UPLOAD_SUCCESS="上传成功";
	String UPLOAD_ERROR="上传失败";
	
	Integer CODE_SUCCESS=1; //操作成功
	Integer CODE_ERROR=-1;//失败
	/**
	 * 公用常量
	 */
	Integer CODE_ZERO = 0;
	Integer CODE_ONE = 1;
	Integer CODE_TWO = 2;
	Integer CODE_THREE = 3;
	/**
	 * 默认密码配置
	 */
	String USER_DEFAULT_PWD = "123456";
	/**
	 * 验证码不正确
	 */
	String USER_LOGIN_CODE_ERROR_MSG = "验证码不正确";
	
	/**
	 * 客户下有信息，无法删除
	 */
	String DELETE_CUSTOMER_NO = "该客户下有其他信息，无法删除";
	
	/**
	 * 用户下有信息，无法删除
	 */
	String DELETE_USER_NO = "该用户下有其他信息，无法删除";
	/**
	 * 您没有权限,已发送请求，请等待审批
	 */
	String JURISDICTION_NO_TI = "您没有权限,已发送请求，请等待审批";
	/**
	 * 样片下有信息无法删除
	 */
	String DELETE_SAMPLE_SUCCESS = "样片下有信息无法删除";
	/**
	 * 密码不正确
	 */
	String UPDATE_PWD_ERROR = "密码不正确";
	/**
	 * 订单中存在信息，无法删除
	 */
	String DELETE__HASORDER = "订单中存在信息，无法删除";
	
	/**
	 * 留言下有信息，无法删除
	 */
	String DELETE_MESSAGE_NO = "留言下有信息，无法删除";
	/**
	 * 类别下有信息，无法删除
	 */
	String DELETE_TYPE_NO = "类别下有信息，无法删除";
	/**
	 * 余额不足
	 */
	String PRICE_NO = "余额不足";
	/**
	 * 样片名重复
	 */
	String SAMPLENAME_HAVE = "样片名重复";
	/**
	 * 未到过期时间  无法过期
	 */
	String TIME_BEOVERDUE = "未到过期时间  无法过期";
	/**
	 * 审批中存在未审批的样片
	 */
	String APPROVAL_HAS_SAMPLE_UNPROCESSED = "该样片正在处理中，请稍后操作";
	/**
	 * 该留言已有回复，请前往回复管理查看
	 */
	String MESSAGE_HAS_REPLY = "该留言已有回复，请前往回复管理查看";
	/**
	 * 该留言没有回复，请前往回复管添加回复
	 */
	String MESSAGE_NO_REPLY = "该留言没有回复，请前往回复管添加回复";
}
