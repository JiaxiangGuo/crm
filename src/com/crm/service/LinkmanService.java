package com.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.domain.Linkman;
import com.crm.domain.PageBean;

public interface LinkmanService {

	PageBean<Linkman> findByPage(Integer currentPage, Integer pageSize, DetachedCriteria criteria);

	void add(Linkman linkman);

}
