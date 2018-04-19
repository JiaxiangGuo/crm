package com.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.crm.dao.CustomerDao;
import com.crm.domain.Customer;
import com.crm.domain.Linkman;
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
	}
	/**
	 * 删除客户
	 */
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}
	/**
	 * 通过id查找用户
	 */
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}
	/**
	 * 修改客户
	 */
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	/**
	 * 查询所有客户
	 */
	public List<Customer> findAll() {
		return customerDao.findAll();
	}
}
