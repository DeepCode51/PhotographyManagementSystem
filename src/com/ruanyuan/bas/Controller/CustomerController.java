package com.ruanyuan.bas.Controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruanyuan.bas.pojo.Customer;
import com.ruanyuan.bas.service.CustomerService;
import com.ruanyuan.bas.vo.CustomerVo;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.ResultObj;
import com.ruanyuan.sys.utils.WebUtils;

import cn.hutool.crypto.SecureUtil;

/**
 * 客户控制层
 *@Date 2020年04月14日 10:45
 */
@RestController
@RequestMapping("customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	/**
	 * 查询所有客户
	 */
	@RequestMapping("getCustomerAll")
	public DataGridView getCustomerAll(CustomerVo customerVo) {
		return customerService.getCustomerAll(customerVo);
	}
	
	/**
	 * 查询所有返回List集合
	 * @param customerVo
	 * @return
	 */
	@RequestMapping("getCustomerAll1")
	public List<Customer> getCustomerAll1(CustomerVo customerVo) {
		return customerService.getCustomerAll1(customerVo);
	}
	
	/**
	 * 添加客户
	 */
	@RequestMapping("addCustomer")
	public ResultObj addCustomer(CustomerVo customerVo) {
		try {
			customerService.addCustomer(customerVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	/**
	 * 前台添加客户
	 */
	@RequestMapping("addPcCustomer")
	public ResultObj addPcCustomer(CustomerVo customerVo) {
		try {
			customerService.addPcCustomer(customerVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	/**
	 * 修改客户
	 */
	@RequestMapping("updateCustomer")
	public ResultObj updateCustomer(CustomerVo customerVo) {
		try {
			customerService.updateCustomer(customerVo);
			System.out.print("新密码是"+customerVo.getPassword());
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	/**
	 * 前台修改客户信息
	 */
	@RequestMapping("updatePcCustomer")
	public ResultObj updatePcCustomer(CustomerVo customerVo) {
		try {
			customerService.updatePcCustomer(customerVo);
			System.out.print("新密码是"+customerVo.getPassword());
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	/**
	 * 删除客户
	 */
	@RequestMapping("deleteCustomer")
	public ResultObj deleteCustomer(Integer customerId) {
		try {
			if(customerService.deleteCustomer(customerId)==1) {
				return ResultObj.DELETE_SUCCESS;
			}else {
				return ResultObj.DELETE_CUSTOMER_NO;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	/**
	 * 根据客户id查询客户信息
	 */
	@RequestMapping("getCustomerId")
	public Customer getCustomerId(Integer customerId) {
		return customerService.getCustomerId(customerId);
	}
	/**
	 * 重置密码
	 */
	@RequestMapping("updateCustomerPwd")
	public ResultObj updateCustomerPwd(Integer customerId) {
		try {
			customerService.updateCustomerPwd(customerId);
			return ResultObj.RESET_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.RESET_ERROR;
		}
	}
	/**
	 * 账号查重
	 */
	@RequestMapping("getCustomerLoginName")
	public Boolean getCustomerLoginName(String loginName) {
		if(customerService.getCustomerLoginName(loginName)==null) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 验证账号密码是否正确
	 * @param customerVo 包装类
	 * @param model 模型
	 * @return
	 */
	@PostMapping("login")
	public ResultObj login(CustomerVo customerVo) {
		try {
			Customer customer1=customerService.login(customerVo);
			if(customer1 != null) {
				//存放到session
				WebUtils.getHttpSession().setAttribute("CUSTOMER", customer1);
				return ResultObj.LOGIN_SUCCESS;
			}else {
				return ResultObj.LOGIN_ERROR;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultObj.LOGIN_ERROR;
		}	
	}
	/**
	 * 根据客户编号查询客户信息
	 * @param customerId 客户编号
	 * @return 客户信息
	 */
	@RequestMapping("getCustomerById")
	public Customer getCustomerById(Integer customerId,Model model) {
		Customer c=customerService.getCustomerId(customerId);
		model.addAttribute("customer",c);
		return customerService.getCustomerId(customerId);
	}
	/**
	 * 检验输入框密码是否与原密码相等
	 * @param pwd 输入的新密码
	 * @return 是否相等
	 */
	@PostMapping("pwdToMd5")
	public Integer pwdToMd5(String pwd) {
		Customer customer=new Customer();
		String pwd2=SecureUtil.md5(pwd);
		if(WebUtils.getHttpSession().getAttribute("CUSTOMER")!=null) {
			customer=(Customer) WebUtils.getHttpSession().getAttribute("CUSTOMER");
		}
		System.out.println("pwd2:"+pwd2);
		System.out.println("customer:"+customer.getPassword());
		//如果密码是正确的则返回1 否则返回2
		if(pwd2.equals(customer.getPassword())) {
			return 1;
		}else {
			return 2;
		}
	}

}
