package com.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.crm.domain.Customer;
import com.crm.domain.Linkman;
import com.crm.domain.PageBean;
/**
 * 客户模块
 * @author Guojiaxiang
 *
 */
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {
	/**
	 * 保存客户
	 */
	public void add(Customer customer) {
		this.getHibernateTemplate().save(customer);
	}

	/**
	 * 分页查询
	 */
	public PageBean<Customer> findByPage(Integer currentPage, Integer pageSize, DetachedCriteria criteria) {
		PageBean<Customer> page = new PageBean<Customer>();
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
		List<Customer> beanList = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, (currentPage-1)*pageSize, pageSize);
		page.setBeanList(beanList);
		return page;
	}
	
	/**
	 * 删除客户
	 */
	public void delete(Customer customer) {
		this.getHibernateTemplate().delete(customer);
	}

	/**
	 * 通过id查找用户
	 */
	public Customer findById(Long cust_id) {
		return this.getHibernateTemplate().get(Customer.class, cust_id);
	}
	
	/**
	 * 修改客户
	 */
	public void update(Customer customer) {
		this.getHibernateTemplate().update(customer);
	}

	/**
	 * 查询所有客户
	 */
	public List<Customer> findAll() {
		return (List<Customer>) this.getHibernateTemplate().find("from Customer");
	}

	
}
