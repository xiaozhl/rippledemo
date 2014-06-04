package com.study.datasource;

import org.springframework.util.Assert;

public class DatasourceSwitch {
	private static final ThreadLocal contextHolder = new ThreadLocal();
	public static void setDatasource(String datasource)
	{
		Assert.notNull(datasource,"数据源创建失败。。");
		contextHolder.set(datasource);
	}
	public static void setMaster()
	{
		contextHolder.remove();
		contextHolder.set("master");
	}
	public static void setSlave()
	{
		contextHolder.remove();
		contextHolder.set("slave");
	}
	
	public static String getDataSource()
	{
		if(contextHolder.get() == null)
		{
			return "master";
		}
		return contextHolder.get().toString();
	}
}
