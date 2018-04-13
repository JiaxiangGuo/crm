package com.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.domain.Customer;
import com.crm.domain.PageBean;

public interface CustomerService {

	void add(Customer customer);

	PageBean<Customer> findByPage(Integer currentPage, Integer pageSize, DetachedCriteria criteria);

}