package com.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.domain.Linkman;
import com.crm.domain.PageBean;

public interface LinkmanDao {

	PageBean<Linkman> findByPage(Integer currentPage, Integer pageSize, DetachedCriteria criteria);

	void add(Linkman linkman);

	Linkman findById(Long lkm_id);

	void update(Linkman linkman);

	void delete(Linkman linkman);

}
