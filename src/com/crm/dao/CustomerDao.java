package com.crm.dao;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.domain.Customer;
import com.crm.domain.PageBean;

public interface CustomerDao {

	void add(Customer customer);

	PageBean<Customer> findByPage(Integer currentPage, Integer pageSize, DetachedCriteria criteria);

	void delete(Customer customer);

	Customer findById(Long cust_id);

	void update(Customer customer);

}
