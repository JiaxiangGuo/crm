package com.crm.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.engine.jdbc.batch.internal.NonBatchingBatch;

import com.crm.domain.Dict;
import com.crm.service.DictService;
import com.crm.utils.FastJsonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DictAction extends ActionSupport implements ModelDriven<Dict>{
	private Dict dict = new Dict();
	public Dict getModel() {
		return dict;
	}
	
	private DictService dictService;
	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}
	public String findByCode(){
		List<Dict> list = dictService.findByCode(dict.getDict_type_code());
		String jsonString = FastJsonUtil.toJSONString(list);
		FastJsonUtil.write_json(ServletActionContext.getResponse(), jsonString);
		return NONE;
	}
}
