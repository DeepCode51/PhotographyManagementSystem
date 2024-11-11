package com.ruanyuan.bas.Controller;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyuan.bas.pojo.Customer;
import com.ruanyuan.bas.service.CustomerService;
import com.ruanyuan.bas.vo.CustomerVo;
import com.ruanyuan.bus.pojo.Order;
import com.ruanyuan.bus.service.OrderService;
import com.ruanyuan.bus.vo.OrderVo;
import com.ruanyuan.sam.pojo.Sample;
import com.ruanyuan.sam.service.SampleService;

/**
 * Excel导出
 */
@Controller
@RequestMapping("excel")
public class ExcelController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private SampleService sampleService;
	
	/**
	 * 客户下载
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("customerdown")
	public void getexcel2(HttpServletResponse response,CustomerVo customerVo) throws Exception {
	  	List<Customer> listcust = customerService.getCustomerAll1(customerVo);
	    //创建一个工作簿
	    @SuppressWarnings("resource")
		Workbook workbook=new XSSFWorkbook(); 
		//Workbook workbook= new XSSFWorkbook();
	    Sheet sheet = workbook.createSheet("客户信息");
	    //定义标题
	    String[] titles ={"客户编号","账号","姓名","性别","地址","联系方式","客户余额"};
	    //行
	    Row row = sheet.createRow(0);
	    //写入标题行
	    for (int i =0;i<titles.length;i++){
	        Cell cell=row.createCell(i);
	        cell.setCellValue(titles[i]);
	    }
	    //根据标题查询行数
	    for (int i = 0;i<listcust.size();i++){
	        //创建第i+1行
	        row=sheet.createRow(i+1);
	        //得到第一个字段的值
	        Customer cust = listcust.get(i);
	        //创建第i个列
	        Cell idCell =row.createCell(0);
	        //写入第i+1行第i列  写入第一个字符
	        idCell.setCellValue(cust.getCustomerId());		            
	        //写第二个字符
	        Cell loginCell =row.createCell(1);
	        loginCell.setCellValue(cust.getLoginName());
	        Cell realCell =row.createCell(2);
	        realCell.setCellValue(cust.getRealName());
	        Cell sexCell =row.createCell(3);
	        sexCell.setCellValue(cust.getSex()==1?"男":"女");
	        Cell addressCell =row.createCell(4);
	        addressCell.setCellValue(cust.getAddress());
	        Cell phoneCell =row.createCell(5);
	        phoneCell.setCellValue(cust.getPhone());
	        Cell balanceCell =row.createCell(6);
	        balanceCell.setCellValue(cust.getBalance());
	    }
	    sheet.setDefaultColumnWidth(15);
	    //创建文件名称
	    String fileName= URLEncoder.encode("客户信息.xlsx","UTF-8");
	    response.setContentType("application/octet-stream");
	    response.setHeader("Content-disposition","attachment;filename="+fileName);
	    response.setHeader("filename",fileName);      
	    //写入文件
	    workbook.write(response.getOutputStream());
	}
	  
	  @RequestMapping("orderdown")
	  public void getexcel3(HttpServletResponse response,OrderVo orderVo) throws Exception {
		  	List<Order> listorder = orderService.getOrderAll2(orderVo);
			//创建一个工作簿
			@SuppressWarnings("resource")
			Workbook workbook=new XSSFWorkbook(); 
			//Workbook workbook= new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("订单信息");
			//定义标题
			String[] titles ={"订单编号","客户名称","样片名称","成交价格","订单时间"};
			//行
			Row row = sheet.createRow(0);
			//写入标题行
			for (int i =0;i<titles.length;i++){
			    Cell cell=row.createCell(i);
			    cell.setCellValue(titles[i]);
			}
			//根据标题查询行数
			for (int i = 0;i<listorder.size();i++){
			    //创建第i+1行
			    row=sheet.createRow(i+1);
			    //得到第一个字段的值
			    Order order = listorder.get(i);
			    //创建第i个列
			    Cell idCell =row.createCell(0);
			    //写入第i+1行第i列  写入第一个字符
			    idCell.setCellValue(order.getOrderId());		            
			    //写第二个字符
			    Cell realCell =row.createCell(1);
			    realCell.setCellValue(order.getCustomer().getRealName());
			    Cell snameCell =row.createCell(2);
			    Sample sample = sampleService.getSampleId(order.getSubscribe().getSampleId());
			    snameCell.setCellValue(sample.getSampleName());
			    Cell priceCell =row.createCell(3);
			    priceCell.setCellValue(order.getSubscribe().getPrice());
			    Cell timeCell =row.createCell(4);
			    timeCell.setCellValue(order.getOrderTime());
			}
			sheet.setDefaultColumnWidth(20);
			//创建文件名称
			String fileName= URLEncoder.encode("订单信息.xlsx","UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition","attachment;filename="+fileName);
			response.setHeader("filename",fileName);      
			//写入文件
			workbook.write(response.getOutputStream());
	  }
	
}
