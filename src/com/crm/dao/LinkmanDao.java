package com.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.domain.Linkman;
import com.crm.domain.PageBean;

public interface LinkmanDao {

	PageBean<Linkman> findByPage(Integer currentPage, Integer pageSize, DetachedCriteria criteria);

	void add(Linkman linkman);

}
