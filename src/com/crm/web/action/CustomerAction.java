package com.crm.web.action;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.crm.domain.Customer;
import com.crm.domain.PageBean;
import com.crm.service.CustomerService;
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
	 * 添加客户
	 * @return
	 */
	public String add(){
		customerService.add(customer);
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
		PageBean<Customer> page = customerService.findByPage(currentPage, pageSize, criteria);
		
		//压栈
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("page", page);
		
		return "customerList";
	}

}
