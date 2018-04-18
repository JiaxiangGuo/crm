package com.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.crm.domain.Customer;
import com.crm.domain.Linkman;
import com.crm.domain.PageBean;

public class LinkmanDaoImpl extends HibernateDaoSupport implements LinkmanDao{

	public PageBean<Linkman> findByPage(Integer currentPage, Integer pageSize, DetachedCriteria criteria) {
		PageBean<Linkman> page = new PageBean<Linkman>();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		
		//先查询总记录数
		criteria.setProjection(Projections.rowCount());
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list != null && list.size() > 0){
			int totalCount = list.get(0).intValue();
			page.setTotalCount(totalCount);
		}
		
		//分页查询数据，每页显示的数据，使用limit
		criteria.setProjection(null);
		List<Linkman> beanList = (List<Linkman>) this.getHibernateTemplate().findByCriteria(criteria, (currentPage-1)*pageSize, pageSize);
		page.setBeanList(beanList);
		return page;
		
	}

}
