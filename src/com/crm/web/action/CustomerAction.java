package com.crm.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.crm.domain.Customer;
import com.crm.domain.Dict;
import com.crm.domain.PageBean;
import com.crm.service.CustomerService;
import com.crm.utils.FastJsonUtil;
import com.crm.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 客户模块
 * @author Guojiaxiang
 *
 */

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	private Customer customer = new Customer();
	public Customer getModel() {
		return customer;
	}
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	/**
	 * 文件的上传，需要在CustomerAction类中定义成员的属性，命名是有规则的！！
	 * private File upload;		// 表示要上传的文件
	 * private String uploadFileName;	表示是上传文件的名称（没有中文乱码）
	 * private String uploadContentType;	表示上传文件的MIME类型
	 * 提供set方法，拦截器就注入值了
	 */
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	
	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = UploadUtils.getUUIDName(uploadFileName);
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	/**
	 * 添加客户
	 * @return
	 * @throws IOException 
	 */
	public String add() throws IOException{
		//保存图片
		if(uploadFileName != null){
			String filePath = "D:\\Program Files\\apache-tomcat-7.0.52\\webapps\\upload\\"+uploadFileName;
			//新建一个文件
			File file = new File(filePath);
			//拷贝文件
			FileUtils.copyFile(upload, file);
			customer.setFile_path(filePath);
		}
		customerService.add(customer);
		return SUCCESS;
	}
	
	/**
	 * 删除客户
	 */
	public String delete(){
		customer = customerService.findById(customer.getCust_id());
		if(customer.getFile_path() != null){
			File file = new File(customer.getFile_path());
			file.delete();
		}
		customerService.delete(customer);
		return NONE;
	}
	
	/**
	 * 修改客户
	 */
	public String edit(){
		customer = customerService.findById(customer.getCust_id());
		ActionContext.getContext().getValueStack().set("customer",customer);
		return "edit";
	}
	public String update() throws IOException{
		if(uploadFileName != null){
			File file;
			if(customer.getFile_path() != null){
				file = new File(customer.getFile_path());
				file.delete();
			}
			String filePath = "D:\\Program Files\\apache-tomcat-7.0.52\\webapps\\upload\\"+uploadFileName;
			file = new File(filePath);
			FileUtils.copyFile(upload, file);
			customer.setFile_path(filePath);
		}
		customerService.update(customer);
		return NONE;
	}
	
	//属性驱动的方式
	//当前页，默认为1
	private Integer currentPage = 1;
	public void setCurrentPage(Integer currentPage) {
		if(currentPage == null){
			currentPage = 1;
		}
		this.currentPage = currentPage;
	}
	//每页显示数据条数
	private Integer pageSize = 3;
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * 分页查询方法
	 * @return
	 */
	public String findByPage(){
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		//拼接查询的条件
		String cust_name = customer.getCust_name();
		if(cust_name != null && !cust_name.trim().isEmpty()){
			criteria.add(Restrictions.like("cust_name", "%"+cust_name+"%"));
		}
		//拼接客户的级别
		Dict level = customer.getLevel();
		if(level != null && !level.getDict_id().trim().isEmpty()){
			criteria.add(Restrictions.eq("level.dict_id", level.getDict_id()));
		}
		//拼接客户的来源
		Dict source = customer.getSource();
		if(source != null && !source.getDict_id().trim().isEmpty()){
			criteria.add(Restrictions.eq("source.dict_id", source.getDict_id()));
		}
		
		//查询
		PageBean<Customer> page = customerService.findByPage(currentPage, pageSize, criteria);
		
		//压栈
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("page", page);
		
		return "customerList";
	}
	/**
	 * 查询所有用户
	 */
	public String findAll(){
		List<Customer> list = customerService.findAll();
		String jsonString = FastJsonUtil.toJSONString(list);
		FastJsonUtil.write_json(ServletActionContext.getResponse(), jsonString);
		System.out.println(jsonString);
		return NONE;
	}
}
