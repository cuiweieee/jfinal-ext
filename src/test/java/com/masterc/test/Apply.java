package com.masterc.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Page;

/**
 * 商城会员模型
 */
public class Apply extends BaseApply<Apply> {
	
	/** 
	 * @Fields serialVersionUID: 
	 */
	  	
	private static final long serialVersionUID = -4246711497687108581L;
	
	public static final Apply dao = new Apply();
	
	public Page<Apply> findAll(JSONObject jsonObject,Integer pageNumber,Integer pageSize){
		List<String> param = new ArrayList<String>();
		String sql = new StringBuilder().append("select * ").toString();
		StringBuilder sqlExceptSelect = new StringBuilder();
		sqlExceptSelect.append("FROM cc_apply WHERE 1 = 1 ");
		if(StringUtils.isNotBlank(jsonObject.getString("salesman_code"))){
			sqlExceptSelect.append(" and salesman_code = ? ");
			param.add(jsonObject.getString("salesman_code"));
		}
		if(StringUtils.isNotBlank(jsonObject.getString("customer_phone"))){
			sqlExceptSelect.append(" and customer_phone = ? ");
			param.add(jsonObject.getString("customer_phone"));
		}
		sqlExceptSelect.append(" ORDER BY CREATE_DATE DESC");
		return Apply.dao.paginate(pageNumber, pageSize, sql, sqlExceptSelect.toString(),param.toArray());
	}

	public List<Apply> applyExport(JSONObject jsonObject) {
		StringBuilder sqlExceptSelect = new StringBuilder();
		List<String> param = new ArrayList<String>();
		sqlExceptSelect.append("select * FROM cc_apply WHERE 1 = 1 ");
		if(StringUtils.isNotBlank(jsonObject.getString("salesman_code"))){
			sqlExceptSelect.append(" and salesman_code = ? ");
			param.add(jsonObject.getString("salesman_code"));
		}
		if(StringUtils.isNotBlank(jsonObject.getString("customer_phone"))){
			sqlExceptSelect.append(" and customer_phone = ? ");
			param.add(jsonObject.getString("customer_phone"));
		}
		return find(sqlExceptSelect.toString(),param.toArray());
	}
	
}
