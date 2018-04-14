package com.crm.service;

import java.util.List;

import com.crm.dao.DictDao;
import com.crm.domain.Dict;

public class DictServiceImpl implements DictService {
	private DictDao dictDao;

	public void setDictDao(DictDao dictDao) {
		this.dictDao = dictDao;
	}

	public List<Dict> findByCode(String dict_type_code) {
		return dictDao.findByCode(dict_type_code);
	}
	
}
