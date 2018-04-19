package com.crm.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.crm.domain.Customer;
import com.crm.domain.Linkman;
import com.crm.domain.PageBean;
import com.crm.service.CustomerService;
import com.crm.service.LinkmanService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
/**
 * 联系人模块
 * @author Guojiaxiang
 *
 */
public class LinkmanAction extends ActionSupport implements ModelDriven<Linkman> {
	
	private static final long serialVersionUID = 1L;
	private Linkman linkman = new Linkman();
	public Linkman getModel() {
		return linkman;
	}
	
	private LinkmanService linkmanService;
	public void setLinkmanService(LinkmanService linkmanService) {
		this.linkmanService = linkmanService;
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
	
	public String findByPage(){
		DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);
		
		String lkm_name = linkman.getLkm_name();
		if(lkm_name != null && !lkm_name.trim().isEmpty()){
			criteria.add(Restrictions.like("lkm_name", "%"+lkm_name+"%"));
		}
		Customer customer = linkman.getCustomer();
		if(customer != null && customer.getCust_id() != null){
			criteria.add(Restrictions.eq("customer.cust_id", customer.getCust_id()));
		}
		PageBean<Linkman> page = linkmanService.findByPage(currentPage, pageSize, criteria);
		//压栈
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("page", page);
		return "list";
	}
	
	/**
	 * 添加联系人
	 */
	public String add(){
		linkmanService.add(linkman);
		return NONE;
	}
	
	
	/**
	 * 修改联系人
	 */
	public String edit(){
		linkman = linkmanService.findById(linkman.getLkm_id());
		ActionContext.getContext().getValueStack().set("linkman", linkman);
		return "edit";
	}
	public String update(){
		linkmanService.update(linkman);
		return NONE;
	}
	
	/**
	 * 删除联系人
	 */
	public String delete(){
		
		linkmanService.delete(linkman);
		return NONE;
	}
	
	
	
}
