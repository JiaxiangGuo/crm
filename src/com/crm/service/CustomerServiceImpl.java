package com.crm.service;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.crm.dao.CustomerDao;
import com.crm.domain.Customer;
import com.crm.domain.PageBean;

/**
 * 客户模块
 * @author Guojiaxiang
 *
 */
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	/**
	 * 保存客户
	 */
	public void add(Customer customer){
		customerDao.add(customer);
	}
	/**
	 * 分页查询
	 */
	public PageBean<Customer> findByPage(Integer currentPage, Integer pageSize, DetachedCriteria criteria) {
		return customerDao.findByPage(currentPage, pageSize, criteria);
	};
}
