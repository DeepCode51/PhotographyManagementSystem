package com.ruanyuan.bas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyuan.bas.pojo.Customer;
import com.ruanyuan.bas.service.CustomerService;
import com.ruanyuan.sys.utils.WebUtils;

/**
 * 前台页面控制
 * @Data 2020年4月13日 下午9:25:28
 */
@Controller
@RequestMapping("pc")
public class PCController {
	@Autowired
	private CustomerService customerService;
	/**
	 * 跳转到前台页面
	 */
	@RequestMapping("index")
	public String getindex() {
		return "pc/index";
	}
	/**
	 * 转发至注册页面
	 * @return 注册页
	 */
	@RequestMapping("toRegister")
	public String toRegister() {
		return "pc/static/register";
	}
	/**
	 * 转发至登录页面
	 * @return 登录页
	 */
	@RequestMapping("toLogin")
	public String toLogin() {
		return "pc/static/login";
	}
	/**
	 * 跳转到样片欣赏
	 */
	@RequestMapping("sampleDetails")
	public String toDetails() {
		return "pc/static/sampleDetails";
	}
	/**
	 * 跳转到样片详情
	 */
	@RequestMapping("sampleAppretion")
	public String getsampleAppretion() {
		return "pc/static/sampleAppretion";
	}
	/**
	 * 跳转到个人中心
	 * @return
	 */
	@RequestMapping("toPersonalCenter")
	public String toPersonalCenter(Model model) {
		//到个人中心时将客户信息放入
		if(WebUtils.getHttpSession().getAttribute("CUSTOMER")!=null) {
			Customer customer=(Customer) WebUtils.getHttpSession().getAttribute("CUSTOMER");
			Customer c2=customerService.getCustomerId(customer.getCustomerId());
			model.addAttribute("customer",c2);
		}
		return "pc/static/personalCenter";
	}
	/**
	 * 跳转到预约查看
	 * @return
	 */
	@RequestMapping("viewSubscribe")
	public String toViewSubscribe() {
		return "pc/static/viewSubscribe";
	}
	/**
	 * 跳转到订单查看
	 * @return
	 */
	@RequestMapping("viewOrders")
	public String toViewOrders() {
		return "pc/static/viewOrders";
	}
	/**
	 * 跳转到选片指南
	 */
	@RequestMapping("guideselection")
	public String getGuideselection() {
		return "pc/guide/guideselection";
	}
	/**
	 * 跳转到关于我们
	 */
	@RequestMapping("getAboutas")
	public String getAboutsas() {
		return "pc/guide/aboutas";
	}
	/**
	 * 跳入个人信息页
	 * @return
	 */
	@RequestMapping("toPersonalInfo")
	public String topersonalInfo(Model model) {
		//到个人中心时将客户信息放入
		if(WebUtils.getHttpSession().getAttribute("CUSTOMER")!=null) {
			Customer customer=(Customer) WebUtils.getHttpSession().getAttribute("CUSTOMER");
			Customer c2=customerService.getCustomerId(customer.getCustomerId());
			model.addAttribute("customer",c2);
		}
		return "pc/static/personalCenter/personalInfo";
	}
}
