package com.crm.service;

import java.util.List;

import com.crm.domain.Dict;

public interface DictService {

	List<Dict> findByCode(String dict_type_code);

}
