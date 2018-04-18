package com.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.crm.dao.LinkmanDao;
import com.crm.domain.Linkman;
import com.crm.domain.PageBean;

@Transactional
public class LinkmanServiceImpl implements LinkmanService {

	private LinkmanDao linkmanDao;
	public void setLinkmanDao(LinkmanDao linkmanDao) {
		this.linkmanDao = linkmanDao;
	}

	public PageBean<Linkman> findByPage(Integer currentPage, Integer pageSize, DetachedCriteria criteria) {
		return linkmanDao.findByPage(currentPage, pageSize, criteria);
	}
	
	
}
