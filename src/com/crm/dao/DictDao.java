package com.crm.dao;

import java.util.List;

import com.crm.domain.Dict;

public interface DictDao {

	List<Dict> findByCode(String dict_type_code);

}
