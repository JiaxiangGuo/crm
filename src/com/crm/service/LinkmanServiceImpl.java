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
	
	/**
	 *添加联系人
	 */
	public void add(Linkman linkman) {
		linkmanDao.add(linkman);
		
	}
	/**
	 * 查询联系人
	 */
	public Linkman findById(Long lkm_id) {
		return linkmanDao.findById(lkm_id);
	}
	/**
	 * 修改联系人
	 */
	public void update(Linkman linkman) {
		linkmanDao.update(linkman);
	}
	
	
}
