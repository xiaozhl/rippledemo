package com.study.convertor;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

public class ListConvertor extends DefaultTypeConverter {

	@Override
	public Object convertValue(Map<String, Object> context, Object value,
			Class toType) {
		
		if(toType == List.class)
		{
			String[] arr = (String[]) value;
			JSONArray jsonarray = JSONArray.fromObject(arr[0]);
			return JSONArray.toList(jsonarray);
		}
		return super.convertValue(context, value, toType);
	}

}
