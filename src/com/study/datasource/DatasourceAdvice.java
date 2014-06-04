package com.study.datasource;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;


public class DatasourceAdvice implements MethodBeforeAdvice {

	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		String className = target.getClass().getName();//com.study.business.QueryOrders
		className = className.substring(className.lastIndexOf(".")+1,className.length());//QueryOrders
		
		//如果className 首字符是Query，就认为是查询操作，就指定是slave数据源
		if(className.startsWith("Query") || className.indexOf("Login")!=-1)
		{
			System.out.println("切换到了从服务器");
			DatasourceSwitch.setSlave();
		}
		else
		{
			System.out.println("切换到了主服务器");
			DatasourceSwitch.setMaster();
		}
	}

}
