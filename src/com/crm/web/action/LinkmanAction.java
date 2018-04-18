package com.crm.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.domain.Linkman;
import com.crm.domain.PageBean;
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
		
		PageBean<Linkman> page = linkmanService.findByPage(currentPage, pageSize, criteria);
		//压栈
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("page", page);
		return "list";
	}
	

}