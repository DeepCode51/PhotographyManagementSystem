package com.ruanyuan.sys.utils;

import com.ruanyuan.sys.constast.SysConstast;
/**
 * 消息码
 * @Data 2020年4月12日 下午7:18:22
 */
public class ResultObj {
	
	
	private Integer code=0;
	private String msg;
	/**
	 * 登录成功
	 */
	public static final ResultObj LOGIN_SUCCESS=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.LOGIN_SUCCESS); 
	/**
	 * 登录失败
	 */
	public static final ResultObj LOGIN_ERROR=new ResultObj(SysConstast.CODE_ERROR, SysConstast.LOGIN_ERROR); 
	/**
	 * 注册成功
	 */
	public static final ResultObj REGISTER_SUCCESS=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.REGISTER_SUCCESS); 
	/**
	 * 注册失败
	 */
	public static final ResultObj REGISTER_ERROR=new ResultObj(SysConstast.CODE_ERROR, SysConstast.REGISTER_ERROR); 
	/**
	 * 添加成功
	 */
	public static final ResultObj ADD_SUCCESS=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.ADD_SUCCESS); 
	/**
	 * 添加失败
	 */
	public static final ResultObj ADD_ERROR=new ResultObj(SysConstast.CODE_ERROR, SysConstast.ADD_ERROR); 
	/**
	 * 更新成功
	 */
	public static final ResultObj UPDATE_SUCCESS=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.UPDATE_SUCCESS); 
	/**
	 * 更新失败
	 */
	public static final ResultObj UPDATE_ERROR=new ResultObj(SysConstast.CODE_ERROR, SysConstast.UPDATE_ERROR); 
	/**
	 * 删除成功
	 */
	public static final ResultObj DELETE_SUCCESS=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.DELETE_SUCCESS); 
	/**
	 * 删除失败
	 */
	public static final ResultObj DELETE_ERROR=new ResultObj(SysConstast.CODE_ERROR, SysConstast.DELETE_ERROR); 
	
	/**
	 * 重置成功
	 */
	public static final ResultObj RESET_SUCCESS=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.RESET_SUCCESS); 
	/**
	 * 重置失败
	 */
	public static final ResultObj RESET_ERROR=new ResultObj(SysConstast.CODE_ERROR, SysConstast.RESET_ERROR); 
	/**
	 * 分配成功
	 */
	public static final ResultObj DISPATCH_SUCCESS=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.DISPATCH_SUCCESS); 
	/**
	 * 分配失败
	 */
	public static final ResultObj DISPATCH_ERROR=new ResultObj(SysConstast.CODE_ERROR, SysConstast.DISPATCH_ERROR); 
	
	/**
	 * 状态码1
	 */
	public static final ResultObj STATUS_TRUE=new ResultObj(SysConstast.CODE_SUCCESS); 
	/**
	 * 状态码-1
	 */
	public static final ResultObj STATUS_FALSE=new ResultObj(SysConstast.CODE_ERROR); 
	
	/**
	 * 客户下有信息，无法删除
	 */
	public static final ResultObj DELETE_CUSTOMER_NO=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.DELETE_CUSTOMER_NO);
	/**
	 * 留言下有信息，无法删除 
	 */
	public static final ResultObj DELETE_MESSAGE_NO=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.DELETE_MESSAGE_NO);
	/**
	 * 类别下有信息，无法删除 
	 */
	public static final ResultObj DELETE_TYPE_NO=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.DELETE_TYPE_NO);
	/**
	 * 用户下有信息，无法删除
	 */
	public static final ResultObj DELETE_USER_NO=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.DELETE_USER_NO);
	/**
	 * 没有权限提示
	 */
	public static final ResultObj JURISDICTION_NO = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.JURISDICTION_NO);
	/**
	 * 没有权限提示
	 */
	public static final ResultObj JURISDICTION_NO_TI = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.JURISDICTION_NO_TI);
	/**
	 * 样片下有信息无法删除
	 */
	public static final ResultObj DELETE_SAMPLE_SUCCESS = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.DELETE_SAMPLE_SUCCESS);
	/**
	 * 密码不正确
	 */
	public static final ResultObj UPDATE_PWD_ERROR = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.UPDATE_PWD_ERROR);
	/**
	   * 订单中存在信息，无法删除
	 */
	public static final ResultObj DELETE__HASORDER = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.DELETE__HASORDER);
	/**
	 * 余额不足
	 */
	public static final ResultObj PRICE_NO = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.PRICE_NO);
	/**
	 * 样片名重复
	 */
	public static final ResultObj SAMPLENAME_HAVE =new ResultObj(SysConstast.CODE_TWO, SysConstast.SAMPLENAME_HAVE);
	/**
	 * 未到过期时间  无法过期
	 */
	public static final ResultObj TIME_BEOVERDUE = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.TIME_BEOVERDUE);
	/**
	 * 审批中存在未审批的样片
	 */
	public static final ResultObj APPROVAL_HAS_SAMPLE_UNPROCESSED =  new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.APPROVAL_HAS_SAMPLE_UNPROCESSED);
	/**
	 * 该留言已有回复，请前往回复管理查看
	 */
	public static final ResultObj MESSAGE_HAS_REPLY = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.MESSAGE_HAS_REPLY);
	/**
	 * 该留言没有回复，请前往回复管添加回复
	 */
	public static final ResultObj MESSAGE_NO_REPLY = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.MESSAGE_NO_REPLY);
	/**
	 * 
	 */
	public static final ResultObj CODE_TWO = new ResultObj(SysConstast.CODE_TWO);;
	private ResultObj(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	public ResultObj(Integer code) {
		super();
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	

}
